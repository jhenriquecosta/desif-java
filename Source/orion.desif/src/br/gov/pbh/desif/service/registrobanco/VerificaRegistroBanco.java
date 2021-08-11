

package br.gov.pbh.desif.service.registrobanco;

import br.gov.pbh.bhiss.utilitarios.validadores.CpfCnpj;
import br.gov.pbh.bhiss.utilitarios.validadores.InscricaoMunicipal;
import br.gov.pbh.desif.dao.*;
import br.gov.pbh.desif.model.pojo.*;
import br.gov.pbh.desif.model.registros.Data;
import br.gov.pbh.desif.model.registros.RegUtil;
import br.gov.pbh.desif.service.contexto.Contexto;
import br.gov.pbh.desif.view.telas.PanGerarDeclaracao;
import br.gov.pbh.desif.view.util.SwingUtils;
import java.io.PrintStream;
import java.text.*;
import java.util.*;

public class VerificaRegistroBanco
{

    private PanGerarDeclaracao panGerDec;
    private RegUtil regUtil;
    private Data dt;
    private IdentDeclaracaoDao declDao;
    private TituloDao tituloDao;
    private IdentificacaoDeclaracao declaracao;
    private CidadeDao cidadeDao;
    private IdentDependenciaDao dependenciaDao;
    private IdentificacaoDependencia dependencia;
    private TipoDependenciaDao tipoDependenciaDao;
    private ApuracaoReceitaDao apurSubIssqnDao;
    private ApuracaoReceita apurSubIssqn;
    private IssqnMensalDao demMenIssqnDao;
    private IssqnMensal demMenIssqn;
    private CodTributacaoDesifDao codTribuDao;
    private CodTribuMunicipalDao codTribuMuniDao;
    private String registro;

    public VerificaRegistroBanco()
    {
        registro = "";
        try
        {
            updateCnpj();
        }
        catch(Exception ex)
        {
            RegUtil.exErro = true;
            SwingUtils.msgErro(null, (new StringBuilder()).append(ex.getCause().toString()).append("\n\n Mensagem: ").append(ex.getMessage()).toString());
        }
        try
        {
            regUtil = new RegUtil();
            panGerDec = (PanGerarDeclaracao)Contexto.getObject("panGD");
            dt = new Data();
            panGerDec.atualizarProgressoValidacao(0, 100);
            registro = "0000";
            verificaReg0000();
        }
        catch(Exception ex)
        {
            RegUtil.exErro = true;
            SwingUtils.msgErro(null, (new StringBuilder()).append("\nOcorreu um erro inexperado, por favor contacte o administrador do sistema. \n").append(ex.toString()).append("\nMensagem: ").append(ex.getMessage()).toString());
            System.out.println(ex.toString());
            ex.printStackTrace();
        }
        try
        {
            panGerDec.atualizarProgressoValidacao(5, 100);
            registro = "0400";
            verificaReg0400();
        }
        catch(Exception ex)
        {
            RegUtil.exErro = true;
            SwingUtils.msgErro(null, (new StringBuilder()).append("\nOcorreu um erro inexperado, por favor contacte o administrador do sistema. \n").append(ex.toString()).append("\nMensagem: ").append(ex.getMessage()).toString());
            ex.printStackTrace();
        }
        try
        {
            registro = "0430";
            verificaReg0430();
        }
        catch(Exception ex)
        {
            RegUtil.exErro = true;
            SwingUtils.msgErro(null, (new StringBuilder()).append("\nOcorreu um erro inexperado, por favor contacte o administrador do sistema. \n").append(ex.toString()).append("\nMensagem: ").append(ex.getMessage()).toString());
            ex.printStackTrace();
        }
        try
        {
            registro = "0440";
            verificaReg0440();
            panGerDec.atualizarProgressoValidacao(100, 100);
        }
        catch(Exception ex)
        {
            RegUtil.exErro = true;
            SwingUtils.msgErro(null, (new StringBuilder()).append("\nOcorreu um erro inexperado, por favor contacte o administrador do sistema. \n").append(ex.toString()).append("\nMensagem: ").append(ex.getMessage()).toString());
            ex.printStackTrace();
        }
    }

    public void verificaReg0000()
        throws Exception
    {
        declDao = (IdentDeclaracaoDao)Contexto.getObject("declaracaoDao");
        declaracao = (IdentificacaoDeclaracao)declDao.load(new Integer(Integer.parseInt("1")));
        verificaTipoInstituicao();
        verificaCodMunicipio();
        verificaCnpjRespRclh();
    }

    public void verificaTipoInstituicao()
        throws Exception
    {
        int coluna = 5;
        tituloDao = (TituloDao)Contexto.getObject("tituloDao");
        List resp = tituloDao.identificarTipoInstituicao("id", declaracao.getTitulo().toUpperCase());
        if(resp.size() == 0)
        {
            String txtSolucao = (new StringBuilder()).append("Tipo de Institui\347\343o informado: ").append(declaracao.getTitulo()).toString();
            regUtil.setErro(declaracao.getLinhaIdentificacaoDeclaracao().intValue(), "ED003", coluna, (short)2, registro, txtSolucao);
        }
    }

    public void verificaCodMunicipio()
        throws Exception
    {
        int coluna = 6;
        if(declaracao.getCidade().longValue() != 0xf423fL)
        {
            cidadeDao = (CidadeDao)Contexto.getObject("cidadeDao");
            List resp = cidadeDao.identificarCodCidade("id", declaracao.getCidade());
            if(resp.size() == 0)
            {
                String txtSolucao = (new StringBuilder()).append("C\363digo de Munic\355pio: ").append(declaracao.getCidade()).toString();
                regUtil.setErro(declaracao.getLinhaIdentificacaoDeclaracao().intValue(), "EG001", coluna, (short)2, registro, txtSolucao);
            }
        }
    }

    public void verificaCnpjRespRclh()
        throws Exception
    {
        int coluna = 13;
        if(!declaracao.getCnpjResponsavelRecolhimento().equals(""))
        {
            dependenciaDao = (IdentDependenciaDao)Contexto.getObject("dependenciaDao");
            List dependencias = dependenciaDao.findField("cnpjProprio", declaracao.getCnpjResponsavelRecolhimento());
            if(dependencias.size() < 1)
            {
                String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(declaracao.getCnpjResponsavelRecolhimento()).toString();
                regUtil.setErro(declaracao.getLinhaIdentificacaoDeclaracao(), "ED032", coluna, (short)2, registro, txtSolucao);
            } else
            {
                List dep = dependenciaDao.findField("cnpjProprio", declaracao.getCnpjResponsavelRecolhimento());
                if(dep.size() == 1)
                    if(((IdentificacaoDependencia)dep.get(0)).getContabilidadePropria() == 1)
                    {
                        if(declaracao.getCidade() != 0x2f6598L)
                        {
                            String txtSolucao = (new StringBuilder()).append("C\363digo da depend\352ncia: ").append(((IdentificacaoDependencia)dep.get(0)).getCodigoDependencia()).append(" <BR>CNPJ respons\341vel pelo recolhimento: ").append(declaracao.getCnpjResponsavelRecolhimento()).append("<BR>munic\355pio da depend\352ncia : ").append(((IdentificacaoDependencia)dep).getCidade()).toString();
                            regUtil.setErro(declaracao.getLinhaIdentificacaoDeclaracao(), "ED033", coluna, (short)2, registro, txtSolucao);
                        }
                    } else
                    if(((IdentificacaoDependencia)dep.get(0)).getContabilidadePropria() == 2)
                    {
                        String txtSolucao = (new StringBuilder()).append("C\363digo da depend\352ncia: ").append(((IdentificacaoDependencia)dep.get(0)).getCodigoDependencia()).append(" <BR>CNPJ respons\341vel pelo recolhimento: ").append(declaracao.getCnpjResponsavelRecolhimento()).append("<BR>indicador de contabilidade pr\363pria: ").append(((IdentificacaoDependencia)dep.get(0)).getContabilidadePropria()).toString();
                        regUtil.setErro(declaracao.getLinhaIdentificacaoDeclaracao(), "ED019", coluna, (short)2, registro, txtSolucao);
                    }
            }
        }
    }

    public void verificaReg0400()
        throws Exception
    {
        apurSubIssqnDao = (ApuracaoReceitaDao)Contexto.getObject("apuracaoMensalIssqnDao");
        try
        {
            dependenciaDao = (IdentDependenciaDao)Contexto.getObject("dependenciaDao");
            List dependencias = dependenciaDao.findAll();
            if(dependencias.isEmpty() && (declaracao.getModuloDeclaracao() == 1 || declaracao.getModuloDeclaracao() == 2))
            {
                String txtSolucao = (new StringBuilder()).append("M\363dulo da declara\347\343o: ").append(declaracao.getModuloDeclaracao()).toString();
                regUtil.setErro(0L, "ED036", 2, (short)2, registro, txtSolucao);
            }
            Iterator i = dependencias.iterator();
            double incremento = regUtil.incremetoPorcentagem(10D, dependencias.size());
            double sentinela = 5D;
            int atualizar = 0;
            for(; i.hasNext(); verificaDataFimPara())
            {
                if(sentinela < 15D)
                {
                    atualizar = (int)sentinela;
                    panGerDec.atualizarProgressoValidacao(atualizar, 100);
                    sentinela += incremento;
                }
                dependencia = (IdentificacaoDependencia)i.next();
                List resp = dependenciaDao.findField("codigoDependencia", dependencia.getCodigoDependencia());
                if(resp.size() > 1)
                {
                    String txtSolucao = (new StringBuilder()).append("C\363digo da depend\352ncia duplicado: ").append(dependencia.getCodigoDependencia()).toString();
                    regUtil.setErro(dependencia.getLinhaIdentificacaoDependencia().intValue(), "ED028", 3, (short)2, registro, txtSolucao);
                }
                verificaCodDependencia();
                verificaIndInscMunicipal();
                verificaCnpjProprio();
                verificaCnpjUnificador();
                verificaTipoDependencia();
                verContabilidadePropria();
                verificaCodMunicipioDependencia();
                verificaDataInicPara();
            }

        }
        catch(Exception e)
        {
        }
    }

    public void verificaCodDependencia()
    {
        int coluna = 3;
        if(dependencia.getOpcaoInscricaoMunicipal() == 1) //&& !InscricaoMunicipal.validarInscMun(dependencia.getCodigoDependencia()))
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo dep\352ndencia: ").append(dependencia.getCodigoDependencia()).toString();
            regUtil.setErro(dependencia.getLinhaIdentificacaoDependencia().intValue(), "ED039", coluna, (short)2, registro, txtSolucao);
        }
    }

    public void verificaIndInscMunicipal()
    {
        int coluna = 4;
        if(dependencia.getCnpjUnificado().equals(dependencia.getCnpjProprio()) && dependencia.getOpcaoInscricaoMunicipal() != 1)
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo da depend\352ncia: ").append(dependencia.getCodigoDependencia()).append("<BR>indicador de inscri\347\343o municipal: ").append(dependencia.getOpcaoInscricaoMunicipal()).append("<BR>CNPJ unificador: ").append(dependencia.getCnpjUnificado()).append("<BR>CNPJ proprio: ").append(dependencia.getCnpjProprio()).toString();
            regUtil.setErro(dependencia.getLinhaIdentificacaoDependencia().intValue(), "ED041", coluna, (short)2, registro, txtSolucao);
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
                regUtil.setErro(dependencia.getLinhaIdentificacaoDependencia().intValue(), "EG004", coluna, (short)2, registro, txtSolucao);
            }
            List resp = dependenciaDao.findField("cnpjProprio", dependencia.getCnpjProprio());
            if(resp.size() > 1)
            {
                String txtSolucao = (new StringBuilder()).append("C\363digo da depend\352ncia: ").append(dependencia.getCodigoDependencia()).append("<BR>CNPJ pr\363prio: ").append(dependencia.getCnpjProprio()).toString();
                regUtil.setErro(dependencia.getLinhaIdentificacaoDependencia().intValue(), "ED029", coluna, (short)2, registro, txtSolucao);
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
            regUtil.setErro(dependencia.getLinhaIdentificacaoDependencia().intValue(), "ED008", coluna, (short)2, registro, txtSolucao);
        }
    }

    public void verContabilidadePropria()
        throws Exception
    {
        int coluna = 10;
        if(dependencia.getDataInicioParalizacao() == null && dependencia.getDataInicioParalizacao() == null)
        {
            if(dependencia.getCnpjProprio().equals(dependencia.getCnpjUnificado()) && dependencia.getContabilidadePropria().intValue() == 2)
            {
                String txtSolucao = (new StringBuilder()).append("CNPJ unificador: ").append(dependencia.getCnpjUnificado()).append("<BR>CNPJ pr\363prio  ").append(dependencia.getCnpjProprio()).toString();
                regUtil.setErro(dependencia.getLinhaIdentificacaoDependencia().intValue(), "ED016", coluna, (short)2, registro, txtSolucao);
            } else
            if(dependencia.getContabilidadePropria().intValue() == 1 && declaracao.getModuloDeclaracao().shortValue() != 1 && !apurSubIssqnDao.verificaExistenciaCodeDependencia(dependencia.getCodigoDependencia()))
                regUtil.setAlerta(dependencia.getLinhaIdentificacaoDependencia().intValue(), "A005", coluna, (short)2, registro);
        } else
        if(dependencia.getCnpjProprio().equals(dependencia.getCnpjUnificado()) && dependencia.getContabilidadePropria().intValue() == 2)
        {
            boolean respInicio = dependenciaDao.verificaDentroParalisacao(declaracao.getDataInicioCompetencia());
            boolean respFim = dependenciaDao.verificaDentroParalisacao(declaracao.getDataFimCompetencia());
            if(!respInicio && !respFim)
            {
                String txtSolucao = (new StringBuilder()).append("CNPJ unificador: ").append(dependencia.getCnpjUnificado()).append("<BR>CNPJ pr\363prio  ").append(dependencia.getCnpjProprio()).toString();
                regUtil.setErro(dependencia.getLinhaIdentificacaoDependencia().intValue(), "ED016", coluna, (short)2, registro, txtSolucao);
            }
        } else
        if(dependencia.getContabilidadePropria().intValue() == 1)
        {
            boolean respInicio = dependenciaDao.verificaDentroParalisacao(declaracao.getDataInicioCompetencia());
            boolean respFim = dependenciaDao.verificaDentroParalisacao(declaracao.getDataFimCompetencia());
            if(!respInicio && !respFim && declaracao.getModuloDeclaracao().shortValue() != 1 && !apurSubIssqnDao.verificaExistenciaCodeDependencia(dependencia.getCodigoDependencia()))
                regUtil.setAlerta(dependencia.getLinhaIdentificacaoDependencia().intValue(), "A005", coluna, (short)2, registro);
        }
    }

    public void verificaCnpjUnificador()
        throws Exception
    {
        int coluna = 8;
        if(!dependencia.getCnpjUnificado().equals(dependencia.getCnpjProprio()) && (dependencia.getTipoDependencia().intValue() == 1 || dependencia.getTipoDependencia().intValue() == 2))
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo da depend\352ncia: ").append(dependencia.getCodigoDependencia()).append("<BR>tipo de dep\352ndencia: ").append(dependencia.getTipoDependencia()).append("<BR>CNPJ unificador: ").append(dependencia.getCnpjUnificado()).append("<BR>CNPJ proprio: ").append(dependencia.getCnpjProprio()).toString();
            regUtil.setErro(dependencia.getLinhaIdentificacaoDependencia().intValue(), "ED040", coluna, (short)2, registro, txtSolucao);
        }
        if(!CpfCnpj.validarCpfCnpj((new StringBuilder()).append(declaracao.getCnpjInstituicao()).append(dependencia.getCnpjUnificado()).toString()))
        {
            String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(declaracao.getCnpjInstituicao()).append(dependencia.getCnpjUnificado()).toString();
            regUtil.setErro(dependencia.getLinhaIdentificacaoDependencia().intValue(), "EG004", coluna, (short)2, registro, txtSolucao);
        }
        List resp = dependenciaDao.findField("cnpjProprio", dependencia.getCnpjUnificado());
        if(resp.size() < 1)
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo da depend\352ncia: ").append(dependencia.getCodigoDependencia()).append("<BR>CNPJ Unificador: ").append(dependencia.getCnpjUnificado()).toString();
            regUtil.setErro(dependencia.getLinhaIdentificacaoDependencia().intValue(), "ED038", coluna, (short)2, registro, txtSolucao);
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
                String txtSolucao = (new StringBuilder()).append("C\363digo de Munic\355pio: ").append(declaracao.getCidade()).toString();
                regUtil.setErro(dependencia.getLinhaIdentificacaoDependencia().intValue(), "EG001", coluna, (short)2, registro, txtSolucao);
            }
        }
    }

    public void verificaDataInicPara()
        throws Exception
    {
        int coluna = 11;
        if(dependencia.getDataInicioParalizacao() != null)
        {
            String data1 = dependencia.getDataInicioParalizacao().toString().replaceAll("-", "");
            String data2 = declaracao.getDataFimCompetencia().toString().replaceAll("-", "");
            if(dt.comparaDataMaior(data1, data2, "yyyyMMdd"))
            {
                String data3 = declaracao.getDataInicioCompetencia().toString().replaceAll("-", "");
                String txtSolucao = (new StringBuilder()).append("C\363digo da depend\352ncia: ").append(dependencia.getCodigoDependencia()).append("<BR>data in\355cio compet\352ncia da declara\347\343o: ").append(data3).append("<BR>data in\355cio da paralisa\347\343o: ").append(data1).toString();
                regUtil.setErro(dependencia.getLinhaIdentificacaoDependencia().intValue(), "ED030", coluna, (short)2, registro, txtSolucao);
            }
        }
    }

    public void verificaDataFimPara()
        throws Exception
    {
        int coluna = 12;
        if(dependencia.getDataFimParalizacao() != null)
        {
            String data1 = dependencia.getDataFimParalizacao().toString().replaceAll("-", "");
            String data2 = declaracao.getDataInicioCompetencia().toString().replaceAll("-", "");
            if(dt.comparaDataMaior(data2, data1, "yyyyMMdd"))
            {
                String txtSolucao = (new StringBuilder()).append("C\363digo da depend\352ncia: ").append(dependencia.getCodigoDependencia()).append("<BR>compet\352ncia da declara\347\343o: ").append(data2).append("<BR>data fim da paralisa\347\343o: ").append(data1).toString();
                regUtil.setErro(dependencia.getLinhaIdentificacaoDependencia().intValue(), "ED027", coluna, (short)2, registro, txtSolucao);
            }
        }
    }

    public void verificaReg0430()
        throws Exception
    {
        apurSubIssqnDao = (ApuracaoReceitaDao)Contexto.getObject("apuracaoMensalIssqnDao");
        List apuracoesReceitas = apurSubIssqnDao.findAll();
        double incremento = regUtil.incremetoPorcentagem(65D, apuracoesReceitas.size());
        double sentinela = 15D;
        int atualizar = 0;
        for(Iterator i = apuracoesReceitas.iterator(); i.hasNext(); verificaIncentivoFisc())
        {
            if(sentinela < 80D)
            {
                atualizar = (int)sentinela;
                panGerDec.atualizarProgressoValidacao(atualizar, 100);
                sentinela += incremento;
            }
            apurSubIssqn = (ApuracaoReceita)i.next();
            verificarCodDepe();
            verificarCodTributacaoDesif();
            verificaValCredMens();
            verificaValDebtMens();
            verificaValReceDecl();
            verificaDeduReceDecl();
            verificaBaseCalc();
            verificaAliqIssqn();
        }

    }

    public void verificarCodDepe()
        throws Exception
    {
        int coluna = 3;
        List resp = dependenciaDao.findField("codigoDependencia", apurSubIssqn.getCodigoDependencia());
        if(resp.size() == 0)
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo da depend\352ncia: ").append(apurSubIssqn.getCodigoDependencia()).toString();
            regUtil.setErro(apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EG006", coluna, (short)2, registro, txtSolucao);
        } else
        if(((IdentificacaoDependencia)resp.get(0)).getContabilidadePropria().intValue() == 2)
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo da depend\352ncia : ").append(apurSubIssqn.getCodigoDependencia()).append("<BR>Contabilidade pr\363pria: ").append(((IdentificacaoDependencia)resp.get(0)).getContabilidadePropria()).toString();
            regUtil.setErro(apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EM019", coluna, (short)2, registro, txtSolucao);
        }
    }

    public void verificarCodTributacaoDesif()
    {
        int coluna = 5;
        codTribuDao = (CodTributacaoDesifDao)Contexto.getObject("codTribDao");
        List respTrib = codTribuDao.buscaCodTributacaoDesif("id", apurSubIssqn.getCodigoTributacaoDesIf());
        if(respTrib.size() < 1)
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo da depend\352ncia: ").append(apurSubIssqn.getCodigoDependencia()).append("<BR>c\363digo de tributa\347\343o DES-IF: ").append(apurSubIssqn.getCodigoTributacaoDesIf()).toString();
            regUtil.setErro(apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EG011", coluna, (short)2, registro, txtSolucao);
        }
    }

    public void verificaValCredMens()
        throws Exception
    {
        int coluna = 6;
        if(apurSubIssqn.getValorCreditoMensal().doubleValue() < 0.0D)
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo de depend\352ncia: ").append(apurSubIssqn.getCodigoDependencia()).append("<BR>subt\355tulo: ").append(apurSubIssqn.getCodigoSubTitulo()).append("<BR>total de lan\347amentos a cr\351dito: ").append(apurSubIssqn.getValorCreditoMensal()).toString();
            regUtil.setErro(apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EM026", coluna, (short)2, registro, txtSolucao);
        }
    }

    public void verificaValDebtMens()
        throws Exception
    {
        int coluna = 7;
        if(apurSubIssqn.getValorDebitoMensal().doubleValue() < 0.0D)
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo de depend\352ncia: ").append(apurSubIssqn.getCodigoDependencia()).append("<BR>subt\355tulo: ").append(apurSubIssqn.getCodigoSubTitulo()).append("<BR>total de lan\347amentos a d\351bito: ").append(apurSubIssqn.getValorDebitoMensal()).toString();
            regUtil.setErro(apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EM027", coluna, (short)2, registro, txtSolucao);
        }
    }

    public void verificaValReceDecl()
        throws Exception
    {
        int coluna = 8;
        if(apurSubIssqn.getValorReceitaDeclarada().doubleValue() < 0.0D)
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo de depend\352ncia: ").append(apurSubIssqn.getCodigoDependencia()).append("<BR>subt\355tulo: ").append(apurSubIssqn.getCodigoSubTitulo()).append("<BR>receita declarada: ").append(apurSubIssqn.getValorReceitaDeclarada()).toString();
            regUtil.setErro(apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EM021", coluna, (short)2, registro, txtSolucao);
        }
    }

    public void verificaDeduReceDecl()
        throws Exception
    {
        int coluna = 9;
        if(apurSubIssqn.getValorDeducaoReceitaDeclarada().doubleValue() < 0.0D)
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo de depend\352ncia: ").append(apurSubIssqn.getCodigoDependencia()).append("<BR>subt\355tulo: ").append(apurSubIssqn.getCodigoSubTitulo()).append("<BR>valor da dedu\347\343o da receita declarada: ").append(apurSubIssqn.getValorDeducaoReceitaDeclarada()).toString();
            regUtil.setErro(apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EM064", coluna, (short)2, registro, txtSolucao);
        }
        if(apurSubIssqn.getValorDeducaoReceitaDeclarada().doubleValue() > apurSubIssqn.getValorReceitaDeclarada().doubleValue())
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo de depend\352ncia: ").append(apurSubIssqn.getCodigoDependencia()).append("<BR>subt\355tulo: ").append(apurSubIssqn.getCodigoSubTitulo()).append("<BR>receita declarada: ").append(apurSubIssqn.getValorReceitaDeclarada()).append("<BR>dedu\347\343o da receita declarada: ").append(apurSubIssqn.getValorDeducaoReceitaDeclarada()).toString();
            regUtil.setErro(apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EM028", coluna, (short)2, registro, txtSolucao);
        }
    }

    public void verificaBaseCalc()
        throws Exception
    {
        int coluna = 11;
        if(apurSubIssqn.getValorBaseCalculo().doubleValue() < 0.0D)
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo da depend\352ncia: ").append(apurSubIssqn.getCodigoDependencia()).append("<BR>subt\355tulo: ").append(apurSubIssqn.getCodigoSubTitulo()).append("<BR>base de c\341lculo: ").append(apurSubIssqn.getValorBaseCalculo()).toString();
            regUtil.setErro(apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EM032", coluna, (short)2, registro, txtSolucao);
        }
        if(apurSubIssqn.getValorReceitaDeclarada().doubleValue() - apurSubIssqn.getValorDeducaoReceitaDeclarada().doubleValue() != apurSubIssqn.getValorBaseCalculo().doubleValue())
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo de depend\352ncia: ").append(apurSubIssqn.getCodigoDependencia()).append("<BR>Subt\355tulo: ").append(apurSubIssqn.getCodigoSubTitulo()).append("<BR>receita declarada: ").append(apurSubIssqn.getValorReceitaDeclarada()).append("<BR>dedu\347\343o da receita: ").append(apurSubIssqn.getValorDeducaoReceitaDeclarada()).append("<BR>de c\341lculo: ").append(apurSubIssqn.getValorBaseCalculo()).toString();
            regUtil.setErro(apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EM033", coluna, (short)2, registro, txtSolucao);
        }
    }

    public void verificaAliqIssqn()
    {
        int coluna = 12;
        if(apurSubIssqn.getValorAliquotaIssqn().doubleValue() < 0.0D)
        {
            String txtSolucao = (new StringBuilder()).append("Codigo depend\352ncia: ").append(apurSubIssqn.getCodigoDependencia()).append("<BR>al\355quota: ").append(apurSubIssqn.getValorAliquotaIssqn()).toString();
            regUtil.setErro(apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EM074", coluna, (short)2, registro, txtSolucao);
        }
        boolean resp = apurSubIssqnDao.verificaUnicidade(apurSubIssqn.getCodigoDependencia(), apurSubIssqn.getCodigoSubTitulo(), apurSubIssqn.getCodigoTributacaoDesIf(), apurSubIssqn.getValorAliquotaIssqn());
        if(resp)
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo de depend\352ncia: ").append(apurSubIssqn.getCodigoDependencia()).append("<BR>Subt\355tulo: ").append(apurSubIssqn.getCodigoSubTitulo()).append("<BR>c\363digo de tributa\347\343o: ").append(apurSubIssqn.getCodigoTributacaoDesIf()).append("<BR>al\355quota: ").append(apurSubIssqn.getValorAliquotaIssqn()).toString();
            regUtil.setErro(apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EM059", coluna, (short)2, registro, txtSolucao);
        }
        codTribuMuniDao = (CodTribuMunicipalDao)Contexto.getObject("codTribMunicDao");
        List retorno = codTribuMuniDao.buscaCodTribuAliqMunicipal(apurSubIssqn.getCodigoTributacaoDesIf(), new Long(0x2f6598L), apurSubIssqn.getValorAliquotaIssqn(), declaracao.getDataInicioCompetencia());
        if(retorno.size() < 1)
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo de depend\352ncia: ").append(apurSubIssqn.getCodigoDependencia()).append("<BR>m\352s-ano da declara\347\343o: ").append(declaracao.getDataInicioCompetencia()).append("<BR>c\363digo de tributa\347\343o: ").append(apurSubIssqn.getCodigoTributacaoDesIf()).append("<BR>al\355quota: ").append(apurSubIssqn.getValorAliquotaIssqn()).toString();
            regUtil.setErro(apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EM046", coluna, (short)2, registro, txtSolucao);
        }
    }

    public void verificaIncentivoFisc()
        throws Exception
    {
        int coluna = 13;
        if(apurSubIssqn.getValorIncentivoFiscal().doubleValue() < 0.0D)
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo de depend\352ncia: ").append(apurSubIssqn.getCodigoDependencia()).append("<BR>valor do incentivo fiscal: ").append(apurSubIssqn.getValorIncentivoFiscal()).toString();
            regUtil.setErro(apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EM076", coluna, (short)2, registro, txtSolucao);
        }
        double issqnDevido = (apurSubIssqn.getValorBaseCalculo().doubleValue() * apurSubIssqn.getValorAliquotaIssqn().doubleValue()) / 100D;
        if(issqnDevido < apurSubIssqn.getValorIncentivoFiscal().doubleValue())
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo de depend\352ncia: ").append(apurSubIssqn.getCodigoDependencia()).append("<BR>subt\355tulo: ").append(apurSubIssqn.getCodigoSubTitulo()).append("<BR>base c\341lculo: ").append(apurSubIssqn.getValorBaseCalculo()).append("<BR>al\355quota: ").append(apurSubIssqn.getValorAliquotaIssqn()).append("<BR>incentivo fiscal: ").append(apurSubIssqn.getValorIncentivoFiscal()).toString();
            regUtil.setErro(apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EM034", coluna, (short)2, registro, txtSolucao);
        }
    }

    public void verificaErroInexistenciaRegistro0440Consolidacao()
    {
label0:
        switch(declaracao.getTipoConsolidacao().intValue())
        {
        case 2: // '\002'
        case 4: // '\004'
        default:
            break;

        case 1: // '\001'
            if(apurSubIssqn != null)
            {
                List retorno = demMenIssqnDao.verificaExistenciaRegistro0440Consolidacao1(apurSubIssqn.getValorAliquotaIssqn());
                if(retorno == null || retorno.size() < 1)
                {
                    String txtSolucao = (new StringBuilder()).append("Tipo de consolida\347\343o: ").append(declaracao.getTipoConsolidacao()).append("<BR>aliquota: ").append(apurSubIssqn.getValorAliquotaIssqn()).toString();
                    regUtil.setErro(apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EM067", 12, (short)2, registro, txtSolucao);
                }
            }
            break;

        case 3: // '\003'
            if(apurSubIssqn == null)
                break;
            List resp = apurSubIssqnDao.buscaDadosAliquotaCnpj();
            Iterator i = resp.iterator();
            do
            {
                Object ob[];
                List respExiste;
                do
                {
                    do
                    {
                        if(!i.hasNext())
                            break label0;
                        ob = (Object[])(Object[])i.next();
                    } while(ob[0] == null || ob[1] == null);
                    respExiste = demMenIssqnDao.verificaExistenciaRegistro0440Consolidacao3(ob[0].toString(), ob[1].toString());
                } while(respExiste != null && respExiste.size() >= 1);
                String txtSolucao = (new StringBuilder()).append("Tipo de consolida\347\343o: ").append(declaracao.getTipoConsolidacao()).append("<BR>c\363digo da depend\352ncia: ").append(apurSubIssqn.getCodigoDependencia()).append("<BR>CNPJ unificador: ").append(ob[1].toString()).append("<BR>al\355quota: ").append(ob[0].toString()).toString();
                regUtil.setErro(apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EM069", 12, (short)2, registro, txtSolucao);
            } while(true);
        }
    }

    public void verificaReg0440()
        throws Exception
    {
        boolean existe0430 = false;
        demMenIssqnDao = (IssqnMensalDao)Contexto.getObject("demMensalIssqnDao");
        List demMensaisIssqn = demMenIssqnDao.findAll();
        List ListApurSubIssqn = apurSubIssqnDao.findAll();
        if(ListApurSubIssqn.size() > 0)
            existe0430 = true;
        if(demMensaisIssqn.size() == 0)
        {
            verificaExisteRegistro0440();
            return;
        }
        Iterator i = demMensaisIssqn.iterator();
        verificaErroInexistenciaRegistro0440Consolidacao();
        double incremento = regUtil.incremetoPorcentagem(20D, demMensaisIssqn.size());
        double sentinela = 80D;
        int atualizar = 0;
        for(; i.hasNext(); verificaValorIssqnRecolher())
        {
            if(sentinela < 100D)
            {
                atualizar = (int)sentinela;
                panGerDec.atualizarProgressoValidacao(atualizar, 100);
                sentinela += incremento;
            }
            demMenIssqn = (IssqnMensal)i.next();
            calculaConsolidacoes(existe0430);
            verificaCnpj();
            verificaCodigoTributacaoDesif();
            verificaDeducaoReceitaDeclaradaConsolidada();
            verificaBaseCalculo();
            verificaAliquotaIssqn(existe0430);
            verificaIssqnDevido();
            verificaIssqnRetido();
            verificaIncentivoFiscal();
            verificaValorCreditoCompensar();
            verificaOrigemCredACompensar();
            verificaValorIssqnRecolhido();
        }

    }

    public void calculaConsolidacoes(boolean existe0430)
    {
        switch(declaracao.getTipoConsolidacao().intValue())
        {
        case 2: // '\002'
        case 4: // '\004'
        default:
            break;

        case 1: // '\001'
            List result1 = apurSubIssqnDao.somaConsolidacao1(demMenIssqn.getValorAliquotaIssqn());
            Object resultados1[] = (Object[])(Object[])result1.get(0);
            if(resultados1[0] != null)
            {
                verificaReceitaDeclaradaConsolidada(new Double(Double.parseDouble(resultados1[0].toString())), existe0430);
                verificaDeducaoReceitaDeclaradaSubTitulo(new Double(Double.parseDouble(resultados1[1].toString())));
                verificaIncentivoFiscalSubtitulo(new Double(Double.parseDouble(resultados1[2].toString())));
            } else
            {
                verificaReceitaDeclaradaConsolidada(Double.valueOf(0.0D), existe0430);
            }
            break;

        case 3: // '\003'
            List result3 = apurSubIssqnDao.somaConsolidacao3(demMenIssqn.getValorAliquotaIssqn(), demMenIssqn.getCnpj());
            Object resultados3[] = (Object[])(Object[])result3.get(0);
            if(resultados3[0] != null)
            {
                verificaReceitaDeclaradaConsolidada(new Double(Double.parseDouble(resultados3[0].toString())), existe0430);
                verificaDeducaoReceitaDeclaradaSubTitulo(new Double(Double.parseDouble(resultados3[1].toString())));
                verificaIncentivoFiscalSubtitulo(new Double(Double.parseDouble(resultados3[2].toString())));
            } else
            {
                verificaReceitaDeclaradaConsolidada(Double.valueOf(0.0D), existe0430);
            }
            break;
        }
    }

    public void verificaExisteRegistro0440()
        throws Exception
    {
        int coluna = 2;
        regUtil.setErro(0L, "EM079", coluna, (short)2, registro);
    }

    public void verificaCnpj()
        throws Exception
    {
        int coluna = 3;
        if(!CpfCnpj.validarCpfCnpj((new StringBuilder()).append(declaracao.getCnpjInstituicao()).append(demMenIssqn.getCnpj()).toString()))
        {
            String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(declaracao.getCnpjInstituicao()).append(demMenIssqn.getCnpj()).toString();
            regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EG004", coluna, (short)2, registro, txtSolucao);
        }
        if(declaracao.getTipoConsolidacao().shortValue() != 3)
            if(declaracao.getTipoConsolidacao().shortValue() != 4);
        if((declaracao.getTipoConsolidacao().shortValue() == 1 || declaracao.getTipoConsolidacao().shortValue() == 2) && !demMenIssqn.getCnpj().equals(declaracao.getCnpjResponsavelRecolhimento()))
        {
            String txtSolucao = (new StringBuilder()).append("Tipo de consolida\347\343o: ").append(declaracao.getTipoConsolidacao()).append("<BR>CNPJ respons\341vel pelo recolhimento: ").append(declaracao.getCnpjResponsavelRecolhimento()).append("CNPJ do registro 0440: ").append(demMenIssqn.getCnpj()).toString();
            regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM013", coluna, (short)2, registro, txtSolucao);
        }
        List resp = dependenciaDao.findField("cnpjUnificado", demMenIssqn.getCnpj());
        if(resp.size() < 1)
        {
            String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(demMenIssqn.getCnpj()).toString();
            regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM037", coluna, (short)2, registro, txtSolucao);
        }
    }

    public void verificaCodigoTributacaoDesif()
        throws Exception
    {
        int coluna = 4;
        if(declaracao.getTipoConsolidacao().shortValue() == 1 || declaracao.getTipoConsolidacao().shortValue() == 3)
        {
            if(!demMenIssqn.getCodigoTributacaoDesIf().equals(""))
            {
                String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(demMenIssqn.getCnpj()).append("<BR>tipo de consolida\347\343o: ").append(declaracao.getTipoConsolidacao()).append("<BR>c\363digo de tributa\347\343o da DESIF: ").append(demMenIssqn.getCodigoTributacaoDesIf()).toString();
                regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM020", coluna, (short)2, registro, txtSolucao);
            }
        } else
        if((declaracao.getTipoConsolidacao().shortValue() == 2 || declaracao.getTipoConsolidacao().shortValue() == 4) && demMenIssqn.getCodigoTributacaoDesIf().equals(""))
        {
            String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(demMenIssqn.getCnpj()).append("<BR>tipo consolida\347\343o: ").append(declaracao.getTipoConsolidacao()).toString();
            regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM078", coluna, (short)2, registro, txtSolucao);
        }
        if(demMenIssqn.getCodigoTributacaoDesIf() == null || demMenIssqn.getCodigoTributacaoDesIf().equals(""))
            return;
        codTribuDao = (CodTributacaoDesifDao)Contexto.getObject("codTribDao");
        List respTrib = codTribuDao.buscaCodTributacaoDesif("id", demMenIssqn.getCodigoTributacaoDesIf());
        if(respTrib.size() < 1)
        {
            String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(demMenIssqn.getCnpj()).append("<BR>c\363digo de tributa\347\343o DES-IF: ").append(demMenIssqn.getCodigoTributacaoDesIf()).toString();
            regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EG011", coluna, (short)2, registro, txtSolucao);
        }
    }

    public void verificaReceitaDeclaradaConsolidada(Double valorConsolidado, boolean existe0430)
    {
        int coluna = 5;
        if((!existe0430) & (demMenIssqn.getValorReceitaDeclaradaConsolidada().doubleValue() > 0.0D))
            regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM080", coluna, (short)2, registro);
        if(valorConsolidado.doubleValue() != demMenIssqn.getValorReceitaDeclaradaConsolidada().doubleValue())
            switch(declaracao.getTipoConsolidacao().intValue())
            {
            case 1: // '\001'
                String txtSolucao1 = (new StringBuilder()).append("Al\355quota: ").append(demMenIssqn.getValorAliquotaIssqn()).append("<BR>valor da receita declarada consolidada: ").append(demMenIssqn.getValorReceitaDeclaradaConsolidada()).append("<BR>soma das receitas declaradas dos registros 0430: ").append(valorConsolidado).toString();
                regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM047", coluna, (short)2, registro, txtSolucao1);
                break;

            case 3: // '\003'
                String txtSolucao3 = (new StringBuilder()).append("CNPJ: ").append(demMenIssqn.getCnpj()).append("<BR>al\355quota: ").append(demMenIssqn.getValorAliquotaIssqn()).append("<BR>valor da receita declarada consolidada : ").append(demMenIssqn.getValorReceitaDeclaradaConsolidada()).append("<BR>soma das receitas declaradas dos registros 0430: ").append(valorConsolidado).toString();
                regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM049", coluna, (short)2, registro, txtSolucao3);
                break;
            }
    }

    public void verificaDeducaoReceitaDeclaradaSubTitulo(Double valorConsolidado)
    {
        int coluna = 6;
        if(valorConsolidado.doubleValue() != demMenIssqn.getValorDeducaoReceitaDeclaradaSubtitulo().doubleValue())
            switch(declaracao.getTipoConsolidacao().intValue())
            {
            case 1: // '\001'
                String txtSolucao1 = (new StringBuilder()).append("Al\355quota: ").append(demMenIssqn.getValorAliquotaIssqn()).append("<BR>valor da dedu\347\343o por Subt\355tulo: ").append(demMenIssqn.getValorDeducaoReceitaDeclaradaSubtitulo()).append("soma das dedu\347\365es dos registros 0430: ").append(valorConsolidado).toString();
                regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM022", coluna, (short)2, registro, txtSolucao1);
                break;

            case 3: // '\003'
                String txtSolucao3 = (new StringBuilder()).append("CNPJ: ").append(demMenIssqn.getCnpj()).append("<br>al\355quota: ").append(demMenIssqn.getValorAliquotaIssqn()).append("<BR>valor da dedu\347\343o: ").append(demMenIssqn.getValorDeducaoReceitaDeclaradaSubtitulo()).append("<BR>soma das dedu\347\365es dos registros 0430: ").append(valorConsolidado).toString();
                regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM024", coluna, (short)2, registro, txtSolucao3);
                break;
            }
    }

    public void verificaDeducaoReceitaDeclaradaConsolidada()
    {
        int coluna = 7;
        if(demMenIssqn.getValorDeducaoReceitaDeclaradaConsolidada().doubleValue() > demMenIssqn.getValorReceitaDeclaradaConsolidada().doubleValue())
        {
            String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(demMenIssqn.getCnpj()).append("<BR>receita declarada consolidada: ").append(demMenIssqn.getValorReceitaDeclaradaConsolidada()).append("<BR>dedu\347\343o da receita por subt\355tulo: ").append(demMenIssqn.getValorDeducaoReceitaDeclaradaSubtitulo()).append("<BR>dedu\347\343o da receita consolidada : ").append(demMenIssqn.getValorDeducaoReceitaDeclaradaConsolidada()).toString();
            regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM051", coluna, (short)2, registro, txtSolucao);
        }
    }

    public void verificaBaseCalculo()
        throws Exception
    {
        int coluna = 9;
        if(demMenIssqn.getValorBaseCalculo().doubleValue() < 0.0D)
        {
            String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(demMenIssqn.getCnpj()).append("<BR>base de c\341lculo: ").append(demMenIssqn.getValorBaseCalculo()).toString();
            regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM070", coluna, (short)2, registro, txtSolucao);
        }
        double calc = demMenIssqn.getValorReceitaDeclaradaConsolidada().doubleValue() - demMenIssqn.getValorDeducaoReceitaDeclaradaConsolidada().doubleValue() - demMenIssqn.getValorDeducaoReceitaDeclaradaSubtitulo().doubleValue();
        calc = regUtil.formataCasasDecimais(calc);
        if(demMenIssqn.getValorBaseCalculo().doubleValue() != calc)
        {
            String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(demMenIssqn.getCnpj()).append("<BR>receita declarada consolidada: ").append(demMenIssqn.getValorReceitaDeclaradaConsolidada()).append("<BR>dedu\347\343o por subt\355tulo: ").append(demMenIssqn.getValorDeducaoReceitaDeclaradaSubtitulo()).append("<BR>dedu\347\343o da receita consolidada: ").append(demMenIssqn.getValorDeducaoReceitaDeclaradaConsolidada()).append("<BR>base de c\341lculo: ").append(demMenIssqn.getValorBaseCalculo()).toString();
            regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM053", coluna, (short)2, registro, txtSolucao);
        }
    }

    public void verificaAliquotaIssqn(boolean existe0430)
        throws Exception
    {
        int coluna = 10;
        if(demMenIssqn.getValorAliquotaIssqn().doubleValue() < 0.0D)
        {
            String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(demMenIssqn.getCnpj()).append("<BR>aliquota: ").append(demMenIssqn.getValorAliquotaIssqn()).toString();
            regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM075", coluna, (short)2, registro, txtSolucao);
        }
        switch(declaracao.getTipoConsolidacao().intValue())
        {
        default:
            break;

        case 1: // '\001'
            if(existe0430)
            {
                boolean resp1 = apurSubIssqnDao.verificaExisteRegistroDependenciaUnificadaConsolidacao1(demMenIssqn.getValorAliquotaIssqn());
                if(!resp1)
                    regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM003", coluna, (short)2, registro);
            } else
            if(demMenIssqn.getValorAliquotaIssqn().doubleValue() > 0.0D)
                regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM081", coluna, (short)2, registro);
            List result1 = demMenIssqnDao.verificaUnicidadeConsolidacao1(demMenIssqn.getValorAliquotaIssqn().toString());
            if(result1.size() > 1)
            {
                String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(demMenIssqn.getCnpj()).toString();
                regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM060", coluna, (short)2, registro, txtSolucao);
            }
            break;

        case 3: // '\003'
            if(existe0430)
            {
                boolean resp3 = apurSubIssqnDao.verificaExisteRegistroDependenciaUnificadaConsolidacao3(demMenIssqn.getValorAliquotaIssqn(), demMenIssqn.getCnpj());
                if(!resp3)
                {
                    String txtSolucao = (new StringBuilder()).append("Tipo de Consolida\347\343o: ").append(declaracao.getTipoConsolidacao()).append(" <BR>CNPJ unificador: ").append(demMenIssqn.getCnpj()).append(" <BR>al\355quota: ").append(demMenIssqn.getValorAliquotaIssqn()).toString();
                    regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM001", coluna, (short)2, registro, txtSolucao);
                }
            } else
            if(demMenIssqn.getValorAliquotaIssqn().doubleValue() > 0.0D)
                regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM081", coluna, (short)2, registro);
            List result3 = demMenIssqnDao.verificaUnicidadeConsolidacao3(demMenIssqn.getValorAliquotaIssqn().toString(), demMenIssqn.getCnpj());
            if(result3.size() > 1)
            {
                String txtSolicitacao = (new StringBuilder()).append("CNPJ: ").append(demMenIssqn.getCnpj()).append("<BR>, c\363digo de tributa\347\343o: ").append(demMenIssqn.getCodigoTributacaoDesIf()).append("<BR>al\355quota: ").append(demMenIssqn.getValorAliquotaIssqn()).toString();
                regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM060", coluna, (short)2, registro, txtSolicitacao);
            }
            break;
        }
    }

    public void verificaIssqnDevido()
        throws Exception
    {
        int coluna = 11;
        double val = (demMenIssqn.getValorBaseCalculo().doubleValue() * demMenIssqn.getValorAliquotaIssqn().doubleValue()) / 100D;
        val = regUtil.formataCasasDecimais(val);
        if(val != demMenIssqn.getValorIssqnDevido().doubleValue())
        {
            NumberFormat format = new DecimalFormat("0.00", DecimalFormatSymbols.getInstance(Locale.US));
            String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(demMenIssqn.getCnpj()).append("<BR>base de c\341lculo: ").append(format.format(demMenIssqn.getValorBaseCalculo())).append("<BR>al\355quota ISSQN: ").append(format.format(demMenIssqn.getValorAliquotaIssqn())).append("<BR>ISSQN devido: ").append(format.format(demMenIssqn.getValorIssqnDevido())).toString();
            regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM054", coluna, (short)2, registro, txtSolucao);
        }
    }

    public void verificaIssqnRetido()
        throws Exception
    {
        int coluna = 12;
        if(demMenIssqn.getValorIssqnRetido().doubleValue() > demMenIssqn.getValorIssqnDevido().doubleValue() - demMenIssqn.getValorIncentivoFiscal().doubleValue() - demMenIssqn.getValorIncentivoFiscalSubtitulo().doubleValue())
        {
            String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(demMenIssqn.getCnpj()).append("<BR>ISSQN devido: ").append(demMenIssqn.getValorIssqnDevido()).append("<BR>incentivo por subt\355tulo: ").append(demMenIssqn.getValorIncentivoFiscalSubtitulo()).append("<BR>incentivo: ").append(demMenIssqn.getValorIncentivoFiscal()).append("<BR>ISSQN retido: ").append(demMenIssqn.getValorIssqnRetido()).toString();
            regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM055", coluna, (short)2, registro, txtSolucao);
        }
    }

    public void verificaIncentivoFiscalSubtitulo(Double valorConsolidado)
    {
        int coluna = 13;
        if(regUtil.formataCasasDecimais(valorConsolidado.doubleValue()) != regUtil.formataCasasDecimais(demMenIssqn.getValorIncentivoFiscalSubtitulo().doubleValue()))
            switch(declaracao.getTipoConsolidacao().intValue())
            {
            case 1: // '\001'
                String txtSolucao1 = (new StringBuilder()).append("Al\355quota: ").append(demMenIssqn.getValorAliquotaIssqn()).append(" <BR>valor do incentivo fiscal por Subt\355tulo: ").append(demMenIssqn.getValorIncentivoFiscalSubtitulo()).append("<BR>soma dos incentivos fiscais dos registros 0430: ").append(valorConsolidado).toString();
                regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM005", coluna, (short)2, registro, txtSolucao1);
                break;

            case 2: // '\002'
                regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM006", coluna, (short)2, registro);
                break;

            case 3: // '\003'
                String txtSolucao3 = (new StringBuilder()).append("Al\355quota: ").append(demMenIssqn.getValorAliquotaIssqn()).append(" <BR>valor do incentivo fiscal por Subt\355tulo: ").append(demMenIssqn.getValorIncentivoFiscalSubtitulo()).append("<BR>soma dos incentivos fiscais dos registros 0430: ").append(valorConsolidado).toString();
                regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM007", coluna, (short)2, registro, txtSolucao3);
                break;

            case 4: // '\004'
                regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM008", coluna, (short)2, registro);
                break;
            }
    }

    public void verificaIncentivoFiscal()
        throws Exception
    {
        int coluna = 14;
        if(demMenIssqn.getValorIncentivoFiscal().doubleValue() < 0.0D)
        {
            String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(demMenIssqn.getCnpj()).append("<BR>valor da incentivo fiscal: ").append(demMenIssqn.getValorIncentivoFiscal()).toString();
            regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM077", coluna, (short)2, registro, txtSolucao);
        }
        if(demMenIssqn.getValorIncentivoFiscal().doubleValue() > demMenIssqn.getValorIssqnDevido().doubleValue())
        {
            String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(demMenIssqn.getCnpj()).append("<BR>ISSQN devido:  ").append(demMenIssqn.getValorIssqnDevido()).append("<BR>incentivo por subt\355tulo: ").append(demMenIssqn.getValorIncentivoFiscalSubtitulo()).append("<BR>incentivo: ").append(demMenIssqn.getValorIncentivoFiscal()).toString();
            regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM056", coluna, (short)2, registro, txtSolucao);
        }
    }

    public void verificaValorCreditoCompensar()
        throws Exception
    {
        int coluna = 16;
        if(demMenIssqn.getValorCredito().doubleValue() > demMenIssqn.getValorIssqnDevido().doubleValue() - (demMenIssqn.getValorIssqnRetido().doubleValue() - demMenIssqn.getValorIncentivoFiscal().doubleValue()))
        {
            String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(demMenIssqn.getCnpj()).append("<BR>Cr\351dito a compensar: ").append(demMenIssqn.getValorCredito()).append("<BR>ISS devido: ").append(demMenIssqn.getValorIssqnDevido()).append("<BR>ISS retido: ").append(demMenIssqn.getValorIssqnRetido()).append("<BR>Incentivo fiscal por subt\355tulo: ").append(demMenIssqn.getValorIncentivoFiscalSubtitulo()).append("<BR>incentivo fiscal: ").append(demMenIssqn.getValorIncentivoFiscal()).toString();
            regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM015", coluna, (short)2, registro, txtSolucao);
        }
        if(demMenIssqn.getValorCredito().doubleValue() < 0.0D)
        {
            String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(demMenIssqn.getCnpj()).append("<BR>valor do cr\351dito: ").append(demMenIssqn.getValorCredito()).toString();
            regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM058", coluna, (short)2, registro, txtSolucao);
        }
    }

    public void verificaOrigemCredACompensar()
    {
        int coluna = 17;
        OrigemCredCompensarDao origCredDao = (OrigemCredCompensarDao)Contexto.getObject("desCredCompDao");
        Double resp = origCredDao.valorSomatorioOrigemCredito(demMenIssqn.getId());
        if(resp == null)
            resp = Double.valueOf(0.0D);
        if(demMenIssqn.getValorCredito().doubleValue() != resp.doubleValue())
        {
            String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(demMenIssqn.getCnpj()).append("<BR>credito a compensar: ").append(demMenIssqn.getValorCredito()).append("<BR>soma dos valores de origem do credito a compensar: ").append(resp).toString();
            regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM036", coluna, (short)2, registro, txtSolucao);
        }
        List l = new ArrayList(demMenIssqn.getOrigemCredCompensars());
        if(l != null)
        {
            for(int i = 0; i < l.size(); i++)
            {
                boolean resp1 = declDao.verificaMaiorDataInicioCompetencia(((OrigemCreditoCompensar)l.get(i)).getDataCompetenciaOrigemCredito());
                if(resp1)
                {
                    String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(demMenIssqn.getCnpj()).append("<BR>al\355quota: ").append(demMenIssqn.getValorAliquotaIssqn()).append("<BR>compet\352ncia da declara\347\343o: ").append(declaracao.getDataInicioCompetencia()).append("<BR>compet\352ncia do cr\351dito a compensar: ").append(((OrigemCreditoCompensar)l.get(i)).getDataCompetenciaOrigemCredito()).toString();
                    regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM011", coluna, (short)2, registro, txtSolucao);
                }
            }

        }
    }

    public void verificaValorIssqnRecolhido()
    {
        int coluna = 18;
        if(demMenIssqn.getValorIssqnRecolhido().doubleValue() < 0.0D)
        {
            String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(demMenIssqn.getCnpj()).append("<BR>al\355quota: ").append(demMenIssqn.getValorAliquotaIssqn()).append(" <BR>valor do ISSQN recolhido: ").append(demMenIssqn.getValorIssqnRecolhido()).toString();
            regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM009", coluna, (short)2, registro, txtSolucao);
        }
    }

    public void verificaValorIssqnRecolher()
        throws Exception
    {
        int coluna = 21;
        double val = demMenIssqn.getValorIssqnDevido().doubleValue() - demMenIssqn.getValorIssqnRetido().doubleValue() - demMenIssqn.getValorIncentivoFiscalSubtitulo().doubleValue() - demMenIssqn.getValorIncentivoFiscal().doubleValue() - demMenIssqn.getValorIssqnRecolhido().doubleValue() - demMenIssqn.getValorCredito().doubleValue();
        if(val < 0.0D && demMenIssqn.getValorIssqnRecolher().doubleValue() != 0.0D)
        {
            String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(demMenIssqn.getCnpj()).append("<BR>al\355quota ").append(demMenIssqn.getValorAliquotaIssqn()).append("<BR>valor do ISSQN a recolher: ").append(demMenIssqn.getValorIssqnRecolher()).toString();
            regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM012", coluna, (short)2, registro, txtSolucao);
        }
        if(demMenIssqn.getMotivoNaoExigibilidade() == null)
        {
            val = regUtil.formataCasasDecimais(val);
            if(val >= 0.0D && val != demMenIssqn.getValorIssqnRecolher().doubleValue())
            {
                String txtSolucao = "";
                if(demMenIssqn.getMotivoNaoExigibilidade() != null)
                    txtSolucao = (new StringBuilder()).append("CNPJ: ").append(demMenIssqn.getCnpj()).append("<BR>ISSQN devido: ").append(demMenIssqn.getValorIssqnDevido()).append("<BR>valor retido: ").append(demMenIssqn.getValorIssqnRetido()).append("<BR>incentivo fiscal por subt\355tulo: ").append(demMenIssqn.getValorIncentivoFiscalSubtitulo()).append("<BR>incentivo fiscal: ").append(demMenIssqn.getValorIncentivoFiscal()).append("<BR>cr\351dito a compensar: ").append(demMenIssqn.getValorCredito()).append("<BR>valor recolhido: ").append(demMenIssqn.getValorIssqnRecolhido()).append("<BR>motivo de n\343o exigibilidade: ").append(demMenIssqn.getMotivoNaoExigibilidade()).toString();
                else
                    txtSolucao = (new StringBuilder()).append("CNPJ: ").append(demMenIssqn.getCnpj()).append("<BR>ISSQN devido: ").append(demMenIssqn.getValorIssqnDevido()).append("<BR>valor retido: ").append(demMenIssqn.getValorIssqnRetido()).append("<BR>incentivo fiscal por subt\355tulo: ").append(demMenIssqn.getValorIncentivoFiscalSubtitulo()).append("<BR>incentivo fiscal: ").append(demMenIssqn.getValorIncentivoFiscal()).append("<BR>cr\351dito a compensar: ").append(demMenIssqn.getValorCredito()).append("<BR>valor recolhido: ").append(demMenIssqn.getValorIssqnRecolhido()).toString();
                regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM040", coluna, (short)2, registro, txtSolucao);
            }
        } else
        if(demMenIssqn.getValorIssqnRecolher().doubleValue() != 0.0D)
        {
            String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(demMenIssqn.getCnpj()).append("<BR>motivo de n\343o exigibilidade: ").append(demMenIssqn.getMotivoNaoExigibilidade()).append("<BR>ISSQN a recolher: ").append(demMenIssqn.getValorIssqnRecolher()).toString();
            regUtil.setErro(demMenIssqn.getLinhaIssqnMensal().intValue(), "EM043", coluna, (short)2, registro, txtSolucao);
        }
    }

    public void updateCnpj()
    {
        declDao = (IdentDeclaracaoDao)Contexto.getObject("declaracaoDao");
        declaracao = (IdentificacaoDeclaracao)declDao.load(new Integer(Integer.parseInt("1")));
        apurSubIssqnDao = (ApuracaoReceitaDao)Contexto.getObject("apuracaoMensalIssqnDao");
        if(declaracao.getTipoConsolidacao().intValue() == 1 || declaracao.getTipoConsolidacao().intValue() == 2)
            apurSubIssqnDao.updateAll(declaracao.getCnpjResponsavelRecolhimento());
        else
        if(declaracao.getTipoConsolidacao().intValue() == 3 || declaracao.getTipoConsolidacao().intValue() == 4)
        {
            dependenciaDao = (IdentDependenciaDao)Contexto.getObject("dependenciaDao");
            List dependencias = dependenciaDao.findAll();
            for(Iterator i = dependencias.iterator(); i.hasNext(); apurSubIssqnDao.updateCodDependencia(dependencia.getCnpjUnificado(), dependencia.getCodigoDependencia()))
                dependencia = (IdentificacaoDependencia)i.next();

        }
    }
}
