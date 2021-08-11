
package br.gov.pbh.desif.view.telas;

import br.com.esec.version.SDKVersion;
import br.gov.pbh.des.componentes.utils.DesLookandFeel;
import br.gov.pbh.des.componentes.utils.EffectTransparence;
import br.gov.pbh.des.componentes.utils.SwingUtils;
import br.gov.pbh.des.images.ImageFactory;
import br.gov.pbh.desif.control.Controle;
import br.gov.pbh.desif.model.registros.RegUtil;
import br.gov.pbh.desif.service.contexto.Contexto;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import org.jdesktop.layout.GroupLayout;

public class FrmPrincipal extends JFrame 
{
    private final boolean botoesVisible = true;
    private Controle controle;
    private JButton btnAjuda;
    private JButton btnBalanceteAnalitico;
    private JButton btnConfigurar;
    private JButton btnContasAnaliticasPgcc;
    private JButton btnDemonstrativoRateio;
    private JButton btnEmitirRecibo;
    private JButton btnGuia;
    private JButton btnImpDecl;
    private JButton btnPgccCosif;
    private JButton btnProtocolo;
    private JButton btnSair;
    private JButton btnSeparadorVisualizarDeclaracao1;
    private JButton btnSeparadorVisualizarDeclaracao2;
    private JButton btnSeparadorVisualizarDeclaracao3;
    private JButton btnServicosRemVar;
    private JButton btnTarifasServicos;
    private JButton btnVerificarAssinatura;
    private JButton btnVisApurSub;
    private JButton btnVisIssqn;
    private JButton btnVisualizarDependencia;
    private JButton btnVisualizarDependencia1;
    private JLabel jLabel10;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel jLabel9;
    private JPanel jPanel1;
    private JLabel labDesif;
    private JPanel panMenu;
    private JPanel panPrincipal;
    private JPanel pnlMenu;
    private JPanel pnlMenu1;
    private JToolBar tbAMI;
    private JToolBar tbContabil;
    private JToolBar tbICM;
    private JToolBar toolBar;

    public FrmPrincipal()
    {
        this.initComponents();
        System.out.println("version = " + System.getProperty("java.version"));
        this.setTitle("ORION TECNOLOGIAS - Declara\u00e7\u00e3o Eletr\u00f4nica de Servi\u00e7os de Institui\u00e7\u00f5es Financeiras DES-IF 4.2");
        DesLookandFeel.getInstance().formatarJButtons(new JButton[]{this.btnImpDecl, this.btnProtocolo, this.btnVisualizarDependencia, this.btnVisApurSub, this.btnVisIssqn, this.btnGuia, this.btnVerificarAssinatura, this.btnEmitirRecibo});
        this.setBotoes(false);
        SwingUtils.getInstance().centralizar((JFrame)this);
        this.setGuia(false);
        this.btnAjuda.setCursor(Cursor.getPredefinedCursor(12));
        this.btnConfigurar.setCursor(Cursor.getPredefinedCursor(12));
        this.btnSair.setCursor(Cursor.getPredefinedCursor(12));
        this.setIconImage(ImageFactory.getInstance().getImage("icon_des_if.GIF").getImage());
        System.out.println(SDKVersion.getVersion());
    }

    public void setGuia(boolean b)
    {
        this.btnGuia.setVisible(b);
    }

    public void visualizarDependencias() 
    {
        this.controle = (Controle)Contexto.getObject("controle");
        List resp = this.controle.buscaDadosDinamicosRelatorioDependencias();
        Object dadosEstaticos = this.controle.buscaDadosEstaticosRelatorioDependencias();
        this.controle.gerarRelatorioDependencias(resp, dadosEstaticos);
    }

    public void visualizarApurIssqn()
    {
        this.controle = (Controle)Contexto.getObject("controle");
        short tipoConsolidacao = this.controle.buscaTipoConsolidacao();
        if (tipoConsolidacao == 1) 
        {
            List resp = this.controle.buscaDadosDinamicosRelatorioApurIssqn(null);
            Object dadosEstaticos = this.controle.buscaDadosEstaticosRelatorioApurIssqn();
            if (dadosEstaticos == null) 
            {
                br.gov.pbh.desif.view.util.SwingUtils.msgErro(null, "N\u00e3o existe valores de apura\u00e7\u00e3o para esta declara\u00e7\u00e3o");
            } 
            else 
            {
                this.controle.gerarRelatorioApuracaoIssqn(resp, dadosEstaticos);
            }
        }
        else if (tipoConsolidacao == 3) 
        {
            List resp1 = this.controle.buscaFiltrosApuracaoIssqn();
            if (resp1.isEmpty()) 
            {
                br.gov.pbh.desif.view.util.SwingUtils.msgErro(null, "N\u00e3o existe valores de apura\u00e7\u00e3o para esta declara\u00e7\u00e3o");
            }
            else 
            {
                PanFiltroApuracaoIssqn painel = (PanFiltroApuracaoIssqn)Contexto.getObject("panFiltApurIssqn");
                painel.initValoresComboBox(resp1);
                this.abrirTela(painel);
            }
        }
    }

    public void visualizarApurSubTitulo() {
        this.controle = (Controle)Contexto.getObject("controle");
        List resp = this.controle.buscaFiltrosApuracaoSubtituloCNPJCodDependencia();
        List resp1 = this.controle.buscaFiltrosApuracaoSubtituloCodTribAliquota();
        PanFiltroApuracaoSubtitulo painel = (PanFiltroApuracaoSubtitulo)Contexto.getObject("panFiltApurSubtitulo");
        painel.initValoresComboBox(resp, resp1);
        this.abrirTela(painel);
    }

    public void visualizarBalanceteAnalitico() {
        this.controle = (Controle)Contexto.getObject("controle");
        List resp = this.controle.buscaFiltrosDependenciasBalancAnalit();
        List resp2 = this.controle.buscaFiltrosContasBalancAnalit();
        List resp3 = this.controle.buscaFiltrosCompetenciaBalancAnalit();
        PanFiltroBalanceteAnalitico painel = (PanFiltroBalanceteAnalitico)Contexto.getObject("panFiltBalancAnalit");
        painel.initValoresComboBoxDependencias(resp);
        painel.initValoresComboBoxContas(resp2);
        painel.initValoresComboBoxCompetencia(resp3);
        this.abrirTela(painel);
    }

    public void visualizarDemonstrativoRateio() {
        this.controle = (Controle)Contexto.getObject("controle");
        List resp = this.controle.buscaFiltrosDependenciasDemRateio();
        List resp2 = this.controle.buscaFiltrosCompetenciaDemRateio();
        List resp3 = this.controle.buscaFiltrosValRateioDemRateio();
        List resp4 = this.controle.buscaFiltrosCodEventoDemRateio();
        PanFiltroDemonstrativoRateio painel = (PanFiltroDemonstrativoRateio)Contexto.getObject("panFiltDemRateio");
        painel.initValoresComboBoxDependencias(resp);
        painel.initValoresComboBoxCompetencia(resp2);
        painel.initValoresComboBoxValorRateio(resp3);
        painel.initValoresComboBoxCodEvento(resp4);
        this.abrirTela(painel);
    }

    public void visualizarPGCC() {
        this.controle = (Controle)Contexto.getObject("controle");
        List resp = this.controle.buscaFiltrosContasICM();
        List resp1 = this.controle.buscaFiltrosContaSuperiorPGCC();
        List resp2 = this.controle.buscaFiltrosContaCosifPGCC();
        List resp3 = this.controle.buscaFiltrosCodTributacaoICM();
        PanFiltroPGCC painel = (PanFiltroPGCC)Contexto.getObject("panFiltPGCC");
        painel.initValoresComboBoxContas(resp);
        painel.initValoresComboBoxContaSuperior(resp1);
        painel.initValoresComboBoxContaCosif(resp2);
        painel.initValoresComboBoxCodTributacao(resp3);
        this.abrirTela(painel);
    }

    public void visualizarPGCC2() {
        this.controle = (Controle)Contexto.getObject("controle");
        List resp = this.controle.buscaFiltrosDadosGeraisPGCC2("", "");
        List resp2 = this.controle.buscaFiltrosCodTributacaoICM();
        PanFiltroPGCC2 painel = (PanFiltroPGCC2)Contexto.getObject("panFiltPGCC2");
        painel.initValoresComboBoxContas(resp);
        painel.initValoresComboBoxCodTributacao(resp2);
        this.abrirTela(painel);
    }

    public void visualizarTarifServInstituicao() {
        this.controle = (Controle)Contexto.getObject("controle");
        List resp = this.controle.buscaFiltrosIdTarifa();
        List resp2 = this.controle.buscaFiltrosCodSubtitulo();
        PanFiltroTarifasServicosInstituicao painel = (PanFiltroTarifasServicosInstituicao)Contexto.getObject("panFiltTarServInst");
        painel.initValoresComboBoxCodIdTarifa(resp);
        painel.initValoresComboBoxCodSubtitulo(resp2);
        this.abrirTela(painel);
    }

    public void visualizarTarifServRemuVariavel() {
        this.controle = (Controle)Contexto.getObject("controle");
        List resp = this.controle.buscaFiltrosIdServRemVar();
        List resp2 = this.controle.buscaFiltrosCodSubtituloRemVar();
        PanFiltroServRemuVariavel painel = (PanFiltroServRemuVariavel)Contexto.getObject("panFiltServRemVar");
        painel.initValoresComboBoxCodIdServico(resp);
        painel.initValoresComboBoxCodSubtitulo(resp2);
        this.abrirTela(painel);
    }

   

    public void abrirArquivoProtocolo() {
        DialArquivoWS arq = new DialArquivoWS(null, true, "Selecione o protocolo", "XML");
        String caminhoDiretorio = RegUtil.caminhoDiretorioPadrao + File.separator + "protocolo";
        arq.setDiretorioPadrao(caminhoDiretorio);
        arq.setVisible(true);
    }

    public void selecionaPainelModulo(int modulo) {
        if (modulo == 1) {
            this.tbContabil.setVisible(true);
            this.tbAMI.setVisible(false);
            this.tbICM.setVisible(false);
        } else if (modulo == 2) {
            this.tbContabil.setVisible(false);
            this.tbAMI.setVisible(true);
            this.tbICM.setVisible(false);
        } else if (modulo == 3) {
            this.tbContabil.setVisible(false);
            this.tbAMI.setVisible(false);
            this.tbICM.setVisible(true);
        } else if (modulo == 0) {
            this.tbContabil.setVisible(false);
            this.tbAMI.setVisible(false);
            this.tbICM.setVisible(false);
        }
    }

    public void setBotoesVisible(boolean op) {
        this.btnVisIssqn.setVisible(op);
        this.btnVisApurSub.setVisible(op);
        this.btnVisualizarDependencia.setVisible(op);
        this.btnGuia.setVisible(op);
    }

    public void setBotaoImpDecl(boolean b) {
        this.btnImpDecl.setEnabled(b);
    }

    public void setBotoes(boolean op) {
    }

    public void emitirRecibo() {
        this.controle = (Controle)Contexto.getObject("controle");
        Object declaracao = this.controle.buscaIdentificacaoDeclaracao();
        int quantidadeDependecias = this.controle.buscaCountIdentificacoDependencia();
        this.controle.gerarRecibo(declaracao, quantidadeDependecias);
    }

    public void verificarAssinaturaDeclaracao() {
        String caminhoSelecionado = null;
        DialArquivoWS arq = new DialArquivoWS(null, true, "Selecione arquivo", "TXT");
        arq.setVisible(true);
        caminhoSelecionado = arq.getCaminhoArquivoSelecionado();
        if (caminhoSelecionado != null) {
            this.controle = (Controle)Contexto.getObject("controle");
            this.controle.verificarDocumento(caminhoSelecionado);
        }
    }

    private void initComponents() {
        this.jPanel1 = new JPanel();
        this.jLabel3 = new JLabel();
        this.panMenu = new JPanel();
        this.btnAjuda = new JButton();
        this.btnSair = new JButton();
        this.btnConfigurar = new JButton();
        this.pnlMenu = new JPanel();
        this.toolBar = new JToolBar();
        this.btnImpDecl = new JButton();
        this.btnProtocolo = new JButton();
        this.btnVerificarAssinatura = new JButton();
        this.panPrincipal = new JPanel();
        this.labDesif = new JLabel();
        this.pnlMenu1 = new JPanel();
        this.tbAMI = new JToolBar();
        this.jLabel4 = new JLabel();
        this.btnSeparadorVisualizarDeclaracao1 = new JButton();
        this.jLabel6 = new JLabel();
        this.btnVisualizarDependencia = new JButton();
        this.btnVisApurSub = new JButton();
        this.btnVisIssqn = new JButton();
        this.btnGuia = new JButton();
        this.btnEmitirRecibo = new JButton();
        this.tbContabil = new JToolBar();
        this.jLabel9 = new JLabel();
        this.btnSeparadorVisualizarDeclaracao3 = new JButton();
        this.jLabel10 = new JLabel();
        this.btnVisualizarDependencia1 = new JButton();
        this.btnBalanceteAnalitico = new JButton();
        this.btnDemonstrativoRateio = new JButton();
        this.tbICM = new JToolBar();
        this.jLabel7 = new JLabel();
        this.btnSeparadorVisualizarDeclaracao2 = new JButton();
        this.jLabel8 = new JLabel();
        this.btnPgccCosif = new JButton();
        this.btnContasAnaliticasPgcc = new JButton();
        this.btnTarifasServicos = new JButton();
        this.btnServicosRemVar = new JButton();
        this.setDefaultCloseOperation(0);
        this.addWindowListener(new WindowAdapter(){

            @Override
            public void windowClosing(WindowEvent evt) {
                FrmPrincipal.this.formWindowClosing(evt);
            }
        });
        this.jPanel1.setBackground(new Color(255, 255, 255));
        this.jLabel3.setHorizontalAlignment(0);
        this.jLabel3.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/top_des_if.GIF")));
        GroupLayout jPanel1Layout = new GroupLayout((Container)this.jPanel1);
        this.jPanel1.setLayout((LayoutManager)jPanel1Layout);
        jPanel1Layout.setHorizontalGroup((GroupLayout.Group)jPanel1Layout.createParallelGroup(1).add((GroupLayout.Group)jPanel1Layout.createSequentialGroup().addContainerGap().add((Component)this.jLabel3, -2, 498, -2).addContainerGap(266, 32767)));
        jPanel1Layout.setVerticalGroup((GroupLayout.Group)jPanel1Layout.createParallelGroup(1).add((Component)this.jLabel3, -1, 57, 32767));
        this.panMenu.setBackground(new Color(236, 233, 233));
        this.btnAjuda.setBackground(new Color(236, 233, 233));
        this.btnAjuda.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/ajuda.gif")));
        this.btnAjuda.setBorder(BorderFactory.createLineBorder(new Color(236, 233, 233), 2));
        this.btnAjuda.setContentAreaFilled(false);
        this.btnSair.setBackground(new Color(236, 233, 233));
        this.btnSair.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/sair_sistema.gif")));
        this.btnSair.setText(" ");
        this.btnSair.setBorder(BorderFactory.createLineBorder(new Color(236, 233, 233), 2));
        this.btnSair.setContentAreaFilled(false);
        this.btnSair.setName("btnSair1");
        this.btnSair.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                FrmPrincipal.this.btnSairActionPerformed(evt);
            }
        });
        this.btnConfigurar.setBackground(new Color(236, 233, 233));
        this.btnConfigurar.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/configuracoes.gif")));
        this.btnConfigurar.setBorder(BorderFactory.createLineBorder(new Color(236, 233, 233), 2));
        this.btnConfigurar.setContentAreaFilled(false);
        this.btnConfigurar.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                FrmPrincipal.this.btnConfigurarActionPerformed(evt);
            }
        });
        GroupLayout panMenuLayout = new GroupLayout((Container)this.panMenu);
        this.panMenu.setLayout((LayoutManager)panMenuLayout);
        panMenuLayout.setHorizontalGroup((GroupLayout.Group)panMenuLayout.createParallelGroup(1).add(2, (GroupLayout.Group)panMenuLayout.createSequentialGroup().addContainerGap(227, 32767).add((Component)this.btnAjuda).addPreferredGap(0).add((Component)this.btnConfigurar).addPreferredGap(0).add((Component)this.btnSair).add(160, 160, 160)));
        panMenuLayout.setVerticalGroup((GroupLayout.Group)panMenuLayout.createParallelGroup(1).add((Component)this.btnAjuda).add((Component)this.btnConfigurar).add((Component)this.btnSair));
        this.pnlMenu.setMaximumSize(new Dimension(211, 82));
        this.pnlMenu.setMinimumSize(new Dimension(211, 82));
        this.pnlMenu.setOpaque(false);
        this.pnlMenu.setPreferredSize(new Dimension(211, 82));
        this.pnlMenu.setLayout(null);
        this.toolBar.setBackground(new Color(249, 249, 249));
        this.toolBar.setFloatable(false);
        this.toolBar.setOrientation(1);
        this.toolBar.setRollover(true);
        this.toolBar.setDoubleBuffered(true);
        this.toolBar.setMaximumSize(new Dimension(239, 80));
        this.toolBar.setMinimumSize(new Dimension(239, 80));
        this.toolBar.setPreferredSize(new Dimension(239, 80));
        this.toolBar.setRequestFocusEnabled(false);
        this.btnImpDecl.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/bt_importar_declaracao.PNG")));
        this.btnImpDecl.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2));
        this.btnImpDecl.setContentAreaFilled(false);
        this.btnImpDecl.setFocusable(false);
        this.btnImpDecl.setHorizontalTextPosition(0);
        this.btnImpDecl.setMargin(new Insets(2, 20, 2, 14));
        this.btnImpDecl.setMaximumSize(new Dimension(237, 25));
        this.btnImpDecl.setMinimumSize(new Dimension(237, 25));
        this.btnImpDecl.setPreferredSize(new Dimension(237, 25));
        this.btnImpDecl.setVerticalTextPosition(3);
        this.btnImpDecl.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                FrmPrincipal.this.btnImpDeclActionPerformed(evt);
            }
        });
        this.toolBar.add(this.btnImpDecl);
        this.btnProtocolo.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/bt_visualizar_protocolo.png")));
        this.btnProtocolo.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2));
        this.btnProtocolo.setContentAreaFilled(false);
        this.btnProtocolo.setDefaultCapable(true);
        this.btnProtocolo.setFocusable(false);
        this.btnProtocolo.setHorizontalAlignment(2);
        this.btnProtocolo.setHorizontalTextPosition(0);
        this.btnProtocolo.setMargin(new Insets(2, 20, 2, 14));
        this.btnProtocolo.setMaximumSize(new Dimension(237, 25));
        this.btnProtocolo.setMinimumSize(new Dimension(237, 25));
        this.btnProtocolo.setPreferredSize(new Dimension(237, 25));
        this.btnProtocolo.setVerticalTextPosition(3);
        this.btnProtocolo.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                FrmPrincipal.this.btnProtocoloActionPerformed(evt);
            }
        });
        this.toolBar.add(this.btnProtocolo);
        this.btnVerificarAssinatura.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/verificar-assinatura.gif")));
        this.btnVerificarAssinatura.setContentAreaFilled(false);
        this.btnVerificarAssinatura.setFocusable(false);
        this.btnVerificarAssinatura.setHorizontalAlignment(2);
        this.btnVerificarAssinatura.setHorizontalTextPosition(2);
        this.btnVerificarAssinatura.setMargin(new Insets(2, 20, 2, 14));
        this.btnVerificarAssinatura.setMaximumSize(new Dimension(237, 25));
        this.btnVerificarAssinatura.setMinimumSize(new Dimension(237, 25));
        this.btnVerificarAssinatura.setPreferredSize(new Dimension(237, 25));
        this.btnVerificarAssinatura.setVerticalTextPosition(3);
        this.btnVerificarAssinatura.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                FrmPrincipal.this.btnVerificarAssinaturaActionPerformed(evt);
            }
        });
        this.toolBar.add(this.btnVerificarAssinatura);
        this.pnlMenu.add(this.toolBar);
        this.toolBar.setBounds(0, 0, 210, 80);
        this.panPrincipal.setBackground(new Color(255, 255, 255));
        this.panPrincipal.setLayout(new BoxLayout(this.panPrincipal, 2));
        this.panPrincipal.add(this.labDesif);
        this.pnlMenu1.setOpaque(false);
        this.pnlMenu1.setLayout(null);
        this.panPrincipal.add(this.pnlMenu1);
        this.tbAMI.setBackground(new Color(249, 249, 249));
        this.tbAMI.setFloatable(false);
        this.tbAMI.setOrientation(1);
        this.tbAMI.setRollover(true);
        this.tbAMI.setDoubleBuffered(true);
        this.tbAMI.setMaximumSize(new Dimension(239, 340));
        this.tbAMI.setMinimumSize(new Dimension(239, 340));
        this.tbAMI.setVisible(false);
        this.jLabel4.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/linha_branca.gif")));
        this.jLabel4.setText(" ");
        this.jLabel4.setHorizontalTextPosition(0);
        this.jLabel4.setMaximumSize(new Dimension(237, 25));
        this.jLabel4.setMinimumSize(new Dimension(237, 25));
        this.jLabel4.setPreferredSize(new Dimension(237, 25));
        this.jLabel4.setVerticalTextPosition(3);
        this.tbAMI.add(this.jLabel4);
        this.btnSeparadorVisualizarDeclaracao1.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/visualizar_declaracao.gif")));
        this.btnSeparadorVisualizarDeclaracao1.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2));
        this.btnSeparadorVisualizarDeclaracao1.setContentAreaFilled(false);
        this.btnSeparadorVisualizarDeclaracao1.setFocusable(false);
        this.btnSeparadorVisualizarDeclaracao1.setHorizontalTextPosition(0);
        this.btnSeparadorVisualizarDeclaracao1.setIconTextGap(8);
        this.btnSeparadorVisualizarDeclaracao1.setMaximumSize(new Dimension(237, 25));
        this.btnSeparadorVisualizarDeclaracao1.setMinimumSize(new Dimension(237, 25));
        this.btnSeparadorVisualizarDeclaracao1.setPreferredSize(new Dimension(237, 25));
        this.btnSeparadorVisualizarDeclaracao1.setVerticalTextPosition(3);
        this.tbAMI.add(this.btnSeparadorVisualizarDeclaracao1);
        this.jLabel6.setBackground(new Color(249, 249, 249));
        this.jLabel6.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/linha_branca.gif")));
        this.jLabel6.setText(" ");
        this.jLabel6.setHorizontalTextPosition(0);
        this.jLabel6.setMaximumSize(new Dimension(237, 25));
        this.jLabel6.setMinimumSize(new Dimension(237, 25));
        this.jLabel6.setPreferredSize(new Dimension(237, 25));
        this.jLabel6.setVerticalTextPosition(3);
        this.tbAMI.add(this.jLabel6);
        this.btnVisualizarDependencia.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/dependencias.gif")));
        this.btnVisualizarDependencia.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2));
        this.btnVisualizarDependencia.setContentAreaFilled(false);
        this.btnVisualizarDependencia.setFocusable(false);
        this.btnVisualizarDependencia.setHorizontalTextPosition(0);
        this.btnVisualizarDependencia.setMargin(new Insets(2, 20, 2, 14));
        this.btnVisualizarDependencia.setVerticalTextPosition(3);
        this.btnVisualizarDependencia.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                FrmPrincipal.this.btnVisualizarDependenciaActionPerformed(evt);
            }
        });
        this.tbAMI.add(this.btnVisualizarDependencia);
        this.btnVisApurSub.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/apuracao_subtitulo.gif")));
        this.btnVisApurSub.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2));
        this.btnVisApurSub.setContentAreaFilled(false);
        this.btnVisApurSub.setFocusable(false);
        this.btnVisApurSub.setHorizontalTextPosition(0);
        this.btnVisApurSub.setMargin(new Insets(2, 20, 2, 14));
        this.btnVisApurSub.setMaximumSize(new Dimension(237, 25));
        this.btnVisApurSub.setMinimumSize(new Dimension(237, 25));
        this.btnVisApurSub.setPreferredSize(new Dimension(237, 25));
        this.btnVisApurSub.setVerticalTextPosition(3);
        this.btnVisApurSub.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                FrmPrincipal.this.btnVisApurSubActionPerformed(evt);
            }
        });
        this.tbAMI.add(this.btnVisApurSub);
        this.btnVisIssqn.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/apuracao_issqn.gif")));
        this.btnVisIssqn.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2));
        this.btnVisIssqn.setContentAreaFilled(false);
        this.btnVisIssqn.setFocusable(false);
        this.btnVisIssqn.setHorizontalTextPosition(0);
        this.btnVisIssqn.setMargin(new Insets(2, 20, 2, 14));
        this.btnVisIssqn.setMaximumSize(new Dimension(237, 25));
        this.btnVisIssqn.setMinimumSize(new Dimension(237, 25));
        this.btnVisIssqn.setPreferredSize(new Dimension(237, 25));
        this.btnVisIssqn.setVerticalTextPosition(3);
        this.btnVisIssqn.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                FrmPrincipal.this.btnVisIssqnActionPerformed(evt);
            }
        });
        this.tbAMI.add(this.btnVisIssqn);
        this.btnGuia.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/bt_emitir_guia.png")));
        this.btnGuia.setFocusable(false);
        this.btnGuia.setHorizontalAlignment(2);
        this.btnGuia.setHorizontalTextPosition(0);
        this.btnGuia.setMaximumSize(new Dimension(237, 25));
        this.btnGuia.setMinimumSize(new Dimension(237, 25));
        this.btnGuia.setPreferredSize(new Dimension(237, 25));
        this.btnGuia.setVerticalTextPosition(3);
        this.btnGuia.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                FrmPrincipal.this.btnGuiaActionPerformed(evt);
            }
        });
        this.tbAMI.add(this.btnGuia);
        this.btnEmitirRecibo.setBackground(new Color(249, 249, 249));
        this.btnEmitirRecibo.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/bt_emitir_recibo.png")));
        this.btnEmitirRecibo.setFocusable(false);
        this.btnEmitirRecibo.setHorizontalAlignment(2);
        this.btnEmitirRecibo.setHorizontalTextPosition(0);
        this.btnEmitirRecibo.setMargin(new Insets(2, 20, 2, 14));
        this.btnEmitirRecibo.setMaximumSize(new Dimension(237, 25));
        this.btnEmitirRecibo.setMinimumSize(new Dimension(237, 25));
        this.btnEmitirRecibo.setPreferredSize(new Dimension(237, 25));
        this.btnEmitirRecibo.setVerticalTextPosition(3);
        this.btnEmitirRecibo.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                FrmPrincipal.this.btnEmitirReciboActionPerformed(evt);
            }
        });
        this.tbAMI.add(this.btnEmitirRecibo);
        this.btnEmitirRecibo.setVisible(false);
        this.tbContabil.setBackground(new Color(249, 249, 249));
        this.tbContabil.setFloatable(false);
        this.tbContabil.setOrientation(1);
        this.tbContabil.setRollover(true);
        this.tbContabil.setDoubleBuffered(true);
        this.tbContabil.setMaximumSize(new Dimension(239, 320));
        this.tbContabil.setMinimumSize(new Dimension(239, 320));
        this.tbContabil.setPreferredSize(new Dimension(239, 172));
        this.tbContabil.setVisible(false);
        this.jLabel9.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/linha_branca.gif")));
        this.jLabel9.setText(" ");
        this.jLabel9.setHorizontalTextPosition(0);
        this.jLabel9.setMaximumSize(new Dimension(237, 25));
        this.jLabel9.setMinimumSize(new Dimension(237, 25));
        this.jLabel9.setPreferredSize(new Dimension(237, 25));
        this.jLabel9.setVerticalTextPosition(3);
        this.tbContabil.add(this.jLabel9);
        this.btnSeparadorVisualizarDeclaracao3.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/visualizar_declaracao.gif")));
        this.btnSeparadorVisualizarDeclaracao3.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2));
        this.btnSeparadorVisualizarDeclaracao3.setContentAreaFilled(false);
        this.btnSeparadorVisualizarDeclaracao3.setFocusable(false);
        this.btnSeparadorVisualizarDeclaracao3.setHorizontalTextPosition(0);
        this.btnSeparadorVisualizarDeclaracao3.setIconTextGap(8);
        this.btnSeparadorVisualizarDeclaracao3.setMaximumSize(new Dimension(237, 25));
        this.btnSeparadorVisualizarDeclaracao3.setMinimumSize(new Dimension(237, 25));
        this.btnSeparadorVisualizarDeclaracao3.setPreferredSize(new Dimension(237, 25));
        this.btnSeparadorVisualizarDeclaracao3.setVerticalTextPosition(3);
        this.tbContabil.add(this.btnSeparadorVisualizarDeclaracao3);
        this.jLabel10.setBackground(new Color(249, 249, 249));
        this.jLabel10.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/linha_branca.gif")));
        this.jLabel10.setText(" ");
        this.jLabel10.setHorizontalTextPosition(0);
        this.jLabel10.setMaximumSize(new Dimension(237, 25));
        this.jLabel10.setMinimumSize(new Dimension(237, 25));
        this.jLabel10.setPreferredSize(new Dimension(237, 25));
        this.jLabel10.setVerticalTextPosition(3);
        this.tbContabil.add(this.jLabel10);
        this.btnVisualizarDependencia1.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/dependencias.gif")));
        this.btnVisualizarDependencia1.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2));
        this.btnVisualizarDependencia1.setContentAreaFilled(false);
        this.btnVisualizarDependencia1.setFocusable(false);
        this.btnVisualizarDependencia1.setHorizontalTextPosition(0);
        this.btnVisualizarDependencia1.setMargin(new Insets(2, 20, 2, 14));
        this.btnVisualizarDependencia1.setVerticalTextPosition(3);
        this.btnVisualizarDependencia1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                FrmPrincipal.this.btnVisualizarDependencia1ActionPerformed(evt);
            }
        });
        this.tbContabil.add(this.btnVisualizarDependencia1);
        this.btnBalanceteAnalitico.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/balancete_analitico.png")));
        this.btnBalanceteAnalitico.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2));
        this.btnBalanceteAnalitico.setContentAreaFilled(false);
        this.btnBalanceteAnalitico.setFocusable(false);
        this.btnBalanceteAnalitico.setHorizontalAlignment(2);
        this.btnBalanceteAnalitico.setHorizontalTextPosition(0);
        this.btnBalanceteAnalitico.setMargin(new Insets(2, 20, 2, 14));
        this.btnBalanceteAnalitico.setMaximumSize(new Dimension(237, 25));
        this.btnBalanceteAnalitico.setMinimumSize(new Dimension(237, 25));
        this.btnBalanceteAnalitico.setVerticalTextPosition(3);
        this.btnBalanceteAnalitico.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                FrmPrincipal.this.btnBalanceteAnaliticoActionPerformed(evt);
            }
        });
        this.tbContabil.add(this.btnBalanceteAnalitico);
        this.btnDemonstrativoRateio.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/demonstrativo_rateio.png")));
        this.btnDemonstrativoRateio.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2));
        this.btnDemonstrativoRateio.setContentAreaFilled(false);
        this.btnDemonstrativoRateio.setFocusable(false);
        this.btnDemonstrativoRateio.setHorizontalTextPosition(0);
        this.btnDemonstrativoRateio.setMargin(new Insets(2, 20, 2, 14));
        this.btnDemonstrativoRateio.setMaximumSize(new Dimension(237, 25));
        this.btnDemonstrativoRateio.setMinimumSize(new Dimension(237, 25));
        this.btnDemonstrativoRateio.setVerticalTextPosition(3);
        this.btnDemonstrativoRateio.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                FrmPrincipal.this.btnDemonstrativoRateioActionPerformed(evt);
            }
        });
        this.tbContabil.add(this.btnDemonstrativoRateio);
        this.tbICM.setBackground(new Color(249, 249, 249));
        this.tbICM.setFloatable(false);
        this.tbICM.setOrientation(1);
        this.tbICM.setRollover(true);
        this.tbICM.setDoubleBuffered(true);
        this.tbICM.setMaximumSize(new Dimension(239, 340));
        this.tbICM.setMinimumSize(new Dimension(239, 340));
        this.tbICM.setVisible(false);
        this.jLabel7.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/linha_branca.gif")));
        this.jLabel7.setText(" ");
        this.jLabel7.setHorizontalTextPosition(0);
        this.jLabel7.setMaximumSize(new Dimension(237, 25));
        this.jLabel7.setMinimumSize(new Dimension(237, 25));
        this.jLabel7.setPreferredSize(new Dimension(237, 25));
        this.jLabel7.setVerticalTextPosition(3);
        this.tbICM.add(this.jLabel7);
        this.btnSeparadorVisualizarDeclaracao2.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/visualizar_declaracao.gif")));
        this.btnSeparadorVisualizarDeclaracao2.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2));
        this.btnSeparadorVisualizarDeclaracao2.setContentAreaFilled(false);
        this.btnSeparadorVisualizarDeclaracao2.setFocusable(false);
        this.btnSeparadorVisualizarDeclaracao2.setHorizontalTextPosition(0);
        this.btnSeparadorVisualizarDeclaracao2.setIconTextGap(8);
        this.btnSeparadorVisualizarDeclaracao2.setMaximumSize(new Dimension(237, 25));
        this.btnSeparadorVisualizarDeclaracao2.setMinimumSize(new Dimension(237, 25));
        this.btnSeparadorVisualizarDeclaracao2.setPreferredSize(new Dimension(237, 25));
        this.btnSeparadorVisualizarDeclaracao2.setVerticalTextPosition(3);
        this.tbICM.add(this.btnSeparadorVisualizarDeclaracao2);
        this.jLabel8.setBackground(new Color(249, 249, 249));
        this.jLabel8.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/linha_branca.gif")));
        this.jLabel8.setText(" ");
        this.jLabel8.setHorizontalTextPosition(0);
        this.jLabel8.setMaximumSize(new Dimension(237, 25));
        this.jLabel8.setMinimumSize(new Dimension(237, 25));
        this.jLabel8.setPreferredSize(new Dimension(237, 25));
        this.jLabel8.setVerticalTextPosition(3);
        this.tbICM.add(this.jLabel8);
        this.btnPgccCosif.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/pgcc_cosif.png")));
        this.btnPgccCosif.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2));
        this.btnPgccCosif.setContentAreaFilled(false);
        this.btnPgccCosif.setFocusable(false);
        this.btnPgccCosif.setHorizontalAlignment(2);
        this.btnPgccCosif.setHorizontalTextPosition(2);
        this.btnPgccCosif.setMargin(new Insets(2, 20, 2, 14));
        this.btnPgccCosif.setMaximumSize(new Dimension(237, 25));
        this.btnPgccCosif.setMinimumSize(new Dimension(237, 25));
        this.btnPgccCosif.setVerticalTextPosition(3);
        this.btnPgccCosif.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                FrmPrincipal.this.btnPgccCosifActionPerformed(evt);
            }
        });
        this.tbICM.add(this.btnPgccCosif);
        this.btnContasAnaliticasPgcc.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/contas_analiticas.png")));
        this.btnContasAnaliticasPgcc.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2));
        this.btnContasAnaliticasPgcc.setContentAreaFilled(false);
        this.btnContasAnaliticasPgcc.setFocusable(false);
        this.btnContasAnaliticasPgcc.setHorizontalAlignment(2);
        this.btnContasAnaliticasPgcc.setHorizontalTextPosition(2);
        this.btnContasAnaliticasPgcc.setMargin(new Insets(2, 20, 2, 14));
        this.btnContasAnaliticasPgcc.setMaximumSize(new Dimension(237, 19));
        this.btnContasAnaliticasPgcc.setMinimumSize(new Dimension(237, 19));
        this.btnContasAnaliticasPgcc.setVerticalTextPosition(3);
        this.btnContasAnaliticasPgcc.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                FrmPrincipal.this.btnContasAnaliticasPgccActionPerformed(evt);
            }
        });
        this.tbICM.add(this.btnContasAnaliticasPgcc);
        this.btnTarifasServicos.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/tarifas_servicos.png")));
        this.btnTarifasServicos.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2));
        this.btnTarifasServicos.setContentAreaFilled(false);
        this.btnTarifasServicos.setFocusable(false);
        this.btnTarifasServicos.setHorizontalAlignment(2);
        this.btnTarifasServicos.setHorizontalTextPosition(2);
        this.btnTarifasServicos.setMargin(new Insets(2, 20, 2, 14));
        this.btnTarifasServicos.setMaximumSize(new Dimension(237, 25));
        this.btnTarifasServicos.setMinimumSize(new Dimension(237, 25));
        this.btnTarifasServicos.setVerticalTextPosition(3);
        this.btnTarifasServicos.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                FrmPrincipal.this.btnTarifasServicosActionPerformed(evt);
            }
        });
        this.tbICM.add(this.btnTarifasServicos);
        this.btnServicosRemVar.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/servicos_rem_variavel.png")));
        this.btnServicosRemVar.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2));
        this.btnServicosRemVar.setContentAreaFilled(false);
        this.btnServicosRemVar.setFocusable(false);
        this.btnServicosRemVar.setHorizontalAlignment(2);
        this.btnServicosRemVar.setHorizontalTextPosition(2);
        this.btnServicosRemVar.setMargin(new Insets(2, 20, 2, 14));
        this.btnServicosRemVar.setMaximumSize(new Dimension(237, 25));
        this.btnServicosRemVar.setMinimumSize(new Dimension(237, 25));
        this.btnServicosRemVar.setVerticalTextPosition(3);
        this.btnServicosRemVar.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                FrmPrincipal.this.btnServicosRemVarActionPerformed(evt);
            }
        });
        this.tbICM.add(this.btnServicosRemVar);
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout((LayoutManager)layout);
        layout.setHorizontalGroup((GroupLayout.Group)layout.createParallelGroup(1).add((GroupLayout.Group)layout.createSequentialGroup().add((GroupLayout.Group)layout.createParallelGroup(1).add(2, (GroupLayout.Group)layout.createSequentialGroup().addContainerGap().add((GroupLayout.Group)layout.createParallelGroup(2).add(1, (Component)this.panMenu, -1, -1, 32767).add((GroupLayout.Group)layout.createSequentialGroup().add((Component)this.pnlMenu, -1, 211, 32767).addPreferredGap(0).add((Component)this.panPrincipal, -1, 547, 32767)))).add((Component)this.jPanel1, -1, -1, 32767)).addContainerGap()).add((GroupLayout.Group)layout.createParallelGroup(1).add((GroupLayout.Group)layout.createSequentialGroup().addContainerGap().add((Component)this.tbAMI, -2, 210, -2).addContainerGap(564, 32767))).add((GroupLayout.Group)layout.createParallelGroup(1).add((GroupLayout.Group)layout.createSequentialGroup().addContainerGap().add((Component)this.tbICM, -2, 210, -2).add(564, 564, 564))).add((GroupLayout.Group)layout.createParallelGroup(1).add((GroupLayout.Group)layout.createSequentialGroup().addContainerGap().add((Component)this.tbContabil, -2, 210, -2).addContainerGap(564, 32767))));
        layout.setVerticalGroup((GroupLayout.Group)layout.createParallelGroup(1).add((GroupLayout.Group)layout.createSequentialGroup().add((Component)this.jPanel1, -2, -1, -2).addPreferredGap(0).add((Component)this.panMenu, -2, -1, -2).addPreferredGap(0).add((GroupLayout.Group)layout.createParallelGroup(1).add((Component)this.panPrincipal, -2, 388, -2).add((Component)this.pnlMenu, -2, -1, -2)).add(55, 55, 55)).add((GroupLayout.Group)layout.createParallelGroup(1).add((GroupLayout.Group)layout.createSequentialGroup().add(182, 182, 182).add((Component)this.tbAMI, -2, 299, -2).addContainerGap(58, 32767))).add((GroupLayout.Group)layout.createParallelGroup(1).add((GroupLayout.Group)layout.createSequentialGroup().add(179, 179, 179).add((Component)this.tbICM, -2, 177, -2).addContainerGap(183, 32767))).add((GroupLayout.Group)layout.createParallelGroup(1).add((GroupLayout.Group)layout.createSequentialGroup().add(178, 178, 178).add((Component)this.tbContabil, -2, -1, -2).addContainerGap(189, 32767))));
        this.pack();
    }

    private void formWindowClosing(WindowEvent evt) {
        if (br.gov.pbh.desif.view.util.SwingUtils.msgQues(this, "Deseja sair do sistema?") == 0) {
            System.exit(0);
        }
    }

    private void btnSairActionPerformed(ActionEvent evt) {
        if (br.gov.pbh.desif.view.util.SwingUtils.msgQues(this, "Deseja sair da tela Gerar Declara\u00e7\u00e3o ?") == 0) {
            System.exit(0);
        }
    }

    private void btnConfigurarActionPerformed(ActionEvent evt) {
        DialConfiguracao dialConf = (DialConfiguracao)Contexto.getObject("dialConfiguracao");
        dialConf.setVisible(true);
    }

    public JButton setCursorPadrao(JButton botao) {
        botao.setCursor(Cursor.getPredefinedCursor(0));
        ((FrmPrincipal)Contexto.getObject("frmPrincipal")).setCursor(Cursor.getPredefinedCursor(0));
        return botao;
    }

    public JButton setCursorAmpulheta(JButton botao) {
        botao.setCursor(Cursor.getPredefinedCursor(3));
        ((FrmPrincipal)Contexto.getObject("frmPrincipal")).setCursor(Cursor.getPredefinedCursor(3));
        return botao;
    }

    private void btnPgccCosifActionPerformed(ActionEvent evt) {
        this.setCursorAmpulheta(this.btnPgccCosif);
        this.visualizarPGCC();
        this.setCursorPadrao(this.btnPgccCosif);
    }

    private void btnContasAnaliticasPgccActionPerformed(ActionEvent evt) {
        this.setCursorAmpulheta(this.btnContasAnaliticasPgcc);
        this.visualizarPGCC2();
        this.setCursorPadrao(this.btnContasAnaliticasPgcc);
    }

    private void btnTarifasServicosActionPerformed(ActionEvent evt) {
        this.setCursorAmpulheta(this.btnTarifasServicos);
        this.visualizarTarifServInstituicao();
        this.setCursorPadrao(this.btnTarifasServicos);
    }

    private void btnServicosRemVarActionPerformed(ActionEvent evt) {
        this.setCursorAmpulheta(this.btnServicosRemVar);
        this.visualizarTarifServRemuVariavel();
        this.setCursorPadrao(this.btnServicosRemVar);
    }

    private void btnVisualizarDependenciaActionPerformed(ActionEvent evt) {
        this.visualizarDependencias();
    }

    private void btnVisApurSubActionPerformed(ActionEvent evt) {
        this.setCursorAmpulheta(this.btnVisApurSub);
        this.visualizarApurSubTitulo();
        this.setCursorPadrao(this.btnVisApurSub);
    }

    private void btnVisIssqnActionPerformed(ActionEvent evt) {
        this.setCursorAmpulheta(this.btnVisIssqn);
        this.visualizarApurIssqn();
        this.setCursorPadrao(this.btnVisIssqn);
    }

    private void btnGuiaActionPerformed(ActionEvent evt) {
        this.abrirTela((JPanel)Contexto.getObject("panFiltGuia"));
    }

    private void btnEmitirReciboActionPerformed(ActionEvent evt) {
        this.emitirRecibo();
    }

    private void btnBalanceteAnaliticoActionPerformed(ActionEvent evt) {
        this.setCursorAmpulheta(this.btnBalanceteAnalitico);
        this.visualizarBalanceteAnalitico();
        this.setCursorPadrao(this.btnBalanceteAnalitico);
    }

    private void btnDemonstrativoRateioActionPerformed(ActionEvent evt) {
        this.setCursorAmpulheta(this.btnDemonstrativoRateio);
        this.visualizarDemonstrativoRateio();
        this.setCursorPadrao(this.btnDemonstrativoRateio);
    }

    private void btnVisualizarDependencia1ActionPerformed(ActionEvent evt) {
        this.visualizarDependencias();
    }

    private void btnVerificarAssinaturaActionPerformed(ActionEvent evt) {
        this.verificarAssinaturaDeclaracao();
    }

    private void btnProtocoloActionPerformed(ActionEvent evt) {
        this.abrirArquivoProtocolo();
    }

    private void btnImpDeclActionPerformed(ActionEvent evt)
    {
        this.selecionaPainelModulo(0);
        new Thread(){

            @Override
            public void run() 
            {
                FrmPrincipal.this.btnImpDecl.setCursor(Cursor.getPredefinedCursor(3));
                ((FrmPrincipal)Contexto.getObject("frmPrincipal")).setCursor(Cursor.getPredefinedCursor(3));
                FrmPrincipal.this.labDesif.setVisible(false);
                ((PanGerarDeclaracao)Contexto.getObject("panGD")).limparTela();
                if (FrmPrincipal.this.botoesVisible) 
                {
                    FrmPrincipal.this.setBotoes(false);
                }
                FrmPrincipal.this.abrirTela((JPanel)Contexto.getObject("panGD"));
                FrmPrincipal.this.btnImpDecl.setCursor(Cursor.getPredefinedCursor(0));
                ((FrmPrincipal)Contexto.getObject("frmPrincipal")).setCursor(Cursor.getPredefinedCursor(0));
            }
        }.start();
    }
    public void abrirTela(JPanel panel) 
    {
        this.setCursor(Cursor.getPredefinedCursor(3));
        this.panPrincipal.removeAll();
        this.panPrincipal.repaint();
        if (!panel.isVisible()) {
            panel.setVisible(true);
        }
        panel.setBounds(this.panPrincipal.getBounds());
        panel.setLocation(0, 0);
        this.panPrincipal.add(panel);
        final EffectTransparence transparence = new EffectTransparence((JComponent)this.panPrincipal, (JComponent)this.getContentPane());
        this.remove(this.panPrincipal);
        new Thread(){

            @Override
            public void run() {
                FrmPrincipal.this.panPrincipal.setVisible(true);
                transparence.inserir();
                FrmPrincipal.this.panPrincipal.repaint();
                FrmPrincipal.this.panPrincipal.revalidate();
            }
        }.start();
        this.setCursor(Cursor.getPredefinedCursor(0));
    }
}

