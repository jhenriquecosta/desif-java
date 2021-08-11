
package br.gov.pbh.desif.view.telas;

import br.gov.pbh.des.componentes.utils.DesLookandFeel;
import br.gov.pbh.des.componentes.utils.EffectTransparence;
import br.gov.pbh.des.componentes.utils.SwingUtils;
import br.gov.pbh.des.images.ImageFactory;
import br.gov.pbh.desif.control.Controle;
import br.gov.pbh.desif.model.registros.RegUtil;
import br.gov.pbh.desif.service.contexto.Contexto;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.PrintStream;
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

    private boolean botoesVisible;
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
        botoesVisible = true;
        initComponents();
        System.out.println((new StringBuilder()).append("version = ").append(System.getProperty("java.version")).toString());
        setTitle("H2BI.DES-IF - Declara\347\343o Eletr\364nica de Servi\347os de Institui\347\365es Financeiras DES-IF  1.00");
        DesLookandFeel.getInstance().formatarJButtons(new JButton[] {
            btnImpDecl, btnProtocolo, btnVisualizarDependencia, btnVisApurSub, btnVisIssqn, btnGuia, btnVerificarAssinatura, btnEmitirRecibo
        });
        setBotoes(false);
        SwingUtils.getInstance().centralizar(this);
        setGuia(false);
        btnAjuda.setCursor(Cursor.getPredefinedCursor(12));
        btnConfigurar.setCursor(Cursor.getPredefinedCursor(12));
        btnSair.setCursor(Cursor.getPredefinedCursor(12));
        setIconImage(ImageFactory.getInstance().getImage("icon_des_if.GIF").getImage());
    }

    public void setGuia(boolean b)
    {
        btnGuia.setVisible(b);
    }

    public void visualizarDependencias()
    {
        controle = (Controle)Contexto.getObject("controle");
        java.util.List resp = controle.buscaDadosDinamicosRelatorioDependencias();
        Object dadosEstaticos = controle.buscaDadosEstaticosRelatorioDependencias();
        controle.gerarRelatorioDependencias(resp, dadosEstaticos);
    }

    public void visualizarApurIssqn()
    {
        controle = (Controle)Contexto.getObject("controle");
        short tipoConsolidacao = controle.buscaTipoConsolidacao().shortValue();
        if(tipoConsolidacao == 1)
        {
            java.util.List resp = controle.buscaDadosDinamicosRelatorioApurIssqn(null);
            Object dadosEstaticos = controle.buscaDadosEstaticosRelatorioApurIssqn();
            if(dadosEstaticos == null)
                br.gov.pbh.desif.view.util.SwingUtils.msgErro(null, "N\343o existe valores de apura\347\343o para esta declara\347\343o");
            else
                controle.gerarRelatorioApuracaoIssqn(resp, dadosEstaticos);
        } else
        if(tipoConsolidacao == 3)
        {
            java.util.List resp1 = controle.buscaFiltrosApuracaoIssqn();
            if(resp1.size() == 0)
            {
                br.gov.pbh.desif.view.util.SwingUtils.msgErro(null, "N\343o existe valores de apura\347\343o para esta declara\347\343o");
            } else
            {
                PanFiltroApuracaoIssqn painel = (PanFiltroApuracaoIssqn)Contexto.getObject("panFiltApurIssqn");
                painel.initValoresComboBox(resp1);
                abrirTela(painel);
            }
        }
    }

    public void visualizarApurSubTitulo()
    {
        controle = (Controle)Contexto.getObject("controle");
        java.util.List resp = controle.buscaFiltrosApuracaoSubtituloCNPJCodDependencia();
        java.util.List resp1 = controle.buscaFiltrosApuracaoSubtituloCodTribAliquota();
        PanFiltroApuracaoSubtitulo painel = (PanFiltroApuracaoSubtitulo)Contexto.getObject("panFiltApurSubtitulo");
        painel.initValoresComboBox(resp, resp1);
        abrirTela(painel);
    }

    public void visualizarBalanceteAnalitico()
    {
        controle = (Controle)Contexto.getObject("controle");
        java.util.List resp = controle.buscaFiltrosDependenciasBalancAnalit();
        java.util.List resp2 = controle.buscaFiltrosContasBalancAnalit();
        java.util.List resp3 = controle.buscaFiltrosCompetenciaBalancAnalit();
        PanFiltroBalanceteAnalitico painel = (PanFiltroBalanceteAnalitico)Contexto.getObject("panFiltBalancAnalit");
        painel.initValoresComboBoxDependencias(resp);
        painel.initValoresComboBoxContas(resp2);
        painel.initValoresComboBoxCompetencia(resp3);
        abrirTela(painel);
    }

    public void visualizarDemonstrativoRateio()
    {
        controle = (Controle)Contexto.getObject("controle");
        java.util.List resp = controle.buscaFiltrosDependenciasDemRateio();
        java.util.List resp2 = controle.buscaFiltrosCompetenciaDemRateio();
        java.util.List resp3 = controle.buscaFiltrosValRateioDemRateio();
        java.util.List resp4 = controle.buscaFiltrosCodEventoDemRateio();
        PanFiltroDemonstrativoRateio painel = (PanFiltroDemonstrativoRateio)Contexto.getObject("panFiltDemRateio");
        painel.initValoresComboBoxDependencias(resp);
        painel.initValoresComboBoxCompetencia(resp2);
        painel.initValoresComboBoxValorRateio(resp3);
        painel.initValoresComboBoxCodEvento(resp4);
        abrirTela(painel);
    }

    public void visualizarPGCC()
    {
        controle = (Controle)Contexto.getObject("controle");
        java.util.List resp = controle.buscaFiltrosContasICM();
        java.util.List resp1 = controle.buscaFiltrosContaSuperiorPGCC();
        java.util.List resp2 = controle.buscaFiltrosContaCosifPGCC();
        java.util.List resp3 = controle.buscaFiltrosCodTributacaoICM();
        PanFiltroPGCC painel = (PanFiltroPGCC)Contexto.getObject("panFiltPGCC");
        painel.initValoresComboBoxContas(resp);
        painel.initValoresComboBoxContaSuperior(resp1);
        painel.initValoresComboBoxContaCosif(resp2);
        painel.initValoresComboBoxCodTributacao(resp3);
        abrirTela(painel);
    }

    public void visualizarPGCC2()
    {
        controle = (Controle)Contexto.getObject("controle");
        java.util.List resp = controle.buscaFiltrosDadosGeraisPGCC2("", "");
        java.util.List resp2 = controle.buscaFiltrosCodTributacaoICM();
        PanFiltroPGCC2 painel = (PanFiltroPGCC2)Contexto.getObject("panFiltPGCC2");
        painel.initValoresComboBoxContas(resp);
        painel.initValoresComboBoxCodTributacao(resp2);
        abrirTela(painel);
    }

    public void visualizarTarifServInstituicao()
    {
        controle = (Controle)Contexto.getObject("controle");
        java.util.List resp = controle.buscaFiltrosIdTarifa();
        java.util.List resp2 = controle.buscaFiltrosCodSubtitulo();
        PanFiltroTarifasServicosInstituicao painel = (PanFiltroTarifasServicosInstituicao)Contexto.getObject("panFiltTarServInst");
        painel.initValoresComboBoxCodIdTarifa(resp);
        painel.initValoresComboBoxCodSubtitulo(resp2);
        abrirTela(painel);
    }

    public void visualizarTarifServRemuVariavel()
    {
        controle = (Controle)Contexto.getObject("controle");
        java.util.List resp = controle.buscaFiltrosIdServRemVar();
        java.util.List resp2 = controle.buscaFiltrosCodSubtituloRemVar();
        PanFiltroServRemuVariavel painel = (PanFiltroServRemuVariavel)Contexto.getObject("panFiltServRemVar");
        painel.initValoresComboBoxCodIdServico(resp);
        painel.initValoresComboBoxCodSubtitulo(resp2);
        abrirTela(painel);
    }



    public void abrirArquivoProtocolo()
    {
        DialArquivoWS arq = new DialArquivoWS(null, true, "Selecione o protocolo", "XML");
        String caminhoDiretorio = (new StringBuilder()).append(RegUtil.caminhoDiretorioPadrao).append(File.separator).append("protocolo").toString();
        arq.setDiretorioPadrao(caminhoDiretorio);
        arq.setVisible(true);
    }

  

    public void setBotoesVisible(boolean op)
    {
        btnVisIssqn.setVisible(op);
        btnVisApurSub.setVisible(op);
        btnVisualizarDependencia.setVisible(op);
        btnGuia.setVisible(op);
    }

    public void setBotaoImpDecl(boolean b)
    {
        btnImpDecl.setEnabled(b);
    }

    public void setBotoes(boolean flag)
    {
    }

    public void emitirRecibo()
    {
        controle = (Controle)Contexto.getObject("controle");
        Object declaracao = controle.buscaIdentificacaoDeclaracao();
        int quantidadeDependecias = controle.buscaCountIdentificacoDependencia();
        controle.gerarRecibo(declaracao, quantidadeDependecias);
    }

    public void verificarAssinaturaDeclaracao()
    {
        String caminhoSelecionado = null;
        DialArquivoWS arq = new DialArquivoWS(null, true, "Selecione arquivo", "TXT");
        arq.setVisible(true);
        caminhoSelecionado = arq.getCaminhoArquivoSelecionado();
        if(caminhoSelecionado != null)
        {
            controle = (Controle)Contexto.getObject("controle");
            controle.verificarDocumento(caminhoSelecionado);
        }
    }

    private void initComponents()
    {
        jPanel1 = new JPanel();
        jLabel3 = new JLabel();
        panMenu = new JPanel();
        btnAjuda = new JButton();
        btnSair = new JButton();
        btnConfigurar = new JButton();
        pnlMenu = new JPanel();
        toolBar = new JToolBar();
        btnImpDecl = new JButton();
        btnProtocolo = new JButton();
        btnVerificarAssinatura = new JButton();
        panPrincipal = new JPanel();
        labDesif = new JLabel();
        pnlMenu1 = new JPanel();
        tbAMI = new JToolBar();
        jLabel4 = new JLabel();
        btnSeparadorVisualizarDeclaracao1 = new JButton();
        jLabel6 = new JLabel();
        btnVisualizarDependencia = new JButton();
        btnVisApurSub = new JButton();
        btnVisIssqn = new JButton();
        btnGuia = new JButton();
        btnEmitirRecibo = new JButton();
        
        tbContabil = new JToolBar();
        jLabel9 = new JLabel();
        btnSeparadorVisualizarDeclaracao3 = new JButton();
        jLabel10 = new JLabel();
        btnVisualizarDependencia1 = new JButton();
        btnBalanceteAnalitico = new JButton();
        btnDemonstrativoRateio = new JButton();
        tbICM = new JToolBar();
        jLabel7 = new JLabel();
        btnSeparadorVisualizarDeclaracao2 = new JButton();
        jLabel8 = new JLabel();
        btnPgccCosif = new JButton();
        btnContasAnaliticasPgcc = new JButton();
        btnTarifasServicos = new JButton();
        btnServicosRemVar = new JButton();
        setDefaultCloseOperation(0);
        addWindowListener(new WindowAdapter() 
        {
            @Override
            public void windowClosing(WindowEvent evt)
            {
                formWindowClosing(evt);
            }
        }
        );
        jPanel1.setBackground(new Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(0);
        jLabel3.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/top_des_if.GIF")));
        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().addContainerGap().add(jLabel3, -2, 498, -2).addContainerGap(266, 32767)));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(1).add(jLabel3, -1, 57, 32767));
        panMenu.setBackground(new Color(236, 233, 233));
        btnAjuda.setBackground(new Color(236, 233, 233));
        btnAjuda.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/ajuda.gif")));
        btnAjuda.setBorder(BorderFactory.createLineBorder(new Color(236, 233, 233), 2));
        btnAjuda.setContentAreaFilled(false);
        btnSair.setBackground(new Color(236, 233, 233));
        btnSair.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/sair_sistema.gif")));
        btnSair.setText(" ");
        btnSair.setBorder(BorderFactory.createLineBorder(new Color(236, 233, 233), 2));
        btnSair.setContentAreaFilled(false);
        btnSair.setName("btnSair1");
        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt)
            {
                btnSairActionPerformed(evt);
            }
        }
        );
        btnConfigurar.setBackground(new Color(236, 233, 233));
        btnConfigurar.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/configuracoes.gif")));
        btnConfigurar.setBorder(BorderFactory.createLineBorder(new Color(236, 233, 233), 2));
        btnConfigurar.setContentAreaFilled(false);
        btnConfigurar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt)
            {
                btnConfigurarActionPerformed(evt);
            }
        } 
        );
        GroupLayout panMenuLayout = new GroupLayout(panMenu);
        panMenu.setLayout(panMenuLayout);
        panMenuLayout.setHorizontalGroup(panMenuLayout.createParallelGroup(1).add(2, panMenuLayout.createSequentialGroup().addContainerGap(227, 32767).add(btnAjuda).addPreferredGap(0).add(btnConfigurar).addPreferredGap(0).add(btnSair).add(160, 160, 160)));
        panMenuLayout.setVerticalGroup(panMenuLayout.createParallelGroup(1).add(btnAjuda).add(btnConfigurar).add(btnSair));
        pnlMenu.setMaximumSize(new Dimension(211, 82));
        pnlMenu.setMinimumSize(new Dimension(211, 82));
        pnlMenu.setOpaque(false);
        pnlMenu.setPreferredSize(new Dimension(211, 82));
        pnlMenu.setLayout(null);
        toolBar.setBackground(new Color(249, 249, 249));
        toolBar.setFloatable(false);
        toolBar.setOrientation(1);
        toolBar.setRollover(true);
        toolBar.setDoubleBuffered(true);
        toolBar.setMaximumSize(new Dimension(239, 80));
        toolBar.setMinimumSize(new Dimension(239, 80));
        toolBar.setPreferredSize(new Dimension(239, 80));
        toolBar.setRequestFocusEnabled(false);
        btnImpDecl.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/bt_importar_declaracao.PNG")));
        btnImpDecl.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2));
        btnImpDecl.setContentAreaFilled(false);
        btnImpDecl.setFocusable(false);
        btnImpDecl.setHorizontalTextPosition(0);
        btnImpDecl.setMargin(new Insets(2, 20, 2, 14));
        btnImpDecl.setMaximumSize(new Dimension(237, 25));
        btnImpDecl.setMinimumSize(new Dimension(237, 25));
        btnImpDecl.setPreferredSize(new Dimension(237, 25));
        btnImpDecl.setVerticalTextPosition(3);
      
        //importa declaracao
        btnImpDecl.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent evt)
            {
                btnImpDeclActionPerformed(evt);
            }
        });
        
        toolBar.add(btnImpDecl);
        btnProtocolo.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/bt_visualizar_protocolo.png")));
        btnProtocolo.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2));
        btnProtocolo.setContentAreaFilled(false);
        btnProtocolo.setDefaultCapable(true);
        btnProtocolo.setFocusable(false);
        btnProtocolo.setHorizontalAlignment(2);
        btnProtocolo.setHorizontalTextPosition(0);
        btnProtocolo.setMargin(new Insets(2, 20, 2, 14));
        btnProtocolo.setMaximumSize(new Dimension(237, 25));
        btnProtocolo.setMinimumSize(new Dimension(237, 25));
        btnProtocolo.setPreferredSize(new Dimension(237, 25));
        btnProtocolo.setVerticalTextPosition(3);
        
        btnProtocolo.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent evt)
            {
                btnProtocoloActionPerformed(evt);
            }
        });
        
        toolBar.add(btnProtocolo);
        btnVerificarAssinatura.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/verificar-assinatura.gif")));
        btnVerificarAssinatura.setContentAreaFilled(false);
        btnVerificarAssinatura.setFocusable(false);
        btnVerificarAssinatura.setHorizontalAlignment(2);
        btnVerificarAssinatura.setHorizontalTextPosition(2);
        btnVerificarAssinatura.setMargin(new Insets(2, 20, 2, 14));
        btnVerificarAssinatura.setMaximumSize(new Dimension(237, 25));
        btnVerificarAssinatura.setMinimumSize(new Dimension(237, 25));
        btnVerificarAssinatura.setPreferredSize(new Dimension(237, 25));
        btnVerificarAssinatura.setVerticalTextPosition(3);
        btnVerificarAssinatura.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt)
            {
                btnVerificarAssinaturaActionPerformed(evt);
            }
        }
        );
        toolBar.add(btnVerificarAssinatura);
        pnlMenu.add(toolBar);
        toolBar.setBounds(0, 0, 210, 80);
        panPrincipal.setBackground(new Color(255, 255, 255));
        panPrincipal.setLayout(new BoxLayout(panPrincipal, 2));
        panPrincipal.add(labDesif);
        pnlMenu1.setOpaque(false);
        pnlMenu1.setLayout(null);
        panPrincipal.add(pnlMenu1);
        
        tbAMI.setBackground(new Color(249, 249, 249));
        tbAMI.setFloatable(false);
        tbAMI.setOrientation(1);
        tbAMI.setRollover(true);
        tbAMI.setDoubleBuffered(true);
        tbAMI.setMaximumSize(new Dimension(239, 340));
        tbAMI.setMinimumSize(new Dimension(239, 340));
        tbAMI.setVisible(false);
        
        jLabel4.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/linha_branca.gif")));
        jLabel4.setText(" ");
        jLabel4.setHorizontalTextPosition(0);
        jLabel4.setMaximumSize(new Dimension(237, 25));
        jLabel4.setMinimumSize(new Dimension(237, 25));
        jLabel4.setPreferredSize(new Dimension(237, 25));
        jLabel4.setVerticalTextPosition(3);
        tbAMI.add(jLabel4);
        
        btnSeparadorVisualizarDeclaracao1.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/visualizar_declaracao.gif")));
        btnSeparadorVisualizarDeclaracao1.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2));
        btnSeparadorVisualizarDeclaracao1.setContentAreaFilled(false);
        btnSeparadorVisualizarDeclaracao1.setFocusable(false);
        btnSeparadorVisualizarDeclaracao1.setHorizontalTextPosition(0);
        btnSeparadorVisualizarDeclaracao1.setIconTextGap(8);
        btnSeparadorVisualizarDeclaracao1.setMaximumSize(new Dimension(237, 25));
        btnSeparadorVisualizarDeclaracao1.setMinimumSize(new Dimension(237, 25));
        btnSeparadorVisualizarDeclaracao1.setPreferredSize(new Dimension(237, 25));
        btnSeparadorVisualizarDeclaracao1.setVerticalTextPosition(3);
        
        tbAMI.add(btnSeparadorVisualizarDeclaracao1);
        jLabel6.setBackground(new Color(249, 249, 249));
        jLabel6.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/linha_branca.gif")));
        jLabel6.setText(" ");
        jLabel6.setHorizontalTextPosition(0);
        jLabel6.setMaximumSize(new Dimension(237, 25));
        jLabel6.setMinimumSize(new Dimension(237, 25));
        jLabel6.setPreferredSize(new Dimension(237, 25));
        jLabel6.setVerticalTextPosition(3);
        tbAMI.add(jLabel6);
        btnVisualizarDependencia.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/dependencias.gif")));
        btnVisualizarDependencia.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2));
        btnVisualizarDependencia.setContentAreaFilled(false);
        btnVisualizarDependencia.setFocusable(false);
        btnVisualizarDependencia.setHorizontalTextPosition(0);
        btnVisualizarDependencia.setMargin(new Insets(2, 20, 2, 14));
        btnVisualizarDependencia.setVerticalTextPosition(3);
        btnVisualizarDependencia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt)
            {
                btnVisualizarDependenciaActionPerformed(evt);
            }
        }
        );
        tbAMI.add(btnVisualizarDependencia);
        btnVisApurSub.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/apuracao_subtitulo.gif")));
        btnVisApurSub.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2));
        btnVisApurSub.setContentAreaFilled(false);
        btnVisApurSub.setFocusable(false);
        btnVisApurSub.setHorizontalTextPosition(0);
        btnVisApurSub.setMargin(new Insets(2, 20, 2, 14));
        btnVisApurSub.setMaximumSize(new Dimension(237, 25));
        btnVisApurSub.setMinimumSize(new Dimension(237, 25));
        btnVisApurSub.setPreferredSize(new Dimension(237, 25));
        btnVisApurSub.setVerticalTextPosition(3);
        btnVisApurSub.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt)
            {
                btnVisApurSubActionPerformed(evt);
            }
        }
        );
        tbAMI.add(btnVisApurSub);
        btnVisIssqn.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/apuracao_issqn.gif")));
        btnVisIssqn.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2));
        btnVisIssqn.setContentAreaFilled(false);
        btnVisIssqn.setFocusable(false);
        btnVisIssqn.setHorizontalTextPosition(0);
        btnVisIssqn.setMargin(new Insets(2, 20, 2, 14));
        btnVisIssqn.setMaximumSize(new Dimension(237, 25));
        btnVisIssqn.setMinimumSize(new Dimension(237, 25));
        btnVisIssqn.setPreferredSize(new Dimension(237, 25));
        btnVisIssqn.setVerticalTextPosition(3);
        btnVisIssqn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt)
            {
                btnVisIssqnActionPerformed(evt);
            }
        }
        );
        tbAMI.add(btnVisIssqn);
        btnGuia.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/bt_emitir_guia.png")));
        btnGuia.setFocusable(false);
        btnGuia.setHorizontalAlignment(2);
        btnGuia.setHorizontalTextPosition(0);
        btnGuia.setMaximumSize(new Dimension(237, 25));
        btnGuia.setMinimumSize(new Dimension(237, 25));
        btnGuia.setPreferredSize(new Dimension(237, 25));
        btnGuia.setVerticalTextPosition(3);
        btnGuia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt)
            {
                btnGuiaActionPerformed(evt);
            }
        }
        );
        tbAMI.add(btnGuia);
        btnEmitirRecibo.setBackground(new Color(249, 249, 249));
        btnEmitirRecibo.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/bt_emitir_recibo.png")));
        btnEmitirRecibo.setFocusable(false);
        btnEmitirRecibo.setHorizontalAlignment(2);
        btnEmitirRecibo.setHorizontalTextPosition(0);
        btnEmitirRecibo.setMargin(new Insets(2, 20, 2, 14));
        btnEmitirRecibo.setMaximumSize(new Dimension(237, 25));
        btnEmitirRecibo.setMinimumSize(new Dimension(237, 25));
        btnEmitirRecibo.setPreferredSize(new Dimension(237, 25));
        btnEmitirRecibo.setVerticalTextPosition(3);
        btnEmitirRecibo.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent evt)
            {
                btnEmitirReciboActionPerformed(evt);
            }
        }
        );
        tbAMI.add(btnEmitirRecibo);
        btnEmitirRecibo.setVisible(true);
        
        
        tbContabil.setBackground(new Color(249, 249, 249));
        tbContabil.setFloatable(false);
        tbContabil.setOrientation(1);
        tbContabil.setRollover(true);
        tbContabil.setDoubleBuffered(true);
        tbContabil.setMaximumSize(new Dimension(239, 320));
        tbContabil.setMinimumSize(new Dimension(239, 320));
        tbContabil.setPreferredSize(new Dimension(239, 172));
        tbContabil.setVisible(false);
        
        jLabel9.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/linha_branca.gif")));
        jLabel9.setText(" ");
        jLabel9.setHorizontalTextPosition(0);
        jLabel9.setMaximumSize(new Dimension(237, 25));
        jLabel9.setMinimumSize(new Dimension(237, 25));
        jLabel9.setPreferredSize(new Dimension(237, 25));
        jLabel9.setVerticalTextPosition(3);
        tbContabil.add(jLabel9);
        btnSeparadorVisualizarDeclaracao3.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/visualizar_declaracao.gif")));
        btnSeparadorVisualizarDeclaracao3.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2));
        btnSeparadorVisualizarDeclaracao3.setContentAreaFilled(false);
        btnSeparadorVisualizarDeclaracao3.setFocusable(false);
        btnSeparadorVisualizarDeclaracao3.setHorizontalTextPosition(0);
        btnSeparadorVisualizarDeclaracao3.setIconTextGap(8);
        btnSeparadorVisualizarDeclaracao3.setMaximumSize(new Dimension(237, 25));
        btnSeparadorVisualizarDeclaracao3.setMinimumSize(new Dimension(237, 25));
        btnSeparadorVisualizarDeclaracao3.setPreferredSize(new Dimension(237, 25));
        btnSeparadorVisualizarDeclaracao3.setVerticalTextPosition(3);
        tbContabil.add(btnSeparadorVisualizarDeclaracao3);
        jLabel10.setBackground(new Color(249, 249, 249));
        jLabel10.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/linha_branca.gif")));
        jLabel10.setText(" ");
        jLabel10.setHorizontalTextPosition(0);
        jLabel10.setMaximumSize(new Dimension(237, 25));
        jLabel10.setMinimumSize(new Dimension(237, 25));
        jLabel10.setPreferredSize(new Dimension(237, 25));
        jLabel10.setVerticalTextPosition(3);
        tbContabil.add(jLabel10);
        btnVisualizarDependencia1.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/dependencias.gif")));
        btnVisualizarDependencia1.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2));
        btnVisualizarDependencia1.setContentAreaFilled(false);
        btnVisualizarDependencia1.setFocusable(false);
        btnVisualizarDependencia1.setHorizontalTextPosition(0);
        btnVisualizarDependencia1.setMargin(new Insets(2, 20, 2, 14));
        btnVisualizarDependencia1.setVerticalTextPosition(3);
        btnVisualizarDependencia1.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent evt)
            {
                btnVisualizarDependencia1ActionPerformed(evt);
            }
            
        }
);
        tbContabil.add(btnVisualizarDependencia1);
        btnBalanceteAnalitico.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/balancete_analitico.png")));
        btnBalanceteAnalitico.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2));
        btnBalanceteAnalitico.setContentAreaFilled(false);
        btnBalanceteAnalitico.setFocusable(false);
        btnBalanceteAnalitico.setHorizontalAlignment(2);
        btnBalanceteAnalitico.setHorizontalTextPosition(0);
        btnBalanceteAnalitico.setMargin(new Insets(2, 20, 2, 14));
        btnBalanceteAnalitico.setMaximumSize(new Dimension(237, 25));
        btnBalanceteAnalitico.setMinimumSize(new Dimension(237, 25));
        btnBalanceteAnalitico.setVerticalTextPosition(3);
        btnBalanceteAnalitico.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent evt)
            {
                btnBalanceteAnaliticoActionPerformed(evt);
            }
        }
);
        tbContabil.add(btnBalanceteAnalitico);
        btnDemonstrativoRateio.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/demonstrativo_rateio.png")));
        btnDemonstrativoRateio.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2));
        btnDemonstrativoRateio.setContentAreaFilled(false);
        btnDemonstrativoRateio.setFocusable(false);
        btnDemonstrativoRateio.setHorizontalTextPosition(0);
        btnDemonstrativoRateio.setMargin(new Insets(2, 20, 2, 14));
        btnDemonstrativoRateio.setMaximumSize(new Dimension(237, 25));
        btnDemonstrativoRateio.setMinimumSize(new Dimension(237, 25));
        btnDemonstrativoRateio.setVerticalTextPosition(3);
        btnDemonstrativoRateio.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent evt)
            {
                btnDemonstrativoRateioActionPerformed(evt);
            }
        }
);
        tbContabil.add(btnDemonstrativoRateio);
        tbICM.setBackground(new Color(249, 249, 249));
        tbICM.setFloatable(false);
        tbICM.setOrientation(1);
        tbICM.setRollover(true);
        tbICM.setDoubleBuffered(true);
        tbICM.setMaximumSize(new Dimension(239, 340));
        tbICM.setMinimumSize(new Dimension(239, 340));
        tbICM.setVisible(false);
        jLabel7.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/linha_branca.gif")));
        jLabel7.setText(" ");
        jLabel7.setHorizontalTextPosition(0);
        jLabel7.setMaximumSize(new Dimension(237, 25));
        jLabel7.setMinimumSize(new Dimension(237, 25));
        jLabel7.setPreferredSize(new Dimension(237, 25));
        jLabel7.setVerticalTextPosition(3);
        tbICM.add(jLabel7);
        btnSeparadorVisualizarDeclaracao2.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/visualizar_declaracao.gif")));
        btnSeparadorVisualizarDeclaracao2.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2));
        btnSeparadorVisualizarDeclaracao2.setContentAreaFilled(false);
        btnSeparadorVisualizarDeclaracao2.setFocusable(false);
        btnSeparadorVisualizarDeclaracao2.setHorizontalTextPosition(0);
        btnSeparadorVisualizarDeclaracao2.setIconTextGap(8);
        btnSeparadorVisualizarDeclaracao2.setMaximumSize(new Dimension(237, 25));
        btnSeparadorVisualizarDeclaracao2.setMinimumSize(new Dimension(237, 25));
        btnSeparadorVisualizarDeclaracao2.setPreferredSize(new Dimension(237, 25));
        btnSeparadorVisualizarDeclaracao2.setVerticalTextPosition(3);
        tbICM.add(btnSeparadorVisualizarDeclaracao2);
        jLabel8.setBackground(new Color(249, 249, 249));
        jLabel8.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/linha_branca.gif")));
        jLabel8.setText(" ");
        jLabel8.setHorizontalTextPosition(0);
        jLabel8.setMaximumSize(new Dimension(237, 25));
        jLabel8.setMinimumSize(new Dimension(237, 25));
        jLabel8.setPreferredSize(new Dimension(237, 25));
        jLabel8.setVerticalTextPosition(3);
        tbICM.add(jLabel8);
        btnPgccCosif.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/pgcc_cosif.png")));
        btnPgccCosif.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2));
        btnPgccCosif.setContentAreaFilled(false);
        btnPgccCosif.setFocusable(false);
        btnPgccCosif.setHorizontalAlignment(2);
        btnPgccCosif.setHorizontalTextPosition(2);
        btnPgccCosif.setMargin(new Insets(2, 20, 2, 14));
        btnPgccCosif.setMaximumSize(new Dimension(237, 25));
        btnPgccCosif.setMinimumSize(new Dimension(237, 25));
        btnPgccCosif.setVerticalTextPosition(3);
        btnPgccCosif.addActionListener(new ActionListener() 
        {   
            public void actionPerformed(ActionEvent evt)
            {
                btnPgccCosifActionPerformed(evt);
            }
        }
);
        tbICM.add(btnPgccCosif);
        btnContasAnaliticasPgcc.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/contas_analiticas.png")));
        btnContasAnaliticasPgcc.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2));
        btnContasAnaliticasPgcc.setContentAreaFilled(false);
        btnContasAnaliticasPgcc.setFocusable(false);
        btnContasAnaliticasPgcc.setHorizontalAlignment(2);
        btnContasAnaliticasPgcc.setHorizontalTextPosition(2);
        btnContasAnaliticasPgcc.setMargin(new Insets(2, 20, 2, 14));
        btnContasAnaliticasPgcc.setMaximumSize(new Dimension(237, 19));
        btnContasAnaliticasPgcc.setMinimumSize(new Dimension(237, 19));
        btnContasAnaliticasPgcc.setVerticalTextPosition(3);
        btnContasAnaliticasPgcc.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt)
            {
                btnContasAnaliticasPgccActionPerformed(evt);
            }

       
        }
);
        tbICM.add(btnContasAnaliticasPgcc);
        btnTarifasServicos.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/tarifas_servicos.png")));
        btnTarifasServicos.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2));
        btnTarifasServicos.setContentAreaFilled(false);
        btnTarifasServicos.setFocusable(false);
        btnTarifasServicos.setHorizontalAlignment(2);
        btnTarifasServicos.setHorizontalTextPosition(2);
        btnTarifasServicos.setMargin(new Insets(2, 20, 2, 14));
        btnTarifasServicos.setMaximumSize(new Dimension(237, 25));
        btnTarifasServicos.setMinimumSize(new Dimension(237, 25));
        btnTarifasServicos.setVerticalTextPosition(3);
        btnTarifasServicos.addActionListener(new ActionListener() {

    
            public void actionPerformed(ActionEvent evt)
            {
                btnTarifasServicosActionPerformed(evt);
            }

            
          
        }
);
        tbICM.add(btnTarifasServicos);
        btnServicosRemVar.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/servicos_rem_variavel.png")));
        btnServicosRemVar.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2));
        btnServicosRemVar.setContentAreaFilled(false);
        btnServicosRemVar.setFocusable(false);
        btnServicosRemVar.setHorizontalAlignment(2);
        btnServicosRemVar.setHorizontalTextPosition(2);
        btnServicosRemVar.setMargin(new Insets(2, 20, 2, 14));
        btnServicosRemVar.setMaximumSize(new Dimension(237, 25));
        btnServicosRemVar.setMinimumSize(new Dimension(237, 25));
        btnServicosRemVar.setVerticalTextPosition(3);
        btnServicosRemVar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt)
            {
                btnServicosRemVarActionPerformed(evt);
            }

                }
);
        tbICM.add(btnServicosRemVar);
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().add(layout.createParallelGroup(1).add(2, layout.createSequentialGroup().addContainerGap().add(layout.createParallelGroup(2).add(1, panMenu, -1, -1, 32767).add(layout.createSequentialGroup().add(pnlMenu, -1, 211, 32767).addPreferredGap(0).add(panPrincipal, -1, 547, 32767)))).add(jPanel1, -1, -1, 32767)).addContainerGap()).add(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(tbAMI, -2, 210, -2).addContainerGap(564, 32767))).add(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(tbICM, -2, 210, -2).add(564, 564, 564))).add(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(tbContabil, -2, 210, -2).addContainerGap(564, 32767))));
        layout.setVerticalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().add(jPanel1, -2, -1, -2).addPreferredGap(0).add(panMenu, -2, -1, -2).addPreferredGap(0).add(layout.createParallelGroup(1).add(panPrincipal, -2, 388, -2).add(pnlMenu, -2, -1, -2)).add(55, 55, 55)).add(layout.createParallelGroup(1).add(layout.createSequentialGroup().add(182, 182, 182).add(tbAMI, -2, 299, -2).addContainerGap(58, 32767))).add(layout.createParallelGroup(1).add(layout.createSequentialGroup().add(179, 179, 179).add(tbICM, -2, 177, -2).addContainerGap(183, 32767))).add(layout.createParallelGroup(1).add(layout.createSequentialGroup().add(178, 178, 178).add(tbContabil, -2, -1, -2).addContainerGap(189, 32767))));
        pack();
    }

    private void formWindowClosing(WindowEvent evt)
    {
        if(br.gov.pbh.desif.view.util.SwingUtils.msgQues(this, "Deseja sair do sistema?") == 0)
            System.exit(0);
    }

    private void btnSairActionPerformed(ActionEvent evt)
    {
        if(br.gov.pbh.desif.view.util.SwingUtils.msgQues(this, "Deseja sair da tela Gerar Declara\347\343o ?") == 0)
            System.exit(0);
    }

    private void btnConfigurarActionPerformed(ActionEvent evt)
    {
        DialConfiguracao dialConf = (DialConfiguracao)Contexto.getObject("dialConfiguracao");
        dialConf.setVisible(true);
    }

    public JButton setCursorPadrao(JButton botao)
    {
        botao.setCursor(Cursor.getPredefinedCursor(0));
        ((FrmPrincipal)Contexto.getObject("frmPrincipal")).setCursor(Cursor.getPredefinedCursor(0));
        return botao;
    }

    public JButton setCursorAmpulheta(JButton botao)
    {
        botao.setCursor(Cursor.getPredefinedCursor(3));
        ((FrmPrincipal)Contexto.getObject("frmPrincipal")).setCursor(Cursor.getPredefinedCursor(3));
        return botao;
    }

    private void btnPgccCosifActionPerformed(ActionEvent evt)
    {
        setCursorAmpulheta(btnPgccCosif);
        visualizarPGCC();
        setCursorPadrao(btnPgccCosif);
    }

    private void btnContasAnaliticasPgccActionPerformed(ActionEvent evt)
    {
        setCursorAmpulheta(btnContasAnaliticasPgcc);
        visualizarPGCC2();
        setCursorPadrao(btnContasAnaliticasPgcc);
    }

    private void btnTarifasServicosActionPerformed(ActionEvent evt)
    {
        setCursorAmpulheta(btnTarifasServicos);
        visualizarTarifServInstituicao();
        setCursorPadrao(btnTarifasServicos);
    }

    private void btnServicosRemVarActionPerformed(ActionEvent evt)
    {
        setCursorAmpulheta(btnServicosRemVar);
        visualizarTarifServRemuVariavel();
        setCursorPadrao(btnServicosRemVar);
    }

    private void btnVisualizarDependenciaActionPerformed(ActionEvent evt)
    {
        visualizarDependencias();
    }

    private void btnVisApurSubActionPerformed(ActionEvent evt)
    {
        setCursorAmpulheta(btnVisApurSub);
        visualizarApurSubTitulo();
        setCursorPadrao(btnVisApurSub);
    }

    private void btnVisIssqnActionPerformed(ActionEvent evt)
    {
        setCursorAmpulheta(btnVisIssqn);
        visualizarApurIssqn();
        setCursorPadrao(btnVisIssqn);
    }

    private void btnGuiaActionPerformed(ActionEvent evt)
    {
        abrirTela((JPanel)Contexto.getObject("panFiltGuia"));
    }

    private void btnEmitirReciboActionPerformed(ActionEvent evt)
    {
        emitirRecibo();
    }

    private void btnBalanceteAnaliticoActionPerformed(ActionEvent evt)
    {
        setCursorAmpulheta(btnBalanceteAnalitico);
        visualizarBalanceteAnalitico();
        setCursorPadrao(btnBalanceteAnalitico);
    }

    private void btnDemonstrativoRateioActionPerformed(ActionEvent evt)
    {
        setCursorAmpulheta(btnDemonstrativoRateio);
        visualizarDemonstrativoRateio();
        setCursorPadrao(btnDemonstrativoRateio);
    }

    private void btnVisualizarDependencia1ActionPerformed(ActionEvent evt)
    {
        visualizarDependencias();
    }

    private void btnVerificarAssinaturaActionPerformed(ActionEvent evt)
    {
        verificarAssinaturaDeclaracao();
    }

    private void btnProtocoloActionPerformed(ActionEvent evt)
    {
        abrirArquivoProtocolo();
    }

    
      public void selecionaPainelModulo(int modulo)
    {
        switch (modulo)
        {
            case 0:
                 tbContabil.setVisible(false);
                 tbAMI.setVisible(false);
                 tbICM.setVisible(false);
                 break;
            case 1:
                 tbContabil.setVisible(true);  
                 tbAMI.setVisible(false);
                 tbICM.setVisible(false);
                 break;
            case 2:
                 tbContabil.setVisible(false);
                 tbAMI.setVisible(true);
                 tbICM.setVisible(false);
                 break;
            case 3:
                 tbContabil.setVisible(false);
                 tbAMI.setVisible(false);
                 tbICM.setVisible(true);
        }       
    }
    private void btnImpDeclActionPerformed(ActionEvent evt)
    {
        
       
        selecionaPainelModulo(1);
        new Thread() 
        {
            public void run()
            {
                btnImpDecl.setCursor(Cursor.getPredefinedCursor(3));
                ((FrmPrincipal)Contexto.getObject("frmPrincipal")).setCursor(Cursor.getPredefinedCursor(3));
                labDesif.setVisible(false);
                ((PanGerarDeclaracao)Contexto.getObject("panGD")).limparTela();
                
                if(botoesVisible) { setBotoes(false);}
                
                abrirTela((JPanel)Contexto.getObject("panGD"));
                btnImpDecl.setCursor(Cursor.getPredefinedCursor(0));
                ((FrmPrincipal)Contexto.getObject("frmPrincipal")).setCursor(Cursor.getPredefinedCursor(0));
            }
        }.start();
    }
    
    public void abrirTela(JPanel panel)
    {
        setCursor(Cursor.getPredefinedCursor(3));
        panPrincipal.removeAll();
        panel.setBounds(panPrincipal.getBounds());
        panel.setLocation(0, 0);
        panPrincipal.add(panel);
     //   remove(panPrincipal);
     
        if(!panel.isVisible())
        {
            panel.setVisible(true);
        }
        (new Thread()
        {
            @Override
            public void run()
            {
                panPrincipal.setVisible(true);
                panPrincipal.revalidate();
                panPrincipal.repaint();
               
            }  
        }).start();
        setCursor(Cursor.getPredefinedCursor(0));
    }
}
