
package br.gov.pbh.desif.dao;

import br.gov.pbh.desif.base.dao.BaseDao;
import java.util.List;

public interface TarifaServicoDao
    extends BaseDao
{

    public abstract boolean verificaUnicidadeCodIdentTarifaeCosSubtitulo(String s, String s1);

    public abstract List findField(String s, String s1);
}