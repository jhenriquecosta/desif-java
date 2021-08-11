/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.registros;

import br.gov.pbh.desif.model.pojo.BalanceteAnaliticoMensal;
import br.gov.pbh.desif.model.pojo.IdentificacaoDependencia;
import br.gov.pbh.desif.model.registros.Data;
import br.gov.pbh.desif.model.registros.RegUtil;
import java.util.Date;

public class Registro0410 {
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
    private String[] token;
    private int linha;
    String registro;

    public Registro0410(String[] token, int linha) {
        this.registro = token[1];
        this.regUtil = new RegUtil();
        this.dt = new Data();
        this.token = token;
        this.linha = linha;
        this.verNumLinha();
        this.verRegistro();
        this.verCodDependencia();
        this.verConta();
        this.verAnoMesCompetencia();
        this.verSaldoInicial();
        this.verValorDebito();
        this.verValorCredito();
        this.verSaldoFinal();
    }

    private void verNumLinha() {
        int coluna = 1;
        String txtErroCasasDecimais = "Quantidade de casas decimais diferente de seis: ";
        this.numLinha = this.regUtil.contCaracterRegistro(this.linha, this.token[0].trim(), 6, coluna, this.registro);
        if (this.numLinha.equals("")) {
            this.regUtil.setErro(this.linha, "EG013", coluna, (short)1, this.registro);
        } else if (!this.regUtil.isInteiro(this.numLinha)) {
            String txtSolucao = "Valor invalido, n\u00e3o num\u00e9rico: " + this.numLinha;
            this.regUtil.setErro(this.linha, "EG008", coluna, (short)1, this.registro, txtSolucao);
        } else if (!this.regUtil.validaSequenciaLinha(this.linha, this.numLinha)) {
            String txtSolucao = "Numero da linha errado: " + this.numLinha;
            this.regUtil.setErro(this.linha, "EG003", coluna, (short)1, this.registro, txtSolucao);
        } else if (!this.regUtil.verificaCasasDecimais(this.numLinha)) {
            String txtSolucao = txtErroCasasDecimais + this.numLinha;
            this.regUtil.setErro(this.linha, "EG009", coluna, (short)1, this.registro, txtSolucao);
        }
    }

    private void verRegistro() {
        int coluna = 2;
        String txtErroCasasDecimais = "Quantidade de casas decimais diferente de quatro: ";
        this.registroValidacao = this.regUtil.contCaracterRegistro(this.linha, this.token[1].trim(), 4, coluna, this.registro);
        if (!this.regUtil.verificaCasasDecimais(this.numLinha)) {
            String txtSolucao = txtErroCasasDecimais + this.numLinha;
            this.regUtil.setErro(this.linha, "EG009", coluna, (short)1, this.registro, txtSolucao);
        }
    }

    private void verCodDependencia() {
        int coluna = 3;
        String txtErroCasasDecimais = "Quantidade de casas decimais diferente de quinze (15): ";
        this.codDependencia = this.regUtil.contCaracterRegistro(this.linha, this.token[2].trim(), 15, coluna, this.registro);
        if (this.codDependencia.equals("")) {
            this.regUtil.setErro(this.linha, "EG002", coluna, (short)1, this.registro);
        } else if (!this.regUtil.verificaCasasDecimais(this.codDependencia)) {
            String txtSolucao = txtErroCasasDecimais + this.codDependencia;
            this.regUtil.setErro(this.linha, "EG009", coluna, (short)1, this.registro, txtSolucao);
        }
    }

    private void verAnoMesCompetencia() {
        int coluna = 4;
        String txtErroCasasDecimais = "Quantidade de casas decimais diferente de seis: ";
        this.anoMesCompetencia = this.regUtil.contCaracterRegistro(this.linha, this.token[3].trim(), 6, coluna, this.registro);
        if (!this.regUtil.verificaCasasDecimais(this.anoMesCompetencia)) {
            String txtSolucao = txtErroCasasDecimais + this.anoMesCompetencia;
            this.regUtil.setErro(this.linha, "EG009", coluna, (short)1, this.registro, txtSolucao);
        }
        if (this.anoMesCompetencia == null || this.anoMesCompetencia.equals("") || !this.dt.validaData(this.anoMesCompetencia, "yyyyMM") || this.dt.validaDiferencaParaAnoCorrente(this.anoMesCompetencia, "yyyyMM", ">", 10) || this.dt.validaDiferencaParaAnoCorrente(this.anoMesCompetencia, "yyyyMM", "<", -10)) {
            this.regUtil.setErro(this.linha, "EG007", coluna, (short)1, this.registro);
        }
    }

    private void verConta() {
        int coluna = 5;
        String txtErroCasasDecimais = "Quantidade de casas decimais diferente de trinta (30): ";
        this.conta = this.regUtil.contCaracterRegistro(this.linha, this.token[4].trim(), 30, coluna, this.registro);
        if (this.conta == null || this.conta.equals("")) {
            this.regUtil.setErro(this.linha, "EG016", coluna, (short)1, this.registro);
        } else if (!this.regUtil.verificaCasasDecimais(this.conta)) {
            String txtSolucao = txtErroCasasDecimais + this.conta;
            this.regUtil.setErro(this.linha, "EG009", coluna, (short)1, this.registro, txtSolucao);
        }
    }

    private void verSaldoInicial() {
        int coluna = 6;
        String txtErroCasasDecimais = "Quantidade de casas decimais diferente de dezoito: ";
        this.saldoInicial = this.regUtil.contCaracterRegistro(this.linha, this.token[5].trim(), 19, coluna, this.registro);
        if (this.saldoInicial.equals("")) {
            String txtSolucao = "C\u00f3digo da Depend\u00eancia: " + this.codDependencia + ", Conta: " + this.conta + ", per\u00edodo de compet\u00eancia: " + this.anoMesCompetencia;
            this.regUtil.setErro(this.linha, "EC003", coluna, (short)1, this.registro, txtSolucao);
        } else if (!this.regUtil.isNumeric(this.saldoInicial)) {
            String txtSolucao = "Valor invalido, n\u00e3o num\u00e9rico: " + this.saldoInicial;
            this.regUtil.setErro(this.linha, "EG008", coluna, (short)1, this.registro, txtSolucao);
        } else if (!this.regUtil.verificaCasasDecimais(this.saldoInicial)) {
            String txtSolucao = txtErroCasasDecimais + this.saldoInicial;
            this.regUtil.setErro(this.linha, "EG009", coluna, (short)1, this.registro, txtSolucao);
        }
    }

    private void verValorDebito() {
        int coluna = 7;
        String txtErroCasasDecimais = "Quantidade de casas decimais diferente de dezoito: ";
        this.valorDebito = this.regUtil.contCaracterRegistro(this.linha, this.token[6].trim(), 19, coluna, this.registro);
        if (this.valorDebito.equals("")) {
            String txtSolucao = "C\u00f3digo da Depend\u00eancia: " + this.codDependencia + ", Conta: " + this.conta + ", per\u00edodo de compet\u00eancia: " + this.anoMesCompetencia;
            this.regUtil.setErro(this.linha, "EC006", coluna, (short)1, this.registro, txtSolucao);
        } else if (!this.regUtil.isNumeric(this.valorDebito)) {
            String txtSolucao = "Valor invalido, n\u00e3o num\u00e9rico: " + this.valorDebito;
            this.regUtil.setErro(this.linha, "EG008", coluna, (short)1, this.registro, txtSolucao);
        } else if (Float.parseFloat(this.regUtil.trocaVirgulaPonto(this.valorDebito)) < 0.0f) {
            String txtSolucao = "C\u00f3digo da Depend\u00eancia: " + this.codDependencia + ", Conta: " + this.conta + ", per\u00edodo de compet\u00eancia: " + this.anoMesCompetencia + ", Valor do d\u00e9bito:" + this.valorDebito;
            this.regUtil.setErro(this.linha, "EC007", coluna, (short)1, this.registro, txtSolucao);
        } else if (!this.regUtil.verificaCasasDecimais(this.valorDebito)) {
            String txtSolucao = txtErroCasasDecimais + this.valorDebito;
            this.regUtil.setErro(this.linha, "EG009", coluna, (short)1, this.registro, txtSolucao);
        }
    }

    private void verValorCredito() {
        int coluna = 8;
        String txtErroCasasDecimais = "Quantidade de casas decimais diferente de dezoito: ";
        this.valorCredito = this.regUtil.contCaracterRegistro(this.linha, this.token[7].trim(), 19, coluna, this.registro);
        if (this.valorCredito.equals("")) {
            String txtSolucao = "C\u00f3digo da Depend\u00eancia: " + this.codDependencia + ", Conta: " + this.conta + ", per\u00edodo de compet\u00eancia: " + this.anoMesCompetencia;
            this.regUtil.setErro(this.linha, "EC008", coluna, (short)1, this.registro, txtSolucao);
        } else if (!this.regUtil.isNumeric(this.valorCredito)) {
            String txtSolucao = "Valor invalido, n\u00e3o num\u00e9rico: " + this.valorCredito;
            this.regUtil.setErro(this.linha, "EG008", coluna, (short)1, this.registro, txtSolucao);
        } else if (Float.parseFloat(this.regUtil.trocaVirgulaPonto(this.valorCredito)) < 0.0f) {
            String txtSolucao = "C\u00f3digo da Depend\u00eancia: " + this.codDependencia + ", Conta: " + this.conta + ", per\u00edodo de compet\u00eancia: " + this.anoMesCompetencia + ", Saldo Inicial:" + this.saldoInicial + ", Valor do Cr\u00e9dito:" + this.valorCredito + ", Valor do D\u00e9bito:" + this.valorDebito + ", Saldo Final:" + this.saldoFinal;
            this.regUtil.setErro(this.linha, "EC009", coluna, (short)1, this.registro, txtSolucao);
        } else if (!this.regUtil.verificaCasasDecimais(this.valorCredito)) {
            String txtSolucao = txtErroCasasDecimais + this.valorCredito;
            this.regUtil.setErro(this.linha, "EG009", coluna, (short)1, this.registro, txtSolucao);
        }
    }

    private void verSaldoFinal() {
        int coluna = 9;
        String txtErroCasasDecimais = "Quantidade de casas decimais diferente de dezoito: ";
        this.saldoFinal = this.regUtil.contCaracterRegistro(this.linha, this.token[8].trim(), 19, coluna, this.registro);
        if (this.saldoFinal.equals("")) {
            String txtSolucao = "C\u00f3digo da Depend\u00eancia: " + this.codDependencia + ", Conta: " + this.conta + ", per\u00edodo de compet\u00eancia: " + this.anoMesCompetencia;
            this.regUtil.setErro(this.linha, "EC010", coluna, (short)1, this.registro, txtSolucao);
        } else if (!this.regUtil.isNumeric(this.saldoFinal)) {
            String txtSolucao = "Valor invalido, n\u00e3o num\u00e9rico: " + this.saldoFinal;
            this.regUtil.setErro(this.linha, "EG008", coluna, (short)1, this.registro, txtSolucao);
        } else if (!this.regUtil.verificaCasasDecimais(this.saldoFinal)) {
            String txtSolucao = txtErroCasasDecimais + this.saldoFinal;
            this.regUtil.setErro(this.linha, "EG009", coluna, (short)1, this.registro, txtSolucao);
        }
    }

    public BalanceteAnaliticoMensal getBalanceteAnaliticoMensal() {
        Date anoMesCompet = this.regUtil.parseData(this.anoMesCompetencia + "01", "yyyyMMdd");
        this.balanceteAnaliticoMensal = new BalanceteAnaliticoMensal(new Long(Long.parseLong(this.numLinha)), new Long(Long.parseLong(this.numLinha)), this.codDependencia, anoMesCompet, this.conta, new Double(Double.parseDouble(this.regUtil.trocaVirgulaPonto(this.saldoInicial))), new Double(Double.parseDouble(this.regUtil.trocaVirgulaPonto(this.valorDebito))), new Double(Double.parseDouble(this.regUtil.trocaVirgulaPonto(this.valorCredito))), new Double(Double.parseDouble(this.regUtil.trocaVirgulaPonto(this.saldoFinal))));
        return this.balanceteAnaliticoMensal;
    }
}

