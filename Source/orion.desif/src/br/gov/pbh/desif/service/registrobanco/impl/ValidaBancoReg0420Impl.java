

package br.gov.pbh.desif.service.registrobanco.impl;

import br.gov.pbh.desif.dao.*;
import br.gov.pbh.desif.model.pojo.DemonstrativoRateioMensal;
import br.gov.pbh.desif.model.pojo.IdentificacaoDeclaracao;
import br.gov.pbh.desif.model.registros.Data;
import br.gov.pbh.desif.model.registros.RegUtil;
import br.gov.pbh.desif.service.contexto.Contexto;
import br.gov.pbh.desif.service.registrobanco.ValidaBancoReg0420;
import br.gov.pbh.desif.view.telas.PanGerarDeclaracao;
import java.util.Iterator;
import java.util.List;

public class ValidaBancoReg0420Impl
    implements ValidaBancoReg0420
{

    private DemonstrativoRateioMensal rateioMensal;
    private DemonstrativoRateioMensalDao rateioMensalDao;
    private RegUtil regUtil;
    private String registro;
    private Data dt;
    private IdentificacaoDeclaracao declaracao;
    private IdentDeclaracaoDao declaracaoDao;
    private PanGerarDeclaracao panGD;
    private IdentDependenciaDao dependenciaDao;
    private EventosContabeisDao eventosContabeisDao;

    public ValidaBancoReg0420Impl()
    {
        registro = "0420";
    }

    public void executar()
        throws Exception
    {
        regUtil = new RegUtil();
        dt = new Data();
        declaracao = (IdentificacaoDeclaracao)declaracaoDao.load(new Integer(Integer.parseInt("1")));
        panGD = (PanGerarDeclaracao)Contexto.getObject("panGD");
        List rateioMensalList = rateioMensalDao.findAll();
        double incremento = regUtil.incremetoPorcentagem(65D, rateioMensalList.size());
        double sentinela = 80D;
        int atualizar = 0;
        for(Iterator i = rateioMensalList.iterator(); i.hasNext(); verificarCodDepe())
        {
            if(sentinela < 100D)
            {
                atualizar = (int)sentinela;
                panGD.atualizarProgressoValidacao(atualizar, 100);
                sentinela += incremento;
            }
            rateioMensal = (DemonstrativoRateioMensal)i.next();
            verificaExistenciaRegistroRateioMensal();
            verificaPeriodoCompetencia();
        }

    }

    public void verificarCodDepe()
        throws Exception
    {
        int coluna = 3;
        List resp = dependenciaDao.findField("codigoDependencia", rateioMensal.getCodigoDependencia());
        if(resp.size() == 0)
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo da depend\352ncia: ").append(rateioMensal.getCodigoDependencia()).toString();
            regUtil.setErro(rateioMensal.getNumLinha().longValue(), "EG006", coluna, (short)2, registro, txtSolucao);
        }
    }

    public void verificaPeriodoCompetencia()
        throws Exception
    {
        int coluna = 2;
        boolean dentroPeriodoCompetencia = declaracaoDao.verificaDentroCompetencia(rateioMensal.getAnoMesCompetencia());
        if(!dentroPeriodoCompetencia)
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo da depend\352ncia: ").append(rateioMensal.getCodigoDependencia()).append(", Compet\352ncia da declara\347\343o: de ").append(dt.formataData(declaracao.getDataInicioCompetencia(), "yyyyMMdd")).append(" a ").append(dt.formataData(declaracao.getDataFimCompetencia(), "yyyyMMdd")).append(" Compet\352ncia informada: ").append(dt.formataData(rateioMensal.getAnoMesCompetencia(), "yyyyMMdd")).toString();
            regUtil.setErro(rateioMensal.getNumLinha().longValue(), "EC014", coluna, (short)2, registro, txtSolucao);
        }
    }

    public void verificaExistenciaRegistroRateioMensal()
        throws Exception
    {
        int coluna = 8;
        if(!eventosContabeisDao.verificaExistenciaCodigoEvento(rateioMensal.getCodigoEvento().intValue()))
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo da depend\352ncia: ").append(rateioMensal.getCodigoDependencia()).append(", Per\355odo de Compet\352ncia: ").append(dt.formataData(rateioMensal.getAnoMesCompetencia(), "yyyyMMdd")).append(", Valor rateado: ").append(rateioMensal.getValorReceitaRateada()).append(", Tipo de Partida: ").append(rateioMensal.getTipoPartida()).append(", Codigo do Evento: ").append(rateioMensal.getCodigoEvento()).toString();
            regUtil.setErro(rateioMensal.getCodigoEvento().intValue(), "EC019", coluna, (short)2, registro, txtSolucao);
        }
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

    public EventosContabeisDao getEventosContabeisDao()
    {
        return eventosContabeisDao;
    }

    public void setEventosContabeisDao(EventosContabeisDao eventosContabeisDao)
    {
        this.eventosContabeisDao = eventosContabeisDao;
    }

    public PanGerarDeclaracao getPanGD()
    {
        return panGD;
    }

    public void setPanGD(PanGerarDeclaracao panGD)
    {
        this.panGD = panGD;
    }

    public DemonstrativoRateioMensalDao getRateioMensalDao()
    {
        return rateioMensalDao;
    }

    public void setRateioMensalDao(DemonstrativoRateioMensalDao rateioMensalDao)
    {
        this.rateioMensalDao = rateioMensalDao;
    }
}
