
package br.gov.pbh.desif.ws.xml.util;

import java.io.Writer;
import javanet.staxutils.XMLEventStreamWriter;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.Characters;

public class XMLOperacoes {
    private static final XMLOperacoes instance = new XMLOperacoes();

    private XMLOperacoes() {
    }

    public static XMLOperacoes getInstance() {
        return instance;
    }

    public static class OperacoesXMLStreamWriter
    extends XMLEventStreamWriter {
        private final XMLEventWriter out;
        private final Characters quebraDeLinha;
        private Writer originalWriter;

        public OperacoesXMLStreamWriter(XMLEventWriter out, Writer originalWriter, Characters quebraDeLinha) {
            super(out);
            this.out = out;
            this.originalWriter = originalWriter;
            this.quebraDeLinha = quebraDeLinha;
        }

        public void insereQuebraDeLinha() throws XMLStreamException {
            if (this.quebraDeLinha != null) {
                this.out.add(this.quebraDeLinha);
            }
        }

        public Object getProperty(String name) throws IllegalArgumentException {
            if ("com.ctc.wstx.outputUnderlyingWriter".equals(name)) {
                return this.originalWriter;
            }
            return super.getProperty(name);
        }
    }

    public static interface Operacao {
        public void executa(XMLStreamWriter var1, Object[] var2) throws XMLStreamException;
    }

}

