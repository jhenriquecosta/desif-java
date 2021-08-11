
package br.gov.pbh.desif.dao;

import br.gov.pbh.desif.base.dao.BaseDao;
import br.gov.pbh.desif.model.pojo.Cosif;
import java.util.List;

public interface CosifDao
    extends BaseDao
{

    public abstract List findField(String s, String s1);

    public abstract List buscarRaizCosif();

    public abstract List buscarGalhos(String s);

    public abstract Cosif BuscaCosif(String s);
}