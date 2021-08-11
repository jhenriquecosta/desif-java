
package br.gov.pbh.desif.ws.xml.parser;

import java.util.HashMap;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.util.ListIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class ServicoParser<T> extends BaseParser
{
    protected static final boolean USA_NAMESPACE_LOCAL = true;
    private static final ServicoParser<?>[] objetos;
    private static final Map<String, ServicoParser<?>> nameSpaceMap;
    private static final Map<TipoServico, List<ServicoParser<?>>> tipoMap;
    protected final TipoServico tipo;
    protected final boolean envio;
    protected final String otherSideNameSpaceURI;
    protected final XSDTypesParser xsdTypesParser;
    protected final TiposSimplesParser tiposSimplesParser;
    protected final TiposComplexosParser tiposComplexosParser;
    
    public static String[] getNamespaceURIs(final TipoServico tipo, final boolean envio) {
        final List<ServicoParser<?>> list = ServicoParser.tipoMap.get(tipo);
        if (list == null || list.isEmpty()) {
            return new String[0];
        }
        final ArrayList<String> array = new ArrayList<>(list.size() / 2);
        for (final ServicoParser<?> servico : list) {
            if (envio == servico.isEnvio()) {
                array.add(servico.getNameSpaceURI());
            }
        }
        return array.toArray(new String[array.size()]);
    }
    
    public static ServicoParser<?> getInstance(final String nameSpaceURI) {
        return ServicoParser.nameSpaceMap.get(nameSpaceURI);
    }
    
    public static <E> ServicoParser<E> getInstance(final String nameSpaceURI, final Class<E> objectClass) {
        final ServicoParser<?> servicoParser = getInstance(nameSpaceURI);
        if (servicoParser.aceitaObjeto(objectClass)) {
            return (ServicoParser<E>)servicoParser;
        }
        return null;
    }
    
    public static <T> ServicoParser<T> getVersaoMaisRecente(final TipoServico tipo, final boolean envio) {
        final List<ServicoParser<?>> list = ServicoParser.tipoMap.get(tipo);
        if (list != null && !list.isEmpty()) {
            final ListIterator<ServicoParser<?>> listIterator = list.listIterator(list.size());
            while (listIterator.hasPrevious()) {
                final ServicoParser<?> servico = listIterator.previous();
                if (envio == servico.isEnvio()) {
                    return (ServicoParser<T>)servico;
                }
            }
        }
        return null;
    }
    
    public static <T> ServicoParser<T> getOtherSide(final String nameSpaceURIOriginal) {
        final ServicoParser<?> servicoParserOriginal = ServicoParser.nameSpaceMap.get(nameSpaceURIOriginal);
        return (ServicoParser<T>)((servicoParserOriginal == null) ? null : ServicoParser.nameSpaceMap.get(servicoParserOriginal.getOtherSideNameSpaceURI()));
    }
    
    protected ServicoParser(final TipoServico tipo, final boolean envio, final String nameSpaceURI, final String otherSideNameSpaceURI, final XSDTypesParser xsdTypesParser, final TiposSimplesParser tiposSimplesParser, final TiposComplexosParser tiposComplexosParser) {
        super(null, nameSpaceURI, new BaseParser[] { xsdTypesParser, tiposSimplesParser, tiposComplexosParser });
        if (tipo == null) {
            throw new IllegalArgumentException("Tipo do servi\u00e7o n\u00e3o pode ser nulo!");
        }
        this.tipo = tipo;
        this.envio = envio;
        this.otherSideNameSpaceURI = otherSideNameSpaceURI;
        this.xsdTypesParser = xsdTypesParser;
        this.tiposSimplesParser = tiposSimplesParser;
        this.tiposComplexosParser = tiposComplexosParser;
    }
    
    public abstract boolean aceitaObjeto(final Class<?> p0);
    
    public abstract T readServico(final XMLStreamReader p0, final boolean p1) throws XMLStreamException;
    
    public abstract void writeServico(final XMLStreamWriter p0, final T p1) throws XMLStreamException;
    
    public TipoServico getTipo() {
        return this.tipo;
    }
    
    public boolean isEnvio() {
        return this.envio;
    }
    
    public String getOtherSideNameSpaceURI() {
        return this.otherSideNameSpaceURI;
    }
    
    protected void readElementoEspecifico(final XMLStreamReader xml, final T value) throws XMLStreamException {
        throw new XMLStreamException("Not implemented!");
    }
    
    static {
        objetos = new ServicoParser[] { ServicoEnviarDeclaracaoResposta.getInstance() };
        nameSpaceMap = new HashMap<>(ServicoParser.objetos.length);
        tipoMap = new HashMap<>(TipoServico.values().length);
        for (final ServicoParser<?> obj : ServicoParser.objetos) {
            final TipoServico _tipo = obj.getTipo();
            if (_tipo != null) {
                ServicoParser.nameSpaceMap.put(obj.getNameSpaceURI(), obj);
                List<ServicoParser<?>> list = ServicoParser.tipoMap.get(_tipo);
                if (list == null) {
                    list = new ArrayList<>(2);
                    ServicoParser.tipoMap.put(_tipo, list);
                }
                list.add(obj);
            }
            else {
                System.err.println("Objeto de Servi\u00e7o n\u00e3o definiu seu tipo e ser\u00e1 ignorado: " + obj.getClass());
            }
        }
    }
    
    public enum TipoServico
    {
        EnviarDeclaracao;
    }
}
