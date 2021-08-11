package br.gov.pbh.desif.service.util;

import br.gov.pbh.desif.service.certificacao.ProxyAuthenticator;
import java.net.Authenticator;
import java.util.Properties;

public class Configuracao
{

    private String host;
    private String porta;
    private ProxyAuthenticator proxyAut;
    private String diretorioPadrao;
    private String login;
    private String Senha;
    private int tipoProxy;
    private int confProxy;

    public Configuracao()
    {
    }

    public void iniciarConfiguracoesProxy(String host, String porta, String login, String senha)
    {
        if(confProxy == 1)
        {
            System.setProperty("java.net.useSystemProxies", "true");
            return;
        }
        System.setProperty("java.net.useSystemProxies", "false");
        if(!verificarNuloOuVazio(host) && !verificarNuloOuVazio(porta) && !verificarNuloOuVazio(login))
        {
            configuraProxy(host.trim(), porta.trim(), login.trim(), senha.trim());
            tipoProxy = 1;
            setConfProxy(2);
        }
        if(verificarNuloOuVazio(host) && verificarNuloOuVazio(porta) && verificarNuloOuVazio(login))
        {
            configuraProxy();
            tipoProxy = 2;
            setConfProxy(2);
        }
        if(!verificarNuloOuVazio(host) && !verificarNuloOuVazio(porta) && verificarNuloOuVazio(login))
        {
            configuraProxy(host.trim(), porta.trim());
            tipoProxy = 3;
            setConfProxy(2);
        }
    }

    public static boolean verificarNuloOuVazio(String valor)
    {
        if(valor == null)
            return true;
        return valor.trim().length() == 0;
    }

    public void configuraProxy()
    {
        System.getProperties().put("http.proxySet", "false");
    }

    public void configuraProxy(String host, String porta)
    {
        System.getProperties().put("http.proxySet", "true");
        setHost(host);
        setPorta(porta);
    }

    public void configuraProxy(String host, String porta, String login, String senha)
    {
        System.getProperties().put("http.proxySet", "true");
        setHost(host);
        setPorta(porta);
        setLogin(login);
        setSenha(senha);
        proxyAut = new ProxyAuthenticator(login, senha);
        setProxyAut(proxyAut);
    }

    public String getHost()
    {
        return host;
    }

    public void setHost(String host)
    {
        this.host = host;
        System.getProperties().put("http.proxyHost", host);
    }

    public String getPorta()
    {
        return porta;
    }

    public void setPorta(String porta)
    {
        this.porta = porta;
        System.getProperties().put("http.proxyPort", porta);
    }

    public ProxyAuthenticator getProxyAut()
    {
        return proxyAut;
    }

    public void setProxyAut(ProxyAuthenticator proxyAut)
    {
        this.proxyAut = proxyAut;
        Authenticator.setDefault(proxyAut);
    }

    public String getDiretorioPadrao()
    {
        return diretorioPadrao;
    }

    public void setDiretorioPadrao(String diretorioPadrao)
    {
        this.diretorioPadrao = diretorioPadrao;
    }

    public String getSenha()
    {
        return Senha;
    }

    public void setSenha(String Senha)
    {
        this.Senha = Senha;
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public int getTipoProxy()
    {
        return tipoProxy;
    }

    public void setTipoProxy(int tipoProxy)
    {
        this.tipoProxy = tipoProxy;
    }

    public int getConfProxy()
    {
        return confProxy;
    }

    public void setConfProxy(int confProxy)
    {
        this.confProxy = confProxy;
    }
}
