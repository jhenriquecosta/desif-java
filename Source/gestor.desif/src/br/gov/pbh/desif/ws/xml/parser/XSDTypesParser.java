
package br.gov.pbh.desif.ws.xml.parser;

import br.gov.pbh.desif.ws.xml.WSContantes;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

public class XSDTypesParser
extends BaseParser {
    private static final XSDTypesParser instance = new XSDTypesParser();

    public static XSDTypesParser getInstance() {
        return instance;
    }

    protected XSDTypesParser() {
        super("xsi", "http://www.w3.org/2001/XMLSchema-instance", new BaseParser[0]);
    }

    public Date readDateTime(XMLStreamReader xml) throws XMLStreamException {
        xml.require(4, null, null);
        String text = xml.getText();
        this.checkNotEmpty("xsd:dateTime", text, xml);
        SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        DATETIME_FORMAT.setLenient(Boolean.FALSE);
        try {
            return DATETIME_FORMAT.parse(text.trim());
        }
        catch (ParseException e) {
            throw new XMLStreamException("Data no formato inv\u00e1lido: " + text, xml.getLocation(), e);
        }
    }

    public void writeDateTime(XMLStreamWriter xml, Date value) throws XMLStreamException {
        this.checkNotEmpty("xsd:dateTime", value, null);
        xml.writeCharacters(WSContantes.Formatters.DATETIME_FORMAT.format(value));
    }

    public Date readDate(XMLStreamReader xml) throws XMLStreamException {
        xml.require(4, null, null);
        String text = xml.getText();
        this.checkNotEmpty("xsd:date", text, xml);
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
        DATE_FORMAT.setLenient(Boolean.FALSE);
        try {
            return DATE_FORMAT.parse(text.trim());
        }
        catch (ParseException e) {
            throw new XMLStreamException("Data no formato inv\u00e1lido: " + text, xml.getLocation(), e);
        }
    }

    public void writeDate(XMLStreamWriter xml, Date value) throws XMLStreamException {
        this.checkNotEmpty("xsd:date", value, null);
        xml.writeCharacters(WSContantes.Formatters.DATE_FORMAT.format(value));
    }

    public Byte readByte(XMLStreamReader xml) throws XMLStreamException {
        xml.require(4, null, null);
        return ParserHelper.getByte(xml.getText());
    }

    public void writeByte(XMLStreamWriter xml, Byte value) throws XMLStreamException {
        xml.writeCharacters(value.toString());
    }

    public BigInteger readInt(XMLStreamReader xml) throws XMLStreamException {
        xml.require(4, null, null);
        return ParserHelper.getBigInteger(xml.getText().trim());
    }

    public void writeInt(XMLStreamWriter xml, BigInteger value) throws XMLStreamException {
        xml.writeCharacters(value.toString());
    }

    public void writeIntAsLong(XMLStreamWriter xml, Long value) throws XMLStreamException {
        xml.writeCharacters(value.toString());
    }

    public BigInteger readNonNegativeInteger(XMLStreamReader xml) throws XMLStreamException {
        BigInteger value = this.readInt(xml);
        if (value != null && value.signum() < 0) {
            throw new XMLStreamException("Valor n\u00e3o pode ser negativo: " + value, xml.getLocation());
        }
        return value;
    }

    public void writeNonNegativeInteger(XMLStreamWriter xml, BigInteger value) throws XMLStreamException {
        if (value.signum() < 0) {
            throw new XMLStreamException("Valor n\u00e3o pode ser negativo: " + value);
        }
        this.writeInt(xml, value);
    }

    public Double readDecimal(XMLStreamReader xml, DecimalFormat format) throws XMLStreamException {
        xml.require(4, null, null);
        String text = xml.getText().trim();
        try 
        {
            return format == null ? ParserHelper.getDouble(text) : format.parse(text).doubleValue();
        }
        catch (ParseException e)
        {
            this.throwException("Valor n\u00e3o v\u00e1lido: " + text, xml, e);
            return null;
        }
    }

    public void writeDecimal(XMLStreamWriter xml, Double value, DecimalFormat format) throws XMLStreamException {
        xml.writeCharacters(format == null ? value.toString() : format.format(value));
    }

    public String readString(XMLStreamReader xml) throws XMLStreamException {
        xml.require(4, null, null);
        return xml.getText().trim();
    }

    public void writeString(XMLStreamWriter xml, String value) throws XMLStreamException {
        xml.writeCharacters(value);
    }

    public Boolean readBoolean(XMLStreamReader xml) throws XMLStreamException {
        xml.require(4, null, null);
        String value = xml.getText().trim();
        if ("true".equals(value) || "1".equals(value)) {
            return Boolean.TRUE;
        }
        if ("false".equals(value) || "0".equals(value)) {
            return Boolean.FALSE;
        }
        this.throwException("xsd:boolean aceita apenas os literais {true, false, 1, 0}.", xml);
        return null;
    }

    public void writeBoolean(XMLStreamWriter xml, Boolean value) throws XMLStreamException {
        xml.writeCharacters(value.toString());
    }

    public static void main(String[] args) {
        Double ax = Double.valueOf("E0");
    }
}

