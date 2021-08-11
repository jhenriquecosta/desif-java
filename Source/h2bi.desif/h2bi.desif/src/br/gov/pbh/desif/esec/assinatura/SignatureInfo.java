
package br.gov.pbh.desif.esec.assinatura;

import br.com.esec.pkix.x509.*;
import br.com.esec.pkix.x509.extns.X509SubjectAltNameExtension;
import br.com.esec.pkix.x509.imp.X509GeneralNameList;
import br.com.esec.pkix.x509.imp.X509RFC822Name;
import br.com.esec.sdk.certificate.*;
import java.util.Date;

// Referenced classes of package br.gov.pbh.desif.esec.assinatura:
//            SignatureStatus, PersonType

public class SignatureInfo
{

    private String name;
    private String email;
    private String documentNumber;
    private Date notBefore;
    private Date notAfter;
    private String issuerName;
    private PersonType personType;
    private boolean ICPBrasil;
    private SignatureStatus signatureStatus;
    private Date signatureDate;
    private CertificateStatus certificateChain[];

    public SignatureInfo()
    {
    }

    public SignatureInfo(CertificateStatus certificateChain[], Date signatureDate, SignatureStatus signatureStatus)
    {
        this.certificateChain = certificateChain;
        this.signatureDate = signatureDate;
        this.signatureStatus = signatureStatus;
        X509CertificateImpl signerCert = certificateChain[0].getCertificate();
        notBefore = signerCert.getNotBefore();
        notAfter = signerCert.getNotAfter();
        issuerName = signerCert.getIssuer().getFirst(X509Principal.COMMON_NAME);
        name = signerCert.getSubject().getFirst(X509Principal.COMMON_NAME);
        ICPBrasilInformations icpInfo = ICPBrasilInformations.getICPBrasilInformations(signerCert);
        ICPBrasil = false;
        email = recoverEmail(signerCert);
        if(icpInfo != null)
        {
            ICPBrasil = true;
            if(icpInfo instanceof PersonInformations)
            {
                PersonInformations pfInfo = (PersonInformations)icpInfo;
                personType = PersonType.PessoaFisica;
                documentNumber = pfInfo.getCPF();
            } else
            if(icpInfo instanceof LegalPersonInformation)
            {
                LegalPersonInformation pfInfo = (LegalPersonInformation)icpInfo;
                personType = PersonType.PessoaJuridica;
                documentNumber = pfInfo.getCNPJ();
            }
        }
    }

    private String recoverEmail(X509CertificateImpl cert)
    {
        String email = cert.getSubject().getFirst(X509Principal.EMAIL_ADDRESS);
        if(email == null)
        {
            X509SubjectAltNameExtension san = (X509SubjectAltNameExtension)cert.getExtensions().getExtension(X509SubjectAltNameExtension.OID);
            if(san != null)
            {
                br.com.esec.pkix.x509.imp.X509GeneralName names[] = san.getNames().getNames();
                for(int i = 0; i < names.length; i++)
                    if(names[i] instanceof X509RFC822Name)
                        email = ((X509RFC822Name)names[i]).getText();

            }
        }
        return email;
    }

    public String getName()
    {
        return name;
    }

    public String getEmail()
    {
        return email;
    }

    public String getDocumentNumber()
    {
        return documentNumber;
    }

    public Date getNotBefore()
    {
        return notBefore;
    }

    public Date getNotAfter()
    {
        return notAfter;
    }

    public String getIssuerName()
    {
        return issuerName;
    }

    public PersonType getPersonType()
    {
        return personType;
    }

    public boolean isICPBrasil()
    {
        return ICPBrasil;
    }

    public SignatureStatus getSignatureStatus()
    {
        return signatureStatus;
    }

    public Date getSignatureDate()
    {
        return signatureDate;
    }

    public CertificateStatus[] getCertificateChain()
    {
        return certificateChain;
    }

    public String toString()
    {
        return name;
    }
}