
package br.gov.pbh.desif.esec.assinatura;

import br.com.esec.pkix.x509.*;
import br.com.esec.pkix.x509.extns.X509SubjectAltNameExtension;
import br.com.esec.pkix.x509.imp.X509GeneralNameList;
import br.com.esec.pkix.x509.imp.X509RFC822Name;
import br.com.esec.sdk.certificate.*;
import java.util.Date;

// Referenced classes of package br.gov.pbh.desif.esec.assinatura:
//            CertificateManagerSingleton, CRLManagerSingleton

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

    public Certificado(X509CertificateImpl certificate, Date dataUtilizacao)
    {
        nome = certificate.getSubject().getFirst(X509Principal.COMMON_NAME);
        email = certificate.getSubject().getFirst(X509Principal.EMAIL_ADDRESS);
        X509SubjectAltNameExtension extension = (X509SubjectAltNameExtension)certificate.getExtensions().getExtension(X509SubjectAltNameExtension.OID);
        if(email == null)
        {
            br.com.esec.pkix.x509.imp.X509GeneralName arr$[] = extension.getNames().getNames();
            int len$ = arr$.length;
            for(int i$ = 0; i$ < len$; i$++)
            {
                br.com.esec.pkix.x509.imp.X509GeneralName general = arr$[i$];
                if(general instanceof X509RFC822Name)
                    email = ((X509RFC822Name)general).getText();
            }

        }
        ICPBrasilInformations informations = ICPBrasilInformations.getICPBrasilInformations(certificate);
        if(informations instanceof PersonInformations)
        {
            tipo = "PF";
            cpf = ((PersonInformations)informations).getCPF();
            rg = ((PersonInformations)informations).getRG();
            nascimento = ((PersonInformations)informations).getDataNascimento();
        } else
        if(informations instanceof LegalPersonInformation)
        {
            tipo = "PJ";
            cnpj = ((LegalPersonInformation)informations).getCNPJ();
            rg = null;
            nascimento = null;
        }
        dataEmissao = certificate.getNotBefore();
        dataVencimento = certificate.getNotAfter();
        verificarCertificadoIcpBrasil(certificate, dataUtilizacao);
    }

    private void verificarCertificadoIcpBrasil(X509CertificateImpl certificate, Date dataUtilizacao)
    {
        CertificateVerifier certificateVerifier = new CertificateVerifier(true, CertificateManagerSingleton.getInstance().getCertificateManager(), CRLManagerSingleton.getInstance().getCRLManager(), false);
        X509CertificateImpl x509CertificateImpls[] = new X509CertificateImpl[1];
        x509CertificateImpls[0] = certificate;
        CertificateStatusChain certificateStatusChain = certificateVerifier.verify(x509CertificateImpls, dataUtilizacao);
        if(certificateStatusChain.getCertChainStatus() == 0)
            valido = true;
        else
            valido = false;
    }

    public String getNome()
    {
        return nome;
    }

    public String getEmail()
    {
        return email;
    }

    public String getTipo()
    {
        return tipo;
    }

    public String getCpf()
    {
        return cpf;
    }

    public String getCnpj()
    {
        return cnpj;
    }

    public String getRg()
    {
        return rg;
    }

    public Date getNascimento()
    {
        return nascimento;
    }

    public Date getDataEmissao()
    {
        return dataEmissao;
    }

    public Date getDataVencimento()
    {
        return dataVencimento;
    }

    public boolean isValido()
    {
        return valido;
    }
}