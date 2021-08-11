
package br.gov.pbh.desif.service.certificacao;

import br.com.esec.net.ProxyConfiguration;
import br.gov.pbh.desif.esec.assinatura.DigitalSignatureManager;
import br.gov.pbh.desif.esec.assinatura.EnvelopeInfo;
import br.gov.pbh.desif.esec.assinatura.SignatureStatus;
import br.gov.pbh.desif.model.registros.RegUtil;
import br.gov.pbh.desif.service.contexto.Contexto;
import br.gov.pbh.desif.service.util.Configuracao;
import br.gov.pbh.desif.view.util.SwingUtils;
import java.io.File;
import java.security.GeneralSecurityException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import org.apache.log4j.Appender;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

public class ServicoVerificarAssinatura
extends Thread {
    private String caminhoDeclaracao;
    private File fileDeclaracao = null;
    private File fileAssinatura = null;
    private boolean flagStop = true;
    public Logger logger = Logger.getLogger((String)ServicoVerificarAssinatura.class.getName());


    @Override
    public void run()
    {
        do
            try
            {
                for(; verificarDocumento();stop())
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
            this.logger.setLevel(Level.ERROR);
            String caminhoDiretorio = System.getProperty("user.home") + File.separator + ".desif";
            String caminhoLog = caminhoDiretorio + File.separator + "log.log";
            RollingFileAppender fileAppender = new RollingFileAppender((Layout)new PatternLayout("%m%n"), caminhoLog);
            fileAppender.setMaxBackupIndex(1);
            fileAppender.setMaxFileSize("100KB");
            logger.addAppender((Appender)fileAppender);
            fileDeclaracao = new File(caminhoDeclaracao);
            if (!fileDeclaracao.exists())
            {
                if (SwingUtils.msgQues(null, "Arquivo da declaracao inexistente. \n Deseja procurar em outro local?") == 0) 
                {
                    JFileChooser txtFileChooser = new JFileChooser(RegUtil.caminhoDiretorioPadrao);
                    txtFileChooser.setFileFilter(new TxtFileFilter());
                    int txtStatus = txtFileChooser.showOpenDialog(null);
                    if (txtStatus == 0) 
                    {
                        this.fileDeclaracao = txtFileChooser.getSelectedFile();
                        this.fileAssinatura = new File(this.fileDeclaracao.getName() + ".p7s");
                        if (!this.fileAssinatura.exists()) {
                            if (SwingUtils.msgQues(null, "Arquivo de assinatura inexistente. \n Deseja procurar em outro local?") == 0) {
                                JFileChooser p7sFileChooser = new JFileChooser(".");
                                p7sFileChooser.setFileFilter(new P7sFileFilter());
                                int p7sStatus = p7sFileChooser.showOpenDialog(null);
                                if (p7sStatus == 0) {
                                    this.fileAssinatura = txtFileChooser.getSelectedFile();
                                    if (this.flagStop) {
                                        EnvelopeInfo envelope = DigitalSignatureManager.verifySignature(this.fileAssinatura.getAbsolutePath(), this.fileDeclaracao.getAbsolutePath());
                                        DialVerificarAssinantes dialVer = new DialVerificarAssinantes(new JFrame(), true, envelope, this.fileDeclaracao.getPath(), this.fileAssinatura.getPath());
                                        dialVer.setVisible(true);
                                    }
                                } else if (p7sStatus == 1) {
                                    SwingUtils.msgAlerte(null, "Assinatura da Declaracao n\u00e3o encontrada.");
                                }
                            } else {
                                SwingUtils.msgAlerte(null, "Arquivo de assinatura n\u00e3o encontrado.");
                            }
                        }
                    } else if (txtStatus == 1) {
                        SwingUtils.msgAlerte(null, "Declaracao n\u00e3o encontrada.");
                    }
                } else {
                    SwingUtils.msgAlerte(null, "Declaracao n\u00e3o encontrada.");
                }
            }
            else 
            {
                fileAssinatura = new File(fileDeclaracao.getPath() + ".p7s");
                if (!fileAssinatura.exists())
                {
                    SwingUtils.msgErro(null, "N\u00e3o existe o arquivo de assinatura com extens\u00e3o .p7s");
                    while (!ServicoVerificarAssinatura.interrupted())
                    {
                        this.interrupt();
                    }
                    this.flagStop = false;
                    resposta = false;
                }
                else 
                {
                    try 
                    {
                        if (this.flagStop) 
                        {
                            EnvelopeInfo envelope = DigitalSignatureManager.verifySignature(this.fileAssinatura.getAbsolutePath(), this.fileDeclaracao.getAbsolutePath());
                            if (envelope.getSignatureStatus() == SignatureStatus.Invalid)
                            {
                                throw new GeneralSecurityException("Assinatura Inv\u00e1lida");
                            }
                            DialVerificarAssinantes dialVer = new DialVerificarAssinantes(new JFrame(), true, envelope, this.fileDeclaracao.getPath(), this.fileAssinatura.getPath());
                            dialVer.setVisible(true);
                            while (!ServicoVerificarAssinatura.interrupted()) 
                            {
                                this.interrupt();
                            }
                            this.flagStop = false;
                            resposta = false;
                        }
                        resposta = true;
                    }
                    catch (Exception ex) 
                    {
                        SwingUtils.msgErro(null, "Assinatura e o arquivo original n\u00e3o conferem!");
                        while (!ServicoVerificarAssinatura.interrupted()) 
                        {
                            this.interrupt();
                        }
                        this.flagStop = false;
                        resposta = false;
                    }
                }
            }
        }
        catch (Exception ex) 
        {
            RegUtil.imprimirErro(ex, this.logger);
            ex.printStackTrace();
            if (ex.getCause().getMessage() != null) {
                SwingUtils.msgErro(null, ex.getCause().getMessage());
                resposta = false;
            }
            resposta = false;
            while (!ServicoVerificarAssinatura.interrupted()) {
                this.interrupt();
            }
            this.flagStop = false;
            resposta = false;
        }
        return resposta;
    }

    public void configuraDadosCache() {
        Configuracao conf = (Configuracao)Contexto.getObject("configuracao");
        ProxyConfiguration proxy = ProxyConfiguration.getInstance();
        if (conf.getConfProxy() == 2) {
            switch (conf.getTipoProxy()) {
                case 1: {
                    proxy.setProxyAuthorization(true);
                    String host = conf.getHost();
                    int porta = Integer.parseInt(conf.getPorta());
                    String user = conf.getProxyAut().getPasswordAuthentication().getUserName();
                    String senha = new String(conf.getProxyAut().getPasswordAuthentication().getPassword());
                    proxy.setProxyConfiguration(host, porta, user, senha);
                    break;
                }
                case 2: {
                    break;
                }
                case 3: {
                    proxy.setProxyConfiguration(conf.getHost(), Integer.parseInt(conf.getPorta()));
                }
            }
        }
    }

    public void setCaminhoDeclaracao(String caminhoDeclaracao) {
        this.caminhoDeclaracao = caminhoDeclaracao;
    }

    public String getCaminhoDeclaracao() {
        return this.caminhoDeclaracao;
    }
}

