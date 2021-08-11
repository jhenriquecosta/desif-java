
package br.gov.pbh.desif.view.telas;

import java.awt.Color;
import java.awt.Container;
import java.io.PrintStream;
import java.util.Properties;
import javax.swing.*;
import org.jdesktop.layout.GroupLayout;

public class testeJeff extends JFrame
{

    private JButton jButton1;
    private JButton jButton2;
    private JMenu jMenu1;
    private JMenu jMenu2;
    private JMenuBar jMenuBar1;
    private JPanel jPanel1;
    private JToolBar jToolBar1;

    public testeJeff()
    {
        initComponents();
    }

    private void initComponents()
    {
        jToolBar1 = new JToolBar();
        jButton1 = new JButton();
        jPanel1 = new JPanel();
        jButton2 = new JButton();
        jMenuBar1 = new JMenuBar();
        jMenu1 = new JMenu();
        jMenu2 = new JMenu();
        setDefaultCloseOperation(3);
        setBackground(new Color(255, 255, 255));
        setForeground(Color.white);
        jToolBar1.setBackground(new Color(255, 255, 255));
        jToolBar1.setOrientation(1);
        jToolBar1.setRollover(true);
        jButton1.setText("jButton1");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(0);
        jButton1.setVerticalTextPosition(3);
        jToolBar1.add(jButton1);
        jPanel1.setBackground(new Color(255, 255, 255));
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(0);
        jButton2.setVerticalTextPosition(3);
        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().add(123, 123, 123).add(jButton2, -2, 446, -2).addContainerGap(90, 32767)));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().addContainerGap().add(jButton2, -2, 199, -2).addContainerGap(43, 32767)));
        jMenu1.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/bt_abrir.png")));
        jMenuBar1.add(jMenu1);
        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);
        setJMenuBar(jMenuBar1);
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(jToolBar1, -2, 108, -2).add(18, 18, 18).add(jPanel1, -2, -1, -2).addContainerGap(-1, 32767)));
        layout.setVerticalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().add(56, 56, 56).add(layout.createParallelGroup(1).add(layout.createSequentialGroup().add(jToolBar1, -2, 251, -2).addContainerGap()).add(layout.createSequentialGroup().add(jPanel1, -1, -1, 32767).addContainerGap(65, 32767)))));
        pack();
    }

    public static void main(String args[])
    {
        Properties p = System.getProperties();
        System.out.println(System.getProperties().getProperty("http.proxyPort"));
    }
}
