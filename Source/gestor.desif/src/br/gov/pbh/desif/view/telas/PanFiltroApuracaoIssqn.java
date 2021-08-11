/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  br.gov.pbh.des.componentes.utils.DesLookandFeel
 */
package br.gov.pbh.desif.view.telas;

import br.gov.pbh.des.componentes.utils.DesLookandFeel;
import br.gov.pbh.desif.control.Controle;
import br.gov.pbh.desif.model.pojo.FiltroCNPJCodDependenciaVO;
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
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;

public class PanFiltroApuracaoIssqn
extends JPanel {
    private JButton btnGerarRelatorio;
    private JButton btnVoltar;
    private JComboBox jCBCnpjCodDepen;
    private JLabel jLabel1;
    private JLabel jLabel2;

    public PanFiltroApuracaoIssqn() {
        this.initComponents();
        DesLookandFeel.getInstance().formatarJButtons(new JButton[]{this.btnGerarRelatorio, this.btnVoltar});
    }

    public void initValoresComboBox(List elementosComboBox) {
        Iterator it = elementosComboBox.iterator();
        while (it.hasNext()) {
            this.jCBCnpjCodDepen.addItem(this.formataValorCombo(it.next()));
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

    private String formataValorCombo(Object valor) {
        FiltroCNPJCodDependenciaVO valorFiltro = (FiltroCNPJCodDependenciaVO)valor;
        String resp = valorFiltro.getCnpjInstituicao() + valorFiltro.getCnpj() + " - " + valorFiltro.getCodigoDependencia();
        return resp;
    }

    public void gerarRelatorioApuracaoIssqn() {
        String codDependencia = this.formataValorSelecionadoCombo(this.jCBCnpjCodDepen.getSelectedItem());
        if (codDependencia == null) {
            SwingUtils.msgGenerica(this, "Selecione um item para gerar o relat\u00f3rio.", "Selecione");
            return;
        }
        Controle controle = (Controle)Contexto.getObject("controle");
        Object dadosEstaticos = controle.buscaDadosEstaticosRelatorioApurIssqn(codDependencia);
        List resp = controle.buscaDadosDinamicosRelatorioApurIssqn(codDependencia);
        if (resp.isEmpty()) {
            SwingUtils.msgGenerica(this, "N\u00e3o h\u00e1 dados para gerar o relat\u00f3rio!", "Relat\u00f3rio Vazio");
            return;
        }
        controle.gerarRelatorioApuracaoIssqn(resp, dadosEstaticos);
    }

    private void initComponents() {
        this.jCBCnpjCodDepen = new JComboBox();
        this.jLabel1 = new JLabel();
        this.btnGerarRelatorio = new JButton();
        this.btnVoltar = new JButton();
        this.jLabel2 = new JLabel();
        this.jCBCnpjCodDepen.setToolTipText("Selecione um item ");
        this.jCBCnpjCodDepen.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                PanFiltroApuracaoIssqn.this.jCBCnpjCodDepenActionPerformed(evt);
            }
        });
        this.jLabel1.setText("Selecione CNPJ - cod depend\u00eancia");
        this.btnGerarRelatorio.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/bt_gerar_relatorio.png")));
        this.btnGerarRelatorio.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                PanFiltroApuracaoIssqn.this.btnGerarRelatorioActionPerformed(evt);
            }
        });
        this.btnVoltar.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/voltar.gif")));
        this.btnVoltar.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                PanFiltroApuracaoIssqn.this.btnVoltarActionPerformed(evt);
            }
        });
        this.jLabel2.setFont(new Font("Tahoma", 1, 14));
        this.jLabel2.setHorizontalAlignment(0);
        this.jLabel2.setText("Apura\u00e7\u00e3o do ISSQN");
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel1).addComponent(this.btnGerarRelatorio)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.btnVoltar).addComponent(this.jCBCnpjCodDepen, -2, -1, -2)).addContainerGap(173, 32767)).addGroup(layout.createSequentialGroup().addGap(72, 72, 72).addComponent(this.jLabel2, -1, 313, 32767).addGap(82, 82, 82)));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel2, -2, 22, -2).addGap(86, 86, 86).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel1).addComponent(this.jCBCnpjCodDepen)).addGap(39, 39, 39).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.btnGerarRelatorio).addComponent(this.btnVoltar)).addGap(99, 99, 99)));
    }

    private void jCBCnpjCodDepenActionPerformed(ActionEvent evt) {
    }

    private void btnGerarRelatorioActionPerformed(ActionEvent evt) {
        ((FrmPrincipal)Contexto.getObject("frmPrincipal")).setCursorAmpulheta(this.btnGerarRelatorio);
        this.gerarRelatorioApuracaoIssqn();
        ((FrmPrincipal)Contexto.getObject("frmPrincipal")).setCursorPadrao(this.btnGerarRelatorio);
    }

    private void btnVoltarActionPerformed(ActionEvent evt) {
        if (RegUtil.exErro) {
            ((FrmPrincipal)Contexto.getObject("frmPrincipal")).abrirTela((JPanel)Contexto.getObject("panErro"));
        } else {
            ((FrmPrincipal)Contexto.getObject("frmPrincipal")).abrirTela((JPanel)Contexto.getObject("panSucesso"));
        }
    }

}

