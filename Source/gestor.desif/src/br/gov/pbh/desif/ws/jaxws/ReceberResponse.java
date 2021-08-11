/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.ws.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value=XmlAccessType.FIELD)
@XmlType(name="ReceberResponse", propOrder={"outputXML"})
public class ReceberResponse {
    @XmlElement(required=true)
    protected String outputXML;

    public String getOutputXML() {
        return this.outputXML;
    }

    public void setOutputXML(String value) {
        this.outputXML = value;
    }
}

