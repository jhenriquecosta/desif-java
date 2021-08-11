/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.service.relatorios;

import br.gov.pbh.desif.dao.IdentDeclaracaoDao;
import br.gov.pbh.desif.dao.IdentDependenciaDao;
import br.gov.pbh.desif.dao.RelatorioInfoComunsDao;
import br.gov.pbh.desif.service.contexto.Contexto;
import java.io.Serializable;
import java.util.List;

public class DadosRelatorioInfoComuns {
    private RelatorioInfoComunsDao relInfoComunsDao = (RelatorioInfoComunsDao)Contexto.getObject("relatorioICMDao");
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

    public List buscaFiltrosContasICM() {
        return this.relInfoComunsDao.buscaDadosContasICM();
    }

    public List buscaFiltrosCodTributacaoICM() {
        return this.relInfoComunsDao.buscaDadosCodTributacaoICM();
    }

    public List buscaFiltrosContaSuperiorPGCC() {
        return this.relInfoComunsDao.buscaDadosContaSuperiorPGCC();
    }

    public List buscaFiltrosContaCosifPGCC() {
        return this.relInfoComunsDao.buscaDadosContaCosifPGCC();
    }

    public List buscaFiltrosDadosGeraisPGCC(String conta, String contaSuperior, String contaCosif, String codTribDesif) {
        return this.relInfoComunsDao.buscaDadosGeraisPGCC(conta, contaSuperior, contaCosif, codTribDesif);
    }

    public List buscaFiltrosDadosGeraisPGCC2(String codTributacao, String conta) {
        return this.relInfoComunsDao.buscaDadosGeraisPGCC2(codTributacao, conta);
    }

    public List buscaFiltrosIdTarifa() {
        return this.relInfoComunsDao.buscaDadosIdTarifa();
    }

    public List buscaFiltrosCodSubtitulo() {
        return this.relInfoComunsDao.buscaDadosCodSubtitulo();
    }

    public List buscaFiltrosDadosGeraisTarServInstituicao(String idTarifa, String codSubtitulo) {
        return this.relInfoComunsDao.buscaDadosGeraisTarServInstituicao(idTarifa, codSubtitulo);
    }

    public List buscaFiltrosIdServRemVar() {
        return this.relInfoComunsDao.buscaDadosIdServRemVar();
    }

    public List buscaFiltrosCodSubtituloRemVar() {
        return this.relInfoComunsDao.buscaDadosCodSubtituloRemVar();
    }

    public List buscaFiltrosDadosGeraisServRemVar(String idServico, String codSubtitulo) {
        return this.relInfoComunsDao.buscaDadosGeraisServRemVar(idServico, codSubtitulo);
    }
}

