/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.pojo;

import br.gov.pbh.desif.model.pojo.base.AbstractIdentificacaoDeclaracao;
import java.io.Serializable;
import java.util.Date;

public class IdentificacaoDeclaracao
extends AbstractIdentificacaoDeclaracao
implements Serializable {
    public IdentificacaoDeclaracao() {
    }

    public IdentificacaoDeclaracao(Integer id, String titulo, Long cidade, Integer linhaIdentificacaoDeclaracao, String cnpjInstituicao, String nomeInstituicao, Date dataInicioCompetencia, Date dataFimCompetencia, Short moduloDeclaracao, Short tipoDeclaracao, Short tipoConsolidacao, String cnpjResponsavelRecolhimento, String protocoloDeclaracao) {
        super(id, titulo, cidade, linhaIdentificacaoDeclaracao, cnpjInstituicao, nomeInstituicao, dataInicioCompetencia, dataFimCompetencia, moduloDeclaracao, tipoDeclaracao, tipoConsolidacao, cnpjResponsavelRecolhimento, protocoloDeclaracao);
    }

    public IdentificacaoDeclaracao(Integer id, String titulo, Long cidade, Integer linhaIdentificacaoDeclaracao, String cnpjInstituicao, String nomeInstituicao, Date dataInicioCompetencia, Date dataFimCompetencia, Short moduloDeclaracao, Short tipoDeclaracao, String cnpjResponsavelRecolhimento, String protocoloDeclaracao) {
        super(id, titulo, cidade, linhaIdentificacaoDeclaracao, cnpjInstituicao, nomeInstituicao, dataInicioCompetencia, dataFimCompetencia, moduloDeclaracao, tipoDeclaracao, cnpjResponsavelRecolhimento, protocoloDeclaracao);
    }
}

