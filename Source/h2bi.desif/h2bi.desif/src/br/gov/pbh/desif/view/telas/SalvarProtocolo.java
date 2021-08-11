
package br.gov.pbh.desif.view.telas;

import java.awt.Container;
import java.awt.Frame;
import java.io.File;
import javax.swing.*;

public class SalvarProtocolo extends JDialog
{

    private JFileChooser jFileChooser1;

    public SalvarProtocolo(Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
    }

    public String getDiretorioSelecionado()
    {
        return jFileChooser1.getSelectedFile().getAbsolutePath();
    }

    private void initComponents()
    {
        jFileChooser1 = new JFileChooser();
        setDefaultCloseOperation(2);
        jFileChooser1.setDialogType(1);
        jFileChooser1.setFileSelectionMode(1);
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jFileChooser1, -1, 513, 32767).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jFileChooser1, -1, 357, 32767).addContainerGap()));
        pack();
    }
}
