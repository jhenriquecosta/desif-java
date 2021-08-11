
package br.gov.pbh.desif.dao.impl;

import br.gov.pbh.desif.base.dao.impl.BaseDaoImpl;
import br.gov.pbh.desif.dao.NewIdentDeclaracaoDao;
import br.gov.pbh.desif.model.pojo.IdentificacaoDeclaracao;
import java.util.List;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

public class NewIdentDeclaracaoDaoImpl extends BaseDaoImpl
    implements NewIdentDeclaracaoDao
{

    public NewIdentDeclaracaoDaoImpl()
    {
    }

    public List findField(String campo, String valor)
    {
        Criteria c = getConexao().createCriteria(getReferenceClass());
        c.add(Restrictions.eq(campo, valor));
        List result = c.list();
        return result;
    }

    public String buscaNumVersaoDocumentacao()
    {
        Query q = getConexao().createQuery("SELECT idnVersao FROM NewIdentificacaoDeclaracao");
        q.setMaxResults(1);
        String numVersaoDocumentacao = q.list().get(0).toString();
        return numVersaoDocumentacao;
    }

    public Class getReferenceClass()
    {
        return br.gov.pbh.desif.model.pojo.IdentificacaoDeclaracao.class;
    }
}