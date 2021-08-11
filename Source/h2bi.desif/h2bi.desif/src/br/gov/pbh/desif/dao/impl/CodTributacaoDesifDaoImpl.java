package br.gov.pbh.desif.dao.impl;

import br.gov.pbh.desif.base.dao.impl.BaseDaoImpl;
import br.gov.pbh.desif.dao.CodTributacaoDesifDao;
import br.gov.pbh.desif.model.pojo.CodigoTributacaoDesif;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class CodTributacaoDesifDaoImpl extends BaseDaoImpl
    implements CodTributacaoDesifDao
{

    public CodTributacaoDesifDaoImpl()
    {
    }

    public Class getReferenceClass()
    {
        return br.gov.pbh.desif.model.pojo.CodigoTributacaoDesif.class;
    }

    public List buscaCodTributacaoDesif(String campo, String valor)
    {
        Criteria c = getConexao().createCriteria(getReferenceClass());
        c.add(Restrictions.eq(campo, valor));
        List result = c.list();
        return result;
    }
}