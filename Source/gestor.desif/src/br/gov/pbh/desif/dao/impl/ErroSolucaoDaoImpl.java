
package br.gov.pbh.desif.dao.impl;

import br.gov.pbh.desif.base.dao.impl.BaseDaoImpl;
import br.gov.pbh.desif.dao.ErroSolucaoDao;
import br.gov.pbh.desif.model.pojo.ErroSolucao;

public class ErroSolucaoDaoImpl extends BaseDaoImpl
    implements ErroSolucaoDao
{

    public ErroSolucaoDaoImpl()
    {
    }

    public Class getReferenceClass()
    {
       return br.gov.pbh.desif.model.pojo.ErroSolucao.class;
    }
}