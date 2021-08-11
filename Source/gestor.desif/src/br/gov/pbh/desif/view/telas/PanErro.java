/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  br.gov.pbh.des.componentes.utils.DesLookandFeel
 *  org.jdesktop.layout.GroupLayout
 *  org.jdesktop.layout.GroupLayout$Group
 *  org.jdesktop.layout.GroupLayout$ParallelGroup
 *  org.jdesktop.layout.GroupLayout$SequentialGroup
 */
package br.gov.pbh.desif.view.telas;

import br.gov.pbh.des.componentes.utils.DesLookandFeel;
import br.gov.pbh.desif.model.registros.RegUtil;
import br.gov.pbh.desif.service.contexto.Contexto;
import br.gov.pbh.desif.view.telas.DialErroImp;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import org.jdesktop.layout.GroupLayout;

public class PanErro
extends JPanel {
    private JButton btnCabErro;
    private JButton btnVisErro;
    private JPanel jPanel1;
    private JSeparator jSeparator1;
    private JLabel labInfErro;

    public PanErro() {
        this.initComponents();
        this.btnVisErro.setCursor(Cursor.getPredefinedCursor(12));
        DesLookandFeel.getInstance().formatarJButtons(new JButton[]{this.btnCabErro, this.btnVisErro});
        this.setLabel();
    }

    public void setaTela() {
    }

    private void setLabel() {
        this.labInfErro.setText("Ocorreram " + RegUtil.countErro + " erros na importa\u00e7\u00e3o da declara\u00e7\u00e3o. ");
    }

    private void initComponents() {
        this.jPanel1 = new JPanel();
        this.jSeparator1 = new JSeparator();
        this.labInfErro = new JLabel();
        this.btnVisErro = new JButton();
        this.btnCabErro = new JButton();
        this.jPanel1.setBackground(new Color(255, 232, 232));
        this.jSeparator1.setBackground(new Color(255, 78, 78));
        this.jSeparator1.setForeground(new Color(255, 78, 78));
        this.btnVisErro.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/visualizar_erros.gif")));
        this.btnVisErro.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                PanErro.this.btnVisErroActionPerformed(evt);
            }
        });
        GroupLayout jPanel1Layout = new GroupLayout((Container)this.jPanel1);
        this.jPanel1.setLayout((LayoutManager)jPanel1Layout);
        jPanel1Layout.setHorizontalGroup((GroupLayout.Group)jPanel1Layout.createParallelGroup(1).add(2, (Component)this.jSeparator1, -1, 490, 32767).add((GroupLayout.Group)jPanel1Layout.createSequentialGroup().addContainerGap().add((Component)this.labInfErro).addContainerGap(480, 32767)).add((GroupLayout.Group)jPanel1Layout.createSequentialGroup().addContainerGap().add((Component)this.btnVisErro).addContainerGap(289, 32767)));
        jPanel1Layout.setVerticalGroup((GroupLayout.Group)jPanel1Layout.createParallelGroup(1).add((GroupLayout.Group)jPanel1Layout.createSequentialGroup().add((Component)this.jSeparator1, -2, -1, -2).add(30, 30, 30).add((Component)this.labInfErro).addPreferredGap(0, 40, 32767).add((Component)this.btnVisErro).addContainerGap()));
        this.btnCabErro.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/cab_erro.GIF")));
        GroupLayout layout = new GroupLayout((Container)this);
        this.setLayout((LayoutManager)layout);
        layout.setHorizontalGroup((GroupLayout.Group)layout.createParallelGroup(1).add((GroupLayout.Group)layout.createSequentialGroup().addContainerGap().add((GroupLayout.Group)layout.createParallelGroup(1).add((Component)this.jPanel1, -1, -1, 32767).add((Component)this.btnCabErro, -2, 96, -2)).addContainerGap()));
        layout.setVerticalGroup((GroupLayout.Group)layout.createParallelGroup(1).add((GroupLayout.Group)layout.createSequentialGroup().add(15, 15, 15).add((Component)this.btnCabErro, -2, 41, -2).addPreferredGap(1).add((Component)this.jPanel1, -2, -1, -2).addContainerGap(131, 32767)));
    }

    private void btnVisErroActionPerformed(ActionEvent evt) {
        ((DialErroImp)Contexto.getObject("dialErroImp")).setVisible(true);
    }

}

