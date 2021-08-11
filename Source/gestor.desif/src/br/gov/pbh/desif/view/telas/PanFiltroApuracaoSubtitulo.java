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
import br.gov.pbh.desif.model.pojo.FiltroCodTribAliquotaVO;
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

public class PanFiltroApuracaoSubtitulo
extends JPanel {
    private JButton btnGerarRelatorio;
    private JButton btnVoltar;
    private JComboBox jCBCnpjCodDepen;
    private JComboBox jCBCodTribAliq;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;

    public PanFiltroApuracaoSubtitulo() {
        this.initComponents();
        DesLookandFeel.getInstance().formatarJButtons(new JButton[]{this.btnGerarRelatorio, this.btnVoltar});
    }

    public void initValoresComboBox(List elementosCNPJCodDep, List elementosCodTribAliq) {
        Iterator it = elementosCNPJCodDep.iterator();
        this.jCBCnpjCodDepen.removeAllItems();
        this.jCBCnpjCodDepen.addItem("");
        while (it.hasNext()) {
            this.jCBCnpjCodDepen.addItem(this.formataValorComboCNPJCodDepen(it.next()));
        }
        Iterator it1 = elementosCodTribAliq.iterator();
        this.jCBCodTribAliq.removeAllItems();
        this.jCBCodTribAliq.addItem("");
        while (it1.hasNext()) {
            this.jCBCodTribAliq.addItem(this.formataValorComboCodTribAliq(it1.next()));
        }
    }

    private String formataValorComboCNPJCodDepen(Object valor) {
        FiltroCNPJCodDependenciaVO valorFiltro = (FiltroCNPJCodDependenciaVO)valor;
        String resp = valorFiltro.getCnpjInstituicao() + valorFiltro.getCnpj() + " | " + valorFiltro.getCodigoDependencia();
        return resp;
    }

    private String formataValorComboCodTribAliq(Object valor) {
        FiltroCodTribAliquotaVO valorFiltro = (FiltroCodTribAliquotaVO)valor;
        String resp = valorFiltro.getValorAliquotaIssqn().toString();
        return resp;
    }

    private String formataValorSelecionadoCombo(Object valor) {
        String s = (String)valor;
        String resp = null;
        if (!s.equals("")) {
            String[] vet = s.split("\\|");
            resp = vet.length > 1 ? vet[1].trim() : vet[0].trim();
        }
        return resp;
    }

    public void gerarRelatorioApuracaoSubtitulo() {
        Controle controle = (Controle)Contexto.getObject("controle");
        String codDependencia = this.formataValorSelecionadoCombo(this.jCBCnpjCodDepen.getSelectedItem());
        String aliquota = this.formataValorSelecionadoCombo(this.jCBCodTribAliq.getSelectedItem());
        short tipoConsolidacao = controle.buscaTipoConsolidacao();
        if (tipoConsolidacao == 1) {
            if (codDependencia == null) {
                SwingUtils.msgGenerica(this, "Selecione um c\u00f3digo de depend\u00eancia para gerar o relat\u00f3rio.", "Selecione");
                return;
            }
        } else if (tipoConsolidacao == 3 && codDependencia == null && aliquota == null) {
            SwingUtils.msgGenerica(this, "Selecione um filtro para gerar o relat\u00f3rio.", "Selecione");
            return;
        }
        List resp = controle.buscaDadosDinamicosRelatorioApurReceitaSubtitulo(codDependencia, aliquota);
        Object dadosEstaticos = controle.buscaDadosEstaticosRelatorioApurSubtitulo(codDependencia);
        if (resp.isEmpty()) {
            SwingUtils.msgGenerica(this, "N\u00e3o h\u00e1 dados para gerar o relat\u00f3rio!", "Relat\u00f3rio Vazio");
            return;
        }
        controle.gerarRelatorioApuracaoSubtitulo(resp, dadosEstaticos);
    }

    private void initComponents() {
        this.jCBCnpjCodDepen = new JComboBox();
        this.jLabel1 = new JLabel();
        this.btnGerarRelatorio = new JButton();
        this.jLabel2 = new JLabel();
        this.jCBCodTribAliq = new JComboBox();
        this.btnVoltar = new JButton();
        this.jLabel3 = new JLabel();
        this.jCBCnpjCodDepen.setToolTipText("Selecione um item ");
        this.jCBCnpjCodDepen.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                PanFiltroApuracaoSubtitulo.this.jCBCnpjCodDepenActionPerformed(evt);
            }
        });
        this.jLabel1.setText("CNPJ - c\u00f3d. depend\u00eancia:");
        this.btnGerarRelatorio.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/bt_gerar_relatorio.png")));
        this.btnGerarRelatorio.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                PanFiltroApuracaoSubtitulo.this.btnGerarRelatorioActionPerformed(evt);
            }
        });
        this.jLabel2.setText("C\u00f3d. de tributa\u00e7\u00e3o - aliquota:");
        this.jCBCodTribAliq.setToolTipText("Selecione um item ");
        this.jCBCodTribAliq.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                PanFiltroApuracaoSubtitulo.this.jCBCodTribAliqActionPerformed(evt);
            }
        });
        this.btnVoltar.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/voltar.gif")));
        this.btnVoltar.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                PanFiltroApuracaoSubtitulo.this.btnVoltarActionPerformed(evt);
            }
        });
        this.jLabel3.setFont(new Font("Tahoma", 1, 14));
        this.jLabel3.setHorizontalAlignment(0);
        this.jLabel3.setText("Apura\u00e7\u00e3o por Subt\u00edtulo");
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(69, 69, 69).addComponent(this.btnGerarRelatorio)).addGroup(layout.createSequentialGroup().addGap(83, 83, 83).addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(this.jLabel2, GroupLayout.Alignment.LEADING).addComponent(this.jLabel1, GroupLayout.Alignment.LEADING)))).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(this.jCBCnpjCodDepen, -2, -1, -2).addComponent(this.jCBCodTribAliq, -2, -1, -2).addComponent(this.btnVoltar)).addContainerGap(114, 32767)).addGroup(layout.createSequentialGroup().addGap(44, 44, 44).addComponent(this.jLabel3, -2, 350, -2).addContainerGap(73, 32767)));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel3, -2, 22, -2).addGap(35, 35, 35).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel1).addComponent(this.jCBCnpjCodDepen)).addGap(52, 52, 52).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel2).addComponent(this.jCBCodTribAliq)).addGap(70, 70, 70).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.btnGerarRelatorio).addComponent(this.btnVoltar)).addGap(47, 47, 47)));
    }

    private void jCBCnpjCodDepenActionPerformed(ActionEvent evt) {
    }

    private void btnGerarRelatorioActionPerformed(ActionEvent evt) {
        ((FrmPrincipal)Contexto.getObject("frmPrincipal")).setCursorAmpulheta(this.btnGerarRelatorio);
        this.gerarRelatorioApuracaoSubtitulo();
        ((FrmPrincipal)Contexto.getObject("frmPrincipal")).setCursorPadrao(this.btnGerarRelatorio);
    }

    private void jCBCodTribAliqActionPerformed(ActionEvent evt) {
    }

    private void btnVoltarActionPerformed(ActionEvent evt) {
        if (RegUtil.exErro) {
            ((FrmPrincipal)Contexto.getObject("frmPrincipal")).abrirTela((JPanel)Contexto.getObject("panErro"));
        } else {
            ((FrmPrincipal)Contexto.getObject("frmPrincipal")).abrirTela((JPanel)Contexto.getObject("panSucesso"));
        }
    }

}

