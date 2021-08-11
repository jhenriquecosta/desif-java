package br.gov.pbh.desif.service.certificacao;

import java.awt.Container;
import java.awt.EventQueue;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JFrame;

public class Teste extends JFrame
{

    public Teste()
    {
        initComponents();
    }

    private void initComponents()
    {
        setDefaultCloseOperation(3);
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 400, 32767));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 300, 32767));
        pack();
    }

    public static void main(String args[])
    {
        EventQueue.invokeLater(new Runnable() {

            public void run()
            {
                try
                {
                    (new Teste()).setVisible(true);
                    MessageDigest digesto = MessageDigest.getInstance("SHA-1");
                    digesto.update("Guilherme".getBytes());
                }
                catch(NoSuchAlgorithmException ex)
                {
                    Logger.getLogger(br.gov.pbh.desif.service.certificacao.Teste.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
);
    }
}
