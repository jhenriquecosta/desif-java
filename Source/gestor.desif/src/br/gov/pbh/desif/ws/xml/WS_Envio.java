
import br.gov.pbh.desif.ws.jaxws.DesIfService;
import br.gov.pbh.desif.ws.jaxws.DesIfServiceDelegate;
import br.gov.pbh.desif.ws.jaxws.ReceberRequest;
import br.gov.pbh.desif.ws.jaxws.ReceberResponse;
import br.gov.pbh.desif.ws.jaxws.TcDeclaracao;
import br.gov.pbh.desif.ws.xml.parser.ParserHelper;
import br.gov.pbh.desif.ws.xml.parser.ServicoEnviarDeclaracaoResposta;
import br.gov.pbh.desif.ws.xml.vo.services.ServicoEnviarDeclaracaoRespostaVO;
import java.io.StringReader;
import java.util.HashSet;
import java.util.Set;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class WS_Envio
{
   // $FF: renamed from: ws br.gov.pbh.desif.ws.jaxws.DesIfService
   static DesIfService getWsDesif = new DesIfService();
   static DesIfServiceDelegate service;

   public static void main(String[] args) throws XMLStreamException {
      ReceberRequest envio = createEnvio();
      ReceberResponse resposta = service.receber(envio);
      String xml = resposta.getOutputXML();
      tratarRespostaWS(xml);
   }

   private static void tratarRespostaWS(String xml) throws XMLStreamException {
      System.out.println(xml);
      StringReader reader = new StringReader(xml);
      XMLStreamReader xmlStream = ParserHelper.getXMLStreamReader(reader);
      ServicoEnviarDeclaracaoRespostaVO vo = ServicoEnviarDeclaracaoResposta.getInstance().readServico(xmlStream);
   }

   private static ReceberRequest createEnvio() {
      ReceberRequest envio = new ReceberRequest();
      envio.setVersao("1.0");
      envio.setZip(null);
      return envio;
   }

   static {
      service = getWsDesif.getWs();
   }
}
