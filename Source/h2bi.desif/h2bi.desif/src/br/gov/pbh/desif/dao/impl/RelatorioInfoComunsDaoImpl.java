
package br.gov.pbh.desif.dao.impl;

import br.gov.pbh.desif.base.dao.impl.BaseDaoImpl;
import br.gov.pbh.desif.dao.RelatorioInfoComunsDao;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

// Referenced classes of package br.gov.pbh.desif.dao.impl:
//            PlanoGeralContaComentadoDaoImpl

public class RelatorioInfoComunsDaoImpl extends BaseDaoImpl
    implements RelatorioInfoComunsDao
{

    public RelatorioInfoComunsDaoImpl()
    {
    }

    public Class getReferenceClass()
    {
        return br.gov.pbh.desif.dao.impl.PlanoGeralContaComentadoDaoImpl.class;
    }

    public List buscaDadosContasICM()
    {
        List result = null;
        Query q = getConexao().createQuery("SELECT distinct pgcc.conta FROM PlanoGeralContaComentado as pgcc order by pgcc.conta");
        result = q.list();
        return result;
    }

    public List buscaDadosCodTributacaoICM()
    {
        List result = null;
        Query q = getConexao().createQuery("SELECT distinct pgcc.codTribDesif FROM PlanoGeralContaComentado as pgcc where pgcc.codTribDesif <> '' order by pgcc.codTribDesif");
        result = q.list();
        return result;
    }

    public List buscaDadosGeraisPGCC2(String codTributacao, String conta)
    {
        List result = null;
        String qryBusca = "FROM PlanoGeralContaComentado as pgcc ";
        String qryParcial = null;
        if(!codTributacao.isEmpty())
            qryParcial = " where pgcc.codTribDesif = :codTribDesif ";
        if(!conta.isEmpty())
            if(qryParcial == null)
                qryParcial = " where pgcc.conta = :conta";
            else
                qryParcial = (new StringBuilder()).append(qryParcial).append(" and pgcc.conta = :conta").toString();
        if(qryParcial == null)
            qryParcial = " order by pgcc.conta";
        else
            qryParcial = (new StringBuilder()).append(qryParcial).append(" order by pgcc.conta").toString();
        Query q = getConexao().createQuery((new StringBuilder()).append(qryBusca).append(qryParcial).toString());
        if(!codTributacao.isEmpty())
            q.setString("codTribDesif", codTributacao);
        if(!conta.isEmpty())
            q.setString("conta", conta);
        result = q.list();
        return result;
    }

    public List buscaDadosIdTarifa()
    {
        List result = null;
        Query q = getConexao().createQuery("SELECT DISTINCT ts.codIdentTarifa FROM TarifaServico AS ts ORDER BY ts.codIdentTarifa");
        result = q.list();
        return result;
    }

    public List buscaDadosCodSubtitulo()
    {
        List result = null;
        Query q = getConexao().createQuery("SELECT DISTINCT ts.codSubtitulo FROM TarifaServico AS ts ORDER BY ts.codSubtitulo");
        result = q.list();
        return result;
    }

    public List buscaDadosGeraisTarServInstituicao(String idTarifa, String codSubtitulo)
    {
        List result = null;
        String qryBusca = "SELECT new br.gov.pbh.desif.model.pojo.TarifaServico(ts.codIdentTarifa, ts.codSubtitulo, ts.descTarifa) FROM TarifaServico as ts ";
        String qryParcial = null;
        if(!idTarifa.isEmpty())
            qryParcial = " where ts.codIdentTarifa = :idTarifa ";
        if(!codSubtitulo.isEmpty())
            if(qryParcial == null)
                qryParcial = " where ts.codSubtitulo = :codSubtitulo";
            else
                qryParcial = (new StringBuilder()).append(qryParcial).append(" and ts.codSubtitulo = :codSubtitulo").toString();
        if(qryParcial == null)
            qryParcial = " order by ts.codIdentTarifa";
        else
            qryParcial = (new StringBuilder()).append(qryParcial).append(" order by ts.codIdentTarifa").toString();
        Query q = getConexao().createQuery((new StringBuilder()).append(qryBusca).append(qryParcial).toString());
        if(!idTarifa.isEmpty())
            q.setString("idTarifa", idTarifa);
        if(!codSubtitulo.isEmpty())
            q.setString("codSubtitulo", codSubtitulo);
        result = q.list();
        return result;
    }

    public List buscaDadosIdServRemVar()
    {
        List result = null;
        Query q = getConexao().createQuery("SELECT DISTINCT srv.codIdentServRemnVariavel FROM IdentServicosRemunVariavel AS srv ORDER BY srv.codIdentServRemnVariavel");
        result = q.list();
        return result;
    }

    public List buscaDadosCodSubtituloRemVar()
    {
        List result = null;
        Query q = getConexao().createQuery("SELECT DISTINCT srv.codSubtitulo FROM IdentServicosRemunVariavel AS srv ORDER BY srv.codSubtitulo");
        result = q.list();
        return result;
    }

    public List buscaDadosGeraisServRemVar(String idServico, String codSubtitulo)
    {
        List result = null;
        String qryBusca = "SELECT new br.gov.pbh.desif.model.pojo.IdentServicosRemunVariavel(srv.codIdentServRemnVariavel, srv.codSubtitulo, srv.descServRemnVariavel) FROM IdentServicosRemunVariavel as srv ";
        String qryParcial = null;
        if(!idServico.isEmpty())
            qryParcial = " where srv.codIdentServRemnVariavel = :idServico ";
        if(!codSubtitulo.isEmpty())
            if(qryParcial == null)
                qryParcial = " where srv.codSubtitulo = :codSubtitulo";
            else
                qryParcial = (new StringBuilder()).append(qryParcial).append(" and srv.codSubtitulo = :codSubtitulo").toString();
        if(qryParcial == null)
            qryParcial = " order by srv.codIdentServRemnVariavel";
        else
            qryParcial = (new StringBuilder()).append(qryParcial).append(" order by srv.codIdentServRemnVariavel").toString();
        Query q = getConexao().createQuery((new StringBuilder()).append(qryBusca).append(qryParcial).toString());
        if(!idServico.isEmpty())
            q.setString("idServico", idServico);
        if(!codSubtitulo.isEmpty())
            q.setString("codSubtitulo", codSubtitulo);
        result = q.list();
        return result;
    }

    public List buscaDadosContaSuperiorPGCC()
    {
        List result = null;
        Query q = getConexao().createQuery("SELECT distinct pgcc.contaSupe FROM PlanoGeralContaComentado as pgcc where pgcc.contaSupe <> '' order by pgcc.contaSupe");
        result = q.list();
        return result;
    }

    public List buscaDadosContaCosifPGCC()
    {
        List result = null;
        Query q = getConexao().createQuery("SELECT distinct pgcc.contaCosif FROM PlanoGeralContaComentado as pgcc order by pgcc.contaCosif");
        result = q.list();
        return result;
    }

    public List buscaDadosGeraisPGCC(String conta, String contaSuperior, String contaCosif, String codTribDesif)
    {
        List result = null;
        String qryBusca = "FROM PlanoGeralContaComentado as pgcc ";
        String qryParcial = null;
        if(!codTribDesif.isEmpty())
            qryParcial = " where pgcc.codTribDesif = :codTribDesif ";
        if(!conta.isEmpty())
            if(qryParcial == null)
                qryParcial = " where pgcc.conta = :conta";
            else
                qryParcial = (new StringBuilder()).append(qryParcial).append(" and pgcc.conta = :conta").toString();
        if(!contaSuperior.isEmpty())
            if(qryParcial == null)
                qryParcial = " where pgcc.contaSupe = :contaSuperior";
            else
                qryParcial = (new StringBuilder()).append(qryParcial).append(" and pgcc.contaSupe = :contaSuperior").toString();
        if(!contaCosif.isEmpty())
            if(qryParcial == null)
                qryParcial = " where pgcc.contaCosif = :contaCosif";
            else
                qryParcial = (new StringBuilder()).append(qryParcial).append(" and pgcc.contaCosif = :contaCosif").toString();
        if(qryParcial == null)
            qryParcial = " order by pgcc.conta";
        else
            qryParcial = (new StringBuilder()).append(qryParcial).append(" order by pgcc.conta").toString();
        Query q = getConexao().createQuery((new StringBuilder()).append(qryBusca).append(qryParcial).toString());
        if(!codTribDesif.isEmpty())
            q.setString("codTribDesif", codTribDesif);
        if(!conta.isEmpty())
            q.setString("conta", conta);
        if(!contaSuperior.isEmpty())
            q.setString("contaSuperior", contaSuperior);
        if(!contaCosif.isEmpty())
            q.setString("contaCosif", contaCosif);
        result = q.list();
        return result;
    }
}