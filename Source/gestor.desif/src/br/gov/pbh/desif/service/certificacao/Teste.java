/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.service.certificacao;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.LayoutManager;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JFrame;

public class Teste
extends JFrame {
    public Teste() {
        this.initComponents();
    }

    private void initComponents() {
        this.setDefaultCloseOperation(3);
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 400, 32767));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 300, 32767));
        this.pack();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){

            @Override
            public void run() {
                try {
                    new Teste().setVisible(true);
                    MessageDigest digesto = MessageDigest.getInstance("SHA-1");
                    digesto.update("Guilherme".getBytes());
                }
                catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

}

