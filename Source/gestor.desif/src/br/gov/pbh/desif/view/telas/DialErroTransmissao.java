/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  br.gov.pbh.des.componentes.utils.DesLookandFeel
 *  br.gov.pbh.des.componentes.utils.SwingUtils
 */
package br.gov.pbh.desif.view.telas;

import br.gov.pbh.des.componentes.utils.DesLookandFeel;
import br.gov.pbh.des.componentes.utils.SwingUtils;
import br.gov.pbh.desif.service.relatorios.RelatorioErrosWS;
import br.gov.pbh.desif.ws.cliente.Erros;
import java.awt.Component;
import java.awt.Container;
import java.awt.Frame;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.List;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;

public class DialErroTransmissao
extends JDialog {
    List<Erros> mensagens;
    private JButton btnVisualizarProtocolo;
    private JLabel jLabel1;
    private JPanel jPanel1;

    public DialErroTransmissao(Frame parent, boolean modal, List<Erros> mensagens) {
        super(parent, modal);
        this.initComponents();
        DesLookandFeel.getInstance().formatarJButton(this.btnVisualizarProtocolo);
        SwingUtils.getInstance().centralizar((JDialog)this);
        this.mensagens = mensagens;
    }

    private void initComponents() {
        this.jPanel1 = new JPanel();
        this.btnVisualizarProtocolo = new JButton();
        this.jLabel1 = new JLabel();
        this.setDefaultCloseOperation(2);
        this.setTitle("Erro");
        this.btnVisualizarProtocolo.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/visualizar_erros.gif")));
        this.btnVisualizarProtocolo.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                DialErroTransmissao.this.btnVisualizarProtocoloActionPerformed(evt);
            }
        });
        this.jLabel1.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/errosTransmissao.PNG")));
        GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
        this.jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel1, -1, -1, 32767).addComponent(this.btnVisualizarProtocolo, GroupLayout.Alignment.TRAILING)).addContainerGap()));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel1).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 41, 32767).addComponent(this.btnVisualizarProtocolo).addContainerGap()));
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jPanel1, -2, -1, -2));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jPanel1, -2, -1, -2));
        this.pack();
    }

    private void btnVisualizarProtocoloActionPerformed(ActionEvent evt) {
        RelatorioErrosWS relatorioErrosWS = new RelatorioErrosWS(this.mensagens);
        relatorioErrosWS.gerarRelatorioErros();
    }

}

