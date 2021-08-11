

package br.gov.pbh.desif.dao.impl;

import br.gov.pbh.desif.base.dao.impl.BaseDaoImpl;
import br.gov.pbh.desif.dao.SistemaAlertaDao;
import br.gov.pbh.desif.model.pojo.SistemaAlerta;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class SistemaAlertaDaoImpl extends BaseDaoImpl
    implements SistemaAlertaDao
{

    public SistemaAlertaDaoImpl()
    {
    }

    public Class getReferenceClass()
    {
        return br.gov.pbh.desif.model.pojo.SistemaAlerta.class;
    }

    public Long countSistemaAlerta()
    {
        Query q = getConexao().createQuery("SELECT count(id) FROM SistemaAlerta");
        return (Long)q.uniqueResult();
    }

    public List paginacaoDadosSistemaErro(double pagina)
    {
        Query q = getConexao().createQuery("FROM SistemaAlerta");
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
}