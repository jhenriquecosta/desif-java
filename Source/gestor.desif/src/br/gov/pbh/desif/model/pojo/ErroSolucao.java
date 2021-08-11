/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.pojo;

import br.gov.pbh.desif.model.pojo.Erro;
import br.gov.pbh.desif.model.pojo.Solucao;
import br.gov.pbh.desif.model.pojo.base.AbstractErroSolucao;
import java.io.Serializable;

public class ErroSolucao
extends AbstractErroSolucao
implements Serializable {
    public ErroSolucao() {
    }

    public ErroSolucao(Long id, Erro erro, Solucao solucao) {
        super(id, erro, solucao);
    }
}

