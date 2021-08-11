
package br.gov.pbh.desif.dao.impl;

import br.gov.pbh.desif.base.dao.impl.BaseDaoImpl;
import br.gov.pbh.desif.dao.TipoDependenciaDao;
import br.gov.pbh.desif.model.pojo.TipoDependencia;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class TipoDependenciaDaoImpl extends BaseDaoImpl
    implements TipoDependenciaDao
{

    public TipoDependenciaDaoImpl()
    {
    }

    public Class getReferenceClass()
    {
       return br.gov.pbh.desif.model.pojo.TipoDependencia.class;
    }

    public List identificarTipoDependencia(String campo, Integer valor)
    {
        Criteria c = getConexao().createCriteria(getReferenceClass());
        c.add(Restrictions.eq(campo, valor));
        List result = c.list();
        return result;
    }
}