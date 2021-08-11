/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.pojo.base;

import java.io.Serializable;
import java.util.Date;

public abstract class AbstractRelatorioEstaticoDependenciaVO
implements Serializable {
    private String nomeInstituicao;
    private String nomeCidade;
    private Short tipoDeclaracao;
    private Date dataInicioCompetencia;
    private String descTitulo;
    private String protocoloDeclaracao;

    public AbstractRelatorioEstaticoDependenciaVO() {
    }

    public AbstractRelatorioEstaticoDependenciaVO(String nomeInstituicao, String nomeCidade, Short tipoDeclaracao, Date dataInicioCompetencia, String descTitulo, String protocoloDeclaracao) {
        this.nomeInstituicao = nomeInstituicao;
        this.nomeCidade = nomeCidade;
        this.tipoDeclaracao = tipoDeclaracao;
        this.dataInicioCompetencia = dataInicioCompetencia;
        this.descTitulo = descTitulo;
        this.protocoloDeclaracao = protocoloDeclaracao;
    }

    public Date getDataInicioCompetencia() {
        return this.dataInicioCompetencia;
    }

    public void setDataInicioCompetencia(Date dataInicioCompetencia) {
        this.dataInicioCompetencia = dataInicioCompetencia;
    }

    public String getDescTitulo() {
        return this.descTitulo;
    }

    public void setDescTitulo(String descTitulo) {
        this.descTitulo = descTitulo;
    }

    public String getNomeCidade() {
        return this.nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }

    public String getNomeInstituicao() {
        return this.nomeInstituicao;
    }

    public void setNomeInstituicao(String nomeInstituicao) {
        this.nomeInstituicao = nomeInstituicao;
    }

    public String getProtocoloDeclaracao() {
        return this.protocoloDeclaracao;
    }

    public void setProtocoloDeclaracao(String protocoloDeclaracao) {
        this.protocoloDeclaracao = protocoloDeclaracao;
    }

    public Short getTipoDeclaracao() {
        return this.tipoDeclaracao;
    }

    public void setTipoDeclaracao(Short tipoDeclaracao) {
        this.tipoDeclaracao = tipoDeclaracao;
    }
}

