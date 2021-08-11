/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.pojo;

import br.gov.pbh.desif.model.pojo.base.AbstractErro;
import java.io.Serializable;
import java.util.Set;

public class Erro
extends AbstractErro
implements Serializable {
    public Erro() {
    }

    public Erro(String id) {
        super(id);
    }

    public Erro(String id, String mensagem, String motivo, Set arSistemaErros, Set erroSolucaos) {
        super(id, mensagem, motivo, arSistemaErros, erroSolucaos);
    }
}

