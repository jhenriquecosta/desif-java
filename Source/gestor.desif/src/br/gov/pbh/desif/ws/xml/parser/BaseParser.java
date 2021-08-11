

package br.gov.pbh.desif.ws.xml.parser;

import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.Arrays;
import java.math.BigInteger;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public abstract class BaseParser
{
    protected static final String XSI_PREFIX = "xsi";
    public static final String SCHEMA_PREFIX = "http://es605-304:8080/schemas3/";
    protected final String nameSpacePrefix;
    protected final String nameSpaceURI;
    protected final BaseParser[] parsersUtilizados;
    
    protected BaseParser(final String nameSpacePrefix, final String nameSpaceURI, final BaseParser... parsersUtilizados)
    {
        this.nameSpacePrefix = nameSpacePrefix;
        this.nameSpaceURI = nameSpaceURI;
        this.parsersUtilizados = parsersUtilizados;
    }
    
    public String getNameSpacePrefix() {
        return this.nameSpacePrefix;
    }
    
    public String getNameSpaceURI() {
        return this.nameSpaceURI;
    }
    
    public static final String readStartDocument(final XMLStreamReader xml) throws XMLStreamException {
        xml.require(7, null, null);
        ParserHelper.posicionaProximoElemento(xml);
        return xml.getNamespaceURI();
    }
    
    public static final void readEndDocument(final XMLStreamReader xml, final boolean close) throws XMLStreamException {
        xml.require(8, null, null);
        if (close) {
            xml.close();
        }
    }
    
    public static final void writeStartDocument(final XMLStreamWriter xml) throws XMLStreamException {
        xml.writeStartDocument("UTF-8", "1.0");
    }
    
    public static final void writeEndDocument(final XMLStreamWriter xml, final boolean close) throws XMLStreamException {
        xml.writeEndDocument();
        if (close) {
            xml.close();
        }
    }
    
    protected String consomeStartRetornaId(final XMLStreamReader xml, final String localName, final boolean optional) throws XMLStreamException {
        if (null == localName) {
            throw new IllegalArgumentException("localName n\u00e3o pode ser nulo!");
        }
        if (optional && !this.isStartElement(xml, localName)) {
            return null;
        }
        this.requireStart(xml, this.nameSpaceURI, localName);
        final String retorno = xml.getAttributeValue(null, "id");
        ParserHelper.posicionaProximoElemento(xml);
        return retorno;
    }
    
    protected String consomeStartRetornaId(final XMLStreamReader xml, final String localName, final boolean optional, final String nameSpace) throws XMLStreamException {
        if (null == localName) {
            throw new IllegalArgumentException("localName n\u00e3o pode ser nulo!");
        }
        if (optional && !this.isStartElement(xml, localName)) {
            return null;
        }
        this.requireStart(xml, nameSpace, localName);
        final String retorno = xml.getAttributeValue(null, "id");
        ParserHelper.posicionaProximoElemento(xml);
        return retorno;
    }
    
    protected boolean isStartElement(final XMLStreamReader xml, final String localName) {
        return xml.getEventType() == 1 && (null == localName || localName.equals(xml.getLocalName()));
    }
    
    protected void throwException(final String text, final XMLStreamReader xml) throws XMLStreamException {
        this.throwException(text, xml, null);
    }
    
    protected void throwException(final String text, final XMLStreamReader xml, final Throwable th) throws XMLStreamException {
        throw (xml == null) ? new XMLStreamException(text, th) : new XMLStreamException(text, xml.getLocation(), th);
    }
    
    protected void checkNotEmpty(final String typeName, final Object value, final XMLStreamReader xml) throws XMLStreamException {
        if (ParserHelper.isEmpty(value)) {
            this.throwException(typeName + " n\u00e3o pode ser vazio!", xml);
        }
    }
    
    protected void checkLength(final String typeName, final String value, final int length, final XMLStreamReader xml) throws XMLStreamException {
        if (value != null && value.length() != length) {
            this.throwException(typeName + " tem que ter exatamente " + length + " caracteres.", xml);
        }
    }
    
    protected void checkMaxLength(final String typeName, final String value, final int maxLength, final XMLStreamReader xml) throws XMLStreamException {
        if (value != null && value.length() > maxLength) {
            this.throwException(typeName + " n\u00e3o pode ter mais que " + maxLength + " caracteres.", xml);
        }
    }
    
    protected void checkMinLength(final String typeName, final String value, final int minLength, final XMLStreamReader xml) throws XMLStreamException {
        if (value == null || value.length() < minLength) {
            this.throwException(typeName + " n\u00e3o pode ter menos que " + minLength + " caracteres.", xml);
        }
    }
    
    protected void checkTotalDigits(final String typeName, final Number value, final int totalDigits, final boolean fixed, final XMLStreamReader xml) throws XMLStreamException {
        this.checkTotalDigits(typeName, value, totalDigits, 0, fixed, xml);
    }
    
    protected void checkTotalDigits(final String typeName, final Number value, final int totalDigits, final int fractionDigits, final boolean fixed, final XMLStreamReader xml) throws XMLStreamException {
        if (value != null) {
            if (value instanceof Long || value instanceof Integer) {
                final long maxNumber = (value instanceof Long) ? ParserHelper.getMaxLong(totalDigits) : ParserHelper.getMaxInt(totalDigits);
                long longValue = value.longValue();
                if (longValue < 0L) {
                    longValue = -longValue;
                }
                if (longValue > maxNumber) {
                    this.throwException(typeName + " n\u00e3o pode ser maior que " + maxNumber + " (" + totalDigits + " digitos): " + value, xml);
                }
                else if (fixed) {
                    final long minNumber = (value instanceof Long) ? ParserHelper.getMinLong(totalDigits) : ParserHelper.getMinInt(totalDigits);
                    if (longValue < minNumber) {
                        this.throwException(typeName + " n\u00e3o pode ser menor que " + minNumber + " (" + totalDigits + " digitos): " + value, xml);
                    }
                }
            }
            else {
                if (!(value instanceof BigInteger)) {
                    throw new IllegalArgumentException(("Tipo de numero n\u00e3o suportado: " + value == null) ? null : value.getClass().getName());
                }
                final BigInteger maxNumber2 = ParserHelper.getMaxBigInteger(totalDigits);
                final BigInteger bigIntegerValue = ((BigInteger)value).abs();
                if (bigIntegerValue.compareTo(maxNumber2) > 0) {
                    this.throwException(typeName + " n\u00e3o pode ser maior que " + maxNumber2 + " (" + totalDigits + " digitos): " + value, xml);
                }
                else if (fixed) {
                    final BigInteger minNumber2 = ParserHelper.getMinBigInteger(totalDigits);
                    if (bigIntegerValue.compareTo(minNumber2) < 0) {
                        this.throwException(typeName + " n\u00e3o pode ser menor que " + minNumber2 + " (" + totalDigits + " digitos): " + value, xml);
                    }
                }
            }
        }
    }
    
    protected void checkNumberOptions(final String typeName, final Integer value, final XMLStreamReader xml, final Integer... options) throws XMLStreamException {
        if (value != null) {
            boolean ok = false;
            for (final Number number : options) {
                if (value.equals(number)) {
                    ok = true;
                    break;
                }
            }
            if (!ok) {
                this.throwException(typeName + " s\u00f3 pode ser uma das seguintes op\u00e7\u00f5es: " + Arrays.toString(options), xml);
            }
        }
    }
    
    protected boolean consomeStart(final XMLStreamReader xml, final String localName, final boolean optional, final String nameSpace) throws XMLStreamException {
        if (null == localName) {
            throw new IllegalArgumentException("localName n\u00e3o pode ser nulo!");
        }
        if (optional && !this.isStartElement(xml, localName)) {
            return false;
        }
        this.requireStart(xml, nameSpace, localName);
        ParserHelper.posicionaProximoElemento(xml);
        return true;
    }
    
    protected void consomeStart(final XMLStreamReader xml, final String localName) throws XMLStreamException {
        this.consomeStart(xml, localName, false);
    }
    
    protected void consomeStart(final XMLStreamReader xml, final String localName, final String nameSpace) throws XMLStreamException {
        this.consomeStart(xml, localName, false, null);
    }
    
    protected boolean consomeStart(final XMLStreamReader xml, final String localName, final boolean optional) throws XMLStreamException {
        if (null == localName) {
            throw new IllegalArgumentException("localName n\u00e3o pode ser nulo!");
        }
        if (optional && !this.isStartElement(xml, localName)) {
            return false;
        }
        this.requireStart(xml, this.nameSpaceURI, localName);
        ParserHelper.posicionaProximoElemento(xml);
        return true;
    }
    
    protected void consomeEnd(final XMLStreamReader xml, final String localName, final String nameSpace) throws XMLStreamException {
        if (null == localName) {
            throw new IllegalArgumentException("localName n\u00e3o pode ser nulo!");
        }
        this.requireEnd(xml, nameSpace, localName);
        ParserHelper.posicionaProximoElemento(xml);
    }
    
    protected void consomeEnd(final XMLStreamReader xml, final String localName) throws XMLStreamException {
        if (null == localName) {
            throw new IllegalArgumentException("localName n\u00e3o pode ser nulo!");
        }
        this.requireEnd(xml, this.nameSpaceURI, localName);
        ParserHelper.posicionaProximoElemento(xml);
    }
    
    protected void requireStart(final XMLStreamReader xml, final String nameSpace, final String localName) throws XMLStreamException {
        if (!xml.isStartElement()) {
            this.throwException("\u00c9 esperado o in\u00edcio do elemento " + localName + " nessa posi\u00e7\u00e3o, mas foi encontrado um evento " + ParserHelper.getEventTypeString(xml), xml);
        }
        if (nameSpace != null || localName != null) {
            this.requireNamespaceAndLocalName(xml, nameSpace, localName);
        }
    }
    
    protected void requireEnd(final XMLStreamReader xml, final String nameSpace, final String localName) throws XMLStreamException {
        if (!xml.isEndElement()) {
            this.throwException("\u00c9 esperado o fechamento do elemento " + localName + " nessa posi\u00e7\u00e3o, mas foi encontrado um evento " + ParserHelper.getEventTypeString(xml), xml);
        }
        if (nameSpace != null || localName != null) {
            this.requireNamespaceAndLocalName(xml, nameSpace, localName);
        }
    }
    
    private void requireNamespaceAndLocalName(final XMLStreamReader xml, final String nameSpace, final String localName) throws XMLStreamException {
        if (nameSpace != null) {
            final String uri = xml.getNamespaceURI();
            if (nameSpace.length() == 0) {
                if (uri != null && uri.length() > 0) {
                    this.throwException("\u00c9 esperado um namespace vazio, mas o namespace \u00e9 " + uri, xml);
                }
            }
            else if (!nameSpace.equals(uri)) {
                this.throwException("\u00c9 esperado o namespace " + nameSpace + ", no entanto foi encontrado " + uri, xml);
            }
        }
        if (localName != null) {
            final String name = xml.getLocalName();
            if (!localName.equals(name)) {
                this.throwException("Esperado nome " + localName + ", mas foi encontrado " + name, xml);
            }
        }
    }
    
    public void writeStartElement(final XMLStreamWriter xml, final String localName, final boolean elementoRaiz, final boolean insereNameSpaceLocal) throws XMLStreamException {
        if (!insereNameSpaceLocal) {
            xml.writeStartElement(this.nameSpaceURI, localName);
        }
        else {
            xml.writeStartElement(null, localName);
            xml.writeAttribute("xmlns", this.nameSpaceURI);
        }
        if (elementoRaiz) {
            this.writeNamespaces(xml, this.nameSpaceURI, insereNameSpaceLocal);
        }
    }
    
    public void writeEndElement(final XMLStreamWriter xml) throws XMLStreamException {
        xml.writeEndElement();
    }
    
    public void writeDirectStrings(final XMLStreamWriter xml, final Collection<String> listaXML) throws XMLStreamException {
        if (!ParserHelper.isEmpty(listaXML)) {
            xml.writeCharacters("");
            xml.flush();
            final Writer w = (Writer)xml.getProperty("com.ctc.wstx.outputUnderlyingWriter");
            if (w == null) {
                throw new XMLStreamException("N\u00e3o conseguiu obter o Writer a partir do XMLStreamWriter do tipo " + xml.getClass() + ". Deveria ser filho do tipo com.ctc.wstx.sw.BaseStreamWriter");
            }
            try {
                for (final String objeto : listaXML) {
                    w.write(objeto);
                }
            }
            catch (IOException e) {
                throw new XMLStreamException("N\u00e3o conseguiu escrever diretamente para o Writer.", e);
            }
            try {
                w.flush();
            }
            catch (IOException e) {
                throw new XMLStreamException("Erro inexperado ao dar \"flush\" no writer.", e);
            }
        }
    }
    
    protected void consomeSignature(final XMLStreamReader xml) throws XMLStreamException {
        if (this.consomeStart(xml, "Signature", true, "http://www.w3.org/2000/09/xmldsig#")) {
            while (xml.hasNext() && (xml.getEventType() != 2 || !"Signature".equals(xml.getLocalName()))) {
                ParserHelper.posicionaProximoElemento(xml);
            }
            this.consomeEnd(xml, "Signature", "http://www.w3.org/2000/09/xmldsig#");
        }
    }
    
    private void writeNamespaces(final XMLStreamWriter xml, final String defaultNamespaceURI, final boolean usaNamespaceLocal) throws XMLStreamException {
        if (usaNamespaceLocal) {
            for (final BaseParser parser : this.parsersUtilizados) {
                if ("xsi".equals(parser.getNameSpacePrefix())) {
                    break;
                }
            }
        }
        else {
            xml.writeDefaultNamespace(defaultNamespaceURI);
            for (final BaseParser parser : this.parsersUtilizados) {
                if (!ParserHelper.isEmpty(parser.getNameSpacePrefix())) {
                    xml.writeNamespace(parser.getNameSpacePrefix(), parser.getNameSpaceURI());
                }
            }
            xml.writeNamespace("ds", "http://www.w3.org/2000/09/xmldsig#");
        }
    }
    
    private String getSchemaLocation(final String defaultNamespaceURI) {
        final StringBuilder sb = new StringBuilder(defaultNamespaceURI).append(" schemas/").append(this.getFileName(defaultNamespaceURI)).append(' ');
        for (final BaseParser parser : this.parsersUtilizados) {
            if (!ParserHelper.isEmpty(parser.getNameSpacePrefix()) && !"xsi".equals(parser.getNameSpacePrefix())) {
                sb.append(parser.getNameSpaceURI()).append(' ').append(this.getFileName(parser.getNameSpaceURI())).append(' ');
            }
        }
        sb.append("http://www.w3.org/2000/09/xmldsig# xmldsig-core-schema20020212.xsd");
        return sb.toString();
    }
    
    private String getFileName(final String namespaceURI) {
        return namespaceURI.substring(namespaceURI.lastIndexOf(47) + 1);
    }
}
