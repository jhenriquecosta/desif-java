package br.gov.pbh.desif.esec.config.tab;

import br.com.esec.util.libs.DownloadLibs;
import br.gov.pbh.desif.esec.assinatura.PKCS11Tools;
import br.gov.pbh.desif.esec.config.ConfigurationManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

// Referenced classes of package br.gov.pbh.desif.esec.config.tab:
//            AbstractConfigurationTab

public class GeneralConfigurationTab extends AbstractConfigurationTab
{

    private static GeneralConfigurationTab instance;
    private JRadioButton fileRadioButton;
    private JRadioButton smartcardRadioButton;
    private JRadioButton hsmRadioButton;
    private JRadioButton mscapiRadioButton;
    private JComboBox smartcardCombo;
    private JTextField libHSMTextField;
    private JComboBox slotList;
    private JLabel libHSMLabelField;
    private JLabel slotHSMLabelField;
    private JTextField fileTextFieldSign;
    private JButton findFileButtonSign;
    private JLabel fileLabelFieldSign;
    private JTextField fileTextFieldEncrypt;
    private JButton findFileButtonEncrypt;
    private JCheckBox chkSelfEncryption;
    private JLabel fileLabelFieldEncrypt;
    private DefaultComboBoxModel comboBoxModel;
    private JPanel mainPanel;
    private String selectedCard;
    private boolean hasPkcs11Terminals;
    private ConfigurationManager confManager;

    public JPanel getPanel()
    {
        if(mainPanel == null)
        {
            title = "Geral";
            jbInit();
        }
        reloadConfiguration();
        return mainPanel;
    }

    public String validateConfiguration()
    {
        confManager.setSelfEncryption(chkSelfEncryption.isSelected());
        if(mscapiRadioButton.isSelected())
            confManager.setKeyStoreType(3);
        else
        if(smartcardRadioButton.isSelected())
        {
            selectedCard = (String)comboBoxModel.getSelectedItem();
            if(selectedCard == null)
                return "Escolha um modelo de cartao/token";
            confManager.setKeyStoreType(1);
            confManager.setPKCS11Model(selectedCard);
        } else
        if(fileRadioButton.isSelected())
        {
            String strFileSign = fileTextFieldSign.getText();
            if(strFileSign == null || strFileSign.equals(""))
                return "Arquivo PKCS12 invalido";
            File arquivoSign = new File(strFileSign);
            if(!arquivoSign.exists())
                return "Arquivo PKCS12 nao existe";
            if(!arquivoSign.isFile())
                return "Arquivo PKCS12 nao pode ser diretorio";
            confManager.setKeyStoreType(2);
            confManager.setPKCS12FileNameSign(arquivoSign.getAbsolutePath());
            String strFileEncrypt = fileTextFieldEncrypt.getText();
            if(strFileEncrypt == null || strFileEncrypt.equals(""))
                return "Arquivo PKCS12 invalido";
            File arquivoEncrypt = new File(strFileEncrypt);
            if(!arquivoEncrypt.exists())
                return "Arquivo PKCS12 nao existe";
            if(!arquivoEncrypt.isFile())
                return "Arquivo PKCS12 nao pode ser diretorio";
            confManager.setPKCS12FileNameEncrypt(arquivoEncrypt.getAbsolutePath());
        } else
        if(hsmRadioButton.isSelected())
        {
            String libHsm = libHSMTextField.getText();
            if(libHsm == null || libHsm.equals(""))
                return "Biblioteca PKCS#11 inv\uFFFDlida";
            int slotHsm = slotList.getSelectedIndex();
            confManager.setHSMSlot(slotHsm);
            confManager.setKeyStoreType(4);
            confManager.setHSMLib(libHsm);
        } else
        {
            return "Tipo de repositorio de chaves invalido";
        }
        return null;
    }

    public static GeneralConfigurationTab getInstance()
    {
        if(instance == null)
            instance = new GeneralConfigurationTab();
        return instance;
    }

    public static GeneralConfigurationTab getInstance(Integer appletType)
    {
        if(instance == null)
            instance = new GeneralConfigurationTab();
        return instance;
    }

    public void reloadConfiguration()
    {
        if(chkSelfEncryption.isEnabled())
            chkSelfEncryption.setSelected(confManager.isSelfEncryption());
        switch(confManager.getKeyStoreType())
        {
        default:
            break;

        case 1: // '\001'
            fileRadioButton.setEnabled(true);
            smartcardRadioButton.setEnabled(true);
            String value = confManager.getPKCS11Model();
            smartcardRadioButton.doClick();
            if(!hasPkcs11Terminals)
                fileRadioButton.doClick();
            if(value == null || value.equals(""))
                comboBoxModel.setSelectedItem(null);
            else
                comboBoxModel.setSelectedItem(value);
            fileTextFieldSign.setText("");
            fileTextFieldEncrypt.setText("");
            break;

        case 2: // '\002'
            fileRadioButton.setEnabled(true);
            smartcardRadioButton.setEnabled(true);
            File fileSign = new File(confManager.getPKCS12FileNameSign());
            fileTextFieldSign.setText(fileSign.getAbsolutePath());
            File fileEncrypt = new File(confManager.getPKCS12FileNameEncrypt());
            fileTextFieldEncrypt.setText(fileEncrypt.getAbsolutePath());
            fileRadioButton.doClick();
            comboBoxModel.setSelectedItem(null);
            break;

        case 4: // '\004'
            hsmRadioButton.setEnabled(true);
            smartcardRadioButton.setEnabled(true);
            String hsmLib = confManager.getHSMLib();
            hsmRadioButton.doClick();
            libHSMTextField.setText(hsmLib);
            int hsmSlot = confManager.getHSMSlot();
            slotList.setSelectedIndex(hsmSlot);
            comboBoxModel.setSelectedItem(null);
            break;

        case 3: // '\003'
            comboBoxModel.setSelectedItem(null);
            fileTextFieldSign.setText("");
            fileTextFieldEncrypt.setText("");
            fileTextFieldSign.setEnabled(false);
            findFileButtonSign.setEnabled(false);
            fileTextFieldEncrypt.setEnabled(false);
            findFileButtonEncrypt.setEnabled(false);
            fileRadioButton.setEnabled(false);
            smartcardRadioButton.setEnabled(false);
            smartcardCombo.setSelectedItem(null);
            smartcardCombo.setEnabled(false);
            mscapiRadioButton.doClick();
            break;
        }
    }

    private void findFileButtonSign_actionPerformed(ActionEvent e)
    {
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory((new File(confManager.getPKCS12FileNameSign())).getParentFile());
        int returnValue = fc.showOpenDialog(mainPanel);
        if(returnValue == 0)
        {
            File arquivo = fc.getSelectedFile();
            fileTextFieldSign.setText(arquivo.getAbsolutePath());
            if(fileTextFieldEncrypt.getText() == null || fileTextFieldEncrypt.getText().trim().equals(""))
                fileTextFieldEncrypt.setText(arquivo.getAbsolutePath());
        }
    }

    private void findFileButtonEncrypt_actionPerformed(ActionEvent e)
    {
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory((new File(confManager.getPKCS12FileNameEncrypt())).getParentFile());
        int returnValue = fc.showOpenDialog(mainPanel);
        if(returnValue == 0)
        {
            File arquivo = fc.getSelectedFile();
            fileTextFieldEncrypt.setText(arquivo.getAbsolutePath());
        }
    }

    private void smartcardCombo_actionPerformed(ActionEvent event)
    {
        selectedCard = (String)comboBoxModel.getSelectedItem();
        if(selectedCard == null)
        {
            return;
        } else
        {
            confManager.setPKCS11Model(selectedCard);
            confManager.setKeyStoreType(1);
            return;
        }
    }

    private void fileRadioButton_actionPerformed(ActionEvent e)
    {
        fileTextFieldSign.setEnabled(true);
        findFileButtonSign.setEnabled(true);
        fileTextFieldEncrypt.setEnabled(true);
        findFileButtonEncrypt.setEnabled(true);
        smartcardCombo.setSelectedItem(null);
        smartcardCombo.setEnabled(false);
        libHSMTextField.setEnabled(false);
        libHSMTextField.setEditable(false);
        slotList.setEnabled(false);
        libHSMTextField.setText("");
        slotList.setSelectedIndex(-1);
        File fileSign = new File(confManager.getPKCS12FileNameSign());
        String fileNameSign = fileSign.getAbsolutePath();
        if(fileNameSign.equals(fileTextFieldSign.getText()))
            if(!fileSign.exists());
        File fileEncrypt = new File(confManager.getPKCS12FileNameEncrypt());
        String fileNameEncrypt = fileEncrypt.getAbsolutePath();
        if(fileNameEncrypt.equals(fileTextFieldEncrypt.getText()) && fileEncrypt.exists() && confManager.getPKCS12FileNameEncrypt() != null)
            confManager.setKeyStoreType(2);
    }

    private void hsmRadioButton_actionPerformed(ActionEvent e)
    {
        fileTextFieldSign.setEnabled(false);
        findFileButtonSign.setEnabled(false);
        fileTextFieldEncrypt.setEnabled(false);
        findFileButtonEncrypt.setEnabled(false);
        libHSMTextField.setEnabled(true);
        slotList.setEnabled(true);
        slotList.setSelectedIndex(0);
        libHSMTextField.setEditable(true);
        libHSMTextField.requestFocus();
        smartcardCombo.setSelectedItem(null);
        smartcardCombo.setEnabled(false);
        fileTextFieldEncrypt.setText("");
        fileTextFieldSign.setText("");
    }

    private void smartcardRadioButton_actionPerformed(ActionEvent e)
    {
        try
        {
            String models[] = PKCS11Tools.getSmartCardModels();
            if(models == null)
                models = new String[0];
            hasPkcs11Terminals = models.length != 0;
            comboBoxModel = new DefaultComboBoxModel(models);
            comboBoxModel.setSelectedItem(null);
            smartcardCombo.setModel(comboBoxModel);
            smartcardCombo.insertItemAt("Automatico", 0);
            smartcardCombo.setSelectedIndex(0);
            Dimension dim = smartcardCombo.getPreferredSize();
            int maxContent = getMaxSize(models);
            int maxSize = (int)((double)maxContent * 7.5D);
            if(maxSize > 350)
                maxSize = 350;
            if(maxSize < 150)
                maxSize = 150;
            dim.width = maxSize;
            smartcardCombo.setPreferredSize(dim);
            smartcardCombo.repaint();
            smartcardCombo.setEnabled(true);
            fileTextFieldSign.setText("");
            fileTextFieldSign.setEnabled(false);
            findFileButtonSign.setEnabled(false);
            fileTextFieldEncrypt.setText("");
            fileTextFieldEncrypt.setEnabled(false);
            findFileButtonEncrypt.setEnabled(false);
            libHSMTextField.setEnabled(false);
            libHSMTextField.setText("");
            libHSMTextField.setEditable(false);
            slotList.setEnabled(false);
            slotList.setSelectedIndex(-1);
            if(!hasPkcs11Terminals)
                return;
        }
        catch(NoClassDefFoundError ne)
        {
            throw ne;
        }
    }

    private void mscapiRadioButton_actionPerformed(ActionEvent e)
    {
        boolean enableFields = !mscapiRadioButton.isSelected();
        comboBoxModel.setSelectedItem(null);
        fileTextFieldSign.setText("");
        fileTextFieldEncrypt.setText("");
        fileTextFieldSign.setEnabled(enableFields);
        findFileButtonSign.setEnabled(enableFields);
        fileTextFieldEncrypt.setEnabled(enableFields);
        findFileButtonEncrypt.setEnabled(enableFields);
        fileRadioButton.setEnabled(!enableFields);
        libHSMTextField.setEnabled(enableFields);
        libHSMTextField.setText("");
        libHSMTextField.setEditable(enableFields);
        slotList.setEnabled(enableFields);
        slotList.setSelectedIndex(-1);
        smartcardRadioButton.setEnabled(!enableFields);
        smartcardCombo.setSelectedItem(null);
        smartcardCombo.setEnabled(enableFields);
    }

    private void jbInit()
    {
        mainPanel = new JPanel();
        fileRadioButton = new JRadioButton();
        smartcardRadioButton = new JRadioButton();
        mscapiRadioButton = new JRadioButton();
        smartcardCombo = new JComboBox();
        fileLabelFieldSign = new JLabel();
        fileTextFieldSign = new JTextField();
        findFileButtonSign = new JButton();
        hsmRadioButton = new JRadioButton();
        libHSMLabelField = new JLabel();
        libHSMTextField = new JTextField();
        hsmRadioButton = new JRadioButton();
        slotHSMLabelField = new JLabel();
        slotList = new JComboBox();
        for(int i = 0; i < 25; i++)
            slotList.insertItemAt((new StringBuilder()).append(" ").append(i).append(" ").toString(), i);

        fileLabelFieldEncrypt = new JLabel();
        fileTextFieldEncrypt = new JTextField();
        findFileButtonEncrypt = new JButton();
        chkSelfEncryption = new JCheckBox("Auto Encripta\347\343o");
        ButtonGroup keystoreRadioGroup = new ButtonGroup();
        JPanel subPanelPkcs11 = new JPanel();
        JPanel subPanelMsCapi = new JPanel();
        JPanel subPanelPkcs12 = new JPanel();
        JPanel subPanelPkcs12Sign = new JPanel();
        JPanel subPanelPkcs12Encrypt = new JPanel();
        JPanel subPanelHsm = new JPanel();
        JPanel subPanelLibHsm = new JPanel();
        JPanel subMainPanel = new JPanel();
        Border border1 = BorderFactory.createEtchedBorder(Color.white, new Color(148, 145, 140));
        Border border2 = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        TitledBorder titledBorder1 = new TitledBorder(border1, "Reposit\363rio de chaves");
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(border2);
        subMainPanel.setBorder(titledBorder1);
        subMainPanel.setLayout(new GridLayout(7, 1));
        mainPanel.add(subMainPanel, "North");
        subMainPanel.add(subPanelPkcs11, null);
        subPanelMsCapi.add(mscapiRadioButton, null);
        subPanelPkcs11.add(smartcardRadioButton, null);
        subPanelPkcs11.add(smartcardCombo, null);
        subMainPanel.add(subPanelPkcs12, null);
        subMainPanel.add(subPanelPkcs12Sign, null);
        subMainPanel.add(subPanelPkcs12Encrypt, null);
        if(DownloadLibs.isWindows())
            subMainPanel.add(subPanelMsCapi, null);
        subMainPanel.add(subPanelHsm, null);
        subMainPanel.add(subPanelLibHsm, null);
        JLabel l = new JLabel();
        l.setText("                                                                  ");
        subPanelPkcs12.add(fileRadioButton, null);
        subPanelPkcs12.add(l, null);
        subPanelPkcs12.add(chkSelfEncryption, null);
        subPanelPkcs12Sign.add(fileLabelFieldSign, null);
        subPanelPkcs12Sign.add(fileTextFieldSign, null);
        subPanelPkcs12Sign.add(findFileButtonSign, null);
        subPanelPkcs12Encrypt.add(fileLabelFieldEncrypt, null);
        subPanelPkcs12Encrypt.add(fileTextFieldEncrypt, null);
        subPanelPkcs12Encrypt.add(findFileButtonEncrypt, null);
        fileRadioButton.setText("Arquivo PKCS12 (A1)");
        fileRadioButton.addActionListener(new ActionListener() {

        
            public void actionPerformed(ActionEvent e)
            {
                fileRadioButton_actionPerformed(e);
            }

     
        }
);
        findFileButtonSign.setEnabled(false);
        findFileButtonSign.setText("Procurar");
        findFileButtonSign.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                findFileButtonSign_actionPerformed(e);
            }

            
            
        }
);
        findFileButtonEncrypt.setEnabled(false);
        findFileButtonEncrypt.setText("Procurar");
        findFileButtonEncrypt.addActionListener(new ActionListener() {

             public void actionPerformed(ActionEvent e)
            {
                findFileButtonEncrypt_actionPerformed(e);
            }

            
          
        }
);
        subPanelPkcs12Sign.setLayout(new FlowLayout(0));
        JLabel l1 = new JLabel();
        l1.setText("    ");
        subPanelHsm.add(hsmRadioButton, null);
        subPanelHsm.add(l1, null);
        subPanelLibHsm.add(libHSMLabelField, null);
        subPanelLibHsm.add(libHSMTextField, null);
        libHSMTextField.setColumns(10);
        subPanelLibHsm.add(l1, null);
        subPanelLibHsm.add(slotHSMLabelField, null);
        subPanelLibHsm.add(slotList, null);
        subPanelHsm.setLayout(new FlowLayout(0));
        subPanelLibHsm.setLayout(new FlowLayout(0));
        hsmRadioButton.setText("HSM");
        hsmRadioButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                hsmRadioButton_actionPerformed(e);
            }

            
        
        }
);
        subPanelMsCapi.setLayout(new FlowLayout(0));
        smartcardRadioButton.setSelected(true);
        smartcardRadioButton.setText("Cartao / Token");
        smartcardRadioButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                smartcardRadioButton_actionPerformed(e);
            }

        }
);
        mscapiRadioButton.setText("Microsoft CAPI");
        mscapiRadioButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                mscapiRadioButton_actionPerformed(e);
            }

            
          
        }
);
        String finalGap[] = getLabelPKCS12("Assinatura", "Sigilo");
        fileLabelFieldSign.setText(finalGap[0]);
        fileLabelFieldEncrypt.setText(finalGap[1]);
        libHSMLabelField.setText("       Biblioteca PKCS#11");
        slotHSMLabelField.setText("N\372mero do Slot");
        subPanelPkcs12Encrypt.setLayout(new FlowLayout(0));
        subPanelPkcs12.setLayout(new FlowLayout(0));
        subPanelPkcs11.setLayout(new FlowLayout(0));
        fileTextFieldSign.setEnabled(false);
        fileTextFieldSign.setEditable(false);
        fileTextFieldSign.setColumns(20);
        fileTextFieldEncrypt.setEnabled(false);
        fileTextFieldEncrypt.setEditable(false);
        fileTextFieldEncrypt.setColumns(20);
        keystoreRadioGroup.add(smartcardRadioButton);
        keystoreRadioGroup.add(fileRadioButton);
        if(DownloadLibs.isWindows())
            keystoreRadioGroup.add(mscapiRadioButton);
        keystoreRadioGroup.add(hsmRadioButton);
        comboBoxModel = new DefaultComboBoxModel();
        smartcardCombo.setModel(comboBoxModel);
        smartcardCombo.addActionListener(new ActionListener() {

    
            public void actionPerformed(ActionEvent e)
            {
                smartcardCombo_actionPerformed(e);
            }

           
        }
);
        Dimension actualDim = smartcardCombo.getPreferredSize();
        smartcardCombo.setAutoscrolls(true);
        if(!hasPkcs11Terminals)
            actualDim.width += 100;
        Dimension maxDim = new Dimension();
        maxDim.height = actualDim.height;
        maxDim.width = actualDim.width + 300;
        smartcardCombo.setPreferredSize(actualDim);
        smartcardCombo.setMaximumSize(maxDim);
    }

    private GeneralConfigurationTab()
    {
        name = "geral";
        confManager = ConfigurationManager.getInstance();
        getPanel();
    }

    private String[] getLabelPKCS12(String labelSignValue, String labelEncryptValue)
    {
        String finalGapEncrypt = "";
        int len = labelSignValue.trim().length() - labelEncryptValue.trim().length();
        if(len == 2)
            finalGapEncrypt = (new StringBuilder()).append(finalGapEncrypt).append("   ").toString();
        else
            finalGapEncrypt = (new StringBuilder()).append(finalGapEncrypt).append("         ").toString();
        String finalGap[] = new String[2];
        finalGap[0] = (new StringBuilder()).append("           ").append(labelSignValue.trim()).toString();
        finalGap[1] = (new StringBuilder()).append("           ").append(labelEncryptValue.trim()).append(finalGapEncrypt).toString();
        return finalGap;
    }

    private int getMaxSize(String models[])
    {
        int maxSize = 0;
        for(int i = 0; i < models.length; i++)
            if(models[i].length() > maxSize)
                maxSize = models[i].length();

        return maxSize;
    }







}