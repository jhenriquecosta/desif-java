/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.service.registrobanco.impl;

import br.gov.pbh.desif.dao.ApuracaoReceitaDao;
import br.gov.pbh.desif.dao.CodTribuMunicipalDao;
import br.gov.pbh.desif.dao.CodTributacaoDesifDao;
import br.gov.pbh.desif.dao.IdentDeclaracaoDao;
import br.gov.pbh.desif.dao.IdentDependenciaDao;
import br.gov.pbh.desif.dao.IssqnMensalDao;
import br.gov.pbh.desif.model.pojo.ApuracaoReceita;
import br.gov.pbh.desif.model.pojo.IdentificacaoDeclaracao;
import br.gov.pbh.desif.model.pojo.IdentificacaoDependencia;
import br.gov.pbh.desif.model.registros.RegUtil;
import br.gov.pbh.desif.service.contexto.Contexto;
import br.gov.pbh.desif.service.registrobanco.ValidaBancoReg0430;
import br.gov.pbh.desif.view.telas.PanGerarDeclaracao;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ValidaBancoReg0430Impl
implements ValidaBancoReg0430 {
    private ApuracaoReceitaDao apuracaoMensalIssqnDao;
    private RegUtil regUtil;
    private PanGerarDeclaracao panGD;
    private ApuracaoReceita apurSubIssqn;
    private IdentDependenciaDao dependenciaDao;
    private CodTributacaoDesifDao codTribDao;
    private CodTribuMunicipalDao codTribMunicDao;
    private IdentificacaoDeclaracao declaracao;
    private IdentDeclaracaoDao declaracaoDao;
    private IssqnMensalDao demMensalIssqnDao;
    private final String registro = "0430";

    @Override
    public void executar() throws Exception {
        this.regUtil = new RegUtil();
        this.declaracao = (IdentificacaoDeclaracao)this.declaracaoDao.load(new Integer(Integer.parseInt("1")));
        this.panGD = (PanGerarDeclaracao)Contexto.getObject("panGD");
        List apuracoesReceitas = this.apuracaoMensalIssqnDao.buscaRegistros0430ComMovimento();
        System.out.println("tamanho de 0430 => " + apuracoesReceitas.size());
        double incremento = this.regUtil.incremetoPorcentagem(65.0, apuracoesReceitas.size());
        double sentinela = 15.0;
        int atualizar = 0;
        Iterator i = apuracoesReceitas.iterator();
        while (i.hasNext()) 
        {
            if (sentinela < 80.0) 
            {
                atualizar = (int)sentinela;
                this.panGD.atualizarProgressoValidacao(atualizar, 100);
                sentinela += incremento;
            }
            this.apurSubIssqn = (ApuracaoReceita)i.next();
            this.verificarCodDepe();
            this.verificarCodTributacaoDesif();
            this.verificaValCredMens();
            this.verificaValDebtMens();
            this.verificaValReceDecl();
            this.verificaDeduReceDecl();
            this.verificaBaseCalc();
            this.verificaAliqIssqn();
            this.verificaIncentivoFisc();
        }
    }

    public void verificarCodDepe() throws Exception {
        int coluna = 3;
        List resp = this.dependenciaDao.findField("codigoDependencia", this.apurSubIssqn.getCodigoDependencia());
        if (resp.isEmpty()) {
            String txtSolucao = "C\u00f3digo da depend\u00eancia: " + this.apurSubIssqn.getCodigoDependencia();
            this.regUtil.setErro(this.apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EG006", coluna, (short)2, "0430", txtSolucao);
        } else if (((IdentificacaoDependencia)resp.get(0)).getContabilidadePropria().intValue() == 2) {
            String txtSolucao = "C\u00f3digo da depend\u00eancia : " + this.apurSubIssqn.getCodigoDependencia() + "<BR>Contabilidade pr\u00f3pria: " + ((IdentificacaoDependencia)resp.get(0)).getContabilidadePropria();
            this.regUtil.setErro(this.apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EM019", coluna, (short)2, "0430", txtSolucao);
        }
    }

    public void verificarCodTributacaoDesif() {
        int coluna = 5;
        List respTrib = this.codTribDao.buscaCodTributacaoDesif("id", this.apurSubIssqn.getCodigoTributacaoDesIf());
        if (respTrib.size() < 1) {
            String txtSolucao = "C\u00f3digo da depend\u00eancia: " + this.apurSubIssqn.getCodigoDependencia() + "<BR>c\u00f3digo de tributa\u00e7\u00e3o DES-IF: " + this.apurSubIssqn.getCodigoTributacaoDesIf();
            this.regUtil.setErro(this.apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EG011", coluna, (short)2, "0430", txtSolucao);
        }
    }

    public void verificaValCredMens() throws Exception {
        int coluna = 6;
        if (this.apurSubIssqn.getValorCreditoMensal() < 0.0) {
            String txtSolucao = "C\u00f3digo de depend\u00eancia: " + this.apurSubIssqn.getCodigoDependencia() + "<BR>subt\u00edtulo: " + this.apurSubIssqn.getCodigoSubTitulo() + "<BR>total de lan\u00e7amentos a cr\u00e9dito: " + this.apurSubIssqn.getValorCreditoMensal();
            this.regUtil.setErro(this.apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EM026", coluna, (short)2, "0430", txtSolucao);
        }
    }

    public void verificaValDebtMens() throws Exception {
        int coluna = 7;
        if (this.apurSubIssqn.getValorDebitoMensal() < 0.0) {
            String txtSolucao = "C\u00f3digo de depend\u00eancia: " + this.apurSubIssqn.getCodigoDependencia() + "<BR>subt\u00edtulo: " + this.apurSubIssqn.getCodigoSubTitulo() + "<BR>total de lan\u00e7amentos a d\u00e9bito: " + this.apurSubIssqn.getValorDebitoMensal();
            this.regUtil.setErro(this.apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EM027", coluna, (short)2, "0430", txtSolucao);
        }
    }

    public void verificaValReceDecl() throws Exception {
        int coluna = 8;
        if (this.apurSubIssqn.getValorReceitaDeclarada() < 0.0) {
            String txtSolucao = "C\u00f3digo de depend\u00eancia: " + this.apurSubIssqn.getCodigoDependencia() + "<BR>subt\u00edtulo: " + this.apurSubIssqn.getCodigoSubTitulo() + "<BR>receita declarada: " + this.apurSubIssqn.getValorReceitaDeclarada();
            this.regUtil.setErro(this.apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EM021", coluna, (short)2, "0430", txtSolucao);
        }
    }

    public void verificaDeduReceDecl() throws Exception {
        String txtSolucao;
        int coluna = 9;
        if (this.apurSubIssqn.getValorDeducaoReceitaDeclarada() < 0.0) {
            txtSolucao = "C\u00f3digo de depend\u00eancia: " + this.apurSubIssqn.getCodigoDependencia() + "<BR>subt\u00edtulo: " + this.apurSubIssqn.getCodigoSubTitulo() + "<BR>valor da dedu\u00e7\u00e3o da receita declarada: " + this.apurSubIssqn.getValorDeducaoReceitaDeclarada();
            this.regUtil.setErro(this.apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EM064", coluna, (short)2, "0430", txtSolucao);
        }
        if (this.apurSubIssqn.getValorDeducaoReceitaDeclarada() > this.apurSubIssqn.getValorReceitaDeclarada()) {
            txtSolucao = "C\u00f3digo de depend\u00eancia: " + this.apurSubIssqn.getCodigoDependencia() + "<BR>subt\u00edtulo: " + this.apurSubIssqn.getCodigoSubTitulo() + "<BR>receita declarada: " + this.apurSubIssqn.getValorReceitaDeclarada() + "<BR>dedu\u00e7\u00e3o da receita declarada: " + this.apurSubIssqn.getValorDeducaoReceitaDeclarada();
            this.regUtil.setErro(this.apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EM028", coluna, (short)2, "0430", txtSolucao);
        }
    }

    public void verificaBaseCalc() throws Exception {
        int coluna = 11;
        if (this.apurSubIssqn.getValorBaseCalculo() < 0.0) {
            String txtSolucao = "C\u00f3digo da depend\u00eancia: " + this.apurSubIssqn.getCodigoDependencia() + "<BR>subt\u00edtulo: " + this.apurSubIssqn.getCodigoSubTitulo() + "<BR>base de c\u00e1lculo: " + this.apurSubIssqn.getValorBaseCalculo();
            this.regUtil.setErro(this.apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EM032", coluna, (short)2, "0430", txtSolucao);
        }
        Double result = this.apurSubIssqn.getValorReceitaDeclarada() - this.apurSubIssqn.getValorDeducaoReceitaDeclarada();
        if ((result = Double.valueOf(this.regUtil.formataCasasDecimais(result))).doubleValue() != this.apurSubIssqn.getValorBaseCalculo().doubleValue()) {
            String txtSolucao = "C\u00f3digo de depend\u00eancia: " + this.apurSubIssqn.getCodigoDependencia() + "<BR>Subt\u00edtulo: " + this.apurSubIssqn.getCodigoSubTitulo() + "<BR>receita declarada: " + this.apurSubIssqn.getValorReceitaDeclarada() + "<BR>dedu\u00e7\u00e3o da receita: " + this.apurSubIssqn.getValorDeducaoReceitaDeclarada() + "<BR>de c\u00e1lculo: " + this.apurSubIssqn.getValorBaseCalculo();
            this.regUtil.setErro(this.apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EM033", coluna, (short)2, "0430", txtSolucao);
        }
    }

    public void verificaAliqIssqn() throws Exception {
        List retorno;
        boolean resp;
        int coluna = 12;
        if (this.apurSubIssqn.getValorAliquotaIssqn() < 0.0) {
            String txtSolucao = "Codigo depend\u00eancia: " + this.apurSubIssqn.getCodigoDependencia() + "<BR>al\u00edquota: " + this.apurSubIssqn.getValorAliquotaIssqn();
            this.regUtil.setErro(this.apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EM074", coluna, (short)2, "0430", txtSolucao);
        }
        if (resp = this.apuracaoMensalIssqnDao.verificaUnicidade(this.apurSubIssqn.getCodigoDependencia(), this.apurSubIssqn.getCodigoSubTitulo(), this.apurSubIssqn.getCodigoTributacaoDesIf(), this.apurSubIssqn.getValorAliquotaIssqn())) {
            String txtSolucao = "C\u00f3digo de depend\u00eancia: " + this.apurSubIssqn.getCodigoDependencia() + "<BR>Subt\u00edtulo: " + this.apurSubIssqn.getCodigoSubTitulo() + "<BR>c\u00f3digo de tributa\u00e7\u00e3o: " + this.apurSubIssqn.getCodigoTributacaoDesIf() + "<BR>al\u00edquota: " + this.apurSubIssqn.getValorAliquotaIssqn();
            this.regUtil.setErro(this.apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EM059", coluna, (short)2, "0430", txtSolucao);
        }
        if ((retorno = this.codTribMunicDao.buscaCodTribuAliqMunicipal(this.apurSubIssqn.getCodigoTributacaoDesIf(),declaracao.getCidade(), this.apurSubIssqn.getValorAliquotaIssqn(), this.declaracao.getDataInicioCompetencia())).size() < 1) 
        {
            String txtSolucao = "C\u00f3digo de depend\u00eancia: " + this.apurSubIssqn.getCodigoDependencia() + "<BR>m\u00eas-ano da declara\u00e7\u00e3o: " + this.declaracao.getDataInicioCompetencia() + "<BR>c\u00f3digo de tributa\u00e7\u00e3o: " + this.apurSubIssqn.getCodigoTributacaoDesIf() + "<BR>al\u00edquota: " + this.apurSubIssqn.getValorAliquotaIssqn();
            this.regUtil.setErro(this.apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EM046", coluna, (short)2, "0430", txtSolucao);
        }
    }

    public void verificaIncentivoFisc() throws Exception {
        double issqnDevido;
        int coluna = 13;
        if (this.apurSubIssqn.getValorIncentivoFiscal() < 0.0) {
            String txtSolucao = "C\u00f3digo de depend\u00eancia: " + this.apurSubIssqn.getCodigoDependencia() + "<BR>valor do incentivo fiscal: " + this.apurSubIssqn.getValorIncentivoFiscal();
            this.regUtil.setErro(this.apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EM076", coluna, (short)2, "0430", txtSolucao);
        }
        if ((issqnDevido = this.apurSubIssqn.getValorBaseCalculo() * this.apurSubIssqn.getValorAliquotaIssqn() / 100.0) < this.apurSubIssqn.getValorIncentivoFiscal()) {
            String txtSolucao = "C\u00f3digo de depend\u00eancia: " + this.apurSubIssqn.getCodigoDependencia() + "<BR>subt\u00edtulo: " + this.apurSubIssqn.getCodigoSubTitulo() + "<BR>base c\u00e1lculo: " + this.apurSubIssqn.getValorBaseCalculo() + "<BR>al\u00edquota: " + this.apurSubIssqn.getValorAliquotaIssqn() + "<BR>incentivo fiscal: " + this.apurSubIssqn.getValorIncentivoFiscal();
            this.regUtil.setErro(this.apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EM034", coluna, (short)2, "0430", txtSolucao);
        }
    }

    public ApuracaoReceitaDao getApuracaoMensalIssqnDao() {
        return this.apuracaoMensalIssqnDao;
    }

    public void setApuracaoMensalIssqnDao(ApuracaoReceitaDao apuracaoMensalIssqnDao) {
        this.apuracaoMensalIssqnDao = apuracaoMensalIssqnDao;
    }

    public CodTributacaoDesifDao getCodTribDao() {
        return this.codTribDao;
    }

    public void setCodTribDao(CodTributacaoDesifDao codTribDao) {
        this.codTribDao = codTribDao;
    }

    public CodTribuMunicipalDao getCodTribMunicDao() {
        return this.codTribMunicDao;
    }

    public void setCodTribMunicDao(CodTribuMunicipalDao codTribMunicDao) {
        this.codTribMunicDao = codTribMunicDao;
    }

    public IdentDeclaracaoDao getDeclaracaoDao() {
        return this.declaracaoDao;
    }

    public void setDeclaracaoDao(IdentDeclaracaoDao declaracaoDao) {
        this.declaracaoDao = declaracaoDao;
    }

    public IssqnMensalDao getDemMensalIssqnDao() {
        return this.demMensalIssqnDao;
    }

    public void setDemMensalIssqnDao(IssqnMensalDao demMensalIssqnDao) {
        this.demMensalIssqnDao = demMensalIssqnDao;
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
}

