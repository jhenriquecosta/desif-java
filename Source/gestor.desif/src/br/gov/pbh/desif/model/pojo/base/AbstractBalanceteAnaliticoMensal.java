/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.pojo.base;

import java.io.Serializable;
import java.util.Date;

public abstract class AbstractBalanceteAnaliticoMensal
implements Serializable {
    private Long id;
    private String codigoDependencia;
    private Date anoMesCompetencia;
    private String conta;
    private Double saldoInicial;
    private Double valorDebito;
    private Double valorCredito;
    private Double saldoFinal;
    private Long linhaBalanceteAnaliticoMensal;

    public AbstractBalanceteAnaliticoMensal() {
    }

    public AbstractBalanceteAnaliticoMensal(Long id, Long linhaBalanceteAnaliticoMensal, String codigoDependencia, Date AnoMesCompetencia, String conta, Double saldoInicial, Double valorDebito, Double valorCredito, Double saldoFinal) {
        this.id = id;
        this.linhaBalanceteAnaliticoMensal = linhaBalanceteAnaliticoMensal;
        this.setCodigoDependencia(codigoDependencia);
        this.anoMesCompetencia = AnoMesCompetencia;
        this.conta = conta;
        this.saldoInicial = saldoInicial;
        this.valorDebito = valorDebito;
        this.valorCredito = valorCredito;
        this.saldoFinal = saldoFinal;
    }

    public AbstractBalanceteAnaliticoMensal(String conta, Double saldoInicial, Double valorDebito, Double valorCredito, Double saldoFinal, Date anoMesCompetencia) {
        this.conta = conta;
        this.saldoInicial = saldoInicial;
        this.valorDebito = valorDebito;
        this.valorCredito = valorCredito;
        this.saldoFinal = saldoFinal;
        this.anoMesCompetencia = anoMesCompetencia;
    }

    public Date getAnoMesCompetencia() {
        return this.anoMesCompetencia;
    }

    public void setAnoMesCompetencia(Date anoMesCompetencia) {
        this.anoMesCompetencia = anoMesCompetencia;
    }

    public Double getSaldoInicial() {
        return this.saldoInicial;
    }

    public void setSaldoInicial(Double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public String getCodigoDependencia() {
        return this.codigoDependencia;
    }

    public void setCodigoDependencia(String codigoDependencia) {
        if (codigoDependencia.length() == 10) {
            codigoDependencia = "0" + codigoDependencia;
        }
        this.codigoDependencia = codigoDependencia;
    }

    public String getConta() {
        return this.conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getSaldoFinal() {
        return this.saldoFinal;
    }

    public void setSaldoFinal(Double saldoFinal) {
        this.saldoFinal = saldoFinal;
    }

    public Double getValorCredito() {
        return this.valorCredito;
    }

    public void setValorCredito(Double valorCredito) {
        this.valorCredito = valorCredito;
    }

    public Double getValorDebito() {
        return this.valorDebito;
    }

    public void setValorDebito(Double valorDebito) {
        this.valorDebito = valorDebito;
    }

    public Long getLinhaBalanceteAnaliticoMensal() {
        return this.linhaBalanceteAnaliticoMensal;
    }

    public void setLinhaBalanceteAnaliticoMensal(Long linhaBalanceteApuracaoMensal) {
        this.linhaBalanceteAnaliticoMensal = linhaBalanceteApuracaoMensal;
    }
}

