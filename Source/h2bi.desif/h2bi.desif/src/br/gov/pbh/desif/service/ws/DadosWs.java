package br.gov.pbh.desif.service.ws;

import br.gov.pbh.desif.dao.WebServiceDao;
import br.gov.pbh.desif.model.registros.RegUtil;
import br.gov.pbh.desif.service.certificacao.Cifragem;
import br.gov.pbh.desif.view.telas.DialErroTransmissao;
import br.gov.pbh.desif.view.telas.DialSucessoTransmissao;
import br.gov.pbh.desif.view.util.SwingUtils;
import br.gov.pbh.desif.ws.cliente.DesifWSDelegate;
import br.gov.pbh.desif.ws.cliente.DesifWSService;
import br.gov.pbh.desif.ws.cliente.ProtocoloAMI;
import br.gov.pbh.desif.ws.cliente.ProtocoloContabil;
import br.gov.pbh.desif.ws.cliente.ProtocoloICM;
import br.gov.pbh.desif.ws.cliente.ReceberRequest;
import br.gov.pbh.desif.ws.cliente.ReceberResponseAMI;
import br.gov.pbh.desif.ws.cliente.ReceberResponseContabil;
import br.gov.pbh.desif.ws.cliente.ReceberResponseICM;
import java.awt.Frame;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ConnectException;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

public class DadosWs extends Thread
{

    private WebServiceDao wsDao;
    DesifWSService service;
    DesifWSDelegate proxy;
    private boolean flagStop;
    private boolean entrarNoPararThread;
    public Logger logger;

    public void run()
    {
        do
            try
            {
                while(!flagStop) ;
                if(enviarDeclaracao())
                {
                    interrupt();
                    stop();
                } else
                {
                    while(!interrupted()) 
                        interrupt();
                }
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        while(true);
    }

    public DadosWs()
    {
        flagStop = true;
        entrarNoPararThread = true;
        logger = Logger.getLogger(br.gov.pbh.desif.service.ws.DadosWs.class.getName());
        service = new DesifWSService();
        proxy = service.getWs();
    }

    public DadosWs(boolean flag)
    {
        flagStop = true;
        entrarNoPararThread = true;
        logger = Logger.getLogger(br.gov.pbh.desif.service.ws.DadosWs.class.getName());
        entrarNoPararThread = flag;
    }

    public boolean enviarDeclaracao()
    {
        boolean resp = false;
        try
        {
            BasicConfigurator.configure();
            logger.setLevel(Level.ERROR);
            String caminhoDiretorio = (new StringBuilder()).append(System.getProperty("user.home")).append(File.separator).append(".desif").toString();
            String caminhoLog = (new StringBuilder()).append(caminhoDiretorio).append(File.separator).append("log.log").toString();
            RollingFileAppender fileAppender = new RollingFileAppender(new PatternLayout("%m%n"), caminhoLog);
            fileAppender.setMaxBackupIndex(1);
            fileAppender.setMaxFileSize("100KB");
            logger.addAppender(fileAppender);
            File fileDeclaracao = new File((new StringBuilder()).append(RegUtil.caminhoArquivo).append(".zip").toString());
            FileOutputStream fo = new FileOutputStream((new StringBuilder()).append(RegUtil.caminhoArquivo).append(".enc").toString());
            byte byteDeclaracao[] = new byte[(int)fileDeclaracao.length()];
            FileInputStream fi = new FileInputStream(fileDeclaracao);
            fi.read(byteDeclaracao);
            fi.close();
            byte encryptedZip[] = Cifragem.getInstance().encrypt(byteDeclaracao);
            fo.write(encryptedZip);
            fo.close();
            if(!fileDeclaracao.exists())
            {
                SwingUtils.msgErro(null, "O arquivo da declara\347\343o n\343o foi encontrado ou n\343o existe.");
                pararThread();
                resp = false;
            } else
            if(byteDeclaracao.length == 0)
            {
                SwingUtils.msgErro(null, "O arquivo da declara\347\343o est\341 vazio.");
                pararThread();
                resp = false;
            }
            DataHandler dhDeclaracao = new DataHandler(new FileDataSource(new File((new StringBuilder()).append(RegUtil.caminhoArquivo).append(".enc").toString())));
            ReceberRequest request = new ReceberRequest();
            request.setVersao("4.05");
            request.setZip(dhDeclaracao);
            if(RegUtil.moduloDeclaracao.equals("2"))
            {
                ReceberResponseAMI recRespAMI = proxy.receberAMI(request);
                if(recRespAMI.getListaErros().size() > 0)
                {
                    java.util.List listAMI = recRespAMI.getListaErros();
                    chamarTelaErroWS(null, true, listAMI);
                } else
                {
                    ProtocoloAMI protAMI = recRespAMI.getProtocoloAMIVO();
                    Object objProtocolo = protAMI;
                    chamarTelaSucessoWS(null, true, objProtocolo, Short.parseShort(RegUtil.moduloDeclaracao));
                }
            } 
            else
            if(RegUtil.moduloDeclaracao.equals("3"))
            {
                ReceberResponseICM recRespICM = proxy.receberICM(request);
                if(recRespICM.getListaErros().size() > 0)
                {
                    java.util.List listICM = recRespICM.getListaErros();
                    chamarTelaErroWS(null, true, listICM);
                } else
                {
                    ProtocoloICM protICM = recRespICM.getProtocoloICM();
                    Object objProtocolo = protICM;
                    chamarTelaSucessoWS(null, true, objProtocolo, Short.parseShort(RegUtil.moduloDeclaracao));
                }
            } else
            if(RegUtil.moduloDeclaracao.equals("1"))
            {
                ReceberResponseContabil recRespCont = proxy.receberContabil(request);
                if(recRespCont.getListaErros().size() > 0)
                {
                    java.util.List listContabil = recRespCont.getListaErros();
                    chamarTelaErroWS(null, true, listContabil);
                } else
                {
                    ProtocoloContabil protCont = recRespCont.getProtocoloContabil();
                    Object objProtocolo = protCont;
                    chamarTelaSucessoWS(null, true, objProtocolo, Short.parseShort(RegUtil.moduloDeclaracao));
                }
            }
            pararThread();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            RegUtil.imprimirErro(ex, logger);
            SwingUtils.msgErro(null, ex.getMessage());
            pararThread();
            resp = false;
        }
        catch(ExceptionInInitializerError e)
        {
            Throwable te = e.getCause();
            if(te instanceof ConnectException)
                SwingUtils.msgErro(null, "O sistema n\343o conseguiu conex\343o com WebService!\n Verifique a conex\343o com a rede.");
            e.printStackTrace();
            RegUtil.imprimirErro(e, logger);
            pararThread();
            resp = false;
        }
        pararThread();
        resp = false;
        return resp;
    }

    public void chamarTelaSucessoWS(Frame parent, boolean modal, Object protocolo, short modulo)
    {
        String resultadoXML[] = new String[2];
        try
        {
            resultadoXML = gerarXML(protocolo, Short.valueOf(modulo));
        }
        catch(IOException ex)
        {
            java.util.logging.Logger.getLogger(br.gov.pbh.desif.service.ws.DadosWs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        salvarDisco(resultadoXML[0], resultadoXML[1]);
        DialSucessoTransmissao dialSucessoTransmissao = new DialSucessoTransmissao(parent, modal, protocolo, modulo);
        dialSucessoTransmissao.setVisible(true);
    }

    public void chamarTelaErroWS(Frame parent, boolean modal, java.util.List list)
    {
        DialErroTransmissao dialErroTransmissao = new DialErroTransmissao(null, true, list);
        dialErroTransmissao.setVisible(true);
    }

    public static void validarAssinatura(String s)
        throws Exception
    {
    }

    private void pararThread()
    {
        for(; !interrupted(); stop())
            interrupt();

    }

    private String[] gerarXML(Object protocolo, Short modulo)
        throws IOException
    {
        String xmlGerado = null;
        String nome = null;
        if(modulo.shortValue() == 1)
        {
            ProtocoloContabil protocoloContabil = (ProtocoloContabil)protocolo;
            String compet = (new StringBuilder()).append(protocoloContabil.getInicCompetDecl()).append("a").append(protocoloContabil.getFimCompetDecl()).toString();
            xmlGerado = protocoloContabil.getXmlAssinado();
            nome = (new StringBuilder()).append("Protocolo_Contabil_").append(String.valueOf(protocoloContabil.getId())).append("_").append(compet).toString();
        } else
        if(modulo.shortValue() == 2)
        {
            ProtocoloAMI protocoloAMI = (ProtocoloAMI)protocolo;
            String compet = (new StringBuilder()).append(protocoloAMI.getAnoMesInicCmpe()).append("a").append(protocoloAMI.getAnoMesInicCmpe()).toString();
            xmlGerado = protocoloAMI.getXmlAssinado();
            nome = (new StringBuilder()).append("Protocolo_Apuracao_ISSQN_Mensal_").append(String.valueOf(protocoloAMI.getId())).append("_").append(compet).toString();
        } else
        {
            ProtocoloICM protocoloICM = (ProtocoloICM)protocolo;
            String compet = (new StringBuilder()).append(protocoloICM.getInicCompetDecl()).append("a").append(protocoloICM.getFimCompetDecl()).toString();
            xmlGerado = protocoloICM.getXmlAssinado();
            nome = (new StringBuilder()).append("Protocolo_Informacoes_Comuns_Municipios_").append(String.valueOf(protocoloICM.getId())).append("_").append(compet).toString();
        }
        String resultado[] = new String[2];
        resultado[0] = xmlGerado;
        resultado[1] = nome;
        return resultado;
    }

    private void salvarDisco(String xml, String nome)
    {
        try
        {
            String nomeDiretorio = (new StringBuilder()).append(RegUtil.caminhoDiretorioPadrao).append(File.separator).append("protocolo").toString();
            if(!(new File(nomeDiretorio)).exists())
                (new File(nomeDiretorio)).mkdir();
            File arquivoProtocolo = new File(nomeDiretorio, (new StringBuilder()).append(nome).append(".xml").toString());
            arquivoProtocolo.createNewFile();
            if(!arquivoProtocolo.exists())
                SwingUtils.msgErro(null, "Erro na grava\347\343o do protocolo!");
            if(!arquivoProtocolo.canWrite())
            {
                SwingUtils.msgErro(null, "O sistema n\343o tem permiss\343o de escrita no disco.\n O arquivo n\343o pode ser gravado.");
            } else
            {
                BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoProtocolo, true));
                writer.write(xml);
                writer.newLine();
                writer.flush();
            }
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }
}
