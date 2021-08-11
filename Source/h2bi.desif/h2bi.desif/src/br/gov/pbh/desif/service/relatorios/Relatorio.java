
package br.gov.pbh.desif.service.relatorios;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JDialog;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class Relatorio
{

    private Map parametros;
    private String pathArquivoJasper;
    private List registros;
    private java.awt.Frame componentParent;

    public Relatorio()
    {
        parametros = new HashMap();
    }

    public Relatorio(String arquivoJasper, List dados)
    {
        this();
        pathArquivoJasper = arquivoJasper;
        registros = dados;
    }

    public Relatorio(Map parametros, String arquivoJasper, List dados)
    {
        this.parametros = parametros;
        pathArquivoJasper = arquivoJasper;
        registros = dados;
    }

    public Relatorio(Map parametros, String arquivoJasper)
    {
        this.parametros = parametros;
        pathArquivoJasper = arquivoJasper;
    }

    public void gerarRelatorio()
    {
        if(registros != null && !registros.isEmpty())
            try
            {
                JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(registros);
                java.io.InputStream relatorio = getClass().getClassLoader().getResourceAsStream(pathArquivoJasper);
                net.sf.jasperreports.engine.JasperPrint impressao = JasperFillManager.fillReport(relatorio, parametros, dataSource);
                JasperViewer viewer = new JasperViewer(impressao, true);
                JDialog dialog = new JDialog(componentParent, true);
                dialog.setTitle("Relat\363rios");
                dialog.add(viewer.getContentPane());
                java.awt.Insets in = java.awt.Toolkit.getDefaultToolkit().getScreenInsets(dialog.getGraphicsConfiguration());
                java.awt.Dimension d = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
                int width = d.width - (in.left + in.top);
                int height = d.height - (in.top + in.bottom);
                dialog.setSize(width, height);
                dialog.setVisible(true);
            }
            catch(JRException ex)
            {
                ex.printStackTrace();
            }
    }

    public void gerarRelatorioSemDataSource()
    {
        try
        {
            net.sf.jasperreports.engine.JasperPrint impressao = JasperFillManager.fillReport(getClass().getClassLoader().getResourceAsStream(pathArquivoJasper), parametros);
            JasperViewer viewer = new JasperViewer(impressao, true);
            JDialog dialog = new JDialog(componentParent, true);
            dialog.setTitle("Relat\363rios");
            dialog.add(viewer.getContentPane());
            java.awt.Insets in = java.awt.Toolkit.getDefaultToolkit().getScreenInsets(dialog.getGraphicsConfiguration());
            java.awt.Dimension d = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
            int width = d.width - (in.left + in.top);
            int height = d.height - (in.top + in.bottom);
            dialog.setSize(width, height);
            dialog.setVisible(true);
        }
        catch(JRException ex)
        {
            ex.printStackTrace();
        }
    }

    public String getPathArquivoJasper()
    {
        return pathArquivoJasper;
    }

    public void setPathArquivoJasper(String pathArquivoJasper)
    {
        this.pathArquivoJasper = pathArquivoJasper;
    }

    public List getRegistros()
    {
        return registros;
    }

    public void setRegistros(List registros)
    {
        this.registros = registros;
    }

    public Map getParametros()
    {
        return parametros;
    }

    public void setParametros(Map parametros)
    {
        this.parametros = parametros;
    }
}
