
package br.gov.pbh.desif.dao;

import br.gov.pbh.desif.base.dao.BaseDao;
import java.util.List;

public interface IssqnMensalDao
    extends BaseDao
{

    public abstract boolean cnpjUnficadoExisteDemonstrativoIssqnMensal(String s);

    public abstract List verificaExistenciaRegistro0440Consolidacao1(Double double1);

    public abstract List verificaExistenciaRegistro0440Consolidacao3(String s, String s1);

    public abstract List verificaUnicidadeConsolidacao1(String s);

    public abstract List verificaUnicidadeConsolidacao3(String s, String s1);
}