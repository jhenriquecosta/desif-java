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
import br.gov.pbh.desif.ws.cliente.ProtocoloICM;
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

public class RelatorioProtocoloICM {
    public void gerarProtocolo(Object o, String tipoEntrada) throws Exception {
        try {
            ProtocoloICM protocoloICM;
            HashMap<String, Object> parametros = new HashMap<String, Object>();
            Image logo = ImageFactory.getInstance().getImage("bandeiraGuia.PNG").getImage();
            XmlFriendlyReplacer replacer = new XmlFriendlyReplacer("ddd", "_");
            XStream xstream = new XStream((HierarchicalStreamDriver)new DomDriver("UTF-8", replacer));
            xstream.registerConverter((SingleValueConverter)new DateConverter("yyyy-MM-dd HH:mm:ss.SSS", new String[0]));
            xstream.setMode(1001);
            xstream.alias("Protocolo", ProtocoloICM.class);
            xstream.alias("Signature", SignatureProtocolo.class);
            xstream.alias("ProtocoloRetorno", List.class);
            xstream.useAttributeFor(ProtocoloICM.class, "id");
            xstream.aliasField("id", ProtocoloICM.class, "id");
            if (tipoEntrada.equals("caminho")) {
                ProtocoloUtil protUtil = new ProtocoloUtil();
                String caminho = (String)o;
                protUtil.verificarAssinaturaProtocolo(caminho);
                ArrayList retornoArquivo = (ArrayList)xstream.fromXML(protUtil.retiraPrefixoTagAssinatura(caminho));
                protocoloICM = (ProtocoloICM)retornoArquivo.get(0);
            } else {
                protocoloICM = (ProtocoloICM)o;
            }
            parametros.put("protocoloId", (long)protocoloICM.getId());
            parametros.put("IdContrib", protocoloICM.getRaizCnpj() + " - " + protocoloICM.getNome().toString());
            Data dt = new Data();
            String[] dtInic = dt.separaData(protocoloICM.getInicCompetDecl());
            String dtInicConvertida = dtInic[1] + "/" + dtInic[0];
            String[] dtFim = dt.separaData(protocoloICM.getFimCompetDecl());
            String dtFimConvertida = dtFim[1] + "/" + dtFim[0];
            parametros.put("periodoRefDeclaracao", dtInicConvertida + " a " + dtFimConvertida);
            parametros.put("tipoDeclaracao", protocoloICM.getTipoDeclaracao().intValue());
            parametros.put("versaoTermoRef", protocoloICM.getVerTermoRef());
            parametros.put("versaoValidador", protocoloICM.getVerValidador());
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            df.setLenient(false);
            parametros.put("dataEntrega", protocoloICM.getDataEntrega());
            parametros.put("totalContasInformadas", protocoloICM.getTotalContasInfo().toString());
            parametros.put("qtdeSubtituloRegistro0200", protocoloICM.getQtdeSubtituloReg0200().toString());
            parametros.put("qtdeSubtituloRegistro0300", protocoloICM.getQtdeSubtituloReg0300().toString());
            parametros.put("imagem", logo);
            ArrayList<ProtocoloICM> vos = new ArrayList<ProtocoloICM>();
            vos.add(protocoloICM);
            Relatorio relatorio = new Relatorio(parametros, "br/gov/pbh/desif/service/relatorios/DESIF_ProtocoloICM.jasper", vos);
            relatorio.gerarRelatorio();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

