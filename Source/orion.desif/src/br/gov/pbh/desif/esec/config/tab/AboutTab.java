

package br.gov.pbh.desif.esec.config.tab;

import br.com.esec.version.SDKVersion;
import br.gov.pbh.desif.esec.images.LoadImages;
import java.awt.*;
import javax.swing.*;

// Referenced classes of package br.gov.pbh.desif.esec.config.tab:
//            AbstractConfigurationTab

public class AboutTab extends AbstractConfigurationTab
{

    private static AboutTab instance;
    private JPanel mainPanel;
    private static String version = SDKVersion.getVersion();
    private static String release = SDKVersion.getRelease();

    public static AboutTab getInstance()
    {
        if(instance == null)
            instance = new AboutTab();
        return instance;
    }

    public JPanel getPanel()
    {
        title = "Sobre";
        jbInit();
        return mainPanel;
    }

    private AboutTab()
    {
    }

    private void jbInit()
    {
        mainPanel = new JPanel();
        JTextArea aboutTextArea = new JTextArea();
        String logoFile = "esec.png";
        JLabel labelIconEsec = new JLabel(LoadImages.getImage(logoFile));
        JPanel aboutPanel1 = new JPanel();
        JScrollPane scrollPane = new JScrollPane();
        mainPanel.setLayout(new BorderLayout());
        scrollPane.getViewport().setBackground(Color.white);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setPreferredSize(new Dimension(240, 100));
        aboutTextArea.setWrapStyleWord(true);
        aboutTextArea.setLineWrap(false);
        aboutTextArea.setEditable(false);
        aboutTextArea.setOpaque(false);
        aboutTextArea.setBorder(null);
        String aboutMessageEsec = "Copyright (C) 2003-2013\n\ne-Sec Seguran\347a Digital\nhttp\\://www.esec.com.br\n\nEste programa encontra-se protegido\ncontra a utiliza\347\343o n\343o autorizada,\nconforme preceitua a Lei n\372mero 9.609,\nde 19 de fevereiro de 1998,\nregulamentada pelo Decreto n\372m. 2.556,\nde 20 de abril de 1998, combinada com\na Lei n\372mero 9.610, de 19 de fevereiro\nde 1998, estando devidamente registrado\nno Instituto Nacional da Propriedade\nIndustrial - INPI (Decreto n\372mero\n2.556/98, art. 1) sob o n\372mero\n00.055.893, ficando os infratores\nsujeitos \340s san\347\365es c\355veis e penais\nprevistas nos respectivos diplomas\nlegais.";
        String versionText = (new StringBuilder()).append("Versao ").append(version).append(release == null ? "" : (new StringBuilder()).append(" - r").append(release).toString()).append("\n\n").toString();
        aboutTextArea.setText((new StringBuilder()).append(versionText).append(aboutMessageEsec).toString());
        aboutTextArea.setCaretPosition(0);
        aboutTextArea.moveCaretPosition(0);
        aboutTextArea.setFont(new Font("Dialog", 0, 12));
        aboutPanel1.setBackground(Color.white);
        aboutPanel1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        aboutPanel1.setLayout(new BorderLayout());
        labelIconEsec.setBackground(Color.white);
        mainPanel.add(aboutPanel1, "Center");
        aboutPanel1.add(labelIconEsec, "West");
        aboutPanel1.add(scrollPane, "Center");
        scrollPane.getViewport().add(aboutTextArea);
    }

}