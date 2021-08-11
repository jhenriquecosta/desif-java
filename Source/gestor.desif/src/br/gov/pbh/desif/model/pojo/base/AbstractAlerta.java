/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.pojo.base;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractAlerta
implements Serializable {
    private String id;
    private String mensagem;
    private String motivo;
    private Set sistemaAlertas = new HashSet(0);
    private Set erroSolucaos = new HashSet(0);

    public AbstractAlerta() {
    }

    public AbstractAlerta(String id) {
        this.id = id;
    }

    public AbstractAlerta(String id, String mensagem, String motivo) {
        this.id = id;
        this.mensagem = mensagem;
        this.motivo = motivo;
    }

    public AbstractAlerta(String id, String mensagem, String motivo, Set sistemaAlertas, Set erroSolucaos) {
        this.id = id;
        this.mensagem = mensagem;
        this.motivo = motivo;
        this.sistemaAlertas = sistemaAlertas;
        this.erroSolucaos = erroSolucaos;
    }

    public Set getErroSolucaos() {
        return this.erroSolucaos;
    }

    public void setErroSolucaos(Set erroSolucaos) {
        this.erroSolucaos = erroSolucaos;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMensagem() {
        return this.mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMotivo() {
        return this.motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Set getSistemaAlertas() {
        return this.sistemaAlertas;
    }

    public void setSistemaAlertas(Set sistemaAlertas) {
        this.sistemaAlertas = sistemaAlertas;
    }
}

