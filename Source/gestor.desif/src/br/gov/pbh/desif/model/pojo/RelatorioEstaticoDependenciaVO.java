/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.pojo;

import br.gov.pbh.desif.model.pojo.base.AbstractRelatorioEstaticoDependenciaVO;
import java.util.Date;

public class RelatorioEstaticoDependenciaVO
extends AbstractRelatorioEstaticoDependenciaVO {
    public RelatorioEstaticoDependenciaVO() {
    }

    public RelatorioEstaticoDependenciaVO(String nomeInstituicao, String nomeCidade, Short tipoDeclaracao, Date dataInicioCompetencia, String descTitulo, String protocoloDeclaracao) {
        super(nomeInstituicao, nomeCidade, tipoDeclaracao, dataInicioCompetencia, descTitulo, protocoloDeclaracao);
    }
}

