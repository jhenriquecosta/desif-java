// 
// Decompiled by Procyon v0.5.30
// 

package br.gov.pbh.desif;

import br.gov.pbh.desif.arquivosbanco.CopiaArquivosBanco;
import br.gov.pbh.desif.service.util.Configuracao;
import br.gov.pbh.desif.service.contexto.ManipuladoraProperties;
import br.gov.pbh.desif.model.registros.RegUtil;
import javax.swing.UnsupportedLookAndFeelException;
import java.util.logging.Level;
import java.util.logging.Logger;
import br.gov.pbh.des.componentes.utils.DesLookandFeel;
import br.gov.pbh.des.view.splash.SplashScreen;
import java.util.Date;
import java.io.PrintStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.File;
import br.gov.pbh.desif.service.contexto.Contexto;
import br.gov.pbh.desif.view.telas.FrmPrincipal;
import javax.swing.JOptionPane;
import br.gov.pbh.des.view.splash.SplashScreenMain;

public class Main implements SplashScreenMain
{
    private static boolean iniciado;
    
    public Main() 
    {
        this.execute();
    }
    
    public void execute() {
        final JustOneLock ua = new JustOneLock("JustOneId");
        if (ua.isAppActive()) {
            JOptionPane.showMessageDialog(null, "J\u00e1 existe uma aplica\u00e7\u00e3o rodando....", "Mensagem alerta", 0);
            System.exit(1);
        }
        else {
            try {
                final long startTime = System.currentTimeMillis();
             //   this.runSplash();
                configurarPropriedades();
                ((FrmPrincipal)Contexto.getObject("frmPrincipal")).setVisible(true);
                final long endTime = System.currentTimeMillis();
                final long totalTime = endTime - startTime;
                System.out.println("Tempo total = " + totalTime / 1000L + " seg");
                Main.iniciado = true;
                this.trocarSaidaPadrao();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void trocarSaidaPadrao() {
        try {
            final String logdir = System.getProperty("user.home") + File.separator + ".desif";
            final PrintStream ps = new PrintStream(new BufferedOutputStream(new FileOutputStream(new File(logdir, "saidalog.txt"))), true);
            System.setOut(ps);
            System.setErr(ps);
            System.out.println("-----------------------------" + new Date().toString() + "------------------------------------------");
            System.out.println("32 or 64 ? " + System.getProperty("os.arch"));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void runSplash() {
        final SplashScreen splash = SplashScreen.getInstance();
        splash.setMain((SplashScreenMain)this);
        final Thread tSplash = new Thread((Runnable)splash);
        tSplash.start();
        splash.setMsgAtual("Inicializando...");
    }
    
    public static void main(final String[] args) {
        Main.iniciado = false;
        configurarLookandFeel();
        new Main();
    }
    
    public static void configurarLookandFeel() {
        try {
            DesLookandFeel.getInstance().setDesLookAndFeel();
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex2) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (IllegalAccessException ex3) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex3);
        }
        catch (UnsupportedLookAndFeelException ex4) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex4);
        }
    }
    
    public static void configurarPropriedades() {
        try {
            final String caminhoPropriedades = RegUtil.caminhoPropriedades = System.getProperty("user.home") + File.separator + ".desif" + File.separator + "config.properties";
            final ManipuladoraProperties mp = new ManipuladoraProperties(caminhoPropriedades);
            mp.carregarPropriedades();
            final String ver = mp.obterPropriedade("versao");
            RegUtil.caminhoDiretorioPadrao = mp.obterPropriedade("diretorioPadrao");
            if (ver == null) {
                mp.alterarPropriedade("versao", "4.2");
                final String caminhoBanco = RegUtil.caminhoBanco = System.getProperty("user.home") + File.separator + ".desif";
                mp.alterarPropriedade("caminhoBanco", "jdbc:hsqldb:file:" + caminhoBanco + File.separator + "des_if");
                copiarBanco(caminhoBanco);
            }
            else {
                final double versao = Double.parseDouble(ver);
                mp.alterarPropriedade("versao", "4.2");
                final String caminhoBanco2 = System.getProperty("user.home") + File.separator + ".desif";
                mp.alterarPropriedade("caminhoBanco", "jdbc:hsqldb:file:" + caminhoBanco2 + File.separator + "des_if");
                copiarBanco(caminhoBanco2);
                configurarProxy(mp);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void configurarProxy(final ManipuladoraProperties mp) {
        final Configuracao conf = (Configuracao)Contexto.getObject("configuracao");
        final String confProxy = mp.obterPropriedade("confProxy");
        final String host = mp.obterPropriedade("http");
        final String porta = mp.obterPropriedade("porta");
        final String login = mp.obterPropriedade("login");
        final String senha = mp.obterPropriedade("senha");
        if (confProxy != null) {
            conf.setConfProxy(Integer.parseInt(confProxy));
        }
        else {
            conf.setConfProxy(0);
        }
        conf.iniciarConfiguracoesProxy(host, porta, login, senha);
    }
    
    public static void copiarBanco(final String caminhoBanco)
    {
        final CopiaArquivosBanco cab = new CopiaArquivosBanco(caminhoBanco);
         cab.copiar();
    }
    
    public boolean isIniciado()
    {
        return Main.iniciado;
    }
    
    static {
        Main.iniciado = false;
    }
}
