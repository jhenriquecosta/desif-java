/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.ws.cliente;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value=XmlAccessType.FIELD)
@XmlType(name="protocoloContabil", propOrder={"dataEntrega", "fimCompetDecl", "id", "inicCompetDecl", "modulo", "nome", "numBalancInformados", "numDepeInfo", "numDepeInfoBalanc", "numDepeInfoRateio", "numRateioInformados", "raizCnpj", "tipoDeclaracao", "verTermoRef", "verValidador", "xmlAssinado"})
public class ProtocoloContabil {
    @XmlSchemaType(name="dateTime")
    protected Date dataEntrega;
    protected String fimCompetDecl;
    protected Long id;
    protected String inicCompetDecl;
    protected Short modulo;
    protected String nome;
    protected Long numBalancInformados;
    protected Long numDepeInfo;
    protected Long numDepeInfoBalanc;
    protected Long numDepeInfoRateio;
    protected Long numRateioInformados;
    protected String raizCnpj;
    protected Short tipoDeclaracao;
    protected String verTermoRef;
    protected Double verValidador;
    protected String xmlAssinado;

    public Date getDataEntrega() {
        return this.dataEntrega;
    }

    public void setDataEntrega(Date value) {
        this.dataEntrega = value;
    }

    public String getFimCompetDecl() {
        return this.fimCompetDecl;
    }

    public void setFimCompetDecl(String value) {
        this.fimCompetDecl = value;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long value) {
        this.id = value;
    }

    public String getInicCompetDecl() {
        return this.inicCompetDecl;
    }

    public void setInicCompetDecl(String value) {
        this.inicCompetDecl = value;
    }

    public Short getModulo() {
        return this.modulo;
    }

    public void setModulo(Short value) {
        this.modulo = value;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String value) {
        this.nome = value;
    }

    public Long getNumBalancInformados() {
        return this.numBalancInformados;
    }

    public void setNumBalancInformados(Long value) {
        this.numBalancInformados = value;
    }

    public Long getNumDepeInfo() {
        return this.numDepeInfo;
    }

    public void setNumDepeInfo(Long value) {
        this.numDepeInfo = value;
    }

    public Long getNumDepeInfoBalanc() {
        return this.numDepeInfoBalanc;
    }

    public void setNumDepeInfoBalanc(Long value) {
        this.numDepeInfoBalanc = value;
    }

    public Long getNumDepeInfoRateio() {
        return this.numDepeInfoRateio;
    }

    public void setNumDepeInfoRateio(Long value) {
        this.numDepeInfoRateio = value;
    }

    public Long getNumRateioInformados() {
        return this.numRateioInformados;
    }

    public void setNumRateioInformados(Long value) {
        this.numRateioInformados = value;
    }

    public String getRaizCnpj() {
        return this.raizCnpj;
    }

    public void setRaizCnpj(String value) {
        this.raizCnpj = value;
    }

    public Short getTipoDeclaracao() {
        return this.tipoDeclaracao;
    }

    public void setTipoDeclaracao(Short value) {
        this.tipoDeclaracao = value;
    }

    public String getVerTermoRef() {
        return this.verTermoRef;
    }

    public void setVerTermoRef(String value) {
        this.verTermoRef = value;
    }

    public Double getVerValidador() {
        return this.verValidador;
    }

    public void setVerValidador(Double value) {
        this.verValidador = value;
    }

    public String getXmlAssinado() {
        return this.xmlAssinado;
    }

    public void setXmlAssinado(String value) {
        this.xmlAssinado = value;
    }
}

