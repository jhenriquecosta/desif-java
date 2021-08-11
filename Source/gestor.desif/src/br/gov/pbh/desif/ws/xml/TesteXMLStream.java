/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.ws.xml;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

public class TesteXMLStream {
    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream;
        String fileName;
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        XMLStreamReader xml = inputFactory.createXMLStreamReader(fileInputStream = new FileInputStream(fileName = "servico_enviar_lote_rps_envio.xml"));
        int eventType = xml.getEventType();
        System.out.println(eventType == 7);
        System.out.println(xml.getVersion());
        while (xml.hasNext()) {
            eventType = xml.next();
            if (eventType == 10) {
                System.out.println("ATTRIBUTE");
                continue;
            }
            if (eventType == 1) {
                System.out.println("START_ELEMENT");
                System.out.println(xml.getPrefix() + ":" + xml.getLocalName());
                xml.require(1, "http://es605-304:8080/schemas/servico_enviar_lote_rps_envio.xsd", "EnviarLoteRpsEnvio");
                int attributeCount = xml.getAttributeCount();
                for (int i = 0; i < attributeCount; ++i) {
                    System.out.println("Attribute: " + xml.getAttributeNamespace(i) + ':' + xml.getAttributeLocalName(i) + "=\"" + xml.getAttributeValue(i) + '\"');
                }
                int namespaceCount = xml.getNamespaceCount();
                for (int i = 0; i < namespaceCount; ++i) {
                    System.out.println("Namespace: " + xml.getNamespaceURI(i) + " " + xml.getNamespacePrefix(i));
                }
                continue;
            }
            if (eventType != 4) continue;
            System.out.println("CHARACTERS: \"" + xml.getText() + '\"' + xml.getTextLength() + ' ' + xml.getTextStart());
        }
    }
}

