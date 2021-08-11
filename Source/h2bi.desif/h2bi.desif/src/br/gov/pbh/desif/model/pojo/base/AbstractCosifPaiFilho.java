

package br.gov.pbh.desif.model.pojo.base;

import br.gov.pbh.desif.model.pojo.Cosif;
import br.gov.pbh.desif.model.pojo.CosifPaiFilhoConta;
import java.io.Serializable;

public abstract class AbstractCosifPaiFilho
    implements Serializable
{

    private CosifPaiFilhoConta conta;
    private Cosif CosifPaiFilhosForContaCosifPai;
    private Cosif CosifPaiFilhosForContaCosifFilho;

    public AbstractCosifPaiFilho()
    {
    }

    public AbstractCosifPaiFilho(CosifPaiFilhoConta conta, Cosif CosifPaiFilhosForContaCosifPai, Cosif CosifPaiFilhosForContaCosifFilho)
    {
        this.conta = conta;
        this.CosifPaiFilhosForContaCosifPai = CosifPaiFilhosForContaCosifPai;
        this.CosifPaiFilhosForContaCosifFilho = CosifPaiFilhosForContaCosifFilho;
    }

    public Cosif getCosifPaiFilhosForContaCosifFilho()
    {
        return CosifPaiFilhosForContaCosifFilho;
    }

    public void setCosifPaiFilhosForContaCosifFilho(Cosif CosifPaiFilhosForContaCosifFilho)
    {
        this.CosifPaiFilhosForContaCosifFilho = CosifPaiFilhosForContaCosifFilho;
    }

    public Cosif getCosifPaiFilhosForContaCosifPai()
    {
        return CosifPaiFilhosForContaCosifPai;
    }

    public void setCosifPaiFilhosForContaCosifPai(Cosif CosifPaiFilhosForContaCosifPai)
    {
        this.CosifPaiFilhosForContaCosifPai = CosifPaiFilhosForContaCosifPai;
    }

    public CosifPaiFilhoConta getConta()
    {
        return conta;
    }

    public void setConta(CosifPaiFilhoConta conta)
    {
        this.conta = conta;
    }
}