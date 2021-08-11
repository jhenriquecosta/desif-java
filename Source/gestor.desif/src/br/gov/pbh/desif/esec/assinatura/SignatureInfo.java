
package br.gov.pbh.desif.esec.assinatura;

import br.com.esec.pkix.x509.X509CertificateImpl;
import br.com.esec.pkix.x509.X509Extensions;
import br.com.esec.pkix.x509.X509Principal;
import br.com.esec.pkix.x509.extns.X509ExtensionObject;
import br.com.esec.pkix.x509.extns.X509SubjectAltNameExtension;
import br.com.esec.pkix.x509.imp.X509GeneralName;
import br.com.esec.pkix.x509.imp.X509GeneralNameList;
import br.com.esec.pkix.x509.imp.X509RFC822Name;
import br.com.esec.sdk.certificate.CertificateStatus;
import br.com.esec.sdk.certificate.ICPBrasilInformations;
import br.com.esec.sdk.certificate.LegalPersonInformation;
import br.com.esec.sdk.certificate.PersonInformations;
import br.gov.pbh.desif.esec.assinatura.PersonType;
import br.gov.pbh.desif.esec.assinatura.SignatureStatus;
import java.util.Date;

public class SignatureInfo {
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
    private CertificateStatus[] certificateChain;

    public SignatureInfo() {
    }

    public SignatureInfo(CertificateStatus[] certificateChain, Date signatureDate, SignatureStatus signatureStatus) {
        this.certificateChain = certificateChain;
        this.signatureDate = signatureDate;
        this.signatureStatus = signatureStatus;
        X509CertificateImpl signerCert = certificateChain[0].getCertificate();
        this.notBefore = signerCert.getNotBefore();
        this.notAfter = signerCert.getNotAfter();
        this.issuerName = signerCert.getIssuer().getFirst(X509Principal.COMMON_NAME);
        this.name = signerCert.getSubject().getFirst(X509Principal.COMMON_NAME);
        ICPBrasilInformations icpInfo = ICPBrasilInformations.getICPBrasilInformations((X509CertificateImpl)signerCert);
        this.ICPBrasil = false;
        this.email = this.recoverEmail(signerCert);
        if (icpInfo != null) {
            this.ICPBrasil = true;
            if (icpInfo instanceof PersonInformations) {
                PersonInformations pfInfo = (PersonInformations)icpInfo;
                this.personType = PersonType.PessoaFisica;
                this.documentNumber = pfInfo.getCPF();
            } else if (icpInfo instanceof LegalPersonInformation) {
                LegalPersonInformation pfInfo = (LegalPersonInformation)icpInfo;
                this.personType = PersonType.PessoaJuridica;
                this.documentNumber = pfInfo.getCNPJ();
            }
        }
    }

    private String recoverEmail(X509CertificateImpl cert) {
        X509SubjectAltNameExtension san;
        String email = cert.getSubject().getFirst(X509Principal.EMAIL_ADDRESS);
        if (email == null && (san = (X509SubjectAltNameExtension)cert.getExtensions().getExtension(X509SubjectAltNameExtension.OID)) != null) {
            X509GeneralName[] names = san.getNames().getNames();
            for (int i = 0; i < names.length; ++i) {
                if (!(names[i] instanceof X509RFC822Name)) continue;
                email = ((X509RFC822Name)names[i]).getText();
            }
        }
        return email;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getDocumentNumber() {
        return this.documentNumber;
    }

    public Date getNotBefore() {
        return this.notBefore;
    }

    public Date getNotAfter() {
        return this.notAfter;
    }

    public String getIssuerName() {
        return this.issuerName;
    }

    public PersonType getPersonType() {
        return this.personType;
    }

    public boolean isICPBrasil() {
        return this.ICPBrasil;
    }

    public SignatureStatus getSignatureStatus() {
        return this.signatureStatus;
    }

    public Date getSignatureDate() {
        return this.signatureDate;
    }

    public CertificateStatus[] getCertificateChain() {
        return this.certificateChain;
    }

    public String toString() {
        return this.name;
    }
}

