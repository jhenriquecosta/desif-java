/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  br.gov.pbh.des.images.ImageFactory
 *  net.sf.jasperreports.engine.data.JRBeanCollectionDataSource
 */
package br.gov.pbh.desif.service.relatorios;

import br.gov.pbh.des.images.ImageFactory;
import br.gov.pbh.desif.model.pojo.base.AbstractSistemaErro;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RelatorioErros {
    private ErrosVO errosVO;

    public RelatorioErros(ErrosVO errosVO) {
        this.errosVO = errosVO;
    }

    public void gerarRelatorioErros() {
        TreeMap<String, String> legenda = new TreeMap<String, String>();
        Image logo = ImageFactory.getInstance().getImage("bandeiraGuia.PNG").getImage();
        List<AbstractSistemaErro> auxSistemaErros = this.errosVO.getSistemaErros();
        for (int i = 0; i < auxSistemaErros.size(); ++i) {
            AbstractSistemaErro aux = auxSistemaErros.get(i);
            legenda.put(aux.getErro().getId(), aux.getErro().getMensagem());
        }
        ArrayList<LegendaVO> listLegendaVOs = new ArrayList<>();
        for (String codErro : legenda.keySet()) {
            LegendaVO legendaVO = new LegendaVO();
            legendaVO.setCodErro(codErro);
            legendaVO.setDescricao((String)legenda.get(codErro));
            listLegendaVOs.add(legendaVO);
        }
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(this.errosVO.getSistemaErros());
        JRBeanCollectionDataSource dataSourceLegenda = new JRBeanCollectionDataSource(listLegendaVOs);
         Map parametros = new HashMap();
        parametros.put("pathErros", "br/gov/pbh/desif/service/relatorios/DESIF_Erros.jasper");
        parametros.put("pathLegenda", "br/gov/pbh/desif/service/relatorios/DESIF_Legenda.jasper");
        parametros.put("listaErros", dataSource);
        parametros.put("listaLegenda", dataSourceLegenda);
        ArrayList<ErrosVO> vos = new ArrayList<>();
        vos.add(this.errosVO);
        Relatorio relatorio = new Relatorio(parametros, "br/gov/pbh/desif/service/relatorios/DESIF_RelatErros.jasper", vos);
        relatorio.gerarRelatorio();
    }
}

