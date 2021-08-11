/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.esec.assinatura;

import br.gov.pbh.desif.esec.assinatura.Certificado;

public class Assinatura {
    private Certificado certificado;
    private boolean integra;

    public Certificado getCertificado() {
        return this.certificado;
    }

    public void setCertificado(Certificado certificado) {
        this.certificado = certificado;
    }

    public boolean isIntegra() {
        return this.integra;
    }

    public void setIntegra(boolean integra) {
        this.integra = integra;
    }
}

