
package br.gov.pbh.desif.view.telas;

import br.gov.pbh.des.componentes.utils.DesLookandFeel;
import br.gov.pbh.des.componentes.utils.SwingUtils;
import br.gov.pbh.des.view.components.table.ColunaJTable;
import br.gov.pbh.des.view.components.table.TableDes;
import br.gov.pbh.desif.control.Controle;
import br.gov.pbh.desif.model.pojo.Alerta;
import br.gov.pbh.desif.model.pojo.SistemaAlerta;
import br.gov.pbh.desif.model.registros.RegUtil;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.jdesktop.layout.GroupLayout;

public class DialAlerta extends JDialog
{

    private Controle controle;
    private static long pagina;
    private static long contSistemaAlerta = 0L;
    private static int delta = 10;
    private static long numPaginas = 0L;
    private boolean resto;
    private Alerta alerta;
    private JButton btnAnterior;
    private JButton btnPrimeiro;
    private JButton btnProximo;
    private JButton btnUltimo;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JScrollPane jScrollPane2;
    private JScrollPane jScrollPane3;
    private JLabel jlbApresentacaoNomeArq;
    private JLabel jlbDetErro;
    private JLabel jlbListErro;
    private JLabel jlbNomeArquivo;
    private JTextPane jtpErro;
    private TableDes tblErros;

    public DialAlerta(Frame parent, boolean modal)
    {
        super(parent, modal);
        pagina = 0L;
        initComponents();
        controle = new Controle();
        construirTabela();
        jlbNomeArquivo.setText(RegUtil.nomeArq);
        paginacao();
        setInicioBotoesPaginacao();
        SwingUtils.getInstance().centralizar(this);
        DesLookandFeel.getInstance().formatarJButtons(new JButton[] {
            btnAnterior, btnPrimeiro, btnProximo, btnUltimo
        });
    }

    public void setInicioBotoesPaginacao()
    {
        if(numPaginas == 0L)
        {
            btnProximo.setEnabled(false);
            btnAnterior.setEnabled(false);
            btnPrimeiro.setEnabled(false);
            btnUltimo.setEnabled(false);
        } else
        {
            btnAnterior.setEnabled(false);
            btnPrimeiro.setEnabled(false);
        }
    }

    private void paginacao()
    {
        contSistemaAlerta = controle.countSistemaAlerta();
        numPaginas = contSistemaAlerta / (long)delta;
        if(contSistemaAlerta % (long)delta == 0L)
        {
            jLabel2.setText((new StringBuilder()).append(numPaginas + 1L).append("").toString());
            resto = false;
        } else
        {
            long setarLabelTotalPaginas = numPaginas + 1L;
            jLabel2.setText((new StringBuilder()).append(setarLabelTotalPaginas).append("").toString());
            resto = true;
        }
    }

    private void removerCodErro()
    {
        tblErros.removeColuna("ce");
        tblErros.removeColuna("valErro");
        tblErros.setTableModel();
    }

    private void construirTabela()
    {
        tblErros.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

      
            public void valueChanged(ListSelectionEvent evt)
            {
                tblErrosValueChanged(evt);
            }

        }
);
        tblErros.setColunas(inicializarColunas());
        tblErros.setDados(getDados());
        tblErros.setTableModel();
        jtpErro.setText("Selecione uma linha para obter a descri\347\343o do alerta.");
        removerCodErro();
    }

    private void tblErrosValueChanged(ListSelectionEvent evt)
    {
        detalharErro();
    }

    private ColunaJTable[] inicializarColunas()
    {
        ColunaJTable colunas[] = new ColunaJTable[7];
        ColunaJTable coluna1 = new ColunaJTable(false, 120, "Tipo");
        ColunaJTable coluna2 = new ColunaJTable(false, 120, "Linha");
        ColunaJTable coluna3 = new ColunaJTable(false, 120, "Campo");
        ColunaJTable coluna4 = new ColunaJTable(false, 120, "Registro");
        ColunaJTable coluna5 = new ColunaJTable(false, 120, "Nome Campo");
        ColunaJTable coluna6 = new ColunaJTable(false, 0, "valErro");
        ColunaJTable coluna7 = new ColunaJTable(false, 0, "ce");
        colunas[0] = coluna1;
        colunas[1] = coluna2;
        colunas[2] = coluna3;
        colunas[3] = coluna4;
        colunas[4] = coluna5;
        colunas[5] = coluna6;
        colunas[6] = coluna7;
        return colunas;
    }

    public java.util.List getDados()
    {
        String tipoErro = "";
        java.util.List aux = controle.buscaSistemaAlerta(pagina);
        java.util.List mat = new ArrayList();
        for(int i = 0; i < aux.size(); i++)
        {
            java.util.List dados = new ArrayList();
            if(((SistemaAlerta)aux.get(i)).getTipoErro().intValue() == 1)
                tipoErro = "Alerta";
            else
            if(((SistemaAlerta)aux.get(i)).getTipoErro().intValue() == 2)
                tipoErro = "Alerta";
            dados.add(tipoErro);
            dados.add((new StringBuilder()).append(((SistemaAlerta)aux.get(i)).getLinha()).append("").toString());
            dados.add((new StringBuilder()).append(((SistemaAlerta)aux.get(i)).getColuna()).append("").toString());
            dados.add(((SistemaAlerta)aux.get(i)).getRegistro());
            dados.add(((SistemaAlerta)aux.get(i)).getNomeCampo());
            dados.add(((SistemaAlerta)aux.get(i)).getValorCampoErro());
            dados.add(((SistemaAlerta)aux.get(i)).getAlerta().getId());
            mat.add(dados);
        }

        return mat;
    }

    private void detalharErro()
    {
        if(tblErros.getSelectedRow() >= 0 && tblErros.getSelectedColumn() >= 0)
        {
            String cod = (String)tblErros.getPosicao(tblErros.getSelectedRow(), tblErros.getModel().getColumnCount() + 1);
            String valErro = (String)tblErros.getPosicao(tblErros.getSelectedRow(), tblErros.getModel().getColumnCount());
            alerta = (Alerta)buscarAlertaCodigo(cod);
            jtpErro.setEditable(false);
            jtpErro.setFont(new Font("Arial", 0, 11));
            jtpErro.setText(construirTexto(alerta, valErro));
            jtpErro.setCaretPosition(0);
        }
    }

    public void proximoPaginacao()
    {
        double pagAux = pagina + 1L;
        if(pagAux <= (double)numPaginas)
            pagina++;
        if(pagAux == (double)numPaginas)
        {
            btnProximo.setEnabled(false);
            btnUltimo.setEnabled(false);
        }
        if(pagina != 0L && !btnAnterior.isEnabled())
        {
            btnAnterior.setEnabled(true);
            btnPrimeiro.setEnabled(true);
        }
        setLabelPagina((new StringBuilder()).append(pagina + 1L).append("").toString());
        if(pagAux + 1.0D == (double)numPaginas && !resto)
        {
            btnProximo.setEnabled(false);
            btnUltimo.setEnabled(false);
        }
        construirTabela();
    }

    public void anteriorPaginacao()
    {
        if(pagina > 0L)
            pagina--;
        if(pagina == 0L)
        {
            btnAnterior.setEnabled(false);
            btnPrimeiro.setEnabled(false);
            btnProximo.setEnabled(true);
            btnUltimo.setEnabled(true);
        }
        if(pagina != 0L && !btnProximo.isEnabled())
        {
            btnAnterior.setEnabled(true);
            btnPrimeiro.setEnabled(true);
        }
        if(pagina != numPaginas && !btnProximo.isEnabled())
        {
            btnProximo.setEnabled(true);
            btnUltimo.setEnabled(true);
        }
        setLabelPagina((new StringBuilder()).append(pagina + 1L).append("").toString());
        construirTabela();
    }

    public void inicialPaginacao()
    {
        pagina = 0L;
        btnAnterior.setEnabled(false);
        btnPrimeiro.setEnabled(false);
        if(!btnProximo.isEnabled())
        {
            btnProximo.setEnabled(true);
            btnUltimo.setEnabled(true);
        }
        setLabelPagina((new StringBuilder()).append(pagina + 1L).append("").toString());
        construirTabela();
    }

    public void finalPaginacao()
    {
        pagina = numPaginas;
        btnProximo.setEnabled(false);
        btnUltimo.setEnabled(false);
        if(pagina != 0L && !btnAnterior.isEnabled())
        {
            btnAnterior.setEnabled(true);
            btnPrimeiro.setEnabled(true);
        }
        if(resto)
        {
            setLabelPagina((new StringBuilder()).append(pagina + 1L).append("").toString());
        } else
        {
            setLabelPagina((new StringBuilder()).append(pagina).append("").toString());
            pagina--;
        }
        construirTabela();
    }

    public void setLabelPagina(String novoValor)
    {
        jLabel1.setText(novoValor);
    }

    private Object buscarAlertaCodigo(String txt)
    {
        return controle.buscaAlertaCodigo(txt);
    }

    private String construirTexto(Alerta a, String valorErro)
    {
        String resp = (new StringBuilder()).append("Codigo alerta : ").append(a.getId()).append(".<br>").append("Descri\347\343o do alerta : ").append(a.getMensagem()).append(".<br>").toString();
        if(valorErro != null)
            resp = (new StringBuilder()).append(resp).append("<font color=\"#FF0000\">").append(valorErro).append("</font><br>").toString();
        return resp;
    }

    private void initComponents()
    {
        jScrollPane2 = new JScrollPane();
        tblErros = new TableDes();
        jlbDetErro = new JLabel();
        jlbListErro = new JLabel();
        jlbApresentacaoNomeArq = new JLabel();
        jlbNomeArquivo = new JLabel();
        jScrollPane3 = new JScrollPane();
        jtpErro = new JTextPane();
        btnProximo = new JButton();
        btnAnterior = new JButton();
        btnPrimeiro = new JButton();
        btnUltimo = new JButton();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        setDefaultCloseOperation(2);
        setTitle("Alerta");
        tblErros.setModel(new DefaultTableModel(new Object[0][], new String[0]));
        tblErros.setAutoResizeMode(4);
        jScrollPane2.setViewportView(tblErros);
        jlbDetErro.setText("Detalhe Alerta");
        jlbListErro.setText("Lista de Alertas");
        jlbApresentacaoNomeArq.setText("Nome Arquivo : ");
        jlbNomeArquivo.setText("nomeArq");
        jtpErro.setContentType("text/html");
        jScrollPane3.setViewportView(jtpErro);
        btnProximo.setBackground(new Color(255, 255, 255));
        btnProximo.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/bt_proximo.gif")));
        btnProximo.addActionListener(new ActionListener() {

          
            public void actionPerformed(ActionEvent evt)
            {
                btnProximoActionPerformed(evt);
            }

         
        }
);
        btnAnterior.setBackground(new Color(255, 255, 255));
        btnAnterior.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/bt_anterior.gif")));
        btnAnterior.addActionListener(new ActionListener() {

        
            public void actionPerformed(ActionEvent evt)
            {
                btnAnteriorActionPerformed(evt);
            }

         
        }
);
        btnPrimeiro.setBackground(new Color(255, 255, 255));
        btnPrimeiro.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/bt_primeira_pagina.gif")));
        btnPrimeiro.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt)
            {
                btnPrimeiroActionPerformed(evt);
            }

        
        }
);
        btnUltimo.setBackground(new Color(255, 255, 255));
        btnUltimo.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/bt_ultima_pagina.gif")));
        btnUltimo.addActionListener(new ActionListener() {

      
            public void actionPerformed(ActionEvent evt)
            {
                btnUltimoActionPerformed(evt);
            }

            
        }
);
        jLabel1.setText("1");
        jLabel2.setText("2");
        jLabel3.setText("/");
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(1).add(2, layout.createSequentialGroup().addContainerGap().add(layout.createParallelGroup(1).add(jScrollPane3, -1, 607, 32767).add(jScrollPane2, -1, 607, 32767).add(layout.createSequentialGroup().add(jlbListErro).add(91, 91, 91).add(jlbApresentacaoNomeArq).add(18, 18, 18).add(jlbNomeArquivo)).add(layout.createSequentialGroup().add(jlbDetErro).add(117, 117, 117).add(btnPrimeiro, -2, 22, -2).addPreferredGap(0).add(btnAnterior, -2, 22, -2).add(18, 18, 18).add(jLabel1).addPreferredGap(0).add(jLabel3).addPreferredGap(0).add(jLabel2).add(18, 18, 18).add(btnProximo, -2, 20, -2).addPreferredGap(0).add(btnUltimo, -2, 22, -2))).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(1).add(2, layout.createSequentialGroup().addContainerGap().add(layout.createParallelGroup(3).add(jlbListErro).add(jlbApresentacaoNomeArq).add(jlbNomeArquivo)).addPreferredGap(0).add(jScrollPane2, -1, 163, 32767).addPreferredGap(0).add(layout.createParallelGroup(1).add(2, jlbDetErro).add(btnPrimeiro, -2, 20, -2).add(layout.createParallelGroup(2).add(layout.createParallelGroup(3).add(jLabel1).add(jLabel2).add(jLabel3)).add(btnAnterior, -2, 20, -2)).add(layout.createParallelGroup(2, false).add(1, btnProximo, 0, 0, 32767).add(1, btnUltimo, -2, 20, 32767))).addPreferredGap(0).add(jScrollPane3, -2, 131, -2).addContainerGap()));
        pack();
    }

    private void btnProximoActionPerformed(ActionEvent evt)
    {
        proximoPaginacao();
    }

    private void btnAnteriorActionPerformed(ActionEvent evt)
    {
        anteriorPaginacao();
    }

    private void btnPrimeiroActionPerformed(ActionEvent evt)
    {
        inicialPaginacao();
    }

    private void btnUltimoActionPerformed(ActionEvent evt)
    {
        finalPaginacao();
    }






}
