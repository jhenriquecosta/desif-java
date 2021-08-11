
package br.gov.pbh.desif.dao.impl;

import br.gov.pbh.desif.base.dao.impl.BaseDaoImpl;
import br.gov.pbh.desif.dao.EventosContabeisDao;
import br.gov.pbh.desif.model.pojo.EventosContabeis;
import java.util.List;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

public class EventosContabeisDaoImpl extends BaseDaoImpl
    implements EventosContabeisDao
{

    public EventosContabeisDaoImpl()
    {
    }

    public Class getReferenceClass()
    {
        return br.gov.pbh.desif.model.pojo.EventosContabeis.class;
    }

    public List findField(String campo, String valor)
    {
        Criteria c = getConexao().createCriteria(getReferenceClass());
        c.add(Restrictions.eq(campo, valor));
        List result = c.list();
        return result;
    }

    public boolean verificaExistenciaCodigoEvento(int id)
    {
        boolean existe = false;
        Query q = getConexao().createQuery("select id from EventosContabeis where id = :id");
        q.setInteger("id", id);
        q.setMaxResults(1);
        if(q.list().size() > 0)
            existe = true;
        return existe;
    }
}