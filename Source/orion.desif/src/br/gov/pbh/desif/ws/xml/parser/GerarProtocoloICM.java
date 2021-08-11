
package br.gov.pbh.desif.ws.xml.parser;

import br.gov.pbh.desif.model.pojo.SignatureProtocolo;
import br.gov.pbh.desif.ws.cliente.ProtocoloICM;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GerarProtocoloICM
{

    public GerarProtocoloICM()
    {
    }

    public String receberDadosProtocoloICM(ProtocoloICM dados)
        throws IOException
    {
        XmlFriendlyReplacer replacer = new XmlFriendlyReplacer("ddd", "_");
        XStream xstream = new XStream(new DomDriver("UTF-8", replacer));
        xstream.setMode(1001);
        xstream.alias("Protocolo", br.gov.pbh.desif.ws.cliente.ProtocoloICM.class);
        xstream.alias("Signature", br.gov.pbh.desif.model.pojo.SignatureProtocolo.class);
        xstream.alias("ProtocoloRetorno", null);
        xstream.useAttributeFor(br.gov.pbh.desif.ws.cliente.ProtocoloICM.class, "id");
        xstream.aliasField("id", br.gov.pbh.desif.ws.cliente.ProtocoloICM.class, "id");
        List protocoloRetorno = new ArrayList(1);
        protocoloRetorno.add(dados);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        java.io.Writer writer = new OutputStreamWriter(outputStream, "UTF-8");
        xstream.toXML(protocoloRetorno, writer);
        String saidaXML = outputStream.toString("UTF-8");
        gerarArquivoXML(saidaXML);
        return saidaXML;
    }

    private void gerarArquivoXML(String arquivo)
        throws IOException
    {
        byte saidaXML[] = arquivo.getBytes();
        FileOutputStream outPut = new FileOutputStream("C://ProtocoloICMTesteAgora.xml");
        outPut.write(saidaXML);
    }
}
