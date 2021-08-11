/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.pojo;

import br.gov.pbh.desif.model.pojo.IssqnMensal;
import br.gov.pbh.desif.model.pojo.base.AbstractOrigemCreditoCompensar;
import java.io.Serializable;
import java.util.Date;

public class OrigemCreditoCompensar
extends AbstractOrigemCreditoCompensar
implements Serializable {
    public OrigemCreditoCompensar() {
    }

    public OrigemCreditoCompensar(Long id, IssqnMensal issqnMensal, Date dataCompetenciaOrigemCredito, Double valorOrigemCredito) {
        super(id, issqnMensal, dataCompetenciaOrigemCredito, valorOrigemCredito);
    }
}

