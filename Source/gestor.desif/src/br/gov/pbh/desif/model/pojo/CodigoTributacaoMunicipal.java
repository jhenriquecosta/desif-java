/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.pojo;

import br.gov.pbh.desif.model.pojo.Cidade;
import br.gov.pbh.desif.model.pojo.CodigoTributacaoDesif;
import br.gov.pbh.desif.model.pojo.base.AbstractCodigoTributacaoMunicipal;
import java.io.Serializable;
import java.util.Date;

public class CodigoTributacaoMunicipal
extends AbstractCodigoTributacaoMunicipal
implements Serializable {
    public CodigoTributacaoMunicipal() {
    }

    public CodigoTributacaoMunicipal(String id) {
        super(id);
    }

    public CodigoTributacaoMunicipal(String id, Cidade cidade, CodigoTributacaoDesif codTributacao, Double valorAliquota, Date dataInicioVigencia, Date dataFimVigencia) {
        super(id, cidade, codTributacao, valorAliquota, dataInicioVigencia, dataFimVigencia);
    }
}

