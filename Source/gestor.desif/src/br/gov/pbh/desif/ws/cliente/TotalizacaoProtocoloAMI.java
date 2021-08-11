/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.ws.cliente;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value=XmlAccessType.FIELD)
@XmlType(name="totalizacaoProtocoloAMI", propOrder={"aliqISSQN", "deduReceDeclCnso", "deduReceDeclSubTitu", "receDeclCnso"})
public class TotalizacaoProtocoloAMI {
    protected Double aliqISSQN;
    protected Double deduReceDeclCnso;
    protected Double deduReceDeclSubTitu;
    protected Double receDeclCnso;

    public Double getAliqISSQN() {
        return this.aliqISSQN;
    }

    public void setAliqISSQN(Double value) {
        this.aliqISSQN = value;
    }

    public Double getDeduReceDeclCnso() {
        return this.deduReceDeclCnso;
    }

    public void setDeduReceDeclCnso(Double value) {
        this.deduReceDeclCnso = value;
    }

    public Double getDeduReceDeclSubTitu() {
        return this.deduReceDeclSubTitu;
    }

    public void setDeduReceDeclSubTitu(Double value) {
        this.deduReceDeclSubTitu = value;
    }

    public Double getReceDeclCnso() {
        return this.receDeclCnso;
    }

    public void setReceDeclCnso(Double value) {
        this.receDeclCnso = value;
    }
}

