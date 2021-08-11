package br.gov.pbh.desif.ws.xml;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Iterator;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.*;

public class TesteXML
{

    public TesteXML()
    {
    }

    public static void main(String args[])
        throws Exception
    {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        String fileName = "servico_consultar_nfse_envio.xml";
        FileInputStream fileInputStream = new FileInputStream(fileName);
        XMLEventReader eventReader = inputFactory.createXMLEventReader(fileInputStream);
        do
        {
            if(!eventReader.hasNext())
                break;
            XMLEvent event = eventReader.nextEvent();
            if(event.isStartDocument())
                printInfo((StartDocument)event);
            else
            if(event.isStartElement())
                printInfo(event.asStartElement());
            else
            if(event.isAttribute() || (event instanceof Attribute))
                printInfo((Attribute)event);
            else
            if(event.isEntityReference())
                printInfo((EntityReference)event);
            else
            if(event.isCharacters())
                printInfo(event.asCharacters());
            else
            if(event.isNamespace())
                printInfo((Namespace)event);
            else
            if(event.isProcessingInstruction())
                printInfo((ProcessingInstruction)event);
            else
            if(event.isEndElement())
                printInfo(event.asEndElement());
            else
            if(event.isEndDocument())
                printInfo((EndDocument)event);
            else
            if(event.getEventType() == 5)
                printInfo((Comment)event);
            else
            if(event.getEventType() == 11)
                printInfo((DTD)event);
            else
            if(event.getEventType() == 15)
                printInfo((EntityDeclaration)event);
            else
            if(event.getEventType() == 14)
                printInfo((NotationDeclaration)event);
        } while(true);
    }

    static void printInfo(Attribute element)
    {
        System.out.println("------<ATTRIBUTE");
        System.out.println((new StringBuilder()).append("DTDType: ").append(element.getDTDType()).toString());
        System.out.println((new StringBuilder()).append("Name: ").append(element.getName()).toString());
        System.out.println((new StringBuilder()).append("Value: ").append(element.getValue()).toString());
        System.out.println((new StringBuilder()).append("isSpecified: ").append(element.isSpecified()).toString());
        System.out.println("------>ATTRIBUTE");
    }

    static void printInfo(Characters element)
    {
        System.out.println("------<CHARACTERS");
        System.out.println((new StringBuilder()).append("CDATA: ").append(element.isCData()).toString());
        System.out.println((new StringBuilder()).append("Ignorable White Space: ").append(element.isIgnorableWhiteSpace()).toString());
        System.out.println((new StringBuilder()).append("White Space: ").append(element.isWhiteSpace()).toString());
        System.out.println((new StringBuilder()).append("Data: \"").append(element.getData()).append("\"").toString());
        System.out.println("------>CHARACTERS");
    }

    static void printInfo(Comment element)
    {
        System.out.println("------<COMMENT");
        System.out.println((new StringBuilder()).append("Text: \"").append(element.getText()).append('"').toString());
        System.out.println("------>COMMENT");
    }

    static void printInfo(DTD element)
    {
        System.out.println("------<DTD");
        System.out.println((new StringBuilder()).append("DocumentTypeDeclaration: ").append(element.getDocumentTypeDeclaration()).toString());
        System.out.println((new StringBuilder()).append("Entities: ").append(element.getEntities()).toString());
        System.out.println((new StringBuilder()).append("Notations: ").append(element.getNotations()).toString());
        System.out.println((new StringBuilder()).append("Processed DTD: ").append(element.getProcessedDTD()).toString());
        System.out.println("------>DTD");
    }

    static void printInfo(EndDocument element)
    {
        System.out.println("------<END DOCUMENT");
        System.out.println("------>END DOCUMENT");
    }

    static void printInfo(EndElement element)
    {
        System.out.println("------<END ELEMENT");
        System.out.println((new StringBuilder()).append("Name: ").append(element.getName()).toString());
        System.out.println("------>END ELEMENT");
    }

    static void printInfo(EntityDeclaration element)
    {
        System.out.println("------<ENTITY DECLARATION");
        System.out.println((new StringBuilder()).append(": ").append(element.getBaseURI()).toString());
        System.out.println((new StringBuilder()).append(": ").append(element.getName()).toString());
        System.out.println((new StringBuilder()).append(": ").append(element.getNotationName()).toString());
        System.out.println((new StringBuilder()).append(": ").append(element.getPublicId()).toString());
        System.out.println((new StringBuilder()).append(": ").append(element.getReplacementText()).toString());
        System.out.println((new StringBuilder()).append(": ").append(element.getSystemId()).toString());
        System.out.println("------>ENTITY DECLARATION");
    }

    static void printInfo(EntityReference element)
    {
        System.out.println("------<ENTITY REFERENCE");
        System.out.println((new StringBuilder()).append(": ").append(element.getDeclaration()).toString());
        System.out.println((new StringBuilder()).append(": ").append(element.getName()).toString());
        System.out.println("------>ENTITY REFERENCE");
    }

    static void printInfo(NotationDeclaration element)
    {
        System.out.println("------<NOTATION DECLARATION");
        System.out.println((new StringBuilder()).append(": ").append(element.getName()).toString());
        System.out.println((new StringBuilder()).append(": ").append(element.getPublicId()).toString());
        System.out.println((new StringBuilder()).append(": ").append(element.getSystemId()).toString());
        System.out.println("------>NOTATION DECLARATION");
    }

    static void printInfo(ProcessingInstruction element)
    {
        System.out.println("------<PROCESSING INSTRUCTION");
        System.out.println((new StringBuilder()).append(": ").append(element.getData()).toString());
        System.out.println((new StringBuilder()).append(": ").append(element.getTarget()).toString());
        System.out.println("------>PROCESSING INSTRUCTION");
    }

    static void printInfo(StartDocument element)
    {
        System.out.println("------<START DOCUMENT");
        System.out.println((new StringBuilder()).append("Encoding Set: ").append(element.encodingSet()).toString());
        System.out.println((new StringBuilder()).append("Character Encoding Scheme: ").append(element.getCharacterEncodingScheme()).toString());
        System.out.println((new StringBuilder()).append("Standalone Set: ").append(element.standaloneSet()).toString());
        System.out.println((new StringBuilder()).append("Standalone: ").append(element.isStandalone()).toString());
        System.out.println((new StringBuilder()).append("System Id: ").append(element.getSystemId()).toString());
        System.out.println((new StringBuilder()).append("Version: ").append(element.getVersion()).toString());
        System.out.println("------>START DOCUMENT");
    }

    static void printInfo(StartElement element)
    {
        System.out.println("------<START ELEMENT");
        System.out.println((new StringBuilder()).append("Name: ").append(element.getName()).toString());
        Iterator namespaces = element.getNamespaces();
        for(int i = 1; namespaces.hasNext(); i++)
        {
            Namespace namespace = (Namespace)namespaces.next();
            System.out.println((new StringBuilder()).append(" Namespace ").append(i).toString());
            System.out.println((new StringBuilder()).append("  Prefix: ").append(namespace.getPrefix()).toString());
            System.out.println((new StringBuilder()).append("  Namespace URI: ").append(namespace.getNamespaceURI()).toString());
            System.out.println((new StringBuilder()).append("  Name: ").append(namespace.getName()).toString());
            System.out.println((new StringBuilder()).append("  DTD Type: ").append(namespace.getDTDType()).toString());
        }

        System.out.println("------>START ELEMENT");
    }
}
