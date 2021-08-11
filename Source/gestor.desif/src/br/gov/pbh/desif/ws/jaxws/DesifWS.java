package br.gov.pbh.desif.ws.jaxws;

import br.gov.pbh.desif.ws.jaxws.DesIfServiceDelegate;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;

@WebServiceClient(name = "DesifWS",targetNamespace = "https://bhissdigital.pbh.gov.br/desif-ws/",wsdlLocation = "https://bhisshomologa.pbh.gov.br/desif-ws/ws?wsdl")
public class DesifWS extends Service 
{
   private static final URL DESIFWS_WSDL_LOCATION;
   private static final Logger logger = Logger.getLogger(DesifWS.class.getName());
   private static final String caminhoWS = "http://homologa.prodemge.gov.br/desif-ws/ws?wsdl";

   public DesifWS(URL wsdlLocation, QName serviceName) 
   {
      super(wsdlLocation, serviceName);
   }

   public DesifWS() 
   {
      super(DESIFWS_WSDL_LOCATION, new QName("https://bhissdigital.pbh.gov.br/desif-ws/", "DesifWS"));
   }

   @WebEndpoint(name = "ws")
   public DesIfServiceDelegate getWs() 
   {
      return (DesIfServiceDelegate)super.getPort(new QName("https://bhissdigital.pbh.gov.br/desif-ws/", "ws"), DesIfServiceDelegate.class);
   }

   static 
   {
      URL url = null;

      try {
         URL e = DesifWS.class.getResource(".");
         url = new URL(e, "http://homologa.prodemge.gov.br/desif-ws/ws?wsdl");
      } catch (MalformedURLException var2) {
         logger.warning("Failed to create URL for the wsdl Location: \'http://homologa.prodemge.gov.br/desif-ws/ws?wsdl\', retrying as a local file");
         logger.warning(var2.getMessage());
      }

      DESIFWS_WSDL_LOCATION = url;
   }
}
