
package br.gov.pbh.desif.dao;

import br.gov.pbh.desif.base.dao.BaseDao;
import java.util.Date;
import java.util.List;

public interface CodTribuMunicipalDao
    extends BaseDao
{

    public abstract List buscaCodTribuMunicipal(String s, Long long1, Date date);

    public abstract List buscaCodTribuAliqMunicipal(String s, Long long1, Double double1, Date date);
}