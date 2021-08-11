/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.registros;

import br.gov.pbh.desif.model.pojo.IdentificacaoDeclaracao;
import br.gov.pbh.desif.model.pojo.NewIdentificacaoDeclaracao;
import br.gov.pbh.desif.model.registros.Data;
import br.gov.pbh.desif.model.registros.RegUtil;
import br.gov.pbh.desif.model.registros.Registro0000;

public class Registro0000New
extends Registro0000 {
    private IdentificacaoDeclaracao declaracao;
    private RegUtil regUtil;
    private Data dt;
    private String idnVersao;
    private String tipoArredondamento;
    private String[] token;
    private int linha;
    String registro;

    public Registro0000New(String[] token, int line) {
        super(token, line);
        try {
            this.registro = token[1];
            this.regUtil = new RegUtil();
            this.dt = new Data();
            this.token = token;
            this.verIdnVersao();
            this.verTipoArredondamento();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void verIdnVersao() throws Exception {
        int coluna = 14;
        this.idnVersao = this.regUtil.contCaracterRegistro(this.linha, this.token[13].trim(), 10, coluna, this.registro);
        if (this.idnVersao.equals("")) {
            this.regUtil.setErro(this.linha, "ED042", coluna, (short)1, this.registro);
        }
    }

    public void verTipoArredondamento() throws Exception {
        int coluna = 15;
        this.tipoArredondamento = this.regUtil.contCaracterRegistro(this.linha, this.token[14].trim(), 1, coluna, this.registro);
        String moduloDeclaracao = this.regUtil.contCaracterRegistro(this.linha, this.token[8].trim(), 1, 9, this.registro);
        if (moduloDeclaracao.equals("2")) {
            String txtSolucao;
            if (this.tipoArredondamento.equals("")) {
                txtSolucao = "M\u00f3dulo da declara\u00e7\u00e3o informado: " + moduloDeclaracao;
                this.regUtil.setErro(this.linha, "ED044", coluna, (short)1, this.registro, txtSolucao);
            }
            if (!this.tipoArredondamento.equals("1") & !this.tipoArredondamento.equals("") & this.tipoArredondamento != null) {
                txtSolucao = "M\u00f3dulo da declara\u00e7\u00e3o informado: " + moduloDeclaracao + ", tipo de arredondamento: " + this.tipoArredondamento;
                this.regUtil.setErro(this.linha, "ED045", coluna, (short)1, this.registro, txtSolucao);
            }
        } else if (!this.regUtil.isInteiro(this.tipoArredondamento) && !this.tipoArredondamento.equals("") && this.tipoArredondamento != null) {
            String txtSolucao = "Valor invalido, n\u00e3o num\u00e9rico: " + this.tipoArredondamento;
            this.regUtil.setErro(this.linha, "EG008", coluna, (short)1, this.registro, txtSolucao);
        } else if (!this.tipoArredondamento.equals("") && this.tipoArredondamento != null && (moduloDeclaracao.equals("1") || moduloDeclaracao.equals("3"))) {
            String txtSolucao = "M\u00f3dulo da declara\u00e7\u00e3o informado: " + moduloDeclaracao + ", tipo de arredondamento: " + this.tipoArredondamento;
            this.regUtil.setErro(this.linha, "ED049", coluna, (short)1, this.registro, txtSolucao);
        }
    }

    public NewIdentificacaoDeclaracao getNewDeclaracaoPojo() {
        try {
            String modDeclaracao = this.regUtil.contCaracterRegistro(this.linha, this.token[8].trim(), 1, 9, this.registro);
            IdentificacaoDeclaracao idd = super.getDeclaracaoPojo();
            NewIdentificacaoDeclaracao nidd = modDeclaracao.equals("1") || modDeclaracao.equals("3") ? new NewIdentificacaoDeclaracao(idd, new String(this.idnVersao), null) : new NewIdentificacaoDeclaracao(idd, this.idnVersao, new Short(Short.parseShort(this.tipoArredondamento)));
            return nidd;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

