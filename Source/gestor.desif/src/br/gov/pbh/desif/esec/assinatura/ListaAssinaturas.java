/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.esec.assinatura;

import br.gov.pbh.desif.esec.assinatura.Assinatura;
import java.util.List;

public class ListaAssinaturas {
    List<Assinatura> assinaturas;
    private boolean assinaturasValidas;
    private boolean certificadosValidos;

    public List<Assinatura> getAssinaturas() {
        return this.assinaturas;
    }

    public void setAssinaturas(List<Assinatura> assinaturas) {
        this.assinaturas = assinaturas;
    }

    public boolean isAssinaturasValidas() {
        return this.assinaturasValidas;
    }

    public void setAssinaturasValidas(boolean assinaturasValidas) {
        this.assinaturasValidas = assinaturasValidas;
    }

    public boolean isCertificadosValidos() {
        return this.certificadosValidos;
    }

    public void setCertificadosValidos(boolean certificadosValidos) {
        this.certificadosValidos = certificadosValidos;
    }
}

