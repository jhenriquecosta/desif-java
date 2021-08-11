
package br.gov.pbh.desif.service.relatorios;

import br.gov.pbh.des.images.ImageFactory;
import br.gov.pbh.desif.ws.xml.vo.tiposcomplexos.ProtocoloVO;
import java.util.*;
import javax.swing.ImageIcon;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

// Referenced classes of package br.gov.pbh.desif.service.relatorios:
//            Relatorio

public class RelatorioProtocolo
{

    private ProtocoloVO protocoloVO;

    public RelatorioProtocolo(ProtocoloVO protocoloVO)
    {
        this.protocoloVO = protocoloVO;
    }

    public void gerarProtocolo()
    {
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(protocoloVO.listaDependencia);
        JRBeanCollectionDataSource dataSource2 = new JRBeanCollectionDataSource(protocoloVO.listaTotalizacao);
        Map parametros = new HashMap();
        java.awt.Image logo = ImageFactory.getInstance().getImage("bandeiraGuia.PNG").getImage();
        parametros.put("raizCnpj", protocoloVO.getRaizCnpj());
        parametros.put("numeroProtocolo", protocoloVO.getCodigo());
        parametros.put("instituicao", protocoloVO.getNome());
        parametros.put("competencia", protocoloVO.getAnoMesInicCmpe());
        parametros.put("tipoDeclaracao", protocoloVO.getTipoDecl());
        parametros.put("consolidacao", protocoloVO.getTipoCnso());
        parametros.put("dataEntrega", protocoloVO.getDatEntrega());
        parametros.put("pathDepend", "br/gov/pbh/desif/service/relatorios/DESIF_Dependecias.jasper");
        parametros.put("pathTotalizacao", "br/gov/pbh/desif/service/relatorios/DESIF_Totalizacao.jasper");
        parametros.put("listaDepend", dataSource);
        parametros.put("listaTotalizacao", dataSource2);
        parametros.put("imagem", logo);
        List vos = new ArrayList();
        vos.add(protocoloVO);
        Relatorio relatorio = new Relatorio(parametros, "br/gov/pbh/desif/service/relatorios/DESIF_protocolo.jasper", vos);
        relatorio.gerarRelatorio();
    }
}
