/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.registros;

import br.gov.pbh.desif.model.pojo.PlanoGeralContaComentado;
import br.gov.pbh.desif.model.registros.RegUtil;
import java.util.Set;

public class Registro0100 {
    private PlanoGeralContaComentado pgcc;
    private String numLinha;
    private String registro;
    private String conta;
    private String nome;
    private String descConta;
    private String contaSuperior;
    private String contaCosif;
    private String codTribDesif;
    private String[] token;
    private int linha;
    private RegUtil regUtil;

    public Registro0100(String[] token, int line) {
        try {
            this.regUtil = new RegUtil();
            this.token = token;
            this.linha = line;
            this.registro = token[1];
            this.verLinha();
            this.verConta();
            this.verNome();
            this.verDescConta();
            this.verContaSuperior();
            this.verContaCosif();
            this.verCodTribDesif();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void verLinha() throws Exception {
        String txtSolucao;
        int coluna = 1;
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

    private void verConta() throws Exception {
        int coluna = 3;
        this.conta = this.regUtil.contCaracterRegistro(this.linha, this.token[2].trim(), 30, coluna, this.registro);
        if (this.conta.equals("")) {
            this.regUtil.setErro(this.linha, "EG016", coluna, (short)1, this.registro);
        }
    }

    private void verNome() throws Exception {
        int coluna = 4;
        this.nome = this.regUtil.contCaracterRegistro(this.linha, this.token[3].trim(), 100, coluna, this.registro);
        if (this.nome.equals("")) {
            this.regUtil.setErro(this.linha, "EI003", coluna, (short)1, this.registro);
        }
    }

    private void verDescConta() throws Exception {
        int coluna = 5;
        this.descConta = this.regUtil.contCaracterRegistro(this.linha, this.token[4].trim(), 600, coluna, this.registro);
    }

    private void verContaSuperior() throws Exception {
        int coluna = 6;
        this.contaSuperior = this.regUtil.contCaracterRegistro(this.linha, this.token[5].trim(), 30, coluna, this.registro);
        if (this.contaSuperior.equals(this.conta)) {
            String txtSolucao = "Conta: " + this.conta;
            this.regUtil.setErro(this.linha, "EI006", coluna, (short)1, this.registro, txtSolucao);
        }
    }

    private void verContaCosif() throws Exception {
        int coluna = 7;
        this.contaCosif = this.regUtil.contCaracterRegistro(this.linha, this.token[6].trim(), 20, coluna, this.registro);
        if (this.contaCosif.equals("")) {
            String txtSolucao = "Conta: " + this.conta + " Conta Superior: " + this.contaSuperior;
            this.regUtil.setErro(this.linha, "EI018", coluna, (short)1, this.registro, txtSolucao);
        }
    }

    private void verCodTribDesif() throws Exception {
        int coluna = 8;
        this.codTribDesif = this.regUtil.contCaracterRegistro(this.linha, this.token[7].trim(), 20, coluna, this.registro);
    }

    public PlanoGeralContaComentado getPGCCPojo() {
        try {
            this.pgcc = new PlanoGeralContaComentado(this.conta, this.contaCosif, new Long(Long.parseLong(this.numLinha)), this.nome, this.contaSuperior, this.codTribDesif, this.descConta, null, 0, null, null);
            return this.pgcc;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

