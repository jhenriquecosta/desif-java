/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.pojo.base;

import java.io.Serializable;

public abstract class AbstractPgccsPaiFilhoId
implements Serializable {
    private Long idPgccPai;
    private Long idPgccFilho;

    public AbstractPgccsPaiFilhoId() {
    }

    public AbstractPgccsPaiFilhoId(Long idPgccPai, Long idPgccFilho) {
        this.idPgccPai = idPgccPai;
        this.idPgccFilho = idPgccFilho;
    }

    public Long getIdPgccPai() {
        return this.idPgccPai;
    }

    public void setIdPgccPai(Long idPgccPai) {
        this.idPgccPai = idPgccPai;
    }

    public Long getIdPgccFilho() {
        return this.idPgccFilho;
    }

    public void setIdPgccFilho(Long idPgccFilho) {
        this.idPgccFilho = idPgccFilho;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof AbstractPgccsPaiFilhoId)) {
            return false;
        }
        AbstractPgccsPaiFilhoId castOther = (AbstractPgccsPaiFilhoId)other;
        return (this.getIdPgccPai() == castOther.getIdPgccPai() || this.getIdPgccPai() != null && castOther.getIdPgccPai() != null && this.getIdPgccPai().equals(castOther.getIdPgccPai())) && (this.getIdPgccFilho() == castOther.getIdPgccFilho() || this.getIdPgccFilho() != null && castOther.getIdPgccFilho() != null && this.getIdPgccFilho().equals(castOther.getIdPgccFilho()));
    }

    public int hashCode() {
        int result = 17;
        result = 37 * result + (this.getIdPgccPai() == null ? 0 : this.getIdPgccPai().hashCode());
        result = 37 * result + (this.getIdPgccFilho() == null ? 0 : this.getIdPgccFilho().hashCode());
        return result;
    }
}

