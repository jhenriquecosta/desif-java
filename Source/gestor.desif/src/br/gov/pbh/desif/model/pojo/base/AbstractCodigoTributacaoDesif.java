/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.pojo.base;

import br.gov.pbh.desif.model.pojo.ListaServico;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractCodigoTributacaoDesif
implements Serializable {
    private String id;
    private ListaServico listaServico;
    private String descCodigoTributacao;
    private Set codTribuMunicipals = new HashSet(0);

    public AbstractCodigoTributacaoDesif() {
    }

    public AbstractCodigoTributacaoDesif(String id) {
        this.id = id;
    }

    public AbstractCodigoTributacaoDesif(String id, ListaServico listaServico, String descCodigoTributacao) {
        this.id = id;
        this.listaServico = listaServico;
        this.descCodigoTributacao = descCodigoTributacao;
    }

    public AbstractCodigoTributacaoDesif(String id, ListaServico listaServico, String descCodigoTributacao, Set codTribuMunicipals) {
        this.id = id;
        this.listaServico = listaServico;
        this.descCodigoTributacao = descCodigoTributacao;
        this.codTribuMunicipals = codTribuMunicipals;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ListaServico getListaServico() {
        return this.listaServico;
    }

    public void setListaServico(ListaServico listaServico) {
        this.listaServico = listaServico;
    }

    public String getDescCodigoTributacao() {
        return this.descCodigoTributacao;
    }

    public void setDescCodigoTributacao(String descCodigoTributacao) {
        this.descCodigoTributacao = descCodigoTributacao;
    }

    public Set getCodTribuMunicipals() {
        return this.codTribuMunicipals;
    }

    public void setCodTribuMunicipals(Set codTribuMunicipals) {
        this.codTribuMunicipals = codTribuMunicipals;
    }
}

