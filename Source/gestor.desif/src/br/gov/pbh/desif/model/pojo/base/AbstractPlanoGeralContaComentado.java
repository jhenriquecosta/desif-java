/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.pojo.base;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractPlanoGeralContaComentado
implements Serializable {
    private Long id;
    private String conta;
    private String contaCosif;
    private Long numLinhaPgcc;
    private String nome;
    private String contaSupe;
    private String codTribDesif;
    private String descConta;
    private Integer nivel;
    private Set pgccsPaiFilhosForIdnPgccPai = new HashSet(0);
    private Set pgccsPaiFilhosForIdnPgccFilho = new HashSet(0);

    public AbstractPlanoGeralContaComentado() {
    }

    public AbstractPlanoGeralContaComentado(Long numLinhaPgcc) {
        this.numLinhaPgcc = numLinhaPgcc;
    }

    public AbstractPlanoGeralContaComentado(String conta, String contaCosif, Long numLinhaPgcc, String nome, String contaSupe, String codTribDesif, String descConta, Integer nivel, Set pgccsPaiFilhosForIdnPgccPai, Set pgccsPaiFilhosForIdnPgccFilho) {
        this.conta = conta;
        this.contaCosif = contaCosif;
        this.numLinhaPgcc = numLinhaPgcc;
        this.nome = nome;
        this.contaSupe = contaSupe;
        this.codTribDesif = codTribDesif;
        this.descConta = descConta;
        this.nivel = nivel;
        this.pgccsPaiFilhosForIdnPgccPai = pgccsPaiFilhosForIdnPgccPai;
        this.pgccsPaiFilhosForIdnPgccFilho = pgccsPaiFilhosForIdnPgccFilho;
    }

    public AbstractPlanoGeralContaComentado(String conta, String nome, String codTribDesif, String descConta) {
        this.conta = conta;
        this.nome = nome;
        this.codTribDesif = codTribDesif;
        this.descConta = descConta;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConta() {
        return this.conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getContaCosif() {
        return this.contaCosif;
    }

    public void setContaCosif(String contaCosif) {
        this.contaCosif = contaCosif;
    }

    public Long getNumLinhaPgcc() {
        return this.numLinhaPgcc;
    }

    public void setNumLinhaPgcc(Long numLinhaPgcc) {
        this.numLinhaPgcc = numLinhaPgcc;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getContaSupe() {
        return this.contaSupe;
    }

    public void setContaSupe(String contaSupe) {
        this.contaSupe = contaSupe;
    }

    public String getCodTribDesif() {
        return this.codTribDesif;
    }

    public void setCodTribDesif(String codTribDesif) {
        this.codTribDesif = codTribDesif;
    }

    public String getDescConta() {
        return this.descConta;
    }

    public void setDescConta(String descConta) {
        this.descConta = descConta;
    }

    public Integer getNivel() {
        return this.nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public Set getPgccsPaiFilhosForIdnPgccPai() {
        return this.pgccsPaiFilhosForIdnPgccPai;
    }

    public void setPgccsPaiFilhosForIdnPgccPai(Set pgccsPaiFilhosForIdnPgccPai) {
        this.pgccsPaiFilhosForIdnPgccPai = pgccsPaiFilhosForIdnPgccPai;
    }

    public Set getPgccsPaiFilhosForIdnPgccFilho() {
        return this.pgccsPaiFilhosForIdnPgccFilho;
    }

    public void setPgccsPaiFilhosForIdnPgccFilho(Set pgccsPaiFilhosForIdnPgccFilho) {
        this.pgccsPaiFilhosForIdnPgccFilho = pgccsPaiFilhosForIdnPgccFilho;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        AbstractPlanoGeralContaComentado other = (AbstractPlanoGeralContaComentado)obj;
        if (!(this.id == other.id || this.id != null && this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
}

