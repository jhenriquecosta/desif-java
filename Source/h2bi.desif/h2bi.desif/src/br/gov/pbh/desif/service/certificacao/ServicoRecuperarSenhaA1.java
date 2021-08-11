
package br.gov.pbh.desif.service.certificacao;

import java.io.IOException;

public interface ServicoRecuperarSenhaA1
{

    public abstract char[] recuperarSenhaCertificado()
        throws IOException;
}
