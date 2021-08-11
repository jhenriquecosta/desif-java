/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  br.gov.pbh.des.componentes.utils.SwingUtils
 */
package br.gov.pbh.desif.view.telas;

import br.gov.pbh.des.componentes.utils.SwingUtils;
import br.gov.pbh.desif.service.contexto.Contexto;
import br.gov.pbh.desif.view.telas.PanGerarDeclaracao;
import br.gov.pbh.desif.view.util.TxtFilter;
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
import javax.swing.filechooser.FileFilter;

public class DialArquivo
extends JDialog {
    private JFileChooser fileChooser;

    public DialArquivo(Frame parent, boolean modal) {
        super(parent, modal);
        this.setTitle("Selecione o Arquivo");
        this.initComponents();
        SwingUtils.getInstance().centralizar((JDialog)this);
        this.setarFiltroJFileChooser();
    }

    public void setDiretorioPadrao(String diretorio) {
        if (diretorio != null) {
            this.fileChooser.setCurrentDirectory(new File(diretorio));
        }
    }

    private void initComponents() {
        this.fileChooser = new JFileChooser();
        this.addWindowListener(new WindowAdapter(){

            @Override
            public void windowClosing(WindowEvent evt) {
                DialArquivo.this.closeDialog(evt);
            }
        });
        this.fileChooser.setCurrentDirectory(new File("C:\\Documents and Settings\\guilherme.diniz\\Meus documentos\\Testes"));
        this.fileChooser.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                DialArquivo.this.fileChooserActionPerformed(evt);
            }
        });
        this.getContentPane().add((Component)this.fileChooser, "Center");
        this.pack();
    }

    private void closeDialog(WindowEvent evt) {
        this.setVisible(false);
        this.dispose();
    }

    private void fileChooserActionPerformed(ActionEvent evt) {
        if (evt.getActionCommand().equals("CancelSelection")) {
            this.dispose();
        } else {
            File file = this.fileChooser.getSelectedFile();
            PanGerarDeclaracao pagGerDec = (PanGerarDeclaracao)Contexto.getObject("panGD");
            pagGerDec.setTextTxfCaminhoArq(file.getPath());
            pagGerDec.setAbilitarBtnGerarDeclaracao(true);
            this.dispose();
        }
    }

    private void setarFiltroJFileChooser() {
        this.fileChooser.setAcceptAllFileFilterUsed(false);
        this.fileChooser.setFileFilter(new TxtFilter());
    }

}

