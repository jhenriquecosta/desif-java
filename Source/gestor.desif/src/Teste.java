/*
 * Decompiled with CFR 0_125.
 */
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Teste
extends JFrame {
    private JPanel jPanel1;

    public Teste() {
        this.initComponents();
        this.setInitComponentes();
    }

    private void setInitComponentes() {
        int x = 7;
        int y = 3;
        int z = 3;
        JButton[][][] gondula = new JButton[7][3][3];
        for (int i = 0; i < 7; ++i) {
            for (int j = 0; j < 3; ++j) {
                for (int k = 0; k < 3; ++k) {
                    System.out.println("Position  linha " + i + " coluna: " + j + " altura: " + k);
                       int deltaX;
                     
                    switch (k)
                    {
                        
                        case 0: 
                        {
                            deltaX=0;
                            final JButton jb1 = new JButton();
                            jb1.setText("bT" + i + j + k);
                            jb1.setBackground(Color.red);
                            jb1.setVisible(true);
                            jb1.setBounds(100 * j, 40 * i, 50, 20);
                            System.out.println("Posi\u00e7\u00e3o Vermelho x : " + 100 * j + " Posi\u00e7\u00e3o y : " + 40 * i);
                            jb1.addActionListener(new ActionListener(){

                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    JOptionPane.showMessageDialog(null, jb1.getText());
                                }
                            });
                            gondula[i][j][k] = jb1;
                            break;
                        }
                        case 1: {
                            deltaX = 20;
                            final JButton jb2 = new JButton();
                            jb2.setText("bT" + i + j + k);
                            jb2.setBackground(Color.BLUE);
                            jb2.setVisible(true);
                            jb2.setBounds(100 * j + deltaX, 40 * i, 50, 20);
                            System.out.println("Posi\u00e7\u00e3o Azul x : " + 100 * j + " Posi\u00e7\u00e3o y : " + 40 * i);
                            jb2.addActionListener(new ActionListener(){

                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    JOptionPane.showMessageDialog(null, jb2.getText());
                                }
                            });
                            gondula[i][j][k] = jb2;
                            break;
                        }
                        case 2: {
                            deltaX = 30;
                            final JButton jb3 = new JButton();
                            jb3.setText("bT" + i + j + k);
                            jb3.setAlignmentX(20.0f);
                            jb3.setAlignmentY(20.0f);
                            jb3.setBackground(Color.GREEN);
                            jb3.setVisible(true);
                            jb3.setBounds(100 * j + deltaX, 40 * i, 50, 20);
                            jb3.addActionListener(new ActionListener(){

                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    JOptionPane.showMessageDialog(null, jb3.getText());
                                }
                            });
                            gondula[i][j][k] = jb3;
                        }
                    }
                    this.jPanel1.add(gondula[i][j][k]);
                }
            }
        }
        this.jPanel1.revalidate();
        this.jPanel1.repaint();
    }

    private void initComponents() {
        this.jPanel1 = new JPanel();
        this.setDefaultCloseOperation(3);
        GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
        this.jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 533, 32767));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 293, 32767));
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -1, -1, 32767).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -1, -1, 32767).addContainerGap()));
        this.pack();
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if (!"Nimbus".equals(info.getName())) continue;
                UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex) {
            Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex) {
            Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
        }
        EventQueue.invokeLater(new Runnable(){

            @Override
            public void run() {
                new Teste().setVisible(true);
            }
        });
    }

}

