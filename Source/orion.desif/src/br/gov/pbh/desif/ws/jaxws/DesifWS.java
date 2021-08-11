
package br.gov.pbh.desif.ws.jaxws;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

// Referenced classes of package br.gov.pbh.desif.ws.jaxws:
//            DesIfServiceDelegate

public class DesifWS extends Service
{

    private static final URL DESIFWS_WSDL_LOCATION;
    private static final Logger logger;
    private static final String caminhoWS = "http://homologa.prodemge.gov.br/desif-ws/ws?wsdl";

    public DesifWS(URL wsdlLocation, QName serviceName)
    {
        super(wsdlLocation, serviceName);
    }

    public DesifWS()
    {
        super(DESIFWS_WSDL_LOCATION, new QName("https://bhissdigital.pbh.gov.br/desif-ws/", "DesifWS"));
    }

    public DesIfServiceDelegate getWs()
    {
        return (DesIfServiceDelegate)super.getPort(new QName("https://bhissdigital.pbh.gov.br/desif-ws/", "ws"), br.gov.pbh.desif.ws.jaxws.DesIfServiceDelegate.class);
    }

    static 
    {
        logger = Logger.getLogger(br.gov.pbh.desif.ws.jaxws.DesifWS.class.getName());
        URL url = null;
        try
        {
            URL baseUrl = br.gov.pbh.desif.ws.jaxws.DesifWS.class.getResource(".");
            url = new URL(baseUrl, "http://homologa.prodemge.gov.br/desif-ws/ws?wsdl");
        }
        catch(MalformedURLException e)
        {
            logger.warning("Failed to create URL for the wsdl Location: 'http://homologa.prodemge.gov.br/desif-ws/ws?wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        DESIFWS_WSDL_LOCATION = url;
    }
}
