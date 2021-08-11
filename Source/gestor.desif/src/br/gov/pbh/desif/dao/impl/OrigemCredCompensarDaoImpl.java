
package br.gov.pbh.desif.dao.impl;

import br.gov.pbh.desif.base.dao.impl.BaseDaoImpl;
import br.gov.pbh.desif.dao.OrigemCredCompensarDao;
import br.gov.pbh.desif.model.pojo.OrigemCreditoCompensar;
import org.hibernate.Query;
import org.hibernate.Session;

public class OrigemCredCompensarDaoImpl extends BaseDaoImpl
    implements OrigemCredCompensarDao
{

    public OrigemCredCompensarDaoImpl()
    {
    }

    public Class getReferenceClass()
    {
        return  br.gov.pbh.desif.model.pojo.OrigemCreditoCompensar.class;
    }

    public Double valorSomatorioOrigemCredito(Long idIssqnMensal)
    {
        Query q = getConexao().createQuery("SELECT SUM(valorOrigemCredito) as SOMA FROM OrigemCreditoCompensar WHERE IssqnMensal.id = :idIssqn");
        q.setLong("idIssqn", idIssqnMensal.longValue());
        Double resp = (Double)q.uniqueResult();
        return resp;
    }
}