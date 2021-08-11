

package br.gov.pbh.desif.model.pojo.base;

import java.io.Serializable;
import java.util.Date;

public abstract class AbstractFeriadosId
    implements Serializable
{

    private Date datFeri;
    private String nomFeri;

    public AbstractFeriadosId()
    {
    }

    public AbstractFeriadosId(Date datFeri, String nomFeri)
    {
        this.datFeri = datFeri;
        this.nomFeri = nomFeri;
    }

    public Date getDatFeri()
    {
        return datFeri;
    }

    public void setDatFeri(Date datFeri)
    {
        this.datFeri = datFeri;
    }

    public String getNomFeri()
    {
        return nomFeri;
    }

    public void setNomFeri(String nomFeri)
    {
        this.nomFeri = nomFeri;
    }

    public boolean equals(Object other)
    {
        if(this == other)
            return true;
        if(other == null)
            return false;
        if(!(other instanceof AbstractFeriadosId))
        {
            return false;
        } else
        {
            AbstractFeriadosId castOther = (AbstractFeriadosId)other;
            return (getDatFeri() == castOther.getDatFeri() || getDatFeri() != null && castOther.getDatFeri() != null && getDatFeri().equals(castOther.getDatFeri())) && (getNomFeri() == castOther.getNomFeri() || getNomFeri() != null && castOther.getNomFeri() != null && getNomFeri().equals(castOther.getNomFeri()));
        }
    }

    public int hashCode()
    {
        int result = 17;
        result = 37 * result + (getDatFeri() != null ? getDatFeri().hashCode() : 0);
        result = 37 * result + (getNomFeri() != null ? getNomFeri().hashCode() : 0);
        return result;
    }
}