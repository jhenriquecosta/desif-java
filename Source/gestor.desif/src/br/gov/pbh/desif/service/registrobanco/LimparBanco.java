/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.service.registrobanco;

import br.gov.pbh.desif.dao.LimparBancoDao;
import br.gov.pbh.desif.dao.impl.LimparBancoDaoImpl;
import java.io.Serializable;

public class LimparBanco {
    private LimparBancoDao limBancoDao = new LimparBancoDaoImpl();

    public LimparBanco() {
        this.limparTabelaDeclaracao();
        this.limparTabelaDependencia();
        this.limparTabelaApuracaoSubIssqn();
        this.limparTabelaOrigemCreditoCompensar();
        this.limparTabelaDemonstrativoMensalIssqn();
        this.limparTabelaErroSistema();
        this.limparTabelaAlertaSistema();
        this.limparTabelaTarifasServicosInstituicao();
        this.limparTabelaIdentificacaoServicosRemuneracaoVariavel();
        this.limparTabelaPgccsPaiFilho();
        this.limparTabelaPGCCS();
        this.limparTabelaBalanceteAnalitico();
        this.limparTabelaDemonstrativoRateio();
    }

    private void limparTabelaDeclaracao() {
        String nomeTabela = "IdentificacaoDeclaracao";
        Serializable i = this.limBancoDao.deleteAll(nomeTabela);
    }

    private void limparTabelaDependencia() {
        String nomeTabela = "IdentificacaoDependencia";
        this.limBancoDao.deleteAll(nomeTabela);
    }

    private void limparTabelaApuracaoSubIssqn() {
        String nomeTabela = "ApuracaoReceita";
        this.limBancoDao.deleteAll(nomeTabela);
    }

    private void limparTabelaOrigemCreditoCompensar() {
        String nomeTabela = "OrigemCreditoCompensar";
        this.limBancoDao.deleteAll(nomeTabela);
    }

    private void limparTabelaDemonstrativoMensalIssqn() {
        String nomeTabela = "IssqnMensal";
        this.limBancoDao.deleteAll(nomeTabela);
    }

    private void limparTabelaErroSistema() {
        String nomeTabela = "SistemaErro";
        this.limBancoDao.deleteAll(nomeTabela);
    }

    private void limparTabelaAlertaSistema() {
        String nomeTabela = "SistemaAlerta";
        this.limBancoDao.deleteAll(nomeTabela);
    }

    private void limparTabelaPGCCS() {
        String nomeTabela = "PlanoGeralContaComentado";
        this.limBancoDao.deleteAll(nomeTabela);
    }

    private void limparTabelaPgccsPaiFilho() {
        String nomeTabela = "PgccsPaiFilho";
        this.limBancoDao.deleteAll(nomeTabela);
    }

    private void limparTabelaTarifasServicosInstituicao() {
        String nomeTabela = "TarifaServico";
        this.limBancoDao.deleteAll(nomeTabela);
    }

    private void limparTabelaIdentificacaoServicosRemuneracaoVariavel() {
        String nomeTabela = "IdentServicosRemunVariavel";
        this.limBancoDao.deleteAll(nomeTabela);
    }

    private void limparTabelaBalanceteAnalitico() {
        String nomeTabela = "BalanceteAnaliticoMensal";
        this.limBancoDao.deleteAll(nomeTabela);
    }

    private void limparTabelaDemonstrativoRateio() {
        String nomeTabela = "DemonstrativoRateioMensal";
        this.limBancoDao.deleteAll(nomeTabela);
    }
}

