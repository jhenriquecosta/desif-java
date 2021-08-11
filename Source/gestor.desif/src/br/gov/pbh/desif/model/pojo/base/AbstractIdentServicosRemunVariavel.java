/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.pojo.base;

import java.io.Serializable;

public abstract class AbstractIdentServicosRemunVariavel
implements Serializable {
    private Long id;
    private String codIdentServRemnVariavel;
    private Long numLinhIdenServPrecVarl;
    private String codSubtitulo;
    private String descServRemnVariavel;

    public AbstractIdentServicosRemunVariavel() {
    }

    public AbstractIdentServicosRemunVariavel(Long id, String codIdentServRemnVariavel, Long numLinhIdenServPrecVarl, String codSubtitulo, String descServRemnVariavel) {
        this.id = id;
        this.codIdentServRemnVariavel = codIdentServRemnVariavel;
        this.numLinhIdenServPrecVarl = numLinhIdenServPrecVarl;
        this.codSubtitulo = codSubtitulo;
        this.descServRemnVariavel = descServRemnVariavel;
    }

    public AbstractIdentServicosRemunVariavel(String codIdentServRemnVariavel, String codSubtitulo, String descServRemnVariavel) {
        this.codIdentServRemnVariavel = codIdentServRemnVariavel;
        this.codSubtitulo = codSubtitulo;
        this.descServRemnVariavel = descServRemnVariavel;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodIdentServRemnVariavel() {
        return this.codIdentServRemnVariavel;
    }

    public void setCodIdentServRemnVariavel(String codIdentServRemnVariavel) {
        this.codIdentServRemnVariavel = codIdentServRemnVariavel;
    }

    public Long getNumLinhIdenServPrecVarl() {
        return this.numLinhIdenServPrecVarl;
    }

    public void setNumLinhIdenServPrecVarl(Long numLinhIdenServPrecVarl) {
        this.numLinhIdenServPrecVarl = numLinhIdenServPrecVarl;
    }

    public String getCodSubtitulo() {
        return this.codSubtitulo;
    }

    public void setCodSubtitulo(String codSubtitulo) {
        this.codSubtitulo = codSubtitulo;
    }

    public String getDescServRemnVariavel() {
        return this.descServRemnVariavel;
    }

    public void setDescServRemnVariavel(String descServRemnVariavel) {
        this.descServRemnVariavel = descServRemnVariavel;
    }
}

