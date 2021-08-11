
package br.gov.pbh.desif.view.telas;

import br.gov.pbh.des.componentes.utils.DesLookandFeel;
import br.gov.pbh.desif.control.Controle;
import br.gov.pbh.desif.model.pojo.FiltroCNPJCodDependenciaVO;
import br.gov.pbh.desif.model.pojo.FiltroCodTribAliquotaVO;
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

public class PanFiltroApuracaoSubtitulo extends JPanel
{

    private JButton btnGerarRelatorio;
    private JButton btnVoltar;
    private JComboBox jCBCnpjCodDepen;
    private JComboBox jCBCodTribAliq;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;

    public PanFiltroApuracaoSubtitulo()
    {
        initComponents();
        DesLookandFeel.getInstance().formatarJButtons(new JButton[] {
            btnGerarRelatorio, btnVoltar
        });
    }

    public void initValoresComboBox(java.util.List elementosCNPJCodDep, java.util.List elementosCodTribAliq)
    {
        Iterator it = elementosCNPJCodDep.iterator();
        jCBCnpjCodDepen.removeAllItems();
        jCBCnpjCodDepen.addItem("");
        for(; it.hasNext(); jCBCnpjCodDepen.addItem(formataValorComboCNPJCodDepen(it.next())));
        Iterator it1 = elementosCodTribAliq.iterator();
        jCBCodTribAliq.removeAllItems();
        jCBCodTribAliq.addItem("");
        for(; it1.hasNext(); jCBCodTribAliq.addItem(formataValorComboCodTribAliq(it1.next())));
    }

    private String formataValorComboCNPJCodDepen(Object valor)
    {
        FiltroCNPJCodDependenciaVO valorFiltro = (FiltroCNPJCodDependenciaVO)valor;
        String resp = (new StringBuilder()).append(valorFiltro.getCnpjInstituicao()).append(valorFiltro.getCnpj()).append(" | ").append(valorFiltro.getCodigoDependencia()).toString();
        return resp;
    }

    private String formataValorComboCodTribAliq(Object valor)
    {
        FiltroCodTribAliquotaVO valorFiltro = (FiltroCodTribAliquotaVO)valor;
        String resp = valorFiltro.getValorAliquotaIssqn().toString();
        return resp;
    }

    private String formataValorSelecionadoCombo(Object valor)
    {
        String s = (String)valor;
        String resp = null;
        if(!s.equals(""))
        {
            String vet[] = s.split("\\|");
            if(vet.length > 1)
                resp = vet[1].trim();
            else
                resp = vet[0].trim();
        }
        return resp;
    }

    public void gerarRelatorioApuracaoSubtitulo()
    {
        Controle controle = (Controle)Contexto.getObject("controle");
        String codDependencia = formataValorSelecionadoCombo(jCBCnpjCodDepen.getSelectedItem());
        String aliquota = formataValorSelecionadoCombo(jCBCodTribAliq.getSelectedItem());
        short tipoConsolidacao = controle.buscaTipoConsolidacao().shortValue();
        if(tipoConsolidacao == 1)
        {
            if(codDependencia == null)
            {
                SwingUtils.msgGenerica(this, "Selecione um c\363digo de depend\352ncia para gerar o relat\363rio.", "Selecione");
                return;
            }
        } else
        if(tipoConsolidacao == 3 && codDependencia == null && aliquota == null)
        {
            SwingUtils.msgGenerica(this, "Selecione um filtro para gerar o relat\363rio.", "Selecione");
            return;
        }
        java.util.List resp = controle.buscaDadosDinamicosRelatorioApurReceitaSubtitulo(codDependencia, aliquota);
        Object dadosEstaticos = controle.buscaDadosEstaticosRelatorioApurSubtitulo(codDependencia);
        if(resp.isEmpty())
        {
            SwingUtils.msgGenerica(this, "N\343o h\341 dados para gerar o relat\363rio!", "Relat\363rio Vazio");
            return;
        } else
        {
            controle.gerarRelatorioApuracaoSubtitulo(resp, dadosEstaticos);
            return;
        }
    }

    private void initComponents()
    {
        jCBCnpjCodDepen = new JComboBox();
        jLabel1 = new JLabel();
        btnGerarRelatorio = new JButton();
        jLabel2 = new JLabel();
        jCBCodTribAliq = new JComboBox();
        btnVoltar = new JButton();
        jLabel3 = new JLabel();
        jCBCnpjCodDepen.setToolTipText("Selecione um item ");
        jCBCnpjCodDepen.addActionListener(new ActionListener() {

          
            public void actionPerformed(ActionEvent evt)
            {
                jCBCnpjCodDepenActionPerformed(evt);
            }

           
        }
);
        jLabel1.setText("CNPJ - c\363d. depend\352ncia:");
        btnGerarRelatorio.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/bt_gerar_relatorio.png")));
        btnGerarRelatorio.addActionListener(new ActionListener() {

      
            public void actionPerformed(ActionEvent evt)
            {
                btnGerarRelatorioActionPerformed(evt);
            }

            
         
        }
);
        jLabel2.setText("C\363d. de tributa\347\343o - aliquota:");
        jCBCodTribAliq.setToolTipText("Selecione um item ");
        jCBCodTribAliq.addActionListener(new ActionListener() {

         
            public void actionPerformed(ActionEvent evt)
            {
                jCBCodTribAliqActionPerformed(evt);
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
        jLabel3.setFont(new Font("Tahoma", 1, 14));
        jLabel3.setHorizontalAlignment(0);
        jLabel3.setText("Apura\347\343o por Subt\355tulo");
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(69, 69, 69).addComponent(btnGerarRelatorio)).addGroup(layout.createSequentialGroup().addGap(83, 83, 83).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)))).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(jCBCnpjCodDepen, -2, -1, -2).addComponent(jCBCodTribAliq, -2, -1, -2).addComponent(btnVoltar)).addContainerGap(114, 32767)).addGroup(layout.createSequentialGroup().addGap(44, 44, 44).addComponent(jLabel3, -2, 350, -2).addContainerGap(73, 32767)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap().addComponent(jLabel3, -2, 22, -2).addGap(35, 35, 35).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel1).addComponent(jCBCnpjCodDepen)).addGap(52, 52, 52).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel2).addComponent(jCBCodTribAliq)).addGap(70, 70, 70).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(btnGerarRelatorio).addComponent(btnVoltar)).addGap(47, 47, 47)));
    }

    private void jCBCnpjCodDepenActionPerformed(ActionEvent actionevent)
    {
    }

    private void btnGerarRelatorioActionPerformed(ActionEvent evt)
    {
        ((FrmPrincipal)Contexto.getObject("frmPrincipal")).setCursorAmpulheta(btnGerarRelatorio);
        gerarRelatorioApuracaoSubtitulo();
        ((FrmPrincipal)Contexto.getObject("frmPrincipal")).setCursorPadrao(btnGerarRelatorio);
    }

    private void jCBCodTribAliqActionPerformed(ActionEvent actionevent)
    {
    }

    private void btnVoltarActionPerformed(ActionEvent evt)
    {
        if(RegUtil.exErro)
            ((FrmPrincipal)Contexto.getObject("frmPrincipal")).abrirTela((JPanel)Contexto.getObject("panErro"));
        else
            ((FrmPrincipal)Contexto.getObject("frmPrincipal")).abrirTela((JPanel)Contexto.getObject("panSucesso"));
    }




}
