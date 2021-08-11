
package br.gov.pbh.desif.view.telas;

import br.gov.pbh.des.componentes.utils.DesLookandFeel;
import br.gov.pbh.desif.control.Controle;
import br.gov.pbh.desif.model.registros.RegUtil;
import br.gov.pbh.desif.service.contexto.Contexto;
import br.gov.pbh.desif.view.util.SwingUtils;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;

// Referenced classes of package br.gov.pbh.desif.view.telas:
//            FrmPrincipal

public class PanFiltroBalanceteAnalitico extends JPanel
{

    private JButton btnGerarRelatorio;
    private JButton btnVoltar;
    private JComboBox jCBAnoMesCompetencia;
    private JComboBox jCBCodigoDependencia;
    private JComboBox jCBCodigoPGCC;
    private JLabel jLabel1;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;

    public PanFiltroBalanceteAnalitico()
    {
        initComponents();
        DesLookandFeel.getInstance().formatarJButtons(new JButton[] {
            btnGerarRelatorio, btnVoltar
        });
    }

    public void initValoresComboBoxDependencias(java.util.List elementosComboBox)
    {
        jCBCodigoDependencia = inicializarComboBox(jCBCodigoDependencia);
        for(Iterator it = elementosComboBox.iterator(); it.hasNext(); jCBCodigoDependencia.addItem(it.next().toString()));
    }

    public void initValoresComboBoxContas(java.util.List elementosComboBox)
    {
        jCBCodigoPGCC = inicializarComboBox(jCBCodigoPGCC);
        for(Iterator it = elementosComboBox.iterator(); it.hasNext(); jCBCodigoPGCC.addItem(it.next().toString()));
    }

    public void initValoresComboBoxCompetencia(java.util.List elementosComboBox)
    {
        jCBAnoMesCompetencia = inicializarComboBox(jCBAnoMesCompetencia);
        for(Iterator it = elementosComboBox.iterator(); it.hasNext(); jCBAnoMesCompetencia.addItem(it.next().toString()));
    }

    private String formataValorSelecionadoCombo(Object valor)
    {
        String s = (String)valor;
        String resp = null;
        if(!s.equals(""))
        {
            String vet[] = s.split("-", -1);
            resp = vet[1].trim();
        }
        return resp;
    }

    public JComboBox inicializarComboBox(JComboBox jCBEntrada)
    {
        JComboBox jCBZerado = new JComboBox();
        jCBZerado = jCBEntrada;
        jCBZerado.removeAllItems();
        jCBZerado.setModel(new DefaultComboBoxModel(new String[] {
            " "
        }));
        jCBZerado.setSelectedIndex(-1);
        jCBZerado.setSelectedItem(null);
        return jCBZerado;
    }

    public void gerarRelatorioBalanceteAnalitico()
    {
        String codDependencia = new String();
        if(jCBCodigoDependencia.getSelectedIndex() != -1)
            codDependencia = jCBCodigoDependencia.getSelectedItem().toString().trim();
        String competencia = new String();
        if(jCBAnoMesCompetencia.getSelectedIndex() != -1)
            competencia = jCBAnoMesCompetencia.getSelectedItem().toString().trim();
        String conta = new String();
        if(jCBCodigoPGCC.getSelectedIndex() != -1)
            conta = jCBCodigoPGCC.getSelectedItem().toString().trim();
        if(codDependencia.isEmpty() && competencia.isEmpty() && conta.isEmpty())
            SwingUtils.msgGenerica(this, "A gera\347\343o deste relat\363rio poder\341 ser demorada.", "Relat\363rio Sem Sele\347\343o de Filtros");
        Controle controle = (Controle)Contexto.getObject("controle");
        java.util.List dados = controle.buscaFiltrosDadosGeraisBalancAnalit(codDependencia, competencia, conta);
        java.util.List dadosDependencia = null;
        if(!codDependencia.isEmpty())
            dadosDependencia = controle.buscaFiltrosDadosDependencia(codDependencia);
        java.util.List dadosDecl = controle.buscaFiltrosDadosDeclaracao();
        if(dados.isEmpty())
        {
            SwingUtils.msgGenerica(this, "N\343o h\341 dados para gerar o relat\363rio!", "Relat\363rio Vazio");
            return;
        } else
        {
            controle.gerarRelatorioBalanceteAnalitico(dados, dadosDependencia, dadosDecl);
            return;
        }
    }

    private void initComponents()
    {
        btnGerarRelatorio = new JButton();
        btnVoltar = new JButton();
        jCBAnoMesCompetencia = new JComboBox();
        jLabel5 = new JLabel();
        jLabel6 = new JLabel();
        jLabel7 = new JLabel();
        jCBCodigoPGCC = new JComboBox();
        jCBCodigoDependencia = new JComboBox();
        jLabel1 = new JLabel();
        setPreferredSize(new Dimension(467, 315));
        btnGerarRelatorio.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/bt_gerar_relatorio.png")));
        btnGerarRelatorio.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt)
            {
                btnGerarRelatorioActionPerformed(evt);
            }

        }
);
        btnVoltar.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/voltar.gif")));
        btnVoltar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt)
            {
                btnVoltarActionPerformed(evt);
            }

          
        }
);
        jCBAnoMesCompetencia.setModel(new DefaultComboBoxModel(new String[] {
            " "
        }));
        jCBAnoMesCompetencia.setName("jCBAnoMesCompetencia");
        jLabel5.setFont(new Font("Tahoma", 1, 11));
        jLabel5.setText("Compet\352ncia");
        jLabel6.setFont(new Font("Tahoma", 1, 11));
        jLabel6.setText("C\363digo da Depend\352ncia");
        jLabel7.setFont(new Font("Tahoma", 1, 11));
        jLabel7.setText("Conta (C\363digo PGCC)");
        jCBCodigoPGCC.setModel(new DefaultComboBoxModel(new String[] {
            " "
        }));
        jCBCodigoPGCC.setName("jCBCodigoPGCC");
        jCBCodigoDependencia.setModel(new DefaultComboBoxModel(new String[] {
            " "
        }));
        jCBCodigoDependencia.setName("jCBCodigoDependencia");
        jCBCodigoDependencia.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt)
            {
                jCBCodigoDependenciaActionPerformed(evt);
            }

           
        }
);
        jLabel1.setFont(new Font("Tahoma", 1, 14));
        jLabel1.setHorizontalAlignment(0);
        jLabel1.setText("Balancete Anal\355tico");
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(49, 49, 49).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, -1, 313, 32767).addGroup(layout.createSequentialGroup().addComponent(btnGerarRelatorio, -1, -1, 32767).addGap(35, 35, 35).addComponent(btnVoltar)).addComponent(jCBCodigoDependencia, javax.swing.GroupLayout.Alignment.LEADING, 0, 313, 32767).addComponent(jCBAnoMesCompetencia, 0, 313, 32767).addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING).addComponent(jCBCodigoPGCC, javax.swing.GroupLayout.Alignment.LEADING, 0, 313, 32767).addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)).addGap(105, 105, 105)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jLabel1, -2, 22, -2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jLabel6).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jCBCodigoDependencia, -2, -1, -2).addGap(18, 18, 18).addComponent(jLabel5).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jCBAnoMesCompetencia, -2, -1, -2).addGap(18, 18, 18).addComponent(jLabel7).addGap(3, 3, 3).addComponent(jCBCodigoPGCC, -2, -1, -2).addGap(29, 29, 29).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(btnVoltar).addComponent(btnGerarRelatorio)).addContainerGap(54, 32767)));
    }

    private void btnGerarRelatorioActionPerformed(ActionEvent evt)
    {
        ((FrmPrincipal)Contexto.getObject("frmPrincipal")).setCursorAmpulheta(btnGerarRelatorio);
        gerarRelatorioBalanceteAnalitico();
        ((FrmPrincipal)Contexto.getObject("frmPrincipal")).setCursorPadrao(btnGerarRelatorio);
    }

    private void btnVoltarActionPerformed(ActionEvent evt)
    {
        if(RegUtil.exErro)
            ((FrmPrincipal)Contexto.getObject("frmPrincipal")).abrirTela((JPanel)Contexto.getObject("panErro"));
        else
            ((FrmPrincipal)Contexto.getObject("frmPrincipal")).abrirTela((JPanel)Contexto.getObject("panSucesso"));
    }

    private void jCBCodigoDependenciaActionPerformed(ActionEvent actionevent)
    {
    }



}
