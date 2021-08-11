/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.pojo;

import br.gov.pbh.desif.model.pojo.base.AbstractRelatorioDependenciasVO;
import java.io.Serializable;
import java.util.Date;

public class RelatorioDependenciasVO
extends AbstractRelatorioDependenciasVO
implements Serializable {
    public RelatorioDependenciasVO() {
    }

    public RelatorioDependenciasVO(String cnpjInstituicao, String cnpjProprio, String codigoDependencia, String cnpjUnificado, String tipoDependencia, short possuiContabilidade, String nomeMunicipio, String Endereco, Date dataInicioParalizacao, Date dataFimParalizacao) {
        super(cnpjInstituicao, cnpjProprio, codigoDependencia, cnpjUnificado, tipoDependencia, possuiContabilidade, nomeMunicipio, Endereco, dataInicioParalizacao, dataFimParalizacao);
    }
}

