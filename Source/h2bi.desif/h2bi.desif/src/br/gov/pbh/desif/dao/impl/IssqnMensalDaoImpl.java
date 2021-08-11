
package br.gov.pbh.desif.dao.impl;

import br.gov.pbh.desif.base.dao.impl.BaseDaoImpl;
import br.gov.pbh.desif.dao.IssqnMensalDao;
import br.gov.pbh.desif.model.pojo.IssqnMensal;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class IssqnMensalDaoImpl extends BaseDaoImpl
    implements IssqnMensalDao
{

    public IssqnMensalDaoImpl()
    {
    }

    public Class getReferenceClass()
    {
         return br.gov.pbh.desif.model.pojo.IssqnMensal.class;
    }

    public boolean cnpjUnficadoExisteDemonstrativoIssqnMensal(String cnpj)
    {
        boolean resp = false;
        Query q = getConexao().createQuery("SELECT issqn FROM IssqnMensal as issqn WHERE issqn.cnpj = :cnpj");
        q.setString("cnpj", cnpj);
        q.setMaxResults(2);
        return resp;
    }

    public List verificaExistenciaRegistro0440Consolidacao1(Double aliqIssqn)
    {
        List result = null;
        Query q = getConexao().createQuery("SELECT iss.id  FROM IssqnMensal as iss, ApuracaoReceita as ap  WHERE iss.cnpj = ap.cnpj and iss.valorAliquotaIssqn = :aliqIssqn and ((ap.valorBaseCalculo * ap.valorAliquotaIssqn / 100) - ap.valorIncentivoFiscal) >= 0 and ap.motivoNaoExigibilidade is null");
        q.setDouble("aliqIssqn", aliqIssqn.doubleValue());
        result = q.list();
        return result;
    }

    public List verificaExistenciaRegistro0440Consolidacao3(String aliqIssqn, String cnpj)
    {
        List result = null;
        Query q = getConexao().createQuery("SELECT iss.id  FROM IssqnMensal as iss, ApuracaoReceita  as ap WHERE iss.cnpj = ap.cnpj and iss.valorAliquotaIssqn = :aliqIssqn and iss.cnpj = :cnpj and ((ap.valorBaseCalculo * ap.valorAliquotaIssqn / 100) - ap.valorIncentivoFiscal) > 0 and ap.motivoNaoExigibilidade is null group by iss.id");
        q.setString("aliqIssqn", aliqIssqn);
        q.setString("cnpj", cnpj);
        result = q.list();
        return result;
    }

    public List verificaUnicidadeConsolidacao1(String aliqIssqn)
    {
        List result = null;
        Query q = getConexao().createQuery("SELECT id  FROM IssqnMensal WHERE valorAliquotaIssqn = :aliqIssqn ");
        q.setString("aliqIssqn", aliqIssqn);
        result = q.list();
        return result;
    }

    public List verificaUnicidadeConsolidacao3(String aliqIssqn, String cnpj)
    {
        List result = null;
        Query q = getConexao().createQuery("SELECT id  FROM IssqnMensal WHERE valorAliquotaIssqn = :aliqIssqn and cnpj = :cnpj ");
        q.setString("aliqIssqn", aliqIssqn);
        q.setString("cnpj", cnpj);
        result = q.list();
        return result;
    }
}