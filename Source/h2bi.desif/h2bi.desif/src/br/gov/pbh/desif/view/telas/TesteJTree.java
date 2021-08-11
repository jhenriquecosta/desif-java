
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
import javax.swing.tree.TreePath;

public class TesteJTree extends javax.swing.JFrame
{

    PlanoGeralContaComentadoDao pgccDao;
    PgccsPaiFilhoDao pf;
    CosifDao cosifDao;
    CosifPaiFilhoDao cpf;
    Integer cont;
    List listaGeral;
    List geral;
    int count;
    String tipoArvore;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JTree jTree1;
    private JTextArea textAreaExibicao;

    public TesteJTree()
    {
        cont = Integer.valueOf(0);
        listaGeral = new ArrayList();
        geral = new ArrayList();
        count = 0;
        tipoArvore = mostraPainelInicializacao();
        if(tipoArvore.equals("1"))
        {
            cosifDao = new CosifDaoImpl();
            cpf = new CosifPaiFilhoDaoImpl();
        } else
        {
            pgccDao = new PlanoGeralContaComentadoDaoImpl();
            pf = new PgccsPaiFilhoDaoImpl();
        }
        initComponents();
        jTree1.setSelectionRow(0);
    }

    private DefaultMutableTreeNode getNodo()
    {
        return startDados();
    }

    public String mostraPainelInicializacao()
    {
        tipoArvore = null;
        if(count == 0)
            tipoArvore = JOptionPane.showInputDialog("Entre com um numero \n1- \301rvore Cosif \n2- \301rvore PGCC");
        else
            tipoArvore = JOptionPane.showInputDialog("Deve ser digitado um dos numeros abaixo: \nEntre com um numero \n1- \301rvore Cosif \n2- \301rvore PGCC");
        if(tipoArvore == null)
            System.exit(0);
        else
        if((!tipoArvore.trim().equals("1")) & (!tipoArvore.trim().equals("2")))
        {
            count++;
            mostraPainelInicializacao();
        }
        return tipoArvore;
    }

    public DefaultMutableTreeNode startDados()
    {
        List a = inicializarDados();
        if(tipoArvore.equals("1"))
        {
            Cosif p = (Cosif)a.get(0);
            DefaultMutableTreeNode pai = new DefaultMutableTreeNode(p.getNumeroContaCosif());
            return montar(p.getNumeroContaCosif(), pai);
        } else
        {
            PlanoGeralContaComentado p = (PlanoGeralContaComentado)a.get(0);
            DefaultMutableTreeNode pai = new DefaultMutableTreeNode(p.getConta());
            return montar(p.getConta(), pai);
        }
    }

    public List inicializarDados()
    {
        if(tipoArvore.equals("1"))
        {
            List raiz = cosifDao.buscarRaizCosif();
            System.out.println((new StringBuilder()).append("tamanho raiz Cosif => ").append(raiz.size()).toString());
            return raiz;
        } else
        {
            List raiz = pgccDao.buscarRaizArvore();
            System.out.println((new StringBuilder()).append("tamanho raiz PGCC => ").append(raiz.size()).toString());
            return raiz;
        }
    }

    private DefaultMutableTreeNode montar(String conta, DefaultMutableTreeNode no)
    {
        List galhosResultantes;
        if(tipoArvore.equals("1"))
        {
            Cosif cosif = null;
            galhosResultantes = null;
            galhosResultantes = cosifDao.buscarGalhos(conta);
            for(int i = 0; i < galhosResultantes.size(); i++)
            {
                cosif = (Cosif)galhosResultantes.get(i);
                if(cosifDao.buscarGalhos(cosif.getNumeroContaCosif()).size() > 0)
                {
                    DefaultMutableTreeNode noPai = new DefaultMutableTreeNode(cosif.getNumeroContaCosif());
                    montar(cosif.getNumeroContaCosif(), noPai);
                    no.add(noPai);
                } else
                {
                    DefaultMutableTreeNode folha = new DefaultMutableTreeNode(cosif.getNumeroContaCosif());
                    no.add(folha);
                }
            }

            return no;
        }
        PlanoGeralContaComentado pgcc = null;
        galhosResultantes = null;
        galhosResultantes = pgccDao.buscarGalhos(conta);
        for(int i = 0; i < galhosResultantes.size(); i++)
        {
            pgcc = (PlanoGeralContaComentado)galhosResultantes.get(i);
            if(pgccDao.buscarGalhos(pgcc.getConta()).size() > 0)
            {
                DefaultMutableTreeNode noPai = new DefaultMutableTreeNode(pgcc.getConta());
                montar(pgcc.getConta(), noPai);
                no.add(noPai);
            } else
            {
                DefaultMutableTreeNode folha = new DefaultMutableTreeNode(pgcc.getConta());
                no.add(folha);
            }
        }

        return no;
    }

    public void escreverTextArea(Object o)
    {
        if(tipoArvore.equals("1"))
        {
            Cosif cos = (Cosif)o;
            String txt = (new StringBuilder()).append("Conta cosif =>                     ").append(cos.getNumeroContaCosif()).append("\nN\355vle Cosif =>                      ").append(cos.getNumNivel()).append("\nConta Cosif Superior =>   ").append(cos.getNumeroContaSuperior()).append("\nNome Conta Cosif =>       ").append(cos.getNomeContaCosif()).toString();
            textAreaExibicao.setText(txt);
        } else
        {
            PlanoGeralContaComentado pgcc = (PlanoGeralContaComentado)o;
            String txt = (new StringBuilder()).append("Conta Pgcc =>                     ").append(pgcc.getConta()).append("\nN\355vle Pgcc =>                      ").append(pgcc.getNivel()).append("\nNome Conta Pgcc Superior =>       ").append(pgcc.getContaSupe()).append("\nConta Pgcc Cosif =>   ").append(pgcc.getContaCosif()).append("\nConta Pgcc Descricao =>   ").append(pgcc.getDescConta()).append("\nConta Pgcc Nome =>   ").append(pgcc.getNome()).toString();
            textAreaExibicao.setText(txt);
        }
    }

    public void descreverInformacoesConta(String conta)
    {
        Object resp = buscaDadosConta(conta);
        escreverTextArea(resp);
    }

    public Object buscaDadosConta(String conta)
    {
        if(tipoArvore.equals("1"))
        {
            List result = cosifDao.findField("numeroContaCosif", conta);
            return result.get(0);
        } else
        {
            List result = pgccDao.findField("conta", conta);
            return result.get(0);
        }
    }

    private void initComponents()
    {
        jScrollPane1 = new JScrollPane();
        jTree1 = new JTree(getNodo());
        jScrollPane2 = new JScrollPane();
        textAreaExibicao = new JTextArea();
        setDefaultCloseOperation(3);
        jTree1.addTreeSelectionListener(new TreeSelectionListener() {

            public void valueChanged(TreeSelectionEvent evt)
            {
                jTree1ValueChanged(evt);
            }
        }
);
        jScrollPane1.setViewportView(jTree1);
        textAreaExibicao.setColumns(20);
        textAreaExibicao.setEditable(false);
        textAreaExibicao.setRows(5);
        textAreaExibicao.setText("Para obter informa\347\365es sobre a conta \nclique em uma das contas da \341rvore ao lado.");
        textAreaExibicao.setToolTipText("Descri\347\343o da conta");
        jScrollPane2.setViewportView(textAreaExibicao);
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jScrollPane1, -2, 191, -2).addGap(18, 18, 18).addComponent(jScrollPane2, -1, 292, 32767).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false).addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING).addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)).addContainerGap(-1, 32767)));
        pack();
    }

    private void jTree1ValueChanged(TreeSelectionEvent evt)
    {
        TreePath paths[] = jTree1.getSelectionPaths();
        if(paths != null)
        {
            String registro = paths[0].getLastPathComponent().toString();
            System.out.println((new StringBuilder()).append("registro => ").append(registro).toString());
            descreverInformacoesConta(registro);
        }
    }

}
