
package br.gov.pbh.desif.dao;

import br.gov.pbh.desif.base.dao.BaseDao;
import java.util.List;

public interface CidadeDao
    extends BaseDao
{

    public abstract List identificarCodCidade(String s, Long long1);
}