
package br.gov.pbh.desif.service.relatorios;

import br.gov.pbh.desif.dao.*;
import br.gov.pbh.desif.service.contexto.Contexto;
import java.util.List;

public class DadosRelatorioContabil
{

    private RelatorioContabilDao relContabDao;
    private IdentDeclaracaoDao declaracaoDao;
    private IdentDependenciaDao dependenciaDao;

    public DadosRelatorioContabil()
    {
        relContabDao = (RelatorioContabilDao)Contexto.getObject("relatorioContabilDao");
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

    public List buscaDadosDependencia(String codDependencia)
    {
        return relContabDao.buscaDadosDependencia(codDependencia);
    }

    public List buscaDadosDeclaracao()
    {
        return relContabDao.buscaDadosDeclaracao();
    }

    public List buscaFiltrosDependenciasBalancAnalit()
    {
        return relContabDao.buscaDadosDependenciasBalancAnalit();
    }

    public List buscaFiltrosContasBalancAnalit()
    {
        return relContabDao.buscaDadosContasBalancAnalit();
    }

    public List buscaFiltrosCompetBalancAnalit()
    {
        return relContabDao.buscaDadosCompetBalancAnalit();
    }

    public List buscaFiltrosDadosGeraisBalancAnalit(String codDependencia, String competencia, String conta)
    {
        return relContabDao.buscaDadosGeraisBalancAnalit(codDependencia, competencia, conta);
    }

    public List buscaFiltrosDependenciasDemRateio()
    {
        return relContabDao.buscaDadosDependenciasDemRateio();
    }

    public List buscaFiltrosCompetDemRateio()
    {
        return relContabDao.buscaDadosCompetDemRateio();
    }

    public List buscaFiltrosValRateioDemRateio()
    {
        return relContabDao.buscaDadosValRateioDemRateio();
    }

    public List buscaFiltrosCodEventoDemRateio()
    {
        return relContabDao.buscaDadosCodEventoDemRateio();
    }

    public List buscaFiltrosDadosGeraisDemRateio(String codDependencia, String competencia, String valRateio, String codEvento)
    {
        return relContabDao.buscaDadosGeraisDemRateio(codDependencia, competencia, valRateio, codEvento);
    }
}
