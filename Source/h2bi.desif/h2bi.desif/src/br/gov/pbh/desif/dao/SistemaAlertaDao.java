
package br.gov.pbh.desif.dao;

import br.gov.pbh.desif.base.dao.BaseDao;
import java.util.List;

public interface SistemaAlertaDao
    extends BaseDao
{

    public abstract Long countSistemaAlerta();

    public abstract List paginacaoDadosSistemaErro(double d);
}