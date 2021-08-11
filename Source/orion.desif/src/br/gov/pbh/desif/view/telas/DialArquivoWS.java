
package br.gov.pbh.desif.view.telas;

import br.gov.pbh.des.componentes.utils.SwingUtils;
import br.gov.pbh.desif.control.Controle;
import br.gov.pbh.desif.service.contexto.Contexto;
import br.gov.pbh.desif.view.util.TxtFilter;
import br.gov.pbh.desif.view.util.XmlFilter;
import java.awt.Container;
import java.awt.Frame;
import java.awt.event.*;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

public class DialArquivoWS extends JDialog
{

    private final Controle controle;
    private final String tipoFiltro;
    private String caminhoArquivoSelecionado;
    private JFileChooser fileChooser;

    public DialArquivoWS(Frame parent, boolean modal, String titulo, String tipoFiltro)
    {
        super(parent, modal);
        controle = (Controle)Contexto.getObject("controle");
        this.tipoFiltro = tipoFiltro;
        setTitle(titulo);
        initComponents();
        SwingUtils.getInstance().centralizar(this);
        setarFiltroJFileChooser(tipoFiltro);
    }

    public void setDiretorioPadrao(String diretorio)
    {
        if(diretorio != null)
            fileChooser.setCurrentDirectory(new File(diretorio));
    }

    private void initComponents()
    {
        fileChooser = new JFileChooser();
        addWindowListener(new WindowAdapter() {

        
            @Override
            public void windowClosing(WindowEvent evt)
            {
                closeDialog(evt);
            }

           
        }
);
        fileChooser.setCurrentDirectory(new File("C:\\Documents and Settings\\guilherme.diniz\\Meus documentos\\Testes"));
        fileChooser.addActionListener(new ActionListener() {

     
            @Override
            public void actionPerformed(ActionEvent evt)
            {
                fileChooserActionPerformed(evt);
            }
        }
);
        getContentPane().add(fileChooser, "Center");
        pack();
    }

    private void closeDialog(WindowEvent evt)
    {
        setVisible(false);
        dispose();
    }

    private void fileChooserActionPerformed(ActionEvent evt)
    {
        if(evt.getActionCommand().equals("CancelSelection"))
        {
            dispose();
        } else
        {
            File file = fileChooser.getSelectedFile();
            if(tipoFiltro.equals("XML"))
                try
                {
                    controle.carregaArquivoProtocolo(file.getPath());
                }
                catch(Exception ex)
                {
                    Logger.getLogger(br.gov.pbh.desif.view.telas.DialArquivoWS.class.getName()).log(Level.SEVERE, null, ex);
                }
            else
            if(tipoFiltro.equals("TXT"))
                setCaminhoArquivoSelecionado(file.getPath());
                dispose();
        }
    }

    private void setarFiltroJFileChooser(String tipoFiltro)
    {
        fileChooser.setAcceptAllFileFilterUsed(false);
        if(tipoFiltro.equals("XML"))
            fileChooser.setFileFilter(new XmlFilter());
        else
        if(tipoFiltro.equals("TXT"))
            fileChooser.setFileFilter(new TxtFilter());
    }

    public void setCaminhoArquivoSelecionado(String caminho)
    {
        caminhoArquivoSelecionado = caminho;
    }

    public String getCaminhoArquivoSelecionado()
    {
        return caminhoArquivoSelecionado;
    }


}
