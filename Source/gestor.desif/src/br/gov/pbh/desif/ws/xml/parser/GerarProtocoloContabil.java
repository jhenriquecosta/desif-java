
package br.gov.pbh.desif.ws.xml.parser;

import br.gov.pbh.desif.model.pojo.SignatureProtocolo;
import br.gov.pbh.desif.ws.cliente.ProtocoloContabil;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamDriver;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GerarProtocoloContabil {
    public String receberDadosProtocoloContabil(ProtocoloContabil dados) throws IOException {
        XmlFriendlyReplacer replacer = new XmlFriendlyReplacer("ddd", "_");
        XStream xstream = new XStream((HierarchicalStreamDriver)new DomDriver("UTF-8", replacer));
        xstream.setMode(1001);
        xstream.alias("Protocolo", ProtocoloContabil.class);
        xstream.alias("Signature", SignatureProtocolo.class);
        xstream.alias("ProtocoloRetorno", List.class);
        xstream.useAttributeFor(ProtocoloContabil.class, "id");
        xstream.aliasField("id", ProtocoloContabil.class, "id");
        ArrayList<ProtocoloContabil> protocoloRetorno = new ArrayList<ProtocoloContabil>(1);
        protocoloRetorno.add(dados);
        String saidaXML = xstream.toXML(protocoloRetorno);
        return saidaXML;
    }
}

