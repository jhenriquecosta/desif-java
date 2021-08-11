/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.dao;

import br.gov.pbh.desif.base.dao.BaseDao;
import br.gov.pbh.desif.model.pojo.PlanoGeralContaComentado;
import java.util.List;

public interface PlanoGeralContaComentadoDao
extends BaseDao {
    public List findField(String var1, String var2);

    public List buscarRaizArvore();

    public List buscarGalhos(String var1);

    public PlanoGeralContaComentado buscaPgcc(String var1);

    public List findField(String var1, Integer var2);
}

