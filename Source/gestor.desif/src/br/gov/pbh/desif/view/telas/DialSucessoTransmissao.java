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
import br.gov.pbh.desif.service.relatorios.RelatorioProtocoloAMI;
import br.gov.pbh.desif.service.relatorios.RelatorioProtocoloContabil;
import br.gov.pbh.desif.service.relatorios.RelatorioProtocoloICM;
import br.gov.pbh.desif.ws.cliente.ProtocoloAMI;
import br.gov.pbh.desif.ws.cliente.ProtocoloContabil;
import br.gov.pbh.desif.ws.cliente.ProtocoloICM;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;

public class DialSucessoTransmissao
extends JDialog {
    ProtocoloContabil protocoloContabil;
    ProtocoloICM protocoloICM;
    ProtocoloAMI protocoloAMI;
    short modulo;
    private JButton btnVisualizarProtocolo;
    private JLabel jLabel1;
    private JPanel jPanel1;

    public DialSucessoTransmissao(Frame parent, boolean modal, Object protocolo, short modulo) {
        super(parent, modal);
        this.initComponents();
        DesLookandFeel.getInstance().formatarJButton(this.btnVisualizarProtocolo);
        SwingUtils.getInstance().centralizar((JDialog)this);
        if (modulo == 1) {
            this.protocoloContabil = (ProtocoloContabil)protocolo;
        } else if (modulo == 2) {
            this.protocoloAMI = (ProtocoloAMI)protocolo;
        } else {
            this.protocoloICM = (ProtocoloICM)protocolo;
        }
        this.modulo = modulo;
    }

    private void initComponents() {
        this.jPanel1 = new JPanel();
        this.btnVisualizarProtocolo = new JButton();
        this.jLabel1 = new JLabel();
        this.setDefaultCloseOperation(2);
        this.setTitle("Sucesso");
        this.btnVisualizarProtocolo.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/bt_visualizar_protocolo.png")));
        this.btnVisualizarProtocolo.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                DialSucessoTransmissao.this.btnVisualizarProtocoloActionPerformed(evt);
            }
        });
        this.jLabel1.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/sucessoTransmissao.PNG")));
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
        System.out.println("Entrarrr");
        if (this.modulo == 1) {
            try {
                RelatorioProtocoloContabil relatorioProtocoloContabil = new RelatorioProtocoloContabil();
                relatorioProtocoloContabil.gerarProtocolo(this.protocoloContabil, "objeto");
            }
            catch (Exception ex) {
                ex.printStackTrace();
                Logger.getLogger(DialSucessoTransmissao.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (this.modulo == 2) {
            RelatorioProtocoloAMI relatorioProtocoloAMI = new RelatorioProtocoloAMI();
            try {
                relatorioProtocoloAMI.gerarProtocolo(this.protocoloAMI, "objeto");
            }
            catch (Exception ex) {
                ex.printStackTrace();
                Logger.getLogger(DialSucessoTransmissao.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (this.modulo == 3) {
            RelatorioProtocoloICM relatorioProtocoloICM = new RelatorioProtocoloICM();
            try {
                relatorioProtocoloICM.gerarProtocolo(this.protocoloICM, "objeto");
            }
            catch (Exception ex) {
                ex.printStackTrace();
                Logger.getLogger(DialSucessoTransmissao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}

