/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.ws.cliente;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value=XmlAccessType.FIELD)
@XmlType(name="receberAMIResponse", propOrder={"_return"})
public class ReceberAMIResponse {
    @XmlElement(name="return")
    protected ReceberResponseAMI _return;

    public ReceberResponseAMI getReturn() {
        return this._return;
    }

    public void setReturn(ReceberResponseAMI value) {
        this._return = value;
    }
}

