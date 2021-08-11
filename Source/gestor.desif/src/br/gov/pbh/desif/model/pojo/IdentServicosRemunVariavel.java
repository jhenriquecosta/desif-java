/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.pojo;

import br.gov.pbh.desif.model.pojo.base.AbstractIdentServicosRemunVariavel;
import java.io.Serializable;

public class IdentServicosRemunVariavel
extends AbstractIdentServicosRemunVariavel
implements Serializable {
    public IdentServicosRemunVariavel() {
    }

    public IdentServicosRemunVariavel(Long id, String codIdentServRemnVariavel, Long numLinhIdenServPrecVarl, String codSubtitulo, String descServRemnVariavel) {
        super(id, codIdentServRemnVariavel, numLinhIdenServPrecVarl, codSubtitulo, descServRemnVariavel);
    }

    public IdentServicosRemunVariavel(String codIdentServRemnVariavel, String codSubtitulo, String descServRemnVariavel) {
        super(codIdentServRemnVariavel, codSubtitulo, descServRemnVariavel);
    }
}

