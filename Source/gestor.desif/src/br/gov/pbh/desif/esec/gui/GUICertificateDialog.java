/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  br.com.esec.sdk.device.CertificateAndKey
 */
package br.gov.pbh.desif.esec.gui;

import br.com.esec.sdk.device.CertificateAndKey;
import br.gov.pbh.desif.esec.gui.GUICertificateSelection;
import br.gov.pbh.desif.esec.gui.NoSelectedCertificateException;
import br.gov.pbh.desif.esec.util.Util;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.LayoutManager;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUICertificateDialog
extends JDialog {
    private static final long serialVersionUID = 1L;
    public static final int SIGNATURE_TYPE = 0;
    public static final int ENCRYPTION_TYPE = 1;
    public static int OK_COMMAND = 0;
    public static int CANCEL_COMMAND = 1;
    private int command;
    private GUICertificateSelection certSelection;
    private Window currentWindow;

    public GUICertificateDialog(CertificateAndKey[] certs, Window parent) {
        super((Frame)((JFrame)parent), true);
        System.out.println("Parent: " + parent);
        this.jbInit(certs);
    }

    public CertificateAndKey getSelectedCertificate() throws NoSelectedCertificateException {
        this.validate();
        this.pack();
        Util.centerOnScreen(this);
        this.validate();
        this.pack();
        this.setVisible(true);
        if (this.command == OK_COMMAND) {
            return this.certSelection.getSelectedCertificate();
        }
        throw new NoSelectedCertificateException();
    }

    private void jbInit(CertificateAndKey[] certs) {
        String title = "Selecione um certificado para assinatura";
        JPanel buttonPanel = new JPanel(new FlowLayout(1));
        JButton cancelButton = new JButton("Cancelar");
        JButton okButton = new JButton("OK");
        this.getContentPane().setLayout(new BorderLayout());
        this.setDefaultCloseOperation(0);
        this.setResizable(false);
        this.setTitle(title);
        cancelButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                GUICertificateDialog.this.cancelButton_actionPerformed(e);
            }
        });
        okButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                GUICertificateDialog.this.okButton_actionPerformed(e);
            }
        });
        okButton.setPreferredSize(cancelButton.getPreferredSize());
        this.certSelection = new GUICertificateSelection(certs);
        this.getContentPane().add((Component)this.certSelection, "North");
        this.getContentPane().add((Component)buttonPanel, "Center");
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
    }

    void okButton_actionPerformed(ActionEvent e) {
        this.command = OK_COMMAND;
        this.setVisible(false);
    }

    void cancelButton_actionPerformed(ActionEvent e) {
        this.command = CANCEL_COMMAND;
        this.setVisible(false);
    }

    public static void main(String[] args) {
    }

}

