
package br.gov.pbh.desif.dao.impl;

import br.gov.pbh.desif.base.dao.impl.BaseDaoImpl;
import br.gov.pbh.desif.dao.ErroDao;
import br.gov.pbh.desif.model.pojo.Erro;

public class ErroDaoImpl extends BaseDaoImpl
    implements ErroDao
{

    public ErroDaoImpl()
    {
    }

    public Class getReferenceClass()
    {
        return br.gov.pbh.desif.model.pojo.Erro.class;
    }
}