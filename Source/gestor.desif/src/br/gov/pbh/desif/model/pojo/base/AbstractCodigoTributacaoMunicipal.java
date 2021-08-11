/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.pojo.base;

import br.gov.pbh.desif.model.pojo.Cidade;
import br.gov.pbh.desif.model.pojo.CodigoTributacaoDesif;
import java.io.Serializable;
import java.util.Date;

public abstract class AbstractCodigoTributacaoMunicipal
implements Serializable {
    private String id;
    private Cidade cidade;
    private CodigoTributacaoDesif codTributacao;
    private double valorAliquota;
    private Date dataInicioVigencia;
    private Date dataFimVigencia;

    public AbstractCodigoTributacaoMunicipal() {
    }

    public AbstractCodigoTributacaoMunicipal(String id) {
        this.id = id;
    }

    public AbstractCodigoTributacaoMunicipal(String id, Cidade cidade, CodigoTributacaoDesif codTributacao, double valorAliquota, Date dataInicioVigencia, Date dataFimVigencia) {
        this.id = id;
        this.cidade = cidade;
        this.codTributacao = codTributacao;
        this.valorAliquota = valorAliquota;
        this.dataInicioVigencia = dataInicioVigencia;
        this.dataFimVigencia = dataFimVigencia;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Cidade getCidade() {
        return this.cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public CodigoTributacaoDesif getCodTributacao() {
        return this.codTributacao;
    }

    public void setCodTributacao(CodigoTributacaoDesif codTributacao) {
        this.codTributacao = codTributacao;
    }

    public double getValorAliquota() {
        return this.valorAliquota;
    }

    public void setValorAliquota(double valorAliquota) {
        this.valorAliquota = valorAliquota;
    }

    public Date getDataInicioVigencia() {
        return this.dataInicioVigencia;
    }

    public void setDataInicioVigencia(Date dataInicioVigencia) {
        this.dataInicioVigencia = dataInicioVigencia;
    }

    public Date getDataFimVigencia() {
        return this.dataFimVigencia;
    }

    public void setDataFimVigencia(Date dataFimVigencia) {
        this.dataFimVigencia = dataFimVigencia;
    }
}

