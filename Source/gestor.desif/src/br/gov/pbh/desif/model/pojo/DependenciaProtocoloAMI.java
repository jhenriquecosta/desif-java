/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.pojo;

public class DependenciaProtocoloAMI {
    private String cnpjProprio;
    private Short indrInscMunl;
    private String codDepe;
    private Short ctblPropria;

    public DependenciaProtocoloAMI(String cnpjProprio, Short indrInscMunl, String codDepe, Short ctblPropria) {
        this.cnpjProprio = cnpjProprio;
        this.indrInscMunl = indrInscMunl;
        this.codDepe = codDepe;
        this.ctblPropria = ctblPropria;
    }

    public DependenciaProtocoloAMI() {
    }

    public String getCnpjProprio() {
        return this.cnpjProprio;
    }

    public void setCnpjProprio(String cnpjProprio) {
        this.cnpjProprio = cnpjProprio;
    }

    public String getCodDepe() {
        return this.codDepe;
    }

    public void setCodDepe(String codDepe) {
        this.codDepe = codDepe;
    }

    public Short getCtblPropria() {
        return this.ctblPropria;
    }

    public void setCtblPropria(Short ctblPropria) {
        this.ctblPropria = ctblPropria;
    }

    public Short getIndrInscMunl() {
        return this.indrInscMunl;
    }

    public void setIndrInscMunl(Short indrInscMunl) {
        this.indrInscMunl = indrInscMunl;
    }
}

