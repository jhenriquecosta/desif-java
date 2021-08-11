
package br.gov.pbh.desif.model.pojo.base;

import java.io.Serializable;

public abstract class AbstractCosifPaiFilhoConta
    implements Serializable
{

    private String CosifPaiFilhosForContaCosifPai;
    private String CosifPaiFilhosForContaCosifFilho;

    public AbstractCosifPaiFilhoConta()
    {
    }

    public AbstractCosifPaiFilhoConta(String CosifPaiFilhosForContaCosifPai, String CosifPaiFilhosForContaCosifFilho)
    {
        this.CosifPaiFilhosForContaCosifPai = CosifPaiFilhosForContaCosifPai;
        this.CosifPaiFilhosForContaCosifFilho = CosifPaiFilhosForContaCosifFilho;
    }

    public String getCosifPaiFilhosForContaCosifFilho()
    {
        return CosifPaiFilhosForContaCosifFilho;
    }

    public void setCosifPaiFilhosForContaCosifFilho(String CosifPaiFilhosForContaCosifFilho)
    {
        this.CosifPaiFilhosForContaCosifFilho = CosifPaiFilhosForContaCosifFilho;
    }

    public String getCosifPaiFilhosForContaCosifPai()
    {
        return CosifPaiFilhosForContaCosifPai;
    }

    public void setCosifPaiFilhosForContaCosifPai(String CosifPaiFilhosForContaCosifPai)
    {
        this.CosifPaiFilhosForContaCosifPai = CosifPaiFilhosForContaCosifPai;
    }
}