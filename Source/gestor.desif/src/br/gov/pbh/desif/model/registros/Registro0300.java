/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.registros;

import br.gov.pbh.desif.model.pojo.IdentServicosRemunVariavel;
import br.gov.pbh.desif.model.registros.RegUtil;

public class Registro0300 {
    private IdentServicosRemunVariavel identServRemVar;
    private String numLinha;
    private String registro;
    private String codIdentServRemunVariavel;
    private String descComplementServRemunVariavel;
    private String codSubtitulo;
    private String[] token;
    private int linha;
    private RegUtil regUtil;

    public Registro0300(String[] token, int line) {
        try {
            this.regUtil = new RegUtil();
            this.token = token;
            this.linha = line;
            this.registro = token[1];
            this.verLinha();
            this.verDescComplementServRemunVariavel();
            this.verCodIdentServRemunVariavel();
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

    private void verCodIdentServRemunVariavel() throws Exception {
        int coluna = 3;
        this.codIdentServRemunVariavel = this.regUtil.contCaracterRegistro(this.linha, this.token[2].trim(), 6, coluna, this.registro);
        if (this.codIdentServRemunVariavel.equals("")) {
            String txtSolucao = "Descri\u00e7\u00e3o Servi\u00e7o: " + this.descComplementServRemunVariavel;
            this.regUtil.setErro(this.linha, "EI016", coluna, (short)1, this.registro, txtSolucao);
        } else if (!this.regUtil.isNumeric(this.codIdentServRemunVariavel)) {
            String txtSolucao = "Valor invalido, n\u00e3o num\u00e9rico: " + this.codIdentServRemunVariavel;
            this.regUtil.setErro(this.linha, "EG008", coluna, (short)1, this.registro, txtSolucao);
        }
    }

    private void verDescComplementServRemunVariavel() throws Exception {
        int coluna = 4;
        this.descComplementServRemunVariavel = this.regUtil.contCaracterRegistro(this.linha, this.token[3].trim(), 255, coluna, this.registro);
    }

    private void verSubtitulo() throws Exception {
        int coluna = 5;
        this.codSubtitulo = this.regUtil.contCaracterRegistro(this.linha, this.token[4].trim(), 30, coluna, this.registro);
        if (this.codSubtitulo.equals("")) {
            this.regUtil.setErro(this.linha, "EG015", coluna, (short)1, this.registro);
        }
    }

    public IdentServicosRemunVariavel getServicosRemVariavel() {
        try {
            this.identServRemVar = new IdentServicosRemunVariavel(new Long(Long.parseLong(this.numLinha)), this.codIdentServRemunVariavel, new Long(Long.parseLong(this.numLinha)), this.codSubtitulo, this.descComplementServRemunVariavel);
            return this.identServRemVar;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

