

package br.gov.pbh.desif.service.registrobanco.impl;

import br.gov.pbh.desif.dao.*;
import br.gov.pbh.desif.model.pojo.*;
import br.gov.pbh.desif.model.registros.RegUtil;
import br.gov.pbh.desif.service.contexto.Contexto;
import br.gov.pbh.desif.service.registrobanco.ValidaBancoReg0430;
import br.gov.pbh.desif.view.telas.PanGerarDeclaracao;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;

public class ValidaBancoReg0430Impl
    implements ValidaBancoReg0430
{

    private ApuracaoReceitaDao apuracaoMensalIssqnDao;
    private RegUtil regUtil;
    private PanGerarDeclaracao panGD;
    private ApuracaoReceita apurSubIssqn;
    private IdentDependenciaDao dependenciaDao;
    private CodTributacaoDesifDao codTribDao;
    private CodTribuMunicipalDao codTribMunicDao;
    private IdentificacaoDeclaracao declaracao;
    private IdentDeclaracaoDao declaracaoDao;
    private IssqnMensalDao demMensalIssqnDao;
    private final String registro = "0430";

    public ValidaBancoReg0430Impl()
        throws Exception
    {
    }

    public void executar()
        throws Exception
    {
        regUtil = new RegUtil();
        declaracao = (IdentificacaoDeclaracao)declaracaoDao.load(new Integer(Integer.parseInt("1")));
        panGD = (PanGerarDeclaracao)Contexto.getObject("panGD");
        List apuracoesReceitas = apuracaoMensalIssqnDao.buscaRegistros0430ComMovimento();
        System.out.println((new StringBuilder()).append("tamanho de 0430 => ").append(apuracoesReceitas.size()).toString());
        double incremento = regUtil.incremetoPorcentagem(65D, apuracoesReceitas.size());
        double sentinela = 15D;
        int atualizar = 0;
        for(Iterator i = apuracoesReceitas.iterator(); i.hasNext(); verificaIncentivoFisc())
        {
            if(sentinela < 80D)
            {
                atualizar = (int)sentinela;
                panGD.atualizarProgressoValidacao(atualizar, 100);
                sentinela += incremento;
            }
            apurSubIssqn = (ApuracaoReceita)i.next();
            verificarCodDepe();
            verificarCodTributacaoDesif();
            verificaValCredMens();
            verificaValDebtMens();
            verificaValReceDecl();
            verificaDeduReceDecl();
            verificaBaseCalc();
            verificaAliqIssqn();
        }

    }

    public void verificarCodDepe()
        throws Exception
    {
        int coluna = 3;
        List resp = dependenciaDao.findField("codigoDependencia", apurSubIssqn.getCodigoDependencia());
        if(resp.isEmpty())
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo da depend\352ncia: ").append(apurSubIssqn.getCodigoDependencia()).toString();
            regUtil.setErro(apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EG006", coluna, (short)2, "0430", txtSolucao);
        } else
        if(((IdentificacaoDependencia)resp.get(0)).getContabilidadePropria().intValue() == 2)
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo da depend\352ncia : ").append(apurSubIssqn.getCodigoDependencia()).append("<BR>Contabilidade pr\363pria: ").append(((IdentificacaoDependencia)resp.get(0)).getContabilidadePropria()).toString();
            regUtil.setErro(apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EM019", coluna, (short)2, "0430", txtSolucao);
        }
    }

    public void verificarCodTributacaoDesif()
    {
        int coluna = 5;
        List respTrib = codTribDao.buscaCodTributacaoDesif("id", apurSubIssqn.getCodigoTributacaoDesIf());
        if(respTrib.size() < 1)
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo da depend\352ncia: ").append(apurSubIssqn.getCodigoDependencia()).append("<BR>c\363digo de tributa\347\343o DES-IF: ").append(apurSubIssqn.getCodigoTributacaoDesIf()).toString();
            regUtil.setErro(apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EG011", coluna, (short)2, "0430", txtSolucao);
        }
    }

    public void verificaValCredMens()
        throws Exception
    {
        int coluna = 6;
        if(apurSubIssqn.getValorCreditoMensal().doubleValue() < 0.0D)
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo de depend\352ncia: ").append(apurSubIssqn.getCodigoDependencia()).append("<BR>subt\355tulo: ").append(apurSubIssqn.getCodigoSubTitulo()).append("<BR>total de lan\347amentos a cr\351dito: ").append(apurSubIssqn.getValorCreditoMensal()).toString();
            regUtil.setErro(apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EM026", coluna, (short)2, "0430", txtSolucao);
        }
    }

    public void verificaValDebtMens()
        throws Exception
    {
        int coluna = 7;
        if(apurSubIssqn.getValorDebitoMensal().doubleValue() < 0.0D)
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo de depend\352ncia: ").append(apurSubIssqn.getCodigoDependencia()).append("<BR>subt\355tulo: ").append(apurSubIssqn.getCodigoSubTitulo()).append("<BR>total de lan\347amentos a d\351bito: ").append(apurSubIssqn.getValorDebitoMensal()).toString();
            regUtil.setErro(apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EM027", coluna, (short)2, "0430", txtSolucao);
        }
    }

    public void verificaValReceDecl()
        throws Exception
    {
        int coluna = 8;
        if(apurSubIssqn.getValorReceitaDeclarada().doubleValue() < 0.0D)
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo de depend\352ncia: ").append(apurSubIssqn.getCodigoDependencia()).append("<BR>subt\355tulo: ").append(apurSubIssqn.getCodigoSubTitulo()).append("<BR>receita declarada: ").append(apurSubIssqn.getValorReceitaDeclarada()).toString();
            regUtil.setErro(apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EM021", coluna, (short)2, "0430", txtSolucao);
        }
    }

    public void verificaDeduReceDecl()
        throws Exception
    {
        int coluna = 9;
        if(apurSubIssqn.getValorDeducaoReceitaDeclarada().doubleValue() < 0.0D)
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo de depend\352ncia: ").append(apurSubIssqn.getCodigoDependencia()).append("<BR>subt\355tulo: ").append(apurSubIssqn.getCodigoSubTitulo()).append("<BR>valor da dedu\347\343o da receita declarada: ").append(apurSubIssqn.getValorDeducaoReceitaDeclarada()).toString();
            regUtil.setErro(apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EM064", coluna, (short)2, "0430", txtSolucao);
        }
        if(apurSubIssqn.getValorDeducaoReceitaDeclarada().doubleValue() > apurSubIssqn.getValorReceitaDeclarada().doubleValue())
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo de depend\352ncia: ").append(apurSubIssqn.getCodigoDependencia()).append("<BR>subt\355tulo: ").append(apurSubIssqn.getCodigoSubTitulo()).append("<BR>receita declarada: ").append(apurSubIssqn.getValorReceitaDeclarada()).append("<BR>dedu\347\343o da receita declarada: ").append(apurSubIssqn.getValorDeducaoReceitaDeclarada()).toString();
            regUtil.setErro(apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EM028", coluna, (short)2, "0430", txtSolucao);
        }
    }

    public void verificaBaseCalc()
        throws Exception
    {
        int coluna = 11;
        if(apurSubIssqn.getValorBaseCalculo().doubleValue() < 0.0D)
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo da depend\352ncia: ").append(apurSubIssqn.getCodigoDependencia()).append("<BR>subt\355tulo: ").append(apurSubIssqn.getCodigoSubTitulo()).append("<BR>base de c\341lculo: ").append(apurSubIssqn.getValorBaseCalculo()).toString();
            regUtil.setErro(apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EM032", coluna, (short)2, "0430", txtSolucao);
        }
        Double result = Double.valueOf(apurSubIssqn.getValorReceitaDeclarada().doubleValue() - apurSubIssqn.getValorDeducaoReceitaDeclarada().doubleValue());
        result = Double.valueOf(regUtil.formataCasasDecimais(result.doubleValue()));
        if(result.doubleValue() != apurSubIssqn.getValorBaseCalculo().doubleValue())
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo de depend\352ncia: ").append(apurSubIssqn.getCodigoDependencia()).append("<BR>Subt\355tulo: ").append(apurSubIssqn.getCodigoSubTitulo()).append("<BR>receita declarada: ").append(apurSubIssqn.getValorReceitaDeclarada()).append("<BR>dedu\347\343o da receita: ").append(apurSubIssqn.getValorDeducaoReceitaDeclarada()).append("<BR>de c\341lculo: ").append(apurSubIssqn.getValorBaseCalculo()).toString();
            regUtil.setErro(apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EM033", coluna, (short)2, "0430", txtSolucao);
        }
    }

    public void verificaAliqIssqn()
    {
        int coluna = 12;
        if(apurSubIssqn.getValorAliquotaIssqn().doubleValue() < 0.0D)
        {
            String txtSolucao = (new StringBuilder()).append("Codigo depend\352ncia: ").append(apurSubIssqn.getCodigoDependencia()).append("<BR>al\355quota: ").append(apurSubIssqn.getValorAliquotaIssqn()).toString();
            regUtil.setErro(apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EM074", coluna, (short)2, "0430", txtSolucao);
        }
        boolean resp = apuracaoMensalIssqnDao.verificaUnicidade(apurSubIssqn.getCodigoDependencia(), apurSubIssqn.getCodigoSubTitulo(), apurSubIssqn.getCodigoTributacaoDesIf(), apurSubIssqn.getValorAliquotaIssqn());
        if(resp)
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo de depend\352ncia: ").append(apurSubIssqn.getCodigoDependencia()).append("<BR>Subt\355tulo: ").append(apurSubIssqn.getCodigoSubTitulo()).append("<BR>c\363digo de tributa\347\343o: ").append(apurSubIssqn.getCodigoTributacaoDesIf()).append("<BR>al\355quota: ").append(apurSubIssqn.getValorAliquotaIssqn()).toString();
            regUtil.setErro(apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EM059", coluna, (short)2, "0430", txtSolucao);
        }
        List retorno = codTribMunicDao.buscaCodTribuAliqMunicipal(apurSubIssqn.getCodigoTributacaoDesIf(), new Long(0x2f6598L), apurSubIssqn.getValorAliquotaIssqn(), declaracao.getDataInicioCompetencia());
        if(retorno.size() < 1)
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo de depend\352ncia: ").append(apurSubIssqn.getCodigoDependencia()).append("<BR>m\352s-ano da declara\347\343o: ").append(declaracao.getDataInicioCompetencia()).append("<BR>c\363digo de tributa\347\343o: ").append(apurSubIssqn.getCodigoTributacaoDesIf()).append("<BR>al\355quota: ").append(apurSubIssqn.getValorAliquotaIssqn()).toString();
            regUtil.setErro(apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EM046", coluna, (short)2, "0430", txtSolucao);
        }
    }

    public void verificaIncentivoFisc()
        throws Exception
    {
        int coluna = 13;
        if(apurSubIssqn.getValorIncentivoFiscal().doubleValue() < 0.0D)
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo de depend\352ncia: ").append(apurSubIssqn.getCodigoDependencia()).append("<BR>valor do incentivo fiscal: ").append(apurSubIssqn.getValorIncentivoFiscal()).toString();
            regUtil.setErro(apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EM076", coluna, (short)2, "0430", txtSolucao);
        }
        double issqnDevido = (apurSubIssqn.getValorBaseCalculo().doubleValue() * apurSubIssqn.getValorAliquotaIssqn().doubleValue()) / 100D;
        if(issqnDevido < apurSubIssqn.getValorIncentivoFiscal().doubleValue())
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo de depend\352ncia: ").append(apurSubIssqn.getCodigoDependencia()).append("<BR>subt\355tulo: ").append(apurSubIssqn.getCodigoSubTitulo()).append("<BR>base c\341lculo: ").append(apurSubIssqn.getValorBaseCalculo()).append("<BR>al\355quota: ").append(apurSubIssqn.getValorAliquotaIssqn()).append("<BR>incentivo fiscal: ").append(apurSubIssqn.getValorIncentivoFiscal()).toString();
            regUtil.setErro(apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EM034", coluna, (short)2, "0430", txtSolucao);
        }
    }

    public ApuracaoReceitaDao getApuracaoMensalIssqnDao()
    {
        return apuracaoMensalIssqnDao;
    }

    public void setApuracaoMensalIssqnDao(ApuracaoReceitaDao apuracaoMensalIssqnDao)
    {
        this.apuracaoMensalIssqnDao = apuracaoMensalIssqnDao;
    }

    public CodTributacaoDesifDao getCodTribDao()
    {
        return codTribDao;
    }

    public void setCodTribDao(CodTributacaoDesifDao codTribDao)
    {
        this.codTribDao = codTribDao;
    }

    public CodTribuMunicipalDao getCodTribMunicDao()
    {
        return codTribMunicDao;
    }

    public void setCodTribMunicDao(CodTribuMunicipalDao codTribMunicDao)
    {
        this.codTribMunicDao = codTribMunicDao;
    }

    public IdentDeclaracaoDao getDeclaracaoDao()
    {
        return declaracaoDao;
    }

    public void setDeclaracaoDao(IdentDeclaracaoDao declaracaoDao)
    {
        this.declaracaoDao = declaracaoDao;
    }

    public IssqnMensalDao getDemMensalIssqnDao()
    {
        return demMensalIssqnDao;
    }

    public void setDemMensalIssqnDao(IssqnMensalDao demMensalIssqnDao)
    {
        this.demMensalIssqnDao = demMensalIssqnDao;
    }

    public IdentDependenciaDao getDependenciaDao()
    {
        return dependenciaDao;
    }

    public void setDependenciaDao(IdentDependenciaDao dependenciaDao)
    {
        this.dependenciaDao = dependenciaDao;
    }

    public PanGerarDeclaracao getPanGD()
    {
        return panGD;
    }

    public void setPanGD(PanGerarDeclaracao panGD)
    {
        this.panGD = panGD;
    }
}
