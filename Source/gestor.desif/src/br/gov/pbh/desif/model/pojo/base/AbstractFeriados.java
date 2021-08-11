/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.pojo.base;

import br.gov.pbh.desif.model.pojo.FeriadosId;
import java.io.Serializable;

public abstract class AbstractFeriados
implements Serializable {
    private FeriadosId id;
    private Short tipFeri;

    public AbstractFeriados() {
    }

    public AbstractFeriados(FeriadosId id, Short tipFeri) {
        this.id = id;
        this.tipFeri = tipFeri;
    }

    public FeriadosId getId() {
        return this.id;
    }

    public void setId(FeriadosId id) {
        this.id = id;
    }

    public Short getTipFeri() {
        return this.tipFeri;
    }

    public void setTipFeri(Short tipFeri) {
        this.tipFeri = tipFeri;
    }
}

