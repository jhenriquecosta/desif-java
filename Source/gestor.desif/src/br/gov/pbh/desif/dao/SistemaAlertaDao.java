/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.dao;

import br.gov.pbh.desif.base.dao.BaseDao;
import java.util.List;

public interface SistemaAlertaDao
extends BaseDao {
    public Long countSistemaAlerta();

    public List paginacaoDadosSistemaErro(double var1);
}

