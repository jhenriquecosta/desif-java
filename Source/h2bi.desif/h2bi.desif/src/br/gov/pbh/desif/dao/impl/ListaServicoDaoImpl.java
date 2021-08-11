
package br.gov.pbh.desif.dao.impl;

import br.gov.pbh.desif.base.dao.impl.BaseDaoImpl;
import br.gov.pbh.desif.dao.ListaServicoDao;
import br.gov.pbh.desif.model.pojo.ListaServico;

public class ListaServicoDaoImpl extends BaseDaoImpl
    implements ListaServicoDao
{

    public ListaServicoDaoImpl()
    {
    }

    public Class getReferenceClass()
    {
        return br.gov.pbh.desif.model.pojo.ListaServico.class;
    }
}