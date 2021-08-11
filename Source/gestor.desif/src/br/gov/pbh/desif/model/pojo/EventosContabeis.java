/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.pojo;

import br.gov.pbh.desif.model.pojo.base.AbstractEventosContabeis;
import java.io.Serializable;

public class EventosContabeis
extends AbstractEventosContabeis
implements Serializable {
    public EventosContabeis() {
    }

    public EventosContabeis(int id, String descricaoEvento) {
        super(id, descricaoEvento);
    }
}

