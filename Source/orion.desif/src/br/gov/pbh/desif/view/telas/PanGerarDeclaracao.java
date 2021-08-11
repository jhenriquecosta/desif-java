
package br.gov.pbh.desif.view.telas;

import br.gov.pbh.des.componentes.utils.DesLookandFeel;
import br.gov.pbh.desif.control.Controle;
import br.gov.pbh.desif.model.registros.RegUtil;
import br.gov.pbh.desif.service.contexto.Contexto;
import br.gov.pbh.desif.view.util.SwingUtils;
import java.awt.event.*;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import org.jdesktop.layout.GroupLayout;

// Referenced classes of package br.gov.pbh.desif.view.telas:
//            FrmPrincipal, DialArquivo

public class PanGerarDeclaracao extends JPanel
{

   

    private Controle controle;  //injetado ?
    private FrmPrincipal framePrinc; //injetado ?
    
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
        inicializar();
    }

    public void inicializar()
    {
        initComponents();
        setInitVisual();
        DesLookandFeel.getInstance().formatarJButtons(new JButton[] 
        {
            btnAbrir, btnGerarDeclaracao, btnOkStrutura, btnOkConsistencia
        });
    }

    public void setInitVisual()
    {
        jlbEstrutural.setVisible(false);
        btnOkStrutura.setVisible(false);
        jlbConsist.setVisible(false);
        btnOkConsistencia.setVisible(false);
        prbProgImport.setVisible(false);
        prbProgValidacao.setVisible(false);
    }

    void setIcones()
    {
    }

    public void setTextTxfCaminhoArq(String caminho)
    {
        txfCaminhoArq.setText(caminho);
    }

    public String getTextTxfCaminhoArq()
    {
        return txfCaminhoArq.getText();
    }

    public void setAbilitarBtnGerarDeclaracao(boolean b)
    {
        btnGerarDeclaracao.setEnabled(b);
    }

    public FrmPrincipal getFrmPrincipal()
    {
        framePrinc = (FrmPrincipal)Contexto.getObject("frmPrincipal");
        return framePrinc;
    }

    public void limparBanco()
    {
        controle.limparBanco();
    }

    public void limparTela()
    {
        RegUtil.exErro = false;
        RegUtil.countErro = 0;
        RegUtil.nomeArq = null;
        txfCaminhoArq.setText("");
        btnGerarDeclaracao.setEnabled(false);
        jlbEstrutural.setVisible(false);
        jlbConsist.setVisible(false);
        btnOkStrutura.setVisible(false);
        btnOkConsistencia.setVisible(false);
        prbProgValidacao.setVisible(false);
        prbProgValidacao.setString("");
        prbProgValidacao.setStringPainted(true);
        prbProgValidacao.setValue(0);
        prbProgImport.setVisible(false);
        prbProgImport.setString("");
        prbProgImport.setStringPainted(true);
        prbProgImport.setValue(0);
    }

    public void prepararImportacao()
    {
        try
        {
            btnGerarDeclaracao.setEnabled(false);
            RegUtil.exErro = false;
            RegUtil.countErro = 0;
            RegUtil.nomeArq = null;
            prbProgImport.setVisible(true);
            prbProgImport.setString("");
            prbProgImport.setStringPainted(true);
            prbProgImport.setValue(0);
            limparBanco();
            validacaoEstrutural();
            if(!RegUtil.exErro)
            {
                validarNegocio();
            }
        }
        catch(Exception e)
        {
            RegUtil.exErro = true;
            Logger.getLogger(br.gov.pbh.desif.view.telas.PanGerarDeclaracao.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }

    public void validarNegocio()
    {
        jlbConsist.setVisible(true);
        prbProgValidacao.setVisible(true);
        prbProgValidacao.setString("");
        prbProgValidacao.setStringPainted(true);
        prbProgValidacao.setValue(0);
        controle.verificaRegistrosBanco();
        if(RegUtil.exErro)
        {
            if(RegUtil.moduloDeclaracao.equals("1"))
            {
                ((FrmPrincipal)Contexto.getObject("frmPrincipal")).abrirTela((JPanel)Contexto.getObject("panErro"));
            }
            if(RegUtil.moduloDeclaracao.equals("2"))
            {
                SwingUtils.msgErro(this, "Ocorreram erros de Valida\347\343o.");
                ((FrmPrincipal)Contexto.getObject("frmPrincipal")).setBotoes(true);
                ((FrmPrincipal)Contexto.getObject("frmPrincipal")).abrirTela((JPanel)Contexto.getObject("panErro"));
                ((FrmPrincipal)Contexto.getObject("frmPrincipal")).setGuia(false);
                setAbilitarBtnGerarDeclaracao(false);
            } 
            if(RegUtil.moduloDeclaracao.equals("3"))
            {
                ((FrmPrincipal)Contexto.getObject("frmPrincipal")).abrirTela((JPanel)Contexto.getObject("panErro"));
            }   
        
        }
        else
        {
            btnOkConsistencia.setVisible(true);
            switch (RegUtil.moduloDeclaracao) {
                case "1":
                    ((FrmPrincipal)Contexto.getObject("frmPrincipal")).abrirTela((JPanel)Contexto.getObject("panSucesso"));
                    ((FrmPrincipal)Contexto.getObject("frmPrincipal")).setBotoes(false);
                    ((FrmPrincipal)Contexto.getObject("frmPrincipal")).setGuia(false);
                    ((FrmPrincipal)Contexto.getObject("frmPrincipal")).selecionaPainelModulo(1);
                    break;
                case "2":
                    ((FrmPrincipal)Contexto.getObject("frmPrincipal")).setBotoes(true);
                    ((FrmPrincipal)Contexto.getObject("frmPrincipal")).setGuia(true);
                    ((FrmPrincipal)Contexto.getObject("frmPrincipal")).abrirTela((JPanel)Contexto.getObject("panSucesso"));
                    setAbilitarBtnGerarDeclaracao(false);
                    ((FrmPrincipal)Contexto.getObject("frmPrincipal")).selecionaPainelModulo(2);
                    break;
                case "3":
                    ((FrmPrincipal)Contexto.getObject("frmPrincipal")).abrirTela((JPanel)Contexto.getObject("panSucesso"));
                    ((FrmPrincipal)Contexto.getObject("frmPrincipal")).selecionaPainelModulo(3);
                    break;
            }
            Date dataf = new Date();
            System.out.println((new StringBuilder()).append("Final do processamento:  ").append(dataf.toString()).toString());
        }
    }

    public void validacaoEstrutural() throws IOException
    {
        jlbEstrutural.setVisible(true);
        controle.importarArquivo(txfCaminhoArq.getText());
        if(RegUtil.exErro)
        {
            SwingUtils.msgErro(this, "Ocorreram erros de Estrutura.");
            ((FrmPrincipal)Contexto.getObject("frmPrincipal")).abrirTela((JPanel)Contexto.getObject("panErro"));
        } 
        else
        {
            btnOkStrutura.setVisible(true);
        }
    }

    public void atualizarProgressoImportacao(int nLinha, int qtdLinha)
    {
        int perct = (nLinha * 100) / qtdLinha;
        prbProgImport.setValue(perct);
        prbProgImport.setString((new StringBuilder()).append(perct).append(" %").toString());
    }

    public void atualizarProgressoValidacao(int nLinha, int qtdLinha)
    {
        int perct = (nLinha * 100) / qtdLinha;
        prbProgValidacao.setValue(perct);
        prbProgValidacao.setString((new StringBuilder()).append(perct).append(" %").toString());
    }

    public Controle getControle()
    {
        return controle;
    }

    public void setControle(Controle controle)
    {
        this.controle = controle;
    }

    private void initComponents()
    {
        jlbEstrutural = new JLabel();
        jlbArquivo = new JLabel();
        prbProgImport = new JProgressBar();
        txfCaminhoArq = new JTextField();
        btnAbrir = new JButton();
        btnGerarDeclaracao = new JButton();
        prbProgValidacao = new JProgressBar();
        jlbConsist = new JLabel();
        btnOkStrutura = new JButton();
        btnOkConsistencia = new JButton();
        jlbEstrutural.setText("Valida\347\343o Estrutural");
        jlbArquivo.setText("Arquivo");
        prbProgImport.setEnabled(false);
        txfCaminhoArq.setEditable(false);
        txfCaminhoArq.addKeyListener(new KeyAdapter() {

        
            @Override
            public void keyReleased(KeyEvent evt)
            {
                txfCaminhoArqKeyReleased(evt);
            }

        }
);
        btnAbrir.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/bt_abrir.png")));
        btnAbrir.setText(" ");
        btnAbrir.addActionListener(new ActionListener() 
        {

             @Override
             public void actionPerformed(ActionEvent evt)
            {
                btnAbrirActionPerformed(evt);
            }
 }
);
        btnGerarDeclaracao.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/importar.gif")));
        btnGerarDeclaracao.setText(" ");
        btnGerarDeclaracao.setEnabled(false);
        btnGerarDeclaracao.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt)
            {
                btnGerarDeclaracaoActionPerformed(evt);
            }

        }
);
        prbProgValidacao.setEnabled(false);
        jlbConsist.setText("Valida\347\343o Consist\352ncia");
        btnOkStrutura.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/ok.gif")));
        btnOkConsistencia.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/ok.gif")));
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().add(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(layout.createParallelGroup(1).add(jlbArquivo).add(btnGerarDeclaracao).add(layout.createSequentialGroup().add(txfCaminhoArq, -2, 379, -2).addPreferredGap(1).add(btnAbrir, -2, 79, -2)))).add(layout.createSequentialGroup().add(34, 34, 34).add(layout.createParallelGroup(1).add(prbProgImport, -2, -1, -2).add(layout.createSequentialGroup().add(34, 34, 34).add(jlbEstrutural))).addPreferredGap(0).add(btnOkStrutura).add(31, 31, 31).add(layout.createParallelGroup(1, false).add(prbProgValidacao, -2, -1, -2).add(2, layout.createSequentialGroup().addPreferredGap(0, 26, -2).add(jlbConsist).add(16, 16, 16))).addPreferredGap(0).add(btnOkConsistencia))).addContainerGap(12, 32767)));
        layout.setVerticalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().add(27, 27, 27).add(jlbArquivo).addPreferredGap(0).add(layout.createParallelGroup(3).add(txfCaminhoArq, -2, -1, -2).add(btnAbrir)).addPreferredGap(0).add(layout.createParallelGroup(2).add(layout.createSequentialGroup().add(btnGerarDeclaracao).add(73, 73, 73).add(layout.createParallelGroup(2).add(layout.createSequentialGroup().add(jlbEstrutural).addPreferredGap(0).add(prbProgImport, -2, 20, -2)).add(btnOkStrutura))).add(layout.createSequentialGroup().add(jlbConsist).addPreferredGap(0).add(prbProgValidacao, -2, 20, -2)).add(btnOkConsistencia)).addContainerGap(126, 32767)));
    }

    private void txfCaminhoArqKeyReleased(KeyEvent evt)
    {
        if(txfCaminhoArq.getText().trim().length() >= 1)
        {
            setAbilitarBtnGerarDeclaracao(false);
        }
        else
        {
            setAbilitarBtnGerarDeclaracao(true);
        }
    }

    private void btnGerarDeclaracaoActionPerformed(ActionEvent evt)
    {
        Date data = new Date();
        System.out.println((new StringBuilder()).append("In\355cio do processamento: ").append(data.toString()).toString());
        (new Thread() 
        {
            @Override
            public void run()
            {
                prepararImportacao();
            }
        }
        ).start();
          
//  task = new Task();
       // task.start();
        //  this.prepararImportacao();
    }

    private void btnAbrirActionPerformed(ActionEvent evt)
    {
        ((DialArquivo)Contexto.getObject("dialArquivo")).setDiretorioPadrao(RegUtil.caminhoDiretorioPadrao);
        ((DialArquivo)Contexto.getObject("dialArquivo")).setVisible(true);
    }



}
