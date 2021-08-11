/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  org.jdesktop.layout.GroupLayout
 *  org.jdesktop.layout.GroupLayout$Group
 *  org.jdesktop.layout.GroupLayout$ParallelGroup
 *  org.jdesktop.layout.GroupLayout$SequentialGroup
 */
package br.gov.pbh.desif.view.telas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.LayoutManager;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import org.jdesktop.layout.GroupLayout;

public class testeJeff
extends JFrame {
    private JButton jButton1;
    private JButton jButton2;
    private JMenu jMenu1;
    private JMenu jMenu2;
    private JMenuBar jMenuBar1;
    private JPanel jPanel1;
    private JToolBar jToolBar1;

    public testeJeff() {
        this.initComponents();
    }

    private void initComponents() {
        this.jToolBar1 = new JToolBar();
        this.jButton1 = new JButton();
        this.jPanel1 = new JPanel();
        this.jButton2 = new JButton();
        this.jMenuBar1 = new JMenuBar();
        this.jMenu1 = new JMenu();
        this.jMenu2 = new JMenu();
        this.setDefaultCloseOperation(3);
        this.setBackground(new Color(255, 255, 255));
        this.setForeground(Color.white);
        this.jToolBar1.setBackground(new Color(255, 255, 255));
        this.jToolBar1.setOrientation(1);
        this.jToolBar1.setRollover(true);
        this.jButton1.setText("jButton1");
        this.jButton1.setFocusable(false);
        this.jButton1.setHorizontalTextPosition(0);
        this.jButton1.setVerticalTextPosition(3);
        this.jToolBar1.add(this.jButton1);
        this.jPanel1.setBackground(new Color(255, 255, 255));
        this.jButton2.setFocusable(false);
        this.jButton2.setHorizontalTextPosition(0);
        this.jButton2.setVerticalTextPosition(3);
        GroupLayout jPanel1Layout = new GroupLayout((Container)this.jPanel1);
        this.jPanel1.setLayout((LayoutManager)jPanel1Layout);
        jPanel1Layout.setHorizontalGroup((GroupLayout.Group)jPanel1Layout.createParallelGroup(1).add((GroupLayout.Group)jPanel1Layout.createSequentialGroup().add(123, 123, 123).add((Component)this.jButton2, -2, 446, -2).addContainerGap(90, 32767)));
        jPanel1Layout.setVerticalGroup((GroupLayout.Group)jPanel1Layout.createParallelGroup(1).add((GroupLayout.Group)jPanel1Layout.createSequentialGroup().addContainerGap().add((Component)this.jButton2, -2, 199, -2).addContainerGap(43, 32767)));
        this.jMenu1.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/bt_abrir.png")));
        this.jMenuBar1.add(this.jMenu1);
        this.jMenu2.setText("Edit");
        this.jMenuBar1.add(this.jMenu2);
        this.setJMenuBar(this.jMenuBar1);
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout((LayoutManager)layout);
        layout.setHorizontalGroup((GroupLayout.Group)layout.createParallelGroup(1).add((GroupLayout.Group)layout.createSequentialGroup().addContainerGap().add((Component)this.jToolBar1, -2, 108, -2).add(18, 18, 18).add((Component)this.jPanel1, -2, -1, -2).addContainerGap(-1, 32767)));
        layout.setVerticalGroup((GroupLayout.Group)layout.createParallelGroup(1).add((GroupLayout.Group)layout.createSequentialGroup().add(56, 56, 56).add((GroupLayout.Group)layout.createParallelGroup(1).add((GroupLayout.Group)layout.createSequentialGroup().add((Component)this.jToolBar1, -2, 251, -2).addContainerGap()).add((GroupLayout.Group)layout.createSequentialGroup().add((Component)this.jPanel1, -1, -1, 32767).addContainerGap(65, 32767)))));
        this.pack();
    }

    public static void main(String[] args) {
        Properties p = System.getProperties();
        System.out.println(System.getProperties().getProperty("http.proxyPort"));
    }
}

