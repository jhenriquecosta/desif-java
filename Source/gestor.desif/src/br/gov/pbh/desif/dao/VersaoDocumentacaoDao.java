/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.dao;

import br.gov.pbh.desif.base.dao.BaseDao;
import java.util.Date;
import java.util.List;

public interface VersaoDocumentacaoDao
extends BaseDao {
    public List findField(String var1, String var2);

    public String buscaNumVersaoDocumentacao();

    public Date buscaDataInicioVersaoDocumentacao(String var1);

    public Date buscaDataFimVersaoDocumentacao(String var1);
}

