
package br.gov.pbh.desif.dao.impl;

import br.gov.pbh.desif.base.dao.impl.BaseDaoImpl;
import br.gov.pbh.desif.dao.WebServiceDao;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class WebServiceDaoImpl extends BaseDaoImpl
    implements WebServiceDao
{

    public WebServiceDaoImpl()
    {
    }

    public Class getReferenceClass()
    {
        return null;
    }

    public List buscarCnpjConsistenciaWs()
    {
        List result = null;
        Query q = getConexao().createQuery("SELECT cnpjProprio FROM IdentificacaoDependencia where cnpjProprio <> '' or cnpjProprio is null");
        result = q.list();
        return result;
    }

    public List buscarInscricaoMunicipalWs()
    {
        List result = null;
        Query q = getConexao().createQuery("SELECT codigoDependencia, cnpjUnificado, cnpjProprio FROM IdentificacaoDependencia where opcaoInscricaoMunicipal = '1'");
        result = q.list();
        return result;
    }
}