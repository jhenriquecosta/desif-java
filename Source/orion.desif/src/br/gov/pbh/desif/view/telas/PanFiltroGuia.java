
package br.gov.pbh.desif.view.telas;

import br.gov.pbh.des.componentes.utils.DesLookandFeel;
import br.gov.pbh.des.view.components.table.ColunaJTable;
import br.gov.pbh.des.view.components.table.TableDes;
import br.gov.pbh.desif.control.Controle;
import br.gov.pbh.desif.model.pojo.GuiaEstaticaVO;
import br.gov.pbh.desif.model.pojo.IssqnMensal;
import br.gov.pbh.desif.service.contexto.Contexto;
import br.gov.pbh.desif.view.util.SwingUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle;
import javax.swing.table.DefaultTableModel;

// Referenced classes of package br.gov.pbh.desif.view.telas:
//            DialGuia, FrmPrincipal

public class PanFiltroGuia extends JPanel
{

    private Controle controle;
    private List aux;
    private JButton btnVoltar;
    private JLabel jLabel1;
    private JScrollPane jScrollPane2;
    private TableDes tblGuias;

    public PanFiltroGuia()
    {
        initComponents();
        construirTabela();
        DesLookandFeel.getInstance().formatarJButtons(new JButton[] {
            btnVoltar
        });
    }

    private void initComponents()
    {
        jScrollPane2 = new JScrollPane();
        tblGuias = new TableDes();
        jLabel1 = new JLabel();
        btnVoltar = new JButton();
        tblGuias.setModel(new DefaultTableModel(new Object[0][], new String[0]));
        tblGuias.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                tblGuiasMouseClicked(evt);
            }

        }
);
        jScrollPane2.setViewportView(tblGuias);
        jLabel1.setText("Clique duas vezes sobre a linha que deseja calcular a guia.");
        btnVoltar.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/voltar.gif")));
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {

          
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnVoltarActionPerformed(evt);
            }

            
        }
);
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jScrollPane2, -1, 465, 32767).addComponent(jLabel1).addComponent(btnVoltar, javax.swing.GroupLayout.Alignment.TRAILING)).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap().addComponent(jLabel1).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jScrollPane2, -1, 216, 32767).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(btnVoltar).addContainerGap()));
    }

    private void construirTabela()
    {
        tblGuias.setColunas(inicializarColunas());
        tblGuias.setDados(getDados());
        tblGuias.setAddCheckboxColumn(false);
        tblGuias.setTableModel();
    }

    private ColunaJTable[] inicializarColunas()
    {
        ColunaJTable colunas[] = new ColunaJTable[10];
        ColunaJTable coluna0 = new ColunaJTable(false, 100, "CNPJ Depend\352ncia");
        ColunaJTable coluna1 = new ColunaJTable(false, 120, "Receita Tribut\341vel");
        ColunaJTable coluna2 = new ColunaJTable(false, 70, "Dedu\347\365es");
        ColunaJTable coluna3 = new ColunaJTable(false, 100, "Base de calculo");
        ColunaJTable coluna4 = new ColunaJTable(false, 80, "ISS Devido");
        ColunaJTable coluna5 = new ColunaJTable(false, 80, "ISSQN retido");
        ColunaJTable coluna6 = new ColunaJTable(false, 80, "Incentivos");
        ColunaJTable coluna7 = new ColunaJTable(false, 120, "Cr\351ditos a compensar");
        ColunaJTable coluna8 = new ColunaJTable(false, 100, "ISSQN Recolhido");
        ColunaJTable coluna9 = new ColunaJTable(false, 100, "ISSQN a recolher");
        colunas[0] = coluna0;
        colunas[1] = coluna1;
        colunas[2] = coluna2;
        colunas[3] = coluna3;
        colunas[4] = coluna4;
        colunas[5] = coluna5;
        colunas[6] = coluna6;
        colunas[7] = coluna7;
        colunas[8] = coluna8;
        colunas[9] = coluna9;
        return colunas;
    }

    public List getDados()
    {
        controle = new Controle();
        aux = controle.buscaFiltrosGuia();
        List mat = new ArrayList();
        for(int i = 0; i < aux.size(); i++)
        {
            List dados = new ArrayList();
            dados.add(((IssqnMensal)aux.get(i)).getCnpj());
            dados.add(((IssqnMensal)aux.get(i)).getValorReceitaDeclaradaConsolidada());
            dados.add(Double.valueOf(((IssqnMensal)aux.get(i)).getValorDeducaoReceitaDeclaradaConsolidada().doubleValue() + ((IssqnMensal)aux.get(i)).getValorDeducaoReceitaDeclaradaSubtitulo().doubleValue()));
            dados.add(((IssqnMensal)aux.get(i)).getValorBaseCalculo());
            dados.add(((IssqnMensal)aux.get(i)).getValorIssqnDevido());
            dados.add(((IssqnMensal)aux.get(i)).getValorIssqnRetido());
            dados.add(Double.valueOf(((IssqnMensal)aux.get(i)).getValorIncentivoFiscal().doubleValue() + ((IssqnMensal)aux.get(i)).getValorIncentivoFiscalSubtitulo().doubleValue()));
            dados.add(((IssqnMensal)aux.get(i)).getValorCredito());
            dados.add(((IssqnMensal)aux.get(i)).getValorIssqnRecolhido());
            dados.add(((IssqnMensal)aux.get(i)).getValorIssqnRecolher());
            mat.add(dados);
        }

        return mat;
    }

    private void gerarGuia(java.awt.event.MouseEvent evt)
    {
        if(evt.getClickCount() == 2)
        {
            String cnpj = (String)tblGuias.getPosicao(tblGuias.getSelectedRow(), 0);
            Object resp = buscaValorListaIssqnMensal(cnpj);
            Object respGuia = controle.buscaDadosGuia(cnpj);
            IssqnMensal issqn = (IssqnMensal)resp;
            GuiaEstaticaVO dadosDescritGuia = (GuiaEstaticaVO)respGuia;
            Double txExpediente = controle.buscaTaxaExpediente();
            if(issqn.getValorIssqnDevido().doubleValue() > 0.0D)
            {
                Object dadosGuia[] = new Object[3];
                dadosGuia[0] = resp;
                dadosGuia[1] = respGuia;
                dadosGuia[2] = txExpediente;
                DialGuia dialog = new DialGuia(null, true, dadosGuia);
                dialog.setVisible(true);
            } else
            {
                SwingUtils.msgAlertaEnvio(this, "O calculo da guia n\343o pode ser efetuado, Est\341 \351 uma declara\347\343o sem movimento.");
            }
        }
    }

    private Object buscaValorListaIssqnMensal(String cnpj)
    {
        Object resp = null;
        for(int i = 0; i < aux.size(); i++)
            if(((IssqnMensal)aux.get(i)).getCnpj().equals(cnpj))
                resp = aux.get(i);

        return resp;
    }

    public boolean verificaVencimentoProximoAno(Date competencia)
    {
        boolean resp = false;
        return resp;
    }

    private void tblGuiasMouseClicked(java.awt.event.MouseEvent evt)
    {
        gerarGuia(evt);
    }

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt)
    {
        ((FrmPrincipal)Contexto.getObject("frmPrincipal")).abrirTela((JPanel)Contexto.getObject("panSucesso"));
    }


}
