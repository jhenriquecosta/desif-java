
package br.gov.pbh.desif.dao;

import br.gov.pbh.desif.base.dao.BaseDao;

public interface LimparBancoDao
    extends BaseDao
{

    public abstract void limparSessao();
}