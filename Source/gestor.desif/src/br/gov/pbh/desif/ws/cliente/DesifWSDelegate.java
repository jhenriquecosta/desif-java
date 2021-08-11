package br.gov.pbh.desif.ws.cliente;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

@WebService(name = "DesifWSDelegate",targetNamespace = "http://server.jaxws.ws.desif.pbh.gov.br/")
public interface DesifWSDelegate 
{
   @WebMethod
   @WebResult(targetNamespace = "")
   @RequestWrapper(localName = "receberICM",targetNamespace = "http://server.jaxws.ws.desif.pbh.gov.br/",className = "br.gov.pbh.desif.ws.cliente.ReceberICM")
   @ResponseWrapper(localName = "receberICMResponse",targetNamespace = "http://server.jaxws.ws.desif.pbh.gov.br/",className = "br.gov.pbh.desif.ws.cliente.ReceberICMResponse")
   ReceberResponseICM receberICM(@WebParam(name = "arg0",targetNamespace = "") ReceberRequest var1);

   @WebMethod
   @WebResult(targetNamespace = "")
   @RequestWrapper(localName = "receberContabil",targetNamespace = "http://server.jaxws.ws.desif.pbh.gov.br/",className = "br.gov.pbh.desif.ws.cliente.ReceberContabil")
   @ResponseWrapper(localName = "receberContabilResponse",targetNamespace = "http://server.jaxws.ws.desif.pbh.gov.br/",className = "br.gov.pbh.desif.ws.cliente.ReceberContabilResponse")
   ReceberResponseContabil receberContabil(@WebParam(name = "arg0",targetNamespace = "") ReceberRequest var1);

   @WebMethod
   @WebResult(targetNamespace = "")
   @RequestWrapper(localName = "receberAMI",targetNamespace = "http://server.jaxws.ws.desif.pbh.gov.br/",className = "br.gov.pbh.desif.ws.cliente.ReceberAMI")
   @ResponseWrapper(localName = "receberAMIResponse",targetNamespace = "http://server.jaxws.ws.desif.pbh.gov.br/",className = "br.gov.pbh.desif.ws.cliente.ReceberAMIResponse")
   ReceberResponseAMI receberAMI(@WebParam(name = "arg0",targetNamespace = "") ReceberRequest var1);
}
