
package br.gov.pbh.desif.dao.impl;

import br.gov.pbh.desif.base.dao.impl.BaseDaoImpl;
import br.gov.pbh.desif.dao.PgccsPaiFilhoDao;
import br.gov.pbh.desif.model.pojo.PgccsPaiFilho;
import br.gov.pbh.desif.model.pojo.PlanoGeralContaComentado;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class PgccsPaiFilhoDaoImpl extends BaseDaoImpl
    implements PgccsPaiFilhoDao
{

    public PgccsPaiFilhoDaoImpl()
    {
    }

    public Class getReferenceClass()
    {
         return br.gov.pbh.desif.model.pojo.PgccsPaiFilho.class;
    }

    public boolean identificarPossuiFilhos(PlanoGeralContaComentado pgcc)
    {
        boolean resposta = false;
        Criteria c = getConexao().createCriteria(getReferenceClass());
        c.add(Restrictions.eq("planoGeralContaComentadoByIdnPgccPai", pgcc));
        if(c.list().size() > 0)
            resposta = true;
        return resposta;
    }
}