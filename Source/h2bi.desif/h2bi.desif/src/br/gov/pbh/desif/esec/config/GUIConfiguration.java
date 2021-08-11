package br.gov.pbh.desif.esec.config;

import br.gov.pbh.desif.esec.config.tab.AbstractConfigurationTab;
import br.gov.pbh.desif.esec.config.tab.DefaultTabFactory;
import br.gov.pbh.desif.esec.config.tab.GeneralConfigurationTab;
import br.gov.pbh.desif.esec.images.LoadImages;
import br.gov.pbh.desif.esec.util.Util;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Iterator;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

// Referenced classes of package br.gov.pbh.desif.esec.config:
//            ConfigurationManager

public class GUIConfiguration extends JDialog
{

    private static final long serialVersionUID = 1L;
    private java.util.List tabs;

    public GUIConfiguration()
    {
        super((JDialog)Util.getActiveWindow(), true);
        tabs = (new DefaultTabFactory()).getTabs();
        jbInit();
    }

    private void jbInit()
    {
        JPanel buttonPanel = new JPanel(new FlowLayout(1));
        JButton cancelButton = new JButton("Cancelar");
        JButton okButton = new JButton("OK");
        JTabbedPane jTabbedPane1 = new JTabbedPane();
        getContentPane().setLayout(new BorderLayout());
        setDefaultCloseOperation(0);
        setResizable(false);
        setTitle("Configuracoes de Certificacao Digital");
        cancelButton.addActionListener(new ActionListener() {

        
            public void actionPerformed(ActionEvent e)
            {
                cancelButton_actionPerformed(e);
            }

        }
);
        okButton.addActionListener(new ActionListener() {


            public void actionPerformed(ActionEvent e)
            {
                okButton_actionPerformed(e);
            }

        }
);
        okButton.setPreferredSize(cancelButton.getPreferredSize());
        getContentPane().add(jTabbedPane1, "Center");
        getContentPane().add(buttonPanel, "South");
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        JPanel panel;
        String title;
        for(Iterator iter = tabs.iterator(); iter.hasNext(); jTabbedPane1.add(panel, title))
        {
            AbstractConfigurationTab tab = (AbstractConfigurationTab)iter.next();
            panel = tab.getPanel();
            title = tab.getTitle();
        }

        setIconImage(LoadImages.getImage("icon.png").getImage());
        validate();
        pack();
        Util.centerOnScreen(this);
        setVisible(true);
    }

    void okButton_actionPerformed(ActionEvent e)
    {
        Iterator iter = tabs.iterator();
        String mensagem = null;
        do
        {
            if(!iter.hasNext())
                break;
            AbstractConfigurationTab tab = (AbstractConfigurationTab)iter.next();
            if(!(tab instanceof GeneralConfigurationTab))
                continue;
            mensagem = ((GeneralConfigurationTab)tab).validateConfiguration();
            if(mensagem != null)
                break;
            try
            {
                ConfigurationManager.getInstance().saveProperties();
            }
            catch(IOException e1)
            {
                mensagem = e1.getMessage();
            }
        } 
        while(true);
        
        if(mensagem == null)
            dispose();
        else
            JOptionPane.showMessageDialog(this, mensagem, "Erro", 0);
    }

    void cancelButton_actionPerformed(ActionEvent e)
    {
        dispose();
    }
}