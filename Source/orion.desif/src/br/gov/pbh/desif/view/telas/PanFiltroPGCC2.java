
package br.gov.pbh.desif.view.telas;

import br.gov.pbh.des.componentes.utils.DesLookandFeel;
import br.gov.pbh.desif.control.Controle;
import br.gov.pbh.desif.dao.PgccsPaiFilhoDao;
import br.gov.pbh.desif.model.pojo.PlanoGeralContaComentado;
import br.gov.pbh.desif.model.registros.RegUtil;
import br.gov.pbh.desif.service.contexto.Contexto;
import br.gov.pbh.desif.view.util.SwingUtils;
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

public class PanFiltroPGCC2 extends JPanel
{

    private JButton btnGerarRelatorio;
    private JButton btnVoltar;
    private JComboBox jCBCodTributacao;
    private JComboBox jCBCodigoPGCC;
    private JLabel jLabel1;
    private JLabel jLabel6;
    private JLabel jLabel7;

    public PanFiltroPGCC2()
    {
        initComponents();
        DesLookandFeel.getInstance().formatarJButtons(new JButton[] {
            btnGerarRelatorio, btnVoltar
        });
    }

    public void initValoresComboBoxCodTributacao(java.util.List elementosComboBox)
    {
        jCBCodTributacao = inicializarComboBox(jCBCodTributacao);
        for(Iterator it = elementosComboBox.iterator(); it.hasNext(); jCBCodTributacao.addItem(it.next().toString()));
    }

    public void initValoresComboBoxContas(java.util.List elementosComboBox)
    {
        jCBCodigoPGCC = inicializarComboBox(jCBCodigoPGCC);
        PgccsPaiFilhoDao pgccsPFIdDao = (PgccsPaiFilhoDao)Contexto.getObject("pgccsPFIdDao");
        ArrayList listaContasAnalit = new ArrayList();
        Iterator i = elementosComboBox.iterator();
        do
        {
            if(!i.hasNext())
                break;
            PlanoGeralContaComentado pgcc = (PlanoGeralContaComentado)i.next();
            if(!pgccsPFIdDao.identificarPossuiFilhos(pgcc))
                jCBCodigoPGCC.addItem(pgcc.getConta());
        } while(true);
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

    public void gerarRelatorioPGCC2()
    {
        String codTributacao = new String();
        String conta = new String();
        if(jCBCodTributacao.getSelectedIndex() != -1)
            codTributacao = jCBCodTributacao.getSelectedItem().toString().trim();
        if(jCBCodigoPGCC.getSelectedIndex() != -1)
            conta = jCBCodigoPGCC.getSelectedItem().toString().trim();
        Controle controle = (Controle)Contexto.getObject("controle");
        java.util.List dados = controle.buscaFiltrosDadosGeraisPGCC2(codTributacao, conta);
        PgccsPaiFilhoDao pgccsPFIdDao = (PgccsPaiFilhoDao)Contexto.getObject("pgccsPFIdDao");
        ArrayList listaContasAnalit = new ArrayList();
        Iterator i = dados.iterator();
        do
        {
            if(!i.hasNext())
                break;
            PlanoGeralContaComentado pgcc = (PlanoGeralContaComentado)i.next();
            if(!pgccsPFIdDao.identificarPossuiFilhos(pgcc))
                listaContasAnalit.add(pgcc);
        } while(true);
        java.util.List dadosDecl = controle.buscaFiltrosDadosDeclaracao();
        if(dados.isEmpty())
        {
            SwingUtils.msgGenerica(this, "N\343o h\341 dados para gerar o relat\363rio!", "Relat\363rio Vazio");
            return;
        } else
        {
            controle.gerarRelatorioPGCC2(listaContasAnalit, dadosDecl);
            return;
        }
    }

    private void initComponents()
    {
        btnGerarRelatorio = new JButton();
        btnVoltar = new JButton();
        jLabel6 = new JLabel();
        jLabel7 = new JLabel();
        jCBCodigoPGCC = new JComboBox();
        jCBCodTributacao = new JComboBox();
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
        jLabel6.setText("C\363digo Tributa\347\343o DES-IF");
        jLabel7.setFont(new Font("Tahoma", 1, 11));
        jLabel7.setText("Conta (C\363digo PGCC)");
        jCBCodigoPGCC.setModel(new DefaultComboBoxModel(new String[] {
            " "
        }));
        jCBCodigoPGCC.setName("jCBCodigoPGCC");
        jCBCodTributacao.setModel(new DefaultComboBoxModel(new String[] {
            " "
        }));
        jCBCodTributacao.setName("jCBCodTributacao");
        jCBCodTributacao.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt)
            {
                jCBCodTributacaoActionPerformed(evt);
            }

        }
);
        jLabel1.setFont(new Font("Tahoma", 1, 14));
        jLabel1.setHorizontalAlignment(0);
        jLabel1.setText("Contas Anal\355ticas PGCC");
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(61, 61, 61).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addGroup(layout.createSequentialGroup().addComponent(btnGerarRelatorio, -2, 185, -2).addGap(61, 61, 61).addComponent(btnVoltar)).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false).addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, -1, -1, 32767).addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup().addComponent(jLabel7).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 233, -2)).addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING).addComponent(jCBCodTributacao, javax.swing.GroupLayout.Alignment.LEADING, 0, -1, 32767).addComponent(jCBCodigoPGCC, javax.swing.GroupLayout.Alignment.LEADING, 0, -1, 32767))).addGap(76, 76, 76)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jLabel1, -2, 26, -2).addGap(18, 18, 18).addComponent(jLabel6).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jCBCodTributacao, -2, 22, -2).addGap(18, 18, 18).addComponent(jLabel7).addGap(3, 3, 3).addComponent(jCBCodigoPGCC, -2, -1, -2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, 32767).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(btnGerarRelatorio).addComponent(btnVoltar)).addGap(41, 41, 41)));
    }

    private void btnGerarRelatorioActionPerformed(ActionEvent evt)
    {
        ((FrmPrincipal)Contexto.getObject("frmPrincipal")).setCursorAmpulheta(btnGerarRelatorio);
        gerarRelatorioPGCC2();
        ((FrmPrincipal)Contexto.getObject("frmPrincipal")).setCursorPadrao(btnGerarRelatorio);
    }

    private void btnVoltarActionPerformed(ActionEvent evt)
    {
        if(RegUtil.exErro)
            ((FrmPrincipal)Contexto.getObject("frmPrincipal")).abrirTela((JPanel)Contexto.getObject("panErro"));
        else
            ((FrmPrincipal)Contexto.getObject("frmPrincipal")).abrirTela((JPanel)Contexto.getObject("panSucesso"));
    }

    private void jCBCodTributacaoActionPerformed(ActionEvent actionevent)
    {
    }



}
