/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.service.registrobanco.impl;

import br.gov.pbh.desif.dao.DemonstrativoRateioMensalDao;
import br.gov.pbh.desif.dao.EventosContabeisDao;
import br.gov.pbh.desif.dao.IdentDeclaracaoDao;
import br.gov.pbh.desif.dao.IdentDependenciaDao;
import br.gov.pbh.desif.model.pojo.DemonstrativoRateioMensal;
import br.gov.pbh.desif.model.pojo.IdentificacaoDeclaracao;
import br.gov.pbh.desif.model.registros.Data;
import br.gov.pbh.desif.model.registros.RegUtil;
import br.gov.pbh.desif.service.contexto.Contexto;
import br.gov.pbh.desif.service.registrobanco.ValidaBancoReg0420;
import br.gov.pbh.desif.view.telas.PanGerarDeclaracao;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ValidaBancoReg0420Impl
implements ValidaBancoReg0420 {
    private DemonstrativoRateioMensal rateioMensal;
    private DemonstrativoRateioMensalDao rateioMensalDao;
    private RegUtil regUtil;
    private String registro = "0420";
    private Data dt;
    private IdentificacaoDeclaracao declaracao;
    private IdentDeclaracaoDao declaracaoDao;
    private PanGerarDeclaracao panGD;
    private IdentDependenciaDao dependenciaDao;
    private EventosContabeisDao eventosContabeisDao;

    @Override
    public void executar() throws Exception {
        this.regUtil = new RegUtil();
        this.dt = new Data();
        this.declaracao = (IdentificacaoDeclaracao)this.declaracaoDao.load(new Integer(Integer.parseInt("1")));
        this.panGD = (PanGerarDeclaracao)Contexto.getObject("panGD");
        List rateioMensalList = this.rateioMensalDao.findAll();
        double incremento = this.regUtil.incremetoPorcentagem(65.0, rateioMensalList.size());
        double sentinela = 80.0;
        int atualizar = 0;
        Iterator i = rateioMensalList.iterator();
        while (i.hasNext()) {
            if (sentinela < 100.0) {
                atualizar = (int)sentinela;
                this.panGD.atualizarProgressoValidacao(atualizar, 100);
                sentinela += incremento;
            }
            this.rateioMensal = (DemonstrativoRateioMensal)i.next();
            this.verificaExistenciaRegistroRateioMensal();
            this.verificaPeriodoCompetencia();
            this.verificarCodDepe();
        }
    }

    public void verificarCodDepe() throws Exception {
        int coluna = 3;
        List resp = this.dependenciaDao.findField("codigoDependencia", this.rateioMensal.getCodigoDependencia());
        if (resp.size() == 0) {
            System.out.println("Esta entrando aqui???");
            System.out.println(this.rateioMensal.getCodigoDependencia());
            String txtSolucao = "C\u00f3digo da depend\u00eancia: " + this.rateioMensal.getCodigoDependencia();
            this.regUtil.setErro(this.rateioMensal.getNumLinha(), "EG006", coluna, (short)2, this.registro, txtSolucao);
        }
    }

    public void verificaPeriodoCompetencia() throws Exception {
        int coluna = 2;
        boolean dentroPeriodoCompetencia = this.declaracaoDao.verificaDentroCompetencia(this.rateioMensal.getAnoMesCompetencia());
        if (!dentroPeriodoCompetencia) {
            String txtSolucao = "C\u00f3digo da depend\u00eancia: " + this.rateioMensal.getCodigoDependencia() + ", Compet\u00eancia da declara\u00e7\u00e3o: de " + this.dt.formataData(this.declaracao.getDataInicioCompetencia(), "yyyyMMdd") + " a " + this.dt.formataData(this.declaracao.getDataFimCompetencia(), "yyyyMMdd") + " Compet\u00eancia informada: " + this.dt.formataData(this.rateioMensal.getAnoMesCompetencia(), "yyyyMMdd");
            this.regUtil.setErro(this.rateioMensal.getNumLinha(), "EC014", coluna, (short)2, this.registro, txtSolucao);
        }
    }

    public void verificaExistenciaRegistroRateioMensal() throws Exception {
        int coluna = 8;
        if (!this.eventosContabeisDao.verificaExistenciaCodigoEvento(this.rateioMensal.getCodigoEvento())) {
            String txtSolucao = "C\u00f3digo da depend\u00eancia: " + this.rateioMensal.getCodigoDependencia() + ", Per\u00edodo de Compet\u00eancia: " + this.dt.formataData(this.rateioMensal.getAnoMesCompetencia(), "yyyyMMdd") + ", Valor rateado: " + this.rateioMensal.getValorReceitaRateada() + ", Tipo de Partida: " + this.rateioMensal.getTipoPartida() + ", Codigo do Evento: " + this.rateioMensal.getCodigoEvento();
            this.regUtil.setErro(this.rateioMensal.getCodigoEvento().intValue(), "EC019", coluna, (short)2, this.registro, txtSolucao);
        }
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

    public EventosContabeisDao getEventosContabeisDao() {
        return this.eventosContabeisDao;
    }

    public void setEventosContabeisDao(EventosContabeisDao eventosContabeisDao) {
        this.eventosContabeisDao = eventosContabeisDao;
    }

    public PanGerarDeclaracao getPanGD() {
        return this.panGD;
    }

    public void setPanGD(PanGerarDeclaracao panGD) {
        this.panGD = panGD;
    }

    public DemonstrativoRateioMensalDao getRateioMensalDao() {
        return this.rateioMensalDao;
    }

    public void setRateioMensalDao(DemonstrativoRateioMensalDao rateioMensalDao) {
        this.rateioMensalDao = rateioMensalDao;
    }
}

