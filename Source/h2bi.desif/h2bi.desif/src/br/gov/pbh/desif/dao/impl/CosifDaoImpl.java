package br.gov.pbh.desif.dao.impl;

import br.gov.pbh.desif.base.dao.impl.BaseDaoImpl;
import br.gov.pbh.desif.dao.CosifDao;
import br.gov.pbh.desif.model.pojo.Cosif;
import java.util.List;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

public class CosifDaoImpl extends BaseDaoImpl
    implements CosifDao
{

    public CosifDaoImpl()
    {
    }

    public Class getReferenceClass()
    {
        return br.gov.pbh.desif.model.pojo.Cosif.class;
    }

    public List findField(String campo, String valor)
    {
        Criteria c = getConexao().createCriteria(getReferenceClass());
        c.add(Restrictions.eq(campo, valor));
        List result = c.list();
        return result;
    }

    public List buscarRaizCosif()
    {
        Query q = getConexao().createQuery("FROM Cosif where numeroContaSuperior = '99999995'");
        return q.list();
    }

    public List buscarGalhos(String contaSuperior)
    {
        Query q = getConexao().createQuery("FROM Cosif where numeroContaSuperior = :contaSuperior");
        q.setString("contaSuperior", contaSuperior);
        return q.list();
    }

    public Cosif BuscaCosif(String contaCosif)
    {
        Criteria c = getConexao().createCriteria(getReferenceClass());
        c.add(Restrictions.eq("numeroContaCosif", contaCosif));
        Cosif result = (Cosif)c.uniqueResult();
        return result;
    }
}