
package br.gov.pbh.desif.service.registrobanco.impl;

import br.gov.pbh.desif.dao.*;
import br.gov.pbh.desif.model.pojo.IdentificacaoDeclaracao;
import br.gov.pbh.desif.model.registros.Data;
import br.gov.pbh.desif.model.registros.RegUtil;
import br.gov.pbh.desif.service.contexto.Contexto;
import br.gov.pbh.desif.service.registrobanco.ValidaBancoReg0410;
import br.gov.pbh.desif.view.telas.PanGerarDeclaracao;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class ValidaBancoReg0410Impl
    implements ValidaBancoReg0410
{

    private BalanceteAnaliticoMensalDao balancAnalitMensalDao;
    private RegUtil regUtil;
    private String registro;
    private Data dt;
    private IdentificacaoDeclaracao declaracao;
    private IdentDeclaracaoDao declaracaoDao;
    private PanGerarDeclaracao panGD;
    private IdentDependenciaDao dependenciaDao;

    public ValidaBancoReg0410Impl()
    {
        registro = "0410";
    }

    public void executar()
        throws Exception
    {
        regUtil = new RegUtil();
        dt = new Data();
        declaracao = (IdentificacaoDeclaracao)declaracaoDao.load(new Integer(Integer.parseInt("1")));
        execucaoGeralMetodos();
    }

    public void mesesDuplicados(ArrayList mesesDuplicados, ArrayList listaCombinadaOrg, List listaDependencia, int k, List listaConta, int j)
        throws NumberFormatException
    {
        for(int x = 0; x < mesesDuplicados.size(); x++)
        {
            ArrayList lstInformacoesRegistroDuplic = localizarInfoRegistro(listaCombinadaOrg, listaDependencia.get(k).toString(), ((Integer)mesesDuplicados.get(x)).intValue());
            String txtSolucao = (new StringBuilder()).append("C\363digo da depend\352ncia: ").append(listaDependencia.get(k)).append(", Compet\352ncia: ").append(((ArrayList)lstInformacoesRegistroDuplic.get(0)).get(3).toString()).append(", Conta: ").append(listaConta.get(j)).toString();
            regUtil.setErro(Long.parseLong(((ArrayList)lstInformacoesRegistroDuplic.get(0)).get(4).toString()), "EC013", 5, (short)2, registro, txtSolucao);
            lstInformacoesRegistroDuplic = null;
        }

    }

    public void verificaContabilidadePropria(ArrayList lstDependencias, ArrayList lstInfoRegAtual)
        throws Exception
    {
        int coluna = 3;
        Long numLinha = new Long(((ArrayList)lstInfoRegAtual.get(0)).get(4).toString());
        String dependenciaAtual = ((ArrayList)lstInfoRegAtual.get(0)).get(0).toString();
        for(int i = 0; i < ((ArrayList)lstDependencias.get(1)).size(); i++)
        {
            if(!((ArrayList)lstDependencias.get(0)).get(i).toString().equals(dependenciaAtual))
                continue;
            Short contabPropria = Short.valueOf(Short.parseShort(((ArrayList)lstDependencias.get(1)).get(i).toString()));
            if(contabPropria.shortValue() == 2)
            {
                String txtSolucao = (new StringBuilder()).append("C\363digo da depend\352ncia: ").append(dependenciaAtual).append(", identificador de contabilidade pr\363pria: ").append(contabPropria).toString();
                regUtil.setErro(numLinha.longValue(), "EC001", coluna, (short)2, registro, txtSolucao);
            }
        }

        lstDependencias = null;
        lstInfoRegAtual = null;
    }

    public void verificarCodigoDependencia(ArrayList lstDependencias, ArrayList lstInfoRegAtual)
        throws Exception
    {
        int coluna = 3;
        Long numLinha = new Long(((ArrayList)lstInfoRegAtual.get(0)).get(4).toString());
        String dependenciaAtual = ((ArrayList)lstInfoRegAtual.get(0)).get(0).toString();
        boolean localizado = false;
        int i = 0;
        do
        {
            if(i >= ((ArrayList)lstDependencias.get(0)).size())
                break;
            if(((ArrayList)lstDependencias.get(0)).get(i).toString().equals(dependenciaAtual))
            {
                localizado = true;
                break;
            }
            i++;
        } while(true);
        if(!localizado)
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo da depend\352ncia: ").append(dependenciaAtual).toString();
            regUtil.setErro(numLinha.longValue(), "EG006", coluna, (short)2, registro, txtSolucao);
        }
        lstDependencias = null;
        lstInfoRegAtual = null;
    }

    public void verificaPeriodoCompetencia(ArrayList lstInfoRegAtual, String dtIniCompDecl, String dtFimCompDecl)
        throws Exception
    {
        int coluna = 4;
        Data dtPeriodoCompet = new Data();
        Long numLinha = new Long(((ArrayList)lstInfoRegAtual.get(0)).get(4).toString());
        String dependenciaReg = ((ArrayList)lstInfoRegAtual.get(0)).get(0).toString();
        String anoMesCompetencia = ((ArrayList)lstInfoRegAtual.get(0)).get(3).toString();
        Boolean dentroPeriodoCompetenciaInicial = Boolean.valueOf(dtPeriodoCompet.comparaDataMaior(anoMesCompetencia, dtIniCompDecl, "yyyyMMdd"));
        if(!dentroPeriodoCompetenciaInicial.booleanValue())
            dentroPeriodoCompetenciaInicial = Boolean.valueOf(dtPeriodoCompet.validaIgualdadeEntreData(anoMesCompetencia, dtIniCompDecl, "yyyyMMdd", 3));
        Boolean dentroPeriodoCompetenciaFinal = Boolean.valueOf(dtPeriodoCompet.comparaDataMaior(dtFimCompDecl, anoMesCompetencia, "yyyyMMdd"));
        if(!dentroPeriodoCompetenciaFinal.booleanValue())
            dentroPeriodoCompetenciaFinal = Boolean.valueOf(dtPeriodoCompet.validaIgualdadeEntreData(anoMesCompetencia, dtFimCompDecl, "yyyyMMdd", 3));
        if(!dentroPeriodoCompetenciaInicial.booleanValue() || !dentroPeriodoCompetenciaFinal.booleanValue())
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo da depend\352ncia: ").append(dependenciaReg).append(", Compet\352ncia da declara\347\343o: de ").append(dtIniCompDecl).append(" a ").append(dtFimCompDecl).append(", Compet\352ncia informada: ").append(anoMesCompetencia).toString();
            regUtil.setErro(numLinha.longValue(), "EC014", coluna, (short)2, registro, txtSolucao);
        }
        lstInfoRegAtual = null;
    }

    public void verificaSaldoFinal(ArrayList lstInfoRegAtual, String contaAtual)
        throws Exception
    {
        int coluna = 9;
        String conta = contaAtual;
        Long numLinha = new Long(((ArrayList)lstInfoRegAtual.get(0)).get(4).toString());
        String dependenciaReg = ((ArrayList)lstInfoRegAtual.get(0)).get(0).toString();
        String anoMesCompetencia = ((ArrayList)lstInfoRegAtual.get(0)).get(3).toString();
        BigDecimal saldoFinal = new BigDecimal(((ArrayList)lstInfoRegAtual.get(0)).get(2).toString());
        BigDecimal saldoInicial = new BigDecimal(((ArrayList)lstInfoRegAtual.get(0)).get(5).toString());
        BigDecimal credito = new BigDecimal(((ArrayList)lstInfoRegAtual.get(0)).get(6).toString());
        BigDecimal debito = new BigDecimal(((ArrayList)lstInfoRegAtual.get(0)).get(7).toString());
        saldoInicial = saldoInicial.add(credito);
        saldoInicial = saldoInicial.subtract(debito);
        if(saldoFinal.compareTo(saldoInicial) != 0)
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo da Depend\352ncia: ").append(dependenciaReg).append(", Conta: ").append(conta).append(", Compet\352ncia: ").append(anoMesCompetencia).append(", Saldo inicial:").append(((ArrayList)lstInfoRegAtual.get(0)).get(5).toString()).append(", Valor do cr\351dito:").append(((ArrayList)lstInfoRegAtual.get(0)).get(6).toString()).append(", Valor do d\351bito:").append(((ArrayList)lstInfoRegAtual.get(0)).get(7).toString()).append(", Saldo final:").append(((ArrayList)lstInfoRegAtual.get(0)).get(2).toString()).toString();
            regUtil.setErro(numLinha.longValue(), "EC011", coluna, (short)2, registro, txtSolucao);
        }
        lstInfoRegAtual = null;
    }

    public void verificaUltimaOcorrenciaConta(ArrayList lstInfoRegistro, String contaAtual, boolean ultimaOcorrenciaAtual)
        throws Exception
    {
        int coluna = 9;
        int mes = Integer.parseInt(((ArrayList)lstInfoRegistro.get(0)).get(1).toString());
        String conta = contaAtual;
        boolean ultimaOcorrencia = ultimaOcorrenciaAtual;
        double saldoFinal = (new Double(((ArrayList)lstInfoRegistro.get(0)).get(2).toString())).doubleValue();
        Long numLinha = new Long(((ArrayList)lstInfoRegistro.get(0)).get(4).toString());
        String dependenciaReg = ((ArrayList)lstInfoRegistro.get(0)).get(0).toString();
        String anoMesCompetencia = ((ArrayList)lstInfoRegistro.get(0)).get(3).toString();
        Date fimCompetencia = declaracao.getDataFimCompetencia();
        Calendar dataFimCompetencia = GregorianCalendar.getInstance();
        dataFimCompetencia.setTime(fimCompetencia);
        int mesFimPeriodoCompetenciaDecl = dataFimCompetencia.get(2) + 1;
        if(mes != mesFimPeriodoCompetenciaDecl && saldoFinal > 0.0D && mes != 6 && mes != 12 && ultimaOcorrencia)
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo da depend\352ncia: ").append(dependenciaReg).append(", Conta: ").append(conta).append(", Compet\352ncia: ").append(anoMesCompetencia).append(", Saldo Final da Compet\352ncia informado: ").append(saldoFinal).toString();
            regUtil.setErro(numLinha.longValue(), "EC017", coluna, (short)2, registro, txtSolucao);
        }
        lstInfoRegistro = null;
    }

    public Double verificaSaldoCompetenciaAnterior(ArrayList listaMesesAtual, ArrayList listaCombinadaOrg, ArrayList lstInfoRegistro, String dependenciaAtual)
    {
        int mes = Integer.parseInt(((ArrayList)lstInfoRegistro.get(0)).get(1).toString());
        int mesInicialSemestre = 0;
        if(mes > 0 && mes <= 6)
            mesInicialSemestre = encontraPrimeiroMesSemestre(listaMesesAtual, 1);
        else
        if(mes > 6 && mes <= 12)
            mesInicialSemestre = encontraPrimeiroMesSemestre(listaMesesAtual, 2);
        double saldoFinalCompetAnterior = (new Double("0.0")).doubleValue();
        if(mes != mesInicialSemestre && mes != 1 && mes != 7)
        {
            lstInfoRegistro = localizarInfoRegistro(listaCombinadaOrg, dependenciaAtual, mes - 1);
            saldoFinalCompetAnterior = (new Double(((ArrayList)lstInfoRegistro.get(0)).get(2).toString())).doubleValue();
        }
        return Double.valueOf(saldoFinalCompetAnterior);
    }

    public void validacoesSaldoInicial(ArrayList lstInfoRegistro, ArrayList listaMesesAtual, ArrayList listaCombinadaOrg, String contaAtual)
        throws Exception
    {
        int coluna = 6;
        int mes = Integer.parseInt(((ArrayList)lstInfoRegistro.get(0)).get(1).toString());
        double saldoInicial = (new Double(((ArrayList)lstInfoRegistro.get(0)).get(5).toString())).doubleValue();
        String dependenciaReg = ((ArrayList)lstInfoRegistro.get(0)).get(0).toString();
        double saldoFinalCompetAnterior = verificaSaldoCompetenciaAnterior(listaMesesAtual, listaCombinadaOrg, lstInfoRegistro, dependenciaReg).doubleValue();
        Long numLinha = new Long(((ArrayList)lstInfoRegistro.get(0)).get(4).toString());
        String conta = contaAtual;
        String anoMesCompetencia = ((ArrayList)lstInfoRegistro.get(0)).get(3).toString();
        Boolean zeroPrimOcorContaSemestre = verificaZeroPrimeiraOcorrenciaConta(listaMesesAtual, listaCombinadaOrg, contaAtual, dependenciaReg);
        if((mes == 1 || mes == 7) && !zeroPrimOcorContaSemestre.booleanValue())
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo da depend\352ncia: ").append(dependenciaReg).append(", Conta: ").append(conta).append(", Competencia: ").append(anoMesCompetencia).append(", Saldo Inicial: ").append(saldoInicial).toString();
            regUtil.setErro(numLinha.longValue(), "EC005", coluna, (short)2, registro, txtSolucao);
        } else
        if((mes == 1 || mes == 7) && !comparaDouble(saldoInicial, 0.0D))
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo da depend\352ncia: ").append(dependenciaReg).append(", Conta: ").append(conta).append(", Competencia: ").append(anoMesCompetencia).append(", Saldo Inicial: ").append(saldoInicial).toString();
            regUtil.setErro(numLinha.longValue(), "EC005", coluna, (short)2, registro, txtSolucao);
        } else
        if((mes != 1 || mes != 7) && !zeroPrimOcorContaSemestre.booleanValue())
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo da depend\352ncia: ").append(dependenciaReg).append(", Conta: ").append(conta).append(", Competencia: ").append(anoMesCompetencia).append(", Saldo Inicial: ").append(saldoInicial).toString();
            regUtil.setErro(numLinha.longValue(), "EC005", coluna, (short)2, registro, txtSolucao);
        } else
        if((mes != 1 || mes != 7) && !comparaDouble(saldoInicial, saldoFinalCompetAnterior))
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo da depend\352ncia: ").append(dependenciaReg).append(", Competencia: ").append(anoMesCompetencia).append(", Saldo Inicial: ").append(saldoInicial).append(" e Saldo Final Compet\352ncia Anterior: ").append(saldoFinalCompetAnterior).toString();
            regUtil.setErro(numLinha.longValue(), "EC004", coluna, (short)2, registro, txtSolucao);
        }
        lstInfoRegistro = null;
    }

    public void execucaoGeralMetodos()
        throws Exception
    {
        panGD = (PanGerarDeclaracao)Contexto.getObject("panGD");
        double incremento = 0.0D;
        double sentinela = 15D;
        int atualizar = 0;
        List listaConta = balancAnalitMensalDao.buscarListaConta();
        ArrayList listaTabelaIdentDependencia = montarListaDependencia(dependenciaDao.buscaCodigoDependencia());
        List lstInicioFimDeclaracao = declaracaoDao.buscaInicioFimCompetencia();
        Data dtDeclCompet = new Data();
        Object o[] = (Object[])(Object[])lstInicioFimDeclaracao.get(0);
        String iniCompetDecl = dtDeclCompet.formataData((Date)o[0], "yyyyMMdd");
        String fimCompetDecl = dtDeclCompet.formataData((Date)o[1], "yyyyMMdd");
        String dataCompet[] = dtDeclCompet.separaData(fimCompetDecl);
        int mesFimPeriodoCompetenciaDecl = Integer.parseInt(dataCompet[1]);
        for(int j = 0; j < listaConta.size(); j++)
        {
            incremento = regUtil.incremetoPorcentagem(65D, listaConta.size());
            if(sentinela < 80D)
            {
                atualizar = (int)sentinela;
                panGD.atualizarProgressoValidacao(atualizar, 100);
                sentinela += incremento;
            }
            List listaCombinada = balancAnalitMensalDao.BuscarContaDependenciaMesCombinado(listaConta.get(j).toString());
            ArrayList listaCombinadaOrganizada = montarListaCombinada(listaCombinada);
            List listaDependencia = organizarListaDependencia(listaCombinada);
label0:
            for(int k = 0; k < listaDependencia.size(); k++)
            {
                ArrayList listaMeses = buscarMesesPorDependencia(listaCombinada, listaDependencia.get(k).toString());
                listaMeses = organizaListaMeses(listaMeses);
                boolean gerouErro = verificaOcorrenciaContinuaSemestre(listaCombinadaOrganizada, listaMeses, mesFimPeriodoCompetenciaDecl, listaConta.get(j).toString(), listaDependencia.get(k).toString()).booleanValue();
                ArrayList mesesDuplicados = verificarDuplicidade(listaMeses);
                if(!mesesDuplicados.isEmpty() && !gerouErro)
                {
                    mesesDuplicados(mesesDuplicados, listaCombinadaOrganizada, listaDependencia, k, listaConta, j);
                    continue;
                }
                ArrayList lstInformacoesRegistro = new ArrayList();
                int semestre = 0;
                do
                {
                    if(semestre >= listaMeses.size())
                        continue label0;
                    String conta = listaConta.get(j).toString();
                    lstInformacoesRegistro = localizarInfoRegistro(listaCombinadaOrganizada, listaDependencia.get(k).toString(), ((Integer)listaMeses.get(semestre)).intValue());
                    ArrayList lstInformacoesRegistroTemp = localizarInfoRegistro(listaCombinadaOrganizada, listaDependencia.get(k).toString(), ((Integer)listaMeses.get(semestre)).intValue() + 1);
                    Boolean ultimaOcConta;
                    if(lstInformacoesRegistroTemp.isEmpty())
                        ultimaOcConta = Boolean.valueOf(true);
                    else
                        ultimaOcConta = Boolean.valueOf(false);
                    verificaUltimaOcorrenciaConta(lstInformacoesRegistro, conta, ultimaOcConta.booleanValue());
                    verificarCodigoDependencia(listaTabelaIdentDependencia, lstInformacoesRegistro);
                    verificaContabilidadePropria(listaTabelaIdentDependencia, lstInformacoesRegistro);
                    verificaPeriodoCompetencia(lstInformacoesRegistro, iniCompetDecl, fimCompetDecl);
                    verificaSaldoFinal(lstInformacoesRegistro, conta);
                    validacoesSaldoInicial(lstInformacoesRegistro, listaMeses, listaCombinadaOrganizada, conta);
                    semestre++;
                } while(true);
            }

        }

    }

    public Boolean verificaOcorrenciaContinuaSemestre(ArrayList listaCombinadaOrg, ArrayList lstMeses, int mesFimPerCompetDecl, String conta, String dependencia)
    {
        ArrayList lstInformacoesRegistroTemp = new ArrayList();
        boolean gerouErro = false;
        boolean existemMesesPrimSemestre = existeMesNoSemestre(lstMeses, 1).booleanValue();
        boolean existemMesesSegSemestre = existeMesNoSemestre(lstMeses, 2).booleanValue();
        int fimPrimSemestre = 0;
        int fimSegSemestre = 0;
        if(existemMesesPrimSemestre)
        {
            fimPrimSemestre = buscarMesFimSemestre(lstMeses, 1);
            if(fimPrimSemestre != 6 && fimPrimSemestre != mesFimPerCompetDecl)
            {
                lstInformacoesRegistroTemp = localizarInfoRegistro(listaCombinadaOrg, dependencia, fimPrimSemestre);
                Long numLinha = new Long(((ArrayList)lstInformacoesRegistroTemp.get(0)).get(4).toString());
                String anoMesCompetencia = ((ArrayList)lstInformacoesRegistroTemp.get(0)).get(3).toString();
                double saldoFinal = (new Double(((ArrayList)lstInformacoesRegistroTemp.get(0)).get(2).toString())).doubleValue();
                String txtSolucao = (new StringBuilder()).append("C\363digo da depend\352ncia: ").append(dependencia).append(", Conta: ").append(conta).append(", Compet\352ncia: ").append(anoMesCompetencia).append(", Saldo Final da Compet\352ncia informado: ").append(saldoFinal).toString();
                regUtil.setErro(numLinha.longValue(), "EC017", 9, (short)2, registro, txtSolucao);
                lstInformacoesRegistroTemp = null;
                gerouErro = true;
            }
        }
        if(existemMesesSegSemestre)
        {
            fimSegSemestre = buscarMesFimSemestre(lstMeses, 2);
            if(fimSegSemestre != 6 && fimSegSemestre != mesFimPerCompetDecl)
            {
                lstInformacoesRegistroTemp = localizarInfoRegistro(listaCombinadaOrg, dependencia, fimSegSemestre);
                Long numLinha = new Long(((ArrayList)lstInformacoesRegistroTemp.get(0)).get(4).toString());
                String anoMesCompetencia = ((ArrayList)lstInformacoesRegistroTemp.get(0)).get(3).toString();
                double saldoFinal = (new Double(((ArrayList)lstInformacoesRegistroTemp.get(0)).get(2).toString())).doubleValue();
                String txtSolucao = (new StringBuilder()).append("C\363digo da depend\352ncia: ").append(dependencia).append(", Conta: ").append(conta).append(", Compet\352ncia: ").append(anoMesCompetencia).append(", Saldo Final da Compet\352ncia informado: ").append(saldoFinal).toString();
                regUtil.setErro(numLinha.longValue(), "EC017", 9, (short)2, registro, txtSolucao);
                lstInformacoesRegistroTemp = null;
                gerouErro = true;
            }
        }
        int posicaoFimPrimSemestre = buscaPosicaoMesSemestre(lstMeses, fimPrimSemestre);
        int posicaoFimSegSemestre = buscaPosicaoMesSemestre(lstMeses, fimSegSemestre);
        if(existemMesesPrimSemestre)
        {
            for(int n = 0; n < posicaoFimPrimSemestre; n++)
                if(n != posicaoFimPrimSemestre && ((Integer)lstMeses.get(n)).intValue() + 1 != ((Integer)lstMeses.get(n + 1)).intValue())
                {
                    lstInformacoesRegistroTemp = localizarInfoRegistro(listaCombinadaOrg, dependencia, ((Integer)lstMeses.get(n)).intValue());
                    Long numLinha = new Long(((ArrayList)lstInformacoesRegistroTemp.get(0)).get(4).toString());
                    String anoMesCompetencia = ((ArrayList)lstInformacoesRegistroTemp.get(0)).get(3).toString();
                    double saldoFinal = (new Double(((ArrayList)lstInformacoesRegistroTemp.get(0)).get(2).toString())).doubleValue();
                    String txtSolucao = (new StringBuilder()).append("C\363digo da depend\352ncia: ").append(dependencia).append(", Conta: ").append(conta).append(", Compet\352ncia: ").append(anoMesCompetencia).append(", Saldo Final da Compet\352ncia informado: ").append(saldoFinal).toString();
                    regUtil.setErro(numLinha.longValue(), "EC017", 9, (short)2, registro, txtSolucao);
                    lstInformacoesRegistroTemp = null;
                    gerouErro = true;
                }

        }
        if(existemMesesSegSemestre)
        {
            for(int n = posicaoFimPrimSemestre + 1; n < posicaoFimSegSemestre; n++)
                if(((Integer)lstMeses.get(n)).intValue() + 1 != ((Integer)lstMeses.get(n + 1)).intValue())
                {
                    lstInformacoesRegistroTemp = localizarInfoRegistro(listaCombinadaOrg, dependencia, ((Integer)lstMeses.get(n)).intValue());
                    Long numLinha = new Long(((ArrayList)lstInformacoesRegistroTemp.get(0)).get(4).toString());
                    String anoMesCompetencia = ((ArrayList)lstInformacoesRegistroTemp.get(0)).get(3).toString();
                    double saldoFinal = (new Double(((ArrayList)lstInformacoesRegistroTemp.get(0)).get(2).toString())).doubleValue();
                    String txtSolucao = (new StringBuilder()).append("C\363digo da depend\352ncia: ").append(dependencia).append(", Conta: ").append(conta).append(", Compet\352ncia: ").append(anoMesCompetencia).append(", Saldo Final da Compet\352ncia informado: ").append(anoMesCompetencia).toString();
                    regUtil.setErro(numLinha.longValue(), "EC017", 9, (short)2, registro, txtSolucao);
                    lstInformacoesRegistroTemp = null;
                    gerouErro = true;
                }

        }
        return Boolean.valueOf(gerouErro);
    }

    public int buscaPosicaoMesSemestre(ArrayList lstMeses, int mes)
    {
        int posicaoFimSemestre = -1;
        int i = 0;
        do
        {
            if(i >= lstMeses.size())
                break;
            if(((Integer)lstMeses.get(i)).intValue() == mes)
            {
                posicaoFimSemestre = i;
                break;
            }
            i++;
        } while(true);
        lstMeses = null;
        return posicaoFimSemestre;
    }

    public Boolean existeMesNoSemestre(ArrayList lstMeses, int semestre)
    {
        boolean existeMesSemestre = false;
        int ini = 0;
        int fim = 0;
        if(semestre == 1)
        {
            ini = 1;
            fim = 6;
        } else
        if(semestre == 2)
        {
            ini = 7;
            fim = 12;
        }
        int i = ini;
        do
        {
            if(i > fim)
                break;
            if(lstMeses.contains(Integer.valueOf(i)))
            {
                existeMesSemestre = true;
                break;
            }
            i++;
        } while(true);
        lstMeses = null;
        return Boolean.valueOf(existeMesSemestre);
    }

    public Boolean verificaZeroPrimeiraOcorrenciaConta(ArrayList listaMesesAtual, ArrayList listaCombinadaOrg, String contaAtual, String dependenciaAtual)
    {
        boolean zeroPrimOcConta = false;
        ArrayList lstInformacoesRegAtual = new ArrayList();
        for(int semestre = 0; semestre < listaMesesAtual.size(); semestre++)
        {
            if(((Integer)listaMesesAtual.get(semestre)).intValue() > 1 && ((Integer)listaMesesAtual.get(semestre)).intValue() <= 6)
            {
                lstInformacoesRegAtual = localizarInfoRegistro(listaCombinadaOrg, dependenciaAtual, encontraPrimeiroMesSemestre(listaMesesAtual, 1));
                if(((ArrayList)lstInformacoesRegAtual.get(0)).get(5).toString().equals("0.0"))
                {
                    zeroPrimOcConta = true;
                    break;
                }
                zeroPrimOcConta = false;
            } else
            if(((Integer)listaMesesAtual.get(semestre)).intValue() > 7)
            {
                lstInformacoesRegAtual = localizarInfoRegistro(listaCombinadaOrg, dependenciaAtual, encontraPrimeiroMesSemestre(listaMesesAtual, 2));
                if(((ArrayList)lstInformacoesRegAtual.get(0)).get(5).toString().equals("0.0"))
                {
                    zeroPrimOcConta = true;
                    break;
                }
                zeroPrimOcConta = false;
            }
            if(((Integer)listaMesesAtual.get(semestre)).intValue() != 1 && ((Integer)listaMesesAtual.get(semestre)).intValue() != 7)
                continue;
            lstInformacoesRegAtual = localizarInfoRegistro(listaCombinadaOrg, dependenciaAtual, ((Integer)listaMesesAtual.get(semestre)).intValue());
            if(((ArrayList)lstInformacoesRegAtual.get(0)).get(5).toString().equals("0.0"))
            {
                zeroPrimOcConta = true;
                break;
            }
            zeroPrimOcConta = false;
        }

        listaCombinadaOrg = null;
        listaMesesAtual = null;
        return Boolean.valueOf(zeroPrimOcConta);
    }

    public ArrayList localizarInfoRegistro(ArrayList listaCombinadaOrg, String dep, int mes)
    {
        ArrayList listaTemp = new ArrayList();
        ArrayList registroLoc = new ArrayList();
        for(int i = 0; i < ((ArrayList)listaCombinadaOrg.get(0)).size(); i++)
            if(((ArrayList)listaCombinadaOrg.get(0)).get(i).equals(dep) && ((ArrayList)listaCombinadaOrg.get(1)).get(i).toString().equals(String.valueOf(mes)))
            {
                listaTemp.add(((ArrayList)listaCombinadaOrg.get(0)).get(i).toString());
                listaTemp.add(((ArrayList)listaCombinadaOrg.get(1)).get(i).toString());
                listaTemp.add(((ArrayList)listaCombinadaOrg.get(2)).get(i).toString());
                listaTemp.add(((ArrayList)listaCombinadaOrg.get(3)).get(i).toString());
                listaTemp.add(((ArrayList)listaCombinadaOrg.get(4)).get(i).toString());
                listaTemp.add(((ArrayList)listaCombinadaOrg.get(5)).get(i).toString());
                listaTemp.add(((ArrayList)listaCombinadaOrg.get(6)).get(i).toString());
                listaTemp.add(((ArrayList)listaCombinadaOrg.get(7)).get(i).toString());
            }

        registroLoc.add(listaTemp);
        listaTemp = null;
        return registroLoc;
    }

    public ArrayList verificarDuplicidade(List lstMeses)
    {
        ArrayList repetido = new ArrayList();
        if(lstMeses.size() > 0)
        {
label0:
            for(int i = ((Integer)lstMeses.get(0)).intValue(); i < lstMeses.size() - 1; i++)
            {
                int j = i + 1;
                do
                {
                    if(j >= lstMeses.size())
                        continue label0;
                    if(lstMeses.get(i) == lstMeses.get(j))
                    {
                        repetido.add(lstMeses.get(j));
                        continue label0;
                    }
                    j++;
                } while(true);
            }

        }
        lstMeses = null;
        return repetido;
    }

    public int encontraPrimeiroMesSemestre(ArrayList lstMeses, int semestre)
    {
        int valorMax = 0;
        int valorMin = 0;
        int mes = 0;
        if(semestre == 1)
        {
            valorMax = 6;
            valorMin = 1;
            mes = 6;
        } else
        if(semestre == 2)
        {
            valorMax = 12;
            valorMin = 7;
            mes = 12;
        }
        for(int i = 0; i < lstMeses.size(); i++)
            if(((Integer)lstMeses.get(i)).intValue() <= valorMax && ((Integer)lstMeses.get(i)).intValue() >= valorMin && ((Integer)lstMeses.get(i)).intValue() < mes)
                mes = ((Integer)lstMeses.get(i)).intValue();

        lstMeses = null;
        return mes;
    }

    public ArrayList organizaListaMeses(ArrayList lstMeses)
    {
        Object o[] = lstMeses.toArray();
        Arrays.sort(o);
        for(int i = 0; i < lstMeses.size(); i++)
        {
            int temp = ((Integer)o[i]).intValue();
            lstMeses.set(i, Integer.valueOf(temp));
        }

        return lstMeses;
    }

    public ArrayList montarListaCombinada(List lista)
    {
        ArrayList dependencias = new ArrayList();
        ArrayList meses = new ArrayList();
        ArrayList saldoFinal = new ArrayList();
        ArrayList anoMesCompet = new ArrayList();
        ArrayList numLinha = new ArrayList();
        ArrayList saldoInicial = new ArrayList();
        ArrayList credito = new ArrayList();
        ArrayList debito = new ArrayList();
        ArrayList listaCombinadaOrg = new ArrayList();
        Data dtLista = new Data();
        String item = null;
        int mes = 0;
        double saldoF = 0.0D;
        String anoMesComp = null;
        Long numL = new Long(0L);
        double saldoI = 0.0D;
        double cred = 0.0D;
        double debit = 0.0D;
        for(int i = 0; i < lista.size(); i++)
        {
            Object o[] = (Object[])(Object[])lista.get(i);
            item = (String)o[0];
            mes = ((Integer)o[1]).intValue();
            saldoF = ((Double)o[2]).doubleValue();
            anoMesComp = dtLista.formataData((Date)o[3], "yyyyMMdd");
            numL = (Long)o[4];
            saldoI = ((Double)o[5]).doubleValue();
            cred = ((Double)o[6]).doubleValue();
            debit = ((Double)o[7]).doubleValue();
            dependencias.add(item);
            meses.add(Integer.valueOf(mes));
            saldoFinal.add(Double.valueOf(saldoF));
            anoMesCompet.add(anoMesComp);
            numLinha.add(numL);
            saldoInicial.add(Double.valueOf(saldoI));
            credito.add(Double.valueOf(cred));
            debito.add(Double.valueOf(debit));
        }

        listaCombinadaOrg.add(dependencias);
        listaCombinadaOrg.add(meses);
        listaCombinadaOrg.add(saldoFinal);
        listaCombinadaOrg.add(anoMesCompet);
        listaCombinadaOrg.add(numLinha);
        listaCombinadaOrg.add(saldoInicial);
        listaCombinadaOrg.add(credito);
        listaCombinadaOrg.add(debito);
        dependencias = null;
        meses = null;
        saldoFinal = null;
        anoMesCompet = null;
        numLinha = null;
        saldoInicial = null;
        credito = null;
        debito = null;
        return listaCombinadaOrg;
    }

    public ArrayList montarListaDependencia(List lista)
    {
        ArrayList dependencias = new ArrayList();
        ArrayList cntbPropria = new ArrayList();
        ArrayList listaDepCombinadaOrg = new ArrayList();
        String codDep = null;
        Short contabPropria = Short.valueOf((short)0);
        for(int i = 0; i < lista.size(); i++)
        {
            Object o[] = (Object[])(Object[])lista.get(i);
            codDep = (String)o[0];
            contabPropria = (Short)o[1];
            dependencias.add(codDep);
            cntbPropria.add(contabPropria);
        }

        listaDepCombinadaOrg.add(dependencias);
        listaDepCombinadaOrg.add(cntbPropria);
        dependencias = null;
        cntbPropria = null;
        return listaDepCombinadaOrg;
    }

    public int buscarMesFimSemestre(List lstMeses, int periodo)
    {
        int inicio = 0;
        int fim = 0;
        int maior = 0;
        if(periodo == 1)
        {
            inicio = 1;
            fim = 6;
        } else
        {
            inicio = 7;
            fim = 12;
        }
        for(int i = 0; i < lstMeses.size(); i++)
            if(((Integer)lstMeses.get(i)).intValue() >= inicio && ((Integer)lstMeses.get(i)).intValue() <= fim && ((Integer)lstMeses.get(i)).intValue() > maior)
                maior = ((Integer)lstMeses.get(i)).intValue();

        lstMeses = null;
        return maior;
    }

    public ArrayList buscarMesesPorDependencia(List lista, String dependencia)
    {
        ArrayList meses = new ArrayList();
        for(int i = 0; i < lista.size(); i++)
        {
            Object o[] = (Object[])(Object[])lista.get(i);
            String item = (String)o[0];
            int mes = 0;
            if(item.equals(dependencia))
            {
                mes = ((Integer)o[1]).intValue();
                meses.add(Integer.valueOf(mes));
            }
        }

        return meses;
    }

    public ArrayList organizarListaDependencia(List lista)
    {
        ArrayList lstDependencia = new ArrayList();
        for(int i = 0; i < lista.size(); i++)
        {
            Object o[] = (Object[])(Object[])lista.get(i);
            String item = (String)o[0];
            boolean itemRepetido = false;
            for(int j = 0; j < lstDependencia.size(); j++)
                if(((String)lstDependencia.get(j)).equals(item))
                    itemRepetido = true;

            if(!itemRepetido)
                lstDependencia.add(item);
        }

        return lstDependencia;
    }

    public boolean comparaDouble(double v1, double v2)
    {
        boolean iguais = false;
        BigDecimal valor1 = new BigDecimal(Double.valueOf(v1).doubleValue());
        BigDecimal valor2 = new BigDecimal(Double.valueOf(v2).doubleValue());
        valor1.setScale(2, RoundingMode.CEILING);
        valor2.setScale(2, RoundingMode.CEILING);
        if(valor1.compareTo(valor2) == 0)
            iguais = true;
        return iguais;
    }

    public BalanceteAnaliticoMensalDao getBalancAnalitMensalDao()
    {
        return balancAnalitMensalDao;
    }

    public void setBalancAnalitMensalDao(BalanceteAnaliticoMensalDao balancAnalitMensalDao)
    {
        this.balancAnalitMensalDao = balancAnalitMensalDao;
    }

    public IdentDeclaracaoDao getDeclaracaoDao()
    {
        return declaracaoDao;
    }

    public void setDeclaracaoDao(IdentDeclaracaoDao declaracaoDao)
    {
        this.declaracaoDao = declaracaoDao;
    }

    public IdentDependenciaDao getDependenciaDao()
    {
        return dependenciaDao;
    }

    public void setDependenciaDao(IdentDependenciaDao dependenciaDao)
    {
        this.dependenciaDao = dependenciaDao;
    }
}
