/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.pojo;

import br.gov.pbh.desif.model.pojo.base.AbstractCosif;
import java.io.Serializable;
import java.util.Date;

public class Cosif
extends AbstractCosif
implements Serializable {
    public Cosif(String numeroContaCosif, Date dataCriacao, String nomeContaCosif, String desFuncConta, Date dataExtinsao, Integer numNivel) {
        super(numeroContaCosif, dataCriacao, nomeContaCosif, desFuncConta, dataExtinsao, numNivel);
    }

    public Cosif() {
    }
}

