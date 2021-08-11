
package br.gov.pbh.desif.view.telas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

public class Arvore extends JFrame
    implements ActionListener
{

    private JTextField campo;
    private JButton botao;
    private JPanel painelCima;
    private JPanel painelBaixo;
    private JTree arvore;

    public Arvore()
    {
        super("Browser");
        getContentPane().setLayout(new BorderLayout());
        campo = new JTextField();
        botao = new JButton("Procurar");
        painelCima = new JPanel(new BorderLayout());
        painelBaixo = new JPanel(new GridLayout(1, 1));
        arvore = new JTree();
        botao.addActionListener(this);
        campo.addActionListener(this);
        painelCima.add(campo, "Center");
        painelCima.add(botao, "East");
        getContentPane().add(painelCima, "North");
        getContentPane().add(painelBaixo, "Center");
        setSize(250, 350);
        setVisible(true);
        setDefaultCloseOperation(3);
    }

    public void actionPerformed(ActionEvent e)
    {
        DefaultMutableTreeNode pai = new DefaultMutableTreeNode(campo.getText());
        varre(campo.getText(), pai);
        arvore = new JTree(pai);
        painelBaixo.removeAll();
        painelBaixo.add(new JScrollPane(arvore));
        getContentPane().validate();
    }

    public void varre(String base, DefaultMutableTreeNode no)
    {
        File diretorio = new File(base);
        File conteudo[] = diretorio.listFiles();
        for(int i = 0; i < conteudo.length; i++)
            if(conteudo[i].isFile())
            {
                DefaultMutableTreeNode arquivo = new DefaultMutableTreeNode(conteudo[i].getName());
                no.add(arquivo);
            } else
            {
                DefaultMutableTreeNode dir = new DefaultMutableTreeNode(conteudo[i].getName());
                varre(conteudo[i].toString(), dir);
                no.add(dir);
            }

    }

    public static void main(String args[])
    {
        new Arvore();
    }
}
