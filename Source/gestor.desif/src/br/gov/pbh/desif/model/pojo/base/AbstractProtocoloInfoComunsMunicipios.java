/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.pojo.base;

import java.io.Serializable;
import java.util.Date;

public abstract class AbstractProtocoloInfoComunsMunicipios
implements Serializable {
    private Long protocolo;
    private String cnpjInstituicao;
    private String nomeInstituicao;
    private Date periodoInicCompetDecl;
    private Date periodoFimCompetDecl;
    private Date dataEntrega;
    private Double versaoValidador;
    private String versaoTermoRef;
    private Short tipoDeclaracao;
    private int totalContasInformadas;
    private int totalContasMaisAnalitico;
    private int totalContasMaisAnaliticoTributaveis;
    private int qtdeSubtituloRegistro0200;
    private int qtdeSubtituloRegistro0300;

    public AbstractProtocoloInfoComunsMunicipios() {
    }

    public AbstractProtocoloInfoComunsMunicipios(Long protocolo, String cnpjInstituicao, String nomeInstituicao, Date periodoInicCompetDecl, Date periodoFimCompetDecl, Date dataEntrega, Double versaoValidador, String versaoTermoRef, Short tipoDeclaracao, int totalContasInformadas, int totalContasMaisAnalitico, int totalContasMaisAnaliticoTributaveis, int qtdeSubtituloRegistro0200, int qtdeSubtituloRegistro0300) {
        this.protocolo = protocolo;
        this.cnpjInstituicao = cnpjInstituicao;
        this.nomeInstituicao = nomeInstituicao;
        this.periodoInicCompetDecl = periodoInicCompetDecl;
        this.periodoFimCompetDecl = periodoFimCompetDecl;
        this.dataEntrega = dataEntrega;
        this.versaoValidador = versaoValidador;
        this.versaoTermoRef = versaoTermoRef;
        this.tipoDeclaracao = tipoDeclaracao;
        this.totalContasInformadas = totalContasInformadas;
        this.totalContasMaisAnalitico = totalContasMaisAnalitico;
        this.totalContasMaisAnaliticoTributaveis = totalContasMaisAnaliticoTributaveis;
        this.qtdeSubtituloRegistro0200 = qtdeSubtituloRegistro0200;
        this.qtdeSubtituloRegistro0300 = qtdeSubtituloRegistro0300;
    }

    public String getCnpjInstituicao() {
        return this.cnpjInstituicao;
    }

    public void setCnpjInstituicao(String cnpjInstituicao) {
        this.cnpjInstituicao = cnpjInstituicao;
    }

    public Date getDataEntrega() {
        return this.dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public String getNomeInstituicao() {
        return this.nomeInstituicao;
    }

    public void setNomeInstituicao(String nomeInstituicao) {
        this.nomeInstituicao = nomeInstituicao;
    }

    public Date getPeriodoFimCompetDecl() {
        return this.periodoFimCompetDecl;
    }

    public void setPeriodoFimCompetDecl(Date periodoFimCompetDecl) {
        this.periodoFimCompetDecl = periodoFimCompetDecl;
    }

    public Date getPeriodoInicCompetDecl() {
        return this.periodoInicCompetDecl;
    }

    public void setPeriodoInicCompetDecl(Date periodoInicCompetDecl) {
        this.periodoInicCompetDecl = periodoInicCompetDecl;
    }

    public Long getProtocolo() {
        return this.protocolo;
    }

    public void setProtocolo(Long protocolo) {
        this.protocolo = protocolo;
    }

    public int getQtdeSubtituloRegistro0200() {
        return this.qtdeSubtituloRegistro0200;
    }

    public void setQtdeSubtituloRegistro0200(int qtdeSubtituloRegistro0200) {
        this.qtdeSubtituloRegistro0200 = qtdeSubtituloRegistro0200;
    }

    public int getQtdeSubtituloRegistro0300() {
        return this.qtdeSubtituloRegistro0300;
    }

    public void setQtdeSubtituloRegistro0300(int qtdeSubtituloRegistro0300) {
        this.qtdeSubtituloRegistro0300 = qtdeSubtituloRegistro0300;
    }

    public Short getTipoDeclaracao() {
        return this.tipoDeclaracao;
    }

    public void setTipoDeclaracao(Short tipoDeclaracao) {
        this.tipoDeclaracao = tipoDeclaracao;
    }

    public int getTotalContasInformadas() {
        return this.totalContasInformadas;
    }

    public void setTotalContasInformadas(int totalContasInformadas) {
        this.totalContasInformadas = totalContasInformadas;
    }

    public int getTotalContasMaisAnalitico() {
        return this.totalContasMaisAnalitico;
    }

    public void setTotalContasMaisAnalitico(int totalContasMaisAnalitico) {
        this.totalContasMaisAnalitico = totalContasMaisAnalitico;
    }

    public int getTotalContasMaisAnaliticoTributaveis() {
        return this.totalContasMaisAnaliticoTributaveis;
    }

    public void setTotalContasMaisAnaliticoTributaveis(int totalContasMaisAnaliticoTributaveis) {
        this.totalContasMaisAnaliticoTributaveis = totalContasMaisAnaliticoTributaveis;
    }

    public String getVersaoTermoRef() {
        return this.versaoTermoRef;
    }

    public void setVersaoTermoRef(String versaoTermoRef) {
        this.versaoTermoRef = versaoTermoRef;
    }

    public Double getVersaoValidador() {
        return this.versaoValidador;
    }

    public void setVersaoValidador(Double versaoValidador) {
        this.versaoValidador = versaoValidador;
    }
}

