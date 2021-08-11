
package br.gov.pbh.desif.dao.impl;

import br.gov.pbh.desif.base.dao.impl.BaseDaoImpl;
import br.gov.pbh.desif.dao.IdentDeclaracaoDao;
import br.gov.pbh.desif.model.pojo.IdentificacaoDeclaracao;
import java.util.Date;
import java.util.List;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

public class IdentDeclaracaoDaoImpl extends BaseDaoImpl
    implements IdentDeclaracaoDao
{

    public IdentDeclaracaoDaoImpl()
    {
    }

    public List findField(String campo, String valor)
    {
        Criteria c = getConexao().createCriteria(getReferenceClass());
        c.add(Restrictions.eq(campo, valor));
        List result = c.list();
        return result;
    }

    public boolean verificaDentroCompetencia(Date dt)
    {
        List result = null;
        boolean resp = false;
        Query q = getConexao().createQuery("FROM IdentificacaoDeclaracao where :data between dataInicioCompetencia and dataFimCompetencia");
        q.setDate("data", dt);
        result = q.list();
        if(result.size() > 0)
            resp = true;
        return resp;
    }

    public boolean verificaMaiorDataInicioCompetencia(Date dt)
    {
        List result = null;
        boolean resp = false;
        Query q = getConexao().createQuery("FROM IdentificacaoDeclaracao where :data >= dataInicioCompetencia");
        q.setDate("data", dt);
        result = q.list();
        if(result.size() > 0)
            resp = true;
        return resp;
    }

    public List buscaInicioFimCompetencia()
    {
        List result = null;
        Query q = getConexao().createQuery("select dcl.dataInicioCompetencia, dcl.dataFimCompetencia from IdentificacaoDeclaracao as dcl");
        result = q.list();
        return result;
    }

    public Class getReferenceClass()
    {
       return br.gov.pbh.desif.model.pojo.IdentificacaoDeclaracao.class;
    }
}