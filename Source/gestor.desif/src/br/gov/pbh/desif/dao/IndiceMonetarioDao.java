/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.dao;

import br.gov.pbh.desif.base.dao.BaseDao;
import java.util.Date;
import java.util.List;

public interface IndiceMonetarioDao
extends BaseDao {
    public List findByDatas(Date var1, Date var2);

    public boolean existeIndice(String var1);

    public Date anoUltimoIndiceAtualizado();
}

