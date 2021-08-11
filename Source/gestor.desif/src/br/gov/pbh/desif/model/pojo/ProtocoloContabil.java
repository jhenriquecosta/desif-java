/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.pojo;

import java.sql.Timestamp;

public class ProtocoloContabil {
    private Long id;
    private String raizCnpj;
    private String nome;
    private String inicCompetDecl;
    private String fimCompetDecl;
    private Long numDepeInfo;
    private Long numDepeInfoBalanc;
    private Long numBalancInformados;
    private Long numDepeInfoRateio;
    private Long numRateioInformados;
    private Timestamp dataEntrega;
    private Double verValidador;
    private String verTermoRef;
    private Short tipoDeclaracao;
    private Short modulo;
    private String xmlAssinado;

    public ProtocoloContabil(Long id, String raizCnpj, String nome, String inicCompetDecl, String fimCompetDecl, Long numDepeInfo, Long numDepeInfoBalanc, Long numBalancInformados, Long numDepeInfoRateio, Long numRateioInformados, Timestamp dataEntrega, Double verValidador, String verTermoRef, Short tipoDeclaracao, Short modulo, String xmlAssinado) {
        this.id = id;
        this.raizCnpj = raizCnpj;
        this.nome = nome;
        this.inicCompetDecl = inicCompetDecl;
        this.fimCompetDecl = fimCompetDecl;
        this.numDepeInfo = numDepeInfo;
        this.numDepeInfoBalanc = numDepeInfoBalanc;
        this.numBalancInformados = numBalancInformados;
        this.numDepeInfoRateio = numDepeInfoRateio;
        this.numRateioInformados = numRateioInformados;
        this.dataEntrega = dataEntrega;
        this.verValidador = verValidador;
        this.verTermoRef = verTermoRef;
        this.tipoDeclaracao = tipoDeclaracao;
        this.modulo = modulo;
        this.xmlAssinado = xmlAssinado;
    }

    public ProtocoloContabil() {
    }

    public Timestamp getDataEntrega() {
        return this.dataEntrega;
    }

    public void setDataEntrega(Timestamp dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public String getFimCompetDecl() {
        return this.fimCompetDecl;
    }

    public void setFimCompetDecl(String fimCompetDecl) {
        this.fimCompetDecl = fimCompetDecl;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInicCompetDecl() {
        return this.inicCompetDecl;
    }

    public void setInicCompetDecl(String inicCompetDecl) {
        this.inicCompetDecl = inicCompetDecl;
    }

    public Short getModulo() {
        return this.modulo;
    }

    public void setModulo(Short modulo) {
        this.modulo = modulo;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getNumBalancInformados() {
        return this.numBalancInformados;
    }

    public void setNumBalancInformados(Long numBalancInformados) {
        this.numBalancInformados = numBalancInformados;
    }

    public Long getNumDepeInfo() {
        return this.numDepeInfo;
    }

    public void setNumDepeInfo(Long numDepeInfo) {
        this.numDepeInfo = numDepeInfo;
    }

    public Long getNumDepeInfoBalanc() {
        return this.numDepeInfoBalanc;
    }

    public void setNumDepeInfoBalanc(Long numDepeInfoBalanc) {
        this.numDepeInfoBalanc = numDepeInfoBalanc;
    }

    public Long getNumDepeInfoRateio() {
        return this.numDepeInfoRateio;
    }

    public void setNumDepeInfoRateio(Long numDepeInfoRateio) {
        this.numDepeInfoRateio = numDepeInfoRateio;
    }

    public Long getNumRateioInformados() {
        return this.numRateioInformados;
    }

    public void setNumRateioInformados(Long numRateioInformados) {
        this.numRateioInformados = numRateioInformados;
    }

    public String getRaizCnpj() {
        return this.raizCnpj;
    }

    public void setRaizCnpj(String raizCnpj) {
        this.raizCnpj = raizCnpj;
    }

    public Short getTipoDeclaracao() {
        return this.tipoDeclaracao;
    }

    public void setTipoDeclaracao(Short tipoDeclaracao) {
        this.tipoDeclaracao = tipoDeclaracao;
    }

    public String getVerTermoRef() {
        return this.verTermoRef;
    }

    public void setVerTermoRef(String verTermoRef) {
        this.verTermoRef = verTermoRef;
    }

    public Double getVerValidador() {
        return this.verValidador;
    }

    public void setVerValidador(Double verValidador) {
        this.verValidador = verValidador;
    }

    public String getXmlAssinado() {
        return this.xmlAssinado;
    }

    public void setXmlAssinado(String xmlAssinado) {
        this.xmlAssinado = xmlAssinado;
    }
}

