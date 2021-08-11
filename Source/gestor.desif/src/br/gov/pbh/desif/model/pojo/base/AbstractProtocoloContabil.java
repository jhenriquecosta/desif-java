/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.pojo.base;

import java.io.Serializable;
import java.util.Date;

public abstract class AbstractProtocoloContabil
implements Serializable {
    private Long protocolo;
    private String cnpjInstituicao;
    private String nomeInstituicao;
    private Date periodoInicCompetDecl;
    private Date periodoFimCompetDecl;
    private int numDepeInfo;
    private int numDepeInfoBalanc;
    private int numBalancInformados;
    private int numDepeInfoRateio;
    private int numRateioInformados;
    private Date dataEntrega;
    private String versaoDocumentacao;
    private String versaoTermoRef;
    private Short tipoDeclaracao;

    public AbstractProtocoloContabil() {
    }

    public AbstractProtocoloContabil(Long protocolo, String cnpjInstituicao, String nomeInstituicao, Date periodoInicCompetDecl, Date periodoFimCompetDecl, int numDepeInfo, int numDepeInfoBalanc, int numBalancInformados, int numDepeInfoRateio, int numRateioInformados, Date dataEntrega, String versaoDocumentacao, String versaoTermoRef, Short tipoDeclaracao) {
        this.protocolo = protocolo;
        this.cnpjInstituicao = cnpjInstituicao;
        this.nomeInstituicao = nomeInstituicao;
        this.periodoInicCompetDecl = periodoInicCompetDecl;
        this.periodoFimCompetDecl = periodoFimCompetDecl;
        this.numDepeInfo = numDepeInfo;
        this.numDepeInfoBalanc = numDepeInfoBalanc;
        this.numBalancInformados = numBalancInformados;
        this.numDepeInfoRateio = numDepeInfoRateio;
        this.numRateioInformados = numRateioInformados;
        this.dataEntrega = dataEntrega;
        this.versaoDocumentacao = versaoDocumentacao;
        this.versaoTermoRef = versaoTermoRef;
        this.tipoDeclaracao = tipoDeclaracao;
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

    public int getNumBalancInformados() {
        return this.numBalancInformados;
    }

    public void setNumBalancInformados(int numBalancInformados) {
        this.numBalancInformados = numBalancInformados;
    }

    public int getNumDepeInfo() {
        return this.numDepeInfo;
    }

    public void setNumDepeInfo(int numDepeInfo) {
        this.numDepeInfo = numDepeInfo;
    }

    public int getNumDepeInfoBalanc() {
        return this.numDepeInfoBalanc;
    }

    public void setNumDepeInfoBalanc(int numDepeInfoBalanc) {
        this.numDepeInfoBalanc = numDepeInfoBalanc;
    }

    public int getNumDepeInfoRateio() {
        return this.numDepeInfoRateio;
    }

    public void setNumDepeInfoRateio(int numDepeInfoRateio) {
        this.numDepeInfoRateio = numDepeInfoRateio;
    }

    public int getNumRateioInformados() {
        return this.numRateioInformados;
    }

    public void setNumRateioInformados(int numRateioInformados) {
        this.numRateioInformados = numRateioInformados;
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

    public Short getTipoDeclaracao() {
        return this.tipoDeclaracao;
    }

    public void setTipoDeclaracao(Short tipoDeclaracao) {
        this.tipoDeclaracao = tipoDeclaracao;
    }

    public String getVersaoTermoRef() {
        return this.versaoTermoRef;
    }

    public void setVersaoTermoRef(String versaoTermoRef) {
        this.versaoTermoRef = versaoTermoRef;
    }

    public String getVersaoDocumentacao() {
        return this.versaoDocumentacao;
    }

    public void setVersaoDocumentacao(String versaoDocumentacao) {
        this.versaoDocumentacao = versaoDocumentacao;
    }
}

