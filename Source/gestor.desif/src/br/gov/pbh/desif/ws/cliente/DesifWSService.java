package br.gov.pbh.desif.ws.cliente;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;

@WebServiceClient
(
   name = "DesifWSService",
   targetNamespace = "http://server.jaxws.ws.desif.pbh.gov.br/",
   wsdlLocation = "http://localhost/desif-ws/ws?wsdl"
)
public class DesifWSService extends Service {
   private static final URL DESIFWSSERVICE_WSDL_LOCATION;
   private static final Logger logger = Logger.getLogger(DesifWSService.class.getName());
   private static final String caminhoWS = "http://bhissdigital.pbh.gov.br/desif-ws/ws?wsdl";

   public DesifWSService(URL wsdlLocation, QName serviceName) {
      super(wsdlLocation, serviceName);
   }

   public DesifWSService() {
      super(DESIFWSSERVICE_WSDL_LOCATION, new QName("http://server.jaxws.ws.desif.pbh.gov.br/", "DesifWSService"));
   }

   @WebEndpoint(
      name = "ws"
   )
   public DesifWSDelegate getWs() {
      return (DesifWSDelegate)super.getPort(new QName("http://server.jaxws.ws.desif.pbh.gov.br/", "ws"), DesifWSDelegate.class);
   }

   static {
      URL url = null;

      try {
         URL e = DesifWSService.class.getResource(".");
         url = new URL(e, "http://bhissdigital.pbh.gov.br/desif-ws/ws?wsdl");
      } catch (MalformedURLException var2) {
         logger.warning("Failed to create URL for the wsdl Location: \'http://localhost/desif-ws/ws?wsdl\', retrying as a local file");
         logger.warning(var2.getMessage());
      }

      DESIFWSSERVICE_WSDL_LOCATION = url;
   }
}
