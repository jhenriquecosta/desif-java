/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.dao;

import br.gov.pbh.desif.base.dao.BaseDao;
import java.util.Date;
import java.util.List;

public interface CodTribuMunicipalDao extends BaseDao 
{
    public List buscaCodTribuMunicipal(String var1, Long var2, Date var3);

    public List buscaCodTribuAliqMunicipal(String var1, Long var2, Double var3, Date var4);
}

