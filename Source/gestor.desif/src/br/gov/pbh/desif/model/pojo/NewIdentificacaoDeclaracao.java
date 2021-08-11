/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.pojo;

import br.gov.pbh.desif.model.pojo.IdentificacaoDeclaracao;
import br.gov.pbh.desif.model.pojo.base.AbstractNewIdentificacaoDeclaracao;
import java.util.Date;

public class NewIdentificacaoDeclaracao
extends AbstractNewIdentificacaoDeclaracao {
    public NewIdentificacaoDeclaracao() {
    }

    public NewIdentificacaoDeclaracao(Integer id, String titulo, Long cidade, Integer linhaIdentificacaoDeclaracao, String cnpjInstituicao, String nomeInstituicao, Date dataInicioCompetencia, Date dataFimCompetencia, Short moduloDeclaracao, Short tipoDeclaracao, Short tipoConsolidacao, String cnpjResponsavelRecolhimento, String protocoloDeclaracao, String idnVersao, Short tipoArredondamento) {
        super(id, titulo, cidade, linhaIdentificacaoDeclaracao, cnpjInstituicao, nomeInstituicao, dataInicioCompetencia, dataFimCompetencia, moduloDeclaracao, tipoDeclaracao, tipoConsolidacao, cnpjResponsavelRecolhimento, protocoloDeclaracao, idnVersao, tipoArredondamento);
    }

    public NewIdentificacaoDeclaracao(IdentificacaoDeclaracao idd, String idnVersao, Short tipoArredondamento) {
        super(idd, idnVersao, tipoArredondamento);
    }
}

