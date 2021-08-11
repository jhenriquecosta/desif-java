
package br.gov.pbh.desif.dao.impl;

import br.gov.pbh.desif.base.dao.impl.BaseDaoImpl;
import br.gov.pbh.desif.dao.IndiceMonetarioDao;
import br.gov.pbh.desif.model.pojo.IndicesMonetarios;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

public class IndiceMonetarioDaoImpl extends BaseDaoImpl
    implements IndiceMonetarioDao
{

    private static final Log log = LogFactory.getLog(br.gov.pbh.desif.dao.impl.IndiceMonetarioDaoImpl.class);

    public IndiceMonetarioDaoImpl()
    {
    }

    public List findByDatas(Date dataIni, Date dataFim)
    {
        Criteria c = getConexao().createCriteria(getReferenceClass());
        c.add(Restrictions.between("id.datRef", dataIni, dataFim));
        return c.list();
    }

    public boolean existeIndice(String dtIndMonet)
    {
        String sql = (new StringBuilder()).append("SELECT ind.id.datRef FROM IndicesMonetarios as ind WHERE ind.id.datRef LIKE '").append(dtIndMonet).append("%'").toString();
        Query q = getConexao().createQuery(sql);
        boolean resp = false;
        if(q.list().size() > 0)
            resp = true;
        else
            resp = false;
        return resp;
    }

    public Date anoUltimoIndiceAtualizado()
    {
        Query q = getConexao().createQuery("SELECT ind.id.datRef FROM IndicesMonetarios as ind ORDER BY ind.id.datRef DESC");
        q.setMaxResults(1);
        Date dtIndiceMonet = (Date)q.list().get(0);
        return dtIndiceMonet;
    }

    public Class getReferenceClass()
    {
        return br.gov.pbh.desif.model.pojo.IndicesMonetarios.class;
    }

}