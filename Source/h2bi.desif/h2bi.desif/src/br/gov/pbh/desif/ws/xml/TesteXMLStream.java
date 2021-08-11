
package br.gov.pbh.desif.ws.xml;

import java.io.FileInputStream;
import java.io.PrintStream;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

public class TesteXMLStream
{

    public TesteXMLStream()
    {
    }

    public static void main(String args[])
        throws Exception
    {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        String fileName = "servico_enviar_lote_rps_envio.xml";
        FileInputStream fileInputStream = new FileInputStream(fileName);
        XMLStreamReader xml = inputFactory.createXMLStreamReader(fileInputStream);
        int eventType = xml.getEventType();
        System.out.println(eventType == 7);
        System.out.println(xml.getVersion());
        do
        {
            if(!xml.hasNext())
                break;
            eventType = xml.next();
            if(eventType == 10)
                System.out.println("ATTRIBUTE");
            else
            if(eventType == 1)
            {
                System.out.println("START_ELEMENT");
                System.out.println((new StringBuilder()).append(xml.getPrefix()).append(":").append(xml.getLocalName()).toString());
                xml.require(1, "http://es605-304:8080/schemas/servico_enviar_lote_rps_envio.xsd", "EnviarLoteRpsEnvio");
                int attributeCount = xml.getAttributeCount();
                for(int i = 0; i < attributeCount; i++)
                    System.out.println((new StringBuilder()).append("Attribute: ").append(xml.getAttributeNamespace(i)).append(':').append(xml.getAttributeLocalName(i)).append("=\"").append(xml.getAttributeValue(i)).append('"').toString());

                int namespaceCount = xml.getNamespaceCount();
                int i = 0;
                while(i < namespaceCount) 
                {
                    System.out.println((new StringBuilder()).append("Namespace: ").append(xml.getNamespaceURI(i)).append(" ").append(xml.getNamespacePrefix(i)).toString());
                    i++;
                }
            } else
            if(eventType == 4)
                System.out.println((new StringBuilder()).append("CHARACTERS: \"").append(xml.getText()).append('"').append(xml.getTextLength()).append(' ').append(xml.getTextStart()).toString());
        } while(true);
    }
}
