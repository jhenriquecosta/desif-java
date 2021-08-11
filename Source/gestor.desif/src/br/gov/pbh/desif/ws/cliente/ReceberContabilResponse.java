/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.ws.cliente;

import br.gov.pbh.desif.ws.cliente.ReceberResponseContabil;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value=XmlAccessType.FIELD)
@XmlType(name="receberContabilResponse", propOrder={"_return"})
public class ReceberContabilResponse {
    @XmlElement(name="return")
    protected ReceberResponseContabil _return;

    public ReceberResponseContabil getReturn() {
        return this._return;
    }

    public void setReturn(ReceberResponseContabil value) {
        this._return = value;
    }
}

