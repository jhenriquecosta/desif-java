/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.pojo.base;

import java.io.Serializable;

public abstract class AbstractTaxasGuias
implements Serializable {
    private Short idnTaxaGuia;
    private Double valTaxaBanc;
    private String desFormMult;
    private String desFormJuro;

    public AbstractTaxasGuias() {
    }

    public AbstractTaxasGuias(Short idnTaxaGuia, Double valTaxaBanc) {
        this.idnTaxaGuia = idnTaxaGuia;
        this.valTaxaBanc = valTaxaBanc;
    }

    public AbstractTaxasGuias(Short idnTaxaGuia, Double valTaxaBanc, String desFormMult, String desFormJuro) {
        this.idnTaxaGuia = idnTaxaGuia;
        this.valTaxaBanc = valTaxaBanc;
        this.desFormMult = desFormMult;
        this.desFormJuro = desFormJuro;
    }

    public Short getIdnTaxaGuia() {
        return this.idnTaxaGuia;
    }

    public void setIdnTaxaGuia(Short idnTaxaGuia) {
        this.idnTaxaGuia = idnTaxaGuia;
    }

    public Double getValTaxaBanc() {
        return this.valTaxaBanc;
    }

    public void setValTaxaBanc(Double valTaxaBanc) {
        this.valTaxaBanc = valTaxaBanc;
    }

    public String getDesFormMult() {
        return this.desFormMult;
    }

    public void setDesFormMult(String desFormMult) {
        this.desFormMult = desFormMult;
    }

    public String getDesFormJuro() {
        return this.desFormJuro;
    }

    public void setDesFormJuro(String desFormJuro) {
        this.desFormJuro = desFormJuro;
    }
}

