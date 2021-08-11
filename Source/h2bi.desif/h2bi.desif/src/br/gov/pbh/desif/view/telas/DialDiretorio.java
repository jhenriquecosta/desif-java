
package br.gov.pbh.desif.view.telas;

import br.gov.pbh.des.componentes.utils.SwingUtils;
import br.gov.pbh.desif.service.contexto.Contexto;
import java.awt.Container;
import java.awt.Frame;
import java.awt.event.*;
import java.io.File;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

// Referenced classes of package br.gov.pbh.desif.view.telas:
//            DialConfiguracao

public class DialDiretorio extends JDialog
{

    private JFileChooser jFileChooser1;

    public DialDiretorio(Frame parent, boolean modal)
    {
        super(parent, modal);
        setTitle("Selecione o Diretorio");
        initComponents();
        SwingUtils.getInstance().centralizar(this);
    }

    private void initComponents()
    {
        jFileChooser1 = new JFileChooser();
        addWindowListener(new WindowAdapter() {

           
            public void windowClosing(WindowEvent evt)
            {
                closeDialog(evt);
            }

            
        }
);
        jFileChooser1.setFileSelectionMode(1);
        jFileChooser1.addActionListener(new ActionListener() {

         
            public void actionPerformed(ActionEvent evt)
            {
                jFileChooser1ActionPerformed(evt);
            }

         
        }
);
        getContentPane().add(jFileChooser1, "Center");
        pack();
    }

    private void closeDialog(WindowEvent evt)
    {
        setVisible(false);
        dispose();
    }

    private void jFileChooser1ActionPerformed(ActionEvent evt)
    {
        if(evt.getActionCommand().equals("CancelSelection"))
        {
            dispose();
        } else
        {
            DialConfiguracao dialConf = (DialConfiguracao)Contexto.getObject("dialConfiguracao");
            File file = jFileChooser1.getSelectedFile();
            dialConf.setjtxtAreaCaminhoDiretorio(file.getPath());
            dispose();
        }
    }


}
