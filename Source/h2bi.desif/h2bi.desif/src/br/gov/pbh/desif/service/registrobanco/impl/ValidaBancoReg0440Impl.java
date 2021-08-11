

package br.gov.pbh.desif.service.registrobanco.impl;

import br.gov.pbh.bhiss.utilitarios.validadores.CpfCnpj;
import br.gov.pbh.desif.dao.*;
import br.gov.pbh.desif.model.pojo.*;
import br.gov.pbh.desif.model.registros.RegUtil;
import br.gov.pbh.desif.service.contexto.Contexto;
import br.gov.pbh.desif.service.registrobanco.ValidaBancoReg0440;
import br.gov.pbh.desif.view.telas.PanGerarDeclaracao;
import java.text.*;
import java.util.*;

public class ValidaBancoReg0440Impl
    implements ValidaBancoReg0440
{

    private IssqnMensalDao demMensalIssqnDao;
    private ApuracaoReceitaDao apuracaoMensalIssqnDao;
    private PanGerarDeclaracao panGD;
    private IssqnMensal demMenIssqn;
    private IdentDeclaracaoDao declaracaoDao;
    private IdentificacaoDeclaracao declaracao;
    private CodTributacaoDesifDao codTribDao;
    private ApuracaoReceita apurSubIssqn;
    private IdentDependenciaDao dependenciaDao;
    private RegUtil regUtil;
    private final String registro = "0440";

    public ValidaBancoReg0440Impl()
        throws Exception
    {
    }

    public void executar()
        throws Exception
    {
        panGD = (PanGerarDeclaracao)Contexto.getObject("panGD");
        regUtil = new RegUtil();
        boolean existe0430 = false;
        declaracao = (IdentificacaoDeclaracao)declaracaoDao.load(new Integer(Integer.parseInt("1")));
        List demMensaisIssqn = demMensalIssqnDao.findAll();
        if(demMensaisIssqn.isEmpty())
        {
            verificaExisteRegistro0440();
            return;
        }
        Iterator i = demMensaisIssqn.iterator();
        verificaErroInexistenciaRegistro0440Consolidacao();
        double incremento = regUtil.incremetoPorcentagem(10D, demMensaisIssqn.size());
        double sentinela = 90D;
        int atualizar = 0;
        while(i.hasNext()) 
        {
            if(sentinela < 100D)
            {
                atualizar = (int)sentinela;
                panGD.atualizarProgressoValidacao(atualizar, 100);
                sentinela += incremento;
            }
            demMenIssqn = (IssqnMensal)i.next();
            List ListApurSubIssqn = apuracaoMensalIssqnDao.verificaExisteCnpj0440(demMenIssqn.getCnpj());
            if(ListApurSubIssqn.size() > 0)
                existe0430 = true;
            calculaConsolidacoes(existe0430);
            verificaCnpj();
            verificaCodigoTributacaoDesif();
            verificaDeducaoReceitaDeclaradaConsolidada(existe0430);
            verificaBaseCalculo(existe0430);
            verificaAliquotaIssqn(existe0430);
            verificaIssqnDevido(existe0430);
            verificaIssqnRetido(existe0430);
            verificaIncentivoFiscal(existe0430);
            verificaValorCreditoCompensar(existe0430);
            verificaOrigemCredACompensar();
            verificaValorIssqnRecolhido(existe0430);
            verificaMotivoNaoExigibilidade(existe0430);
            verificaValorIssqnRecolher(existe0430);
            existe0430 = false;
        }
    }

    public void verificaErroInexistenciaRegistro0440Consolidacao()
    {
        List apuracoesReceitas = apuracaoMensalIssqnDao.buscaRegistros0430ComMovimento();
        Iterator j = apuracoesReceitas.iterator();
        double incremento = regUtil.incremetoPorcentagem(10D, apuracoesReceitas.size());
        double sentinela = 80D;
        int atualizar = 0;
        do
        {
            if(!j.hasNext())
                break;
            if(sentinela < 90D)
            {
                atualizar = (int)sentinela;
                panGD.atualizarProgressoValidacao(atualizar, 100);
                sentinela += incremento;
            }
            apurSubIssqn = (ApuracaoReceita)j.next();
            switch(declaracao.getTipoConsolidacao().intValue())
            {
            case 1: // '\001'
                if(apurSubIssqn != null)
                {
                    List retorno = demMensalIssqnDao.verificaExistenciaRegistro0440Consolidacao1(apurSubIssqn.getValorAliquotaIssqn());
                    if(retorno == null || retorno.size() < 1)
                    {
                        String txtSolucao = (new StringBuilder()).append("Tipo de consolida\347\343o: ").append(declaracao.getTipoConsolidacao()).append("<BR>aliquota: ").append(apurSubIssqn.getValorAliquotaIssqn()).toString();
                        regUtil.setErro(apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EM067", 12, (short)2, "0440", txtSolucao);
                    }
                }
                break;

            case 3: // '\003'
                if(apurSubIssqn != null)
                {
                    List respExiste = demMensalIssqnDao.verificaExistenciaRegistro0440Consolidacao3(apurSubIssqn.getValorAliquotaIssqn().toString(), apurSubIssqn.getCnpj());
                    if(respExiste == null || respExiste.size() < 1)
                    {
                        String txtSolucao = (new StringBuilder()).append("Tipo de consolida\347\343o: ").append(declaracao.getTipoConsolidacao()).append("<BR>c\363digo da depend\352ncia: ").append(apurSubIssqn.getCodigoDependencia()).append("<BR>CNPJ unificador: ").append(apurSubIssqn.getCnpj()).append("<BR>al\355quota: ").append(apurSubIssqn.getValorAliquotaIssqn()).toString();
                        regUtil.setErro(apurSubIssqn.getLinhaApuracaoReceita().longValue(), "EM069", 12, (short)2, "0440", txtSolucao);
                    }
                }
                break;
            }
        } while(true);
    }

    public void calculaConsolidacoes(boolean existe0430)
    {
        switch(declaracao.getTipoConsolidacao().intValue())
        {
        case 2: // '\002'
        case 4: // '\004'
        default:
            break;

        case 1: // '\001'
            List result1 = apuracaoMensalIssqnDao.somaConsolidacao1(demMenIssqn.getValorAliquotaIssqn());
            Object resultados1[] = (Object[])(Object[])result1.get(0);
            if(resultados1[0] != null)
            {
                verificaReceitaDeclaradaConsolidada(new Double(Double.parseDouble(resultados1[0].toString())), existe0430);
                verificaDeducaoReceitaDeclaradaSubTitulo(new Double(Double.parseDouble(resultados1[1].toString())), existe0430);
                verificaIncentivoFiscalSubtitulo(new Double(Double.parseDouble(resultados1[2].toString())), existe0430);
            } else
            {
                verificaReceitaDeclaradaConsolidada(Double.valueOf(0.0D), existe0430);
            }
            break;

        case 3: // '\003'
            List result3 = apuracaoMensalIssqnDao.somaConsolidacao3(demMenIssqn.getValorAliquotaIssqn(), demMenIssqn.getCnpj());
            Object resultados3[] = (Object[])(Object[])result3.get(0);
            if(resultados3[0] != null)
            {
                verificaReceitaDeclaradaConsolidada(new Double(Double.parseDouble(resultados3[0].toString())), existe0430);
                verificaDeducaoReceitaDeclaradaSubTitulo(new Double(Double.parseDouble(resultados3[1].toString())), existe0430);
                verificaIncentivoFiscalSubtitulo(new Double(Double.parseDouble(resultados3[2].toString())), existe0430);
            } else
            {
                verificaReceitaDeclaradaConsolidada(Double.valueOf(0.0D), existe0430);
            }
            break;
        }
    }

    public void verificaExisteRegistro0440()
        throws Exception
    {
        int coluna = 2;
        regUtil.setErro(0L, "EM079", coluna, (short)2, "0440");
    }

    public void verificaCnpj()
        throws Exception
    {
        int coluna = 3;
        if(!CpfCnpj.validarCpfCnpj((new StringBuilder()).append(declaracao.getCnpjInstituicao()).append(demMenIssqn.getCnpj()).toString()))
        {
            String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(declaracao.getCnpjInstituicao()).append(demMenIssqn.getCnpj()).toString();
            regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EG004", coluna, (short)2, "0440", txtSolucao);
        }
        if(declaracao.getTipoConsolidacao().shortValue() != 3)
            if(declaracao.getTipoConsolidacao().shortValue() != 4);
        if((declaracao.getTipoConsolidacao().shortValue() == 1 || declaracao.getTipoConsolidacao().shortValue() == 2) && !demMenIssqn.getCnpj().equals(declaracao.getCnpjResponsavelRecolhimento()))
        {
            String txtSolucao = (new StringBuilder()).append("Tipo de consolida\347\343o: ").append(declaracao.getTipoConsolidacao()).append("<BR>CNPJ respons\341vel pelo recolhimento: ").append(declaracao.getCnpjResponsavelRecolhimento()).append("CNPJ do registro 0440: ").append(demMenIssqn.getCnpj()).toString();
            regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM013", coluna, (short)2, "0440", txtSolucao);
        }
        List resp = dependenciaDao.findField("cnpjUnificado", demMenIssqn.getCnpj());
        if(resp.size() < 1)
        {
            String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(demMenIssqn.getCnpj()).toString();
            regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM037", coluna, (short)2, "0440", txtSolucao);
        }
    }

    public void verificaCodigoTributacaoDesif()
        throws Exception
    {
        int coluna = 4;
        if(declaracao.getTipoConsolidacao().shortValue() == 1 || declaracao.getTipoConsolidacao().shortValue() == 3)
        {
            if(!demMenIssqn.getCodigoTributacaoDesIf().equals(""))
            {
                String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(demMenIssqn.getCnpj()).append("<BR>tipo de consolida\347\343o: ").append(declaracao.getTipoConsolidacao()).append("<BR>c\363digo de tributa\347\343o da DESIF: ").append(demMenIssqn.getCodigoTributacaoDesIf()).toString();
                regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM020", coluna, (short)2, "0440", txtSolucao);
            }
        } else
        if((declaracao.getTipoConsolidacao().shortValue() == 2 || declaracao.getTipoConsolidacao().shortValue() == 4) && demMenIssqn.getCodigoTributacaoDesIf().equals(""))
        {
            String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(demMenIssqn.getCnpj()).append("<BR>tipo consolida\347\343o: ").append(declaracao.getTipoConsolidacao()).toString();
            regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM078", coluna, (short)2, "0440", txtSolucao);
        }
        if(demMenIssqn.getCodigoTributacaoDesIf() == null || demMenIssqn.getCodigoTributacaoDesIf().equals(""))
            return;
        List respTrib = codTribDao.buscaCodTributacaoDesif("id", demMenIssqn.getCodigoTributacaoDesIf());
        if(respTrib.size() < 1)
        {
            String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(demMenIssqn.getCnpj()).append("<BR>c\363digo de tributa\347\343o DES-IF: ").append(demMenIssqn.getCodigoTributacaoDesIf()).toString();
            regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EG011", coluna, (short)2, "0440", txtSolucao);
        }
    }

    public void verificaReceitaDeclaradaConsolidada(Double valorConsolidado, boolean existe0430)
    {
        int coluna = 5;
        if((!existe0430) & (demMenIssqn.getValorReceitaDeclaradaConsolidada().doubleValue() > 0.0D))
            regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM080", coluna, (short)2, "0440");
        if(valorConsolidado.doubleValue() != demMenIssqn.getValorReceitaDeclaradaConsolidada().doubleValue())
            switch(declaracao.getTipoConsolidacao().intValue())
            {
            case 1: // '\001'
                String txtSolucao1 = (new StringBuilder()).append("Al\355quota: ").append(demMenIssqn.getValorAliquotaIssqn()).append("<BR>valor da receita declarada consolidada: ").append(demMenIssqn.getValorReceitaDeclaradaConsolidada()).append("<BR>soma das receitas declaradas dos registros 0430: ").append(valorConsolidado).toString();
                regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM047", coluna, (short)2, "0440", txtSolucao1);
                break;

            case 3: // '\003'
                String txtSolucao3 = (new StringBuilder()).append("CNPJ: ").append(demMenIssqn.getCnpj()).append("<BR>al\355quota: ").append(demMenIssqn.getValorAliquotaIssqn()).append("<BR>valor da receita declarada consolidada : ").append(demMenIssqn.getValorReceitaDeclaradaConsolidada()).append("<BR>soma das receitas declaradas dos registros 0430: ").append(valorConsolidado).toString();
                regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM049", coluna, (short)2, "0440", txtSolucao3);
                break;
            }
    }

    public void verificaDeducaoReceitaDeclaradaSubTitulo(Double valorConsolidado, boolean existe0430)
    {
        int coluna = 6;
        if(!existe0430 && demMenIssqn.getValorDeducaoReceitaDeclaradaSubtitulo() != null && demMenIssqn.getValorDeducaoReceitaDeclaradaSubtitulo().doubleValue() > 0.0D)
        {
            String txtSolucao = (new StringBuilder()).append("CNPJ a que se refere:").append(demMenIssqn.getCnpj()).append(", dedu\347\343o da receita declarada informados: ").append(demMenIssqn.getValorDeducaoReceitaDeclaradaSubtitulo()).toString();
            regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM082", coluna, (short)2, "0440", txtSolucao);
        }
        if(valorConsolidado.doubleValue() != demMenIssqn.getValorDeducaoReceitaDeclaradaSubtitulo().doubleValue())
            switch(declaracao.getTipoConsolidacao().intValue())
            {
            case 1: // '\001'
                String txtSolucao1 = (new StringBuilder()).append("Al\355quota: ").append(demMenIssqn.getValorAliquotaIssqn()).append("<BR>valor da dedu\347\343o por Subt\355tulo: ").append(demMenIssqn.getValorDeducaoReceitaDeclaradaSubtitulo()).append("soma das dedu\347\365es dos registros 0430: ").append(valorConsolidado).toString();
                regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM022", coluna, (short)2, "0440", txtSolucao1);
                break;

            case 3: // '\003'
                String txtSolucao3 = (new StringBuilder()).append("CNPJ: ").append(demMenIssqn.getCnpj()).append("<br>al\355quota: ").append(demMenIssqn.getValorAliquotaIssqn()).append("<BR>valor da dedu\347\343o: ").append(demMenIssqn.getValorDeducaoReceitaDeclaradaSubtitulo()).append("<BR>soma das dedu\347\365es dos registros 0430: ").append(valorConsolidado).toString();
                regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM024", coluna, (short)2, "0440", txtSolucao3);
                break;
            }
    }

    public void verificaDeducaoReceitaDeclaradaConsolidada(boolean existe0430)
        throws Exception
    {
        int coluna = 7;
        if(demMenIssqn.getValorDeducaoReceitaDeclaradaConsolidada().doubleValue() > demMenIssqn.getValorReceitaDeclaradaConsolidada().doubleValue())
        {
            String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(demMenIssqn.getCnpj()).append("<BR>receita declarada consolidada: ").append(demMenIssqn.getValorReceitaDeclaradaConsolidada()).append("<BR>dedu\347\343o da receita por subt\355tulo: ").append(demMenIssqn.getValorDeducaoReceitaDeclaradaSubtitulo()).append("<BR>dedu\347\343o da receita consolidada : ").append(demMenIssqn.getValorDeducaoReceitaDeclaradaConsolidada()).toString();
            regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM051", coluna, (short)2, "0440", txtSolucao);
        }
        if(!existe0430 && demMenIssqn.getValorDeducaoReceitaDeclaradaConsolidada() != null && demMenIssqn.getValorDeducaoReceitaDeclaradaConsolidada().doubleValue() > 0.0D)
        {
            String txtSolucao = (new StringBuilder()).append("CNPJ a que se refere:").append(demMenIssqn.getCnpj()).append(", dedu\347\343o da receita declarada consolidada informados: ").append(demMenIssqn.getValorDeducaoReceitaDeclaradaConsolidada()).toString();
            regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM083", coluna, (short)2, "0440", txtSolucao);
        }
    }

    public void verificaBaseCalculo(boolean existe0430)
        throws Exception
    {
        int coluna = 9;
        if(demMenIssqn.getValorBaseCalculo().doubleValue() < 0.0D)
        {
            String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(demMenIssqn.getCnpj()).append("<BR>base de c\341lculo: ").append(demMenIssqn.getValorBaseCalculo()).toString();
            regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM070", coluna, (short)2, "0440", txtSolucao);
        }
        if(!existe0430 && demMenIssqn.getValorBaseCalculo().doubleValue() != 0.0D)
        {
            String txtSolucao = (new StringBuilder()).append("CNPJ a que se refere:").append(demMenIssqn.getCnpj()).append(", base de c\341lculo informados: ").append(demMenIssqn.getValorBaseCalculo()).toString();
            regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM084", coluna, (short)2, "0440", txtSolucao);
        }
        double calc = demMenIssqn.getValorReceitaDeclaradaConsolidada().doubleValue() - demMenIssqn.getValorDeducaoReceitaDeclaradaConsolidada().doubleValue() - demMenIssqn.getValorDeducaoReceitaDeclaradaSubtitulo().doubleValue();
        calc = regUtil.formataCasasDecimais(calc);
        if(demMenIssqn.getValorBaseCalculo().doubleValue() != calc)
        {
            String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(demMenIssqn.getCnpj()).append("<BR>receita declarada consolidada: ").append(demMenIssqn.getValorReceitaDeclaradaConsolidada()).append("<BR>dedu\347\343o por subt\355tulo: ").append(demMenIssqn.getValorDeducaoReceitaDeclaradaSubtitulo()).append("<BR>dedu\347\343o da receita consolidada: ").append(demMenIssqn.getValorDeducaoReceitaDeclaradaConsolidada()).append("<BR>base de c\341lculo: ").append(demMenIssqn.getValorBaseCalculo()).toString();
            regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM053", coluna, (short)2, "0440", txtSolucao);
        }
    }

    public void verificaAliquotaIssqn(boolean existe0430)
        throws Exception
    {
        int coluna = 10;
        if(demMenIssqn.getValorAliquotaIssqn().doubleValue() < 0.0D)
        {
            String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(demMenIssqn.getCnpj()).append("<BR>aliquota: ").append(demMenIssqn.getValorAliquotaIssqn()).toString();
            regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM075", coluna, (short)2, "0440", txtSolucao);
        }
        switch(declaracao.getTipoConsolidacao().intValue())
        {
        default:
            break;

        case 1: // '\001'
            if(existe0430)
            {
                boolean resp1 = apuracaoMensalIssqnDao.verificaExisteRegistroDependenciaUnificadaConsolidacao1(demMenIssqn.getValorAliquotaIssqn());
                if(!resp1)
                    regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM003", coluna, (short)2, "0440");
            } else
            if(demMenIssqn.getValorAliquotaIssqn().doubleValue() > 0.0D)
                regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM081", coluna, (short)2, "0440");
            List result1 = demMensalIssqnDao.verificaUnicidadeConsolidacao1(demMenIssqn.getValorAliquotaIssqn().toString());
            if(result1.size() > 1)
            {
                String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(demMenIssqn.getCnpj()).toString();
                regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM060", coluna, (short)2, "0440", txtSolucao);
            }
            break;

        case 3: // '\003'
            if(existe0430)
            {
                boolean resp3 = apuracaoMensalIssqnDao.verificaExisteRegistroDependenciaUnificadaConsolidacao3(demMenIssqn.getValorAliquotaIssqn(), demMenIssqn.getCnpj());
                if(!resp3)
                {
                    String txtSolucao = (new StringBuilder()).append("Tipo de Consolida\347\343o: ").append(declaracao.getTipoConsolidacao()).append(" <BR>CNPJ unificador: ").append(demMenIssqn.getCnpj()).append(" <BR>al\355quota: ").append(demMenIssqn.getValorAliquotaIssqn()).toString();
                    regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM001", coluna, (short)2, "0440", txtSolucao);
                }
            } else
            if(demMenIssqn.getValorAliquotaIssqn().doubleValue() > 0.0D)
                regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM081", coluna, (short)2, "0440");
            List result3 = demMensalIssqnDao.verificaUnicidadeConsolidacao3(demMenIssqn.getValorAliquotaIssqn().toString(), demMenIssqn.getCnpj());
            if(result3.size() > 1)
            {
                String txtSolicitacao = (new StringBuilder()).append("CNPJ: ").append(demMenIssqn.getCnpj()).append("<BR>, c\363digo de tributa\347\343o: ").append(demMenIssqn.getCodigoTributacaoDesIf()).append("<BR>al\355quota: ").append(demMenIssqn.getValorAliquotaIssqn()).toString();
                regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM060", coluna, (short)2, "0440", txtSolicitacao);
            }
            break;
        }
    }

    public void verificaIssqnDevido(boolean existe0430)
        throws Exception
    {
        int coluna = 11;
        double val = (demMenIssqn.getValorBaseCalculo().doubleValue() * demMenIssqn.getValorAliquotaIssqn().doubleValue()) / 100D;
        val = regUtil.formataCasasDecimais(val);
        if(val != demMenIssqn.getValorIssqnDevido().doubleValue())
        {
            NumberFormat format = new DecimalFormat("0.00", DecimalFormatSymbols.getInstance(Locale.US));
            String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(demMenIssqn.getCnpj()).append("<BR>base de c\341lculo: ").append(format.format(demMenIssqn.getValorBaseCalculo())).append("<BR>al\355quota ISSQN: ").append(format.format(demMenIssqn.getValorAliquotaIssqn())).append("<BR>ISSQN devido: ").append(format.format(demMenIssqn.getValorIssqnDevido())).toString();
            regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM054", coluna, (short)2, "0440", txtSolucao);
        }
        if(!existe0430 && demMenIssqn.getValorIssqnDevido().doubleValue() != 0.0D)
        {
            String txtSolucao = (new StringBuilder()).append("CNPJ a que se refere: ").append(demMenIssqn.getCnpj()).append(", valor do ISSQN devido informados: ").append(demMenIssqn.getValorIssqnDevido()).toString();
            regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM085", coluna, (short)2, "0440", txtSolucao);
        }
    }

    public void verificaIssqnRetido(boolean existe0430)
        throws Exception
    {
        int coluna = 12;
        if(demMenIssqn.getValorIssqnRetido().doubleValue() > demMenIssqn.getValorIssqnDevido().doubleValue() - demMenIssqn.getValorIncentivoFiscal().doubleValue() - demMenIssqn.getValorIncentivoFiscalSubtitulo().doubleValue())
        {
            String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(demMenIssqn.getCnpj()).append("<BR>ISSQN devido: ").append(demMenIssqn.getValorIssqnDevido()).append("<BR>incentivo por subt\355tulo: ").append(demMenIssqn.getValorIncentivoFiscalSubtitulo()).append("<BR>incentivo: ").append(demMenIssqn.getValorIncentivoFiscal()).append("<BR>ISSQN retido: ").append(demMenIssqn.getValorIssqnRetido()).toString();
            regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM055", coluna, (short)2, "0440", txtSolucao);
        }
        if((!existe0430) & ((demMenIssqn.getValorIssqnRetido().doubleValue() > 0.0D) & (demMenIssqn.getValorIssqnRetido() != null)))
        {
            String txtSolucao = (new StringBuilder()).append("CNPJ a que se refere: ").append(demMenIssqn.getCnpj()).append(", valor de ISSQN retido informados: ").append(demMenIssqn.getValorIssqnRetido()).toString();
            regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM086", coluna, (short)2, "0440", txtSolucao);
        }
    }

    public void verificaIncentivoFiscalSubtitulo(Double valorConsolidado, boolean existe0430)
    {
        int coluna = 13;
        if(!existe0430 && demMenIssqn.getValorIncentivoFiscalSubtitulo() != null && demMenIssqn.getValorIncentivoFiscalSubtitulo().doubleValue() >= 0.0D)
        {
            String txtSolucao = (new StringBuilder()).append("CNPJ a que se refere: ").append(demMenIssqn.getCnpj()).append(", valor de incentivo fiscal por Subt\355tulo informados: ").append(demMenIssqn.getValorIncentivoFiscalSubtitulo()).toString();
            regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM087", coluna, (short)2, "0440", txtSolucao);
        }
        if(regUtil.formataCasasDecimais(valorConsolidado.doubleValue()) != regUtil.formataCasasDecimais(demMenIssqn.getValorIncentivoFiscalSubtitulo().doubleValue()))
            switch(declaracao.getTipoConsolidacao().intValue())
            {
            case 1: // '\001'
                String txtSolucao1 = (new StringBuilder()).append("Al\355quota: ").append(demMenIssqn.getValorAliquotaIssqn()).append(" <BR>valor do incentivo fiscal por Subt\355tulo: ").append(demMenIssqn.getValorIncentivoFiscalSubtitulo()).append("<BR>soma dos incentivos fiscais dos registros 0430: ").append(valorConsolidado).toString();
                regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM005", coluna, (short)2, "0440", txtSolucao1);
                break;

            case 2: // '\002'
                regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM006", coluna, (short)2, "0440");
                break;

            case 3: // '\003'
                String txtSolucao3 = (new StringBuilder()).append("Al\355quota: ").append(demMenIssqn.getValorAliquotaIssqn()).append(" <BR>valor do incentivo fiscal por Subt\355tulo: ").append(demMenIssqn.getValorIncentivoFiscalSubtitulo()).append("<BR>soma dos incentivos fiscais dos registros 0430: ").append(valorConsolidado).toString();
                regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM007", coluna, (short)2, "0440", txtSolucao3);
                break;

            case 4: // '\004'
                regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM008", coluna, (short)2, "0440");
                break;
            }
    }

    public void verificaIncentivoFiscal(boolean existe0430)
        throws Exception
    {
        int coluna = 14;
        if(demMenIssqn.getValorIncentivoFiscal().doubleValue() < 0.0D)
        {
            String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(demMenIssqn.getCnpj()).append("<BR>valor da incentivo fiscal: ").append(demMenIssqn.getValorIncentivoFiscal()).toString();
            regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM077", coluna, (short)2, "0440", txtSolucao);
        }
        if((!existe0430) & ((demMenIssqn.getValorIncentivoFiscal().doubleValue() > 0.0D) & (demMenIssqn.getValorIncentivoFiscal() != null)))
        {
            String txtSolucao = (new StringBuilder()).append("CNPJ a que se refere:").append(demMenIssqn.getCnpj()).append(", valor de incentivo fiscal informados: ").append(demMenIssqn.getValorIncentivoFiscal()).toString();
            regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM088", coluna, (short)2, "0440", txtSolucao);
        }
        if(demMenIssqn.getValorIncentivoFiscal().doubleValue() > demMenIssqn.getValorIssqnDevido().doubleValue())
        {
            String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(demMenIssqn.getCnpj()).append("<BR>ISSQN devido:  ").append(demMenIssqn.getValorIssqnDevido()).append("<BR>incentivo por subt\355tulo: ").append(demMenIssqn.getValorIncentivoFiscalSubtitulo()).append("<BR>incentivo: ").append(demMenIssqn.getValorIncentivoFiscal()).toString();
            regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM056", coluna, (short)2, "0440", txtSolucao);
        }
    }

    public void verificaValorCreditoCompensar(boolean existe0430)
        throws Exception
    {
        int coluna = 16;
        if(demMenIssqn.getValorCredito().doubleValue() > demMenIssqn.getValorIssqnDevido().doubleValue() - (demMenIssqn.getValorIssqnRetido().doubleValue() - demMenIssqn.getValorIncentivoFiscal().doubleValue()))
        {
            String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(demMenIssqn.getCnpj()).append("<BR>Cr\351dito a compensar: ").append(demMenIssqn.getValorCredito()).append("<BR>ISS devido: ").append(demMenIssqn.getValorIssqnDevido()).append("<BR>ISS retido: ").append(demMenIssqn.getValorIssqnRetido()).append("<BR>Incentivo fiscal por subt\355tulo: ").append(demMenIssqn.getValorIncentivoFiscalSubtitulo()).append("<BR>incentivo fiscal: ").append(demMenIssqn.getValorIncentivoFiscal()).toString();
            regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM015", coluna, (short)2, "0440", txtSolucao);
        }
        if(demMenIssqn.getValorCredito().doubleValue() < 0.0D)
        {
            String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(demMenIssqn.getCnpj()).append("<BR>valor do cr\351dito: ").append(demMenIssqn.getValorCredito()).toString();
            regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM058", coluna, (short)2, "0440", txtSolucao);
        }
        if((!existe0430) & ((demMenIssqn.getValorCredito().doubleValue() > 0.0D) & (demMenIssqn.getValorCredito() != null)))
        {
            String txtSolucao = (new StringBuilder()).append("CNPJ a que se refere:").append(demMenIssqn.getCnpj()).append(", valor de cr\351dito a compensar informados: ").append(demMenIssqn.getValorCredito()).toString();
            regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM089", coluna, (short)2, "0440", txtSolucao);
        }
    }

    public void verificaOrigemCredACompensar()
    {
        int coluna = 17;
        OrigemCredCompensarDao origCredDao = (OrigemCredCompensarDao)Contexto.getObject("desCredCompDao");
        Double resp = origCredDao.valorSomatorioOrigemCredito(demMenIssqn.getId());
        if(resp == null)
            resp = Double.valueOf(0.0D);
        if(demMenIssqn.getValorCredito().doubleValue() != resp.doubleValue())
        {
            String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(demMenIssqn.getCnpj()).append("<BR>credito a compensar: ").append(demMenIssqn.getValorCredito()).append("<BR>soma dos valores de origem do credito a compensar: ").append(resp).toString();
            regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM036", coluna, (short)2, "0440", txtSolucao);
        }
        List l = new ArrayList(demMenIssqn.getOrigemCredCompensars());
        if(l != null)
        {
            for(int i = 0; i < l.size(); i++)
            {
                boolean resp1 = declaracaoDao.verificaMaiorDataInicioCompetencia(((OrigemCreditoCompensar)l.get(i)).getDataCompetenciaOrigemCredito());
                if(resp1)
                {
                    String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(demMenIssqn.getCnpj()).append("<BR>al\355quota: ").append(demMenIssqn.getValorAliquotaIssqn()).append("<BR>compet\352ncia da declara\347\343o: ").append(declaracao.getDataInicioCompetencia()).append("<BR>compet\352ncia do cr\351dito a compensar: ").append(((OrigemCreditoCompensar)l.get(i)).getDataCompetenciaOrigemCredito()).toString();
                    regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM011", coluna, (short)2, "0440", txtSolucao);
                }
            }

        }
    }

    public void verificaValorIssqnRecolhido(boolean existe0430)
    {
        int coluna = 18;
        if(demMenIssqn.getValorIssqnRecolhido().doubleValue() < 0.0D)
        {
            String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(demMenIssqn.getCnpj()).append("<BR>al\355quota: ").append(demMenIssqn.getValorAliquotaIssqn()).append(" <BR>valor do ISSQN recolhido: ").append(demMenIssqn.getValorIssqnRecolhido()).toString();
            regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM009", coluna, (short)2, "0440", txtSolucao);
        }
        if((!existe0430) & ((demMenIssqn.getValorIssqnRecolhido().doubleValue() > 0.0D) & (demMenIssqn.getValorIssqnRecolhido() != null)))
        {
            String txtSolucao = (new StringBuilder()).append("CNPJ a que se refere:").append(demMenIssqn.getCnpj()).append(", valor de ISSQN recolhido informados: ").append(demMenIssqn.getValorIssqnRecolhido()).toString();
            regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM090", coluna, (short)2, "0440", txtSolucao);
        }
    }

    public void verificaMotivoNaoExigibilidade(boolean existe0430)
    {
        int coluna = 19;
        if((!existe0430) & (demMenIssqn.getMotivoNaoExigibilidade() != null))
        {
            String txtSolucao = (new StringBuilder()).append("CNPJ a que se refere:").append(demMenIssqn.getCnpj()).append(", motivo de n\343o exigibilidade informados: ").append(demMenIssqn.getMotivoNaoExigibilidade()).toString();
            regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM091", coluna, (short)2, "0440", txtSolucao);
        }
    }

    public void verificaValorIssqnRecolher(boolean existe0430)
        throws Exception
    {
        int coluna = 21;
        double val = demMenIssqn.getValorIssqnDevido().doubleValue() - demMenIssqn.getValorIssqnRetido().doubleValue() - demMenIssqn.getValorIncentivoFiscalSubtitulo().doubleValue() - demMenIssqn.getValorIncentivoFiscal().doubleValue() - demMenIssqn.getValorIssqnRecolhido().doubleValue() - demMenIssqn.getValorCredito().doubleValue();
        if((!existe0430) & ((demMenIssqn.getValorIssqnRecolher().doubleValue() > 0.0D) & (demMenIssqn.getValorIssqnRecolher() != null)))
        {
            String txtSolucao = (new StringBuilder()).append("CNPJ a que se refere:").append(demMenIssqn.getCnpj()).append(", valor de ISSQN a recolher informados: ").append(demMenIssqn.getValorIssqnRecolher()).toString();
            regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM092", coluna, (short)2, "0440", txtSolucao);
        }
        if(val < 0.0D && demMenIssqn.getValorIssqnRecolher().doubleValue() != 0.0D)
        {
            String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(demMenIssqn.getCnpj()).append("<BR>al\355quota ").append(demMenIssqn.getValorAliquotaIssqn()).append("<BR>valor do ISSQN a recolher: ").append(demMenIssqn.getValorIssqnRecolher()).toString();
            regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM012", coluna, (short)2, "0440", txtSolucao);
        }
        if(demMenIssqn.getMotivoNaoExigibilidade() == null)
        {
            val = regUtil.formataCasasDecimais(val);
            if(val >= 0.0D && val != demMenIssqn.getValorIssqnRecolher().doubleValue())
            {
                String txtSolucao = "";
                if(demMenIssqn.getMotivoNaoExigibilidade() != null)
                    txtSolucao = (new StringBuilder()).append("CNPJ: ").append(demMenIssqn.getCnpj()).append("<BR>ISSQN devido: ").append(demMenIssqn.getValorIssqnDevido()).append("<BR>valor retido: ").append(demMenIssqn.getValorIssqnRetido()).append("<BR>incentivo fiscal por subt\355tulo: ").append(demMenIssqn.getValorIncentivoFiscalSubtitulo()).append("<BR>incentivo fiscal: ").append(demMenIssqn.getValorIncentivoFiscal()).append("<BR>cr\351dito a compensar: ").append(demMenIssqn.getValorCredito()).append("<BR>valor recolhido: ").append(demMenIssqn.getValorIssqnRecolhido()).append("<BR>motivo de n\343o exigibilidade: ").append(demMenIssqn.getMotivoNaoExigibilidade()).toString();
                else
                    txtSolucao = (new StringBuilder()).append("CNPJ: ").append(demMenIssqn.getCnpj()).append("<BR>ISSQN devido: ").append(demMenIssqn.getValorIssqnDevido()).append("<BR>valor retido: ").append(demMenIssqn.getValorIssqnRetido()).append("<BR>incentivo fiscal por subt\355tulo: ").append(demMenIssqn.getValorIncentivoFiscalSubtitulo()).append("<BR>incentivo fiscal: ").append(demMenIssqn.getValorIncentivoFiscal()).append("<BR>cr\351dito a compensar: ").append(demMenIssqn.getValorCredito()).append("<BR>valor recolhido: ").append(demMenIssqn.getValorIssqnRecolhido()).toString();
                regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM040", coluna, (short)2, "0440", txtSolucao);
            }
        } else
        if(demMenIssqn.getValorIssqnRecolher().doubleValue() != 0.0D)
        {
            String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(demMenIssqn.getCnpj()).append("<BR>motivo de n\343o exigibilidade: ").append(demMenIssqn.getMotivoNaoExigibilidade()).append("<BR>ISSQN a recolher: ").append(demMenIssqn.getValorIssqnRecolher()).toString();
            regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM043", coluna, (short)2, "0440", txtSolucao);
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
