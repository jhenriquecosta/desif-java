/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  br.gov.pbh.des.componentes.table.ColunaJTable
 *  br.gov.pbh.des.componentes.table.CustomTableColumnModel
 *  br.gov.pbh.des.componentes.table.CustomTableModel
 *  br.gov.pbh.des.componentes.table.TableDes
 *  br.gov.pbh.des.componentes.utils.DesLookandFeel
 */
package br.gov.pbh.desif.view.telas;

import br.gov.pbh.des.componentes.table.ColunaJTable;
import br.gov.pbh.des.componentes.table.CustomTableColumnModel;
import br.gov.pbh.des.componentes.table.CustomTableModel;
import br.gov.pbh.des.componentes.table.TableDes;
import br.gov.pbh.des.componentes.utils.DesLookandFeel;
import br.gov.pbh.desif.control.Controle;
import br.gov.pbh.desif.model.pojo.GuiaEstaticaVO;
import br.gov.pbh.desif.model.pojo.IssqnMensal;
import br.gov.pbh.desif.service.contexto.Contexto;
import br.gov.pbh.desif.view.telas.DialGuia;
import br.gov.pbh.desif.view.telas.FrmPrincipal;
import br.gov.pbh.desif.view.util.SwingUtils;
import java.awt.Component;
import java.awt.Container;
import java.awt.Frame;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class PanFiltroGuia
extends JPanel {
    private Controle controle;
    private List aux;
    private JButton btnVoltar;
    private JLabel jLabel1;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane3;
    private TableDes tableDes1;
    private TableDes tblGuias;

    public PanFiltroGuia() {
        this.initComponents();
        this.construirTabela();
        DesLookandFeel.getInstance().formatarJButtons(new JButton[]{this.btnVoltar});
    }

    private void initComponents() {
        this.jScrollPane1 = new JScrollPane();
        this.tableDes1 = new TableDes();
        this.jLabel1 = new JLabel();
        this.btnVoltar = new JButton();
        this.jScrollPane3 = new JScrollPane();
        this.tblGuias = new TableDes();
        this.jScrollPane1.setViewportView((Component)this.tableDes1);
        this.jLabel1.setText("Clique duas vezes sobre a linha que deseja calcular a guia.");
        this.btnVoltar.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/voltar.gif")));
        this.btnVoltar.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                PanFiltroGuia.this.btnVoltarActionPerformed(evt);
            }
        });
        this.tblGuias.addMouseListener((MouseListener)new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent evt) {
                PanFiltroGuia.this.tblGuiasMouseClicked(evt);
            }
        });
        this.jScrollPane3.setViewportView((Component)this.tblGuias);
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGap(0, 0, 32767).addComponent(this.btnVoltar)).addGroup(layout.createSequentialGroup().addComponent(this.jLabel1).addGap(0, 0, 32767)).addComponent(this.jScrollPane3, -1, 465, 32767)).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel1).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jScrollPane3, -2, 300, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.btnVoltar).addContainerGap()));
    }

    private void construirTabela() {
        this.tblGuias.setColumnModel((TableColumnModel)new CustomTableColumnModel((TableColumn[])this.inicializarColunas()));
        this.tblGuias.setModel((TableModel)new CustomTableModel());
        this.tblGuias.setDados(this.getDados(), this.ordemColunas());
    }

    private List ordemColunas() {
        ArrayList<String> ordem = new ArrayList<String>();
        ordem.add("CNPJ Depend\u00eancia");
        ordem.add("Receita Tribut\u00e1vel");
        ordem.add("Dedu\u00e7\u00f5es");
        ordem.add("Base de calculo");
        ordem.add("ISS Devido");
        ordem.add("Incentivos");
        ordem.add("Cr\u00e9ditos a compensar");
        ordem.add("ISSQN Recolhido");
        ordem.add("ISSQN a recolher");
        return ordem;
    }

    private ColunaJTable[] inicializarColunas() {
        ColunaJTable[] colunas = new ColunaJTable[10];
        ColunaJTable coluna0 = new ColunaJTable(false, 100, "CNPJ Depend\u00eancia");
        ColunaJTable coluna1 = new ColunaJTable(false, 120, "Receita Tribut\u00e1vel");
        ColunaJTable coluna2 = new ColunaJTable(false, 70, "Dedu\u00e7\u00f5es");
        ColunaJTable coluna3 = new ColunaJTable(false, 100, "Base de calculo");
        ColunaJTable coluna4 = new ColunaJTable(false, 80, "ISS Devido");
        ColunaJTable coluna5 = new ColunaJTable(false, 80, "ISSQN retido");
        ColunaJTable coluna6 = new ColunaJTable(false, 80, "Incentivos");
        ColunaJTable coluna7 = new ColunaJTable(false, 120, "Cr\u00e9ditos a compensar");
        ColunaJTable coluna8 = new ColunaJTable(false, 100, "ISSQN Recolhido");
        ColunaJTable coluna9 = new ColunaJTable(false, 100, "ISSQN a recolher");
        colunas[0] = coluna0;
        colunas[1] = coluna1;
        colunas[2] = coluna2;
        colunas[3] = coluna3;
        colunas[4] = coluna4;
        colunas[5] = coluna5;
        colunas[6] = coluna6;
        colunas[7] = coluna7;
        colunas[8] = coluna8;
        colunas[9] = coluna9;
        return colunas;
    }

    public List getDados() {
        this.controle = new Controle();
        this.aux = this.controle.buscaFiltrosGuia();
        ArrayList mat = new ArrayList();
        for (int i = 0; i < this.aux.size(); ++i) {
            ArrayList<Object> dados = new ArrayList<Object>();
            dados.add(((IssqnMensal)this.aux.get(i)).getCnpj());
            dados.add(((IssqnMensal)this.aux.get(i)).getValorReceitaDeclaradaConsolidada());
            dados.add(((IssqnMensal)this.aux.get(i)).getValorDeducaoReceitaDeclaradaConsolidada() + ((IssqnMensal)this.aux.get(i)).getValorDeducaoReceitaDeclaradaSubtitulo());
            dados.add(((IssqnMensal)this.aux.get(i)).getValorBaseCalculo());
            dados.add(((IssqnMensal)this.aux.get(i)).getValorIssqnDevido());
            dados.add(((IssqnMensal)this.aux.get(i)).getValorIssqnRetido());
            dados.add(((IssqnMensal)this.aux.get(i)).getValorIncentivoFiscal() + ((IssqnMensal)this.aux.get(i)).getValorIncentivoFiscalSubtitulo());
            dados.add(((IssqnMensal)this.aux.get(i)).getValorCredito());
            dados.add(((IssqnMensal)this.aux.get(i)).getValorIssqnRecolhido());
            dados.add(((IssqnMensal)this.aux.get(i)).getValorIssqnRecolher());
            mat.add(dados);
        }
        return mat;
    }

    private void gerarGuia(MouseEvent evt) {
        if (evt.getClickCount() == 2) {
            String cnpj = (String)this.tblGuias.getValueAt(this.tblGuias.getSelectedRow(), 0);
            Object resp = this.buscaValorListaIssqnMensal(cnpj);
            Object respGuia = this.controle.buscaDadosGuia(cnpj);
            IssqnMensal issqn = (IssqnMensal)resp;
            GuiaEstaticaVO dadosDescritGuia = (GuiaEstaticaVO)respGuia;
            Double txExpediente = this.controle.buscaTaxaExpediente();
            if (issqn.getValorIssqnDevido() > 0.0) {
                Object[] dadosGuia = new Object[]{resp, respGuia, txExpediente};
                DialGuia dialog = new DialGuia(null, true, dadosGuia);
                dialog.setVisible(true);
            } else {
                SwingUtils.msgAlertaEnvio(this, "O calculo da guia n\u00e3o pode ser efetuado, Est\u00e1 \u00e9 uma declara\u00e7\u00e3o sem movimento.");
            }
        }
    }

    private Object buscaValorListaIssqnMensal(String cnpj) {
        Object resp = null;
        for (int i = 0; i < this.aux.size(); ++i) {
            if (!((IssqnMensal)this.aux.get(i)).getCnpj().equals(cnpj)) continue;
            resp = this.aux.get(i);
        }
        return resp;
    }

    public boolean verificaVencimentoProximoAno(Date competencia) {
        boolean resp = false;
        return resp;
    }

    private void tblGuiasMouseClicked(MouseEvent evt) {
        this.gerarGuia(evt);
    }

    private void btnVoltarActionPerformed(ActionEvent evt) {
        ((FrmPrincipal)Contexto.getObject("frmPrincipal")).abrirTela((JPanel)Contexto.getObject("panSucesso"));
    }

}

