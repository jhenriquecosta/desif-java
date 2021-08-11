/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.ws.cliente;

import br.gov.pbh.desif.ws.cliente.Erros;
import br.gov.pbh.desif.ws.cliente.ProtocoloICM;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value=XmlAccessType.FIELD)
@XmlType(name="receberResponseICM", propOrder={"listaErros", "protocoloICM"})
public class ReceberResponseICM {
    @XmlElement(nillable=true)
    protected List<Erros> listaErros;
    protected ProtocoloICM protocoloICM;

    public List<Erros> getListaErros() {
        if (this.listaErros == null) {
            this.listaErros = new ArrayList<Erros>();
        }
        return this.listaErros;
    }

    public ProtocoloICM getProtocoloICM() {
        return this.protocoloICM;
    }

    public void setProtocoloICM(ProtocoloICM value) {
        this.protocoloICM = value;
    }
}

