
package br.gov.pbh.desif.service.certificacao;

import br.com.esec.net.ProxyConfiguration;
import br.gov.pbh.desif.esec.assinatura.*;
import br.gov.pbh.desif.model.registros.RegUtil;
import br.gov.pbh.desif.service.contexto.Contexto;
import br.gov.pbh.desif.service.util.Configuracao;
import br.gov.pbh.desif.view.util.SwingUtils;
import java.io.File;
import java.net.PasswordAuthentication;
import java.security.GeneralSecurityException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import org.apache.log4j.*;

// Referenced classes of package br.gov.pbh.desif.service.certificacao:
//            TxtFileFilter, P7sFileFilter, DialVerificarAssinantes, ProxyAuthenticator

public class ServicoVerificarAssinatura extends Thread
{

    private String caminhoDeclaracao;
    private File fileDeclaracao;
    private File fileAssinatura;
    private boolean flagStop;
    public Logger logger;

    public ServicoVerificarAssinatura()
    {
        fileDeclaracao = null;
        fileAssinatura = null;
        flagStop = true;
        logger = Logger.getLogger(br.gov.pbh.desif.service.certificacao.ServicoVerificarAssinatura.class.getName());
    }

    public void run()
    {
        do
            try
            {
                for(; verificarDocumento(); stop())
                    interrupt();

                while(!interrupted()) 
                {
                    interrupt();
                    stop();
                }
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        while(true);
    }

    public boolean verificarDocumento()
    {
        boolean resposta = false;
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
            fileDeclaracao = new File(caminhoDeclaracao);
            if(!fileDeclaracao.exists())
            {
                if(SwingUtils.msgQues(null, "Arquivo da declaracao inexistente. \n Deseja procurar em outro local?") == 0)
                {
                    JFileChooser txtFileChooser = new JFileChooser(RegUtil.caminhoDiretorioPadrao);
                    txtFileChooser.setFileFilter(new TxtFileFilter());
                    int txtStatus = txtFileChooser.showOpenDialog(null);
                    if(txtStatus == 0)
                    {
                        fileDeclaracao = txtFileChooser.getSelectedFile();
                        fileAssinatura = new File((new StringBuilder()).append(fileDeclaracao.getName()).append(".p7s").toString());
                        if(!fileAssinatura.exists())
                            if(SwingUtils.msgQues(null, "Arquivo de assinatura inexistente. \n Deseja procurar em outro local?") == 0)
                            {
                                JFileChooser p7sFileChooser = new JFileChooser(".");
                                p7sFileChooser.setFileFilter(new P7sFileFilter());
                                int p7sStatus = p7sFileChooser.showOpenDialog(null);
                                if(p7sStatus == 0)
                                {
                                    fileAssinatura = txtFileChooser.getSelectedFile();
                                    if(flagStop)
                                    {
                                        EnvelopeInfo envelope = DigitalSignatureManager.verifySignature(fileAssinatura.getAbsolutePath(), fileDeclaracao.getAbsolutePath());
                                        DialVerificarAssinantes dialVer = new DialVerificarAssinantes(new JFrame(), true, envelope, fileDeclaracao.getPath(), fileAssinatura.getPath());
                                        dialVer.setVisible(true);
                                    }
                                } else
                                if(p7sStatus == 1)
                                    SwingUtils.msgAlerte(null, "Assinatura da Declaracao n\343o encontrada.");
                            } else
                            {
                                SwingUtils.msgAlerte(null, "Arquivo de assinatura n\343o encontrado.");
                            }
                    } else
                    if(txtStatus == 1)
                        SwingUtils.msgAlerte(null, "Declaracao n\343o encontrada.");
                } else
                {
                    SwingUtils.msgAlerte(null, "Declaracao n\343o encontrada.");
                }
            } else
            {
                fileAssinatura = new File((new StringBuilder()).append(fileDeclaracao.getPath()).append(".p7s").toString());
                if(!fileAssinatura.exists())
                {
                    SwingUtils.msgErro(null, "N\343o existe o arquivo de assinatura com extens\343o .p7s");
                    for(; !interrupted(); interrupt());
                    flagStop = false;
                    resposta = false;
                } else
                {
                    try
                    {
                        if(flagStop)
                        {
                            EnvelopeInfo envelope = DigitalSignatureManager.verifySignature(fileAssinatura.getAbsolutePath(), fileDeclaracao.getAbsolutePath());
                            if(envelope.getSignatureStatus() == SignatureStatus.Invalid)
                                throw new GeneralSecurityException("Assinatura Inv\341lida");
                            DialVerificarAssinantes dialVer = new DialVerificarAssinantes(new JFrame(), true, envelope, fileDeclaracao.getPath(), fileAssinatura.getPath());
                            dialVer.setVisible(true);
                            for(; !interrupted(); interrupt());
                            flagStop = false;
                            resposta = false;
                        }
                        resposta = true;
                    }
                    catch(Exception ex)
                    {
                        SwingUtils.msgErro(null, "Assinatura e o arquivo original n\343o conferem!");
                        for(; !interrupted(); interrupt());
                        flagStop = false;
                        resposta = false;
                    }
                }
            }
        }
        catch(Exception ex)
        {
            RegUtil.imprimirErro(ex, logger);
            ex.printStackTrace();
            if(ex.getCause().getMessage() != null)
            {
                SwingUtils.msgErro(null, ex.getCause().getMessage());
                resposta = false;
            }
            resposta = false;
            for(; !interrupted(); interrupt());
            flagStop = false;
            resposta = false;
        }
        return resposta;
    }

    public void configuraDadosCache()
    {
        Configuracao conf = (Configuracao)Contexto.getObject("configuracao");
        ProxyConfiguration proxy = ProxyConfiguration.getInstance();
        if(conf.getConfProxy() == 2)
            switch(conf.getTipoProxy())
            {
            case 1: // '\001'
                proxy.setProxyAuthorization(true);
                String host = conf.getHost();
                int porta = Integer.parseInt(conf.getPorta());
                String user = conf.getProxyAut().getPasswordAuthentication().getUserName();
                String senha = new String(conf.getProxyAut().getPasswordAuthentication().getPassword());
                proxy.setProxyConfiguration(host, porta, user, senha);
                break;

            case 3: // '\003'
                proxy.setProxyConfiguration(conf.getHost(), Integer.parseInt(conf.getPorta()));
                break;
            }
    }

    public void setCaminhoDeclaracao(String caminhoDeclaracao)
    {
        this.caminhoDeclaracao = caminhoDeclaracao;
    }

    public String getCaminhoDeclaracao()
    {
        return caminhoDeclaracao;
    }
}
