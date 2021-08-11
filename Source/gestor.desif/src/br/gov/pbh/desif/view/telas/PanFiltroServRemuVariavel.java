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

public class PanFiltroServRemuVariavel
extends JPanel {
    private JButton btnGerarRelatorio;
    private JButton btnVoltar;
    private JComboBox jCBCodIdServico;
    private JComboBox jCBCodSubtitulo;
    private JLabel jLabel1;
    private JLabel jLabel6;
    private JLabel jLabel7;

    public PanFiltroServRemuVariavel() {
        this.initComponents();
        DesLookandFeel.getInstance().formatarJButtons(new JButton[]{this.btnGerarRelatorio, this.btnVoltar});
    }

    public void initValoresComboBoxCodIdServico(List elementosComboBox) {
        this.jCBCodIdServico.removeAllItems();
        this.jCBCodIdServico.setModel(new DefaultComboBoxModel<String>(new String[]{" "}));
        this.jCBCodIdServico.setSelectedIndex(-1);
        this.jCBCodIdServico.setSelectedItem(null);
        Iterator it = elementosComboBox.iterator();
        while (it.hasNext()) {
            this.jCBCodIdServico.addItem(it.next().toString());
        }
    }

    public void initValoresComboBoxCodSubtitulo(List elementosComboBox) {
        this.jCBCodSubtitulo.removeAllItems();
        this.jCBCodSubtitulo.setModel(new DefaultComboBoxModel<String>(new String[]{" "}));
        this.jCBCodSubtitulo.setSelectedIndex(-1);
        this.jCBCodSubtitulo.setSelectedItem(null);
        Iterator it = elementosComboBox.iterator();
        while (it.hasNext()) {
            this.jCBCodSubtitulo.addItem(it.next().toString());
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

    public void gerarRelatorioServRemVar() {
        String codSubtitulo = new String();
        String idServico = new String();
        if (this.jCBCodIdServico.getSelectedIndex() != -1) {
            idServico = this.jCBCodIdServico.getSelectedItem().toString().trim();
        }
        if (this.jCBCodSubtitulo.getSelectedIndex() != -1) {
            codSubtitulo = this.jCBCodSubtitulo.getSelectedItem().toString().trim();
        }
        Controle controle = (Controle)Contexto.getObject("controle");
        List dados = controle.buscaFiltrosDadosGeraisServRemVar(idServico, codSubtitulo);
        List dadosDecl = controle.buscaFiltrosDadosDeclaracao();
        if (dados.isEmpty()) {
            SwingUtils.msgGenerica(this, "N\u00e3o h\u00e1 dados para gerar o relat\u00f3rio!", "Relat\u00f3rio Vazio");
            return;
        }
        controle.gerarRelatorioServRemVar(dados, dadosDecl);
    }

    private void initComponents() {
        this.btnGerarRelatorio = new JButton();
        this.btnVoltar = new JButton();
        this.jLabel6 = new JLabel();
        this.jLabel7 = new JLabel();
        this.jCBCodSubtitulo = new JComboBox();
        this.jCBCodIdServico = new JComboBox();
        this.jLabel1 = new JLabel();
        this.btnGerarRelatorio.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/bt_gerar_relatorio.png")));
        this.btnGerarRelatorio.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                PanFiltroServRemuVariavel.this.btnGerarRelatorioActionPerformed(evt);
            }
        });
        this.btnVoltar.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/voltar.gif")));
        this.btnVoltar.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                PanFiltroServRemuVariavel.this.btnVoltarActionPerformed(evt);
            }
        });
        this.jLabel6.setFont(new Font("Tahoma", 1, 11));
        this.jLabel6.setText("C\u00f3digo do Servi\u00e7o de Remunera\u00e7\u00e3o Vari\u00e1vel");
        this.jLabel7.setFont(new Font("Tahoma", 1, 11));
        this.jLabel7.setText("C\u00f3digo do Subt\u00edtulo");
        this.jCBCodSubtitulo.setModel(new DefaultComboBoxModel<String>(new String[]{" "}));
        this.jCBCodSubtitulo.setName("jCBCodSubtitulo");
        this.jCBCodIdServico.setModel(new DefaultComboBoxModel<String>(new String[]{" "}));
        this.jCBCodIdServico.setName("jCBCodIdServico");
        this.jCBCodIdServico.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                PanFiltroServRemuVariavel.this.jCBCodIdServicoActionPerformed(evt);
            }
        });
        this.jLabel1.setFont(new Font("Tahoma", 1, 14));
        this.jLabel1.setHorizontalAlignment(0);
        this.jLabel1.setText("Servi\u00e7os de Remunera\u00e7\u00e3o Vari\u00e1vel");
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(71, 71, 71).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.jLabel6).addContainerGap(148, 32767)).addGroup(layout.createSequentialGroup().addComponent(this.jLabel7).addGap(287, 287, 287)).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(this.jCBCodIdServico, GroupLayout.Alignment.LEADING, 0, 317, 32767).addComponent(this.jCBCodSubtitulo, GroupLayout.Alignment.LEADING, 0, 317, 32767).addComponent(this.jLabel1, GroupLayout.Alignment.LEADING, -1, 317, 32767).addGroup(layout.createSequentialGroup().addComponent(this.btnGerarRelatorio, -1, 179, 32767).addGap(35, 35, 35).addComponent(this.btnVoltar))).addGap(79, 79, 79)))));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel1, -2, 26, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 27, 32767).addComponent(this.jLabel6).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jCBCodIdServico, -2, -1, -2).addGap(18, 18, 18).addComponent(this.jLabel7).addGap(3, 3, 3).addComponent(this.jCBCodSubtitulo, -2, -1, -2).addGap(44, 44, 44).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.btnVoltar).addComponent(this.btnGerarRelatorio)).addGap(107, 107, 107)));
    }

    private void btnGerarRelatorioActionPerformed(ActionEvent evt) {
        ((FrmPrincipal)Contexto.getObject("frmPrincipal")).setCursorAmpulheta(this.btnGerarRelatorio);
        this.gerarRelatorioServRemVar();
        ((FrmPrincipal)Contexto.getObject("frmPrincipal")).setCursorPadrao(this.btnGerarRelatorio);
    }

    private void btnVoltarActionPerformed(ActionEvent evt) {
        if (RegUtil.exErro) {
            ((FrmPrincipal)Contexto.getObject("frmPrincipal")).abrirTela((JPanel)Contexto.getObject("panErro"));
        } else {
            ((FrmPrincipal)Contexto.getObject("frmPrincipal")).abrirTela((JPanel)Contexto.getObject("panSucesso"));
        }
    }

    private void jCBCodIdServicoActionPerformed(ActionEvent evt) {
    }

}

