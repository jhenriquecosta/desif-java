/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.pojo.base;

import br.gov.pbh.desif.model.pojo.IssqnMensal;
import java.io.Serializable;
import java.util.Date;

public abstract class AbstractOrigemCreditoCompensar
implements Serializable {
    private long id;
    private IssqnMensal issqnMensal;
    private Date dataCompetenciaOrigemCredito;
    private double valorOrigemCredito;

    public AbstractOrigemCreditoCompensar() {
    }

    public AbstractOrigemCreditoCompensar(long id, IssqnMensal issqnMensal, Date dataCompetenciaOrigemCredito, double valorOrigemCredito) {
        this.id = id;
        this.issqnMensal = issqnMensal;
        this.dataCompetenciaOrigemCredito = dataCompetenciaOrigemCredito;
        this.valorOrigemCredito = valorOrigemCredito;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public IssqnMensal getIssqnMensal() {
        return this.issqnMensal;
    }

    public void setIssqnMensal(IssqnMensal issqnMensal) {
        this.issqnMensal = issqnMensal;
    }

    public Date getDataCompetenciaOrigemCredito() {
        return this.dataCompetenciaOrigemCredito;
    }

    public void setDataCompetenciaOrigemCredito(Date dataCompetenciaOrigemCredito) {
        this.dataCompetenciaOrigemCredito = dataCompetenciaOrigemCredito;
    }

    public double getValorOrigemCredito() {
        return this.valorOrigemCredito;
    }

    public void setValorOrigemCredito(double valorOrigemCredito) {
        this.valorOrigemCredito = valorOrigemCredito;
    }
}

