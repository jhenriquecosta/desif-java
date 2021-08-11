/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.service.registrobanco.impl;

import br.gov.pbh.desif.dao.BalanceteAnaliticoMensalDao;
import br.gov.pbh.desif.dao.IdentDeclaracaoDao;
import br.gov.pbh.desif.dao.IdentDependenciaDao;
import br.gov.pbh.desif.model.pojo.IdentificacaoDeclaracao;
import br.gov.pbh.desif.model.registros.Data;
import br.gov.pbh.desif.model.registros.RegUtil;
import br.gov.pbh.desif.service.contexto.Contexto;
import br.gov.pbh.desif.service.registrobanco.ValidaBancoReg0410;
import br.gov.pbh.desif.view.telas.PanGerarDeclaracao;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class ValidaBancoReg0410Impl
implements ValidaBancoReg0410 {
    private BalanceteAnaliticoMensalDao balancAnalitMensalDao;
    private RegUtil regUtil;
    private String registro = "0410";
    private Data dt;
    private IdentificacaoDeclaracao declaracao;
    private IdentDeclaracaoDao declaracaoDao;
    private PanGerarDeclaracao panGD;
    private IdentDependenciaDao dependenciaDao;

    @Override
    public void executar() throws Exception {
        this.regUtil = new RegUtil();
        this.dt = new Data();
        this.declaracao = (IdentificacaoDeclaracao)this.declaracaoDao.load(new Integer(Integer.parseInt("1")));
        this.execucaoGeralMetodos();
    }

    public void mesesDuplicados(ArrayList<Integer> mesesDuplicados, ArrayList<ArrayList> listaCombinadaOrg, List listaDependencia, int k, List listaConta, int j) throws NumberFormatException {
        for (int x = 0; x < mesesDuplicados.size(); ++x) {
            ArrayList<ArrayList> lstInformacoesRegistroDuplic = this.localizarInfoRegistro(listaCombinadaOrg, listaDependencia.get(k).toString(), mesesDuplicados.get(x));
            String txtSolucao = "C\u00f3digo da depend\u00eancia: " + listaDependencia.get(k) + ", Compet\u00eancia: " + lstInformacoesRegistroDuplic.get(0).get(3).toString() + ", Conta: " + listaConta.get(j);
            this.regUtil.setErro(Long.parseLong(lstInformacoesRegistroDuplic.get(0).get(4).toString()), "EC013", 5, (short)2, this.registro, txtSolucao);
            lstInformacoesRegistroDuplic = null;
        }
    }

    public void verificaContabilidadePropria(ArrayList<ArrayList> lstDependencias, ArrayList<ArrayList> lstInfoRegAtual) throws Exception {
        int coluna = 3;
        Long numLinha = new Long(lstInfoRegAtual.get(0).get(4).toString());
        String dependenciaAtual = lstInfoRegAtual.get(0).get(0).toString();
        for (int i = 0; i < lstDependencias.get(1).size(); ++i) {
            Short contabPropria;
            if (!lstDependencias.get(0).get(i).toString().equals(dependenciaAtual) || (contabPropria = Short.valueOf(Short.parseShort(lstDependencias.get(1).get(i).toString()))) != 2) continue;
            String txtSolucao = "C\u00f3digo da depend\u00eancia: " + dependenciaAtual + ", identificador de contabilidade pr\u00f3pria: " + contabPropria;
            this.regUtil.setErro(numLinha, "EC001", coluna, (short)2, this.registro, txtSolucao);
        }
        lstDependencias = null;
        lstInfoRegAtual = null;
    }

    public void verificarCodigoDependencia(ArrayList<ArrayList> lstDependencias, ArrayList<ArrayList> lstInfoRegAtual) throws Exception {
        int coluna = 3;
        Long numLinha = new Long(lstInfoRegAtual.get(0).get(4).toString());
        String dependenciaAtual = lstInfoRegAtual.get(0).get(0).toString();
        boolean localizado = false;
        for (int i = 0; i < lstDependencias.get(0).size(); ++i) {
            if (!lstDependencias.get(0).get(i).toString().equals(dependenciaAtual)) continue;
            localizado = true;
            break;
        }
        if (!localizado) {
            String txtSolucao = "C\u00f3digo da depend\u00eancia: " + dependenciaAtual;
            this.regUtil.setErro(numLinha, "EG006", coluna, (short)2, this.registro, txtSolucao);
        }
        lstDependencias = null;
        lstInfoRegAtual = null;
    }

    public void verificaPeriodoCompetencia(ArrayList<ArrayList> lstInfoRegAtual, String dtIniCompDecl, String dtFimCompDecl) throws Exception {
        Boolean dentroPeriodoCompetenciaFinal;
        int coluna = 4;
        Data dtPeriodoCompet = new Data();
        Long numLinha = new Long(lstInfoRegAtual.get(0).get(4).toString());
        String dependenciaReg = lstInfoRegAtual.get(0).get(0).toString();
        String anoMesCompetencia = lstInfoRegAtual.get(0).get(3).toString();
        Boolean dentroPeriodoCompetenciaInicial = dtPeriodoCompet.comparaDataMaior(anoMesCompetencia, dtIniCompDecl, "yyyyMMdd");
        if (!dentroPeriodoCompetenciaInicial.booleanValue()) {
            dentroPeriodoCompetenciaInicial = dtPeriodoCompet.validaIgualdadeEntreData(anoMesCompetencia, dtIniCompDecl, "yyyyMMdd", 3);
        }
        if (!(dentroPeriodoCompetenciaFinal = Boolean.valueOf(dtPeriodoCompet.comparaDataMaior(dtFimCompDecl, anoMesCompetencia, "yyyyMMdd"))).booleanValue()) {
            dentroPeriodoCompetenciaFinal = dtPeriodoCompet.validaIgualdadeEntreData(anoMesCompetencia, dtFimCompDecl, "yyyyMMdd", 3);
        }
        if (!dentroPeriodoCompetenciaInicial.booleanValue() || !dentroPeriodoCompetenciaFinal.booleanValue()) {
            String txtSolucao = "C\u00f3digo da depend\u00eancia: " + dependenciaReg + ", Compet\u00eancia da declara\u00e7\u00e3o: de " + dtIniCompDecl + " a " + dtFimCompDecl + ", Compet\u00eancia informada: " + anoMesCompetencia;
            this.regUtil.setErro(numLinha, "EC014", coluna, (short)2, this.registro, txtSolucao);
        }
        lstInfoRegAtual = null;
    }

    public void verificaSaldoFinal(ArrayList<ArrayList> lstInfoRegAtual, String contaAtual) throws Exception {
        int coluna = 9;
        String conta = contaAtual;
        Long numLinha = new Long(lstInfoRegAtual.get(0).get(4).toString());
        String dependenciaReg = lstInfoRegAtual.get(0).get(0).toString();
        String anoMesCompetencia = lstInfoRegAtual.get(0).get(3).toString();
        BigDecimal saldoFinal = new BigDecimal(lstInfoRegAtual.get(0).get(2).toString());
        BigDecimal saldoInicial = new BigDecimal(lstInfoRegAtual.get(0).get(5).toString());
        BigDecimal credito = new BigDecimal(lstInfoRegAtual.get(0).get(6).toString());
        BigDecimal debito = new BigDecimal(lstInfoRegAtual.get(0).get(7).toString());
        saldoInicial = saldoInicial.add(credito);
        if (saldoFinal.compareTo(saldoInicial = saldoInicial.subtract(debito)) != 0) {
            String txtSolucao = "C\u00f3digo da Depend\u00eancia: " + dependenciaReg + ", Conta: " + conta + ", Compet\u00eancia: " + anoMesCompetencia + ", Saldo inicial:" + lstInfoRegAtual.get(0).get(5).toString() + ", Valor do cr\u00e9dito:" + lstInfoRegAtual.get(0).get(6).toString() + ", Valor do d\u00e9bito:" + lstInfoRegAtual.get(0).get(7).toString() + ", Saldo final:" + lstInfoRegAtual.get(0).get(2).toString();
            this.regUtil.setErro(numLinha, "EC011", coluna, (short)2, this.registro, txtSolucao);
        }
        lstInfoRegAtual = null;
    }

    public void verificaUltimaOcorrenciaConta(ArrayList<ArrayList> lstInfoRegistro, String contaAtual, boolean ultimaOcorrenciaAtual) throws Exception {
        int coluna = 9;
        int mes = Integer.parseInt(lstInfoRegistro.get(0).get(1).toString());
        String conta = contaAtual;
        boolean ultimaOcorrencia = ultimaOcorrenciaAtual;
        double saldoFinal = new Double(lstInfoRegistro.get(0).get(2).toString());
        Long numLinha = new Long(lstInfoRegistro.get(0).get(4).toString());
        String dependenciaReg = lstInfoRegistro.get(0).get(0).toString();
        String anoMesCompetencia = lstInfoRegistro.get(0).get(3).toString();
        Date fimCompetencia = this.declaracao.getDataFimCompetencia();
        Calendar dataFimCompetencia = GregorianCalendar.getInstance();
        dataFimCompetencia.setTime(fimCompetencia);
        int mesFimPeriodoCompetenciaDecl = dataFimCompetencia.get(2) + 1;
        if (mes != mesFimPeriodoCompetenciaDecl && saldoFinal > 0.0 && mes != 6 && mes != 12 && ultimaOcorrencia) {
            String txtSolucao = "C\u00f3digo da depend\u00eancia: " + dependenciaReg + ", Conta: " + conta + ", Compet\u00eancia: " + anoMesCompetencia + ", Saldo Final da Compet\u00eancia informado: " + saldoFinal;
            this.regUtil.setErro(numLinha, "EC017", coluna, (short)2, this.registro, txtSolucao);
        }
        lstInfoRegistro = null;
    }

    public Double verificaSaldoCompetenciaAnterior(ArrayList<Integer> listaMesesAtual, ArrayList<ArrayList> listaCombinadaOrg, ArrayList<ArrayList> lstInfoRegistro, String dependenciaAtual) {
        int mes = Integer.parseInt(lstInfoRegistro.get(0).get(1).toString());
        int mesInicialSemestre = 0;
        if (mes > 0 && mes <= 6) {
            mesInicialSemestre = this.encontraPrimeiroMesSemestre(listaMesesAtual, 1);
        } else if (mes > 6 && mes <= 12) {
            mesInicialSemestre = this.encontraPrimeiroMesSemestre(listaMesesAtual, 2);
        }
        double saldoFinalCompetAnterior = new Double("0.0");
        if (mes != mesInicialSemestre && mes != 1 && mes != 7) {
            lstInfoRegistro = this.localizarInfoRegistro(listaCombinadaOrg, dependenciaAtual, mes - 1);
            saldoFinalCompetAnterior = new Double(lstInfoRegistro.get(0).get(2).toString());
        }
        return saldoFinalCompetAnterior;
    }

    public void validacoesSaldoInicial(ArrayList<ArrayList> lstInfoRegistro, ArrayList<Integer> listaMesesAtual, ArrayList<ArrayList> listaCombinadaOrg, String contaAtual) throws Exception {
        int coluna = 6;
        int mes = Integer.parseInt(lstInfoRegistro.get(0).get(1).toString());
        double saldoInicial = new Double(lstInfoRegistro.get(0).get(5).toString());
        String dependenciaReg = lstInfoRegistro.get(0).get(0).toString();
        double saldoFinalCompetAnterior = this.verificaSaldoCompetenciaAnterior(listaMesesAtual, listaCombinadaOrg, lstInfoRegistro, dependenciaReg);
        Long numLinha = new Long(lstInfoRegistro.get(0).get(4).toString());
        String conta = contaAtual;
        String anoMesCompetencia = lstInfoRegistro.get(0).get(3).toString();
        Boolean zeroPrimOcorContaSemestre = this.verificaZeroPrimeiraOcorrenciaConta(listaMesesAtual, listaCombinadaOrg, contaAtual, dependenciaReg);
        if (!(mes != 1 && mes != 7 || zeroPrimOcorContaSemestre.booleanValue())) {
            String txtSolucao = "C\u00f3digo da depend\u00eancia: " + dependenciaReg + ", Conta: " + conta + ", Competencia: " + anoMesCompetencia + ", Saldo Inicial: " + saldoInicial;
            this.regUtil.setErro(numLinha, "EC005", coluna, (short)2, this.registro, txtSolucao);
        } else if (!(mes != 1 && mes != 7 || this.comparaDouble(saldoInicial, 0.0))) {
            String txtSolucao = "C\u00f3digo da depend\u00eancia: " + dependenciaReg + ", Conta: " + conta + ", Competencia: " + anoMesCompetencia + ", Saldo Inicial: " + saldoInicial;
            this.regUtil.setErro(numLinha, "EC005", coluna, (short)2, this.registro, txtSolucao);
        } else if (!(mes == 1 && mes == 7 || zeroPrimOcorContaSemestre.booleanValue())) {
            String txtSolucao = "C\u00f3digo da depend\u00eancia: " + dependenciaReg + ", Conta: " + conta + ", Competencia: " + anoMesCompetencia + ", Saldo Inicial: " + saldoInicial;
            this.regUtil.setErro(numLinha, "EC005", coluna, (short)2, this.registro, txtSolucao);
        } else if (!(mes == 1 && mes == 7 || this.comparaDouble(saldoInicial, saldoFinalCompetAnterior))) {
            String txtSolucao = "C\u00f3digo da depend\u00eancia: " + dependenciaReg + ", Competencia: " + anoMesCompetencia + ", Saldo Inicial: " + saldoInicial + " e Saldo Final Compet\u00eancia Anterior: " + saldoFinalCompetAnterior;
            this.regUtil.setErro(numLinha, "EC004", coluna, (short)2, this.registro, txtSolucao);
        }
        lstInfoRegistro = null;
    }

    public void execucaoGeralMetodos() throws Exception {
        this.panGD = (PanGerarDeclaracao)Contexto.getObject("panGD");
        double incremento = 0.0;
        double sentinela = 15.0;
        int atualizar = 0;
        List listaConta = this.balancAnalitMensalDao.buscarListaConta();
        ArrayList<ArrayList> listaTabelaIdentDependencia = this.montarListaDependencia(this.dependenciaDao.buscaCodigoDependencia());
        List lstInicioFimDeclaracao = this.declaracaoDao.buscaInicioFimCompetencia();
        Data dtDeclCompet = new Data();
        Object[] o = (Object[])lstInicioFimDeclaracao.get(0);
        String iniCompetDecl = dtDeclCompet.formataData((Date)o[0], "yyyyMMdd");
        String fimCompetDecl = dtDeclCompet.formataData((Date)o[1], "yyyyMMdd");
        String[] dataCompet = dtDeclCompet.separaData(fimCompetDecl);
        int mesFimPeriodoCompetenciaDecl = Integer.parseInt(dataCompet[1]);
        for (int j = 0; j < listaConta.size(); ++j) {
            incremento = this.regUtil.incremetoPorcentagem(65.0, listaConta.size());
            if (sentinela < 80.0) {
                atualizar = (int)sentinela;
                this.panGD.atualizarProgressoValidacao(atualizar, 100);
                sentinela += incremento;
            }
            List listaCombinada = this.balancAnalitMensalDao.BuscarContaDependenciaMesCombinado(listaConta.get(j).toString());
            ArrayList<ArrayList> listaCombinadaOrganizada = this.montarListaCombinada(listaCombinada);
            ArrayList<String> listaDependencia = this.organizarListaDependencia(listaCombinada);
            for (int k = 0; k < listaDependencia.size(); ++k) {
                ArrayList<Integer> listaMeses = this.buscarMesesPorDependencia(listaCombinada, listaDependencia.get(k).toString());
                listaMeses = this.organizaListaMeses(listaMeses);
                boolean gerouErro = this.verificaOcorrenciaContinuaSemestre(listaCombinadaOrganizada, listaMeses, mesFimPeriodoCompetenciaDecl, listaConta.get(j).toString(), listaDependencia.get(k).toString());
                ArrayList<Integer> mesesDuplicados = this.verificarDuplicidade(listaMeses);
                if (!mesesDuplicados.isEmpty() && !gerouErro) {
                    this.mesesDuplicados(mesesDuplicados, listaCombinadaOrganizada, listaDependencia, k, listaConta, j);
                    continue;
                }
                ArrayList<ArrayList> lstInformacoesRegistro = new ArrayList();
                for (int semestre = 0; semestre < listaMeses.size(); ++semestre) {
                    String conta = listaConta.get(j).toString();
                    lstInformacoesRegistro = this.localizarInfoRegistro(listaCombinadaOrganizada, listaDependencia.get(k).toString(), listaMeses.get(semestre));
                    ArrayList<ArrayList> lstInformacoesRegistroTemp = this.localizarInfoRegistro(listaCombinadaOrganizada, listaDependencia.get(k).toString(), listaMeses.get(semestre) + 1);
                    Boolean ultimaOcConta = lstInformacoesRegistroTemp.isEmpty() ? Boolean.valueOf(true) : Boolean.valueOf(false);
                    this.verificaUltimaOcorrenciaConta(lstInformacoesRegistro, conta, ultimaOcConta);
                    this.verificarCodigoDependencia(listaTabelaIdentDependencia, lstInformacoesRegistro);
                    this.verificaContabilidadePropria(listaTabelaIdentDependencia, lstInformacoesRegistro);
                    this.verificaPeriodoCompetencia(lstInformacoesRegistro, iniCompetDecl, fimCompetDecl);
                    this.verificaSaldoFinal(lstInformacoesRegistro, conta);
                    this.validacoesSaldoInicial(lstInformacoesRegistro, listaMeses, listaCombinadaOrganizada, conta);
                }
            }
        }
    }

    public Boolean verificaOcorrenciaContinuaSemestre(ArrayList<ArrayList> listaCombinadaOrg, ArrayList<Integer> lstMeses, int mesFimPerCompetDecl, String conta, String dependencia) {
        String anoMesCompetencia;
        int n;
        Long numLinha;
        String txtSolucao;
        String txtSolucao2;
        double saldoFinal;
        ArrayList lstInformacoesRegistroTemp = new ArrayList();
        boolean gerouErro = false;
        boolean existemMesesPrimSemestre = this.existeMesNoSemestre(lstMeses, 1);
        boolean existemMesesSegSemestre = this.existeMesNoSemestre(lstMeses, 2);
        int fimPrimSemestre = 0;
        int fimSegSemestre = 0;
        if (existemMesesPrimSemestre && (fimPrimSemestre = this.buscarMesFimSemestre(lstMeses, 1)) != 6 && fimPrimSemestre != mesFimPerCompetDecl) {
            lstInformacoesRegistroTemp = this.localizarInfoRegistro(listaCombinadaOrg, dependencia, fimPrimSemestre);
            numLinha = new Long(((ArrayList)lstInformacoesRegistroTemp.get(0)).get(4).toString());
            anoMesCompetencia = ((ArrayList)lstInformacoesRegistroTemp.get(0)).get(3).toString();
            saldoFinal = new Double(((ArrayList)lstInformacoesRegistroTemp.get(0)).get(2).toString());
            txtSolucao = "C\u00f3digo da depend\u00eancia: " + dependencia + ", Conta: " + conta + ", Compet\u00eancia: " + anoMesCompetencia + ", Saldo Final da Compet\u00eancia informado: " + saldoFinal;
            this.regUtil.setErro(numLinha, "EC017", 9, (short)2, this.registro, txtSolucao);
            lstInformacoesRegistroTemp = null;
            gerouErro = true;
        }
        if (existemMesesSegSemestre && (fimSegSemestre = this.buscarMesFimSemestre(lstMeses, 2)) != 6 && fimSegSemestre != mesFimPerCompetDecl) {
            lstInformacoesRegistroTemp = this.localizarInfoRegistro(listaCombinadaOrg, dependencia, fimSegSemestre);
            numLinha = new Long(((ArrayList)lstInformacoesRegistroTemp.get(0)).get(4).toString());
            anoMesCompetencia = ((ArrayList)lstInformacoesRegistroTemp.get(0)).get(3).toString();
            saldoFinal = new Double(((ArrayList)lstInformacoesRegistroTemp.get(0)).get(2).toString());
            txtSolucao = "C\u00f3digo da depend\u00eancia: " + dependencia + ", Conta: " + conta + ", Compet\u00eancia: " + anoMesCompetencia + ", Saldo Final da Compet\u00eancia informado: " + saldoFinal;
            this.regUtil.setErro(numLinha, "EC017", 9, (short)2, this.registro, txtSolucao);
            lstInformacoesRegistroTemp = null;
            gerouErro = true;
        }
        int posicaoFimPrimSemestre = this.buscaPosicaoMesSemestre(lstMeses, fimPrimSemestre);
        int posicaoFimSegSemestre = this.buscaPosicaoMesSemestre(lstMeses, fimSegSemestre);
        if (existemMesesPrimSemestre) {
            for (n = 0; n < posicaoFimPrimSemestre; ++n) {
                if (n == posicaoFimPrimSemestre || lstMeses.get(n) + 1 == lstMeses.get(n + 1)) continue;
                lstInformacoesRegistroTemp = this.localizarInfoRegistro(listaCombinadaOrg, dependencia, lstMeses.get(n));
                numLinha = new Long(((ArrayList)lstInformacoesRegistroTemp.get(0)).get(4).toString());
                anoMesCompetencia = ((ArrayList)lstInformacoesRegistroTemp.get(0)).get(3).toString();
                saldoFinal = new Double(((ArrayList)lstInformacoesRegistroTemp.get(0)).get(2).toString());
                txtSolucao2 = "C\u00f3digo da depend\u00eancia: " + dependencia + ", Conta: " + conta + ", Compet\u00eancia: " + anoMesCompetencia + ", Saldo Final da Compet\u00eancia informado: " + saldoFinal;
                this.regUtil.setErro(numLinha, "EC017", 9, (short)2, this.registro, txtSolucao2);
                lstInformacoesRegistroTemp = null;
                gerouErro = true;
            }
        }
        if (existemMesesSegSemestre) {
            for (n = posicaoFimPrimSemestre + 1; n < posicaoFimSegSemestre; ++n) {
                if (lstMeses.get(n) + 1 == lstMeses.get(n + 1)) continue;
                lstInformacoesRegistroTemp = this.localizarInfoRegistro(listaCombinadaOrg, dependencia, lstMeses.get(n));
                numLinha = new Long(((ArrayList)lstInformacoesRegistroTemp.get(0)).get(4).toString());
                anoMesCompetencia = ((ArrayList)lstInformacoesRegistroTemp.get(0)).get(3).toString();
                saldoFinal = new Double(((ArrayList)lstInformacoesRegistroTemp.get(0)).get(2).toString());
                txtSolucao2 = "C\u00f3digo da depend\u00eancia: " + dependencia + ", Conta: " + conta + ", Compet\u00eancia: " + anoMesCompetencia + ", Saldo Final da Compet\u00eancia informado: " + anoMesCompetencia;
                this.regUtil.setErro(numLinha, "EC017", 9, (short)2, this.registro, txtSolucao2);
                lstInformacoesRegistroTemp = null;
                gerouErro = true;
            }
        }
        return gerouErro;
    }

    public int buscaPosicaoMesSemestre(ArrayList<Integer> lstMeses, int mes) {
        int posicaoFimSemestre = -1;
        for (int i = 0; i < lstMeses.size(); ++i) {
            if (lstMeses.get(i) != mes) continue;
            posicaoFimSemestre = i;
            break;
        }
        lstMeses = null;
        return posicaoFimSemestre;
    }

    public Boolean existeMesNoSemestre(ArrayList<Integer> lstMeses, int semestre) {
        boolean existeMesSemestre = false;
        int ini = 0;
        int fim = 0;
        if (semestre == 1) {
            ini = 1;
            fim = 6;
        } else if (semestre == 2) {
            ini = 7;
            fim = 12;
        }
        for (int i = ini; i <= fim; ++i) {
            if (!lstMeses.contains(i)) continue;
            existeMesSemestre = true;
            break;
        }
        lstMeses = null;
        return existeMesSemestre;
    }

    public Boolean verificaZeroPrimeiraOcorrenciaConta(ArrayList<Integer> listaMesesAtual, ArrayList<ArrayList> listaCombinadaOrg, String contaAtual, String dependenciaAtual) {
        boolean zeroPrimOcConta = false;
        ArrayList lstInformacoesRegAtual = new ArrayList();
        for (int semestre = 0; semestre < listaMesesAtual.size(); ++semestre) {
            if (listaMesesAtual.get(semestre) > 1 && listaMesesAtual.get(semestre) <= 6) {
                lstInformacoesRegAtual = this.localizarInfoRegistro(listaCombinadaOrg, dependenciaAtual, this.encontraPrimeiroMesSemestre(listaMesesAtual, 1));
                if (((ArrayList)lstInformacoesRegAtual.get(0)).get(5).toString().equals("0.0")) {
                    zeroPrimOcConta = true;
                    break;
                }
                zeroPrimOcConta = false;
            } else if (listaMesesAtual.get(semestre) > 7) {
                lstInformacoesRegAtual = this.localizarInfoRegistro(listaCombinadaOrg, dependenciaAtual, this.encontraPrimeiroMesSemestre(listaMesesAtual, 2));
                if (((ArrayList)lstInformacoesRegAtual.get(0)).get(5).toString().equals("0.0")) {
                    zeroPrimOcConta = true;
                    break;
                }
                zeroPrimOcConta = false;
            }
            if (listaMesesAtual.get(semestre) != 1 && listaMesesAtual.get(semestre) != 7) continue;
            lstInformacoesRegAtual = this.localizarInfoRegistro(listaCombinadaOrg, dependenciaAtual, listaMesesAtual.get(semestre));
            if (((ArrayList)lstInformacoesRegAtual.get(0)).get(5).toString().equals("0.0")) {
                zeroPrimOcConta = true;
                break;
            }
            zeroPrimOcConta = false;
        }
        listaCombinadaOrg = null;
        listaMesesAtual = null;
        return zeroPrimOcConta;
    }

    public ArrayList<ArrayList> localizarInfoRegistro(ArrayList<ArrayList> listaCombinadaOrg, String dep, int mes) {
        ArrayList<String> listaTemp = new ArrayList<String>();
        ArrayList<ArrayList> registroLoc = new ArrayList<ArrayList>();
        for (int i = 0; i < listaCombinadaOrg.get(0).size(); ++i) {
            if (!listaCombinadaOrg.get(0).get(i).equals(dep) || !listaCombinadaOrg.get(1).get(i).toString().equals(String.valueOf(mes))) continue;
            listaTemp.add(listaCombinadaOrg.get(0).get(i).toString());
            listaTemp.add(listaCombinadaOrg.get(1).get(i).toString());
            listaTemp.add(listaCombinadaOrg.get(2).get(i).toString());
            listaTemp.add(listaCombinadaOrg.get(3).get(i).toString());
            listaTemp.add(listaCombinadaOrg.get(4).get(i).toString());
            listaTemp.add(listaCombinadaOrg.get(5).get(i).toString());
            listaTemp.add(listaCombinadaOrg.get(6).get(i).toString());
            listaTemp.add(listaCombinadaOrg.get(7).get(i).toString());
        }
        registroLoc.add(listaTemp);
        listaTemp = null;
        return registroLoc;
    }

    public ArrayList<Integer> verificarDuplicidade(List<Integer> lstMeses) {
        ArrayList<Integer> repetido = new ArrayList<Integer>();
        if (lstMeses.size() > 0) {
            block0 : for (int i = lstMeses.get((int)0).intValue(); i < lstMeses.size() - 1; ++i) {
                for (int j = i + 1; j < lstMeses.size(); ++j) {
                    if (lstMeses.get(i) != lstMeses.get(j)) continue;
                    repetido.add(lstMeses.get(j));
                    continue block0;
                }
            }
        }
        lstMeses = null;
        return repetido;
    }

    public int encontraPrimeiroMesSemestre(ArrayList<Integer> lstMeses, int semestre) {
        int valorMax = 0;
        int valorMin = 0;
        int mes = 0;
        if (semestre == 1) {
            valorMax = 6;
            valorMin = 1;
            mes = 6;
        } else if (semestre == 2) {
            valorMax = 12;
            valorMin = 7;
            mes = 12;
        }
        for (int i = 0; i < lstMeses.size(); ++i) {
            if (lstMeses.get(i) > valorMax || lstMeses.get(i) < valorMin || lstMeses.get(i) >= mes) continue;
            mes = lstMeses.get(i);
        }
        lstMeses = null;
        return mes;
    }

    public ArrayList<Integer> organizaListaMeses(ArrayList<Integer> lstMeses) {
        Object[] o = lstMeses.toArray();
        Arrays.sort(o);
        for (int i = 0; i < lstMeses.size(); ++i) {
            int temp = (Integer)o[i];
            lstMeses.set(i, temp);
        }
        return lstMeses;
    }

    public ArrayList<ArrayList> montarListaCombinada(List lista) {
        ArrayList<String> dependencias = new ArrayList<String>();
        ArrayList<Integer> meses = new ArrayList<Integer>();
        ArrayList<Double> saldoFinal = new ArrayList<Double>();
        ArrayList<String> anoMesCompet = new ArrayList<String>();
        ArrayList<Long> numLinha = new ArrayList<Long>();
        ArrayList<Double> saldoInicial = new ArrayList<Double>();
        ArrayList<Double> credito = new ArrayList<Double>();
        ArrayList<Double> debito = new ArrayList<Double>();
        ArrayList<ArrayList> listaCombinadaOrg = new ArrayList<ArrayList>();
        Data dtLista = new Data();
        String item = null;
        int mes = 0;
        double saldoF = 0.0;
        String anoMesComp = null;
        Long numL = new Long(0L);
        double saldoI = 0.0;
        double cred = 0.0;
        double debit = 0.0;
        for (int i = 0; i < lista.size(); ++i) {
            Object[] o = (Object[])lista.get(i);
            item = (String)o[0];
            mes = (Integer)o[1];
            saldoF = (Double)o[2];
            anoMesComp = dtLista.formataData((Date)o[3], "yyyyMMdd");
            numL = (Long)o[4];
            saldoI = (Double)o[5];
            cred = (Double)o[6];
            debit = (Double)o[7];
            dependencias.add(item);
            meses.add(mes);
            saldoFinal.add(saldoF);
            anoMesCompet.add(anoMesComp);
            numLinha.add(numL);
            saldoInicial.add(saldoI);
            credito.add(cred);
            debito.add(debit);
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

    public ArrayList<ArrayList> montarListaDependencia(List lista) {
        ArrayList<String> dependencias = new ArrayList<String>();
        ArrayList<Short> cntbPropria = new ArrayList<Short>();
        ArrayList<ArrayList> listaDepCombinadaOrg = new ArrayList<ArrayList>();
        String codDep = null;
        Short contabPropria = 0;
        for (int i = 0; i < lista.size(); ++i) {
            Object[] o = (Object[])lista.get(i);
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

    public int buscarMesFimSemestre(List<Integer> lstMeses, int periodo) {
        int inicio = 0;
        int fim = 0;
        int maior = 0;
        if (periodo == 1) {
            inicio = 1;
            fim = 6;
        } else {
            inicio = 7;
            fim = 12;
        }
        for (int i = 0; i < lstMeses.size(); ++i) {
            if (lstMeses.get(i) < inicio || lstMeses.get(i) > fim || lstMeses.get(i) <= maior) continue;
            maior = lstMeses.get(i);
        }
        lstMeses = null;
        return maior;
    }

    public ArrayList<Integer> buscarMesesPorDependencia(List lista, String dependencia) {
        ArrayList<Integer> meses = new ArrayList<Integer>();
        for (int i = 0; i < lista.size(); ++i) {
            Object[] o = (Object[])lista.get(i);
            String item = (String)o[0];
            int mes = 0;
            if (!item.equals(dependencia)) continue;
            mes = (Integer)o[1];
            meses.add(mes);
        }
        return meses;
    }

    public ArrayList<String> organizarListaDependencia(List lista) {
        ArrayList<String> lstDependencia = new ArrayList<String>();
        for (int i = 0; i < lista.size(); ++i) {
            Object[] o = (Object[])lista.get(i);
            String item = (String)o[0];
            boolean itemRepetido = false;
            for (int j = 0; j < lstDependencia.size(); ++j) {
                if (!lstDependencia.get(j).equals(item)) continue;
                itemRepetido = true;
            }
            if (itemRepetido) continue;
            lstDependencia.add(item);
        }
        return lstDependencia;
    }

    public boolean comparaDouble(double v1, double v2) {
        boolean iguais = false;
        BigDecimal valor1 = new BigDecimal(v1);
        BigDecimal valor2 = new BigDecimal(v2);
        valor1.setScale(2, RoundingMode.CEILING);
        valor2.setScale(2, RoundingMode.CEILING);
        if (valor1.compareTo(valor2) == 0) {
            iguais = true;
        }
        return iguais;
    }

    public BalanceteAnaliticoMensalDao getBalancAnalitMensalDao() {
        return this.balancAnalitMensalDao;
    }

    public void setBalancAnalitMensalDao(BalanceteAnaliticoMensalDao balancAnalitMensalDao) {
        this.balancAnalitMensalDao = balancAnalitMensalDao;
    }

    public IdentDeclaracaoDao getDeclaracaoDao() {
        return this.declaracaoDao;
    }

    public void setDeclaracaoDao(IdentDeclaracaoDao declaracaoDao) {
        this.declaracaoDao = declaracaoDao;
    }

    public IdentDependenciaDao getDependenciaDao() {
        return this.dependenciaDao;
    }

    public void setDependenciaDao(IdentDependenciaDao dependenciaDao) {
        this.dependenciaDao = dependenciaDao;
    }
}

