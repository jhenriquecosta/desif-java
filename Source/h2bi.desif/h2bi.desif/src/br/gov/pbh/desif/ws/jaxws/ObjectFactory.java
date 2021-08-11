
package br.gov.pbh.desif.ws.jaxws;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

// Referenced classes of package br.gov.pbh.desif.ws.jaxws:
//            ReceberRequest, ReceberResponse

public class ObjectFactory
{

    private static final QName _Output_QNAME = new QName("https://bhissdigital.pbh.gov.br/desif-ws/", "output");
    private static final QName _Input_QNAME = new QName("https://bhissdigital.pbh.gov.br/desif-ws/", "input");

    public ObjectFactory()
    {
    }

    public ReceberRequest createReceberRequest()
    {
        return new ReceberRequest();
    }

    public ReceberResponse createReceberResponse()
    {
        return new ReceberResponse();
    }

    public JAXBElement createOutput(ReceberResponse value)
    {
        return new JAXBElement(_Output_QNAME, br.gov.pbh.desif.ws.jaxws.ReceberResponse.class, null, value);
    }

    public JAXBElement createInput(ReceberRequest value)
    {
        return new JAXBElement(_Input_QNAME, br.gov.pbh.desif.ws.jaxws.ReceberRequest.class, null, value);
    }

}
