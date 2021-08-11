/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.dao;

import br.gov.pbh.desif.base.dao.BaseDao;
import java.util.Date;
import java.util.List;

public interface IdentDeclaracaoDao
extends BaseDao {
    public boolean verificaDentroCompetencia(Date var1);

    public boolean verificaMaiorDataInicioCompetencia(Date var1);

    public List findField(String var1, String var2);

    public List buscaInicioFimCompetencia();
}

