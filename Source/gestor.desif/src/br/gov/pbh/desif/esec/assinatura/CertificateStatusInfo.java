/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  br.com.esec.pkix.x509.X509CertificateImpl
 *  br.com.esec.pkix.x509.X509Principal
 *  br.com.esec.pkix.x509.X509Principal$NameComponent
 *  br.com.esec.sdk.certificate.CertificateStatus
 */
package br.gov.pbh.desif.esec.assinatura;

import br.com.esec.pkix.x509.X509CertificateImpl;
import br.com.esec.pkix.x509.X509Principal;
import br.com.esec.sdk.certificate.CertificateStatus;

public class CertificateStatusInfo {
    private CertificateStatus certStatus;

    public CertificateStatusInfo(CertificateStatus certStatus) {
        this.certStatus = certStatus;
    }

    public CertificateStatus getCertStatus() {
        return this.certStatus;
    }

    public String toString() {
        return this.certStatus.getCertificate().getSubject().getFirst(X509Principal.COMMON_NAME);
    }
}

