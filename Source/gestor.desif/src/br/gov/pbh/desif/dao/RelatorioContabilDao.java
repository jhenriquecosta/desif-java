/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.dao;

import br.gov.pbh.desif.base.dao.BaseDao;
import java.util.List;

public interface RelatorioContabilDao
extends BaseDao {
    public List buscaDadosDependenciasBalancAnalit();

    public List buscaDadosDependencia(String var1);

    public List buscaDadosDeclaracao();

    public List buscaDadosContasBalancAnalit();

    public List buscaDadosCompetBalancAnalit();

    public List buscaDadosGeraisBalancAnalit(String var1, String var2, String var3);

    public List buscaDadosDependenciasDemRateio();

    public List buscaDadosCompetDemRateio();

    public List buscaDadosValRateioDemRateio();

    public List buscaDadosCodEventoDemRateio();

    public List buscaDadosGeraisDemRateio(String var1, String var2, String var3, String var4);
}

