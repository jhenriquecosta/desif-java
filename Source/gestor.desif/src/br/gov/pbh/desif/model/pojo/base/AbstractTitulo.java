/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.pojo.base;

import java.io.Serializable;

public abstract class AbstractTitulo
implements Serializable {
    private String id;
    private String descTitulo;
    private boolean obrigatoria;

    public AbstractTitulo() {
    }

    public AbstractTitulo(String id) {
        this.id = id;
    }

    public AbstractTitulo(String id, String descTitulo, boolean obrigatoria) {
        this.id = id;
        this.descTitulo = descTitulo;
        this.obrigatoria = obrigatoria;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescTitulo() {
        return this.descTitulo;
    }

    public void setDescTitulo(String descTitulo) {
        this.descTitulo = descTitulo;
    }

    public boolean getObrigatoria() {
        return this.obrigatoria;
    }

    public void setObrigatoria(boolean obrigatoria) {
        this.obrigatoria = obrigatoria;
    }
}

