
package br.gov.pbh.desif.ws.xml.parser;

import java.io.Reader;
import java.io.Writer;
import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

public class ParserHelper {
    private static XMLInputFactory inputFactory;
    private static XMLOutputFactory outputFactory;
    private static XMLOutputFactory outputFactoryParaXMLEvent;
    private static final Map<Integer, BigInteger> maxBigIntegerMap;
    private static final BigInteger BIG_ONE_NEG;
    private static long[] maxLongArray;
    private static int[] maxIntArray;

    private static XMLInputFactory getInputFactory() {
        if (inputFactory == null) {
            inputFactory = XMLInputFactory.newInstance();
            inputFactory.setProperty("javax.xml.stream.isCoalescing", false);
            inputFactory.setProperty("javax.xml.stream.supportDTD", false);
            inputFactory.setProperty("javax.xml.stream.isValidating", false);
        }
        return inputFactory;
    }

    private static XMLOutputFactory getOutputFactory() {
        if (outputFactory == null) {
            outputFactory = XMLOutputFactory.newInstance();
            outputFactory.setProperty("javax.xml.stream.isNamespaceAware", false);
        }
        return outputFactory;
    }

    private static XMLOutputFactory getOutputFactoryParaXMLEvent() {
        if (outputFactoryParaXMLEvent == null) {
            outputFactoryParaXMLEvent = XMLOutputFactory.newInstance();
            outputFactoryParaXMLEvent.setProperty("javax.xml.stream.isNamespaceAware", true);
        }
        return outputFactoryParaXMLEvent;
    }

    public static XMLStreamReader getXMLStreamReader(Reader reader) throws XMLStreamException {
        return ParserHelper.getInputFactory().createXMLStreamReader(reader);
    }

    public static XMLEventReader getXMLEventReader(Reader reader) throws XMLStreamException {
        return ParserHelper.getInputFactory().createXMLEventReader(reader);
    }

    public static XMLStreamWriter getXMLStreamWriter(Writer swriter) throws XMLStreamException {
        return ParserHelper.getOutputFactory().createXMLStreamWriter(swriter);
    }

    public static XMLEventWriter getXMLEventWriter(Writer swriter) throws XMLStreamException {
        return ParserHelper.getOutputFactoryParaXMLEvent().createXMLEventWriter(swriter);
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

    public static <T> T getFirst(Collection<T> collection) {
        return collection == null || collection.isEmpty() ? null : (T)collection.iterator().next();
    }

    public static BigInteger getMaxBigInteger(int digits) {
        if (digits < 0) {
            throw new IllegalArgumentException("N\u00famero de digitos n\u00e3o pode ser negativo: " + digits);
        }
        if (digits == 0) {
            return BigInteger.ZERO;
        }
        BigInteger value = maxBigIntegerMap.get(digits);
        if (value == null) {
            value = BigInteger.TEN.pow(digits).subtract(BigInteger.ONE);
            maxBigIntegerMap.put(digits, value);
        }
        return value;
    }

    public static BigInteger getMinBigInteger(int digits) {
        return digits == 0 ? BIG_ONE_NEG : ParserHelper.getMaxBigInteger(digits - 1).add(BigInteger.ONE);
    }

    public static double getMaxDouble(int digits, int fractionDigits) {
        if (digits < 0) {
            throw new IllegalArgumentException("N\u00famero de digitos n\u00e3o pode ser negativo: " + digits);
        }
        double maxDouble = 0.0;
        for (int i = 1; i <= digits; ++i) {
            maxDouble = maxDouble * 10.0 + 9.0;
        }
        double fraction = 0.0;
        for (int i = 0; i < fractionDigits; ++i) {
            fraction = fraction / 10.0 + 0.9;
        }
        return maxDouble + fraction;
    }

    public static double getMaxDouble2(int digits, int fractionDigits) {
        if (digits < 0) {
            throw new IllegalArgumentException("N\u00famero de digitos n\u00e3o pode ser negativo: " + digits);
        }
        return Math.pow(10.0, digits) - 1.0 + (1.0 - Math.pow(10.0, - fractionDigits));
    }

    public static double getMinDouble(int digits) {
        if (digits < 0) {
            throw new IllegalArgumentException("N\u00famero de digitos n\u00e3o pode ser negativo: " + digits);
        }
        return digits == 0 ? -1.0 : Math.pow(10.0, digits - 1);
    }

    public static void main(String[] args)
    {
        System.out.println(Double.MAX_VALUE);
        for (int f = 0; f < 4; ++f) {
            for (int i = 0; i < 20; ++i) 
            {
                double maxDouble2 = getMaxDouble2(i, f);
                double maxDouble = getMaxDouble(i, f);
                double minDouble = ParserHelper.getMinDouble(i);
                System.out.println("" + i + "," + f + ": " + minDouble + " " + maxDouble + " " + maxDouble2 + " " + ((maxDouble = ParserHelper.getMaxDouble(i, f)) == (maxDouble2 = ParserHelper.getMaxDouble2(i, f))));
            }
        }
        for (int i = 0; i < 100; ++i) {
            System.out.println("" + i + ": " + ParserHelper.getMaxBigInteger(i));
        }
    }

    public static long getMaxLong(int digits) {
        if (digits < 0) {
            throw new IllegalArgumentException("N\u00famero de digitos n\u00e3o pode ser negativo: " + digits);
        }
        if (digits >= 19) {
            return Long.MAX_VALUE;
        }
        if (maxLongArray == null) {
            maxLongArray = new long[19];
            for (int i = 1; i <= 18; ++i) {
                ParserHelper.maxLongArray[i] = maxLongArray[i - 1] * 10L + 9L;
            }
        }
        return maxLongArray[digits];
    }

    public static long getMinLong(int digits) {
        if (digits < 0) {
            throw new IllegalArgumentException("N\u00famero de digitos n\u00e3o pode ser negativo: " + digits);
        }
        return digits == 0 ? -1L : (digits >= 19 ? Long.MAX_VALUE : ParserHelper.getMaxLong(digits - 1) + 1L);
    }

    public static int getMaxInt(int digits) {
        if (digits < 0) {
            throw new IllegalArgumentException("N\u00famero de digitos n\u00e3o pode ser negativo: " + digits);
        }
        if (digits >= 10) {
            return Integer.MAX_VALUE;
        }
        if (maxIntArray == null) {
            maxIntArray = new int[10];
            for (int i = 1; i <= 9; ++i) {
                ParserHelper.maxIntArray[i] = maxIntArray[i - 1] * 10 + 9;
            }
        }
        return maxIntArray[digits];
    }

    public static int getMinInt(int digits) {
        if (digits < 0) {
            throw new IllegalArgumentException("N\u00famero de digitos n\u00e3o pode ser negativo: " + digits);
        }
        return digits == 0 ? -1 : (digits >= 10 ? Integer.MAX_VALUE : ParserHelper.getMaxInt(digits - 1) + 1);
    }

    public static boolean isNil(XMLStreamReader xml) throws XMLStreamException {
        xml.require(1, null, null);
        return Boolean.parseBoolean(xml.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil"));
    }

    public static Byte getByte(String text) throws NumberFormatException {
        if (ParserHelper.isEmpty(text)) {
            return null;
        }
        text = text.trim();
        return Byte.valueOf(text);
    }

    public static Integer getInteger(String text) throws NumberFormatException {
        if (ParserHelper.isEmpty(text)) {
            return null;
        }
        text = text.trim();
        return Integer.valueOf(text);
    }

    public static Long getLong(String text) throws NumberFormatException {
        if (ParserHelper.isEmpty(text)) {
            return null;
        }
        text = text.trim();
        return Long.valueOf(text);
    }

    public static BigInteger getBigInteger(String text) throws NumberFormatException {
        if (ParserHelper.isEmpty(text)) {
            return null;
        }
        text = text.trim();
        return new BigInteger(text);
    }

    public static Double getDouble(String text) {
        if (ParserHelper.isEmpty(text)) {
            return null;
        }
        text = text.trim();
        return Double.valueOf(text);
    }

    public static void posicionaProximoElemento(XMLStreamReader xml) throws XMLStreamException {
        if (xml.hasNext()) {
            do {
                xml.next();
            } while (xml.hasNext() && (xml.getEventType() == 5 || xml.getEventType() == 6 || xml.isWhiteSpace()));
        }
    }

    public static String getEventTypeString(XMLStreamReader xml) {
        int type = xml.getEventType();
        switch (type) {
            case 1: {
                return "START_ELEMENT";
            }
            case 2: {
                return "END_ELEMENT";
            }
            case 7: {
                return "START_DOCUMENT";
            }
            case 8: {
                return "END_DOCUMENT";
            }
            case 4: {
                return "CHARACTERS";
            }
            case 12: {
                return "CDATA";
            }
            case 6: {
                return "SPACE";
            }
            case 5: {
                return "COMMENT";
            }
            case 3: {
                return "PROCESSING_INSTRUCTION";
            }
            case 11: {
                return "DTD";
            }
            case 9: {
                return "ENTITY_REFERENCE";
            }
        }
        return "[" + type + "]";
    }

    static {
        maxBigIntegerMap = new HashMap<Integer, BigInteger>(3);
        BIG_ONE_NEG = BigInteger.ONE.negate();
        maxLongArray = null;
        maxIntArray = null;
    }
}

