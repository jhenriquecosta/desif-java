/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.ws.cliente;

import br.gov.pbh.desif.ws.cliente.DependenciaProtocoloAMIold;
import br.gov.pbh.desif.ws.cliente.TotalizacaoProtocoloAMIold;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value=XmlAccessType.FIELD)
@XmlType(name="protocoloAMI", propOrder={"Ano_Mes_Inic_Cmpe", "DataEntrega", "id", "ListaDependencia", "ListaTotalizacao", "Nome", "raizCnpj", "Tipo_Cnso", "Tipo_Decl", "xmlAssinado"})
public class ProtocoloAMIold {
    protected String Ano_Mes_Inic_Cmpe;
    @XmlSchemaType(name="dateTime")
    protected Date DataEntrega;
    protected Long id;
    @XmlElement(nillable=true)
    protected List<DependenciaProtocoloAMIold> ListaDependencia;
    @XmlElement(nillable=true)
    protected List<TotalizacaoProtocoloAMIold> ListaTotalizacao;
    protected String Nome;
    protected String raizCnpj;
    protected Short Tipo_Cnso;
    protected Short Tipo_Decl;
    protected String xmlAssinado;

    public String getAno_Mes_Inic_Cmpe() {
        return this.Ano_Mes_Inic_Cmpe;
    }

    public void setAno_Mes_Inic_Cmpe(String value) {
        this.Ano_Mes_Inic_Cmpe = value;
    }

    public Date getDataEntrega() {
        return this.DataEntrega;
    }

    public void setDataEntrega(Date value) {
        this.DataEntrega = value;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long value) {
        this.id = value;
    }

    public List<DependenciaProtocoloAMIold> getListaDependencia() {
        if (this.ListaDependencia == null) {
            this.ListaDependencia = new ArrayList<DependenciaProtocoloAMIold>();
        }
        return this.ListaDependencia;
    }

    public List<TotalizacaoProtocoloAMIold> getListaTotalizacao() {
        if (this.ListaTotalizacao == null) {
            this.ListaTotalizacao = new ArrayList<TotalizacaoProtocoloAMIold>();
        }
        return this.ListaTotalizacao;
    }

    public String getNome() {
        return this.Nome;
    }

    public void setNome(String value) {
        this.Nome = value;
    }

    public String getRaizCnpj() {
        return this.raizCnpj;
    }

    public void setRaizCnpj(String value) {
        this.raizCnpj = value;
    }

    public Short getTipo_Cnso() {
        return this.Tipo_Cnso;
    }

    public void setTipo_Cnso(Short value) {
        this.Tipo_Cnso = value;
    }

    public Short getTipo_Decl() {
        return this.Tipo_Decl;
    }

    public void setTipo_Decl(Short value) {
        this.Tipo_Decl = value;
    }

    public String getXmlAssinado() {
        return this.xmlAssinado;
    }

    public void setXmlAssinado(String value) {
        this.xmlAssinado = value;
    }
}

