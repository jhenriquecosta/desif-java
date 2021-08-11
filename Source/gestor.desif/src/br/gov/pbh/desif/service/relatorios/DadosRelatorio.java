/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.service.relatorios;

import br.gov.pbh.desif.dao.IdentDeclaracaoDao;
import br.gov.pbh.desif.dao.IdentDependenciaDao;
import br.gov.pbh.desif.dao.RelatorioDao;
import br.gov.pbh.desif.model.pojo.IdentificacaoDeclaracao;
import br.gov.pbh.desif.model.pojo.TaxasGuias;
import br.gov.pbh.desif.service.contexto.Contexto;
import java.io.Serializable;
import java.util.List;

public class DadosRelatorio {
    private RelatorioDao relDao = (RelatorioDao)Contexto.getObject("relatorioDao");
    private IdentDeclaracaoDao declaracaoDao;
    private IdentDependenciaDao dependenciaDao;

    public List buscaDadosDinamicosRelatorioDependencias() {
        List resp = this.relDao.buscaDadosRelatorioDependencias();
        return resp;
    }

    public List buscaDadosDinamicosRelatorioApurIssqn(String codDependencia) {
        List resp = this.relDao.buscaDadosRelatorioApurIssqn(codDependencia);
        return resp;
    }

    public List buscaDadosDinamicosRelatorioApurReceitaSubtitulo(String parametro1, String parametro2) {
        List resp = this.relDao.buscaDadosRelatorioApurReceitaSubtitulo(parametro1, parametro2);
        return resp;
    }

    public Object buscaDadosEstaticosRelatorioDependencias() {
        Object o = this.relDao.buscaDadosEstaticosRelatorioDependencias();
        return o;
    }

    public Object buscaDadosEstaticosRelatorioApurIssqn() {
        Object o = this.relDao.buscaDadosEstaticosRelatorioApurIssqn();
        return o;
    }

    public Object buscaDadosEstaticosRelatorioApurIssqn(String codDependencia) {
        Object o = this.relDao.buscaDadosEstaticosRelatorioApurIssqn(codDependencia);
        return o;
    }

    public Object buscaDadosEstaticosRelatorioApurSubtitulo(String codDependencia) {
        Object o = this.relDao.buscaDadosEstaticosRelatorioApurSubtitulo(codDependencia);
        return o;
    }

    public Object buscaDadosGuia(String cnpj) {
        Object resp = this.relDao.buscaDadosGuia(cnpj);
        return resp;
    }

    public Double buscaTaxaExpediente() {
        Object resp = this.relDao.buscaTaxaExpediente();
        return ((TaxasGuias)resp).getValTaxaBanc();
    }

    public Short buscaTipoConsolidacao() {
        this.declaracaoDao = (IdentDeclaracaoDao)Contexto.getObject("declaracaoDao");
        return ((IdentificacaoDeclaracao)this.declaracaoDao.load(new Integer(Integer.parseInt("1")))).getTipoConsolidacao();
    }

    public String buscaBaseCnpjInstituicao() {
        this.declaracaoDao = (IdentDeclaracaoDao)Contexto.getObject("declaracaoDao");
        return ((IdentificacaoDeclaracao)this.declaracaoDao.load(new Integer(Integer.parseInt("1")))).getCnpjInstituicao();
    }

    public List buscaFiltrosApuracaoIssqn() {
        return this.relDao.buscaFiltrosApuracaoIssqn();
    }

    public List buscaFiltrosApuracaoSubtituloCNPJCodDependencia() {
        return this.relDao.buscaFiltrosApuracaoSubtituloCNPJCodDependencia();
    }

    public List buscaFiltrosApuracaoSubtituloCodTribAliquota() {
        return this.relDao.buscaFiltrosApuracaoSubtituloCodTribAliquota();
    }

    public List buscaFiltrosGuia() {
        return this.relDao.buscaFiltrosGuia();
    }

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

    public Integer buscaNumeroGuia(String sequencia) {
        return this.relDao.sequenciaGuia(sequencia);
    }
}

