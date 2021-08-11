
package br.gov.pbh.desif;

import br.gov.pbh.des.componentes.utils.DesLookandFeel;
import br.gov.pbh.des.view.splash.SplashScreen;
import br.gov.pbh.des.view.splash.SplashScreenMain;
import br.gov.pbh.desif.arquivosbanco.CopiaArquivosBanco;
import br.gov.pbh.desif.model.registros.RegUtil;
import br.gov.pbh.desif.service.contexto.Contexto;
import br.gov.pbh.desif.service.contexto.ManipuladoraProperties;
import br.gov.pbh.desif.service.util.Configuracao;
import br.gov.pbh.desif.view.telas.FrmPrincipal;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;

// Referenced classes of package br.gov.pbh.desif:
//            JustOneLock

public class Main implements SplashScreenMain
{

    private static boolean iniciado = false;

    public Main()
    {
        execute();
    }

    public void execute()
    {
        JustOneLock ua = new JustOneLock("JustOneId");
        if(ua.isAppActive())
        {
            JOptionPane.showMessageDialog(null, "J\341 existe uma aplica\347\343o rodando....", "Mensagem alerta", 0);
            System.exit(1);
        } else
        {
            try
            {
                trocarSaidaPadrao();
                long startTime = System.currentTimeMillis();
            //    runSplash();
                configurarPropriedades();
                ((FrmPrincipal)Contexto.getObject("frmPrincipal")).setVisible(true);
                long endTime = System.currentTimeMillis();
                long totalTime = endTime - startTime;
                System.out.println((new StringBuilder()).append("Tempo total = ").append(totalTime / 1000L).append(" seg").toString());
                iniciado = true;
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    
    

    public void trocarSaidaPadrao()
    {
        try
        {
            String logdir = (new StringBuilder()).append(System.getProperty("user.home")).append(File.separator).append(".desif").toString();
            PrintStream ps = new PrintStream(new BufferedOutputStream(new FileOutputStream(new File(logdir, "saidalog.txt"))), true);
            System.setOut(ps);
            System.setErr(ps);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void runSplash()
    {
        SplashScreen splash = SplashScreen.getInstance();
        splash.setMain(this);
        Thread tSplash = new Thread(splash);
        tSplash.start();
        splash.setMsgAtual("BCP LOADING...");
    }

    public static void main(String args[])
    {
        iniciado = false;
        configurarLookandFeel();
        new Main();
    }

    public static void configurarLookandFeel()
    {
        try
        {
            DesLookandFeel.getInstance().setDesLookAndFeel();
        }
        catch(ClassNotFoundException ex)
        {
            Logger.getLogger(br.gov.pbh.desif.Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(InstantiationException ex)
        {
            Logger.getLogger(br.gov.pbh.desif.Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(IllegalAccessException ex)
        {
            Logger.getLogger(br.gov.pbh.desif.Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(UnsupportedLookAndFeelException ex)
        {
            Logger.getLogger(br.gov.pbh.desif.Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void configurarPropriedades()
    {
        try
        {
            String caminhoPropriedades = (new StringBuilder()).append(System.getProperty("user.home")).append(File.separator).append(".desif").append(File.separator).append("config.properties").toString();
            RegUtil.caminhoPropriedades = caminhoPropriedades;
            ManipuladoraProperties mp = new ManipuladoraProperties(caminhoPropriedades);
            mp.carregarPropriedades();
            String ver = mp.obterPropriedade("versao");
            RegUtil.caminhoDiretorioPadrao = mp.obterPropriedade("diretorioPadrao");
            if(ver == null)
            {
                mp.alterarPropriedade("versao", "4.05");
                String caminhoBanco = (new StringBuilder()).append(System.getProperty("user.home")).append(File.separator).append(".desif").toString();
                RegUtil.caminhoBanco = caminhoBanco;
                mp.alterarPropriedade("caminhoBanco", (new StringBuilder()).append("jdbc:hsqldb:file:").append(caminhoBanco).append(File.separator).append("des_if").toString());
                copiarBanco(caminhoBanco);
            } else
            {
                double versao = Double.parseDouble(ver);
                if(4.0499999999999998D > versao)
                {
                    mp.alterarPropriedade("versao", "4.05");
                    String caminhoBanco = (new StringBuilder()).append(System.getProperty("user.home")).append(File.separator).append(".desif").toString();
                    mp.alterarPropriedade("caminhoBanco", (new StringBuilder()).append("jdbc:hsqldb:file:").append(caminhoBanco).append(File.separator).append("des_if").toString());
                    copiarBanco(caminhoBanco);
                }
               // remarcado by Henrique
                configurarProxy(mp);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public static void configurarProxy(ManipuladoraProperties mp)
    {
        Configuracao conf = (Configuracao)Contexto.getObject("configuracao");
        String confProxy = mp.obterPropriedade("confProxy");
        String host = mp.obterPropriedade("http");
        String porta = mp.obterPropriedade("porta");
        String login = mp.obterPropriedade("login");
        String senha = mp.obterPropriedade("senha");
        if(confProxy != null)
            conf.setConfProxy(Integer.parseInt(confProxy));
        else
            conf.setConfProxy(0);
            conf.iniciarConfiguracoesProxy(host, porta, login, senha);
    }
    public static void copiarBanco(String caminhoBanco)
    {
        CopiaArquivosBanco cab = new CopiaArquivosBanco(caminhoBanco);
        cab.copiar();
    }
    public boolean isIniciado()
    {
        return iniciado;
    }

}