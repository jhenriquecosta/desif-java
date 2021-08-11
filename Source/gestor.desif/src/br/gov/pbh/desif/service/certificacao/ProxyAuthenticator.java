/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.service.certificacao;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

public class ProxyAuthenticator
extends Authenticator {
    private String user;
    private String password;

    public ProxyAuthenticator(String user, String password) {
        this.user = user;
        this.password = password;
    }

    @Override
    public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(this.user, this.password.toCharArray());
    }
}

