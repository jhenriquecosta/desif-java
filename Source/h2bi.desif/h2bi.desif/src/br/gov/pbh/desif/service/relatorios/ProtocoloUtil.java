
package br.gov.pbh.desif.service.relatorios;

import br.gov.pbh.desif.esec.assinatura.*;
import java.io.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;

public class ProtocoloUtil
{

    public ProtocoloUtil()
    {
    }

    public String converterArquivoParaString(String caminhoArquivoProtocolo)
        throws IOException
    {
        String resposta = null;
        File arquivo = new File(caminhoArquivoProtocolo);
        resposta = FileUtils.readFileToString(arquivo);
        return resposta;
    }

    public void verificarAssinaturaProtocolo(String caminhoArquivoProtocolo)
        throws IOException
    {
        ListaAssinaturas listAss = VerificadorAssinatura.verificarXml(converterArquivoParaString(caminhoArquivoProtocolo));
        List listAssinantes = listAss.getAssinaturas();
        if(listAss.isAssinaturasValidas() && listAss.isCertificadosValidos() && ((Assinatura)listAssinantes.get(0)).getCertificado().getCnpj().equals("18715383000140"))
            System.out.println("ASSINATURA VALIDA!!!");
        else
            System.out.println("ASSINATURA INVALIDA!!!");
    }

    public InputStream retiraPrefixoTagAssinatura(String caminho)
    {
        InputStream InputProtocolo = null;
        try
        {
            String protocolo = converterArquivoParaString(caminho);
            protocolo = protocolo.replaceAll("ds:", "");
            InputProtocolo = new ByteArrayInputStream(protocolo.getBytes());
        }
        catch(IOException ex)
        {
            Logger.getLogger(br.gov.pbh.desif.service.relatorios.ProtocoloUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return InputProtocolo;
    }
}
