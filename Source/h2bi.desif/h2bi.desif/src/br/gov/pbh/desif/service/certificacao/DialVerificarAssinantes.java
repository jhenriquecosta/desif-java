
package br.gov.pbh.desif.service.certificacao;

import br.com.esec.sdk.certificate.CertificateStatus;
import br.gov.pbh.desif.esec.assinatura.CertificateStatusInfo;
import br.gov.pbh.desif.esec.assinatura.ContentType;
import br.gov.pbh.desif.esec.assinatura.EnvelopeInfo;
import br.gov.pbh.desif.esec.assinatura.SignatureInfo;
import br.gov.pbh.desif.esec.assinatura.SignatureStatus;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

public class DialVerificarAssinantes extends JDialog
{

    private DefaultMutableTreeNode top;
    private DefaultTreeModel tvmodel;
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

    public DialVerificarAssinantes(Frame parent, boolean modal, EnvelopeInfo envelope, String documento, String assinatura)
    {
        super(parent, modal);
        top = new DefaultMutableTreeNode("Certificados");
        tvmodel = new DefaultTreeModel(top);
        this.assinatura = envelope;
        initComponents();
        iniciarDadosInterfaceAbaGeral();
        iniciarDadosInterfaceAbaAssinatura();
        iniciarDadosCertificados();
        setLocation(calcularXY(getWidth(), getHeight()));
    }

    public Point calcularXY(int largura, int altura)
    {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = screen.width / 2 - largura / 2;
        int y = screen.height / 2 - altura / 2;
        return new Point(x, y);
    }

    private void iniciarDadosInterfaceAbaGeral()
    {
        textArquivo.setText(assinatura.getContentFileName());
        if(assinatura.getSignatureStatus().equals(SignatureStatus.Valid))
            textValidade.setText("V\341lido");
        else
            textValidade.setText("Inv\341lido");
        textCodificacao.setText("Bin\341rio");
        if(assinatura.getContentType().equals(ContentType.Attached))
            textAtachado.setText("Anexado");
        else
            textAtachado.setText("Desanexado");
        textCronologico.setText("N\343o");
        textQuantAssinantes.setText((new StringBuilder()).append("").append(assinatura.getSignatureList().size()).toString());
        textSha1.setText(assinatura.getDigestValue());
        textMd5.setText(null);
    }

    private void iniciarDadosInterfaceAbaAssinatura()
    {
        DefaultListModel model = new DefaultListModel();
        listAssinantes.setModel(model);
        listAssinantes.setSelectedIndex(0);
        if(assinatura.getSignatureList() != null)
        {
            java.util.List sigList = assinatura.getSignatureList();
            for(int i = 0; i < sigList.size(); i++)
                model.addElement(sigList.get(i));

        }
    }

    private void iniciarDadosCertificados()
    {
        if(assinatura.getSignatureList() != null)
        {
            CertificateStatus listaCertificados[] = ((SignatureInfo)assinatura.getSignatureList().get(0)).getCertificateChain();
            addCadeia(listaCertificados);
        }
        ImageIcon cert = new ImageIcon(getClass().getResource("cert.gif"));
        ((DefaultTreeCellRenderer)treeCadeia.getCellRenderer()).setLeafIcon(cert);
        ((DefaultTreeCellRenderer)treeCadeia.getCellRenderer()).setOpenIcon(cert);
        ((DefaultTreeCellRenderer)treeCadeia.getCellRenderer()).setClosedIcon(cert);
        treeCadeia.setRootVisible(false);
        treeCadeia.setModel(tvmodel);
        treeCadeia.expandRow(0);
        for(int i1 = 0; i1 < treeCadeia.getRowCount(); i1++)
            treeCadeia.expandRow(i1);

    }

    private void addCadeia(CertificateStatus certChain[])
    {
        DefaultMutableTreeNode noResultado = (DefaultMutableTreeNode)tvmodel.getRoot();
        for(int i = certChain.length - 1; i >= 0; i--)
        {
            DefaultMutableTreeNode res = new DefaultMutableTreeNode(new CertificateStatusInfo(certChain[i]));
            noResultado.add(res);
            noResultado = res;
        }

    }

    private void initComponents()
    {
        painelAssinaturas = new JTabbedPane();
        painelGeral = new JPanel();
        painelHash = new JPanel();
        labelSha1 = new JLabel();
        labelMd5 = new JLabel();
        textSha1 = new JTextField();
        textMd5 = new JTextField();
        jPanel1 = new JPanel();
        labelNomeArquivo = new JLabel();
        textArquivo = new JTextField();
        labelValidade = new JLabel();
        labelCodificacao = new JLabel();
        labelAtachado = new JLabel();
        labelCronologico = new JLabel();
        labelQuantAssinantes = new JLabel();
        textValidade = new JTextField();
        textCodificacao = new JTextField();
        textAtachado = new JTextField();
        textCronologico = new JTextField();
        textQuantAssinantes = new JTextField();
        jPanel2 = new JPanel();
        jPanel4 = new JPanel();
        jScrollPane1 = new JScrollPane();
        listAssinantes = new JList();
        jPanel3 = new JPanel();
        painelCadeia = new JPanel();
        jPanel5 = new JPanel();
        jScrollPane2 = new JScrollPane();
        treeCadeia = new JTree();
        painelStatus = new JPanel();
        jScrollPane3 = new JScrollPane();
        textStatusCert = new JTextArea();
        btnFechar = new JButton();
        setDefaultCloseOperation(2);
        setTitle("Propriedades da Assinatura Digital");
        painelGeral.setToolTipText("Geral");
        painelHash.setBorder(BorderFactory.createTitledBorder(null, "Hash do Arquivo", 0, 0, new Font("Tahoma", 0, 12), new Color(0, 0, 0)));
        labelSha1.setFont(new Font("Tahoma", 0, 12));
        labelSha1.setText("SHA-1:");
        labelMd5.setFont(new Font("Tahoma", 0, 12));
        labelMd5.setText("MD5:");
        textSha1.setEditable(false);
        textMd5.setEditable(false);
        GroupLayout painelHashLayout = new GroupLayout(painelHash);
        painelHash.setLayout(painelHashLayout);
        painelHashLayout.setHorizontalGroup(painelHashLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(painelHashLayout.createSequentialGroup().addGroup(painelHashLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(labelSha1).addComponent(labelMd5)).addGap(29, 29, 29).addGroup(painelHashLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(textMd5).addComponent(textSha1, -1, 222, 32767)).addContainerGap()));
        painelHashLayout.setVerticalGroup(painelHashLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelHashLayout.createSequentialGroup().addGroup(painelHashLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(labelSha1).addComponent(textSha1, -2, -1, -2)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(painelHashLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(labelMd5).addComponent(textMd5, -2, -1, -2)).addGap(5, 5, 5)));
        jPanel1.setBorder(BorderFactory.createTitledBorder(null, "Propriedades do Arquivo", 0, 0, new Font("Tahoma", 0, 12), new Color(0, 0, 0)));
        labelNomeArquivo.setFont(new Font("Tahoma", 0, 12));
        labelNomeArquivo.setText("Nome do Arquivo:");
        textArquivo.setEditable(false);
        labelValidade.setFont(new Font("Tahoma", 0, 12));
        labelValidade.setText("Validade da Assinatura:");
        labelCodificacao.setFont(new Font("Tahoma", 0, 12));
        labelCodificacao.setText("Codifica\347\343o:");
        labelAtachado.setFont(new Font("Tahoma", 0, 12));
        labelAtachado.setText("Arquivo Atachado:");
        labelCronologico.setFont(new Font("Tahoma", 0, 12));
        labelCronologico.setText("Validade Cronol\363gica:");
        labelQuantAssinantes.setFont(new Font("Tahoma", 0, 12));
        labelQuantAssinantes.setText("Quantidade de Assinantes:");
        textValidade.setEditable(false);
        textCodificacao.setEditable(false);
        textAtachado.setEditable(false);
        textCronologico.setEditable(false);
        textQuantAssinantes.setEditable(false);
        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addComponent(labelNomeArquivo).addContainerGap(467, 32767)).addComponent(textArquivo, -1, 566, 32767).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(labelValidade).addComponent(labelCodificacao).addComponent(labelAtachado).addComponent(labelCronologico).addComponent(labelQuantAssinantes)).addGap(27, 27, 27).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(textCodificacao).addComponent(textAtachado).addComponent(textCronologico).addComponent(textQuantAssinantes, -1, 201, 32767).addComponent(textValidade, -2, 317, -2)).addContainerGap(76, 32767)));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addComponent(labelNomeArquivo).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(textArquivo, -2, -1, -2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(labelValidade).addComponent(textValidade, -2, -1, -2)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(labelCodificacao).addComponent(textCodificacao, -2, -1, -2)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(labelAtachado).addComponent(textAtachado, -2, -1, -2)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(labelCronologico).addComponent(textCronologico, -2, -1, -2)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(labelQuantAssinantes).addComponent(textQuantAssinantes, -2, -1, -2))));
        GroupLayout painelGeralLayout = new GroupLayout(painelGeral);
        painelGeral.setLayout(painelGeralLayout);
        painelGeralLayout.setHorizontalGroup(painelGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelGeralLayout.createSequentialGroup().addContainerGap().addGroup(painelGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(painelHash, javax.swing.GroupLayout.Alignment.LEADING, -1, 582, 32767).addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, -1, -1, 32767)).addContainerGap()));
        painelGeralLayout.setVerticalGroup(painelGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(painelGeralLayout.createSequentialGroup().addContainerGap().addComponent(jPanel1, -2, -1, -2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(painelHash, -1, 71, 32767).addContainerGap()));
        painelAssinaturas.addTab("Geral", painelGeral);
        jPanel4.setBorder(BorderFactory.createTitledBorder(null, "Assinantes", 0, 0, new Font("Tahoma", 0, 12), new Color(0, 0, 0)));
        listAssinantes.setToolTipText("Assinantes");
        listAssinantes.addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent evt)
            {
                listAssinantesValueChanged(evt);
            }

          
        }
);
        jScrollPane1.setViewportView(listAssinantes);
        GroupLayout jPanel4Layout = new GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel4Layout.createSequentialGroup().addContainerGap().addComponent(jScrollPane1, -1, 546, 32767).addContainerGap()));
        jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jScrollPane1, -2, 73, -2));
        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addComponent(jPanel4, -1, -1, 32767).addContainerGap()));
        jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addComponent(jPanel4, -2, -1, -2).addContainerGap(185, 32767)));
        painelAssinaturas.addTab("Assinaturas", jPanel2);
        painelCadeia.setBorder(BorderFactory.createTitledBorder(null, "Caminho da Certifica\347\343o", 0, 0, new Font("Tahoma", 0, 12), new Color(0, 0, 0)));
        treeCadeia.setFont(new Font("Tahoma", 0, 12));
        treeCadeia.addTreeSelectionListener(new TreeSelectionListener() {

            public void valueChanged(TreeSelectionEvent evt)
            {
                treeCadeiaValueChanged(evt);
            }

        }
);
        jScrollPane2.setViewportView(treeCadeia);
        GroupLayout jPanel5Layout = new GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel5Layout.createSequentialGroup().addContainerGap().addComponent(jScrollPane2, -1, 526, 32767).addContainerGap()));
        jPanel5Layout.setVerticalGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, -1, 104, 32767));
        GroupLayout painelCadeiaLayout = new GroupLayout(painelCadeia);
        painelCadeia.setLayout(painelCadeiaLayout);
        painelCadeiaLayout.setHorizontalGroup(painelCadeiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(painelCadeiaLayout.createSequentialGroup().addContainerGap().addComponent(jPanel5, -1, -1, 32767).addContainerGap()));
        painelCadeiaLayout.setVerticalGroup(painelCadeiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jPanel5, -2, -1, -2));
        painelStatus.setBorder(BorderFactory.createTitledBorder(null, "Status do Certificado:", 0, 0, new Font("Tahoma", 0, 12), new Color(0, 0, 0)));
        textStatusCert.setColumns(20);
        textStatusCert.setRows(5);
        jScrollPane3.setViewportView(textStatusCert);
        GroupLayout painelStatusLayout = new GroupLayout(painelStatus);
        painelStatus.setLayout(painelStatusLayout);
        painelStatusLayout.setHorizontalGroup(painelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(painelStatusLayout.createSequentialGroup().addGap(20, 20, 20).addComponent(jScrollPane3, -1, 536, 32767).addContainerGap()));
        painelStatusLayout.setVerticalGroup(painelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jScrollPane3));
        GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addContainerGap().addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(painelStatus, javax.swing.GroupLayout.Alignment.TRAILING, -1, -1, 32767).addComponent(painelCadeia, javax.swing.GroupLayout.Alignment.TRAILING, -1, -1, 32767)).addContainerGap()));
        jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup().addContainerGap(-1, 32767).addComponent(painelCadeia, -2, -1, -2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(painelStatus, -2, -1, -2).addGap(104, 104, 104)));
        painelAssinaturas.addTab("Cadeia de Certifica\347\365es", jPanel3);
        btnFechar.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/service/certificacao/bt_fechar.png")));
        btnFechar.addActionListener(new ActionListener() {

        
            public void actionPerformed(ActionEvent evt)
            {
                btnFecharActionPerformed(evt);
            }

          
        }
);
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(btnFechar, -2, 83, -2).addComponent(painelAssinaturas, -1, 607, 32767)).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(painelAssinaturas, -2, 328, -2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(btnFechar).addContainerGap(-1, 32767)));
        pack();
    }

    private void treeCadeiaValueChanged(TreeSelectionEvent evt)
    {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)treeCadeia.getLastSelectedPathComponent();
        if(node == null)
            return;
        CertificateStatusInfo cert = (CertificateStatusInfo)node.getUserObject();
        if(cert.getCertStatus().getStatus() == 0)
            textStatusCert.setText("V\341lido");
        else
            textStatusCert.setText("Inv\341lido");
    }

    private void btnFecharActionPerformed(ActionEvent evt)
    {
        dispose();
    }

    private void listAssinantesValueChanged(ListSelectionEvent evt)
    {
        SignatureInfo assinante = (SignatureInfo)listAssinantes.getSelectedValue();
        if(assinante == null)
            assinante = new SignatureInfo();
    }



}
