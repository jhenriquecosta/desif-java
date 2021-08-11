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
 */
package br.gov.pbh.desif.service.relatorios;

import br.gov.pbh.des.images.ImageFactory;
import br.gov.pbh.desif.model.pojo.SignatureProtocolo;
import br.gov.pbh.desif.model.registros.Data;
import br.gov.pbh.desif.service.relatorios.ProtocoloUtil;
import br.gov.pbh.desif.service.relatorios.Relatorio;
import br.gov.pbh.desif.ws.cliente.ProtocoloContabil;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.SingleValueConverter;
import com.thoughtworks.xstream.converters.basic.DateConverter;
import com.thoughtworks.xstream.io.HierarchicalStreamDriver;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
import java.awt.Image;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;

public class RelatorioProtocoloContabil {
    public void gerarProtocolo(Object o, String tipoEntrada) throws Exception {
        HashMap<String, Object> parametros = new HashMap<String, Object>();
        Image logo = ImageFactory.getInstance().getImage("bandeiraGuia.PNG").getImage();
        XmlFriendlyReplacer replacer = new XmlFriendlyReplacer("ddd", "_");
        XStream xstream = new XStream((HierarchicalStreamDriver)new DomDriver("UTF-8", replacer));
        xstream.registerConverter((SingleValueConverter)new DateConverter("yyyy-MM-dd HH:mm:ss.SSS", new String[0]));
        xstream.setMode(1001);
        xstream.alias("Protocolo", ProtocoloContabil.class);
        xstream.alias("Signature", SignatureProtocolo.class);
        xstream.alias("ProtocoloRetorno", List.class);
        xstream.useAttributeFor(ProtocoloContabil.class, "id");
        xstream.aliasField("id", ProtocoloContabil.class, "id");
        Object aux = null;
        ProtocoloContabil protocoloCont = new ProtocoloContabil();
        if (tipoEntrada.equals("caminho")) {
            ProtocoloUtil protUtil = new ProtocoloUtil();
            String caminho = (String)o;
            protUtil.verificarAssinaturaProtocolo(caminho);
            ArrayList retornoArquivo = (ArrayList)xstream.fromXML(protUtil.retiraPrefixoTagAssinatura(caminho));
            protocoloCont = (ProtocoloContabil)retornoArquivo.get(0);
        } else {
            protocoloCont = (ProtocoloContabil)o;
        }
        parametros.put("protocoloId", (long)protocoloCont.getId());
        parametros.put("IdContrib", protocoloCont.getRaizCnpj() + " - " + protocoloCont.getNome().toString());
        Data dt = new Data();
        String[] dtInic = dt.separaData(protocoloCont.getInicCompetDecl());
        String dtInicConvertida = dtInic[1].toString() + "/" + dtInic[0].toString();
        String[] dtFim = dt.separaData(protocoloCont.getFimCompetDecl());
        String dtFimConvertida = dtFim[1].toString() + "/" + dtFim[0].toString();
        parametros.put("periodoRefDeclaracao", dtInicConvertida + " a " + dtFimConvertida);
        parametros.put("tipoDeclaracao", protocoloCont.getTipoDeclaracao().intValue());
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
        ArrayList<ProtocoloContabil> vos = new ArrayList<ProtocoloContabil>();
        vos.add(protocoloCont);
        Relatorio relatorio = new Relatorio(parametros, "br/gov/pbh/desif/service/relatorios/DESIF_ProtocoloContabil.jasper", vos);
        relatorio.gerarRelatorio();
    }
}

