/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.pojo.base;

import br.gov.pbh.desif.model.pojo.IdentificacaoDeclaracao;
import br.gov.pbh.desif.model.pojo.base.AbstractIdentificacaoDeclaracao;
import java.io.Serializable;
import java.util.Date;

public abstract class AbstractNewIdentificacaoDeclaracao
extends AbstractIdentificacaoDeclaracao
implements Serializable {
    private String idnVersao;
    private Short tipoArredondamento;

    public AbstractNewIdentificacaoDeclaracao() {
    }

    public AbstractNewIdentificacaoDeclaracao(Integer id, String titulo, Long cidade, Integer linhaIdentificacaoDeclaracao, String cnpjInstituicao, String nomeInstituicao, Date dataInicioCompetencia, Date dataFimCompetencia, Short moduloDeclaracao, Short tipoDeclaracao, Short tipoConsolidacao, String cnpjResponsavelRecolhimento, String protocoloDeclaracao, String idnVersao, Short tipoArredondamento) {
        super(id, titulo, cidade, linhaIdentificacaoDeclaracao, cnpjInstituicao, nomeInstituicao, dataInicioCompetencia, dataFimCompetencia, moduloDeclaracao, tipoDeclaracao, tipoConsolidacao, cnpjResponsavelRecolhimento, protocoloDeclaracao);
        this.idnVersao = idnVersao;
        this.tipoArredondamento = tipoArredondamento;
    }

    public AbstractNewIdentificacaoDeclaracao(IdentificacaoDeclaracao idd, String idnVersao, Short tipoArredondamento) {
        super(idd.getId(), idd.getTitulo(), idd.getCidade(), idd.getLinhaIdentificacaoDeclaracao(), idd.getCnpjInstituicao(), idd.getNomeInstituicao(), idd.getDataInicioCompetencia(), idd.getDataFimCompetencia(), idd.getModuloDeclaracao(), idd.getTipoDeclaracao(), idd.getTipoConsolidacao(), idd.getCnpjResponsavelRecolhimento(), idd.getProtocoloDeclaracao());
        this.idnVersao = idnVersao;
        this.tipoArredondamento = tipoArredondamento;
    }

    public String getIdnVersao() {
        return this.idnVersao;
    }

    public void setIdnVersao(String idnVersao) {
        this.idnVersao = idnVersao;
    }

    public Short getTipoArredondamento() {
        return this.tipoArredondamento;
    }

    public void setTipoArredondamento(Short tipoArredondamento) {
        this.tipoArredondamento = tipoArredondamento;
    }
}

