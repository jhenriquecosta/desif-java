package br.gov.pbh.desif.esec.assinatura;

import br.com.esec.asn1.*;
import br.com.esec.misc.BaseConvertion;
import br.com.esec.misc.MimeType;
import br.com.esec.pkcs.pkcs11.TokenInformation;
import br.com.esec.pkcs.pkcs11.TokenInformationFlags;
import br.com.esec.pkcs.pkcs11.jcryptoki.CryptokiException;
import br.com.esec.pkcs.pkcs11.jcryptoki.Token;
import br.com.esec.pkcs.pkcs7.*;
import br.com.esec.pkcs.pkcs9.SigningTimeAttribute;
import br.com.esec.pkix.x509.*;
import br.com.esec.sdk.certificate.CertificateStatusChain;
import br.com.esec.sdk.certificate.CertificateVerifier;
import br.com.esec.sdk.device.*;
import br.gov.pbh.desif.esec.config.*;
import br.gov.pbh.desif.esec.config.tab.GeneralConfigurationTab;
import br.gov.pbh.desif.esec.gui.*;
import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.util.*;

// Referenced classes of package br.gov.pbh.desif.esec.assinatura:
//            CancelPasswordException, InvalidPasswordException, SignatureInfo, EnvelopeInfo, 
//            ContentType, SignatureStatus, CertificateManagerSingleton, CRLManagerSingleton

public class DigitalSignatureManager
{

    private static GUIConfiguration guiConfiguration;

    public DigitalSignatureManager()
    {
    }

    public static void showConfiguration()
    {
        if(guiConfiguration != null && guiConfiguration.isDisplayable())
        {
            return;
        } else
        {
            guiConfiguration = new GUIConfiguration();
            return;
        }
    }

    public ConfigurationManager getConfig()
    {
        return ConfigurationManager.getInstance();
    }

    public static void reloadConfiguration()
    {
        GeneralConfigurationTab.getInstance().reloadConfiguration();
    }

    public static void signFile(String fileName, boolean detached)
        throws CancelPasswordException, InvalidPasswordException, GeneralSecurityException, CryptoDeviceException, IOException, ByteSourceException, NoSelectedCertificateException
    {
        File f = new File(fileName);
        if(!f.exists())
            throw new FileNotFoundException(fileName);
        File fAssinatura = new File((new StringBuilder()).append(fileName).append(".p7s").toString());
        if(fAssinatura.exists() && fAssinatura.isFile())
            fAssinatura.delete();
        CryptoDevice cd = getCryptoDevice();
        FileByteSource doc = new FileByteSource(f);
        PKCS7RawMessage rawMsg = new PKCS7RawMessage(doc);
        PKCS7SignedMessage signedMessage = new PKCS7SignedMessage(rawMsg);
        signedMessage.setDetachedSignature(detached);
        CertificateAndKey certAndKey = null;
        if(cd != null)
        {
            CertificateAndKey certList[] = cd.getUserCertificates();
            if(certList == null || certList.length == 0)
                throw new GeneralSecurityException("Nao ha certificados de usuarios disponiveis no repositorio!");
            if(certList.length > 1)
            {
                GUICertificateDialog cDialog = new GUICertificateDialog(certList, null);
                certAndKey = cDialog.getSelectedCertificate();
            } else
            {
                certAndKey = cd.getUserCertificates()[0];
            }
        }
        X509CertificateImpl certChain[] = certAndKey.getCertificateChain();
        PrivateKey privateKey = certAndKey.getPrivateKey();
        SignerInformation signerInformation = new SignerInformation(certChain[0]);
        signerInformation.setMessageDigestAlgorithm("SHA1");
        signerInformation.setEncryptionAlgorithm("RSA");
        signerInformation.setPrivateKey(privateKey);
        new SigningTimeAttribute();
        signerInformation.setAuthenticatedAttributes(new AttributeList());
        signerInformation.getAuthenticatedAttributes().addAttribute(new SigningTimeAttribute(new Date()));
        signedMessage.addSignerInformation(signerInformation);
        if(signedMessage.getCertificates() == null)
            signedMessage.setCertificateSet(new CertificateSet());
        for(int c = 0; c < certChain.length; c++)
            signedMessage.getCertificates().addCertificate(certChain[c]);

        FileOutputStream fo = new FileOutputStream((new StringBuilder()).append(fileName).append(".p7s").toString());
        PKCS7ProviderConfiguration provConf = new PKCS7ProviderConfiguration();
        if(cd != null)
            provConf = cd.getPKCS7ProviderConf();
        ByteSource bs = signedMessage.getEncodedByteSource(provConf);
        ByteSourceStream bss = bs.getReadStream();
        byte buffer[] = new byte[0x10000];
        int len;
        for(len = 0; (len = bss.readBytes(buffer)) == buffer.length;)
            fo.write(buffer);

        fo.write(buffer, 0, len);
        fo.close();
    }

    private static CryptoDevice getCryptoDevice()
        throws CancelPasswordException, InvalidPasswordException, GeneralSecurityException, FileNotFoundException, CryptoDeviceException
    {
        CryptoDevice cryptoDevice = null;
        ConfigurationManager config = ConfigurationManager.getInstance();
        int keyStoreType = config.getKeyStoreType();
        if(keyStoreType == 2)
        {
            String pfxFileName = config.getPKCS12FileNameSign();
            char pfxPassword[] = GUIKeyStorePassword.recoverPassword();
            if(pfxPassword == null)
                throw new CancelPasswordException();
            try
            {
                cryptoDevice = PKCS12CryptoDevice.getInstance(pfxFileName, pfxPassword);
            }
            catch(CryptoDeviceException e)
            {
                if(e.getCause() instanceof FileNotFoundException)
                    throw (FileNotFoundException)e.getCause();
                else
                    throw new InvalidPasswordException();
            }
        } else
        if(keyStoreType == 1)
        {
            char scPin[] = new char[0];
            cryptoDevice = SmartCardCryptoDevice.getInstance(0);
            boolean hasProtectedAuthenticationPath = true;
            try
            {
                hasProtectedAuthenticationPath = ((PKCS11CryptoDevice)cryptoDevice).getToken().getInfo().getFlags().hasProtectedAuthenticationPath();
            }
            catch(CryptokiException e1)
            {
                e1.printStackTrace();
            }
            if(!hasProtectedAuthenticationPath)
            {
                scPin = GUIKeyStorePassword.recoverPassword();
                if(scPin == null)
                    throw new CancelPasswordException();
            }
            try
            {
                ((PKCS11CryptoDevice)cryptoDevice).login(scPin);
            }
            catch(CryptokiException e)
            {
                String msg = e.getMessage();
                if(msg.indexOf("PIN_INCORRECT") != -1)
                    throw new InvalidPasswordException();
                else
                    throw new CryptoDeviceException(msg);
            }
        } else
        if(keyStoreType == 4)
        {
            String pkcs11Library = config.getHSMLib();
            int slotNumber = config.getHSMSlot();
            char scPin[] = null;
            cryptoDevice = HSMCryptoDevice.getInstance(slotNumber, pkcs11Library);
            boolean hasProtectedAuthenticationPath = true;
            try
            {
                hasProtectedAuthenticationPath = ((PKCS11CryptoDevice)cryptoDevice).getToken().getInfo().getFlags().hasProtectedAuthenticationPath();
            }
            catch(CryptokiException e)
            {
                e.printStackTrace();
            }
            if(!hasProtectedAuthenticationPath)
            {
                scPin = GUIKeyStorePassword.recoverPassword();
                if(scPin == null)
                    throw new CancelPasswordException();
            }
            try
            {
                ((PKCS11CryptoDevice)cryptoDevice).login(scPin);
            }
            catch(CryptokiException e)
            {
                throw new InvalidPasswordException();
            }
        } else
        if(keyStoreType == 3)
            cryptoDevice = MSCAPICryptoDevice.getInstance();
        return cryptoDevice;
    }

    public static EnvelopeInfo verifySignature(String signatureFile)
        throws FileNotFoundException, ByteSourceException, GeneralSecurityException
    {
        File contentFile = new File(MimeType.removeExtension(signatureFile));
        if(contentFile.exists())
            return verifySignature(signatureFile, contentFile.getAbsolutePath());
        else
            return verifySignature(signatureFile, null);
    }

    public static EnvelopeInfo verifySignature(String signatureFile, String contentFile)
        throws GeneralSecurityException, FileNotFoundException, ByteSourceException
    {
        FileByteSource doc = new FileByteSource(new File(signatureFile));
        PKCS7SignedMessage signedMessage = (PKCS7SignedMessage)PKCS7Message.getInstance(doc);
        ContentType contentType = ContentType.Attached;
        if(signedMessage.isDetachedSignature())
        {
            if(contentFile == null)
                throw new GeneralSecurityException("Arquivo de conteudo nao pode ser nulo");
            File cf = new File(contentFile);
            if(!cf.exists())
                throw new FileNotFoundException((new StringBuilder()).append("Arquivo de conteudo '").append(contentFile).append("' nao existe").toString());
            FileByteSource content = new FileByteSource(cf);
            try
            {
                signedMessage.setDetachedContent(content);
                contentType = ContentType.Detached;
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        String digestValue = generateDigest(signedMessage);
        String digestType = "SHA256";
        int signersLen = signedMessage.getSignerInformations().size();
        List signatureList = new ArrayList();
        boolean isValid = true;
        for(int i = 0; i < signersLen; i++)
        {
            SignerInformation sigInfo = signedMessage.getSignerInformation(i);
            BigInteger serialNumber = sigInfo.getSerialNumber();
            X509Principal issuer = (X509Principal)sigInfo.getIssuer();
            Date signingDate = null;
            SigningTimeAttribute sigTime = null;
            if(sigInfo.getAuthenticatedAttributes() != null)
            {
                sigTime = (SigningTimeAttribute)sigInfo.getAuthenticatedAttributes().getAttribute(SigningTimeAttribute.OID);
                if(sigTime != null)
                    signingDate = sigTime.getSigningTime();
            }
            X509CertificateImpl signerCert = (X509CertificateImpl)signedMessage.getCertificates().getCertificate(issuer, serialNumber);
            if(signerCert == null)
                throw new GeneralSecurityException("O certificado do signatario n\uFFFDo foi encontrado.");
            SignatureStatus signatureStatus = SignatureStatus.Invalid;
            try
            {
                signedMessage.verifySignature(sigInfo, signerCert.getPublicKey());
                signatureStatus = SignatureStatus.Valid;
            }
            catch(Exception e)
            {
                isValid = false;
            }
            CertificateVerifier certVer = new CertificateVerifier(true, CertificateManagerSingleton.getInstance().getCertificateManager(), CRLManagerSingleton.getInstance().getCRLManager(), false);
            X509CertificateImpl signerCersChain[] = new X509CertificateImpl[1];
            signerCersChain[0] = signerCert;
            Date baseDate = new Date();
            if(sigTime != null)
                baseDate = sigTime.getSigningTime();
            CertificateStatusChain certStatusChain = certVer.verify(signerCersChain, baseDate);
            SignatureInfo signatureInfo = new SignatureInfo(certStatusChain.getCertStatusList(), signingDate, signatureStatus);
            signatureList.add(signatureInfo);
        }

        SignatureStatus generalSigStatus = SignatureStatus.Valid;
        if(!isValid)
            generalSigStatus = SignatureStatus.Invalid;
        EnvelopeInfo envInfo = new EnvelopeInfo(contentFile, signatureFile, generalSigStatus, contentType, digestType, digestValue, signatureList);
        return envInfo;
    }

    private static String generateDigest(PKCS7SignedMessage signedMessage)
        throws ByteSourceException, NoSuchAlgorithmException
    {
        PKCS7RawMessage rawMessage = (PKCS7RawMessage)signedMessage.getSubMessage();
        ByteSource bs = rawMessage.getByteSource();
        ByteSourceStream bss = bs.getReadStream();
        byte buffer[] = new byte[0x10000];
        int len = 0;
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        while((len = bss.readBytes(buffer)) == buffer.length) 
            md.update(buffer);
        if(len > 0)
            md.update(buffer, 0, len);
        return BaseConvertion.toHex(md.digest());
    }

    public static void main(String args[])
        throws Exception
    {
        showConfiguration();
    }

    static 
    {
        try
        {
            AddLicense.checkLicense();
            ConfigurationManager.getInstance().loadProperties(true);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}