/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.dao;

import br.gov.pbh.desif.base.dao.BaseDao;
import br.gov.pbh.desif.model.pojo.Cosif;
import java.util.List;

public interface CosifDao
extends BaseDao {
    public List findField(String var1, String var2);

    public List buscarRaizCosif();

    public List buscarGalhos(String var1);

    public Cosif BuscaCosif(String var1);
}

