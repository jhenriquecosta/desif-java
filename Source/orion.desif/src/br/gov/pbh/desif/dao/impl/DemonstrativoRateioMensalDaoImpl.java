
package br.gov.pbh.desif.dao.impl;

import br.gov.pbh.desif.base.dao.impl.BaseDaoImpl;
import br.gov.pbh.desif.dao.DemonstrativoRateioMensalDao;
import br.gov.pbh.desif.model.pojo.DemonstrativoRateioMensal;

public class DemonstrativoRateioMensalDaoImpl extends BaseDaoImpl
    implements DemonstrativoRateioMensalDao
{

    public DemonstrativoRateioMensalDaoImpl()
    {
    }

    public Class getReferenceClass()
    {
       return br.gov.pbh.desif.model.pojo.DemonstrativoRateioMensal.class;
    }
}