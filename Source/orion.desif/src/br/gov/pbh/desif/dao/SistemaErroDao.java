
package br.gov.pbh.desif.dao;

import br.gov.pbh.desif.base.dao.BaseDao;
import java.util.List;

public interface SistemaErroDao
    extends BaseDao
{

    public abstract List paginacaoDadosSistemaErro(double d);

    public abstract Long countSistemaErro();
}