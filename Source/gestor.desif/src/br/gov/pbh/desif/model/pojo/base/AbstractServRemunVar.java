/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.pojo.base;

import java.io.Serializable;

public abstract class AbstractServRemunVar
implements Serializable {
    private String cod;
    private String nomServRemVar;
    private String DescServRemVar;
    private Integer OpcObrig;

    public AbstractServRemunVar() {
    }

    public AbstractServRemunVar(String cod, Integer OpcObrig) {
        this.cod = cod;
        this.OpcObrig = OpcObrig;
    }

    public AbstractServRemunVar(String cod, String nomServRemVar, String DescServRemVar, Integer OpcObrig) {
        this.cod = cod;
        this.nomServRemVar = nomServRemVar;
        this.DescServRemVar = DescServRemVar;
        this.OpcObrig = OpcObrig;
    }

    public String getCod() {
        return this.cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getNomServRemVar() {
        return this.nomServRemVar;
    }

    public void setNomServRemVar(String nomServRemVar) {
        this.nomServRemVar = nomServRemVar;
    }

    public String getDescServRemVar() {
        return this.DescServRemVar;
    }

    public void setDescServRemVar(String DescServRemVar) {
        this.DescServRemVar = DescServRemVar;
    }

    public Integer getOpcObrig() {
        return this.OpcObrig;
    }

    public void setOpcObrig(Integer OpcObrig) {
        this.OpcObrig = OpcObrig;
    }
}

