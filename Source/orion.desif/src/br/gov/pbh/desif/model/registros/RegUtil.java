

package br.gov.pbh.desif.model.registros;

import br.gov.pbh.desif.model.pojo.Alerta;
import br.gov.pbh.desif.model.pojo.Erro;
import br.gov.pbh.desif.service.alerta.AlertaService;
import br.gov.pbh.desif.service.erro.ErroService;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import org.apache.log4j.Logger;

public class RegUtil
{

    public static boolean exErro = false;
    public static boolean exAlerte = false;
    public static int countErro = 0;
    public static int countAlerta = 0;
    public static String nomeArq = null;
    public static String caminhoArquivo = null;
    public static String caminhoPropriedades = null;
    public static String caminhoDiretorioPadrao = null;
    public static String caminhoBanco = null;
    public static String moduloDeclaracao = null;
    private ErroService erroServ;
    private AlertaService alertaServ;

    public RegUtil()
    {
        erroServ = new ErroService();
        alertaServ = new AlertaService();
    }

    public void setAlerta(long linha, String codAlerta, int coluna, short tipoErro, String registro)
    {
        if(registro.length() > 4)
            registro = registro.substring(0, 4);
        if(!exAlerte)
            exAlerte = true;
        countAlerta++;
        System.out.println((new StringBuilder()).append("codAlerta => ").append(codAlerta).toString());
        Alerta codigoAlerta = new Alerta(codAlerta);
        String nomeCampo = buscaNomeCampo(registro, coluna);
        alertaServ.addAlerta(linha, codigoAlerta, coluna, tipoErro, registro, nomeCampo);
    }

    public void setAlerta(long linha, String codAlerta, int coluna, short tipoErro, String registro, String valorCampoErro)
    {
        if(registro.length() > 4)
            registro = registro.substring(0, 4);
        if(!exAlerte)
            exAlerte = true;
        countAlerta++;
        System.out.println((new StringBuilder()).append("codAlerta => ").append(codAlerta).toString());
        Alerta codigoAlerta = new Alerta(codAlerta);
        String nomeCampo = buscaNomeCampo(registro, coluna);
        alertaServ.addAlerta(linha, codigoAlerta, coluna, tipoErro, registro, nomeCampo, valorCampoErro);
    }

    public void setErro(long linha, String codErro, int coluna, short tipoErro, String registro)
    {
        if(registro.length() > 4)
            registro = registro.substring(0, 4);
        if(!exErro)
            exErro = true;
        countErro++;
        System.out.println((new StringBuilder()).append("codErro => ").append(codErro).toString());
        Erro codigoErro = new Erro(codErro);
        String nomeCampo = buscaNomeCampo(registro, coluna);
        erroServ.addErro(linha, codigoErro, coluna, tipoErro, registro, nomeCampo);
    }

    public void setErro(long linha, String codErro, int coluna, short tipoErro, String registro, String valorCampoErro)
    {
        if(registro.length() > 4)
            registro = registro.substring(0, 4);
        if(!exErro)
            exErro = true;
        countErro++;
        System.out.println((new StringBuilder()).append("codErro => ").append(codErro).toString());
        Erro codigoErro = new Erro(codErro);
        String nomeCampo = buscaNomeCampo(registro, coluna);
        erroServ.addErro(linha, codigoErro, coluna, tipoErro, registro, nomeCampo, valorCampoErro);
    }

    public String contCaracterRegistro(int linha, String registro, int qtdCaracter, int coluna, String reg)
    {
        String retorno = registro;
        if(registro.length() > qtdCaracter)
        {
            String txtSolucao = (new StringBuilder()).append("Tamanho campo no layout: ").append(qtdCaracter).append(" Valor com erro: ").append(registro).append(" tamanho: ").append(registro.length()).toString();
            setErro(linha, "EG009", coluna, (short)1, reg, txtSolucao);
        }
        return retorno;
    }

    public boolean isNumeric(String s)
    {
        s = s.replace('.', ',');
        s = s.replace(',', '.');
        boolean resp = true;
        try
        {
            Double.parseDouble(s);
        }
        catch(NumberFormatException ex)
        {
            resp = false;
        }
        return resp;
    }

    public boolean verificaCasasDecimais(String s)
    {
        boolean resp = true;
        s = s.replace('.', ',');
        int indice = s.indexOf(",");
        if(indice > 0)
        {
            String array[] = s.split("\\,", -1);
            if(array.length > 0 && array[1].length() > 2)
                resp = false;
        }
        return resp;
    }

    public boolean isInteiro(String s)
    {
        boolean resp = true;
        try
        {
            Integer.parseInt(s);
        }
        catch(NumberFormatException ex)
        {
            resp = false;
        }
        return resp;
    }

    public String[] multiValorado(String campo, String token)
    {
        byte b[] = campo.getBytes();
        String resp[] = campo.split(token);
        return resp;
    }

    public boolean validaSequenciaLinha(int contLinha, String linhaArq)
    {
        boolean resp = false;
        try
        {
            int linArq = Integer.parseInt(linhaArq);
            if(contLinha == linArq)
                resp = true;
            return resp;
        }
        catch(Exception e)
        {
            return resp = false;
        }
    }

    public Date parseData(String data, String formato)
    {
        try
        {
            Locale ptBr = new Locale("pt", "BR");
            SimpleDateFormat sdf1 = new SimpleDateFormat(formato, ptBr);
            Date d = sdf1.parse(data);
            return d;
        }
        catch(ParseException ex)
        {
            return null;
        }
    }

    public String trocaVirgulaPonto(String str)
    {
        if(!str.equals("") && str != null)
        {
            str = str.replace('.', ',');
            str = str.replace(',', '.');
        } else
        {
            str = "0.00";
        }
        return str;
    }

    public String parseZero(String str)
    {
        String auxs = str;
        try
        {
            str = str.replace(',', '.');
            double aux = Double.parseDouble(str);
            if(aux == 0.0D)
                str = "";
        }
        catch(NumberFormatException ex)
        {
            str = auxs;
        }
        return str;
    }

    private static double arredondar(double x)
    {
        return (new BigDecimal(String.valueOf(x))).setScale(2, 4).doubleValue();
    }

    public double formataCasasDecimais(double valor)
    {
        valor = arredondar(valor);
        return valor;
    }

    public String getArquivoName(String caminho)
    {
        caminhoArquivo = caminho;
        String split = "";
        if(System.getProperty("file.separator").equals("\\"))
            split = "\\\\";
        else
            split = "\\//";
        String token[] = caminho.split(split);
        String nome = token[token.length - 1];
        nomeArq = nome;
        return nomeArq;
    }

    public String buscaNomeCampo(String registro, int campo)
    {
        String nomeCampo = "";
        if(isInteiro(registro))
            switch(Integer.parseInt(registro))
            {
            case 0: // '\0'
                String reg0000[] = {
                    "Num_Linha", "Reg", "CNPJ", "Nome", "Tipo_Inti", "Cod_Munc", "Ano_Mes_Inic_Cmpe", "Ano_Mes_Fim_Cmpe", "Modu_Decl", "Tipo_Decl", 
                    "Prtc_Decl_Ante", "Tipo_Cnso", "CNPJ_Resp_Rclh", "Idn_Versao", "Tipo_Arred"
                };
                nomeCampo = reg0000[--campo];
                break;

            case 100: // 'd'
                String reg0100[] = {
                    "Num_Linha", "Reg", "Conta", "Nome", "Desc_Conta", "Conta_Supe", "Conta_COSIF", "Cod_Trib_DESIF"
                };
                nomeCampo = reg0100[--campo];
                break;

            case 200: 
                String reg0200[] = {
                    "Num_Linha", "Reg", "Idto_Tari", "Desc_Tari", "Sub_Titu"
                };
                nomeCampo = reg0200[--campo];
                break;

            case 300: 
                String reg0300[] = {
                    "Num_Linha", "Reg", "Idto_Serv_Remn_Varl", "Desc_Compl_Serv_Remn_Varl", "Sub_Titu"
                };
                nomeCampo = reg0300[--campo];
                break;

            case 400: 
                String reg0400[] = {
                    "Num_Linha", "Reg", "Cod_Depe", "Indr_Insc_Munl", "Cnpj_Proprio", "Tipo_Depe", "Endr_Depe", "CNPJ_Unif", "Cod_Munc", "Ctbl_Propria", 
                    "Dat_Inic_Para", "Dat_Fim_Para"
                };
                nomeCampo = reg0400[--campo];
                break;

            case 410: 
                String reg0410[] = {
                    "Num_Linha", "Reg", "Cod_Depe", "Ano_Mes_Cmpe", "Conta", "Sald_Inic", "Valr_Debt", "Valr_Cred", "Sald_Final"
                };
                nomeCampo = reg0410[--campo];
                break;

            case 420: 
                String reg0420[] = {
                    "Num_Linha", "Reg", "Cod_Depe", "Ano_Mes_Cmpe", "Desc_Rate", "Valr_Rate", "Tipo_Prda", "Cod_Evto"
                };
                nomeCampo = reg0420[--campo];
                break;

            case 430: 
                String reg0430[] = {
                    "Num_Linha", "Reg", "Cod_Depe", "Sub_Titu", "Cod_Trib_DESIF", "Valr_Cred_Mens", "Valr_Debt_Mens", "Rece_Decl", "Dedu_Rece_Decl", "Desc_Dedu", 
                    "Base_Calc", "Aliq_ISSQN", "Inct_Fisc", "Desc_Inct_Fisc", "Motv_Nao_Exig", "Proc_Motv_Nao_Exig"
                };
                nomeCampo = reg0430[--campo];
                break;

            case 440: 
                String reg0440[] = {
                    "Num_Linha", "Reg", "CNPJ", "Cod_Trib_DESIF", "Rece_Decl_Cnso", "Dedu_Rece_Decl_Sub_Titu", "Dedu_Rece_Decl_Cnso", "Desc_Dedu", "Base_Calc", "Aliq_ISSQN", 
                    "Valr_ISSQN_Devd", "Valr_ISSQN_Retd", "Inct_Fisc_Sub_Titu", "Inct_Fisc", "Desc_Inct_Fisc", "Valr_A_Cmpn", "Orig_Cred_A_Cmpn", "Valr_ISSQN_Rclh", "Motv_Nao_Exig", "Proc_Motv_Nao_Exig", 
                    "ISSQN_A_Relh"
                };
                nomeCampo = reg0440[--campo];
                break;
            }
        return nomeCampo;
    }

    public double incremetoPorcentagem(double delta, double numeroIteracoes)
    {
        double incremento = delta / numeroIteracoes;
        return incremento;
    }

    public String concatenarCNPJ(String base, String identDependencia)
    {
        return (new StringBuilder()).append(base).append(identDependencia).toString();
    }

    public List concatenarCNPJ(String base, List identDependencia)
    {
        List resp = new ArrayList();
        String aux;
        for(Iterator it = identDependencia.iterator(); it.hasNext(); resp.add((new StringBuilder()).append(base).append(aux).toString()))
            aux = (String)it.next();

        return resp;
    }

    public static void imprimirErro(Exception ex, Logger logger)
    {
        logger.error((new StringBuilder()).append("------------------------------------Inicio Erro ").append(new Date()).append("--------------------------").toString());
        if(ex != null)
            logger.error((new StringBuilder()).append("Erro: ").append(ex).toString());
        if(ex.getCause() != null)
            logger.error((new StringBuilder()).append("Causa: ").append(ex.getCause()).toString());
        if(ex.getMessage() != null)
            logger.error((new StringBuilder()).append("Mensagem: ").append(ex.getMessage()).toString());
        logger.error("Pilha: ");
        StackTraceElement st[] = ex.getStackTrace();
        for(int i = 0; i < st.length; i++)
            logger.error(st[i].toString());

        logger.error("------------------------------------------Fim do Erro -------------------------------------");
    }

    public static void imprimirErro(ExceptionInInitializerError ex, Logger logger)
    {
        logger.error((new StringBuilder()).append("------------------------------------Inicio Erro ").append(new Date()).append("--------------------------").toString());
        if(ex != null)
            logger.error((new StringBuilder()).append("Erro: ").append(ex).toString());
        if(ex.getCause() != null)
            logger.error((new StringBuilder()).append("Causa: ").append(ex.getCause()).toString());
        if(ex.getMessage() != null)
            logger.error((new StringBuilder()).append("Mensagem: ").append(ex.getMessage()).toString());
        logger.error("Pilha: ");
        StackTraceElement st[] = ex.getStackTrace();
        for(int i = 0; i < st.length; i++)
            logger.error(st[i].toString());

        logger.error("------------------------------------------Fim do Erro -------------------------------------");
    }

}
