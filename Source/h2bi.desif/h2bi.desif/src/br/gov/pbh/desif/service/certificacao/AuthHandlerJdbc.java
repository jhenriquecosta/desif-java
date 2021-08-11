
package br.gov.pbh.desif.service.certificacao;

import java.io.*;
import javax.security.auth.callback.*;
import javax.swing.JOptionPane;

// Referenced classes of package br.gov.pbh.desif.service.certificacao:
//            CancelReadPasswordException, ServicoRecuperarSenhaA1

public class AuthHandlerJdbc
    implements CallbackHandler
{

    private int pkcsNum;
    protected String providerInfo;
    private ServicoRecuperarSenhaA1 servicoRecuperarSenhaA1;
    private static final boolean DEBUG_IS_ACTIVE = true;

    public AuthHandlerJdbc()
    {
    }

    public void DEBUG(String msg)
    {
        System.out.println(msg);
    }

    public void handle(Callback callbacks[])
        throws IOException, UnsupportedCallbackException
    {
        for(int i = 0; i < callbacks.length; i++)
        {
            if(callbacks[i] instanceof TextOutputCallback)
            {
                TextOutputCallback toc = (TextOutputCallback)callbacks[i];
                switch(toc.getMessageType())
                {
                case 0: // '\0'
                    JOptionPane.showMessageDialog(null, toc.getMessage());
                    break;

                case 2: // '\002'
                    JOptionPane.showMessageDialog(null, (new StringBuilder()).append("Erro: ").append(toc.getMessage()).toString());
                    break;

                case 1: // '\001'
                    JOptionPane.showMessageDialog(null, (new StringBuilder()).append("Aviso: ").append(toc.getMessage()).toString());
                    break;

                default:
                    throw new IOException((new StringBuilder()).append("Tipo de erro desconhecido: ").append(toc.getMessageType()).toString());
                }
                continue;
            }
            if(callbacks[i] instanceof NameCallback)
            {
                NameCallback nc = (NameCallback)callbacks[i];
                System.err.print(nc.getPrompt());
                System.err.flush();
                nc.setName((new BufferedReader(new InputStreamReader(System.in))).readLine());
                continue;
            }
            if(callbacks[i] instanceof PasswordCallback)
            {
                PasswordCallback pc = (PasswordCallback)callbacks[i];
                pc.setPassword(readPassword());
                DEBUG("Senha obtida.");
            } else
            {
                throw new UnsupportedCallbackException(callbacks[i], "Chamada de volta n\uFFFDo reconhecida");
            }
        }

    }

    private char[] readPassword()
        throws CancelReadPasswordException
    {
        try
        {
            return servicoRecuperarSenhaA1.recuperarSenhaCertificado();
        }
        catch(IOException e)
        {
            throw new CancelReadPasswordException("Erro recuperacao senha certificado");
        }
    }

    public String getProviderInfo()
    {
        return providerInfo;
    }

    public void setProviderInfo(String providerInfo)
    {
        this.providerInfo = providerInfo;
    }

    public ServicoRecuperarSenhaA1 getServicoRecuperarSenhaA1()
    {
        return servicoRecuperarSenhaA1;
    }

    public void setServicoRecuperarSenhaA1(ServicoRecuperarSenhaA1 servicoRecuperarSenhaA1)
    {
        this.servicoRecuperarSenhaA1 = servicoRecuperarSenhaA1;
    }
}
