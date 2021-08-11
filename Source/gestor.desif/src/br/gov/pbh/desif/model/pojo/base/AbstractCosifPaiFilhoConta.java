/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.pojo.base;

import java.io.Serializable;

public abstract class AbstractCosifPaiFilhoConta
implements Serializable {
    private String CosifPaiFilhosForContaCosifPai;
    private String CosifPaiFilhosForContaCosifFilho;

    public AbstractCosifPaiFilhoConta() {
    }

    public AbstractCosifPaiFilhoConta(String CosifPaiFilhosForContaCosifPai, String CosifPaiFilhosForContaCosifFilho) {
        this.CosifPaiFilhosForContaCosifPai = CosifPaiFilhosForContaCosifPai;
        this.CosifPaiFilhosForContaCosifFilho = CosifPaiFilhosForContaCosifFilho;
    }

    public String getCosifPaiFilhosForContaCosifFilho() {
        return this.CosifPaiFilhosForContaCosifFilho;
    }

    public void setCosifPaiFilhosForContaCosifFilho(String CosifPaiFilhosForContaCosifFilho) {
        this.CosifPaiFilhosForContaCosifFilho = CosifPaiFilhosForContaCosifFilho;
    }

    public String getCosifPaiFilhosForContaCosifPai() {
        return this.CosifPaiFilhosForContaCosifPai;
    }

    public void setCosifPaiFilhosForContaCosifPai(String CosifPaiFilhosForContaCosifPai) {
        this.CosifPaiFilhosForContaCosifPai = CosifPaiFilhosForContaCosifPai;
    }
}

