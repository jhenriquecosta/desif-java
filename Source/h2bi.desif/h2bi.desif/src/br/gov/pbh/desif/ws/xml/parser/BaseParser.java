
package br.gov.pbh.desif.ws.xml.parser;

import java.io.IOException;
import java.io.Writer;
import java.math.BigInteger;
import java.util.*;
import javax.xml.stream.*;

// Referenced classes of package br.gov.pbh.desif.ws.xml.parser:
//            ParserHelper

public abstract class BaseParser
{

    protected static final String XSI_PREFIX = "xsi";
    public static final String SCHEMA_PREFIX = "http://es605-304:8080/schemas2/";
    protected final String nameSpacePrefix;
    protected final String nameSpaceURI;
    protected final BaseParser parsersUtilizados[];

    BaseParser(String nameSpacePrefix, String nameSpaceURI, BaseParser parsersUtilizados[])
    {
        this.nameSpacePrefix = nameSpacePrefix;
        this.nameSpaceURI = nameSpaceURI;
        this.parsersUtilizados = parsersUtilizados;
    }

    public String getNameSpacePrefix()
    {
        return nameSpacePrefix;
    }

    public String getNameSpaceURI()
    {
        return nameSpaceURI;
    }

    public static final String readStartDocument(XMLStreamReader xml)
        throws XMLStreamException
    {
        xml.require(7, null, null);
        ParserHelper.posicionaProximoElemento(xml);
        return xml.getNamespaceURI();
    }

    public static final void readEndDocument(XMLStreamReader xml, boolean close)
        throws XMLStreamException
    {
        xml.require(8, null, null);
        if(close)
            xml.close();
    }

    public static final void writeStartDocument(XMLStreamWriter xml)
        throws XMLStreamException
    {
        xml.writeStartDocument("UTF-8", "1.0");
    }

    public static final void writeEndDocument(XMLStreamWriter xml, boolean close)
        throws XMLStreamException
    {
        xml.writeEndDocument();
        if(close)
            xml.close();
    }

    protected String consomeStartRetornaId(XMLStreamReader xml, String localName, boolean optional)
        throws XMLStreamException
    {
        if(null == localName)
            throw new IllegalArgumentException("localName n\343o pode ser nulo!");
        if(optional && !isStartElement(xml, localName))
        {
            return null;
        } else
        {
            requireStart(xml, nameSpaceURI, localName);
            String retorno = xml.getAttributeValue(null, "id");
            ParserHelper.posicionaProximoElemento(xml);
            return retorno;
        }
    }

    protected String consomeStartRetornaId(XMLStreamReader xml, String localName, boolean optional, String nameSpace)
        throws XMLStreamException
    {
        if(null == localName)
            throw new IllegalArgumentException("localName n\343o pode ser nulo!");
        if(optional && !isStartElement(xml, localName))
        {
            return null;
        } else
        {
            requireStart(xml, nameSpace, localName);
            String retorno = xml.getAttributeValue(null, "id");
            ParserHelper.posicionaProximoElemento(xml);
            return retorno;
        }
    }

    protected boolean isStartElement(XMLStreamReader xml, String localName)
    {
        return xml.getEventType() == 1 && (null == localName || localName.equals(xml.getLocalName()));
    }

    protected void throwException(String text, XMLStreamReader xml)
        throws XMLStreamException
    {
        throwException(text, xml, null);
    }

    protected void throwException(String text, XMLStreamReader xml, Throwable th)
        throws XMLStreamException
    {
        throw xml != null ? new XMLStreamException(text, xml.getLocation(), th) : new XMLStreamException(text, th);
    }

    protected void checkNotEmpty(String typeName, Object value, XMLStreamReader xml)
        throws XMLStreamException
    {
        if(ParserHelper.isEmpty(value))
            throwException((new StringBuilder()).append(typeName).append(" n\343o pode ser vazio!").toString(), xml);
    }

    protected void checkLength(String typeName, String value, int length, XMLStreamReader xml)
        throws XMLStreamException
    {
        if(value != null && value.length() != length)
            throwException((new StringBuilder()).append(typeName).append(" tem que ter exatamente ").append(length).append(" caracteres.").toString(), xml);
    }

    protected void checkMaxLength(String typeName, String value, int maxLength, XMLStreamReader xml)
        throws XMLStreamException
    {
        if(value != null && value.length() > maxLength)
            throwException((new StringBuilder()).append(typeName).append(" n\343o pode ter mais que ").append(maxLength).append(" caracteres.").toString(), xml);
    }

    protected void checkMinLength(String typeName, String value, int minLength, XMLStreamReader xml)
        throws XMLStreamException
    {
        if(value == null || value.length() < minLength)
            throwException((new StringBuilder()).append(typeName).append(" n\343o pode ter menos que ").append(minLength).append(" caracteres.").toString(), xml);
    }

    protected void checkTotalDigits(String typeName, Number value, int totalDigits, boolean fixed, XMLStreamReader xml)
        throws XMLStreamException
    {
        checkTotalDigits(typeName, value, totalDigits, 0, fixed, xml);
    }

    protected void checkTotalDigits(String typeName, Number value, int totalDigits, int fractionDigits, boolean fixed, XMLStreamReader xml)
        throws XMLStreamException
    {
        if(value != null)
            if((value instanceof Long) || (value instanceof Integer))
            {
                long maxNumber = (value instanceof Long) ? ParserHelper.getMaxLong(totalDigits) : ParserHelper.getMaxInt(totalDigits);
                long longValue = value.longValue();
                if(longValue < 0L)
                    longValue = -longValue;
                if(longValue > maxNumber)
                    throwException((new StringBuilder()).append(typeName).append(" n\343o pode ser maior que ").append(maxNumber).append(" (").append(totalDigits).append(" digitos): ").append(value).toString(), xml);
                else
                if(fixed)
                {
                    long minNumber = (value instanceof Long) ? ParserHelper.getMinLong(totalDigits) : ParserHelper.getMinInt(totalDigits);
                    if(longValue < minNumber)
                        throwException((new StringBuilder()).append(typeName).append(" n\343o pode ser menor que ").append(minNumber).append(" (").append(totalDigits).append(" digitos): ").append(value).toString(), xml);
                }
            } else
            if(value instanceof BigInteger)
            {
                BigInteger maxNumber = ParserHelper.getMaxBigInteger(totalDigits);
                BigInteger bigIntegerValue = ((BigInteger)value).abs();
                if(bigIntegerValue.compareTo(maxNumber) > 0)
                    throwException((new StringBuilder()).append(typeName).append(" n\343o pode ser maior que ").append(maxNumber).append(" (").append(totalDigits).append(" digitos): ").append(value).toString(), xml);
                else
                if(fixed)
                {
                    BigInteger minNumber = ParserHelper.getMinBigInteger(totalDigits);
                    if(bigIntegerValue.compareTo(minNumber) < 0)
                        throwException((new StringBuilder()).append(typeName).append(" n\343o pode ser menor que ").append(minNumber).append(" (").append(totalDigits).append(" digitos): ").append(value).toString(), xml);
                }
            } else
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Tipo de numero n\343o suportado: ").append(value).toString() != null ? value.getClass().getName() : null);
            }
    }

    protected  void checkNumberOptions(String typeName, Integer value, XMLStreamReader xml, Integer options[])
        throws XMLStreamException
    {
        if(value != null)
        {
            boolean ok = false;
            Integer arr$[] = options;
            int len$ = arr$.length;
            int i$ = 0;
            do
            {
                if(i$ >= len$)
                    break;
                Number number = arr$[i$];
                if(value.equals(number))
                {
                    ok = true;
                    break;
                }
                i$++;
            } while(true);
            if(!ok)
                throwException((new StringBuilder()).append(typeName).append(" s\363 pode ser uma das seguintes op\347\365es: ").append(Arrays.toString(options)).toString(), xml);
        }
    }

    protected boolean consomeStart(XMLStreamReader xml, String localName, boolean optional, String nameSpace)
        throws XMLStreamException
    {
        if(null == localName)
            throw new IllegalArgumentException("localName n\343o pode ser nulo!");
        if(optional && !isStartElement(xml, localName))
        {
            return false;
        } else
        {
            requireStart(xml, nameSpace, localName);
            ParserHelper.posicionaProximoElemento(xml);
            return true;
        }
    }

    protected void consomeStart(XMLStreamReader xml, String localName)
        throws XMLStreamException
    {
        consomeStart(xml, localName, false);
    }

    protected void consomeStart(XMLStreamReader xml, String localName, String nameSpace)
        throws XMLStreamException
    {
        consomeStart(xml, localName, false, null);
    }

    protected boolean consomeStart(XMLStreamReader xml, String localName, boolean optional)
        throws XMLStreamException
    {
        if(null == localName)
            throw new IllegalArgumentException("localName n\343o pode ser nulo!");
        if(optional && !isStartElement(xml, localName))
        {
            return false;
        } else
        {
            requireStart(xml, nameSpaceURI, localName);
            ParserHelper.posicionaProximoElemento(xml);
            return true;
        }
    }

    protected void consomeEnd(XMLStreamReader xml, String localName, String nameSpace)
        throws XMLStreamException
    {
        if(null == localName)
        {
            throw new IllegalArgumentException("localName n\343o pode ser nulo!");
        } else
        {
            requireEnd(xml, nameSpace, localName);
            ParserHelper.posicionaProximoElemento(xml);
            return;
        }
    }

    protected void consomeEnd(XMLStreamReader xml, String localName)
        throws XMLStreamException
    {
        if(null == localName)
        {
            throw new IllegalArgumentException("localName n\343o pode ser nulo!");
        } else
        {
            requireEnd(xml, nameSpaceURI, localName);
            ParserHelper.posicionaProximoElemento(xml);
            return;
        }
    }

    protected void requireStart(XMLStreamReader xml, String nameSpace, String localName)
        throws XMLStreamException
    {
        if(!xml.isStartElement())
            throwException((new StringBuilder()).append("\311 esperado o in\355cio do elemento ").append(localName).append(" nessa posi\347\343o, mas foi encontrado um evento ").append(ParserHelper.getEventTypeString(xml)).toString(), xml);
        if(nameSpace != null || localName != null)
            requireNamespaceAndLocalName(xml, nameSpace, localName);
    }

    protected void requireEnd(XMLStreamReader xml, String nameSpace, String localName)
        throws XMLStreamException
    {
        if(!xml.isEndElement())
            throwException((new StringBuilder()).append("\311 esperado o fechamento do elemento ").append(localName).append(" nessa posi\347\343o, mas foi encontrado um evento ").append(ParserHelper.getEventTypeString(xml)).toString(), xml);
        if(nameSpace != null || localName != null)
            requireNamespaceAndLocalName(xml, nameSpace, localName);
    }

    private void requireNamespaceAndLocalName(XMLStreamReader xml, String nameSpace, String localName)
        throws XMLStreamException
    {
        if(nameSpace != null)
        {
            String uri = xml.getNamespaceURI();
            if(nameSpace.length() == 0)
            {
                if(uri != null && uri.length() > 0)
                    throwException((new StringBuilder()).append("\311 esperado um namespace vazio, mas o namespace \351 ").append(uri).toString(), xml);
            } else
            if(!nameSpace.equals(uri))
                throwException((new StringBuilder()).append("\311 esperado o namespace ").append(nameSpace).append(", no entanto foi encontrado ").append(uri).toString(), xml);
        }
        if(localName != null)
        {
            String name = xml.getLocalName();
            if(!localName.equals(name))
                throwException((new StringBuilder()).append("Esperado nome ").append(localName).append(", mas foi encontrado ").append(name).toString(), xml);
        }
    }

    public void writeStartElement(XMLStreamWriter xml, String localName, boolean elementoRaiz, boolean insereNameSpaceLocal)
        throws XMLStreamException
    {
        if(!insereNameSpaceLocal)
        {
            xml.writeStartElement(nameSpaceURI, localName);
        } else
        {
            xml.writeStartElement(null, localName);
            xml.writeAttribute("xmlns", nameSpaceURI);
        }
        if(elementoRaiz)
            writeNamespaces(xml, nameSpaceURI, insereNameSpaceLocal);
    }

    public void writeEndElement(XMLStreamWriter xml)
        throws XMLStreamException
    {
        xml.writeEndElement();
    }

    public void writeDirectStrings(XMLStreamWriter xml, Collection listaXML)
        throws XMLStreamException
    {
        if(!ParserHelper.isEmpty(listaXML))
        {
            xml.writeCharacters("");
            xml.flush();
            Writer w = (Writer)xml.getProperty("com.ctc.wstx.outputUnderlyingWriter");
            if(w == null)
                throw new XMLStreamException((new StringBuilder()).append("N\343o conseguiu obter o Writer a partir do XMLStreamWriter do tipo ").append(xml.getClass()).append(". Deveria ser filho do tipo com.ctc.wstx.sw.BaseStreamWriter").toString());
            try
            {
                String objeto;
                for(Iterator i$ = listaXML.iterator(); i$.hasNext(); w.write(objeto))
                    objeto = (String)i$.next();

            }
            catch(IOException e)
            {
                throw new XMLStreamException("N\343o conseguiu escrever diretamente para o Writer.", e);
            }
            try
            {
                w.flush();
            }
            catch(IOException e)
            {
                throw new XMLStreamException("Erro inexperado ao dar \"flush\" no writer.", e);
            }
        }
    }

    protected void consomeSignature(XMLStreamReader xml)
        throws XMLStreamException
    {
        if(consomeStart(xml, "Signature", true, "http://www.w3.org/2000/09/xmldsig#"))
        {
            for(; xml.hasNext() && (xml.getEventType() != 2 || !"Signature".equals(xml.getLocalName())); ParserHelper.posicionaProximoElemento(xml));
            consomeEnd(xml, "Signature", "http://www.w3.org/2000/09/xmldsig#");
        }
    }

    private void writeNamespaces(XMLStreamWriter xml, String defaultNamespaceURI, boolean usaNamespaceLocal)
        throws XMLStreamException
    {
        if(usaNamespaceLocal)
        {
            BaseParser arr$[] = parsersUtilizados;
            int len$ = arr$.length;
            int i$ = 0;
            do
            {
                if(i$ >= len$)
                    break;
                BaseParser parser = arr$[i$];
                if("xsi".equals(parser.getNameSpacePrefix()))
                    break;
                i$++;
            } while(true);
        } else
        {
            xml.writeDefaultNamespace(defaultNamespaceURI);
            BaseParser arr$[] = parsersUtilizados;
            int len$ = arr$.length;
            for(int i$ = 0; i$ < len$; i$++)
            {
                BaseParser parser = arr$[i$];
                if(!ParserHelper.isEmpty(parser.getNameSpacePrefix()))
                    xml.writeNamespace(parser.getNameSpacePrefix(), parser.getNameSpaceURI());
            }

            xml.writeNamespace("ds", "http://www.w3.org/2000/09/xmldsig#");
        }
    }

    private String getSchemaLocation(String defaultNamespaceURI)
    {
        StringBuilder sb = (new StringBuilder(defaultNamespaceURI)).append(" schemas/").append(getFileName(defaultNamespaceURI)).append(' ');
        BaseParser arr$[] = parsersUtilizados;
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            BaseParser parser = arr$[i$];
            if(!ParserHelper.isEmpty(parser.getNameSpacePrefix()) && !"xsi".equals(parser.getNameSpacePrefix()))
                sb.append(parser.getNameSpaceURI()).append(' ').append(getFileName(parser.getNameSpaceURI())).append(' ');
        }

        sb.append("http://www.w3.org/2000/09/xmldsig# xmldsig-core-schema20020212.xsd");
        return sb.toString();
    }

    private String getFileName(String namespaceURI)
    {
        return namespaceURI.substring(namespaceURI.lastIndexOf('/') + 1);
    }
}
