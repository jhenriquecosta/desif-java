
package br.gov.pbh.desif.view.telas;

import br.gov.pbh.des.componentes.utils.DesLookandFeel;
import br.gov.pbh.desif.control.Controle;
import br.gov.pbh.desif.model.registros.RegUtil;
import br.gov.pbh.desif.service.contexto.Contexto;
import br.gov.pbh.desif.view.util.SwingUtils;
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

public class PanFiltroTarifasServicosInstituicao extends JPanel
{

    private JButton btnGerarRelatorio;
    private JButton btnVoltar;
    private JComboBox jCBCodIdTarifa;
    private JComboBox jCBCodSubtitulo;
    private JLabel jLabel1;
    private JLabel jLabel6;
    private JLabel jLabel7;

    public PanFiltroTarifasServicosInstituicao()
    {
        initComponents();
        DesLookandFeel.getInstance().formatarJButtons(new JButton[] {
            btnGerarRelatorio, btnVoltar
        });
    }

    public void initValoresComboBoxCodIdTarifa(java.util.List elementosComboBox)
    {
        jCBCodIdTarifa.removeAllItems();
        jCBCodIdTarifa.setModel(new DefaultComboBoxModel(new String[] {
            " "
        }));
        jCBCodIdTarifa.setSelectedIndex(-1);
        jCBCodIdTarifa.setSelectedItem(null);
        for(Iterator it = elementosComboBox.iterator(); it.hasNext(); jCBCodIdTarifa.addItem(it.next().toString()));
    }

    public void initValoresComboBoxCodSubtitulo(java.util.List elementosComboBox)
    {
        jCBCodSubtitulo.removeAllItems();
        jCBCodSubtitulo.setModel(new DefaultComboBoxModel(new String[] {
            " "
        }));
        jCBCodSubtitulo.setSelectedIndex(-1);
        jCBCodSubtitulo.setSelectedItem(null);
        for(Iterator it = elementosComboBox.iterator(); it.hasNext(); jCBCodSubtitulo.addItem(it.next().toString()));
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

    public void gerarRelatorioTarServInstituicao()
    {
        String codSubtitulo = new String();
        String idTarifa = new String();
        if(jCBCodIdTarifa.getSelectedIndex() != -1)
            idTarifa = jCBCodIdTarifa.getSelectedItem().toString().trim();
        if(jCBCodSubtitulo.getSelectedIndex() != -1)
            codSubtitulo = jCBCodSubtitulo.getSelectedItem().toString().trim();
        Controle controle = (Controle)Contexto.getObject("controle");
        java.util.List dados = controle.buscaFiltrosDadosGeraisTarServInstituicao(idTarifa, codSubtitulo);
        java.util.List dadosDecl = controle.buscaFiltrosDadosDeclaracao();
        if(dados.isEmpty())
        {
            SwingUtils.msgGenerica(this, "N\343o h\341 dados para gerar o relat\363rio!", "Relat\363rio Vazio");
            return;
        } else
        {
            controle.gerarRelatorioTarServInstituicao(dados, dadosDecl);
            return;
        }
    }

    private void initComponents()
    {
        btnGerarRelatorio = new JButton();
        btnVoltar = new JButton();
        jLabel6 = new JLabel();
        jLabel7 = new JLabel();
        jCBCodSubtitulo = new JComboBox();
        jCBCodIdTarifa = new JComboBox();
        jLabel1 = new JLabel();
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
        jLabel6.setFont(new Font("Tahoma", 1, 11));
        jLabel6.setText("C\363digo de identifica\347\343o da tarifa");
        jLabel7.setFont(new Font("Tahoma", 1, 11));
        jLabel7.setText("C\363digo do subt\355tulo");
        jCBCodSubtitulo.setModel(new DefaultComboBoxModel(new String[] {
            " "
        }));
        jCBCodSubtitulo.setName("jCBCodSubtitulo");
        jCBCodIdTarifa.setModel(new DefaultComboBoxModel(new String[] {
            " "
        }));
        jCBCodIdTarifa.setName("jCBCodIdTarifa");
        jCBCodIdTarifa.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt)
            {
                jCBCodIdTarifaActionPerformed(evt);
            }

            
        }
);
        jLabel1.setFont(new Font("Tahoma", 1, 14));
        jLabel1.setHorizontalAlignment(0);
        jLabel1.setText("Tarifas de Servi\347o");
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(71, 71, 71).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(jLabel7).addContainerGap()).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, -1, 317, 32767).addComponent(jCBCodSubtitulo, 0, 317, 32767).addComponent(jCBCodIdTarifa, javax.swing.GroupLayout.Alignment.LEADING, 0, 317, 32767).addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(btnGerarRelatorio, -1, 179, 32767).addGap(35, 35, 35).addComponent(btnVoltar))).addGap(79, 79, 79)))));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap().addComponent(jLabel1, -2, 26, -2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, 32767).addComponent(jLabel6).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jCBCodIdTarifa, -2, -1, -2).addGap(18, 18, 18).addComponent(jLabel7).addGap(3, 3, 3).addComponent(jCBCodSubtitulo, -2, -1, -2).addGap(65, 65, 65).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(btnVoltar).addComponent(btnGerarRelatorio)).addGap(107, 107, 107)));
    }

    private void btnGerarRelatorioActionPerformed(ActionEvent evt)
    {
        ((FrmPrincipal)Contexto.getObject("frmPrincipal")).setCursorAmpulheta(btnGerarRelatorio);
        gerarRelatorioTarServInstituicao();
        ((FrmPrincipal)Contexto.getObject("frmPrincipal")).setCursorPadrao(btnGerarRelatorio);
    }

    private void btnVoltarActionPerformed(ActionEvent evt)
    {
        if(RegUtil.exErro)
            ((FrmPrincipal)Contexto.getObject("frmPrincipal")).abrirTela((JPanel)Contexto.getObject("panErro"));
        else
            ((FrmPrincipal)Contexto.getObject("frmPrincipal")).abrirTela((JPanel)Contexto.getObject("panSucesso"));
    }

    private void jCBCodIdTarifaActionPerformed(ActionEvent actionevent)
    {
    }



}
