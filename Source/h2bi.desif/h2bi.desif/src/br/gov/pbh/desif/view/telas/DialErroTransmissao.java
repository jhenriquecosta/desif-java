
package br.gov.pbh.desif.view.telas;

import br.gov.pbh.des.componentes.utils.DesLookandFeel;
import br.gov.pbh.des.componentes.utils.SwingUtils;
import br.gov.pbh.desif.service.relatorios.RelatorioErrosWS;
import java.awt.Container;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;

public class DialErroTransmissao extends JDialog
{

    java.util.List mensagens;
    private JButton btnVisualizarProtocolo;
    private JLabel jLabel1;
    private JPanel jPanel1;

    public DialErroTransmissao(Frame parent, boolean modal, java.util.List mensagens)
    {
        super(parent, modal);
        initComponents();
        DesLookandFeel.getInstance().formatarJButton(btnVisualizarProtocolo);
        SwingUtils.getInstance().centralizar(this);
        this.mensagens = mensagens;
    }

    private void initComponents()
    {
        jPanel1 = new JPanel();
        btnVisualizarProtocolo = new JButton();
        jLabel1 = new JLabel();
        setDefaultCloseOperation(2);
        setTitle("Erro");
        btnVisualizarProtocolo.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/visualizar_erros.gif")));
        btnVisualizarProtocolo.addActionListener(new ActionListener() {

         
            public void actionPerformed(ActionEvent evt)
            {
                btnVisualizarProtocoloActionPerformed(evt);
            }

        }
);
        jLabel1.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/errosTransmissao.PNG")));
        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel1, -1, -1, 32767).addComponent(btnVisualizarProtocolo, javax.swing.GroupLayout.Alignment.TRAILING)).addContainerGap()));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(jLabel1).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, 32767).addComponent(btnVisualizarProtocolo).addContainerGap()));
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jPanel1, -2, -1, -2));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jPanel1, -2, -1, -2));
        pack();
    }

    private void btnVisualizarProtocoloActionPerformed(ActionEvent evt)
    {
        RelatorioErrosWS relatorioErrosWS = new RelatorioErrosWS(mensagens);
        relatorioErrosWS.gerarRelatorioErros();
    }

}
