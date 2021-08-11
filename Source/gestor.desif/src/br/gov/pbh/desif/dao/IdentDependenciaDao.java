/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.dao;

import br.gov.pbh.desif.base.dao.BaseDao;
import java.util.Date;
import java.util.List;

public interface IdentDependenciaDao
extends BaseDao {
    public List findField(String var1, String var2);

    public List findFields(String var1, String var2, String var3, Short var4);

    public List unicidadeDependencia();

    public boolean verificaDentroParalisacao(Date var1);

    public List verificaContabilidadePropriaDependencia(String var1);

    public List buscaCodigoDependencia();
}

