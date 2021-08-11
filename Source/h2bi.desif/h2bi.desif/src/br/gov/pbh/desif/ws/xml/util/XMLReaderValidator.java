
package br.gov.pbh.desif.ws.xml.util;

import java.io.*;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class XMLReaderValidator
{
    private static class MyErrorHandler extends DefaultHandler
    {

        private int warning;
        private int error;
        private int fatalError;

        public void warning(SAXParseException e)
            throws SAXException
        {
            warning++;
            System.out.println("Warning: ");
            printInfo(e);
        }

        public void error(SAXParseException e)
            throws SAXException
        {
            error++;
            System.out.println("Error: ");
            printInfo(e);
        }

        public void fatalError(SAXParseException e)
            throws SAXException
        {
            fatalError++;
            System.out.println("Fattal error: ");
            printInfo(e);
        }

        private void printInfo(SAXParseException e)
        {
            System.out.println((new StringBuilder()).append("   Public ID: ").append(e.getPublicId()).toString());
            System.out.println((new StringBuilder()).append("   System ID: ").append(e.getSystemId()).toString());
            System.out.println((new StringBuilder()).append("   Line number: ").append(e.getLineNumber()).toString());
            System.out.println((new StringBuilder()).append("   Column number: ").append(e.getColumnNumber()).toString());
            System.out.println((new StringBuilder()).append("   Message: ").append(e.getMessage()).toString());
        }

        public void reset()
        {
            warning = error = fatalError = 0;
        }

        public boolean hasErrors()
        {
            return error > 0 || fatalError > 0;
        }

        public boolean hasWarnings()
        {
            return warning > 0;
        }

        public int getWarning()
        {
            return warning;
        }

        public int getError()
        {
            return error;
        }

        public int getFatalError()
        {
            return fatalError;
        }

        private MyErrorHandler()
        {
        }

    }


    private static final String parserClass = "org.apache.xerces.parsers.SAXParser";
    private static final String validationFeature = "http://xml.org/sax/features/validation";
    private static final String schemaFeature = "http://apache.org/xml/features/validation/schema";
    private static XMLReader reader;
    private static final MyErrorHandler myErrorHandler = new MyErrorHandler();

    public XMLReaderValidator()
    {
    }

    public static void main(String args[])
    {
        String x = null;
        x = "servico_enviar_lote_rps_resposta.xml";
        x = "servico_consultar_nfse_envio.xml";
        x = "teste.xml";
        validateFile(x);
    }

    public static void validateFile(String systemId)
    {
        validateXML(new InputSource(systemId));
    }

    public static void validateXML(Reader reader)
    {
        validateXML(new InputSource(reader));
    }

    public static void validateXML(InputSource source)
    {
        try
        {
            myErrorHandler.reset();
            XMLReader r = getReader();
            r.parse(source);
            if(myErrorHandler.hasErrors() || myErrorHandler.hasWarnings())
                System.out.println((new StringBuilder()).append("Sumary: errors=").append(myErrorHandler.getError()).append(",fatalErrors=").append(myErrorHandler.getFatalError()).append(",warnings=").append(myErrorHandler.getWarning()).toString());
            else
                System.out.println("XML OK!");
        }
        catch(SAXException e)
        {
            System.out.println(e.toString());
        }
        catch(IOException e)
        {
            System.out.println(e.toString());
        }
    }

    private static XMLReader getReader()
        throws SAXException, SAXNotRecognizedException, SAXNotSupportedException
    {
        if(reader == null)
        {
            reader = XMLReaderFactory.createXMLReader();
            reader.setFeature("http://xml.org/sax/features/validation", true);
            reader.setFeature("http://apache.org/xml/features/validation/schema", true);
            reader.setErrorHandler(myErrorHandler);
        }
        return reader;
    }

}
