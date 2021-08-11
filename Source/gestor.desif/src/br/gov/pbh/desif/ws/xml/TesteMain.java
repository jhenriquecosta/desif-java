/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.ws.xml;

import br.gov.pbh.desif.ws.xml.parser.BaseParser;
import br.gov.pbh.desif.ws.xml.parser.ParserHelper;
import br.gov.pbh.desif.ws.xml.parser.ServicoEnviarDeclaracaoResposta;
import br.gov.pbh.desif.ws.xml.parser.ServicoParser;
import br.gov.pbh.desif.ws.xml.vo.services.ServicoEnviarDeclaracaoRespostaVO;
import br.gov.pbh.desif.ws.xml.vo.tiposcomplexos.DependenciaVO;
import br.gov.pbh.desif.ws.xml.vo.tiposcomplexos.ProtocoloVO;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class TesteMain {
    public static void main(String[] args) throws Exception {
        TesteMain.testWriter();
    }

    private static void testWriter() throws XMLStreamException {
        ServicoEnviarDeclaracaoRespostaVO vo = new ServicoEnviarDeclaracaoRespostaVO();
        StringWriter swriter = new StringWriter();
        XMLStreamWriter xml = ParserHelper.getXMLStreamWriter(swriter);
        ProtocoloVO protocoloVO = new ProtocoloVO();
        protocoloVO.setListaDependencia(new ArrayList<>());
        DependenciaVO dependenciaVO = new DependenciaVO();
        dependenciaVO.setCnpjProprio("456789");
        dependenciaVO.setCodDepe("1");
        dependenciaVO.setCtblPropria(1);
        dependenciaVO.setIndInscMunl(2);
        protocoloVO.getListaDependencia().add(dependenciaVO);
        protocoloVO.setRaizCnpj("00000000");
        protocoloVO.setNome("fasfsdafsda");
        protocoloVO.setAnoMesInicCmpe("234344");
        protocoloVO.setTipoDecl(1);
        protocoloVO.setCodigo(1L);
        vo.setProtocolo(protocoloVO);
        BaseParser.writeStartDocument(xml);
           ServicoParser<ServicoEnviarDeclaracaoRespostaVO> serviceParser = ServicoParser.getInstance(ServicoEnviarDeclaracaoResposta.getInstance().getNameSpaceURI(), ServicoEnviarDeclaracaoRespostaVO.class);
        serviceParser.writeServico(xml, vo);
        BaseParser.writeEndDocument(xml, true);
        String string = swriter.toString();
        System.out.println(string);
        File file = new File("C:\\usr\\servicoRespostaDesIf.xml");
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(string.toCharArray());
            fileWriter.flush();
            fileWriter.close();
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
}

