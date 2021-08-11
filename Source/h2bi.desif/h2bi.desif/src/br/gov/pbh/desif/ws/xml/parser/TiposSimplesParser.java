
package br.gov.pbh.desif.ws.xml.parser;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import javax.xml.stream.*;

// Referenced classes of package br.gov.pbh.desif.ws.xml.parser:
//            BaseParser, XSDTypesParser, ParserHelper

public class TiposSimplesParser extends BaseParser
{

    private static final DecimalFormat TS_VALOR_FORMAT;
    private static final Double TS_VALOR_MAX = Double.valueOf(10000000000000D);
    private static final TiposSimplesParser instance = new TiposSimplesParser("http://es605-304:8080/schemas2/desif.xsd", XSDTypesParser.getInstance());
    private static final DecimalFormat TS_ALIQUOTA_FORMAT;
    private static final Double TS_ALIQUOTA_MAX = Double.valueOf(99999.990000000005D);
    private final XSDTypesParser xsdTypesParser;

    public static TiposSimplesParser getInstance()
    {
        return instance;
    }

    protected TiposSimplesParser(String nameSpaceURI, XSDTypesParser xsdTypesParser)
    {
        super("ts", nameSpaceURI, new BaseParser[] {
            xsdTypesParser
        });
        this.xsdTypesParser = xsdTypesParser;
    }

    public String readTsRaizCnpj(XMLStreamReader xml)
        throws XMLStreamException
    {
        return readString(xml, "tsRaizCnpj", Integer.valueOf(8), Integer.valueOf(8));
    }

    public void writeTsRaizCnpj(XMLStreamWriter xml, String value)
        throws XMLStreamException
    {
        writeString(xml, "tsRaizCnpj", Integer.valueOf(8), Integer.valueOf(8), value);
    }

    public String readTsRazaoSocial(XMLStreamReader xml)
        throws XMLStreamException
    {
        return readString(xml, "tsRazaoSocial", Integer.valueOf(100), Integer.valueOf(1));
    }

    public void writeTsRazaoSocial(XMLStreamWriter xml, String value)
        throws XMLStreamException
    {
        writeString(xml, "tsRazaoSocial", Integer.valueOf(100), Integer.valueOf(1), value);
    }

    public String readTsAnoMesInicCmpe(XMLStreamReader xml)
        throws XMLStreamException
    {
        return readString(xml, "tsAnoMesInicCmpe", Integer.valueOf(6), Integer.valueOf(6));
    }

    public void writeTsAnoMesInicCmpe(XMLStreamWriter xml, String value)
        throws XMLStreamException
    {
        writeString(xml, "tsAnoMesInicCmpe", Integer.valueOf(6), Integer.valueOf(6), value);
    }

    public Integer readTsTipoDeclaracao(XMLStreamReader xml)
        throws XMLStreamException
    {
        Byte value = xsdTypesParser.readByte(xml);
        checkNumberOptions("tsTipoDeclaracao", Integer.valueOf(value.intValue()), xml, new Integer[] {
            Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3)
        });
        ParserHelper.posicionaProximoElemento(xml);
        return Integer.valueOf(value.intValue());
    }

    public void writeTsTipoDeclaracao(XMLStreamWriter xml, Short value)
        throws XMLStreamException
    {
        if(value != null)
        {
            checkNumberOptions("tsTipoDeclaracao", Integer.valueOf(value.intValue()), null, new Integer[] {
                Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3)
            });
            xsdTypesParser.writeByte(xml, Byte.valueOf(value.byteValue()));
        }
    }

    public Integer readTsTipoConsolidacao(XMLStreamReader xml)
        throws XMLStreamException
    {
        Byte value = xsdTypesParser.readByte(xml);
        checkNumberOptions("tsTipoConsolidacao", Integer.valueOf(value.intValue()), xml, new Integer[] {
            Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(4)
        });
        ParserHelper.posicionaProximoElemento(xml);
        return Integer.valueOf(value.intValue());
    }

    public void writeTsTipoConsolidacao(XMLStreamWriter xml, Short value)
        throws XMLStreamException
    {
        if(value != null)
        {
            checkNumberOptions("tsTipoConsolidacao", Integer.valueOf(value.intValue()), null, new Integer[] {
                Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(4)
            });
            xsdTypesParser.writeByte(xml, Byte.valueOf(value.byteValue()));
        }
    }

    public String readTsCnpjProprio(XMLStreamReader xml)
        throws XMLStreamException
    {
        return readString(xml, "tsCnpjProprio", Integer.valueOf(6), Integer.valueOf(6));
    }

    public void writeTsCnpjProprio(XMLStreamWriter xml, String value)
        throws XMLStreamException
    {
        writeString(xml, "tsCnpjProprio", Integer.valueOf(6), Integer.valueOf(6), value);
    }

    public Integer readTsIndrInscMunl(XMLStreamReader xml)
        throws XMLStreamException
    {
        Byte value = xsdTypesParser.readByte(xml);
        checkNumberOptions("tsIndrInscMunl", Integer.valueOf(value.intValue()), xml, new Integer[] {
            Integer.valueOf(1), Integer.valueOf(2)
        });
        ParserHelper.posicionaProximoElemento(xml);
        return Integer.valueOf(value.intValue());
    }

    public void writeTsIndrInscMunl(XMLStreamWriter xml, Short value)
        throws XMLStreamException
    {
        if(value != null)
        {
            checkNumberOptions("tsIndrInscMunl", Integer.valueOf(value.intValue()), null, new Integer[] {
                Integer.valueOf(1), Integer.valueOf(2)
            });
            xsdTypesParser.writeByte(xml, Byte.valueOf(value.byteValue()));
        }
    }

    public String readTsCodDependencia(XMLStreamReader xml)
        throws XMLStreamException
    {
        return readString(xml, "tsCodDependencia", Integer.valueOf(15), Integer.valueOf(1));
    }

    public void writeTsCodDependencia(XMLStreamWriter xml, String value)
        throws XMLStreamException
    {
        writeString(xml, "tsCodDependencia", Integer.valueOf(15), Integer.valueOf(1), value);
    }

    public Integer readTsCtblPropria(XMLStreamReader xml)
        throws XMLStreamException
    {
        Byte value = xsdTypesParser.readByte(xml);
        checkNumberOptions("tsCtblPropria", Integer.valueOf(value.intValue()), xml, new Integer[] {
            Integer.valueOf(1), Integer.valueOf(2)
        });
        ParserHelper.posicionaProximoElemento(xml);
        return Integer.valueOf(value.intValue());
    }

    public void writeTsCtblPropria(XMLStreamWriter xml, Short value)
        throws XMLStreamException
    {
        if(value != null)
        {
            checkNumberOptions("tsCtblPropria", Integer.valueOf(value.intValue()), null, new Integer[] {
                Integer.valueOf(1), Integer.valueOf(2)
            });
            xsdTypesParser.writeByte(xml, Byte.valueOf(value.byteValue()));
        }
    }

    public Double readTsValor(XMLStreamReader xml)
        throws XMLStreamException
    {
        Double value = xsdTypesParser.readDecimal(xml, TS_VALOR_FORMAT);
        if(value.doubleValue() < 0.0D)
            throwException("tsValor n\343o pode ser negativo.", xml);
        if(value.doubleValue() > TS_VALOR_MAX.doubleValue())
            throwException((new StringBuilder()).append("tsValor n\343o pode ser maior que ").append(TS_VALOR_MAX).append(" (18 digitos no total, sendo 2 de casas decimais)").toString(), xml);
        ParserHelper.posicionaProximoElemento(xml);
        return value;
    }

    public void writeTsValor(XMLStreamWriter xml, Double value)
        throws XMLStreamException
    {
        if(value.doubleValue() < 0.0D)
            throwException("tsValor n\343o pode ser negativo.", null);
        if(value.doubleValue() > TS_VALOR_MAX.doubleValue())
            throwException((new StringBuilder()).append("tsValor n\343o pode ser maior que ").append(TS_VALOR_MAX).append(" (18 digitos no total, sendo 2 de casas decimais)").toString(), null);
        xsdTypesParser.writeDecimal(xml, value, TS_VALOR_FORMAT);
    }

    public Double readTsAliquota(XMLStreamReader xml)
        throws XMLStreamException
    {
        Double value = xsdTypesParser.readDecimal(xml, TS_ALIQUOTA_FORMAT);
        if(value.doubleValue() < 0.0D)
            throwException("tsAliquota n\343o pode ser negativo.", xml);
        if(value.doubleValue() > TS_ALIQUOTA_MAX.doubleValue())
            throwException((new StringBuilder()).append("tsAliquota n\343o pode ser maior que ").append(TS_ALIQUOTA_MAX).append(" (7 digitos no total, sendo 2 de casas decimais).").toString(), xml);
        ParserHelper.posicionaProximoElemento(xml);
        return value;
    }

    public void writeTsAliquota(XMLStreamWriter xml, Double value)
        throws XMLStreamException
    {
        if(value.doubleValue() < 0.0D)
            throwException("tsAliquota n\343o pode ser negativo.", null);
        if(value.doubleValue() > TS_ALIQUOTA_MAX.doubleValue())
            throwException((new StringBuilder()).append("tsAliquota n\343o pode ser maior que ").append(TS_ALIQUOTA_MAX).append(" (7 digitos no total, sendo 2 de casas decimais).").toString(), null);
        xsdTypesParser.writeDecimal(xml, value, TS_ALIQUOTA_FORMAT);
    }

    public String readTsIdTag(XMLStreamReader xml, String attributeName)
        throws XMLStreamException
    {
        String value = xml.getAttributeValue(nameSpaceURI, attributeName);
        checkMaxLength("tsIdTag", value, 255, xml);
        return value;
    }

    public void writeTsIdTag(XMLStreamWriter xml, String attributeName, String value)
        throws XMLStreamException
    {
        checkMaxLength("tsIdTag", value, 255, null);
        xml.writeAttribute(nameSpaceURI, attributeName, value);
    }

    public String readTsCodigoMensagem(XMLStreamReader xml)
        throws XMLStreamException
    {
        return readString(xml, "tsCodigoMensagem", Integer.valueOf(5), Integer.valueOf(1));
    }

    public void writeTsCodigoMensagem(XMLStreamWriter xml, String value)
        throws XMLStreamException
    {
        writeString(xml, "tsCodigoMensagem", Integer.valueOf(5), Integer.valueOf(1), value);
    }

    public String readTsDetalheMensagem(XMLStreamReader xml)
        throws XMLStreamException
    {
        return readString(xml, "tsDetalheMensagem", Integer.valueOf(300), Integer.valueOf(1));
    }

    public void writeTsDetalheMensagem(XMLStreamWriter xml, String value)
        throws XMLStreamException
    {
        writeString(xml, "tsDetalheMensagem", Integer.valueOf(300), Integer.valueOf(1), value);
    }

    public String readTsDescricaoMensagem(XMLStreamReader xml)
        throws XMLStreamException
    {
        return readString(xml, "tsDescricaoMensagem", Integer.valueOf(300), Integer.valueOf(1));
    }

    public void writeTsDescricaoMensagem(XMLStreamWriter xml, String value)
        throws XMLStreamException
    {
        writeString(xml, "tsDescricaoMensagem", Integer.valueOf(300), Integer.valueOf(1), value);
    }

    protected String readString(XMLStreamReader xml, String typeName, Integer maxLength, Integer minLength)
        throws XMLStreamException
    {
        String value = xsdTypesParser.readString(xml);
        if(null != maxLength && null != minLength && maxLength.equals(minLength))
        {
            checkLength(typeName, value, maxLength.intValue(), xml);
        } else
        {
            if(null != maxLength)
                checkMaxLength(typeName, value, maxLength.intValue(), xml);
            if(null != minLength)
                checkMinLength(typeName, value, minLength.intValue(), xml);
        }
        ParserHelper.posicionaProximoElemento(xml);
        return value;
    }

    protected void writeString(XMLStreamWriter xml, String typeName, Integer maxLength, Integer minLength, String value)
        throws XMLStreamException
    {
        value = value.trim();
        if(null != maxLength && null != minLength && maxLength.equals(minLength))
        {
            checkLength(typeName, value, maxLength.intValue(), null);
        } else
        {
            if(null != maxLength)
                checkMaxLength(typeName, value, maxLength.intValue(), null);
            if(null != minLength)
                checkMinLength(typeName, value, minLength.intValue(), null);
        }
        xsdTypesParser.writeString(xml, value);
    }

    static 
    {
        TS_VALOR_FORMAT = new DecimalFormat("0.00", new DecimalFormatSymbols(Locale.US));
        TS_ALIQUOTA_FORMAT = new DecimalFormat("00000.##", new DecimalFormatSymbols(Locale.US));
    }
}
