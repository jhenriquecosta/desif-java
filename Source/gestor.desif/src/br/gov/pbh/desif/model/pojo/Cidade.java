/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.pojo;

import br.gov.pbh.desif.model.pojo.base.AbstractCidade;
import java.io.Serializable;
import java.util.Set;

public class Cidade
extends AbstractCidade
implements Serializable {
    public Cidade() {
    }

    public Cidade(Long id) {
        super(id);
    }

    public Cidade(Long id, String nomeCidade, String uf, Set codTribuMunicipals) {
        super(id, nomeCidade, uf, codTribuMunicipals);
    }
}

