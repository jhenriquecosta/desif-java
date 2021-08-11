
package br.gov.pbh.desif.service.relatorios;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.Toolkit;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JDialog;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class Relatorio {
    private Map parametros;
    private String pathArquivoJasper;
    private List registros;
    private Frame componentParent;

    public Relatorio() {
        this.parametros = new HashMap();
    }

    public Relatorio(String arquivoJasper, List dados) {
        this();
        this.pathArquivoJasper = arquivoJasper;
        this.registros = dados;
    }

    public Relatorio(Map parametros, String arquivoJasper, List dados) {
        this.parametros = parametros;
        this.pathArquivoJasper = arquivoJasper;
        this.registros = dados;
    }

    public Relatorio(Map parametros, String arquivoJasper) {
        this.parametros = parametros;
        this.pathArquivoJasper = arquivoJasper;
    }

    public void gerarRelatorio() {
        if (this.registros != null && !this.registros.isEmpty()) {
            try {
                JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource((Collection)this.registros);
                InputStream relatorio = this.getClass().getClassLoader().getResourceAsStream(this.pathArquivoJasper);
                JasperPrint impressao = JasperFillManager.fillReport((InputStream)relatorio, (Map)this.parametros, (JRDataSource)dataSource);
                JasperViewer viewer = new JasperViewer(impressao, true);
                JDialog dialog = new JDialog(this.componentParent, true);
                dialog.setTitle("Relat\u00f3rios");
                dialog.add(viewer.getContentPane());
                Insets in = Toolkit.getDefaultToolkit().getScreenInsets(dialog.getGraphicsConfiguration());
                Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
                int width = d.width - (in.left + in.top);
                int height = d.height - (in.top + in.bottom);
                dialog.setSize(width, height);
                dialog.setVisible(true);
            }
            catch (JRException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void gerarRelatorioSemDataSource() {
        try {
            JasperPrint impressao = JasperFillManager.fillReport((InputStream)this.getClass().getClassLoader().getResourceAsStream(this.pathArquivoJasper), (Map)this.parametros);
            JasperViewer viewer = new JasperViewer(impressao, true);
            JDialog dialog = new JDialog(this.componentParent, true);
            dialog.setTitle("Relat\u00f3rios");
            dialog.add(viewer.getContentPane());
            Insets in = Toolkit.getDefaultToolkit().getScreenInsets(dialog.getGraphicsConfiguration());
            Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
            int width = d.width - (in.left + in.top);
            int height = d.height - (in.top + in.bottom);
            dialog.setSize(width, height);
            dialog.setVisible(true);
        }
        catch (JRException ex) {
            ex.printStackTrace();
        }
    }

    public String getPathArquivoJasper() {
        return this.pathArquivoJasper;
    }

    public void setPathArquivoJasper(String pathArquivoJasper) {
        this.pathArquivoJasper = pathArquivoJasper;
    }

    public List getRegistros() {
        return this.registros;
    }

    public void setRegistros(List registros) {
        this.registros = registros;
    }

    public Map getParametros() {
        return this.parametros;
    }

    public void setParametros(Map parametros) {
        this.parametros = parametros;
    }
}

