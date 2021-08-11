
package br.gov.pbh.desif.esec.gui;

import br.gov.pbh.desif.esec.images.LoadImages;
import br.gov.pbh.desif.esec.util.Util;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

// Referenced classes of package br.gov.pbh.desif.esec.gui:
//            TextLabel

public class GUIKeyStorePassword extends JDialog
{
    public static interface GUIPasswordDialogListener
    {
      public abstract void okOption(Object obj);
      public abstract void cancelOption(Object obj);
    }

    private static final long serialVersionUID = 1L;
    public static final int PIN_PASSWORD = 0;
    public static final int SO_PASSWORD = 1;
    public static final int CHANGE_PASSWORD = 2;
    public static final int SET_PASSWORD = 3;
    JPanel mainPanel;
    BorderLayout borderLayout1;
    JPanel messagePanel;
    JPanel passwordPanel;
    JPanel soPasswordPanel;
    JPanel newPasswordPanel;
    JPanel groupPasswordPanel;
    JPanel confirmPasswordPanel;
    JPanel backButtonsPanel;
    BorderLayout borderLayout2;
    FlowLayout flowLayout1;
    Border border1;
    JLabel passwordLabel;
    JLabel soPasswordLabel;
    JLabel newPasswordLabel;
    JLabel confirmPasswordLabel;
    JPasswordField passwordTextField;
    JPasswordField soPasswordTextField;
    JPasswordField newPasswordTextField;
    JPasswordField confirmPasswordTextField;
    Border border2;
    FlowLayout flowLayout2;
    JPanel buttonsPanel;
    GridLayout gridLayout1;
    JButton okButton;
    JButton cancelButton;
    Border border3;
    private boolean okOptionAllowed;
    private TextLabel messageLabel;
    private final Set passwordDialogListeners;
    private final Icon icon;
    private static Window parentComponent;
    private static boolean returnVisible;
    private static String title = "Informe a Senha";
    private static String message = "Informe a Senha";
    private int passwordType;

    public static void setMessage(String _message)
    {
        message = _message;
    }

    public static void setParentComponent(Window _parentComponent, boolean _returnVisible)
    {
        parentComponent = _parentComponent;
        returnVisible = _returnVisible;
    }

    public static void setGUITitle(String _title)
    {
        title = _title;
    }

    public GUIKeyStorePassword(Dialog _parentComponent, String _title, String _message, int _passwordType)
    {
        super(_parentComponent, _title, true);
        mainPanel = new JPanel();
        borderLayout1 = new BorderLayout();
        messagePanel = new JPanel();
        passwordPanel = new JPanel();
        soPasswordPanel = new JPanel();
        newPasswordPanel = new JPanel();
        groupPasswordPanel = new JPanel();
        confirmPasswordPanel = new JPanel();
        backButtonsPanel = new JPanel();
        borderLayout2 = new BorderLayout();
        flowLayout1 = new FlowLayout();
        passwordLabel = new JLabel();
        soPasswordLabel = new JLabel();
        newPasswordLabel = new JLabel();
        confirmPasswordLabel = new JLabel();
        passwordTextField = new JPasswordField();
        soPasswordTextField = new JPasswordField();
        newPasswordTextField = new JPasswordField();
        confirmPasswordTextField = new JPasswordField();
        flowLayout2 = new FlowLayout();
        buttonsPanel = new JPanel();
        gridLayout1 = new GridLayout();
        okButton = new JButton();
        cancelButton = new JButton();
        okOptionAllowed = false;
        passwordDialogListeners = new HashSet();
        icon = LoadImages.getImage("privateKey.png");
        init(_message, _passwordType);
    }

    public GUIKeyStorePassword(Frame _parentComponent, String _title, String _message, int _passwordType)
    {
        super(_parentComponent, _title, true);
        mainPanel = new JPanel();
        borderLayout1 = new BorderLayout();
        messagePanel = new JPanel();
        passwordPanel = new JPanel();
        soPasswordPanel = new JPanel();
        newPasswordPanel = new JPanel();
        groupPasswordPanel = new JPanel();
        confirmPasswordPanel = new JPanel();
        backButtonsPanel = new JPanel();
        borderLayout2 = new BorderLayout();
        flowLayout1 = new FlowLayout();
        passwordLabel = new JLabel();
        soPasswordLabel = new JLabel();
        newPasswordLabel = new JLabel();
        confirmPasswordLabel = new JLabel();
        passwordTextField = new JPasswordField();
        soPasswordTextField = new JPasswordField();
        newPasswordTextField = new JPasswordField();
        confirmPasswordTextField = new JPasswordField();
        flowLayout2 = new FlowLayout();
        buttonsPanel = new JPanel();
        gridLayout1 = new GridLayout();
        okButton = new JButton();
        cancelButton = new JButton();
        okOptionAllowed = false;
        passwordDialogListeners = new HashSet();
        icon = LoadImages.getImage("privateKey.png");
        init(_message, _passwordType);
    }

    public void addPasswordDialogListener(GUIPasswordDialogListener listener)
    {
        passwordDialogListeners.add(listener);
    }

    @SuppressWarnings("empty-statement")
    public void fireCancelOption()
    {
        passwordTextField.setText(null);
        newPasswordTextField.setText(null);
        confirmPasswordTextField.setText(null);
        for(Iterator i = passwordDialogListeners.iterator(); i.hasNext(); ((GUIPasswordDialogListener)i.next()).cancelOption(this));
    }

    @SuppressWarnings("empty-statement")
    public void fireOkOption()
    {
        if((passwordType == 2 || passwordType == 3) && !Arrays.equals(getNewPassword(), getConfirmPassword()))
        {
            JOptionPane.showMessageDialog(this, "Nova Senha diferente de Confirmacao", "Atencao", 0);
            return;
        }
        for(Iterator i = passwordDialogListeners.iterator(); 
            i.hasNext(); 
            ((GUIPasswordDialogListener)i.next()).okOption(this));
                
        if(parentComponent != null && returnVisible)
        {
            try
            {
                parentComponent.setVisible(true);
            }
            catch(Exception e)
            { }
        }
    }

    public char[] getPassword()
    {
        return passwordTextField.getPassword();
    }

    public char[] getNewPassword()
    {
        return newPasswordTextField.getPassword();
    }

    public char[] getSOPassword()
    {
        return soPasswordTextField.getPassword();
    }

    public char[] getConfirmPassword()
    {
        return confirmPasswordTextField.getPassword();
    }

    public static char[] recoverPassword()
    {
        ArrayList passList = recoverPassword(0);
        if(passList == null || passList.isEmpty())
            return null;
        else
            return (char[])(char[])passList.get(0);
    }

    public static ArrayList recoverPassword(int passwordType)
    {
        if(parentComponent != null)
            parentComponent.setVisible(false);
        GUIKeyStorePassword passwordDialog = null;
        if(passwordType == 1)
        {
            title = "Informe a Senha SO";
            message = "Informe a Senha SO";
        }
        if(passwordType == 2 || passwordType == 3)
        {
            title = "Informe a Nova Senha";
            message = "Informe a Nova Senha";
        } 
       
        
        if(parentComponent instanceof Dialog)
        {  
            passwordDialog = new GUIKeyStorePassword((Dialog)parentComponent, title, message, passwordType);
        }
        if(parentComponent instanceof Frame)
        {   
            passwordDialog = new GUIKeyStorePassword((Frame)parentComponent, title, message, passwordType);
        }
        else
        {
            passwordDialog = new GUIKeyStorePassword((Dialog)null, title, message, passwordType);
        }
            final GUIKeyStorePassword passwordDialog_ = passwordDialog;
            final JPasswordField passwordTextField_ = passwordDialog.passwordTextField;

         GUIPasswordDialogListener listener = new GUIPasswordDialogListener() 
        {
            public void okOption(Object source)
            {
                passwordDialog_.setVisible(false);
            }
            public void cancelOption(Object source)
            {
                passwordDialog_.setVisible(false);
            }
            public void windowOpened(WindowEvent e)
            {
                passwordTextField_.requestFocus();
            }
        };
        
        passwordDialog_.addPasswordDialogListener(listener);
        passwordDialog_.setVisible(true);
        char password[] = passwordDialog_.getPassword();
        char newPassword[] = passwordDialog_.getNewPassword();
        char confirmPassword[] = passwordDialog_.getConfirmPassword();
        char soPassword[] = passwordDialog_.getSOPassword();
        ArrayList passwords = new ArrayList();
        if(passwordType == 2)
        {
            if(password.length == 0 || confirmPassword.length == 0 || newPassword.length == 0)
            {
                return null;
            }
            passwords.add(password);
            passwords.add(newPassword);
            passwords.add(confirmPassword);
        } 
        if(passwordType == 3)
        {
            if(confirmPassword.length == 0 || newPassword.length == 0)
                return null;
            passwords.add(newPassword);
            passwords.add(confirmPassword);
        } 
        if(passwordType == 1)
        {
            if(soPassword.length == 0)
                return null;
            passwords.add(soPassword);
        } 
        else
        {
            if(password.length == 0)
                return null;
            passwords.add(password);
        }
        return passwords;
    }

    private void init(String _message, int _passwordType)
    {
        messageLabel = new TextLabel(_message, 60);
        jbInit(_passwordType);
        try
        {
            pack();
        }
        catch(NullPointerException e)
        {
            e.printStackTrace();
        }
        Util.centerOnScreen(this);
    }

    private void jbInit(int _passwordType)
    {
        this.passwordType = _passwordType;
        border1 = BorderFactory.createEmptyBorder(0, 10, 0, 0);
        border2 = BorderFactory.createEmptyBorder(0, 0, 0, 5);
        border3 = BorderFactory.createEmptyBorder(15, 15, 15, 15);
        Border border4 = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        mainPanel.setLayout(borderLayout1);
        messagePanel.setLayout(borderLayout2);
        flowLayout1.setAlignment(2);
        flowLayout1.setHgap(0);
        flowLayout1.setVgap(0);
        passwordPanel.setBorder(border4);
        soPasswordPanel.setBorder(border4);
        newPasswordPanel.setBorder(border4);
        confirmPasswordPanel.setBorder(border4);
        groupPasswordPanel.setBorder(border1);
        passwordLabel.setBorder(border2);
        passwordLabel.setText("Senha:");
        
        if(passwordType == 2)
        {
            passwordLabel.setText("Senha de Ativacao:");
            soPasswordLabel.setBorder(border2);
            soPasswordLabel.setText("Senha SO:");
            newPasswordLabel.setBorder(border2);
            newPasswordLabel.setText("Nova Senha:");
            confirmPasswordLabel.setBorder(border2);
            confirmPasswordLabel.setText("Confirmacao:");
            passwordTextField.setColumns(15);
            soPasswordTextField.setColumns(15);
            newPasswordTextField.setColumns(15);
            confirmPasswordTextField.setColumns(15);
            backButtonsPanel.setLayout(flowLayout2);
            flowLayout2.setHgap(0);
            flowLayout2.setVgap(0);
            buttonsPanel.setLayout(gridLayout1);
            gridLayout1.setColumns(2);
            gridLayout1.setHgap(5);
            okButton.setText("OK");
            okButton.setEnabled(false);
            cancelButton.setText("Cancelar");
            borderLayout1.setVgap(20);
            mainPanel.setBorder(border3);
            setResizable(false);
            setModal(true);
            getContentPane().add(mainPanel, "Center");
            mainPanel.add(messagePanel, "North");
            passwordPanel.setLayout(flowLayout1);
            soPasswordPanel.setLayout(flowLayout1);
            newPasswordPanel.setLayout(flowLayout1);
            confirmPasswordPanel.setLayout(flowLayout1);
            groupPasswordPanel.setLayout(flowLayout1);
            passwordPanel.add(passwordLabel, "North");
            passwordPanel.add(passwordTextField, "North");
            soPasswordPanel.add(soPasswordLabel, "North");
            soPasswordPanel.add(soPasswordTextField, "North");
            newPasswordPanel.add(newPasswordLabel, "North");
            newPasswordPanel.add(newPasswordTextField, "North");
            confirmPasswordPanel.add(confirmPasswordLabel, "North");
            confirmPasswordPanel.add(confirmPasswordTextField, "North");
            groupPasswordPanel.setLayout(new GridLayout(3, 1));
            groupPasswordPanel.add(passwordPanel, null);
            groupPasswordPanel.add(newPasswordPanel, null);
            groupPasswordPanel.add(confirmPasswordPanel, null);
            mainPanel.add(groupPasswordPanel, "Center");
        } 
        if(passwordType == 3)
        {
            groupPasswordPanel.setLayout(new GridLayout(2, 1));
            groupPasswordPanel.add(newPasswordPanel, null);
            groupPasswordPanel.add(confirmPasswordPanel, null);
            mainPanel.add(groupPasswordPanel, "Center");
        } 
        if (passwordType == 0)
        {
            mainPanel.add(passwordPanel, "Center");
        }
        if (passwordType == 1)
        {
            mainPanel.add(soPasswordPanel, "Center");
            getContentPane().add(backButtonsPanel, "South");
            backButtonsPanel.setBorder(new EmptyBorder(0, 0, 15, 0));
            backButtonsPanel.add(buttonsPanel, null);
            buttonsPanel.add(okButton, null);
            buttonsPanel.add(cancelButton, null);
            setDefaultCloseOperation(0);
            messagePanel.add("North", messageLabel);
            JPanel iconPanel = new JPanel(new BorderLayout());
            iconPanel.setBorder(new EmptyBorder(15, 15, 0, 0));
            JLabel iconLabel = new JLabel();
            iconLabel.setIcon(icon);
            iconPanel.add("North", iconLabel);
            getContentPane().add("West", iconPanel);
            addListeners();
        }
    }

    private void addListeners()
    {
        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e)
            {
                doCancelOption();
            }

        }
        );
        okButton.addActionListener(new ActionListener() {


            public void actionPerformed(ActionEvent e)
            {
                doOkOption();
            }

            
        }
);
        cancelButton.addActionListener(new ActionListener() {


            public void actionPerformed(ActionEvent e)
            {
                doCancelOption();
            }

            
        }
);
        passwordTextField.addKeyListener(new KeyAdapter() {


            public void keyPressed(KeyEvent e)
            {
                if(e.getKeyCode() == 10)
                {
                    doOkOption();
                }
                if(e.getKeyCode() == 27)
                {
                    doCancelOption();
                 }
            }

            public void keyReleased(KeyEvent e)
            {
                checkOkOption();
            }
        }
);
        newPasswordTextField.addKeyListener(new KeyAdapter() {


            public void keyPressed(KeyEvent e)
            {
                if(e.getKeyCode() == 10)
                {
                    doOkOption();
                }
                 if(e.getKeyCode() == 27)
                 {
                     doCancelOption();
                 }
            }

            public void keyReleased(KeyEvent e)
            {
                checkOkOption();
            }

            
        }
);
        confirmPasswordTextField.addKeyListener(new KeyAdapter() {


            public void keyPressed(KeyEvent e)
            {
                if(e.getKeyCode() == 10)
                {
                    doOkOption();
                }
                 if(e.getKeyCode() == 27)
                 {
                     doCancelOption();
                 }
            }

            public void keyReleased(KeyEvent e)
            {
                checkOkOption();
            }

        }
);
        soPasswordTextField.addKeyListener(new KeyAdapter() {


            public void keyPressed(KeyEvent e)
            {
                if(e.getKeyCode() == 10)
                {
                    doOkOption();
                }
                if(e.getKeyCode() == 27)
                {
                    doCancelOption();
                }
            }

            public void keyReleased(KeyEvent e)
            {
                checkOkOption();
            }

        }
);
    }

    private void checkOkOption()
    {
        if(passwordType == 2)
            okOptionAllowed = passwordTextField.getPassword().length > 0 && newPasswordTextField.getPassword().length > 0 && confirmPasswordTextField.getPassword().length > 0;
        
        else
        
        if(passwordType == 3)
        
            okOptionAllowed = newPasswordTextField.getPassword().length > 0 && confirmPasswordTextField.getPassword().length > 0;
        
        else
        
        if(passwordType == 1)
            okOptionAllowed = soPasswordTextField.getPassword().length > 0;
        
        else
            okOptionAllowed = passwordTextField.getPassword().length > 0;
            okButton.setEnabled(okOptionAllowed);
    }

    private void doOkOption()
    {
        if(okOptionAllowed)
        {
            fireOkOption();
        }
    }

    private void doCancelOption()
    {
        fireCancelOption();
    }

    public static void main(String args[])
    {
        recoverPassword();
    }




}