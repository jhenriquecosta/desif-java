/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  br.gov.pbh.des.componentes.utils.DesLookandFeel
 *  org.jdesktop.layout.GroupLayout
 *  org.jdesktop.layout.GroupLayout$Group
 *  org.jdesktop.layout.GroupLayout$ParallelGroup
 *  org.jdesktop.layout.GroupLayout$SequentialGroup
 */
package br.gov.pbh.desif.view.telas;

import br.gov.pbh.des.componentes.utils.DesLookandFeel;
import br.gov.pbh.desif.control.Controle;
import br.gov.pbh.desif.model.registros.RegUtil;
import br.gov.pbh.desif.service.contexto.Contexto;
import br.gov.pbh.desif.view.util.SwingUtils;
import java.awt.event.*;
import java.io.IOException;
import java.util.Date;
import javax.swing.*;
import org.jdesktop.layout.GroupLayout;

public final class PanGerarDeclaracao extends JPanel
{
   
    private Controle controle;
    private FrmPrincipal framePrinc;
    private JButton btnAbrir;
    private JButton btnGerarDeclaracao;
    private JButton btnOkConsistencia;
    private JButton btnOkStrutura;
    private JLabel jlbArquivo;
    private JLabel jlbConsist;
    private JLabel jlbEstrutural;
    private JProgressBar prbProgImport;
    private JProgressBar prbProgValidacao;
    private JTextField txfCaminhoArq;

    public PanGerarDeclaracao() 
    {
        this.inicializar();
    }

    public void inicializar() 
    {
        this.initComponents();
        this.setInitVisual();
        DesLookandFeel.getInstance().formatarJButtons(new JButton[]
        {
            this.btnAbrir, this.btnGerarDeclaracao, this.btnOkStrutura, this.btnOkConsistencia
        });
    }

    public void setInitVisual() {
        this.jlbEstrutural.setVisible(false);
        this.btnOkStrutura.setVisible(false);
        this.jlbConsist.setVisible(false);
        this.btnOkConsistencia.setVisible(false);
        this.prbProgImport.setVisible(false);
        this.prbProgValidacao.setVisible(false);
    }

    void setIcones() {
    }

    public void setTextTxfCaminhoArq(String caminho) {
        this.txfCaminhoArq.setText(caminho);
    }

    public String getTextTxfCaminhoArq() {
        return this.txfCaminhoArq.getText();
    }

    public void setAbilitarBtnGerarDeclaracao(boolean b) {
        this.btnGerarDeclaracao.setEnabled(b);
    }

    public FrmPrincipal getFrmPrincipal() {
        this.framePrinc = (FrmPrincipal)Contexto.getObject("frmPrincipal");
        return this.framePrinc;
    }

    public void limparBanco() {
        this.controle.limparBanco();
    }

    public void limparTela() {
        RegUtil.exErro = false;
        RegUtil.countErro = 0;
        RegUtil.nomeArq = null;
        this.txfCaminhoArq.setText("");
        this.btnGerarDeclaracao.setEnabled(false);
        this.jlbEstrutural.setVisible(false);
        this.jlbConsist.setVisible(false);
        this.btnOkStrutura.setVisible(false);
        this.btnOkConsistencia.setVisible(false);
        this.prbProgValidacao.setVisible(false);
        this.prbProgValidacao.setString("");
        this.prbProgValidacao.setStringPainted(true);
        this.prbProgValidacao.setValue(0);
        this.prbProgImport.setVisible(false);
        this.prbProgImport.setString("");
        this.prbProgImport.setStringPainted(true);
        this.prbProgImport.setValue(0);
    }

    public void prepararImportacao()
    {
        try {
            this.btnGerarDeclaracao.setEnabled(false);
            RegUtil.exErro = false;
            RegUtil.countErro = 0;
            RegUtil.nomeArq = null;
            this.prbProgImport.setVisible(true);
            this.prbProgImport.setString("");
            this.prbProgImport.setStringPainted(true);
            this.prbProgImport.setValue(0);
            this.limparBanco();
            this.validacaoEstrutural();
            if (!RegUtil.exErro) {
                this.validarNegocio();
            }
        }
        catch (Exception e) {
            RegUtil.exErro = true;
            e.printStackTrace();
        }
    }

    public void validarNegocio() {
        this.jlbConsist.setVisible(true);
        this.prbProgValidacao.setVisible(true);
        this.prbProgValidacao.setString("");
        this.prbProgValidacao.setStringPainted(true);
        this.prbProgValidacao.setValue(0);
        this.controle.verificaRegistrosBanco();
        if (RegUtil.exErro) {
            if (RegUtil.moduloDeclaracao.equals("1")) {
                ((FrmPrincipal)Contexto.getObject("frmPrincipal")).abrirTela((JPanel)Contexto.getObject("panErro"));
            } else if (RegUtil.moduloDeclaracao.equals("2")) {
                SwingUtils.msgErro(this, "Ocorreram erros de Valida\u00e7\u00e3o.");
                ((FrmPrincipal)Contexto.getObject("frmPrincipal")).setBotoes(true);
                ((FrmPrincipal)Contexto.getObject("frmPrincipal")).abrirTela((JPanel)Contexto.getObject("panErro"));
                ((FrmPrincipal)Contexto.getObject("frmPrincipal")).setGuia(false);
                this.setAbilitarBtnGerarDeclaracao(false);
            } else if (RegUtil.moduloDeclaracao.equals("3")) {
                ((FrmPrincipal)Contexto.getObject("frmPrincipal")).abrirTela((JPanel)Contexto.getObject("panErro"));
            }
        } else {
            this.btnOkConsistencia.setVisible(true);
            if (RegUtil.moduloDeclaracao.equals("1")) {
                ((FrmPrincipal)Contexto.getObject("frmPrincipal")).abrirTela((JPanel)Contexto.getObject("panSucesso"));
                ((FrmPrincipal)Contexto.getObject("frmPrincipal")).setBotoes(false);
                ((FrmPrincipal)Contexto.getObject("frmPrincipal")).setGuia(false);
                ((FrmPrincipal)Contexto.getObject("frmPrincipal")).selecionaPainelModulo(1);
            } else if (RegUtil.moduloDeclaracao.equals("2")) {
                ((FrmPrincipal)Contexto.getObject("frmPrincipal")).setBotoes(true);
                ((FrmPrincipal)Contexto.getObject("frmPrincipal")).setGuia(true);
                ((FrmPrincipal)Contexto.getObject("frmPrincipal")).abrirTela((JPanel)Contexto.getObject("panSucesso"));
                this.setAbilitarBtnGerarDeclaracao(false);
                ((FrmPrincipal)Contexto.getObject("frmPrincipal")).selecionaPainelModulo(2);
            } else if (RegUtil.moduloDeclaracao.equals("3")) {
                ((FrmPrincipal)Contexto.getObject("frmPrincipal")).abrirTela((JPanel)Contexto.getObject("panSucesso"));
                ((FrmPrincipal)Contexto.getObject("frmPrincipal")).selecionaPainelModulo(3);
            }
            Date dataf = new Date();
            System.out.println("Final do processamento:  " + dataf.toString());
        }
    }

    public void validacaoEstrutural() throws IOException {
        this.jlbEstrutural.setVisible(true);
        this.controle.importarArquivo(this.txfCaminhoArq.getText());
        if (RegUtil.exErro) {
            SwingUtils.msgErro(this, "Ocorreram erros de Estrutura.");
            ((FrmPrincipal)Contexto.getObject("frmPrincipal")).abrirTela((JPanel)Contexto.getObject("panErro"));
        } else {
            this.btnOkStrutura.setVisible(true);
        }
    }

    public void atualizarProgressoImportacao(int nLinha, int qtdLinha) {
        int perct = nLinha * 100 / qtdLinha;
        this.prbProgImport.setValue(perct);
        this.prbProgImport.setString("" + perct + " %");
    }

    public void atualizarProgressoValidacao(int nLinha, int qtdLinha) {
        int perct = nLinha * 100 / qtdLinha;
        this.prbProgValidacao.setValue(perct);
        this.prbProgValidacao.setString("" + perct + " %");
    }

    public Controle getControle() {
        return this.controle;
    }

    public void setControle(Controle controle) {
        this.controle = controle;
    }

    private void initComponents() {
        this.jlbEstrutural = new JLabel();
        this.jlbArquivo = new JLabel();
        this.prbProgImport = new JProgressBar();
        this.txfCaminhoArq = new JTextField();
        this.btnAbrir = new JButton();
        this.btnGerarDeclaracao = new JButton();
        this.prbProgValidacao = new JProgressBar();
        this.jlbConsist = new JLabel();
        this.btnOkStrutura = new JButton();
        this.btnOkConsistencia = new JButton();
        this.jlbEstrutural.setText("Valida\u00e7\u00e3o Estrutural");
        this.jlbArquivo.setText("Arquivo");
        this.prbProgImport.setEnabled(false);
        this.txfCaminhoArq.setEditable(false);
        this.txfCaminhoArq.addKeyListener(new KeyAdapter(){

            @Override
            public void keyReleased(KeyEvent evt) {
                PanGerarDeclaracao.this.txfCaminhoArqKeyReleased(evt);
            }
        });
        this.btnAbrir.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/bt_abrir.png")));
        this.btnAbrir.setText(" ");
        this.btnAbrir.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                PanGerarDeclaracao.this.btnAbrirActionPerformed(evt);
            }
        });
        this.btnGerarDeclaracao.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/importar.gif")));
        this.btnGerarDeclaracao.setText(" ");
        this.btnGerarDeclaracao.setEnabled(false);
        this.btnGerarDeclaracao.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                PanGerarDeclaracao.this.btnGerarDeclaracaoActionPerformed(evt);
            }
        });
        this.prbProgValidacao.setEnabled(false);
        this.jlbConsist.setText("Valida\u00e7\u00e3o Consist\u00eancia");
        this.btnOkStrutura.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/ok.gif")));
        this.btnOkConsistencia.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/ok.gif")));
      
         GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().add(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(layout.createParallelGroup(1).add(jlbArquivo).add(btnGerarDeclaracao).add(layout.createSequentialGroup().add(txfCaminhoArq, -2, 379, -2).addPreferredGap(1).add(btnAbrir, -2, 79, -2)))).add(layout.createSequentialGroup().add(34, 34, 34).add(layout.createParallelGroup(1).add(prbProgImport, -2, -1, -2).add(layout.createSequentialGroup().add(34, 34, 34).add(jlbEstrutural))).addPreferredGap(0).add(btnOkStrutura).add(31, 31, 31).add(layout.createParallelGroup(1, false).add(prbProgValidacao, -2, -1, -2).add(2, layout.createSequentialGroup().addPreferredGap(0, 26, -2).add(jlbConsist).add(16, 16, 16))).addPreferredGap(0).add(btnOkConsistencia))).addContainerGap(12, 32767)));
        layout.setVerticalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().add(27, 27, 27).add(jlbArquivo).addPreferredGap(0).add(layout.createParallelGroup(3).add(txfCaminhoArq, -2, -1, -2).add(btnAbrir)).addPreferredGap(0).add(layout.createParallelGroup(2).add(layout.createSequentialGroup().add(btnGerarDeclaracao).add(73, 73, 73).add(layout.createParallelGroup(2).add(layout.createSequentialGroup().add(jlbEstrutural).addPreferredGap(0).add(prbProgImport, -2, 20, -2)).add(btnOkStrutura))).add(layout.createSequentialGroup().add(jlbConsist).addPreferredGap(0).add(prbProgValidacao, -2, 20, -2)).add(btnOkConsistencia)).addContainerGap(126, 32767)));

         /*    GroupLayout layout = new GroupLayout((Container)this);
        this.setLayout((LayoutManager)layout);
        layout.setHorizontalGroup((GroupLayout.Group)layout.createParallelGroup(1).add((GroupLayout.Group)layout.createSequentialGroup().add((GroupLayout.Group)layout.createParallelGroup(1).add((GroupLayout.Group)layout.createSequentialGroup().addContainerGap().add((GroupLayout.Group)layout.createParallelGroup(1).add((Component)this.jlbArquivo).add((Component)this.btnGerarDeclaracao).add((GroupLayout.Group)layout.createSequentialGroup().add((Component)this.txfCaminhoArq, -2, 379, -2).addPreferredGap(1).add((Component)this.btnAbrir, -2, 79, -2)))).add((GroupLayout.Group)layout.createSequentialGroup().add(34, 34, 34).add((GroupLayout.Group)layout.createParallelGroup(1).add((Component)this.prbProgImport, -2, -1, -2).add((GroupLayout.Group)layout.createSequentialGroup().add(34, 34, 34).add((Component)this.jlbEstrutural))).addPreferredGap(0).add((Component)this.btnOkStrutura).add(31, 31, 31).add((GroupLayout.Group)layout.createParallelGroup(1, false).add((Component)this.prbProgValidacao, -2, -1, -2).add(2, (GroupLayout.Group)layout.createSequentialGroup().addPreferredGap(0, 26, -2).add((Component)this.jlbConsist).add(16, 16, 16))).addPreferredGap(0).add((Component)this.btnOkConsistencia))).addContainerGap(12, 32767)));
        layout.setVerticalGroup((GroupLayout.Group)layout.createParallelGroup(1).add((GroupLayout.Group)layout.createSequentialGroup().add(27, 27, 27).add((Component)this.jlbArquivo).addPreferredGap(0).add((GroupLayout.Group)layout.createParallelGroup(3).add((Component)this.txfCaminhoArq, -2, -1, -2).add((Component)this.btnAbrir)).addPreferredGap(0).add((GroupLayout.Group)layout.createParallelGroup(2).add((GroupLayout.Group)layout.createSequentialGroup().add((Component)this.btnGerarDeclaracao).add(73, 73, 73).add((GroupLayout.Group)layout.createParallelGroup(2).add((GroupLayout.Group)layout.createSequentialGroup().add((Component)this.jlbEstrutural).addPreferredGap(0).add((Component)this.prbProgImport, -2, 20, -2)).add((Component)this.btnOkStrutura))).add((GroupLayout.Group)layout.createSequentialGroup().add((Component)this.jlbConsist).addPreferredGap(0).add((Component)this.prbProgValidacao, -2, 20, -2)).add((Component)this.btnOkConsistencia)).addContainerGap(126, 32767)));
         */
    
    }

    private void txfCaminhoArqKeyReleased(KeyEvent evt) {
        if (this.txfCaminhoArq.getText().trim().length() >= 1) {
            this.setAbilitarBtnGerarDeclaracao(false);
        } else {
            this.setAbilitarBtnGerarDeclaracao(true);
        }
    }

    private void btnGerarDeclaracaoActionPerformed(ActionEvent evt) {
        Date data = new Date();
        System.out.println("In\u00edcio do processamento: " + data.toString());
         (new Thread() 
        {
            @Override
            public void run()
            {
                prepararImportacao();
            }
        }
        ).start();
    }

    private void btnAbrirActionPerformed(ActionEvent evt) 
    {
        ((DialArquivo)Contexto.getObject("dialArquivo")).setDiretorioPadrao(RegUtil.caminhoDiretorioPadrao);
        ((DialArquivo)Contexto.getObject("dialArquivo")).setVisible(true);
    }

    

}

