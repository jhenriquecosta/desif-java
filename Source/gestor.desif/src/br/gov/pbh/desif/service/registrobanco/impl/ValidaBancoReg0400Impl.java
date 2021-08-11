/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  br.gov.pbh.bhiss.utilitarios.validadores.CpfCnpj
 *  br.gov.pbh.bhiss.utilitarios.validadores.InscricaoMunicipal
 */
package br.gov.pbh.desif.service.registrobanco.impl;

import br.gov.pbh.bhiss.utilitarios.validadores.CpfCnpj;
import br.gov.pbh.bhiss.utilitarios.validadores.InscricaoMunicipal;
import br.gov.pbh.desif.dao.ApuracaoReceitaDao;
import br.gov.pbh.desif.dao.BalanceteAnaliticoMensalDao;
import br.gov.pbh.desif.dao.CidadeDao;
import br.gov.pbh.desif.dao.IdentDeclaracaoDao;
import br.gov.pbh.desif.dao.IdentDependenciaDao;
import br.gov.pbh.desif.dao.TipoDependenciaDao;
import br.gov.pbh.desif.model.pojo.IdentificacaoDeclaracao;
import br.gov.pbh.desif.model.pojo.IdentificacaoDependencia;
import br.gov.pbh.desif.model.registros.Data;
import br.gov.pbh.desif.model.registros.RegUtil;
import br.gov.pbh.desif.service.contexto.Contexto;
import br.gov.pbh.desif.service.registrobanco.ValidaBancoReg0400;
import br.gov.pbh.desif.view.telas.PanGerarDeclaracao;
import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ValidaBancoReg0400Impl
implements ValidaBancoReg0400 {
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

    @Override
    public void executar() throws Exception {
        this.regUtil = new RegUtil();
        this.dt = new Data();
        this.panGD = (PanGerarDeclaracao)Contexto.getObject("panGD");
        this.declaracao = (IdentificacaoDeclaracao)this.declaracaoDao.load(new Integer(Integer.parseInt("1")));
        this.apuracaoMensalIssqnDao = (ApuracaoReceitaDao)Contexto.getObject("apuracaoMensalIssqnDao");
        List dependencias = this.dependenciaDao.findAll();
        if (dependencias.isEmpty() && (this.declaracao.getModuloDeclaracao() == 1 || this.declaracao.getModuloDeclaracao() == 2)) {
            String txtSolucao = "M\u00f3dulo da declara\u00e7\u00e3o: " + this.declaracao.getModuloDeclaracao();
            this.regUtil.setErro(0L, "ED036", 2, (short)2, "0400", txtSolucao);
        }
        Iterator i = dependencias.iterator();
        double incremento = this.regUtil.incremetoPorcentagem(10.0, dependencias.size());
        double sentinela = 5.0;
        int atualizar = 0;
        while (i.hasNext()) {
            if (sentinela < 15.0) {
                atualizar = (int)sentinela;
                this.panGD.atualizarProgressoValidacao(atualizar, 100);
                sentinela += incremento;
            }
            this.dependencia = (IdentificacaoDependencia)i.next();
            List resp = this.dependenciaDao.findField("codigoDependencia", this.dependencia.getCodigoDependencia());
            if (resp.size() > 1) {
                String txtSolucao = "C\u00f3digo da depend\u00eancia duplicado: " + this.dependencia.getCodigoDependencia();
                this.regUtil.setErro(this.dependencia.getLinhaIdentificacaoDependencia().intValue(), "ED028", 3, (short)2, "0400", txtSolucao);
            }
            this.verificaCodDependencia();
            this.verificaIndInscMunicipal();
            this.verificaCnpjProprio();
            this.verificaCnpjUnificador();
            this.verificaTipoDependencia();
            this.verContabilidadePropria();
            this.verificaCodMunicipioDependencia();
            this.verificaDataInicPara();
            this.verificaDataFimPara();
            this.verificarModuloDeclaracao();
        }
    }

    public void verificaCodDependencia() {
        int coluna = 3;
        boolean validInscMun = true;
        if(dependencia.getOpcaoInscricaoMunicipal() == 1 && !validInscMun )   //!InscricaoMunicipal.validarInscMun(dependencia.getCodigoDependencia()))
        {
            String txtSolucao = "C\u00f3digo dep\u00eandencia: " + this.dependencia.getCodigoDependencia();
            this.regUtil.setErro(this.dependencia.getLinhaIdentificacaoDependencia().intValue(), "ED039", coluna, (short)2, "0400", txtSolucao);
        }
    }

    public void verificaIndInscMunicipal() {
        int coluna = 4;
        if (this.dependencia.getCnpjUnificado().equals(this.dependencia.getCnpjProprio()) && this.dependencia.getOpcaoInscricaoMunicipal() != 1)
        {
            String txtSolucao = "C\u00f3digo da depend\u00eancia: " + this.dependencia.getCodigoDependencia() + "<BR>indicador de inscri\u00e7\u00e3o municipal: " + this.dependencia.getOpcaoInscricaoMunicipal() + "<BR>CNPJ unificador: " + this.dependencia.getCnpjUnificado() + "<BR>CNPJ proprio: " + this.dependencia.getCnpjProprio();
            this.regUtil.setErro(this.dependencia.getLinhaIdentificacaoDependencia().intValue(), "ED041", coluna, (short)2, "0400", txtSolucao);
        }
    }

    public void verificaCnpjProprio() throws Exception {
        int coluna = 5;
        if (!this.dependencia.getCnpjProprio().trim().equals("")) {
            List resp;
            if (!CpfCnpj.validarCpfCnpj((String)(this.declaracao.getCnpjInstituicao() + this.dependencia.getCnpjProprio()))) {
                String txtSolucao = "CNPJ: " + this.declaracao.getCnpjInstituicao() + this.dependencia.getCnpjProprio();
                this.regUtil.setErro(this.dependencia.getLinhaIdentificacaoDependencia().intValue(), "EG004", coluna, (short)2, "0400", txtSolucao);
            }
            if ((resp = this.dependenciaDao.findField("cnpjProprio", this.dependencia.getCnpjProprio())).size() > 1) {
                String txtSolucao = "C\u00f3digo da depend\u00eancia: " + this.dependencia.getCodigoDependencia() + "<BR>CNPJ pr\u00f3prio: " + this.dependencia.getCnpjProprio();
                this.regUtil.setErro(this.dependencia.getLinhaIdentificacaoDependencia().intValue(), "ED029", coluna, (short)2, "0400", txtSolucao);
            }
        }
    }

    public void verificaTipoDependencia() throws Exception {
        int coluna = 6;
        this.tipoDependenciaDao = (TipoDependenciaDao)Contexto.getObject("tipoDependenciaDao");
        List resp = this.tipoDependenciaDao.identificarTipoDependencia("id", this.dependencia.getTipoDependencia());
        if (resp.size() == 0) {
            String txtSolucao = "C\u00f3digo da depend\u00eancia: " + this.dependencia.getCodigoDependencia() + "<BR>tipo de Depend\u00eancia: " + this.dependencia.getTipoDependencia();
            this.regUtil.setErro(this.dependencia.getLinhaIdentificacaoDependencia().intValue(), "ED008", coluna, (short)2, "0400", txtSolucao);
        }
    }

    public void verContabilidadePropria() throws Exception {
        int coluna = 10;
        if (this.dependencia.getDataInicioParalizacao() == null && this.dependencia.getDataFimParalizacao() == null) {
            if (this.dependencia.getCnpjProprio().equals(this.dependencia.getCnpjUnificado()) && this.dependencia.getContabilidadePropria().intValue() == 2) {
                String txtSolucao = "CNPJ unificador: " + this.dependencia.getCnpjUnificado() + "<BR>CNPJ pr\u00f3prio  " + this.dependencia.getCnpjProprio();
                this.regUtil.setErro(this.dependencia.getLinhaIdentificacaoDependencia().intValue(), "ED016", coluna, (short)2, "0400", txtSolucao);
            } else if (this.dependencia.getContabilidadePropria().intValue() == 1 && this.declaracao.getModuloDeclaracao() != 1 && !this.apuracaoMensalIssqnDao.verificaExistenciaCodeDependencia(this.dependencia.getCodigoDependencia())) {
                this.regUtil.setAlerta(this.dependencia.getLinhaIdentificacaoDependencia().intValue(), "A005", coluna, (short)2, "0400");
            }
        } else if (this.dependencia.getCnpjProprio().equals(this.dependencia.getCnpjUnificado()) && this.dependencia.getContabilidadePropria().intValue() == 2) {
            boolean respInicio = this.dependenciaDao.verificaDentroParalisacao(this.declaracao.getDataInicioCompetencia());
            boolean respFim = this.dependenciaDao.verificaDentroParalisacao(this.declaracao.getDataFimCompetencia());
            if (!respInicio && !respFim) {
                String txtSolucao = "CNPJ unificador: " + this.dependencia.getCnpjUnificado() + "<BR>CNPJ pr\u00f3prio  " + this.dependencia.getCnpjProprio();
                this.regUtil.setErro(this.dependencia.getLinhaIdentificacaoDependencia().intValue(), "ED016", coluna, (short)2, "0400", txtSolucao);
            }
        } else if (this.dependencia.getContabilidadePropria().intValue() == 1) {
            boolean respInicio = this.dependenciaDao.verificaDentroParalisacao(this.declaracao.getDataInicioCompetencia());
            boolean respFim = this.dependenciaDao.verificaDentroParalisacao(this.declaracao.getDataFimCompetencia());
            if (!(this.declaracao.getModuloDeclaracao() == 1 || respInicio || respFim || this.apuracaoMensalIssqnDao.verificaExistenciaCodeDependencia(this.dependencia.getCodigoDependencia()))) {
                this.regUtil.setAlerta(this.dependencia.getLinhaIdentificacaoDependencia().intValue(), "A005", coluna, (short)2, "0400");
            }
            if (this.declaracao.getModuloDeclaracao() == 1) {
                this.balancAnalitMensalDao = (BalanceteAnaliticoMensalDao)Contexto.getObject("balancAnalitMensalDao");
                boolean resposta = this.balancAnalitMensalDao.verificaExistenciaCodeDependencia(this.dependencia.getCodigoDependencia());
                if (!resposta) {
                    String txtSolucao = "C\u00f3digo da Depend\u00eancia: " + this.dependencia.getCodigoDependencia() + ", identificador de contabilidade pr\u00f3pria: " + this.dependencia.getContabilidadePropria();
                    this.regUtil.setErro(this.dependencia.getLinhaIdentificacaoDependencia().intValue(), "EC002", coluna, (short)2, "0400", txtSolucao);
                }
            }
        }
    }

    public void verificaCnpjUnificador() throws Exception {
        String txtSolucao;
        List resp;
        int coluna = 8;
        if (!(this.dependencia.getCnpjUnificado().equals(this.dependencia.getCnpjProprio()) || this.dependencia.getTipoDependencia() != 1 && this.dependencia.getTipoDependencia() != 2)) {
            txtSolucao = "C\u00f3digo da depend\u00eancia: " + this.dependencia.getCodigoDependencia() + "<BR>tipo de dep\u00eandencia: " + this.dependencia.getTipoDependencia() + "<BR>CNPJ unificador: " + this.dependencia.getCnpjUnificado() + "<BR>CNPJ proprio: " + this.dependencia.getCnpjProprio();
            this.regUtil.setErro(this.dependencia.getLinhaIdentificacaoDependencia().intValue(), "ED040", coluna, (short)2, "0400", txtSolucao);
        }
        if (!CpfCnpj.validarCpfCnpj((String)(this.declaracao.getCnpjInstituicao() + this.dependencia.getCnpjUnificado()))) {
            txtSolucao = "CNPJ: " + this.declaracao.getCnpjInstituicao() + this.dependencia.getCnpjUnificado();
            this.regUtil.setErro(this.dependencia.getLinhaIdentificacaoDependencia().intValue(), "EG004", coluna, (short)2, "0400", txtSolucao);
        }
        if ((resp = this.dependenciaDao.findField("cnpjProprio", this.dependencia.getCnpjUnificado())).size() < 1) {
            String txtSolucao2 = "C\u00f3digo da depend\u00eancia: " + this.dependencia.getCodigoDependencia() + "<BR>CNPJ Unificador: " + this.dependencia.getCnpjUnificado();
            this.regUtil.setErro(this.dependencia.getLinhaIdentificacaoDependencia().intValue(), "ED038", coluna, (short)2, "0400", txtSolucao2);
        }
    }

    public void verificaCodMunicipioDependencia() throws Exception {
        int coluna = 9;
        if (this.dependencia.getCidade().intValue() != 999999) {
            this.cidadeDao = (CidadeDao)Contexto.getObject("cidadeDao");
            List resp = this.cidadeDao.identificarCodCidade("id", this.dependencia.getCidade());
            if (resp.size() == 0) {
                String txtSolucao = "C\u00f3digo de Munic\u00edpio: " + this.dependencia.getCidade() + " O c\u00f3digo do munic\u00edpio de Belo Horizonte \u00e9 " + 3106200L;
                this.regUtil.setErro(this.dependencia.getLinhaIdentificacaoDependencia().intValue(), "EG001", coluna, (short)2, "0400", txtSolucao);
            }
        }
    }

    public void verificaDataInicPara() throws Exception {
        Date dataFormatar;
        String inicioParalizacao;
        String fimCompetencia;
        int coluna = 11;
        if (this.dependencia.getDataInicioParalizacao() != null && this.dt.comparaDataMaior(inicioParalizacao = this.dt.formataData(this.dependencia.getDataInicioParalizacao(), "yyyyMMdd"), fimCompetencia = this.dt.formataData(dataFormatar = this.declaracao.getDataFimCompetencia(), "yyyyMMdd"), "yyyyMMdd")) {
            String txtSolucao = "C\u00f3digo da depend\u00eancia: " + this.dependencia.getCodigoDependencia() + "<BR>data fim compet\u00eancia da declara\u00e7\u00e3o: " + fimCompetencia + "<BR>data in\u00edcio da paralisa\u00e7\u00e3o: " + inicioParalizacao;
            this.regUtil.setErro(this.dependencia.getLinhaIdentificacaoDependencia().intValue(), "ED030", coluna, (short)2, "0400", txtSolucao);
        }
    }

    public void verificaDataFimPara() throws Exception {
        int coluna = 12;
        if (this.dependencia.getDataFimParalizacao() != null) {
            String fimParalizacao = this.dt.formataData(this.dependencia.getDataFimParalizacao(), "yyyyMMdd");
            String iniCompetencia = this.dt.formataData(this.declaracao.getDataInicioCompetencia(), "yyyyMMdd");
            if (this.dt.comparaDataMaior(iniCompetencia, fimParalizacao, "yyyyMMdd")) {
                String txtSolucao = "C\u00f3digo da depend\u00eancia: " + this.dependencia.getCodigoDependencia() + "<BR>compet\u00eancia da declara\u00e7\u00e3o: " + iniCompetencia + "<BR>data fim da paralisa\u00e7\u00e3o: " + fimParalizacao;
                this.regUtil.setErro(this.dependencia.getLinhaIdentificacaoDependencia().intValue(), "ED027", coluna, (short)2, "0400", txtSolucao);
            }
        }
    }

    public void verificarModuloDeclaracao() throws Exception {
        int coluna = 2;
        short moduloDeclaracao = this.declaracao.getModuloDeclaracao();
        if (moduloDeclaracao == 3) {
            String txtSolucao = "M\u00f3dulo da declara\u00e7\u00e3o informado: " + moduloDeclaracao;
            this.regUtil.setErro(this.dependencia.getLinhaIdentificacaoDependencia().intValue(), "ED050", coluna, (short)2, "0400", txtSolucao);
        }
    }

    public ApuracaoReceitaDao getApuracaoMensalIssqnDao() {
        return this.apuracaoMensalIssqnDao;
    }

    public void setApuracaoMensalIssqnDao(ApuracaoReceitaDao apuracaoMensalIssqnDao) {
        this.apuracaoMensalIssqnDao = apuracaoMensalIssqnDao;
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

    public PanGerarDeclaracao getPanGD() {
        return this.panGD;
    }

    public void setPanGD(PanGerarDeclaracao panGD) {
        this.panGD = panGD;
    }

    public TipoDependenciaDao getTipoDependenciaDao() {
        return this.tipoDependenciaDao;
    }

    public void setTipoDependenciaDao(TipoDependenciaDao tipoDependenciaDao) {
        this.tipoDependenciaDao = tipoDependenciaDao;
    }
}

