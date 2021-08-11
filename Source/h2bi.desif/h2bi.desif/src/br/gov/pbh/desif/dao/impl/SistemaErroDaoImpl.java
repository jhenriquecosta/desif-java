
package br.gov.pbh.desif.dao.impl;

import br.gov.pbh.desif.base.dao.impl.BaseDaoImpl;
import br.gov.pbh.desif.dao.SistemaErroDao;
import br.gov.pbh.desif.model.pojo.SistemaErro;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class SistemaErroDaoImpl extends BaseDaoImpl
    implements SistemaErroDao
{

    public SistemaErroDaoImpl()
    {
    }

    public Class getReferenceClass()
    {
        return br.gov.pbh.desif.model.pojo.SistemaErro.class;
    }

    public List paginacaoDadosSistemaErro(double pagina)
    {
        Query q = getConexao().createQuery("FROM SistemaErro");
        q.setMaxResults(10);
        if(pagina == 0.0D)
        {
            q.setFirstResult(0);
        } else
        {
            Double mult = new Double(pagina * 10D);
            q.setFirstResult(mult.intValue());
        }
        List result = q.list();
        return result;
    }

    public Long countSistemaErro()
    {
        Query q = getConexao().createQuery("SELECT count(id) FROM SistemaErro");
        return (Long)q.uniqueResult();
    }
}