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
import br.gov.pbh.desif.ws.xml.vo.tiposcomplexos.DependenciaVO;
import br.gov.pbh.desif.ws.xml.vo.tiposcomplexos.ProtocoloVO;
import br.gov.pbh.desif.ws.xml.vo.tiposcomplexos.TotalizacaoVO;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RelatorioProtocolo {
    private ProtocoloVO protocoloVO;

    public RelatorioProtocolo(ProtocoloVO protocoloVO) {
        this.protocoloVO = protocoloVO;
    }

    public void gerarProtocolo() {
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(this.protocoloVO.listaDependencia);
        JRBeanCollectionDataSource dataSource2 = new JRBeanCollectionDataSource(this.protocoloVO.listaTotalizacao);
        HashMap<String, Object> parametros = new HashMap<String, Object>();
        Image logo = ImageFactory.getInstance().getImage("bandeiraGuia.PNG").getImage();
        parametros.put("raizCnpj", this.protocoloVO.getRaizCnpj());
        parametros.put("numeroProtocolo", this.protocoloVO.getCodigo());
        parametros.put("instituicao", this.protocoloVO.getNome());
        parametros.put("competencia", this.protocoloVO.getAnoMesInicCmpe());
        parametros.put("tipoDeclaracao", this.protocoloVO.getTipoDecl());
        parametros.put("consolidacao", this.protocoloVO.getTipoCnso());
        parametros.put("dataEntrega", this.protocoloVO.getDatEntrega());
        parametros.put("pathDepend", "br/gov/pbh/desif/service/relatorios/DESIF_Dependecias.jasper");
        parametros.put("pathTotalizacao", "br/gov/pbh/desif/service/relatorios/DESIF_Totalizacao.jasper");
        parametros.put("listaDepend", (Object)dataSource);
        parametros.put("listaTotalizacao", (Object)dataSource2);
        parametros.put("imagem", logo);
        ArrayList<ProtocoloVO> vos = new ArrayList<ProtocoloVO>();
        vos.add(this.protocoloVO);
        Relatorio relatorio = new Relatorio(parametros, "br/gov/pbh/desif/service/relatorios/DESIF_protocolo.jasper", vos);
        relatorio.gerarRelatorio();
    }
}

