
package br.gov.pbh.desif.view.telas;

import br.gov.pbh.des.componentes.utils.DesLookandFeel;
import br.gov.pbh.des.componentes.utils.SwingUtils;
import br.gov.pbh.desif.service.relatorios.*;
import br.gov.pbh.desif.ws.cliente.*;
import java.awt.Container;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class DialSucessoTransmissao extends JDialog
{

    ProtocoloContabil protocoloContabil;
    ProtocoloICM protocoloICM;
    ProtocoloAMI protocoloAMI;
    short modulo;
    private JButton btnVisualizarProtocolo;
    private JLabel jLabel1;
    private JPanel jPanel1;

    public DialSucessoTransmissao(Frame parent, boolean modal, Object protocolo, short modulo)
    {
        super(parent, modal);
        initComponents();
        DesLookandFeel.getInstance().formatarJButton(btnVisualizarProtocolo);
        SwingUtils.getInstance().centralizar(this);
        if(modulo == 1)
            protocoloContabil = (ProtocoloContabil)protocolo;
        else
        if(modulo == 2)
            protocoloAMI = (ProtocoloAMI)protocolo;
        else
            protocoloICM = (ProtocoloICM)protocolo;
        this.modulo = modulo;
    }

    private void initComponents()
    {
        jPanel1 = new JPanel();
        btnVisualizarProtocolo = new JButton();
        jLabel1 = new JLabel();
        setDefaultCloseOperation(2);
        setTitle("Sucesso");
        btnVisualizarProtocolo.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/bt_visualizar_protocolo.png")));
        btnVisualizarProtocolo.addActionListener(new ActionListener() {

           public void actionPerformed(ActionEvent evt)
            {
                btnVisualizarProtocoloActionPerformed(evt);
            }

        }
);
        jLabel1.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/sucessoTransmissao.PNG")));
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
        if(modulo == 1)
            try
            {
                RelatorioProtocoloContabil relatorioProtocoloContabil = new RelatorioProtocoloContabil();
                relatorioProtocoloContabil.gerarProtocolo(protocoloContabil, "objeto");
            }
            catch(Exception ex)
            {
                Logger.getLogger(br.gov.pbh.desif.view.telas.DialSucessoTransmissao.class.getName()).log(Level.SEVERE, null, ex);
            }
        else
        if(modulo == 2)
        {
            RelatorioProtocoloAMI relatorioProtocoloAMI = new RelatorioProtocoloAMI();
            try
            {
                relatorioProtocoloAMI.gerarProtocolo(protocoloAMI, "objeto");
            }
            catch(Exception ex)
            {
                Logger.getLogger(br.gov.pbh.desif.view.telas.DialSucessoTransmissao.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else
        if(modulo == 3)
        {
            RelatorioProtocoloICM relatorioProtocoloICM = new RelatorioProtocoloICM();
            try
            {
                relatorioProtocoloICM.gerarProtocolo(protocoloICM, "objeto");
            }
            catch(Exception ex)
            {
                Logger.getLogger(br.gov.pbh.desif.view.telas.DialSucessoTransmissao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
