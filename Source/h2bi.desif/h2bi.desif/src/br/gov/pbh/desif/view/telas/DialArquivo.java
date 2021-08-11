
package br.gov.pbh.desif.view.telas;

import br.gov.pbh.des.componentes.utils.SwingUtils;
import br.gov.pbh.desif.service.contexto.Contexto;
import br.gov.pbh.desif.view.util.TxtFilter;
import java.awt.Container;
import java.awt.Frame;
import java.awt.event.*;
import java.io.File;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

// Referenced classes of package br.gov.pbh.desif.view.telas:
//            PanGerarDeclaracao

public class DialArquivo extends JDialog
{

    private JFileChooser fileChooser;

    public DialArquivo(Frame parent, boolean modal)
    {
        super(parent, modal);
        setTitle("Selecione o Arquivo");
        initComponents();
        SwingUtils.getInstance().centralizar(this);
        setarFiltroJFileChooser();
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
        }
        else
        {
            File file = fileChooser.getSelectedFile();
            PanGerarDeclaracao pagGerDec = (PanGerarDeclaracao)Contexto.getObject("panGD");
            pagGerDec.setTextTxfCaminhoArq(file.getPath());
            pagGerDec.setAbilitarBtnGerarDeclaracao(true);
            dispose();
        }
    }

    private void setarFiltroJFileChooser()
    {
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileFilter(new TxtFilter());
    }


}
