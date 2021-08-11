
package br.gov.pbh.desif.dao;

import br.gov.pbh.desif.base.dao.BaseDao;
import java.util.Date;
import java.util.List;

public interface IdentDeclaracaoDao
    extends BaseDao
{

    public abstract boolean verificaDentroCompetencia(Date date);

    public abstract boolean verificaMaiorDataInicioCompetencia(Date date);

    public abstract List findField(String s, String s1);

    public abstract List buscaInicioFimCompetencia();
}