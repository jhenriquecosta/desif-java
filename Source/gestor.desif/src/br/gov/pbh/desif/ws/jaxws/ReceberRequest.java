/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.ws.jaxws;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value=XmlAccessType.FIELD)
@XmlType(name="ReceberRequest", propOrder={"zip"})
public class ReceberRequest {
    @XmlElement(required=true)
    @XmlMimeType(value="application/octet-stream")
    protected DataHandler zip;
    @XmlAttribute
    protected String versao;

    public DataHandler getZip() {
        return this.zip;
    }

    public void setZip(DataHandler value) {
        this.zip = value;
    }

    public String getVersao() {
        return this.versao;
    }

    public void setVersao(String value) {
        this.versao = value;
    }
}

