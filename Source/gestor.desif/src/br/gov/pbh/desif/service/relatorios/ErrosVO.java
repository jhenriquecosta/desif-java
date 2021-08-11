/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.service.relatorios;

import br.gov.pbh.desif.model.pojo.base.AbstractSistemaErro;
import java.util.List;

public class ErrosVO {
    private List<AbstractSistemaErro> sistemaErros;

    public List<AbstractSistemaErro> getSistemaErros() {
        return this.sistemaErros;
    }

    public void setSistemaErros(List<AbstractSistemaErro> sistemaErros) {
        this.sistemaErros = sistemaErros;
    }
}

