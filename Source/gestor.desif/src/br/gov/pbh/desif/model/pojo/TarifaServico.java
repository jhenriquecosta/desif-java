/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.pojo;

import br.gov.pbh.desif.model.pojo.base.AbstractTarifaServico;
import java.io.Serializable;

public class TarifaServico
extends AbstractTarifaServico
implements Serializable {
    public TarifaServico() {
    }

    public TarifaServico(Long id, Long numLinhTariServ, String codIdentTarifa, String codSubtitulo, String descTarifa) {
        super(id, numLinhTariServ, codIdentTarifa, codSubtitulo, descTarifa);
    }

    public TarifaServico(String codIdentTarifa, String codSubtitulo, String descTarifa) {
        super(codIdentTarifa, codSubtitulo, descTarifa);
    }
}

