
package br.gov.pbh.desif.ws.xml.parser;

import br.gov.pbh.desif.ws.xml.vo.tiposcomplexos.MensagemRetornoVO;
import java.util.ArrayList;
import br.gov.pbh.desif.ws.xml.vo.tiposcomplexos.ProtocoloVO;
import br.gov.pbh.desif.ws.xml.vo.tiposcomplexos.TotalizacaoVO;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.XMLStreamException;
import br.gov.pbh.desif.ws.xml.vo.tiposcomplexos.DependenciaVO;
import javax.xml.stream.XMLStreamReader;

public class TiposComplexosParser extends BaseParser
{
    private static final TiposComplexosParser instance;
    private final XSDTypesParser xsdTypesParser;
    private final TiposSimplesParser tiposSimplesParser;
    
    public static TiposComplexosParser getInstance() {
        return TiposComplexosParser.instance;
    }
    
    protected TiposComplexosParser(final String nameSpaceURI, final TiposSimplesParser tiposSimplesParser, final XSDTypesParser xsdTypesParser) {
        super("tc", nameSpaceURI, new BaseParser[] { tiposSimplesParser, xsdTypesParser });
        this.tiposSimplesParser = tiposSimplesParser;
        this.xsdTypesParser = xsdTypesParser;
    }
    
    public DependenciaVO readTcDependencia(final XMLStreamReader xml) throws XMLStreamException {
        final DependenciaVO dependencia = new DependenciaVO();
        if (this.consomeStart(xml, "CNPJ_Proprio", true)) {
            dependencia.setCnpjProprio(this.tiposSimplesParser.readTsCnpjProprio(xml));
            this.consomeEnd(xml, "CNPJ_Proprio");
        }
        this.consomeStart(xml, "Indr_Insc_Munl");
        dependencia.setIndInscMunl(this.tiposSimplesParser.readTsIndrInscMunl(xml));
        this.consomeEnd(xml, "Indr_Insc_Munl");
        this.consomeStart(xml, "Cod_Depe");
        dependencia.setCodDepe(this.tiposSimplesParser.readTsCodDependencia(xml));
        this.consomeEnd(xml, "Cod_Depe");
        this.consomeStart(xml, "Ctbl_Propria");
        dependencia.setCtblPropria(this.tiposSimplesParser.readTsCtblPropria(xml));
        this.consomeEnd(xml, "Ctbl_Propria");
        return dependencia;
    }
    
    public void writeTcDependencia(final XMLStreamWriter xml, final DependenciaVO value, final boolean insereNameSpaceLocal) throws XMLStreamException {
        if (value.getCnpjProprio() != null)
        {
            this.writeStartElement(xml, "CNPJ_Proprio", false, insereNameSpaceLocal);
            this.tiposSimplesParser.writeTsCnpjProprio(xml, value.getCnpjProprio());
            this.writeEndElement(xml);
        }
        this.writeStartElement(xml, "Indr_Insc_Munl", false, insereNameSpaceLocal);
        this.tiposSimplesParser.writeTsIndrInscMunl(xml, value.getIndInscMunl());
        this.writeEndElement(xml);
        this.writeStartElement(xml, "Cod_Depe", false, insereNameSpaceLocal);
        this.tiposSimplesParser.writeTsCodDependencia(xml, value.getCodDepe());
        this.writeEndElement(xml);
        this.writeStartElement(xml, "Ctbl_Propria", false, insereNameSpaceLocal);
        this.tiposSimplesParser.writeTsCtblPropria(xml, value.getCtblPropria());
        this.writeEndElement(xml);
    }
    
    public TotalizacaoVO readTcTotalizacao(final XMLStreamReader xml) throws XMLStreamException {
        final TotalizacaoVO totalizacao = new TotalizacaoVO();
        this.consomeStart(xml, "Rece_Decl_Cnso");
        totalizacao.setReceDeclCnso(this.tiposSimplesParser.readTsValor(xml));
        this.consomeEnd(xml, "Rece_Decl_Cnso");
        if (this.consomeStart(xml, "Dedu_Rece_Decl_Sub_Titu", true)) {
            totalizacao.setDeduReceDeclSubTitu(this.tiposSimplesParser.readTsValor(xml));
            this.consomeEnd(xml, "Dedu_Rece_Decl_Sub_Titu");
        }
        if (this.consomeStart(xml, "Dedu_Rece_Decl_Cnso", true)) {
            totalizacao.setDeduReceDeclCnso(this.tiposSimplesParser.readTsValor(xml));
            this.consomeEnd(xml, "Dedu_Rece_Decl_Cnso");
        }
        this.consomeStart(xml, "Aliq_ISSQN");
        totalizacao.setAliqISSQN(this.tiposSimplesParser.readTsAliquota(xml));
        this.consomeEnd(xml, "Aliq_ISSQN");
        return totalizacao;
    }
    
    public void writeTcTotalizacao(final XMLStreamWriter xml, final TotalizacaoVO value, final boolean insereNameSpaceLocal) throws XMLStreamException {
        this.writeStartElement(xml, "Rece_Decl_Cnso", false, insereNameSpaceLocal);
        this.tiposSimplesParser.writeTsValor(xml, value.getReceDeclCnso());
        this.writeEndElement(xml);
        if (value.getDeduReceDeclSubTitu() != null) {
            this.writeStartElement(xml, "Dedu_Rece_Decl_Sub_Titu", false, insereNameSpaceLocal);
            this.tiposSimplesParser.writeTsValor(xml, value.getDeduReceDeclSubTitu());
            this.writeEndElement(xml);
        }
        if (value.getDeduReceDeclSubTitu() != null) {
            this.writeStartElement(xml, "Dedu_Rece_Decl_Cnso", false, insereNameSpaceLocal);
            this.tiposSimplesParser.writeTsValor(xml, value.getDeduReceDeclCnso());
            this.writeEndElement(xml);
        }
        this.writeStartElement(xml, "Aliq_ISSQN", false, insereNameSpaceLocal);
        this.tiposSimplesParser.writeTsAliquota(xml, value.getAliqISSQN());
        this.writeEndElement(xml);
    }
    
    public ProtocoloVO readTcInfProtocoloSemRaizCnpj(final XMLStreamReader xml) throws XMLStreamException {
        final ProtocoloVO value = new ProtocoloVO();
        this.consomeStart(xml, "Nome");
        value.setNome(this.tiposSimplesParser.readTsRazaoSocial(xml));
        this.consomeEnd(xml, "Nome");
        this.consomeStart(xml, "Ano_Mes_Inic_Cmpe");
        value.setAnoMesInicCmpe(this.tiposSimplesParser.readTsAnoMesInicCmpe(xml));
        this.consomeEnd(xml, "Ano_Mes_Inic_Cmpe");
        this.consomeStart(xml, "Tipo_Decl");
        value.setTipoDecl(this.tiposSimplesParser.readTsTipoDeclaracao(xml));
        this.consomeEnd(xml, "Tipo_Decl");
        if (this.consomeStart(xml, "Tipo_Cnso", true)) {
            value.setTipoCnso(this.tiposSimplesParser.readTsTipoDeclaracao(xml));
            this.consomeEnd(xml, "Tipo_Cnso");
        }
        if (this.consomeStart(xml, "DataEntrega", true)) {
            value.setDatEntrega(this.xsdTypesParser.readDateTime(xml));
            ParserHelper.posicionaProximoElemento(xml);
            this.consomeEnd(xml, "DataEntrega");
        }
        if (this.consomeStart(xml, "ListaDependencia", true)) {
            value.setListaDependencia(new ArrayList<>());
            while (this.consomeStart(xml, "Dependencia", true)) {
                value.getListaDependencia().add(this.readTcDependencia(xml));
                this.consomeEnd(xml, "Dependencia");
            }
            this.consomeEnd(xml, "ListaDependencia");
        }
        if (this.consomeStart(xml, "ListaTotalizacao", true)) {
            value.setListaTotalizacao(new ArrayList<>());
            while (this.consomeStart(xml, "Totalizacao", true)) {
                value.getListaTotalizacao().add(this.readTcTotalizacao(xml));
                this.consomeEnd(xml, "Totalizacao");
            }
            this.consomeEnd(xml, "ListaTotalizacao");
        }
        return value;
    }
    
    public ProtocoloVO readTcInfProtocolo(final XMLStreamReader xml) throws XMLStreamException {
        final ProtocoloVO value = new ProtocoloVO();
        this.consomeStart(xml, "RaizCnpj");
        value.setRaizCnpj(this.tiposSimplesParser.readTsRaizCnpj(xml));
        this.consomeEnd(xml, "RaizCnpj");
        this.consomeStart(xml, "Nome");
        value.setNome(this.tiposSimplesParser.readTsRazaoSocial(xml));
        this.consomeEnd(xml, "Nome");
        this.consomeStart(xml, "Ano_Mes_Inic_Cmpe");
        value.setAnoMesInicCmpe(this.tiposSimplesParser.readTsAnoMesInicCmpe(xml));
        this.consomeEnd(xml, "Ano_Mes_Inic_Cmpe");
        this.consomeStart(xml, "Tipo_Decl");
        value.setTipoDecl(this.tiposSimplesParser.readTsTipoDeclaracao(xml));
        this.consomeEnd(xml, "Tipo_Decl");
        if (this.consomeStart(xml, "Tipo_Cnso", true)) {
            value.setTipoCnso(this.tiposSimplesParser.readTsTipoDeclaracao(xml));
            this.consomeEnd(xml, "Tipo_Cnso");
        }
        if (this.consomeStart(xml, "DataEntrega", true)) {
            value.setDatEntrega(this.xsdTypesParser.readDateTime(xml));
            ParserHelper.posicionaProximoElemento(xml);
            this.consomeEnd(xml, "DataEntrega");
        }
        if (this.consomeStart(xml, "ListaDependencia", true)) {
            value.setListaDependencia(new ArrayList<DependenciaVO>());
            while (this.consomeStart(xml, "Dependencia", true)) {
                value.getListaDependencia().add(this.readTcDependencia(xml));
                this.consomeEnd(xml, "Dependencia");
            }
            this.consomeEnd(xml, "ListaDependencia");
        }
        if (this.consomeStart(xml, "ListaTotalizacao", true)) {
            value.setListaTotalizacao(new ArrayList<TotalizacaoVO>());
            while (this.consomeStart(xml, "Totalizacao", true)) {
                value.getListaTotalizacao().add(this.readTcTotalizacao(xml));
                this.consomeEnd(xml, "Totalizacao");
            }
            this.consomeEnd(xml, "ListaTotalizacao");
        }
        return value;
    }
    
    public void writeTcInfProtocolo(final XMLStreamWriter xml, final ProtocoloVO value, final boolean insereNameSpaceLocal) throws XMLStreamException {
        if (value.getCodigo() == null) {
            throw new XMLStreamException("Tag id do protocolo n\ufffdo pode ser null");
        }
        this.tiposSimplesParser.writeTsIdTag(xml, "id", value.getCodigo().toString());
        this.writeStartElement(xml, "RaizCnpj", false, insereNameSpaceLocal);
        this.tiposSimplesParser.writeTsRaizCnpj(xml, value.getRaizCnpj());
        this.writeEndElement(xml);
        this.writeStartElement(xml, "Nome", false, insereNameSpaceLocal);
        this.tiposSimplesParser.writeTsRazaoSocial(xml, value.getNome());
        this.writeEndElement(xml);
        this.writeStartElement(xml, "Ano_Mes_Inic_Cmpe", false, insereNameSpaceLocal);
        this.tiposSimplesParser.writeTsAnoMesInicCmpe(xml, value.getAnoMesInicCmpe());
        this.writeEndElement(xml);
        this.writeStartElement(xml, "Tipo_Decl", false, insereNameSpaceLocal);
        this.tiposSimplesParser.writeTsTipoDeclaracao(xml, value.getTipoDecl());
        this.writeEndElement(xml);
        if (!ParserHelper.isEmpty(value.getTipoCnso()))
        {
            this.writeStartElement(xml, "Tipo_Cnso", false, insereNameSpaceLocal);
            this.tiposSimplesParser.writeTsTipoConsolidacao(xml, (short)(Object)value.getTipoCnso());
            this.writeEndElement(xml);
        }
        if (!ParserHelper.isEmpty(value.getDatEntrega())) {
            this.writeStartElement(xml, "DataEntrega", false, insereNameSpaceLocal);
            this.xsdTypesParser.writeDateTime(xml, value.getDatEntrega());
            this.writeEndElement(xml);
        }
        if (!ParserHelper.isEmpty(value.getListaDependencia())) {
            this.writeStartElement(xml, "ListaDependencia", false, insereNameSpaceLocal);
            for (final DependenciaVO dependencia : value.getListaDependencia()) {
                this.writeStartElement(xml, "Dependencia", false, insereNameSpaceLocal);
                this.writeTcDependencia(xml, dependencia, false);
                this.writeEndElement(xml);
            }
            this.writeEndElement(xml);
        }
        if (!ParserHelper.isEmpty(value.getListaTotalizacao())) {
            this.writeStartElement(xml, "ListaTotalizacao", false, insereNameSpaceLocal);
            for (final TotalizacaoVO totalizacao : value.getListaTotalizacao()) {
                this.writeStartElement(xml, "Totalizacao", false, insereNameSpaceLocal);
                this.writeTcTotalizacao(xml, totalizacao, false);
                this.writeEndElement(xml);
            }
            this.writeEndElement(xml);
        }
    }
    
    public MensagemRetornoVO readTcMensagemRetorno(final XMLStreamReader xml) throws XMLStreamException {
        final MensagemRetornoVO resp = new MensagemRetornoVO();
        if (this.consomeStart(xml, "Detalhe", true)) {
            resp.setDetalhe(this.tiposSimplesParser.readTsDetalheMensagem(xml));
            this.consomeEnd(xml, "Detalhe");
        }
        this.consomeStart(xml, "Codigo");
        resp.setCodigo(this.tiposSimplesParser.readTsCodigoMensagem(xml));
        this.consomeEnd(xml, "Codigo");
        this.consomeStart(xml, "Mensagem");
        resp.setMensagem(this.tiposSimplesParser.readTsDescricaoMensagem(xml));
        this.consomeEnd(xml, "Mensagem");
        return resp;
    }
    
    public void writeTcMensagemRetorno(final XMLStreamWriter xml, final MensagemRetornoVO value, final boolean insereNameSpaceLocal) throws XMLStreamException {
        if (!ParserHelper.isEmpty(value.getDetalhe())) {
            this.writeStartElement(xml, "Detalhe", false, insereNameSpaceLocal);
            this.tiposSimplesParser.writeTsDetalheMensagem(xml, value.getDetalhe());
            this.writeEndElement(xml);
        }
        this.writeStartElement(xml, "Codigo", false, insereNameSpaceLocal);
        this.tiposSimplesParser.writeTsCodigoMensagem(xml, value.getCodigo());
        this.writeEndElement(xml);
        this.writeStartElement(xml, "Mensagem", false, insereNameSpaceLocal);
        this.tiposSimplesParser.writeTsDescricaoMensagem(xml, value.getMensagem());
        this.writeEndElement(xml);
    }
    
    static 
    {
        instance = new TiposComplexosParser("http://es605-304:8080/schemas2/desif.xsd", TiposSimplesParser.getInstance(), XSDTypesParser.getInstance());
    }
}
