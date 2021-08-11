/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  br.gov.pbh.des.images.ImageFactory
 *  net.sf.jasperreports.engine.data.JRBeanCollectionDataSource
 */
package br.gov.pbh.desif.service.relatorios;

import br.gov.pbh.des.images.ImageFactory;
import br.gov.pbh.desif.service.relatorios.Relatorio;
import br.gov.pbh.desif.ws.cliente.Erros;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RelatorioErrosWS {
    private List<Erros> mensagens;

    public RelatorioErrosWS(List<Erros> mensagens) {
        this.mensagens = mensagens;
    }

    public void gerarRelatorioErros() {
        Image logo = ImageFactory.getInstance().getImage("bandeiraGuia.PNG").getImage();
        JRBeanCollectionDataSource dataSourceLegenda = new JRBeanCollectionDataSource(this.mensagens);
        HashMap<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pathLegenda", "br/gov/pbh/desif/service/relatorios/DESIF_LegendaWS.jasper");
        parametros.put("listaLegenda", (Object)dataSourceLegenda);
        parametros.put("imagem", logo);
        ArrayList<List<Erros>> vos = new ArrayList<List<Erros>>();
        vos.add(this.mensagens);
        Relatorio relatorio = new Relatorio(parametros, "br/gov/pbh/desif/service/relatorios/DESIF_RelatErrosWS.jasper", vos);
        relatorio.gerarRelatorio();
    }
}

