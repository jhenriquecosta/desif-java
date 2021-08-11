

package br.gov.pbh.desif.model.pojo.base;

import java.io.Serializable;
import java.util.Date;

public abstract class AbstractIndicesMonetariosId
    implements Serializable
{

    private Date datRef;
    private Short tipIndi;

    public AbstractIndicesMonetariosId()
    {
    }

    public AbstractIndicesMonetariosId(Date datRef, Short tipIndi)
    {
        this.datRef = datRef;
        this.tipIndi = tipIndi;
    }

    public Date getDatRef()
    {
        return datRef;
    }

    public void setDatRef(Date datRef)
    {
        this.datRef = datRef;
    }

    public Short getTipIndi()
    {
        return tipIndi;
    }

    public void setTipIndi(Short tipIndi)
    {
        this.tipIndi = tipIndi;
    }

    public boolean equals(Object other)
    {
        if(this == other)
            return true;
        if(other == null)
            return false;
        if(!(other instanceof AbstractIndicesMonetariosId))
        {
            return false;
        } else
        {
            AbstractIndicesMonetariosId castOther = (AbstractIndicesMonetariosId)other;
            return (getDatRef() == castOther.getDatRef() || getDatRef() != null && castOther.getDatRef() != null && getDatRef().equals(castOther.getDatRef())) && (getTipIndi() == castOther.getTipIndi() || getTipIndi() != null && castOther.getTipIndi() != null && getTipIndi().equals(castOther.getTipIndi()));
        }
    }

    public int hashCode()
    {
        int result = 17;
        result = 37 * result + (getDatRef() != null ? getDatRef().hashCode() : 0);
        result = 37 * result + (getTipIndi() != null ? getTipIndi().hashCode() : 0);
        return result;
    }
}