/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.dao;

import br.gov.pbh.desif.base.dao.BaseDao;
import java.util.List;

public interface RelatorioDao
extends BaseDao {
    public List buscaDadosRelatorioDependencias();

    public List buscaDadosRelatorioApurIssqn(String var1);

    public List buscaDadosRelatorioApurReceitaSubtitulo(String var1, String var2);

    public Object buscaDadosEstaticosRelatorioDependencias();

    public Object buscaDadosEstaticosRelatorioApurIssqn();

    public Object buscaDadosEstaticosRelatorioApurIssqn(String var1);

    public Object buscaDadosEstaticosRelatorioApurSubtitulo(String var1);

    public Object buscaDadosGuia(String var1);

    public Object buscaTaxaExpediente();

    public List buscaFiltrosApuracaoIssqn();

    public List buscaFiltrosApuracaoSubtituloCNPJCodDependencia();

    public List buscaFiltrosApuracaoSubtituloCodTribAliquota();

    public List buscaFiltrosGuia();

    public Integer sequenciaGuia(String var1);
}

