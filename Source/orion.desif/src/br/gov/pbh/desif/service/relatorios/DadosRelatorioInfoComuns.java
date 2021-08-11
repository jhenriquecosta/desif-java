
package br.gov.pbh.desif.service.relatorios;

import br.gov.pbh.desif.dao.*;
import br.gov.pbh.desif.service.contexto.Contexto;
import java.util.List;

public class DadosRelatorioInfoComuns
{

    private RelatorioInfoComunsDao relInfoComunsDao;
    private IdentDeclaracaoDao declaracaoDao;
    private IdentDependenciaDao dependenciaDao;

    public DadosRelatorioInfoComuns()
    {
        relInfoComunsDao = (RelatorioInfoComunsDao)Contexto.getObject("relatorioICMDao");
    }

    public Object buscaDadosIdentificacaoDeclaracao()
    {
        declaracaoDao = (IdentDeclaracaoDao)Contexto.getObject("declaracaoDao");
        Object o = declaracaoDao.load(Integer.valueOf(1));
        return o;
    }

    public int buscaCountIdentificacoDependencia()
    {
        
        dependenciaDao = (IdentDependenciaDao)Contexto.getObject("dependenciaDao");
        List dependencias = dependenciaDao.findAll();
        return dependencias.size();
    }

    public List buscaFiltrosContasICM()
    {
        return relInfoComunsDao.buscaDadosContasICM();
    }

    public List buscaFiltrosCodTributacaoICM()
    {
        return relInfoComunsDao.buscaDadosCodTributacaoICM();
    }

    public List buscaFiltrosContaSuperiorPGCC()
    {
        return relInfoComunsDao.buscaDadosContaSuperiorPGCC();
    }

    public List buscaFiltrosContaCosifPGCC()
    {
        return relInfoComunsDao.buscaDadosContaCosifPGCC();
    }

    public List buscaFiltrosDadosGeraisPGCC(String conta, String contaSuperior, String contaCosif, String codTribDesif)
    {
        return relInfoComunsDao.buscaDadosGeraisPGCC(conta, contaSuperior, contaCosif, codTribDesif);
    }

    public List buscaFiltrosDadosGeraisPGCC2(String codTributacao, String conta)
    {
        return relInfoComunsDao.buscaDadosGeraisPGCC2(codTributacao, conta);
    }

    public List buscaFiltrosIdTarifa()
    {
        return relInfoComunsDao.buscaDadosIdTarifa();
    }

    public List buscaFiltrosCodSubtitulo()
    {
        return relInfoComunsDao.buscaDadosCodSubtitulo();
    }

    public List buscaFiltrosDadosGeraisTarServInstituicao(String idTarifa, String codSubtitulo)
    {
        return relInfoComunsDao.buscaDadosGeraisTarServInstituicao(idTarifa, codSubtitulo);
    }

    public List buscaFiltrosIdServRemVar()
    {
        return relInfoComunsDao.buscaDadosIdServRemVar();
    }

    public List buscaFiltrosCodSubtituloRemVar()
    {
        return relInfoComunsDao.buscaDadosCodSubtituloRemVar();
    }

    public List buscaFiltrosDadosGeraisServRemVar(String idServico, String codSubtitulo)
    {
        return relInfoComunsDao.buscaDadosGeraisServRemVar(idServico, codSubtitulo);
    }
}
