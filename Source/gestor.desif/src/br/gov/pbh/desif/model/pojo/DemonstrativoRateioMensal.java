/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.pojo;

import br.gov.pbh.desif.model.pojo.base.AbstractDemonstrativoRateioMensal;
import java.io.Serializable;
import java.util.Date;

public class DemonstrativoRateioMensal
extends AbstractDemonstrativoRateioMensal
implements Serializable {
    public DemonstrativoRateioMensal() {
    }

    public DemonstrativoRateioMensal(Long id, Long numLinha, String codigoDependencia, Date AnoMesCompetencia, String descricaoDetalhadaReceita, Double valorReceitaRateada, Short tipoPartida, Integer codigoEvento) {
        super(id, numLinha, codigoDependencia, AnoMesCompetencia, descricaoDetalhadaReceita, valorReceitaRateada, tipoPartida, codigoEvento);
    }

    public DemonstrativoRateioMensal(String codigoDependencia, Date AnoMesCompetencia, String descricaoDetalhadaReceita, Double valorReceitaRateada, Short tipoPartida) {
        super(codigoDependencia, AnoMesCompetencia, descricaoDetalhadaReceita, valorReceitaRateada, tipoPartida);
    }
}

