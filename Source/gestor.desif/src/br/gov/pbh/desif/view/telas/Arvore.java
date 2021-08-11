/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.view.telas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

public class Arvore
extends JFrame
implements ActionListener {
    private JTextField campo;
    private JButton botao;
    private JPanel painelCima;
    private JPanel painelBaixo;
    private JTree arvore;

    public Arvore() {
        super("Browser");
        this.getContentPane().setLayout(new BorderLayout());
        this.campo = new JTextField();
        this.botao = new JButton("Procurar");
        this.painelCima = new JPanel(new BorderLayout());
        this.painelBaixo = new JPanel(new GridLayout(1, 1));
        this.arvore = new JTree();
        this.botao.addActionListener(this);
        this.campo.addActionListener(this);
        this.painelCima.add((Component)this.campo, "Center");
        this.painelCima.add((Component)this.botao, "East");
        this.getContentPane().add((Component)this.painelCima, "North");
        this.getContentPane().add((Component)this.painelBaixo, "Center");
        this.setSize(250, 350);
        this.setVisible(true);
        this.setDefaultCloseOperation(3);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DefaultMutableTreeNode pai = new DefaultMutableTreeNode(this.campo.getText());
        this.varre(this.campo.getText(), pai);
        this.arvore = new JTree(pai);
        this.painelBaixo.removeAll();
        this.painelBaixo.add(new JScrollPane(this.arvore));
        this.getContentPane().validate();
    }

    public void varre(String base, DefaultMutableTreeNode no) {
        File diretorio = new File(base);
        File[] conteudo = diretorio.listFiles();
        for (int i = 0; i < conteudo.length; ++i) {
            if (conteudo[i].isFile()) {
                DefaultMutableTreeNode arquivo = new DefaultMutableTreeNode(conteudo[i].getName());
                no.add(arquivo);
                continue;
            }
            DefaultMutableTreeNode dir = new DefaultMutableTreeNode(conteudo[i].getName());
            this.varre(conteudo[i].toString(), dir);
            no.add(dir);
        }
    }

    public static void main(String[] args) {
        new Arvore();
    }
}

