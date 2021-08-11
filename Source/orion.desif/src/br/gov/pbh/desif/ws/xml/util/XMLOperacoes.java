
package br.gov.pbh.desif.ws.xml.util;

import java.io.Writer;
import javanet.staxutils.XMLEventStreamWriter;
import javax.xml.stream.*;
import javax.xml.stream.events.Characters;

public class XMLOperacoes
{
    public static class OperacoesXMLStreamWriter extends XMLEventStreamWriter
    {

        private final XMLEventWriter out;
        private final Characters quebraDeLinha;
        private final Writer originalWriter;

        public void insereQuebraDeLinha()
            throws XMLStreamException
        {
            if(quebraDeLinha != null)
                out.add(quebraDeLinha);
        }

        public Object getProperty(String name)
            throws IllegalArgumentException
        {
            if("com.ctc.wstx.outputUnderlyingWriter".equals(name))
                return originalWriter;
            else
                return super.getProperty(name);
        }

        public OperacoesXMLStreamWriter(XMLEventWriter out, Writer originalWriter, Characters quebraDeLinha)
        {
            super(out);
            this.out = out;
            this.originalWriter = originalWriter;
            this.quebraDeLinha = quebraDeLinha;
        }
    }

    public static interface Operacao
    {

        public abstract void executa(XMLStreamWriter xmlstreamwriter, Object aobj[])
            throws XMLStreamException;
    }


    private static final XMLOperacoes instance = new XMLOperacoes();

    private XMLOperacoes()
    {
    }

    public static XMLOperacoes getInstance()
    {
        return instance;
    }

}
