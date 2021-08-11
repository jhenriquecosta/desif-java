
package br.gov.pbh.desif.service.ws;

import br.gov.pbh.desif.dao.WebServiceDao;
import br.gov.pbh.desif.model.registros.RegUtil;
import br.gov.pbh.desif.service.certificacao.Cifragem;
import br.gov.pbh.desif.view.telas.DialErroTransmissao;
import br.gov.pbh.desif.view.telas.DialSucessoTransmissao;
import br.gov.pbh.desif.view.util.SwingUtils;
import br.gov.pbh.desif.ws.cliente.DesifWSDelegate;
import br.gov.pbh.desif.ws.cliente.DesifWSService;
import br.gov.pbh.desif.ws.cliente.Erros;
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
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import org.apache.log4j.Appender;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Layout;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

public class DadosWs
extends Thread {
    private WebServiceDao wsDao;
    DesifWSService service;
    DesifWSDelegate proxy;
    private boolean flagStop = true;
    private boolean entrarNoPararThread = true;
    public org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger((String)DadosWs.class.getName());


    @Override
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

    public DadosWs() {
        this.service = new DesifWSService();
        this.proxy = this.service.getWs();
    }

    public DadosWs(boolean flag) {
        this.entrarNoPararThread = flag;
    }

    public boolean enviarDeclaracao() {
        boolean resp = false;
        try {
            BasicConfigurator.configure();
            this.logger.setLevel(org.apache.log4j.Level.ERROR);
            String caminhoDiretorio = System.getProperty("user.home") + File.separator + ".desif";
            String caminhoLog = caminhoDiretorio + File.separator + "log.log";
            RollingFileAppender fileAppender = new RollingFileAppender((Layout)new PatternLayout("%m%n"), caminhoLog);
            fileAppender.setMaxBackupIndex(1);
            fileAppender.setMaxFileSize("100KB");
            this.logger.addAppender((Appender)fileAppender);
            File fileDeclaracao = new File(RegUtil.caminhoArquivo + ".zip");
            FileOutputStream fo = new FileOutputStream(RegUtil.caminhoArquivo + ".enc");
            byte[] byteDeclaracao = new byte[(int)fileDeclaracao.length()];
            FileInputStream fi = new FileInputStream(fileDeclaracao);
            fi.read(byteDeclaracao);
            fi.close();
            byte[] encryptedZip = Cifragem.getInstance().encrypt(byteDeclaracao);
            fo.write(encryptedZip);
            fo.close();
            if (!fileDeclaracao.exists()) {
                SwingUtils.msgErro(null, "O arquivo da declara\u00e7\u00e3o n\u00e3o foi encontrado ou n\u00e3o existe.");
                this.pararThread();
                resp = false;
            } else if (byteDeclaracao.length == 0) {
                SwingUtils.msgErro(null, "O arquivo da declara\u00e7\u00e3o est\u00e1 vazio.");
                this.pararThread();
                resp = false;
            }
            DataHandler dhDeclaracao = new DataHandler(new FileDataSource(new File(RegUtil.caminhoArquivo + ".enc")));
            ReceberRequest request = new ReceberRequest();
            request.setVersao("4.2");
            request.setZip(dhDeclaracao);
            if (RegUtil.moduloDeclaracao.equals("2")) 
            {
                ReceberResponseAMI recRespAMI = this.proxy.receberAMI(request);
                if (recRespAMI.getListaErros().size() > 0)
                {
                    List<Erros> listAMI = recRespAMI.getListaErros();
                    this.chamarTelaErroWS(null, true, listAMI);
                }
                else
                {
                    ProtocoloAMI protAMI = recRespAMI.getProtocoloAMIVO();
                    if (protAMI.getMensagem() != null) 
                    {
                        String mensagem = new String(protAMI.getMensagem().getBytes("UTF-8"));
                        protAMI.setMensagem(mensagem);
                    }
                    ProtocoloAMI objProtocolo = protAMI;
                    this.chamarTelaSucessoWS(null, true, objProtocolo, Short.parseShort(RegUtil.moduloDeclaracao));
                }
            } 
            else if (RegUtil.moduloDeclaracao.equals("3")) 
            {
                ReceberResponseICM recRespICM = this.proxy.receberICM(request);
                if (recRespICM.getListaErros().size() > 0) 
                {
                    List<Erros> listICM = recRespICM.getListaErros();
                    this.chamarTelaErroWS(null, true, listICM);
                } 
                else
                {
                    ProtocoloICM protICM;
                    ProtocoloICM objProtocolo = protICM = recRespICM.getProtocoloICM();
                    this.chamarTelaSucessoWS(null, true, objProtocolo, Short.parseShort(RegUtil.moduloDeclaracao));
                }
            } 
            else if (RegUtil.moduloDeclaracao.equals("1")) 
            {
                ReceberResponseContabil recRespCont = this.proxy.receberContabil(request);
                if (recRespCont.getListaErros().size() > 0) 
                {
                    List<Erros> listContabil = recRespCont.getListaErros();
                    this.chamarTelaErroWS(null, true, listContabil);
                }
                else {
                    ProtocoloContabil protCont;
                    ProtocoloContabil objProtocolo = protCont = recRespCont.getProtocoloContabil();
                    this.chamarTelaSucessoWS(null, true, objProtocolo, Short.parseShort(RegUtil.moduloDeclaracao));
                }
            }
            this.pararThread();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            RegUtil.imprimirErro(ex, this.logger);
            SwingUtils.msgErro(null, ex.getMessage());
            this.pararThread();
            resp = false;
        }
        catch (ExceptionInInitializerError e) {
            Throwable te = e.getCause();
            if (te instanceof ConnectException) {
                SwingUtils.msgErro(null, "O sistema n\u00e3o conseguiu conex\u00e3o com WebService!\n Verifique a conex\u00e3o com a rede.");
            }
            e.printStackTrace();
            RegUtil.imprimirErro(e, this.logger);
            this.pararThread();
            resp = false;
        }
        this.pararThread();
        resp = false;
        return resp;
    }

    public void chamarTelaSucessoWS(Frame parent, boolean modal, Object protocolo, short modulo) {
        String[] resultadoXML = new String[2];
        try {
            resultadoXML = this.gerarXML(protocolo, modulo);
        }
        catch (IOException ex) {
            Logger.getLogger(DadosWs.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.salvarDisco(resultadoXML[0], resultadoXML[1]);
        DialSucessoTransmissao dialSucessoTransmissao = new DialSucessoTransmissao(parent, modal, protocolo, modulo);
        dialSucessoTransmissao.setVisible(true);
    }

    public void chamarTelaErroWS(Frame parent, boolean modal, List<Erros> list) {
        DialErroTransmissao dialErroTransmissao = new DialErroTransmissao(null, true, list);
        dialErroTransmissao.setVisible(true);
    }

    public static void validarAssinatura(String xml) throws Exception {
    }

    private void pararThread() {
        while (!DadosWs.interrupted()) {
            this.interrupt();
            this.stop();
        }
    }

    private String[] gerarXML(Object protocolo, Short modulo) throws IOException {
        String xmlGerado = null;
        String nome = null;
        if (modulo == 1) {
            ProtocoloContabil protocoloContabil = (ProtocoloContabil)protocolo;
            String compet = protocoloContabil.getInicCompetDecl() + "a" + protocoloContabil.getFimCompetDecl();
            xmlGerado = protocoloContabil.getXmlAssinado();
            nome = "Protocolo_Contabil_" + String.valueOf(protocoloContabil.getId()) + "_" + compet;
        } else if (modulo == 2) {
            ProtocoloAMI protocoloAMI = (ProtocoloAMI)protocolo;
            String compet = protocoloAMI.getAnoMesInicCmpe() + "a" + protocoloAMI.getAnoMesInicCmpe();
            xmlGerado = protocoloAMI.getXmlAssinado();
            nome = "Protocolo_Apuracao_ISSQN_Mensal_" + String.valueOf(protocoloAMI.getId()) + "_" + compet;
        } else {
            ProtocoloICM protocoloICM = (ProtocoloICM)protocolo;
            String compet = protocoloICM.getInicCompetDecl() + "a" + protocoloICM.getFimCompetDecl();
            xmlGerado = protocoloICM.getXmlAssinado();
            nome = "Protocolo_Informacoes_Comuns_Municipios_" + String.valueOf(protocoloICM.getId()) + "_" + compet;
        }
        String[] resultado = new String[]{xmlGerado, nome};
        return resultado;
    }

    private void salvarDisco(String xml, String nome) {
        try {
            String nomeDiretorio = RegUtil.caminhoDiretorioPadrao + File.separator + "protocolo";
            if (!new File(nomeDiretorio).exists()) {
                new File(nomeDiretorio).mkdir();
            }
            File arquivoProtocolo = new File(nomeDiretorio, nome + ".xml");
            arquivoProtocolo.createNewFile();
            if (!arquivoProtocolo.exists()) {
                SwingUtils.msgErro(null, "Erro na grava\u00e7\u00e3o do protocolo!");
            }
            if (!arquivoProtocolo.canWrite()) {
                SwingUtils.msgErro(null, "O sistema n\u00e3o tem permiss\u00e3o de escrita no disco.\n O arquivo n\u00e3o pode ser gravado.");
            } else {
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter((OutputStream)new FileOutputStream(arquivoProtocolo), "UTF-8"));
                out.write(xml);
                out.flush();
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

