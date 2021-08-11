/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.registros;

import br.gov.pbh.desif.model.pojo.TarifaServico;
import br.gov.pbh.desif.model.registros.RegUtil;

public class Registro0200 {
    private TarifaServico tarServ;
    private String numLinha;
    private String registro;
    private String codIdentTarifa;
    private String descTarifa;
    private String codSubtitulo;
    private String[] token;
    private int linha;
    private RegUtil regUtil;

    public Registro0200(String[] token, int line) {
        try {
            this.regUtil = new RegUtil();
            this.token = token;
            this.linha = line;
            this.registro = token[1];
            this.verLinha();
            this.verDescTarifa();
            this.verCodIdentTarifa();
            this.verSubtitulo();
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

    private void verCodIdentTarifa() throws Exception {
        int coluna = 3;
        this.codIdentTarifa = this.regUtil.contCaracterRegistro(this.linha, this.token[2].trim().toUpperCase(), 20, coluna, this.registro);
        if (this.codIdentTarifa.equals("")) {
            String txtSolucao = "Descri\u00e7\u00e3o : " + this.descTarifa;
            this.regUtil.setErro(this.linha, "EI011", coluna, (short)1, this.registro, txtSolucao);
        }
    }

    private void verDescTarifa() throws Exception {
        int coluna = 4;
        this.descTarifa = this.regUtil.contCaracterRegistro(this.linha, this.token[3].trim(), 100, coluna, this.registro);
    }

    private void verSubtitulo() throws Exception {
        int coluna = 5;
        this.codSubtitulo = this.regUtil.contCaracterRegistro(this.linha, this.token[4].trim(), 30, coluna, this.registro);
        if (this.codSubtitulo.equals("")) {
            this.regUtil.setErro(this.linha, "EG015", coluna, (short)1, this.registro);
        }
    }

    public TarifaServico getTarifaServico() {
        try {
            this.tarServ = new TarifaServico(new Long(Long.parseLong(this.numLinha)), new Long(Long.parseLong(this.numLinha)), this.codIdentTarifa, this.codSubtitulo, this.descTarifa);
            return this.tarServ;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

