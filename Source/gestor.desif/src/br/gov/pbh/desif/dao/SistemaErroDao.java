/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.dao;

import br.gov.pbh.desif.base.dao.BaseDao;
import java.util.List;

public interface SistemaErroDao
extends BaseDao {
    public List paginacaoDadosSistemaErro(double var1);

    public Long countSistemaErro();
}

