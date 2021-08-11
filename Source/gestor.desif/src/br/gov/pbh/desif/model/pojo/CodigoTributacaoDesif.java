/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.pojo;

import br.gov.pbh.desif.model.pojo.ListaServico;
import br.gov.pbh.desif.model.pojo.base.AbstractCodigoTributacaoDesif;
import java.io.Serializable;
import java.util.Set;

public class CodigoTributacaoDesif
extends AbstractCodigoTributacaoDesif
implements Serializable {
    public CodigoTributacaoDesif() {
    }

    public CodigoTributacaoDesif(String id) {
        super(id);
    }

    public CodigoTributacaoDesif(String id, ListaServico listaServico, String descCodigoTributacao) {
        super(id, listaServico, descCodigoTributacao);
    }

    public CodigoTributacaoDesif(String id, ListaServico listaServico, String descCodigoTributacao, Set codTribuMunicipals) {
        super(id, listaServico, descCodigoTributacao, codTribuMunicipals);
    }
}

