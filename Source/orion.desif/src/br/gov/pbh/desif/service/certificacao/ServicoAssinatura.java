package br.gov.pbh.desif.service.certificacao;

import br.com.esec.asn1.ByteSourceException;
import br.com.esec.net.ProxyConfiguration;
import br.com.esec.sdk.device.CryptoDeviceException;
import br.gov.pbh.desif.esec.assinatura.*;
import br.gov.pbh.desif.esec.gui.NoSelectedCertificateException;
import br.gov.pbh.desif.model.registros.RegUtil;
import br.gov.pbh.desif.service.contexto.Contexto;
import br.gov.pbh.desif.service.util.Configuracao;
import br.gov.pbh.desif.view.util.SwingUtils;
import java.io.*;
import java.net.PasswordAuthentication;
import java.security.GeneralSecurityException;
import org.apache.log4j.Logger;

// Referenced classes of package br.gov.pbh.desif.service.certificacao:
//            ProxyAuthenticator

public class ServicoAssinatura extends Thread
{

    private String caminhoDeclaracao;
    private FileInputStream fis;
    private FileOutputStream fous;
    private byte bufferDeclaracaoAssinada[];
    boolean flagStop;
    public Logger logger;

    public ServicoAssinatura()
    {
        caminhoDeclaracao = RegUtil.caminhoArquivo;
        fis = null;
        fous = null;
        bufferDeclaracaoAssinada = null;
        flagStop = true;
        logger = Logger.getLogger(br.gov.pbh.desif.service.certificacao.ServicoAssinatura.class.getName());
    }

    public void run()
    {
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

    public boolean assinar() throws CancelPasswordException, InvalidPasswordException, GeneralSecurityException, CryptoDeviceException, IOException, ByteSourceException, NoSelectedCertificateException
    {
        DigitalSignatureManager.signFile(caminhoDeclaracao, true);
        SwingUtils.msgAlerte(null, (new StringBuilder()).append("Assinatura da Declaracao no diretorio: \n").append(caminhoDeclaracao).append(" foi realizado com sucesso. \n").append("O arquivo de assinatura ").append(caminhoDeclaracao).append(".p7s").append(" foi gerado.").toString());
        return true;
    }
}
