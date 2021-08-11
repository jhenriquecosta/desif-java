/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.pojo.base;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractSolucao
implements Serializable {
    private String id;
    private String descSolucao;
    private Set erroSolucaos = new HashSet(0);

    public AbstractSolucao() {
    }

    public AbstractSolucao(String id, String descSolucao) {
        this.id = id;
        this.descSolucao = descSolucao;
    }

    public AbstractSolucao(String id, String descSolucao, Set erroSolucaos) {
        this.id = id;
        this.descSolucao = descSolucao;
        this.erroSolucaos = erroSolucaos;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescSolucao() {
        return this.descSolucao;
    }

    public void setDescSolucao(String descSolucao) {
        this.descSolucao = descSolucao;
    }

    public Set getErroSolucaos() {
        return this.erroSolucaos;
    }

    public void setErroSolucaos(Set erroSolucaos) {
        this.erroSolucaos = erroSolucaos;
    }
}

