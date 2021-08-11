/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.dao;

import br.gov.pbh.desif.base.dao.BaseDao;
import java.util.List;

public interface RelatorioInfoComunsDao
extends BaseDao {
    public List buscaDadosContasICM();

    public List buscaDadosCodTributacaoICM();

    public List buscaDadosGeraisPGCC(String var1, String var2, String var3, String var4);

    public List buscaDadosGeraisPGCC2(String var1, String var2);

    public List buscaDadosContaSuperiorPGCC();

    public List buscaDadosContaCosifPGCC();

    public List buscaDadosIdTarifa();

    public List buscaDadosCodSubtitulo();

    public List buscaDadosGeraisTarServInstituicao(String var1, String var2);

    public List buscaDadosIdServRemVar();

    public List buscaDadosCodSubtituloRemVar();

    public List buscaDadosGeraisServRemVar(String var1, String var2);
}

