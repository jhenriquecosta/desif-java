
package br.gov.pbh.desif.view.telas;

import br.gov.pbh.des.componentes.textfields.NumerosInteirosTextField;
import br.gov.pbh.des.componentes.utils.DesLookandFeel;
import br.gov.pbh.des.componentes.utils.SwingUtils;
import br.gov.pbh.desif.esec.assinatura.DigitalSignatureManager;
import br.gov.pbh.desif.model.registros.RegUtil;
import br.gov.pbh.desif.service.contexto.Contexto;
import br.gov.pbh.desif.service.contexto.ManipuladoraProperties;
import br.gov.pbh.desif.service.util.Configuracao;
import java.awt.Component;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

public class DialConfiguracao
extends JDialog {
    private Configuracao conf = (Configuracao)Contexto.getObject("configuracao");
    private JButton btnCancelar;
    private JButton btnConfigAssinatura;
    private JButton btnConfirmar;
    private JButton btnSelecionarDiretorino;
    private JButton btnVerificarCosif;
    private ButtonGroup buttonGroup1;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JScrollPane jScrollPane1;
    private JLabel jlbCache;
    private JLabel jlbLogin;
    private JLabel jlbPorta;
    private JLabel jlbSenha;
    private JRadioButton jrbConfBrowser;
    private JRadioButton jrbConfProxy;
    private JTextArea jtxtAreaCaminhoDiretorio;
    private JTextField jtxtCache;
    private JTextField jtxtLogin;
    private NumerosInteirosTextField jtxtPorta;
    private JPasswordField jtxtSenha;

    public DialConfiguracao(Frame parent, boolean modal) {
        super(parent, modal);
        this.initComponents();
        SwingUtils.getInstance().centralizar((JDialog)this);
        this.abilitarCamposProxy(false);
        this.carregarPropriedades();
        this.jtxtAreaCaminhoDiretorio.setLineWrap(true);
        DesLookandFeel.getInstance().formatarJButtons(new JButton[]{this.btnCancelar, this.btnConfirmar, this.btnSelecionarDiretorino});
        this.btnVerificarCosif.setVisible(false);
    }

    public void carregarPropriedades() {
        try {
            if (RegUtil.caminhoDiretorioPadrao != null) {
                this.jtxtAreaCaminhoDiretorio.setText(RegUtil.caminhoDiretorioPadrao);
            }
            if (this.conf.getConfProxy() == 1) {
                this.jrbConfBrowser.setSelected(true);
            } else if (this.conf.getConfProxy() == 2 && this.conf.getHost() != null && this.conf.getPorta() != null) {
                this.jrbConfProxy.setSelected(true);
                this.abilitarCamposProxy(true);
                this.jtxtCache.setText(this.conf.getHost());
                this.jtxtPorta.setText(this.conf.getPorta());
                if (this.conf.getLogin() != null) {
                    this.jtxtLogin.setText(this.conf.getLogin());
                }
                if (this.conf.getSenha() != null) {
                    this.jtxtSenha.setText(this.conf.getSenha());
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            br.gov.pbh.desif.view.util.SwingUtils.msgErro(null, "Erro ao descriptografar a senha");
        }
    }

    private void initComponents() {
        this.buttonGroup1 = new ButtonGroup();
        this.jPanel1 = new JPanel();
        this.jrbConfProxy = new JRadioButton();
        this.jlbCache = new JLabel();
        this.jtxtCache = new JTextField();
        this.jlbPorta = new JLabel();
        this.jtxtPorta = new NumerosInteirosTextField();
        this.jlbLogin = new JLabel();
        this.jtxtLogin = new JTextField();
        this.jlbSenha = new JLabel();
        this.jtxtSenha = new JPasswordField();
        this.jrbConfBrowser = new JRadioButton();
        this.jPanel2 = new JPanel();
        this.btnSelecionarDiretorino = new JButton();
        this.jScrollPane1 = new JScrollPane();
        this.jtxtAreaCaminhoDiretorio = new JTextArea();
        this.btnConfirmar = new JButton();
        this.btnCancelar = new JButton();
        this.btnVerificarCosif = new JButton();
        this.jPanel3 = new JPanel();
        this.btnConfigAssinatura = new JButton();
        this.setDefaultCloseOperation(2);
        this.setTitle("Configura\u00e7\u00f5es Desif");
        this.jPanel1.setBorder(BorderFactory.createTitledBorder(null, "Conex\u00e3o", 0, 0, new Font("Tahoma", 0, 12)));
        this.jPanel1.setEnabled(false);
        this.buttonGroup1.add(this.jrbConfProxy);
        this.jrbConfProxy.setText("Configura\u00e7\u00e3o proxy");
        this.jrbConfProxy.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                DialConfiguracao.this.jrbConfProxyActionPerformed(evt);
            }
        });
        this.jlbCache.setText("HTTP:");
        this.jlbPorta.setText("Porta:");
        this.jlbLogin.setText("Login:");
        this.jlbSenha.setText("Senha:");
        this.buttonGroup1.add(this.jrbConfBrowser);
        this.jrbConfBrowser.setText("Usar configura\u00e7\u00f5es do navegador");
        this.jrbConfBrowser.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                DialConfiguracao.this.jrbConfBrowserActionPerformed(evt);
            }
        });
        GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
        this.jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGap(22, 22, 22).addComponent(this.jlbCache).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jtxtCache, -1, 191, 32767).addGap(18, 18, 18).addComponent(this.jlbPorta).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent((Component)this.jtxtPorta, -2, 114, -2)).addGroup(jPanel1Layout.createSequentialGroup().addGap(22, 22, 22).addComponent(this.jlbLogin).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jtxtLogin, -2, 192, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jlbSenha).addGap(10, 10, 10).addComponent(this.jtxtSenha, -1, 111, 32767)).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(this.jrbConfProxy)).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(this.jrbConfBrowser))).addContainerGap()));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.jrbConfProxy).addGap(14, 14, 14).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE, false).addComponent(this.jlbCache).addComponent(this.jlbPorta).addComponent(this.jtxtCache, -2, -1, -2).addComponent((Component)this.jtxtPorta, -2, -1, -2)).addGap(18, 18, 18).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jlbLogin).addComponent(this.jtxtLogin, -2, -1, -2).addComponent(this.jlbSenha).addComponent(this.jtxtSenha, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 16, 32767).addComponent(this.jrbConfBrowser).addContainerGap()));
        this.jPanel2.setBorder(BorderFactory.createTitledBorder(null, "Diretorio de importa\u00e7\u00e3o declara\u00e7\u00e3o", 0, 0, new Font("Tahoma", 0, 12)));
        this.btnSelecionarDiretorino.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/bt_pesquisar.png")));
        this.btnSelecionarDiretorino.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                DialConfiguracao.this.btnSelecionarDiretorinoActionPerformed(evt);
            }
        });
        this.jtxtAreaCaminhoDiretorio.setColumns(10);
        this.jtxtAreaCaminhoDiretorio.setEditable(false);
        this.jtxtAreaCaminhoDiretorio.setRows(4);
        this.jtxtAreaCaminhoDiretorio.setTabSize(6);
        this.jScrollPane1.setViewportView(this.jtxtAreaCaminhoDiretorio);
        GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
        this.jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jScrollPane1, -1, 408, 32767).addComponent(this.btnSelecionarDiretorino)).addContainerGap()));
        jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap(-1, 32767).addComponent(this.btnSelecionarDiretorino).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jScrollPane1, -2, -1, -2)));
        this.btnConfirmar.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/bt_confirmar.png")));
        this.btnConfirmar.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                DialConfiguracao.this.btnConfirmarActionPerformed(evt);
            }
        });
        this.btnCancelar.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/bt_cancelar.png")));
        this.btnCancelar.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                DialConfiguracao.this.btnCancelarActionPerformed(evt);
            }
        });
        this.btnVerificarCosif.setText("Verificar Cosif");
        this.btnVerificarCosif.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                DialConfiguracao.this.btnVerificarCosifActionPerformed(evt);
            }
        });
        this.jPanel3.setBorder(BorderFactory.createTitledBorder("Assinatura"));
        this.btnConfigAssinatura.setText("Configurar");
        this.btnConfigAssinatura.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                DialConfiguracao.this.btnConfigAssinaturaActionPerformed(evt);
            }
        });
        GroupLayout jPanel3Layout = new GroupLayout(this.jPanel3);
        this.jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addContainerGap().addComponent(this.btnConfigAssinatura).addContainerGap(327, 32767)));
        jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addContainerGap().addComponent(this.btnConfigAssinatura).addContainerGap(41, 32767)));
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup().addGap(18, 18, 18).addComponent(this.jPanel3, -1, -1, 32767)).addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -1, -1, 32767)).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel2, -1, -1, 32767)).addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup().addGap(19, 19, 19).addComponent(this.btnVerificarCosif).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 66, 32767).addComponent(this.btnConfirmar).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.btnCancelar))).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -2, -1, -2).addGap(18, 18, 18).addComponent(this.jPanel2, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanel3, -1, -1, 32767).addGap(18, 18, 18).addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.btnCancelar).addComponent(this.btnConfirmar)).addComponent(this.btnVerificarCosif)).addContainerGap()));
        this.pack();
    }

    private void jrbConfProxyActionPerformed(ActionEvent evt) {
        if (this.jrbConfProxy.isSelected()) {
            this.abilitarCamposProxy(true);
        } else {
            this.abilitarCamposProxy(false);
        }
    }

    private void btnSelecionarDiretorinoActionPerformed(ActionEvent evt) {
        DialDiretorio dialDiretorio = new DialDiretorio(null, true);
        dialDiretorio.setVisible(true);
    }

    private void btnCancelarActionPerformed(ActionEvent evt) {
        this.dispose();
    }

    private void btnConfirmarActionPerformed(ActionEvent evt) {
        this.configurarPropriedadesAssinatura();
        this.dispose();
    }

    private void jrbConfBrowserActionPerformed(ActionEvent evt) {
        this.abilitarCamposProxy(false);
    }

    private void btnVerificarCosifActionPerformed(ActionEvent evt) {
        new TesteJTree().setVisible(true);
    }

    private void btnConfigAssinaturaActionPerformed(ActionEvent evt) {
        DigitalSignatureManager.showConfiguration();
    }

    private void abilitarCamposProxy(boolean b) {
        this.jtxtCache.setText("");
        this.jtxtPorta.setText("");
        this.jtxtLogin.setText("");
        this.jtxtSenha.setText(null);
        this.jtxtCache.setEnabled(b);
        this.jtxtPorta.setEnabled(b);
        this.jtxtLogin.setEnabled(b);
        this.jtxtSenha.setEnabled(b);
    }

    public void setjtxtAreaCaminhoDiretorio(String str) {
        RegUtil.caminhoDiretorioPadrao = str;
        this.jtxtAreaCaminhoDiretorio.setText(str);
    }

    public String getjtxtAreaCaminhoDiretorio() {
        return this.jtxtAreaCaminhoDiretorio.getText();
    }

    public void configurarPropriedadesAssinatura() {
        try {
            ManipuladoraProperties mp = new ManipuladoraProperties(RegUtil.caminhoPropriedades);
            mp.carregarPropriedades();
            System.out.println("O que tem em confProxy => " + this.conf.getConfProxy());
            if (this.jrbConfBrowser.isSelected()) {
                mp.alterarPropriedade("confProxy", "1");
                this.conf.setConfProxy(1);
                System.setProperty("java.net.useSystemProxies", "true");
                mp.alterarPropriedade("diretorioPadrao", this.getjtxtAreaCaminhoDiretorio());
            } else {
                System.setProperty("java.net.useSystemProxies", "false");
                mp.alterarPropriedade("confProxy", "2");
                mp.alterarPropriedade("http", this.jtxtCache.getText());
                mp.alterarPropriedade("porta", this.jtxtPorta.getText());
                mp.alterarPropriedade("diretorioPadrao", this.getjtxtAreaCaminhoDiretorio());
                mp.alterarPropriedade("login", this.jtxtLogin.getText());
                mp.alterarPropriedade("senha", new String(this.jtxtSenha.getPassword()));
                this.conf.iniciarConfiguracoesProxy(this.jtxtCache.getText().trim(), this.jtxtPorta.getText().trim(), this.jtxtLogin.getText(), new String(this.jtxtSenha.getPassword()));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            br.gov.pbh.desif.view.util.SwingUtils.msgErro(null, "Erro na Criptografia da senha");
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello, World!!!");
        DialConfiguracao dc = new DialConfiguracao(null, true);
        dc.setVisible(true);
    }

}

