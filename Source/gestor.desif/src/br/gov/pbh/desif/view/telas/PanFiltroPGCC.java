/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  br.gov.pbh.des.componentes.utils.DesLookandFeel
 */
package br.gov.pbh.desif.view.telas;

import br.gov.pbh.des.componentes.utils.DesLookandFeel;
import br.gov.pbh.desif.control.Controle;
import br.gov.pbh.desif.model.registros.RegUtil;
import br.gov.pbh.desif.service.contexto.Contexto;
import br.gov.pbh.desif.view.telas.FrmPrincipal;
import br.gov.pbh.desif.view.util.SwingUtils;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;

public class PanFiltroPGCC
extends JPanel {
    private JButton btnGerarRelatorio;
    private JButton btnVoltar;
    private JComboBox jCBCodTribDesif;
    private JComboBox jCBConta;
    private JComboBox jCBContaCosif;
    private JComboBox jCBContaSuperior;
    private JLabel jLabel1;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;

    public PanFiltroPGCC() {
        this.initComponents();
        DesLookandFeel.getInstance().formatarJButtons(new JButton[]{this.btnGerarRelatorio, this.btnVoltar});
    }

    public void initValoresComboBoxCodTributacao(List elementosComboBox) {
        this.jCBCodTribDesif = this.inicializarComboBox(this.jCBCodTribDesif);
        Iterator it = elementosComboBox.iterator();
        while (it.hasNext()) {
            this.jCBCodTribDesif.addItem(it.next().toString());
        }
    }

    public void initValoresComboBoxContas(List elementosComboBox) {
        this.jCBConta = this.inicializarComboBox(this.jCBConta);
        Iterator it = elementosComboBox.iterator();
        while (it.hasNext()) {
            this.jCBConta.addItem(it.next().toString());
        }
    }

    public void initValoresComboBoxContaSuperior(List elementosComboBox) {
        this.jCBContaSuperior = this.inicializarComboBox(this.jCBContaSuperior);
        Iterator it = elementosComboBox.iterator();
        while (it.hasNext()) {
            this.jCBContaSuperior.addItem(it.next().toString());
        }
    }

    public void initValoresComboBoxContaCosif(List elementosComboBox) {
        this.jCBContaCosif = this.inicializarComboBox(this.jCBContaCosif);
        Iterator it = elementosComboBox.iterator();
        while (it.hasNext()) {
            this.jCBContaCosif.addItem(it.next().toString());
        }
    }

    public JComboBox inicializarComboBox(JComboBox jCBEntrada) {
        JComboBox jCBZerado = new JComboBox();
        jCBZerado = jCBEntrada;
        jCBZerado.removeAllItems();
        jCBZerado.setModel(new DefaultComboBoxModel<String>(new String[]{" "}));
        jCBZerado.setSelectedIndex(-1);
        jCBZerado.setSelectedItem(null);
        return jCBZerado;
    }

    private void initComponents() {
        this.btnGerarRelatorio = new JButton();
        this.btnVoltar = new JButton();
        this.jCBCodTribDesif = new JComboBox();
        this.jCBContaCosif = new JComboBox();
        this.jCBContaSuperior = new JComboBox();
        this.jLabel4 = new JLabel();
        this.jLabel5 = new JLabel();
        this.jLabel6 = new JLabel();
        this.jLabel7 = new JLabel();
        this.jCBConta = new JComboBox();
        this.jLabel1 = new JLabel();
        this.setPreferredSize(new Dimension(467, 369));
        this.btnGerarRelatorio.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/bt_gerar_relatorio.png")));
        this.btnGerarRelatorio.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                PanFiltroPGCC.this.btnGerarRelatorioActionPerformed(evt);
            }
        });
        this.btnVoltar.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/voltar.gif")));
        this.btnVoltar.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                PanFiltroPGCC.this.btnVoltarActionPerformed(evt);
            }
        });
        this.jCBContaCosif.setName("jCBCodigoEvento");
        this.jCBContaSuperior.setName("jCBAnoMesCompetencia");
        this.jCBContaSuperior.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                PanFiltroPGCC.this.jCBContaSuperiorActionPerformed(evt);
            }
        });
        this.jLabel4.setFont(new Font("Tahoma", 1, 11));
        this.jLabel4.setText("Conta COSIF");
        this.jLabel5.setFont(new Font("Tahoma", 1, 11));
        this.jLabel5.setText("Conta Superior");
        this.jLabel6.setFont(new Font("Tahoma", 1, 11));
        this.jLabel6.setText("Conta");
        this.jLabel7.setFont(new Font("Tahoma", 1, 11));
        this.jLabel7.setText("C\u00f3digo Tributa\u00e7\u00e3o DES-IF");
        this.jCBConta.setToolTipText("Selecione um item ");
        this.jCBConta.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                PanFiltroPGCC.this.jCBContaActionPerformed(evt);
            }
        });
        this.jLabel1.setFont(new Font("Tahoma", 1, 14));
        this.jLabel1.setHorizontalAlignment(0);
        this.jLabel1.setText("PGCC com COSIF");
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(77, 77, 77).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.jLabel6).addContainerGap(357, 32767)).addGroup(layout.createSequentialGroup().addComponent(this.jLabel5).addGap(341, 341, 341)).addGroup(layout.createSequentialGroup().addComponent(this.jLabel4).addContainerGap(321, 32767)).addGroup(layout.createSequentialGroup().addComponent(this.jLabel7).addContainerGap()).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(this.jLabel1, GroupLayout.Alignment.LEADING, -1, 348, 32767).addComponent(this.jCBConta, GroupLayout.Alignment.LEADING, 0, 348, 32767).addComponent(this.jCBContaSuperior, GroupLayout.Alignment.LEADING, 0, 348, 32767).addComponent(this.jCBContaCosif, GroupLayout.Alignment.LEADING, 0, 348, 32767).addComponent(this.jCBCodTribDesif, GroupLayout.Alignment.LEADING, 0, 348, 32767).addGroup(layout.createSequentialGroup().addGap(2, 2, 2).addComponent(this.btnGerarRelatorio, -1, 185, 32767).addGap(58, 58, 58).addComponent(this.btnVoltar))).addGap(77, 77, 77)))));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel1, -2, 26, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel6).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jCBConta, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel5).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jCBContaSuperior, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jLabel4).addGap(3, 3, 3).addComponent(this.jCBContaCosif, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jLabel7).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jCBCodTribDesif, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 93, 32767).addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(this.btnVoltar).addComponent(this.btnGerarRelatorio)).addContainerGap()));
    }

    private void btnGerarRelatorioActionPerformed(ActionEvent evt) {
        ((FrmPrincipal)Contexto.getObject("frmPrincipal")).setCursorAmpulheta(this.btnGerarRelatorio);
        this.gerarRelatorioPGCC();
        ((FrmPrincipal)Contexto.getObject("frmPrincipal")).setCursorPadrao(this.btnGerarRelatorio);
    }

    public void gerarRelatorioPGCC() {
        String conta = new String();
        String contaSuperior = new String();
        String codTribDesif = new String();
        String contaCosif = new String();
        if (this.jCBConta.getSelectedIndex() != -1) {
            conta = this.jCBConta.getSelectedItem().toString().trim();
        }
        if (this.jCBContaSuperior.getSelectedIndex() != -1) {
            contaSuperior = this.jCBContaSuperior.getSelectedItem().toString().trim();
        }
        if (this.jCBCodTribDesif.getSelectedIndex() != -1) {
            codTribDesif = this.jCBCodTribDesif.getSelectedItem().toString().trim();
        }
        if (this.jCBContaCosif.getSelectedIndex() != -1) {
            contaCosif = this.jCBContaCosif.getSelectedItem().toString().trim();
        }
        Controle controle = (Controle)Contexto.getObject("controle");
        List dados = controle.buscaFiltrosDadosGeraisPGCC(conta, contaSuperior, contaCosif, codTribDesif);
        ArrayList listaContasAnalit = new ArrayList();
        listaContasAnalit = (ArrayList)dados;
        List dadosDecl = controle.buscaFiltrosDadosDeclaracao();
        if (dados.isEmpty()) {
            SwingUtils.msgGenerica(this, "N\u00e3o h\u00e1 dados para gerar o relat\u00f3rio!", "Relat\u00f3rio Vazio");
            return;
        }
        controle.gerarRelatorioPGCC(listaContasAnalit, dadosDecl);
    }

    private void btnVoltarActionPerformed(ActionEvent evt) {
        if (RegUtil.exErro) {
            ((FrmPrincipal)Contexto.getObject("frmPrincipal")).abrirTela((JPanel)Contexto.getObject("panErro"));
        } else {
            ((FrmPrincipal)Contexto.getObject("frmPrincipal")).abrirTela((JPanel)Contexto.getObject("panSucesso"));
        }
    }

    private void jCBContaActionPerformed(ActionEvent evt) {
    }

    private void jCBContaSuperiorActionPerformed(ActionEvent evt) {
    }

}

