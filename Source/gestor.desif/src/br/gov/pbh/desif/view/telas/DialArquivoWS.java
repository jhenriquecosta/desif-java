/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  br.gov.pbh.des.componentes.utils.SwingUtils
 */
package br.gov.pbh.desif.view.telas;

import br.gov.pbh.des.componentes.utils.SwingUtils;
import br.gov.pbh.desif.control.Controle;
import br.gov.pbh.desif.service.contexto.Contexto;
import br.gov.pbh.desif.view.util.TxtFilter;
import br.gov.pbh.desif.view.util.XmlFilter;
import java.awt.Component;
import java.awt.Container;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class DialArquivoWS
extends JDialog {
    private Controle controle = (Controle)Contexto.getObject("controle");
    private String tipoFiltro;
    private String caminhoArquivoSelecionado;
    private JFileChooser fileChooser;

    public DialArquivoWS(Frame parent, boolean modal, String titulo, String tipoFiltro) {
        super(parent, modal);
        this.tipoFiltro = tipoFiltro;
        this.setTitle(titulo);
        this.initComponents();
        SwingUtils.getInstance().centralizar((JDialog)this);
        this.setarFiltroJFileChooser(tipoFiltro);
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
                DialArquivoWS.this.closeDialog(evt);
            }
        });
        this.fileChooser.setCurrentDirectory(new File("C:\\Documents and Settings\\guilherme.diniz\\Meus documentos\\Testes"));
        this.fileChooser.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                DialArquivoWS.this.fileChooserActionPerformed(evt);
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
            if (this.tipoFiltro.equals("XML")) {
                try {
                    this.controle.carregaArquivoProtocolo(file.getPath());
                }
                catch (Exception ex) {
                    Logger.getLogger(DialArquivoWS.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (this.tipoFiltro.equals("TXT")) {
                this.setCaminhoArquivoSelecionado(file.getPath());
            }
            this.dispose();
        }
    }

    private void setarFiltroJFileChooser(String tipoFiltro) {
        this.fileChooser.setAcceptAllFileFilterUsed(false);
        if (tipoFiltro.equals("XML")) {
            this.fileChooser.setFileFilter(new XmlFilter());
        } else if (tipoFiltro.equals("TXT")) {
            this.fileChooser.setFileFilter(new TxtFilter());
        }
    }

    public void setCaminhoArquivoSelecionado(String caminho) {
        this.caminhoArquivoSelecionado = caminho;
    }

    public String getCaminhoArquivoSelecionado() {
        return this.caminhoArquivoSelecionado;
    }

}

