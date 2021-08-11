/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.pojo.base;

import br.gov.pbh.desif.model.pojo.Erro;
import java.io.Serializable;

public abstract class AbstractSistemaErro
implements Serializable {
    private Long id;
    private Erro erro;
    private Long linha;
    private Integer coluna;
    private Short tipoErro;
    private String registro;
    private String nomeCampo;
    private String valorCampoErro;

    public AbstractSistemaErro() {
    }

    public AbstractSistemaErro(Long id, Erro erro, Long linha, Integer coluna, Short tipoErro, String registro, String nomeCampo) {
        this.id = id;
        this.erro = erro;
        this.linha = linha;
        this.coluna = coluna;
        this.tipoErro = tipoErro;
        this.registro = registro;
        this.nomeCampo = nomeCampo;
    }

    public AbstractSistemaErro(Long id, Erro erro, Long linha, Integer coluna, Short tipoErro, String registro, String nomeCampo, String valorCampoErro) {
        this.id = id;
        this.erro = erro;
        this.linha = linha;
        this.coluna = coluna;
        this.tipoErro = tipoErro;
        this.registro = registro;
        this.nomeCampo = nomeCampo;
        this.valorCampoErro = valorCampoErro;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Erro getErro() {
        return this.erro;
    }

    public void setErro(Erro erro) {
        this.erro = erro;
    }

    public Long getLinha() {
        return this.linha;
    }

    public void setLinha(Long linha) {
        this.linha = linha;
    }

    public Integer getColuna() {
        return this.coluna;
    }

    public void setColuna(Integer coluna) {
        this.coluna = coluna;
    }

    public Short getTipoErro() {
        return this.tipoErro;
    }

    public void setTipoErro(Short tipoErro) {
        this.tipoErro = tipoErro;
    }

    public String getNomeCampo() {
        return this.nomeCampo;
    }

    public void setNomeCampo(String nomeCampo) {
        this.nomeCampo = nomeCampo;
    }

    public String getRegistro() {
        return this.registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public String getValorCampoErro() {
        return this.valorCampoErro;
    }

    public void setValorCampoErro(String valorCampoErro) {
        this.valorCampoErro = valorCampoErro;
    }
}

