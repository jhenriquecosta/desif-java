
package br.gov.pbh.desif.dao;

import br.gov.pbh.desif.base.dao.BaseDao;
import java.util.List;

public interface RelatorioInfoComunsDao
    extends BaseDao
{

    public abstract List buscaDadosContasICM();

    public abstract List buscaDadosCodTributacaoICM();

    public abstract List buscaDadosGeraisPGCC(String s, String s1, String s2, String s3);

    public abstract List buscaDadosGeraisPGCC2(String s, String s1);

    public abstract List buscaDadosContaSuperiorPGCC();

    public abstract List buscaDadosContaCosifPGCC();

    public abstract List buscaDadosIdTarifa();

    public abstract List buscaDadosCodSubtitulo();

    public abstract List buscaDadosGeraisTarServInstituicao(String s, String s1);

    public abstract List buscaDadosIdServRemVar();

    public abstract List buscaDadosCodSubtituloRemVar();

    public abstract List buscaDadosGeraisServRemVar(String s, String s1);
}