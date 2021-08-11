/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.registros;

import br.gov.pbh.desif.model.pojo.ApuracaoReceita;
import br.gov.pbh.desif.model.registros.RegUtil;

public class Registro0430 {
    ApuracaoReceita apuracaoMensalIssqn;
    private RegUtil regUtil;
    private String numLinha;
    private String codDependencia;
    private String subTitulo;
    private String codTributacaoDesif;
    private String valorCreditoMensal;
    private String valorDebitoMensal;
    private String receitaDeclarada;
    private String deducaoReceitaDeclarada;
    private String descDeducao;
    private String baseCalculo;
    private String aliquotaIssqn;
    private String incentivoFiscal;
    private String descIncentivoFiscal;
    private String motivoNaoExigibilidade;
    private String processoMotivoNaoExigibilidade;
    private String[] token;
    private int linha;
    private String registro;
    private String txtErroCasasDecimais;

    public Registro0430(String[] token, int linha) {
        this.registro = token[1];
        this.regUtil = new RegUtil();
        this.token = token;
        this.linha = linha;
        this.txtErroCasasDecimais = "Quantidade de casas decimais diferente de duas: ";
        this.verNumLinha();
        this.verCodDependencia();
        this.verSubTitulo();
        this.verCodTributacaoDesif();
        this.verValorCreditoMensal();
        this.verValorDebitoMensal();
        this.verReceitaDeclarada();
        this.verDeducaoReceitaDeclarada();
        this.verDescDeducao();
        this.verBaseCalculo();
        this.verAliquotaIssqn();
        this.verIncentivoFiscal();
        this.verDescIncentivoFiscal();
        this.verMotivoNaoExigibilidade();
        this.verProcessoMotivoNaoExigibilidade();
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

    private void verSubTitulo() {
        int coluna = 4;
        this.subTitulo = this.regUtil.contCaracterRegistro(this.linha, this.token[3].trim(), 30, coluna, this.registro);
        if (this.subTitulo.equals("")) {
            String txtSolucao = "C\u00f3digo da depend\u00eancia: " + this.codDependencia + "<BR>aliquota: " + this.aliquotaIssqn;
            this.regUtil.setErro(this.linha, "EM071", coluna, (short)1, this.registro, txtSolucao);
        }
    }

    private void verCodTributacaoDesif() {
        int coluna = 5;
        this.codTributacaoDesif = this.regUtil.contCaracterRegistro(this.linha, this.token[4].trim(), 20, coluna, this.registro);
        if (this.codTributacaoDesif.equals("")) {
            String txtSolucao = "C\u00f3digo da depend\u00eancia: " + this.codDependencia + "<BR>subt\u00edtulo: " + this.subTitulo;
            this.regUtil.setErro(this.linha, "EM004", coluna, (short)1, this.registro, txtSolucao);
        }
    }

    private void verValorCreditoMensal() {
        int coluna = 6;
        this.valorCreditoMensal = this.regUtil.contCaracterRegistro(this.linha, this.token[5].trim(), 19, coluna, this.registro);
        if (this.valorCreditoMensal.equals("")) {
            String txtSolucao = "C\u00f3digo de depend\u00eancia: " + this.codDependencia + "<BR>subt\u00edtulo: " + this.subTitulo;
            this.regUtil.setErro(this.linha, "EM061", coluna, (short)1, this.registro, txtSolucao);
        } else if (!this.regUtil.isNumeric(this.valorCreditoMensal)) {
            String txtSolucao = "Valor invalido, n\u00e3o num\u00e9rico: " + this.valorCreditoMensal;
            this.regUtil.setErro(this.linha, "EG008", coluna, (short)1, this.registro, txtSolucao);
        } else if (!this.regUtil.verificaCasasDecimais(this.valorCreditoMensal)) {
            String txtSolucao = this.txtErroCasasDecimais + this.valorCreditoMensal;
            this.regUtil.setErro(this.linha, "EG009", coluna, (short)1, this.registro, txtSolucao);
        }
    }

    private void verValorDebitoMensal() {
        int coluna = 7;
        this.valorDebitoMensal = this.regUtil.contCaracterRegistro(this.linha, this.token[6].trim(), 19, coluna, this.registro);
        if (this.valorDebitoMensal.equals("")) {
            String txtSolucao = "C\u00f3digo de depend\u00eancia: " + this.codDependencia + "<BR>subt\u00edtulo: " + this.subTitulo;
            this.regUtil.setErro(this.linha, "EM062", coluna, (short)1, this.registro, txtSolucao);
        } else if (!this.regUtil.isNumeric(this.valorDebitoMensal)) {
            String txtSolucao = "Valor invalido, n\u00e3o num\u00e9rico: " + this.valorDebitoMensal;
            this.regUtil.setErro(this.linha, "EG008", coluna, (short)1, this.registro, txtSolucao);
        } else if (!this.regUtil.verificaCasasDecimais(this.valorDebitoMensal)) {
            String txtSolucao = this.txtErroCasasDecimais + this.valorDebitoMensal;
            this.regUtil.setErro(this.linha, "EG009", coluna, (short)1, this.registro, txtSolucao);
        }
    }

    private void verReceitaDeclarada() {
        int coluna = 8;
        this.receitaDeclarada = this.regUtil.contCaracterRegistro(this.linha, this.token[7].trim(), 19, coluna, this.registro);
        if (this.receitaDeclarada.equals("")) {
            String txtSolucao = "C\u00f3digo de depend\u00eancia: " + this.codDependencia + "<BR>subt\u00edtulo: " + this.subTitulo;
            this.regUtil.setErro(this.linha, "EM063", coluna, (short)1, this.registro, txtSolucao);
        } else if (!this.regUtil.isNumeric(this.receitaDeclarada)) {
            String txtSolucao = "Valor invalido, n\u00e3o num\u00e9rico: " + this.receitaDeclarada;
            this.regUtil.setErro(this.linha, "EG008", coluna, (short)1, this.registro, txtSolucao);
        } else if (!this.regUtil.verificaCasasDecimais(this.receitaDeclarada)) {
            String txtSolucao = this.txtErroCasasDecimais + this.receitaDeclarada;
            this.regUtil.setErro(this.linha, "EG009", coluna, (short)1, this.registro, txtSolucao);
        }
    }

    private void verDeducaoReceitaDeclarada() {
        int coluna = 9;
        this.deducaoReceitaDeclarada = this.regUtil.contCaracterRegistro(this.linha, this.token[8].trim(), 19, coluna, this.registro);
        if (!this.deducaoReceitaDeclarada.equals("")) {
            this.deducaoReceitaDeclarada = this.regUtil.parseZero(this.deducaoReceitaDeclarada);
        }
        if (!this.deducaoReceitaDeclarada.equals("")) {
            if (!this.regUtil.isNumeric(this.deducaoReceitaDeclarada)) {
                String txtSolucao = "Valor invalido, n\u00e3o num\u00e9rico: " + this.deducaoReceitaDeclarada;
                this.regUtil.setErro(this.linha, "EG008", coluna, (short)1, this.registro, txtSolucao);
            } else if (!this.regUtil.verificaCasasDecimais(this.deducaoReceitaDeclarada)) {
                String txtSolucao = this.txtErroCasasDecimais + this.deducaoReceitaDeclarada;
                this.regUtil.setErro(this.linha, "EG009", coluna, (short)1, this.registro, txtSolucao);
            }
        }
    }

    private void verDescDeducao() {
        String txtSolucao;
        int coluna = 10;
        this.descDeducao = this.regUtil.contCaracterRegistro(this.linha, this.token[9].trim(), 255, coluna, this.registro);
        if (!this.deducaoReceitaDeclarada.equals("") && (this.descDeducao.equals("") || this.descDeducao == null)) {
            txtSolucao = "C\u00f3digo de depend\u00eancia: " + this.codDependencia + "<BR>subt\u00edtulo: " + this.subTitulo + "<BR>dedu\u00e7\u00e3o da receita declarada: " + this.deducaoReceitaDeclarada;
            this.regUtil.setErro(this.linha, "EM029", coluna, (short)1, this.registro, txtSolucao);
        }
        if (this.deducaoReceitaDeclarada.equals("") && !this.descDeducao.equals("")) {
            txtSolucao = "C\u00f3digo de depend\u00eancia: " + this.codDependencia + "<BR>descri\u00e7\u00e3o da dedu\u00e7\u00e3o da receita declarada: " + this.descDeducao;
            this.regUtil.setErro(this.linha, "EM072", coluna, (short)1, this.registro, txtSolucao);
        }
    }

    private void verBaseCalculo() {
        int coluna = 11;
        this.baseCalculo = this.regUtil.contCaracterRegistro(this.linha, this.token[10].trim(), 19, coluna, this.registro);
        if (this.baseCalculo.equals("")) {
            String txtSolucao = "c\u00f3digo de depend\u00eancia: " + this.codDependencia + "<BR>subt\u00edtulo: " + this.subTitulo;
            this.regUtil.setErro(this.linha, "EM030", coluna, (short)1, this.registro, txtSolucao);
        } else if (!this.regUtil.isNumeric(this.baseCalculo)) {
            String txtSolucao = "Valor invalido, n\u00e3o num\u00e9rico: " + this.baseCalculo;
            this.regUtil.setErro(this.linha, "EG008", coluna, (short)1, this.registro, txtSolucao);
        } else if (!this.regUtil.verificaCasasDecimais(this.baseCalculo)) {
            String txtSolucao = this.txtErroCasasDecimais + this.baseCalculo;
            this.regUtil.setErro(this.linha, "EG009", coluna, (short)1, this.registro, txtSolucao);
        }
    }

    private void verAliquotaIssqn() {
        int coluna = 12;
        this.aliquotaIssqn = this.regUtil.contCaracterRegistro(this.linha, this.token[11].trim(), 7, coluna, this.registro);
        if (this.aliquotaIssqn.equals("")) {
            String txtSolucao = "C\u00f3digo de depend\u00eancia: " + this.codDependencia;
            this.regUtil.setErro(this.linha, "EM038", coluna, (short)1, this.registro, txtSolucao);
        } else if (!this.regUtil.isNumeric(this.aliquotaIssqn)) {
            String txtSolucao = "Valor invalido, n\u00e3o num\u00e9rico: " + this.aliquotaIssqn;
            this.regUtil.setErro(this.linha, "EG008", coluna, (short)1, this.registro, txtSolucao);
        } else if (!this.regUtil.verificaCasasDecimais(this.aliquotaIssqn)) {
            String txtSolucao = this.txtErroCasasDecimais + this.aliquotaIssqn;
            this.regUtil.setErro(this.linha, "EG009", coluna, (short)1, this.registro, txtSolucao);
        }
    }

    private void verIncentivoFiscal() {
        int coluna = 13;
        this.incentivoFiscal = this.regUtil.contCaracterRegistro(this.linha, this.token[12].trim(), 19, coluna, this.registro);
        if (!this.incentivoFiscal.equals("")) {
            this.incentivoFiscal = this.regUtil.parseZero(this.incentivoFiscal);
        }
        if (!this.incentivoFiscal.equals("")) {
            if (!this.regUtil.isNumeric(this.incentivoFiscal)) {
                String txtSolucao = "Valor invalido, n\u00e3o num\u00e9rico: " + this.incentivoFiscal;
                this.regUtil.setErro(this.linha, "EG008", coluna, (short)1, this.registro, txtSolucao);
            } else if (!this.regUtil.verificaCasasDecimais(this.incentivoFiscal)) {
                String txtSolucao = this.txtErroCasasDecimais + this.incentivoFiscal;
                this.regUtil.setErro(this.linha, "EG009", coluna, (short)1, this.registro, txtSolucao);
            }
        }
    }

    private void verDescIncentivoFiscal() {
        int coluna = 14;
        this.descIncentivoFiscal = this.regUtil.contCaracterRegistro(this.linha, this.token[13].trim(), 255, coluna, this.registro);
        if (!this.incentivoFiscal.equals("") && this.descIncentivoFiscal.equals("")) {
            String aux = this.codDependencia + "  " + this.subTitulo + "  " + this.descIncentivoFiscal + "  " + this.incentivoFiscal;
            this.regUtil.setErro(this.linha, "EM042", coluna, (short)1, this.registro, aux);
        } else if (this.incentivoFiscal.equals("") && !this.descIncentivoFiscal.equals("")) {
            String txtSolucao = "C\u00f3digo de depend\u00eancia: " + this.codDependencia + "<BR>subt\u00edtulo: " + this.subTitulo + "descri\u00e7\u00e3o  do incentivo fiscal: " + this.descIncentivoFiscal;
            this.regUtil.setErro(this.linha, "EM035", coluna, (short)1, this.registro, txtSolucao);
        }
    }

    private void verMotivoNaoExigibilidade() {
        int coluna = 15;
        this.motivoNaoExigibilidade = this.regUtil.contCaracterRegistro(this.linha, this.token[14].trim(), 1, coluna, this.registro);
        if (!this.motivoNaoExigibilidade.equals("")) {
            String txtSolucao;
            if (!this.regUtil.isNumeric(this.motivoNaoExigibilidade)) {
                txtSolucao = "Valor invalido, n\u00e3o num\u00e9rico: " + this.motivoNaoExigibilidade;
                this.regUtil.setErro(this.linha, "EG008", coluna, (short)1, this.registro, txtSolucao);
            }
            if (!this.motivoNaoExigibilidade.equals("1") && !this.motivoNaoExigibilidade.equals("2")) {
                txtSolucao = "Motivo de n\u00e3o exigibilidade: " + this.motivoNaoExigibilidade;
                this.regUtil.setErro(this.linha, "EM016", coluna, (short)1, this.registro, txtSolucao);
            }
        }
    }

    private void verProcessoMotivoNaoExigibilidade() {
        int coluna = 16;
        this.processoMotivoNaoExigibilidade = this.regUtil.contCaracterRegistro(this.linha, this.token[15].trim(), 20, coluna, this.registro);
        if (this.motivoNaoExigibilidade.equals("1") | this.motivoNaoExigibilidade.equals("2") && this.processoMotivoNaoExigibilidade.equals("")) {
            String txtSolucao = "C\u00f3digo depend\u00eancia: " + this.codDependencia + "<BR>motivo de n\u00e3o exigibilidade: " + this.motivoNaoExigibilidade;
            this.regUtil.setErro(this.linha, "EM044", coluna, (short)1, this.registro, txtSolucao);
        }
    }

    public ApuracaoReceita getApuracaoMensalIssqnPojo() {
        this.valorCreditoMensal = this.regUtil.trocaVirgulaPonto(this.valorCreditoMensal);
        this.valorDebitoMensal = this.regUtil.trocaVirgulaPonto(this.valorDebitoMensal);
        this.receitaDeclarada = this.regUtil.trocaVirgulaPonto(this.receitaDeclarada);
        this.deducaoReceitaDeclarada = this.regUtil.trocaVirgulaPonto(this.deducaoReceitaDeclarada);
        this.baseCalculo = this.regUtil.trocaVirgulaPonto(this.baseCalculo);
        this.aliquotaIssqn = this.regUtil.trocaVirgulaPonto(this.aliquotaIssqn);
        this.incentivoFiscal = this.regUtil.trocaVirgulaPonto(this.incentivoFiscal);
        if (this.motivoNaoExigibilidade.equals("")) {
            this.motivoNaoExigibilidade = null;
        }
        this.apuracaoMensalIssqn = this.motivoNaoExigibilidade != null ? new ApuracaoReceita(new Long(Long.parseLong(this.numLinha)), this.codTributacaoDesif, new Long(Long.parseLong(this.numLinha)), this.codDependencia, this.subTitulo, new Double(Double.parseDouble(this.valorCreditoMensal)), new Double(Double.parseDouble(this.valorDebitoMensal)), new Double(Double.parseDouble(this.receitaDeclarada)), new Double(Double.parseDouble(this.baseCalculo)), new Double(Double.parseDouble(this.aliquotaIssqn)), new Double(Double.parseDouble(this.deducaoReceitaDeclarada)), this.descDeducao, new Double(Double.parseDouble(this.incentivoFiscal)), this.descIncentivoFiscal, new Short(Short.parseShort(this.motivoNaoExigibilidade)), this.processoMotivoNaoExigibilidade, null) : new ApuracaoReceita(new Long(Long.parseLong(this.numLinha)), this.codTributacaoDesif, new Long(Long.parseLong(this.numLinha)), this.codDependencia, this.subTitulo, new Double(Double.parseDouble(this.valorCreditoMensal)), new Double(Double.parseDouble(this.valorDebitoMensal)), new Double(Double.parseDouble(this.receitaDeclarada)), new Double(Double.parseDouble(this.baseCalculo)), new Double(Double.parseDouble(this.aliquotaIssqn)), new Double(Double.parseDouble(this.deducaoReceitaDeclarada)), this.descDeducao, new Double(Double.parseDouble(this.incentivoFiscal)), this.descIncentivoFiscal, null, this.processoMotivoNaoExigibilidade, null);
        return this.apuracaoMensalIssqn;
    }
}

