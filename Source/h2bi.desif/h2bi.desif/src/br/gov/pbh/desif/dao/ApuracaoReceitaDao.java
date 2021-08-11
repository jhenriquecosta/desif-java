
package br.gov.pbh.desif.dao;

import br.gov.pbh.desif.base.dao.BaseDao;
import java.util.List;

public interface ApuracaoReceitaDao
    extends BaseDao
{

    public abstract boolean verificaUnicidade(String s, String s1, String s2, Double double1);

    public abstract boolean verificaExisteRegistroDependenciaUnificadaConsolidacao1(Double double1);

    public abstract boolean verificaExisteRegistroDependenciaUnificadaConsolidacao2(String s, Double double1);

    public abstract boolean verificaExisteRegistroDependenciaUnificadaConsolidacao3(Double double1, String s);

    public abstract boolean verificaExisteRegistroDependenciaUnificadaConsolidacao4(String s, Double double1);

    public abstract List somaConsolidacao1(Double double1);

    public abstract List somaConsolidacao3(Double double1, String s);

    public abstract void updateAll(String s);

    public abstract void updateCodDependencia(String s, String s1);

    public abstract boolean verificaExistenciaCodeDependencia(String s);

    public abstract List buscaDadosAliquotaCnpj();

    public abstract List verificaExisteCnpj0440(String s);

    public abstract List buscaRegistros0430ComMovimento();
}