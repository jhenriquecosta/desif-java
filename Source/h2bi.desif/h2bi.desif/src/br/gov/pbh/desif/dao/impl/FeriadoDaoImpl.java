
package br.gov.pbh.desif.dao.impl;

import br.gov.pbh.desif.base.dao.impl.BaseDaoImpl;
import br.gov.pbh.desif.dao.FeriadoDao;
import br.gov.pbh.desif.model.pojo.Feriados;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class FeriadoDaoImpl extends BaseDaoImpl
    implements FeriadoDao
{

    public FeriadoDaoImpl()
    {
    }

    public List findAllDates()
    {
        Criteria c = getConexao().createCriteria(getReferenceClass());
        c.setProjection(Projections.property("id.datFeri"));
        return c.list();
    }

    public boolean findDateNextYear(Date newYear)
    {
        boolean resp = false;
        Criteria c = getConexao().createCriteria(getReferenceClass());
        List result = c.add(Restrictions.gt("id.datFeri", newYear)).list();
        if(result.size() > 0)
            resp = true;
        return resp;
    }

    public Class getReferenceClass()
    {
        return br.gov.pbh.desif.model.pojo.Feriados.class;
    }
}