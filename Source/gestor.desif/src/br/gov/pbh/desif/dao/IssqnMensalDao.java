/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.dao;

import br.gov.pbh.desif.base.dao.BaseDao;
import java.util.List;

public interface IssqnMensalDao
extends BaseDao {
    public boolean cnpjUnficadoExisteDemonstrativoIssqnMensal(String var1);

    public List verificaExistenciaRegistro0440Consolidacao1(Double var1);

    public List verificaExistenciaRegistro0440Consolidacao3(String var1, String var2);

    public List verificaUnicidadeConsolidacao1(String var1);

    public List verificaUnicidadeConsolidacao3(String var1, String var2);
}

