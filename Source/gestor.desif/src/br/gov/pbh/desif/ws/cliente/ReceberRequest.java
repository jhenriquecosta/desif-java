/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.ws.cliente;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value=XmlAccessType.FIELD)
@XmlType(name="receberRequest", propOrder={"versao", "zip"})
public class ReceberRequest {
    protected String versao;
    protected DataHandler zip;

    public String getVersao() {
        return this.versao;
    }

    public void setVersao(String value) {
        this.versao = value;
    }

    public DataHandler getZip() {
        return this.zip;
    }

    public void setZip(DataHandler value) {
        this.zip = value;
    }
}

