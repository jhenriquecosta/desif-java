/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.ws.cliente;

import java.util.Date;
import java.util.List;

public class ProtocoloAMI {
    private Long id;
    private String raizCnpj;
    private String nome;
    private String anoMesInicCmpe;
    private Short tipoDecl;
    private Short tipoCnso;
    private Date dataEntrega;
    private String verTermoRef;
    private Double verValidador;
    private String xmlAssinado;
    private Short modulo;
    private String mensagem;
    private List<DependenciaProtocoloAMI> listaDependencia;
    private List<TotalizacaoProtocoloAMI> listaTotalizacao;

    public ProtocoloAMI(Long id, String raizCnpj, String nome, String anoMesInicCmpe, Short tipoDecl, Short tipoCnso, Date dataEntrega, String verTermoRef, Double verValidador, String xmlAssinado, Short modulo, List<DependenciaProtocoloAMI> listaDependencia, List<TotalizacaoProtocoloAMI> listaTotalizacao, String mensagem) {
        this.id = id;
        this.raizCnpj = raizCnpj;
        this.nome = nome;
        this.anoMesInicCmpe = anoMesInicCmpe;
        this.tipoDecl = tipoDecl;
        this.tipoCnso = tipoCnso;
        this.dataEntrega = dataEntrega;
        this.verTermoRef = verTermoRef;
        this.verValidador = verValidador;
        this.xmlAssinado = xmlAssinado;
        this.modulo = modulo;
        this.listaDependencia = listaDependencia;
        this.listaTotalizacao = listaTotalizacao;
        this.mensagem = mensagem;
    }

    public ProtocoloAMI() {
    }

    public String getAnoMesInicCmpe() {
        return this.anoMesInicCmpe;
    }

    public void setAnoMesInicCmpe(String anoMesInicCmpe) {
        this.anoMesInicCmpe = anoMesInicCmpe;
    }

    public Date getDataEntrega() {
        return this.dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<DependenciaProtocoloAMI> getListaDependencia() {
        return this.listaDependencia;
    }

    public void setListaDependencia(List<DependenciaProtocoloAMI> listaDependencia) {
        this.listaDependencia = listaDependencia;
    }

    public List<TotalizacaoProtocoloAMI> getListaTotalizacao() {
        return this.listaTotalizacao;
    }

    public void setListaTotalizacao(List<TotalizacaoProtocoloAMI> listaTotalizacao) {
        this.listaTotalizacao = listaTotalizacao;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRaizCnpj() {
        return this.raizCnpj;
    }

    public void setRaizCnpj(String raizCnpj) {
        this.raizCnpj = raizCnpj;
    }

    public Short getTipoCnso() {
        return this.tipoCnso;
    }

    public void setTipoCnso(Short tipoCnso) {
        this.tipoCnso = tipoCnso;
    }

    public Short getTipoDecl() {
        return this.tipoDecl;
    }

    public void setTipoDecl(Short tipoDecl) {
        this.tipoDecl = tipoDecl;
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

    public Short getModulo() {
        return this.modulo;
    }

    public void setModulo(Short modulo) {
        this.modulo = modulo;
    }

    public String getMensagem() {
        return this.mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}

