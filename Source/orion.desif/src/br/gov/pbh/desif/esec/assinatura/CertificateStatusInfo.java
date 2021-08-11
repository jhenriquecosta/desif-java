
package br.gov.pbh.desif.esec.assinatura;

import br.com.esec.pkix.x509.X509CertificateImpl;
import br.com.esec.pkix.x509.X509Principal;
import br.com.esec.sdk.certificate.CertificateStatus;

public class CertificateStatusInfo
{

    private CertificateStatus certStatus;

    public CertificateStatusInfo(CertificateStatus certStatus)
    {
        this.certStatus = certStatus;
    }

    public CertificateStatus getCertStatus()
    {
        return certStatus;
    }

    public String toString()
    {
        return certStatus.getCertificate().getSubject().getFirst(X509Principal.COMMON_NAME);
    }
}