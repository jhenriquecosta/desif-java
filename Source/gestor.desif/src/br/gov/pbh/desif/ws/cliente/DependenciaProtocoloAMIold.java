/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.ws.cliente;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value=XmlAccessType.FIELD)
@XmlType(name="dependenciaProtocoloAMIold", propOrder={"CNPJ_Proprio", "Cod_Depe", "Ctbl_Propria", "Indr_Insc_Munl"})
public class DependenciaProtocoloAMIold {
    protected String CNPJ_Proprio;
    protected String Cod_Depe;
    protected Short Ctbl_Propria;
    protected Short Indr_Insc_Munl;

    public String getCNPJ_Proprio() {
        return this.CNPJ_Proprio;
    }

    public void setCNPJ_Proprio(String value) {
        this.CNPJ_Proprio = value;
    }

    public String getCod_Depe() {
        return this.Cod_Depe;
    }

    public void setCod_Depe(String value) {
        this.Cod_Depe = value;
    }

    public Short getCtbl_Propria() {
        return this.Ctbl_Propria;
    }

    public void setCtbl_Propria(Short value) {
        this.Ctbl_Propria = value;
    }

    public Short getIndr_Insc_Munl() {
        return this.Indr_Insc_Munl;
    }

    public void setIndr_Insc_Munl(Short value) {
        this.Indr_Insc_Munl = value;
    }
}

