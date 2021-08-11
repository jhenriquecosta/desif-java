
package br.gov.pbh.desif.esec.assinatura;

import br.com.esec.pkix.x509.X509CertificateImpl;
import br.com.esec.pkix.x509.X509Principal;
import br.com.esec.pkix.x509.extns.X509SubjectAltNameExtension;
import br.com.esec.pkix.x509.imp.X509GeneralName;
import br.com.esec.pkix.x509.imp.X509RFC822Name;
import br.com.esec.sdk.certificate.CertificateStatusChain;
import br.com.esec.sdk.certificate.CertificateVerifier;
import br.com.esec.sdk.certificate.ICPBrasilInformations;
import br.com.esec.sdk.certificate.LegalPersonInformation;
import br.com.esec.sdk.certificate.PersonInformations;
import java.util.Date;

public class Certificado 
{
    private String nome;
    private String email;
    private String tipo;
    private String cpf;
    private String cnpj;
    private String rg;
    private Date nascimento;
    private Date dataEmissao;
    private Date dataVencimento;
    private boolean valido;

    public Certificado(X509CertificateImpl certificate, Date dataUtilizacao) {
        ICPBrasilInformations informations;
        this.nome = certificate.getSubject().getFirst(X509Principal.COMMON_NAME);
        this.email = certificate.getSubject().getFirst(X509Principal.EMAIL_ADDRESS);
        X509SubjectAltNameExtension extension = (X509SubjectAltNameExtension)certificate.getExtensions().getExtension(X509SubjectAltNameExtension.OID);
        if (this.email == null) {
            for (X509GeneralName general : extension.getNames().getNames()) 
            {
                if (!(general instanceof X509RFC822Name)) continue;
                this.email = ((X509RFC822Name)general).getText();
            }
        }
        if ((informations = ICPBrasilInformations.getICPBrasilInformations((X509CertificateImpl)certificate)) instanceof PersonInformations) {
            this.tipo = "PF";
            this.cpf = ((PersonInformations)informations).getCPF();
            this.rg = ((PersonInformations)informations).getRG();
            this.nascimento = ((PersonInformations)informations).getDataNascimento();
        } else if (informations instanceof LegalPersonInformation) {
            this.tipo = "PJ";
            this.cnpj = ((LegalPersonInformation)informations).getCNPJ();
            this.rg = null;
            this.nascimento = null;
        }
        this.dataEmissao = certificate.getNotBefore();
        this.dataVencimento = certificate.getNotAfter();
        this.verificarCertificadoIcpBrasil(certificate, dataUtilizacao);
    }

    private void verificarCertificadoIcpBrasil(X509CertificateImpl certificate, Date dataUtilizacao) {
        X509CertificateImpl[] x509CertificateImpls;
        CertificateVerifier certificateVerifier = new CertificateVerifier(true, CertificateManagerSingleton.getInstance().getCertificateManager(), CRLManagerSingleton.getInstance().getCRLManager(), false);
        CertificateStatusChain certificateStatusChain = certificateVerifier.verify(x509CertificateImpls = new X509CertificateImpl[]{certificate}, dataUtilizacao);
        this.valido = certificateStatusChain.getCertChainStatus() == 0;
    }

    public String getNome() {
        return this.nome;
    }

    public String getEmail() {
        return this.email;
    }

    public String getTipo() {
        return this.tipo;
    }

    public String getCpf() {
        return this.cpf;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public String getRg() {
        return this.rg;
    }

    public Date getNascimento() {
        return this.nascimento;
    }

    public Date getDataEmissao() {
        return this.dataEmissao;
    }

    public Date getDataVencimento() {
        return this.dataVencimento;
    }

    public boolean isValido() {
        return this.valido;
    }
}

