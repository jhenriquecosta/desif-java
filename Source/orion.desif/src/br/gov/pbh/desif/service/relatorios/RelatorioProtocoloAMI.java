package br.gov.pbh.desif.service.relatorios;

import br.gov.pbh.des.images.ImageFactory;
import br.gov.pbh.desif.model.pojo.DependenciaProtocoloAMI;
import br.gov.pbh.desif.model.pojo.SignatureProtocolo;
import br.gov.pbh.desif.model.pojo.TotalizacaoProtocoloAMI;
import br.gov.pbh.desif.model.registros.Data;
import br.gov.pbh.desif.ws.cliente.ProtocoloAMI;
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
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

// Referenced classes of package br.gov.pbh.desif.service.relatorios:
//            ProtocoloUtil, Relatorio

public class RelatorioProtocoloAMI
{

    private ProtocoloAMI protocoloAMI;

    public RelatorioProtocoloAMI(ProtocoloAMI protocoloAMI)
    {
        this.protocoloAMI = protocoloAMI;
    }

    public RelatorioProtocoloAMI()
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
        xstream.alias("Protocolo", br.gov.pbh.desif.ws.cliente.ProtocoloAMI.class);
        xstream.alias("Dependencia", br.gov.pbh.desif.model.pojo.DependenciaProtocoloAMI.class);
        xstream.alias("Totalizacao", br.gov.pbh.desif.model.pojo.TotalizacaoProtocoloAMI.class);
        xstream.alias("Signature", br.gov.pbh.desif.model.pojo.SignatureProtocolo.class);
        xstream.alias("EnviarDeclaracaoResposta", java.util.List.class);
        xstream.alias("ProtocoloRetorno", java.util.List.class);
        xstream.useAttributeFor(br.gov.pbh.desif.ws.cliente.ProtocoloAMI.class, "id");
        xstream.aliasField("id", br.gov.pbh.desif.ws.cliente.ProtocoloAMI.class, "id");
        File aux = null;
        ProtocoloAMI protAMI;
        if(tipoEntrada.equals("caminho"))
        {
            ProtocoloUtil protUtil = new ProtocoloUtil();
            String caminho = (String)o;
            protUtil.verificarAssinaturaProtocolo(caminho);
            ArrayList retornoArquivo = (ArrayList)xstream.fromXML(protUtil.retiraPrefixoTagAssinatura(caminho));
            try
            {
                protAMI = (ProtocoloAMI)((ArrayList)retornoArquivo.get(0)).get(0);
            }
            catch(Exception e)
            {
                protAMI = (ProtocoloAMI)retornoArquivo.get(0);
            }
        } else
        {
            protAMI = (ProtocoloAMI)o;
        }
        if(protAMI.getRaizCnpj() != null)
            parametros.put("raizCnpj", (new StringBuilder()).append(protAMI.getRaizCnpj().toString()).append(" - ").toString());
        else
            parametros.put("raizCnpj", "");
        parametros.put("numeroProtocolo", Long.valueOf(protAMI.getId().longValue()));
        parametros.put("instituicao", protAMI.getNome().toString());
        Data dt = new Data();
        String dtCompet[] = dt.separaData(protAMI.getAnoMesInicCmpe());
        String dtCompetConvertida = (new StringBuilder()).append(dtCompet[1]).append("/").append(dtCompet[0]).toString();
        parametros.put("competencia", dtCompetConvertida);
        parametros.put("tipoDeclaracao", Integer.valueOf(protAMI.getTipoDecl().intValue()));
        parametros.put("consolidacao", Integer.valueOf(protAMI.getTipoCnso().intValue()));
        String verTermoRef;
        if(protAMI.getVerTermoRef() == null || protAMI.getVerTermoRef().equalsIgnoreCase(""))
            verTermoRef = "N\343o Informado";
        else
            verTermoRef = protAMI.getVerTermoRef().toString();
        parametros.put("versaoTermoRef", verTermoRef);
        String verValidador;
        if(protAMI.getVerValidador() == null || protAMI.getVerValidador().doubleValue() == 0.0D)
            verValidador = "N\343o Informado";
        else
            verValidador = protAMI.getVerValidador().toString();
        parametros.put("versaoValidador", verValidador);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        df.setLenient(false);
        parametros.put("dataEntrega", protAMI.getDataEntrega());
        parametros.put("pathDepend", "br/gov/pbh/desif/service/relatorios/DESIF_Dependecias.jasper");
        parametros.put("pathTotalizacao", "br/gov/pbh/desif/service/relatorios/DESIF_Totalizacao.jasper");
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(protAMI.getListaDependencia());
        JRBeanCollectionDataSource dataSource2 = new JRBeanCollectionDataSource(protAMI.getListaTotalizacao());
        parametros.put("listaDepend", dataSource);
        parametros.put("listaTotalizacao", dataSource2);
        parametros.put("imagem", logo);
        List vos = new ArrayList();
        vos.add(protAMI);
        Relatorio relatorio = new Relatorio(parametros, "br/gov/pbh/desif/service/relatorios/DESIF_protocolo.jasper", vos);
        relatorio.gerarRelatorio();
    }
}
