/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.pojo;

import br.gov.pbh.desif.model.pojo.base.AbstractProtocoloInfoComunsMunicipios;
import java.util.Date;

public class ProtocoloInfoComunsMunicipios
extends AbstractProtocoloInfoComunsMunicipios {
    public ProtocoloInfoComunsMunicipios() {
    }

    public ProtocoloInfoComunsMunicipios(Long protocolo, String cnpjInstituicao, String nomeInstituicao, Date periodoInicCompetDecl, Date periodoFimCompetDecl, Date dataEntrega, Double versaoValidador, String versaoTermoRef, Short tipoDeclaracao, int totalContasInformadas, int totalContasMaisAnalitico, int totalContasMaisAnaliticoTributaveis, int qtdeSubtituloRegistro0200, int qtdeSubtituloRegistro0300) {
        super(protocolo, cnpjInstituicao, nomeInstituicao, periodoInicCompetDecl, periodoFimCompetDecl, dataEntrega, versaoValidador, versaoTermoRef, tipoDeclaracao, totalContasInformadas, totalContasMaisAnalitico, totalContasMaisAnaliticoTributaveis, qtdeSubtituloRegistro0200, qtdeSubtituloRegistro0300);
    }
}

