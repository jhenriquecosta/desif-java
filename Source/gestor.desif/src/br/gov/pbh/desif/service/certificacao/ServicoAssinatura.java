
package br.gov.pbh.desif.service.certificacao;

import br.com.esec.asn1.ByteSourceException;
import br.com.esec.net.ProxyConfiguration;
import br.com.esec.sdk.device.CryptoDeviceException;
import br.gov.pbh.desif.esec.assinatura.CancelPasswordException;
import br.gov.pbh.desif.esec.assinatura.DigitalSignatureManager;
import br.gov.pbh.desif.esec.assinatura.InvalidPasswordException;
import br.gov.pbh.desif.esec.gui.NoSelectedCertificateException;
import br.gov.pbh.desif.model.registros.RegUtil;
import br.gov.pbh.desif.service.contexto.Contexto;
import br.gov.pbh.desif.service.util.Configuracao;
import br.gov.pbh.desif.view.util.SwingUtils;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import org.apache.log4j.Logger;

public class ServicoAssinatura extends Thread 
{
    private String caminhoDeclaracao = RegUtil.caminhoArquivo;
    private FileInputStream fis = null;
    private FileOutputStream fous = null;
    private byte[] bufferDeclaracaoAssinada = null;
    boolean flagStop = true;
    public Logger logger = Logger.getLogger((String)ServicoAssinatura.class.getName());

    @Override
    public void run() 
    {
    }

    public void configuraDadosCache() {
        Configuracao conf = (Configuracao)Contexto.getObject("configuracao");
        ProxyConfiguration proxy = ProxyConfiguration.getInstance();
        if (conf.getConfProxy() == 2)
        {
            switch (conf.getTipoProxy()) 
            {
                case 1: 
                {
                    proxy.setProxyAuthorization(true);
                    String host = conf.getHost();
                    int porta = Integer.parseInt(conf.getPorta());
                    String user = conf.getProxyAut().getPasswordAuthentication().getUserName();
                    String senha = new String(conf.getProxyAut().getPasswordAuthentication().getPassword());
                    proxy.setProxyConfiguration(host, porta, user, senha);
                    break;
                }
                case 2: 
                {
                    break;
                }
                case 3: {
                    proxy.setProxyConfiguration(conf.getHost(), Integer.parseInt(conf.getPorta()));
                }
            }
        }
    }

    public boolean assinar() throws CancelPasswordException, InvalidPasswordException, GeneralSecurityException, CryptoDeviceException, IOException, ByteSourceException, NoSelectedCertificateException {
        DigitalSignatureManager.signFile(this.caminhoDeclaracao, true);
        SwingUtils.msgAlerte(null, "Assinatura da Declaracao no diretorio: \n" + this.caminhoDeclaracao + " foi realizado com sucesso. \nO arquivo de assinatura " + this.caminhoDeclaracao + ".p7s foi gerado.");
        return true;
    }
}

