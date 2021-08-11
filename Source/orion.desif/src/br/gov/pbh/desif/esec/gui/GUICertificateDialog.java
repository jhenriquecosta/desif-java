package br.gov.pbh.desif.esec.gui;

import br.com.esec.sdk.device.CertificateAndKey;
import br.gov.pbh.desif.esec.util.Util;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import javax.swing.*;

// Referenced classes of package br.gov.pbh.desif.esec.gui:
//            NoSelectedCertificateException, GUICertificateSelection

public class GUICertificateDialog extends JDialog
{

    private static final long serialVersionUID = 1L;
    public static final int SIGNATURE_TYPE = 0;
    public static final int ENCRYPTION_TYPE = 1;
    public static int OK_COMMAND = 0;
    public static int CANCEL_COMMAND = 1;
    private int command;
    private GUICertificateSelection certSelection;
    private Window currentWindow;

    public GUICertificateDialog(CertificateAndKey certs[], Window parent)
    {
        super((JFrame)parent, true);
        System.out.println((new StringBuilder()).append("Parent: ").append(parent).toString());
        jbInit(certs);
    }

    public CertificateAndKey getSelectedCertificate()
        throws NoSelectedCertificateException
    {
        validate();
        pack();
        Util.centerOnScreen(this);
        validate();
        pack();
        setVisible(true);
        if(command == OK_COMMAND)
            return certSelection.getSelectedCertificate();
        else
            throw new NoSelectedCertificateException();
    }

    private void jbInit(CertificateAndKey certs[])
    {
        String title = "Selecione um certificado para assinatura";
        JPanel buttonPanel = new JPanel(new FlowLayout(1));
        JButton cancelButton = new JButton("Cancelar");
        JButton okButton = new JButton("OK");
        getContentPane().setLayout(new BorderLayout());
        setDefaultCloseOperation(0);
        setResizable(false);
        setTitle(title);
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
        certSelection = new GUICertificateSelection(certs);
        getContentPane().add(certSelection, "North");
        getContentPane().add(buttonPanel, "Center");
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
    }

    void okButton_actionPerformed(ActionEvent e)
    {
        command = OK_COMMAND;
        setVisible(false);
    }

    void cancelButton_actionPerformed(ActionEvent e)
    {
        command = CANCEL_COMMAND;
        setVisible(false);
    }

    public static void main(String args1[])
    {
    }

}