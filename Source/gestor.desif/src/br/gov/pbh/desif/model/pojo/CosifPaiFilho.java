/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.pojo;

import br.gov.pbh.desif.model.pojo.Cosif;
import br.gov.pbh.desif.model.pojo.CosifPaiFilhoConta;
import br.gov.pbh.desif.model.pojo.base.AbstractCosifPaiFilho;
import java.io.Serializable;

public class CosifPaiFilho
extends AbstractCosifPaiFilho
implements Serializable {
    public CosifPaiFilho() {
    }

    public CosifPaiFilho(CosifPaiFilhoConta conta, Cosif CosifByNumContaCosifPai, Cosif CosifByNumContaCosifFilho) {
        super(conta, CosifByNumContaCosifPai, CosifByNumContaCosifFilho);
    }
}

