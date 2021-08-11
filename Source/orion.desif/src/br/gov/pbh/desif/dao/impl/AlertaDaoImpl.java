package br.gov.pbh.desif.dao.impl;
import br.gov.pbh.desif.base.dao.impl.BaseDaoImpl;
import br.gov.pbh.desif.dao.AlertaDao;
import br.gov.pbh.desif.model.pojo.Alerta;

public class AlertaDaoImpl extends BaseDaoImpl
    implements AlertaDao
{

    public AlertaDaoImpl()
    {
    }

    @Override
    public Class getReferenceClass()
    {
        return br.gov.pbh.desif.model.pojo.Alerta.class;
    }
}