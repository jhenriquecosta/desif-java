/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.esec.gui;

import br.gov.pbh.desif.esec.gui.TextLabel;
import br.gov.pbh.desif.esec.images.LoadImages;
import br.gov.pbh.desif.esec.util.Util;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class GUIKeyStorePassword
extends JDialog {
    private static final long serialVersionUID = 1L;
    public static final int PIN_PASSWORD = 0;
    public static final int SO_PASSWORD = 1;
    public static final int CHANGE_PASSWORD = 2;
    public static final int SET_PASSWORD = 3;
    JPanel mainPanel = new JPanel();
    BorderLayout borderLayout1 = new BorderLayout();
    JPanel messagePanel = new JPanel();
    JPanel passwordPanel = new JPanel();
    JPanel soPasswordPanel = new JPanel();
    JPanel newPasswordPanel = new JPanel();
    JPanel groupPasswordPanel = new JPanel();
    JPanel confirmPasswordPanel = new JPanel();
    JPanel backButtonsPanel = new JPanel();
    BorderLayout borderLayout2 = new BorderLayout();
    FlowLayout flowLayout1 = new FlowLayout();
    Border border1;
    JLabel passwordLabel = new JLabel();
    JLabel soPasswordLabel = new JLabel();
    JLabel newPasswordLabel = new JLabel();
    JLabel confirmPasswordLabel = new JLabel();
    JPasswordField passwordTextField = new JPasswordField();
    JPasswordField soPasswordTextField = new JPasswordField();
    JPasswordField newPasswordTextField = new JPasswordField();
    JPasswordField confirmPasswordTextField = new JPasswordField();
    Border border2;
    FlowLayout flowLayout2 = new FlowLayout();
    JPanel buttonsPanel = new JPanel();
    GridLayout gridLayout1 = new GridLayout();
    JButton okButton = new JButton();
    JButton cancelButton = new JButton();
    Border border3;
    private boolean okOptionAllowed = false;
    private TextLabel messageLabel;
    private Set passwordDialogListeners = new HashSet();
    private Icon icon = LoadImages.getImage("privateKey.png");
    private static Window parentComponent;
    private static boolean returnVisible;
    private static String title;
    private static String message;
    private int passwordType;

    public static void setMessage(String _message) {
        message = _message;
    }

    public static void setParentComponent(Window parentComponent, boolean returnVisible) {
        GUIKeyStorePassword.parentComponent = parentComponent;
        GUIKeyStorePassword.returnVisible = returnVisible;
    }

    public static void setGUITitle(String _title) {
        title = _title;
    }

    public GUIKeyStorePassword(Dialog parentComponent, String title, String message, int passwordType) {
        super(parentComponent, title, true);
        this.init(message, passwordType);
    }

    public GUIKeyStorePassword(Frame parentComponent, String title, String message, int changePassword) {
        super(parentComponent, title, true);
        this.init(message, this.passwordType);
    }

    public void addPasswordDialogListener(GUIPasswordDialogListener listener) {
        this.passwordDialogListeners.add(listener);
    }

    public void fireCancelOption() {
        this.passwordTextField.setText(null);
        this.newPasswordTextField.setText(null);
        this.confirmPasswordTextField.setText(null);
        Iterator i = this.passwordDialogListeners.iterator();
        while (i.hasNext()) {
            ((GUIPasswordDialogListener)i.next()).cancelOption(this);
        }
    }

    public void fireOkOption() {
        if (!(this.passwordType != 2 && this.passwordType != 3 || Arrays.equals(this.getNewPassword(), this.getConfirmPassword()))) {
            JOptionPane.showMessageDialog(this, "Nova Senha diferente de Confirmacao", "Atencao", 0);
            return;
        }
        Iterator i = this.passwordDialogListeners.iterator();
        while (i.hasNext()) {
            ((GUIPasswordDialogListener)i.next()).okOption(this);
        }
        if (parentComponent != null && returnVisible) {
            try {
                parentComponent.setVisible(true);
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
    }

    public char[] getPassword() {
        return this.passwordTextField.getPassword();
    }

    public char[] getNewPassword() {
        return this.newPasswordTextField.getPassword();
    }

    public char[] getSOPassword() {
        return this.soPasswordTextField.getPassword();
    }

    public char[] getConfirmPassword() {
        return this.confirmPasswordTextField.getPassword();
    }

    public static char[] recoverPassword() {
        ArrayList passList = GUIKeyStorePassword.recoverPassword(0);
        if (passList == null || passList.size() == 0) {
            return null;
        }
        return (char[])passList.get(0);
    }

    public static ArrayList recoverPassword(int passwordType) {
        if (parentComponent != null) {
            parentComponent.setVisible(false);
        }
        GUIKeyStorePassword passwordDialog = null;
        if (passwordType == 1) {
            title = "Informe a Senha SO";
            message = "Informe a Senha SO";
        }
        if (passwordType == 2 || passwordType == 3) {
            title = "Informe a Nova Senha";
            message = "Informe a Nova Senha";
        } else {
            title = "Informe a Senha";
            message = "Informe a Senha";
        }
        passwordDialog = parentComponent instanceof Dialog ? new GUIKeyStorePassword((Dialog)parentComponent, title, message, passwordType) : (parentComponent instanceof Frame ? new GUIKeyStorePassword((Frame)parentComponent, title, message, passwordType) : new GUIKeyStorePassword((Dialog)null, title, message, passwordType));
        final GUIKeyStorePassword passwordDialog_ = passwordDialog;
        final JPasswordField passwordTextField_ = passwordDialog.passwordTextField;
        GUIPasswordDialogListener listener = new GUIPasswordDialogListener(){

            @Override
            public void okOption(Object source) {
                passwordDialog_.setVisible(false);
            }

            @Override
            public void cancelOption(Object source) {
                passwordDialog_.setVisible(false);
            }

            public void windowOpened(WindowEvent e) {
                passwordTextField_.requestFocus();
            }
        };
        passwordDialog_.addPasswordDialogListener(listener);
        passwordDialog_.setVisible(true);
        char[] password = passwordDialog_.getPassword();
        char[] newPassword = passwordDialog_.getNewPassword();
        char[] confirmPassword = passwordDialog_.getConfirmPassword();
        char[] soPassword = passwordDialog_.getSOPassword();
        ArrayList<char[]> passwords = new ArrayList<char[]>();
        if (passwordType == 2) {
            if (password.length == 0 || confirmPassword.length == 0 || newPassword.length == 0) {
                return null;
            }
            passwords.add(password);
            passwords.add(newPassword);
            passwords.add(confirmPassword);
        } else if (passwordType == 3) {
            if (confirmPassword.length == 0 || newPassword.length == 0) {
                return null;
            }
            passwords.add(newPassword);
            passwords.add(confirmPassword);
        } else if (passwordType == 1) {
            if (soPassword.length == 0) {
                return null;
            }
            passwords.add(soPassword);
        } else {
            if (password.length == 0) {
                return null;
            }
            passwords.add(password);
        }
        return passwords;
    }

    private void init(String message, int passwordType) {
        this.messageLabel = new TextLabel(message, 60);
        this.jbInit(passwordType);
        try {
            this.pack();
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }
        Util.centerOnScreen(this);
    }

    private void jbInit(int passwordType) {
        this.passwordType = passwordType;
        this.border1 = BorderFactory.createEmptyBorder(0, 10, 0, 0);
        this.border2 = BorderFactory.createEmptyBorder(0, 0, 0, 5);
        this.border3 = BorderFactory.createEmptyBorder(15, 15, 15, 15);
        Border border4 = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        this.mainPanel.setLayout(this.borderLayout1);
        this.messagePanel.setLayout(this.borderLayout2);
        this.flowLayout1.setAlignment(2);
        this.flowLayout1.setHgap(0);
        this.flowLayout1.setVgap(0);
        this.passwordPanel.setBorder(border4);
        this.soPasswordPanel.setBorder(border4);
        this.newPasswordPanel.setBorder(border4);
        this.confirmPasswordPanel.setBorder(border4);
        this.groupPasswordPanel.setBorder(this.border1);
        this.passwordLabel.setBorder(this.border2);
        this.passwordLabel.setText("Senha:");
        if (passwordType == 2) {
            this.passwordLabel.setText("Senha de Ativacao:");
        }
        this.soPasswordLabel.setBorder(this.border2);
        this.soPasswordLabel.setText("Senha SO:");
        this.newPasswordLabel.setBorder(this.border2);
        this.newPasswordLabel.setText("Nova Senha:");
        this.confirmPasswordLabel.setBorder(this.border2);
        this.confirmPasswordLabel.setText("Confirmacao:");
        this.passwordTextField.setColumns(15);
        this.soPasswordTextField.setColumns(15);
        this.newPasswordTextField.setColumns(15);
        this.confirmPasswordTextField.setColumns(15);
        this.backButtonsPanel.setLayout(this.flowLayout2);
        this.flowLayout2.setHgap(0);
        this.flowLayout2.setVgap(0);
        this.buttonsPanel.setLayout(this.gridLayout1);
        this.gridLayout1.setColumns(2);
        this.gridLayout1.setHgap(5);
        this.okButton.setText("OK");
        this.okButton.setEnabled(false);
        this.cancelButton.setText("Cancelar");
        this.borderLayout1.setVgap(20);
        this.mainPanel.setBorder(this.border3);
        this.setResizable(false);
        this.setModal(true);
        this.getContentPane().add((Component)this.mainPanel, "Center");
        this.mainPanel.add((Component)this.messagePanel, "North");
        this.passwordPanel.setLayout(this.flowLayout1);
        this.soPasswordPanel.setLayout(this.flowLayout1);
        this.newPasswordPanel.setLayout(this.flowLayout1);
        this.confirmPasswordPanel.setLayout(this.flowLayout1);
        this.groupPasswordPanel.setLayout(this.flowLayout1);
        this.passwordPanel.add((Component)this.passwordLabel, "North");
        this.passwordPanel.add((Component)this.passwordTextField, "North");
        this.soPasswordPanel.add((Component)this.soPasswordLabel, "North");
        this.soPasswordPanel.add((Component)this.soPasswordTextField, "North");
        this.newPasswordPanel.add((Component)this.newPasswordLabel, "North");
        this.newPasswordPanel.add((Component)this.newPasswordTextField, "North");
        this.confirmPasswordPanel.add((Component)this.confirmPasswordLabel, "North");
        this.confirmPasswordPanel.add((Component)this.confirmPasswordTextField, "North");
        if (passwordType == 2) {
            this.groupPasswordPanel.setLayout(new GridLayout(3, 1));
            this.groupPasswordPanel.add((Component)this.passwordPanel, (Object)null);
            this.groupPasswordPanel.add((Component)this.newPasswordPanel, (Object)null);
            this.groupPasswordPanel.add((Component)this.confirmPasswordPanel, (Object)null);
            this.mainPanel.add((Component)this.groupPasswordPanel, "Center");
        } else if (passwordType == 3) {
            this.groupPasswordPanel.setLayout(new GridLayout(2, 1));
            this.groupPasswordPanel.add((Component)this.newPasswordPanel, (Object)null);
            this.groupPasswordPanel.add((Component)this.confirmPasswordPanel, (Object)null);
            this.mainPanel.add((Component)this.groupPasswordPanel, "Center");
        } else if (passwordType == 0) {
            this.mainPanel.add((Component)this.passwordPanel, "Center");
        } else if (passwordType == 1) {
            this.mainPanel.add((Component)this.soPasswordPanel, "Center");
        }
        this.getContentPane().add((Component)this.backButtonsPanel, "South");
        this.backButtonsPanel.setBorder(new EmptyBorder(0, 0, 15, 0));
        this.backButtonsPanel.add((Component)this.buttonsPanel, (Object)null);
        this.buttonsPanel.add((Component)this.okButton, (Object)null);
        this.buttonsPanel.add((Component)this.cancelButton, (Object)null);
        this.setDefaultCloseOperation(0);
        this.messagePanel.add("North", this.messageLabel);
        JPanel iconPanel = new JPanel(new BorderLayout());
        iconPanel.setBorder(new EmptyBorder(15, 15, 0, 0));
        JLabel iconLabel = new JLabel();
        iconLabel.setIcon(this.icon);
        iconPanel.add("North", iconLabel);
        this.getContentPane().add("West", iconPanel);
        this.addListeners();
    }

    private void addListeners() {
        this.addWindowListener(new WindowAdapter(){

            @Override
            public void windowClosing(WindowEvent e) {
                GUIKeyStorePassword.this.doCancelOption();
            }
        });
        this.okButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                GUIKeyStorePassword.this.doOkOption();
            }
        });
        this.cancelButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                GUIKeyStorePassword.this.doCancelOption();
            }
        });
        this.passwordTextField.addKeyListener(new KeyAdapter(){

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    GUIKeyStorePassword.this.doOkOption();
                }
                if (e.getKeyCode() == 27) {
                    GUIKeyStorePassword.this.doCancelOption();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                GUIKeyStorePassword.this.checkOkOption();
            }
        });
        this.newPasswordTextField.addKeyListener(new KeyAdapter(){

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    GUIKeyStorePassword.this.doOkOption();
                }
                if (e.getKeyCode() == 27) {
                    GUIKeyStorePassword.this.doCancelOption();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                GUIKeyStorePassword.this.checkOkOption();
            }
        });
        this.confirmPasswordTextField.addKeyListener(new KeyAdapter(){

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    GUIKeyStorePassword.this.doOkOption();
                }
                if (e.getKeyCode() == 27) {
                    GUIKeyStorePassword.this.doCancelOption();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                GUIKeyStorePassword.this.checkOkOption();
            }
        });
        this.soPasswordTextField.addKeyListener(new KeyAdapter(){

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    GUIKeyStorePassword.this.doOkOption();
                }
                if (e.getKeyCode() == 27) {
                    GUIKeyStorePassword.this.doCancelOption();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                GUIKeyStorePassword.this.checkOkOption();
            }
        });
    }

    private void checkOkOption() {
        this.okOptionAllowed = this.passwordType == 2 ? this.passwordTextField.getPassword().length > 0 && this.newPasswordTextField.getPassword().length > 0 && this.confirmPasswordTextField.getPassword().length > 0 : (this.passwordType == 3 ? this.newPasswordTextField.getPassword().length > 0 && this.confirmPasswordTextField.getPassword().length > 0 : (this.passwordType == 1 ? this.soPasswordTextField.getPassword().length > 0 : this.passwordTextField.getPassword().length > 0));
        this.okButton.setEnabled(this.okOptionAllowed);
    }

    private void doOkOption() {
        if (this.okOptionAllowed) {
            this.fireOkOption();
        }
    }

    private void doCancelOption() {
        this.fireCancelOption();
    }

    public static void main(String[] args) {
        GUIKeyStorePassword.recoverPassword();
    }

    static {
        title = "Informe a Senha";
        message = "Informe a Senha";
    }

    public static interface GUIPasswordDialogListener {
        public void okOption(Object var1);

        public void cancelOption(Object var1);
    }

}

