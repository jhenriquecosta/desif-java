
package br.gov.pbh.desif.ws.xml.parser;

import br.gov.pbh.desif.model.pojo.SignatureProtocolo;
import br.gov.pbh.desif.ws.cliente.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GerarProtocoloAMI
{

    public GerarProtocoloAMI()
    {
    }

    public String receberDadosProtocoloAMI(ProtocoloAMI dados)
        throws IOException
    {
        XmlFriendlyReplacer replacer = new XmlFriendlyReplacer("ddd", "_");
        XStream xstream = new XStream(new DomDriver("UTF-8", replacer));
        xstream.setMode(1001);
        xstream.alias("Protocolo",  br.gov.pbh.desif.ws.cliente.ProtocoloAMI.class.getClass());
        xstream.alias("Dependencia", br.gov.pbh.desif.ws.cliente.DependenciaProtocoloAMI.class);
        xstream.alias("Totalizacao", br.gov.pbh.desif.ws.cliente.TotalizacaoProtocoloAMI.class);
        xstream.alias("Signature", br.gov.pbh.desif.model.pojo.SignatureProtocolo.class);
        xstream.alias("EnviarDeclaracaoResposta",java.util.List.class );
        xstream.alias("ProtocoloRetorno", java.util.List.class);
        xstream.useAttributeFor(br.gov.pbh.desif.ws.cliente.ProtocoloAMI.class, "id");
        xstream.aliasField("id", br.gov.pbh.desif.ws.cliente.ProtocoloAMI.class, "id");
        List protocoloRetorno = new ArrayList(1);
        protocoloRetorno.add(dados);
        String saidaXML = xstream.toXML(protocoloRetorno);
        return saidaXML;
    }
}
