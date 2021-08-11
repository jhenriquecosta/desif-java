
package br.gov.pbh.desif.dao;

import br.gov.pbh.desif.base.dao.BaseDao;
import java.util.List;

public interface RelatorioContabilDao
    extends BaseDao
{

    public abstract List buscaDadosDependenciasBalancAnalit();

    public abstract List buscaDadosDependencia(String s);

    public abstract List buscaDadosDeclaracao();

    public abstract List buscaDadosContasBalancAnalit();

    public abstract List buscaDadosCompetBalancAnalit();

    public abstract List buscaDadosGeraisBalancAnalit(String s, String s1, String s2);

    public abstract List buscaDadosDependenciasDemRateio();

    public abstract List buscaDadosCompetDemRateio();

    public abstract List buscaDadosValRateioDemRateio();

    public abstract List buscaDadosCodEventoDemRateio();

    public abstract List buscaDadosGeraisDemRateio(String s, String s1, String s2, String s3);
}