package br.gov.pbh.desif.ws.xml.parser;

import br.gov.pbh.desif.ws.xml.vo.services.ServicoEnviarDeclaracaoRespostaVO;
import br.gov.pbh.desif.ws.xml.vo.tiposcomplexos.ProtocoloVO;
import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.*;

// Referenced classes of package br.gov.pbh.desif.ws.xml.parser:
//            ServicoParser, XSDTypesParser, TiposSimplesParser, TiposComplexosParser, 
//            ParserHelper

public class ServicoEnviarDeclaracaoResposta extends ServicoParser
{

    public static final String NSURI = "http://es605-304:8080/schemas2/desif.xsd";
    private static ServicoEnviarDeclaracaoResposta instance = null;

    public static ServicoEnviarDeclaracaoResposta getInstance()
    {
        if(instance == null)
            instance = new ServicoEnviarDeclaracaoResposta("http://es605-304:8080/schemas2/desif.xsd", "http://es605-304:8080/schemas2/desif.xsd", XSDTypesParser.getInstance(), TiposSimplesParser.getInstance(), TiposComplexosParser.getInstance());
        return instance;
    }

    protected ServicoEnviarDeclaracaoResposta(String nameSpaceURI, String otherSideNameSpaceURI, XSDTypesParser xsdTypesParser, TiposSimplesParser tiposSimplesParser, TiposComplexosParser tiposComplexosParser)
    {
        super(ServicoParser.TipoServico.EnviarDeclaracao, true, nameSpaceURI, otherSideNameSpaceURI, xsdTypesParser, tiposSimplesParser, tiposComplexosParser);
    }

    public boolean aceitaObjeto(Class objectClass)
    {
        return ((Object) (br.gov.pbh.desif.ws.xml.vo.services.ServicoEnviarDeclaracaoRespostaVO.class)).equals(((Object) (objectClass)));
    }

    public ServicoEnviarDeclaracaoRespostaVO readServico(XMLStreamReader xml, boolean existeTagRaizCnpj)
        throws XMLStreamException
    {
        consomeStart(xml, "EnviarDeclaracaoResposta");
        ServicoEnviarDeclaracaoRespostaVO servico = new ServicoEnviarDeclaracaoRespostaVO();
        boolean protocoloRetorno = false;
        if(consomeStart(xml, "ProtocoloRetorno", true))
        {
            String id = consomeStartRetornaId(xml, "Protocolo", false);
            if(existeTagRaizCnpj)
                servico.setProtocolo(tiposComplexosParser.readTcInfProtocolo(xml));
            else
                servico.setProtocolo(tiposComplexosParser.readTcInfProtocoloSemRaizCnpj(xml));
            servico.getProtocolo().setCodigo(Long.valueOf(id));
            consomeEnd(xml, "Protocolo");
            consomeSignature(xml);
            consomeEnd(xml, "ProtocoloRetorno");
            protocoloRetorno = true;
        } else
        if(consomeStart(xml, "ListaMensagensRetorno", true) && !protocoloRetorno)
        {
            servico.setMensagens(((List) (new ArrayList())));
            for(; consomeStart(xml, "MensagemRetorno", true); consomeEnd(xml, "MensagemRetorno"));
            consomeEnd(xml, "ListaMensagensRetorno");
        } else
        {
            throw new XMLStreamException("Ou a lista de mensagens ou o protocolo devem estar preenchidos, n\uFFFDo ambos ou sequer nenhum.");
        }
        consomeEnd(xml, "EnviarDeclaracaoResposta");
        return servico;
    }

    public ProtocoloVO readProtocolo(XMLStreamReader xml, boolean existeRaizCnpj)
        throws XMLStreamException
    {
        ProtocoloVO protocolo = new ProtocoloVO();
        if(consomeStart(xml, "ProtocoloRetorno", false, ((String) (null))))
        {
            String id = consomeStartRetornaId(xml, "Protocolo", false, ((String) (null)));
            if(existeRaizCnpj)
                protocolo = tiposComplexosParser.readTcInfProtocolo(xml);
            else
                protocolo = tiposComplexosParser.readTcInfProtocoloSemRaizCnpj(xml);
            protocolo.setCodigo(Long.valueOf(id));
            consomeEnd(xml, "Protocolo");
            consomeSignature(xml);
            consomeEnd(xml, "ProtocoloRetorno");
        }
        return protocolo;
    }

    public void writeServico(XMLStreamWriter xml, ServicoEnviarDeclaracaoRespostaVO value)
        throws XMLStreamException
    {
        if(!ParserHelper.isEmpty(((Object) (value))))
        {
            if(!ParserHelper.isEmpty(((Object) (value.getMensagens()))) && !ParserHelper.isEmpty(((Object) (value.getProtocolo()))))
                throw new XMLStreamException("Ou a lista de mensagens ou o protocolo devem estar preenchidos, n\uFFFDo ambos.");
            writeStartElement(xml, "EnviarDeclaracaoResposta", true, true);
            if(!ParserHelper.isEmpty(((Object) (value.getMensagens()))))
            {
                writeStartElement(xml, "ListaMensagensRetorno", false, false);
                writeEndElement(xml);
                writeEndElement(xml);
            } else
            if(!ParserHelper.isEmpty(((Object) (value.getProtocolo()))))
            {
                writeStartElement(xml, "ProtocoloRetorno", false, false);
                writeStartElement(xml, "Protocolo", false, false);
                tiposComplexosParser.writeTcInfProtocolo(xml, value.getProtocolo(), false);
                writeEndElement(xml);
                writeEndElement(xml);
                writeEndElement(xml);
            } else
            {
                throw new XMLStreamException("A lista de mensagens ou o protocolo devem estar preenchidos.");
            }
        } else
        {
            throw new XMLStreamException("O objeto \uFFFD uma refer\uFFFDncia a null, n\uFFFDo \uFFFD poss\uFFFDvel fazer o parser.");
        }
    }

    public void writeServico(XMLStreamWriter xml, ServicoEnviarDeclaracaoRespostaVO value, String xmlProcolo)
        throws XMLStreamException
    {
        if(!ParserHelper.isEmpty(((Object) (value))))
        {
            writeStartElement(xml, "EnviarDeclaracaoResposta", true, true);
            if(!ParserHelper.isEmpty(((Object) (value.getProtocolo()))))
            {
                List aux = ((List) (new ArrayList()));
                aux.add(((Object) (xmlProcolo)));
                writeDirectStrings(xml, ((java.util.Collection) (aux)));
                writeEndElement(xml);
            } else
            {
                throw new XMLStreamException("O protocolo deve estar preenchidos.");
            }
        } else
        {
            throw new XMLStreamException("O objeto \uFFFD uma refer\uFFFDncia a null, n\uFFFDo \uFFFD poss\uFFFDvel fazer o parser.");
        }
    }

    
    public void writeServico(XMLStreamWriter x0, Object x1) throws XMLStreamException
    {
        writeServico(x0, (ServicoEnviarDeclaracaoRespostaVO)x1);
    }

 /*   public Object readServico(XMLStreamReader x0, boolean x1) throws XMLStreamException
    {
        return ((Object) (readServico(x0, x1)));
    }*/
}
