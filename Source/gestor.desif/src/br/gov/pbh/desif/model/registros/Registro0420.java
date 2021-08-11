/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.registros;

import br.gov.pbh.desif.model.pojo.DemonstrativoRateioMensal;
import br.gov.pbh.desif.model.registros.Data;
import br.gov.pbh.desif.model.registros.RegUtil;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Registro0420 {
    DemonstrativoRateioMensal demonstrativoRateioMensal;
    private RegUtil regUtil;
    private Data dt;
    private String numLinha;
    private String registroValidacao;
    private String codDependencia;
    private String anoMesCompetencia;
    private String descricaoDetalhada;
    private String valorReceitaRateada;
    private String tipoPartida;
    private String codigoEvento;
    private String[] token;
    private int linha;
    String registro;

    public Registro0420(String[] token, int linha) {
        this.registro = token[1];
        this.regUtil = new RegUtil();
        this.dt = new Data();
        this.token = token;
        this.linha = linha;
        this.verNumLinha();
        this.verRegistro();
        this.verCodDependencia();
        this.verAnoMesCompetencia();
        this.verCodigoEvento();
        this.verTipoPartida();
        this.verValorReceitaRateada();
        this.verDescricaoDetalhada();
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
        String txtErroCasasDecimais = "Quantidade de casas decimais diferente de quinze: ";
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
        String[] dataSeparada = this.dt.separaData(this.anoMesCompetencia);
        int mesSeparado = Integer.parseInt(dataSeparada[1]);
        int anoSeparado = Integer.parseInt(dataSeparada[0]);
        Calendar calendario = GregorianCalendar.getInstance();
        int anoAtual = calendario.get(1);
        if (this.anoMesCompetencia == null || this.anoMesCompetencia.equals("") || !this.dt.validaData(this.anoMesCompetencia, "yyyyMM") || this.dt.validaDiferencaParaAnoCorrente(this.anoMesCompetencia, "yyyyMM", ">", 10) || this.dt.validaDiferencaParaAnoCorrente(this.anoMesCompetencia, "yyyyMM", "<", -10)) {
            this.regUtil.setErro(this.linha, "EG007", coluna, (short)1, this.registro);
        }
    }

    private void verDescricaoDetalhada() {
        int coluna = 5;
        String txtErroCasasDecimais = "Quantidade de casas decimais diferente de duzentos e cinquenta e cinco (255): ";
        this.descricaoDetalhada = this.regUtil.contCaracterRegistro(this.linha, this.token[4].trim(), 255, coluna, this.registro);
        if (this.descricaoDetalhada.equals("")) {
            String txtSolucao = "C\u00f3digo da dependencia: " + this.codDependencia + ", per\u00edodo de compet\u00eancia: " + this.anoMesCompetencia + ", valor do rateio: " + this.valorReceitaRateada;
            this.regUtil.setErro(this.linha, "EC015", coluna, (short)1, this.registro, txtSolucao);
        }
    }

    private void verTipoPartida() {
        int coluna = 7;
        String txtErroCasasDecimais = "Quantidade de casas decimais diferente de um: ";
        this.tipoPartida = this.regUtil.contCaracterRegistro(this.linha, this.token[6].trim(), 1, coluna, this.registro);
        if (this.tipoPartida.equals("")) {
            this.regUtil.setErro(this.linha, "EG017", coluna, (short)1, this.registro);
        } else if (!this.regUtil.isInteiro(this.tipoPartida)) {
            String txtSolucao = "Valor invalido, n\u00e3o num\u00e9rico: " + this.tipoPartida;
            this.regUtil.setErro(this.linha, "EG008", coluna, (short)1, this.registro, txtSolucao);
        } else if (!this.regUtil.verificaCasasDecimais(this.tipoPartida)) {
            String txtSolucao = txtErroCasasDecimais + this.tipoPartida;
            this.regUtil.setErro(this.linha, "EG009", coluna, (short)1, this.registro, txtSolucao);
        }
    }

    private void verCodigoEvento() {
        int coluna = 8;
        String txtErroCasasDecimais = "Quantidade de casas decimais diferente de tr\u00eas: ";
        this.codigoEvento = this.regUtil.contCaracterRegistro(this.linha, this.token[7].trim(), 3, coluna, this.registro);
        if (this.codigoEvento.equals("")) {
            String txtSolucao = "C\u00f3digo da depend\u00eancia: " + this.codDependencia + ", per\u00edodo de compet\u00eancia: " + this.anoMesCompetencia + ", valor do rateio: " + this.valorReceitaRateada + ", tipo de partida: " + this.tipoPartida;
            this.regUtil.setErro(this.linha, "EC018", coluna, (short)1, this.registro, txtSolucao);
        } else if (this.codigoEvento.equals("113")) {
            String txtSolucao = "C\u00f3digo da depend\u00eancia: " + this.codDependencia + ", per\u00edodo de compet\u00eancia: " + this.anoMesCompetencia + ", valor do rateio: " + this.valorReceitaRateada + ", tipo de partida: " + this.tipoPartida + ", c\u00f3digo do evento: " + this.codigoEvento;
            this.regUtil.setErro(this.linha, "EC020", coluna, (short)1, this.registro, txtSolucao);
        } else if (!this.regUtil.isInteiro(this.codigoEvento)) {
            String txtSolucao = "Valor invalido, n\u00e3o num\u00e9rico: " + this.codigoEvento;
            this.regUtil.setErro(this.linha, "EG008", coluna, (short)1, this.registro, txtSolucao);
        } else if (!this.regUtil.verificaCasasDecimais(this.codigoEvento)) {
            String txtSolucao = txtErroCasasDecimais + this.codigoEvento;
            this.regUtil.setErro(this.linha, "EG009", coluna, (short)1, this.registro, txtSolucao);
        }
    }

    private void verValorReceitaRateada() {
        int coluna = 6;
        String txtErroCasasDecimais = "Quantidade de casas decimais diferente de dezoito: ";
        this.valorReceitaRateada = this.regUtil.contCaracterRegistro(this.linha, this.token[5].trim(), 18, coluna, this.registro);
        this.valorReceitaRateada = this.regUtil.trocaVirgulaPonto(this.valorReceitaRateada);
        if (this.valorReceitaRateada.equals("")) {
            String txtSolucao = "C\u00f3digo da depend\u00eancia: " + this.codDependencia + ", per\u00edodo de compet\u00eancia: " + this.anoMesCompetencia + ", tipo de partida: " + this.tipoPartida;
            this.regUtil.setErro(this.linha, "EC016", coluna, (short)1, this.registro, txtSolucao);
        } else if (!this.regUtil.isInteiro(this.codigoEvento) || Double.parseDouble(this.valorReceitaRateada) < 0.0) {
            String txtSolucao = "Valor invalido, n\u00e3o num\u00e9rico: " + this.valorReceitaRateada;
            this.regUtil.setErro(this.linha, "EG008", coluna, (short)1, this.registro, txtSolucao);
        } else if (!this.regUtil.verificaCasasDecimais(this.valorReceitaRateada)) {
            String txtSolucao = txtErroCasasDecimais + this.valorReceitaRateada;
            this.regUtil.setErro(this.linha, "EG009", coluna, (short)1, this.registro, txtSolucao);
        }
    }

    public DemonstrativoRateioMensal getDemonstrativoRateioMensal() {
        Date anoMesCompet = this.regUtil.parseData(this.anoMesCompetencia + "01", "yyyyMMdd");
        this.demonstrativoRateioMensal = new DemonstrativoRateioMensal(new Long(Long.parseLong(this.numLinha)), new Long(Long.parseLong(this.numLinha)), this.codDependencia, anoMesCompet, this.descricaoDetalhada, new Double(Double.parseDouble(this.regUtil.trocaVirgulaPonto(this.valorReceitaRateada))), new Short(Short.parseShort(this.tipoPartida)), new Integer(Integer.parseInt(this.codigoEvento)));
        return this.demonstrativoRateioMensal;
    }
}

