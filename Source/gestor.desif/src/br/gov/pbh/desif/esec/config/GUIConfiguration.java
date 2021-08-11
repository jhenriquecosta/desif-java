/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.esec.config;

import br.gov.pbh.desif.esec.config.ConfigurationManager;
import br.gov.pbh.desif.esec.config.tab.AbstractConfigurationTab;
import br.gov.pbh.desif.esec.config.tab.DefaultTabFactory;
import br.gov.pbh.desif.esec.config.tab.GeneralConfigurationTab;
import br.gov.pbh.desif.esec.images.LoadImages;
import br.gov.pbh.desif.esec.util.Util;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class GUIConfiguration
extends JDialog {
    private static final long serialVersionUID = 1L;
    private List tabs = new DefaultTabFactory().getTabs();

    public GUIConfiguration() {
        super((Dialog)((JDialog)Util.getActiveWindow()), true);
        this.jbInit();
    }

    private void jbInit() {
        JPanel buttonPanel = new JPanel(new FlowLayout(1));
        JButton cancelButton = new JButton("Cancelar");
        JButton okButton = new JButton("OK");
        JTabbedPane jTabbedPane1 = new JTabbedPane();
        this.getContentPane().setLayout(new BorderLayout());
        this.setDefaultCloseOperation(0);
        this.setResizable(false);
        this.setTitle("Configuracoes de Certificacao Digital");
        cancelButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                GUIConfiguration.this.cancelButton_actionPerformed(e);
            }
        });
        okButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                GUIConfiguration.this.okButton_actionPerformed(e);
            }
        });
        okButton.setPreferredSize(cancelButton.getPreferredSize());
        this.getContentPane().add((Component)jTabbedPane1, "Center");
        this.getContentPane().add((Component)buttonPanel, "South");
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
        this.setIconImage(LoadImages.getImage("icon.png").getImage());
        this.validate();
        this.pack();
        Util.centerOnScreen(this);
        this.setVisible(true);
    }

    void okButton_actionPerformed(ActionEvent e) {
        Iterator iter = this.tabs.iterator();
        String mensagem = null;
        while (iter.hasNext()) {
            AbstractConfigurationTab tab = (AbstractConfigurationTab)iter.next();
            if (!(tab instanceof GeneralConfigurationTab)) continue;
            mensagem = ((GeneralConfigurationTab)tab).validateConfiguration();
            if (mensagem != null) break;
            try {
                ConfigurationManager.getInstance().saveProperties();
            }
            catch (IOException e1) {
                mensagem = e1.getMessage();
            }
        }
        if (mensagem == null) {
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, mensagem, "Erro", 0);
        }
    }

    void cancelButton_actionPerformed(ActionEvent e) {
        this.dispose();
    }

}

