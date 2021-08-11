
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
import java.util.ArrayList;
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

public class PanFiltroPGCC extends JPanel
{

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

    public PanFiltroPGCC()
    {
        initComponents();
        DesLookandFeel.getInstance().formatarJButtons(new JButton[] {
            btnGerarRelatorio, btnVoltar
        });
    }

    public void initValoresComboBoxCodTributacao(java.util.List elementosComboBox)
    {
        jCBCodTribDesif = inicializarComboBox(jCBCodTribDesif);
        for(Iterator it = elementosComboBox.iterator(); it.hasNext(); jCBCodTribDesif.addItem(it.next().toString()));
    }

    public void initValoresComboBoxContas(java.util.List elementosComboBox)
    {
        jCBConta = inicializarComboBox(jCBConta);
        for(Iterator it = elementosComboBox.iterator(); it.hasNext(); jCBConta.addItem(it.next().toString()));
    }

    public void initValoresComboBoxContaSuperior(java.util.List elementosComboBox)
    {
        jCBContaSuperior = inicializarComboBox(jCBContaSuperior);
        for(Iterator it = elementosComboBox.iterator(); it.hasNext(); jCBContaSuperior.addItem(it.next().toString()));
    }

    public void initValoresComboBoxContaCosif(java.util.List elementosComboBox)
    {
        jCBContaCosif = inicializarComboBox(jCBContaCosif);
        for(Iterator it = elementosComboBox.iterator(); it.hasNext(); jCBContaCosif.addItem(it.next().toString()));
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
        jCBCodTribDesif = new JComboBox();
        jCBContaCosif = new JComboBox();
        jCBContaSuperior = new JComboBox();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        jLabel6 = new JLabel();
        jLabel7 = new JLabel();
        jCBConta = new JComboBox();
        jLabel1 = new JLabel();
        setPreferredSize(new Dimension(467, 369));
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
        jCBContaCosif.setName("jCBCodigoEvento");
        jCBContaSuperior.setName("jCBAnoMesCompetencia");
        jCBContaSuperior.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt)
            {
                jCBContaSuperiorActionPerformed(evt);
            }

        }
);
        jLabel4.setFont(new Font("Tahoma", 1, 11));
        jLabel4.setText("Conta COSIF-INICIAR");
        jLabel5.setFont(new Font("Tahoma", 1, 11));
        jLabel5.setText("Conta Superior");
        jLabel6.setFont(new Font("Tahoma", 1, 11));
        jLabel6.setText("Conta");
        jLabel7.setFont(new Font("Tahoma", 1, 11));
        jLabel7.setText("C\363digo Tributa\347\343o DES-IF");
        jCBConta.setToolTipText("Selecione um item ");
        jCBConta.addActionListener(new ActionListener() {
          @Override
         public void actionPerformed(ActionEvent evt)
            {
                jCBContaActionPerformed(evt);
            }

        }
        );
        jLabel1.setFont(new Font("Tahoma", 1, 14));
        jLabel1.setHorizontalAlignment(0);
        jLabel1.setText("PGCC com COSIF");
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(77, 77, 77).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(jLabel6).addContainerGap(357, 32767)).addGroup(layout.createSequentialGroup().addComponent(jLabel5).addGap(341, 341, 341)).addGroup(layout.createSequentialGroup().addComponent(jLabel4).addContainerGap(321, 32767)).addGroup(layout.createSequentialGroup().addComponent(jLabel7).addContainerGap()).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, -1, 348, 32767).addComponent(jCBConta, javax.swing.GroupLayout.Alignment.LEADING, 0, 348, 32767).addComponent(jCBContaSuperior, javax.swing.GroupLayout.Alignment.LEADING, 0, 348, 32767).addComponent(jCBContaCosif, javax.swing.GroupLayout.Alignment.LEADING, 0, 348, 32767).addComponent(jCBCodTribDesif, javax.swing.GroupLayout.Alignment.LEADING, 0, 348, 32767).addGroup(layout.createSequentialGroup().addGap(2, 2, 2).addComponent(btnGerarRelatorio, -1, 185, 32767).addGap(58, 58, 58).addComponent(btnVoltar))).addGap(77, 77, 77)))));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jLabel1, -2, 26, -2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel6).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jCBConta, -2, -1, -2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel5).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jCBContaSuperior, -2, -1, -2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jLabel4).addGap(3, 3, 3).addComponent(jCBContaCosif, -2, -1, -2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jLabel7).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jCBCodTribDesif, -2, -1, -2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, 32767).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(btnVoltar).addComponent(btnGerarRelatorio)).addContainerGap()));
    }

    private void btnGerarRelatorioActionPerformed(ActionEvent evt)
    {
        ((FrmPrincipal)Contexto.getObject("frmPrincipal")).setCursorAmpulheta(btnGerarRelatorio);
        gerarRelatorioPGCC();
        ((FrmPrincipal)Contexto.getObject("frmPrincipal")).setCursorPadrao(btnGerarRelatorio);
    }

    public void gerarRelatorioPGCC()
    {
        String conta = new String();
        String contaSuperior = new String();
        String codTribDesif = new String();
        String contaCosif = new String();
        if(jCBConta.getSelectedIndex() != -1)
            conta = jCBConta.getSelectedItem().toString().trim();
        if(jCBContaSuperior.getSelectedIndex() != -1)
            contaSuperior = jCBContaSuperior.getSelectedItem().toString().trim();
        if(jCBCodTribDesif.getSelectedIndex() != -1)
            codTribDesif = jCBCodTribDesif.getSelectedItem().toString().trim();
        if(jCBContaCosif.getSelectedIndex() != -1)
            contaCosif = jCBContaCosif.getSelectedItem().toString().trim();
        Controle controle = (Controle)Contexto.getObject("controle");
        java.util.List dados = controle.buscaFiltrosDadosGeraisPGCC(conta, contaSuperior, contaCosif, codTribDesif);
        ArrayList listaContasAnalit = new ArrayList();
        listaContasAnalit = (ArrayList)dados;
        java.util.List dadosDecl = controle.buscaFiltrosDadosDeclaracao();
        if(dados.isEmpty())
        {
            SwingUtils.msgGenerica(this, "N\343o h\341 dados para gerar o relat\363rio!", "Relat\363rio Vazio");
            return;
        } else
        {
            controle.gerarRelatorioPGCC(listaContasAnalit, dadosDecl);
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

    private void jCBContaActionPerformed(ActionEvent actionevent)
    {
    }

    private void jCBContaSuperiorActionPerformed(ActionEvent actionevent)
    {
    }




}
