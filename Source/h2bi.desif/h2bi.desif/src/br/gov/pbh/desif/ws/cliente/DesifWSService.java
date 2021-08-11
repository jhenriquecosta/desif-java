
package br.gov.pbh.desif.ws.cliente;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
public class DesifWSService extends Service
{

    private static final URL DESIFWSSERVICE_WSDL_LOCATION;
    private static final Logger logger;
    private static final String caminhoWS = "http:..bhissdigital.pbh.gov.br.desif-ws.ws?wsdl";

    public DesifWSService(URL wsdlLocation, QName serviceName)
    {
        super(wsdlLocation, serviceName);
    }

    public DesifWSService()
    {
        super(DESIFWSSERVICE_WSDL_LOCATION, new QName("http:..server.jaxws.ws.desif.pbh.gov.br.", "DesifWSService"));
    }

    public DesifWSDelegate getWs()
    {
        return (DesifWSDelegate)super.getPort(new QName("http:..server.jaxws.ws.desif.pbh.gov.br.", "ws"), br.gov.pbh.desif.ws.cliente.DesifWSDelegate.class);
    }

    static 
    {
        logger = Logger.getLogger(br.gov.pbh.desif.ws.cliente.DesifWSService.class.getName());
        URL url = null;
        try
        {
            URL baseUrl = br.gov.pbh.desif.ws.cliente.DesifWSService.class.getResource(".");
            url = new URL(baseUrl, "http:..bhissdigital.pbh.gov.br.desif-ws.ws?wsdl");
        }
        catch(MalformedURLException e)
        {
            logger.warning("Failed to create URL for the wsdl Location: 'http:..localhost.desif-ws.ws?wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        DESIFWSSERVICE_WSDL_LOCATION = url;
    }
}
