package br.gov.pbh.desif.dao.impl;

import br.gov.pbh.desif.base.dao.impl.BaseDaoImpl;
import br.gov.pbh.desif.dao.CidadeDao;
import br.gov.pbh.desif.model.pojo.Cidade;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class CidadeDaoImpl extends BaseDaoImpl
    implements CidadeDao
{

    public CidadeDaoImpl()
    {
    }

    public Class getReferenceClass()
    {
        return br.gov.pbh.desif.model.pojo.Cidade.class;
    }

    public List identificarCodCidade(String campo, Long valor)
    {
        Criteria c = getConexao().createCriteria(getReferenceClass());
        c.add(Restrictions.eq(campo, valor));
        List result = c.list();
        return result;
    }
}