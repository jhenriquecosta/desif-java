
package br.gov.pbh.desif.service.relatorios;

import br.gov.pbh.desif.dao.*;
import br.gov.pbh.desif.model.pojo.IdentificacaoDeclaracao;
import br.gov.pbh.desif.model.pojo.TaxasGuias;
import br.gov.pbh.desif.service.contexto.Contexto;
import java.util.List;

public class DadosRelatorio
{

    private RelatorioDao relDao;
    private IdentDeclaracaoDao declaracaoDao;
    private IdentDependenciaDao dependenciaDao;

    public DadosRelatorio()
    {
        relDao = (RelatorioDao)Contexto.getObject("relatorioDao");
    }

    public List buscaDadosDinamicosRelatorioDependencias()
    {
        List resp = relDao.buscaDadosRelatorioDependencias();
        return resp;
    }

    public List buscaDadosDinamicosRelatorioApurIssqn(String codDependencia)
    {
        List resp = relDao.buscaDadosRelatorioApurIssqn(codDependencia);
        return resp;
    }

    public List buscaDadosDinamicosRelatorioApurReceitaSubtitulo(String parametro1, String parametro2)
    {
        List resp = relDao.buscaDadosRelatorioApurReceitaSubtitulo(parametro1, parametro2);
        return resp;
    }

    public Object buscaDadosEstaticosRelatorioDependencias()
    {
        Object o = relDao.buscaDadosEstaticosRelatorioDependencias();
        return o;
    }

    public Object buscaDadosEstaticosRelatorioApurIssqn()
    {
        Object o = relDao.buscaDadosEstaticosRelatorioApurIssqn();
        return o;
    }

    public Object buscaDadosEstaticosRelatorioApurIssqn(String codDependencia)
    {
        Object o = relDao.buscaDadosEstaticosRelatorioApurIssqn(codDependencia);
        return o;
    }

    public Object buscaDadosEstaticosRelatorioApurSubtitulo(String codDependencia)
    {
        Object o = relDao.buscaDadosEstaticosRelatorioApurSubtitulo(codDependencia);
        return o;
    }

    public Object buscaDadosGuia(String cnpj)
    {
        Object resp = relDao.buscaDadosGuia(cnpj);
        return resp;
    }

    public Double buscaTaxaExpediente()
    {
        Object resp = relDao.buscaTaxaExpediente();
        return ((TaxasGuias)resp).getValTaxaBanc();
    }

    public Short buscaTipoConsolidacao()
    {
        declaracaoDao = (IdentDeclaracaoDao)Contexto.getObject("declaracaoDao");
        return ((IdentificacaoDeclaracao)declaracaoDao.load(new Integer(Integer.parseInt("1")))).getTipoConsolidacao();
    }

    public String buscaBaseCnpjInstituicao()
    {
        declaracaoDao = (IdentDeclaracaoDao)Contexto.getObject("declaracaoDao");
        return ((IdentificacaoDeclaracao)declaracaoDao.load(new Integer(Integer.parseInt("1")))).getCnpjInstituicao();
    }

    public List buscaFiltrosApuracaoIssqn()
    {
        return relDao.buscaFiltrosApuracaoIssqn();
    }

    public List buscaFiltrosApuracaoSubtituloCNPJCodDependencia()
    {
        return relDao.buscaFiltrosApuracaoSubtituloCNPJCodDependencia();
    }

    public List buscaFiltrosApuracaoSubtituloCodTribAliquota()
    {
        return relDao.buscaFiltrosApuracaoSubtituloCodTribAliquota();
    }

    public List buscaFiltrosGuia()
    {
        return relDao.buscaFiltrosGuia();
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

    public Integer buscaNumeroGuia(String sequencia)
    {
        return relDao.sequenciaGuia(sequencia);
    }
}
