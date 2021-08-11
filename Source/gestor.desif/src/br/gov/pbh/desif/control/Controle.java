
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
import java.awt.Frame;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Serializable;
import java.net.ConnectException;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.xml.stream.XMLStreamException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.log4j.Appender;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Layout;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;
import sun.security.pkcs11.wrapper.PKCS11Exception;

public class Controle {
    private ValidaBancoInformacoesComunsMunicipios validaICM;
    private ValidaBancoApuracaoMensalIssqn validaAMI;
    private ValidaBancoModuloContabil validaModContab;
    DadosRelatorio dadosRelatorio = (DadosRelatorio)Contexto.getObject("dadosRelatorio");
    DadosRelatorioInfoComuns dadosRelatorioICM = (DadosRelatorioInfoComuns)Contexto.getObject("dadosRelatorioICM");
    DadosRelatorioContabil dadosRelatorioContabil = (DadosRelatorioContabil)Contexto.getObject("dadosRelatorioContab");

    public void importarArquivo(String caminho) {
        try {
            ImportDesIf importDesIf = (ImportDesIf)Contexto.getObject("importDesif");
            importDesIf.importarArquivo(caminho);
        }
        catch (ExceptionLeituraArquivo ex) {
            SwingUtils.msgErro(null, "Ocorreu um erro na leitura do arquivo");
        }
        catch (ExceptionArquivoInvalido ex) {
            SwingUtils.msgErro(null, "Arquivo invalido");
        }
    }

    public void verificaRegistrosBanco() {
        try {
            if (RegUtil.moduloDeclaracao != null) {
                int mod = Integer.parseInt(RegUtil.moduloDeclaracao);
                switch (mod) {
                    case 1: {
                        this.validaModContab.executar();
                        break;
                    }
                    case 2: {
                        this.validaAMI.executar();
                        break;
                    }
                    case 3: {
                        this.validaICM.executar();
                    }
                }
            }
        }
        catch (Exception e) {
            RegUtil.exErro = true;
            e.printStackTrace();
        }
    }

    public void limparBanco() {
        LimparBanco limparBanco = (LimparBanco)Contexto.getObject("limparBanco");
    }

    public long countSistemaErro() {
        SistemaErroDao sistErroDao = (SistemaErroDao)Contexto.getObject("sistemaErroDao");
        return sistErroDao.countSistemaErro();
    }

    public long countSistemaAlerta() {
        SistemaAlertaDao sistAlertaDao = (SistemaAlertaDao)Contexto.getObject("sistemaAlertaDao");
        return sistAlertaDao.countSistemaAlerta();
    }

    public List buscaSistemaAlerta(double pagina) {
        SistemaAlertaDao sistAlertaDao = (SistemaAlertaDao)Contexto.getObject("sistemaAlertaDao");
        return sistAlertaDao.paginacaoDadosSistemaErro(pagina);
    }

    public List buscaSistemaErro(double pagina) {
        SistemaErroDao sistErroDao = (SistemaErroDao)Contexto.getObject("sistemaErroDao");
        return sistErroDao.paginacaoDadosSistemaErro(pagina);
    }

    public Object buscaErroCodigo(String cod) {
        ErroDao erroDao = (ErroDao)Contexto.getObject("erroDao");
        return erroDao.load((Serializable)((Object)cod));
    }

    public Object buscaAlertaCodigo(String cod) {
        AlertaDao alertaDao = (AlertaDao)Contexto.getObject("alertaDao");
        return alertaDao.load((Serializable)((Object)cod));
    }

    public Object buscaSolucao(String cod) {
        ErroDao erroDao = (ErroDao)Contexto.getObject("erroDao");
        return erroDao.load((Serializable)((Object)cod));
    }

    public Integer numeroGuia(String sequencia) {
        return this.dadosRelatorio.buscaNumeroGuia(sequencia);
    }

    public List buscaDadosDinamicosRelatorioDependencias() {
        return this.dadosRelatorio.buscaDadosDinamicosRelatorioDependencias();
    }

    public List buscaDadosDinamicosRelatorioApurIssqn(String codDependencia) {
        return this.dadosRelatorio.buscaDadosDinamicosRelatorioApurIssqn(codDependencia);
    }

    public List buscaDadosDinamicosRelatorioApurReceitaSubtitulo(String codDependencia, String aliquota) {
        return this.dadosRelatorio.buscaDadosDinamicosRelatorioApurReceitaSubtitulo(codDependencia, aliquota);
    }

    public Object buscaDadosEstaticosRelatorioDependencias() {
        return this.dadosRelatorio.buscaDadosEstaticosRelatorioDependencias();
    }

    public Object buscaDadosEstaticosRelatorioApurIssqn() {
        return this.dadosRelatorio.buscaDadosEstaticosRelatorioApurIssqn();
    }

    public Object buscaDadosEstaticosRelatorioApurIssqn(String codDependencia) {
        return this.dadosRelatorio.buscaDadosEstaticosRelatorioApurIssqn(codDependencia);
    }

    public Object buscaDadosEstaticosRelatorioApurSubtitulo(String codDependencia) {
        return this.dadosRelatorio.buscaDadosEstaticosRelatorioApurSubtitulo(codDependencia);
    }

    public Object buscaDadosGuia(String cnpj) {
        return this.dadosRelatorio.buscaDadosGuia(cnpj);
    }

    public Double buscaTaxaExpediente() {
        return this.dadosRelatorio.buscaTaxaExpediente();
    }

    public Short buscaTipoConsolidacao() {
        return this.dadosRelatorio.buscaTipoConsolidacao();
    }

    public String buscaBaseCnpjInstituicao() {
        return this.dadosRelatorio.buscaBaseCnpjInstituicao();
    }

    public List buscaFiltrosApuracaoIssqn() {
        return this.dadosRelatorio.buscaFiltrosApuracaoIssqn();
    }

    public List buscaFiltrosApuracaoSubtituloCNPJCodDependencia() {
        return this.dadosRelatorio.buscaFiltrosApuracaoSubtituloCNPJCodDependencia();
    }

    public List buscaFiltrosApuracaoSubtituloCodTribAliquota() {
        return this.dadosRelatorio.buscaFiltrosApuracaoSubtituloCodTribAliquota();
    }

    public List buscaFiltrosGuia() {
        return this.dadosRelatorio.buscaFiltrosGuia();
    }

    public Object buscaIdentificacaoDeclaracao() {
        return this.dadosRelatorio.buscaDadosIdentificacaoDeclaracao();
    }

    public int buscaCountIdentificacoDependencia() {
        return this.dadosRelatorio.buscaCountIdentificacoDependencia();
    }

    public List buscaFiltrosContasICM() {
        return this.dadosRelatorioICM.buscaFiltrosContasICM();
    }

    public List buscaFiltrosContaSuperiorPGCC() {
        return this.dadosRelatorioICM.buscaFiltrosContaSuperiorPGCC();
    }

    public List buscaFiltrosContaCosifPGCC() {
        return this.dadosRelatorioICM.buscaFiltrosContaCosifPGCC();
    }

    public List buscaFiltrosCodTributacaoICM() {
        return this.dadosRelatorioICM.buscaFiltrosCodTributacaoICM();
    }

    public List buscaFiltrosDadosGeraisPGCC(String conta, String contaSuperior, String contaCosif, String codTribDesif) {
        return this.dadosRelatorioICM.buscaFiltrosDadosGeraisPGCC(conta, contaSuperior, contaCosif, codTribDesif);
    }

    public List buscaFiltrosDadosGeraisPGCC2(String codTributacao, String conta) {
        return this.dadosRelatorioICM.buscaFiltrosDadosGeraisPGCC2(codTributacao, conta);
    }

    public List buscaFiltrosIdTarifa() {
        return this.dadosRelatorioICM.buscaFiltrosIdTarifa();
    }

    public List buscaFiltrosCodSubtitulo() {
        return this.dadosRelatorioICM.buscaFiltrosCodSubtitulo();
    }

    public List buscaFiltrosDadosGeraisTarServInstituicao(String idTarifa, String codSubtitulo) {
        return this.dadosRelatorioICM.buscaFiltrosDadosGeraisTarServInstituicao(idTarifa, codSubtitulo);
    }

    public List buscaFiltrosIdServRemVar() {
        return this.dadosRelatorioICM.buscaFiltrosIdServRemVar();
    }

    public List buscaFiltrosCodSubtituloRemVar() {
        return this.dadosRelatorioICM.buscaFiltrosCodSubtituloRemVar();
    }

    public List buscaFiltrosDadosGeraisServRemVar(String idServico, String codSubtitulo) {
        return this.dadosRelatorioICM.buscaFiltrosDadosGeraisServRemVar(idServico, codSubtitulo);
    }

    public List buscaFiltrosDependenciasBalancAnalit() {
        return this.dadosRelatorioContabil.buscaFiltrosDependenciasBalancAnalit();
    }

    public List buscaFiltrosContasBalancAnalit() {
        return this.dadosRelatorioContabil.buscaFiltrosContasBalancAnalit();
    }

    public List buscaFiltrosCompetenciaBalancAnalit() {
        return this.dadosRelatorioContabil.buscaFiltrosCompetBalancAnalit();
    }

    public List buscaFiltrosDadosGeraisBalancAnalit(String codDependencia, String competencia, String conta) {
        return this.dadosRelatorioContabil.buscaFiltrosDadosGeraisBalancAnalit(codDependencia, competencia, conta);
    }

    public List buscaFiltrosDependenciasDemRateio() {
        return this.dadosRelatorioContabil.buscaFiltrosDependenciasDemRateio();
    }

    public List buscaFiltrosCompetenciaDemRateio() {
        return this.dadosRelatorioContabil.buscaFiltrosCompetDemRateio();
    }

    public List buscaFiltrosValRateioDemRateio() {
        return this.dadosRelatorioContabil.buscaFiltrosValRateioDemRateio();
    }

    public List buscaFiltrosCodEventoDemRateio() {
        return this.dadosRelatorioContabil.buscaFiltrosCodEventoDemRateio();
    }

    public List buscaFiltrosDadosGeraisDemRateio(String codDependencia, String competencia, String valorRateio, String codEvento) {
        return this.dadosRelatorioContabil.buscaFiltrosDadosGeraisDemRateio(codDependencia, competencia, valorRateio, codEvento);
    }

    public List buscaFiltrosDadosDependencia(String codDependencia) {
        return this.dadosRelatorioContabil.buscaDadosDependencia(codDependencia);
    }

    public List buscaFiltrosDadosDeclaracao() {
        return this.dadosRelatorioContabil.buscaDadosDeclaracao();
    }

    public void gerarRelatorioDependencias(List dataSurce, Object dadosEstaticos) {
        String path = "br/gov/pbh/desif/service/relatorios/VisualizaDependencias.jasper";
        RelatorioEstaticoDependenciaVO relEstatico = (RelatorioEstaticoDependenciaVO)dadosEstaticos;
        Image logo = ImageFactory.getInstance().getImage("bandeiraGuia.PNG").getImage();
        HashMap<String, Object> parametros = new HashMap<String, Object>();
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

    public void gerarRelatorioApuracaoIssqn(List dataSurce, Object dadosEstaticos) {
        HashMap<String, Object> parametros = new HashMap<String, Object>();
        String path2 = "br/gov/pbh/desif/service/relatorios/VisualizaApuracaoIssqn.jasper";
        Image logo = ImageFactory.getInstance().getImage("bandeiraGuia.PNG").getImage();
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

    public void gerarRelatorioApuracaoSubtitulo(List dataSurce, Object dadosEstaticos) {
        HashMap<String, Object> parametros = new HashMap<String, Object>();
        Image logo = ImageFactory.getInstance().getImage("bandeiraGuia.PNG").getImage();
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

    public void gerarRelatorioPGCC(List dataSource, List dadosDecl) {
        HashMap<String, Object> parametros = new HashMap<String, Object>();
        Image logo = ImageFactory.getInstance().getImage("bandeiraGuia.PNG").getImage();
        String path1 = "br/gov/pbh/desif/service/relatorios/RelatorioPGCC.jasper";
        ArrayList dadosDeclaracao = (ArrayList)dadosDecl;
        String cnpj = ((IdentificacaoDeclaracao)dadosDeclaracao.get(0)).getCnpjInstituicao();
        String nomeInstituicao = ((IdentificacaoDeclaracao)dadosDeclaracao.get(0)).getNomeInstituicao();
        JRBeanCollectionDataSource dados = new JRBeanCollectionDataSource((Collection)dataSource);
        parametros.put("cnpj", cnpj);
        parametros.put("nome", nomeInstituicao);
        parametros.put("listaRelatorio", (Object)dados);
        parametros.put("pathSub", "br/gov/pbh/desif/service/relatorios/subRelatorioPGCC.jasper");
        parametros.put("imagem", logo);
        Relatorio relatorio = new Relatorio(parametros, path1, dataSource);
        relatorio.gerarRelatorio();
    }

    public void gerarRelatorioPGCC2(List dataSource, List dadosDecl) {
        HashMap<String, Object> parametros = new HashMap<String, Object>();
        Image logo = ImageFactory.getInstance().getImage("bandeiraGuia.PNG").getImage();
        String path1 = "br/gov/pbh/desif/service/relatorios/relatorioPGCC2.jasper";
        ArrayList dadosDeclaracao = (ArrayList)dadosDecl;
        String cnpj = ((IdentificacaoDeclaracao)dadosDeclaracao.get(0)).getCnpjInstituicao();
        String nomeInstituicao = ((IdentificacaoDeclaracao)dadosDeclaracao.get(0)).getNomeInstituicao();
        JRBeanCollectionDataSource dados = new JRBeanCollectionDataSource((Collection)dataSource);
        parametros.put("cnpj", cnpj);
        parametros.put("nome", nomeInstituicao);
        parametros.put("listaRelatorio", (Object)dados);
        parametros.put("listaRelatorio", (Object)dados);
        parametros.put("pathSub", "br/gov/pbh/desif/service/relatorios/subRelatorioPGCC2.jasper");
        parametros.put("imagem", logo);
        Relatorio relatorio = new Relatorio(parametros, path1, dataSource);
        relatorio.gerarRelatorio();
    }

    public void gerarRelatorioTarServInstituicao(List dataSource, List dadosDecl) {
        HashMap<String, Object> parametros = new HashMap<String, Object>();
        Image logo = ImageFactory.getInstance().getImage("bandeiraGuia.PNG").getImage();
        String path1 = "br/gov/pbh/desif/service/relatorios/RelatorioTarServInstituicao.jasper";
        ArrayList dadosDeclaracao = (ArrayList)dadosDecl;
        String cnpj = ((IdentificacaoDeclaracao)dadosDeclaracao.get(0)).getCnpjInstituicao();
        String nomeInstituicao = ((IdentificacaoDeclaracao)dadosDeclaracao.get(0)).getNomeInstituicao();
        JRBeanCollectionDataSource dados = new JRBeanCollectionDataSource((Collection)dataSource);
        parametros.put("cnpj", cnpj);
        parametros.put("nome", nomeInstituicao);
        parametros.put("listaRelatorio", (Object)dados);
        parametros.put("pathSub", "br/gov/pbh/desif/service/relatorios/subRelatorioTarServInstituicao.jasper");
        parametros.put("imagem", logo);
        Relatorio relatorio = new Relatorio(parametros, path1, dataSource);
        relatorio.gerarRelatorio();
    }

    public void gerarRelatorioServRemVar(List dataSource, List dadosDecl) {
        HashMap<String, Object> parametros = new HashMap<String, Object>();
        Image logo = ImageFactory.getInstance().getImage("bandeiraGuia.PNG").getImage();
        String path1 = "br/gov/pbh/desif/service/relatorios/RelatorioServRemVar.jasper";
        ArrayList dadosDeclaracao = (ArrayList)dadosDecl;
        String cnpj = ((IdentificacaoDeclaracao)dadosDeclaracao.get(0)).getCnpjInstituicao();
        String nomeInstituicao = ((IdentificacaoDeclaracao)dadosDeclaracao.get(0)).getNomeInstituicao();
        JRBeanCollectionDataSource dados = new JRBeanCollectionDataSource((Collection)dataSource);
        parametros.put("cnpj", cnpj);
        parametros.put("nome", nomeInstituicao);
        parametros.put("listaRelatorio", (Object)dados);
        parametros.put("pathSub", "br/gov/pbh/desif/service/relatorios/subRelatorioServRemVar.jasper");
        parametros.put("imagem", logo);
        Relatorio relatorio = new Relatorio(parametros, path1, dataSource);
        relatorio.gerarRelatorio();
    }

    public void gerarRelatorioBalanceteAnalitico(List dataSource, List dadosDep, List dadosDecl) {
        HashMap<String, Object> parametros = new HashMap<String, Object>();
        Image logo = ImageFactory.getInstance().getImage("bandeiraGuia.PNG").getImage();
        String path1 = "br/gov/pbh/desif/service/relatorios/RelatorioBalanceteAnalitico.jasper";
        ArrayList dadosDeclaracao = (ArrayList)dadosDecl;
        ArrayList dadosDependencia = (ArrayList)dadosDep;
        String cnpj = ((IdentificacaoDeclaracao)dadosDeclaracao.get(0)).getCnpjInstituicao();
        String nomeInstituicao = ((IdentificacaoDeclaracao)dadosDeclaracao.get(0)).getNomeInstituicao();
        String codDependencia = null;
        String cnpjProprio = null;
        String enderecoDep = null;
        if (dadosDependencia != null) {
            codDependencia = ((IdentificacaoDependencia)dadosDependencia.get(0)).getCodigoDependencia();
            cnpjProprio = ((IdentificacaoDependencia)dadosDependencia.get(0)).getCnpjProprio();
            enderecoDep = ((IdentificacaoDependencia)dadosDependencia.get(0)).getEnderecoDependencia();
        }
        if (codDependencia == null || codDependencia.isEmpty()) {
            codDependencia = "N\u00e3o se aplica";
        }
        if (cnpjProprio == null || cnpjProprio.isEmpty()) {
            cnpjProprio = "N\u00e3o se aplica";
        }
        if (enderecoDep == null || enderecoDep.isEmpty()) {
            enderecoDep = "N\u00e3o se aplica";
        }
        JRBeanCollectionDataSource dados = new JRBeanCollectionDataSource((Collection)dataSource);
        parametros.put("cnpj", cnpj);
        parametros.put("nome", nomeInstituicao);
        parametros.put("codDepe", codDependencia);
        parametros.put("cnpjProprio", cnpjProprio);
        parametros.put("enderecoDepe", enderecoDep);
        parametros.put("listaRelatorio", (Object)dados);
        parametros.put("pathSub", "br/gov/pbh/desif/service/relatorios/subRelatorioBalanceteAnalitico.jasper");
        parametros.put("imagem", logo);
        Relatorio relatorio = new Relatorio(parametros, path1, dataSource);
        relatorio.gerarRelatorio();
    }

    public void gerarRelatorioDemRateio(List dataSource, List dadosDep, List dadosDecl) {
        HashMap<String, Object> parametros = new HashMap<String, Object>();
        Image logo = ImageFactory.getInstance().getImage("bandeiraGuia.PNG").getImage();
        String path1 = "br/gov/pbh/desif/service/relatorios/RelatorioDemonstrativoRateio.jasper";
        ArrayList dadosDeclaracao = (ArrayList)dadosDecl;
        ArrayList dadosDependencia = (ArrayList)dadosDep;
        String cnpj = ((IdentificacaoDeclaracao)dadosDeclaracao.get(0)).getCnpjInstituicao();
        String nomeInstituicao = ((IdentificacaoDeclaracao)dadosDeclaracao.get(0)).getNomeInstituicao();
        String codDependencia = null;
        String cnpjProprio = null;
        String enderecoDep = null;
        if (dadosDependencia != null) {
            codDependencia = ((IdentificacaoDependencia)dadosDependencia.get(0)).getCodigoDependencia();
            cnpjProprio = ((IdentificacaoDependencia)dadosDependencia.get(0)).getCnpjProprio();
            enderecoDep = ((IdentificacaoDependencia)dadosDependencia.get(0)).getEnderecoDependencia();
        }
        if (codDependencia == null || codDependencia.isEmpty()) {
            codDependencia = "N\u00e3o se aplica";
        }
        if (cnpjProprio == null || cnpjProprio.isEmpty()) {
            cnpjProprio = "N\u00e3o se aplica";
        }
        if (enderecoDep == null || enderecoDep.isEmpty()) {
            enderecoDep = "N\u00e3o se aplica";
        }
        JRBeanCollectionDataSource dados = new JRBeanCollectionDataSource((Collection)dataSource);
        parametros.put("cnpj", cnpj);
        parametros.put("nome", nomeInstituicao);
        parametros.put("codDepe", codDependencia);
        parametros.put("cnpjProprio", cnpjProprio);
        parametros.put("enderecoDepe", enderecoDep);
        parametros.put("listaRelatorio", (Object)dados);
        parametros.put("pathSub", "br/gov/pbh/desif/service/relatorios/subRelatorioDemonstrativoRateio.jasper");
        parametros.put("imagem", logo);
        Relatorio relatorio = new Relatorio(parametros, path1, dataSource);
        relatorio.gerarRelatorio();
    }

    public void gerarRecibo(Object declaracao, int quantDependencias) {
        HashMap<String, Object> parametros = new HashMap<String, Object>();
        Image logo = ImageFactory.getInstance().getImage("bandeiraGuia.PNG").getImage();
        String path = "br/gov/pbh/desif/service/relatorios/ReciboDESIF.jasper";
        IdentificacaoDeclaracao identDeclara = (IdentificacaoDeclaracao)declaracao;
        parametros.put("nomeInstituicao", identDeclara.getNomeInstituicao());
        parametros.put("baseCnpj", identDeclara.getCnpjInstituicao());
        parametros.put("competencia", identDeclara.getDataInicioCompetencia());
        parametros.put("tipoDeclaracao", identDeclara.getTipoDeclaracao());
        parametros.put("quantDep", quantDependencias);
        parametros.put("imagem", logo);
        Relatorio rel = new Relatorio(parametros, path);
        rel.gerarRelatorioSemDataSource();
    }

    public void enviarDeclaracao() 
    {
        block10 : {
            org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger((String)Controle.class.getName());
            try {
                if (!this.ziparArquivos()) {
                    return;
                }
                BasicConfigurator.configure();
                logger.setLevel(org.apache.log4j.Level.ERROR);
                String caminhoDiretorio = System.getProperty("user.home") + File.separator + ".desif";
                String caminhoLog = caminhoDiretorio + File.separator + "log.log";
                RollingFileAppender fileAppender = new RollingFileAppender((Layout)new PatternLayout("%m%n"), caminhoLog);
                fileAppender.setMaxBackupIndex(1);
                fileAppender.setMaxFileSize("100KB");
                logger.addAppender((Appender)fileAppender);
                if (RegUtil.caminhoDiretorioPadrao == null || RegUtil.caminhoDiretorioPadrao.length() == 0) {
                    SwingUtils.msgErro(null, "Antes de enviar a declaracao \u00e9 necess\u00e1rio configurar o diretorio de importa\u00e7\u00e3o de declara\u00e7\u00e3o!\n Entre no menu \"Configura\u00e7\u00f5es\" e selecione um diretorio.");
                    return;
                }
                DadosWs dadosWs = new DadosWs();
                DlgProcessamento dlg = new DlgProcessamento((Frame)new JFrame(), (Thread)dadosWs);
                dlg.setTextoLabel("Enviar declaracao");
                dlg.setTextoProgressBar("Enviando...");
                dlg.setTextoTitulo("Enviando declara\u00e7\u00e3o");
                dlg.iniciar();
            }
            catch (Exception e) {
                e.printStackTrace();
                RegUtil.imprimirErro(e, logger);
                Throwable auxTe = null;
                for (Throwable te = e.getCause(); te != null; te = te.getCause()) {
                    auxTe = te;
                }
                if (auxTe != null) {
                    if (auxTe instanceof ConnectException) {
                        SwingUtils.msgErro(null, "N\u00e3o foi possivel estabelecer conex\u00e3o com o Servidor da PBH!");
                    } else if (auxTe instanceof PKCS11Exception) {
                        SwingUtils.msgErro(null, "Senha incorreta");
                    } else {
                        SwingUtils.msgErro(null, auxTe.getMessage());
                        e.printStackTrace();
                    }
                }
                if (e.getMessage() == null) break block10;
                SwingUtils.msgErro(null, e.getMessage());
            }
        }
    }

    public void assinarDocumento() {
        boolean resp = false;
        ServicoAssinatura serAss = (ServicoAssinatura)Contexto.getObject("serAss");
        try {
            resp = serAss.assinar();
        }
        catch (CancelPasswordException cpe) {
            cpe.printStackTrace();
            SwingUtils.msgErro(null, "Digita\u00e7\u00e3o da senha cancelada!");
            this.escreverLog(cpe);
        }
        catch (InvalidPasswordException ipe) {
            ipe.printStackTrace();
            SwingUtils.msgErro(null, "Senha do certificado inv\u00e1lida!");
            this.escreverLog(ipe);
        }
        catch (GeneralSecurityException gse) {
            gse.printStackTrace();
            if (gse.getMessage().indexOf("A opera\u00e7\u00e3o foi cancelada pelo usu\u00e1rio.") != -1) {
                SwingUtils.msgErro(null, "Digita\u00e7\u00e3o da senha cancelada!");
            } else {
                SwingUtils.msgErro(null, "Dispositivo assinador tokem/smart card n\u00e3o encontrado ou n\u00e3o reconhecido!");
            }
            this.escreverLog(gse);
        }
        catch (NoSelectedCertificateException nsce) {
            nsce.printStackTrace();
            SwingUtils.msgErro(null, "Nenhum certificado selecionado, selecione um certificado para assinatura!");
            this.escreverLog(nsce);
        }
        catch (CryptoDeviceException cde) {
            cde.printStackTrace();
            SwingUtils.msgErro(null, "Aconteceu um erro na comunica\u00e7\u00e3o com o dispositivo assinador!");
            this.escreverLog((Exception)cde);
        }
        catch (FileNotFoundException ioe) {
            ioe.printStackTrace();
            SwingUtils.msgErro(null, "Caminho definido para o certificado A1 n\u00e3o encontrado!");
            this.escreverLog(ioe);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            SwingUtils.msgErro(null, "Erro ao assinar documento!");
            this.escreverLog(ex);
        }
    }

    public void escreverLog(Exception ex) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            org.apache.log4j.Logger logger = null;
            logger = org.apache.log4j.Logger.getLogger((String)Controle.class.getName());
            BasicConfigurator.configure();
            logger.setLevel(org.apache.log4j.Level.ERROR);
            String caminhoDiretorio = System.getProperty("user.home") + File.separator + ".desif";
            String caminhoLog = caminhoDiretorio + File.separator + "log.log";
            RollingFileAppender fileAppender = new RollingFileAppender((Layout)new PatternLayout("%m%n"), caminhoLog);
            fileAppender.setMaxBackupIndex(1);
            fileAppender.setMaxFileSize("100KB");
            fileAppender.setMaximumFileSize(150000L);
            logger.addAppender((Appender)fileAppender);
            if (ex.toString() != null) {
                logger.error((Object)"--------------------------------------Inicio Erro--------------------------------------------------------");
                System.out.println("QWER=>" + sdf.format(GregorianCalendar.getInstance().getTime()));
                logger.error((Object)sdf.format(GregorianCalendar.getInstance().getTime()));
                logger.error((Object)("toStringErro=>" + ex.toString()));
            }
            if (ex.getCause() != null) {
                logger.error((Object)("cause=>" + ex.getCause().toString()));
            }
            if (ex.getLocalizedMessage() != null) {
                logger.error((Object)("lmsg=>" + ex.getLocalizedMessage()));
            }
            if (ex.getMessage() != null) {
                logger.error((Object)("msg=>" + ex.getMessage()));
            }
            if (ex.getStackTrace() != null) {
                for (int i = 0; i < ex.getStackTrace().length; ++i) {
                    StackTraceElement[] ste = ex.getStackTrace();
                    logger.error((Object)("=>" + ste[i]));
                }
            }
            BasicConfigurator.resetConfiguration();
        }
        catch (IOException Exio) {
            Exio.printStackTrace();
        }
    }

    public void escreverMensagemParaUsuarioPegarErro(String mensagem) {
        SwingUtils.msgErro(null, "OCORREU UM ERRO NA ASSINATURA DO ARQUIVO!\n Favor enviar o arquivo log.log que esta na pasta: " + mensagem + "\n para o e-mail desif@pbh.gov.br. \nObrigado!");
    }

    public boolean ziparArquivos() {
        File arqAssinatura;
        boolean resp = true;
        System.out.println("Qual o caminho do arquivo => " + RegUtil.caminhoArquivo);
        File arqZip = new File(RegUtil.caminhoArquivo + ".zip");
        File arqDeclaracao = new File(RegUtil.caminhoArquivo);
        if (!arqDeclaracao.exists()) {
            SwingUtils.msgErro(null, "N\u00e3o existe arquivo da declara\u00e7\u00e3o!");
            resp = false;
        }
        if (!(arqAssinatura = new File(RegUtil.caminhoArquivo + ".p7s")).exists()) {
            SwingUtils.msgErro(null, "N\u00e3o existe arquivo assinado!");
            resp = false;
        }
        File[] vet = new File[]{arqDeclaracao, arqAssinatura};
        ArquivoZip manipuladorZip = new ArquivoZip();
        try {
            manipuladorZip.criarZip(arqZip, vet);
        }
        catch (ZipException ex) {
            resp = false;
            Logger.getLogger(Controle.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex) {
            resp = false;
            Logger.getLogger(Controle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resp;
    }

    public void verificarDocumento(String caminhoArquivo) 
    {
        ServicoVerificarAssinatura serVerAss = (ServicoVerificarAssinatura)Contexto.getObject("serVerAss");
        serVerAss.setCaminhoDeclaracao(caminhoArquivo);
        DlgProcessamento dlg = new DlgProcessamento((Frame)((FrmPrincipal)Contexto.getObject("frmPrincipal")), (Thread)serVerAss);
        dlg.setTextoLabel("Verificar documento");
        dlg.setTextoProgressBar("Verificando...");
        dlg.setTextoTitulo("Verificando documento");
        dlg.iniciar();
    }

    public boolean carregaArquivoProtocolo(String caminho) throws Exception {
        boolean retorno = false;
        CarregarArquivo carregaArq = new CarregarArquivo();
        String arq = carregaArq.lerArquivo(caminho);
        if (arq == null) {
            SwingUtils.msgErro(null, "Ocorreu um erro na leitura do protocolo");
        }
        if (arq != null) {
            try {
                String modulo = this.encontrarModulo(arq, "<modulo>");
                if (modulo.equals("2")) {
                    RelatorioProtocoloAMI relatorioProtocoloAMI = new RelatorioProtocoloAMI();
                    relatorioProtocoloAMI.gerarProtocolo(caminho, "caminho");
                } else if (modulo.equalsIgnoreCase("3")) {
                    RelatorioProtocoloICM relatorioProtocoloICM = new RelatorioProtocoloICM();
                    relatorioProtocoloICM.gerarProtocolo(caminho, "caminho");
                } else if (modulo.equalsIgnoreCase("1")) {
                    RelatorioProtocoloContabil relatorioProtocoloContabil = new RelatorioProtocoloContabil();
                    relatorioProtocoloContabil.gerarProtocolo(caminho, "caminho");
                } else if (modulo.equalsIgnoreCase("vazio")) {
                    RelatorioProtocoloAMIold relatorioProtocoloAMIold = new RelatorioProtocoloAMIold();
                    relatorioProtocoloAMIold.gerarProtocolo(caminho, "caminho");
                }
                retorno = true;
            }
            catch (XMLStreamException ex) {
                ex.printStackTrace();
                retorno = false;
            }
        }
        return retorno;
    }

    private String encontrarModulo(String entrada, String tag) {
        String modulo = "vazio";
        String tagBuscada = tag;
        String tagBuscadaFim = tagBuscada.substring(0, 1) + '/' + tagBuscada.substring(1, tagBuscada.length());
        if (entrada.lastIndexOf(tagBuscada) > 0) {
            modulo = entrada.substring(entrada.lastIndexOf(tagBuscada) + tagBuscada.length(), entrada.lastIndexOf(tagBuscadaFim));
        }
        return modulo;
    }

    public ValidaBancoApuracaoMensalIssqn getValidaAMI() {
        return this.validaAMI;
    }

    public void setValidaAMI(ValidaBancoApuracaoMensalIssqn validaAMI) {
        this.validaAMI = validaAMI;
    }

    public ValidaBancoInformacoesComunsMunicipios getValidaICM() {
        return this.validaICM;
    }

    public void setValidaICM(ValidaBancoInformacoesComunsMunicipios validaICM) {
        this.validaICM = validaICM;
    }

    public ValidaBancoModuloContabil getValidaModContab() {
        return this.validaModContab;
    }

    public void setValidaModContab(ValidaBancoModuloContabil validaModContab) {
        this.validaModContab = validaModContab;
    }
}

