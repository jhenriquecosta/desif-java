
package br.gov.pbh.desif.model.pojo.base;

import java.io.Serializable;

public abstract class AbstractPgccsPaiFilhoId
    implements Serializable
{

    private Long idPgccPai;
    private Long idPgccFilho;

    public AbstractPgccsPaiFilhoId()
    {
    }

    public AbstractPgccsPaiFilhoId(Long idPgccPai, Long idPgccFilho)
    {
        this.idPgccPai = idPgccPai;
        this.idPgccFilho = idPgccFilho;
    }

    public Long getIdPgccPai()
    {
        return idPgccPai;
    }

    public void setIdPgccPai(Long idPgccPai)
    {
        this.idPgccPai = idPgccPai;
    }

    public Long getIdPgccFilho()
    {
        return idPgccFilho;
    }

    public void setIdPgccFilho(Long idPgccFilho)
    {
        this.idPgccFilho = idPgccFilho;
    }

    public boolean equals(Object other)
    {
        if(this == other)
            return true;
        if(other == null)
            return false;
        if(!(other instanceof AbstractPgccsPaiFilhoId))
        {
            return false;
        } else
        {
            AbstractPgccsPaiFilhoId castOther = (AbstractPgccsPaiFilhoId)other;
            return (getIdPgccPai() == castOther.getIdPgccPai() || getIdPgccPai() != null && castOther.getIdPgccPai() != null && getIdPgccPai().equals(castOther.getIdPgccPai())) && (getIdPgccFilho() == castOther.getIdPgccFilho() || getIdPgccFilho() != null && castOther.getIdPgccFilho() != null && getIdPgccFilho().equals(castOther.getIdPgccFilho()));
        }
    }

    public int hashCode()
    {
        int result = 17;
        result = 37 * result + (getIdPgccPai() != null ? getIdPgccPai().hashCode() : 0);
        result = 37 * result + (getIdPgccFilho() != null ? getIdPgccFilho().hashCode() : 0);
        return result;
    }
}