/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.service.certificacao;

import br.gov.pbh.desif.service.certificacao.CancelReadPasswordException;
import br.gov.pbh.desif.service.certificacao.ServicoRecuperarSenhaA1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.TextOutputCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.swing.JOptionPane;

public class AuthHandlerJdbc
implements CallbackHandler {
    private int pkcsNum;
    protected String providerInfo;
    private ServicoRecuperarSenhaA1 servicoRecuperarSenhaA1;
    private static final boolean DEBUG_IS_ACTIVE = true;

    public void DEBUG(String msg) {
        System.out.println(msg);
    }

    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        block5 : for (int i = 0; i < callbacks.length; ++i) {
            if (callbacks[i] instanceof TextOutputCallback) {
                TextOutputCallback toc = (TextOutputCallback)callbacks[i];
                switch (toc.getMessageType()) {
                    case 0: {
                        JOptionPane.showMessageDialog(null, toc.getMessage());
                        continue block5;
                    }
                    case 2: {
                        JOptionPane.showMessageDialog(null, "Erro: " + toc.getMessage());
                        continue block5;
                    }
                    case 1: {
                        JOptionPane.showMessageDialog(null, "Aviso: " + toc.getMessage());
                        continue block5;
                    }
                }
                throw new IOException("Tipo de erro desconhecido: " + toc.getMessageType());
            }
            if (callbacks[i] instanceof NameCallback) {
                NameCallback nc = (NameCallback)callbacks[i];
                System.err.print(nc.getPrompt());
                System.err.flush();
                nc.setName(new BufferedReader(new InputStreamReader(System.in)).readLine());
                continue;
            }
            if (callbacks[i] instanceof PasswordCallback) {
                PasswordCallback pc = (PasswordCallback)callbacks[i];
                pc.setPassword(this.readPassword());
                this.DEBUG("Senha obtida.");
                continue;
            }
            throw new UnsupportedCallbackException(callbacks[i], "Chamada de volta n\ufffdo reconhecida");
        }
    }

    private char[] readPassword() throws CancelReadPasswordException {
        try {
            return this.servicoRecuperarSenhaA1.recuperarSenhaCertificado();
        }
        catch (IOException e) {
            throw new CancelReadPasswordException("Erro recuperacao senha certificado");
        }
    }

    public String getProviderInfo() {
        return this.providerInfo;
    }

    public void setProviderInfo(String providerInfo) {
        this.providerInfo = providerInfo;
    }

    public ServicoRecuperarSenhaA1 getServicoRecuperarSenhaA1() {
        return this.servicoRecuperarSenhaA1;
    }

    public void setServicoRecuperarSenhaA1(ServicoRecuperarSenhaA1 servicoRecuperarSenhaA1) {
        this.servicoRecuperarSenhaA1 = servicoRecuperarSenhaA1;
    }
}

