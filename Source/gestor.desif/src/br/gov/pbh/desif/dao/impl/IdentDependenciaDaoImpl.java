
package br.gov.pbh.desif.dao.impl;

import br.gov.pbh.desif.base.dao.impl.BaseDaoImpl;
import br.gov.pbh.desif.dao.IdentDependenciaDao;
import br.gov.pbh.desif.model.pojo.IdentificacaoDependencia;
import java.util.Date;
import java.util.List;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

public class IdentDependenciaDaoImpl extends BaseDaoImpl
    implements IdentDependenciaDao
{

    public IdentDependenciaDaoImpl()
    {
    }

    public List findField(String campo, String valor)
    {
        Criteria c = getConexao().createCriteria(getReferenceClass());
        c.add(Restrictions.eq(campo, valor));
        List result = c.list();
        return result;
    }

    public List findFields(String campo1, String campo2, String valor1, Short valor2)
    {
        List result = null;
        Criteria c = getConexao().createCriteria(getReferenceClass());
        c.add(Restrictions.eq(campo1, valor1));
        c.add(Restrictions.eq(campo2, valor2));
        result = c.list();
        return result;
    }

    public List unicidadeDependencia()
    {
        List result = null;
        Query q = getConexao().createQuery("select LIMIT 0 2 codigoDependencia from IdentificacaoDependencia group by codigoDependencia having count(codigoDependencia) > 1 ");
        result = q.list();
        return result;
    }

    public boolean verificaDentroParalisacao(Date dt)
    {
        List result = null;
        boolean resp = false;
        Query q = getConexao().createQuery("FROM IdentificacaoDependencia where :data between dataInicioParalizacao and dataFimParalizacao");
        q.setDate("data", dt);
        result = q.list();
        if(result.size() > 0)
            resp = true;
        return resp;
    }

    public List verificaContabilidadePropriaDependencia(String codDependencia)
    {
        List result = null;
        Query q = getConexao().createQuery("select dep.contabilidadePropria from IdentificacaoDependencia as dep where dep.codigoDependencia = :codDependencia");
        q.setString("codDependencia", codDependencia);
        q.setMaxResults(1);
        result = q.list();
        return result;
    }

    public List buscaCodigoDependencia()
    {
        List lstCodigoDependencia = null;
        Query q = getConexao().createQuery("select dep.codigoDependencia, dep.contabilidadePropria from IdentificacaoDependencia as dep");
        lstCodigoDependencia = q.list();
        return lstCodigoDependencia;
    }

    public Class getReferenceClass()
    {
        return br.gov.pbh.desif.model.pojo.IdentificacaoDependencia.class;
    }
}