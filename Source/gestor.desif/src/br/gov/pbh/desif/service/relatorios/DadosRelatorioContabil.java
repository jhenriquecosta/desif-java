/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.service.relatorios;

import br.gov.pbh.desif.dao.IdentDeclaracaoDao;
import br.gov.pbh.desif.dao.IdentDependenciaDao;
import br.gov.pbh.desif.dao.RelatorioContabilDao;
import br.gov.pbh.desif.service.contexto.Contexto;
import java.io.Serializable;
import java.util.List;

public class DadosRelatorioContabil {
    private RelatorioContabilDao relContabDao = (RelatorioContabilDao)Contexto.getObject("relatorioContabilDao");
    private IdentDeclaracaoDao declaracaoDao;
    private IdentDependenciaDao dependenciaDao;

    public Object buscaDadosIdentificacaoDeclaracao() {
        this.declaracaoDao = (IdentDeclaracaoDao)Contexto.getObject("declaracaoDao");
        Object o = this.declaracaoDao.load(Integer.valueOf(1));
        return o;
    }

    public int buscaCountIdentificacoDependencia() {
        this.dependenciaDao = (IdentDependenciaDao)Contexto.getObject("dependenciaDao");
        List dependencias = this.dependenciaDao.findAll();
        return dependencias.size();
    }

    public List buscaDadosDependencia(String codDependencia) {
        return this.relContabDao.buscaDadosDependencia(codDependencia);
    }

    public List buscaDadosDeclaracao() {
        return this.relContabDao.buscaDadosDeclaracao();
    }

    public List buscaFiltrosDependenciasBalancAnalit() {
        return this.relContabDao.buscaDadosDependenciasBalancAnalit();
    }

    public List buscaFiltrosContasBalancAnalit() {
        return this.relContabDao.buscaDadosContasBalancAnalit();
    }

    public List buscaFiltrosCompetBalancAnalit() {
        return this.relContabDao.buscaDadosCompetBalancAnalit();
    }

    public List buscaFiltrosDadosGeraisBalancAnalit(String codDependencia, String competencia, String conta) {
        return this.relContabDao.buscaDadosGeraisBalancAnalit(codDependencia, competencia, conta);
    }

    public List buscaFiltrosDependenciasDemRateio() {
        return this.relContabDao.buscaDadosDependenciasDemRateio();
    }

    public List buscaFiltrosCompetDemRateio() {
        return this.relContabDao.buscaDadosCompetDemRateio();
    }

    public List buscaFiltrosValRateioDemRateio() {
        return this.relContabDao.buscaDadosValRateioDemRateio();
    }

    public List buscaFiltrosCodEventoDemRateio() {
        return this.relContabDao.buscaDadosCodEventoDemRateio();
    }

    public List buscaFiltrosDadosGeraisDemRateio(String codDependencia, String competencia, String valRateio, String codEvento) {
        return this.relContabDao.buscaDadosGeraisDemRateio(codDependencia, competencia, valRateio, codEvento);
    }
}

