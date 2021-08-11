
package br.gov.pbh.desif.view.telas;

import br.gov.pbh.des.componentes.utils.DesLookandFeel;
import br.gov.pbh.desif.control.Controle;
import br.gov.pbh.desif.model.pojo.FiltroCNPJCodDependenciaVO;
import br.gov.pbh.desif.model.registros.RegUtil;
import br.gov.pbh.desif.service.contexto.Contexto;
import br.gov.pbh.desif.view.util.SwingUtils;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;

// Referenced classes of package br.gov.pbh.desif.view.telas:
//            FrmPrincipal

public class PanFiltroApuracaoIssqn extends JPanel
{

    private JButton btnGerarRelatorio;
    private JButton btnVoltar;
    private JComboBox jCBCnpjCodDepen;
    private JLabel jLabel1;
    private JLabel jLabel2;

    public PanFiltroApuracaoIssqn()
    {
        initComponents();
        DesLookandFeel.getInstance().formatarJButtons(new JButton[] {
            btnGerarRelatorio, btnVoltar
        });
    }

    public void initValoresComboBox(java.util.List elementosComboBox)
    {
        for(Iterator it = elementosComboBox.iterator(); it.hasNext(); jCBCnpjCodDepen.addItem(formataValorCombo(it.next())));
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

    private String formataValorCombo(Object valor)
    {
        FiltroCNPJCodDependenciaVO valorFiltro = (FiltroCNPJCodDependenciaVO)valor;
        String resp = (new StringBuilder()).append(valorFiltro.getCnpjInstituicao()).append(valorFiltro.getCnpj()).append(" - ").append(valorFiltro.getCodigoDependencia()).toString();
        return resp;
    }

    public void gerarRelatorioApuracaoIssqn()
    {
        String codDependencia = formataValorSelecionadoCombo(jCBCnpjCodDepen.getSelectedItem());
        if(codDependencia == null)
        {
            SwingUtils.msgGenerica(this, "Selecione um item para gerar o relat\363rio.", "Selecione");
            return;
        }
        Controle controle = (Controle)Contexto.getObject("controle");
        Object dadosEstaticos = controle.buscaDadosEstaticosRelatorioApurIssqn(codDependencia);
        java.util.List resp = controle.buscaDadosDinamicosRelatorioApurIssqn(codDependencia);
        if(resp.isEmpty())
        {
            SwingUtils.msgGenerica(this, "N\343o h\341 dados para gerar o relat\363rio!", "Relat\363rio Vazio");
            return;
        } else
        {
            controle.gerarRelatorioApuracaoIssqn(resp, dadosEstaticos);
            return;
        }
    }

    private void initComponents()
    {
        jCBCnpjCodDepen = new JComboBox();
        jLabel1 = new JLabel();
        btnGerarRelatorio = new JButton();
        btnVoltar = new JButton();
        jLabel2 = new JLabel();
        jCBCnpjCodDepen.setToolTipText("Selecione um item ");
        jCBCnpjCodDepen.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt)
            {
                jCBCnpjCodDepenActionPerformed(evt);
            }

        }
);
        jLabel1.setText("Selecione CNPJ - cod depend\352ncia");
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
        jLabel2.setFont(new Font("Tahoma", 1, 14));
        jLabel2.setHorizontalAlignment(0);
        jLabel2.setText("Apura\347\343o do ISSQN");
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel1).addComponent(btnGerarRelatorio)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(btnVoltar).addComponent(jCBCnpjCodDepen, -2, -1, -2)).addContainerGap(173, 32767)).addGroup(layout.createSequentialGroup().addGap(72, 72, 72).addComponent(jLabel2, -1, 313, 32767).addGap(82, 82, 82)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jLabel2, -2, 22, -2).addGap(86, 86, 86).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel1).addComponent(jCBCnpjCodDepen)).addGap(39, 39, 39).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(btnGerarRelatorio).addComponent(btnVoltar)).addGap(99, 99, 99)));
    }

    private void jCBCnpjCodDepenActionPerformed(ActionEvent actionevent)
    {
    }

    private void btnGerarRelatorioActionPerformed(ActionEvent evt)
    {
        ((FrmPrincipal)Contexto.getObject("frmPrincipal")).setCursorAmpulheta(btnGerarRelatorio);
        gerarRelatorioApuracaoIssqn();
        ((FrmPrincipal)Contexto.getObject("frmPrincipal")).setCursorPadrao(btnGerarRelatorio);
    }

    private void btnVoltarActionPerformed(ActionEvent evt)
    {
        if(RegUtil.exErro)
            ((FrmPrincipal)Contexto.getObject("frmPrincipal")).abrirTela((JPanel)Contexto.getObject("panErro"));
        else
            ((FrmPrincipal)Contexto.getObject("frmPrincipal")).abrirTela((JPanel)Contexto.getObject("panSucesso"));
    }



}
