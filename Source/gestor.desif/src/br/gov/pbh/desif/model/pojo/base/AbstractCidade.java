/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.pojo.base;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractCidade
implements Serializable {
    private long id;
    private String nomeCidade;
    private String uf;
    private Set codTribuMunicipals = new HashSet(0);

    public AbstractCidade() {
    }

    public AbstractCidade(Long id) {
        this.id = id;
    }

    public AbstractCidade(long id, String nomeCidade, String uf) {
        this.id = id;
        this.nomeCidade = nomeCidade;
        this.uf = uf;
    }

    public AbstractCidade(long id, String nomeCidade, String uf, Set codTribuMunicipals) {
        this.id = id;
        this.nomeCidade = nomeCidade;
        this.uf = uf;
        this.codTribuMunicipals = codTribuMunicipals;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeCidade() {
        return this.nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }

    public String getUf() {
        return this.uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Set getCodTribuMunicipals() {
        return this.codTribuMunicipals;
    }

    public void setCodTribuMunicipals(Set codTribuMunicipals) {
        this.codTribuMunicipals = codTribuMunicipals;
    }
}

