/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.registros;

import br.gov.pbh.desif.model.pojo.IdentificacaoDependencia;
import br.gov.pbh.desif.model.registros.Data;
import br.gov.pbh.desif.model.registros.RegUtil;
import java.util.Date;

public class Registro0400 {
    private IdentificacaoDependencia dependencia;
    private RegUtil regUtil;
    private Data dt;
    private String numLinha;
    private String codDependencia;
    private String indInscricaoMunicipal;
    private String cnpjProprio;
    private String tipoDependencia;
    private String endDependencia;
    private String cnpjUnificado;
    private String codMunicipio;
    private String contabilidadePropria;
    private String datInicParalizacao;
    private String datFimParalizacao;
    private String[] token;
    private int linha;
    String registro;

    public Registro0400(String[] token, int linha) {
        this.registro = token[1];
        this.regUtil = new RegUtil();
        this.dt = new Data();
        this.token = token;
        this.linha = linha;
        this.verNumLinha();
        this.verCodDependencia();
        this.verIndInscricaoMunicipal();
        this.verCnpjProprio();
        this.verTipoDependencia();
        this.verEndDependencia();
        this.verCnpjUnificado();
        this.verCodMunicipio();
        this.verDatInicParalizacao();
        this.verDatFimParalizacao();
        this.verContabilidadePropria();
    }

    private void verNumLinha() {
        int coluna = 1;
        this.numLinha = this.regUtil.contCaracterRegistro(this.linha, this.token[0].trim(), 6, coluna, this.registro);
        if (this.numLinha.equals("")) {
            this.regUtil.setErro(this.linha, "EG013", coluna, (short)1, this.registro);
        } else if (!this.regUtil.isInteiro(this.numLinha)) {
            String txtSolucao = "Valor invalido, n\u00e3o num\u00e9rico: " + this.numLinha;
            this.regUtil.setErro(this.linha, "EG008", coluna, (short)1, this.registro, txtSolucao);
        } else if (!this.regUtil.validaSequenciaLinha(this.linha, this.numLinha)) {
            String txtSolucao = "Numero da linha errado: " + this.numLinha;
            this.regUtil.setErro(this.linha, "EG003", coluna, (short)1, this.registro, txtSolucao);
        }
    }

    private void verCodDependencia() {
        int coluna = 3;
        this.codDependencia = this.regUtil.contCaracterRegistro(this.linha, this.token[2].trim(), 15, coluna, this.registro);
        if (this.codDependencia.equals("")) {
            this.regUtil.setErro(this.linha, "EG002", coluna, (short)1, this.registro);
        }
    }

    private void verIndInscricaoMunicipal() {
        String txtSolucao;
        int coluna = 4;
        this.indInscricaoMunicipal = this.regUtil.contCaracterRegistro(this.linha, this.token[3].trim(), 1, coluna, this.registro);
        if (this.indInscricaoMunicipal.equals("") || !this.indInscricaoMunicipal.equals("1") & !this.indInscricaoMunicipal.equals("2")) {
            txtSolucao = "C\u00f3digo da Depend\u00eancia: " + this.codDependencia + "<BR>Indicador de Inscri\u00e7\u00e3o municipal: " + this.indInscricaoMunicipal;
            this.regUtil.setErro(this.linha, "ED007", coluna, (short)1, this.registro, txtSolucao);
        }
        if (!this.regUtil.isInteiro(this.indInscricaoMunicipal)) {
            txtSolucao = "Valor invalido, n\u00e3o num\u00e9rico: " + this.indInscricaoMunicipal;
            this.regUtil.setErro(this.linha, "EG008", coluna, (short)1, this.registro, txtSolucao);
        }
    }

    private void verCnpjProprio() {
        int coluna = 5;
        this.cnpjProprio = this.regUtil.contCaracterRegistro(this.linha, this.token[4].trim(), 6, coluna, this.registro);
    }

    private void verTipoDependencia() {
        int coluna = 6;
        this.tipoDependencia = this.regUtil.contCaracterRegistro(this.linha, this.token[5].trim(), 2, coluna, this.registro);
        if (this.tipoDependencia.equals("")) {
            String txtSolucao = "C\u00f3digo da depend\u00eancia: " + this.codDependencia + "<BR>CNPJ pr\u00f3prio: " + this.cnpjProprio;
            this.regUtil.setErro(this.linha, "ED017", coluna, (short)1, this.registro, txtSolucao);
        } else if (!this.regUtil.isInteiro(this.tipoDependencia)) {
            String txtSolucao = "Valor invalido, n\u00e3o num\u00e9rico: " + this.tipoDependencia;
            this.regUtil.setErro(this.linha, "EG008", coluna, (short)1, this.registro, txtSolucao);
        }
    }

    private void verEndDependencia() {
        int coluna = 7;
        this.endDependencia = this.regUtil.contCaracterRegistro(this.linha, this.token[6].trim(), 100, coluna, this.registro);
        if (this.indInscricaoMunicipal.equals("2") && this.endDependencia.equals("")) {
            String txtSolucao = "C\u00f3digo da depend\u00eancia: " + this.codDependencia + "<BR>indicador de inscri\u00e7\u00e3o municipal: " + this.indInscricaoMunicipal;
            this.regUtil.setErro(this.linha, "ED009", coluna, (short)1, this.registro, txtSolucao);
        }
    }

    private void verCnpjUnificado() {
        int coluna = 8;
        this.cnpjUnificado = this.regUtil.contCaracterRegistro(this.linha, this.token[7].trim(), 6, coluna, this.registro);
        if (this.cnpjUnificado.equals("")) {
            this.regUtil.setErro(this.linha, "EG004", coluna, (short)1, this.registro);
        }
    }

    private void verCodMunicipio() {
        int coluna = 9;
        this.codMunicipio = this.regUtil.contCaracterRegistro(this.linha, this.token[8].trim(), 7, coluna, this.registro);
        if (this.codMunicipio.equals("")) {
            this.regUtil.setErro(this.linha, "EG010", coluna, (short)1, this.registro);
        } else if (!this.regUtil.isInteiro(this.codMunicipio)) {
            String txtSolucao = "Valor invalido, n\u00e3o num\u00e9rico: " + this.codMunicipio;
            this.regUtil.setErro(this.linha, "EG008", coluna, (short)1, this.registro, txtSolucao);
        }
    }

    private void verContabilidadePropria() {
        int coluna = 10;
        this.contabilidadePropria = this.regUtil.contCaracterRegistro(this.linha, this.token[9].trim(), 1, coluna, this.registro);
        if (this.contabilidadePropria.equals("") || !this.contabilidadePropria.equals("1") & !this.contabilidadePropria.equals("2")) {
            String txtSolucao = "C\u00f3digo da depend\u00eancia: " + this.codDependencia + "<BR>identifica\u00e7\u00e3o de contabilidade pr\u00f3pria: " + this.contabilidadePropria;
            this.regUtil.setErro(this.linha, "EM010", coluna, (short)1, this.registro, txtSolucao);
        }
    }

    private void verDatInicParalizacao() {
        int coluna = 11;
        this.datInicParalizacao = this.regUtil.contCaracterRegistro(this.linha, this.token[10].trim(), 8, coluna, this.registro);
        if (!this.datInicParalizacao.trim().equals("") && this.datInicParalizacao != null) {
            if (!this.dt.validaData(this.datInicParalizacao, "yyyyMMdd")) {
                String txtSolucao = "C\u00f3digo da depend\u00eancia: " + this.codDependencia + " <BR>data de in\u00edcio da paralisa\u00e7\u00e3o: " + this.datInicParalizacao;
                this.regUtil.setErro(this.linha, "EG005", coluna, (short)1, this.registro, txtSolucao);
            } else if (!this.dt.validaDiferencaParaAnoCorrente(this.datInicParalizacao, "yyyyMMdd", ">", -10)) {
                this.regUtil.setAlerta(this.linha, "A006", coluna, (short)1, this.registro);
            }
        }
    }

    private void verDatFimParalizacao() {
        int coluna = 12;
        this.datFimParalizacao = this.regUtil.contCaracterRegistro(this.linha, this.token[11].trim(), 8, coluna, this.registro);
        if (!this.datFimParalizacao.trim().equals("") && this.datFimParalizacao != null) {
            String txtSolucao;
            if (!this.dt.validaData(this.datFimParalizacao, "yyyyMMdd")) {
                txtSolucao = "C\u00f3digo da depend\u00eancia: " + this.codDependencia + " <BR>data de fim da paralisa\u00e7\u00e3o: " + this.datFimParalizacao;
                this.regUtil.setErro(this.linha, "EG005", coluna, (short)1, this.registro, txtSolucao);
            } else if (this.datInicParalizacao.equals("") & !this.datFimParalizacao.equals("")) {
                txtSolucao = "C\u00f3digo da depend\u00eancia: " + this.codDependencia + "<BR>data in\u00edcio da paralisa\u00e7\u00e3o: " + this.datInicParalizacao + "<BR>data fim da paralisa\u00e7\u00e3o: " + this.datFimParalizacao;
                this.regUtil.setErro(this.linha, "ED010", coluna, (short)1, this.registro, txtSolucao);
            } else if (this.dt.validaData(this.datFimParalizacao, "yyyyMMdd") && this.dt.validaData(this.datInicParalizacao, "yyyyMMdd") && (!this.dt.comparaDataMaior(this.datFimParalizacao, this.datInicParalizacao, "yyyyMMdd") || this.datFimParalizacao.equals(this.datInicParalizacao))) {
                txtSolucao = "C\u00f3digo da depend\u00eancia: " + this.codDependencia + "<BR>data in\u00edcio da paralisa\u00e7\u00e3o: " + this.datInicParalizacao + "<BR>data fim da paralisa\u00e7\u00e3o: " + this.datFimParalizacao;
                this.regUtil.setErro(this.linha, "ED011", coluna, (short)1, this.registro, txtSolucao);
            }
            if (this.dt.compararNumDiasEntreDatas(this.datInicParalizacao, this.datFimParalizacao, "yyyyMMdd") > 180L) {
                txtSolucao = "C\u00f3digo da depend\u00eancia: " + this.codDependencia + "<BR>inicio paralisa\u00e7\u00e3o:  " + this.datInicParalizacao + "<BR>fim da paralisa\u00e7\u00e3o: " + this.datFimParalizacao;
                this.regUtil.setAlerta(this.linha, "A003", coluna, (short)1, this.registro);
            }
        }
    }

    public IdentificacaoDependencia getDependenciaPojo() {
        try {
            Date anoMIP = this.regUtil.parseData(this.datInicParalizacao, "yyyyMMdd");
            Date anoMFP = this.regUtil.parseData(this.datFimParalizacao, "yyyyMMdd");
            this.dependencia = new IdentificacaoDependencia(new Long(Long.parseLong(this.numLinha)), new Integer(Integer.parseInt(this.tipoDependencia)), new Long(Long.parseLong(this.codMunicipio)), new Long(Long.parseLong(this.numLinha)), this.codDependencia, new Short(Short.parseShort(this.indInscricaoMunicipal)), new Short(Short.parseShort(this.contabilidadePropria)), this.cnpjUnificado, this.cnpjProprio, this.endDependencia, anoMIP, anoMFP);
            return this.dependencia;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

