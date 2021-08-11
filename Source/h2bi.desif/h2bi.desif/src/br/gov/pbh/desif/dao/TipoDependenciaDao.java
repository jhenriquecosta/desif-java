
package br.gov.pbh.desif.dao;

import br.gov.pbh.desif.base.dao.BaseDao;
import java.util.List;

public interface TipoDependenciaDao
    extends BaseDao
{

    public abstract List identificarTipoDependencia(String s, Integer integer);
}