
package br.gov.pbh.desif.ws.cliente;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

// Referenced classes of package br.gov.pbh.desif.ws.cliente:
//            ProtocoloICM, ReceberContabilResponse, ReceberResponseICM, ReceberContabil, 
//            DependenciaProtocoloAMI, ReceberICMResponse, ReceberAMIResponse, Erros, 
//            ReceberResponseContabil, ReceberAMI, ProtocoloAMI, ReceberResponseAMI, 
//            ReceberICM, ProtocoloContabil, TotalizacaoProtocoloAMI, ReceberRequest

public class ObjectFactory
{

    private static final QName _ReceberICM_QNAME = new QName("http://server.jaxws.ws.desif.pbh.gov.br/", "receberICM");
    private static final QName _ReceberContabilResponse_QNAME = new QName("http://server.jaxws.ws.desif.pbh.gov.br/", "receberContabilResponse");
    private static final QName _ReceberAMIResponse_QNAME = new QName("http://server.jaxws.ws.desif.pbh.gov.br/", "receberAMIResponse");
    private static final QName _ReceberAMI_QNAME = new QName("http://server.jaxws.ws.desif.pbh.gov.br/", "receberAMI");
    private static final QName _ReceberICMResponse_QNAME = new QName("http://server.jaxws.ws.desif.pbh.gov.br/", "receberICMResponse");
    private static final QName _ReceberContabil_QNAME = new QName("http://server.jaxws.ws.desif.pbh.gov.br/", "receberContabil");

    public ObjectFactory()
    {
    }

    public ProtocoloICM createProtocoloICM()
    {
        return new ProtocoloICM();
    }

    public ReceberContabilResponse createReceberContabilResponse()
    {
        return new ReceberContabilResponse();
    }

    public ReceberResponseICM createReceberResponseICM()
    {
        return new ReceberResponseICM();
    }

    public ReceberContabil createReceberContabil()
    {
        return new ReceberContabil();
    }

    public DependenciaProtocoloAMI createDependenciaProtocoloAMI()
    {
        return new DependenciaProtocoloAMI();
    }

    public ReceberICMResponse createReceberICMResponse()
    {
        return new ReceberICMResponse();
    }

    public ReceberAMIResponse createReceberAMIResponse()
    {
        return new ReceberAMIResponse();
    }

    public Erros createErros()
    {
        return new Erros();
    }

    public ReceberResponseContabil createReceberResponseContabil()
    {
        return new ReceberResponseContabil();
    }

    public ReceberAMI createReceberAMI()
    {
        return new ReceberAMI();
    }

    public ProtocoloAMI createProtocoloAMI()
    {
        return new ProtocoloAMI();
    }

    public ReceberResponseAMI createReceberResponseAMI()
    {
        return new ReceberResponseAMI();
    }

    public ReceberICM createReceberICM()
    {
        return new ReceberICM();
    }

    public ProtocoloContabil createProtocoloContabil()
    {
        return new ProtocoloContabil();
    }

    public TotalizacaoProtocoloAMI createTotalizacaoProtocoloAMI()
    {
        return new TotalizacaoProtocoloAMI();
    }

    public ReceberRequest createReceberRequest()
    {
        return new ReceberRequest();
    }

    public JAXBElement createReceberICM(ReceberICM value)
    {
        return new JAXBElement(_ReceberICM_QNAME, br.gov.pbh.desif.ws.cliente.ReceberICM.class, null, value);
    }

    public JAXBElement createReceberContabilResponse(ReceberContabilResponse value)
    {
        return new JAXBElement(_ReceberContabilResponse_QNAME, br.gov.pbh.desif.ws.cliente.ReceberContabilResponse.class, null, value);
    }

    public JAXBElement createReceberAMIResponse(ReceberAMIResponse value)
    {
        return new JAXBElement(_ReceberAMIResponse_QNAME, br.gov.pbh.desif.ws.cliente.ReceberAMIResponse.class, null, value);
    }

    public JAXBElement createReceberAMI(ReceberAMI value)
    {
        return new JAXBElement(_ReceberAMI_QNAME, br.gov.pbh.desif.ws.cliente.ReceberAMI.class, null, value);
    }

    public JAXBElement createReceberICMResponse(ReceberICMResponse value)
    {
        return new JAXBElement(_ReceberICMResponse_QNAME, br.gov.pbh.desif.ws.cliente.ReceberICMResponse.class, null, value);
    }

    public JAXBElement createReceberContabil(ReceberContabil value)
    {
        return new JAXBElement(_ReceberContabil_QNAME, br.gov.pbh.desif.ws.cliente.ReceberContabil.class, null, value);
    }

}
