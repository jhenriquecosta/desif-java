

package br.gov.pbh.desif.service.registrobanco.impl;

import br.gov.pbh.bhiss.utilitarios.validadores.CpfCnpj;
import br.gov.pbh.bhiss.utilitarios.validadores.InscricaoMunicipal;
import br.gov.pbh.desif.dao.*;
import br.gov.pbh.desif.model.pojo.IdentificacaoDeclaracao;
import br.gov.pbh.desif.model.pojo.IdentificacaoDependencia;
import br.gov.pbh.desif.model.registros.Data;
import br.gov.pbh.desif.model.registros.RegUtil;
import br.gov.pbh.desif.service.contexto.Contexto;
import br.gov.pbh.desif.service.registrobanco.ValidaBancoReg0400;
import br.gov.pbh.desif.view.telas.PanGerarDeclaracao;
import java.util.Iterator;
import java.util.List;

public class ValidaBancoReg0400Impl
    implements ValidaBancoReg0400
{

    private CidadeDao cidadeDao;
    private TipoDependenciaDao tipoDependenciaDao;
    private PanGerarDeclaracao panGD;
    private IdentDeclaracaoDao declaracaoDao;
    private ApuracaoReceitaDao apuracaoMensalIssqnDao;
    private BalanceteAnaliticoMensalDao balancAnalitMensalDao;
    private IdentDependenciaDao dependenciaDao;
    private IdentificacaoDeclaracao declaracao;
    private IdentificacaoDependencia dependencia;
    private RegUtil regUtil;
    private Data dt;
    private final String registro = "0400";

    public ValidaBancoReg0400Impl()
    {
    }

    public void executar()
        throws Exception
    {
        regUtil = new RegUtil();
        dt = new Data();
        panGD = (PanGerarDeclaracao)Contexto.getObject("panGD");
        declaracao = (IdentificacaoDeclaracao)declaracaoDao.load(new Integer(Integer.parseInt("1")));
        apuracaoMensalIssqnDao = (ApuracaoReceitaDao)Contexto.getObject("apuracaoMensalIssqnDao");
        List dependencias = dependenciaDao.findAll();
        if(dependencias.isEmpty() && (declaracao.getModuloDeclaracao().shortValue() == 1 || declaracao.getModuloDeclaracao().shortValue() == 2))
        {
            String txtSolucao = (new StringBuilder()).append("M\363dulo da declara\347\343o: ").append(declaracao.getModuloDeclaracao()).toString();
            regUtil.setErro(0L, "ED036", 2, (short)2, "0400", txtSolucao);
        }
        Iterator i = dependencias.iterator();
        double incremento = regUtil.incremetoPorcentagem(10D, dependencias.size());
        double sentinela = 5D;
        int atualizar = 0;
        for(; i.hasNext(); verificarModuloDeclaracao())
        {
            if(sentinela < 15D)
            {
                atualizar = (int)sentinela;
                panGD.atualizarProgressoValidacao(atualizar, 100);
                sentinela += incremento;
            }
            dependencia = (IdentificacaoDependencia)i.next();
            List resp = dependenciaDao.findField("codigoDependencia", dependencia.getCodigoDependencia());
            if(resp.size() > 1)
            {
                String txtSolucao = (new StringBuilder()).append("C\363digo da depend\352ncia duplicado: ").append(dependencia.getCodigoDependencia()).toString();
                regUtil.setErro(dependencia.getLinhaIdentificacaoDependencia().intValue(), "ED028", 3, (short)2, "0400", txtSolucao);
            }
            verificaCodDependencia();
            verificaIndInscMunicipal();
            verificaCnpjProprio();
            verificaCnpjUnificador();
            verificaTipoDependencia();
            verContabilidadePropria();
            verificaCodMunicipioDependencia();
            verificaDataInicPara();
            verificaDataFimPara();
        }

    }

    public void verificaCodDependencia()
    {
        int coluna = 3;
        if(dependencia.getOpcaoInscricaoMunicipal().shortValue() == 1 && !InscricaoMunicipal.validarInscMunOld(dependencia.getCodigoDependencia()))
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo dep\352ndencia: ").append(dependencia.getCodigoDependencia()).toString();
            regUtil.setErro(dependencia.getLinhaIdentificacaoDependencia().intValue(), "ED039", coluna, (short)2, "0400", txtSolucao);
        }
    }

    public void verificaIndInscMunicipal()
    {
        int coluna = 4;
        if(dependencia.getCnpjUnificado().equals(dependencia.getCnpjProprio()) && dependencia.getOpcaoInscricaoMunicipal().shortValue() != 1)
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo da depend\352ncia: ").append(dependencia.getCodigoDependencia()).append("<BR>indicador de inscri\347\343o municipal: ").append(dependencia.getOpcaoInscricaoMunicipal()).append("<BR>CNPJ unificador: ").append(dependencia.getCnpjUnificado()).append("<BR>CNPJ proprio: ").append(dependencia.getCnpjProprio()).toString();
            regUtil.setErro(dependencia.getLinhaIdentificacaoDependencia().intValue(), "ED041", coluna, (short)2, "0400", txtSolucao);
        }
    }

    public void verificaCnpjProprio()
        throws Exception
    {
        int coluna = 5;
        if(!dependencia.getCnpjProprio().equals(""))
        {
            if(!CpfCnpj.validarCpfCnpj((new StringBuilder()).append(declaracao.getCnpjInstituicao()).append(dependencia.getCnpjProprio()).toString()))
            {
                String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(declaracao.getCnpjInstituicao()).append(dependencia.getCnpjProprio()).toString();
                regUtil.setErro(dependencia.getLinhaIdentificacaoDependencia().intValue(), "EG004", coluna, (short)2, "0400", txtSolucao);
            }
            List resp = dependenciaDao.findField("cnpjProprio", dependencia.getCnpjProprio());
            if(resp.size() > 1)
            {
                String txtSolucao = (new StringBuilder()).append("C\363digo da depend\352ncia: ").append(dependencia.getCodigoDependencia()).append("<BR>CNPJ pr\363prio: ").append(dependencia.getCnpjProprio()).toString();
                regUtil.setErro(dependencia.getLinhaIdentificacaoDependencia().intValue(), "ED029", coluna, (short)2, "0400", txtSolucao);
            }
        }
    }

    public void verificaTipoDependencia()
        throws Exception
    {
        int coluna = 6;
        tipoDependenciaDao = (TipoDependenciaDao)Contexto.getObject("tipoDependenciaDao");
        List resp = tipoDependenciaDao.identificarTipoDependencia("id", dependencia.getTipoDependencia());
        if(resp.size() == 0)
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo da depend\352ncia: ").append(dependencia.getCodigoDependencia()).append("<BR>tipo de Depend\352ncia: ").append(dependencia.getTipoDependencia()).toString();
            regUtil.setErro(dependencia.getLinhaIdentificacaoDependencia().intValue(), "ED008", coluna, (short)2, "0400", txtSolucao);
        }
    }

    public void verContabilidadePropria()
        throws Exception
    {
        int coluna = 10;
        if(dependencia.getDataInicioParalizacao() == null && dependencia.getDataFimParalizacao() == null)
        {
            if(dependencia.getCnpjProprio().equals(dependencia.getCnpjUnificado()) && dependencia.getContabilidadePropria().intValue() == 2)
            {
                String txtSolucao = (new StringBuilder()).append("CNPJ unificador: ").append(dependencia.getCnpjUnificado()).append("<BR>CNPJ pr\363prio  ").append(dependencia.getCnpjProprio()).toString();
                regUtil.setErro(dependencia.getLinhaIdentificacaoDependencia().intValue(), "ED016", coluna, (short)2, "0400", txtSolucao);
            } else
            if(dependencia.getContabilidadePropria().intValue() == 1 && declaracao.getModuloDeclaracao().shortValue() != 1 && !apuracaoMensalIssqnDao.verificaExistenciaCodeDependencia(dependencia.getCodigoDependencia()))
                regUtil.setAlerta(dependencia.getLinhaIdentificacaoDependencia().intValue(), "A005", coluna, (short)2, "0400");
        } else
        if(dependencia.getCnpjProprio().equals(dependencia.getCnpjUnificado()) && dependencia.getContabilidadePropria().intValue() == 2)
        {
            boolean respInicio = dependenciaDao.verificaDentroParalisacao(declaracao.getDataInicioCompetencia());
            boolean respFim = dependenciaDao.verificaDentroParalisacao(declaracao.getDataFimCompetencia());
            if(!respInicio && !respFim)
            {
                String txtSolucao = (new StringBuilder()).append("CNPJ unificador: ").append(dependencia.getCnpjUnificado()).append("<BR>CNPJ pr\363prio  ").append(dependencia.getCnpjProprio()).toString();
                regUtil.setErro(dependencia.getLinhaIdentificacaoDependencia().intValue(), "ED016", coluna, (short)2, "0400", txtSolucao);
            }
        } else
        if(dependencia.getContabilidadePropria().intValue() == 1)
        {
            boolean respInicio = dependenciaDao.verificaDentroParalisacao(declaracao.getDataInicioCompetencia());
            boolean respFim = dependenciaDao.verificaDentroParalisacao(declaracao.getDataFimCompetencia());
            if(declaracao.getModuloDeclaracao().shortValue() != 1 && !respInicio && !respFim && !apuracaoMensalIssqnDao.verificaExistenciaCodeDependencia(dependencia.getCodigoDependencia()))
                regUtil.setAlerta(dependencia.getLinhaIdentificacaoDependencia().intValue(), "A005", coluna, (short)2, "0400");
            if(declaracao.getModuloDeclaracao().shortValue() == 1)
            {
                balancAnalitMensalDao = (BalanceteAnaliticoMensalDao)Contexto.getObject("balancAnalitMensalDao");
                boolean resposta = balancAnalitMensalDao.verificaExistenciaCodeDependencia(dependencia.getCodigoDependencia());
                if(!resposta)
                {
                    String txtSolucao = (new StringBuilder()).append("C\363digo da Depend\352ncia: ").append(dependencia.getCodigoDependencia()).append(", identificador de contabilidade pr\363pria: ").append(dependencia.getContabilidadePropria()).toString();
                    regUtil.setErro(dependencia.getLinhaIdentificacaoDependencia().intValue(), "EC002", coluna, (short)2, "0400", txtSolucao);
                }
            }
        }
    }

    public void verificaCnpjUnificador()
        throws Exception
    {
        int coluna = 8;
        if(!dependencia.getCnpjUnificado().equals(dependencia.getCnpjProprio()) && (dependencia.getTipoDependencia().intValue() == 1 || dependencia.getTipoDependencia().intValue() == 2))
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo da depend\352ncia: ").append(dependencia.getCodigoDependencia()).append("<BR>tipo de dep\352ndencia: ").append(dependencia.getTipoDependencia()).append("<BR>CNPJ unificador: ").append(dependencia.getCnpjUnificado()).append("<BR>CNPJ proprio: ").append(dependencia.getCnpjProprio()).toString();
            regUtil.setErro(dependencia.getLinhaIdentificacaoDependencia().intValue(), "ED040", coluna, (short)2, "0400", txtSolucao);
        }
        if(!CpfCnpj.validarCpfCnpj((new StringBuilder()).append(declaracao.getCnpjInstituicao()).append(dependencia.getCnpjUnificado()).toString()))
        {
            String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(declaracao.getCnpjInstituicao()).append(dependencia.getCnpjUnificado()).toString();
            regUtil.setErro(dependencia.getLinhaIdentificacaoDependencia().intValue(), "EG004", coluna, (short)2, "0400", txtSolucao);
        }
        List resp = dependenciaDao.findField("cnpjProprio", dependencia.getCnpjUnificado());
        if(resp.size() < 1)
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo da depend\352ncia: ").append(dependencia.getCodigoDependencia()).append("<BR>CNPJ Unificador: ").append(dependencia.getCnpjUnificado()).toString();
            regUtil.setErro(dependencia.getLinhaIdentificacaoDependencia().intValue(), "ED038", coluna, (short)2, "0400", txtSolucao);
        }
    }

    public void verificaCodMunicipioDependencia()
        throws Exception
    {
        int coluna = 9;
        if(dependencia.getCidade().intValue() != 0xf423f)
        {
            cidadeDao = (CidadeDao)Contexto.getObject("cidadeDao");
            List resp = cidadeDao.identificarCodCidade("id", dependencia.getCidade());
            if(resp.size() == 0)
            {
                String txtSolucao = (new StringBuilder()).append("C\363digo de Munic\355pio: ").append(dependencia.getCidade()).append(" O c\363digo do munic\355pio de Belo Horizonte \351 ").append(0x2f6598L).toString();
                regUtil.setErro(dependencia.getLinhaIdentificacaoDependencia().intValue(), "EG001", coluna, (short)2, "0400", txtSolucao);
            }
        }
    }

    public void verificaDataInicPara()
        throws Exception
    {
        int coluna = 11;
        if(dependencia.getDataInicioParalizacao() != null)
        {
            String inicioParalizacao = dt.formataData(dependencia.getDataInicioParalizacao(), "yyyyMMdd");
            java.util.Date dataFormatar = declaracao.getDataFimCompetencia();
            String fimCompetencia = dt.formataData(dataFormatar, "yyyyMMdd");
            if(dt.comparaDataMaior(inicioParalizacao, fimCompetencia, "yyyyMMdd"))
            {
                String txtSolucao = (new StringBuilder()).append("C\363digo da depend\352ncia: ").append(dependencia.getCodigoDependencia()).append("<BR>data fim compet\352ncia da declara\347\343o: ").append(fimCompetencia).append("<BR>data in\355cio da paralisa\347\343o: ").append(inicioParalizacao).toString();
                regUtil.setErro(dependencia.getLinhaIdentificacaoDependencia().intValue(), "ED030", coluna, (short)2, "0400", txtSolucao);
            }
        }
    }

    public void verificaDataFimPara()
        throws Exception
    {
        int coluna = 12;
        if(dependencia.getDataFimParalizacao() != null)
        {
            String fimParalizacao = dt.formataData(dependencia.getDataFimParalizacao(), "yyyyMMdd");
            String iniCompetencia = dt.formataData(declaracao.getDataInicioCompetencia(), "yyyyMMdd");
            if(dt.comparaDataMaior(iniCompetencia, fimParalizacao, "yyyyMMdd"))
            {
                String txtSolucao = (new StringBuilder()).append("C\363digo da depend\352ncia: ").append(dependencia.getCodigoDependencia()).append("<BR>compet\352ncia da declara\347\343o: ").append(iniCompetencia).append("<BR>data fim da paralisa\347\343o: ").append(fimParalizacao).toString();
                regUtil.setErro(dependencia.getLinhaIdentificacaoDependencia().intValue(), "ED027", coluna, (short)2, "0400", txtSolucao);
            }
        }
    }

    public void verificarModuloDeclaracao()
        throws Exception
    {
        int coluna = 2;
        int moduloDeclaracao = declaracao.getModuloDeclaracao().shortValue();
        if(moduloDeclaracao == 3)
        {
            String txtSolucao = (new StringBuilder()).append("M\363dulo da declara\347\343o informado: ").append(moduloDeclaracao).toString();
            regUtil.setErro(dependencia.getLinhaIdentificacaoDependencia().intValue(), "ED050", coluna, (short)2, "0400", txtSolucao);
        }
    }

    public ApuracaoReceitaDao getApuracaoMensalIssqnDao()
    {
        return apuracaoMensalIssqnDao;
    }

    public void setApuracaoMensalIssqnDao(ApuracaoReceitaDao apuracaoMensalIssqnDao)
    {
        this.apuracaoMensalIssqnDao = apuracaoMensalIssqnDao;
    }

    public CidadeDao getCidadeDao()
    {
        return cidadeDao;
    }

    public void setCidadeDao(CidadeDao cidadeDao)
    {
        this.cidadeDao = cidadeDao;
    }

    public IdentDeclaracaoDao getDeclaracaoDao()
    {
        return declaracaoDao;
    }

    public void setDeclaracaoDao(IdentDeclaracaoDao declaracaoDao)
    {
        this.declaracaoDao = declaracaoDao;
    }

    public IdentDependenciaDao getDependenciaDao()
    {
        return dependenciaDao;
    }

    public void setDependenciaDao(IdentDependenciaDao dependenciaDao)
    {
        this.dependenciaDao = dependenciaDao;
    }

    public PanGerarDeclaracao getPanGD()
    {
        return panGD;
    }

    public void setPanGD(PanGerarDeclaracao panGD)
    {
        this.panGD = panGD;
    }

    public TipoDependenciaDao getTipoDependenciaDao()
    {
        return tipoDependenciaDao;
    }

    public void setTipoDependenciaDao(TipoDependenciaDao tipoDependenciaDao)
    {
        this.tipoDependenciaDao = tipoDependenciaDao;
    }
}
