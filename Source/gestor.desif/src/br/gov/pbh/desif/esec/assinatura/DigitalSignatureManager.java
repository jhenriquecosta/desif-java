

package br.gov.pbh.desif.esec.assinatura;

import br.com.esec.asn1.ByteSource;
import br.com.esec.asn1.ByteSourceException;
import br.com.esec.asn1.ByteSourceStream;
import br.com.esec.asn1.FileByteSource;
import br.com.esec.misc.BaseConvertion;
import br.com.esec.misc.MimeType;
import br.com.esec.pkcs.pkcs11.jcryptoki.CryptokiException;
import br.com.esec.pkcs.pkcs7.PKCS7Message;
import br.com.esec.pkcs.pkcs7.PKCS7ProviderConfiguration;
import br.com.esec.pkcs.pkcs7.PKCS7RawMessage;
import br.com.esec.pkcs.pkcs7.PKCS7SignedMessage;
import br.com.esec.pkcs.pkcs7.SignerInformation;
import br.com.esec.pkcs.pkcs9.SigningTimeAttribute;
import br.com.esec.pkix.x509.AttributeList;
import br.com.esec.pkix.x509.CertificateSet;
import br.com.esec.pkix.x509.X509Attribute;
import br.com.esec.pkix.x509.X509CertificateImpl;
import br.com.esec.pkix.x509.X509Principal;
import br.com.esec.sdk.certificate.CertificateStatusChain;
import br.com.esec.sdk.certificate.CertificateVerifier;
import br.com.esec.sdk.device.CertificateAndKey;
import br.com.esec.sdk.device.CryptoDevice;
import br.com.esec.sdk.device.CryptoDeviceException;
import br.com.esec.sdk.device.HSMCryptoDevice;
import br.com.esec.sdk.device.MSCAPICryptoDevice;
import br.com.esec.sdk.device.PKCS11CryptoDevice;
import br.com.esec.sdk.device.PKCS12CryptoDevice;
import br.com.esec.sdk.device.SmartCardCryptoDevice;
import br.gov.pbh.desif.esec.config.AddLicense;
import br.gov.pbh.desif.esec.config.ConfigurationManager;
import br.gov.pbh.desif.esec.config.GUIConfiguration;
import br.gov.pbh.desif.esec.config.tab.GeneralConfigurationTab;
import br.gov.pbh.desif.esec.gui.GUICertificateDialog;
import br.gov.pbh.desif.esec.gui.GUIKeyStorePassword;
import br.gov.pbh.desif.esec.gui.NoSelectedCertificateException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;

public class DigitalSignatureManager
{
    private static GUIConfiguration guiConfiguration;

    public static void showConfiguration()
    {
        if (guiConfiguration != null && guiConfiguration.isDisplayable()) 
        {
            return;
        }
        guiConfiguration = new GUIConfiguration();
    }

    public ConfigurationManager getConfig() 
    {
        return ConfigurationManager.getInstance();
    }

    public static void reloadConfiguration() 
    {
        GeneralConfigurationTab.getInstance().reloadConfiguration();
    }

    public static void signFile(String fileName, boolean detached) throws CancelPasswordException, InvalidPasswordException, GeneralSecurityException, CryptoDeviceException, IOException, ByteSourceException, NoSelectedCertificateException {
        File f = new File(fileName);
        if (!f.exists())
        {
            throw new FileNotFoundException(fileName);
        }
        File fAssinatura = new File(fileName + ".p7s");
        if (fAssinatura.exists() && fAssinatura.isFile()) {
            fAssinatura.delete();
        }
        CryptoDevice cd = DigitalSignatureManager.getCryptoDevice();
        FileByteSource doc = new FileByteSource(f);
        PKCS7RawMessage rawMsg = new PKCS7RawMessage((ByteSource)doc);
        PKCS7SignedMessage signedMessage = new PKCS7SignedMessage((PKCS7Message)rawMsg);
        signedMessage.setDetachedSignature(detached);
        CertificateAndKey certAndKey = null;
        if (cd != null) 
        {
            CertificateAndKey[] certList = cd.getUserCertificates();
            if (certList == null || certList.length == 0) {
                throw new GeneralSecurityException("Nao ha certificados de usuarios disponiveis no repositorio!");
            }
            if (certList.length > 1) {
                GUICertificateDialog cDialog = new GUICertificateDialog(certList, null);
                certAndKey = cDialog.getSelectedCertificate();
            } else {
                certAndKey = cd.getUserCertificates()[0];
            }
        }
        X509CertificateImpl[] certChain = certAndKey.getCertificateChain();
        PrivateKey privateKey = certAndKey.getPrivateKey();
        SignerInformation signerInformation = new SignerInformation((Certificate)certChain[0]);
        signerInformation.setMessageDigestAlgorithm("SHA1");
        signerInformation.setEncryptionAlgorithm("RSA");
        signerInformation.setPrivateKey(privateKey);
        new SigningTimeAttribute();
        signerInformation.setAuthenticatedAttributes(new AttributeList());
        signerInformation.getAuthenticatedAttributes().addAttribute((X509Attribute)new SigningTimeAttribute(new Date()));
        signedMessage.addSignerInformation(signerInformation);
        if (signedMessage.getCertificates() == null) {
            signedMessage.setCertificateSet(new CertificateSet());
        }
        for (int c = 0; c < certChain.length; ++c) {
            signedMessage.getCertificates().addCertificate((X509Certificate)certChain[c]);
        }
        FileOutputStream fo = new FileOutputStream(fileName + ".p7s");
        PKCS7ProviderConfiguration provConf = new PKCS7ProviderConfiguration();
        if (cd != null) {
            provConf = cd.getPKCS7ProviderConf();
        }
        ByteSource bs = signedMessage.getEncodedByteSource(provConf);
        ByteSourceStream bss = bs.getReadStream();
        byte[] buffer = new byte[65536];
        int len = 0;
        while ((len = bss.readBytes(buffer)) == buffer.length) {
            fo.write(buffer);
        }
        fo.write(buffer, 0, len);
        fo.close();
    }

    private static CryptoDevice getCryptoDevice() throws CancelPasswordException, InvalidPasswordException, GeneralSecurityException, FileNotFoundException, CryptoDeviceException {
        CryptoDevice cryptoDevice = null;
        ConfigurationManager config = ConfigurationManager.getInstance();
        int keyStoreType = config.getKeyStoreType();
        if (keyStoreType == 2) {
            String pfxFileName = config.getPKCS12FileNameSign();
            char[] pfxPassword = GUIKeyStorePassword.recoverPassword();
            if (pfxPassword == null) {
                throw new CancelPasswordException();
            }
            try 
            {
               cryptoDevice = PKCS12CryptoDevice.getInstance(pfxFileName, pfxPassword);
                
            }
            catch (CryptoDeviceException e) {
                if (e.getCause() instanceof FileNotFoundException) {
                    throw (FileNotFoundException)e.getCause();
                }
                throw new InvalidPasswordException();
            }
        }
        if (keyStoreType == 1) {
            char[] scPin = new char[]{};
            cryptoDevice = SmartCardCryptoDevice.getInstance((int)0);
            boolean hasProtectedAuthenticationPath = true;
            try {
                hasProtectedAuthenticationPath = ((PKCS11CryptoDevice)cryptoDevice).getToken().getInfo().getFlags().hasProtectedAuthenticationPath();
            }
            catch (CryptokiException e1)
            {
                e1.printStackTrace();
            }
            if (!hasProtectedAuthenticationPath && (scPin = GUIKeyStorePassword.recoverPassword()) == null) 
            {
                throw new CancelPasswordException();
            }
            try {
                ((PKCS11CryptoDevice)cryptoDevice).login(scPin);
            }
            catch (CryptokiException e) {
                String msg = e.getMessage();
                if (msg.indexOf("PIN_INCORRECT") != -1) 
                {
                    throw new InvalidPasswordException();
                }
                throw new CryptoDeviceException(msg);
            }
        }
        if (keyStoreType == 4) {
            String pkcs11Library = config.getHSMLib();
            int slotNumber = config.getHSMSlot();
            char[] scPin = null;
            cryptoDevice = HSMCryptoDevice.getInstance((int)slotNumber, (String)pkcs11Library);
            boolean hasProtectedAuthenticationPath = true;
            try {
                hasProtectedAuthenticationPath = ((PKCS11CryptoDevice)cryptoDevice).getToken().getInfo().getFlags().hasProtectedAuthenticationPath();
            }
            catch (CryptokiException e) {
                e.printStackTrace();
            }
            if (!hasProtectedAuthenticationPath && (scPin = GUIKeyStorePassword.recoverPassword()) == null) {
                throw new CancelPasswordException();
            }
            try {
                ((PKCS11CryptoDevice)cryptoDevice).login(scPin);
            }
            catch (CryptokiException e) {
                throw new InvalidPasswordException();
            }
        }
        if (keyStoreType == 3) {
            cryptoDevice = MSCAPICryptoDevice.getInstance();
        }
        return cryptoDevice;
    }

    public static EnvelopeInfo verifySignature(String signatureFile) throws FileNotFoundException, ByteSourceException, GeneralSecurityException {
        File contentFile = new File(MimeType.removeExtension((String)signatureFile));
        if (contentFile.exists()) {
            return DigitalSignatureManager.verifySignature(signatureFile, contentFile.getAbsolutePath());
        }
        return DigitalSignatureManager.verifySignature(signatureFile, null);
    }

    public static EnvelopeInfo verifySignature(String signatureFile, String contentFile) throws GeneralSecurityException, FileNotFoundException, ByteSourceException {
        FileByteSource doc = new FileByteSource(new File(signatureFile));
        PKCS7SignedMessage signedMessage = (PKCS7SignedMessage)PKCS7Message.getInstance((ByteSource)doc);
        ContentType contentType = ContentType.Attached;
        if (signedMessage.isDetachedSignature()) {
            if (contentFile == null) {
                throw new GeneralSecurityException("Arquivo de conteudo nao pode ser nulo");
            }
            File cf = new File(contentFile);
            if (!cf.exists()) {
                throw new FileNotFoundException("Arquivo de conteudo '" + contentFile + "' nao existe");
            }
            FileByteSource content = new FileByteSource(cf);
            try {
                signedMessage.setDetachedContent((ByteSource)content);
                contentType = ContentType.Detached;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        String digestValue = DigitalSignatureManager.generateDigest(signedMessage);
        String digestType = "SHA256";
        int signersLen = signedMessage.getSignerInformations().size();
        ArrayList<SignatureInfo> signatureList = new ArrayList<SignatureInfo>();
        boolean isValid = true;
        for (int i = 0; i < signersLen; ++i) {
            X509CertificateImpl signerCert;
            SignerInformation sigInfo = signedMessage.getSignerInformation(i);
            BigInteger serialNumber = sigInfo.getSerialNumber();
            X509Principal issuer = (X509Principal)sigInfo.getIssuer();
            Date signingDate = null;
            SigningTimeAttribute sigTime = null;
            if (sigInfo.getAuthenticatedAttributes() != null && (sigTime = (SigningTimeAttribute)sigInfo.getAuthenticatedAttributes().getAttribute(SigningTimeAttribute.OID)) != null) {
                signingDate = sigTime.getSigningTime();
            }
            if ((signerCert = (X509CertificateImpl)signedMessage.getCertificates().getCertificate((Principal)issuer, serialNumber)) == null) {
                throw new GeneralSecurityException("O certificado do signatario n\ufffdo foi encontrado.");
            }
            SignatureStatus signatureStatus = SignatureStatus.Invalid;
            try {
                signedMessage.verifySignature(sigInfo, signerCert.getPublicKey());
                signatureStatus = SignatureStatus.Valid;
            }
            catch (Exception e) {
                isValid = false;
            }
            CertificateVerifier certVer = new CertificateVerifier(true, CertificateManagerSingleton.getInstance().getCertificateManager(), CRLManagerSingleton.getInstance().getCRLManager(), false);
            X509CertificateImpl[] signerCersChain = new X509CertificateImpl[]{signerCert};
            Date baseDate = new Date();
            if (sigTime != null) {
                baseDate = sigTime.getSigningTime();
            }
            CertificateStatusChain certStatusChain = certVer.verify(signerCersChain, baseDate);
            SignatureInfo signatureInfo = new SignatureInfo(certStatusChain.getCertStatusList(), signingDate, signatureStatus);
            signatureList.add(signatureInfo);
        }
        SignatureStatus generalSigStatus = SignatureStatus.Valid;
        if (!isValid) {
            generalSigStatus = SignatureStatus.Invalid;
        }
        EnvelopeInfo envInfo = new EnvelopeInfo(contentFile, signatureFile, generalSigStatus, contentType, digestType, digestValue, signatureList);
        return envInfo;
    }

    private static String generateDigest(PKCS7SignedMessage signedMessage) throws ByteSourceException, NoSuchAlgorithmException {
        PKCS7RawMessage rawMessage = (PKCS7RawMessage)signedMessage.getSubMessage();
        ByteSource bs = rawMessage.getByteSource();
        ByteSourceStream bss = bs.getReadStream();
        byte[] buffer = new byte[65536];
        int len = 0;
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        while ((len = bss.readBytes(buffer)) == buffer.length) {
            md.update(buffer);
        }
        if (len > 0) {
            md.update(buffer, 0, len);
        }
        return BaseConvertion.toHex((byte[])md.digest());
    }

    public static void main(String[] args) throws Exception {
        DigitalSignatureManager.showConfiguration();
    }

    static {
        try {
            AddLicense.checkLicense();
            ConfigurationManager.getInstance().loadProperties(true);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

