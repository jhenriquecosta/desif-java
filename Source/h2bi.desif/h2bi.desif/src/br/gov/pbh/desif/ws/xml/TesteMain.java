
package br.gov.pbh.desif.ws.xml;

import br.gov.pbh.desif.ws.xml.parser.BaseParser;
import br.gov.pbh.desif.ws.xml.parser.ParserHelper;
import br.gov.pbh.desif.ws.xml.parser.ServicoEnviarDeclaracaoResposta;
import br.gov.pbh.desif.ws.xml.parser.ServicoParser;
import br.gov.pbh.desif.ws.xml.vo.services.ServicoEnviarDeclaracaoRespostaVO;
import br.gov.pbh.desif.ws.xml.vo.tiposcomplexos.DependenciaVO;
import br.gov.pbh.desif.ws.xml.vo.tiposcomplexos.ProtocoloVO;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class TesteMain
{

    public TesteMain()
    {
    }

    public static void main(String args[])
        throws Exception
    {
        testWriter();
    }

    private static void testWriter()
        throws XMLStreamException
    {
        ServicoEnviarDeclaracaoRespostaVO vo = new ServicoEnviarDeclaracaoRespostaVO();
        StringWriter swriter = new StringWriter();
        XMLStreamWriter xml = ParserHelper.getXMLStreamWriter(swriter);
        ProtocoloVO protocoloVO = new ProtocoloVO();
        protocoloVO.setListaDependencia(new ArrayList());
        DependenciaVO dependenciaVO = new DependenciaVO();
        dependenciaVO.setCnpjProprio("456789");
        dependenciaVO.setCodDepe("1");
        dependenciaVO.setCtblPropria(Integer.valueOf(1));
        dependenciaVO.setIndInscMunl(Integer.valueOf(2));
        protocoloVO.getListaDependencia().add(dependenciaVO);
        protocoloVO.setNome("fasfsdafsda");
        protocoloVO.setAnoMesInicCmpe("234344");
        protocoloVO.setTipoDecl(Integer.valueOf(1));
        vo.setProtocolo(protocoloVO);
        BaseParser.writeStartDocument(xml);
        ServicoParser serviceParser = ServicoParser.getInstance(ServicoEnviarDeclaracaoResposta.getInstance().getNameSpaceURI(), br.gov.pbh.desif.ws.xml.vo.services.ServicoEnviarDeclaracaoRespostaVO.class);
        serviceParser.writeServico(xml, vo);
        BaseParser.writeEndDocument(xml, true);
        String string = swriter.toString();
        System.out.println(string);
        File file = new File("C:\\servicoRespostaDesIf.xml");
        try
        {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(string.toCharArray());
            fileWriter.flush();
            fileWriter.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
