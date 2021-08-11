package br.gov.pbh.desif.ws.xml.parser;

import br.gov.pbh.desif.ws.xml.vo.tiposcomplexos.*;
import java.util.*;
import javax.xml.stream.*;

// Referenced classes of package br.gov.pbh.desif.ws.xml.parser:
//            BaseParser, TiposSimplesParser, XSDTypesParser, ParserHelper

public class TiposComplexosParser extends BaseParser
{

    private static final TiposComplexosParser instance = new TiposComplexosParser("http://es605-304:8080/schemas2/desif.xsd", TiposSimplesParser.getInstance(), XSDTypesParser.getInstance());
    private final XSDTypesParser xsdTypesParser;
    private final TiposSimplesParser tiposSimplesParser;

    public static TiposComplexosParser getInstance()
    {
        return instance;
    }

    protected TiposComplexosParser(String nameSpaceURI, TiposSimplesParser tiposSimplesParser, XSDTypesParser xsdTypesParser)
    {
        super("tc", nameSpaceURI, new BaseParser[] {
            tiposSimplesParser, xsdTypesParser
        });
        this.tiposSimplesParser = tiposSimplesParser;
        this.xsdTypesParser = xsdTypesParser;
    }

    public DependenciaVO readTcDependencia(XMLStreamReader xml)
        throws XMLStreamException
    {
        DependenciaVO dependencia = new DependenciaVO();
        if(consomeStart(xml, "CNPJ_Proprio", true))
        {
            dependencia.setCnpjProprio(tiposSimplesParser.readTsCnpjProprio(xml));
            consomeEnd(xml, "CNPJ_Proprio");
        }
        consomeStart(xml, "Indr_Insc_Munl");
        dependencia.setIndInscMunl(tiposSimplesParser.readTsIndrInscMunl(xml));
        consomeEnd(xml, "Indr_Insc_Munl");
        consomeStart(xml, "Cod_Depe");
        dependencia.setCodDepe(tiposSimplesParser.readTsCodDependencia(xml));
        consomeEnd(xml, "Cod_Depe");
        consomeStart(xml, "Ctbl_Propria");
        dependencia.setCtblPropria(tiposSimplesParser.readTsCtblPropria(xml));
        consomeEnd(xml, "Ctbl_Propria");
        return dependencia;
    }

    public void writeTcDependencia(XMLStreamWriter xml, DependenciaVO value, boolean insereNameSpaceLocal)
        throws XMLStreamException
    {
        if(value.getCnpjProprio() != null)
        {
            writeStartElement(xml, "CNPJ_Proprio", false, insereNameSpaceLocal);
            tiposSimplesParser.writeTsCnpjProprio(xml, value.getCnpjProprio());
            writeEndElement(xml);
        }
        writeStartElement(xml, "Indr_Insc_Munl", false, insereNameSpaceLocal);
        tiposSimplesParser.writeTsIndrInscMunl(xml, Short.valueOf(value.getIndInscMunl().shortValue()));
        writeEndElement(xml);
        writeStartElement(xml, "Cod_Depe", false, insereNameSpaceLocal);
        tiposSimplesParser.writeTsCodDependencia(xml, value.getCodDepe());
        writeEndElement(xml);
        writeStartElement(xml, "Ctbl_Propria", false, insereNameSpaceLocal);
        tiposSimplesParser.writeTsCtblPropria(xml, Short.valueOf(value.getCtblPropria().shortValue()));
        writeEndElement(xml);
    }

    public TotalizacaoVO readTcTotalizacao(XMLStreamReader xml)
        throws XMLStreamException
    {
        TotalizacaoVO totalizacao = new TotalizacaoVO();
        consomeStart(xml, "Rece_Decl_Cnso");
        totalizacao.setReceDeclCnso(tiposSimplesParser.readTsValor(xml));
        consomeEnd(xml, "Rece_Decl_Cnso");
        if(consomeStart(xml, "Dedu_Rece_Decl_Sub_Titu", true))
        {
            totalizacao.setDeduReceDeclSubTitu(tiposSimplesParser.readTsValor(xml));
            consomeEnd(xml, "Dedu_Rece_Decl_Sub_Titu");
        }
        if(consomeStart(xml, "Dedu_Rece_Decl_Cnso", true))
        {
            totalizacao.setDeduReceDeclCnso(tiposSimplesParser.readTsValor(xml));
            consomeEnd(xml, "Dedu_Rece_Decl_Cnso");
        }
        consomeStart(xml, "Aliq_ISSQN");
        totalizacao.setAliqISSQN(tiposSimplesParser.readTsAliquota(xml));
        consomeEnd(xml, "Aliq_ISSQN");
        return totalizacao;
    }

    public void writeTcTotalizacao(XMLStreamWriter xml, TotalizacaoVO value, boolean insereNameSpaceLocal)
        throws XMLStreamException
    {
        writeStartElement(xml, "Rece_Decl_Cnso", false, insereNameSpaceLocal);
        tiposSimplesParser.writeTsValor(xml, value.getReceDeclCnso());
        writeEndElement(xml);
        if(value.getDeduReceDeclSubTitu() != null)
        {
            writeStartElement(xml, "Dedu_Rece_Decl_Sub_Titu", false, insereNameSpaceLocal);
            tiposSimplesParser.writeTsValor(xml, value.getDeduReceDeclSubTitu());
            writeEndElement(xml);
        }
        if(value.getDeduReceDeclSubTitu() != null)
        {
            writeStartElement(xml, "Dedu_Rece_Decl_Cnso", false, insereNameSpaceLocal);
            tiposSimplesParser.writeTsValor(xml, value.getDeduReceDeclCnso());
            writeEndElement(xml);
        }
        writeStartElement(xml, "Aliq_ISSQN", false, insereNameSpaceLocal);
        tiposSimplesParser.writeTsAliquota(xml, value.getAliqISSQN());
        writeEndElement(xml);
    }

    public ProtocoloVO readTcInfProtocoloSemRaizCnpj(XMLStreamReader xml)
        throws XMLStreamException
    {
        ProtocoloVO value = new ProtocoloVO();
        consomeStart(xml, "Nome");
        value.setNome(tiposSimplesParser.readTsRazaoSocial(xml));
        consomeEnd(xml, "Nome");
        consomeStart(xml, "Ano_Mes_Inic_Cmpe");
        value.setAnoMesInicCmpe(tiposSimplesParser.readTsAnoMesInicCmpe(xml));
        consomeEnd(xml, "Ano_Mes_Inic_Cmpe");
        consomeStart(xml, "Tipo_Decl");
        value.setTipoDecl(tiposSimplesParser.readTsTipoDeclaracao(xml));
        consomeEnd(xml, "Tipo_Decl");
        if(consomeStart(xml, "Tipo_Cnso", true))
        {
            value.setTipoCnso(tiposSimplesParser.readTsTipoDeclaracao(xml));
            consomeEnd(xml, "Tipo_Cnso");
        }
        if(consomeStart(xml, "DataEntrega", true))
        {
            value.setDatEntrega(xsdTypesParser.readDateTime(xml));
            ParserHelper.posicionaProximoElemento(xml);
            consomeEnd(xml, "DataEntrega");
        }
        if(consomeStart(xml, "ListaDependencia", true))
        {
            value.setListaDependencia(new ArrayList());
            for(; consomeStart(xml, "Dependencia", true); consomeEnd(xml, "Dependencia"))
                value.getListaDependencia().add(readTcDependencia(xml));

            consomeEnd(xml, "ListaDependencia");
        }
        if(consomeStart(xml, "ListaTotalizacao", true))
        {
            value.setListaTotalizacao(new ArrayList());
            for(; consomeStart(xml, "Totalizacao", true); consomeEnd(xml, "Totalizacao"))
                value.getListaTotalizacao().add(readTcTotalizacao(xml));

            consomeEnd(xml, "ListaTotalizacao");
        }
        return value;
    }

    public ProtocoloVO readTcInfProtocolo(XMLStreamReader xml)
        throws XMLStreamException
    {
        ProtocoloVO value = new ProtocoloVO();
        consomeStart(xml, "RaizCnpj");
        value.setRaizCnpj(tiposSimplesParser.readTsRaizCnpj(xml));
        consomeEnd(xml, "RaizCnpj");
        consomeStart(xml, "Nome");
        value.setNome(tiposSimplesParser.readTsRazaoSocial(xml));
        consomeEnd(xml, "Nome");
        consomeStart(xml, "Ano_Mes_Inic_Cmpe");
        value.setAnoMesInicCmpe(tiposSimplesParser.readTsAnoMesInicCmpe(xml));
        consomeEnd(xml, "Ano_Mes_Inic_Cmpe");
        consomeStart(xml, "Tipo_Decl");
        value.setTipoDecl(tiposSimplesParser.readTsTipoDeclaracao(xml));
        consomeEnd(xml, "Tipo_Decl");
        if(consomeStart(xml, "Tipo_Cnso", true))
        {
            value.setTipoCnso(tiposSimplesParser.readTsTipoDeclaracao(xml));
            consomeEnd(xml, "Tipo_Cnso");
        }
        if(consomeStart(xml, "DataEntrega", true))
        {
            value.setDatEntrega(xsdTypesParser.readDateTime(xml));
            ParserHelper.posicionaProximoElemento(xml);
            consomeEnd(xml, "DataEntrega");
        }
        if(consomeStart(xml, "ListaDependencia", true))
        {
            value.setListaDependencia(new ArrayList());
            for(; consomeStart(xml, "Dependencia", true); consomeEnd(xml, "Dependencia"))
                value.getListaDependencia().add(readTcDependencia(xml));

            consomeEnd(xml, "ListaDependencia");
        }
        if(consomeStart(xml, "ListaTotalizacao", true))
        {
            value.setListaTotalizacao(new ArrayList());
            for(; consomeStart(xml, "Totalizacao", true); consomeEnd(xml, "Totalizacao"))
                value.getListaTotalizacao().add(readTcTotalizacao(xml));

            consomeEnd(xml, "ListaTotalizacao");
        }
        return value;
    }

    public void writeTcInfProtocolo(XMLStreamWriter xml, ProtocoloVO value, boolean insereNameSpaceLocal)
        throws XMLStreamException
    {
        if(value.getCodigo() == null)
            throw new XMLStreamException("Tag id do protocolo n\uFFFDo pode ser null");
        tiposSimplesParser.writeTsIdTag(xml, "id", value.getCodigo().toString());
        writeStartElement(xml, "RaizCnpj", false, insereNameSpaceLocal);
        tiposSimplesParser.writeTsRaizCnpj(xml, value.getRaizCnpj());
        writeEndElement(xml);
        writeStartElement(xml, "Nome", false, insereNameSpaceLocal);
        tiposSimplesParser.writeTsRazaoSocial(xml, value.getNome());
        writeEndElement(xml);
        writeStartElement(xml, "Ano_Mes_Inic_Cmpe", false, insereNameSpaceLocal);
        tiposSimplesParser.writeTsAnoMesInicCmpe(xml, value.getAnoMesInicCmpe());
        writeEndElement(xml);
        writeStartElement(xml, "Tipo_Decl", false, insereNameSpaceLocal);
        tiposSimplesParser.writeTsTipoDeclaracao(xml, Short.valueOf(value.getTipoDecl().shortValue()));
        writeEndElement(xml);
        if(!ParserHelper.isEmpty(value.getTipoCnso()))
        {
            writeStartElement(xml, "Tipo_Cnso", false, insereNameSpaceLocal);
            tiposSimplesParser.writeTsTipoConsolidacao(xml, Short.valueOf(value.getTipoCnso().shortValue()));
            writeEndElement(xml);
        }
        if(!ParserHelper.isEmpty(value.getDatEntrega()))
        {
            writeStartElement(xml, "DataEntrega", false, insereNameSpaceLocal);
            xsdTypesParser.writeDateTime(xml, value.getDatEntrega());
            writeEndElement(xml);
        }
        if(!ParserHelper.isEmpty(value.getListaDependencia()))
        {
            writeStartElement(xml, "ListaDependencia", false, insereNameSpaceLocal);
            for(Iterator i$ = value.getListaDependencia().iterator(); i$.hasNext(); writeEndElement(xml))
            {
                DependenciaVO dependencia = (DependenciaVO)i$.next();
                writeStartElement(xml, "Dependencia", false, insereNameSpaceLocal);
                writeTcDependencia(xml, dependencia, false);
            }

            writeEndElement(xml);
        }
        if(!ParserHelper.isEmpty(value.getListaTotalizacao()))
        {
            writeStartElement(xml, "ListaTotalizacao", false, insereNameSpaceLocal);
            for(Iterator i$ = value.getListaTotalizacao().iterator(); i$.hasNext(); writeEndElement(xml))
            {
                TotalizacaoVO totalizacao = (TotalizacaoVO)i$.next();
                writeStartElement(xml, "Totalizacao", false, insereNameSpaceLocal);
                writeTcTotalizacao(xml, totalizacao, false);
            }

            writeEndElement(xml);
        }
    }

    public MensagemRetornoVO readTcMensagemRetorno(XMLStreamReader xml)
        throws XMLStreamException
    {
        MensagemRetornoVO resp = new MensagemRetornoVO();
        if(consomeStart(xml, "Detalhe", true))
        {
            resp.setDetalhe(tiposSimplesParser.readTsDetalheMensagem(xml));
            consomeEnd(xml, "Detalhe");
        }
        consomeStart(xml, "Codigo");
        resp.setCodigo(tiposSimplesParser.readTsCodigoMensagem(xml));
        consomeEnd(xml, "Codigo");
        consomeStart(xml, "Mensagem");
        resp.setMensagem(tiposSimplesParser.readTsDescricaoMensagem(xml));
        consomeEnd(xml, "Mensagem");
        return resp;
    }

    public void writeTcMensagemRetorno(XMLStreamWriter xml, MensagemRetornoVO value, boolean insereNameSpaceLocal)
        throws XMLStreamException
    {
        if(!ParserHelper.isEmpty(value.getDetalhe()))
        {
            writeStartElement(xml, "Detalhe", false, insereNameSpaceLocal);
            tiposSimplesParser.writeTsDetalheMensagem(xml, value.getDetalhe());
            writeEndElement(xml);
        }
        writeStartElement(xml, "Codigo", false, insereNameSpaceLocal);
        tiposSimplesParser.writeTsCodigoMensagem(xml, value.getCodigo());
        writeEndElement(xml);
        writeStartElement(xml, "Mensagem", false, insereNameSpaceLocal);
        tiposSimplesParser.writeTsDescricaoMensagem(xml, value.getMensagem());
        writeEndElement(xml);
    }

}
