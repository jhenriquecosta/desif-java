
package br.gov.pbh.desif.dao.impl;

import br.gov.pbh.desif.base.dao.impl.BaseDaoImpl;
import br.gov.pbh.desif.dao.IdentServicosRemunVariavelDao;
import br.gov.pbh.desif.model.pojo.IdentServicosRemunVariavel;
import java.util.List;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

public class IdentServicosRemunVariavelDaoImpl extends BaseDaoImpl
    implements IdentServicosRemunVariavelDao
{

    public IdentServicosRemunVariavelDaoImpl()
    {
    }

    public Class getReferenceClass()
    {
       return br.gov.pbh.desif.model.pojo.IdentServicosRemunVariavel.class;
    }

    public List findField(String campo, String valor)
    {
        Criteria c = getConexao().createCriteria(getReferenceClass());
        c.add(Restrictions.eq(campo, valor));
        List result = c.list();
        return result;
    }

    public boolean verificaUnicidadeCodIdentServRemVarSubtitulo(String codIdentServRemVar, String codSubtitulo)
    {
        List result = null;
        boolean resp = false;
        Query q = getConexao().createQuery("FROM IdentServicosRemunVariavel where codIdentServRemnVariavel = :codIdentServRemVar and codSubtitulo = :codSubtitulo");
        q.setString("codIdentServRemVar", codIdentServRemVar);
        q.setString("codSubtitulo", codSubtitulo);
        result = q.list();
        if(result.size() > 1)
            resp = true;
        return resp;
    }
}