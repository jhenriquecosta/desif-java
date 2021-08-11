/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.pojo.base;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractListaServico
implements Serializable {
    private String id;
    private String nomeListaServico;
    private Date dataInicio;
    private Date dataFim;
    private Set codTributacaos = new HashSet(0);

    public AbstractListaServico() {
    }

    public AbstractListaServico(String id, String nomeListaServico, Date dataInicio) {
        this.id = id;
        this.nomeListaServico = nomeListaServico;
        this.dataInicio = dataInicio;
    }

    public AbstractListaServico(String id, String nomeListaServico, Date dataInicio, Date dataFim, Set codTributacaos) {
        this.id = id;
        this.nomeListaServico = nomeListaServico;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.codTributacaos = codTributacaos;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomeListaServico() {
        return this.nomeListaServico;
    }

    public void setNomeListaServico(String nomeListaServico) {
        this.nomeListaServico = nomeListaServico;
    }

    public Date getDataInicio() {
        return this.dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return this.dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Set getCodTributacaos() {
        return this.codTributacaos;
    }

    public void setCodTributacaos(Set codTributacaos) {
        this.codTributacaos = codTributacaos;
    }
}

