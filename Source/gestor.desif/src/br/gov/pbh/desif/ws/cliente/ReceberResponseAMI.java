/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.ws.cliente;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value=XmlAccessType.FIELD)
@XmlType(name="receberResponseAMI", propOrder={"listaErros", "protocoloAMIVO"})
public class ReceberResponseAMI {
    @XmlElement(nillable=true)
    protected List<Erros> listaErros;
    protected ProtocoloAMI protocoloAMIVO;

    public List<Erros> getListaErros() {
        if (this.listaErros == null) {
            this.listaErros = new ArrayList<Erros>();
        }
        return this.listaErros;
    }

    public ProtocoloAMI getProtocoloAMIVO() {
        return this.protocoloAMIVO;
    }

    public void setProtocoloAMIVO(ProtocoloAMI value) {
        this.protocoloAMIVO = value;
    }
}

