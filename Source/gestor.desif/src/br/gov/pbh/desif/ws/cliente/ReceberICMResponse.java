/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.ws.cliente;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value=XmlAccessType.FIELD)
@XmlType(name="receberICMResponse", propOrder={"_return"})
public class ReceberICMResponse 
{
    @XmlElement(name="return")
    protected ReceberResponseICM _return;

    public ReceberResponseICM getReturn() 
    {
        return this._return;
    }

    public void setReturn(ReceberResponseICM value) 
    {
        this._return = value;
    }
}

