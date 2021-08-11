/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.pojo.base;

import java.io.Serializable;
import java.util.Date;

public abstract class AbstractIdentificacaoDeclaracao
implements Serializable {
    private Integer id;
    private String titulo;
    private Long cidade;
    private Integer linhaIdentificacaoDeclaracao;
    private String cnpjInstituicao;
    private String nomeInstituicao;
    private Date dataInicioCompetencia;
    private Date dataFimCompetencia;
    private Short moduloDeclaracao;
    private Short tipoDeclaracao;
    private Short tipoConsolidacao;
    private String cnpjResponsavelRecolhimento;
    private String protocoloDeclaracao;

    public AbstractIdentificacaoDeclaracao() {
    }

    public AbstractIdentificacaoDeclaracao(Integer id, String titulo, Long cidade, Integer linhaIdentificacaoDeclaracao, String cnpjInstituicao, String nomeInstituicao, Date dataInicioCompetencia, Date dataFimCompetencia, Short moduloDeclaracao, Short tipoDeclaracao, Short tipoConsolidacao, String cnpjResponsavelRecolhimento, String protocoloDeclaracao) {
        this.id = id;
        this.titulo = titulo;
        this.cidade = cidade;
        this.linhaIdentificacaoDeclaracao = linhaIdentificacaoDeclaracao;
        this.cnpjInstituicao = cnpjInstituicao;
        this.nomeInstituicao = nomeInstituicao;
        this.dataInicioCompetencia = dataInicioCompetencia;
        this.dataFimCompetencia = dataFimCompetencia;
        this.moduloDeclaracao = moduloDeclaracao;
        this.tipoDeclaracao = tipoDeclaracao;
        this.tipoConsolidacao = tipoConsolidacao;
        this.cnpjResponsavelRecolhimento = cnpjResponsavelRecolhimento;
        this.protocoloDeclaracao = protocoloDeclaracao;
    }

    public AbstractIdentificacaoDeclaracao(Integer id, String titulo, Long cidade, Integer linhaIdentificacaoDeclaracao, String cnpjInstituicao, String nomeInstituicao, Date dataInicioCompetencia, Date dataFimCompetencia, Short moduloDeclaracao, Short tipoDeclaracao, String cnpjResponsavelRecolhimento, String protocoloDeclaracao) {
        this.id = id;
        this.titulo = titulo;
        this.cidade = cidade;
        this.linhaIdentificacaoDeclaracao = linhaIdentificacaoDeclaracao;
        this.cnpjInstituicao = cnpjInstituicao;
        this.nomeInstituicao = nomeInstituicao;
        this.dataInicioCompetencia = dataInicioCompetencia;
        this.dataFimCompetencia = dataFimCompetencia;
        this.moduloDeclaracao = moduloDeclaracao;
        this.tipoDeclaracao = tipoDeclaracao;
        this.cnpjResponsavelRecolhimento = cnpjResponsavelRecolhimento;
        this.protocoloDeclaracao = protocoloDeclaracao;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Long getCidade() {
        return this.cidade;
    }

    public void setCidade(Long cidade) {
        this.cidade = cidade;
    }

    public Integer getLinhaIdentificacaoDeclaracao() {
        return this.linhaIdentificacaoDeclaracao;
    }

    public void setLinhaIdentificacaoDeclaracao(Integer linhaIdentificacaoDeclaracao) {
        this.linhaIdentificacaoDeclaracao = linhaIdentificacaoDeclaracao;
    }

    public String getCnpjInstituicao() {
        return this.cnpjInstituicao;
    }

    public void setCnpjInstituicao(String cnpjInstituicao) {
        this.cnpjInstituicao = cnpjInstituicao;
    }

    public String getNomeInstituicao() {
        return this.nomeInstituicao;
    }

    public void setNomeInstituicao(String nomeInstituicao) {
        this.nomeInstituicao = nomeInstituicao;
    }

    public Date getDataInicioCompetencia() {
        return this.dataInicioCompetencia;
    }

    public void setDataInicioCompetencia(Date dataInicioCompetencia) {
        this.dataInicioCompetencia = dataInicioCompetencia;
    }

    public Date getDataFimCompetencia() {
        return this.dataFimCompetencia;
    }

    public void setDataFimCompetencia(Date dataFimCompetencia) {
        this.dataFimCompetencia = dataFimCompetencia;
    }

    public Short getModuloDeclaracao() {
        return this.moduloDeclaracao;
    }

    public void setModuloDeclaracao(Short moduloDeclaracao) {
        this.moduloDeclaracao = moduloDeclaracao;
    }

    public Short getTipoDeclaracao() {
        return this.tipoDeclaracao;
    }

    public void setTipoDeclaracao(Short tipoDeclaracao) {
        this.tipoDeclaracao = tipoDeclaracao;
    }

    public Short getTipoConsolidacao() {
        return this.tipoConsolidacao;
    }

    public void setTipoConsolidacao(Short tipoConsolidacao) {
        this.tipoConsolidacao = tipoConsolidacao;
    }

    public String getCnpjResponsavelRecolhimento() {
        return this.cnpjResponsavelRecolhimento;
    }

    public void setCnpjResponsavelRecolhimento(String cnpjResponsavelRecolhimento) {
        this.cnpjResponsavelRecolhimento = cnpjResponsavelRecolhimento;
    }

    public String getProtocoloDeclaracao() {
        return this.protocoloDeclaracao;
    }

    public void setProtocoloDeclaracao(String protocoloDeclaracao) {
        this.protocoloDeclaracao = protocoloDeclaracao;
    }

    public String toString() {
        String resp = "Id= " + this.id + "\ntitulo= " + this.titulo + "\ncidade= " + this.cidade + "\nlinha= " + this.linhaIdentificacaoDeclaracao + "/ncnpjInstitui\u00e7\u00e3o= " + this.cnpjInstituicao + "\nnome istitui\u00e7\u00e3o= " + this.nomeInstituicao + "\n data inicio " + this.dataInicioCompetencia.toString() + "\n data fim= " + this.dataFimCompetencia + "\nmodulo declara\u00e7\u00e3o= " + this.moduloDeclaracao + "\nTipo declara\u00e7\u00e3o= " + this.tipoDeclaracao + "\nTipo Consolida\u00e7\u00e3o = " + this.tipoConsolidacao + "\ncnpj resp recolhimento= " + this.cnpjResponsavelRecolhimento + "\nprotocolo Declara\u00e7\u00e3o= " + this.protocoloDeclaracao;
        return resp;
    }
}

