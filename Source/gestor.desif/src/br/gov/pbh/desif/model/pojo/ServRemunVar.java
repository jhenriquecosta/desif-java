/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.pojo;

import br.gov.pbh.desif.model.pojo.base.AbstractServRemunVar;
import java.io.Serializable;

public class ServRemunVar
extends AbstractServRemunVar
implements Serializable {
    public ServRemunVar() {
    }

    public ServRemunVar(String cod, Integer OpcObrig) {
        super(cod, OpcObrig);
    }

    public ServRemunVar(String cod, String nomServRemVar, String DescServRemVar, Integer OpcObrig) {
        super(cod, nomServRemVar, DescServRemVar, OpcObrig);
    }
}

