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

public class PanFiltroDemonstrativoRateio
extends JPanel {
    private JButton btnGerarRelatorio;
    private JButton btnVoltar;
    private JComboBox jCBCodDependencia;
    private JComboBox jCBCodEvento;
    private JComboBox jCBCompetencia;
    private JComboBox jCBValorRateio;
    private JLabel jLabel1;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;

    public PanFiltroDemonstrativoRateio() {
        this.initComponents();
        DesLookandFeel.getInstance().formatarJButtons(new JButton[]{this.btnGerarRelatorio, this.btnVoltar});
    }

    public void initValoresComboBoxDependencias(List elementosComboBox) {
        this.jCBCodDependencia = this.inicializarComboBox(this.jCBCodDependencia);
        Iterator it = elementosComboBox.iterator();
        while (it.hasNext()) {
            this.jCBCodDependencia.addItem(it.next().toString());
        }
    }

    public void initValoresComboBoxCompetencia(List elementosComboBox) {
        this.jCBCompetencia = this.inicializarComboBox(this.jCBCompetencia);
        Iterator it = elementosComboBox.iterator();
        while (it.hasNext()) {
            this.jCBCompetencia.addItem(it.next().toString());
        }
    }

    public void initValoresComboBoxValorRateio(List elementosComboBox) {
        this.jCBValorRateio = this.inicializarComboBox(this.jCBValorRateio);
        Iterator it = elementosComboBox.iterator();
        while (it.hasNext()) {
            this.jCBValorRateio.addItem(it.next().toString());
        }
    }

    public void initValoresComboBoxCodEvento(List elementosComboBox) {
        this.jCBCodEvento = this.inicializarComboBox(this.jCBCodEvento);
        Iterator it = elementosComboBox.iterator();
        while (it.hasNext()) {
            this.jCBCodEvento.addItem(it.next().toString());
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
        this.jCBValorRateio = new JComboBox();
        this.jCBCodEvento = new JComboBox();
        this.jCBCompetencia = new JComboBox();
        this.jLabel4 = new JLabel();
        this.jLabel5 = new JLabel();
        this.jLabel6 = new JLabel();
        this.jLabel7 = new JLabel();
        this.jCBCodDependencia = new JComboBox();
        this.jLabel1 = new JLabel();
        this.setPreferredSize(new Dimension(467, 315));
        this.btnGerarRelatorio.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/bt_gerar_relatorio.png")));
        this.btnGerarRelatorio.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                PanFiltroDemonstrativoRateio.this.btnGerarRelatorioActionPerformed(evt);
            }
        });
        this.btnVoltar.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/voltar.gif")));
        this.btnVoltar.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                PanFiltroDemonstrativoRateio.this.btnVoltarActionPerformed(evt);
            }
        });
        this.jCBCodEvento.setName("jCBCodigoEvento");
        this.jCBCompetencia.setName("jCBAnoMesCompetencia");
        this.jCBCompetencia.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                PanFiltroDemonstrativoRateio.this.jCBCompetenciaActionPerformed(evt);
            }
        });
        this.jLabel4.setFont(new Font("Tahoma", 1, 11));
        this.jLabel4.setText("C\u00f3digo do Evento");
        this.jLabel5.setFont(new Font("Tahoma", 1, 11));
        this.jLabel5.setText("Compet\u00eancia");
        this.jLabel6.setFont(new Font("Tahoma", 1, 11));
        this.jLabel6.setText("C\u00f3digo da Depend\u00eancia");
        this.jLabel7.setFont(new Font("Tahoma", 1, 11));
        this.jLabel7.setText("Valor do Rateio");
        this.jCBCodDependencia.setToolTipText("Selecione um item ");
        this.jCBCodDependencia.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                PanFiltroDemonstrativoRateio.this.jCBCodDependenciaActionPerformed(evt);
            }
        });
        this.jLabel1.setFont(new Font("Tahoma", 1, 14));
        this.jLabel1.setHorizontalAlignment(0);
        this.jLabel1.setText("Demonstrativo de Rateio");
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(56, 56, 56).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.jLabel5).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 262, -2)).addGroup(layout.createSequentialGroup().addComponent(this.jLabel4).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 239, -2)).addGroup(layout.createSequentialGroup().addComponent(this.jLabel7, -2, 86, -2).addGap(250, 250, 250)).addGroup(GroupLayout.Alignment.TRAILING, layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(this.jCBCompetencia, GroupLayout.Alignment.LEADING, 0, 336, 32767).addComponent(this.jLabel1, GroupLayout.Alignment.LEADING, -1, 336, 32767).addComponent(this.jCBCodEvento, GroupLayout.Alignment.LEADING, 0, 336, 32767).addComponent(this.jCBValorRateio, GroupLayout.Alignment.LEADING, 0, 336, 32767).addGroup(layout.createSequentialGroup().addComponent(this.btnGerarRelatorio, -1, -1, 32767).addGap(58, 58, 58).addComponent(this.btnVoltar)).addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup().addComponent(this.jLabel6).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 205, -2)).addComponent(this.jCBCodDependencia, 0, 336, 32767))).addGap(98, 98, 98)));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel1, -2, 22, -2).addGap(13, 13, 13).addComponent(this.jLabel6).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jCBCodDependencia, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jLabel5).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jCBCompetencia, -2, -1, -2).addGap(11, 11, 11).addComponent(this.jLabel4).addGap(3, 3, 3).addComponent(this.jCBCodEvento, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jLabel7).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jCBValorRateio, -2, -1, -2).addGap(18, 18, 18).addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(this.btnVoltar).addComponent(this.btnGerarRelatorio)).addContainerGap(24, 32767)));
    }

    private void btnGerarRelatorioActionPerformed(ActionEvent evt) {
        ((FrmPrincipal)Contexto.getObject("frmPrincipal")).setCursorAmpulheta(this.btnGerarRelatorio);
        this.gerarRelatorioDemRateio();
        ((FrmPrincipal)Contexto.getObject("frmPrincipal")).setCursorPadrao(this.btnGerarRelatorio);
    }

    public void gerarRelatorioDemRateio() {
        String codDependencia = new String();
        String competencia = new String();
        String valorRateio = new String();
        String codEvento = new String();
        if (this.jCBCodDependencia.getSelectedIndex() != -1) {
            codDependencia = this.jCBCodDependencia.getSelectedItem().toString().trim();
        }
        if (this.jCBCompetencia.getSelectedIndex() != -1) {
            competencia = this.jCBCompetencia.getSelectedItem().toString().trim();
        }
        if (this.jCBValorRateio.getSelectedIndex() != -1) {
            valorRateio = this.jCBValorRateio.getSelectedItem().toString().trim();
        }
        if (this.jCBCodEvento.getSelectedIndex() != -1) {
            codEvento = this.jCBCodEvento.getSelectedItem().toString().trim();
        }
        Controle controle = (Controle)Contexto.getObject("controle");
        List dados = controle.buscaFiltrosDadosGeraisDemRateio(codDependencia, competencia, valorRateio, codEvento);
        List dadosDependencia = null;
        if (!codDependencia.isEmpty()) {
            dadosDependencia = controle.buscaFiltrosDadosDependencia(codDependencia);
        }
        List dadosDecl = controle.buscaFiltrosDadosDeclaracao();
        if (dados.isEmpty()) {
            SwingUtils.msgGenerica(this, "N\u00e3o h\u00e1 dados para gerar o relat\u00f3rio!", "Relat\u00f3rio Vazio");
            return;
        }
        controle.gerarRelatorioDemRateio(dados, dadosDependencia, dadosDecl);
    }

    private void btnVoltarActionPerformed(ActionEvent evt) {
        if (RegUtil.exErro) {
            ((FrmPrincipal)Contexto.getObject("frmPrincipal")).abrirTela((JPanel)Contexto.getObject("panErro"));
        } else {
            ((FrmPrincipal)Contexto.getObject("frmPrincipal")).abrirTela((JPanel)Contexto.getObject("panSucesso"));
        }
    }

    private void jCBCodDependenciaActionPerformed(ActionEvent evt) {
    }

    private void jCBCompetenciaActionPerformed(ActionEvent evt) {
    }

}

