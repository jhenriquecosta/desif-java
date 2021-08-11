// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 10/04/2014 17:27:16
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) fieldsfirst 
// Source File Name:   Registro0410.java

package br.gov.pbh.desif.model.registros;

import br.gov.pbh.desif.model.pojo.BalanceteAnaliticoMensal;
import br.gov.pbh.desif.model.pojo.IdentificacaoDependencia;
import java.util.Calendar;
import java.util.GregorianCalendar;

// Referenced classes of package br.gov.pbh.desif.model.registros:
//            RegUtil, Data

public class Registro0410
{

    private BalanceteAnaliticoMensal balanceteAnaliticoMensal;
    private IdentificacaoDependencia dependencia;
    private RegUtil regUtil;
    private Data dt;
    private String numLinha;
    private String registroValidacao;
    private String codDependencia;
    private String anoMesCompetencia;
    private String conta;
    private String saldoInicial;
    private String saldoFinal;
    private String valorDebito;
    private String valorCredito;
    private String token[];
    private int linha;
    String registro;

    public Registro0410(String token[], int linha)
    {
        registro = token[1];
        regUtil = new RegUtil();
        dt = new Data();
        this.token = token;
        this.linha = linha;
        verNumLinha();
        verRegistro();
        verCodDependencia();
        verConta();
        verAnoMesCompetencia();
        verSaldoInicial();
        verValorDebito();
        verValorCredito();
        verSaldoFinal();
    }

    private void verNumLinha()
    {
        int coluna = 1;
        String txtErroCasasDecimais = "Quantidade de casas decimais diferente de seis: ";
        numLinha = regUtil.contCaracterRegistro(linha, token[0].trim(), 6, coluna, registro);
        if(numLinha.equals(""))
            regUtil.setErro(linha, "EG013", coluna, (short)1, registro);
        else
        if(!regUtil.isInteiro(numLinha))
        {
            String txtSolucao = (new StringBuilder()).append("Valor invalido, n\343o num\351rico: ").append(numLinha).toString();
            regUtil.setErro(linha, "EG008", coluna, (short)1, registro, txtSolucao);
        } else
        if(!regUtil.validaSequenciaLinha(linha, numLinha))
        {
            String txtSolucao = (new StringBuilder()).append("Numero da linha errado: ").append(numLinha).toString();
            regUtil.setErro(linha, "EG003", coluna, (short)1, registro, txtSolucao);
        } else
        if(!regUtil.verificaCasasDecimais(numLinha))
        {
            String txtSolucao = (new StringBuilder()).append(txtErroCasasDecimais).append(numLinha).toString();
            regUtil.setErro(linha, "EG009", coluna, (short)1, registro, txtSolucao);
        }
    }

    private void verRegistro()
    {
        int coluna = 2;
        String txtErroCasasDecimais = "Quantidade de casas decimais diferente de quatro: ";
        registroValidacao = regUtil.contCaracterRegistro(linha, token[1].trim(), 4, coluna, registro);
        if(!regUtil.verificaCasasDecimais(numLinha))
        {
            String txtSolucao = (new StringBuilder()).append(txtErroCasasDecimais).append(numLinha).toString();
            regUtil.setErro(linha, "EG009", coluna, (short)1, registro, txtSolucao);
        }
    }

    private void verCodDependencia()
    {
        int coluna = 3;
        String txtErroCasasDecimais = "Quantidade de casas decimais diferente de quinze (15): ";
        codDependencia = regUtil.contCaracterRegistro(linha, token[2].trim(), 15, coluna, registro);
        if(codDependencia.equals(""))
            regUtil.setErro(linha, "EG002", coluna, (short)1, registro);
        else
        if(!regUtil.verificaCasasDecimais(codDependencia))
        {
            String txtSolucao = (new StringBuilder()).append(txtErroCasasDecimais).append(codDependencia).toString();
            regUtil.setErro(linha, "EG009", coluna, (short)1, registro, txtSolucao);
        }
    }

    private void verAnoMesCompetencia()
    {
        int coluna = 4;
        String txtErroCasasDecimais = "Quantidade de casas decimais diferente de seis: ";
        anoMesCompetencia = regUtil.contCaracterRegistro(linha, token[3].trim(), 6, coluna, registro);
        if(!regUtil.verificaCasasDecimais(anoMesCompetencia))
        {
            String txtSolucao = (new StringBuilder()).append(txtErroCasasDecimais).append(anoMesCompetencia).toString();
            regUtil.setErro(linha, "EG009", coluna, (short)1, registro, txtSolucao);
        }
        String dataSeparada[] = dt.separaData(anoMesCompetencia);
        int mesSeparado = Integer.parseInt(dataSeparada[1]);
        int anoSeparado = Integer.parseInt(dataSeparada[0]);
        Calendar calendario = GregorianCalendar.getInstance();
        int anoAtual = calendario.get(1);
        if(anoMesCompetencia == null || anoMesCompetencia.equals("") || !dt.validaData(anoMesCompetencia, "yyyyMM") || dt.validaDiferencaParaAnoCorrente(anoMesCompetencia, "yyyyMM", ">", 10) || dt.validaDiferencaParaAnoCorrente(anoMesCompetencia, "yyyyMM", "<", -10))
            regUtil.setErro(linha, "EG007", coluna, (short)1, registro);
    }

    private void verConta()
    {
        int coluna = 5;
        String txtErroCasasDecimais = "Quantidade de casas decimais diferente de trinta (30): ";
        conta = regUtil.contCaracterRegistro(linha, token[4].trim(), 30, coluna, registro);
        if(conta == null || conta.equals(""))
            regUtil.setErro(linha, "EG016", coluna, (short)1, registro);
        else
        if(!regUtil.verificaCasasDecimais(conta))
        {
            String txtSolucao = (new StringBuilder()).append(txtErroCasasDecimais).append(conta).toString();
            regUtil.setErro(linha, "EG009", coluna, (short)1, registro, txtSolucao);
        }
    }

    private void verSaldoInicial()
    {
        int coluna = 6;
        String txtErroCasasDecimais = "Quantidade de casas decimais diferente de dezoito: ";
        saldoInicial = regUtil.contCaracterRegistro(linha, token[5].trim(), 19, coluna, registro);
        if(saldoInicial.equals(""))
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo da Depend\352ncia: ").append(codDependencia).append(", Conta: ").append(conta).append(", per\355odo de compet\352ncia: ").append(anoMesCompetencia).toString();
            regUtil.setErro(linha, "EC003", coluna, (short)1, registro, txtSolucao);
        } else
        if(!regUtil.isNumeric(saldoInicial))
        {
            String txtSolucao = (new StringBuilder()).append("Valor invalido, n\343o num\351rico: ").append(saldoInicial).toString();
            regUtil.setErro(linha, "EG008", coluna, (short)1, registro, txtSolucao);
        } else
        if(!regUtil.verificaCasasDecimais(saldoInicial))
        {
            String txtSolucao = (new StringBuilder()).append(txtErroCasasDecimais).append(saldoInicial).toString();
            regUtil.setErro(linha, "EG009", coluna, (short)1, registro, txtSolucao);
        }
    }

    private void verValorDebito()
    {
        int coluna = 7;
        String txtErroCasasDecimais = "Quantidade de casas decimais diferente de dezoito: ";
        valorDebito = regUtil.contCaracterRegistro(linha, token[6].trim(), 19, coluna, registro);
        if(valorDebito.equals(""))
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo da Depend\352ncia: ").append(codDependencia).append(", Conta: ").append(conta).append(", per\355odo de compet\352ncia: ").append(anoMesCompetencia).toString();
            regUtil.setErro(linha, "EC006", coluna, (short)1, registro, txtSolucao);
        } else
        if(!regUtil.isNumeric(valorDebito))
        {
            String txtSolucao = (new StringBuilder()).append("Valor invalido, n\343o num\351rico: ").append(valorDebito).toString();
            regUtil.setErro(linha, "EG008", coluna, (short)1, registro, txtSolucao);
        } else
        if(Float.parseFloat(regUtil.trocaVirgulaPonto(valorDebito)) < 0.0F)
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo da Depend\352ncia: ").append(codDependencia).append(", Conta: ").append(conta).append(", per\355odo de compet\352ncia: ").append(anoMesCompetencia).append(", Valor do d\351bito:").append(valorDebito).toString();
            regUtil.setErro(linha, "EC007", coluna, (short)1, registro, txtSolucao);
        } else
        if(!regUtil.verificaCasasDecimais(valorDebito))
        {
            String txtSolucao = (new StringBuilder()).append(txtErroCasasDecimais).append(valorDebito).toString();
            regUtil.setErro(linha, "EG009", coluna, (short)1, registro, txtSolucao);
        }
    }

    private void verValorCredito()
    {
        int coluna = 8;
        String txtErroCasasDecimais = "Quantidade de casas decimais diferente de dezoito: ";
        valorCredito = regUtil.contCaracterRegistro(linha, token[7].trim(), 19, coluna, registro);
        if(valorCredito.equals(""))
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo da Depend\352ncia: ").append(codDependencia).append(", Conta: ").append(conta).append(", per\355odo de compet\352ncia: ").append(anoMesCompetencia).toString();
            regUtil.setErro(linha, "EC008", coluna, (short)1, registro, txtSolucao);
        } else
        if(!regUtil.isNumeric(valorCredito))
        {
            String txtSolucao = (new StringBuilder()).append("Valor invalido, n\343o num\351rico: ").append(valorCredito).toString();
            regUtil.setErro(linha, "EG008", coluna, (short)1, registro, txtSolucao);
        } else
        if(Float.parseFloat(regUtil.trocaVirgulaPonto(valorCredito)) < 0.0F)
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo da Depend\352ncia: ").append(codDependencia).append(", Conta: ").append(conta).append(", per\355odo de compet\352ncia: ").append(anoMesCompetencia).append(", Saldo Inicial:").append(saldoInicial).append(", Valor do Cr\351dito:").append(valorCredito).append(", Valor do D\351bito:").append(valorDebito).append(", Saldo Final:").append(saldoFinal).toString();
            regUtil.setErro(linha, "EC009", coluna, (short)1, registro, txtSolucao);
        } else
        if(!regUtil.verificaCasasDecimais(valorCredito))
        {
            String txtSolucao = (new StringBuilder()).append(txtErroCasasDecimais).append(valorCredito).toString();
            regUtil.setErro(linha, "EG009", coluna, (short)1, registro, txtSolucao);
        }
    }

    private void verSaldoFinal()
    {
        int coluna = 9;
        String txtErroCasasDecimais = "Quantidade de casas decimais diferente de dezoito: ";
        saldoFinal = regUtil.contCaracterRegistro(linha, token[8].trim(), 19, coluna, registro);
        if(saldoFinal.equals(""))
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo da Depend\352ncia: ").append(codDependencia).append(", Conta: ").append(conta).append(", per\355odo de compet\352ncia: ").append(anoMesCompetencia).toString();
            regUtil.setErro(linha, "EC010", coluna, (short)1, registro, txtSolucao);
        } else
        if(!regUtil.isNumeric(saldoFinal))
        {
            String txtSolucao = (new StringBuilder()).append("Valor invalido, n\343o num\351rico: ").append(saldoFinal).toString();
            regUtil.setErro(linha, "EG008", coluna, (short)1, registro, txtSolucao);
        } else
        if(!regUtil.verificaCasasDecimais(saldoFinal))
        {
            String txtSolucao = (new StringBuilder()).append(txtErroCasasDecimais).append(saldoFinal).toString();
            regUtil.setErro(linha, "EG009", coluna, (short)1, registro, txtSolucao);
        }
    }

    public BalanceteAnaliticoMensal getBalanceteAnaliticoMensal()
    {
        java.util.Date anoMesCompet = regUtil.parseData((new StringBuilder()).append(anoMesCompetencia).append("01").toString(), "yyyyMMdd");
        balanceteAnaliticoMensal = new BalanceteAnaliticoMensal(new Long(Long.parseLong(numLinha)), new Long(Long.parseLong(numLinha)), codDependencia, anoMesCompet, conta, new Double(Double.parseDouble(regUtil.trocaVirgulaPonto(saldoInicial))), new Double(Double.parseDouble(regUtil.trocaVirgulaPonto(valorDebito))), new Double(Double.parseDouble(regUtil.trocaVirgulaPonto(valorCredito))), new Double(Double.parseDouble(regUtil.trocaVirgulaPonto(saldoFinal))));
        return balanceteAnaliticoMensal;
    }
}