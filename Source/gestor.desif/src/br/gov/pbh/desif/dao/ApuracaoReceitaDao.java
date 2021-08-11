/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.dao;

import br.gov.pbh.desif.base.dao.BaseDao;
import java.util.List;

public interface ApuracaoReceitaDao
extends BaseDao {
    public boolean verificaUnicidade(String var1, String var2, String var3, Double var4);

    public boolean verificaExisteRegistroDependenciaUnificadaConsolidacao1(Double var1);

    public boolean verificaExisteRegistroDependenciaUnificadaConsolidacao2(String var1, Double var2);

    public boolean verificaExisteRegistroDependenciaUnificadaConsolidacao3(Double var1, String var2);

    public boolean verificaExisteRegistroDependenciaUnificadaConsolidacao4(String var1, Double var2);

    public List somaConsolidacao1(Double var1);

    public List somaConsolidacao3(Double var1, String var2);

    public void updateAll(String var1);

    public void updateCodDependencia(String var1, String var2);

    public boolean verificaExistenciaCodeDependencia(String var1);

    public List buscaDadosAliquotaCnpj();

    public List verificaExisteCnpj0440(String var1);

    public List buscaRegistros0430ComMovimento();
}

