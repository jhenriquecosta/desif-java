/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.pojo;

import br.gov.pbh.desif.model.pojo.base.AbstractAlerta;
import java.io.Serializable;
import java.util.Set;

public class Alerta
extends AbstractAlerta
implements Serializable {
    public Alerta() {
    }

    public Alerta(String id) {
        super(id);
    }

    public Alerta(String id, String mensagem, String motivo, Set arSistemaAlertas, Set erroSolucaos) {
        super(id, mensagem, motivo, arSistemaAlertas, erroSolucaos);
    }
}

