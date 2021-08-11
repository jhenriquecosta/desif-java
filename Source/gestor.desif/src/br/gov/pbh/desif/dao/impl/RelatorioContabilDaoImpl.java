
package br.gov.pbh.desif.dao.impl;

import br.gov.pbh.desif.base.dao.impl.BaseDaoImpl;
import br.gov.pbh.desif.dao.RelatorioContabilDao;
import br.gov.pbh.desif.model.pojo.BalanceteAnaliticoMensal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

// Referenced classes of package br.gov.pbh.desif.dao.impl:
//            RelatorioInfoComunsDaoImpl

public class RelatorioContabilDaoImpl extends BaseDaoImpl
    implements RelatorioContabilDao
{

    public RelatorioContabilDaoImpl()
    {
    }

    public Class getReferenceClass()
    {
        return br.gov.pbh.desif.model.pojo.BalanceteAnaliticoMensal.class;
    }

    public List buscaDadosDeclaracao()
    {
        List result = null;
        Query q = getConexao().createQuery("SELECT new br.gov.pbh.desif.model.pojo.IdentificacaoDeclaracao(dcl.id, dcl.titulo, dcl.cidade, dcl.linhaIdentificacaoDeclaracao, dcl.cnpjInstituicao, dcl.nomeInstituicao, dcl.dataInicioCompetencia, dcl.dataFimCompetencia, dcl.moduloDeclaracao, dcl.tipoDeclaracao, dcl.tipoConsolidacao, dcl.cnpjResponsavelRecolhimento, dcl.protocoloDeclaracao) FROM IdentificacaoDeclaracao AS dcl");
        result = q.list();
        return result;
    }

    public List buscaDadosDependenciasBalancAnalit()
    {
        List result = null;
        Query q = getConexao().createQuery("SELECT distinct bam.codigoDependencia FROM BalanceteAnaliticoMensal as bam order by bam.codigoDependencia");
        result = q.list();
        return result;
    }

    public List buscaDadosDependencia(String codDependencia)
    {
        List result = null;
        Query q = getConexao().createQuery("SELECT new br.gov.pbh.desif.model.pojo.IdentificacaoDependencia(dcl.id, dcl.tipoDependencia, dcl.cidade, dcl.linhaIdentificacaoDependencia, dcl.codigoDependencia, dcl.opcaoInscricaoMunicipal, dcl.contabilidadePropria, dcl.cnpjUnificado, dcl.cnpjProprio, dcl.enderecoDependencia, dcl.dataInicioParalizacao, dcl.dataFimParalizacao) FROM IdentificacaoDependencia as dcl WHERE dcl.codigoDependencia = :codDependencia");
        q.setString("codDependencia", codDependencia);
        result = q.list();
        return result;
    }

    public List buscaDadosContasBalancAnalit()
    {
        List result = null;
        Query q = getConexao().createQuery("SELECT distinct bam.conta FROM BalanceteAnaliticoMensal as bam order by bam.conta");
        result = q.list();
        return result;
    }

    public List buscaDadosCompetBalancAnalit()
    {
        List result = null;
        Query q = getConexao().createQuery("SELECT distinct bam.anoMesCompetencia FROM BalanceteAnaliticoMensal as bam order by bam.anoMesCompetencia");
        result = q.list();
        return result;
    }

    public List buscaDadosGeraisBalancAnalit(String codDependencia, String competencia, String conta)
    {
        List result = null;
        Date dtCompetencia = new Date();
        String qryBusca = "SELECT new br.gov.pbh.desif.model.pojo.BalanceteAnaliticoMensal(bam.conta, bam.saldoInicial, bam.valorDebito, bam.valorCredito, bam.saldoFinal, bam.anoMesCompetencia) FROM BalanceteAnaliticoMensal as bam ";
        String qryParcial = null;
        if(!codDependencia.isEmpty() && codDependencia != null)
            qryParcial = " where bam.codigoDependencia = :codDependencia";
        if(!competencia.isEmpty() && competencia != null)
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try
            {
                dtCompetencia = dateFormat.parse(competencia);
            }
            catch(ParseException ex)
            {
                Logger.getLogger(br.gov.pbh.desif.dao.impl.RelatorioInfoComunsDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(qryParcial == null)
                qryParcial = " where bam.anoMesCompetencia = :competencia";
            else
                qryParcial = (new StringBuilder()).append(qryParcial).append(" and bam.anoMesCompetencia = :competencia").toString();
        }
        if(!conta.isEmpty() && conta != null)
            if(qryParcial == null)
                qryParcial = " where bam.conta = :conta";
            else
                qryParcial = (new StringBuilder()).append(qryParcial).append(" and bam.conta = :conta").toString();
        if(qryParcial == null)
            qryParcial = " order by bam.conta, bam.anoMesCompetencia";
        else
            qryParcial = (new StringBuilder()).append(qryParcial).append(" order by bam.conta, bam.anoMesCompetencia").toString();
        Query q = getConexao().createQuery((new StringBuilder()).append(qryBusca).append(qryParcial).toString());
        if(!codDependencia.isEmpty())
            q.setString("codDependencia", codDependencia);
        if(!competencia.isEmpty())
            q.setDate("competencia", dtCompetencia);
        if(!conta.isEmpty())
            q.setString("conta", conta);
        result = q.list();
        return result;
    }

    public List buscaDadosDependenciasDemRateio()
    {
        List result = null;
        Query q = getConexao().createQuery("SELECT distinct rat.codigoDependencia FROM DemonstrativoRateioMensal as rat order by rat.codigoDependencia");
        result = q.list();
        return result;
    }

    public List buscaDadosCompetDemRateio()
    {
        List result = null;
        Query q = getConexao().createQuery("SELECT distinct rat.anoMesCompetencia FROM DemonstrativoRateioMensal as rat order by rat.anoMesCompetencia");
        result = q.list();
        return result;
    }

    public List buscaDadosValRateioDemRateio()
    {
        List result = null;
        Query q = getConexao().createQuery("SELECT distinct rat.valorReceitaRateada FROM DemonstrativoRateioMensal as rat order by rat.valorReceitaRateada");
        result = q.list();
        return result;
    }

    public List buscaDadosCodEventoDemRateio()
    {
        List result = null;
        Query q = getConexao().createQuery("SELECT distinct rat.codigoEvento FROM DemonstrativoRateioMensal as rat order by rat.codigoEvento");
        result = q.list();
        return result;
    }

    public List buscaDadosGeraisDemRateio(String codDependencia, String competencia, String valRateio, String codEvento)
    {
        List result = null;
        Date dtCompetencia = new Date();
        String qryBusca = "SELECT new br.gov.pbh.desif.model.pojo.DemonstrativoRateioMensal(rat.codigoDependencia, rat.anoMesCompetencia, rat.descricaoDetalhadaReceita, rat.valorReceitaRateada, rat.tipoPartida) FROM DemonstrativoRateioMensal as rat ";
        String qryParcial = null;
        if(!codDependencia.isEmpty())
            qryParcial = " where rat.codigoDependencia = :codDependencia";
        if(!competencia.isEmpty())
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try
            {
                dtCompetencia = dateFormat.parse(competencia);
            }
            catch(ParseException ex)
            {
                Logger.getLogger(br.gov.pbh.desif.dao.impl.RelatorioInfoComunsDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(qryParcial == null)
                qryParcial = " where rat.anoMesCompetencia = :competencia";
            else
                qryParcial = (new StringBuilder()).append(qryParcial).append(" and rat.anoMesCompetencia = :competencia").toString();
        }
        if(!valRateio.isEmpty())
            if(qryParcial == null)
                qryParcial = " where rat.valorReceitaRateada = :valRateio";
            else
                qryParcial = (new StringBuilder()).append(qryParcial).append(" and rat.valorReceitaRateada = :valRateio").toString();
        if(!codEvento.isEmpty())
            if(qryParcial == null)
                qryParcial = " where rat.codigoEvento = :codEvento";
            else
                qryParcial = (new StringBuilder()).append(qryParcial).append(" and rat.codigoEvento = :codEvento").toString();
        if(qryParcial == null)
            qryParcial = " order by rat.codigoDependencia, rat.anoMesCompetencia";
        else
            qryParcial = (new StringBuilder()).append(qryParcial).append(" order by rat.codigoDependencia, rat.anoMesCompetencia").toString();
        Query q = getConexao().createQuery((new StringBuilder()).append(qryBusca).append(qryParcial).toString());
        if(!codDependencia.isEmpty())
            q.setString("codDependencia", codDependencia);
        if(!competencia.isEmpty())
            q.setDate("competencia", dtCompetencia);
        if(!valRateio.isEmpty())
            q.setString("valRateio", valRateio);
        if(!codEvento.isEmpty())
            q.setString("codEvento", codEvento);
        result = q.list();
        return result;
    }
}