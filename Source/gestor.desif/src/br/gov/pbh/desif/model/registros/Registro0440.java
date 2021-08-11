/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.registros;

import br.gov.pbh.desif.model.pojo.IssqnMensal;
import br.gov.pbh.desif.model.pojo.OrigemCreditoCompensar;
import br.gov.pbh.desif.model.registros.Data;
import br.gov.pbh.desif.model.registros.RegUtil;
import br.gov.pbh.desif.service.arquivo.ExceptionEncoding;
import br.gov.pbh.desif.view.util.SwingUtils;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Registro0440 {
    Data dt = new Data();
    private IssqnMensal demMensIssqn;
    private RegUtil regUtil;
    private String numLinha;
    private String cnpj;
    private String receitaDeclaradaConsolidada;
    private String baseCalculo;
    private String aliquotaIssqn;
    private String issqnDevido;
    private String codTributacaoDesif;
    private String deducaoReceitaDeclaradaSubTitulo;
    private String deducaoReceitaDeclaradaConsolidada;
    private String descDeducao;
    private String valorIssqnRetido;
    private String incentivoFiscalSubTitulo;
    private String incentivoFiscal;
    private String issqnRecolhido;
    private String issqnARecolher;
    private String descIncentivoFiscal;
    private String valorCredito;
    private String motivoNaoExigibilidade;
    private String processoMotivoNaoExigibilidade;
    private String origemCreditoACompensar;
    private String cmpeOrigCred;
    private String valrOrigCred;
    private String[] token;
    private int linha;
    private Set origCredCompensar = new HashSet();
    private String registro;
    private String txtErroCasasDecimais;

    public Registro0440(String[] token, int linha) {
        this.registro = token[1];
        this.regUtil = new RegUtil();
        this.token = token;
        this.linha = linha;
        this.txtErroCasasDecimais = "Quantidade de casas decimais diferente de duas: ";
        this.verNumLinha();
        this.verCnpj();
        this.verReceitaDeclaradaConsolidada();
        this.verBaseCalculo();
        this.verAliquotaIssqn();
        this.verValorIssqnDevido();
        this.verCodTributacaoDesif();
        this.verDeducaoReceitaDeclaradaSubTitulo();
        this.verDeducaoReceitaDeclaradaConsolidada();
        this.verDescDeducao();
        this.verValorIssqnRetido();
        this.verIncentivoFiscalSubTitulo();
        this.verIncentivoFiscal();
        this.verIssqnRecolhido();
        this.verIssqnARecolher();
        this.verDescIncentivoFiscal();
        this.verValorCredito();
        this.verMotivoNaoExigibilidade();
        this.verProcessoMotivoNaoExigibilidade();
        this.verOrigemCreditoAcompensar();
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

    private void verCnpj() {
        int coluna = 3;
        this.cnpj = this.regUtil.contCaracterRegistro(this.linha, this.token[2].trim(), 6, coluna, this.registro);
        if (this.cnpj.equals("")) {
            this.regUtil.setErro(this.linha, "EG004", coluna, (short)1, this.registro);
        }
    }

    private void verCodTributacaoDesif() {
        int coluna = 4;
        this.codTributacaoDesif = this.regUtil.contCaracterRegistro(this.linha, this.token[3].trim(), 20, coluna, this.registro);
    }

    private void verReceitaDeclaradaConsolidada() {
        int coluna = 5;
        this.receitaDeclaradaConsolidada = this.regUtil.contCaracterRegistro(this.linha, this.token[4].trim(), 19, coluna, this.registro);
        if (this.receitaDeclaradaConsolidada.equals("")) {
            String txtSolucao = "CNPJ: " + this.cnpj;
            this.regUtil.setErro(this.linha, "EM039", coluna, (short)1, this.registro, txtSolucao);
        } else if (!this.regUtil.isNumeric(this.receitaDeclaradaConsolidada)) {
            String txtSolucao = "Valor invalido, n\u00e3o num\u00e9rico: " + this.receitaDeclaradaConsolidada;
            this.regUtil.setErro(this.linha, "EG008", coluna, (short)1, this.registro, txtSolucao);
        } else if (!this.regUtil.verificaCasasDecimais(this.receitaDeclaradaConsolidada)) {
            String txtSolucao = this.txtErroCasasDecimais + this.receitaDeclaradaConsolidada;
            this.regUtil.setErro(this.linha, "EG009", coluna, (short)1, this.registro, txtSolucao);
        }
    }

    private void verDeducaoReceitaDeclaradaSubTitulo() {
        int coluna = 6;
        this.deducaoReceitaDeclaradaSubTitulo = this.regUtil.contCaracterRegistro(this.linha, this.token[5].trim(), 19, coluna, this.registro);
        if (!this.deducaoReceitaDeclaradaSubTitulo.equals("")) {
            if (!this.regUtil.isNumeric(this.deducaoReceitaDeclaradaSubTitulo)) {
                String txtSolucao = "Valor invalido, n\u00e3o num\u00e9rico: " + this.deducaoReceitaDeclaradaSubTitulo;
                this.regUtil.setErro(this.linha, "EG008", coluna, (short)1, this.registro, txtSolucao);
            } else if (!this.regUtil.verificaCasasDecimais(this.deducaoReceitaDeclaradaSubTitulo)) {
                String txtSolucao = this.txtErroCasasDecimais + this.deducaoReceitaDeclaradaSubTitulo;
                this.regUtil.setErro(this.linha, "EG009", coluna, (short)1, this.registro, txtSolucao);
            }
        }
    }

    private void verDeducaoReceitaDeclaradaConsolidada() {
        int coluna = 7;
        this.deducaoReceitaDeclaradaConsolidada = this.regUtil.contCaracterRegistro(this.linha, this.token[6].trim(), 19, coluna, this.registro);
        if (!this.deducaoReceitaDeclaradaConsolidada.equals("")) {
            this.deducaoReceitaDeclaradaConsolidada = this.regUtil.parseZero(this.deducaoReceitaDeclaradaConsolidada);
        }
        if (!this.deducaoReceitaDeclaradaConsolidada.equals("")) {
            if (!this.regUtil.isNumeric(this.deducaoReceitaDeclaradaConsolidada)) {
                String txtSolucao = "Valor invalido, n\u00e3o num\u00e9rico: " + this.deducaoReceitaDeclaradaConsolidada;
                this.regUtil.setErro(this.linha, "EG008", coluna, (short)1, this.registro, txtSolucao);
            } else if (!this.regUtil.verificaCasasDecimais(this.deducaoReceitaDeclaradaConsolidada)) {
                String txtSolucao = this.txtErroCasasDecimais + this.deducaoReceitaDeclaradaConsolidada;
                this.regUtil.setErro(this.linha, "EG009", coluna, (short)1, this.registro, txtSolucao);
            }
        }
    }

    private void verDescDeducao() {
        String txtSolucao;
        int coluna = 8;
        this.descDeducao = this.regUtil.contCaracterRegistro(this.linha, this.token[7].trim(), 255, coluna, this.registro);
        if (!this.deducaoReceitaDeclaradaConsolidada.equals("") && this.descDeducao.equals("")) {
            txtSolucao = "CNPJ: " + this.cnpj + "<BR>dedu\u00e7\u00e3o da receita consolidada: " + this.deducaoReceitaDeclaradaConsolidada;
            this.regUtil.setErro(this.linha, "EM052", coluna, (short)1, this.registro, txtSolucao);
        }
        if (this.deducaoReceitaDeclaradaConsolidada.equals("") && !this.descDeducao.equals("")) {
            txtSolucao = "CNPJ: " + this.cnpj + "<BR>descri\u00e7\u00e3o da dedu\u00e7\u00e3o da receita declarada consolidada:  " + this.descDeducao;
            this.regUtil.setErro(this.linha, "EM073", coluna, (short)1, this.registro, txtSolucao);
        }
    }

    private void verBaseCalculo() {
        int coluna = 9;
        this.baseCalculo = this.regUtil.contCaracterRegistro(this.linha, this.token[8].trim(), 19, coluna, this.registro);
        if (this.baseCalculo.equals("")) {
            String txtSolucao = "CNPJ: " + this.cnpj;
            this.regUtil.setErro(this.linha, "EM031", coluna, (short)1, this.registro, txtSolucao);
        } else if (!this.regUtil.isNumeric(this.baseCalculo)) {
            String txtSolucao = "Valor invalido, n\u00e3o num\u00e9rico: " + this.baseCalculo;
            this.regUtil.setErro(this.linha, "EG008", coluna, (short)1, this.registro, txtSolucao);
        } else if (!this.regUtil.verificaCasasDecimais(this.baseCalculo)) {
            String txtSolucao = this.txtErroCasasDecimais + this.baseCalculo;
            this.regUtil.setErro(this.linha, "EG009", coluna, (short)1, this.registro, txtSolucao);
        }
    }

    private void verAliquotaIssqn() {
        int coluna = 10;
        this.aliquotaIssqn = this.regUtil.contCaracterRegistro(this.linha, this.token[9].trim(), 8, coluna, this.registro);
        if (this.aliquotaIssqn.equals("")) {
            String txtSolucao = "CNPJ: " + this.cnpj;
            this.regUtil.setErro(this.linha, "EM038", coluna, (short)1, this.registro, txtSolucao);
        } else if (!this.regUtil.isNumeric(this.aliquotaIssqn)) {
            String txtSolucao = "Valor invalido, n\u00e3o num\u00e9rico: " + this.aliquotaIssqn;
            this.regUtil.setErro(this.linha, "EG008", coluna, (short)1, this.registro, txtSolucao);
        } else if (!this.regUtil.verificaCasasDecimais(this.aliquotaIssqn)) {
            String txtSolucao = this.txtErroCasasDecimais + this.aliquotaIssqn;
            this.regUtil.setErro(this.linha, "EG009", coluna, (short)1, this.registro, txtSolucao);
        }
    }

    private void verValorIssqnDevido() {
        int coluna = 11;
        this.issqnDevido = this.regUtil.contCaracterRegistro(this.linha, this.token[10].trim(), 19, coluna, this.registro);
        if (this.issqnDevido.equals("")) {
            String txtSolucao = "CNPJ: " + this.cnpj;
            this.regUtil.setErro(this.linha, "EM041", coluna, (short)1, this.registro, txtSolucao);
        } else if (!this.regUtil.isNumeric(this.issqnDevido)) {
            String txtSolucao = "Valor invalido, n\u00e3o num\u00e9rico: " + this.issqnDevido;
            this.regUtil.setErro(this.linha, "EG008", coluna, (short)1, this.registro, txtSolucao);
        } else if (!this.regUtil.verificaCasasDecimais(this.issqnDevido)) {
            String txtSolucao = this.txtErroCasasDecimais + this.issqnDevido;
            this.regUtil.setErro(this.linha, "EG009", coluna, (short)1, this.registro, txtSolucao);
        }
    }

    private void verValorIssqnRetido() {
        int coluna = 12;
        this.valorIssqnRetido = this.regUtil.contCaracterRegistro(this.linha, this.token[11].trim(), 19, coluna, this.registro);
        if (!this.valorIssqnRetido.equals("")) {
            if (!this.regUtil.isNumeric(this.valorIssqnRetido)) {
                String txtSolucao = "Valor invalido, n\u00e3o num\u00e9rico: " + this.valorIssqnRetido;
                this.regUtil.setErro(this.linha, "EG008", coluna, (short)1, this.registro, txtSolucao);
            } else if (!this.regUtil.verificaCasasDecimais(this.valorIssqnRetido)) {
                String txtSolucao = this.txtErroCasasDecimais + this.valorIssqnRetido;
                this.regUtil.setErro(this.linha, "EG009", coluna, (short)1, this.registro, txtSolucao);
            }
        }
    }

    private void verIncentivoFiscalSubTitulo() {
        int coluna = 13;
        this.incentivoFiscalSubTitulo = this.regUtil.contCaracterRegistro(this.linha, this.token[12].trim(), 19, coluna, this.registro);
        if (!this.incentivoFiscalSubTitulo.equals("")) {
            this.incentivoFiscalSubTitulo = this.regUtil.parseZero(this.incentivoFiscalSubTitulo);
        }
        if (!this.incentivoFiscalSubTitulo.equals("")) {
            if (!this.regUtil.isNumeric(this.incentivoFiscalSubTitulo)) {
                String txtSolucao = "Valor invalido, n\u00e3o num\u00e9rico: " + this.incentivoFiscalSubTitulo;
                this.regUtil.setErro(this.linha, "EG008", coluna, (short)1, this.registro, txtSolucao);
            } else if (!this.regUtil.verificaCasasDecimais(this.incentivoFiscalSubTitulo)) {
                String txtSolucao = this.txtErroCasasDecimais + this.incentivoFiscalSubTitulo;
                this.regUtil.setErro(this.linha, "EG009", coluna, (short)1, this.registro, txtSolucao);
            }
        }
    }

    private void verIncentivoFiscal() {
        int coluna = 14;
        this.incentivoFiscal = this.regUtil.contCaracterRegistro(this.linha, this.token[13].trim(), 19, coluna, this.registro);
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
        String txtSolucao;
        int coluna = 15;
        this.descIncentivoFiscal = this.regUtil.contCaracterRegistro(this.linha, this.token[14].trim(), 255, coluna, this.registro);
        if (!this.incentivoFiscal.equals("") && (this.descIncentivoFiscal.equals("") || this.descIncentivoFiscal == null)) {
            txtSolucao = "CNPJ: " + this.cnpj + " <BR>incentivo fiscal: " + this.incentivoFiscal;
            this.regUtil.setErro(this.linha, "EM057", coluna, (short)1, this.registro, txtSolucao);
        }
        if (this.incentivoFiscal.equals("") && !this.descIncentivoFiscal.equals("")) {
            txtSolucao = "CNPJ: " + this.cnpj + "<BR>incentivo fiscal: " + this.incentivoFiscal;
            this.regUtil.setErro(this.linha, "EM042", coluna, (short)1, this.registro, txtSolucao);
        }
    }

    private void verValorCredito() {
        int coluna = 16;
        this.valorCredito = this.regUtil.contCaracterRegistro(this.linha, this.token[15].trim(), 19, coluna, this.registro);
        if (!this.valorCredito.equals("")) {
            if (!this.regUtil.isNumeric(this.valorCredito)) {
                String txtSolucao = "Valor invalido, n\u00e3o num\u00e9rico: " + this.valorCredito;
                this.regUtil.setErro(this.linha, "EG008", coluna, (short)1, this.registro, txtSolucao);
            } else if (!this.regUtil.verificaCasasDecimais(this.valorCredito)) {
                String txtSolucao = this.txtErroCasasDecimais + this.valorCredito;
                this.regUtil.setErro(this.linha, "EG009", coluna, (short)1, this.registro, txtSolucao);
            }
        }
    }

    private void verOrigemCreditoAcompensar() {
        try {
            int coluna = 17;
            boolean flag = false;
            this.origemCreditoACompensar = this.token[16].trim();
            if (!this.valorCredito.equals("")) {
                this.valorCredito = this.regUtil.parseZero(this.valorCredito);
            }
            if (!this.valorCredito.equals("") && this.origemCreditoACompensar.equals("")) {
                String txtSolucao = "CNPJ: " + this.cnpj + "<BR>al\u00edquota: " + this.aliquotaIssqn + "<BR>valor do cr\u00e9dito a ser compensado: " + this.origemCreditoACompensar;
                this.regUtil.setErro(this.linha, "EM018", coluna, (short)1, this.registro, txtSolucao);
                flag = true;
            } else {
                if (this.valorCredito.equals("") && this.origemCreditoACompensar.equals("")) {
                    return;
                }
                if (this.origemCreditoACompensar.indexOf("\u00a3") < 0) {
                    throw new ExceptionEncoding();
                }
                String[] s = this.regUtil.multiValorado(this.origemCreditoACompensar, "\u00a7");
                for (int i = 0; i < s.length; ++i) {
                    String txtSolucao;
                    String[] ob = this.regUtil.multiValorado(s[i], "\u00a3");
                    if (ob.length == 2) {
                        this.cmpeOrigCred = this.regUtil.contCaracterRegistro(this.linha, ob[0], 6, coluna, this.registro);
                        if (!ob[1].equals("")) {
                            ob[1] = this.regUtil.parseZero(ob[1]);
                        }
                        if (!ob[0].equals("") && ob[1].equals("")) {
                            txtSolucao = "CNPJ: " + this.cnpj + "compet\u00eancia do cr\u00e9dito a compensar: " + ob[0];
                            this.regUtil.setErro(this.linha, "EM065", coluna, (short)1, this.registro, txtSolucao);
                            flag = true;
                        }
                        if (ob[0].equals("") || !this.dt.validaData(ob[0], "yyyyMM")) {
                            txtSolucao = "CNPJ: " + this.cnpj + "<BR>compet\u00eancia do cr\u00e9dito a compensar: " + ob[0];
                            this.regUtil.setErro(this.linha, "EM014", coluna, (short)1, this.registro, txtSolucao);
                            flag = true;
                        }
                        this.cmpeOrigCred = this.regUtil.contCaracterRegistro(this.linha, ob[1], 19, coluna, this.registro);
                        if (!ob[1].equals("")) {
                            if (!this.regUtil.isNumeric(ob[1])) {
                                txtSolucao = "Valor invalido, n\u00e3o num\u00e9rico: " + ob[1];
                                this.regUtil.setErro(this.linha, "EG008", coluna, (short)1, this.registro, txtSolucao);
                                flag = true;
                            } else if (!this.regUtil.verificaCasasDecimais(ob[1])) {
                                txtSolucao = this.txtErroCasasDecimais + ob[1];
                                this.regUtil.setErro(this.linha, "EG009", coluna, (short)1, this.registro, txtSolucao);
                            }
                        }
                        if (ob[1].equals("") || !ob[0].equals("")) continue;
                        txtSolucao = "CNPJ: " + this.cnpj + "<BR>valor do cr\u00e9dito: " + ob[1];
                        this.regUtil.setErro(this.linha, "EM045", coluna, (short)1, this.registro, txtSolucao);
                        flag = true;
                        continue;
                    }
                    txtSolucao = "CNPJ: " + this.cnpj + "<BR> valor do cr\u00e9dito a compensar: " + this.valorCredito;
                    this.regUtil.setErro(this.linha, "EM017", coluna, (short)1, this.registro, txtSolucao);
                    flag = true;
                }
                if (!flag) {
                    this.inicializarOrigemCreditoAcompensar(s);
                }
            }
        }
        catch (ExceptionEncoding exEncoding) {
            exEncoding.printStackTrace();
            RegUtil.exErro = true;
            SwingUtils.msgErro(null, "Problema na leitura do arquivo, verifique se a c\u00f3difica\u00e7\u00e3o do arquivo \u00e9 UTF-8");
        }
    }

    public void inicializarOrigemCreditoAcompensar(String[] s) {
        for (int i = 0; i < s.length; ++i) {
            String[] ob = this.regUtil.multiValorado(s[i], "\u00a3");
            OrigemCreditoCompensar occ = new OrigemCreditoCompensar();
            ob[0] = ob[0] + "01";
            Date dataComp = this.regUtil.parseData(ob[0], "yyyyMMdd");
            if (dataComp != null) {
                occ.setDataCompetenciaOrigemCredito(dataComp);
            }
            String aux = ob[1].replace(',', '.');
            occ.setValorOrigemCredito(Double.parseDouble(aux));
            this.origCredCompensar.add(occ);
        }
    }

    private void verIssqnRecolhido() {
        int coluna = 18;
        this.issqnRecolhido = this.regUtil.contCaracterRegistro(this.linha, this.token[17].trim(), 19, coluna, this.registro);
        if (!this.issqnRecolhido.equals("")) {
            if (!this.regUtil.isNumeric(this.issqnRecolhido)) {
                String txtSolucao = "Valor invalido, n\u00e3o num\u00e9rico: " + this.issqnRecolhido;
                this.regUtil.setErro(this.linha, "EG008", coluna, (short)1, this.registro, txtSolucao);
            } else if (!this.regUtil.verificaCasasDecimais(this.issqnRecolhido)) {
                String txtSolucao = this.txtErroCasasDecimais + this.issqnRecolhido;
                this.regUtil.setErro(this.linha, "EG009", coluna, (short)1, this.registro, txtSolucao);
            }
        }
    }

    private void verMotivoNaoExigibilidade() {
        int coluna = 19;
        this.motivoNaoExigibilidade = this.regUtil.contCaracterRegistro(this.linha, this.token[18].trim(), 1, coluna, this.registro);
        if (!this.motivoNaoExigibilidade.equals("")) {
            if (!this.regUtil.isInteiro(this.motivoNaoExigibilidade)) {
                String txtSolucao = "Valor invalido, n\u00e3o num\u00e9rico: " + this.motivoNaoExigibilidade;
                this.regUtil.setErro(this.linha, "EG008", coluna, (short)1, this.registro, txtSolucao);
            } else if (!this.motivoNaoExigibilidade.equals("1") & !this.motivoNaoExigibilidade.equals("2")) {
                String txtSolucao = "Motivo de n\u00e3o exigibilidade: " + this.motivoNaoExigibilidade;
                this.regUtil.setErro(this.linha, "EM016", coluna, (short)1, this.registro, txtSolucao);
            }
        }
    }

    private void verProcessoMotivoNaoExigibilidade() {
        int coluna = 20;
        this.processoMotivoNaoExigibilidade = this.regUtil.contCaracterRegistro(this.linha, this.token[19].trim(), 20, coluna, this.registro);
        if (this.motivoNaoExigibilidade.equals("1") | this.motivoNaoExigibilidade.equals("2") && this.processoMotivoNaoExigibilidade.equals("")) {
            String txtSolucao = "CNPJ: " + this.cnpj + "<BR>motivo de n\u00e3o exigibilidade: " + this.motivoNaoExigibilidade;
            this.regUtil.setErro(this.linha, "EM044", coluna, (short)1, this.registro, txtSolucao);
        }
    }

    private void verIssqnARecolher() {
        int coluna = 21;
        this.issqnARecolher = this.regUtil.contCaracterRegistro(this.linha, this.token[20].trim(), 19, coluna, this.registro);
        if (!this.issqnARecolher.equals("")) {
            if (!this.regUtil.isNumeric(this.issqnARecolher)) {
                String txtSolucao = "Valor invalido, n\u00e3o num\u00e9rico: " + this.issqnARecolher;
                this.regUtil.setErro(this.linha, "EG008", coluna, (short)1, this.registro, txtSolucao);
            } else if (!this.regUtil.verificaCasasDecimais(this.issqnARecolher)) {
                String txtSolucao = this.txtErroCasasDecimais + this.issqnARecolher;
                this.regUtil.setErro(this.linha, "EG009", coluna, (short)1, this.registro, txtSolucao);
            }
        }
    }

    public IssqnMensal getDemostrativoMensalIssqn() {
        this.receitaDeclaradaConsolidada = this.regUtil.trocaVirgulaPonto(this.receitaDeclaradaConsolidada);
        this.baseCalculo = this.regUtil.trocaVirgulaPonto(this.baseCalculo);
        this.aliquotaIssqn = this.regUtil.trocaVirgulaPonto(this.aliquotaIssqn);
        this.issqnDevido = this.regUtil.trocaVirgulaPonto(this.issqnDevido);
        this.deducaoReceitaDeclaradaSubTitulo = this.regUtil.trocaVirgulaPonto(this.deducaoReceitaDeclaradaSubTitulo);
        this.deducaoReceitaDeclaradaConsolidada = this.regUtil.trocaVirgulaPonto(this.deducaoReceitaDeclaradaConsolidada);
        this.valorIssqnRetido = this.regUtil.trocaVirgulaPonto(this.valorIssqnRetido);
        this.incentivoFiscalSubTitulo = this.regUtil.trocaVirgulaPonto(this.incentivoFiscalSubTitulo);
        this.incentivoFiscal = this.regUtil.trocaVirgulaPonto(this.incentivoFiscal);
        this.descIncentivoFiscal = this.regUtil.trocaVirgulaPonto(this.descIncentivoFiscal);
        this.issqnRecolhido = this.regUtil.trocaVirgulaPonto(this.issqnRecolhido);
        this.issqnARecolher = this.regUtil.trocaVirgulaPonto(this.issqnARecolher);
        this.valorCredito = this.regUtil.trocaVirgulaPonto(this.valorCredito);
        this.demMensIssqn = !this.motivoNaoExigibilidade.equals("") ? new IssqnMensal(new Long(Long.parseLong(this.numLinha)), new Long(Long.parseLong(this.numLinha)), this.cnpj, new Double(Double.parseDouble(this.receitaDeclaradaConsolidada)), new Double(this.baseCalculo), new Double(Double.parseDouble(this.aliquotaIssqn)), new Double(Double.parseDouble(this.issqnDevido)), this.codTributacaoDesif, new Double(Double.parseDouble(this.deducaoReceitaDeclaradaSubTitulo)), new Double(Double.parseDouble(this.deducaoReceitaDeclaradaConsolidada)), this.descDeducao, new Double(Double.parseDouble(this.valorIssqnRetido)), new Double(Double.parseDouble(this.incentivoFiscalSubTitulo)), new Double(Double.parseDouble(this.incentivoFiscal)), new Double(Double.parseDouble(this.issqnRecolhido)), new Double(Double.parseDouble(this.issqnARecolher)), this.descIncentivoFiscal, new Double(Double.parseDouble(this.valorCredito)), new Short(Short.parseShort(this.motivoNaoExigibilidade)), this.processoMotivoNaoExigibilidade, this.origCredCompensar) : new IssqnMensal(new Long(Long.parseLong(this.numLinha)), new Long(Long.parseLong(this.numLinha)), this.cnpj, new Double(Double.parseDouble(this.receitaDeclaradaConsolidada)), new Double(this.baseCalculo), new Double(Double.parseDouble(this.aliquotaIssqn)), new Double(Double.parseDouble(this.issqnDevido)), this.codTributacaoDesif, new Double(Double.parseDouble(this.deducaoReceitaDeclaradaSubTitulo)), new Double(Double.parseDouble(this.deducaoReceitaDeclaradaConsolidada)), this.descDeducao, new Double(Double.parseDouble(this.valorIssqnRetido)), new Double(Double.parseDouble(this.incentivoFiscalSubTitulo)), new Double(Double.parseDouble(this.incentivoFiscal)), new Double(Double.parseDouble(this.issqnRecolhido)), new Double(Double.parseDouble(this.issqnARecolher)), this.descIncentivoFiscal, new Double(Double.parseDouble(this.valorCredito)), null, this.processoMotivoNaoExigibilidade, this.origCredCompensar);
        return this.demMensIssqn;
    }
}

