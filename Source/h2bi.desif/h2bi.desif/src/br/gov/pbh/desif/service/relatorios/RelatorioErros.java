
package br.gov.pbh.desif.service.relatorios;

import br.gov.pbh.des.images.ImageFactory;
import br.gov.pbh.desif.model.pojo.Erro;
import br.gov.pbh.desif.model.pojo.base.AbstractSistemaErro;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.swing.ImageIcon;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

// Referenced classes of package br.gov.pbh.desif.service.relatorios:
//            LegendaVO, Relatorio, ErrosVO

public class RelatorioErros
{

    private ErrosVO errosVO;

    public RelatorioErros(ErrosVO errosVO)
    {
        this.errosVO = errosVO;
    }

    public void gerarRelatorioErros()
    {
        Map legenda = new TreeMap();
        Image logo = ImageFactory.getInstance().getImage("bandeiraGuia.PNG").getImage();
        java.util.List auxSistemaErros = errosVO.getSistemaErros();
        for(int i = 0; i < auxSistemaErros.size(); i++)
        {
            AbstractSistemaErro aux = (AbstractSistemaErro)auxSistemaErros.get(i);
            legenda.put(aux.getErro().getId(), aux.getErro().getMensagem());
        }

        java.util.List listLegendaVOs = new ArrayList();
        LegendaVO legendaVO;
        for(Iterator iterator = legenda.keySet().iterator(); iterator.hasNext(); listLegendaVOs.add(legendaVO))
        {
            String codErro = (String)iterator.next();
            legendaVO = new LegendaVO();
            legendaVO.setCodErro(codErro);
            legendaVO.setDescricao((String)legenda.get(codErro));
        }

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(errosVO.getSistemaErros());
        JRBeanCollectionDataSource dataSourceLegenda = new JRBeanCollectionDataSource(listLegendaVOs);
        Map parametros = new HashMap();
        parametros.put("pathErros", "br/gov/pbh/desif/service/relatorios/DESIF_Erros.jasper");
        parametros.put("pathLegenda", "br/gov/pbh/desif/service/relatorios/DESIF_Legenda.jasper");
        parametros.put("listaErros", dataSource);
        parametros.put("listaLegenda", dataSourceLegenda);
        java.util.List vos = new ArrayList();
        vos.add(errosVO);
        Relatorio relatorio = new Relatorio(parametros, "br/gov/pbh/desif/service/relatorios/DESIF_RelatErros.jasper", vos);
        relatorio.gerarRelatorio();
    }
}
