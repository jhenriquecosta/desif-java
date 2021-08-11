
package br.gov.pbh.desif.service.relatorios;

import br.gov.pbh.des.images.ImageFactory;
import br.gov.pbh.desif.model.pojo.DependenciaProtocoloAMIold;
import br.gov.pbh.desif.model.pojo.SignatureProtocolo;
import br.gov.pbh.desif.model.pojo.TotalizacaoProtocoloAMIold;
import br.gov.pbh.desif.model.registros.Data;
import br.gov.pbh.desif.ws.cliente.ProtocoloAMIold;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DateConverter;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

// Referenced classes of package br.gov.pbh.desif.service.relatorios:
//            Relatorio

public class RelatorioProtocoloAMIold
{

    private ProtocoloAMIold protocoloAMI;

    public RelatorioProtocoloAMIold(ProtocoloAMIold protocoloAMI)
    {
        this.protocoloAMI = protocoloAMI;
    }

    public RelatorioProtocoloAMIold()
    {
    }

    public void gerarProtocolo(Object o, String tipoEntrada)
        throws Exception
    {
        Map parametros = new HashMap();
        java.awt.Image logo = ImageFactory.getInstance().getImage("bandeiraGuia.PNG").getImage();
        XmlFriendlyReplacer replacer = new XmlFriendlyReplacer("ddd", "_");
        XStream xstream = new XStream(new DomDriver("UTF-8", replacer));
        xstream.registerConverter(new DateConverter("yyyy-MM-dd'T'HH:mm:ss", new String[0]));
        xstream.setMode(1001);
        xstream.alias("Protocolo", br.gov.pbh.desif.ws.cliente.ProtocoloAMIold.class);
        xstream.alias("Dependencia", br.gov.pbh.desif.model.pojo.DependenciaProtocoloAMIold.class);
        xstream.alias("Totalizacao", br.gov.pbh.desif.model.pojo.TotalizacaoProtocoloAMIold.class);
        xstream.alias("Signature", br.gov.pbh.desif.model.pojo.SignatureProtocolo.class);
        xstream.alias("EnviarDeclaracaoResposta", java.util.List.class);
        xstream.alias("ProtocoloRetorno", java.util.List.class);
        xstream.useAttributeFor(br.gov.pbh.desif.ws.cliente.ProtocoloAMIold.class, "id");
        xstream.aliasField("id", br.gov.pbh.desif.ws.cliente.ProtocoloAMIold.class, "id");
        ProtocoloAMIold protAMI;
        if(tipoEntrada.equals("caminho"))
        {
            String caminho = (String)o;
            File arquivo = new File(caminho);
            InputStream entrada = new FileInputStream(arquivo);
            ArrayList retornoArquivo = (ArrayList)xstream.fromXML(entrada);
            try
            {
                protAMI = (ProtocoloAMIold)((ArrayList)retornoArquivo.get(0)).get(0);
            }
            catch(Exception e)
            {
                protAMI = (ProtocoloAMIold)retornoArquivo.get(0);
            }
        } else
        {
            protAMI = (ProtocoloAMIold)o;
        }
        if(protAMI.getRaizCnpj() != null)
            parametros.put("raizCnpj", (new StringBuilder()).append(protAMI.getRaizCnpj().toString()).append(" - ").toString());
        else
            parametros.put("raizCnpj", "");
        parametros.put("numeroProtocolo", Long.valueOf(protAMI.getId().longValue()));
        parametros.put("instituicao", protAMI.getNome().toString());
        Data dt = new Data();
        String dtCompet[] = dt.separaData(protAMI.getAno_Mes_Inic_Cmpe());
        String dtCompetConvertida = (new StringBuilder()).append(dtCompet[1]).append("/").append(dtCompet[0]).toString();
        parametros.put("competencia", dtCompetConvertida);
        parametros.put("tipoDeclaracao", Integer.valueOf(protAMI.getTipo_Decl().intValue()));
        parametros.put("consolidacao", Integer.valueOf(protAMI.getTipo_Cnso().intValue()));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        df.setLenient(false);
        parametros.put("dataEntrega", protAMI.getDataEntrega());
        parametros.put("pathDepend", "br/gov/pbh/desif/service/relatorios/DESIF_DependeciasOld.jasper");
        parametros.put("pathTotalizacao", "br/gov/pbh/desif/service/relatorios/DESIF_TotalizacaoOld.jasper");
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(protAMI.getListaDependencia());
        JRBeanCollectionDataSource dataSource2 = new JRBeanCollectionDataSource(protAMI.getListaTotalizacao());
        parametros.put("listaDepend", dataSource);
        parametros.put("listaTotalizacao", dataSource2);
        parametros.put("imagem", logo);
        List vos = new ArrayList();
        vos.add(protAMI);
        Relatorio relatorio = new Relatorio(parametros, "br/gov/pbh/desif/service/relatorios/DESIF_protocoloOld.jasper", vos);
        relatorio.gerarRelatorio();
    }
}
