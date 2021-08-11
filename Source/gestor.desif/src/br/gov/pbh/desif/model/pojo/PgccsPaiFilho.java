/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.pojo;

import br.gov.pbh.desif.model.pojo.PgccsPaiFilhoId;
import br.gov.pbh.desif.model.pojo.PlanoGeralContaComentado;
import br.gov.pbh.desif.model.pojo.base.AbstractPgccsPaiFilho;
import java.io.Serializable;

public class PgccsPaiFilho
extends AbstractPgccsPaiFilho
implements Serializable {
    public PgccsPaiFilho() {
    }

    public PgccsPaiFilho(PgccsPaiFilhoId id, PlanoGeralContaComentado planoGeralContaComentadoByIdnPgccPai, PlanoGeralContaComentado planoGeralContaComentadoByIdnPgccFilho) {
        super(id, planoGeralContaComentadoByIdnPgccPai, planoGeralContaComentadoByIdnPgccFilho);
    }
}

