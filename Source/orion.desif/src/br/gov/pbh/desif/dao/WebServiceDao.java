
package br.gov.pbh.desif.dao;

import br.gov.pbh.desif.base.dao.BaseDao;
import java.util.List;

public interface WebServiceDao
    extends BaseDao
{

    public abstract List buscarCnpjConsistenciaWs();

    public abstract List buscarInscricaoMunicipalWs();
}