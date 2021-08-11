/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  br.gov.pbh.bhiss.utilitarios.validadores.CpfCnpj
 */
package br.gov.pbh.desif.model.registros;

import br.gov.pbh.bhiss.utilitarios.validadores.CpfCnpj;
import br.gov.pbh.desif.model.pojo.IdentificacaoDeclaracao;
import br.gov.pbh.desif.model.registros.Data;
import br.gov.pbh.desif.model.registros.RegUtil;
import br.gov.pbh.desif.service.arquivo.ExceptionBOM;
import br.gov.pbh.desif.service.util.ConstantsBusiness;
import br.gov.pbh.desif.view.util.SwingUtils;
import java.io.PrintStream;
import java.util.Date;

public class Registro0000 {
    private IdentificacaoDeclaracao declaracao;
    private RegUtil regUtil;
    private Data dt;
    private String numLinha;
    private String cnpj;
    private String nome;
    private String tipoInstituicao;
    private String codMunicipio;
    private String anoMesInicCompet;
    private String anoMesFimCompet;
    private String moduloDeclaracao;
    private String tipoDeclaracao;
    private String protcDeclaracaoAnterior;
    private String tipoConsolidacao;
    private String cnpjResponsavelRecolhimento;
    private String[] token;
    private int linha;
    String registro;

    public Registro0000(String[] token, int line) {
        try {
            this.registro = token[1];
            this.regUtil = new RegUtil();
            this.dt = new Data();
            this.token = token;
            this.linha = line;
            this.verLinha();
            this.verCnjp();
            this.verNome();
            this.verTipoInstituicao();
            this.verCodMunicipio();
            this.verModuloDeclaracao();
            this.verAnoMesInicCompet();
            this.verAnoMesFimCompet();
            this.verTipoDeclaracao();
            this.verTipoConsolidacao();
            this.verCnpjResponsavelRecolhimento();
            this.verprotcDeclaracaoAnterio();
        }
        catch (ExceptionBOM exBOM) {
            exBOM.printStackTrace();
            RegUtil.exErro = true;
            SwingUtils.msgErro(null, "Problema na leitura do arquivo, verifique se a c\u00f3difica\u00e7\u00e3o do arquivo \u00e9 UTF-8 SEM BOM");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void verLinha() throws Exception {
        String txtSolucao;
        int coluna = 1;
        if (!this.token[0].substring(0, 1).equals("0") && !this.token[0].substring(0, 1).equals("1") && this.token[0].substring(0, 1).charAt(0) == '\ufeff') {
            System.out.println("A formata\u00e7ao deve ser sem BOM =" + this.token[0].trim());
            throw new ExceptionBOM();
        }
        this.numLinha = this.regUtil.contCaracterRegistro(this.linha, this.token[0].trim(), 6, coluna, this.registro);
        if (this.numLinha.equals("")) {
            this.regUtil.setErro(this.linha, "EG013", coluna, (short)1, this.registro);
        } else if (!this.regUtil.isInteiro(this.numLinha)) {
            txtSolucao = "Valor invalido, n\u00e3o num\u00e9rico: " + this.numLinha;
            this.regUtil.setErro(this.linha, "EG008", coluna, (short)1, this.registro, txtSolucao);
        }
        if (!this.regUtil.validaSequenciaLinha(this.linha, this.numLinha)) {
            txtSolucao = "Numero da linha errado: " + this.numLinha;
            this.regUtil.setErro(this.linha, "EG003", coluna, (short)1, this.registro, txtSolucao);
        }
    }

    private void verCnjp() throws Exception {
        int coluna = 3;
        this.cnpj = this.regUtil.contCaracterRegistro(this.linha, this.token[2].trim(), 8, coluna, this.registro);
        if (this.cnpj.equals("") || !this.regUtil.isNumeric(this.cnpj)) {
            this.regUtil.setErro(this.linha, "ED001", coluna, (short)1, this.registro);
        }
    }

    private void verNome() throws Exception {
        int coluna = 4;
        this.nome = this.regUtil.contCaracterRegistro(this.linha, this.token[3].trim(), 100, coluna, this.registro);
        if (this.nome.equals("")) {
            this.regUtil.setErro(this.linha, "ED002", coluna, (short)1, this.registro);
        }
    }

    private void verTipoInstituicao() throws Exception {
        int coluna = 5;
        this.tipoInstituicao = this.regUtil.contCaracterRegistro(this.linha, this.token[4].trim(), 1, coluna, this.registro);
        if (this.tipoInstituicao.equals("")) {
            this.regUtil.setErro(this.linha, "ED020", coluna, (short)1, this.registro);
        }
    }

    private void verCodMunicipio() throws Exception {
        int coluna = 6;
        this.codMunicipio = this.regUtil.contCaracterRegistro(this.linha, this.token[5].trim(), 7, coluna, this.registro);
        if (this.codMunicipio.equals("")) {
            this.regUtil.setErro(this.linha, "EG010", coluna, (short)1, this.registro);
        } else if (!this.regUtil.isInteiro(this.codMunicipio)) {
            String txtSolucao = "Valor invalido, n\u00e3o num\u00e9rico: " + this.codMunicipio;
            this.regUtil.setErro(this.linha, "EG008", coluna, (short)1, this.registro, txtSolucao);
        }
    }

    private void verAnoMesInicCompet() throws Exception {
        String txtAlerta;
        String txtSolucao;
        int coluna = 7;
        this.anoMesInicCompet = this.regUtil.contCaracterRegistro(this.linha, this.token[6].trim(), 6, coluna, this.registro);
        if (this.anoMesInicCompet == null || this.anoMesInicCompet.equals("") || !this.dt.validaData(this.anoMesInicCompet, "yyyyMM")) {
            this.regUtil.setErro(this.linha, "EG007", coluna, (short)1, this.registro);
        } else if (!this.dt.validaDiferencaParaAnoCorrente(this.anoMesInicCompet, "yyyyMM", ">", -10)) {
            txtSolucao = "Ano de compet\u00eancia informado: " + this.anoMesInicCompet;
            this.regUtil.setErro(this.linha, "ED004", coluna, (short)1, this.registro, txtSolucao);
        }
        if (this.moduloDeclaracao.equals("1")) {
            if (!this.dt.validaMes(this.anoMesInicCompet, "yyyyMM", 1)) {
                txtAlerta = "M\u00f3dulo declara\u00e7\u00e3o: M\u00f3dulo Cont\u00e1bil, m\u00eas inicio de compet\u00eancia informado: " + this.anoMesInicCompet;
                this.regUtil.setAlerta(this.linha, "A002", coluna, (short)2, this.registro, txtAlerta);
            }
        } else if (this.moduloDeclaracao.equals("3") && !this.dt.validaMes(this.anoMesInicCompet, "yyyyMM", 1)) {
            txtAlerta = "M\u00f3dulo declara\u00e7\u00e3o: M\u00f3dulo de Informa\u00e7\u00f5es Comuns aos Mun\u00edcipios, m\u00eas inicio de compet\u00eancia informado: " + this.anoMesInicCompet;
            this.regUtil.setAlerta(this.linha, "A007", coluna, (short)2, this.registro, txtAlerta);
        }
        if (this.dt.validaDiferencaParaAnoCorrente(this.anoMesInicCompet, "yyyyMM", ">", 0)) {
            txtSolucao = "Ano de compet\u00eancia informado: " + this.anoMesInicCompet;
            this.regUtil.setErro(this.linha, "ED005", coluna, (short)1, this.registro, txtSolucao);
        }
    }

    private void verAnoMesFimCompet() throws Exception {
        String txtSolucao;
        int coluna = 8;
        this.anoMesFimCompet = this.regUtil.contCaracterRegistro(this.linha, this.token[7].trim(), 6, coluna, this.registro);
        if (this.anoMesFimCompet == null || this.anoMesFimCompet.equals("") || !this.dt.validaData(this.anoMesFimCompet, "yyyyMM")) {
            this.regUtil.setErro(this.linha, "EG007", coluna, (short)1, this.registro);
        } else if (this.dt.validaDiferencaParaAnoCorrente(this.anoMesFimCompet, "yyyyMM", ">", 0)) {
            txtSolucao = "Ano de compet\u00eancia informado: " + this.anoMesFimCompet;
            this.regUtil.setErro(this.linha, "ED005", coluna, (short)1, this.registro, txtSolucao);
        } else if (!this.dt.validaDiferencaParaAnoCorrente(this.anoMesFimCompet, "yyyyMM", ">", -10)) {
            txtSolucao = "Ano de compet\u00eancia informado: " + this.anoMesFimCompet;
            this.regUtil.setErro(this.linha, "ED004", coluna, (short)1, this.registro, txtSolucao);
        } else if (!this.dt.validaIgualdadeEntreData(this.anoMesInicCompet, this.anoMesFimCompet, "yyyyMM", 0)) {
            txtSolucao = "Ano de compet\u00eancia inicial informado: " + this.anoMesInicCompet.substring(0, 4) + ", ano de compet\u00eancia final informado: " + this.anoMesFimCompet.substring(0, 4);
            this.regUtil.setErro(this.linha, "ED052", coluna, (short)1, this.registro, txtSolucao);
        } else if (this.dt.comparaDataMaior(this.anoMesInicCompet, this.anoMesFimCompet, "yyyyMM")) {
            txtSolucao = "Ano e m\u00eas de in\u00edcio da compet\u00eancia da declara\u00e7\u00e3o informado: " + this.anoMesInicCompet + ", \u00e9 maior que o ano e m\u00eas de fim da compet\u00eancia da declara\u00e7\u00e3o informado: " + this.anoMesFimCompet;
            this.regUtil.setErro(this.linha, "ED054", coluna, (short)1, this.registro, txtSolucao);
        }
        if (this.moduloDeclaracao.equals("2")) {
            txtSolucao = "Ano-m\u00eas in\u00edcio: " + this.anoMesInicCompet + " <BR>ano-m\u00eas fim da compet\u00eancia: " + this.anoMesFimCompet;
            if (!this.dt.validaIgualdadeEntreData(this.anoMesInicCompet, this.anoMesFimCompet, "yyyyMM", 3)) {
                this.regUtil.setErro(this.linha, "ED023", coluna, (short)1, this.registro, txtSolucao);
            }
        }
        if (this.moduloDeclaracao.equals("1") || this.moduloDeclaracao.equals("3")) {
            String txtModulo = this.moduloDeclaracao.equals("1") ? "M\u00f3dulo Cont\u00e1bil" : "M\u00f3dulo de Informa\u00e7\u00f5es Comuns aos Mun\u00edcipios";
            if (!this.dt.validaMes(this.anoMesFimCompet, "yyyyMM", 12)) {
                String txtAlerta = "M\u00f3dulo declara\u00e7\u00e3o: " + txtModulo + ", m\u00eas inicio de compet\u00eancia informado: " + this.anoMesFimCompet;
                this.regUtil.setAlerta(this.linha, "A001", coluna, (short)2, this.registro, txtAlerta);
            }
        }
    }

    private void verModuloDeclaracao() throws Exception {
        int coluna = 9;
        this.moduloDeclaracao = this.regUtil.contCaracterRegistro(this.linha, this.token[8].trim(), 1, coluna, this.registro);
        if (this.moduloDeclaracao.equals("")) {
            this.regUtil.setErro(this.linha, "ED014", coluna, (short)1, this.registro);
        } else if (!this.regUtil.isInteiro(this.moduloDeclaracao)) {
            String txtSolucao = "Valor invalido, n\u00e3o num\u00e9rico: " + this.moduloDeclaracao;
            this.regUtil.setErro(this.linha, "EG008", coluna, (short)1, this.registro, txtSolucao);
        } else if (!this.moduloDeclaracao.equals("1") & !this.moduloDeclaracao.equals("2") & !this.moduloDeclaracao.equals("3")) {
            String txtSolucao = "M\u00f3dulo da declara\u00e7\u00e3o informado: " + this.moduloDeclaracao;
            this.regUtil.setErro(this.linha, "ED015", coluna, (short)1, this.registro, txtSolucao);
        }
    }

    private void verTipoDeclaracao() throws Exception {
        int coluna = 10;
        this.tipoDeclaracao = this.regUtil.contCaracterRegistro(this.linha, this.token[9].trim(), 1, coluna, this.registro);
        if (this.tipoDeclaracao.equals("")) {
            this.regUtil.setErro(this.linha, "ED018", coluna, (short)1, this.registro);
        } else if (!this.regUtil.isInteiro(this.tipoDeclaracao)) {
            String txtSolucao = "Valor invalido, n\u00e3o num\u00e9rico: " + this.tipoDeclaracao;
            this.regUtil.setErro(this.linha, "EG008", coluna, (short)1, this.registro, txtSolucao);
        } else if (this.moduloDeclaracao.equals("3")) {
            if (!this.tipoDeclaracao.equals("1")) {
                String txtSolucao = "M\u00f3dulo da declara\u00e7\u00e3o: " + this.moduloDeclaracao + ", Tipo de declara\u00e7\u00e3o informado: " + this.tipoDeclaracao;
                this.regUtil.setErro(this.linha, "ED046", coluna, (short)1, this.registro, txtSolucao);
            }
        } else if (!this.tipoDeclaracao.equals("1") && !this.tipoDeclaracao.equals("2")) {
            String txtSolucao = "Tipo de declara\u00e7\u00e3o informado: " + this.tipoDeclaracao;
            this.regUtil.setErro(this.linha, "ED006", coluna, (short)1, this.registro, txtSolucao);
        }
    }

    private void verprotcDeclaracaoAnterio() throws Exception {
        String txtSolucao;
        int coluna = 11;
        this.protcDeclaracaoAnterior = this.regUtil.contCaracterRegistro(this.linha, this.token[10].trim(), 30, coluna, this.registro);
        if ((this.moduloDeclaracao.equals("2") || this.moduloDeclaracao.equals("1")) && this.tipoDeclaracao.equals("2") && (this.protcDeclaracaoAnterior == null || this.protcDeclaracaoAnterior.equals(""))) {
            txtSolucao = "Tipo de declara\u00e7\u00e3o: " + this.tipoDeclaracao;
            this.regUtil.setErro(this.linha, "ED024", coluna, (short)1, this.registro, txtSolucao);
        }
        if (this.tipoDeclaracao.equals("1") && this.protcDeclaracaoAnterior != null && !this.protcDeclaracaoAnterior.equals("")) {
            txtSolucao = "Tipo de declara\u00e7\u00e3o: " + this.tipoDeclaracao + "<BR>protocolo: " + this.protcDeclaracaoAnterior;
            this.regUtil.setErro(this.linha, "ED026", coluna, (short)1, this.registro, txtSolucao);
        }
    }

    private void verTipoConsolidacao() throws Exception {
        String txtSolucao;
        int coluna = 12;
        this.tipoConsolidacao = this.regUtil.contCaracterRegistro(this.linha, this.token[11].trim(), 1, coluna, this.registro);
        if (!this.tipoConsolidacao.equals("") && !this.regUtil.isInteiro(this.tipoConsolidacao)) {
            txtSolucao = "Valor invalido, n\u00e3o num\u00e9rico: " + this.tipoConsolidacao;
            this.regUtil.setErro(this.linha, "EG008", coluna, (short)1, this.registro, txtSolucao);
        }
        if (this.moduloDeclaracao.equals("2")) {
            if (this.tipoConsolidacao.equals("")) {
                txtSolucao = "Modulo da declara\u00e7\u00e3o: " + this.moduloDeclaracao;
                this.regUtil.setErro(this.linha, "ED012", coluna, (short)1, this.registro, txtSolucao);
            }
            if (!this.verificaTipoConsolidacaoValidaMunicipio(this.tipoConsolidacao)) {
                String txtSolucao1 = "Tipo de consolida\u00e7\u00e3o: " + this.tipoConsolidacao;
                this.regUtil.setErro(this.linha, "ED022", coluna, (short)1, this.registro, txtSolucao1);
            }
            if (!(this.tipoConsolidacao.equals("1") || this.tipoConsolidacao.equals("2") || this.tipoConsolidacao.equals("3") || this.tipoConsolidacao.equals("4"))) {
                String txtSolucao2 = "M\u00f3dulo da declara\u00e7\u00e3o: " + this.moduloDeclaracao + " <BR>tipo de consolida\u00e7\u00e3o: " + this.tipoConsolidacao;
                this.regUtil.setErro(this.linha, "ED031", coluna, (short)1, this.registro, txtSolucao2);
            }
        }
        if (!this.moduloDeclaracao.equals("2") && !this.tipoConsolidacao.equals("") && this.tipoConsolidacao != null) {
            txtSolucao = "M\u00f3dulo da declara\u00e7\u00e3o: " + this.moduloDeclaracao + " <BR>tipo de consolida\u00e7\u00e3o: " + this.tipoConsolidacao;
            this.regUtil.setErro(this.linha, "ED021", coluna, (short)1, this.registro, txtSolucao);
        }
        if ((this.moduloDeclaracao.equals("1") || this.moduloDeclaracao.equals("3")) && !this.tipoConsolidacao.equals("") && this.tipoConsolidacao != null) {
            txtSolucao = "M\u00f3dulo da declara\u00e7\u00e3o: " + this.moduloDeclaracao + " <BR>tipo de consolida\u00e7\u00e3o: " + this.tipoConsolidacao;
            this.regUtil.setErro(this.linha, "ED047", coluna, (short)1, this.registro, txtSolucao);
        }
    }

    private void verCnpjResponsavelRecolhimento() throws Exception {
        String txtSolucao;
        int coluna = 13;
        this.cnpjResponsavelRecolhimento = this.regUtil.contCaracterRegistro(this.linha, this.token[12].trim(), 6, coluna, this.registro);
        if (!this.cnpjResponsavelRecolhimento.equals("")) {
            if (!CpfCnpj.validarCpfCnpj((String)(this.cnpj + this.cnpjResponsavelRecolhimento))) {
                txtSolucao = "CNPJ: " + this.cnpj + this.cnpjResponsavelRecolhimento;
                this.regUtil.setErro(this.linha, "EG004", coluna, (short)1, this.registro, txtSolucao);
            }
            if (this.moduloDeclaracao.equals("1")) {
                txtSolucao = "CNPJ respons\u00e1vel pelo recolhimento: " + this.cnpjResponsavelRecolhimento + " <BR>m\u00f3dulo da declara\u00e7\u00e3o: " + this.moduloDeclaracao;
                this.regUtil.setErro(this.linha, "ED034", coluna, (short)1, this.registro, txtSolucao);
            }
        } else if (this.moduloDeclaracao.equals("2") && (this.tipoConsolidacao.equals("1") || this.tipoConsolidacao.equals("2"))) {
            txtSolucao = "M\u00f3dulo da declara\u00e7\u00e3o: " + this.moduloDeclaracao + "<BR>tipo de consolida\u00e7\u00e3o: " + this.tipoConsolidacao;
            this.regUtil.setErro(this.linha, "ED013", coluna, (short)1, this.registro, txtSolucao);
        }
        if ((this.moduloDeclaracao.equals("1") || this.moduloDeclaracao.equals("3")) && !this.cnpjResponsavelRecolhimento.equals("") && this.cnpjResponsavelRecolhimento != null) {
            txtSolucao = "M\u00f3dulo da declara\u00e7\u00e3o: " + this.moduloDeclaracao + ", Respons\u00e1vel pelo recolhimento: " + this.cnpjResponsavelRecolhimento;
            this.regUtil.setErro(this.linha, "ED048", coluna, (short)1, this.registro, txtSolucao);
        }
        if (this.moduloDeclaracao.equals("2") && (this.tipoConsolidacao.equals("3") || this.tipoConsolidacao.equals("4")) && !this.cnpjResponsavelRecolhimento.equals("") && this.cnpjResponsavelRecolhimento != null) {
            txtSolucao = "M\u00f3dulo da declara\u00e7\u00e3o: " + this.moduloDeclaracao + ", tipo de consolida\u00e7\u00e3o: " + this.tipoConsolidacao + ", respons\u00e1vel pelo recolhimento: " + this.cnpjResponsavelRecolhimento;
            this.regUtil.setErro(this.linha, "ED051", coluna, (short)1, this.registro, txtSolucao);
        }
    }

    public boolean verificaTipoConsolidacaoValidaMunicipio(String tipoConsolidacao) {
        boolean flag = false;
        for (int i = 0; i < ConstantsBusiness.TIPO_CONSOLIDACAO_ACEITA.length; ++i) {
            if (!tipoConsolidacao.equals(ConstantsBusiness.TIPO_CONSOLIDACAO_ACEITA[i])) continue;
            flag = true;
        }
        return flag;
    }

    public IdentificacaoDeclaracao getDeclaracaoPojo() {
        try {
            Date anoMIC = this.regUtil.parseData(this.anoMesInicCompet + "01", "yyyyMMdd");
            String[] dataSeparada = this.dt.separaData(this.anoMesFimCompet);
            int ultimoDiaMes = this.dt.ultimoDiaMes(Integer.parseInt(dataSeparada[1]), Integer.parseInt(dataSeparada[0]));
            Date anoMFC = this.regUtil.parseData(this.anoMesFimCompet + ultimoDiaMes, "yyyyMMdd");
            this.declaracao = this.tipoConsolidacao.equals("") ? new IdentificacaoDeclaracao(new Integer(Integer.parseInt(this.numLinha)), this.tipoInstituicao.toUpperCase(), new Long(Long.parseLong(this.codMunicipio)), new Integer(Integer.parseInt(this.numLinha)), this.cnpj, this.nome, anoMIC, anoMFC, new Short(Short.parseShort(this.moduloDeclaracao)), new Short(Short.parseShort(this.tipoDeclaracao)), this.cnpjResponsavelRecolhimento, this.protcDeclaracaoAnterior) : new IdentificacaoDeclaracao(new Integer(Integer.parseInt(this.numLinha)), this.tipoInstituicao.toUpperCase(), new Long(Long.parseLong(this.codMunicipio)), new Integer(Integer.parseInt(this.numLinha)), this.cnpj, this.nome, anoMIC, anoMFC, new Short(Short.parseShort(this.moduloDeclaracao)), new Short(Short.parseShort(this.tipoDeclaracao)), new Short(Short.parseShort(this.tipoConsolidacao)), this.cnpjResponsavelRecolhimento, this.protcDeclaracaoAnterior);
            return this.declaracao;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

