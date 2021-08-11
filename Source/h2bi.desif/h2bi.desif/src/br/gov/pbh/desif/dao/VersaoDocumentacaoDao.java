
package br.gov.pbh.desif.dao;

import br.gov.pbh.desif.base.dao.BaseDao;
import java.util.Date;
import java.util.List;

public interface VersaoDocumentacaoDao
    extends BaseDao
{

    public abstract List findField(String s, String s1);

    public abstract String buscaNumVersaoDocumentacao();

    public abstract Date buscaDataInicioVersaoDocumentacao(String s);

    public abstract Date buscaDataFimVersaoDocumentacao(String s);
}