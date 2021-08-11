/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  br.gov.pbh.des.images.ImageFactory
 *  com.thoughtworks.xstream.XStream
 *  com.thoughtworks.xstream.converters.SingleValueConverter
 *  com.thoughtworks.xstream.converters.basic.DateConverter
 *  com.thoughtworks.xstream.io.HierarchicalStreamDriver
 *  com.thoughtworks.xstream.io.xml.DomDriver
 *  com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer
 *  net.sf.jasperreports.engine.data.JRBeanCollectionDataSource
 */
package br.gov.pbh.desif.service.relatorios;

import br.gov.pbh.des.images.ImageFactory;
import br.gov.pbh.desif.model.pojo.DependenciaProtocoloAMIold;
import br.gov.pbh.desif.model.pojo.SignatureProtocolo;
import br.gov.pbh.desif.model.pojo.TotalizacaoProtocoloAMIold;
import br.gov.pbh.desif.model.registros.Data;
import br.gov.pbh.desif.service.relatorios.ProtocoloUtil;
import br.gov.pbh.desif.service.relatorios.Relatorio;
import br.gov.pbh.desif.ws.cliente.ProtocoloAMIold;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.SingleValueConverter;
import com.thoughtworks.xstream.converters.basic.DateConverter;
import com.thoughtworks.xstream.io.HierarchicalStreamDriver;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RelatorioProtocoloAMIold {
    private ProtocoloAMIold protocoloAMI;

    public RelatorioProtocoloAMIold(ProtocoloAMIold protocoloAMI) {
        this.protocoloAMI = protocoloAMI;
    }

    public RelatorioProtocoloAMIold() {
    }

    public void gerarProtocolo(Object o, String tipoEntrada) throws Exception {
        ProtocoloAMIold protAMI;
        HashMap<String, Object> parametros = new HashMap<String, Object>();
        Image logo = ImageFactory.getInstance().getImage("bandeiraGuia.PNG").getImage();
        XmlFriendlyReplacer replacer = new XmlFriendlyReplacer("ddd", "_");
        XStream xstream = new XStream((HierarchicalStreamDriver)new DomDriver("UTF-8", replacer));
        xstream.registerConverter((SingleValueConverter)new DateConverter("yyyy-MM-dd'T'HH:mm:ss", new String[0]));
        xstream.setMode(1001);
        xstream.alias("Protocolo", ProtocoloAMIold.class);
        xstream.alias("Dependencia", DependenciaProtocoloAMIold.class);
        xstream.alias("Totalizacao", TotalizacaoProtocoloAMIold.class);
        xstream.alias("Signature", SignatureProtocolo.class);
        xstream.alias("EnviarDeclaracaoResposta", List.class);
        xstream.alias("ProtocoloRetorno", List.class);
        xstream.useAttributeFor(ProtocoloAMIold.class, "id");
        xstream.aliasField("id", ProtocoloAMIold.class, "id");
        if (tipoEntrada.equals("caminho")) {
            String caminho = (String)o;
            File arquivo = new File(caminho);
            FileInputStream entrada = new FileInputStream(arquivo);
            ProtocoloUtil protUtil = new ProtocoloUtil();
            protUtil.verificarAssinaturaProtocolo(caminho);
            ArrayList retornoArquivo = (ArrayList)xstream.fromXML((InputStream)entrada);
            try {
                protAMI = (ProtocoloAMIold)((ArrayList)retornoArquivo.get(0)).get(0);
            }
            catch (Exception e) {
                protAMI = (ProtocoloAMIold)retornoArquivo.get(0);
            }
        } else {
            protAMI = (ProtocoloAMIold)o;
        }
        if (protAMI.getRaizCnpj() != null) {
            parametros.put("raizCnpj", protAMI.getRaizCnpj().toString() + " - ");
        } else {
            parametros.put("raizCnpj", "");
        }
        parametros.put("numeroProtocolo", (long)protAMI.getId());
        parametros.put("instituicao", protAMI.getNome().toString());
        Data dt = new Data();
        String[] dtCompet = dt.separaData(protAMI.getAno_Mes_Inic_Cmpe());
        String dtCompetConvertida = dtCompet[1] + "/" + dtCompet[0];
        parametros.put("competencia", dtCompetConvertida);
        parametros.put("tipoDeclaracao", protAMI.getTipo_Decl().intValue());
        parametros.put("consolidacao", protAMI.getTipo_Cnso().intValue());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        df.setLenient(false);
        parametros.put("dataEntrega", protAMI.getDataEntrega());
        parametros.put("pathDepend", "br/gov/pbh/desif/service/relatorios/DESIF_DependeciasOld.jasper");
        parametros.put("pathTotalizacao", "br/gov/pbh/desif/service/relatorios/DESIF_TotalizacaoOld.jasper");
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(protAMI.getListaDependencia());
        JRBeanCollectionDataSource dataSource2 = new JRBeanCollectionDataSource(protAMI.getListaTotalizacao());
        parametros.put("listaDepend", (Object)dataSource);
        parametros.put("listaTotalizacao", (Object)dataSource2);
        parametros.put("imagem", logo);
        ArrayList<ProtocoloAMIold> vos = new ArrayList<ProtocoloAMIold>();
        vos.add(protAMI);
        Relatorio relatorio = new Relatorio(parametros, "br/gov/pbh/desif/service/relatorios/DESIF_protocoloOld.jasper", vos);
        relatorio.gerarRelatorio();
    }
}

