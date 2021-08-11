
package br.gov.pbh.desif.dao.impl;

import br.gov.pbh.desif.base.dao.impl.BaseDaoImpl;
import br.gov.pbh.desif.dao.VersaoDocumentacaoDao;
import br.gov.pbh.desif.model.pojo.VersaoDocumentacao;
import java.util.Date;
import java.util.List;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

public class VersaoDocumentacaoDaoImpl extends BaseDaoImpl
    implements VersaoDocumentacaoDao
{

    public VersaoDocumentacaoDaoImpl()
    {
    }

    public Class getReferenceClass()
    {
        return br.gov.pbh.desif.model.pojo.VersaoDocumentacao.class;
    }

    public List findField(String campo, String valor)
    {
        Criteria c = getConexao().createCriteria(getReferenceClass());
        c.add(Restrictions.eq(campo, valor));
        List result = c.list();
        return result;
    }

    public String buscaNumVersaoDocumentacao()
    {
        Query q = getConexao().createQuery("SELECT numVersaoDocumentacao FROM VersaoDocumentacao WHERE dataFimVersao IS NULL ORDER BY dataInicioVersao DESC ");
        q.setMaxResults(1);
        String numVersaoDocumentacao = q.list().get(0).toString();
        return numVersaoDocumentacao;
    }

    public Date buscaDataInicioVersaoDocumentacao(String numVersaoDocumentacao)
    {
        Query q = getConexao().createQuery("SELECT dataInicioVersao FROM VersaoDocumentacao WHERE numVersaoDocumentacao = :numVersaoDocumentacao");
        q.setString("numVersaoDocumentacao", numVersaoDocumentacao);
        q.setMaxResults(1);
        Date dataInicioVersaoDocumentacao = (Date)q.list().get(0);
        return dataInicioVersaoDocumentacao;
    }

    public Date buscaDataFimVersaoDocumentacao(String numVersaoDocumentacao)
    {
        Date dataInicioVersaoDocumentacao = null;
        Query q = getConexao().createQuery("SELECT dataFimVersao FROM VersaoDocumentacao WHERE numVersaoDocumentacao = :numVersaoDocumentacao");
        q.setString("numVersaoDocumentacao", numVersaoDocumentacao);
        q.setMaxResults(1);
        if(q.list().size() > 0 && q.list().get(0) != null)
            dataInicioVersaoDocumentacao = (Date)q.list().get(0);
        return dataInicioVersaoDocumentacao;
    }
}