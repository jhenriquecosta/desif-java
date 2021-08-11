/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.ws.cliente;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value=XmlAccessType.FIELD)
@XmlType(name="erros", propOrder={"detalhes", "id", "motivo"})
public class Erros {
    protected String detalhes;
    protected String id;
    protected String motivo;

    public String getDetalhes() {
        return this.detalhes;
    }

    public void setDetalhes(String value) {
        this.detalhes = value;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String value) {
        this.id = value;
    }

    public String getMotivo() {
        return this.motivo;
    }

    public void setMotivo(String value) {
        this.motivo = value;
    }
}

