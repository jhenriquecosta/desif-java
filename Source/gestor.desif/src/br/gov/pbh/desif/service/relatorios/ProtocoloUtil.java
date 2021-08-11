/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  org.apache.commons.io.FileUtils
 */
package br.gov.pbh.desif.service.relatorios;

import br.gov.pbh.desif.esec.assinatura.Assinatura;
import br.gov.pbh.desif.esec.assinatura.Certificado;
import br.gov.pbh.desif.esec.assinatura.ListaAssinaturas;
import br.gov.pbh.desif.esec.assinatura.VerificadorAssinatura;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;

public class ProtocoloUtil {
    public String converterArquivoParaString(String caminhoArquivoProtocolo) throws IOException {
        String resposta = null;
        File arquivo = new File(caminhoArquivoProtocolo);
        resposta = FileUtils.readFileToString((File)arquivo);
        return resposta;
    }

    public void verificarAssinaturaProtocolo(String caminhoArquivoProtocolo) throws IOException {
        ListaAssinaturas listAss = VerificadorAssinatura.verificarXml(this.converterArquivoParaString(caminhoArquivoProtocolo));
        List<Assinatura> listAssinantes = listAss.getAssinaturas();
        System.out.println("" + listAss.isAssinaturasValidas() + " assinatura valida");
        System.out.println("" + listAss.isCertificadosValidos() + " certificados validos");
        System.out.println("" + listAssinantes.get(0).isIntegra() + " integra");
        if (listAss.isAssinaturasValidas() && listAss.isCertificadosValidos() && listAssinantes.get(0).getCertificado().getCnpj().equals("18715383000140")) {
            System.out.println("ASSINATURA VALIDA!!!");
        } else {
            System.out.println("ASSINATURA INVALIDA!!!");
        }
    }

    public InputStream retiraPrefixoTagAssinatura(String caminho) {
        ByteArrayInputStream InputProtocolo = null;
        try {
            String protocolo = this.converterArquivoParaString(caminho);
            protocolo = protocolo.replaceAll("ds:", "");
            InputProtocolo = new ByteArrayInputStream(protocolo.getBytes());
        }
        catch (IOException ex) {
            Logger.getLogger(ProtocoloUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return InputProtocolo;
    }
}

