
package br.gov.pbh.desif.dao.impl;

import br.gov.pbh.desif.base.dao.impl.BaseDaoImpl;
import br.gov.pbh.desif.dao.RelatorioDao;
import br.gov.pbh.desif.model.pojo.RelatorioDependenciasVO;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class RelatorioDaoImpl extends BaseDaoImpl
    implements RelatorioDao
{

    public RelatorioDaoImpl()
    {
    }

    public Class getReferenceClass()
    {
        return br.gov.pbh.desif.model.pojo.RelatorioDependenciasVO.class;
    }

    public List buscaDadosRelatorioDependencias()
    {
        List result = null;
        Query q = getConexao().createQuery("SELECT new br.gov.pbh.desif.model.pojo.RelatorioDependenciasVO(decl.cnpjInstituicao, dep.cnpjProprio, dep.codigoDependencia, dep.cnpjUnificado, tipo.descTipoDependencia, dep.contabilidadePropria, cid.nomeCidade, dep.enderecoDependencia, dep.dataInicioParalizacao, dep.dataFimParalizacao) FROM IdentificacaoDeclaracao as decl, IdentificacaoDependencia as dep, TipoDependencia as tipo, Cidade as cid where tipo.id = dep.tipoDependencia and cid.id = dep.cidade");
        result = q.list();
        return result;
    }

    public Object buscaDadosEstaticosRelatorioDependencias()
    {
        Query q = getConexao().createQuery("SELECT new br.gov.pbh.desif.model.pojo.RelatorioEstaticoDependenciaVO(decl.nomeInstituicao, cid.nomeCidade, decl.tipoDeclaracao, decl.dataInicioCompetencia, tit.descTitulo, decl.protocoloDeclaracao) FROM IdentificacaoDeclaracao as decl, Cidade as cid, Titulo as tit where decl.cidade = cid.id and decl.titulo = tit.id");
        return q.uniqueResult();
    }

    public List buscaDadosRelatorioApurIssqn(String codDependencia)
    {
        List result = null;
        Query q;
        if(codDependencia == null)
        {
            q = getConexao().createQuery("SELECT new br.gov.pbh.desif.model.pojo.RelatorioApuracaoIssqnVO(codigoTributacaoDesIf, valorReceitaDeclaradaConsolidada, valorDeducaoReceitaDeclaradaSubtitulo, valorDeducaoReceitaDeclaradaConsolidada, valorBaseCalculo, valorAliquotaIssqn, valorIssqnDevido, valorIssqnRetido, valorIncentivoFiscalSubtitulo, valorIncentivoFiscal, valorCredito, valorIssqnRecolhido, processoMotivoNaoExigibilidade, valorIssqnRecolher) FROM IssqnMensal");
        } else
        {
            q = getConexao().createQuery("SELECT new br.gov.pbh.desif.model.pojo.RelatorioApuracaoIssqnVO(issqn.codigoTributacaoDesIf, issqn.valorReceitaDeclaradaConsolidada, issqn.valorDeducaoReceitaDeclaradaSubtitulo, issqn.valorDeducaoReceitaDeclaradaConsolidada, issqn.valorBaseCalculo, issqn.valorAliquotaIssqn, issqn.valorIssqnDevido, issqn.valorIssqnRetido, issqn.valorIncentivoFiscalSubtitulo, issqn.valorIncentivoFiscal, issqn.valorCredito, issqn.valorIssqnRecolhido, issqn.processoMotivoNaoExigibilidade, issqn.valorIssqnRecolher) FROM IssqnMensal as issqn, ApuracaoReceita as apur WHERE apur.cnpj = issqn.cnpj and apur.codigoDependencia = :codDependencia group by issqn.codigoTributacaoDesIf, issqn.valorReceitaDeclaradaConsolidada, issqn.valorDeducaoReceitaDeclaradaSubtitulo, issqn.valorDeducaoReceitaDeclaradaConsolidada, issqn.valorBaseCalculo, issqn.valorAliquotaIssqn, issqn.valorIssqnDevido, issqn.valorIssqnRetido, issqn.valorIncentivoFiscalSubtitulo, issqn.valorIncentivoFiscal, issqn.valorCredito, issqn.valorIssqnRecolhido, issqn.processoMotivoNaoExigibilidade, issqn.valorIssqnRecolher");
            q.setString("codDependencia", codDependencia);
        }
        result = q.list();
        return result;
    }

    public Object buscaDadosEstaticosRelatorioApurIssqn()
    {
        Query q = getConexao().createQuery("SELECT new br.gov.pbh.desif.model.pojo.RelatorioEstaticoApuracaoIssqnVO(cid.nomeCidade, decl.nomeInstituicao, decl.cnpjInstituicao, issqn.cnpj, decl.dataInicioCompetencia, decl.tipoConsolidacao) FROM IdentificacaoDeclaracao as decl, IdentificacaoDependencia as dep, Cidade as cid, IssqnMensal as issqn where decl.cidade = cid.id and decl.cnpjResponsavelRecolhimento = issqn.cnpj and dep.cnpjUnificado = issqn.cnpj group by cid.nomeCidade, decl.nomeInstituicao, decl.cnpjInstituicao, issqn.cnpj, decl.dataInicioCompetencia, decl.tipoConsolidacao");
        return q.uniqueResult();
    }

    public Object buscaDadosEstaticosRelatorioApurIssqn(String codDependencia)
    {
        Query q = getConexao().createQuery("SELECT new br.gov.pbh.desif.model.pojo.RelatorioEstaticoApuracaoIssqnVO(cid.nomeCidade, decl.nomeInstituicao, decl.cnpjInstituicao, issqn.cnpj, decl.dataInicioCompetencia, decl.tipoConsolidacao) FROM IdentificacaoDeclaracao as decl, Cidade as cid, IssqnMensal as issqn, IdentificacaoDependencia as dep where dep.cidade = cid.id and dep.cnpjUnificado = issqn.cnpj and dep.codigoDependencia = :codDependencia");
        q.setString("codDependencia", codDependencia);
        return q.uniqueResult();
    }

    public List buscaDadosRelatorioApurReceitaSubtitulo(String parametro1, String parametro2)
    {
        List result = null;
        Query q = null;
        if(parametro1 == null && parametro2 == null)
        {
            q = getConexao().createQuery("SELECT new br.gov.pbh.desif.model.pojo.ApuracaoReceita(codigoTributacaoDesIf, codigoSubTitulo, valorCreditoMensal, valorDebitoMensal, valorReceitaDeclarada, valorBaseCalculo, valorAliquotaIssqn, valorDeducaoReceitaDeclarada, valorIncentivoFiscal, descIncentivoFiscal, processoMotivoNaoExigibilidade) FROM ApuracaoReceita");
            result = q.list();
        } else
        if(parametro1 != null && parametro2 != null)
        {
            q = getConexao().createQuery("SELECT new br.gov.pbh.desif.model.pojo.ApuracaoReceita(codigoTributacaoDesIf, codigoSubTitulo, valorCreditoMensal, valorDebitoMensal, valorReceitaDeclarada, valorBaseCalculo, valorAliquotaIssqn, valorDeducaoReceitaDeclarada, valorIncentivoFiscal, descIncentivoFiscal, processoMotivoNaoExigibilidade) FROM ApuracaoReceita WHERE codigoDependencia = :codDepe and valorAliquotaIssqn = :aliquota");
            q.setString("codDepe", parametro1);
            q.setString("aliquota", parametro2);
            result = q.list();
        } else
        {
            q = getConexao().createQuery("SELECT new br.gov.pbh.desif.model.pojo.ApuracaoReceita(codigoTributacaoDesIf, codigoSubTitulo, valorCreditoMensal, valorDebitoMensal, valorReceitaDeclarada, valorBaseCalculo, valorAliquotaIssqn, valorDeducaoReceitaDeclarada, valorIncentivoFiscal, descIncentivoFiscal, processoMotivoNaoExigibilidade) FROM ApuracaoReceita WHERE codigoDependencia = :codDepe order by codigoTributacaoDesIf");
            q.setString("codDepe", parametro1);
            result = q.list();
        }
        return result;
    }

    public Object buscaDadosEstaticosRelatorioApurSubtitulo(String codDependencia)
    {
        Query q = getConexao().createQuery("SELECT new br.gov.pbh.desif.model.pojo.RelatorioEstaticoApuracaoSubtituloVO(cid.nomeCidade, decl.nomeInstituicao, decl.dataInicioCompetencia, decl.cnpjInstituicao, dep.cnpjProprio, dep.enderecoDependencia, dep.cnpjUnificado, dep.codigoDependencia) FROM IdentificacaoDeclaracao as decl, Cidade as cid, ApuracaoReceita as apur, IdentificacaoDependencia as dep where decl.cidade = cid.id and dep.codigoDependencia = apur.codigoDependencia and apur.codigoDependencia = :codDepe group by cid.nomeCidade, decl.nomeInstituicao, decl.dataInicioCompetencia, decl.cnpjInstituicao, dep.cnpjProprio, dep.enderecoDependencia,  dep.cnpjUnificado, apur.codigoDependencia");
        q.setString("codDepe", codDependencia);
        return q.uniqueResult();
    }

    public Object buscaDadosGuia(String cnpj)
    {
        Query q = getConexao().createQuery("SELECT new br.gov.pbh.desif.model.pojo.GuiaEstaticaVO(decl.nomeInstituicao, decl.dataInicioCompetencia, decl.cnpjInstituicao, dep.opcaoInscricaoMunicipal, dep.codigoDependencia, decl.tipoDeclaracao) FROM IdentificacaoDeclaracao as decl, IdentificacaoDependencia as dep WHERE dep.cnpjProprio = :cnpj GROUP BY decl.nomeInstituicao, decl.dataInicioCompetencia, decl.cnpjInstituicao, dep.opcaoInscricaoMunicipal, dep.codigoDependencia, decl.tipoDeclaracao");
        q.setString("cnpj", cnpj);
        Object o = q.uniqueResult();
        return o;
    }

    public List buscaFiltrosApuracaoIssqn()
    {
        List result = null;
        Query q = getConexao().createQuery("SELECT new br.gov.pbh.desif.model.pojo.FiltroCNPJCodDependenciaVO(decl.cnpjInstituicao, apur.codigoDependencia, issqn.cnpj) FROM IdentificacaoDeclaracao decl, ApuracaoReceita as apur, IssqnMensal as issqn WHERE apur.cnpj = issqn.cnpj GROUP BY decl.cnpjInstituicao, apur.codigoDependencia, issqn.cnpj");
        result = q.list();
        return result;
    }

    public List buscaFiltrosApuracaoSubtituloCNPJCodDependencia()
    {
        List result = null;
        Query q = getConexao().createQuery("SELECT new br.gov.pbh.desif.model.pojo.FiltroCNPJCodDependenciaVO(decl.cnpjInstituicao, apur.codigoDependencia, dep.cnpjUnificado) FROM IdentificacaoDeclaracao decl, ApuracaoReceita as apur, IdentificacaoDependencia as dep where dep.codigoDependencia = apur.codigoDependencia GROUP BY decl.cnpjInstituicao, apur.codigoDependencia, dep.cnpjUnificado");
        result = q.list();
        return result;
    }

    public List buscaFiltrosApuracaoSubtituloCodTribAliquota()
    {
        List result = null;
        Query q = getConexao().createQuery("SELECT new br.gov.pbh.desif.model.pojo.FiltroCodTribAliquotaVO(apur.codigoTributacaoDesIf, apur.valorAliquotaIssqn) FROM IdentificacaoDeclaracao decl, ApuracaoReceita as apur GROUP BY apur.codigoTributacaoDesIf, apur.valorAliquotaIssqn");
        result = q.list();
        return result;
    }

    public List buscaFiltrosGuia()
    {
        List result = null;
        Query q = getConexao().createQuery("SELECT new br.gov.pbh.desif.model.pojo.IssqnMensal(cnpj, SUM(valorReceitaDeclaradaConsolidada), SUM(valorBaseCalculo), SUM(valorIssqnDevido), SUM(valorDeducaoReceitaDeclaradaSubtitulo), SUM(valorDeducaoReceitaDeclaradaConsolidada), SUM(valorIssqnRetido), SUM(valorIncentivoFiscalSubtitulo), SUM(valorIncentivoFiscal), SUM(valorIssqnRecolhido), SUM(valorIssqnRecolher),  SUM(valorCredito)) FROM IssqnMensal group by cnpj");
        result = q.list();
        return result;
    }

    public Object buscaTaxaExpediente()
    {
        Query q = getConexao().createQuery("SELECT new br.gov.pbh.desif.model.pojo.TaxasGuias(idnTaxaGuia, valTaxaBanc, desFormMult, desFormJuro) FROM TaxasGuias");
        return q.list().get(0);
    }

    public Integer sequenciaGuia(String sequencia)
    {
        Integer retorno = null;
        Query q = getConexao().createSQLQuery((new StringBuilder()).append("CALL NEXT VALUE FOR ").append(sequencia).toString());
        retorno = (Integer)q.uniqueResult();
        return retorno;
    }
}