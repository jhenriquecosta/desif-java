
package br.gov.pbh.desif.service.relatorios;

import br.gov.pbh.des.images.ImageFactory;
import br.gov.pbh.desif.model.pojo.SignatureProtocolo;
import br.gov.pbh.desif.model.registros.Data;
import br.gov.pbh.desif.ws.cliente.ProtocoloContabil;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DateConverter;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;

// Referenced classes of package br.gov.pbh.desif.service.relatorios:
//            ProtocoloUtil, Relatorio

public class RelatorioProtocoloContabil
{

    public RelatorioProtocoloContabil()
    {
    }

    public void gerarProtocolo(Object o, String tipoEntrada)
        throws Exception
    {
        Map parametros = new HashMap();
        java.awt.Image logo = ImageFactory.getInstance().getImage("bandeiraGuia.PNG").getImage();
        XmlFriendlyReplacer replacer = new XmlFriendlyReplacer("ddd", "_");
        XStream xstream = new XStream(new DomDriver("UTF-8", replacer));
        xstream.registerConverter(new DateConverter("yyyy-MM-dd HH:mm:ss.SSS", new String[0]));
        xstream.setMode(1001);
        xstream.alias("Protocolo", br.gov.pbh.desif.ws.cliente.ProtocoloContabil.class);
        xstream.alias("Signature", br.gov.pbh.desif.model.pojo.SignatureProtocolo.class);
        xstream.alias("ProtocoloRetorno", java.util.List.class);
        xstream.useAttributeFor(br.gov.pbh.desif.ws.cliente.ProtocoloContabil.class, "id");
        xstream.aliasField("id", br.gov.pbh.desif.ws.cliente.ProtocoloContabil.class, "id");
        File aux = null;
        ProtocoloContabil protocoloCont = new ProtocoloContabil();
        if(tipoEntrada.equals("caminho"))
        {
            ProtocoloUtil protUtil = new ProtocoloUtil();
            String caminho = (String)o;
            protUtil.verificarAssinaturaProtocolo(caminho);
            ArrayList retornoArquivo = (ArrayList)xstream.fromXML(protUtil.retiraPrefixoTagAssinatura(caminho));
            protocoloCont = (ProtocoloContabil)retornoArquivo.get(0);
        } else
        {
            protocoloCont = (ProtocoloContabil)o;
        }
        parametros.put("protocoloId", Long.valueOf(protocoloCont.getId().longValue()));
        parametros.put("IdContrib", (new StringBuilder()).append(protocoloCont.getRaizCnpj()).append(" - ").append(protocoloCont.getNome().toString()).toString());
        Data dt = new Data();
        String dtInic[] = dt.separaData(protocoloCont.getInicCompetDecl());
        String dtInicConvertida = (new StringBuilder()).append(dtInic[1].toString()).append("/").append(dtInic[0].toString()).toString();
        String dtFim[] = dt.separaData(protocoloCont.getFimCompetDecl());
        String dtFimConvertida = (new StringBuilder()).append(dtFim[1].toString()).append("/").append(dtFim[0].toString()).toString();
        parametros.put("periodoRefDeclaracao", (new StringBuilder()).append(dtInicConvertida).append(" a ").append(dtFimConvertida).toString());
        parametros.put("tipoDeclaracao", Integer.valueOf(protocoloCont.getTipoDeclaracao().intValue()));
        parametros.put("versaoTermoRef", protocoloCont.getVerTermoRef());
        parametros.put("versaoValidador", protocoloCont.getVerValidador());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        df.setLenient(false);
        parametros.put("dataEntrega", protocoloCont.getDataEntrega());
        parametros.put("numDepeInfo", protocoloCont.getNumDepeInfo().toString());
        parametros.put("numDepeInfoBalanc", protocoloCont.getNumDepeInfoBalanc().toString());
        parametros.put("numBalancInformados", protocoloCont.getNumBalancInformados().toString());
        parametros.put("numDepeInfoRateio", protocoloCont.getNumDepeInfoRateio().toString());
        parametros.put("numRateioInformados", protocoloCont.getNumRateioInformados().toString());
        parametros.put("imagem", logo);
        List vos = new ArrayList();
        vos.add(protocoloCont);
        Relatorio relatorio = new Relatorio(parametros, "br/gov/pbh/desif/service/relatorios/DESIF_ProtocoloContabil.jasper", vos);
        relatorio.gerarRelatorio();
    }
}
