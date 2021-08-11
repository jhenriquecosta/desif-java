
package br.gov.pbh.desif.dao;

import br.gov.pbh.desif.base.dao.BaseDao;
import java.util.Date;
import java.util.List;

public interface IdentDependenciaDao
    extends BaseDao
{

    public abstract List findField(String s, String s1);

    public abstract List findFields(String s, String s1, String s2, Short short1);

    public abstract List unicidadeDependencia();

    public abstract boolean verificaDentroParalisacao(Date date);

    public abstract List verificaContabilidadePropriaDependencia(String s);

    public abstract List buscaCodigoDependencia();
}