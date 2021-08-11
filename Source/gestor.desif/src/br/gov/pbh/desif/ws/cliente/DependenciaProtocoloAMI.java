/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.ws.cliente;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value=XmlAccessType.FIELD)
@XmlType(name="dependenciaProtocoloAMI", propOrder={"cnpjProprio", "codDepe", "ctblPropria", "indrInscMunl"})
public class DependenciaProtocoloAMI {
    protected String cnpjProprio;
    protected String codDepe;
    protected Short ctblPropria;
    protected Short indrInscMunl;

    public String getCnpjProprio() {
        return this.cnpjProprio;
    }

    public void setCnpjProprio(String value) {
        this.cnpjProprio = value;
    }

    public String getCodDepe() {
        return this.codDepe;
    }

    public void setCodDepe(String value) {
        this.codDepe = value;
    }

    public Short getCtblPropria() {
        return this.ctblPropria;
    }

    public void setCtblPropria(Short value) {
        this.ctblPropria = value;
    }

    public Short getIndrInscMunl() {
        return this.indrInscMunl;
    }

    public void setIndrInscMunl(Short value) {
        this.indrInscMunl = value;
    }
}

