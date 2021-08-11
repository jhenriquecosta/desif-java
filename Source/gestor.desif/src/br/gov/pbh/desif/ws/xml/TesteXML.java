/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.ws.xml;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.Comment;
import javax.xml.stream.events.DTD;
import javax.xml.stream.events.EndDocument;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.EntityDeclaration;
import javax.xml.stream.events.EntityReference;
import javax.xml.stream.events.Namespace;
import javax.xml.stream.events.NotationDeclaration;
import javax.xml.stream.events.ProcessingInstruction;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class TesteXML {
    public static void main(String[] args) throws Exception {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        String fileName = "servico_consultar_nfse_envio.xml";
        FileInputStream fileInputStream = new FileInputStream(fileName);
        XMLEventReader eventReader = inputFactory.createXMLEventReader(fileInputStream);
        while (eventReader.hasNext()) {
            XMLEvent event = eventReader.nextEvent();
            if (event.isStartDocument()) {
                TesteXML.printInfo((StartDocument)event);
                continue;
            }
            if (event.isStartElement()) {
                TesteXML.printInfo(event.asStartElement());
                continue;
            }
            if (event.isAttribute() || event instanceof Attribute) {
                TesteXML.printInfo((Attribute)event);
                continue;
            }
            if (event.isEntityReference()) {
                TesteXML.printInfo((EntityReference)event);
                continue;
            }
            if (event.isCharacters()) {
                TesteXML.printInfo(event.asCharacters());
                continue;
            }
            if (event.isNamespace()) {
                TesteXML.printInfo((Namespace)event);
                continue;
            }
            if (event.isProcessingInstruction()) {
                TesteXML.printInfo((ProcessingInstruction)event);
                continue;
            }
            if (event.isEndElement()) {
                TesteXML.printInfo(event.asEndElement());
                continue;
            }
            if (event.isEndDocument()) {
                TesteXML.printInfo((EndDocument)event);
                continue;
            }
            if (event.getEventType() == 5) {
                TesteXML.printInfo((Comment)event);
                continue;
            }
            if (event.getEventType() == 11) {
                TesteXML.printInfo((DTD)event);
                continue;
            }
            if (event.getEventType() == 15) {
                TesteXML.printInfo((EntityDeclaration)event);
                continue;
            }
            if (event.getEventType() != 14) continue;
            TesteXML.printInfo((NotationDeclaration)event);
        }
    }

    static void printInfo(Attribute element) {
        System.out.println("------<ATTRIBUTE");
        System.out.println("DTDType: " + element.getDTDType());
        System.out.println("Name: " + element.getName());
        System.out.println("Value: " + element.getValue());
        System.out.println("isSpecified: " + element.isSpecified());
        System.out.println("------>ATTRIBUTE");
    }

    static void printInfo(Characters element) {
        System.out.println("------<CHARACTERS");
        System.out.println("CDATA: " + element.isCData());
        System.out.println("Ignorable White Space: " + element.isIgnorableWhiteSpace());
        System.out.println("White Space: " + element.isWhiteSpace());
        System.out.println("Data: \"" + element.getData() + "\"");
        System.out.println("------>CHARACTERS");
    }

    static void printInfo(Comment element) {
        System.out.println("------<COMMENT");
        System.out.println("Text: \"" + element.getText() + '\"');
        System.out.println("------>COMMENT");
    }

    static void printInfo(DTD element) {
        System.out.println("------<DTD");
        System.out.println("DocumentTypeDeclaration: " + element.getDocumentTypeDeclaration());
        System.out.println("Entities: " + element.getEntities());
        System.out.println("Notations: " + element.getNotations());
        System.out.println("Processed DTD: " + element.getProcessedDTD());
        System.out.println("------>DTD");
    }

    static void printInfo(EndDocument element) {
        System.out.println("------<END DOCUMENT");
        System.out.println("------>END DOCUMENT");
    }

    static void printInfo(EndElement element) {
        System.out.println("------<END ELEMENT");
        System.out.println("Name: " + element.getName());
        System.out.println("------>END ELEMENT");
    }

    static void printInfo(EntityDeclaration element) {
        System.out.println("------<ENTITY DECLARATION");
        System.out.println(": " + element.getBaseURI());
        System.out.println(": " + element.getName());
        System.out.println(": " + element.getNotationName());
        System.out.println(": " + element.getPublicId());
        System.out.println(": " + element.getReplacementText());
        System.out.println(": " + element.getSystemId());
        System.out.println("------>ENTITY DECLARATION");
    }

    static void printInfo(EntityReference element) {
        System.out.println("------<ENTITY REFERENCE");
        System.out.println(": " + element.getDeclaration());
        System.out.println(": " + element.getName());
        System.out.println("------>ENTITY REFERENCE");
    }

    static void printInfo(NotationDeclaration element) {
        System.out.println("------<NOTATION DECLARATION");
        System.out.println(": " + element.getName());
        System.out.println(": " + element.getPublicId());
        System.out.println(": " + element.getSystemId());
        System.out.println("------>NOTATION DECLARATION");
    }

    static void printInfo(ProcessingInstruction element) {
        System.out.println("------<PROCESSING INSTRUCTION");
        System.out.println(": " + element.getData());
        System.out.println(": " + element.getTarget());
        System.out.println("------>PROCESSING INSTRUCTION");
    }

    static void printInfo(StartDocument element) {
        System.out.println("------<START DOCUMENT");
        System.out.println("Encoding Set: " + element.encodingSet());
        System.out.println("Character Encoding Scheme: " + element.getCharacterEncodingScheme());
        System.out.println("Standalone Set: " + element.standaloneSet());
        System.out.println("Standalone: " + element.isStandalone());
        System.out.println("System Id: " + element.getSystemId());
        System.out.println("Version: " + element.getVersion());
        System.out.println("------>START DOCUMENT");
    }

    static void printInfo(StartElement element) {
        System.out.println("------<START ELEMENT");
        System.out.println("Name: " + element.getName());
        Iterator namespaces = element.getNamespaces();
        int i = 1;
        while (namespaces.hasNext()) {
            Namespace namespace = (Namespace)namespaces.next();
            System.out.println(" Namespace " + i);
            System.out.println("  Prefix: " + namespace.getPrefix());
            System.out.println("  Namespace URI: " + namespace.getNamespaceURI());
            System.out.println("  Name: " + namespace.getName());
            System.out.println("  DTD Type: " + namespace.getDTDType());
            ++i;
        }
        System.out.println("------>START ELEMENT");
    }
}

