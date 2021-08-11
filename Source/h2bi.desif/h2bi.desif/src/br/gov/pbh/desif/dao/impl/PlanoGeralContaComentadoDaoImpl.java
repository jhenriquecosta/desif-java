
package br.gov.pbh.desif.dao.impl;

import br.gov.pbh.desif.base.dao.impl.BaseDaoImpl;
import br.gov.pbh.desif.dao.PlanoGeralContaComentadoDao;
import br.gov.pbh.desif.model.pojo.PlanoGeralContaComentado;
import java.util.List;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

public class PlanoGeralContaComentadoDaoImpl extends BaseDaoImpl
    implements PlanoGeralContaComentadoDao
{

    public PlanoGeralContaComentadoDaoImpl()
    {
    }

    public Class getReferenceClass()
    {
        return br.gov.pbh.desif.model.pojo.PlanoGeralContaComentado.class;
    }

    public List findField(String campo, String valor)
    {
        Criteria c = getConexao().createCriteria(getReferenceClass());
        c.add(Restrictions.eq(campo, valor));
        List result = c.list();
        return result;
    }

    public List buscarRaizArvore()
    {
        Query q = getConexao().createQuery("FROM PlanoGeralContaComentado where contaSupe = ''");
        return q.list();
    }

    public List buscarGalhos(String contaSuperior)
    {
        Query q = getConexao().createQuery("FROM PlanoGeralContaComentado where contaSupe = :contaSuperior");
        q.setString("contaSuperior", contaSuperior);
        return q.list();
    }

    public PlanoGeralContaComentado buscaPgcc(String contaPgcc)
    {
        Criteria c = getConexao().createCriteria(getReferenceClass());
        c.add(Restrictions.eq("conta", contaPgcc));
        PlanoGeralContaComentado result = (PlanoGeralContaComentado)c.uniqueResult();
        return result;
    }

    public List findField(String campo, Integer valor)
    {
        Criteria c = getConexao().createCriteria(getReferenceClass());
        c.add(Restrictions.eq(campo, valor));
        List result = c.list();
        return result;
    }
}