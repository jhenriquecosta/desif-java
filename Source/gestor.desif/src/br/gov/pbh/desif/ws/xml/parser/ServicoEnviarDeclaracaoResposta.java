// 
// Decompiled by Procyon v0.5.30
// 

package br.gov.pbh.desif.ws.xml.parser;

import javax.xml.stream.XMLStreamWriter;
import br.gov.pbh.desif.ws.xml.vo.tiposcomplexos.ProtocoloVO;
import java.util.List;
import br.gov.pbh.desif.ws.cliente.Erros;
import java.util.ArrayList;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import br.gov.pbh.desif.ws.xml.vo.services.ServicoEnviarDeclaracaoRespostaVO;

public class ServicoEnviarDeclaracaoResposta extends ServicoParser<ServicoEnviarDeclaracaoRespostaVO>
{
    public static final String NSURI = "http://es605-304:8080/schemas2/desif.xsd";
    private static ServicoEnviarDeclaracaoResposta instance;
    
    public static ServicoEnviarDeclaracaoResposta getInstance() {
        if (ServicoEnviarDeclaracaoResposta.instance == null) {
            ServicoEnviarDeclaracaoResposta.instance = new ServicoEnviarDeclaracaoResposta("http://es605-304:8080/schemas2/desif.xsd", "http://es605-304:8080/schemas2/desif.xsd", XSDTypesParser.getInstance(), TiposSimplesParser.getInstance(), TiposComplexosParser.getInstance());
        }
        return ServicoEnviarDeclaracaoResposta.instance;
    }
    
    protected ServicoEnviarDeclaracaoResposta(final String nameSpaceURI, final String otherSideNameSpaceURI, final XSDTypesParser xsdTypesParser, final TiposSimplesParser tiposSimplesParser, final TiposComplexosParser tiposComplexosParser) {
        super(TipoServico.EnviarDeclaracao, true, nameSpaceURI, otherSideNameSpaceURI, xsdTypesParser, tiposSimplesParser, tiposComplexosParser);
    }
    
    @Override
    public boolean aceitaObjeto(final Class<?> objectClass) 
    {
        return ServicoEnviarDeclaracaoRespostaVO.class.equals(objectClass);
    }
    
    @Override
    public ServicoEnviarDeclaracaoRespostaVO readServico(final XMLStreamReader xml, final boolean existeTagRaizCnpj) throws XMLStreamException {
        this.consomeStart(xml, "EnviarDeclaracaoResposta");
        final ServicoEnviarDeclaracaoRespostaVO servico = new ServicoEnviarDeclaracaoRespostaVO();
        boolean protocoloRetorno = false;
        if (this.consomeStart(xml, "ProtocoloRetorno", true)) {
            final String id = this.consomeStartRetornaId(xml, "Protocolo", false);
            if (existeTagRaizCnpj) {
                servico.setProtocolo(this.tiposComplexosParser.readTcInfProtocolo(xml));
            }
            else {
                servico.setProtocolo(this.tiposComplexosParser.readTcInfProtocoloSemRaizCnpj(xml));
            }
            servico.getProtocolo().setCodigo(Long.valueOf(id));
            this.consomeEnd(xml, "Protocolo");
            this.consomeSignature(xml);
            this.consomeEnd(xml, "ProtocoloRetorno");
            protocoloRetorno = true;
        }
        else {
            if (!this.consomeStart(xml, "ListaMensagensRetorno", true) || protocoloRetorno) {
                throw new XMLStreamException("Ou a lista de mensagens ou o protocolo devem estar preenchidos, n\ufffdo ambos ou sequer nenhum.");
            }
            servico.setMensagens(new ArrayList<Erros>());
            while (this.consomeStart(xml, "MensagemRetorno", true)) {
                this.consomeEnd(xml, "MensagemRetorno");
            }
            this.consomeEnd(xml, "ListaMensagensRetorno");
        }
        this.consomeEnd(xml, "EnviarDeclaracaoResposta");
        return servico;
    }
    
    public ProtocoloVO readProtocolo(final XMLStreamReader xml, final boolean existeRaizCnpj) throws XMLStreamException {
        ProtocoloVO protocolo = new ProtocoloVO();
        if (this.consomeStart(xml, "ProtocoloRetorno", false, null)) {
            final String id = this.consomeStartRetornaId(xml, "Protocolo", false, null);
            if (existeRaizCnpj) {
                protocolo = this.tiposComplexosParser.readTcInfProtocolo(xml);
            }
            else {
                protocolo = this.tiposComplexosParser.readTcInfProtocoloSemRaizCnpj(xml);
            }
            protocolo.setCodigo(Long.valueOf(id));
            this.consomeEnd(xml, "Protocolo");
            this.consomeSignature(xml);
            this.consomeEnd(xml, "ProtocoloRetorno");
        }
        return protocolo;
    }
    
    @Override
    public void writeServico(final XMLStreamWriter xml, final ServicoEnviarDeclaracaoRespostaVO value) throws XMLStreamException {
        if (ParserHelper.isEmpty(value)) {
            throw new XMLStreamException("O objeto \ufffd uma refer\ufffdncia a null, n\ufffdo \ufffd poss\ufffdvel fazer o parser.");
        }
        if (!ParserHelper.isEmpty(value.getMensagens()) && !ParserHelper.isEmpty(value.getProtocolo())) {
            throw new XMLStreamException("Ou a lista de mensagens ou o protocolo devem estar preenchidos, n\ufffdo ambos.");
        }
        this.writeStartElement(xml, "EnviarDeclaracaoResposta", true, true);
        if (!ParserHelper.isEmpty(value.getMensagens())) {
            this.writeStartElement(xml, "ListaMensagensRetorno", false, false);
            this.writeEndElement(xml);
            this.writeEndElement(xml);
        }
        else {
            if (ParserHelper.isEmpty(value.getProtocolo())) 
            {
                throw new XMLStreamException("A lista de mensagens ou o protocolo devem estar preenchidos.");
            }
            this.writeStartElement(xml, "ProtocoloRetorno", false, false);
            this.writeStartElement(xml, "Protocolo", false, false);
            this.tiposComplexosParser.writeTcInfProtocolo(xml, value.getProtocolo(), false);
            this.writeEndElement(xml);
            this.writeEndElement(xml);
            this.writeEndElement(xml);
        }
    }
    
    public void writeServico(final XMLStreamWriter xml, final ServicoEnviarDeclaracaoRespostaVO value, final String xmlProcolo) throws XMLStreamException {
        if (ParserHelper.isEmpty(value)) {
            throw new XMLStreamException("O objeto \ufffd uma refer\ufffdncia a null, n\ufffdo \ufffd poss\ufffdvel fazer o parser.");
        }
        this.writeStartElement(xml, "EnviarDeclaracaoResposta", true, true);
        if (!ParserHelper.isEmpty(value.getProtocolo())) {
            final List<String> aux = new ArrayList<String>();
            aux.add(xmlProcolo);
            this.writeDirectStrings(xml, aux);
            this.writeEndElement(xml);
            return;
        }
        throw new XMLStreamException("O protocolo deve estar preenchidos.");
    }
}
