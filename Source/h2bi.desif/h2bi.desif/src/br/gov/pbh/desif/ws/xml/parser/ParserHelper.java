
package br.gov.pbh.desif.ws.xml.parser;

import java.io.*;
import java.math.BigInteger;
import java.util.*;
import javax.xml.stream.*;

public class ParserHelper
{

    private static XMLInputFactory inputFactory;
    private static XMLOutputFactory outputFactory;
    private static XMLOutputFactory outputFactoryParaXMLEvent;
    private static final Map maxBigIntegerMap = new HashMap(3);
    private static final BigInteger BIG_ONE_NEG;
    private static long maxLongArray[] = null;
    private static int maxIntArray[] = null;

    public ParserHelper()
    {
    }

    private static XMLInputFactory getInputFactory()
    {
        if(inputFactory == null)
        {
            inputFactory = XMLInputFactory.newInstance();
            inputFactory.setProperty("javax.xml.stream.isCoalescing", Boolean.valueOf(false));
            inputFactory.setProperty("javax.xml.stream.supportDTD", Boolean.valueOf(false));
            inputFactory.setProperty("javax.xml.stream.isValidating", Boolean.valueOf(false));
        }
        return inputFactory;
    }

    private static XMLOutputFactory getOutputFactory()
    {
        if(outputFactory == null)
        {
            outputFactory = XMLOutputFactory.newInstance();
            outputFactory.setProperty("javax.xml.stream.isNamespaceAware", Boolean.valueOf(false));
        }
        return outputFactory;
    }

    private static XMLOutputFactory getOutputFactoryParaXMLEvent()
    {
        if(outputFactoryParaXMLEvent == null)
        {
            outputFactoryParaXMLEvent = XMLOutputFactory.newInstance();
            outputFactoryParaXMLEvent.setProperty("javax.xml.stream.isNamespaceAware", Boolean.valueOf(true));
        }
        return outputFactoryParaXMLEvent;
    }

    public static XMLStreamReader getXMLStreamReader(Reader reader)
        throws XMLStreamException
    {
        return getInputFactory().createXMLStreamReader(reader);
    }

    public static XMLEventReader getXMLEventReader(Reader reader)
        throws XMLStreamException
    {
        return getInputFactory().createXMLEventReader(reader);
    }

    public static XMLStreamWriter getXMLStreamWriter(Writer swriter)
        throws XMLStreamException
    {
        return getOutputFactory().createXMLStreamWriter(swriter);
    }

    public static XMLEventWriter getXMLEventWriter(Writer swriter)
        throws XMLStreamException
    {
        return getOutputFactoryParaXMLEvent().createXMLEventWriter(swriter);
    }

    public static boolean isEmpty(CharSequence text)
    {
        if(text == null || text.length() == 0)
            return true;
        for(int i = 0; i < text.length(); i++)
            if(text.charAt(i) > ' ')
                return false;

        return true;
    }

    public static boolean isEmpty(Object value)
    {
        return value == null || (value instanceof CharSequence) && isEmpty((CharSequence)value) || (value instanceof Collection) && ((Collection)value).isEmpty();
    }

    public static Object getFirst(Collection collection)
    {
        return collection != null && !collection.isEmpty() ? collection.iterator().next() : null;
    }

    public static BigInteger getMaxBigInteger(int digits)
    {
        if(digits < 0)
            throw new IllegalArgumentException((new StringBuilder()).append("N\372mero de digitos n\343o pode ser negativo: ").append(digits).toString());
        if(digits == 0)
            return BigInteger.ZERO;
        BigInteger value = (BigInteger)maxBigIntegerMap.get(Integer.valueOf(digits));
        if(value == null)
        {
            value = BigInteger.TEN.pow(digits).subtract(BigInteger.ONE);
            maxBigIntegerMap.put(Integer.valueOf(digits), value);
        }
        return value;
    }

    public static BigInteger getMinBigInteger(int digits)
    {
        return digits != 0 ? getMaxBigInteger(digits - 1).add(BigInteger.ONE) : BIG_ONE_NEG;
    }

    public static double getMaxDouble(int digits, int fractionDigits)
    {
        if(digits < 0)
            throw new IllegalArgumentException((new StringBuilder()).append("N\372mero de digitos n\343o pode ser negativo: ").append(digits).toString());
        double maxDouble = 0.0D;
        for(int i = 1; i <= digits; i++)
            maxDouble = maxDouble * 10D + 9D;

        double fraction = 0.0D;
        for(int i = 0; i < fractionDigits; i++)
            fraction = fraction / 10D + 0.90000000000000002D;

        return maxDouble + fraction;
    }

    public static double getMaxDouble2(int digits, int fractionDigits)
    {
        if(digits < 0)
            throw new IllegalArgumentException((new StringBuilder()).append("N\372mero de digitos n\343o pode ser negativo: ").append(digits).toString());
        else
            return (Math.pow(10D, digits) - 1.0D) + (1.0D - Math.pow(10D, -fractionDigits));
    }

    public static double getMinDouble(int digits)
    {
        if(digits < 0)
            throw new IllegalArgumentException((new StringBuilder()).append("N\372mero de digitos n\343o pode ser negativo: ").append(digits).toString());
        else
            return digits != 0 ? Math.pow(10D, digits - 1) : -1D;
    }

    public static void main(String args[])
    {
        System.out.println(1.7976931348623157E+308D);
        for(int f = 0; f < 4; f++)
        {
            for(int i = 0; i < 20; i++)
            {
                double minDouble = getMinDouble(i);
                double maxDouble = getMaxDouble(i, f);
                double maxDouble2 = getMaxDouble2(i, f);
                System.out.println((new StringBuilder()).append("").append(i).append(",").append(f).append(": ").append(minDouble).append(" ").append(maxDouble).append(" ").append(maxDouble2).append(" ").append(maxDouble == maxDouble2).toString());
            }

        }

        for(int i = 0; i < 100; i++)
            System.out.println((new StringBuilder()).append("").append(i).append(": ").append(getMaxBigInteger(i)).toString());

    }

    public static long getMaxLong(int digits)
    {
        if(digits < 0)
            throw new IllegalArgumentException((new StringBuilder()).append("N\372mero de digitos n\343o pode ser negativo: ").append(digits).toString());
        if(digits >= 19)
            return 0x7fffffffffffffffL;
        if(maxLongArray == null)
        {
            maxLongArray = new long[19];
            for(int i = 1; i <= 18; i++)
                maxLongArray[i] = maxLongArray[i - 1] * 10L + 9L;

        }
        return maxLongArray[digits];
    }

    public static long getMinLong(int digits)
    {
        if(digits < 0)
            throw new IllegalArgumentException((new StringBuilder()).append("N\372mero de digitos n\343o pode ser negativo: ").append(digits).toString());
        else
            return digits != 0 ? digits < 19 ? getMaxLong(digits - 1) + 1L : 0x7fffffffffffffffL : -1L;
    }

    public static int getMaxInt(int digits)
    {
        if(digits < 0)
            throw new IllegalArgumentException((new StringBuilder()).append("N\372mero de digitos n\343o pode ser negativo: ").append(digits).toString());
        if(digits >= 10)
            return 0x7fffffff;
        if(maxIntArray == null)
        {
            maxIntArray = new int[10];
            for(int i = 1; i <= 9; i++)
                maxIntArray[i] = maxIntArray[i - 1] * 10 + 9;

        }
        return maxIntArray[digits];
    }

    public static int getMinInt(int digits)
    {
        if(digits < 0)
            throw new IllegalArgumentException((new StringBuilder()).append("N\372mero de digitos n\343o pode ser negativo: ").append(digits).toString());
        else
            return digits != 0 ? digits < 10 ? getMaxInt(digits - 1) + 1 : 0x7fffffff : -1;
    }

    public static boolean isNil(XMLStreamReader xml)
        throws XMLStreamException
    {
        xml.require(1, null, null);
        return Boolean.parseBoolean(xml.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil"));
    }

    public static Byte getByte(String text)
        throws NumberFormatException
    {
        if(isEmpty(text))
        {
            return null;
        } else
        {
            text = text.trim();
            return Byte.valueOf(text);
        }
    }

    public static Integer getInteger(String text)
        throws NumberFormatException
    {
        if(isEmpty(text))
        {
            return null;
        } else
        {
            text = text.trim();
            return Integer.valueOf(text);
        }
    }

    public static Long getLong(String text)
        throws NumberFormatException
    {
        if(isEmpty(text))
        {
            return null;
        } else
        {
            text = text.trim();
            return Long.valueOf(text);
        }
    }

    public static BigInteger getBigInteger(String text)
        throws NumberFormatException
    {
        if(isEmpty(text))
        {
            return null;
        } else
        {
            text = text.trim();
            return new BigInteger(text);
        }
    }

    public static Double getDouble(String text)
    {
        if(isEmpty(text))
        {
            return null;
        } else
        {
            text = text.trim();
            return Double.valueOf(text);
        }
    }

    public static void posicionaProximoElemento(XMLStreamReader xml)
        throws XMLStreamException
    {
        if(xml.hasNext())
            do
                xml.next();
            while(xml.hasNext() && (xml.getEventType() == 5 || xml.getEventType() == 6 || xml.isWhiteSpace()));
    }

    public static String getEventTypeString(XMLStreamReader xml)
    {
        int type = xml.getEventType();
        switch(type)
        {
        case 1: // '\001'
            return "START_ELEMENT";

        case 2: // '\002'
            return "END_ELEMENT";

        case 7: // '\007'
            return "START_DOCUMENT";

        case 8: // '\b'
            return "END_DOCUMENT";

        case 4: // '\004'
            return "CHARACTERS";

        case 12: // '\f'
            return "CDATA";

        case 6: // '\006'
            return "SPACE";

        case 5: // '\005'
            return "COMMENT";

        case 3: // '\003'
            return "PROCESSING_INSTRUCTION";

        case 11: // '\013'
            return "DTD";

        case 9: // '\t'
            return "ENTITY_REFERENCE";

        case 10: // '\n'
        default:
            return (new StringBuilder()).append("[").append(type).append("]").toString();
        }
    }

    static 
    {
        BIG_ONE_NEG = BigInteger.ONE.negate();
    }
}
