
package br.gov.pbh.desif.service.relatorios;

import br.gov.pbh.des.images.ImageFactory;
import java.util.*;
import javax.swing.ImageIcon;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

// Referenced classes of package br.gov.pbh.desif.service.relatorios:
//            Relatorio

public class RelatorioErrosWS
{

    private List mensagens;

    public RelatorioErrosWS(List mensagens)
    {
        this.mensagens = mensagens;
    }

    public void gerarRelatorioErros()
    {
        java.awt.Image logo = ImageFactory.getInstance().getImage("bandeiraGuia.PNG").getImage();
        JRBeanCollectionDataSource dataSourceLegenda = new JRBeanCollectionDataSource(mensagens);
        Map parametros = new HashMap();
        parametros.put("pathLegenda", "br/gov/pbh/desif/service/relatorios/DESIF_LegendaWS.jasper");
        parametros.put("listaLegenda", dataSourceLegenda);
        parametros.put("imagem", logo);
        List vos = new ArrayList();
        vos.add(mensagens);
        Relatorio relatorio = new Relatorio(parametros, "br/gov/pbh/desif/service/relatorios/DESIF_RelatErrosWS.jasper", vos);
        relatorio.gerarRelatorio();
    }
}
