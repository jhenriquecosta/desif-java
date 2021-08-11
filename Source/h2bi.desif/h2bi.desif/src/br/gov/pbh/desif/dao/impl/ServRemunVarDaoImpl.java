
package br.gov.pbh.desif.dao.impl;

import br.gov.pbh.desif.base.dao.impl.BaseDaoImpl;
import br.gov.pbh.desif.dao.ServRemunVarDao;
import br.gov.pbh.desif.model.pojo.ServRemunVar;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class ServRemunVarDaoImpl extends BaseDaoImpl
    implements ServRemunVarDao
{

    public ServRemunVarDaoImpl()
    {
    }

    public Class getReferenceClass()
    {
        return br.gov.pbh.desif.model.pojo.ServRemunVar.class;
    }

    public List findField(String campo, String valor)
    {
        Criteria c = getConexao().createCriteria(getReferenceClass());
        c.add(Restrictions.eq(campo, valor));
        List result = c.list();
        return result;
    }
}