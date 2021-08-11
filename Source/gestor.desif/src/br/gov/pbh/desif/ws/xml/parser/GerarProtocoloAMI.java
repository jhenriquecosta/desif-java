package br.gov.pbh.desif.ws.xml.parser;

import br.gov.pbh.desif.model.pojo.SignatureProtocolo;
import br.gov.pbh.desif.ws.cliente.DependenciaProtocoloAMI;
import br.gov.pbh.desif.ws.cliente.ProtocoloAMI;
import br.gov.pbh.desif.ws.cliente.TotalizacaoProtocoloAMI;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamDriver;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GerarProtocoloAMI {
    public String receberDadosProtocoloAMI(ProtocoloAMI dados) throws IOException {
        XmlFriendlyReplacer replacer = new XmlFriendlyReplacer("ddd", "_");
        XStream xstream = new XStream((HierarchicalStreamDriver)new DomDriver("UTF-8", replacer));
        xstream.setMode(1001);
        xstream.alias("Protocolo", ProtocoloAMI.class);
        xstream.alias("Dependencia", DependenciaProtocoloAMI.class);
        xstream.alias("Totalizacao", TotalizacaoProtocoloAMI.class);
        xstream.alias("Signature", SignatureProtocolo.class);
        xstream.alias("EnviarDeclaracaoResposta", List.class);
        xstream.alias("ProtocoloRetorno", List.class);
        xstream.useAttributeFor(ProtocoloAMI.class, "id");
        xstream.aliasField("id", ProtocoloAMI.class, "id");
        ArrayList<ProtocoloAMI> protocoloRetorno = new ArrayList<ProtocoloAMI>(1);
        protocoloRetorno.add(dados);
        String saidaXML = xstream.toXML(protocoloRetorno);
        return saidaXML;
    }
}

