
package br.gov.pbh.desif.service.registrobanco;

import br.gov.pbh.desif.dao.LimparBancoDao;
import br.gov.pbh.desif.dao.impl.LimparBancoDaoImpl;

public class LimparBanco
{

    private LimparBancoDao limBancoDao;

    public LimparBanco()
    {
        limBancoDao = new LimparBancoDaoImpl();
        limparTabelaDeclaracao();
        limparTabelaDependencia();
        limparTabelaApuracaoSubIssqn();
        limparTabelaOrigemCreditoCompensar();
        limparTabelaDemonstrativoMensalIssqn();
        limparTabelaErroSistema();
        limparTabelaAlertaSistema();
        limparTabelaTarifasServicosInstituicao();
        limparTabelaIdentificacaoServicosRemuneracaoVariavel();
        limparTabelaPgccsPaiFilho();
        limparTabelaPGCCS();
        limBancoDao.limparSessao();
        limparTabelaBalanceteAnalitico();
        limparTabelaDemonstrativoRateio();
    }

    private void limparTabelaDeclaracao()
    {
        String nomeTabela = "IdentificacaoDeclaracao";
        java.io.Serializable i = limBancoDao.deleteAll(nomeTabela);
    }

    private void limparTabelaDependencia()
    {
        String nomeTabela = "IdentificacaoDependencia";
        limBancoDao.deleteAll(nomeTabela);
    }

    private void limparTabelaApuracaoSubIssqn()
    {
        String nomeTabela = "ApuracaoReceita";
        limBancoDao.deleteAll(nomeTabela);
    }

    private void limparTabelaOrigemCreditoCompensar()
    {
        String nomeTabela = "OrigemCreditoCompensar";
        limBancoDao.deleteAll(nomeTabela);
    }

    private void limparTabelaDemonstrativoMensalIssqn()
    {
        String nomeTabela = "IssqnMensal";
        limBancoDao.deleteAll(nomeTabela);
    }

    private void limparTabelaErroSistema()
    {
        String nomeTabela = "SistemaErro";
        limBancoDao.deleteAll(nomeTabela);
    }

    private void limparTabelaAlertaSistema()
    {
        String nomeTabela = "SistemaAlerta";
        limBancoDao.deleteAll(nomeTabela);
    }

    private void limparTabelaPGCCS()
    {
        String nomeTabela = "PlanoGeralContaComentado";
        limBancoDao.deleteAll(nomeTabela);
    }

    private void limparTabelaPgccsPaiFilho()
    {
        String nomeTabela = "PgccsPaiFilho";
        limBancoDao.deleteAll(nomeTabela);
    }

    private void limparTabelaTarifasServicosInstituicao()
    {
        String nomeTabela = "TarifaServico";
        limBancoDao.deleteAll(nomeTabela);
    }

    private void limparTabelaIdentificacaoServicosRemuneracaoVariavel()
    {
        String nomeTabela = "IdentServicosRemunVariavel";
        limBancoDao.deleteAll(nomeTabela);
    }

    private void limparTabelaBalanceteAnalitico()
    {
        String nomeTabela = "BalanceteAnaliticoMensal";
        limBancoDao.deleteAll(nomeTabela);
    }

    private void limparTabelaDemonstrativoRateio()
    {
        String nomeTabela = "DemonstrativoRateioMensal";
        limBancoDao.deleteAll(nomeTabela);
    }
}
