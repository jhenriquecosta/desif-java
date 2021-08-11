
package br.gov.pbh.desif.ws.xml.parser;

import java.io.PrintStream;
import java.util.*;
import javax.xml.stream.*;

// Referenced classes of package br.gov.pbh.desif.ws.xml.parser:
//            BaseParser, XSDTypesParser, TiposSimplesParser, TiposComplexosParser, 
//            ServicoEnviarDeclaracaoResposta

public abstract class ServicoParser extends BaseParser
{
    public enum TipoServico
    {
        EnviarDeclaracao;
    }

    protected static final boolean USA_NAMESPACE_LOCAL = true;
    private static final ServicoParser objetos[] = {
        ServicoEnviarDeclaracaoResposta.getInstance()
    };
    private static final Map nameSpaceMap;
    private static final Map tipoMap;
    protected final TipoServico tipo;
    protected final boolean envio;
    protected final String otherSideNameSpaceURI;
    protected final XSDTypesParser xsdTypesParser;
    protected final TiposSimplesParser tiposSimplesParser;
    protected final TiposComplexosParser tiposComplexosParser;

    public static String[] getNamespaceURIs(TipoServico tipo, boolean envio)
    {
        List list = (List)tipoMap.get(tipo);
        if(list == null || list.isEmpty())
            return new String[0];
        ArrayList array = new ArrayList(list.size() / 2);
        Iterator i$ = list.iterator();
        do
        {
            if(!i$.hasNext())
                break;
            ServicoParser servico = (ServicoParser)i$.next();
            if(envio == servico.isEnvio())
                array.add(servico.getNameSpaceURI());
        } while(true);
        return (String[])array.toArray(new String[array.size()]);
    }

    public static ServicoParser getInstance(String nameSpaceURI)
    {
        return (ServicoParser)nameSpaceMap.get(nameSpaceURI);
    }

    public static ServicoParser getInstance(String nameSpaceURI, Class objectClass)
    {
        ServicoParser servicoParser = getInstance(nameSpaceURI);
        if(servicoParser.aceitaObjeto(objectClass))
            return servicoParser;
        else
            return null;
    }

    public static ServicoParser getVersaoMaisRecente(TipoServico tipo, boolean envio)
    {
label0:
        {
            List list = (List)tipoMap.get(tipo);
            if(list == null || list.isEmpty())
                break label0;
            ListIterator listIterator = list.listIterator(list.size());
            ServicoParser servico;
            do
            {
                if(!listIterator.hasPrevious())
                    break label0;
                servico = (ServicoParser)listIterator.previous();
            } while(envio != servico.isEnvio());
            return servico;
        }
        return null;
    }

    public static ServicoParser getOtherSide(String nameSpaceURIOriginal)
    {
        ServicoParser servicoParserOriginal = (ServicoParser)nameSpaceMap.get(nameSpaceURIOriginal);
        return servicoParserOriginal != null ? (ServicoParser)nameSpaceMap.get(servicoParserOriginal.getOtherSideNameSpaceURI()) : null;
    }

    ServicoParser(TipoServico tipo, boolean envio, String nameSpaceURI, String otherSideNameSpaceURI, XSDTypesParser xsdTypesParser, TiposSimplesParser tiposSimplesParser, TiposComplexosParser tiposComplexosParser)
    {
        super(null, nameSpaceURI, new BaseParser[] {
            xsdTypesParser, tiposSimplesParser, tiposComplexosParser
        });
        if(tipo == null)
        {
            throw new IllegalArgumentException("Tipo do servi\347o n\343o pode ser nulo!");
        } else
        {
            this.tipo = tipo;
            this.envio = envio;
            this.otherSideNameSpaceURI = otherSideNameSpaceURI;
            this.xsdTypesParser = xsdTypesParser;
            this.tiposSimplesParser = tiposSimplesParser;
            this.tiposComplexosParser = tiposComplexosParser;
        }
    }

    public abstract boolean aceitaObjeto(Class class1);

    public abstract Object readServico(XMLStreamReader xmlstreamreader, boolean flag)
        throws XMLStreamException;

    public abstract void writeServico(XMLStreamWriter xmlstreamwriter, Object obj)
        throws XMLStreamException;

    public TipoServico getTipo()
    {
        return tipo;
    }

    public boolean isEnvio()
    {
        return envio;
    }

    public String getOtherSideNameSpaceURI()
    {
        return otherSideNameSpaceURI;
    }

    protected void readElementoEspecifico(XMLStreamReader xml, Object value)
        throws XMLStreamException
    {
        throw new XMLStreamException("Not implemented!");
    }

    static 
    {
        nameSpaceMap = new HashMap(objetos.length);
        tipoMap = new HashMap(TipoServico.values().length);
        ServicoParser arr$[] = objetos;
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            ServicoParser obj = arr$[i$];
            TipoServico _tipo = obj.getTipo();
            if(_tipo != null)
            {
                nameSpaceMap.put(obj.getNameSpaceURI(), obj);
                List list = (List)tipoMap.get(_tipo);
                if(list == null)
                {
                    list = new ArrayList(2);
                    tipoMap.put(_tipo, list);
                }
                list.add(obj);
            } else
            {
                System.err.println((new StringBuilder()).append("Objeto de Servi\347o n\343o definiu seu tipo e ser\341 ignorado: ").append(obj.getClass()).toString());
            }
        }

    }
}
