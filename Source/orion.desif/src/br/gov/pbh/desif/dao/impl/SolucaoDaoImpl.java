
package br.gov.pbh.desif.dao.impl;

import br.gov.pbh.desif.base.dao.impl.BaseDaoImpl;
import br.gov.pbh.desif.dao.SolucaoDao;
import br.gov.pbh.desif.model.pojo.Solucao;

public class SolucaoDaoImpl extends BaseDaoImpl
    implements SolucaoDao
{

    public SolucaoDaoImpl()
    {
    }

    public Class getReferenceClass()
    {
        return br.gov.pbh.desif.model.pojo.Solucao.class;
    }
}