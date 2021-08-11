
package br.gov.pbh.desif.dao;

import br.gov.pbh.desif.base.dao.BaseDao;
import java.util.List;

public interface TituloDao
    extends BaseDao
{

    public abstract List identificarTipoInstituicao(String s, String s1);
}