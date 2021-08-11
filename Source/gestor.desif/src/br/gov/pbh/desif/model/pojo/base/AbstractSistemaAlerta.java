/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.pojo.base;

import br.gov.pbh.desif.model.pojo.Alerta;
import java.io.Serializable;

public class AbstractSistemaAlerta
implements Serializable {
    private Long id;
    private Alerta alerta;
    private Long linha;
    private Integer coluna;
    private Short tipoErro;
    private String registro;
    private String nomeCampo;
    private String valorCampoErro;

    public AbstractSistemaAlerta() {
    }

    public AbstractSistemaAlerta(Long id, Alerta alerta, Long linha, Integer coluna, Short tipoErro, String registro, String nomeCampo) {
        this.id = id;
        this.alerta = alerta;
        this.linha = linha;
        this.coluna = coluna;
        this.tipoErro = tipoErro;
        this.registro = registro;
        this.nomeCampo = nomeCampo;
    }

    public AbstractSistemaAlerta(Long id, Alerta alerta, Long linha, Integer coluna, Short tipoErro, String registro, String nomeCampo, String valorCampoErro) {
        this.id = id;
        this.alerta = alerta;
        this.linha = linha;
        this.coluna = coluna;
        this.tipoErro = tipoErro;
        this.registro = registro;
        this.nomeCampo = nomeCampo;
        this.valorCampoErro = valorCampoErro;
    }

    public Alerta getAlerta() {
        return this.alerta;
    }

    public void setAlerta(Alerta alerta) {
        this.alerta = alerta;
    }

    public Integer getColuna() {
        return this.coluna;
    }

    public void setColuna(Integer coluna) {
        this.coluna = coluna;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLinha() {
        return this.linha;
    }

    public void setLinha(Long linha) {
        this.linha = linha;
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

    public Short getTipoErro() {
        return this.tipoErro;
    }

    public void setTipoErro(Short tipoErro) {
        this.tipoErro = tipoErro;
    }

    public String getValorCampoErro() {
        return this.valorCampoErro;
    }

    public void setValorCampoErro(String valorCampoErro) {
        this.valorCampoErro = valorCampoErro;
    }
}

