
package br.gov.pbh.desif.dao;

import br.gov.pbh.desif.base.dao.BaseDao;
import br.gov.pbh.desif.model.pojo.Cosif;

public interface CosifPaiFilhoDao
    extends BaseDao
{

    public abstract boolean identificarPossuiFilhos(Cosif cosif);
}