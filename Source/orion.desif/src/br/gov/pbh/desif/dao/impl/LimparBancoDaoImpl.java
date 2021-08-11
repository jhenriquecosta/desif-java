
package br.gov.pbh.desif.dao.impl;

import br.gov.pbh.desif.base.dao.impl.BaseDaoImpl;
import br.gov.pbh.desif.dao.LimparBancoDao;
import org.hibernate.Session;

public class LimparBancoDaoImpl extends BaseDaoImpl
    implements LimparBancoDao
{

    public LimparBancoDaoImpl()
    {
    }

    public Class getReferenceClass()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void limparSessao()
    {
        getConexao().evict(this);
    }
}