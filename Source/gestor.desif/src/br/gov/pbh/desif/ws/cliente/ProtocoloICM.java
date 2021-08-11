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
@XmlType(name="protocoloICM", propOrder={"dataEntrega", "fimCompetDecl", "id", "inicCompetDecl", "modulo", "nome", "qtdeSubtituloReg0200", "qtdeSubtituloReg0300", "raizCnpj", "tipoDeclaracao", "totalContasInfo", "totalContasMaisAnalit", "totalContasMaisAnalitTrib", "verTermoRef", "verValidador", "xmlAssinado"})
public class ProtocoloICM
{
    @XmlSchemaType(name="dateTime")
    protected Date dataEntrega;
    protected String fimCompetDecl;
    protected Long id;
    protected String inicCompetDecl;
    protected Short modulo;
    protected String nome;
    protected Long qtdeSubtituloReg0200;
    protected Long qtdeSubtituloReg0300;
    protected String raizCnpj;
    protected Short tipoDeclaracao;
    protected Long totalContasInfo;
    protected Long totalContasMaisAnalit;
    protected Long totalContasMaisAnalitTrib;
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

    public Long getQtdeSubtituloReg0200() {
        return this.qtdeSubtituloReg0200;
    }

    public void setQtdeSubtituloReg0200(Long value) {
        this.qtdeSubtituloReg0200 = value;
    }

    public Long getQtdeSubtituloReg0300() {
        return this.qtdeSubtituloReg0300;
    }

    public void setQtdeSubtituloReg0300(Long value) {
        this.qtdeSubtituloReg0300 = value;
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

    public Long getTotalContasInfo() {
        return this.totalContasInfo;
    }

    public void setTotalContasInfo(Long value) {
        this.totalContasInfo = value;
    }

    public Long getTotalContasMaisAnalit() {
        return this.totalContasMaisAnalit;
    }

    public void setTotalContasMaisAnalit(Long value) {
        this.totalContasMaisAnalit = value;
    }

    public Long getTotalContasMaisAnalitTrib() {
        return this.totalContasMaisAnalitTrib;
    }

    public void setTotalContasMaisAnalitTrib(Long value) {
        this.totalContasMaisAnalitTrib = value;
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

