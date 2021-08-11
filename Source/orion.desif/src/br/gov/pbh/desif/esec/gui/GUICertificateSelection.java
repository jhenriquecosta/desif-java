
package br.gov.pbh.desif.esec.gui;

import br.com.esec.pkix.x509.X509CertificateImpl;
import br.com.esec.pkix.x509.X509Exception;
import br.com.esec.pkix.x509.X509Principal;
import br.com.esec.sdk.certificate.*;
import br.com.esec.sdk.device.CertificateAndKey;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.security.Principal;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;

// Referenced classes of package br.gov.pbh.desif.esec.gui:
//            NoSelectedCertificateException

class GUICertificateSelection extends JPanel
{
    private class TableRowSelectionListener implements ListSelectionListener
    {

        public void valueChanged(ListSelectionEvent e)
        {
            int selectedRow = certTable.getSelectedRow();
            int selectedColumn = certTable.getSelectedColumn();
            Object obj = tableModel.getCompleteValueAt(selectedRow, selectedColumn);
            if(obj == null)
                obj = "";
            infoBox.setText(obj.toString().replace(',', '\n'));
            infoBox.setSelectionStart(0);
            infoBox.setSelectionEnd(0);
        }

        private TableRowSelectionListener()
        {
            super();
        }
    }

    private class CertificateTableModel extends AbstractTableModel
    {

        private static final long serialVersionUID = 1L;
        private String CACertificate = null;
        private String userCertificate = null;
        private String notAfterDate = null;
        private String header[] = null;
        private X509Certificate certificates[] = null;
        
        public X509Certificate[] getCertificates()
        {
            return certificates;
        }

        public X509CertificateImpl getCertificate(int selectedIndex)
        {
            return (X509CertificateImpl)certificates[selectedIndex];
        }

        public int getColumnCount()
        {
            return header.length;
        }

        public String getColumnName(int columnIndex)
        {
            return header[columnIndex];
        }

        public Object getCompleteValueAt(int rowIndex, int columnIndex)
        {
            String columnName = getColumnName(columnIndex);
            if(columnName.equals(userCertificate))
                return (new StringBuilder()).append(certificates[rowIndex].getSubjectDN().getName()).append(getCPF_CNPJ(certificates[rowIndex])).toString();
            if(columnName.equals(CACertificate))
                return certificates[rowIndex].getIssuerDN().getName();
            if(columnName.equals(notAfterDate))
                return sdf.format(certificates[rowIndex].getNotAfter());
            else
                return "Erro";
        }

        public int getRowCount()
        {
            return certificates.length;
        }

        public Object getValueAt(int rowIndex, int columnIndex)
        {
            String columnName = getColumnName(columnIndex);
            if(columnName.equals(userCertificate))
                return getName(((X509CertificateImpl)certificates[rowIndex]).getSubject());
            if(columnName.equals(CACertificate))
                return getName(((X509CertificateImpl)certificates[rowIndex]).getIssuer());
            else
                return sdf.format(certificates[rowIndex].getNotAfter());
        }

        public CertificateTableModel(X509Certificate certs[])
        {
         
            super();
            header = (new String[] {
                userCertificate, CACertificate, notAfterDate
            });
            CACertificate = "Certificado da AC";
            userCertificate = "Certificado do Usuario";
            notAfterDate = "Data de Vencimento";
            header[0] = userCertificate;
            header[1] = CACertificate;
            header[2] = notAfterDate;
            certificates = certs;
        }
    }


    public static final int SIGNATURE_TYPE = 0;
    public static final int ENCRYPTION_TYPE = 1;
    private static final long serialVersionUID = 1L;
    private CertificateAndKey certRefs[] = null;
    private JTextArea infoBox = null;
    private X509Certificate certs[] = null;
    private CertificateTableModel tableModel = null;
    private JTable certTable = null;
    private SimpleDateFormat sdf = null;

    public GUICertificateSelection(CertificateAndKey certRefs[])
    {
        infoBox = new JTextArea();
        certTable = new JTable();
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        orderByName(certRefs);
        this.certRefs = certRefs;
        certs = new X509Certificate[certRefs.length];
        for(int i = certRefs.length - 1; i >= 0; i--)
            certs[i] = certRefs[i].getCertificateChain()[0];

        tableModel = new CertificateTableModel(certs);
        jbInit();
    }

    public CertificateAndKey getSelectedCertificate()
        throws NoSelectedCertificateException
    {
        int selectedRow = certTable.getSelectedRow();
        if(selectedRow < 0)
            throw new NoSelectedCertificateException();
        X509Certificate cert = tableModel.getCertificates()[selectedRow];
        if(cert == null)
            throw new NoSelectedCertificateException();
        for(int i = certs.length - 1; i >= 0; i--)
            if(certs[i].equals(cert))
                return certRefs[i];

        throw new NoSelectedCertificateException();
    }

    private void jbInit()
    {
        certTable.setPreferredScrollableViewportSize(new Dimension(400, 100));
        certTable.setModel(tableModel);
        certTable.getTableHeader().setReorderingAllowed(false);
        ListSelectionModel selModel = certTable.getSelectionModel();
        selModel.setSelectionMode(0);
        ListSelectionModel colModel = certTable.getColumnModel().getSelectionModel();
        ListSelectionListener mudaCampo = new TableRowSelectionListener();
        certTable.setColumnSelectionInterval(0, 0);
        selModel.addListSelectionListener(mudaCampo);
        colModel.addListSelectionListener(mudaCampo);
        if(certTable.getRowCount() > 0)
            certTable.setRowSelectionInterval(0, 0);
        setLayout(new BorderLayout());
        JPanel painelCima = new JPanel();
        painelCima.setLayout(new BorderLayout());
        JPanel painelBaixo = new JPanel();
        painelBaixo.setLayout(new BorderLayout());
        painelCima.setBorder(new TitledBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10), "Lista de certificados:"));
        painelBaixo.setBorder(new TitledBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10), "Informacoes do certificado selecionado:"));
        infoBox.setWrapStyleWord(true);
        infoBox.setLineWrap(true);
        infoBox.setEditable(false);
        add(painelCima, "North");
        JScrollPane scrollPaneCerts = new JScrollPane(certTable);
        painelCima.add(scrollPaneCerts, "Center");
        add(painelBaixo, "South");
        JScrollPane scrollPaneInfos = new JScrollPane(infoBox);
        painelBaixo.add(scrollPaneInfos, "Center");
    }

    private void orderByName(CertificateAndKey certs[])
    {
        if(certs != null && certs.length > 0)
        {
            for(int i = 0; i < certs.length - 1; i++)
            {
                String certName1 = getName(certs[i]);
                for(int j = i + 1; j < certs.length; j++)
                {
                    String certName2 = getName(certs[j]);
                    if(certName1 != null && certName2 != null && certName1.compareToIgnoreCase(certName2) >= 0)
                    {
                        CertificateAndKey aux = certs[i];
                        certs[i] = certs[j];
                        certs[j] = aux;
                        certName1 = certName2;
                    }
                }

            }

        }
    }

    private String getName(CertificateAndKey cert)
    {
        return getName(cert.getCertificateChain()[0].getSubject());
    }

    private String getName(X509Principal principal)
    {
        String cn = principal.getFirst(X509Principal.COMMON_NAME);
        if(cn != null)
            return cn;
        String email = principal.getFirst(X509Principal.EMAIL_ADDRESS);
        if(email != null)
            return email;
        String ou = principal.getFirst(X509Principal.ORGANIZATIONAL_UNIT_NAME);
        if(ou != null)
            return ou;
        String o = principal.getFirst(X509Principal.ORGANIZATION_NAME);
        if(o != null)
            return o;
        else
            return "empty_name";
    }

    private String getCPF_CNPJ(X509Certificate cert) 
    {
        try
        {
            ICPBrasilInformations icpInfo;
            icpInfo = ICPBrasilInformations.getICPBrasilInformations(new X509CertificateImpl(cert));
        if(icpInfo == null)
        {
           return "";
        }
        if(icpInfo instanceof PersonInformations)
        {
            PersonInformations pInfo = (PersonInformations)icpInfo;
            return (new StringBuilder()).append("\nCPF=").append(formatCPF(pInfo.getCPF())).toString();
        }
        if(icpInfo instanceof LegalPersonInformation)
            {
                LegalPersonInformation lpInfo = (LegalPersonInformation)icpInfo;
                return (new StringBuilder()).append("\nCNPJ=").append(formatCNPJ(lpInfo.getCNPJ())).toString();
            }
        }
        catch(X509Exception e) 
            {}
        return "";
    }

    private static String formatCPF(String cpf)
    {
        cpf = cpf.trim();
        if(cpf.length() != 11)
        {
            return cpf;
        } else
        {
            String cnpjFormated = (new StringBuilder()).append(cpf.substring(0, 3)).append(".").append(cpf.substring(3, 6)).append(".").append(cpf.substring(6, 9)).append("-").append(cpf.substring(9, 11)).toString();
            return cnpjFormated;
        }
    }

    private static String formatCNPJ(String cnpj)
    {
        cnpj = cnpj.trim();
        if(cnpj.length() != 14)
        {
            return cnpj;
        } else
        {
            String cnpjFormated = (new StringBuilder()).append(cnpj.substring(0, 2)).append(".").append(cnpj.substring(2, 5)).append(".").append(cnpj.substring(5, 8)).append("/").append(cnpj.substring(8, 12)).append("-").append(cnpj.substring(12, 14)).toString();
            return cnpjFormated;
        }
    }






}
