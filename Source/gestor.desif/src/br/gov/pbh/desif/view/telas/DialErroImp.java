/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  br.gov.pbh.des.componentes.table.ColunaJTable
 *  br.gov.pbh.des.componentes.table.CustomTableColumnModel
 *  br.gov.pbh.des.componentes.table.CustomTableModel
 *  br.gov.pbh.des.componentes.table.TableDes
 *  br.gov.pbh.des.componentes.utils.DesLookandFeel
 *  br.gov.pbh.des.componentes.utils.SwingUtils
 *  org.jdesktop.layout.GroupLayout
 *  org.jdesktop.layout.GroupLayout$Group
 *  org.jdesktop.layout.GroupLayout$ParallelGroup
 *  org.jdesktop.layout.GroupLayout$SequentialGroup
 */
package br.gov.pbh.desif.view.telas;

import br.gov.pbh.des.componentes.table.ColunaJTable;
import br.gov.pbh.des.componentes.table.CustomTableColumnModel;
import br.gov.pbh.des.componentes.table.CustomTableModel;
import br.gov.pbh.des.componentes.table.TableDes;
import br.gov.pbh.des.componentes.utils.DesLookandFeel;
import br.gov.pbh.des.componentes.utils.SwingUtils;
import br.gov.pbh.desif.control.Controle;
import br.gov.pbh.desif.model.pojo.Erro;
import br.gov.pbh.desif.model.pojo.ErroSolucao;
import br.gov.pbh.desif.model.pojo.SistemaErro;
import br.gov.pbh.desif.model.pojo.Solucao;
import br.gov.pbh.desif.model.pojo.base.AbstractSistemaErro;
import br.gov.pbh.desif.model.registros.RegUtil;
import br.gov.pbh.desif.service.relatorios.ErrosVO;
import br.gov.pbh.desif.service.relatorios.RelatorioErros;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Frame;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import org.jdesktop.layout.GroupLayout;

public class DialErroImp
extends JDialog {
    private Controle controle;
    private Erro erro;
    private static long pagina;
    private static long contSistemaErro;
    private static int delta;
    private static long numPaginas;
    private boolean resto;
    private JButton btnAnterior;
    private JButton btnPrimeiro;
    private JButton btnProximo;
    private JButton btnRelatorioErros;
    private JButton btnUltimo;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane3;
    private JLabel jlbApresentacaoNomeArq;
    private JLabel jlbDetErro;
    private JLabel jlbListErro;
    private JLabel jlbNomeArquivo;
    private JTextPane jtpErro;
    private TableDes tblErros;

    public DialErroImp(Frame parent, boolean modal) {
        super(parent, modal);
        pagina = 0L;
        this.initComponents();
        this.controle = new Controle();
        this.construirTabela();
        this.jlbNomeArquivo.setText(RegUtil.nomeArq);
        this.paginacao();
        this.setInicioBotoesPaginacao();
        SwingUtils.getInstance().centralizar((JDialog)this);
        DesLookandFeel.getInstance().formatarJButtons(new JButton[]{this.btnAnterior, this.btnPrimeiro, this.btnProximo, this.btnUltimo, this.btnRelatorioErros});
    }

    public void setInicioBotoesPaginacao() {
        if (numPaginas == 0L) {
            this.btnProximo.setEnabled(false);
            this.btnAnterior.setEnabled(false);
            this.btnPrimeiro.setEnabled(false);
            this.btnUltimo.setEnabled(false);
        } else {
            this.btnAnterior.setEnabled(false);
            this.btnPrimeiro.setEnabled(false);
        }
    }

    public void paginacao() {
        contSistemaErro = this.controle.countSistemaErro();
        numPaginas = contSistemaErro / (long)delta;
        if (contSistemaErro % (long)delta == 0L) {
            this.jLabel2.setText("" + numPaginas + "");
            this.resto = false;
        } else {
            long setarLabelTotalPaginas = numPaginas + 1L;
            this.jLabel2.setText("" + setarLabelTotalPaginas + "");
            this.resto = true;
        }
    }

    private void removerCodErro() {
        ColunaJTable coluna = (ColunaJTable)this.tblErros.getColumnModel().getColumn(6);
        ColunaJTable coluna1 = (ColunaJTable)this.tblErros.getColumnModel().getColumn(5);
        this.tblErros.removerColuna(coluna);
        this.tblErros.removerColuna(coluna1);
    }

    private void construirTabela() {
        this.tblErros.getSelectionModel().addListSelectionListener(new ListSelectionListener(){

            @Override
            public void valueChanged(ListSelectionEvent evt) {
                DialErroImp.this.tblErrosValueChanged(evt);
            }
        });
        this.tblErros.setColumnModel((TableColumnModel)new CustomTableColumnModel((TableColumn[])this.inicializarColunas()));
        this.tblErros.setModel((TableModel)new CustomTableModel());
        this.tblErros.setDados(this.getDados(), this.ordemColunas());
        this.jtpErro.setText("Selecione uma linha para obter a descri\u00e7\u00e3o do erro.");
        this.removerCodErro();
    }

    private List ordemColunas() {
        ArrayList<String> ordem = new ArrayList<String>();
        ordem.add("Tipo Erro");
        ordem.add("Linha");
        ordem.add("Campo");
        ordem.add("Registro");
        ordem.add("Nome Campo");
        ordem.add("valErro");
        ordem.add("ce");
        return ordem;
    }

    private void tblErrosValueChanged(ListSelectionEvent evt) {
        this.detalharErro();
    }

    private ColunaJTable[] inicializarColunas() {
        ColunaJTable[] colunas = new ColunaJTable[7];
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

    public List getDados() {
        String tipoErro = "";
        List aux = this.controle.buscaSistemaErro(pagina);
        ArrayList mat = new ArrayList();
        for (int i = 0; i < aux.size(); ++i) {
            ArrayList<String> dados = new ArrayList<String>();
            if (((SistemaErro)aux.get(i)).getTipoErro().intValue() == 1) {
                tipoErro = "Erro Estrutural";
            } else if (((SistemaErro)aux.get(i)).getTipoErro().intValue() == 2) {
                tipoErro = "Erro Valida\u00e7\u00e3o";
            }
            dados.add(tipoErro);
            dados.add(((SistemaErro)aux.get(i)).getLinha() + "");
            dados.add(((SistemaErro)aux.get(i)).getColuna() + "");
            dados.add(((SistemaErro)aux.get(i)).getRegistro());
            dados.add(((SistemaErro)aux.get(i)).getNomeCampo());
            dados.add(((SistemaErro)aux.get(i)).getValorCampoErro());
            dados.add(((SistemaErro)aux.get(i)).getErro().getId());
            mat.add(dados);
        }
        return mat;
    }

    private void detalharErro() {
        if (this.tblErros.getSelectedRow() >= 0 && this.tblErros.getSelectedColumn() >= 0) {
            String cod = (String)this.tblErros.getModel().getValueAt(this.tblErros.getSelectedRow(), this.tblErros.getModel().getColumnCount() - 1);
            String valErro = (String)this.tblErros.getModel().getValueAt(this.tblErros.getSelectedRow(), this.tblErros.getModel().getColumnCount() - 2);
            this.erro = (Erro)this.buscarErroCodigo(cod);
            this.jtpErro.setEditable(false);
            this.jtpErro.setFont(new Font("Arial", 0, 11));
            this.jtpErro.setText(this.construirTexto(this.erro, valErro));
            this.jtpErro.setCaretPosition(0);
        }
    }

    public void proximoPaginacao() {
        double pagAux = pagina + 1L;
        if (pagAux <= (double)numPaginas) {
            ++pagina;
        }
        if (pagAux == (double)numPaginas) {
            this.btnProximo.setEnabled(false);
            this.btnUltimo.setEnabled(false);
        }
        if (pagina != 0L && !this.btnAnterior.isEnabled()) {
            this.btnAnterior.setEnabled(true);
            this.btnPrimeiro.setEnabled(true);
        }
        this.setLabelPagina("" + (pagina + 1L) + "");
        if (pagAux + 1.0 == (double)numPaginas && !this.resto) {
            this.btnProximo.setEnabled(false);
            this.btnUltimo.setEnabled(false);
        }
        this.construirTabela();
    }

    public void anteriorPaginacao() {
        if (pagina > 0L) {
            // empty if block
        }
        if (--pagina == 0L) {
            this.btnAnterior.setEnabled(false);
            this.btnPrimeiro.setEnabled(false);
            this.btnProximo.setEnabled(true);
            this.btnUltimo.setEnabled(true);
        }
        if (pagina != 0L && !this.btnProximo.isEnabled()) {
            this.btnAnterior.setEnabled(true);
            this.btnPrimeiro.setEnabled(true);
        }
        if (pagina != numPaginas && !this.btnProximo.isEnabled()) {
            this.btnProximo.setEnabled(true);
            this.btnUltimo.setEnabled(true);
        }
        this.setLabelPagina("" + (pagina + 1L) + "");
        this.construirTabela();
    }

    public void inicialPaginacao() {
        pagina = 0L;
        this.btnAnterior.setEnabled(false);
        this.btnPrimeiro.setEnabled(false);
        if (!this.btnProximo.isEnabled()) {
            this.btnProximo.setEnabled(true);
            this.btnUltimo.setEnabled(true);
        }
        this.setLabelPagina("" + (pagina + 1L) + "");
        this.construirTabela();
    }

    public void finalPaginacao() {
        pagina = numPaginas;
        this.btnProximo.setEnabled(false);
        this.btnUltimo.setEnabled(false);
        if (pagina != 0L && !this.btnAnterior.isEnabled()) {
            this.btnAnterior.setEnabled(true);
            this.btnPrimeiro.setEnabled(true);
        }
        if (this.resto) {
            this.setLabelPagina("" + (pagina + 1L) + "");
        } else {
            this.setLabelPagina("" + pagina + "");
            --pagina;
        }
        this.construirTabela();
    }

    public void setLabelPagina(String novoValor) {
        this.jLabel1.setText(novoValor);
    }

    private Object buscarErroCodigo(String txt) {
        return this.controle.buscaErroCodigo(txt);
    }

    private String construirTexto(Erro e, String valorErro) {
        Iterator it;
        ErroSolucao erroSol;
        Set l;
        String resp = "Codigo Erro : " + e.getId() + ".<br>Descri\u00e7\u00e3o do Erro : " + e.getMensagem() + ".<br>";
        if (valorErro != null) {
            resp = resp + "<font color=\"#FF0000\">" + valorErro + "</font><br>";
        }
        if (e.getErroSolucaos().size() > 0 && !(erroSol = (ErroSolucao)(it = (l = e.getErroSolucaos()).iterator()).next()).getSolucao().getDescSolucao().equals("")) {
            resp = resp + "Solu\u00e7\u00e3o do Erro : " + erroSol.getSolucao().getDescSolucao() + "<br>";
        }
        return resp;
    }

    private void initComponents() {
        this.jlbDetErro = new JLabel();
        this.jlbListErro = new JLabel();
        this.jlbApresentacaoNomeArq = new JLabel();
        this.jlbNomeArquivo = new JLabel();
        this.jScrollPane3 = new JScrollPane();
        this.jtpErro = new JTextPane();
        this.btnProximo = new JButton();
        this.btnAnterior = new JButton();
        this.btnPrimeiro = new JButton();
        this.btnUltimo = new JButton();
        this.jLabel1 = new JLabel();
        this.jLabel2 = new JLabel();
        this.jLabel3 = new JLabel();
        this.btnRelatorioErros = new JButton();
        this.jScrollPane1 = new JScrollPane();
        this.tblErros = new TableDes();
        this.setDefaultCloseOperation(2);
        this.setTitle("Erro");
        this.jlbDetErro.setText("Detalhe Erro");
        this.jlbListErro.setText("Lista de Erros");
        this.jlbApresentacaoNomeArq.setText("Nome Arquivo : ");
        this.jlbNomeArquivo.setText("nomeArq");
        this.jtpErro.setContentType("text/html");
        this.jScrollPane3.setViewportView(this.jtpErro);
        this.btnProximo.setBackground(new Color(255, 255, 255));
        this.btnProximo.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/bt_proximo.gif")));
        this.btnProximo.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                DialErroImp.this.btnProximoActionPerformed(evt);
            }
        });
        this.btnAnterior.setBackground(new Color(255, 255, 255));
        this.btnAnterior.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/bt_anterior.gif")));
        this.btnAnterior.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                DialErroImp.this.btnAnteriorActionPerformed(evt);
            }
        });
        this.btnPrimeiro.setBackground(new Color(255, 255, 255));
        this.btnPrimeiro.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/bt_primeira_pagina.gif")));
        this.btnPrimeiro.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                DialErroImp.this.btnPrimeiroActionPerformed(evt);
            }
        });
        this.btnUltimo.setBackground(new Color(255, 255, 255));
        this.btnUltimo.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/bt_ultima_pagina.gif")));
        this.btnUltimo.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                DialErroImp.this.btnUltimoActionPerformed(evt);
            }
        });
        this.jLabel1.setText("1");
        this.jLabel2.setText("2");
        this.jLabel3.setText("/");
        this.btnRelatorioErros.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/relatorio-erros.gif")));
        this.btnRelatorioErros.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                DialErroImp.this.btnRelatorioErrosActionPerformed(evt);
            }
        });
        this.jScrollPane1.setViewportView((Component)this.tblErros);
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout((LayoutManager)layout);
        layout.setHorizontalGroup((GroupLayout.Group)layout.createParallelGroup(1).add((GroupLayout.Group)layout.createSequentialGroup().addContainerGap().add((GroupLayout.Group)layout.createParallelGroup(1).add((Component)this.jScrollPane3, -1, 607, 32767).add(2, (GroupLayout.Group)layout.createSequentialGroup().add(0, 0, 32767).add((Component)this.btnRelatorioErros)).add((GroupLayout.Group)layout.createSequentialGroup().add((GroupLayout.Group)layout.createParallelGroup(1).add((GroupLayout.Group)layout.createSequentialGroup().add((Component)this.jlbListErro).add(91, 91, 91).add((Component)this.jlbApresentacaoNomeArq).add(18, 18, 18).add((Component)this.jlbNomeArquivo)).add((GroupLayout.Group)layout.createSequentialGroup().add((Component)this.jlbDetErro).add(117, 117, 117).add((Component)this.btnPrimeiro, -2, 22, -2).addPreferredGap(0).add((Component)this.btnAnterior, -2, 22, -2).add(18, 18, 18).add((Component)this.jLabel1).addPreferredGap(0).add((Component)this.jLabel3).addPreferredGap(0).add((Component)this.jLabel2).add(18, 18, 18).add((Component)this.btnProximo, -2, 20, -2).addPreferredGap(0).add((Component)this.btnUltimo, -2, 22, -2))).add(0, 0, 32767)).add((Component)this.jScrollPane1)).addContainerGap()));
        layout.setVerticalGroup((GroupLayout.Group)layout.createParallelGroup(1).add(2, (GroupLayout.Group)layout.createSequentialGroup().addContainerGap(12, 32767).add((GroupLayout.Group)layout.createParallelGroup(3).add((Component)this.jlbListErro).add((Component)this.jlbApresentacaoNomeArq).add((Component)this.jlbNomeArquivo)).addPreferredGap(0).add((Component)this.jScrollPane1, -2, 165, -2).addPreferredGap(0).add((GroupLayout.Group)layout.createParallelGroup(1).add(2, (Component)this.jlbDetErro).add((Component)this.btnPrimeiro, -2, 20, -2).add((GroupLayout.Group)layout.createParallelGroup(2).add((GroupLayout.Group)layout.createParallelGroup(3).add((Component)this.jLabel1).add((Component)this.jLabel2).add((Component)this.jLabel3)).add((Component)this.btnAnterior, -2, 20, -2)).add((GroupLayout.Group)layout.createParallelGroup(2, false).add(1, (Component)this.btnProximo, 0, 0, 32767).add(1, (Component)this.btnUltimo, -2, 20, -2))).addPreferredGap(0).add((Component)this.jScrollPane3, -2, 101, -2).addPreferredGap(0).add((Component)this.btnRelatorioErros)));
        this.pack();
    }

    private void btnProximoActionPerformed(ActionEvent evt) {
        this.proximoPaginacao();
    }

    private void btnAnteriorActionPerformed(ActionEvent evt) {
        this.anteriorPaginacao();
    }

    private void btnPrimeiroActionPerformed(ActionEvent evt) {
        this.inicialPaginacao();
    }

    private void btnUltimoActionPerformed(ActionEvent evt) {
        this.finalPaginacao();
    }

    private void btnRelatorioErrosActionPerformed(ActionEvent evt) {
        ArrayList<AbstractSistemaErro> erros = new ArrayList<AbstractSistemaErro>();
        int i = 0;
        while ((long)i < numPaginas + 1L) {
            List aux = this.controle.buscaSistemaErro(pagina);
            erros.addAll(aux);
            ++i;
        }
        ErrosVO errosVO = new ErrosVO();
        errosVO.setSistemaErros(erros);
        System.out.println(erros.size());
        RelatorioErros relatorioErros = new RelatorioErros(errosVO);
        relatorioErros.gerarRelatorioErros();
    }

    static {
        contSistemaErro = 0L;
        delta = 10;
        numPaginas = 0L;
    }

}

