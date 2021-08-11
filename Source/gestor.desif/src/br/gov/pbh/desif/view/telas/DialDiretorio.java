/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  br.gov.pbh.des.componentes.utils.SwingUtils
 */
package br.gov.pbh.desif.view.telas;

import br.gov.pbh.des.componentes.utils.SwingUtils;
import br.gov.pbh.desif.service.contexto.Contexto;
import br.gov.pbh.desif.view.telas.DialConfiguracao;
import java.awt.Component;
import java.awt.Container;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

public class DialDiretorio
extends JDialog {
    private JFileChooser jFileChooser1;

    public DialDiretorio(Frame parent, boolean modal) {
        super(parent, modal);
        this.setTitle("Selecione o Diretorio");
        this.initComponents();
        SwingUtils.getInstance().centralizar((JDialog)this);
    }

    private void initComponents() {
        this.jFileChooser1 = new JFileChooser();
        this.addWindowListener(new WindowAdapter(){

            @Override
            public void windowClosing(WindowEvent evt) {
                DialDiretorio.this.closeDialog(evt);
            }
        });
        this.jFileChooser1.setFileSelectionMode(1);
        this.jFileChooser1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                DialDiretorio.this.jFileChooser1ActionPerformed(evt);
            }
        });
        this.getContentPane().add((Component)this.jFileChooser1, "Center");
        this.pack();
    }

    private void closeDialog(WindowEvent evt) {
        this.setVisible(false);
        this.dispose();
    }

    private void jFileChooser1ActionPerformed(ActionEvent evt) {
        if (evt.getActionCommand().equals("CancelSelection")) {
            this.dispose();
        } else {
            DialConfiguracao dialConf = (DialConfiguracao)Contexto.getObject("dialConfiguracao");
            File file = this.jFileChooser1.getSelectedFile();
            dialConf.setjtxtAreaCaminhoDiretorio(file.getPath());
            this.dispose();
        }
    }

}

