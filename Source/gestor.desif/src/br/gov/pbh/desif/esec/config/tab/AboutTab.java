/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  br.com.esec.version.SDKVersion
 */
package br.gov.pbh.desif.esec.config.tab;

import br.com.esec.version.SDKVersion;
import br.gov.pbh.desif.esec.config.tab.AbstractConfigurationTab;
import br.gov.pbh.desif.esec.images.LoadImages;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JViewport;
import javax.swing.border.Border;

public class AboutTab
extends AbstractConfigurationTab {
    private static AboutTab instance;
    private JPanel mainPanel;
    private static String version;
    private static String release;

    public static AboutTab getInstance() {
        if (instance == null) {
            instance = new AboutTab();
        }
        return instance;
    }

    @Override
    public JPanel getPanel() {
        this.title = "Sobre";
        this.jbInit();
        return this.mainPanel;
    }

    private AboutTab() {
    }

    private void jbInit() {
        this.mainPanel = new JPanel();
        JTextArea aboutTextArea = new JTextArea();
        String logoFile = "esec.png";
        JLabel labelIconEsec = new JLabel(LoadImages.getImage(logoFile));
        JPanel aboutPanel1 = new JPanel();
        JScrollPane scrollPane = new JScrollPane();
        this.mainPanel.setLayout(new BorderLayout());
        scrollPane.getViewport().setBackground(Color.white);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setPreferredSize(new Dimension(240, 100));
        aboutTextArea.setWrapStyleWord(true);
        aboutTextArea.setLineWrap(false);
        aboutTextArea.setEditable(false);
        aboutTextArea.setOpaque(false);
        aboutTextArea.setBorder(null);
        String aboutMessageEsec = "Copyright (C) 2003-2013\n\ne-Sec Seguran\u00e7a Digital\nhttp\\://www.esec.com.br\n\nEste programa encontra-se protegido\ncontra a utiliza\u00e7\u00e3o n\u00e3o autorizada,\nconforme preceitua a Lei n\u00famero 9.609,\nde 19 de fevereiro de 1998,\nregulamentada pelo Decreto n\u00fam. 2.556,\nde 20 de abril de 1998, combinada com\na Lei n\u00famero 9.610, de 19 de fevereiro\nde 1998, estando devidamente registrado\nno Instituto Nacional da Propriedade\nIndustrial - INPI (Decreto n\u00famero\n2.556/98, art. 1) sob o n\u00famero\n00.055.893, ficando os infratores\nsujeitos \u00e0s san\u00e7\u00f5es c\u00edveis e penais\nprevistas nos respectivos diplomas\nlegais.";
        String versionText = "Versao " + version + (release != null ? new StringBuilder().append(" - r").append(release).toString() : "") + "\n\n";
        aboutTextArea.setText(versionText + aboutMessageEsec);
        aboutTextArea.setCaretPosition(0);
        aboutTextArea.moveCaretPosition(0);
        aboutTextArea.setFont(new Font("Dialog", 0, 12));
        aboutPanel1.setBackground(Color.white);
        aboutPanel1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        aboutPanel1.setLayout(new BorderLayout());
        labelIconEsec.setBackground(Color.white);
        this.mainPanel.add((Component)aboutPanel1, "Center");
        aboutPanel1.add((Component)labelIconEsec, "West");
        aboutPanel1.add((Component)scrollPane, "Center");
        scrollPane.getViewport().add(aboutTextArea);
    }

    static {
        version = SDKVersion.getVersion();
        release = SDKVersion.getRelease();
    }
}

