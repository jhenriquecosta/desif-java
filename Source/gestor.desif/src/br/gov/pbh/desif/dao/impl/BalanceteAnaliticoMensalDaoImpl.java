package br.gov.pbh.desif.dao.impl;

import br.gov.pbh.desif.base.dao.impl.BaseDaoImpl;
import br.gov.pbh.desif.dao.BalanceteAnaliticoMensalDao;
import br.gov.pbh.desif.model.pojo.BalanceteAnaliticoMensal;
import java.util.Date;
import java.util.List;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

public class BalanceteAnaliticoMensalDaoImpl extends BaseDaoImpl
    implements BalanceteAnaliticoMensalDao
{

    public BalanceteAnaliticoMensalDaoImpl()
    {
    }

    public Class getReferenceClass()
    {
        return br.gov.pbh.desif.model.pojo.BalanceteAnaliticoMensal.class;
    }

    public List findField(String campo, String valor)
    {
        Criteria c = getConexao().createCriteria(getReferenceClass());
        c.add(Restrictions.eq(campo, valor));
        List result = c.list();
        return result;
    }

    public boolean verificaExistenciaCodeDependencia(String codDependencia)
    {
        boolean resp = false;
        Query q = getConexao().createQuery("SELECT bam.codigoDependencia FROM BalanceteAnaliticoMensal as bam WHERE bam.codigoDependencia = :codDependencia");
        q.setString("codDependencia", codDependencia);
        q.setMaxResults(1);
        if(q.list().size() >= 1)
            resp = true;
        return resp;
    }

    public boolean verificaContaRepetida(String codConta, String codDependencia, Date anoMesCompetencia)
    {
        boolean resp = false;
        Query q = getConexao().createQuery("SELECT COUNT(ba.conta) FROM BalanceteAnaliticoMensal AS ba WHERE ba.codigoDependencia = :codDependencia AND ba.anoMesCompetencia = :anoMesCompetencia AND ba.conta = :codConta");
        q.setString("codConta", codConta);
        q.setString("codDependencia", codDependencia);
        q.setDate("anoMesCompetencia", anoMesCompetencia);
        int total = Integer.parseInt(q.list().get(0).toString());
        if(total > 1)
            resp = true;
        return resp;
    }

    public boolean verificaZeroPrimeiraOcorrenciaConta(String codConta, String codDependencia, String semestre)
    {
        boolean resp = false;
        Query q = getConexao().createQuery("SELECT ba.saldoInicial FROM BalanceteAnaliticoMensal AS ba WHERE (ba.conta = :codConta) AND (MONTH(ba.anoMesCompetencia) > :semestre) AND (ba.codigoDependencia = :codDependencia) ORDER BY ba.anoMesCompetencia");
        q.setString("codConta", codConta);
        q.setString("codDependencia", codDependencia);
        q.setString("semestre", semestre);
        q.setMaxResults(1);
        double valorSaldoInicial = Double.parseDouble(q.list().get(0).toString());
        if(valorSaldoInicial == 0.0D)
            resp = true;
        return resp;
    }

    public String verificaSaldoFinalCompetenciaAnterior(String codConta, String mes, String ano, String codDependencia)
    {
        String valorSaldoFinal = "0";
        String inicioPeriodo = "0";
        Query q = getConexao().createQuery("SELECT ba.saldoFinal FROM BalanceteAnaliticoMensal AS ba WHERE ((ba.conta = :codConta) AND (MONTH(ba.anoMesCompetencia) < :mes ) AND (MONTH(ba.anoMesCompetencia) > :inicioPeriodo ) AND (YEAR(ba.anoMesCompetencia) = :ano) AND (ba.codigoDependencia = :codDependencia)) ORDER BY ba.anoMesCompetencia desc");
        if(Integer.parseInt(mes) >= 1 && Integer.parseInt(mes) <= 6)
            inicioPeriodo = "0";
        else
            inicioPeriodo = "6";
        q.setString("codConta", codConta);
        q.setString("mes", mes);
        q.setString("inicioPeriodo", inicioPeriodo);
        q.setString("ano", ano);
        q.setString("codDependencia", codDependencia);
        q.setMaxResults(1);
        if(q.list().size() > 0)
            valorSaldoFinal = q.list().get(0).toString();
        return valorSaldoFinal;
    }

    public boolean verificaUltimaOcorrencia(String codConta, Date anoMesCompetencia)
    {
        boolean resp = false;
        Query q = getConexao().createQuery("SELECT count(ba.saldoFinal) FROM BalanceteAnaliticoMensal AS ba WHERE ba.conta = :codConta and ba.anoMesCompetencia > :anoMesCompetencia");
        q.setString("codConta", codConta);
        q.setDate("anoMesCompetencia", anoMesCompetencia);
        int total = Integer.parseInt(q.list().get(0).toString());
        if(total >= 1)
            resp = true;
        return resp;
    }

    public List listaOcorrenciasContasSemestre(String conta, String mesFim, String mesInicial, String codDependencia)
    {
        Query q = getConexao().createQuery("select month(anoMesCompetencia) from BalanceteAnaliticoMensal where conta = :conta and codigoDependencia = :codDependencia and month(anoMesCompetencia) <= :mesFim and month(anoMesCompetencia) >= :mesInicial order by anoMesCompetencia desc");
        q.setString("conta", conta);
        q.setString("mesInicial", mesInicial);
        q.setString("mesFim", mesFim);
        q.setString("codDependencia", codDependencia);
        List listaSemestre = q.list();
        return listaSemestre;
    }

    public int buscaNumLinhaAtual(String conta, String mes, String codigoDependencia)
    {
        Query q = getConexao().createQuery("select ba.linhaBalanceteAnaliticoMensal from BalanceteAnaliticoMensal as ba where ba.conta = :conta and ba.codigoDependencia = :codigoDependencia and month(ba.anoMesCompetencia) = :mes");
        q.setString("conta", conta);
        q.setString("mes", mes);
        q.setString("codigoDependencia", codigoDependencia);
        int resultado = Integer.parseInt(q.list().get(0).toString());
        return resultado;
    }

    public List buscarListaConta()
    {
        Query q = getConexao().createQuery("SELECT distinct ba.conta from BalanceteAnaliticoMensal as ba");
        List resultado = q.list();
        return resultado;
    }

    public List buscarListaDependencia(String conta)
    {
        Query q = getConexao().createQuery("SELECT distinct ba.codigoDependencia from BalanceteAnaliticoMensal as ba where ba.conta = :conta");
        q.setString("conta", conta);
        List resultado = q.list();
        return resultado;
    }

    public List buscarListaMeses(String conta, String codigoDependencia)
    {
        Query q = getConexao().createQuery("select month(ba.anoMesCompetencia) from BalanceteAnaliticoMensal as ba where ba.conta = :conta and ba.codigoDependencia = :codigoDependencia");
        q.setString("conta", conta);
        q.setString("codigoDependencia", codigoDependencia);
        List resultado = q.list();
        return resultado;
    }

    public String buscarDataCompetencia(String conta, String codigoDependencia, String mes)
    {
        Query q = getConexao().createQuery("select ba.anoMesCompetencia from BalanceteAnaliticoMensal as ba where ba.conta = :conta and ba.codigoDependencia = :codigoDependencia and month(ba.anoMesCompetencia) = :mes");
        q.setString("conta", conta);
        q.setString("codigoDependencia", codigoDependencia);
        q.setString("mes", mes);
        String resultado = q.list().get(0).toString();
        return resultado;
    }

    public String buscarSaldoFinal(String conta, String codigoDependencia, String mes)
    {
        Query q = getConexao().createQuery("select ba.saldoFinal from BalanceteAnaliticoMensal as ba where ba.conta = :conta and ba.codigoDependencia = :codigoDependencia and month(ba.anoMesCompetencia) = :mes");
        q.setString("conta", conta);
        q.setString("codigoDependencia", codigoDependencia);
        q.setString("mes", mes);
        String resultado = q.list().get(0).toString();
        return resultado;
    }

    public List BuscarContaDependenciaMesCombinado(String conta)
    {
        Query q = getConexao().createQuery("select ba.codigoDependencia, month(ba.anoMesCompetencia), ba.saldoFinal, ba.anoMesCompetencia, ba.linhaBalanceteAnaliticoMensal, ba.saldoInicial, ba.valorCredito, ba.valorDebito from BalanceteAnaliticoMensal as ba where ba.conta = :conta order by ba.codigoDependencia, month(ba.anoMesCompetencia)");
        q.setString("conta", conta);
        List resultado = q.list();
        return resultado;
    }
}