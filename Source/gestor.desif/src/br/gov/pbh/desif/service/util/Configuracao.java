/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.service.util;

import br.gov.pbh.desif.service.certificacao.ProxyAuthenticator;
import java.net.Authenticator;

public class Configuracao {
    private String host;
    private String porta;
    private ProxyAuthenticator proxyAut;
    private String diretorioPadrao;
    private String login;
    private String Senha;
    private int tipoProxy;
    private int confProxy;

    public void iniciarConfiguracoesProxy(String host, String porta, String login, String senha) {
        if (this.confProxy == 1) {
            System.setProperty("java.net.useSystemProxies", "true");
            return;
        }
        System.setProperty("java.net.useSystemProxies", "false");
        if (!(Configuracao.verificarNuloOuVazio(host) || Configuracao.verificarNuloOuVazio(porta) || Configuracao.verificarNuloOuVazio(login))) {
            this.configuraProxy(host.trim(), porta.trim(), login.trim(), senha.trim());
            this.tipoProxy = 1;
            this.setConfProxy(2);
        }
        if (Configuracao.verificarNuloOuVazio(host) && Configuracao.verificarNuloOuVazio(porta) && Configuracao.verificarNuloOuVazio(login)) {
            this.configuraProxy();
            this.tipoProxy = 2;
            this.setConfProxy(2);
        }
        if (!Configuracao.verificarNuloOuVazio(host) && !Configuracao.verificarNuloOuVazio(porta) && Configuracao.verificarNuloOuVazio(login)) {
            this.configuraProxy(host.trim(), porta.trim());
            this.tipoProxy = 3;
            this.setConfProxy(2);
        }
    }

    public static boolean verificarNuloOuVazio(String valor) {
        if (valor == null) {
            return true;
        }
        if (valor.trim().length() == 0) {
            return true;
        }
        return false;
    }

    public void configuraProxy() {
        System.getProperties().put("http.proxySet", "false");
    }

    public void configuraProxy(String host, String porta) {
        System.getProperties().put("http.proxySet", "true");
        this.setHost(host);
        this.setPorta(porta);
    }

    public void configuraProxy(String host, String porta, String login, String senha) {
        System.getProperties().put("http.proxySet", "true");
        this.setHost(host);
        this.setPorta(porta);
        this.setLogin(login);
        this.setSenha(senha);
        this.proxyAut = new ProxyAuthenticator(login, senha);
        this.setProxyAut(this.proxyAut);
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
        System.getProperties().put("http.proxyHost", host);
    }

    public String getPorta() {
        return this.porta;
    }

    public void setPorta(String porta) {
        this.porta = porta;
        System.getProperties().put("http.proxyPort", porta);
    }

    public ProxyAuthenticator getProxyAut() {
        return this.proxyAut;
    }

    public void setProxyAut(ProxyAuthenticator proxyAut) {
        this.proxyAut = proxyAut;
        Authenticator.setDefault(proxyAut);
    }

    public String getDiretorioPadrao() {
        return this.diretorioPadrao;
    }

    public void setDiretorioPadrao(String diretorioPadrao) {
        this.diretorioPadrao = diretorioPadrao;
    }

    public String getSenha() {
        return this.Senha;
    }

    public void setSenha(String Senha) {
        this.Senha = Senha;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getTipoProxy() {
        return this.tipoProxy;
    }

    public void setTipoProxy(int tipoProxy) {
        this.tipoProxy = tipoProxy;
    }

    public int getConfProxy() {
        return this.confProxy;
    }

    public void setConfProxy(int confProxy) {
        this.confProxy = confProxy;
    }
}

