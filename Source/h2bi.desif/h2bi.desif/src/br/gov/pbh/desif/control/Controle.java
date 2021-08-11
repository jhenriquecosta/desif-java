package br.gov.pbh.desif.control;

import br.com.esec.sdk.device.CryptoDeviceException;
import br.gov.pbh.bhiss.utilitarios.arquivos.ArquivoZip;
import br.gov.pbh.des.componentes.processamento.DlgProcessamento;
import br.gov.pbh.des.images.ImageFactory;
import br.gov.pbh.desif.dao.AlertaDao;
import br.gov.pbh.desif.dao.ErroDao;
import br.gov.pbh.desif.dao.SistemaAlertaDao;
import br.gov.pbh.desif.dao.SistemaErroDao;
import br.gov.pbh.desif.esec.assinatura.CancelPasswordException;
import br.gov.pbh.desif.esec.assinatura.InvalidPasswordException;
import br.gov.pbh.desif.esec.gui.NoSelectedCertificateException;
import br.gov.pbh.desif.model.pojo.IdentificacaoDeclaracao;
import br.gov.pbh.desif.model.pojo.IdentificacaoDependencia;
import br.gov.pbh.desif.model.pojo.RelatorioEstaticoApuracaoIssqnVO;
import br.gov.pbh.desif.model.pojo.RelatorioEstaticoApuracaoSubtituloVO;
import br.gov.pbh.desif.model.pojo.RelatorioEstaticoDependenciaVO;
import br.gov.pbh.desif.model.registros.RegUtil;
import br.gov.pbh.desif.service.arquivo.CarregarArquivo;
import br.gov.pbh.desif.service.arquivo.ExceptionArquivoInvalido;
import br.gov.pbh.desif.service.arquivo.ExceptionLeituraArquivo;
import br.gov.pbh.desif.service.arquivo.ImportDesIf;
import br.gov.pbh.desif.service.certificacao.ServicoAssinatura;
import br.gov.pbh.desif.service.certificacao.ServicoVerificarAssinatura;
import br.gov.pbh.desif.service.contexto.Contexto;
import br.gov.pbh.desif.service.registrobanco.LimparBanco;
import br.gov.pbh.desif.service.registrobanco.ValidaBancoApuracaoMensalIssqn;
import br.gov.pbh.desif.service.registrobanco.ValidaBancoInformacoesComunsMunicipios;
import br.gov.pbh.desif.service.registrobanco.ValidaBancoModuloContabil;
import br.gov.pbh.desif.service.relatorios.DadosRelatorio;
import br.gov.pbh.desif.service.relatorios.DadosRelatorioContabil;
import br.gov.pbh.desif.service.relatorios.DadosRelatorioInfoComuns;
import br.gov.pbh.desif.service.relatorios.Relatorio;
import br.gov.pbh.desif.service.relatorios.RelatorioProtocoloAMI;
import br.gov.pbh.desif.service.relatorios.RelatorioProtocoloAMIold;
import br.gov.pbh.desif.service.relatorios.RelatorioProtocoloContabil;
import br.gov.pbh.desif.service.relatorios.RelatorioProtocoloICM;
import br.gov.pbh.desif.service.ws.DadosWs;
import br.gov.pbh.desif.view.telas.FrmPrincipal;
import br.gov.pbh.desif.view.util.SwingUtils;
import iaik.pkcs.pkcs11.wrapper.PKCS11Exception;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ConnectException;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipException;
import javax.swing.JFrame;
import javax.xml.stream.XMLStreamException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;
//import sun.security.pkcs11.wrapper.PKCS11Exception;

public class Controle
{

    private ValidaBancoInformacoesComunsMunicipios validaICM;
    private ValidaBancoApuracaoMensalIssqn validaAMI;
    private ValidaBancoModuloContabil validaModContab;
    DadosRelatorio dadosRelatorio;
    DadosRelatorioInfoComuns dadosRelatorioICM;
    DadosRelatorioContabil dadosRelatorioContabil;

    public Controle()
    {
        dadosRelatorio = (DadosRelatorio)Contexto.getObject("dadosRelatorio");
        dadosRelatorioICM = (DadosRelatorioInfoComuns)Contexto.getObject("dadosRelatorioICM");
        dadosRelatorioContabil = (DadosRelatorioContabil)Contexto.getObject("dadosRelatorioContab");
    }

    public void importarArquivo(String caminho)
    {
        try
        {
            ImportDesIf importDesIf = (ImportDesIf)Contexto.getObject("importDesif");
            importDesIf.importarArquivo(caminho);
        }
        catch(ExceptionLeituraArquivo ex)
        {
            SwingUtils.msgErro(null, "Ocorreu um erro na leitura do arquivo");
        }
        catch(ExceptionArquivoInvalido ex)
        {
            SwingUtils.msgErro(null, "Arquivo invalido");
        }
    }

    public void verificaRegistrosBanco()
    {
        try
        {
            if(RegUtil.moduloDeclaracao != null)
            {
                int mod = Integer.parseInt(RegUtil.moduloDeclaracao);
                switch(mod)
                {
                case 1: // '\001'
                    validaModContab.executar();
                    break;

                case 2: // '\002'
                    validaAMI.executar();
                    break;

                case 3: // '\003'
                    validaICM.executar();
                    break;
                }
            }
        }
        catch(Exception e)
        {
            RegUtil.exErro = true;
            e.printStackTrace();
        }
    }

    public void limparBanco()
    {
        LimparBanco limparBanco = (LimparBanco)Contexto.getObject("limparBanco");
    }

    public long countSistemaErro()
    {
        SistemaErroDao sistErroDao = (SistemaErroDao)Contexto.getObject("sistemaErroDao");
        return sistErroDao.countSistemaErro().longValue();
    }

    public long countSistemaAlerta()
    {
        SistemaAlertaDao sistAlertaDao = (SistemaAlertaDao)Contexto.getObject("sistemaAlertaDao");
        return sistAlertaDao.countSistemaAlerta().longValue();
    }

    public List buscaSistemaAlerta(double pagina)
    {
        SistemaAlertaDao sistAlertaDao = (SistemaAlertaDao)Contexto.getObject("sistemaAlertaDao");
        return sistAlertaDao.paginacaoDadosSistemaErro(pagina);
    }

    public List buscaSistemaErro(double pagina)
    {
        SistemaErroDao sistErroDao = (SistemaErroDao)Contexto.getObject("sistemaErroDao");
        return sistErroDao.paginacaoDadosSistemaErro(pagina);
    }

    public Object buscaErroCodigo(String cod)
    {
        ErroDao erroDao = (ErroDao)Contexto.getObject("erroDao");
        return erroDao.load(cod);
    }

    public Object buscaAlertaCodigo(String cod)
    {
        AlertaDao alertaDao = (AlertaDao)Contexto.getObject("alertaDao");
        return alertaDao.load(cod);
    }

    public Object buscaSolucao(String cod)
    {
        ErroDao erroDao = (ErroDao)Contexto.getObject("erroDao");
        return erroDao.load(cod);
    }

    public Integer numeroGuia(String sequencia)
    {
        return dadosRelatorio.buscaNumeroGuia(sequencia);
    }

    public List buscaDadosDinamicosRelatorioDependencias()
    {
        return dadosRelatorio.buscaDadosDinamicosRelatorioDependencias();
    }

    public List buscaDadosDinamicosRelatorioApurIssqn(String codDependencia)
    {
        return dadosRelatorio.buscaDadosDinamicosRelatorioApurIssqn(codDependencia);
    }

    public List buscaDadosDinamicosRelatorioApurReceitaSubtitulo(String codDependencia, String aliquota)
    {
        return dadosRelatorio.buscaDadosDinamicosRelatorioApurReceitaSubtitulo(codDependencia, aliquota);
    }

    public Object buscaDadosEstaticosRelatorioDependencias()
    {
        return dadosRelatorio.buscaDadosEstaticosRelatorioDependencias();
    }

    public Object buscaDadosEstaticosRelatorioApurIssqn()
    {
        return dadosRelatorio.buscaDadosEstaticosRelatorioApurIssqn();
    }

    public Object buscaDadosEstaticosRelatorioApurIssqn(String codDependencia)
    {
        return dadosRelatorio.buscaDadosEstaticosRelatorioApurIssqn(codDependencia);
    }

    public Object buscaDadosEstaticosRelatorioApurSubtitulo(String codDependencia)
    {
        return dadosRelatorio.buscaDadosEstaticosRelatorioApurSubtitulo(codDependencia);
    }

    public Object buscaDadosGuia(String cnpj)
    {
        return dadosRelatorio.buscaDadosGuia(cnpj);
    }

    public Double buscaTaxaExpediente()
    {
        return dadosRelatorio.buscaTaxaExpediente();
    }

    public Short buscaTipoConsolidacao()
    {
        return dadosRelatorio.buscaTipoConsolidacao();
    }

    public String buscaBaseCnpjInstituicao()
    {
        return dadosRelatorio.buscaBaseCnpjInstituicao();
    }

    public List buscaFiltrosApuracaoIssqn()
    {
        return dadosRelatorio.buscaFiltrosApuracaoIssqn();
    }

    public List buscaFiltrosApuracaoSubtituloCNPJCodDependencia()
    {
        return dadosRelatorio.buscaFiltrosApuracaoSubtituloCNPJCodDependencia();
    }

    public List buscaFiltrosApuracaoSubtituloCodTribAliquota()
    {
        return dadosRelatorio.buscaFiltrosApuracaoSubtituloCodTribAliquota();
    }

    public List buscaFiltrosGuia()
    {
        return dadosRelatorio.buscaFiltrosGuia();
    }

    public Object buscaIdentificacaoDeclaracao()
    {
        return dadosRelatorio.buscaDadosIdentificacaoDeclaracao();
    }

    public int buscaCountIdentificacoDependencia()
    {
        return dadosRelatorio.buscaCountIdentificacoDependencia();
    }

    public List buscaFiltrosContasICM()
    {
        return dadosRelatorioICM.buscaFiltrosContasICM();
    }

    public List buscaFiltrosContaSuperiorPGCC()
    {
        return dadosRelatorioICM.buscaFiltrosContaSuperiorPGCC();
    }

    public List buscaFiltrosContaCosifPGCC()
    {
        return dadosRelatorioICM.buscaFiltrosContaCosifPGCC();
    }

    public List buscaFiltrosCodTributacaoICM()
    {
        return dadosRelatorioICM.buscaFiltrosCodTributacaoICM();
    }

    public List buscaFiltrosDadosGeraisPGCC(String conta, String contaSuperior, String contaCosif, String codTribDesif)
    {
        return dadosRelatorioICM.buscaFiltrosDadosGeraisPGCC(conta, contaSuperior, contaCosif, codTribDesif);
    }

    public List buscaFiltrosDadosGeraisPGCC2(String codTributacao, String conta)
    {
        return dadosRelatorioICM.buscaFiltrosDadosGeraisPGCC2(codTributacao, conta);
    }

    public List buscaFiltrosIdTarifa()
    {
        return dadosRelatorioICM.buscaFiltrosIdTarifa();
    }

    public List buscaFiltrosCodSubtitulo()
    {
        return dadosRelatorioICM.buscaFiltrosCodSubtitulo();
    }

    public List buscaFiltrosDadosGeraisTarServInstituicao(String idTarifa, String codSubtitulo)
    {
        return dadosRelatorioICM.buscaFiltrosDadosGeraisTarServInstituicao(idTarifa, codSubtitulo);
    }

    public List buscaFiltrosIdServRemVar()
    {
        return dadosRelatorioICM.buscaFiltrosIdServRemVar();
    }

    public List buscaFiltrosCodSubtituloRemVar()
    {
        return dadosRelatorioICM.buscaFiltrosCodSubtituloRemVar();
    }

    public List buscaFiltrosDadosGeraisServRemVar(String idServico, String codSubtitulo)
    {
        return dadosRelatorioICM.buscaFiltrosDadosGeraisServRemVar(idServico, codSubtitulo);
    }

    public List buscaFiltrosDependenciasBalancAnalit()
    {
        return dadosRelatorioContabil.buscaFiltrosDependenciasBalancAnalit();
    }

    public List buscaFiltrosContasBalancAnalit()
    {
        return dadosRelatorioContabil.buscaFiltrosContasBalancAnalit();
    }

    public List buscaFiltrosCompetenciaBalancAnalit()
    {
        return dadosRelatorioContabil.buscaFiltrosCompetBalancAnalit();
    }

    public List buscaFiltrosDadosGeraisBalancAnalit(String codDependencia, String competencia, String conta)
    {
        return dadosRelatorioContabil.buscaFiltrosDadosGeraisBalancAnalit(codDependencia, competencia, conta);
    }

    public List buscaFiltrosDependenciasDemRateio()
    {
        return dadosRelatorioContabil.buscaFiltrosDependenciasDemRateio();
    }

    public List buscaFiltrosCompetenciaDemRateio()
    {
        return dadosRelatorioContabil.buscaFiltrosCompetDemRateio();
    }

    public List buscaFiltrosValRateioDemRateio()
    {
        return dadosRelatorioContabil.buscaFiltrosValRateioDemRateio();
    }

    public List buscaFiltrosCodEventoDemRateio()
    {
        return dadosRelatorioContabil.buscaFiltrosCodEventoDemRateio();
    }

    public List buscaFiltrosDadosGeraisDemRateio(String codDependencia, String competencia, String valorRateio, String codEvento)
    {
        return dadosRelatorioContabil.buscaFiltrosDadosGeraisDemRateio(codDependencia, competencia, valorRateio, codEvento);
    }

    public List buscaFiltrosDadosDependencia(String codDependencia)
    {
        return dadosRelatorioContabil.buscaDadosDependencia(codDependencia);
    }

    public List buscaFiltrosDadosDeclaracao()
    {
        return dadosRelatorioContabil.buscaDadosDeclaracao();
    }

    public void gerarRelatorioDependencias(List dataSurce, Object dadosEstaticos)
    {
        String path = "br/gov/pbh/desif/service/relatorios/VisualizaDependencias.jasper";
        RelatorioEstaticoDependenciaVO relEstatico = (RelatorioEstaticoDependenciaVO)dadosEstaticos;
        java.awt.Image logo = ImageFactory.getInstance().getImage("bandeiraGuia.PNG").getImage();
        Map parametros = new HashMap();
        parametros.put("razaoSocial", relEstatico.getNomeInstituicao());
        parametros.put("nomeMunicipio", relEstatico.getNomeCidade());
        parametros.put("tipoDeclaracao", relEstatico.getTipoDeclaracao());
        parametros.put("dataCompetencia", relEstatico.getDataInicioCompetencia());
        parametros.put("descTitulo", relEstatico.getDescTitulo());
        parametros.put("protocolo", relEstatico.getProtocoloDeclaracao());
        parametros.put("imagem", logo);
        Relatorio rel = new Relatorio(parametros, path, dataSurce);
        rel.gerarRelatorio();
    }

    public void gerarRelatorioApuracaoIssqn(List dataSurce, Object dadosEstaticos)
    {
        Map parametros = new HashMap();
        String path2 = "br/gov/pbh/desif/service/relatorios/VisualizaApuracaoIssqn.jasper";
        java.awt.Image logo = ImageFactory.getInstance().getImage("bandeiraGuia.PNG").getImage();
        RelatorioEstaticoApuracaoIssqnVO relEstaticoApurIssqn = (RelatorioEstaticoApuracaoIssqnVO)dadosEstaticos;
        parametros.put("nomeMunicipio", relEstaticoApurIssqn.getNomeMunicipio());
        parametros.put("razaoSocial", relEstaticoApurIssqn.getNomeInstituicao());
        parametros.put("iniCnpj", relEstaticoApurIssqn.getIniCNPJ());
        parametros.put("fimCnpj", relEstaticoApurIssqn.getFimCNPJ());
        parametros.put("dataCompetencia", relEstaticoApurIssqn.getDataIniCompetencia());
        parametros.put("tipoConsolidacao", relEstaticoApurIssqn.getTipoConsolidacao());
        parametros.put("imagem", logo);
        Relatorio rel2 = new Relatorio(parametros, path2, dataSurce);
        rel2.gerarRelatorio();
    }

    public void gerarRelatorioApuracaoSubtitulo(List dataSurce, Object dadosEstaticos)
    {
        Map parametros = new HashMap();
        java.awt.Image logo = ImageFactory.getInstance().getImage("bandeiraGuia.PNG").getImage();
        String path1 = "br/gov/pbh/desif/service/relatorios/VisualizaApuracaoSubTitulo.jasper";
        RelatorioEstaticoApuracaoSubtituloVO relEstaticoApurSubtitulo = (RelatorioEstaticoApuracaoSubtituloVO)dadosEstaticos;
        parametros.put("nomeMunicipio", relEstaticoApurSubtitulo.getNomeMunicipio());
        parametros.put("razaoSocial", relEstaticoApurSubtitulo.getNomeInstituicao());
        parametros.put("iniCnpj", relEstaticoApurSubtitulo.getIniCNPJ());
        parametros.put("dataCompetencia", relEstaticoApurSubtitulo.getDataIniCompetencia());
        parametros.put("endereco", relEstaticoApurSubtitulo.getEnderecoDependencia());
        parametros.put("cnpjProprio", relEstaticoApurSubtitulo.getCnpjProprio());
        parametros.put("cnpjUnificado", relEstaticoApurSubtitulo.getCnpjUnificado());
        parametros.put("codDepe", relEstaticoApurSubtitulo.getCodigoDependencia());
        parametros.put("imagem", logo);
        Relatorio rel3 = new Relatorio(parametros, path1, dataSurce);
        rel3.gerarRelatorio();
    }

    public void gerarRelatorioPGCC(List dataSource, List dadosDecl)
    {
        Map parametros = new HashMap();
        java.awt.Image logo = ImageFactory.getInstance().getImage("bandeiraGuia.PNG").getImage();
        String path1 = "br/gov/pbh/desif/service/relatorios/RelatorioPGCC.jasper";
        ArrayList dadosDeclaracao = (ArrayList)dadosDecl;
        String cnpj = ((IdentificacaoDeclaracao)dadosDeclaracao.get(0)).getCnpjInstituicao();
        String nomeInstituicao = ((IdentificacaoDeclaracao)dadosDeclaracao.get(0)).getNomeInstituicao();
        JRBeanCollectionDataSource dados = new JRBeanCollectionDataSource(dataSource);
        parametros.put("cnpj", cnpj);
        parametros.put("nome", nomeInstituicao);
        parametros.put("listaRelatorio", dados);
        parametros.put("pathSub", "br/gov/pbh/desif/service/relatorios/subRelatorioPGCC.jasper");
        parametros.put("imagem", logo);
        Relatorio relatorio = new Relatorio(parametros, path1, dataSource);
        relatorio.gerarRelatorio();
    }

    public void gerarRelatorioPGCC2(List dataSource, List dadosDecl)
    {
        Map parametros = new HashMap();
        java.awt.Image logo = ImageFactory.getInstance().getImage("bandeiraGuia.PNG").getImage();
        String path1 = "br/gov/pbh/desif/service/relatorios/relatorioPGCC2.jasper";
        ArrayList dadosDeclaracao = (ArrayList)dadosDecl;
        String cnpj = ((IdentificacaoDeclaracao)dadosDeclaracao.get(0)).getCnpjInstituicao();
        String nomeInstituicao = ((IdentificacaoDeclaracao)dadosDeclaracao.get(0)).getNomeInstituicao();
        JRBeanCollectionDataSource dados = new JRBeanCollectionDataSource(dataSource);
        parametros.put("cnpj", cnpj);
        parametros.put("nome", nomeInstituicao);
        parametros.put("listaRelatorio", dados);
        parametros.put("listaRelatorio", dados);
        parametros.put("pathSub", "br/gov/pbh/desif/service/relatorios/subRelatorioPGCC2.jasper");
        parametros.put("imagem", logo);
        Relatorio relatorio = new Relatorio(parametros, path1, dataSource);
        relatorio.gerarRelatorio();
    }

    public void gerarRelatorioTarServInstituicao(List dataSource, List dadosDecl)
    {
        Map parametros = new HashMap();
        java.awt.Image logo = ImageFactory.getInstance().getImage("bandeiraGuia.PNG").getImage();
        String path1 = "br/gov/pbh/desif/service/relatorios/RelatorioTarServInstituicao.jasper";
        ArrayList dadosDeclaracao = (ArrayList)dadosDecl;
        String cnpj = ((IdentificacaoDeclaracao)dadosDeclaracao.get(0)).getCnpjInstituicao();
        String nomeInstituicao = ((IdentificacaoDeclaracao)dadosDeclaracao.get(0)).getNomeInstituicao();
        JRBeanCollectionDataSource dados = new JRBeanCollectionDataSource(dataSource);
        parametros.put("cnpj", cnpj);
        parametros.put("nome", nomeInstituicao);
        parametros.put("listaRelatorio", dados);
        parametros.put("pathSub", "br/gov/pbh/desif/service/relatorios/subRelatorioTarServInstituicao.jasper");
        parametros.put("imagem", logo);
        Relatorio relatorio = new Relatorio(parametros, path1, dataSource);
        relatorio.gerarRelatorio();
    }

    public void gerarRelatorioServRemVar(List dataSource, List dadosDecl)
    {
        Map parametros = new HashMap();
        java.awt.Image logo = ImageFactory.getInstance().getImage("bandeiraGuia.PNG").getImage();
        String path1 = "br/gov/pbh/desif/service/relatorios/RelatorioServRemVar.jasper";
        ArrayList dadosDeclaracao = (ArrayList)dadosDecl;
        String cnpj = ((IdentificacaoDeclaracao)dadosDeclaracao.get(0)).getCnpjInstituicao();
        String nomeInstituicao = ((IdentificacaoDeclaracao)dadosDeclaracao.get(0)).getNomeInstituicao();
        JRBeanCollectionDataSource dados = new JRBeanCollectionDataSource(dataSource);
        parametros.put("cnpj", cnpj);
        parametros.put("nome", nomeInstituicao);
        parametros.put("listaRelatorio", dados);
        parametros.put("pathSub", "br/gov/pbh/desif/service/relatorios/subRelatorioServRemVar.jasper");
        parametros.put("imagem", logo);
        Relatorio relatorio = new Relatorio(parametros, path1, dataSource);
        relatorio.gerarRelatorio();
    }

    public void gerarRelatorioBalanceteAnalitico(List dataSource, List dadosDep, List dadosDecl)
    {
        Map parametros = new HashMap();
        java.awt.Image logo = ImageFactory.getInstance().getImage("bandeiraGuia.PNG").getImage();
        String path1 = "br/gov/pbh/desif/service/relatorios/RelatorioBalanceteAnalitico.jasper";
        ArrayList dadosDeclaracao = (ArrayList)dadosDecl;
        ArrayList dadosDependencia = (ArrayList)dadosDep;
        String cnpj = ((IdentificacaoDeclaracao)dadosDeclaracao.get(0)).getCnpjInstituicao();
        String nomeInstituicao = ((IdentificacaoDeclaracao)dadosDeclaracao.get(0)).getNomeInstituicao();
        String codDependencia = null;
        String cnpjProprio = null;
        String enderecoDep = null;
        if(dadosDependencia != null)
        {
            codDependencia = ((IdentificacaoDependencia)dadosDependencia.get(0)).getCodigoDependencia();
            cnpjProprio = ((IdentificacaoDependencia)dadosDependencia.get(0)).getCnpjProprio();
            enderecoDep = ((IdentificacaoDependencia)dadosDependencia.get(0)).getEnderecoDependencia();
        }
        if(codDependencia == null || codDependencia.isEmpty())
            codDependencia = "N\343o se aplica";
        if(cnpjProprio == null || cnpjProprio.isEmpty())
            cnpjProprio = "N\343o se aplica";
        if(enderecoDep == null || enderecoDep.isEmpty())
            enderecoDep = "N\343o se aplica";
        JRBeanCollectionDataSource dados = new JRBeanCollectionDataSource(dataSource);
        parametros.put("cnpj", cnpj);
        parametros.put("nome", nomeInstituicao);
        parametros.put("codDepe", codDependencia);
        parametros.put("cnpjProprio", cnpjProprio);
        parametros.put("enderecoDepe", enderecoDep);
        parametros.put("listaRelatorio", dados);
        parametros.put("pathSub", "br/gov/pbh/desif/service/relatorios/subRelatorioBalanceteAnalitico.jasper");
        parametros.put("imagem", logo);
        Relatorio relatorio = new Relatorio(parametros, path1, dataSource);
        relatorio.gerarRelatorio();
    }

    public void gerarRelatorioDemRateio(List dataSource, List dadosDep, List dadosDecl)
    {
        Map parametros = new HashMap();
        java.awt.Image logo = ImageFactory.getInstance().getImage("bandeiraGuia.PNG").getImage();
        String path1 = "br/gov/pbh/desif/service/relatorios/RelatorioDemonstrativoRateio.jasper";
        ArrayList dadosDeclaracao = (ArrayList)dadosDecl;
        ArrayList dadosDependencia = (ArrayList)dadosDep;
        String cnpj = ((IdentificacaoDeclaracao)dadosDeclaracao.get(0)).getCnpjInstituicao();
        String nomeInstituicao = ((IdentificacaoDeclaracao)dadosDeclaracao.get(0)).getNomeInstituicao();
        String codDependencia = null;
        String cnpjProprio = null;
        String enderecoDep = null;
        if(dadosDependencia != null)
        {
            codDependencia = ((IdentificacaoDependencia)dadosDependencia.get(0)).getCodigoDependencia();
            cnpjProprio = ((IdentificacaoDependencia)dadosDependencia.get(0)).getCnpjProprio();
            enderecoDep = ((IdentificacaoDependencia)dadosDependencia.get(0)).getEnderecoDependencia();
        }
        if(codDependencia == null || codDependencia.isEmpty())
            codDependencia = "N\343o se aplica";
        if(cnpjProprio == null || cnpjProprio.isEmpty())
            cnpjProprio = "N\343o se aplica";
        if(enderecoDep == null || enderecoDep.isEmpty())
            enderecoDep = "N\343o se aplica";
        JRBeanCollectionDataSource dados = new JRBeanCollectionDataSource(dataSource);
        parametros.put("cnpj", cnpj);
        parametros.put("nome", nomeInstituicao);
        parametros.put("codDepe", codDependencia);
        parametros.put("cnpjProprio", cnpjProprio);
        parametros.put("enderecoDepe", enderecoDep);
        parametros.put("listaRelatorio", dados);
        parametros.put("pathSub", "br/gov/pbh/desif/service/relatorios/subRelatorioDemonstrativoRateio.jasper");
        parametros.put("imagem", logo);
        Relatorio relatorio = new Relatorio(parametros, path1, dataSource);
        relatorio.gerarRelatorio();
    }

    public void gerarRecibo(Object declaracao, int quantDependencias)
    {
        Map parametros = new HashMap();
        java.awt.Image logo = ImageFactory.getInstance().getImage("bandeiraGuia.PNG").getImage();
        String path = "br/gov/pbh/desif/service/relatorios/ReciboDESIF.jasper";
        IdentificacaoDeclaracao identDeclara = (IdentificacaoDeclaracao)declaracao;
        parametros.put("nomeInstituicao", identDeclara.getNomeInstituicao());
        parametros.put("baseCnpj", identDeclara.getCnpjInstituicao());
        parametros.put("competencia", identDeclara.getDataInicioCompetencia());
        parametros.put("tipoDeclaracao", identDeclara.getTipoDeclaracao());
        parametros.put("quantDep", Integer.valueOf(quantDependencias));
        parametros.put("imagem", logo);
        Relatorio rel = new Relatorio(parametros, path);
        rel.gerarRelatorioSemDataSource();
    }

    public void enviarDeclaracao()
    {
        try
        {
            Logger logger = Logger.getLogger(br.gov.pbh.desif.control.Controle.class.getName());
            if(!ziparArquivos())
            {
                return;
            }
            BasicConfigurator.configure();
            logger.setLevel(Level.ERROR);
            String caminhoDiretorio = (new StringBuilder()).append(System.getProperty("user.home")).append(File.separator).append(".desif").toString();
            String caminhoLog = (new StringBuilder()).append(caminhoDiretorio).append(File.separator).append("log.log").toString();
            RollingFileAppender fileAppender = new RollingFileAppender(new PatternLayout("%m%n"), caminhoLog);
            fileAppender.setMaxBackupIndex(1);
            fileAppender.setMaxFileSize("100KB");
            logger.addAppender(fileAppender);
            if(RegUtil.caminhoDiretorioPadrao == null || RegUtil.caminhoDiretorioPadrao.length() == 0)
            {
                SwingUtils.msgErro(null, "Antes de enviar a declaracao \351 necess\341rio configurar o diretorio de importa\347\343o de declara\347\343o!\n Entre no menu \"Configura\347\365es\" e selecione um diretorio.");
                return;
            }
            try
            {
                DadosWs dadosWs = new DadosWs();
                DlgProcessamento dlg = new DlgProcessamento(new JFrame(), dadosWs);
                dlg.setTextoLabel("Enviar declaracao");
                dlg.setTextoProgressBar("Enviando...");
                dlg.setTextoTitulo("Enviando declara\347\343o");
                dlg.iniciar();
            }
            catch(Exception e)
            {
                e.printStackTrace();
                Throwable te = e.getCause();
                RegUtil.imprimirErro(e, logger);
                Throwable auxTe = null;
                for(; te != null; te = te.getCause())
                    auxTe = te;

                if(auxTe != null)
                {
                    if(auxTe instanceof ConnectException)
                        SwingUtils.msgErro(null, "N\343o foi possivel estabelecer conex\343o com o Servidor da PBH!");
                    else
                    if(auxTe instanceof PKCS11Exception)
                    {
                        SwingUtils.msgErro(null, "     Senha incorreta");
                    } else
                    {
                        SwingUtils.msgErro(null, auxTe.getMessage());
                        e.printStackTrace();
                    }
                } else
                if(e.getMessage() != null)
                    SwingUtils.msgErro(null, e.getMessage());
            }
        }
         catch(IOException Exio)
        {
            Exio.printStackTrace();
        }
        return;
    }

    public void assinarDocumento()
    {
        boolean resp = false;
        ServicoAssinatura serAss = (ServicoAssinatura)Contexto.getObject("serAss");
        try
        {
            resp = serAss.assinar();
        }
        catch(CancelPasswordException cpe)
        {
            cpe.printStackTrace();
            SwingUtils.msgErro(null, "Digita\347\343o da senha cancelada!");
            escreverLog(cpe);
        }
        catch(InvalidPasswordException ipe)
        {
            ipe.printStackTrace();
            SwingUtils.msgErro(null, "Senha do certificado inv\341lida!");
            escreverLog(ipe);
        }
        catch(GeneralSecurityException gse)
        {
            gse.printStackTrace();
            if(gse.getMessage().indexOf("A opera\347\343o foi cancelada pelo usu\341rio.") != -1)
                SwingUtils.msgErro(null, "Digita\347\343o da senha cancelada!");
            else
                SwingUtils.msgErro(null, "Dispositivo assinador tokem/smart card n\343o encontrado ou n\343o reconhecido!");
            escreverLog(gse);
        }
        catch(NoSelectedCertificateException nsce)
        {
            nsce.printStackTrace();
            SwingUtils.msgErro(null, "Nenhum certificado selecionado, selecione um certificado para assinatura!");
            escreverLog(nsce);
        }
        catch(CryptoDeviceException cde)
        {
            cde.printStackTrace();
            SwingUtils.msgErro(null, "Aconteceu um erro na comunica\347\343o com o dispositivo assinador!");
            escreverLog(cde);
        }
        catch(FileNotFoundException ioe)
        {
            ioe.printStackTrace();
            SwingUtils.msgErro(null, "Caminho definido para o certificado A1 n\343o encontrado!");
            escreverLog(ioe);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            SwingUtils.msgErro(null, "Erro ao assinar documento!");
            escreverLog(ex);
        }
    }

    public void escreverLog(Exception ex)
    {
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Logger logger = null;
            logger = Logger.getLogger(br.gov.pbh.desif.control.Controle.class.getName());
            BasicConfigurator.configure();
            logger.setLevel(Level.ERROR);
            String caminhoDiretorio = (new StringBuilder()).append(System.getProperty("user.home")).append(File.separator).append(".desif").toString();
            String caminhoLog = (new StringBuilder()).append(caminhoDiretorio).append(File.separator).append("log.log").toString();
            RollingFileAppender fileAppender = new RollingFileAppender(new PatternLayout("%m%n"), caminhoLog);
            fileAppender.setMaxBackupIndex(1);
            fileAppender.setMaxFileSize("100KB");
            fileAppender.setMaximumFileSize(0x249f0L);
            logger.addAppender(fileAppender);
            if(ex.toString() != null)
            {
                logger.error("--------------------------------------Inicio Erro--------------------------------------------------------");
                System.out.println((new StringBuilder()).append("QWER=>").append(sdf.format(GregorianCalendar.getInstance().getTime())).toString());
                logger.error(sdf.format(GregorianCalendar.getInstance().getTime()));
                logger.error((new StringBuilder()).append("toStringErro=>").append(ex.toString()).toString());
            }
            if(ex.getCause() != null)
                logger.error((new StringBuilder()).append("cause=>").append(ex.getCause().toString()).toString());
            if(ex.getLocalizedMessage() != null)
                logger.error((new StringBuilder()).append("lmsg=>").append(ex.getLocalizedMessage()).toString());
            if(ex.getMessage() != null)
                logger.error((new StringBuilder()).append("msg=>").append(ex.getMessage()).toString());
            if(ex.getStackTrace() != null)
            {
                for(int i = 0; i < ex.getStackTrace().length; i++)
                {
                    StackTraceElement ste[] = ex.getStackTrace();
                    logger.error((new StringBuilder()).append("=>").append(ste[i]).toString());
                }

            }
            BasicConfigurator.resetConfiguration();
        }
        catch(IOException Exio)
        {
            Exio.printStackTrace();
        }
    }

    public void escreverMensagemParaUsuarioPegarErro(String mensagem)
    {
        SwingUtils.msgErro(null, (new StringBuilder()).append("OCORREU UM ERRO NA ASSINATURA DO ARQUIVO!\n Favor enviar o arquivo log.log que esta na pasta: ").append(mensagem).append("\n para o e-mail desif@pbh.gov.br. \nObrigado!").toString());
    }

    public boolean ziparArquivos()
    {
        boolean resp = true;
        System.out.println((new StringBuilder()).append("Qual o caminho do arquivo => ").append(RegUtil.caminhoArquivo).toString());
        File arqZip = new File((new StringBuilder()).append(RegUtil.caminhoArquivo).append(".zip").toString());
        File arqDeclaracao = new File(RegUtil.caminhoArquivo);
        if(!arqDeclaracao.exists())
        {
            SwingUtils.msgErro(null, "N\343o existe arquivo da declara\347\343o!");
            resp = false;
        }
        File arqAssinatura = new File((new StringBuilder()).append(RegUtil.caminhoArquivo).append(".p7s").toString());
        if(!arqAssinatura.exists())
        {
            SwingUtils.msgErro(null, "N\343o existe arquivo assinado!");
            resp = false;
        }
        File vet[] = {
            arqDeclaracao, arqAssinatura
        };
        ArquivoZip manipuladorZip = new ArquivoZip();
        try
        {
            manipuladorZip.criarZip(arqZip, vet);
        }
        catch(ZipException ex)
        {
            resp = false;
            java.util.logging.Logger.getLogger(br.gov.pbh.desif.control.Controle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch(IOException ex)
        {
            resp = false;
            java.util.logging.Logger.getLogger(br.gov.pbh.desif.control.Controle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return resp;
    }

    public void verificarDocumento(String caminhoArquivo)
    {
        ServicoVerificarAssinatura serVerAss = (ServicoVerificarAssinatura)Contexto.getObject("serVerAss");
        serVerAss.setCaminhoDeclaracao(caminhoArquivo);
        DlgProcessamento dlg = new DlgProcessamento((FrmPrincipal)Contexto.getObject("frmPrincipal"), serVerAss);
        dlg.setTextoLabel("Verificar documento");
        dlg.setTextoProgressBar("Verificando...");
        dlg.setTextoTitulo("Verificando documento");
        dlg.iniciar();
    }

    public boolean carregaArquivoProtocolo(String caminho)
        throws Exception
    {
        boolean retorno = false;
        CarregarArquivo carregaArq = new CarregarArquivo();
        String arq = carregaArq.lerArquivo(caminho);
        if(arq == null)
            SwingUtils.msgErro(null, "Ocorreu um erro na leitura do protocolo");
        if(arq != null)
            try
            {
                String modulo = encontrarModulo(arq, "<modulo>");
                if(modulo.equals("2"))
                {
                    RelatorioProtocoloAMI relatorioProtocoloAMI = new RelatorioProtocoloAMI();
                    relatorioProtocoloAMI.gerarProtocolo(caminho, "caminho");
                } else
                if(modulo.equalsIgnoreCase("3"))
                {
                    RelatorioProtocoloICM relatorioProtocoloICM = new RelatorioProtocoloICM();
                    relatorioProtocoloICM.gerarProtocolo(caminho, "caminho");
                } else
                if(modulo.equalsIgnoreCase("1"))
                {
                    RelatorioProtocoloContabil relatorioProtocoloContabil = new RelatorioProtocoloContabil();
                    relatorioProtocoloContabil.gerarProtocolo(caminho, "caminho");
                } else
                if(modulo.equalsIgnoreCase("vazio"))
                {
                    RelatorioProtocoloAMIold relatorioProtocoloAMIold = new RelatorioProtocoloAMIold();
                    relatorioProtocoloAMIold.gerarProtocolo(caminho, "caminho");
                }
                retorno = true;
            }
            catch(XMLStreamException ex)
            {
                ex.printStackTrace();
                retorno = false;
            }
        return retorno;
    }

    private String encontrarModulo(String entrada, String tag)
    {
        String modulo = "vazio";
        String tagBuscada = tag;
        String tagBuscadaFim = (new StringBuilder()).append(tagBuscada.substring(0, 1)).append('/').append(tagBuscada.substring(1, tagBuscada.length())).toString();
        if(entrada.lastIndexOf(tagBuscada) > 0)
            modulo = entrada.substring(entrada.lastIndexOf(tagBuscada) + tagBuscada.length(), entrada.lastIndexOf(tagBuscadaFim));
        return modulo;
    }

    public ValidaBancoApuracaoMensalIssqn getValidaAMI()
    {
        return validaAMI;
    }

    public void setValidaAMI(ValidaBancoApuracaoMensalIssqn validaAMI)
    {
        this.validaAMI = validaAMI;
    }

    public ValidaBancoInformacoesComunsMunicipios getValidaICM()
    {
        return validaICM;
    }

    public void setValidaICM(ValidaBancoInformacoesComunsMunicipios validaICM)
    {
        this.validaICM = validaICM;
    }

    public ValidaBancoModuloContabil getValidaModContab()
    {
        return validaModContab;
    }

    public void setValidaModContab(ValidaBancoModuloContabil validaModContab)
    {
        this.validaModContab = validaModContab;
    }
}