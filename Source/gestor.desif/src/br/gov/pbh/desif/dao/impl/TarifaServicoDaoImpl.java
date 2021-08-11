
package br.gov.pbh.desif.dao.impl;

import br.gov.pbh.desif.base.dao.impl.BaseDaoImpl;
import br.gov.pbh.desif.dao.TarifaServicoDao;
import br.gov.pbh.desif.model.pojo.TarifaServico;
import java.util.List;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

public class TarifaServicoDaoImpl extends BaseDaoImpl
    implements TarifaServicoDao
{

    public TarifaServicoDaoImpl()
    {
    }

    public Class getReferenceClass()
    {
        return br.gov.pbh.desif.model.pojo.TarifaServico.class;
    }

    public boolean verificaUnicidadeCodIdentTarifaeCosSubtitulo(String codIdentTarifa, String codSubtitulo)
    {
        List result = null;
        boolean resp = false;
        Query q = getConexao().createQuery("FROM TarifaServico where codIdentTarifa = :codIdentTarifa and codSubtitulo = :codSubtitulo");
        q.setString("codIdentTarifa", codIdentTarifa);
        q.setString("codSubtitulo", codSubtitulo);
        result = q.list();
        if(result.size() > 1)
            resp = true;
        return resp;
    }

    public List findField(String campo, String valor)
    {
        Criteria c = getConexao().createCriteria(getReferenceClass());
        c.add(Restrictions.eq(campo, valor));
        List result = c.list();
        return result;
    }
}