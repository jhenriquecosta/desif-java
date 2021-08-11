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

public class PanFiltroBalanceteAnalitico
extends JPanel {
    private JButton btnGerarRelatorio;
    private JButton btnVoltar;
    private JComboBox jCBAnoMesCompetencia;
    private JComboBox jCBCodigoDependencia;
    private JComboBox jCBCodigoPGCC;
    private JLabel jLabel1;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;

    public PanFiltroBalanceteAnalitico() {
        this.initComponents();
        DesLookandFeel.getInstance().formatarJButtons(new JButton[]{this.btnGerarRelatorio, this.btnVoltar});
    }

    public void initValoresComboBoxDependencias(List elementosComboBox) {
        this.jCBCodigoDependencia = this.inicializarComboBox(this.jCBCodigoDependencia);
        Iterator it = elementosComboBox.iterator();
        while (it.hasNext()) {
            this.jCBCodigoDependencia.addItem(it.next().toString());
        }
    }

    public void initValoresComboBoxContas(List elementosComboBox) {
        this.jCBCodigoPGCC = this.inicializarComboBox(this.jCBCodigoPGCC);
        Iterator it = elementosComboBox.iterator();
        while (it.hasNext()) {
            this.jCBCodigoPGCC.addItem(it.next().toString());
        }
    }

    public void initValoresComboBoxCompetencia(List elementosComboBox) {
        this.jCBAnoMesCompetencia = this.inicializarComboBox(this.jCBAnoMesCompetencia);
        Iterator it = elementosComboBox.iterator();
        while (it.hasNext()) {
            this.jCBAnoMesCompetencia.addItem(it.next().toString());
        }
    }

    private String formataValorSelecionadoCombo(Object valor) {
        String s = (String)valor;
        String resp = null;
        if (!s.equals("")) {
            String[] vet = s.split("-", -1);
            resp = vet[1].trim();
        }
        return resp;
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

    public void gerarRelatorioBalanceteAnalitico() {
        String codDependencia = new String();
        if (this.jCBCodigoDependencia.getSelectedIndex() != -1) {
            codDependencia = this.jCBCodigoDependencia.getSelectedItem().toString().trim();
        }
        String competencia = new String();
        if (this.jCBAnoMesCompetencia.getSelectedIndex() != -1) {
            competencia = this.jCBAnoMesCompetencia.getSelectedItem().toString().trim();
        }
        String conta = new String();
        if (this.jCBCodigoPGCC.getSelectedIndex() != -1) {
            conta = this.jCBCodigoPGCC.getSelectedItem().toString().trim();
        }
        if (codDependencia.isEmpty() && competencia.isEmpty() && conta.isEmpty()) {
            SwingUtils.msgGenerica(this, "A gera\u00e7\u00e3o deste relat\u00f3rio poder\u00e1 ser demorada.", "Relat\u00f3rio Sem Sele\u00e7\u00e3o de Filtros");
        }
        Controle controle = (Controle)Contexto.getObject("controle");
        List dados = controle.buscaFiltrosDadosGeraisBalancAnalit(codDependencia, competencia, conta);
        List dadosDependencia = null;
        if (!codDependencia.isEmpty()) {
            dadosDependencia = controle.buscaFiltrosDadosDependencia(codDependencia);
        }
        List dadosDecl = controle.buscaFiltrosDadosDeclaracao();
        if (dados.isEmpty()) {
            SwingUtils.msgGenerica(this, "N\u00e3o h\u00e1 dados para gerar o relat\u00f3rio!", "Relat\u00f3rio Vazio");
            return;
        }
        controle.gerarRelatorioBalanceteAnalitico(dados, dadosDependencia, dadosDecl);
    }

    private void initComponents() {
        this.btnGerarRelatorio = new JButton();
        this.btnVoltar = new JButton();
        this.jCBAnoMesCompetencia = new JComboBox();
        this.jLabel5 = new JLabel();
        this.jLabel6 = new JLabel();
        this.jLabel7 = new JLabel();
        this.jCBCodigoPGCC = new JComboBox();
        this.jCBCodigoDependencia = new JComboBox();
        this.jLabel1 = new JLabel();
        this.setPreferredSize(new Dimension(467, 315));
        this.btnGerarRelatorio.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/bt_gerar_relatorio.png")));
        this.btnGerarRelatorio.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                PanFiltroBalanceteAnalitico.this.btnGerarRelatorioActionPerformed(evt);
            }
        });
        this.btnVoltar.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/voltar.gif")));
        this.btnVoltar.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                PanFiltroBalanceteAnalitico.this.btnVoltarActionPerformed(evt);
            }
        });
        this.jCBAnoMesCompetencia.setModel(new DefaultComboBoxModel<String>(new String[]{" "}));
        this.jCBAnoMesCompetencia.setName("jCBAnoMesCompetencia");
        this.jLabel5.setFont(new Font("Tahoma", 1, 11));
        this.jLabel5.setText("Compet\u00eancia");
        this.jLabel6.setFont(new Font("Tahoma", 1, 11));
        this.jLabel6.setText("C\u00f3digo da Depend\u00eancia");
        this.jLabel7.setFont(new Font("Tahoma", 1, 11));
        this.jLabel7.setText("Conta (C\u00f3digo PGCC)");
        this.jCBCodigoPGCC.setModel(new DefaultComboBoxModel<String>(new String[]{" "}));
        this.jCBCodigoPGCC.setName("jCBCodigoPGCC");
        this.jCBCodigoDependencia.setModel(new DefaultComboBoxModel<String>(new String[]{" "}));
        this.jCBCodigoDependencia.setName("jCBCodigoDependencia");
        this.jCBCodigoDependencia.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                PanFiltroBalanceteAnalitico.this.jCBCodigoDependenciaActionPerformed(evt);
            }
        });
        this.jLabel1.setFont(new Font("Tahoma", 1, 14));
        this.jLabel1.setHorizontalAlignment(0);
        this.jLabel1.setText("Balancete Anal\u00edtico");
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(49, 49, 49).addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(this.jLabel1, GroupLayout.Alignment.LEADING, -1, 313, 32767).addGroup(layout.createSequentialGroup().addComponent(this.btnGerarRelatorio, -1, -1, 32767).addGap(35, 35, 35).addComponent(this.btnVoltar)).addComponent(this.jCBCodigoDependencia, GroupLayout.Alignment.LEADING, 0, 313, 32767).addComponent(this.jCBAnoMesCompetencia, 0, 313, 32767).addComponent(this.jLabel7, GroupLayout.Alignment.LEADING).addComponent(this.jLabel5, GroupLayout.Alignment.LEADING).addComponent(this.jCBCodigoPGCC, GroupLayout.Alignment.LEADING, 0, 313, 32767).addComponent(this.jLabel6, GroupLayout.Alignment.LEADING)).addGap(105, 105, 105)));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel1, -2, 22, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jLabel6).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jCBCodigoDependencia, -2, -1, -2).addGap(18, 18, 18).addComponent(this.jLabel5).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jCBAnoMesCompetencia, -2, -1, -2).addGap(18, 18, 18).addComponent(this.jLabel7).addGap(3, 3, 3).addComponent(this.jCBCodigoPGCC, -2, -1, -2).addGap(29, 29, 29).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.btnVoltar).addComponent(this.btnGerarRelatorio)).addContainerGap(54, 32767)));
    }

    private void btnGerarRelatorioActionPerformed(ActionEvent evt) {
        ((FrmPrincipal)Contexto.getObject("frmPrincipal")).setCursorAmpulheta(this.btnGerarRelatorio);
        this.gerarRelatorioBalanceteAnalitico();
        ((FrmPrincipal)Contexto.getObject("frmPrincipal")).setCursorPadrao(this.btnGerarRelatorio);
    }

    private void btnVoltarActionPerformed(ActionEvent evt) {
        if (RegUtil.exErro) {
            ((FrmPrincipal)Contexto.getObject("frmPrincipal")).abrirTela((JPanel)Contexto.getObject("panErro"));
        } else {
            ((FrmPrincipal)Contexto.getObject("frmPrincipal")).abrirTela((JPanel)Contexto.getObject("panSucesso"));
        }
    }

    private void jCBCodigoDependenciaActionPerformed(ActionEvent evt) {
    }

}

