
package br.gov.pbh.desif.dao;

import br.gov.pbh.desif.base.dao.BaseDao;
import java.util.List;

public interface CodTributacaoDesifDao
    extends BaseDao
{

    public abstract List buscaCodTributacaoDesif(String s, String s1);
}