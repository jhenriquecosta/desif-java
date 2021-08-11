
package br.gov.pbh.desif.service.registrobanco.impl;

import br.gov.pbh.desif.dao.*;
import br.gov.pbh.desif.model.pojo.IdentificacaoDeclaracao;
import br.gov.pbh.desif.model.pojo.IdentificacaoDependencia;
import br.gov.pbh.desif.model.registros.Data;
import br.gov.pbh.desif.model.registros.RegUtil;
import br.gov.pbh.desif.service.contexto.Contexto;
import br.gov.pbh.desif.service.registrobanco.ValidaBancoReg0000;
import java.util.List;

public class ValidaBancoReg0000Impl
    implements ValidaBancoReg0000
{

    private IdentDeclaracaoDao declaracaoDao;
    private NewIdentDeclaracaoDao newIdentDeclaracaoDao;
    private TituloDao tituloDao;
    private VersaoDocumentacaoDao versaoDocumentacaoDao;
    private IdentificacaoDeclaracao declaracao;
    private CidadeDao cidadeDao;
    private IdentDependenciaDao dependenciaDao;
    private Data dt;
    private RegUtil regUtil;
    private final String registro = "0000";

    public ValidaBancoReg0000Impl()
    {
    }

    public void executar()
        throws Exception
    {
        regUtil = new RegUtil();
        dt = new Data();
        declaracao = (IdentificacaoDeclaracao)declaracaoDao.load(new Integer(Integer.parseInt("1")));
        verificaTipoInstituicao();
        verificaCodMunicipio();
        verificaVersao();
        if(declaracao.getModuloDeclaracao().shortValue() == 2)
            verificaCnpjRespRclh();
    }

    public void verificaVersao()
        throws Exception
    {
        int coluna = 14;
        versaoDocumentacaoDao = (VersaoDocumentacaoDao)Contexto.getObject("versaoDocumentacaoDao");
        newIdentDeclaracaoDao = (NewIdentDeclaracaoDao)Contexto.getObject("newIdentDeclaracaoDao");
        String numVersaoDocumentacao = versaoDocumentacaoDao.buscaNumVersaoDocumentacao();
        String idnVersao = newIdentDeclaracaoDao.buscaNumVersaoDocumentacao();
        if(numVersaoDocumentacao == null)
            numVersaoDocumentacao = "-- Vers\343o da documenta\347\343o n\343o foi encontrada no banco de dados local --";
        if(!numVersaoDocumentacao.equals(idnVersao))
        {
            String txtSolucao;
            txtSolucao = txtSolucao = (new StringBuilder()).append("Indicador de vers\343o da DES-IF diferente da atual. Indicador da vers\343o atual: ").append(numVersaoDocumentacao).append("\nvers\343o informada: ").append(idnVersao).toString();
            regUtil.setErro(declaracao.getLinhaIdentificacaoDeclaracao().intValue(), "ED043", coluna, (short)2, "0000", txtSolucao);
        }
    }

    public void verificaTipoInstituicao()
        throws Exception
    {
        int coluna = 5;
        List resp = tituloDao.identificarTipoInstituicao("id", declaracao.getTitulo().toUpperCase());
        if(resp.size() == 0)
        {
            String txtSolucao = (new StringBuilder()).append("Tipo de Institui\347\343o informado: ").append(declaracao.getTitulo()).toString();
            regUtil.setErro(declaracao.getLinhaIdentificacaoDeclaracao().intValue(), "ED003", coluna, (short)2, "0000", txtSolucao);
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
                String txtSolucao = (new StringBuilder()).append("C\363digo de Munic\355pio: ").append(declaracao.getCidade()).append(" O c\363digo do munic\355pio de Belo Horizonte \351 ").append(0x2f6598L).toString();
                regUtil.setErro(declaracao.getLinhaIdentificacaoDeclaracao().intValue(), "EG001", coluna, (short)2, "0000", txtSolucao);
            }
        }
    }

    public void verificaCnpjRespRclh()
        throws Exception
    {
        int coluna = 13;
        if(!declaracao.getCnpjResponsavelRecolhimento().equals(""))
        {
            List dependencias = dependenciaDao.findField("cnpjProprio", declaracao.getCnpjResponsavelRecolhimento());
            if(dependencias.size() < 1)
            {
                String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(declaracao.getCnpjResponsavelRecolhimento()).toString();
                regUtil.setErro(declaracao.getLinhaIdentificacaoDeclaracao().intValue(), "ED032", coluna, (short)2, "0000", txtSolucao);
            } else
            {
                List dep = dependenciaDao.findField("cnpjProprio", declaracao.getCnpjResponsavelRecolhimento());
                if(dep.size() == 1)
                {
                    IdentificacaoDependencia identDep = (IdentificacaoDependencia)dep.get(0);
                    if(identDep.getContabilidadePropria().shortValue() == 1)
                    {
                        if(identDep.getCidade().longValue() != 0x2f6598L)
                        {
                            String txtSolucao = (new StringBuilder()).append("C\363digo da depend\352ncia: ").append(identDep.getCodigoDependencia()).append(" <BR>CNPJ respons\341vel pelo recolhimento: ").append(declaracao.getCnpjResponsavelRecolhimento()).append("<BR>munic\355pio da depend\352ncia : ").append(identDep.getCidade()).toString();
                            regUtil.setErro(declaracao.getLinhaIdentificacaoDeclaracao().intValue(), "ED033", coluna, (short)2, "0000", txtSolucao);
                        }
                    } else
                    if(((IdentificacaoDependencia)dep.get(0)).getContabilidadePropria().shortValue() == 2)
                    {
                        String txtSolucao = (new StringBuilder()).append("C\363digo da depend\352ncia: ").append(identDep.getCodigoDependencia()).append(" <BR>CNPJ respons\341vel pelo recolhimento: ").append(declaracao.getCnpjResponsavelRecolhimento()).append("<BR>indicador de contabilidade pr\363pria: ").append(identDep.getContabilidadePropria()).toString();
                        regUtil.setErro(declaracao.getLinhaIdentificacaoDeclaracao().intValue(), "ED019", coluna, (short)2, "0000", txtSolucao);
                    }
                }
            }
        }
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

    public TituloDao getTituloDao()
    {
        return tituloDao;
    }

    public void setTituloDao(TituloDao tituloDao)
    {
        this.tituloDao = tituloDao;
    }
}
