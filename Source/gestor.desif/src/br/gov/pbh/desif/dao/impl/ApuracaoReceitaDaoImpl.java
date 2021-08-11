
package br.gov.pbh.desif.dao.impl;

import br.gov.pbh.desif.base.dao.impl.BaseDaoImpl;
import br.gov.pbh.desif.dao.ApuracaoReceitaDao;
import br.gov.pbh.desif.model.pojo.ApuracaoReceita;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;

public class ApuracaoReceitaDaoImpl extends BaseDaoImpl
    implements ApuracaoReceitaDao
{

    public ApuracaoReceitaDaoImpl()
    {
    }

    public Class getReferenceClass()
    {
        return br.gov.pbh.desif.model.pojo.ApuracaoReceita.class;
    }

    public boolean verificaUnicidade(String codDependencia, String codSubTitulo, String codTribDesif, Double aliqIssqn)
    {
        List result = null;
        boolean resp = false;
        Query q = getConexao().createQuery("FROM ApuracaoReceita a where codigoDependencia = :codDependencia and codigoSubTitulo = :codSubTitulo and codigoTributacaoDesIf = :codTribDesif and valorAliquotaIssqn = :aliqIssqn");
        q.setString("codDependencia", codDependencia);
        q.setString("codSubTitulo", codSubTitulo);
        q.setString("codTribDesif", codTribDesif);
        q.setDouble("aliqIssqn", aliqIssqn.doubleValue());
        result = q.list();
        if(result.size() > 1)
            resp = true;
        return resp;
    }

    public List somaConsolidacao1(Double aliqIssqn)
    {
        Query q = getConexao().createQuery("SELECT SUM(valorReceitaDeclarada), SUM(valorDeducaoReceitaDeclarada), SUM(valorIncentivoFiscal) FROM ApuracaoReceita as ap WHERE valorAliquotaIssqn = :aliqIssqn and motivoNaoExigibilidade is null");
        q.setDouble("aliqIssqn", aliqIssqn.doubleValue());
        List result = q.list();
        return result;
    }

    public List somaConsolidacao3(Double aliqIssqn, String cnpj)
    {
        List result = null;
        Query q = getConexao().createQuery("SELECT SUM(ap.valorReceitaDeclarada), SUM(ap.valorDeducaoReceitaDeclarada), SUM(ap.valorIncentivoFiscal) FROM ApuracaoReceita as ap WHERE ap.cnpj = :cnpj and ap.valorAliquotaIssqn = :aliqIssqn and ap.motivoNaoExigibilidade is null");
        q.setDouble("aliqIssqn", aliqIssqn.doubleValue());
        q.setString("cnpj", cnpj);
        result = q.list();
        return result;
    }

    public boolean verificaExisteRegistroDependenciaUnificadaConsolidacao1(Double aliqIssqn)
    {
        List result = null;
        boolean resp = false;
        Query q = getConexao().createQuery("SELECT ap FROM ApuracaoReceita as ap WHERE valorAliquotaIssqn = :aliqIssqn");
        q.setDouble("aliqIssqn", aliqIssqn.doubleValue());
        result = q.list();
        if(result.size() > 0)
            resp = true;
        return resp;
    }

    public boolean verificaExisteRegistroDependenciaUnificadaConsolidacao2(String codTribDesif, Double aliqIssqn)
    {
        List result = null;
        boolean resp = false;
        Query q = getConexao().createQuery("SELECT ap FROM ApuracaoReceita as ap WHERE  codigoTributacaoDesIf = :codTribDesif and valorAliquotaIssqn = :aliqIssqn");
        q.setString("codTribDesif", codTribDesif);
        q.setDouble("aliqIssqn", aliqIssqn.doubleValue());
        result = q.list();
        if(result.size() > 0)
            resp = true;
        return resp;
    }

    public boolean verificaExisteRegistroDependenciaUnificadaConsolidacao3(Double aliqIssqn, String cnpj)
    {
        List result = null;
        boolean resp = false;
        Query q = getConexao().createQuery("SELECT ap FROM ApuracaoReceita as ap, IdentificacaoDependencia as dep, IssqnMensal as issqn WHERE issqn.cnpj = :cnpj and dep.codigoDependencia = ap.codigoDependencia and ap.valorAliquotaIssqn = :aliqIssqn");
        q.setDouble("aliqIssqn", aliqIssqn.doubleValue());
        q.setString("cnpj", cnpj);
        result = q.list();
        if(result.size() > 0)
            resp = true;
        return resp;
    }

    public boolean verificaExisteRegistroDependenciaUnificadaConsolidacao4(String codTribDesif, Double aliqIssqn)
    {
        List result = null;
        boolean resp = false;
        Query q = getConexao().createQuery("SELECT ap FROM ApuracaoReceita as ap, IdentificacaoDependencia as dep, IssqnMensal as issqn WHERE issqn.cnpj = dep.cnpjUnificado and dep.codigoDependencia = ap.codigoDependencia and ap.codigoTributacaoDesIf = :codTribDesif and ap.valorAliquotaIssqn = :aliqIssqn");
        q.setString("codTribDesif", codTribDesif);
        q.setDouble("aliqIssqn", aliqIssqn.doubleValue());
        result = q.list();
        if(result.size() > 0)
            resp = true;
        return resp;
    }

    public boolean verificaExistenciaCodeDependencia(String codDependencia)
    {
        boolean resp = false;
        Query q = getConexao().createQuery("SELECT apu FROM ApuracaoReceita as apu WHERE apu.codigoDependencia = :codDependencia");
        q.setString("codDependencia", codDependencia);
        q.setMaxResults(1);
        if(q.list().size() >= 1)
            resp = true;
        return resp;
    }

    public void updateAll(String cnpj)
    {
        Query q = getConexao().createQuery("update ApuracaoReceita set cnpj = :cnpj");
        q.setString("cnpj", cnpj);
        q.executeUpdate();
    }

    public void updateCodDependencia(String cnpj, String codDependencia)
    {
        Query q = getConexao().createQuery("update ApuracaoReceita set cnpj = :cnpj where codigoDependencia = :codDependencia");
        q.setString("cnpj", cnpj);
        q.setString("codDependencia", codDependencia);
        q.executeUpdate();
    }

    public List buscaDadosAliquotaCnpj()
    {
        List result = null;
        Query q = getConexao().createQuery("SELECT valorAliquotaIssqn, cnpj FROM ApuracaoReceita GROUP BY  valorAliquotaIssqn, cnpj");
        result = q.list();
        return result;
    }

    public List verificaExisteCnpj0440(String cnpj)
    {
        List result = null;
        Query q = getConexao().createQuery("FROM ApuracaoReceita WHERE cnpj = :cnpj");
        q.setString("cnpj", cnpj);
        result = q.list();
        return result;
    }

    public List buscaRegistros0430ComMovimento()
    {
        List result = null;
        Query q = getConexao().createQuery("FROM ApuracaoReceita WHERE id NOT IN (FROM ApuracaoReceita WHERE valorCreditoMensal = 0 and valorDebitoMensal = 0 and valorReceitaDeclarada = 0 and valorBaseCalculo = 0)");
        result = q.list();
        ApuracaoReceita objeto;
        for(Iterator it = result.iterator(); it.hasNext(); getConexao().refresh(objeto))
            objeto = (ApuracaoReceita)it.next();

        return result;
    }
}