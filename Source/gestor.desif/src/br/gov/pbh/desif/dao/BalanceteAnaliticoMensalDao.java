/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.dao;

import br.gov.pbh.desif.base.dao.BaseDao;
import java.util.Date;
import java.util.List;

public interface BalanceteAnaliticoMensalDao
extends BaseDao {
    public boolean verificaExistenciaCodeDependencia(String var1);

    public boolean verificaContaRepetida(String var1, String var2, Date var3);

    public boolean verificaZeroPrimeiraOcorrenciaConta(String var1, String var2, String var3);

    public String verificaSaldoFinalCompetenciaAnterior(String var1, String var2, String var3, String var4);

    public boolean verificaUltimaOcorrencia(String var1, Date var2);

    public List findField(String var1, String var2);

    public List listaOcorrenciasContasSemestre(String var1, String var2, String var3, String var4);

    public int buscaNumLinhaAtual(String var1, String var2, String var3);

    public List buscarListaConta();

    public List buscarListaDependencia(String var1);

    public List buscarListaMeses(String var1, String var2);

    public String buscarDataCompetencia(String var1, String var2, String var3);

    public String buscarSaldoFinal(String var1, String var2, String var3);

    public List BuscarContaDependenciaMesCombinado(String var1);
}

