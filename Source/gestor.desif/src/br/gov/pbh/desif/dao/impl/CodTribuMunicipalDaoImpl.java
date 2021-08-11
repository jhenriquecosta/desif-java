package br.gov.pbh.desif.dao.impl;

import br.gov.pbh.desif.base.dao.impl.BaseDaoImpl;
import br.gov.pbh.desif.dao.CodTribuMunicipalDao;
import br.gov.pbh.desif.model.pojo.CodigoTributacaoMunicipal;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class CodTribuMunicipalDaoImpl extends BaseDaoImpl
    implements CodTribuMunicipalDao
{

    public CodTribuMunicipalDaoImpl()
    {
    }

    public Class getReferenceClass()
    {
        return br.gov.pbh.desif.model.pojo.CodigoTributacaoMunicipal.class;
    }

    public List buscaCodTribuMunicipal(String codTributacao, Long codCidade, Date dataComp)
    {
        Criteria c = getConexao().createCriteria(getReferenceClass());
        c.add(Restrictions.eq("codTributacao.id", codTributacao));
        c.add(Restrictions.eq("cidade.id", codCidade));
        c.add(Restrictions.le("dataInicioVigencia", dataComp));
        c.add(Restrictions.or(Restrictions.ge("dataFimVigencia", dataComp), Restrictions.isNull("dataFimVigencia")));
        List result = c.list();
        return result;
    }

    public List buscaCodTribuAliqMunicipal(String codTributacao, Long codCidade, Double aliqIssqn, Date dataComp)
    {
        Criteria c = getConexao().createCriteria(getReferenceClass());
        c.add(Restrictions.eq("codTributacao.id", codTributacao));
        c.add(Restrictions.eq("cidade.id", codCidade));
        c.add(Restrictions.eq("valorAliquota", aliqIssqn));
        c.add(Restrictions.le("dataInicioVigencia", dataComp));
        c.add(Restrictions.or(Restrictions.ge("dataFimVigencia", dataComp), Restrictions.isNull("dataFimVigencia")));
        List result = c.list();
        return result;
    }
}