/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  br.com.esec.util.libs.DownloadLibs
 */
package br.gov.pbh.desif.esec.config.tab;

import br.com.esec.util.libs.DownloadLibs;
import br.gov.pbh.desif.esec.assinatura.PKCS11Tools;
import br.gov.pbh.desif.esec.config.ConfigurationManager;
import br.gov.pbh.desif.esec.config.tab.AbstractConfigurationTab;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class GeneralConfigurationTab
extends AbstractConfigurationTab {
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

    @Override
    public JPanel getPanel() {
        if (this.mainPanel == null) {
            this.title = "Geral";
            this.jbInit();
        }
        this.reloadConfiguration();
        return this.mainPanel;
    }

    public String validateConfiguration() {
        this.confManager.setSelfEncryption(this.chkSelfEncryption.isSelected());
        if (this.mscapiRadioButton.isSelected()) {
            this.confManager.setKeyStoreType(3);
        } else if (this.smartcardRadioButton.isSelected()) {
            this.selectedCard = (String)this.comboBoxModel.getSelectedItem();
            if (this.selectedCard == null) {
                return "Escolha um modelo de cartao/token";
            }
            this.confManager.setKeyStoreType(1);
            this.confManager.setPKCS11Model(this.selectedCard);
        } else if (this.fileRadioButton.isSelected()) {
            String strFileSign = this.fileTextFieldSign.getText();
            if (strFileSign == null || strFileSign.equals("")) {
                return "Arquivo PKCS12 invalido";
            }
            File arquivoSign = new File(strFileSign);
            if (!arquivoSign.exists()) {
                return "Arquivo PKCS12 nao existe";
            }
            if (!arquivoSign.isFile()) {
                return "Arquivo PKCS12 nao pode ser diretorio";
            }
            this.confManager.setKeyStoreType(2);
            this.confManager.setPKCS12FileNameSign(arquivoSign.getAbsolutePath());
            String strFileEncrypt = this.fileTextFieldEncrypt.getText();
            if (strFileEncrypt == null || strFileEncrypt.equals("")) {
                return "Arquivo PKCS12 invalido";
            }
            File arquivoEncrypt = new File(strFileEncrypt);
            if (!arquivoEncrypt.exists()) {
                return "Arquivo PKCS12 nao existe";
            }
            if (!arquivoEncrypt.isFile()) {
                return "Arquivo PKCS12 nao pode ser diretorio";
            }
            this.confManager.setPKCS12FileNameEncrypt(arquivoEncrypt.getAbsolutePath());
        } else if (this.hsmRadioButton.isSelected()) {
            String libHsm = this.libHSMTextField.getText();
            if (libHsm == null || libHsm.equals("")) {
                return "Biblioteca PKCS#11 inv\ufffdlida";
            }
            int slotHsm = this.slotList.getSelectedIndex();
            this.confManager.setHSMSlot(slotHsm);
            this.confManager.setKeyStoreType(4);
            this.confManager.setHSMLib(libHsm);
        } else {
            return "Tipo de repositorio de chaves invalido";
        }
        return null;
    }

    public static GeneralConfigurationTab getInstance() {
        if (instance == null) {
            instance = new GeneralConfigurationTab();
        }
        return instance;
    }

    public static GeneralConfigurationTab getInstance(Integer appletType) {
        if (instance == null) {
            instance = new GeneralConfigurationTab();
        }
        return instance;
    }

    public void reloadConfiguration() {
        if (this.chkSelfEncryption.isEnabled()) {
            this.chkSelfEncryption.setSelected(this.confManager.isSelfEncryption());
        }
        switch (this.confManager.getKeyStoreType()) {
            case 1: {
                this.fileRadioButton.setEnabled(true);
                this.smartcardRadioButton.setEnabled(true);
                String value = this.confManager.getPKCS11Model();
                this.smartcardRadioButton.doClick();
                if (!this.hasPkcs11Terminals) {
                    this.fileRadioButton.doClick();
                }
                if (value == null || value.equals("")) {
                    this.comboBoxModel.setSelectedItem(null);
                } else {
                    this.comboBoxModel.setSelectedItem(value);
                }
                this.fileTextFieldSign.setText("");
                this.fileTextFieldEncrypt.setText("");
                break;
            }
            case 2: {
                this.fileRadioButton.setEnabled(true);
                this.smartcardRadioButton.setEnabled(true);
                File fileSign = new File(this.confManager.getPKCS12FileNameSign());
                this.fileTextFieldSign.setText(fileSign.getAbsolutePath());
                File fileEncrypt = new File(this.confManager.getPKCS12FileNameEncrypt());
                this.fileTextFieldEncrypt.setText(fileEncrypt.getAbsolutePath());
                this.fileRadioButton.doClick();
                this.comboBoxModel.setSelectedItem(null);
                break;
            }
            case 4: {
                this.hsmRadioButton.setEnabled(true);
                this.smartcardRadioButton.setEnabled(true);
                String hsmLib = this.confManager.getHSMLib();
                this.hsmRadioButton.doClick();
                this.libHSMTextField.setText(hsmLib);
                int hsmSlot = this.confManager.getHSMSlot();
                this.slotList.setSelectedIndex(hsmSlot);
                this.comboBoxModel.setSelectedItem(null);
                break;
            }
            case 3: {
                this.comboBoxModel.setSelectedItem(null);
                this.fileTextFieldSign.setText("");
                this.fileTextFieldEncrypt.setText("");
                this.fileTextFieldSign.setEnabled(false);
                this.findFileButtonSign.setEnabled(false);
                this.fileTextFieldEncrypt.setEnabled(false);
                this.findFileButtonEncrypt.setEnabled(false);
                this.fileRadioButton.setEnabled(false);
                this.smartcardRadioButton.setEnabled(false);
                this.smartcardCombo.setSelectedItem(null);
                this.smartcardCombo.setEnabled(false);
                this.mscapiRadioButton.doClick();
            }
        }
    }

    private void findFileButtonSign_actionPerformed(ActionEvent e) {
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File(this.confManager.getPKCS12FileNameSign()).getParentFile());
        int returnValue = fc.showOpenDialog(this.mainPanel);
        if (returnValue == 0) {
            File arquivo = fc.getSelectedFile();
            this.fileTextFieldSign.setText(arquivo.getAbsolutePath());
            if (this.fileTextFieldEncrypt.getText() == null || this.fileTextFieldEncrypt.getText().trim().equals("")) {
                this.fileTextFieldEncrypt.setText(arquivo.getAbsolutePath());
            }
        }
    }

    private void findFileButtonEncrypt_actionPerformed(ActionEvent e) {
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File(this.confManager.getPKCS12FileNameEncrypt()).getParentFile());
        int returnValue = fc.showOpenDialog(this.mainPanel);
        if (returnValue == 0) {
            File arquivo = fc.getSelectedFile();
            this.fileTextFieldEncrypt.setText(arquivo.getAbsolutePath());
        }
    }

    private void smartcardCombo_actionPerformed(ActionEvent event) {
        this.selectedCard = (String)this.comboBoxModel.getSelectedItem();
        if (this.selectedCard == null) {
            return;
        }
        this.confManager.setPKCS11Model(this.selectedCard);
        this.confManager.setKeyStoreType(1);
    }

    private void fileRadioButton_actionPerformed(ActionEvent e) {
        File fileEncrypt;
        String fileNameEncrypt;
        this.fileTextFieldSign.setEnabled(true);
        this.findFileButtonSign.setEnabled(true);
        this.fileTextFieldEncrypt.setEnabled(true);
        this.findFileButtonEncrypt.setEnabled(true);
        this.smartcardCombo.setSelectedItem(null);
        this.smartcardCombo.setEnabled(false);
        this.libHSMTextField.setEnabled(false);
        this.libHSMTextField.setEditable(false);
        this.slotList.setEnabled(false);
        this.libHSMTextField.setText("");
        this.slotList.setSelectedIndex(-1);
        File fileSign = new File(this.confManager.getPKCS12FileNameSign());
        String fileNameSign = fileSign.getAbsolutePath();
        if (!fileNameSign.equals(this.fileTextFieldSign.getText()) || fileSign.exists()) {
            // empty if block
        }
        if ((fileNameEncrypt = (fileEncrypt = new File(this.confManager.getPKCS12FileNameEncrypt())).getAbsolutePath()).equals(this.fileTextFieldEncrypt.getText()) && fileEncrypt.exists() && this.confManager.getPKCS12FileNameEncrypt() != null) {
            this.confManager.setKeyStoreType(2);
        }
    }

    private void hsmRadioButton_actionPerformed(ActionEvent e) {
        this.fileTextFieldSign.setEnabled(false);
        this.findFileButtonSign.setEnabled(false);
        this.fileTextFieldEncrypt.setEnabled(false);
        this.findFileButtonEncrypt.setEnabled(false);
        this.libHSMTextField.setEnabled(true);
        this.slotList.setEnabled(true);
        this.slotList.setSelectedIndex(0);
        this.libHSMTextField.setEditable(true);
        this.libHSMTextField.requestFocus();
        this.smartcardCombo.setSelectedItem(null);
        this.smartcardCombo.setEnabled(false);
        this.fileTextFieldEncrypt.setText("");
        this.fileTextFieldSign.setText("");
    }

    private void smartcardRadioButton_actionPerformed(ActionEvent e) {
        String[] models = PKCS11Tools.getSmartCardModels();
        if (models == null) {
            models = new String[]{};
        }
        this.hasPkcs11Terminals = models.length != 0;
        this.comboBoxModel = new DefaultComboBoxModel<String>(models);
        this.comboBoxModel.setSelectedItem(null);
        this.smartcardCombo.setModel(this.comboBoxModel);
        this.smartcardCombo.insertItemAt("Automatico", 0);
        this.smartcardCombo.setSelectedIndex(0);
        Dimension dim = this.smartcardCombo.getPreferredSize();
        int maxContent = this.getMaxSize(models);
        int maxSize = (int)((double)maxContent * 7.5);
        if (maxSize > 350) {
            maxSize = 350;
        }
        if (maxSize < 150) {
            maxSize = 150;
        }
        dim.width = maxSize;
        this.smartcardCombo.setPreferredSize(dim);
        this.smartcardCombo.repaint();
        this.smartcardCombo.setEnabled(true);
        this.fileTextFieldSign.setText("");
        this.fileTextFieldSign.setEnabled(false);
        this.findFileButtonSign.setEnabled(false);
        this.fileTextFieldEncrypt.setText("");
        this.fileTextFieldEncrypt.setEnabled(false);
        this.findFileButtonEncrypt.setEnabled(false);
        this.libHSMTextField.setEnabled(false);
        this.libHSMTextField.setText("");
        this.libHSMTextField.setEditable(false);
        this.slotList.setEnabled(false);
        this.slotList.setSelectedIndex(-1);
        if (!this.hasPkcs11Terminals) {
            return;
        }
    }

    private void mscapiRadioButton_actionPerformed(ActionEvent e) {
        boolean enableFields = !this.mscapiRadioButton.isSelected();
        this.comboBoxModel.setSelectedItem(null);
        this.fileTextFieldSign.setText("");
        this.fileTextFieldEncrypt.setText("");
        this.fileTextFieldSign.setEnabled(enableFields);
        this.findFileButtonSign.setEnabled(enableFields);
        this.fileTextFieldEncrypt.setEnabled(enableFields);
        this.findFileButtonEncrypt.setEnabled(enableFields);
        this.fileRadioButton.setEnabled(!enableFields);
        this.libHSMTextField.setEnabled(enableFields);
        this.libHSMTextField.setText("");
        this.libHSMTextField.setEditable(enableFields);
        this.slotList.setEnabled(enableFields);
        this.slotList.setSelectedIndex(-1);
        this.smartcardRadioButton.setEnabled(!enableFields);
        this.smartcardCombo.setSelectedItem(null);
        this.smartcardCombo.setEnabled(enableFields);
    }

    private void jbInit() {
        this.mainPanel = new JPanel();
        this.fileRadioButton = new JRadioButton();
        this.smartcardRadioButton = new JRadioButton();
        this.mscapiRadioButton = new JRadioButton();
        this.smartcardCombo = new JComboBox();
        this.fileLabelFieldSign = new JLabel();
        this.fileTextFieldSign = new JTextField();
        this.findFileButtonSign = new JButton();
        this.hsmRadioButton = new JRadioButton();
        this.libHSMLabelField = new JLabel();
        this.libHSMTextField = new JTextField();
        this.hsmRadioButton = new JRadioButton();
        this.slotHSMLabelField = new JLabel();
        this.slotList = new JComboBox();
        for (int i = 0; i < 25; ++i) {
            this.slotList.insertItemAt(" " + i + " ", i);
        }
        this.fileLabelFieldEncrypt = new JLabel();
        this.fileTextFieldEncrypt = new JTextField();
        this.findFileButtonEncrypt = new JButton();
        this.chkSelfEncryption = new JCheckBox("Auto Encripta\u00e7\u00e3o");
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
        TitledBorder titledBorder1 = new TitledBorder(border1, "Reposit\u00f3rio de chaves");
        this.mainPanel.setLayout(new BorderLayout());
        this.mainPanel.setBorder(border2);
        subMainPanel.setBorder(titledBorder1);
        subMainPanel.setLayout(new GridLayout(7, 1));
        this.mainPanel.add((Component)subMainPanel, "North");
        subMainPanel.add((Component)subPanelPkcs11, (Object)null);
        subPanelMsCapi.add((Component)this.mscapiRadioButton, (Object)null);
        subPanelPkcs11.add((Component)this.smartcardRadioButton, (Object)null);
        subPanelPkcs11.add((Component)this.smartcardCombo, (Object)null);
        subMainPanel.add((Component)subPanelPkcs12, (Object)null);
        subMainPanel.add((Component)subPanelPkcs12Sign, (Object)null);
        subMainPanel.add((Component)subPanelPkcs12Encrypt, (Object)null);
        if (DownloadLibs.isWindows()) {
            subMainPanel.add((Component)subPanelMsCapi, (Object)null);
        }
        subMainPanel.add((Component)subPanelHsm, (Object)null);
        subMainPanel.add((Component)subPanelLibHsm, (Object)null);
        JLabel l = new JLabel();
        l.setText("                                                                  ");
        subPanelPkcs12.add((Component)this.fileRadioButton, (Object)null);
        subPanelPkcs12.add((Component)l, (Object)null);
        subPanelPkcs12.add((Component)this.chkSelfEncryption, (Object)null);
        subPanelPkcs12Sign.add((Component)this.fileLabelFieldSign, (Object)null);
        subPanelPkcs12Sign.add((Component)this.fileTextFieldSign, (Object)null);
        subPanelPkcs12Sign.add((Component)this.findFileButtonSign, (Object)null);
        subPanelPkcs12Encrypt.add((Component)this.fileLabelFieldEncrypt, (Object)null);
        subPanelPkcs12Encrypt.add((Component)this.fileTextFieldEncrypt, (Object)null);
        subPanelPkcs12Encrypt.add((Component)this.findFileButtonEncrypt, (Object)null);
        this.fileRadioButton.setText("Arquivo PKCS12 (A1)");
        this.fileRadioButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                GeneralConfigurationTab.this.fileRadioButton_actionPerformed(e);
            }
        });
        this.findFileButtonSign.setEnabled(false);
        this.findFileButtonSign.setText("Procurar");
        this.findFileButtonSign.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                GeneralConfigurationTab.this.findFileButtonSign_actionPerformed(e);
            }
        });
        this.findFileButtonEncrypt.setEnabled(false);
        this.findFileButtonEncrypt.setText("Procurar");
        this.findFileButtonEncrypt.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                GeneralConfigurationTab.this.findFileButtonEncrypt_actionPerformed(e);
            }
        });
        subPanelPkcs12Sign.setLayout(new FlowLayout(0));
        JLabel l1 = new JLabel();
        l1.setText("    ");
        subPanelHsm.add((Component)this.hsmRadioButton, (Object)null);
        subPanelHsm.add((Component)l1, (Object)null);
        subPanelLibHsm.add((Component)this.libHSMLabelField, (Object)null);
        subPanelLibHsm.add((Component)this.libHSMTextField, (Object)null);
        this.libHSMTextField.setColumns(10);
        subPanelLibHsm.add((Component)l1, (Object)null);
        subPanelLibHsm.add((Component)this.slotHSMLabelField, (Object)null);
        subPanelLibHsm.add((Component)this.slotList, (Object)null);
        subPanelHsm.setLayout(new FlowLayout(0));
        subPanelLibHsm.setLayout(new FlowLayout(0));
        this.hsmRadioButton.setText("HSM");
        this.hsmRadioButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                GeneralConfigurationTab.this.hsmRadioButton_actionPerformed(e);
            }
        });
        subPanelMsCapi.setLayout(new FlowLayout(0));
        this.smartcardRadioButton.setSelected(true);
        this.smartcardRadioButton.setText("Cartao / Token");
        this.smartcardRadioButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                GeneralConfigurationTab.this.smartcardRadioButton_actionPerformed(e);
            }
        });
        this.mscapiRadioButton.setText("Microsoft CAPI");
        this.mscapiRadioButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                GeneralConfigurationTab.this.mscapiRadioButton_actionPerformed(e);
            }
        });
        String[] finalGap = this.getLabelPKCS12("Assinatura", "Sigilo");
        this.fileLabelFieldSign.setText(finalGap[0]);
        this.fileLabelFieldEncrypt.setText(finalGap[1]);
        this.libHSMLabelField.setText("       Biblioteca PKCS#11");
        this.slotHSMLabelField.setText("N\u00famero do Slot");
        subPanelPkcs12Encrypt.setLayout(new FlowLayout(0));
        subPanelPkcs12.setLayout(new FlowLayout(0));
        subPanelPkcs11.setLayout(new FlowLayout(0));
        this.fileTextFieldSign.setEnabled(false);
        this.fileTextFieldSign.setEditable(false);
        this.fileTextFieldSign.setColumns(20);
        this.fileTextFieldEncrypt.setEnabled(false);
        this.fileTextFieldEncrypt.setEditable(false);
        this.fileTextFieldEncrypt.setColumns(20);
        keystoreRadioGroup.add(this.smartcardRadioButton);
        keystoreRadioGroup.add(this.fileRadioButton);
        if (DownloadLibs.isWindows()) {
            keystoreRadioGroup.add(this.mscapiRadioButton);
        }
        keystoreRadioGroup.add(this.hsmRadioButton);
        this.comboBoxModel = new DefaultComboBoxModel();
        this.smartcardCombo.setModel(this.comboBoxModel);
        this.smartcardCombo.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                GeneralConfigurationTab.this.smartcardCombo_actionPerformed(e);
            }
        });
        Dimension actualDim = this.smartcardCombo.getPreferredSize();
        this.smartcardCombo.setAutoscrolls(true);
        if (!this.hasPkcs11Terminals) {
            actualDim.width += 100;
        }
        Dimension maxDim = new Dimension();
        maxDim.height = actualDim.height;
        maxDim.width = actualDim.width + 300;
        this.smartcardCombo.setPreferredSize(actualDim);
        this.smartcardCombo.setMaximumSize(maxDim);
    }

    private GeneralConfigurationTab() {
        this.name = "geral";
        this.confManager = ConfigurationManager.getInstance();
        this.getPanel();
    }

    private String[] getLabelPKCS12(String labelSignValue, String labelEncryptValue) {
        String finalGapEncrypt = "";
        int len = labelSignValue.trim().length() - labelEncryptValue.trim().length();
        finalGapEncrypt = len == 2 ? finalGapEncrypt + "   " : finalGapEncrypt + "         ";
        String[] finalGap = new String[]{"           " + labelSignValue.trim(), "           " + labelEncryptValue.trim() + finalGapEncrypt};
        return finalGap;
    }

    private int getMaxSize(String[] models) {
        int maxSize = 0;
        for (int i = 0; i < models.length; ++i) {
            if (models[i].length() <= maxSize) continue;
            maxSize = models[i].length();
        }
        return maxSize;
    }

}

