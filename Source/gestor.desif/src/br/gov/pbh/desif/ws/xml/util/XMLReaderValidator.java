/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.ws.xml.util;

import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class XMLReaderValidator {
    private static final String parserClass = "org.apache.xerces.parsers.SAXParser";
    private static final String validationFeature = "http://xml.org/sax/features/validation";
    private static final String schemaFeature = "http://apache.org/xml/features/validation/schema";
    private static XMLReader reader;
    private static final MyErrorHandler myErrorHandler;

    public static void main(String[] args) {
        String x = null;
        x = "servico_enviar_lote_rps_resposta.xml";
        x = "servico_consultar_nfse_envio.xml";
        x = "teste.xml";
        XMLReaderValidator.validateFile(x);
    }

    public static void validateFile(String systemId) {
        XMLReaderValidator.validateXML(new InputSource(systemId));
    }

    public static void validateXML(Reader reader) {
        XMLReaderValidator.validateXML(new InputSource(reader));
    }

    public static void validateXML(InputSource source) {
        try {
            myErrorHandler.reset();
            XMLReader r = XMLReaderValidator.getReader();
            r.parse(source);
            if (myErrorHandler.hasErrors() || myErrorHandler.hasWarnings()) {
                System.out.println("Sumary: errors=" + myErrorHandler.getError() + ",fatalErrors=" + myErrorHandler.getFatalError() + ",warnings=" + myErrorHandler.getWarning());
            } else {
                System.out.println("XML OK!");
            }
        }
        catch (SAXException e) {
            System.out.println(e.toString());
        }
        catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    private static XMLReader getReader() throws SAXException, SAXNotRecognizedException, SAXNotSupportedException {
        if (reader == null) {
            reader = XMLReaderFactory.createXMLReader();
            reader.setFeature(validationFeature, true);
            reader.setFeature(schemaFeature, true);
            reader.setErrorHandler(myErrorHandler);
        }
        return reader;
    }

    static {
        myErrorHandler = new MyErrorHandler();
    }

    private static class MyErrorHandler
    extends DefaultHandler {
        private int warning;
        private int error;
        private int fatalError;

        private MyErrorHandler() {
        }

        @Override
        public void warning(SAXParseException e) throws SAXException {
            ++this.warning;
            System.out.println("Warning: ");
            this.printInfo(e);
        }

        @Override
        public void error(SAXParseException e) throws SAXException {
            ++this.error;
            System.out.println("Error: ");
            this.printInfo(e);
        }

        @Override
        public void fatalError(SAXParseException e) throws SAXException {
            ++this.fatalError;
            System.out.println("Fattal error: ");
            this.printInfo(e);
        }

        private void printInfo(SAXParseException e) {
            System.out.println("   Public ID: " + e.getPublicId());
            System.out.println("   System ID: " + e.getSystemId());
            System.out.println("   Line number: " + e.getLineNumber());
            System.out.println("   Column number: " + e.getColumnNumber());
            System.out.println("   Message: " + e.getMessage());
        }

        public void reset() {
            this.fatalError = 0;
            this.error = 0;
            this.warning = 0;
        }

        public boolean hasErrors() {
            return this.error > 0 || this.fatalError > 0;
        }

        public boolean hasWarnings() {
            return this.warning > 0;
        }

        public int getWarning() {
            return this.warning;
        }

        public int getError() {
            return this.error;
        }

        public int getFatalError() {
            return this.fatalError;
        }
    }

}

