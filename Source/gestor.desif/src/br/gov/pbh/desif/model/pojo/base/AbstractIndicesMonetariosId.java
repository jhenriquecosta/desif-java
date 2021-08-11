/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.pojo.base;

import java.io.Serializable;
import java.util.Date;

public abstract class AbstractIndicesMonetariosId
implements Serializable {
    private Date datRef;
    private Short tipIndi;

    public AbstractIndicesMonetariosId() {
    }

    public AbstractIndicesMonetariosId(Date datRef, Short tipIndi) {
        this.datRef = datRef;
        this.tipIndi = tipIndi;
    }

    public Date getDatRef() {
        return this.datRef;
    }

    public void setDatRef(Date datRef) {
        this.datRef = datRef;
    }

    public Short getTipIndi() {
        return this.tipIndi;
    }

    public void setTipIndi(Short tipIndi) {
        this.tipIndi = tipIndi;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof AbstractIndicesMonetariosId)) {
            return false;
        }
        AbstractIndicesMonetariosId castOther = (AbstractIndicesMonetariosId)other;
        return (this.getDatRef() == castOther.getDatRef() || this.getDatRef() != null && castOther.getDatRef() != null && this.getDatRef().equals(castOther.getDatRef())) && (this.getTipIndi() == castOther.getTipIndi() || this.getTipIndi() != null && castOther.getTipIndi() != null && this.getTipIndi().equals(castOther.getTipIndi()));
    }

    public int hashCode() {
        int result = 17;
        result = 37 * result + (this.getDatRef() == null ? 0 : this.getDatRef().hashCode());
        result = 37 * result + (this.getTipIndi() == null ? 0 : this.getTipIndi().hashCode());
        return result;
    }
}

