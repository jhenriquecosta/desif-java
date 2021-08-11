
package br.gov.pbh.desif.dao;

import br.gov.pbh.desif.base.dao.BaseDao;
import java.util.List;

public interface RelatorioDao
    extends BaseDao
{

    public abstract List buscaDadosRelatorioDependencias();

    public abstract List buscaDadosRelatorioApurIssqn(String s);

    public abstract List buscaDadosRelatorioApurReceitaSubtitulo(String s, String s1);

    public abstract Object buscaDadosEstaticosRelatorioDependencias();

    public abstract Object buscaDadosEstaticosRelatorioApurIssqn();

    public abstract Object buscaDadosEstaticosRelatorioApurIssqn(String s);

    public abstract Object buscaDadosEstaticosRelatorioApurSubtitulo(String s);

    public abstract Object buscaDadosGuia(String s);

    public abstract Object buscaTaxaExpediente();

    public abstract List buscaFiltrosApuracaoIssqn();

    public abstract List buscaFiltrosApuracaoSubtituloCNPJCodDependencia();

    public abstract List buscaFiltrosApuracaoSubtituloCodTribAliquota();

    public abstract List buscaFiltrosGuia();

    public abstract Integer sequenciaGuia(String s);
}