/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  br.com.esec.sdk.certificate.CertificateStatus
 */
package br.gov.pbh.desif.service.certificacao;

import br.com.esec.sdk.certificate.CertificateStatus;
import br.gov.pbh.desif.esec.assinatura.CertificateStatusInfo;
import br.gov.pbh.desif.esec.assinatura.ContentType;
import br.gov.pbh.desif.esec.assinatura.EnvelopeInfo;
import br.gov.pbh.desif.esec.assinatura.SignatureInfo;
import br.gov.pbh.desif.esec.assinatura.SignatureStatus;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.LayoutStyle;
import javax.swing.ListModel;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;

public class DialVerificarAssinantes
extends JDialog {
    private DefaultMutableTreeNode top = new DefaultMutableTreeNode("Certificados");
    private DefaultTreeModel tvmodel = new DefaultTreeModel(this.top);
    private EnvelopeInfo assinatura;
    private JButton btnFechar;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JPanel jPanel5;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JScrollPane jScrollPane3;
    private JLabel labelAtachado;
    private JLabel labelCodificacao;
    private JLabel labelCronologico;
    private JLabel labelMd5;
    private JLabel labelNomeArquivo;
    private JLabel labelQuantAssinantes;
    private JLabel labelSha1;
    private JLabel labelValidade;
    private JList listAssinantes;
    private JTabbedPane painelAssinaturas;
    private JPanel painelCadeia;
    private JPanel painelGeral;
    private JPanel painelHash;
    private JPanel painelStatus;
    private JTextField textArquivo;
    private JTextField textAtachado;
    private JTextField textCodificacao;
    private JTextField textCronologico;
    private JTextField textMd5;
    private JTextField textQuantAssinantes;
    private JTextField textSha1;
    private JTextArea textStatusCert;
    private JTextField textValidade;
    private JTree treeCadeia;

    public DialVerificarAssinantes(Frame parent, boolean modal, EnvelopeInfo envelope, String documento, String assinatura) {
        super(parent, modal);
        this.assinatura = envelope;
        this.initComponents();
        this.iniciarDadosInterfaceAbaGeral();
        this.iniciarDadosInterfaceAbaAssinatura();
        this.iniciarDadosCertificados();
        this.setLocation(this.calcularXY(this.getWidth(), this.getHeight()));
    }

    public Point calcularXY(int largura, int altura) {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = screen.width / 2 - largura / 2;
        int y = screen.height / 2 - altura / 2;
        return new Point(x, y);
    }

    private void iniciarDadosInterfaceAbaGeral() {
        this.textArquivo.setText(this.assinatura.getContentFileName());
        if (this.assinatura.getSignatureStatus().equals((Object)SignatureStatus.Valid)) {
            this.textValidade.setText("V\u00e1lido");
        } else {
            this.textValidade.setText("Inv\u00e1lido");
        }
        this.textCodificacao.setText("Bin\u00e1rio");
        if (this.assinatura.getContentType().equals((Object)ContentType.Attached)) {
            this.textAtachado.setText("Anexado");
        } else {
            this.textAtachado.setText("Desanexado");
        }
        this.textCronologico.setText("N\u00e3o");
        this.textQuantAssinantes.setText("" + this.assinatura.getSignatureList().size());
        this.textSha1.setText(this.assinatura.getDigestValue());
        this.textMd5.setText(null);
    }

    private void iniciarDadosInterfaceAbaAssinatura() {
        DefaultListModel<SignatureInfo> model = new DefaultListModel<SignatureInfo>();
        this.listAssinantes.setModel(model);
        this.listAssinantes.setSelectedIndex(0);
        if (this.assinatura.getSignatureList() != null) {
            List<SignatureInfo> sigList = this.assinatura.getSignatureList();
            for (int i = 0; i < sigList.size(); ++i) {
                model.addElement(sigList.get(i));
            }
        }
    }

    private void iniciarDadosCertificados() {
        if (this.assinatura.getSignatureList() != null) {
            CertificateStatus[] listaCertificados = this.assinatura.getSignatureList().get(0).getCertificateChain();
            this.addCadeia(listaCertificados);
        }
        ImageIcon cert = new ImageIcon(this.getClass().getResource("cert.gif"));
        ((DefaultTreeCellRenderer)this.treeCadeia.getCellRenderer()).setLeafIcon(cert);
        ((DefaultTreeCellRenderer)this.treeCadeia.getCellRenderer()).setOpenIcon(cert);
        ((DefaultTreeCellRenderer)this.treeCadeia.getCellRenderer()).setClosedIcon(cert);
        this.treeCadeia.setRootVisible(false);
        this.treeCadeia.setModel(this.tvmodel);
        this.treeCadeia.expandRow(0);
        for (int i1 = 0; i1 < this.treeCadeia.getRowCount(); ++i1) {
            this.treeCadeia.expandRow(i1);
        }
    }

    private void addCadeia(CertificateStatus[] certChain) {
        DefaultMutableTreeNode noResultado = (DefaultMutableTreeNode)this.tvmodel.getRoot();
        for (int i = certChain.length - 1; i >= 0; --i) {
            DefaultMutableTreeNode res = new DefaultMutableTreeNode(new CertificateStatusInfo(certChain[i]));
            noResultado.add(res);
            noResultado = res;
        }
    }

    private void initComponents() {
        this.painelAssinaturas = new JTabbedPane();
        this.painelGeral = new JPanel();
        this.painelHash = new JPanel();
        this.labelSha1 = new JLabel();
        this.labelMd5 = new JLabel();
        this.textSha1 = new JTextField();
        this.textMd5 = new JTextField();
        this.jPanel1 = new JPanel();
        this.labelNomeArquivo = new JLabel();
        this.textArquivo = new JTextField();
        this.labelValidade = new JLabel();
        this.labelCodificacao = new JLabel();
        this.labelAtachado = new JLabel();
        this.labelCronologico = new JLabel();
        this.labelQuantAssinantes = new JLabel();
        this.textValidade = new JTextField();
        this.textCodificacao = new JTextField();
        this.textAtachado = new JTextField();
        this.textCronologico = new JTextField();
        this.textQuantAssinantes = new JTextField();
        this.jPanel2 = new JPanel();
        this.jPanel4 = new JPanel();
        this.jScrollPane1 = new JScrollPane();
        this.listAssinantes = new JList();
        this.jPanel3 = new JPanel();
        this.painelCadeia = new JPanel();
        this.jPanel5 = new JPanel();
        this.jScrollPane2 = new JScrollPane();
        this.treeCadeia = new JTree();
        this.painelStatus = new JPanel();
        this.jScrollPane3 = new JScrollPane();
        this.textStatusCert = new JTextArea();
        this.btnFechar = new JButton();
        this.setDefaultCloseOperation(2);
        this.setTitle("Propriedades da Assinatura Digital");
        this.painelGeral.setToolTipText("Geral");
        this.painelHash.setBorder(BorderFactory.createTitledBorder(null, "Hash do Arquivo", 0, 0, new Font("Tahoma", 0, 12), new Color(0, 0, 0)));
        this.labelSha1.setFont(new Font("Tahoma", 0, 12));
        this.labelSha1.setText("SHA-1:");
        this.labelMd5.setFont(new Font("Tahoma", 0, 12));
        this.labelMd5.setText("MD5:");
        this.textSha1.setEditable(false);
        this.textMd5.setEditable(false);
        GroupLayout painelHashLayout = new GroupLayout(this.painelHash);
        this.painelHash.setLayout(painelHashLayout);
        painelHashLayout.setHorizontalGroup(painelHashLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(painelHashLayout.createSequentialGroup().addGroup(painelHashLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.labelSha1).addComponent(this.labelMd5)).addGap(29, 29, 29).addGroup(painelHashLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.textMd5).addComponent(this.textSha1, -1, 222, 32767)).addContainerGap()));
        painelHashLayout.setVerticalGroup(painelHashLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, painelHashLayout.createSequentialGroup().addGroup(painelHashLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.labelSha1).addComponent(this.textSha1, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(painelHashLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.labelMd5).addComponent(this.textMd5, -2, -1, -2)).addGap(5, 5, 5)));
        this.jPanel1.setBorder(BorderFactory.createTitledBorder(null, "Propriedades do Arquivo", 0, 0, new Font("Tahoma", 0, 12), new Color(0, 0, 0)));
        this.labelNomeArquivo.setFont(new Font("Tahoma", 0, 12));
        this.labelNomeArquivo.setText("Nome do Arquivo:");
        this.textArquivo.setEditable(false);
        this.labelValidade.setFont(new Font("Tahoma", 0, 12));
        this.labelValidade.setText("Validade da Assinatura:");
        this.labelCodificacao.setFont(new Font("Tahoma", 0, 12));
        this.labelCodificacao.setText("Codifica\u00e7\u00e3o:");
        this.labelAtachado.setFont(new Font("Tahoma", 0, 12));
        this.labelAtachado.setText("Arquivo Atachado:");
        this.labelCronologico.setFont(new Font("Tahoma", 0, 12));
        this.labelCronologico.setText("Validade Cronol\u00f3gica:");
        this.labelQuantAssinantes.setFont(new Font("Tahoma", 0, 12));
        this.labelQuantAssinantes.setText("Quantidade de Assinantes:");
        this.textValidade.setEditable(false);
        this.textCodificacao.setEditable(false);
        this.textAtachado.setEditable(false);
        this.textCronologico.setEditable(false);
        this.textQuantAssinantes.setEditable(false);
        GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
        this.jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.labelNomeArquivo).addContainerGap(467, 32767)).addComponent(this.textArquivo, -1, 566, 32767).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.labelValidade).addComponent(this.labelCodificacao).addComponent(this.labelAtachado).addComponent(this.labelCronologico).addComponent(this.labelQuantAssinantes)).addGap(27, 27, 27).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.textCodificacao).addComponent(this.textAtachado).addComponent(this.textCronologico).addComponent(this.textQuantAssinantes, -1, 201, 32767).addComponent(this.textValidade, -2, 317, -2)).addContainerGap(76, 32767)));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.labelNomeArquivo).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.textArquivo, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.labelValidade).addComponent(this.textValidade, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.labelCodificacao).addComponent(this.textCodificacao, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.labelAtachado).addComponent(this.textAtachado, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.labelCronologico).addComponent(this.textCronologico, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.labelQuantAssinantes).addComponent(this.textQuantAssinantes, -2, -1, -2))));
        GroupLayout painelGeralLayout = new GroupLayout(this.painelGeral);
        this.painelGeral.setLayout(painelGeralLayout);
        painelGeralLayout.setHorizontalGroup(painelGeralLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, painelGeralLayout.createSequentialGroup().addContainerGap().addGroup(painelGeralLayout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(this.painelHash, GroupLayout.Alignment.LEADING, -1, 582, 32767).addComponent(this.jPanel1, GroupLayout.Alignment.LEADING, -1, -1, 32767)).addContainerGap()));
        painelGeralLayout.setVerticalGroup(painelGeralLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(painelGeralLayout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.painelHash, -1, 71, 32767).addContainerGap()));
        this.painelAssinaturas.addTab("Geral", this.painelGeral);
        this.jPanel4.setBorder(BorderFactory.createTitledBorder(null, "Assinantes", 0, 0, new Font("Tahoma", 0, 12), new Color(0, 0, 0)));
        this.listAssinantes.setToolTipText("Assinantes");
        this.listAssinantes.addListSelectionListener(new ListSelectionListener(){

            @Override
            public void valueChanged(ListSelectionEvent evt) {
                DialVerificarAssinantes.this.listAssinantesValueChanged(evt);
            }
        });
        this.jScrollPane1.setViewportView(this.listAssinantes);
        GroupLayout jPanel4Layout = new GroupLayout(this.jPanel4);
        this.jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel4Layout.createSequentialGroup().addContainerGap().addComponent(this.jScrollPane1, -1, 546, 32767).addContainerGap()));
        jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jScrollPane1, -2, 73, -2));
        GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
        this.jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel4, -1, -1, 32767).addContainerGap()));
        jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel4, -2, -1, -2).addContainerGap(185, 32767)));
        this.painelAssinaturas.addTab("Assinaturas", this.jPanel2);
        this.painelCadeia.setBorder(BorderFactory.createTitledBorder(null, "Caminho da Certifica\u00e7\u00e3o", 0, 0, new Font("Tahoma", 0, 12), new Color(0, 0, 0)));
        this.treeCadeia.setFont(new Font("Tahoma", 0, 12));
        this.treeCadeia.addTreeSelectionListener(new TreeSelectionListener(){

            @Override
            public void valueChanged(TreeSelectionEvent evt) {
                DialVerificarAssinantes.this.treeCadeiaValueChanged(evt);
            }
        });
        this.jScrollPane2.setViewportView(this.treeCadeia);
        GroupLayout jPanel5Layout = new GroupLayout(this.jPanel5);
        this.jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel5Layout.createSequentialGroup().addContainerGap().addComponent(this.jScrollPane2, -1, 526, 32767).addContainerGap()));
        jPanel5Layout.setVerticalGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jScrollPane2, GroupLayout.Alignment.TRAILING, -1, 104, 32767));
        GroupLayout painelCadeiaLayout = new GroupLayout(this.painelCadeia);
        this.painelCadeia.setLayout(painelCadeiaLayout);
        painelCadeiaLayout.setHorizontalGroup(painelCadeiaLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(painelCadeiaLayout.createSequentialGroup().addContainerGap().addComponent(this.jPanel5, -1, -1, 32767).addContainerGap()));
        painelCadeiaLayout.setVerticalGroup(painelCadeiaLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jPanel5, -2, -1, -2));
        this.painelStatus.setBorder(BorderFactory.createTitledBorder(null, "Status do Certificado:", 0, 0, new Font("Tahoma", 0, 12), new Color(0, 0, 0)));
        this.textStatusCert.setColumns(20);
        this.textStatusCert.setRows(5);
        this.jScrollPane3.setViewportView(this.textStatusCert);
        GroupLayout painelStatusLayout = new GroupLayout(this.painelStatus);
        this.painelStatus.setLayout(painelStatusLayout);
        painelStatusLayout.setHorizontalGroup(painelStatusLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(painelStatusLayout.createSequentialGroup().addGap(20, 20, 20).addComponent(this.jScrollPane3, -1, 536, 32767).addContainerGap()));
        painelStatusLayout.setVerticalGroup(painelStatusLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jScrollPane3));
        GroupLayout jPanel3Layout = new GroupLayout(this.jPanel3);
        this.jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addContainerGap().addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.painelStatus, GroupLayout.Alignment.TRAILING, -1, -1, 32767).addComponent(this.painelCadeia, GroupLayout.Alignment.TRAILING, -1, -1, 32767)).addContainerGap()));
        jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup().addContainerGap(-1, 32767).addComponent(this.painelCadeia, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.painelStatus, -2, -1, -2).addGap(104, 104, 104)));
        this.painelAssinaturas.addTab("Cadeia de Certifica\u00e7\u00f5es", this.jPanel3);
        this.btnFechar.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/service/certificacao/bt_fechar.png")));
        this.btnFechar.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                DialVerificarAssinantes.this.btnFecharActionPerformed(evt);
            }
        });
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(this.btnFechar, -2, 83, -2).addComponent(this.painelAssinaturas, -1, 607, 32767)).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.painelAssinaturas, -2, 328, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.btnFechar).addContainerGap(-1, 32767)));
        this.pack();
    }

    private void treeCadeiaValueChanged(TreeSelectionEvent evt) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)this.treeCadeia.getLastSelectedPathComponent();
        if (node == null) {
            return;
        }
        CertificateStatusInfo cert = (CertificateStatusInfo)node.getUserObject();
        if (cert.getCertStatus().getStatus() == 0) {
            this.textStatusCert.setText("V\u00e1lido");
        } else {
            this.textStatusCert.setText("Inv\u00e1lido");
        }
    }

    private void btnFecharActionPerformed(ActionEvent evt) {
        this.dispose();
    }

    private void listAssinantesValueChanged(ListSelectionEvent evt) {
        SignatureInfo assinante = (SignatureInfo)this.listAssinantes.getSelectedValue();
        if (assinante == null) {
            assinante = new SignatureInfo();
        }
    }

}

