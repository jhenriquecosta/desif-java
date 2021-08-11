package br.gov.pbh.desif.view.telas;

import br.gov.pbh.bhiss.utilitarios.arquivos.properties.ManipuladoraProperties;
import br.gov.pbh.des.componentes.textfields.NumerosInteirosTextField;
import br.gov.pbh.des.componentes.utils.DesLookandFeel;
import br.gov.pbh.des.componentes.utils.SwingUtils;
import br.gov.pbh.desif.esec.assinatura.DigitalSignatureManager;
import br.gov.pbh.desif.model.registros.RegUtil;
import br.gov.pbh.desif.service.contexto.Contexto;
import br.gov.pbh.desif.service.util.Configuracao;
import java.awt.Container;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
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

// Referenced classes of package br.gov.pbh.desif.view.telas:
//            DialDiretorio, TesteJTree

public class DialConfiguracao extends JDialog
{

    private Configuracao conf;
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

    public DialConfiguracao(Frame parent, boolean modal)
    {
        super(parent, modal);
        conf = (Configuracao)Contexto.getObject("configuracao");
        initComponents();
        SwingUtils.getInstance().centralizar(this);
        abilitarCamposProxy(false);
        carregarPropriedades();
        jtxtAreaCaminhoDiretorio.setLineWrap(true);
        DesLookandFeel.getInstance().formatarJButtons(new JButton[] {
            btnCancelar, btnConfirmar, btnSelecionarDiretorino
        });
        btnVerificarCosif.setVisible(false);
    }

    public void carregarPropriedades()
    {
        try
        {
            if(RegUtil.caminhoDiretorioPadrao != null)
                jtxtAreaCaminhoDiretorio.setText(RegUtil.caminhoDiretorioPadrao);
            if(conf.getConfProxy() == 1)
                jrbConfBrowser.setSelected(true);
            else
            if(conf.getConfProxy() == 2 && conf.getHost() != null && conf.getPorta() != null)
            {
                jrbConfProxy.setSelected(true);
                abilitarCamposProxy(true);
                jtxtCache.setText(conf.getHost());
                jtxtPorta.setText(conf.getPorta());
                if(conf.getLogin() != null)
                    jtxtLogin.setText(conf.getLogin());
                if(conf.getSenha() != null)
                    jtxtSenha.setText(conf.getSenha());
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            br.gov.pbh.desif.view.util.SwingUtils.msgErro(null, "Erro ao descriptografar a senha");
        }
    }

    private void initComponents()
    {
        buttonGroup1 = new ButtonGroup();
        jPanel1 = new JPanel();
        jrbConfProxy = new JRadioButton();
        jlbCache = new JLabel();
        jtxtCache = new JTextField();
        jlbPorta = new JLabel();
        jtxtPorta = new NumerosInteirosTextField();
        jlbLogin = new JLabel();
        jtxtLogin = new JTextField();
        jlbSenha = new JLabel();
        jtxtSenha = new JPasswordField();
        jrbConfBrowser = new JRadioButton();
        jPanel2 = new JPanel();
        btnSelecionarDiretorino = new JButton();
        jScrollPane1 = new JScrollPane();
        jtxtAreaCaminhoDiretorio = new JTextArea();
        btnConfirmar = new JButton();
        btnCancelar = new JButton();
        btnVerificarCosif = new JButton();
        jPanel3 = new JPanel();
        btnConfigAssinatura = new JButton();
        setDefaultCloseOperation(2);
        setTitle("Configura\347\365es Desif");
        jPanel1.setBorder(BorderFactory.createTitledBorder(null, "Conex\343o", 0, 0, new Font("Tahoma", 0, 12)));
        jPanel1.setEnabled(false);
        buttonGroup1.add(jrbConfProxy);
        jrbConfProxy.setText("Configura\347\343o proxy");
        jrbConfProxy.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt)
            {
                jrbConfProxyActionPerformed(evt);
            }

        }
);
        jlbCache.setText("HTTP:");
        jlbPorta.setText("Porta:");
        jlbLogin.setText("Login:");
        jlbSenha.setText("Senha:");
        buttonGroup1.add(jrbConfBrowser);
        jrbConfBrowser.setText("Usar configura\347\365es do navegador");
        jrbConfBrowser.addActionListener(new ActionListener() {

          
            public void actionPerformed(ActionEvent evt)
            {
                jrbConfBrowserActionPerformed(evt);
            }

           
        }
);
        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGap(22, 22, 22).addComponent(jlbCache).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jtxtCache, -1, 191, 32767).addGap(18, 18, 18).addComponent(jlbPorta).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jtxtPorta, -2, 114, -2)).addGroup(jPanel1Layout.createSequentialGroup().addGap(22, 22, 22).addComponent(jlbLogin).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jtxtLogin, -2, 192, -2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jlbSenha).addGap(10, 10, 10).addComponent(jtxtSenha, -1, 111, 32767)).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(jrbConfProxy)).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(jrbConfBrowser))).addContainerGap()));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addComponent(jrbConfProxy).addGap(14, 14, 14).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false).addComponent(jlbCache).addComponent(jlbPorta).addComponent(jtxtCache, -2, -1, -2).addComponent(jtxtPorta, -2, -1, -2)).addGap(18, 18, 18).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jlbLogin).addComponent(jtxtLogin, -2, -1, -2).addComponent(jlbSenha).addComponent(jtxtSenha, -2, -1, -2)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, 32767).addComponent(jrbConfBrowser).addContainerGap()));
        jPanel2.setBorder(BorderFactory.createTitledBorder(null, "Diretorio de importa\347\343o declara\347\343o", 0, 0, new Font("Tahoma", 0, 12)));
        btnSelecionarDiretorino.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/bt_pesquisar.png")));
        btnSelecionarDiretorino.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt)
            {
                btnSelecionarDiretorinoActionPerformed(evt);
            }

            
        }
);
        jtxtAreaCaminhoDiretorio.setColumns(10);
        jtxtAreaCaminhoDiretorio.setEditable(false);
        jtxtAreaCaminhoDiretorio.setRows(4);
        jtxtAreaCaminhoDiretorio.setTabSize(6);
        jScrollPane1.setViewportView(jtxtAreaCaminhoDiretorio);
        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jScrollPane1, -1, 408, 32767).addComponent(btnSelecionarDiretorino)).addContainerGap()));
        jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap(-1, 32767).addComponent(btnSelecionarDiretorino).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jScrollPane1, -2, -1, -2)));
        btnConfirmar.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/bt_confirmar.png")));
        btnConfirmar.addActionListener(new ActionListener() {

       
            public void actionPerformed(ActionEvent evt)
            {
                btnConfirmarActionPerformed(evt);
            }

         
        }
);
        btnCancelar.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/bt_cancelar.png")));
        btnCancelar.addActionListener(new ActionListener() {

      
            public void actionPerformed(ActionEvent evt)
            {
                btnCancelarActionPerformed(evt);
            }

          
        }
);
        btnVerificarCosif.setText("Verificar Cosif");
        btnVerificarCosif.addActionListener(new ActionListener() {

    
            public void actionPerformed(ActionEvent evt)
            {
                btnVerificarCosifActionPerformed(evt);
            }

          
        }
);
        jPanel3.setBorder(BorderFactory.createTitledBorder("Assinatura"));
        btnConfigAssinatura.setText("Configurar");
        btnConfigAssinatura.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt)
            {
                btnConfigAssinaturaActionPerformed(evt);
            }

          
        }
);
        GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addContainerGap().addComponent(btnConfigAssinatura).addContainerGap(327, 32767)));
        jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addContainerGap().addComponent(btnConfigAssinatura).addContainerGap(41, 32767)));
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup().addGap(18, 18, 18).addComponent(jPanel3, -1, -1, 32767)).addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup().addContainerGap().addComponent(jPanel1, -1, -1, 32767)).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jPanel2, -1, -1, 32767)).addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup().addGap(19, 19, 19).addComponent(btnVerificarCosif).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, 32767).addComponent(btnConfirmar).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(btnCancelar))).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jPanel1, -2, 169, -2).addGap(18, 18, 18).addComponent(jPanel2, -2, -1, -2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jPanel3, -1, -1, 32767).addGap(18, 18, 18).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(btnCancelar).addComponent(btnConfirmar)).addComponent(btnVerificarCosif)).addContainerGap()));
        pack();
    }

    private void jrbConfProxyActionPerformed(ActionEvent evt)
    {
        if(jrbConfProxy.isSelected())
            abilitarCamposProxy(true);
        else
            abilitarCamposProxy(false);
    }

    private void btnSelecionarDiretorinoActionPerformed(ActionEvent evt)
    {
        DialDiretorio dialDiretorio = new DialDiretorio(null, true);
        dialDiretorio.setVisible(true);
    }

    private void btnCancelarActionPerformed(ActionEvent evt)
    {
        dispose();
    }

    private void btnConfirmarActionPerformed(ActionEvent evt)
    {
        configurarPropriedadesAssinatura();
        dispose();
    }

    private void jrbConfBrowserActionPerformed(ActionEvent evt)
    {
        abilitarCamposProxy(false);
    }

    private void btnVerificarCosifActionPerformed(ActionEvent evt)
    {
        (new TesteJTree()).setVisible(true);
    }

    private void btnConfigAssinaturaActionPerformed(ActionEvent evt)
    {
        DigitalSignatureManager.showConfiguration();
    }

    private void abilitarCamposProxy(boolean b)
    {
        jtxtCache.setText("");
        jtxtPorta.setText("");
        jtxtLogin.setText("");
        jtxtSenha.setText(null);
        jtxtCache.setEnabled(b);
        jtxtPorta.setEnabled(b);
        jtxtLogin.setEnabled(b);
        jtxtSenha.setEnabled(b);
    }

    public void setjtxtAreaCaminhoDiretorio(String str)
    {
        RegUtil.caminhoDiretorioPadrao = str;
        jtxtAreaCaminhoDiretorio.setText(str);
    }

    public String getjtxtAreaCaminhoDiretorio()
    {
        return jtxtAreaCaminhoDiretorio.getText();
    }

    public void configurarPropriedadesAssinatura()
    {
        try
        {
            ManipuladoraProperties mp = new ManipuladoraProperties(RegUtil.caminhoPropriedades);
            mp.carregarPropriedades();
            System.out.println((new StringBuilder()).append("O que tem em confProxy => ").append(conf.getConfProxy()).toString());
            if(jrbConfBrowser.isSelected())
            {
                mp.alterarPropriedade("confProxy", "1");
                conf.setConfProxy(1);
                System.setProperty("java.net.useSystemProxies", "true");
                mp.alterarPropriedade("diretorioPadrao", getjtxtAreaCaminhoDiretorio());
            } else
            {
                System.setProperty("java.net.useSystemProxies", "false");
                mp.alterarPropriedade("confProxy", "2");
                mp.alterarPropriedade("http", jtxtCache.getText());
                mp.alterarPropriedade("porta", jtxtPorta.getText());
                mp.alterarPropriedade("diretorioPadrao", getjtxtAreaCaminhoDiretorio());
                mp.alterarPropriedade("login", jtxtLogin.getText());
                mp.alterarPropriedade("senha", new String(jtxtSenha.getPassword()));
                conf.iniciarConfiguracoesProxy(jtxtCache.getText().trim(), jtxtPorta.getText().trim(), jtxtLogin.getText(), new String(jtxtSenha.getPassword()));
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            br.gov.pbh.desif.view.util.SwingUtils.msgErro(null, "Erro na Criptografia da senha");
        }
    }

    public static void main(String args[])
    {
        System.out.println("Hello, World!!!");
        DialConfiguracao dc = new DialConfiguracao(null, true);
        dc.setVisible(true);
    }







}
