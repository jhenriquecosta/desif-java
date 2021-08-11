/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.pojo;

import br.gov.pbh.desif.model.pojo.Erro;
import br.gov.pbh.desif.model.pojo.base.AbstractSistemaErro;
import java.io.Serializable;

public class SistemaErro
extends AbstractSistemaErro
implements Serializable {
    public SistemaErro() {
    }

    public SistemaErro(Long id, Erro erro, Long linha, Integer coluna, Short tipoErro, String registro, String nomeCampo) {
        super(id, erro, linha, coluna, tipoErro, registro, nomeCampo);
    }

    public SistemaErro(Long id, Erro erro, Long linha, Integer coluna, Short tipoErro, String registro, String nomeCampo, String valorCampoErro) {
        super(id, erro, linha, coluna, tipoErro, registro, nomeCampo, valorCampoErro);
    }
}

