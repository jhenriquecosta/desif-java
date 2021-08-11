
package br.gov.pbh.desif.service.relatorios;

import br.gov.pbh.des.images.ImageFactory;
import br.gov.pbh.desif.model.pojo.SignatureProtocolo;
import br.gov.pbh.desif.model.registros.Data;
import br.gov.pbh.desif.ws.cliente.ProtocoloICM;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DateConverter;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;

// Referenced classes of package br.gov.pbh.desif.service.relatorios:
//            ProtocoloUtil, Relatorio

public class RelatorioProtocoloICM
{

    public RelatorioProtocoloICM()
    {
    }

    public void gerarProtocolo(Object o, String tipoEntrada)
        throws Exception
    {
        try
        {
            Map parametros = new HashMap();
            java.awt.Image logo = ImageFactory.getInstance().getImage("bandeiraGuia.PNG").getImage();
            XmlFriendlyReplacer replacer = new XmlFriendlyReplacer("ddd", "_");
            XStream xstream = new XStream(new DomDriver("UTF-8", replacer));
            xstream.registerConverter(new DateConverter("yyyy-MM-dd HH:mm:ss.SSS", new String[0]));
            xstream.setMode(1001);
            xstream.alias("Protocolo", br.gov.pbh.desif.ws.cliente.ProtocoloICM.class);
            xstream.alias("Signature", br.gov.pbh.desif.model.pojo.SignatureProtocolo.class);
            xstream.alias("ProtocoloRetorno",  java.util.List.class);
            xstream.useAttributeFor(br.gov.pbh.desif.ws.cliente.ProtocoloICM.class, "id");
            xstream.aliasField("id", br.gov.pbh.desif.ws.cliente.ProtocoloICM.class, "id");
            ProtocoloICM protocoloICM;
            if(tipoEntrada.equals("caminho"))
            {
                ProtocoloUtil protUtil = new ProtocoloUtil();
                String caminho = (String)o;
                protUtil.verificarAssinaturaProtocolo(caminho);
                ArrayList retornoArquivo = (ArrayList)xstream.fromXML(protUtil.retiraPrefixoTagAssinatura(caminho));
                protocoloICM = (ProtocoloICM)retornoArquivo.get(0);
            } else
            {
                protocoloICM = (ProtocoloICM)o;
            }
            parametros.put("protocoloId", Long.valueOf(protocoloICM.getId().longValue()));
            parametros.put("IdContrib", (new StringBuilder()).append(protocoloICM.getRaizCnpj()).append(" - ").append(protocoloICM.getNome().toString()).toString());
            Data dt = new Data();
            String dtInic[] = dt.separaData(protocoloICM.getInicCompetDecl());
            String dtInicConvertida = (new StringBuilder()).append(dtInic[1]).append("/").append(dtInic[0]).toString();
            String dtFim[] = dt.separaData(protocoloICM.getFimCompetDecl());
            String dtFimConvertida = (new StringBuilder()).append(dtFim[1]).append("/").append(dtFim[0]).toString();
            parametros.put("periodoRefDeclaracao", (new StringBuilder()).append(dtInicConvertida).append(" a ").append(dtFimConvertida).toString());
            parametros.put("tipoDeclaracao", Integer.valueOf(protocoloICM.getTipoDeclaracao().intValue()));
            parametros.put("versaoTermoRef", protocoloICM.getVerTermoRef());
            parametros.put("versaoValidador", protocoloICM.getVerValidador());
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            df.setLenient(false);
            parametros.put("dataEntrega", protocoloICM.getDataEntrega());
            parametros.put("totalContasInformadas", protocoloICM.getTotalContasInfo().toString());
            parametros.put("totalContasMaisAnalitico", protocoloICM.getTotalContasMaisAnalit().toString());
            parametros.put("totalContasMaisAnaliticoTributaveis", protocoloICM.getTotalContasMaisAnalitTrib().toString());
            parametros.put("qtdeSubtituloRegistro0200", protocoloICM.getQtdeSubtituloReg0200().toString());
            parametros.put("qtdeSubtituloRegistro0300", protocoloICM.getQtdeSubtituloReg0300().toString());
            parametros.put("imagem", logo);
            List vos = new ArrayList();
            vos.add(protocoloICM);
            Relatorio relatorio = new Relatorio(parametros, "br/gov/pbh/desif/service/relatorios/DESIF_ProtocoloICM.jasper", vos);
            relatorio.gerarRelatorio();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
