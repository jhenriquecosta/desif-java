
package br.gov.pbh.desif.ws.xml.parser;

import java.text.DecimalFormatSymbols;
import java.util.Locale;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.text.DecimalFormat;

public class TiposSimplesParser extends BaseParser
{
    private static final DecimalFormat TS_VALOR_FORMAT;
    private static final Double TS_VALOR_MAX;
    private static final TiposSimplesParser instance;
    private static final DecimalFormat TS_ALIQUOTA_FORMAT;
    private static final Double TS_ALIQUOTA_MAX;
    private final XSDTypesParser xsdTypesParser;
    
    public static TiposSimplesParser getInstance()
    {
        return TiposSimplesParser.instance;
    }
    
    protected TiposSimplesParser(final String nameSpaceURI, final XSDTypesParser xsdTypesParser) {
        super("ts", nameSpaceURI, new BaseParser[] { xsdTypesParser });
        this.xsdTypesParser = xsdTypesParser;
    }
    
    public String readTsRaizCnpj(final XMLStreamReader xml) throws XMLStreamException {
        return this.readString(xml, "tsRaizCnpj", 8, 8);
    }
    
    public void writeTsRaizCnpj(final XMLStreamWriter xml, final String value) throws XMLStreamException {
        this.writeString(xml, "tsRaizCnpj", 8, 8, value);
    }
    
    public String readTsRazaoSocial(final XMLStreamReader xml) throws XMLStreamException {
        return this.readString(xml, "tsRazaoSocial", 100, 1);
    }
    
    public void writeTsRazaoSocial(final XMLStreamWriter xml, final String value) throws XMLStreamException {
        this.writeString(xml, "tsRazaoSocial", 100, 1, value);
    }
    
    public String readTsAnoMesInicCmpe(final XMLStreamReader xml) throws XMLStreamException {
        return this.readString(xml, "tsAnoMesInicCmpe", 6, 6);
    }
    
    public void writeTsAnoMesInicCmpe(final XMLStreamWriter xml, final String value) throws XMLStreamException {
        this.writeString(xml, "tsAnoMesInicCmpe", 6, 6, value);
    }
    
    public Integer readTsTipoDeclaracao(final XMLStreamReader xml) throws XMLStreamException {
        final Byte value = this.xsdTypesParser.readByte(xml);
        this.checkNumberOptions("tsTipoDeclaracao", (int)value, xml, 1, 2, 3);
        ParserHelper.posicionaProximoElemento(xml);
        return (int)value;
    }
    
    public void writeTsTipoDeclaracao(final XMLStreamWriter xml, final int value) throws XMLStreamException
    {
            this.checkNumberOptions("tsTipoDeclaracao", value, null, 1, 2, 3);
            this.xsdTypesParser.writeByte(xml, (byte)value);
     
    }
    
    public Integer readTsTipoConsolidacao(final XMLStreamReader xml) throws XMLStreamException {
        final Byte value = this.xsdTypesParser.readByte(xml);
        this.checkNumberOptions("tsTipoConsolidacao", (int)value, xml, 1, 2, 3, 4);
        ParserHelper.posicionaProximoElemento(xml);
        return (int)value;
    }
    
    public void writeTsTipoConsolidacao(final XMLStreamWriter xml, final Short value) throws XMLStreamException {
        if (value != null) {
            this.checkNumberOptions("tsTipoConsolidacao", (int)value, null, 1, 2, 3, 4);
            this.xsdTypesParser.writeByte(xml, (byte)(Object)value);
        }
    }
    
    public String readTsCnpjProprio(final XMLStreamReader xml) throws XMLStreamException {
        return this.readString(xml, "tsCnpjProprio", 6, 6);
    }
    
    public void writeTsCnpjProprio(final XMLStreamWriter xml, final String value) throws XMLStreamException {
        this.writeString(xml, "tsCnpjProprio", 6, 6, value);
    }
    
    public Integer readTsIndrInscMunl(final XMLStreamReader xml) throws XMLStreamException {
        final Byte value = this.xsdTypesParser.readByte(xml);
        this.checkNumberOptions("tsIndrInscMunl", (int)value, xml, 1, 2);
        ParserHelper.posicionaProximoElemento(xml);
        return (int)value;
    }
    
    public void writeTsIndrInscMunl(final XMLStreamWriter xml, final int value) throws XMLStreamException {
            this.checkNumberOptions("tsIndrInscMunl",value, null, 1, 2);
            this.xsdTypesParser.writeByte(xml, (byte)value);
       
    }
    
    public String readTsCodDependencia(final XMLStreamReader xml) throws XMLStreamException {
        return this.readString(xml, "tsCodDependencia", 15, 1);
    }
    
    public void writeTsCodDependencia(final XMLStreamWriter xml, final String value) throws XMLStreamException {
        this.writeString(xml, "tsCodDependencia", 15, 1, value);
    }
    
    public Integer readTsCtblPropria(final XMLStreamReader xml) throws XMLStreamException {
        final Byte value = this.xsdTypesParser.readByte(xml);
        this.checkNumberOptions("tsCtblPropria", (int)value, xml, 1, 2);
        ParserHelper.posicionaProximoElemento(xml);
        return (int)value;
    }
    
    public void writeTsCtblPropria(final XMLStreamWriter xml, final int value) throws XMLStreamException {
    
            this.checkNumberOptions("tsCtblPropria", (int)value, null, 1, 2);
            this.xsdTypesParser.writeByte(xml, (byte)value);
    
    }
    
    public Double readTsValor(final XMLStreamReader xml) throws XMLStreamException {
        final Double value = this.xsdTypesParser.readDecimal(xml, TiposSimplesParser.TS_VALOR_FORMAT);
        if (value < 0.0) {
            this.throwException("tsValor n\u00e3o pode ser negativo.", xml);
        }
        if (value > TiposSimplesParser.TS_VALOR_MAX) {
            this.throwException("tsValor n\u00e3o pode ser maior que " + TiposSimplesParser.TS_VALOR_MAX + " (18 digitos no total, sendo 2 de casas decimais)", xml);
        }
        ParserHelper.posicionaProximoElemento(xml);
        return value;
    }
    
    public void writeTsValor(final XMLStreamWriter xml, final Double value) throws XMLStreamException {
        if (value < 0.0) {
            this.throwException("tsValor n\u00e3o pode ser negativo.", null);
        }
        if (value > TiposSimplesParser.TS_VALOR_MAX) {
            this.throwException("tsValor n\u00e3o pode ser maior que " + TiposSimplesParser.TS_VALOR_MAX + " (18 digitos no total, sendo 2 de casas decimais)", null);
        }
        this.xsdTypesParser.writeDecimal(xml, value, TiposSimplesParser.TS_VALOR_FORMAT);
    }
    
    public Double readTsAliquota(final XMLStreamReader xml) throws XMLStreamException {
        final Double value = this.xsdTypesParser.readDecimal(xml, TiposSimplesParser.TS_ALIQUOTA_FORMAT);
        if (value < 0.0) {
            this.throwException("tsAliquota n\u00e3o pode ser negativo.", xml);
        }
        if (value > TiposSimplesParser.TS_ALIQUOTA_MAX) {
            this.throwException("tsAliquota n\u00e3o pode ser maior que " + TiposSimplesParser.TS_ALIQUOTA_MAX + " (7 digitos no total, sendo 2 de casas decimais).", xml);
        }
        ParserHelper.posicionaProximoElemento(xml);
        return value;
    }
    
    public void writeTsAliquota(final XMLStreamWriter xml, final Double value) throws XMLStreamException {
        if (value < 0.0) {
            this.throwException("tsAliquota n\u00e3o pode ser negativo.", null);
        }
        if (value > TiposSimplesParser.TS_ALIQUOTA_MAX) {
            this.throwException("tsAliquota n\u00e3o pode ser maior que " + TiposSimplesParser.TS_ALIQUOTA_MAX + " (7 digitos no total, sendo 2 de casas decimais).", null);
        }
        this.xsdTypesParser.writeDecimal(xml, value, TiposSimplesParser.TS_ALIQUOTA_FORMAT);
    }
    
    public String readTsIdTag(final XMLStreamReader xml, final String attributeName) throws XMLStreamException {
        final String value = xml.getAttributeValue(this.nameSpaceURI, attributeName);
        this.checkMaxLength("tsIdTag", value, 255, xml);
        return value;
    }
    
    public void writeTsIdTag(final XMLStreamWriter xml, final String attributeName, final String value) throws XMLStreamException {
        this.checkMaxLength("tsIdTag", value, 255, null);
        xml.writeAttribute(this.nameSpaceURI, attributeName, value);
    }
    
    public String readTsCodigoMensagem(final XMLStreamReader xml) throws XMLStreamException {
        return this.readString(xml, "tsCodigoMensagem", 5, 1);
    }
    
    public void writeTsCodigoMensagem(final XMLStreamWriter xml, final String value) throws XMLStreamException {
        this.writeString(xml, "tsCodigoMensagem", 5, 1, value);
    }
    
    public String readTsDetalheMensagem(final XMLStreamReader xml) throws XMLStreamException {
        return this.readString(xml, "tsDetalheMensagem", 300, 1);
    }
    
    public void writeTsDetalheMensagem(final XMLStreamWriter xml, final String value) throws XMLStreamException {
        this.writeString(xml, "tsDetalheMensagem", 300, 1, value);
    }
    
    public String readTsDescricaoMensagem(final XMLStreamReader xml) throws XMLStreamException {
        return this.readString(xml, "tsDescricaoMensagem", 300, 1);
    }
    
    public void writeTsDescricaoMensagem(final XMLStreamWriter xml, final String value) throws XMLStreamException {
        this.writeString(xml, "tsDescricaoMensagem", 300, 1, value);
    }
    
    protected String readString(final XMLStreamReader xml, final String typeName, final Integer maxLength, final Integer minLength) throws XMLStreamException {
        final String value = this.xsdTypesParser.readString(xml);
        if (null != maxLength && null != minLength && maxLength.equals(minLength)) {
            this.checkLength(typeName, value, maxLength, xml);
        }
        else {
            if (null != maxLength) {
                this.checkMaxLength(typeName, value, maxLength, xml);
            }
            if (null != minLength) {
                this.checkMinLength(typeName, value, minLength, xml);
            }
        }
        ParserHelper.posicionaProximoElemento(xml);
        return value;
    }
    
    protected void writeString(final XMLStreamWriter xml, final String typeName, final Integer maxLength, final Integer minLength, String value) throws XMLStreamException {
        value = value.trim();
        if (null != maxLength && null != minLength && maxLength.equals(minLength)) {
            this.checkLength(typeName, value, maxLength, null);
        }
        else {
            if (null != maxLength) {
                this.checkMaxLength(typeName, value, maxLength, null);
            }
            if (null != minLength) {
                this.checkMinLength(typeName, value, minLength, null);
            }
        }
        this.xsdTypesParser.writeString(xml, value);
    }
    
    static
    {
        instance = new TiposSimplesParser("http://es605-304:8080/schemas2/desif.xsd", XSDTypesParser.getInstance());
        TS_VALOR_FORMAT = new DecimalFormat("0.00", new DecimalFormatSymbols(Locale.US));
        TS_VALOR_MAX = 1.0E13;
        TS_ALIQUOTA_FORMAT = new DecimalFormat("00000.##", new DecimalFormatSymbols(Locale.US));
        TS_ALIQUOTA_MAX = 99999.99;
    }
}
