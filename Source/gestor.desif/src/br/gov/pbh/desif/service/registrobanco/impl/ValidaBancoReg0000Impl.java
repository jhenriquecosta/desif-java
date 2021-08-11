/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.service.registrobanco.impl;

import br.gov.pbh.desif.dao.CidadeDao;
import br.gov.pbh.desif.dao.IdentDeclaracaoDao;
import br.gov.pbh.desif.dao.IdentDependenciaDao;
import br.gov.pbh.desif.dao.NewIdentDeclaracaoDao;
import br.gov.pbh.desif.dao.TituloDao;
import br.gov.pbh.desif.dao.VersaoDocumentacaoDao;
import br.gov.pbh.desif.model.pojo.IdentificacaoDeclaracao;
import br.gov.pbh.desif.model.pojo.IdentificacaoDependencia;
import br.gov.pbh.desif.model.registros.Data;
import br.gov.pbh.desif.model.registros.RegUtil;
import br.gov.pbh.desif.service.contexto.Contexto;
import br.gov.pbh.desif.service.registrobanco.ValidaBancoReg0000;
import java.io.Serializable;
import java.util.List;

public class ValidaBancoReg0000Impl
implements ValidaBancoReg0000 {
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

    @Override
    public void executar() throws Exception {
        this.regUtil = new RegUtil();
        this.dt = new Data();
        this.declaracao = (IdentificacaoDeclaracao)this.declaracaoDao.load(new Integer(Integer.parseInt("1")));
        this.verificaTipoInstituicao();
        this.verificaCodMunicipio();
        this.verificaVersao();
        if (this.declaracao.getModuloDeclaracao() == 2) {
            this.verificaCnpjRespRclh();
        }
    }

    public void verificaVersao() throws Exception
    {
        int coluna = 14;
        this.versaoDocumentacaoDao = (VersaoDocumentacaoDao)Contexto.getObject("versaoDocumentacaoDao");
        this.newIdentDeclaracaoDao = (NewIdentDeclaracaoDao)Contexto.getObject("newIdentDeclaracaoDao");
        String numVersaoDocumentacao = this.versaoDocumentacaoDao.buscaNumVersaoDocumentacao();
        String idnVersao = this.newIdentDeclaracaoDao.buscaNumVersaoDocumentacao();
        if (numVersaoDocumentacao == null) {
            numVersaoDocumentacao = "-- Vers\u00e3o da documenta\u00e7\u00e3o n\u00e3o foi encontrada no banco de dados local --";
        }
        if (!numVersaoDocumentacao.equals(idnVersao)) {
            String txtSolucao = "Indicador de vers\u00e3o da DES-IF diferente da atual. Indicador da vers\u00e3o atual: " + numVersaoDocumentacao + "\nvers\u00e3o informada: " + idnVersao;
            this.regUtil.setErro(this.declaracao.getLinhaIdentificacaoDeclaracao().intValue(), "ED043", coluna, (short)2, "0000", txtSolucao);
        }
    }

    public void verificaTipoInstituicao() throws Exception {
        int coluna = 5;
        List resp = this.tituloDao.identificarTipoInstituicao("id", this.declaracao.getTitulo().toUpperCase());
        if (resp.size() == 0) {
            String txtSolucao = "Tipo de Institui\u00e7\u00e3o informado: " + this.declaracao.getTitulo();
            this.regUtil.setErro(this.declaracao.getLinhaIdentificacaoDeclaracao().intValue(), "ED003", coluna, (short)2, "0000", txtSolucao);
        }
    }

    public void verificaCodMunicipio() throws Exception {
        int coluna = 6;
        if (this.declaracao.getCidade() != 999999L) {
            this.cidadeDao = (CidadeDao)Contexto.getObject("cidadeDao");
            List resp = this.cidadeDao.identificarCodCidade("id", this.declaracao.getCidade());
            if (resp.size() == 0) {
                String txtSolucao = "C\u00f3digo de Munic\u00edpio: " + this.declaracao.getCidade() + " O c\u00f3digo do munic\u00edpio de Belo Horizonte \u00e9 " + 3106200L;
                this.regUtil.setErro(this.declaracao.getLinhaIdentificacaoDeclaracao().intValue(), "EG001", coluna, (short)2, "0000", txtSolucao);
            }
        }
    }

    public void verificaCnpjRespRclh() throws Exception {
        int coluna = 13;
        if (!this.declaracao.getCnpjResponsavelRecolhimento().trim().equals("")) {
            List dependencias = this.dependenciaDao.findField("cnpjProprio", this.declaracao.getCnpjResponsavelRecolhimento());
            if (dependencias.size() < 1) {
                String txtSolucao = "CNPJ: " + this.declaracao.getCnpjResponsavelRecolhimento();
                this.regUtil.setErro(this.declaracao.getLinhaIdentificacaoDeclaracao().intValue(), "ED032", coluna, (short)2, "0000", txtSolucao);
            } else {
                List dep = this.dependenciaDao.findField("cnpjProprio", this.declaracao.getCnpjResponsavelRecolhimento());
                if (dep.size() == 1) {
                    IdentificacaoDependencia identDep = (IdentificacaoDependencia)dep.get(0);
                    if (identDep.getContabilidadePropria() == 1) {
                        if (identDep.getCidade() != 3106200L) {
                            String txtSolucao = "C\u00f3digo da depend\u00eancia: " + identDep.getCodigoDependencia() + " <BR>CNPJ respons\u00e1vel pelo recolhimento: " + this.declaracao.getCnpjResponsavelRecolhimento() + "<BR>munic\u00edpio da depend\u00eancia : " + identDep.getCidade();
                            this.regUtil.setErro(this.declaracao.getLinhaIdentificacaoDeclaracao().intValue(), "ED033", coluna, (short)2, "0000", txtSolucao);
                        }
                    } else if (((IdentificacaoDependencia)dep.get(0)).getContabilidadePropria() == 2) {
                        String txtSolucao = "C\u00f3digo da depend\u00eancia: " + identDep.getCodigoDependencia() + " <BR>CNPJ respons\u00e1vel pelo recolhimento: " + this.declaracao.getCnpjResponsavelRecolhimento() + "<BR>indicador de contabilidade pr\u00f3pria: " + identDep.getContabilidadePropria();
                        this.regUtil.setErro(this.declaracao.getLinhaIdentificacaoDeclaracao().intValue(), "ED019", coluna, (short)2, "0000", txtSolucao);
                    }
                }
            }
        }
    }

    public CidadeDao getCidadeDao() {
        return this.cidadeDao;
    }

    public void setCidadeDao(CidadeDao cidadeDao) {
        this.cidadeDao = cidadeDao;
    }

    public IdentDeclaracaoDao getDeclaracaoDao() {
        return this.declaracaoDao;
    }

    public void setDeclaracaoDao(IdentDeclaracaoDao declaracaoDao) {
        this.declaracaoDao = declaracaoDao;
    }

    public IdentDependenciaDao getDependenciaDao() {
        return this.dependenciaDao;
    }

    public void setDependenciaDao(IdentDependenciaDao dependenciaDao) {
        this.dependenciaDao = dependenciaDao;
    }

    public TituloDao getTituloDao() {
        return this.tituloDao;
    }

    public void setTituloDao(TituloDao tituloDao) {
        this.tituloDao = tituloDao;
    }
}

