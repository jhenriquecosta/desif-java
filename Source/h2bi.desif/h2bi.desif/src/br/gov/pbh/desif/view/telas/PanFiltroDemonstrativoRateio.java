
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

public class PanFiltroDemonstrativoRateio extends JPanel
{

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

    public PanFiltroDemonstrativoRateio()
    {
        initComponents();
        DesLookandFeel.getInstance().formatarJButtons(new JButton[] {
            btnGerarRelatorio, btnVoltar
        });
    }

    public void initValoresComboBoxDependencias(java.util.List elementosComboBox)
    {
        jCBCodDependencia = inicializarComboBox(jCBCodDependencia);
        for(Iterator it = elementosComboBox.iterator(); it.hasNext(); jCBCodDependencia.addItem(it.next().toString()));
    }

    public void initValoresComboBoxCompetencia(java.util.List elementosComboBox)
    {
        jCBCompetencia = inicializarComboBox(jCBCompetencia);
        for(Iterator it = elementosComboBox.iterator(); it.hasNext(); jCBCompetencia.addItem(it.next().toString()));
    }

    public void initValoresComboBoxValorRateio(java.util.List elementosComboBox)
    {
        jCBValorRateio = inicializarComboBox(jCBValorRateio);
        for(Iterator it = elementosComboBox.iterator(); it.hasNext(); jCBValorRateio.addItem(it.next().toString()));
    }

    public void initValoresComboBoxCodEvento(java.util.List elementosComboBox)
    {
        jCBCodEvento = inicializarComboBox(jCBCodEvento);
        for(Iterator it = elementosComboBox.iterator(); it.hasNext(); jCBCodEvento.addItem(it.next().toString()));
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

    private void initComponents()
    {
        btnGerarRelatorio = new JButton();
        btnVoltar = new JButton();
        jCBValorRateio = new JComboBox();
        jCBCodEvento = new JComboBox();
        jCBCompetencia = new JComboBox();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        jLabel6 = new JLabel();
        jLabel7 = new JLabel();
        jCBCodDependencia = new JComboBox();
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
        jCBCodEvento.setName("jCBCodigoEvento");
        jCBCompetencia.setName("jCBAnoMesCompetencia");
        jCBCompetencia.addActionListener(new ActionListener() {

             public void actionPerformed(ActionEvent evt)
            {
                jCBCompetenciaActionPerformed(evt);
            }

            
        }
);
        jLabel4.setFont(new Font("Tahoma", 1, 11));
        jLabel4.setText("C\363digo do Evento");
        jLabel5.setFont(new Font("Tahoma", 1, 11));
        jLabel5.setText("Compet\352ncia");
        jLabel6.setFont(new Font("Tahoma", 1, 11));
        jLabel6.setText("C\363digo da Depend\352ncia");
        jLabel7.setFont(new Font("Tahoma", 1, 11));
        jLabel7.setText("Valor do Rateio");
        jCBCodDependencia.setToolTipText("Selecione um item ");
        jCBCodDependencia.addActionListener(new ActionListener() {

             public void actionPerformed(ActionEvent evt)
            {
                jCBCodDependenciaActionPerformed(evt);
            }

            
        }
);
        jLabel1.setFont(new Font("Tahoma", 1, 14));
        jLabel1.setHorizontalAlignment(0);
        jLabel1.setText("Demonstrativo de Rateio");
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(56, 56, 56).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(jLabel5).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 262, -2)).addGroup(layout.createSequentialGroup().addComponent(jLabel4).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 239, -2)).addGroup(layout.createSequentialGroup().addComponent(jLabel7, -2, 86, -2).addGap(250, 250, 250)).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(jCBCompetencia, javax.swing.GroupLayout.Alignment.LEADING, 0, 336, 32767).addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, -1, 336, 32767).addComponent(jCBCodEvento, javax.swing.GroupLayout.Alignment.LEADING, 0, 336, 32767).addComponent(jCBValorRateio, javax.swing.GroupLayout.Alignment.LEADING, 0, 336, 32767).addGroup(layout.createSequentialGroup().addComponent(btnGerarRelatorio, -1, -1, 32767).addGap(58, 58, 58).addComponent(btnVoltar)).addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup().addComponent(jLabel6).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 205, -2)).addComponent(jCBCodDependencia, 0, 336, 32767))).addGap(98, 98, 98)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jLabel1, -2, 22, -2).addGap(13, 13, 13).addComponent(jLabel6).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jCBCodDependencia, -2, -1, -2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jLabel5).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jCBCompetencia, -2, -1, -2).addGap(11, 11, 11).addComponent(jLabel4).addGap(3, 3, 3).addComponent(jCBCodEvento, -2, -1, -2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jLabel7).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jCBValorRateio, -2, -1, -2).addGap(18, 18, 18).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(btnVoltar).addComponent(btnGerarRelatorio)).addContainerGap(24, 32767)));
    }

    private void btnGerarRelatorioActionPerformed(ActionEvent evt)
    {
        ((FrmPrincipal)Contexto.getObject("frmPrincipal")).setCursorAmpulheta(btnGerarRelatorio);
        gerarRelatorioDemRateio();
        ((FrmPrincipal)Contexto.getObject("frmPrincipal")).setCursorPadrao(btnGerarRelatorio);
    }

    public void gerarRelatorioDemRateio()
    {
        String codDependencia = new String();
        String competencia = new String();
        String valorRateio = new String();
        String codEvento = new String();
        if(jCBCodDependencia.getSelectedIndex() != -1)
            codDependencia = jCBCodDependencia.getSelectedItem().toString().trim();
        if(jCBCompetencia.getSelectedIndex() != -1)
            competencia = jCBCompetencia.getSelectedItem().toString().trim();
        if(jCBValorRateio.getSelectedIndex() != -1)
            valorRateio = jCBValorRateio.getSelectedItem().toString().trim();
        if(jCBCodEvento.getSelectedIndex() != -1)
            codEvento = jCBCodEvento.getSelectedItem().toString().trim();
        Controle controle = (Controle)Contexto.getObject("controle");
        java.util.List dados = controle.buscaFiltrosDadosGeraisDemRateio(codDependencia, competencia, valorRateio, codEvento);
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
            controle.gerarRelatorioDemRateio(dados, dadosDependencia, dadosDecl);
            return;
        }
    }

    private void btnVoltarActionPerformed(ActionEvent evt)
    {
        if(RegUtil.exErro)
            ((FrmPrincipal)Contexto.getObject("frmPrincipal")).abrirTela((JPanel)Contexto.getObject("panErro"));
        else
            ((FrmPrincipal)Contexto.getObject("frmPrincipal")).abrirTela((JPanel)Contexto.getObject("panSucesso"));
    }

    private void jCBCodDependenciaActionPerformed(ActionEvent actionevent)
    {
    }

    private void jCBCompetenciaActionPerformed(ActionEvent actionevent)
    {
    }




}
