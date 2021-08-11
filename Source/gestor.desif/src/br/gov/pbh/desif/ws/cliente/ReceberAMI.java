/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.ws.cliente;

import br.gov.pbh.desif.ws.cliente.ReceberRequest;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value=XmlAccessType.FIELD)
@XmlType(name="receberAMI", propOrder={"arg0"})
public class ReceberAMI 
{
    protected ReceberRequest arg0;

    public ReceberRequest getArg0() 
    {
        return this.arg0;
    }

    public void setArg0(ReceberRequest value) 
    {
        this.arg0 = value;
    }
}

