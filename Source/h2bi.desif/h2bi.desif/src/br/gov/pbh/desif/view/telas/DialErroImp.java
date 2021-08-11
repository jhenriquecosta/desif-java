
package br.gov.pbh.desif.view.telas;

import br.gov.pbh.des.componentes.utils.DesLookandFeel;
import br.gov.pbh.des.componentes.utils.SwingUtils;
import br.gov.pbh.des.view.components.table.ColunaJTable;
import br.gov.pbh.des.view.components.table.TableDes;
import br.gov.pbh.desif.control.Controle;
import br.gov.pbh.desif.model.pojo.Erro;
import br.gov.pbh.desif.model.pojo.ErroSolucao;
import br.gov.pbh.desif.model.pojo.SistemaErro;
import br.gov.pbh.desif.model.pojo.Solucao;
import br.gov.pbh.desif.model.registros.RegUtil;
import br.gov.pbh.desif.service.relatorios.ErrosVO;
import br.gov.pbh.desif.service.relatorios.RelatorioErros;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.jdesktop.layout.GroupLayout;

public class DialErroImp extends JDialog
{

    private Controle controle;
    private Erro erro;
    private static long pagina;
    private static long contSistemaErro = 0L;
    private static int delta = 10;
    private static long numPaginas = 0L;
    private boolean resto;
    private JButton btnAnterior;
    private JButton btnPrimeiro;
    private JButton btnProximo;
    private JButton btnRelatorioErros;
    private JButton btnUltimo;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JScrollPane jScrollPane2;
    private JScrollPane jScrollPane3;
    private JLabel jlbApresentacaoNomeArq;
    private JLabel jlbDetErro;
    private JLabel jlbListErro;
    private JLabel jlbNomeArquivo;
    private JTextPane jtpErro;
    private TableDes tblErros;

    public DialErroImp(Frame parent, boolean modal)
    {
        super(parent, modal);
        pagina = 0L;
        initComponents();
        controle = new Controle();
        construirTabela();
        jlbNomeArquivo.setText(RegUtil.nomeArq);
        paginacao();
        setInicioBotoesPaginacao();
        SwingUtils.getInstance().centralizar(this);
        DesLookandFeel.getInstance().formatarJButtons(new JButton[] {
            btnAnterior, btnPrimeiro, btnProximo, btnUltimo, btnRelatorioErros
        });
    }

    public void setInicioBotoesPaginacao()
    {
        if(numPaginas == 0L)
        {
            btnProximo.setEnabled(false);
            btnAnterior.setEnabled(false);
            btnPrimeiro.setEnabled(false);
            btnUltimo.setEnabled(false);
        } 
        else
        {
            btnAnterior.setEnabled(false);
            btnPrimeiro.setEnabled(false);
        }
    }

    public void paginacao()
    {
        contSistemaErro = controle.countSistemaErro();
        numPaginas = contSistemaErro / (long)delta;
        if(contSistemaErro % (long)delta == 0L)
        {
            jLabel2.setText((new StringBuilder()).append(numPaginas).append("").toString());
            resto = false;
        } else
        {
            long setarLabelTotalPaginas = numPaginas + 1L;
            jLabel2.setText((new StringBuilder()).append(setarLabelTotalPaginas).append("").toString());
            resto = true;
        }
    }

    private void removerCodErro()
    {
        tblErros.removeColuna("ce");
        tblErros.removeColuna("valErro");
        tblErros.setTableModel();
    }

    private void construirTabela()
    {
        tblErros.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent evt)
            {
                tblErrosValueChanged(evt);
            }

        }
);
        tblErros.setColunas(inicializarColunas());
        tblErros.setDados(getDados());
        tblErros.setTableModel();
        jtpErro.setText("Selecione uma linha para obter a descri\347\343o do erro.");
        removerCodErro();
    }

    private void tblErrosValueChanged(ListSelectionEvent evt)
    {
        detalharErro();
    }

    private ColunaJTable[] inicializarColunas()
    {
        ColunaJTable colunas[] = new ColunaJTable[7];
        ColunaJTable coluna1 = new ColunaJTable(false, 120, "Tipo Erro");
        ColunaJTable coluna2 = new ColunaJTable(false, 120, "Linha");
        ColunaJTable coluna3 = new ColunaJTable(false, 120, "Campo");
        ColunaJTable coluna4 = new ColunaJTable(false, 120, "Registro");
        ColunaJTable coluna5 = new ColunaJTable(false, 120, "Nome Campo");
        ColunaJTable coluna6 = new ColunaJTable(false, 0, "valErro");
        ColunaJTable coluna7 = new ColunaJTable(false, 0, "ce");
        colunas[0] = coluna1;
        colunas[1] = coluna2;
        colunas[2] = coluna3;
        colunas[3] = coluna4;
        colunas[4] = coluna5;
        colunas[5] = coluna6;
        colunas[6] = coluna7;
        return colunas;
    }

    public java.util.List getDados()
    {
        String tipoErro = "";
        java.util.List aux = controle.buscaSistemaErro(pagina);
        java.util.List mat = new ArrayList();
        for(int i = 0; i < aux.size(); i++)
        {
            java.util.List dados = new ArrayList();
            if(((SistemaErro)aux.get(i)).getTipoErro().intValue() == 1)
                tipoErro = "Erro Estrutural";
            else
            if(((SistemaErro)aux.get(i)).getTipoErro().intValue() == 2)
                tipoErro = "Erro Valida\347\343o";
            dados.add(tipoErro);
            dados.add((new StringBuilder()).append(((SistemaErro)aux.get(i)).getLinha()).append("").toString());
            dados.add((new StringBuilder()).append(((SistemaErro)aux.get(i)).getColuna()).append("").toString());
            dados.add(((SistemaErro)aux.get(i)).getRegistro());
            dados.add(((SistemaErro)aux.get(i)).getNomeCampo());
            dados.add(((SistemaErro)aux.get(i)).getValorCampoErro());
            dados.add(((SistemaErro)aux.get(i)).getErro().getId());
            mat.add(dados);
        }

        return mat;
    }

    private void detalharErro()
    {
        if(tblErros.getSelectedRow() >= 0 && tblErros.getSelectedColumn() >= 0)
        {
            String cod = (String)tblErros.getPosicao(tblErros.getSelectedRow(), tblErros.getModel().getColumnCount() + 1);
            String valErro = (String)tblErros.getPosicao(tblErros.getSelectedRow(), tblErros.getModel().getColumnCount());
            erro = (Erro)buscarErroCodigo(cod);
            jtpErro.setEditable(false);
            jtpErro.setFont(new Font("Arial", 0, 11));
            jtpErro.setText(construirTexto(erro, valErro));
            jtpErro.setCaretPosition(0);
        }
    }

    public void proximoPaginacao()
    {
        double pagAux = pagina + 1L;
        if(pagAux <= (double)numPaginas)
            pagina++;
        if(pagAux == (double)numPaginas)
        {
            btnProximo.setEnabled(false);
            btnUltimo.setEnabled(false);
        }
        if(pagina != 0L && !btnAnterior.isEnabled())
        {
            btnAnterior.setEnabled(true);
            btnPrimeiro.setEnabled(true);
        }
        setLabelPagina((new StringBuilder()).append(pagina + 1L).append("").toString());
        if(pagAux + 1.0D == (double)numPaginas && !resto)
        {
            btnProximo.setEnabled(false);
            btnUltimo.setEnabled(false);
        }
        construirTabela();
    }

    public void anteriorPaginacao()
    {
        if(pagina > 0L)
            pagina--;
        if(pagina == 0L)
        {
            btnAnterior.setEnabled(false);
            btnPrimeiro.setEnabled(false);
            btnProximo.setEnabled(true);
            btnUltimo.setEnabled(true);
        }
        if(pagina != 0L && !btnProximo.isEnabled())
        {
            btnAnterior.setEnabled(true);
            btnPrimeiro.setEnabled(true);
        }
        if(pagina != numPaginas && !btnProximo.isEnabled())
        {
            btnProximo.setEnabled(true);
            btnUltimo.setEnabled(true);
        }
        setLabelPagina((new StringBuilder()).append(pagina + 1L).append("").toString());
        construirTabela();
    }

    public void inicialPaginacao()
    {
        pagina = 0L;
        btnAnterior.setEnabled(false);
        btnPrimeiro.setEnabled(false);
        if(!btnProximo.isEnabled())
        {
            btnProximo.setEnabled(true);
            btnUltimo.setEnabled(true);
        }
        setLabelPagina((new StringBuilder()).append(pagina + 1L).append("").toString());
        construirTabela();
    }

    public void finalPaginacao()
    {
        pagina = numPaginas;
        btnProximo.setEnabled(false);
        btnUltimo.setEnabled(false);
        if(pagina != 0L && !btnAnterior.isEnabled())
        {
            btnAnterior.setEnabled(true);
            btnPrimeiro.setEnabled(true);
        }
        if(resto)
        {
            setLabelPagina((new StringBuilder()).append(pagina + 1L).append("").toString());
        } else
        {
            setLabelPagina((new StringBuilder()).append(pagina).append("").toString());
            pagina--;
        }
        construirTabela();
    }

    public void setLabelPagina(String novoValor)
    {
        jLabel1.setText(novoValor);
    }

    private Object buscarErroCodigo(String txt)
    {
        return controle.buscaErroCodigo(txt);
    }

    private String construirTexto(Erro e, String valorErro)
    {
        String resp = (new StringBuilder()).append("Codigo Erro : ").append(e.getId()).append(".<br>").append("Descri\347\343o do Erro : ").append(e.getMensagem()).append(".<br>").toString();
        if(valorErro != null)
            resp = (new StringBuilder()).append(resp).append("<font color=\"#FF0000\">").append(valorErro).append("</font><br>").toString();
        if(e.getErroSolucaos().size() > 0)
        {
            Set l = e.getErroSolucaos();
            Iterator it = l.iterator();
            ErroSolucao erroSol = (ErroSolucao)it.next();
            if(!erroSol.getSolucao().getDescSolucao().equals(""))
                resp = (new StringBuilder()).append(resp).append("Solu\347\343o do Erro : ").append(erroSol.getSolucao().getDescSolucao()).append("<br>").toString();
        }
        return resp;
    }

    private void initComponents()
    {
        jScrollPane2 = new JScrollPane();
        tblErros = new TableDes();
        jlbDetErro = new JLabel();
        jlbListErro = new JLabel();
        jlbApresentacaoNomeArq = new JLabel();
        jlbNomeArquivo = new JLabel();
        jScrollPane3 = new JScrollPane();
        jtpErro = new JTextPane();
        btnProximo = new JButton();
        btnAnterior = new JButton();
        btnPrimeiro = new JButton();
        btnUltimo = new JButton();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        btnRelatorioErros = new JButton();
        setDefaultCloseOperation(2);
        setTitle("Erro");
        tblErros.setModel(new DefaultTableModel(new Object[0][], new String[0]));
        tblErros.setAutoResizeMode(4);
        jScrollPane2.setViewportView(tblErros);
        jlbDetErro.setText("Detalhe Erro");
        jlbListErro.setText("Lista de Erros");
        jlbApresentacaoNomeArq.setText("Nome Arquivo : ");
        jlbNomeArquivo.setText("nomeArq");
        jtpErro.setContentType("text/html");
        jScrollPane3.setViewportView(jtpErro);
        btnProximo.setBackground(new Color(255, 255, 255));
        btnProximo.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/bt_proximo.gif")));
        btnProximo.addActionListener(new ActionListener() {

           public void actionPerformed(ActionEvent evt)
            {
                btnProximoActionPerformed(evt);
            }

           
        }
);
        btnAnterior.setBackground(new Color(255, 255, 255));
        btnAnterior.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/bt_anterior.gif")));
        btnAnterior.addActionListener(new ActionListener() {

     
            public void actionPerformed(ActionEvent evt)
            {
                btnAnteriorActionPerformed(evt);
            }

        }
);
        btnPrimeiro.setBackground(new Color(255, 255, 255));
        btnPrimeiro.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/bt_primeira_pagina.gif")));
        btnPrimeiro.addActionListener(new ActionListener() {

       
            public void actionPerformed(ActionEvent evt)
            {
                btnPrimeiroActionPerformed(evt);
            }

        }
);
        btnUltimo.setBackground(new Color(255, 255, 255));
        btnUltimo.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/bt_ultima_pagina.gif")));
        btnUltimo.addActionListener(new ActionListener() {

         
            public void actionPerformed(ActionEvent evt)
            {
                btnUltimoActionPerformed(evt);
            }

        }
);
        jLabel1.setText("1");
        jLabel2.setText("2");
        jLabel3.setText("/");
        btnRelatorioErros.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/relatorio-erros.gif")));
        btnRelatorioErros.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt)
            {
                btnRelatorioErrosActionPerformed(evt);
            }

        }
);
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(layout.createParallelGroup(1).add(jScrollPane3, -1, 607, 32767).add(jScrollPane2, -1, 607, 32767).add(layout.createSequentialGroup().add(jlbListErro).add(91, 91, 91).add(jlbApresentacaoNomeArq).add(18, 18, 18).add(jlbNomeArquivo)).add(layout.createSequentialGroup().add(jlbDetErro).add(117, 117, 117).add(btnPrimeiro, -2, 22, -2).addPreferredGap(0).add(btnAnterior, -2, 22, -2).add(18, 18, 18).add(jLabel1).addPreferredGap(0).add(jLabel3).addPreferredGap(0).add(jLabel2).add(18, 18, 18).add(btnProximo, -2, 20, -2).addPreferredGap(0).add(btnUltimo, -2, 22, -2)).add(2, btnRelatorioErros)).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(1).add(2, layout.createSequentialGroup().addContainerGap().add(layout.createParallelGroup(3).add(jlbListErro).add(jlbApresentacaoNomeArq).add(jlbNomeArquivo)).addPreferredGap(0).add(jScrollPane2, -1, 155, 32767).addPreferredGap(0).add(layout.createParallelGroup(1).add(2, jlbDetErro).add(btnPrimeiro, -2, 20, -2).add(layout.createParallelGroup(2).add(layout.createParallelGroup(3).add(jLabel1).add(jLabel2).add(jLabel3)).add(btnAnterior, -2, 20, -2)).add(layout.createParallelGroup(2, false).add(1, btnProximo, 0, 0, 32767).add(1, btnUltimo, -2, 20, 32767))).addPreferredGap(0).add(jScrollPane3, -2, 101, -2).addPreferredGap(0).add(btnRelatorioErros).addContainerGap()));
        pack();
    }

    private void btnProximoActionPerformed(ActionEvent evt)
    {
        proximoPaginacao();
    }

    private void btnAnteriorActionPerformed(ActionEvent evt)
    {
        anteriorPaginacao();
    }

    private void btnPrimeiroActionPerformed(ActionEvent evt)
    {
        inicialPaginacao();
    }

    private void btnUltimoActionPerformed(ActionEvent evt)
    {
        finalPaginacao();
    }

    private void btnRelatorioErrosActionPerformed(ActionEvent evt)
    {
        java.util.List erros = new ArrayList();
        for(int i = 0; (long)i < numPaginas + 1L; i++)
        {
            java.util.List aux = controle.buscaSistemaErro(pagina);
            erros.addAll(aux);
        }

        ErrosVO errosVO = new ErrosVO();
        errosVO.setSistemaErros(erros);
        System.out.println(erros.size());
        RelatorioErros relatorioErros = new RelatorioErros(errosVO);
        relatorioErros.gerarRelatorioErros();
    }







}
