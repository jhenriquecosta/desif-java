
package br.gov.pbh.desif.service.relatorios;

import br.gov.pbh.des.images.ImageFactory;
import br.gov.pbh.desif.model.pojo.DependenciaProtocoloAMI;
import br.gov.pbh.desif.model.pojo.SignatureProtocolo;
import br.gov.pbh.desif.model.registros.Data;
import br.gov.pbh.desif.ws.cliente.ProtocoloAMI;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.SingleValueConverter;
import com.thoughtworks.xstream.converters.basic.DateConverter;
import com.thoughtworks.xstream.io.HierarchicalStreamDriver;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
import java.awt.Image;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RelatorioProtocoloAMI {
    private ProtocoloAMI protocoloAMI;

    public RelatorioProtocoloAMI(ProtocoloAMI protocoloAMI) {
        this.protocoloAMI = protocoloAMI;
    }

    public RelatorioProtocoloAMI() {
    }

    public void gerarProtocolo(Object o, String tipoEntrada) throws Exception {
        ProtocoloAMI protAMI;
        HashMap<String, Object> parametros = new HashMap<>();
        Image logo = ImageFactory.getInstance().getImage("bandeiraGuia.PNG").getImage();
        XmlFriendlyReplacer replacer = new XmlFriendlyReplacer("ddd", "_");
        XStream xstream = new XStream((HierarchicalStreamDriver)new DomDriver("UTF-8", replacer));
        xstream.registerConverter((SingleValueConverter)new DateConverter("yyyy-MM-dd HH:mm:ss.SSS", new String[0]));
        xstream.setMode(1001);
        xstream.alias("Protocolo", ProtocoloAMI.class);
        xstream.alias("Dependencia", DependenciaProtocoloAMI.class);
        xstream.alias("Totalizacao", br.gov.pbh.desif.model.pojo.TotalizacaoProtocoloAMI.class);
        xstream.alias("Signature", SignatureProtocolo.class);
        xstream.alias("EnviarDeclaracaoResposta", List.class);
        xstream.alias("ProtocoloRetorno", List.class);
        xstream.useAttributeFor(ProtocoloAMI.class, "id");
        xstream.aliasField("id", ProtocoloAMI.class, "id");
        if (tipoEntrada.equals("caminho")) {
            ProtocoloUtil protUtil = new ProtocoloUtil();
            String caminho = (String)o;
            protUtil.verificarAssinaturaProtocolo(caminho);
            ArrayList retornoArquivo = (ArrayList)xstream.fromXML(protUtil.retiraPrefixoTagAssinatura(caminho));
            try {
                protAMI = (ProtocoloAMI)((ArrayList)retornoArquivo.get(0)).get(0);
            }
            catch (Exception e) {
                protAMI = (ProtocoloAMI)retornoArquivo.get(0);
            }
        } else {
            protAMI = (ProtocoloAMI)o;
        }
        if (protAMI.getRaizCnpj() != null) {
            parametros.put("raizCnpj", protAMI.getRaizCnpj() + " - ");
        } else {
            parametros.put("raizCnpj", "");
        }
        parametros.put("numeroProtocolo", (long)protAMI.getId());
        parametros.put("instituicao", protAMI.getNome());
        Data dt = new Data();
        String[] dtCompet = dt.separaData(protAMI.getAnoMesInicCmpe());
        String dtCompetConvertida = dtCompet[1] + "/" + dtCompet[0];
        parametros.put("competencia", dtCompetConvertida);
        parametros.put("tipoDeclaracao", protAMI.getTipoDecl().intValue());
        parametros.put("consolidacao", protAMI.getTipoCnso().intValue());
        String verTermoRef = protAMI.getVerTermoRef() == null || protAMI.getVerTermoRef().equalsIgnoreCase("") ? "N\u00e3o Informado" : protAMI.getVerTermoRef();
        parametros.put("versaoTermoRef", verTermoRef);
        String verValidador = protAMI.getVerValidador() == null || protAMI.getVerValidador() == 0.0 ? "N\u00e3o Informado" : protAMI.getVerValidador().toString();
        parametros.put("versaoValidador", verValidador);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        df.setLenient(false);
        parametros.put("msg", protAMI.getMensagem());
        parametros.put("prefeitura", "Prefeitura de Belo Horizonte");
        parametros.put("secretaria", "Secretaria Municipal de Finan\u00e7as");
        parametros.put("gerencia", "Gerencia de Tributos mobiliarios");
        parametros.put("dataEntrega", protAMI.getDataEntrega());
        parametros.put("pathDepend", "br/gov/pbh/desif/service/relatorios/DESIF_Dependecias.jasper");
        parametros.put("pathTotalizacao", "br/gov/pbh/desif/service/relatorios/DESIF_Totalizacao.jasper");
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(protAMI.getListaDependencia());
        JRBeanCollectionDataSource dataSource2 = new JRBeanCollectionDataSource(protAMI.getListaTotalizacao());
        parametros.put("listaDepend", (Object)dataSource);
        parametros.put("listaTotalizacao", (Object)dataSource2);
        parametros.put("imagem", logo);
        ArrayList<ProtocoloAMI> vos = new ArrayList<ProtocoloAMI>();
        vos.add(protAMI);
        Relatorio relatorio = new Relatorio(parametros, "br/gov/pbh/desif/service/relatorios/DESIF_protocolo_msg.jasper", vos);
        relatorio.gerarRelatorio();
    }
}

