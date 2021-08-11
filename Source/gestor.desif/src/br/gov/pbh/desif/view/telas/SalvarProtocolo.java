/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.view.telas;

import java.awt.Component;
import java.awt.Container;
import java.awt.Frame;
import java.awt.LayoutManager;
import java.io.File;
import javax.swing.GroupLayout;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

public class SalvarProtocolo
extends JDialog {
    private JFileChooser jFileChooser1;

    public SalvarProtocolo(Frame parent, boolean modal) {
        super(parent, modal);
        this.initComponents();
    }

    public String getDiretorioSelecionado() {
        return this.jFileChooser1.getSelectedFile().getAbsolutePath();
    }

    private void initComponents() {
        this.jFileChooser1 = new JFileChooser();
        this.setDefaultCloseOperation(2);
        this.jFileChooser1.setDialogType(1);
        this.jFileChooser1.setFileSelectionMode(1);
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jFileChooser1, -1, 513, 32767).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jFileChooser1, -1, 357, 32767).addContainerGap()));
        this.pack();
    }
}

