
package br.gov.pbh.desif.dao;

import br.gov.pbh.desif.base.dao.BaseDao;
import br.gov.pbh.desif.model.pojo.PlanoGeralContaComentado;
import java.util.List;

public interface PlanoGeralContaComentadoDao
    extends BaseDao
{

    public abstract List findField(String s, String s1);

    public abstract List buscarRaizArvore();

    public abstract List buscarGalhos(String s);

    public abstract PlanoGeralContaComentado buscaPgcc(String s);

    public abstract List findField(String s, Integer integer);
}