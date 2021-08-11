/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.pojo.base;

import java.io.Serializable;
import java.util.Date;

public abstract class AbstractFeriadosId
implements Serializable {
    private Date datFeri;
    private String nomFeri;

    public AbstractFeriadosId() {
    }

    public AbstractFeriadosId(Date datFeri, String nomFeri) {
        this.datFeri = datFeri;
        this.nomFeri = nomFeri;
    }

    public Date getDatFeri() {
        return this.datFeri;
    }

    public void setDatFeri(Date datFeri) {
        this.datFeri = datFeri;
    }

    public String getNomFeri() {
        return this.nomFeri;
    }

    public void setNomFeri(String nomFeri) {
        this.nomFeri = nomFeri;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof AbstractFeriadosId)) {
            return false;
        }
        AbstractFeriadosId castOther = (AbstractFeriadosId)other;
        return (this.getDatFeri() == castOther.getDatFeri() || this.getDatFeri() != null && castOther.getDatFeri() != null && this.getDatFeri().equals(castOther.getDatFeri())) && (this.getNomFeri() == castOther.getNomFeri() || this.getNomFeri() != null && castOther.getNomFeri() != null && this.getNomFeri().equals(castOther.getNomFeri()));
    }

    public int hashCode() {
        int result = 17;
        result = 37 * result + (this.getDatFeri() == null ? 0 : this.getDatFeri().hashCode());
        result = 37 * result + (this.getNomFeri() == null ? 0 : this.getNomFeri().hashCode());
        return result;
    }
}

