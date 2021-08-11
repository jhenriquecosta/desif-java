
package br.gov.pbh.desif.ws.xml.parser;

import br.gov.pbh.desif.model.pojo.SignatureProtocolo;
import br.gov.pbh.desif.ws.cliente.ProtocoloICM;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamDriver;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class GerarProtocoloICM {
    public String receberDadosProtocoloICM(ProtocoloICM dados) throws IOException {
        XmlFriendlyReplacer replacer = new XmlFriendlyReplacer("ddd", "_");
        XStream xstream = new XStream((HierarchicalStreamDriver)new DomDriver("UTF-8", replacer));
        xstream.setMode(1001);
        xstream.alias("Protocolo", ProtocoloICM.class);
        xstream.alias("Signature", SignatureProtocolo.class);
        xstream.alias("ProtocoloRetorno", List.class);
        xstream.useAttributeFor(ProtocoloICM.class, "id");
        xstream.aliasField("id", ProtocoloICM.class, "id");
        ArrayList<ProtocoloICM> protocoloRetorno = new ArrayList<ProtocoloICM>(1);
        protocoloRetorno.add(dados);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter((OutputStream)outputStream, "UTF-8");
        xstream.toXML(protocoloRetorno, (Writer)writer);
        String saidaXML = outputStream.toString("UTF-8");
        this.gerarArquivoXML(saidaXML);
        return saidaXML;
    }

    private void gerarArquivoXML(String arquivo) throws IOException {
        byte[] saidaXML = arquivo.getBytes();
        FileOutputStream outPut = new FileOutputStream("C://ProtocoloICMTesteAgora.xml");
        outPut.write(saidaXML);
    }
}

