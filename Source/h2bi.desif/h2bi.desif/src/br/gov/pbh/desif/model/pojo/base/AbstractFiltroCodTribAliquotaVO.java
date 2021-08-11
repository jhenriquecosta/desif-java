
package br.gov.pbh.desif.model.pojo.base;

import java.io.Serializable;

public abstract class AbstractFiltroCodTribAliquotaVO
    implements Serializable
{

    private String codigoTributacaoDesIf;
    private Double valorAliquotaIssqn;

    public AbstractFiltroCodTribAliquotaVO()
    {
    }

    public AbstractFiltroCodTribAliquotaVO(String codigoTributacaoDesIf, Double valorAliquotaIssqn)
    {
        this.codigoTributacaoDesIf = codigoTributacaoDesIf;
        this.valorAliquotaIssqn = valorAliquotaIssqn;
    }

    public String getCodigoTributacaoDesIf()
    {
        return codigoTributacaoDesIf;
    }

    public void setCodigoTributacaoDesIf(String codigoTributacaoDesIf)
    {
        this.codigoTributacaoDesIf = codigoTributacaoDesIf;
    }

    public Double getValorAliquotaIssqn()
    {
        return valorAliquotaIssqn;
    }

    public void setValorAliquotaIssqn(Double valorAliquotaIssqn)
    {
        this.valorAliquotaIssqn = valorAliquotaIssqn;
    }
}