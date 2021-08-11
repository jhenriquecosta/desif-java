/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  br.com.esec.pkix.x509.X509CertificateImpl
 *  br.com.esec.pkix.x509.X509Principal
 *  br.com.esec.pkix.x509.X509Principal$NameComponent
 *  br.com.esec.sdk.certificate.ICPBrasilInformations
 *  br.com.esec.sdk.certificate.LegalPersonInformation
 *  br.com.esec.sdk.certificate.PersonInformations
 *  br.com.esec.sdk.device.CertificateAndKey
 */
package br.gov.pbh.desif.esec.gui;

import br.com.esec.pkix.x509.X509CertificateImpl;
import br.com.esec.pkix.x509.X509Principal;
import br.com.esec.sdk.certificate.ICPBrasilInformations;
import br.com.esec.sdk.certificate.LegalPersonInformation;
import br.com.esec.sdk.certificate.PersonInformations;
import br.com.esec.sdk.device.CertificateAndKey;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

class GUICertificateSelection
extends JPanel {
    public static final int SIGNATURE_TYPE = 0;
    public static final int ENCRYPTION_TYPE = 1;
    private static final long serialVersionUID = 1L;
    private CertificateAndKey[] certRefs;
    private JTextArea infoBox = new JTextArea();
    private X509Certificate[] certs;
    private CertificateTableModel tableModel;
    private JTable certTable = new JTable();
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public GUICertificateSelection(CertificateAndKey[] certRefs) {
        this.orderByName(certRefs);
        this.certRefs = certRefs;
        this.certs = new X509Certificate[certRefs.length];
        for (int i = certRefs.length - 1; i >= 0; --i) {
            this.certs[i] = certRefs[i].getCertificateChain()[0];
        }
        this.tableModel = new CertificateTableModel(this.certs);
        this.jbInit();
    }

    public CertificateAndKey getSelectedCertificate() throws NoSelectedCertificateException {
        int selectedRow = this.certTable.getSelectedRow();
        if (selectedRow < 0) {
            throw new NoSelectedCertificateException();
        }
        X509Certificate cert = this.tableModel.getCertificates()[selectedRow];
        if (cert == null) {
            throw new NoSelectedCertificateException();
        }
        for (int i = this.certs.length - 1; i >= 0; --i) {
            if (!this.certs[i].equals(cert)) continue;
            return this.certRefs[i];
        }
        throw new NoSelectedCertificateException();
    }

    private void jbInit() {
        this.certTable.setPreferredScrollableViewportSize(new Dimension(400, 100));
        this.certTable.setModel(this.tableModel);
        this.certTable.getTableHeader().setReorderingAllowed(false);
        ListSelectionModel selModel = this.certTable.getSelectionModel();
        selModel.setSelectionMode(0);
        ListSelectionModel colModel = this.certTable.getColumnModel().getSelectionModel();
        TableRowSelectionListener mudaCampo = new TableRowSelectionListener();
        this.certTable.setColumnSelectionInterval(0, 0);
        selModel.addListSelectionListener(mudaCampo);
        colModel.addListSelectionListener(mudaCampo);
        if (this.certTable.getRowCount() > 0) {
            this.certTable.setRowSelectionInterval(0, 0);
        }
        this.setLayout(new BorderLayout());
        JPanel painelCima = new JPanel();
        painelCima.setLayout(new BorderLayout());
        JPanel painelBaixo = new JPanel();
        painelBaixo.setLayout(new BorderLayout());
        painelCima.setBorder(new TitledBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10), "Lista de certificados:"));
        painelBaixo.setBorder(new TitledBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10), "Informacoes do certificado selecionado:"));
        this.infoBox.setWrapStyleWord(true);
        this.infoBox.setLineWrap(true);
        this.infoBox.setEditable(false);
        this.add((Component)painelCima, "North");
        JScrollPane scrollPaneCerts = new JScrollPane(this.certTable);
        painelCima.add((Component)scrollPaneCerts, "Center");
        this.add((Component)painelBaixo, "South");
        JScrollPane scrollPaneInfos = new JScrollPane(this.infoBox);
        painelBaixo.add((Component)scrollPaneInfos, "Center");
    }

    private void orderByName(CertificateAndKey[] certs) {
        if (certs != null && certs.length > 0) {
            for (int i = 0; i < certs.length - 1; ++i) {
                String certName1 = this.getName(certs[i]);
                for (int j = i + 1; j < certs.length; ++j) {
                    String certName2 = this.getName(certs[j]);
                    if (certName1 == null || certName2 == null || certName1.compareToIgnoreCase(certName2) < 0) continue;
                    CertificateAndKey aux = certs[i];
                    certs[i] = certs[j];
                    certs[j] = aux;
                    certName1 = certName2;
                }
            }
        }
    }

    private String getName(CertificateAndKey cert) {
        return this.getName(cert.getCertificateChain()[0].getSubject());
    }

    private String getName(X509Principal principal) {
        String cn = principal.getFirst(X509Principal.COMMON_NAME);
        if (cn != null) {
            return cn;
        }
        String email = principal.getFirst(X509Principal.EMAIL_ADDRESS);
        if (email != null) {
            return email;
        }
        String ou = principal.getFirst(X509Principal.ORGANIZATIONAL_UNIT_NAME);
        if (ou != null) {
            return ou;
        }
        String o = principal.getFirst(X509Principal.ORGANIZATION_NAME);
        if (o != null) {
            return o;
        }
        return "empty_name";
    }

    private String getCPF_CNPJ(X509Certificate cert) {
        try {
            ICPBrasilInformations icpInfo = ICPBrasilInformations.getICPBrasilInformations((X509CertificateImpl)new X509CertificateImpl((Certificate)cert));
            if (icpInfo != null) {
                if (icpInfo instanceof PersonInformations) {
                    PersonInformations pInfo = (PersonInformations)icpInfo;
                    return "\nCPF=" + GUICertificateSelection.formatCPF(pInfo.getCPF());
                }
                if (icpInfo instanceof LegalPersonInformation) {
                    LegalPersonInformation lpInfo = (LegalPersonInformation)icpInfo;
                    return "\nCNPJ=" + GUICertificateSelection.formatCNPJ(lpInfo.getCNPJ());
                }
            }
        }
        catch (Exception icpInfo) {
            // empty catch block
        }
        return "";
    }

    private static String formatCPF(String cpf) {
        if ((cpf = cpf.trim()).length() != 11) {
            return cpf;
        }
        String cnpjFormated = cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9, 11);
        return cnpjFormated;
    }

    private static String formatCNPJ(String cnpj) {
        if ((cnpj = cnpj.trim()).length() != 14) {
            return cnpj;
        }
        String cnpjFormated = cnpj.substring(0, 2) + "." + cnpj.substring(2, 5) + "." + cnpj.substring(5, 8) + "/" + cnpj.substring(8, 12) + "-" + cnpj.substring(12, 14);
        return cnpjFormated;
    }

    private class TableRowSelectionListener
    implements ListSelectionListener {
        private TableRowSelectionListener() {
        }

        @Override
        public void valueChanged(ListSelectionEvent e) {
            int selectedRow = GUICertificateSelection.this.certTable.getSelectedRow();
            int selectedColumn = GUICertificateSelection.this.certTable.getSelectedColumn();
            Object obj = GUICertificateSelection.this.tableModel.getCompleteValueAt(selectedRow, selectedColumn);
            if (obj == null) {
                obj = "";
            }
            GUICertificateSelection.this.infoBox.setText(obj.toString().replace(',', '\n'));
            GUICertificateSelection.this.infoBox.setSelectionStart(0);
            GUICertificateSelection.this.infoBox.setSelectionEnd(0);
        }
    }

    private class CertificateTableModel
    extends AbstractTableModel {
        private static final long serialVersionUID = 1L;
        private String CACertificate;
        private String userCertificate;
        private String notAfterDate;
        private String[] header;
        private X509Certificate[] certificates;

        public CertificateTableModel(X509Certificate[] certs) {
            this.header = new String[]{this.userCertificate, this.CACertificate, this.notAfterDate};
            this.CACertificate = "Certificado da AC";
            this.userCertificate = "Certificado do Usuario";
            this.notAfterDate = "Data de Vencimento";
            this.header[0] = this.userCertificate;
            this.header[1] = this.CACertificate;
            this.header[2] = this.notAfterDate;
            this.certificates = certs;
        }

        public X509Certificate[] getCertificates() {
            return this.certificates;
        }

        public X509CertificateImpl getCertificate(int selectedIndex) {
            return (X509CertificateImpl)this.certificates[selectedIndex];
        }

        @Override
        public int getColumnCount() {
            return this.header.length;
        }

        @Override
        public String getColumnName(int columnIndex) {
            return this.header[columnIndex];
        }

        public Object getCompleteValueAt(int rowIndex, int columnIndex) {
            String columnName = this.getColumnName(columnIndex);
            if (columnName.equals(this.userCertificate)) {
                return this.certificates[rowIndex].getSubjectDN().getName() + GUICertificateSelection.this.getCPF_CNPJ(this.certificates[rowIndex]);
            }
            if (columnName.equals(this.CACertificate)) {
                return this.certificates[rowIndex].getIssuerDN().getName();
            }
            if (columnName.equals(this.notAfterDate)) {
                return GUICertificateSelection.this.sdf.format(this.certificates[rowIndex].getNotAfter());
            }
            return "Erro";
        }

        @Override
        public int getRowCount() {
            return this.certificates.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            String columnName = this.getColumnName(columnIndex);
            if (columnName.equals(this.userCertificate)) {
                return GUICertificateSelection.this.getName(((X509CertificateImpl)this.certificates[rowIndex]).getSubject());
            }
            if (columnName.equals(this.CACertificate)) {
                return GUICertificateSelection.this.getName(((X509CertificateImpl)this.certificates[rowIndex]).getIssuer());
            }
            return GUICertificateSelection.this.sdf.format(this.certificates[rowIndex].getNotAfter());
        }
    }

}

