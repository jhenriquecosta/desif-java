
package br.gov.pbh.desif.dao;

import br.gov.pbh.desif.base.dao.BaseDao;
import java.util.Date;
import java.util.List;

public interface BalanceteAnaliticoMensalDao
    extends BaseDao
{

    public abstract boolean verificaExistenciaCodeDependencia(String s);

    public abstract boolean verificaContaRepetida(String s, String s1, Date date);

    public abstract boolean verificaZeroPrimeiraOcorrenciaConta(String s, String s1, String s2);

    public abstract String verificaSaldoFinalCompetenciaAnterior(String s, String s1, String s2, String s3);

    public abstract boolean verificaUltimaOcorrencia(String s, Date date);

    public abstract List findField(String s, String s1);

    public abstract List listaOcorrenciasContasSemestre(String s, String s1, String s2, String s3);

    public abstract int buscaNumLinhaAtual(String s, String s1, String s2);

    public abstract List buscarListaConta();

    public abstract List buscarListaDependencia(String s);

    public abstract List buscarListaMeses(String s, String s1);

    public abstract String buscarDataCompetencia(String s, String s1, String s2);

    public abstract String buscarSaldoFinal(String s, String s1, String s2);

    public abstract List BuscarContaDependenciaMesCombinado(String s);
}