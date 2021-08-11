/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.pojo.base;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractErro
implements Serializable {
    private String id;
    private String mensagem;
    private String motivo;
    private Set sistemaErros = new HashSet(0);
    private Set erroSolucaos = new HashSet(0);

    public AbstractErro() {
    }

    public AbstractErro(String id) {
        this.id = id;
    }

    public AbstractErro(String id, String mensagem, String motivo) {
        this.id = id;
        this.mensagem = mensagem;
        this.motivo = motivo;
    }

    public AbstractErro(String id, String mensagem, String motivo, Set sistemaErros, Set erroSolucaos) {
        this.id = id;
        this.mensagem = mensagem;
        this.motivo = motivo;
        this.sistemaErros = sistemaErros;
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

    public Set getSistemaErros() {
        return this.sistemaErros;
    }

    public void setSistemaErros(Set sistemaErros) {
        this.sistemaErros = sistemaErros;
    }

    public Set getErroSolucaos() {
        return this.erroSolucaos;
    }

    public void setErroSolucaos(Set erroSolucaos) {
        this.erroSolucaos = erroSolucaos;
    }
}

