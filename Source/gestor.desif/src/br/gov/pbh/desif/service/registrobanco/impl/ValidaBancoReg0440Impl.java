/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  br.gov.pbh.bhiss.utilitarios.validadores.CpfCnpj
 */
package br.gov.pbh.desif.service.registrobanco.impl;

import br.gov.pbh.bhiss.utilitarios.validadores.CpfCnpj;
import br.gov.pbh.desif.dao.ApuracaoReceitaDao;
import br.gov.pbh.desif.dao.CodTributacaoDesifDao;
import br.gov.pbh.desif.dao.IdentDeclaracaoDao;
import br.gov.pbh.desif.dao.IdentDependenciaDao;
import br.gov.pbh.desif.dao.IssqnMensalDao;
import br.gov.pbh.desif.dao.OrigemCredCompensarDao;
import br.gov.pbh.desif.model.pojo.ApuracaoReceita;
import br.gov.pbh.desif.model.pojo.IdentificacaoDeclaracao;
import br.gov.pbh.desif.model.pojo.IssqnMensal;
import br.gov.pbh.desif.model.pojo.OrigemCreditoCompensar;
import br.gov.pbh.desif.model.registros.RegUtil;
import br.gov.pbh.desif.service.contexto.Contexto;
import br.gov.pbh.desif.service.registrobanco.ValidaBancoReg0440;
import br.gov.pbh.desif.view.telas.PanGerarDeclaracao;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class ValidaBancoReg0440Impl
implements ValidaBancoReg0440 {
    private IssqnMensalDao demMensalIssqnDao;
    private ApuracaoReceitaDao apuracaoMensalIssqnDao;
    private PanGerarDeclaracao panGD;
    private IssqnMensal demMenIssqn;
    private IdentDeclaracaoDao declaracaoDao;
    private IdentificacaoDeclaracao declaracao;
    private CodTributacaoDesifDao codTribDao;
    private ApuracaoReceita apurSubIssqn;
    private IdentDependenciaDao dependenciaDao;
    private RegUtil regUtil;
    private final String registro = "0440";

    @Override
    public void executar() throws Exception {
        this.panGD = (PanGerarDeclaracao)Contexto.getObject("panGD");
        this.regUtil = new RegUtil();
        boolean existe0430 = false;
        this.declaracao = (IdentificacaoDeclaracao)this.declaracaoDao.load(new Integer(Integer.parseInt("1")));
        List demMensaisIssqn = this.demMensalIssqnDao.findAll();
        if (demMensaisIssqn.isEmpty()) {
            this.verificaExisteRegistro0440();
            return;
        }
        Iterator i = demMensaisIssqn.iterator();
        this.verificaErroInexistenciaRegistro0440Consolidacao();
        double incremento = this.regUtil.incremetoPorcentagem(10.0, demMensaisIssqn.size());
        double sentinela = 90.0;
        int atualizar = 0;
        while (i.hasNext()) {
            if (sentinela < 100.0) {
                atualizar = (int)sentinela;
                this.panGD.atualizarProgressoValidacao(atualizar, 100);
                sentinela += incremento;
            }
            this.demMenIssqn = (IssqnMensal)i.next();
            List ListApurSubIssqn = this.apuracaoMensalIssqnDao.verificaExisteCnpj0440(this.demMenIssqn.getCnpj());
            if (ListApurSubIssqn.size() > 0) {
                existe0430 = true;
            }
            this.calculaConsolidacoes(existe0430);
            this.verificaCnpj();
            this.verificaCodigoTributacaoDesif();
            this.verificaDeducaoReceitaDeclaradaConsolidada(existe0430);
            this.verificaBaseCalculo(existe0430);
            this.verificaAliquotaIssqn(existe0430);
            this.verificaIssqnDevido(existe0430);
            this.verificaIssqnRetido(existe0430);
            this.verificaIncentivoFiscal(existe0430);
            this.verificaValorCreditoCompensar(existe0430);
            this.verificaOrigemCredACompensar();
            this.verificaValorIssqnRecolhido(existe0430);
            this.verificaMotivoNaoExigibilidade(existe0430);
            this.verificaValorIssqnRecolher(existe0430);
            existe0430 = false;
        }
    }

    public void verificaErroInexistenciaRegistro0440Consolidacao() 
    {
        List apuracoesReceitas = this.apuracaoMensalIssqnDao.buscaRegistros0430ComMovimento();
        Iterator j = apuracoesReceitas.iterator();
        double incremento = this.regUtil.incremetoPorcentagem(10.0, apuracoesReceitas.size());
        double sentinela = 80.0;
        int atualizar = 0;
        String txtSolucao;
        block4 : while (j.hasNext()) 
        {
            if (sentinela < 90.0) 
            {
                atualizar = (int)sentinela;
                this.panGD.atualizarProgressoValidacao(atualizar, 100);
                sentinela += incremento;
            }
            this.apurSubIssqn = (ApuracaoReceita)j.next();
            switch (this.declaracao.getTipoConsolidacao().intValue())
            {
                
                case 1:
                {
                    if (this.apurSubIssqn == null) break;
                    List retorno = this.demMensalIssqnDao.verificaExistenciaRegistro0440Consolidacao1(this.apurSubIssqn.getValorAliquotaIssqn());
                    if (retorno != null && retorno.size() >= 1) continue block4;
                    txtSolucao = "Tipo de consolida\u00e7\u00e3o: " + this.declaracao.getTipoConsolidacao() + "<BR>aliquota: " + this.apurSubIssqn.getValorAliquotaIssqn();
                    this.regUtil.setErro(this.apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EM067", 12, (short)2, "0440", txtSolucao);
                    break;
                }
                case 3: 
                {
                    List respExiste;
                    if (this.apurSubIssqn == null || (respExiste = this.demMensalIssqnDao.verificaExistenciaRegistro0440Consolidacao3(this.apurSubIssqn.getValorAliquotaIssqn().toString(), this.apurSubIssqn.getCnpj())) != null && respExiste.size() >= 1) break;
                    txtSolucao = "Tipo de consolida\u00e7\u00e3o: " + this.declaracao.getTipoConsolidacao() + "<BR>c\u00f3digo da depend\u00eancia: " + this.apurSubIssqn.getCodigoDependencia() + "<BR>CNPJ unificador: " + this.apurSubIssqn.getCnpj() + "<BR>al\u00edquota: " + this.apurSubIssqn.getValorAliquotaIssqn();
                    this.regUtil.setErro(this.apurSubIssqn.getLinhaApuracaoReceita(), "EM069", 12, (short)2, "0440", txtSolucao);
                }
            }
        }
    }

    public void calculaConsolidacoes(boolean existe0430) {
        switch (this.declaracao.getTipoConsolidacao().intValue()) {
            case 1: {
                List result1 = this.apuracaoMensalIssqnDao.somaConsolidacao1(this.demMenIssqn.getValorAliquotaIssqn());
                Object[] resultados1 = (Object[])result1.get(0);
                if (resultados1[0] != null) {
                    this.verificaReceitaDeclaradaConsolidada(new Double(Double.parseDouble(resultados1[0].toString())), existe0430);
                    this.verificaDeducaoReceitaDeclaradaSubTitulo(new Double(Double.parseDouble(resultados1[1].toString())), existe0430);
                    this.verificaIncentivoFiscalSubtitulo(new Double(Double.parseDouble(resultados1[2].toString())), existe0430);
                    break;
                }
                this.verificaReceitaDeclaradaConsolidada(0.0, existe0430);
                break;
            }
            case 2: {
                break;
            }
            case 3: {
                List result3 = this.apuracaoMensalIssqnDao.somaConsolidacao3(this.demMenIssqn.getValorAliquotaIssqn(), this.demMenIssqn.getCnpj());
                Object[] resultados3 = (Object[])result3.get(0);
                if (resultados3[0] != null) {
                    this.verificaReceitaDeclaradaConsolidada(new Double(Double.parseDouble(resultados3[0].toString())), existe0430);
                    this.verificaDeducaoReceitaDeclaradaSubTitulo(new Double(Double.parseDouble(resultados3[1].toString())), existe0430);
                    this.verificaIncentivoFiscalSubtitulo(new Double(Double.parseDouble(resultados3[2].toString())), existe0430);
                    break;
                }
                this.verificaReceitaDeclaradaConsolidada(0.0, existe0430);
                break;
            }
        }
    }

    public void verificaExisteRegistro0440() throws Exception {
        int coluna = 2;
        this.regUtil.setErro(0L, "EM079", coluna, (short)2, "0440");
    }

    public void verificaCnpj() throws Exception {
        String txtSolucao;
        List resp;
        int coluna = 3;
        if (!CpfCnpj.validarCpfCnpj((String)(this.declaracao.getCnpjInstituicao() + this.demMenIssqn.getCnpj()))) {
            txtSolucao = "CNPJ: " + this.declaracao.getCnpjInstituicao() + this.demMenIssqn.getCnpj();
            this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EG004", coluna, (short)2, "0440", txtSolucao);
        }
        if (this.declaracao.getTipoConsolidacao() == 3 || this.declaracao.getTipoConsolidacao() == 4) {
            // empty if block
        }
        if (!(this.declaracao.getTipoConsolidacao() != 1 && this.declaracao.getTipoConsolidacao() != 2 || this.demMenIssqn.getCnpj().equals(this.declaracao.getCnpjResponsavelRecolhimento()))) {
            txtSolucao = "Tipo de consolida\u00e7\u00e3o: " + this.declaracao.getTipoConsolidacao() + "<BR>CNPJ respons\u00e1vel pelo recolhimento: " + this.declaracao.getCnpjResponsavelRecolhimento() + "CNPJ do registro 0440: " + this.demMenIssqn.getCnpj();
            this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM013", coluna, (short)2, "0440", txtSolucao);
        }
        if ((resp = this.dependenciaDao.findField("cnpjUnificado", this.demMenIssqn.getCnpj())).size() < 1) {
            String txtSolucao2 = "CNPJ: " + this.demMenIssqn.getCnpj();
            this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM037", coluna, (short)2, "0440", txtSolucao2);
        }
    }

    public void verificaCodigoTributacaoDesif() throws Exception {
        String txtSolucao;
        int coluna = 4;
        if (this.declaracao.getTipoConsolidacao() == 1 || this.declaracao.getTipoConsolidacao() == 3) {
            if (!this.demMenIssqn.getCodigoTributacaoDesIf().equals("")) {
                txtSolucao = "CNPJ: " + this.demMenIssqn.getCnpj() + "<BR>tipo de consolida\u00e7\u00e3o: " + this.declaracao.getTipoConsolidacao() + "<BR>c\u00f3digo de tributa\u00e7\u00e3o da DESIF: " + this.demMenIssqn.getCodigoTributacaoDesIf();
                this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM020", coluna, (short)2, "0440", txtSolucao);
            }
        } else if ((this.declaracao.getTipoConsolidacao() == 2 || this.declaracao.getTipoConsolidacao() == 4) && this.demMenIssqn.getCodigoTributacaoDesIf().equals("")) {
            txtSolucao = "CNPJ: " + this.demMenIssqn.getCnpj() + "<BR>tipo consolida\u00e7\u00e3o: " + this.declaracao.getTipoConsolidacao();
            this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM078", coluna, (short)2, "0440", txtSolucao);
        }
        if (this.demMenIssqn.getCodigoTributacaoDesIf() == null || this.demMenIssqn.getCodigoTributacaoDesIf().equals("")) {
            return;
        }
        List respTrib = this.codTribDao.buscaCodTributacaoDesif("id", this.demMenIssqn.getCodigoTributacaoDesIf());
        if (respTrib.size() < 1) {
            String txtSolucao2 = "CNPJ: " + this.demMenIssqn.getCnpj() + "<BR>c\u00f3digo de tributa\u00e7\u00e3o DES-IF: " + this.demMenIssqn.getCodigoTributacaoDesIf();
            this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EG011", coluna, (short)2, "0440", txtSolucao2);
        }
    }

    public void verificaReceitaDeclaradaConsolidada(Double valorConsolidado, boolean existe0430) {
        int coluna = 5;
        if (!existe0430 & this.demMenIssqn.getValorReceitaDeclaradaConsolidada() > 0.0) {
            this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM080", coluna, (short)2, "0440");
        }
        if (valorConsolidado.doubleValue() != this.demMenIssqn.getValorReceitaDeclaradaConsolidada().doubleValue()) {
            switch (this.declaracao.getTipoConsolidacao().intValue()) {
                case 1: {
                    String txtSolucao1 = "Al\u00edquota: " + this.demMenIssqn.getValorAliquotaIssqn() + "<BR>valor da receita declarada consolidada: " + this.demMenIssqn.getValorReceitaDeclaradaConsolidada() + "<BR>soma das receitas declaradas dos registros 0430: " + valorConsolidado;
                    this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM047", coluna, (short)2, "0440", txtSolucao1);
                    break;
                }
                case 3: {
                    String txtSolucao3 = "CNPJ: " + this.demMenIssqn.getCnpj() + "<BR>al\u00edquota: " + this.demMenIssqn.getValorAliquotaIssqn() + "<BR>valor da receita declarada consolidada : " + this.demMenIssqn.getValorReceitaDeclaradaConsolidada() + "<BR>soma das receitas declaradas dos registros 0430: " + valorConsolidado;
                    this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM049", coluna, (short)2, "0440", txtSolucao3);
                }
            }
        }
    }

    public void verificaDeducaoReceitaDeclaradaSubTitulo(Double valorConsolidado, boolean existe0430) {
        int coluna = 6;
        if (!existe0430 && this.demMenIssqn.getValorDeducaoReceitaDeclaradaSubtitulo() != null && this.demMenIssqn.getValorDeducaoReceitaDeclaradaSubtitulo() > 0.0) {
            String txtSolucao = "CNPJ a que se refere:" + this.demMenIssqn.getCnpj() + ", dedu\u00e7\u00e3o da receita declarada informados: " + this.demMenIssqn.getValorDeducaoReceitaDeclaradaSubtitulo();
            this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM082", coluna, (short)2, "0440", txtSolucao);
        }
        if (valorConsolidado.doubleValue() != this.demMenIssqn.getValorDeducaoReceitaDeclaradaSubtitulo().doubleValue()) {
            switch (this.declaracao.getTipoConsolidacao().intValue()) {
                case 1: {
                    String txtSolucao1 = "Al\u00edquota: " + this.demMenIssqn.getValorAliquotaIssqn() + "<BR>valor da dedu\u00e7\u00e3o por Subt\u00edtulo: " + this.demMenIssqn.getValorDeducaoReceitaDeclaradaSubtitulo() + "soma das dedu\u00e7\u00f5es dos registros 0430: " + valorConsolidado;
                    this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM022", coluna, (short)2, "0440", txtSolucao1);
                    break;
                }
                case 3: {
                    String txtSolucao3 = "CNPJ: " + this.demMenIssqn.getCnpj() + "<br>al\u00edquota: " + this.demMenIssqn.getValorAliquotaIssqn() + "<BR>valor da dedu\u00e7\u00e3o: " + this.demMenIssqn.getValorDeducaoReceitaDeclaradaSubtitulo() + "<BR>soma das dedu\u00e7\u00f5es dos registros 0430: " + valorConsolidado;
                    this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM024", coluna, (short)2, "0440", txtSolucao3);
                }
            }
        }
    }

    public void verificaDeducaoReceitaDeclaradaConsolidada(boolean existe0430) throws Exception {
        String txtSolucao;
        int coluna = 7;
        if (this.demMenIssqn.getValorDeducaoReceitaDeclaradaConsolidada() > this.demMenIssqn.getValorReceitaDeclaradaConsolidada()) {
            txtSolucao = "CNPJ: " + this.demMenIssqn.getCnpj() + "<BR>receita declarada consolidada: " + this.demMenIssqn.getValorReceitaDeclaradaConsolidada() + "<BR>dedu\u00e7\u00e3o da receita por subt\u00edtulo: " + this.demMenIssqn.getValorDeducaoReceitaDeclaradaSubtitulo() + "<BR>dedu\u00e7\u00e3o da receita consolidada : " + this.demMenIssqn.getValorDeducaoReceitaDeclaradaConsolidada();
            this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM051", coluna, (short)2, "0440", txtSolucao);
        }
        if (!existe0430 && this.demMenIssqn.getValorDeducaoReceitaDeclaradaConsolidada() != null && this.demMenIssqn.getValorDeducaoReceitaDeclaradaConsolidada() > 0.0) {
            txtSolucao = "CNPJ a que se refere:" + this.demMenIssqn.getCnpj() + ", dedu\u00e7\u00e3o da receita declarada consolidada informados: " + this.demMenIssqn.getValorDeducaoReceitaDeclaradaConsolidada();
            this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM083", coluna, (short)2, "0440", txtSolucao);
        }
    }

    public void verificaBaseCalculo(boolean existe0430) throws Exception {
        String txtSolucao;
        int coluna = 9;
        if (this.demMenIssqn.getValorBaseCalculo() < 0.0) {
            txtSolucao = "CNPJ: " + this.demMenIssqn.getCnpj() + "<BR>base de c\u00e1lculo: " + this.demMenIssqn.getValorBaseCalculo();
            this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM070", coluna, (short)2, "0440", txtSolucao);
        }
        if (!existe0430 && this.demMenIssqn.getValorBaseCalculo() != 0.0) {
            txtSolucao = "CNPJ a que se refere:" + this.demMenIssqn.getCnpj() + ", base de c\u00e1lculo informados: " + this.demMenIssqn.getValorBaseCalculo();
            this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM084", coluna, (short)2, "0440", txtSolucao);
        }
        double calc = this.demMenIssqn.getValorReceitaDeclaradaConsolidada() - this.demMenIssqn.getValorDeducaoReceitaDeclaradaConsolidada() - this.demMenIssqn.getValorDeducaoReceitaDeclaradaSubtitulo();
        calc = this.regUtil.formataCasasDecimais(calc);
        if (this.demMenIssqn.getValorBaseCalculo() != calc) {
            String txtSolucao2 = "CNPJ: " + this.demMenIssqn.getCnpj() + "<BR>receita declarada consolidada: " + this.demMenIssqn.getValorReceitaDeclaradaConsolidada() + "<BR>dedu\u00e7\u00e3o por subt\u00edtulo: " + this.demMenIssqn.getValorDeducaoReceitaDeclaradaSubtitulo() + "<BR>dedu\u00e7\u00e3o da receita consolidada: " + this.demMenIssqn.getValorDeducaoReceitaDeclaradaConsolidada() + "<BR>base de c\u00e1lculo: " + this.demMenIssqn.getValorBaseCalculo();
            this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM053", coluna, (short)2, "0440", txtSolucao2);
        }
    }

    public void verificaAliquotaIssqn(boolean existe0430) throws Exception {
        int coluna = 10;
        if (this.demMenIssqn.getValorAliquotaIssqn() < 0.0) {
            String txtSolucao = "CNPJ: " + this.demMenIssqn.getCnpj() + "<BR>aliquota: " + this.demMenIssqn.getValorAliquotaIssqn();
            this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM075", coluna, (short)2, "0440", txtSolucao);
        }
        switch (this.declaracao.getTipoConsolidacao().intValue()) {
            case 1: {
                if (existe0430) {
                    boolean resp1 = this.apuracaoMensalIssqnDao.verificaExisteRegistroDependenciaUnificadaConsolidacao1(this.demMenIssqn.getValorAliquotaIssqn());
                    if (!resp1) {
                        this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM003", coluna, (short)2, "0440");
                    }
                } else if (this.demMenIssqn.getValorAliquotaIssqn() > 0.0) {
                    this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM081", coluna, (short)2, "0440");
                }
                List result1 = this.demMensalIssqnDao.verificaUnicidadeConsolidacao1(this.demMenIssqn.getValorAliquotaIssqn().toString());
                if (result1.size() <= 1) break;
                String txtSolucao = "CNPJ: " + this.demMenIssqn.getCnpj();
                this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM060", coluna, (short)2, "0440", txtSolucao);
                break;
            }
            case 3: {
                if (existe0430) {
                    boolean resp3 = this.apuracaoMensalIssqnDao.verificaExisteRegistroDependenciaUnificadaConsolidacao3(this.demMenIssqn.getValorAliquotaIssqn(), this.demMenIssqn.getCnpj());
                    if (!resp3) {
                        String txtSolucao = "Tipo de Consolida\u00e7\u00e3o: " + this.declaracao.getTipoConsolidacao() + " <BR>CNPJ unificador: " + this.demMenIssqn.getCnpj() + " <BR>al\u00edquota: " + this.demMenIssqn.getValorAliquotaIssqn();
                        this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM001", coluna, (short)2, "0440", txtSolucao);
                    }
                } else if (this.demMenIssqn.getValorAliquotaIssqn() > 0.0) {
                    this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM081", coluna, (short)2, "0440");
                }
                List result3 = this.demMensalIssqnDao.verificaUnicidadeConsolidacao3(this.demMenIssqn.getValorAliquotaIssqn().toString(), this.demMenIssqn.getCnpj());
                if (result3.size() <= 1) break;
                String txtSolicitacao = "CNPJ: " + this.demMenIssqn.getCnpj() + "<BR>, c\u00f3digo de tributa\u00e7\u00e3o: " + this.demMenIssqn.getCodigoTributacaoDesIf() + "<BR>al\u00edquota: " + this.demMenIssqn.getValorAliquotaIssqn();
                this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM060", coluna, (short)2, "0440", txtSolicitacao);
            }
        }
    }

    public void verificaIssqnDevido(boolean existe0430) throws Exception {
        int coluna = 11;
        double val = this.demMenIssqn.getValorBaseCalculo() * this.demMenIssqn.getValorAliquotaIssqn() / 100.0;
        if ((val = this.regUtil.formataCasasDecimais(val)) != this.demMenIssqn.getValorIssqnDevido()) {
            DecimalFormat format = new DecimalFormat("0.00", DecimalFormatSymbols.getInstance(Locale.US));
            String txtSolucao = "CNPJ: " + this.demMenIssqn.getCnpj() + "<BR>base de c\u00e1lculo: " + format.format(this.demMenIssqn.getValorBaseCalculo()) + "<BR>al\u00edquota ISSQN: " + format.format(this.demMenIssqn.getValorAliquotaIssqn()) + "<BR>ISSQN devido: " + format.format(this.demMenIssqn.getValorIssqnDevido());
            this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM054", coluna, (short)2, "0440", txtSolucao);
        }
        if (!existe0430 && this.demMenIssqn.getValorIssqnDevido() != 0.0) {
            String txtSolucao = "CNPJ a que se refere: " + this.demMenIssqn.getCnpj() + ", valor do ISSQN devido informados: " + this.demMenIssqn.getValorIssqnDevido();
            this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM085", coluna, (short)2, "0440", txtSolucao);
        }
    }

    public void verificaIssqnRetido(boolean existe0430) throws Exception {
        String txtSolucao;
        int coluna = 12;
        if (this.demMenIssqn.getValorIssqnRetido() > this.demMenIssqn.getValorIssqnDevido() - this.demMenIssqn.getValorIncentivoFiscal() - this.demMenIssqn.getValorIncentivoFiscalSubtitulo()) {
            txtSolucao = "CNPJ: " + this.demMenIssqn.getCnpj() + "<BR>ISSQN devido: " + this.demMenIssqn.getValorIssqnDevido() + "<BR>incentivo por subt\u00edtulo: " + this.demMenIssqn.getValorIncentivoFiscalSubtitulo() + "<BR>incentivo: " + this.demMenIssqn.getValorIncentivoFiscal() + "<BR>ISSQN retido: " + this.demMenIssqn.getValorIssqnRetido();
            this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM055", coluna, (short)2, "0440", txtSolucao);
        }
        if (!existe0430 & (this.demMenIssqn.getValorIssqnRetido() > 0.0 & this.demMenIssqn.getValorIssqnRetido() != null)) {
            txtSolucao = "CNPJ a que se refere: " + this.demMenIssqn.getCnpj() + ", valor de ISSQN retido informados: " + this.demMenIssqn.getValorIssqnRetido();
            this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM086", coluna, (short)2, "0440", txtSolucao);
        }
    }

    public void verificaIncentivoFiscalSubtitulo(Double valorConsolidado, boolean existe0430) {
        int coluna = 13;
        if (!existe0430 && this.demMenIssqn.getValorIncentivoFiscalSubtitulo() != null && this.demMenIssqn.getValorIncentivoFiscalSubtitulo() >= 0.0) {
            String txtSolucao = "CNPJ a que se refere: " + this.demMenIssqn.getCnpj() + ", valor de incentivo fiscal por Subt\u00edtulo informados: " + this.demMenIssqn.getValorIncentivoFiscalSubtitulo();
            this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM087", coluna, (short)2, "0440", txtSolucao);
        }
        if (this.regUtil.formataCasasDecimais(valorConsolidado) != this.regUtil.formataCasasDecimais(this.demMenIssqn.getValorIncentivoFiscalSubtitulo())) {
            switch (this.declaracao.getTipoConsolidacao().intValue()) {
                case 1: {
                    String txtSolucao1 = "Al\u00edquota: " + this.demMenIssqn.getValorAliquotaIssqn() + " <BR>valor do incentivo fiscal por Subt\u00edtulo: " + this.demMenIssqn.getValorIncentivoFiscalSubtitulo() + "<BR>soma dos incentivos fiscais dos registros 0430: " + valorConsolidado;
                    this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM005", coluna, (short)2, "0440", txtSolucao1);
                    break;
                }
                case 2: {
                    this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM006", coluna, (short)2, "0440");
                    break;
                }
                case 3: {
                    String txtSolucao3 = "Al\u00edquota: " + this.demMenIssqn.getValorAliquotaIssqn() + " <BR>valor do incentivo fiscal por Subt\u00edtulo: " + this.demMenIssqn.getValorIncentivoFiscalSubtitulo() + "<BR>soma dos incentivos fiscais dos registros 0430: " + valorConsolidado;
                    this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM007", coluna, (short)2, "0440", txtSolucao3);
                    break;
                }
                case 4: {
                    this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM008", coluna, (short)2, "0440");
                }
            }
        }
    }

    public void verificaIncentivoFiscal(boolean existe0430) throws Exception {
        String txtSolucao;
        int coluna = 14;
        if (this.demMenIssqn.getValorIncentivoFiscal() < 0.0) {
            txtSolucao = "CNPJ: " + this.demMenIssqn.getCnpj() + "<BR>valor da incentivo fiscal: " + this.demMenIssqn.getValorIncentivoFiscal();
            this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM077", coluna, (short)2, "0440", txtSolucao);
        }
        if (!existe0430 & (this.demMenIssqn.getValorIncentivoFiscal() > 0.0 & this.demMenIssqn.getValorIncentivoFiscal() != null)) {
            txtSolucao = "CNPJ a que se refere:" + this.demMenIssqn.getCnpj() + ", valor de incentivo fiscal informados: " + this.demMenIssqn.getValorIncentivoFiscal();
            this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM088", coluna, (short)2, "0440", txtSolucao);
        }
        if (this.demMenIssqn.getValorIncentivoFiscal() > this.demMenIssqn.getValorIssqnDevido()) {
            txtSolucao = "CNPJ: " + this.demMenIssqn.getCnpj() + "<BR>ISSQN devido:  " + this.demMenIssqn.getValorIssqnDevido() + "<BR>incentivo por subt\u00edtulo: " + this.demMenIssqn.getValorIncentivoFiscalSubtitulo() + "<BR>incentivo: " + this.demMenIssqn.getValorIncentivoFiscal();
            this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM056", coluna, (short)2, "0440", txtSolucao);
        }
    }

    public void verificaValorCreditoCompensar(boolean existe0430) throws Exception {
        String txtSolucao;
        int coluna = 16;
        if (this.demMenIssqn.getValorCredito() > this.demMenIssqn.getValorIssqnDevido() - (this.demMenIssqn.getValorIssqnRetido() - this.demMenIssqn.getValorIncentivoFiscal())) {
            txtSolucao = "CNPJ: " + this.demMenIssqn.getCnpj() + "<BR>Cr\u00e9dito a compensar: " + this.demMenIssqn.getValorCredito() + "<BR>ISS devido: " + this.demMenIssqn.getValorIssqnDevido() + "<BR>ISS retido: " + this.demMenIssqn.getValorIssqnRetido() + "<BR>Incentivo fiscal por subt\u00edtulo: " + this.demMenIssqn.getValorIncentivoFiscalSubtitulo() + "<BR>incentivo fiscal: " + this.demMenIssqn.getValorIncentivoFiscal();
            this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM015", coluna, (short)2, "0440", txtSolucao);
        }
        if (this.demMenIssqn.getValorCredito() < 0.0) {
            txtSolucao = "CNPJ: " + this.demMenIssqn.getCnpj() + "<BR>valor do cr\u00e9dito: " + this.demMenIssqn.getValorCredito();
            this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM058", coluna, (short)2, "0440", txtSolucao);
        }
        if (!existe0430 & (this.demMenIssqn.getValorCredito() > 0.0 & this.demMenIssqn.getValorCredito() != null)) {
            txtSolucao = "CNPJ a que se refere:" + this.demMenIssqn.getCnpj() + ", valor de cr\u00e9dito a compensar informados: " + this.demMenIssqn.getValorCredito();
            this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM089", coluna, (short)2, "0440", txtSolucao);
        }
    }

    public void verificaOrigemCredACompensar() {
        ArrayList l;
        int coluna = 17;
        OrigemCredCompensarDao origCredDao = (OrigemCredCompensarDao)Contexto.getObject("desCredCompDao");
        Double resp = origCredDao.valorSomatorioOrigemCredito(this.demMenIssqn.getId());
        if (resp == null) {
            resp = 0.0;
        }
        if (this.demMenIssqn.getValorCredito().doubleValue() != resp.doubleValue()) {
            String txtSolucao = "CNPJ: " + this.demMenIssqn.getCnpj() + "<BR>credito a compensar: " + this.demMenIssqn.getValorCredito() + "<BR>soma dos valores de origem do credito a compensar: " + resp;
            this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM036", coluna, (short)2, "0440", txtSolucao);
        }
        if ((l = new ArrayList(this.demMenIssqn.getOrigemCredCompensars())) != null) {
            for (int i = 0; i < l.size(); ++i) {
                boolean resp1 = this.declaracaoDao.verificaMaiorDataInicioCompetencia(((OrigemCreditoCompensar)l.get(i)).getDataCompetenciaOrigemCredito());
                if (!resp1) continue;
                String txtSolucao = "CNPJ: " + this.demMenIssqn.getCnpj() + "<BR>al\u00edquota: " + this.demMenIssqn.getValorAliquotaIssqn() + "<BR>compet\u00eancia da declara\u00e7\u00e3o: " + this.declaracao.getDataInicioCompetencia() + "<BR>compet\u00eancia do cr\u00e9dito a compensar: " + ((OrigemCreditoCompensar)l.get(i)).getDataCompetenciaOrigemCredito();
                this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM011", coluna, (short)2, "0440", txtSolucao);
            }
        }
    }

    public void verificaValorIssqnRecolhido(boolean existe0430) {
        String txtSolucao;
        int coluna = 18;
        if (this.demMenIssqn.getValorIssqnRecolhido() < 0.0) {
            txtSolucao = "CNPJ: " + this.demMenIssqn.getCnpj() + "<BR>al\u00edquota: " + this.demMenIssqn.getValorAliquotaIssqn() + " <BR>valor do ISSQN recolhido: " + this.demMenIssqn.getValorIssqnRecolhido();
            this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM009", coluna, (short)2, "0440", txtSolucao);
        }
        if (!existe0430 & (this.demMenIssqn.getValorIssqnRecolhido() > 0.0 & this.demMenIssqn.getValorIssqnRecolhido() != null)) {
            txtSolucao = "CNPJ a que se refere:" + this.demMenIssqn.getCnpj() + ", valor de ISSQN recolhido informados: " + this.demMenIssqn.getValorIssqnRecolhido();
            this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM090", coluna, (short)2, "0440", txtSolucao);
        }
    }

    public void verificaMotivoNaoExigibilidade(boolean existe0430) {
        int coluna = 19;
        if (!existe0430 & this.demMenIssqn.getMotivoNaoExigibilidade() != null) {
            String txtSolucao = "CNPJ a que se refere:" + this.demMenIssqn.getCnpj() + ", motivo de n\u00e3o exigibilidade informados: " + this.demMenIssqn.getMotivoNaoExigibilidade();
            this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM091", coluna, (short)2, "0440", txtSolucao);
        }
    }

    public void verificaValorIssqnRecolher(boolean existe0430) throws Exception {
        String txtSolucao;
        int coluna = 21;
        double val = this.demMenIssqn.getValorIssqnDevido() - this.demMenIssqn.getValorIssqnRetido() - this.demMenIssqn.getValorIncentivoFiscalSubtitulo() - this.demMenIssqn.getValorIncentivoFiscal() - this.demMenIssqn.getValorIssqnRecolhido() - this.demMenIssqn.getValorCredito();
        if (!existe0430 & (this.demMenIssqn.getValorIssqnRecolher() > 0.0 & this.demMenIssqn.getValorIssqnRecolher() != null)) {
            txtSolucao = "CNPJ a que se refere:" + this.demMenIssqn.getCnpj() + ", valor de ISSQN a recolher informados: " + this.demMenIssqn.getValorIssqnRecolher();
            this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM092", coluna, (short)2, "0440", txtSolucao);
        }
        if (val < 0.0 && this.demMenIssqn.getValorIssqnRecolher() != 0.0) {
            txtSolucao = "CNPJ: " + this.demMenIssqn.getCnpj() + "<BR>al\u00edquota " + this.demMenIssqn.getValorAliquotaIssqn() + "<BR>valor do ISSQN a recolher: " + this.demMenIssqn.getValorIssqnRecolher();
            this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM012", coluna, (short)2, "0440", txtSolucao);
        }
        if (this.demMenIssqn.getMotivoNaoExigibilidade() == null) {
            if ((val = this.regUtil.formataCasasDecimais(val)) >= 0.0 && val != this.demMenIssqn.getValorIssqnRecolher()) {
                txtSolucao = "";
                txtSolucao = this.demMenIssqn.getMotivoNaoExigibilidade() != null ? "CNPJ: " + this.demMenIssqn.getCnpj() + "<BR>ISSQN devido: " + this.demMenIssqn.getValorIssqnDevido() + "<BR>valor retido: " + this.demMenIssqn.getValorIssqnRetido() + "<BR>incentivo fiscal por subt\u00edtulo: " + this.demMenIssqn.getValorIncentivoFiscalSubtitulo() + "<BR>incentivo fiscal: " + this.demMenIssqn.getValorIncentivoFiscal() + "<BR>cr\u00e9dito a compensar: " + this.demMenIssqn.getValorCredito() + "<BR>valor recolhido: " + this.demMenIssqn.getValorIssqnRecolhido() + "<BR>motivo de n\u00e3o exigibilidade: " + this.demMenIssqn.getMotivoNaoExigibilidade() : "CNPJ: " + this.demMenIssqn.getCnpj() + "<BR>ISSQN devido: " + this.demMenIssqn.getValorIssqnDevido() + "<BR>valor retido: " + this.demMenIssqn.getValorIssqnRetido() + "<BR>incentivo fiscal por subt\u00edtulo: " + this.demMenIssqn.getValorIncentivoFiscalSubtitulo() + "<BR>incentivo fiscal: " + this.demMenIssqn.getValorIncentivoFiscal() + "<BR>cr\u00e9dito a compensar: " + this.demMenIssqn.getValorCredito() + "<BR>valor recolhido: " + this.demMenIssqn.getValorIssqnRecolhido();
                this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM040", coluna, (short)2, "0440", txtSolucao);
            }
        } else if (this.demMenIssqn.getValorIssqnRecolher() != 0.0) {
            txtSolucao = "CNPJ: " + this.demMenIssqn.getCnpj() + "<BR>motivo de n\u00e3o exigibilidade: " + this.demMenIssqn.getMotivoNaoExigibilidade() + "<BR>ISSQN a recolher: " + this.demMenIssqn.getValorIssqnRecolher();
            this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM043", coluna, (short)2, "0440", txtSolucao);
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

