/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.pojo;

import br.gov.pbh.desif.model.pojo.IndicesMonetariosId;
import br.gov.pbh.desif.model.pojo.base.AbstractIndicesMonetarios;
import java.io.Serializable;

public class IndicesMonetarios
extends AbstractIndicesMonetarios
implements Serializable {
    public IndicesMonetarios() {
    }

    public IndicesMonetarios(IndicesMonetariosId id, Double valIndiMone) {
        super(id, valIndiMone);
    }
}

