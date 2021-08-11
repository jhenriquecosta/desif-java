/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.service.certificacao;

import br.gov.pbh.desif.service.certificacao.ServicoRecuperarSenhaA1;
import java.io.IOException;

public class ServicoRecuperarSenhaA1Jdbc
implements ServicoRecuperarSenhaA1 {
    @Override
    public char[] recuperarSenhaCertificado() throws IOException {
        String password = "1234";
        return password.toCharArray();
    }
}

