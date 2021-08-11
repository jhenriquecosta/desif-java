/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.pojo.base;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractCosif
implements Serializable {
    private String numeroContaCosif;
    private Date dataCriacao;
    private String nomeContaCosif;
    private String desFuncConta;
    private String numeroContaSuperior;
    private Date dataExtinsao;
    private Integer numNivel;
    private Set CosifPaiFilhosForContaCosifPai = new HashSet(0);
    private Set CosifPaiFilhosForContaCosifFilho = new HashSet(0);

    public AbstractCosif() {
    }

    public AbstractCosif(String numeroContaCosif, Date dataCriacao, String nomeContaCosif, String desFuncConta, Date dataExtinsao, Integer numNivel) {
        this.numeroContaCosif = numeroContaCosif;
        this.dataCriacao = dataCriacao;
        this.nomeContaCosif = nomeContaCosif;
        this.desFuncConta = desFuncConta;
        this.dataExtinsao = dataExtinsao;
        this.numNivel = numNivel;
    }

    public Date getDataCriacao() {
        return this.dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataExtinsao() {
        return this.dataExtinsao;
    }

    public void setDataExtinsao(Date dataExtinsao) {
        this.dataExtinsao = dataExtinsao;
    }

    public String getDesFuncConta() {
        return this.desFuncConta;
    }

    public void setDesFuncConta(String desFuncConta) {
        this.desFuncConta = desFuncConta;
    }

    public String getNomeContaCosif() {
        return this.nomeContaCosif;
    }

    public void setNomeContaCosif(String nomeContaCosif) {
        this.nomeContaCosif = nomeContaCosif;
    }

    public String getNumeroContaCosif() {
        return this.numeroContaCosif;
    }

    public void setNumeroContaCosif(String numeroContaCosif) {
        this.numeroContaCosif = numeroContaCosif;
    }

    public String getNumeroContaSuperior() {
        return this.numeroContaSuperior;
    }

    public void setNumeroContaSuperior(String numeroContaSuperior) {
        this.numeroContaSuperior = numeroContaSuperior;
    }

    public Integer getNumNivel() {
        return this.numNivel;
    }

    public void setNumNivel(Integer numNivel) {
        this.numNivel = numNivel;
    }

    public Set getCosifPaiFilhosForContaCosifFilho() {
        return this.CosifPaiFilhosForContaCosifFilho;
    }

    public void setCosifPaiFilhosForContaCosifFilho(Set CosifPaiFilhosForContaCosifFilho) {
        this.CosifPaiFilhosForContaCosifFilho = CosifPaiFilhosForContaCosifFilho;
    }

    public Set getCosifPaiFilhosForContaCosifPai() {
        return this.CosifPaiFilhosForContaCosifPai;
    }

    public void setCosifPaiFilhosForContaCosifPai(Set CosifPaiFilhosForContaCosifPai) {
        this.CosifPaiFilhosForContaCosifPai = CosifPaiFilhosForContaCosifPai;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        AbstractCosif other = (AbstractCosif)obj;
        if (!(this.numeroContaCosif == other.numeroContaCosif || this.numeroContaCosif != null && this.numeroContaCosif.equals(other.numeroContaCosif))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + (this.numeroContaCosif != null ? this.numeroContaCosif.hashCode() : 0);
        return hash;
    }
}

