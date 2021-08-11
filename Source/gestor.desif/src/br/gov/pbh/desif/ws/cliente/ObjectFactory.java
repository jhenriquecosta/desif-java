
package br.gov.pbh.desif.ws.cliente;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
    private static final QName _ReceberICM_QNAME = new QName("http://server.jaxws.ws.desif.pbh.gov.br/", "receberICM");
    private static final QName _ReceberContabilResponse_QNAME = new QName("http://server.jaxws.ws.desif.pbh.gov.br/", "receberContabilResponse");
    private static final QName _ReceberAMIResponse_QNAME = new QName("http://server.jaxws.ws.desif.pbh.gov.br/", "receberAMIResponse");
    private static final QName _ReceberAMI_QNAME = new QName("http://server.jaxws.ws.desif.pbh.gov.br/", "receberAMI");
    private static final QName _ReceberICMResponse_QNAME = new QName("http://server.jaxws.ws.desif.pbh.gov.br/", "receberICMResponse");
    private static final QName _ReceberContabil_QNAME = new QName("http://server.jaxws.ws.desif.pbh.gov.br/", "receberContabil");

    public ProtocoloICM createProtocoloICM() {
        return new ProtocoloICM();
    }

    public ReceberContabilResponse createReceberContabilResponse() {
        return new ReceberContabilResponse();
    }

    public ReceberResponseICM createReceberResponseICM() {
        return new ReceberResponseICM();
    }

    public ReceberContabil createReceberContabil() {
        return new ReceberContabil();
    }

    public DependenciaProtocoloAMI createDependenciaProtocoloAMI() {
        return new DependenciaProtocoloAMI();
    }

    public ReceberICMResponse createReceberICMResponse() {
        return new ReceberICMResponse();
    }

    public ReceberAMIResponse createReceberAMIResponse() {
        return new ReceberAMIResponse();
    }

    public Erros createErros() {
        return new Erros();
    }

    public ReceberResponseContabil createReceberResponseContabil() {
        return new ReceberResponseContabil();
    }

    public ReceberAMI createReceberAMI() {
        return new ReceberAMI();
    }

    public ProtocoloAMI createProtocoloAMI() {
        return new ProtocoloAMI();
    }

    public ReceberResponseAMI createReceberResponseAMI() {
        return new ReceberResponseAMI();
    }

    public ReceberICM createReceberICM() {
        return new ReceberICM();
    }

    public ProtocoloContabil createProtocoloContabil() {
        return new ProtocoloContabil();
    }

    public TotalizacaoProtocoloAMI createTotalizacaoProtocoloAMI() {
        return new TotalizacaoProtocoloAMI();
    }

    public ReceberRequest createReceberRequest() {
        return new ReceberRequest();
    }

    @XmlElementDecl(namespace="http://server.jaxws.ws.desif.pbh.gov.br/", name="receberICM")
    public JAXBElement<ReceberICM> createReceberICM(ReceberICM value) {
        return new JAXBElement<ReceberICM>(_ReceberICM_QNAME, ReceberICM.class, null, value);
    }

    @XmlElementDecl(namespace="http://server.jaxws.ws.desif.pbh.gov.br/", name="receberContabilResponse")
    public JAXBElement<ReceberContabilResponse> createReceberContabilResponse(ReceberContabilResponse value) {
        return new JAXBElement<ReceberContabilResponse>(_ReceberContabilResponse_QNAME, ReceberContabilResponse.class, null, value);
    }

    @XmlElementDecl(namespace="http://server.jaxws.ws.desif.pbh.gov.br/", name="receberAMIResponse")
    public JAXBElement<ReceberAMIResponse> createReceberAMIResponse(ReceberAMIResponse value) {
        return new JAXBElement<ReceberAMIResponse>(_ReceberAMIResponse_QNAME, ReceberAMIResponse.class, null, value);
    }

    @XmlElementDecl(namespace="http://server.jaxws.ws.desif.pbh.gov.br/", name="receberAMI")
    public JAXBElement<ReceberAMI> createReceberAMI(ReceberAMI value) {
        return new JAXBElement<ReceberAMI>(_ReceberAMI_QNAME, ReceberAMI.class, null, value);
    }

    @XmlElementDecl(namespace="http://server.jaxws.ws.desif.pbh.gov.br/", name="receberICMResponse")
    public JAXBElement<ReceberICMResponse> createReceberICMResponse(ReceberICMResponse value) {
        return new JAXBElement<ReceberICMResponse>(_ReceberICMResponse_QNAME, ReceberICMResponse.class, null, value);
    }

    @XmlElementDecl(namespace="http://server.jaxws.ws.desif.pbh.gov.br/", name="receberContabil")
    public JAXBElement<ReceberContabil> createReceberContabil(ReceberContabil value) {
        return new JAXBElement<ReceberContabil>(_ReceberContabil_QNAME, ReceberContabil.class, null, value);
    }
}

