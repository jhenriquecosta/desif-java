
package br.gov.pbh.desif.service.certificacao;

import java.io.IOException;

// Referenced classes of package br.gov.pbh.desif.service.certificacao:
//            ServicoRecuperarSenhaA1

public class ServicoRecuperarSenhaA1Jdbc
    implements ServicoRecuperarSenhaA1
{

    public ServicoRecuperarSenhaA1Jdbc()
    {
    }

    public char[] recuperarSenhaCertificado()
        throws IOException
    {
        String password = "1234";
        return password.toCharArray();
    }
}
