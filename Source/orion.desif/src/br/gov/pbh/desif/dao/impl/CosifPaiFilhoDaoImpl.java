package br.gov.pbh.desif.dao.impl;

import br.gov.pbh.desif.base.dao.impl.BaseDaoImpl;
import br.gov.pbh.desif.dao.CosifPaiFilhoDao;
import br.gov.pbh.desif.model.pojo.Cosif;
import br.gov.pbh.desif.model.pojo.CosifPaiFilho;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class CosifPaiFilhoDaoImpl extends BaseDaoImpl
    implements CosifPaiFilhoDao
{

    public CosifPaiFilhoDaoImpl()
    {
    }

    public Class getReferenceClass()
    {
        return br.gov.pbh.desif.model.pojo.CosifPaiFilho.class;
    }

    public boolean identificarPossuiFilhos(Cosif cosif)
    {
        boolean resposta = false;
        Criteria c = getConexao().createCriteria(getReferenceClass());
        c.add(Restrictions.eq("CosifPaiFilhosForContaCosifPai", cosif));
        if(c.list().size() > 0)
            resposta = true;
        return resposta;
    }
}