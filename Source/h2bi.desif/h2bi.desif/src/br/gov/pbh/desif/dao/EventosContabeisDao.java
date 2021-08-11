
package br.gov.pbh.desif.dao;

import br.gov.pbh.desif.base.dao.BaseDao;
import java.util.List;

public interface EventosContabeisDao
    extends BaseDao
{

    public abstract List findField(String s, String s1);

    public abstract boolean verificaExistenciaCodigoEvento(int i);
}