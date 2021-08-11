/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.ws.cliente;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value=XmlAccessType.FIELD)
@XmlType(name="totalizacaoProtocoloAMIold", propOrder={"Aliq_ISSQN", "Dedu_Rece_Decl_Cnso", "Dedu_Rece_Decl_Sub_Titu", "Rece_Decl_Cnso"})
public class TotalizacaoProtocoloAMIold {
    protected Double Aliq_ISSQN;
    protected Double Dedu_Rece_Decl_Cnso;
    protected Double Dedu_Rece_Decl_Sub_Titu;
    protected Double Rece_Decl_Cnso;

    public Double getAliq_ISSQN() {
        return this.Aliq_ISSQN;
    }

    public void setAliq_ISSQN(Double value) {
        this.Aliq_ISSQN = value;
    }

    public Double getDedu_Rece_Decl_Cnso() {
        return this.Dedu_Rece_Decl_Cnso;
    }

    public void setDedu_Rece_Decl_Cnso(Double value) {
        this.Dedu_Rece_Decl_Cnso = value;
    }

    public Double getDedu_Rece_Decl_Sub_Titu() {
        return this.Dedu_Rece_Decl_Sub_Titu;
    }

    public void setDedu_Rece_Decl_Sub_Titu(Double value) {
        this.Dedu_Rece_Decl_Sub_Titu = value;
    }

    public Double getRece_Decl_Cnso() {
        return this.Rece_Decl_Cnso;
    }

    public void setRece_Decl_Cnso(Double value) {
        this.Rece_Decl_Cnso = value;
    }
}

