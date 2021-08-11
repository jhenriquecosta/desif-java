
package br.gov.pbh.desif.dao;

import br.gov.pbh.desif.base.dao.BaseDao;
import java.util.List;

public interface IdentServicosRemunVariavelDao
    extends BaseDao
{

    public abstract List findField(String s, String s1);

    public abstract boolean verificaUnicidadeCodIdentServRemVarSubtitulo(String s, String s1);
}