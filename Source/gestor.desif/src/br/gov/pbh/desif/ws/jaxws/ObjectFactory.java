/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.ws.jaxws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
    private static final QName _Output_QNAME = new QName("https://bhissdigital.pbh.gov.br/desif-ws/", "output");
    private static final QName _Input_QNAME = new QName("https://bhissdigital.pbh.gov.br/desif-ws/", "input");

    public ReceberRequest createReceberRequest() {
        return new ReceberRequest();
    }

    public ReceberResponse createReceberResponse() {
        return new ReceberResponse();
    }

    @XmlElementDecl(namespace="https://bhissdigital.pbh.gov.br/desif-ws/", name="output")
    public JAXBElement<ReceberResponse> createOutput(ReceberResponse value) {
        return new JAXBElement<ReceberResponse>(_Output_QNAME, ReceberResponse.class, null, value);
    }

    @XmlElementDecl(namespace="https://bhissdigital.pbh.gov.br/desif-ws/", name="input")
    public JAXBElement<ReceberRequest> createInput(ReceberRequest value) {
        return new JAXBElement<ReceberRequest>(_Input_QNAME, ReceberRequest.class, null, value);
    }
}

