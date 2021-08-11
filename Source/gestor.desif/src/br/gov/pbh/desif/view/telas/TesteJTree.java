/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.view.telas;

import br.gov.pbh.desif.dao.CosifDao;
import br.gov.pbh.desif.dao.CosifPaiFilhoDao;
import br.gov.pbh.desif.dao.PgccsPaiFilhoDao;
import br.gov.pbh.desif.dao.PlanoGeralContaComentadoDao;
import br.gov.pbh.desif.dao.impl.CosifDaoImpl;
import br.gov.pbh.desif.dao.impl.CosifPaiFilhoDaoImpl;
import br.gov.pbh.desif.dao.impl.PgccsPaiFilhoDaoImpl;
import br.gov.pbh.desif.dao.impl.PlanoGeralContaComentadoDaoImpl;
import br.gov.pbh.desif.model.pojo.Cosif;
import br.gov.pbh.desif.model.pojo.PlanoGeralContaComentado;
import java.awt.Component;
import java.awt.Container;
import java.awt.LayoutManager;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

public class TesteJTree
extends JFrame {
    PlanoGeralContaComentadoDao pgccDao;
    PgccsPaiFilhoDao pf;
    CosifDao cosifDao;
    CosifPaiFilhoDao cpf;
    Integer cont = 0;
    List listaGeral = new ArrayList();
    List geral = new ArrayList();
    int count = 0;
    String tipoArvore;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JTree jTree1;
    private JTextArea textAreaExibicao;

    public TesteJTree() {
        this.tipoArvore = this.mostraPainelInicializacao();
        if (this.tipoArvore.equals("1")) {
            this.cosifDao = new CosifDaoImpl();
            this.cpf = new CosifPaiFilhoDaoImpl();
        } else {
            this.pgccDao = new PlanoGeralContaComentadoDaoImpl();
            this.pf = new PgccsPaiFilhoDaoImpl();
        }
        this.initComponents();
        this.jTree1.setSelectionRow(0);
    }

    private DefaultMutableTreeNode getNodo() {
        return this.startDados();
    }

    public String mostraPainelInicializacao() {
        this.tipoArvore = null;
        this.tipoArvore = this.count == 0 ? JOptionPane.showInputDialog("Entre com um numero \n1- \u00c1rvore Cosif \n2- \u00c1rvore PGCC") : JOptionPane.showInputDialog("Deve ser digitado um dos numeros abaixo: \nEntre com um numero \n1- \u00c1rvore Cosif \n2- \u00c1rvore PGCC");
        if (this.tipoArvore == null) {
            System.exit(0);
        } else if (!this.tipoArvore.trim().equals("1") & !this.tipoArvore.trim().equals("2")) {
            ++this.count;
            this.mostraPainelInicializacao();
        }
        return this.tipoArvore;
    }

    public DefaultMutableTreeNode startDados() {
        List a = this.inicializarDados();
        if (this.tipoArvore.equals("1")) {
            Cosif p = (Cosif)a.get(0);
            DefaultMutableTreeNode pai = new DefaultMutableTreeNode(p.getNumeroContaCosif());
            return this.montar(p.getNumeroContaCosif(), pai);
        }
        PlanoGeralContaComentado p = (PlanoGeralContaComentado)a.get(0);
        DefaultMutableTreeNode pai = new DefaultMutableTreeNode(p.getConta());
        return this.montar(p.getConta(), pai);
    }

    public List inicializarDados() {
        if (this.tipoArvore.equals("1")) {
            List raiz = this.cosifDao.buscarRaizCosif();
            System.out.println("tamanho raiz Cosif => " + raiz.size());
            return raiz;
        }
        List raiz = this.pgccDao.buscarRaizArvore();
        System.out.println("tamanho raiz PGCC => " + raiz.size());
        return raiz;
    }

    private DefaultMutableTreeNode montar(String conta, DefaultMutableTreeNode no) {
        if (this.tipoArvore.equals("1")) {
            Cosif cosif = null;
            List galhosResultantes = null;
            galhosResultantes = this.cosifDao.buscarGalhos(conta);
            for (int i = 0; i < galhosResultantes.size(); ++i) {
                cosif = (Cosif)galhosResultantes.get(i);
                if (this.cosifDao.buscarGalhos(cosif.getNumeroContaCosif()).size() > 0) {
                    DefaultMutableTreeNode noPai = new DefaultMutableTreeNode(cosif.getNumeroContaCosif());
                    this.montar(cosif.getNumeroContaCosif(), noPai);
                    no.add(noPai);
                    continue;
                }
                DefaultMutableTreeNode folha = new DefaultMutableTreeNode(cosif.getNumeroContaCosif());
                no.add(folha);
            }
            return no;
        }
        PlanoGeralContaComentado pgcc = null;
        List galhosResultantes = null;
        galhosResultantes = this.pgccDao.buscarGalhos(conta);
        for (int i = 0; i < galhosResultantes.size(); ++i) {
            pgcc = (PlanoGeralContaComentado)galhosResultantes.get(i);
            if (this.pgccDao.buscarGalhos(pgcc.getConta()).size() > 0) {
                DefaultMutableTreeNode noPai = new DefaultMutableTreeNode(pgcc.getConta());
                this.montar(pgcc.getConta(), noPai);
                no.add(noPai);
                continue;
            }
            DefaultMutableTreeNode folha = new DefaultMutableTreeNode(pgcc.getConta());
            no.add(folha);
        }
        return no;
    }

    public void escreverTextArea(Object o) {
        if (this.tipoArvore.equals("1")) {
            Cosif cos = (Cosif)o;
            String txt = "Conta cosif =>                     " + cos.getNumeroContaCosif() + "\nN\u00edvle Cosif =>                      " + cos.getNumNivel() + "\nConta Cosif Superior =>   " + cos.getNumeroContaSuperior() + "\nNome Conta Cosif =>       " + cos.getNomeContaCosif();
            this.textAreaExibicao.setText(txt);
        } else {
            PlanoGeralContaComentado pgcc = (PlanoGeralContaComentado)o;
            String txt = "Conta Pgcc =>                     " + pgcc.getConta() + "\nN\u00edvle Pgcc =>                      " + pgcc.getNivel() + "\nNome Conta Pgcc Superior =>       " + pgcc.getContaSupe() + "\nConta Pgcc Cosif =>   " + pgcc.getContaCosif() + "\nConta Pgcc Descricao =>   " + pgcc.getDescConta() + "\nConta Pgcc Nome =>   " + pgcc.getNome();
            this.textAreaExibicao.setText(txt);
        }
    }

    public void descreverInformacoesConta(String conta) {
        Object resp = this.buscaDadosConta(conta);
        this.escreverTextArea(resp);
    }

    public Object buscaDadosConta(String conta) {
        if (this.tipoArvore.equals("1")) {
            List result = this.cosifDao.findField("numeroContaCosif", conta);
            return result.get(0);
        }
        List result = this.pgccDao.findField("conta", conta);
        return result.get(0);
    }

    private void initComponents() {
        this.jScrollPane1 = new JScrollPane();
        this.jTree1 = new JTree(this.getNodo());
        this.jScrollPane2 = new JScrollPane();
        this.textAreaExibicao = new JTextArea();
        this.setDefaultCloseOperation(2);
        this.jTree1.addTreeSelectionListener(new TreeSelectionListener(){

            @Override
            public void valueChanged(TreeSelectionEvent evt) {
                TesteJTree.this.jTree1ValueChanged(evt);
            }
        });
        this.jScrollPane1.setViewportView(this.jTree1);
        this.textAreaExibicao.setEditable(false);
        this.textAreaExibicao.setColumns(20);
        this.textAreaExibicao.setRows(5);
        this.textAreaExibicao.setText("Para obter informa\u00e7\u00f5es sobre a conta \nclique em uma das contas da \u00e1rvore ao lado.");
        this.textAreaExibicao.setToolTipText("Descri\u00e7\u00e3o da conta");
        this.jScrollPane2.setViewportView(this.textAreaExibicao);
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jScrollPane1, -2, 191, -2).addGap(18, 18, 18).addComponent(this.jScrollPane2, -1, 292, 32767).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false).addComponent(this.jScrollPane2, GroupLayout.Alignment.LEADING).addComponent(this.jScrollPane1, GroupLayout.Alignment.LEADING)).addContainerGap(-1, 32767)));
        this.pack();
    }

    private void jTree1ValueChanged(TreeSelectionEvent evt) {
        TreePath[] paths = this.jTree1.getSelectionPaths();
        if (paths != null) {
            String registro = paths[0].getLastPathComponent().toString();
            System.out.println("registro => " + registro);
            this.descreverInformacoesConta(registro);
        }
    }

}

