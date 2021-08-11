/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  br.gov.pbh.bhiss.utilitarios.validadores.CpfCnpj
 *  br.gov.pbh.bhiss.utilitarios.validadores.InscricaoMunicipal
 */
package br.gov.pbh.desif.service.registrobanco;

import br.gov.pbh.bhiss.utilitarios.validadores.CpfCnpj;
import br.gov.pbh.bhiss.utilitarios.validadores.InscricaoMunicipal;
import br.gov.pbh.desif.dao.ApuracaoReceitaDao;
import br.gov.pbh.desif.dao.CidadeDao;
import br.gov.pbh.desif.dao.CodTribuMunicipalDao;
import br.gov.pbh.desif.dao.CodTributacaoDesifDao;
import br.gov.pbh.desif.dao.IdentDeclaracaoDao;
import br.gov.pbh.desif.dao.IdentDependenciaDao;
import br.gov.pbh.desif.dao.IssqnMensalDao;
import br.gov.pbh.desif.dao.OrigemCredCompensarDao;
import br.gov.pbh.desif.dao.TipoDependenciaDao;
import br.gov.pbh.desif.dao.TituloDao;
import br.gov.pbh.desif.model.pojo.ApuracaoReceita;
import br.gov.pbh.desif.model.pojo.IdentificacaoDeclaracao;
import br.gov.pbh.desif.model.pojo.IdentificacaoDependencia;
import br.gov.pbh.desif.model.pojo.IssqnMensal;
import br.gov.pbh.desif.model.pojo.OrigemCreditoCompensar;
import br.gov.pbh.desif.model.registros.Data;
import br.gov.pbh.desif.model.registros.RegUtil;
import br.gov.pbh.desif.service.contexto.Contexto;
import br.gov.pbh.desif.view.telas.PanGerarDeclaracao;
import br.gov.pbh.desif.view.util.SwingUtils;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class VerificaRegistroBanco {
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
    private String registro = "";

    public VerificaRegistroBanco() {
        try {
            this.updateCnpj();
        }
        catch (Exception ex) {
            RegUtil.exErro = true;
            SwingUtils.msgErro(null, ex.getCause().toString() + "\n\n Mensagem: " + ex.getMessage());
        }
        try {
            this.regUtil = new RegUtil();
            this.panGerDec = (PanGerarDeclaracao)Contexto.getObject("panGD");
            this.dt = new Data();
            this.panGerDec.atualizarProgressoValidacao(0, 100);
            this.registro = "0000";
            this.verificaReg0000();
        }
        catch (Exception ex) {
            RegUtil.exErro = true;
            SwingUtils.msgErro(null, "\nOcorreu um erro inexperado, por favor contacte o administrador do sistema. \n" + ex.toString() + "\nMensagem: " + ex.getMessage());
            System.out.println(ex.toString());
            ex.printStackTrace();
        }
        try {
            this.panGerDec.atualizarProgressoValidacao(5, 100);
            this.registro = "0400";
            this.verificaReg0400();
        }
        catch (Exception ex) {
            RegUtil.exErro = true;
            SwingUtils.msgErro(null, "\nOcorreu um erro inexperado, por favor contacte o administrador do sistema. \n" + ex.toString() + "\nMensagem: " + ex.getMessage());
            ex.printStackTrace();
        }
        try {
            this.registro = "0430";
            this.verificaReg0430();
        }
        catch (Exception ex) {
            RegUtil.exErro = true;
            SwingUtils.msgErro(null, "\nOcorreu um erro inexperado, por favor contacte o administrador do sistema. \n" + ex.toString() + "\nMensagem: " + ex.getMessage());
            ex.printStackTrace();
        }
        try {
            this.registro = "0440";
            this.verificaReg0440();
            this.panGerDec.atualizarProgressoValidacao(100, 100);
        }
        catch (Exception ex) {
            RegUtil.exErro = true;
            SwingUtils.msgErro(null, "\nOcorreu um erro inexperado, por favor contacte o administrador do sistema. \n" + ex.toString() + "\nMensagem: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void verificaReg0000() throws Exception {
        this.declDao = (IdentDeclaracaoDao)Contexto.getObject("declaracaoDao");
        this.declaracao = (IdentificacaoDeclaracao)this.declDao.load(new Integer(Integer.parseInt("1")));
        this.verificaTipoInstituicao();
        this.verificaCodMunicipio();
        this.verificaCnpjRespRclh();
    }

    public void verificaTipoInstituicao() throws Exception {
        int coluna = 5;
        this.tituloDao = (TituloDao)Contexto.getObject("tituloDao");
        List resp = this.tituloDao.identificarTipoInstituicao("id", this.declaracao.getTitulo().toUpperCase());
        if (resp.size() == 0) {
            String txtSolucao = "Tipo de Institui\u00e7\u00e3o informado: " + this.declaracao.getTitulo();
            this.regUtil.setErro(this.declaracao.getLinhaIdentificacaoDeclaracao().intValue(), "ED003", coluna, (short)2, this.registro, txtSolucao);
        }
    }

    public void verificaCodMunicipio() throws Exception {
        int coluna = 6;
        if (this.declaracao.getCidade() != 999999L) {
            this.cidadeDao = (CidadeDao)Contexto.getObject("cidadeDao");
            List resp = this.cidadeDao.identificarCodCidade("id", this.declaracao.getCidade());
            if (resp.size() == 0) {
                String txtSolucao = "C\u00f3digo de Munic\u00edpio: " + this.declaracao.getCidade();
                this.regUtil.setErro(this.declaracao.getLinhaIdentificacaoDeclaracao().intValue(), "EG001", coluna, (short)2, this.registro, txtSolucao);
            }
        }
    }

    public void verificaCnpjRespRclh() throws Exception {
        int coluna = 13;
        if (!this.declaracao.getCnpjResponsavelRecolhimento().equals("")) {
            this.dependenciaDao = (IdentDependenciaDao)Contexto.getObject("dependenciaDao");
            List dependencias = this.dependenciaDao.findField("cnpjProprio", this.declaracao.getCnpjResponsavelRecolhimento());
            if (dependencias.size() < 1) {
                String txtSolucao = "CNPJ: " + this.declaracao.getCnpjResponsavelRecolhimento();
                this.regUtil.setErro(this.declaracao.getLinhaIdentificacaoDeclaracao().intValue(), "ED032", coluna, (short)2, this.registro, txtSolucao);
            } else {
                List dep = this.dependenciaDao.findField("cnpjProprio", this.declaracao.getCnpjResponsavelRecolhimento());
                if (dep.size() == 1) {
                    if (((IdentificacaoDependencia)dep.get(0)).getContabilidadePropria() == 1) {
                        if (this.declaracao.getCidade() != 3106200L) {
                            String txtSolucao = "C\u00f3digo da depend\u00eancia: " + ((IdentificacaoDependencia)dep.get(0)).getCodigoDependencia() + " <BR>CNPJ respons\u00e1vel pelo recolhimento: " + this.declaracao.getCnpjResponsavelRecolhimento() + "<BR>munic\u00edpio da depend\u00eancia : " + ((IdentificacaoDependencia)((Object)dep)).getCidade();
                            this.regUtil.setErro(this.declaracao.getLinhaIdentificacaoDeclaracao().intValue(), "ED033", coluna, (short)2, this.registro, txtSolucao);
                        }
                    } else if (((IdentificacaoDependencia)dep.get(0)).getContabilidadePropria() == 2) {
                        String txtSolucao = "C\u00f3digo da depend\u00eancia: " + ((IdentificacaoDependencia)dep.get(0)).getCodigoDependencia() + " <BR>CNPJ respons\u00e1vel pelo recolhimento: " + this.declaracao.getCnpjResponsavelRecolhimento() + "<BR>indicador de contabilidade pr\u00f3pria: " + ((IdentificacaoDependencia)dep.get(0)).getContabilidadePropria();
                        this.regUtil.setErro(this.declaracao.getLinhaIdentificacaoDeclaracao().intValue(), "ED019", coluna, (short)2, this.registro, txtSolucao);
                    }
                }
            }
        }
    }

    public void verificaReg0400() throws Exception {
        this.apurSubIssqnDao = (ApuracaoReceitaDao)Contexto.getObject("apuracaoMensalIssqnDao");
        try {
            this.dependenciaDao = (IdentDependenciaDao)Contexto.getObject("dependenciaDao");
            List dependencias = this.dependenciaDao.findAll();
            if (dependencias.size() == 0 && (this.declaracao.getModuloDeclaracao() == 1 || this.declaracao.getModuloDeclaracao() == 2)) {
                String txtSolucao = "M\u00f3dulo da declara\u00e7\u00e3o: " + this.declaracao.getModuloDeclaracao();
                this.regUtil.setErro(0L, "ED036", 2, (short)2, this.registro, txtSolucao);
            }
            Iterator i = dependencias.iterator();
            double incremento = this.regUtil.incremetoPorcentagem(10.0, dependencias.size());
            double sentinela = 5.0;
            int atualizar = 0;
            while (i.hasNext()) {
                if (sentinela < 15.0) {
                    atualizar = (int)sentinela;
                    this.panGerDec.atualizarProgressoValidacao(atualizar, 100);
                    sentinela += incremento;
                }
                this.dependencia = (IdentificacaoDependencia)i.next();
                List resp = this.dependenciaDao.findField("codigoDependencia", this.dependencia.getCodigoDependencia());
                if (resp.size() > 1) {
                    String txtSolucao = "C\u00f3digo da depend\u00eancia duplicado: " + this.dependencia.getCodigoDependencia();
                    this.regUtil.setErro(this.dependencia.getLinhaIdentificacaoDependencia().intValue(), "ED028", 3, (short)2, this.registro, txtSolucao);
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
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void verificaCodDependencia() {
       int coluna = 3;
        boolean validInscMun = true;
        if(dependencia.getOpcaoInscricaoMunicipal() == 1 && !validInscMun )   //!InscricaoMunicipal.validarInscMun(dependencia.getCodigoDependencia()))
        {
            String txtSolucao = "C\u00f3digo dep\u00eandencia: " + this.dependencia.getCodigoDependencia();
            this.regUtil.setErro(this.dependencia.getLinhaIdentificacaoDependencia().intValue(), "ED039", coluna, (short)2, this.registro, txtSolucao);
        }
    }

    public void verificaIndInscMunicipal() {
        int coluna = 4;
        if (this.dependencia.getCnpjUnificado().equals(this.dependencia.getCnpjProprio()) && this.dependencia.getOpcaoInscricaoMunicipal() != 1) 
        {
            String txtSolucao = "C\u00f3digo da depend\u00eancia: " + this.dependencia.getCodigoDependencia() + "<BR>indicador de inscri\u00e7\u00e3o municipal: " + this.dependencia.getOpcaoInscricaoMunicipal() + "<BR>CNPJ unificador: " + this.dependencia.getCnpjUnificado() + "<BR>CNPJ proprio: " + this.dependencia.getCnpjProprio();
            this.regUtil.setErro(this.dependencia.getLinhaIdentificacaoDependencia().intValue(), "ED041", coluna, (short)2, this.registro, txtSolucao);
        }
    }

    public void verificaCnpjProprio() throws Exception {
        int coluna = 5;
        if (!this.dependencia.getCnpjProprio().equals("")) {
            List resp;
            if (!CpfCnpj.validarCpfCnpj((String)(this.declaracao.getCnpjInstituicao() + this.dependencia.getCnpjProprio()))) {
                String txtSolucao = "CNPJ: " + this.declaracao.getCnpjInstituicao() + this.dependencia.getCnpjProprio();
                this.regUtil.setErro(this.dependencia.getLinhaIdentificacaoDependencia().intValue(), "EG004", coluna, (short)2, this.registro, txtSolucao);
            }
            if ((resp = this.dependenciaDao.findField("cnpjProprio", this.dependencia.getCnpjProprio())).size() > 1) {
                String txtSolucao = "C\u00f3digo da depend\u00eancia: " + this.dependencia.getCodigoDependencia() + "<BR>CNPJ pr\u00f3prio: " + this.dependencia.getCnpjProprio();
                this.regUtil.setErro(this.dependencia.getLinhaIdentificacaoDependencia().intValue(), "ED029", coluna, (short)2, this.registro, txtSolucao);
            }
        }
    }

    public void verificaTipoDependencia() throws Exception {
        int coluna = 6;
        this.tipoDependenciaDao = (TipoDependenciaDao)Contexto.getObject("tipoDependenciaDao");
        List resp = this.tipoDependenciaDao.identificarTipoDependencia("id", this.dependencia.getTipoDependencia());
        if (resp.size() == 0) {
            String txtSolucao = "C\u00f3digo da depend\u00eancia: " + this.dependencia.getCodigoDependencia() + "<BR>tipo de Depend\u00eancia: " + this.dependencia.getTipoDependencia();
            this.regUtil.setErro(this.dependencia.getLinhaIdentificacaoDependencia().intValue(), "ED008", coluna, (short)2, this.registro, txtSolucao);
        }
    }

    public void verContabilidadePropria() throws Exception {
        int coluna = 10;
        if (this.dependencia.getDataInicioParalizacao() == null && this.dependencia.getDataInicioParalizacao() == null) {
            if (this.dependencia.getCnpjProprio().equals(this.dependencia.getCnpjUnificado()) && this.dependencia.getContabilidadePropria().intValue() == 2) {
                String txtSolucao = "CNPJ unificador: " + this.dependencia.getCnpjUnificado() + "<BR>CNPJ pr\u00f3prio  " + this.dependencia.getCnpjProprio();
                this.regUtil.setErro(this.dependencia.getLinhaIdentificacaoDependencia().intValue(), "ED016", coluna, (short)2, this.registro, txtSolucao);
            } else if (this.dependencia.getContabilidadePropria().intValue() == 1 && this.declaracao.getModuloDeclaracao() != 1 && !this.apurSubIssqnDao.verificaExistenciaCodeDependencia(this.dependencia.getCodigoDependencia())) {
                this.regUtil.setAlerta(this.dependencia.getLinhaIdentificacaoDependencia().intValue(), "A005", coluna, (short)2, this.registro);
            }
        } else if (this.dependencia.getCnpjProprio().equals(this.dependencia.getCnpjUnificado()) && this.dependencia.getContabilidadePropria().intValue() == 2) {
            boolean respInicio = this.dependenciaDao.verificaDentroParalisacao(this.declaracao.getDataInicioCompetencia());
            boolean respFim = this.dependenciaDao.verificaDentroParalisacao(this.declaracao.getDataFimCompetencia());
            if (!respInicio && !respFim) {
                String txtSolucao = "CNPJ unificador: " + this.dependencia.getCnpjUnificado() + "<BR>CNPJ pr\u00f3prio  " + this.dependencia.getCnpjProprio();
                this.regUtil.setErro(this.dependencia.getLinhaIdentificacaoDependencia().intValue(), "ED016", coluna, (short)2, this.registro, txtSolucao);
            }
        } else if (this.dependencia.getContabilidadePropria().intValue() == 1) {
            boolean respInicio = this.dependenciaDao.verificaDentroParalisacao(this.declaracao.getDataInicioCompetencia());
            boolean respFim = this.dependenciaDao.verificaDentroParalisacao(this.declaracao.getDataFimCompetencia());
            if (!(respInicio || respFim || this.declaracao.getModuloDeclaracao() == 1 || this.apurSubIssqnDao.verificaExistenciaCodeDependencia(this.dependencia.getCodigoDependencia()))) {
                this.regUtil.setAlerta(this.dependencia.getLinhaIdentificacaoDependencia().intValue(), "A005", coluna, (short)2, this.registro);
            }
        }
    }

    public void verificaCnpjUnificador() throws Exception {
        String txtSolucao;
        List resp;
        int coluna = 8;
        if (!(this.dependencia.getCnpjUnificado().equals(this.dependencia.getCnpjProprio()) || this.dependencia.getTipoDependencia() != 1 && this.dependencia.getTipoDependencia() != 2)) {
            txtSolucao = "C\u00f3digo da depend\u00eancia: " + this.dependencia.getCodigoDependencia() + "<BR>tipo de dep\u00eandencia: " + this.dependencia.getTipoDependencia() + "<BR>CNPJ unificador: " + this.dependencia.getCnpjUnificado() + "<BR>CNPJ proprio: " + this.dependencia.getCnpjProprio();
            this.regUtil.setErro(this.dependencia.getLinhaIdentificacaoDependencia().intValue(), "ED040", coluna, (short)2, this.registro, txtSolucao);
        }
        if (!CpfCnpj.validarCpfCnpj((String)(this.declaracao.getCnpjInstituicao() + this.dependencia.getCnpjUnificado()))) {
            txtSolucao = "CNPJ: " + this.declaracao.getCnpjInstituicao() + this.dependencia.getCnpjUnificado();
            this.regUtil.setErro(this.dependencia.getLinhaIdentificacaoDependencia().intValue(), "EG004", coluna, (short)2, this.registro, txtSolucao);
        }
        if ((resp = this.dependenciaDao.findField("cnpjProprio", this.dependencia.getCnpjUnificado())).size() < 1) {
            String txtSolucao2 = "C\u00f3digo da depend\u00eancia: " + this.dependencia.getCodigoDependencia() + "<BR>CNPJ Unificador: " + this.dependencia.getCnpjUnificado();
            this.regUtil.setErro(this.dependencia.getLinhaIdentificacaoDependencia().intValue(), "ED038", coluna, (short)2, this.registro, txtSolucao2);
        }
    }

    public void verificaCodMunicipioDependencia() throws Exception {
        int coluna = 9;
        if (this.dependencia.getCidade().intValue() != 999999) {
            this.cidadeDao = (CidadeDao)Contexto.getObject("cidadeDao");
            List resp = this.cidadeDao.identificarCodCidade("id", this.dependencia.getCidade());
            if (resp.size() == 0) {
                String txtSolucao = "C\u00f3digo de Munic\u00edpio: " + this.declaracao.getCidade();
                this.regUtil.setErro(this.dependencia.getLinhaIdentificacaoDependencia().intValue(), "EG001", coluna, (short)2, this.registro, txtSolucao);
            }
        }
    }

    public void verificaDataInicPara() throws Exception {
        String data2;
        String data1;
        int coluna = 11;
        if (this.dependencia.getDataInicioParalizacao() != null && this.dt.comparaDataMaior(data1 = this.dependencia.getDataInicioParalizacao().toString().replaceAll("-", ""), data2 = this.declaracao.getDataFimCompetencia().toString().replaceAll("-", ""), "yyyyMMdd")) {
            String data3 = this.declaracao.getDataInicioCompetencia().toString().replaceAll("-", "");
            String txtSolucao = "C\u00f3digo da depend\u00eancia: " + this.dependencia.getCodigoDependencia() + "<BR>data in\u00edcio compet\u00eancia da declara\u00e7\u00e3o: " + data3 + "<BR>data in\u00edcio da paralisa\u00e7\u00e3o: " + data1;
            this.regUtil.setErro(this.dependencia.getLinhaIdentificacaoDependencia().intValue(), "ED030", coluna, (short)2, this.registro, txtSolucao);
        }
    }

    public void verificaDataFimPara() throws Exception {
        int coluna = 12;
        if (this.dependencia.getDataFimParalizacao() != null) {
            String data1 = this.dependencia.getDataFimParalizacao().toString().replaceAll("-", "");
            String data2 = this.declaracao.getDataInicioCompetencia().toString().replaceAll("-", "");
            if (this.dt.comparaDataMaior(data2, data1, "yyyyMMdd")) {
                String txtSolucao = "C\u00f3digo da depend\u00eancia: " + this.dependencia.getCodigoDependencia() + "<BR>compet\u00eancia da declara\u00e7\u00e3o: " + data2 + "<BR>data fim da paralisa\u00e7\u00e3o: " + data1;
                this.regUtil.setErro(this.dependencia.getLinhaIdentificacaoDependencia().intValue(), "ED027", coluna, (short)2, this.registro, txtSolucao);
            }
        }
    }

    public void verificaReg0430() throws Exception {
        this.apurSubIssqnDao = (ApuracaoReceitaDao)Contexto.getObject("apuracaoMensalIssqnDao");
        List apuracoesReceitas = this.apurSubIssqnDao.findAll();
        double incremento = this.regUtil.incremetoPorcentagem(65.0, apuracoesReceitas.size());
        double sentinela = 15.0;
        int atualizar = 0;
        Iterator i = apuracoesReceitas.iterator();
        while (i.hasNext()) {
            if (sentinela < 80.0) {
                atualizar = (int)sentinela;
                this.panGerDec.atualizarProgressoValidacao(atualizar, 100);
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
        if (resp.size() == 0) {
            String txtSolucao = "C\u00f3digo da depend\u00eancia: " + this.apurSubIssqn.getCodigoDependencia();
            this.regUtil.setErro(this.apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EG006", coluna, (short)2, this.registro, txtSolucao);
        } else if (((IdentificacaoDependencia)resp.get(0)).getContabilidadePropria().intValue() == 2) {
            String txtSolucao = "C\u00f3digo da depend\u00eancia : " + this.apurSubIssqn.getCodigoDependencia() + "<BR>Contabilidade pr\u00f3pria: " + ((IdentificacaoDependencia)resp.get(0)).getContabilidadePropria();
            this.regUtil.setErro(this.apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EM019", coluna, (short)2, this.registro, txtSolucao);
        }
    }

    public void verificarCodTributacaoDesif() {
        int coluna = 5;
        this.codTribuDao = (CodTributacaoDesifDao)Contexto.getObject("codTribDao");
        List respTrib = this.codTribuDao.buscaCodTributacaoDesif("id", this.apurSubIssqn.getCodigoTributacaoDesIf());
        if (respTrib.size() < 1) {
            String txtSolucao = "C\u00f3digo da depend\u00eancia: " + this.apurSubIssqn.getCodigoDependencia() + "<BR>c\u00f3digo de tributa\u00e7\u00e3o DES-IF: " + this.apurSubIssqn.getCodigoTributacaoDesIf();
            this.regUtil.setErro(this.apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EG011", coluna, (short)2, this.registro, txtSolucao);
        }
    }

    public void verificaValCredMens() throws Exception {
        int coluna = 6;
        if (this.apurSubIssqn.getValorCreditoMensal() < 0.0) {
            String txtSolucao = "C\u00f3digo de depend\u00eancia: " + this.apurSubIssqn.getCodigoDependencia() + "<BR>subt\u00edtulo: " + this.apurSubIssqn.getCodigoSubTitulo() + "<BR>total de lan\u00e7amentos a cr\u00e9dito: " + this.apurSubIssqn.getValorCreditoMensal();
            this.regUtil.setErro(this.apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EM026", coluna, (short)2, this.registro, txtSolucao);
        }
    }

    public void verificaValDebtMens() throws Exception {
        int coluna = 7;
        if (this.apurSubIssqn.getValorDebitoMensal() < 0.0) {
            String txtSolucao = "C\u00f3digo de depend\u00eancia: " + this.apurSubIssqn.getCodigoDependencia() + "<BR>subt\u00edtulo: " + this.apurSubIssqn.getCodigoSubTitulo() + "<BR>total de lan\u00e7amentos a d\u00e9bito: " + this.apurSubIssqn.getValorDebitoMensal();
            this.regUtil.setErro(this.apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EM027", coluna, (short)2, this.registro, txtSolucao);
        }
    }

    public void verificaValReceDecl() throws Exception {
        int coluna = 8;
        if (this.apurSubIssqn.getValorReceitaDeclarada() < 0.0) {
            String txtSolucao = "C\u00f3digo de depend\u00eancia: " + this.apurSubIssqn.getCodigoDependencia() + "<BR>subt\u00edtulo: " + this.apurSubIssqn.getCodigoSubTitulo() + "<BR>receita declarada: " + this.apurSubIssqn.getValorReceitaDeclarada();
            this.regUtil.setErro(this.apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EM021", coluna, (short)2, this.registro, txtSolucao);
        }
    }

    public void verificaDeduReceDecl() throws Exception {
        String txtSolucao;
        int coluna = 9;
        if (this.apurSubIssqn.getValorDeducaoReceitaDeclarada() < 0.0) {
            txtSolucao = "C\u00f3digo de depend\u00eancia: " + this.apurSubIssqn.getCodigoDependencia() + "<BR>subt\u00edtulo: " + this.apurSubIssqn.getCodigoSubTitulo() + "<BR>valor da dedu\u00e7\u00e3o da receita declarada: " + this.apurSubIssqn.getValorDeducaoReceitaDeclarada();
            this.regUtil.setErro(this.apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EM064", coluna, (short)2, this.registro, txtSolucao);
        }
        if (this.apurSubIssqn.getValorDeducaoReceitaDeclarada() > this.apurSubIssqn.getValorReceitaDeclarada()) {
            txtSolucao = "C\u00f3digo de depend\u00eancia: " + this.apurSubIssqn.getCodigoDependencia() + "<BR>subt\u00edtulo: " + this.apurSubIssqn.getCodigoSubTitulo() + "<BR>receita declarada: " + this.apurSubIssqn.getValorReceitaDeclarada() + "<BR>dedu\u00e7\u00e3o da receita declarada: " + this.apurSubIssqn.getValorDeducaoReceitaDeclarada();
            this.regUtil.setErro(this.apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EM028", coluna, (short)2, this.registro, txtSolucao);
        }
    }

    public void verificaBaseCalc() throws Exception {
        String txtSolucao;
        int coluna = 11;
        if (this.apurSubIssqn.getValorBaseCalculo() < 0.0) {
            txtSolucao = "C\u00f3digo da depend\u00eancia: " + this.apurSubIssqn.getCodigoDependencia() + "<BR>subt\u00edtulo: " + this.apurSubIssqn.getCodigoSubTitulo() + "<BR>base de c\u00e1lculo: " + this.apurSubIssqn.getValorBaseCalculo();
            this.regUtil.setErro(this.apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EM032", coluna, (short)2, this.registro, txtSolucao);
        }
        if (this.apurSubIssqn.getValorReceitaDeclarada() - this.apurSubIssqn.getValorDeducaoReceitaDeclarada() != this.apurSubIssqn.getValorBaseCalculo()) {
            txtSolucao = "C\u00f3digo de depend\u00eancia: " + this.apurSubIssqn.getCodigoDependencia() + "<BR>Subt\u00edtulo: " + this.apurSubIssqn.getCodigoSubTitulo() + "<BR>receita declarada: " + this.apurSubIssqn.getValorReceitaDeclarada() + "<BR>dedu\u00e7\u00e3o da receita: " + this.apurSubIssqn.getValorDeducaoReceitaDeclarada() + "<BR>de c\u00e1lculo: " + this.apurSubIssqn.getValorBaseCalculo();
            this.regUtil.setErro(this.apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EM033", coluna, (short)2, this.registro, txtSolucao);
        }
    }

    public void verificaAliqIssqn() {
        boolean resp;
        int coluna = 12;
        if (this.apurSubIssqn.getValorAliquotaIssqn() < 0.0) {
            String txtSolucao = "Codigo depend\u00eancia: " + this.apurSubIssqn.getCodigoDependencia() + "<BR>al\u00edquota: " + this.apurSubIssqn.getValorAliquotaIssqn();
            this.regUtil.setErro(this.apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EM074", coluna, (short)2, this.registro, txtSolucao);
        }
        if (resp = this.apurSubIssqnDao.verificaUnicidade(this.apurSubIssqn.getCodigoDependencia(), this.apurSubIssqn.getCodigoSubTitulo(), this.apurSubIssqn.getCodigoTributacaoDesIf(), this.apurSubIssqn.getValorAliquotaIssqn())) {
            String txtSolucao = "C\u00f3digo de depend\u00eancia: " + this.apurSubIssqn.getCodigoDependencia() + "<BR>Subt\u00edtulo: " + this.apurSubIssqn.getCodigoSubTitulo() + "<BR>c\u00f3digo de tributa\u00e7\u00e3o: " + this.apurSubIssqn.getCodigoTributacaoDesIf() + "<BR>al\u00edquota: " + this.apurSubIssqn.getValorAliquotaIssqn();
            this.regUtil.setErro(this.apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EM059", coluna, (short)2, this.registro, txtSolucao);
        }
        this.codTribuMuniDao = (CodTribuMunicipalDao)Contexto.getObject("codTribMunicDao");
        List retorno = this.codTribuMuniDao.buscaCodTribuAliqMunicipal(this.apurSubIssqn.getCodigoTributacaoDesIf(), new Long(3106200L), this.apurSubIssqn.getValorAliquotaIssqn(), this.declaracao.getDataInicioCompetencia());
        if (retorno.size() < 1) {
            String txtSolucao = "C\u00f3digo de depend\u00eancia: " + this.apurSubIssqn.getCodigoDependencia() + "<BR>m\u00eas-ano da declara\u00e7\u00e3o: " + this.declaracao.getDataInicioCompetencia() + "<BR>c\u00f3digo de tributa\u00e7\u00e3o: " + this.apurSubIssqn.getCodigoTributacaoDesIf() + "<BR>al\u00edquota: " + this.apurSubIssqn.getValorAliquotaIssqn();
            this.regUtil.setErro(this.apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EM046", coluna, (short)2, this.registro, txtSolucao);
        }
    }

    public void verificaIncentivoFisc() throws Exception {
        double issqnDevido;
        int coluna = 13;
        if (this.apurSubIssqn.getValorIncentivoFiscal() < 0.0) {
            String txtSolucao = "C\u00f3digo de depend\u00eancia: " + this.apurSubIssqn.getCodigoDependencia() + "<BR>valor do incentivo fiscal: " + this.apurSubIssqn.getValorIncentivoFiscal();
            this.regUtil.setErro(this.apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EM076", coluna, (short)2, this.registro, txtSolucao);
        }
        if ((issqnDevido = this.apurSubIssqn.getValorBaseCalculo() * this.apurSubIssqn.getValorAliquotaIssqn() / 100.0) < this.apurSubIssqn.getValorIncentivoFiscal()) {
            String txtSolucao = "C\u00f3digo de depend\u00eancia: " + this.apurSubIssqn.getCodigoDependencia() + "<BR>subt\u00edtulo: " + this.apurSubIssqn.getCodigoSubTitulo() + "<BR>base c\u00e1lculo: " + this.apurSubIssqn.getValorBaseCalculo() + "<BR>al\u00edquota: " + this.apurSubIssqn.getValorAliquotaIssqn() + "<BR>incentivo fiscal: " + this.apurSubIssqn.getValorIncentivoFiscal();
            this.regUtil.setErro(this.apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EM034", coluna, (short)2, this.registro, txtSolucao);
        }
    }

    public void verificaErroInexistenciaRegistro0440Consolidacao() 
    {
        switch (this.declaracao.getTipoConsolidacao().intValue())
        {
            case 1: 
            {
                List retorno;
                if (this.apurSubIssqn == null || (retorno = this.demMenIssqnDao.verificaExistenciaRegistro0440Consolidacao1(this.apurSubIssqn.getValorAliquotaIssqn())) != null && retorno.size() >= 1) break;
                String txtSolucao = "Tipo de consolida\u00e7\u00e3o: " + this.declaracao.getTipoConsolidacao() + "<BR>aliquota: " + this.apurSubIssqn.getValorAliquotaIssqn();
                this.regUtil.setErro(this.apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EM067", 12, (short)2, this.registro, txtSolucao);
                break;
            }
            case 2:
            {
                break;
            }
            case 3: {
                if (this.apurSubIssqn == null) break;
                List<Object[]> resp = this.apurSubIssqnDao.buscaDadosAliquotaCnpj();
                for (Object[] ob : resp)
                {
                    List respExiste;
                    if (ob[0] == null || ob[1] == null || (respExiste = this.demMenIssqnDao.verificaExistenciaRegistro0440Consolidacao3(ob[0].toString(), ob[1].toString())) != null && respExiste.size() >= 1) continue;
                    String txtSolucao = "Tipo de consolida\u00e7\u00e3o: " + this.declaracao.getTipoConsolidacao() + "<BR>c\u00f3digo da depend\u00eancia: " + this.apurSubIssqn.getCodigoDependencia() + "<BR>CNPJ unificador: " + ob[1].toString() + "<BR>al\u00edquota: " + ob[0].toString();
                    this.regUtil.setErro(this.apurSubIssqn.getLinhaApuracaoReceita().intValue(), "EM069", 12, (short)2, this.registro, txtSolucao);
                }
            }
        }
    }

    public void verificaReg0440() throws Exception {
        boolean existe0430 = false;
        this.demMenIssqnDao = (IssqnMensalDao)Contexto.getObject("demMensalIssqnDao");
        List demMensaisIssqn = this.demMenIssqnDao.findAll();
        List ListApurSubIssqn = this.apurSubIssqnDao.findAll();
        if (ListApurSubIssqn.size() > 0) {
            existe0430 = true;
        }
        if (demMensaisIssqn.size() == 0) {
            this.verificaExisteRegistro0440();
            return;
        }
        Iterator i = demMensaisIssqn.iterator();
        this.verificaErroInexistenciaRegistro0440Consolidacao();
        double incremento = this.regUtil.incremetoPorcentagem(20.0, demMensaisIssqn.size());
        double sentinela = 80.0;
        int atualizar = 0;
        while (i.hasNext()) {
            if (sentinela < 100.0) {
                atualizar = (int)sentinela;
                this.panGerDec.atualizarProgressoValidacao(atualizar, 100);
                sentinela += incremento;
            }
            this.demMenIssqn = (IssqnMensal)i.next();
            this.calculaConsolidacoes(existe0430);
            this.verificaCnpj();
            this.verificaCodigoTributacaoDesif();
            this.verificaDeducaoReceitaDeclaradaConsolidada();
            this.verificaBaseCalculo();
            this.verificaAliquotaIssqn(existe0430);
            this.verificaIssqnDevido();
            this.verificaIssqnRetido();
            this.verificaIncentivoFiscal();
            this.verificaValorCreditoCompensar();
            this.verificaOrigemCredACompensar();
            this.verificaValorIssqnRecolhido();
            this.verificaValorIssqnRecolher();
        }
    }

    public void calculaConsolidacoes(boolean existe0430) {
        switch (this.declaracao.getTipoConsolidacao().intValue()) {
            case 1: {
                List result1 = this.apurSubIssqnDao.somaConsolidacao1(this.demMenIssqn.getValorAliquotaIssqn());
                Object[] resultados1 = (Object[])result1.get(0);
                if (resultados1[0] != null) {
                    this.verificaReceitaDeclaradaConsolidada(new Double(Double.parseDouble(resultados1[0].toString())), existe0430);
                    this.verificaDeducaoReceitaDeclaradaSubTitulo(new Double(Double.parseDouble(resultados1[1].toString())));
                    this.verificaIncentivoFiscalSubtitulo(new Double(Double.parseDouble(resultados1[2].toString())));
                    break;
                }
                this.verificaReceitaDeclaradaConsolidada(0.0, existe0430);
                break;
            }
            case 2: {
                break;
            }
            case 3: {
                List result3 = this.apurSubIssqnDao.somaConsolidacao3(this.demMenIssqn.getValorAliquotaIssqn(), this.demMenIssqn.getCnpj());
                Object[] resultados3 = (Object[])result3.get(0);
                if (resultados3[0] != null) {
                    this.verificaReceitaDeclaradaConsolidada(new Double(Double.parseDouble(resultados3[0].toString())), existe0430);
                    this.verificaDeducaoReceitaDeclaradaSubTitulo(new Double(Double.parseDouble(resultados3[1].toString())));
                    this.verificaIncentivoFiscalSubtitulo(new Double(Double.parseDouble(resultados3[2].toString())));
                    break;
                }
                this.verificaReceitaDeclaradaConsolidada(0.0, existe0430);
                break;
            }
        }
    }

    public void verificaExisteRegistro0440() throws Exception {
        int coluna = 2;
        this.regUtil.setErro(0L, "EM079", coluna, (short)2, this.registro);
    }

    public void verificaCnpj() throws Exception {
        String txtSolucao;
        List resp;
        int coluna = 3;
        if (!CpfCnpj.validarCpfCnpj((String)(this.declaracao.getCnpjInstituicao() + this.demMenIssqn.getCnpj()))) {
            txtSolucao = "CNPJ: " + this.declaracao.getCnpjInstituicao() + this.demMenIssqn.getCnpj();
            this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EG004", coluna, (short)2, this.registro, txtSolucao);
        }
        if (this.declaracao.getTipoConsolidacao() == 3 || this.declaracao.getTipoConsolidacao() == 4) {
            // empty if block
        }
        if (!(this.declaracao.getTipoConsolidacao() != 1 && this.declaracao.getTipoConsolidacao() != 2 || this.demMenIssqn.getCnpj().equals(this.declaracao.getCnpjResponsavelRecolhimento()))) {
            txtSolucao = "Tipo de consolida\u00e7\u00e3o: " + this.declaracao.getTipoConsolidacao() + "<BR>CNPJ respons\u00e1vel pelo recolhimento: " + this.declaracao.getCnpjResponsavelRecolhimento() + "CNPJ do registro 0440: " + this.demMenIssqn.getCnpj();
            this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM013", coluna, (short)2, this.registro, txtSolucao);
        }
        if ((resp = this.dependenciaDao.findField("cnpjUnificado", this.demMenIssqn.getCnpj())).size() < 1) {
            String txtSolucao2 = "CNPJ: " + this.demMenIssqn.getCnpj();
            this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM037", coluna, (short)2, this.registro, txtSolucao2);
        }
    }

    public void verificaCodigoTributacaoDesif() throws Exception {
        String txtSolucao;
        int coluna = 4;
        if (this.declaracao.getTipoConsolidacao() == 1 || this.declaracao.getTipoConsolidacao() == 3) {
            if (!this.demMenIssqn.getCodigoTributacaoDesIf().equals("")) {
                txtSolucao = "CNPJ: " + this.demMenIssqn.getCnpj() + "<BR>tipo de consolida\u00e7\u00e3o: " + this.declaracao.getTipoConsolidacao() + "<BR>c\u00f3digo de tributa\u00e7\u00e3o da DESIF: " + this.demMenIssqn.getCodigoTributacaoDesIf();
                this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM020", coluna, (short)2, this.registro, txtSolucao);
            }
        } else if ((this.declaracao.getTipoConsolidacao() == 2 || this.declaracao.getTipoConsolidacao() == 4) && this.demMenIssqn.getCodigoTributacaoDesIf().equals("")) {
            txtSolucao = "CNPJ: " + this.demMenIssqn.getCnpj() + "<BR>tipo consolida\u00e7\u00e3o: " + this.declaracao.getTipoConsolidacao();
            this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM078", coluna, (short)2, this.registro, txtSolucao);
        }
        if (this.demMenIssqn.getCodigoTributacaoDesIf() == null || this.demMenIssqn.getCodigoTributacaoDesIf().equals("")) {
            return;
        }
        this.codTribuDao = (CodTributacaoDesifDao)Contexto.getObject("codTribDao");
        List respTrib = this.codTribuDao.buscaCodTributacaoDesif("id", this.demMenIssqn.getCodigoTributacaoDesIf());
        if (respTrib.size() < 1) {
            String txtSolucao2 = "CNPJ: " + this.demMenIssqn.getCnpj() + "<BR>c\u00f3digo de tributa\u00e7\u00e3o DES-IF: " + this.demMenIssqn.getCodigoTributacaoDesIf();
            this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EG011", coluna, (short)2, this.registro, txtSolucao2);
        }
    }

    public void verificaReceitaDeclaradaConsolidada(Double valorConsolidado, boolean existe0430) {
        int coluna = 5;
        if (!existe0430 & this.demMenIssqn.getValorReceitaDeclaradaConsolidada() > 0.0) {
            this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM080", coluna, (short)2, this.registro);
        }
        if (valorConsolidado.doubleValue() != this.demMenIssqn.getValorReceitaDeclaradaConsolidada().doubleValue()) {
            switch (this.declaracao.getTipoConsolidacao().intValue()) {
                case 1: {
                    String txtSolucao1 = "Al\u00edquota: " + this.demMenIssqn.getValorAliquotaIssqn() + "<BR>valor da receita declarada consolidada: " + this.demMenIssqn.getValorReceitaDeclaradaConsolidada() + "<BR>soma das receitas declaradas dos registros 0430: " + valorConsolidado;
                    this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM047", coluna, (short)2, this.registro, txtSolucao1);
                    break;
                }
                case 3: {
                    String txtSolucao3 = "CNPJ: " + this.demMenIssqn.getCnpj() + "<BR>al\u00edquota: " + this.demMenIssqn.getValorAliquotaIssqn() + "<BR>valor da receita declarada consolidada : " + this.demMenIssqn.getValorReceitaDeclaradaConsolidada() + "<BR>soma das receitas declaradas dos registros 0430: " + valorConsolidado;
                    this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM049", coluna, (short)2, this.registro, txtSolucao3);
                }
            }
        }
    }

    public void verificaDeducaoReceitaDeclaradaSubTitulo(Double valorConsolidado) {
        int coluna = 6;
        if (valorConsolidado.doubleValue() != this.demMenIssqn.getValorDeducaoReceitaDeclaradaSubtitulo().doubleValue()) {
            switch (this.declaracao.getTipoConsolidacao().intValue()) {
                case 1: {
                    String txtSolucao1 = "Al\u00edquota: " + this.demMenIssqn.getValorAliquotaIssqn() + "<BR>valor da dedu\u00e7\u00e3o por Subt\u00edtulo: " + this.demMenIssqn.getValorDeducaoReceitaDeclaradaSubtitulo() + "soma das dedu\u00e7\u00f5es dos registros 0430: " + valorConsolidado;
                    this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM022", coluna, (short)2, this.registro, txtSolucao1);
                    break;
                }
                case 3: {
                    String txtSolucao3 = "CNPJ: " + this.demMenIssqn.getCnpj() + "<br>al\u00edquota: " + this.demMenIssqn.getValorAliquotaIssqn() + "<BR>valor da dedu\u00e7\u00e3o: " + this.demMenIssqn.getValorDeducaoReceitaDeclaradaSubtitulo() + "<BR>soma das dedu\u00e7\u00f5es dos registros 0430: " + valorConsolidado;
                    this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM024", coluna, (short)2, this.registro, txtSolucao3);
                }
            }
        }
    }

    public void verificaDeducaoReceitaDeclaradaConsolidada() {
        int coluna = 7;
        if (this.demMenIssqn.getValorDeducaoReceitaDeclaradaConsolidada() > this.demMenIssqn.getValorReceitaDeclaradaConsolidada()) {
            String txtSolucao = "CNPJ: " + this.demMenIssqn.getCnpj() + "<BR>receita declarada consolidada: " + this.demMenIssqn.getValorReceitaDeclaradaConsolidada() + "<BR>dedu\u00e7\u00e3o da receita por subt\u00edtulo: " + this.demMenIssqn.getValorDeducaoReceitaDeclaradaSubtitulo() + "<BR>dedu\u00e7\u00e3o da receita consolidada : " + this.demMenIssqn.getValorDeducaoReceitaDeclaradaConsolidada();
            this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM051", coluna, (short)2, this.registro, txtSolucao);
        }
    }

    public void verificaBaseCalculo() throws Exception {
        int coluna = 9;
        if (this.demMenIssqn.getValorBaseCalculo() < 0.0) {
            String txtSolucao = "CNPJ: " + this.demMenIssqn.getCnpj() + "<BR>base de c\u00e1lculo: " + this.demMenIssqn.getValorBaseCalculo();
            this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM070", coluna, (short)2, this.registro, txtSolucao);
        }
        double calc = this.demMenIssqn.getValorReceitaDeclaradaConsolidada() - this.demMenIssqn.getValorDeducaoReceitaDeclaradaConsolidada() - this.demMenIssqn.getValorDeducaoReceitaDeclaradaSubtitulo();
        calc = this.regUtil.formataCasasDecimais(calc);
        if (this.demMenIssqn.getValorBaseCalculo() != calc) {
            String txtSolucao = "CNPJ: " + this.demMenIssqn.getCnpj() + "<BR>receita declarada consolidada: " + this.demMenIssqn.getValorReceitaDeclaradaConsolidada() + "<BR>dedu\u00e7\u00e3o por subt\u00edtulo: " + this.demMenIssqn.getValorDeducaoReceitaDeclaradaSubtitulo() + "<BR>dedu\u00e7\u00e3o da receita consolidada: " + this.demMenIssqn.getValorDeducaoReceitaDeclaradaConsolidada() + "<BR>base de c\u00e1lculo: " + this.demMenIssqn.getValorBaseCalculo();
            this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM053", coluna, (short)2, this.registro, txtSolucao);
        }
    }

    public void verificaAliquotaIssqn(boolean existe0430) throws Exception {
        int coluna = 10;
        if (this.demMenIssqn.getValorAliquotaIssqn() < 0.0) {
            String txtSolucao = "CNPJ: " + this.demMenIssqn.getCnpj() + "<BR>aliquota: " + this.demMenIssqn.getValorAliquotaIssqn();
            this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM075", coluna, (short)2, this.registro, txtSolucao);
        }
        switch (this.declaracao.getTipoConsolidacao().intValue()) {
            case 1: {
                if (existe0430) {
                    boolean resp1 = this.apurSubIssqnDao.verificaExisteRegistroDependenciaUnificadaConsolidacao1(this.demMenIssqn.getValorAliquotaIssqn());
                    if (!resp1) {
                        this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM003", coluna, (short)2, this.registro);
                    }
                } else if (this.demMenIssqn.getValorAliquotaIssqn() > 0.0) {
                    this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM081", coluna, (short)2, this.registro);
                }
                List result1 = this.demMenIssqnDao.verificaUnicidadeConsolidacao1(this.demMenIssqn.getValorAliquotaIssqn().toString());
                if (result1.size() <= 1) break;
                String txtSolucao = "CNPJ: " + this.demMenIssqn.getCnpj();
                this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM060", coluna, (short)2, this.registro, txtSolucao);
                break;
            }
            case 3: {
                if (existe0430) {
                    boolean resp3 = this.apurSubIssqnDao.verificaExisteRegistroDependenciaUnificadaConsolidacao3(this.demMenIssqn.getValorAliquotaIssqn(), this.demMenIssqn.getCnpj());
                    if (!resp3) {
                        String txtSolucao = "Tipo de Consolida\u00e7\u00e3o: " + this.declaracao.getTipoConsolidacao() + " <BR>CNPJ unificador: " + this.demMenIssqn.getCnpj() + " <BR>al\u00edquota: " + this.demMenIssqn.getValorAliquotaIssqn();
                        this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM001", coluna, (short)2, this.registro, txtSolucao);
                    }
                } else if (this.demMenIssqn.getValorAliquotaIssqn() > 0.0) {
                    this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM081", coluna, (short)2, this.registro);
                }
                List result3 = this.demMenIssqnDao.verificaUnicidadeConsolidacao3(this.demMenIssqn.getValorAliquotaIssqn().toString(), this.demMenIssqn.getCnpj());
                if (result3.size() <= 1) break;
                String txtSolicitacao = "CNPJ: " + this.demMenIssqn.getCnpj() + "<BR>, c\u00f3digo de tributa\u00e7\u00e3o: " + this.demMenIssqn.getCodigoTributacaoDesIf() + "<BR>al\u00edquota: " + this.demMenIssqn.getValorAliquotaIssqn();
                this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM060", coluna, (short)2, this.registro, txtSolicitacao);
            }
        }
    }

    public void verificaIssqnDevido() throws Exception {
        int coluna = 11;
        double val = this.demMenIssqn.getValorBaseCalculo() * this.demMenIssqn.getValorAliquotaIssqn() / 100.0;
        if ((val = this.regUtil.formataCasasDecimais(val)) != this.demMenIssqn.getValorIssqnDevido()) {
            DecimalFormat format = new DecimalFormat("0.00", DecimalFormatSymbols.getInstance(Locale.US));
            String txtSolucao = "CNPJ: " + this.demMenIssqn.getCnpj() + "<BR>base de c\u00e1lculo: " + format.format(this.demMenIssqn.getValorBaseCalculo()) + "<BR>al\u00edquota ISSQN: " + format.format(this.demMenIssqn.getValorAliquotaIssqn()) + "<BR>ISSQN devido: " + format.format(this.demMenIssqn.getValorIssqnDevido());
            this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM054", coluna, (short)2, this.registro, txtSolucao);
        }
    }

    public void verificaIssqnRetido() throws Exception {
        int coluna = 12;
        if (this.demMenIssqn.getValorIssqnRetido() > this.demMenIssqn.getValorIssqnDevido() - this.demMenIssqn.getValorIncentivoFiscal() - this.demMenIssqn.getValorIncentivoFiscalSubtitulo()) {
            String txtSolucao = "CNPJ: " + this.demMenIssqn.getCnpj() + "<BR>ISSQN devido: " + this.demMenIssqn.getValorIssqnDevido() + "<BR>incentivo por subt\u00edtulo: " + this.demMenIssqn.getValorIncentivoFiscalSubtitulo() + "<BR>incentivo: " + this.demMenIssqn.getValorIncentivoFiscal() + "<BR>ISSQN retido: " + this.demMenIssqn.getValorIssqnRetido();
            this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM055", coluna, (short)2, this.registro, txtSolucao);
        }
    }

    public void verificaIncentivoFiscalSubtitulo(Double valorConsolidado) {
        int coluna = 13;
        if (this.regUtil.formataCasasDecimais(valorConsolidado) != this.regUtil.formataCasasDecimais(this.demMenIssqn.getValorIncentivoFiscalSubtitulo())) {
            switch (this.declaracao.getTipoConsolidacao().intValue()) {
                case 1: {
                    String txtSolucao1 = "Al\u00edquota: " + this.demMenIssqn.getValorAliquotaIssqn() + " <BR>valor do incentivo fiscal por Subt\u00edtulo: " + this.demMenIssqn.getValorIncentivoFiscalSubtitulo() + "<BR>soma dos incentivos fiscais dos registros 0430: " + valorConsolidado;
                    this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM005", coluna, (short)2, this.registro, txtSolucao1);
                    break;
                }
                case 2: {
                    this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM006", coluna, (short)2, this.registro);
                    break;
                }
                case 3: {
                    String txtSolucao3 = "Al\u00edquota: " + this.demMenIssqn.getValorAliquotaIssqn() + " <BR>valor do incentivo fiscal por Subt\u00edtulo: " + this.demMenIssqn.getValorIncentivoFiscalSubtitulo() + "<BR>soma dos incentivos fiscais dos registros 0430: " + valorConsolidado;
                    this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM007", coluna, (short)2, this.registro, txtSolucao3);
                    break;
                }
                case 4: {
                    this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM008", coluna, (short)2, this.registro);
                }
            }
        }
    }

    public void verificaIncentivoFiscal() throws Exception {
        String txtSolucao;
        int coluna = 14;
        if (this.demMenIssqn.getValorIncentivoFiscal() < 0.0) {
            txtSolucao = "CNPJ: " + this.demMenIssqn.getCnpj() + "<BR>valor da incentivo fiscal: " + this.demMenIssqn.getValorIncentivoFiscal();
            this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM077", coluna, (short)2, this.registro, txtSolucao);
        }
        if (this.demMenIssqn.getValorIncentivoFiscal() > this.demMenIssqn.getValorIssqnDevido()) {
            txtSolucao = "CNPJ: " + this.demMenIssqn.getCnpj() + "<BR>ISSQN devido:  " + this.demMenIssqn.getValorIssqnDevido() + "<BR>incentivo por subt\u00edtulo: " + this.demMenIssqn.getValorIncentivoFiscalSubtitulo() + "<BR>incentivo: " + this.demMenIssqn.getValorIncentivoFiscal();
            this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM056", coluna, (short)2, this.registro, txtSolucao);
        }
    }

    public void verificaValorCreditoCompensar() throws Exception {
        String txtSolucao;
        int coluna = 16;
        if (this.demMenIssqn.getValorCredito() > this.demMenIssqn.getValorIssqnDevido() - (this.demMenIssqn.getValorIssqnRetido() - this.demMenIssqn.getValorIncentivoFiscal())) {
            txtSolucao = "CNPJ: " + this.demMenIssqn.getCnpj() + "<BR>Cr\u00e9dito a compensar: " + this.demMenIssqn.getValorCredito() + "<BR>ISS devido: " + this.demMenIssqn.getValorIssqnDevido() + "<BR>ISS retido: " + this.demMenIssqn.getValorIssqnRetido() + "<BR>Incentivo fiscal por subt\u00edtulo: " + this.demMenIssqn.getValorIncentivoFiscalSubtitulo() + "<BR>incentivo fiscal: " + this.demMenIssqn.getValorIncentivoFiscal();
            this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM015", coluna, (short)2, this.registro, txtSolucao);
        }
        if (this.demMenIssqn.getValorCredito() < 0.0) {
            txtSolucao = "CNPJ: " + this.demMenIssqn.getCnpj() + "<BR>valor do cr\u00e9dito: " + this.demMenIssqn.getValorCredito();
            this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM058", coluna, (short)2, this.registro, txtSolucao);
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
            this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM036", coluna, (short)2, this.registro, txtSolucao);
        }
        if ((l = new ArrayList(this.demMenIssqn.getOrigemCredCompensars())) != null) {
            for (int i = 0; i < l.size(); ++i) {
                boolean resp1 = this.declDao.verificaMaiorDataInicioCompetencia(((OrigemCreditoCompensar)l.get(i)).getDataCompetenciaOrigemCredito());
                if (!resp1) continue;
                String txtSolucao = "CNPJ: " + this.demMenIssqn.getCnpj() + "<BR>al\u00edquota: " + this.demMenIssqn.getValorAliquotaIssqn() + "<BR>compet\u00eancia da declara\u00e7\u00e3o: " + this.declaracao.getDataInicioCompetencia() + "<BR>compet\u00eancia do cr\u00e9dito a compensar: " + ((OrigemCreditoCompensar)l.get(i)).getDataCompetenciaOrigemCredito();
                this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM011", coluna, (short)2, this.registro, txtSolucao);
            }
        }
    }

    public void verificaValorIssqnRecolhido() {
        int coluna = 18;
        if (this.demMenIssqn.getValorIssqnRecolhido() < 0.0) {
            String txtSolucao = "CNPJ: " + this.demMenIssqn.getCnpj() + "<BR>al\u00edquota: " + this.demMenIssqn.getValorAliquotaIssqn() + " <BR>valor do ISSQN recolhido: " + this.demMenIssqn.getValorIssqnRecolhido();
            this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM009", coluna, (short)2, this.registro, txtSolucao);
        }
    }

    public void verificaValorIssqnRecolher() throws Exception {
        String txtSolucao;
        int coluna = 21;
        double val = this.demMenIssqn.getValorIssqnDevido() - this.demMenIssqn.getValorIssqnRetido() - this.demMenIssqn.getValorIncentivoFiscalSubtitulo() - this.demMenIssqn.getValorIncentivoFiscal() - this.demMenIssqn.getValorIssqnRecolhido() - this.demMenIssqn.getValorCredito();
        if (val < 0.0 && this.demMenIssqn.getValorIssqnRecolher() != 0.0) {
            txtSolucao = "CNPJ: " + this.demMenIssqn.getCnpj() + "<BR>al\u00edquota " + this.demMenIssqn.getValorAliquotaIssqn() + "<BR>valor do ISSQN a recolher: " + this.demMenIssqn.getValorIssqnRecolher();
            this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM012", coluna, (short)2, this.registro, txtSolucao);
        }
        if (this.demMenIssqn.getMotivoNaoExigibilidade() == null) {
            if ((val = this.regUtil.formataCasasDecimais(val)) >= 0.0 && val != this.demMenIssqn.getValorIssqnRecolher()) {
                txtSolucao = "";
                txtSolucao = this.demMenIssqn.getMotivoNaoExigibilidade() != null ? "CNPJ: " + this.demMenIssqn.getCnpj() + "<BR>ISSQN devido: " + this.demMenIssqn.getValorIssqnDevido() + "<BR>valor retido: " + this.demMenIssqn.getValorIssqnRetido() + "<BR>incentivo fiscal por subt\u00edtulo: " + this.demMenIssqn.getValorIncentivoFiscalSubtitulo() + "<BR>incentivo fiscal: " + this.demMenIssqn.getValorIncentivoFiscal() + "<BR>cr\u00e9dito a compensar: " + this.demMenIssqn.getValorCredito() + "<BR>valor recolhido: " + this.demMenIssqn.getValorIssqnRecolhido() + "<BR>motivo de n\u00e3o exigibilidade: " + this.demMenIssqn.getMotivoNaoExigibilidade() : "CNPJ: " + this.demMenIssqn.getCnpj() + "<BR>ISSQN devido: " + this.demMenIssqn.getValorIssqnDevido() + "<BR>valor retido: " + this.demMenIssqn.getValorIssqnRetido() + "<BR>incentivo fiscal por subt\u00edtulo: " + this.demMenIssqn.getValorIncentivoFiscalSubtitulo() + "<BR>incentivo fiscal: " + this.demMenIssqn.getValorIncentivoFiscal() + "<BR>cr\u00e9dito a compensar: " + this.demMenIssqn.getValorCredito() + "<BR>valor recolhido: " + this.demMenIssqn.getValorIssqnRecolhido();
                this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM040", coluna, (short)2, this.registro, txtSolucao);
            }
        } else if (this.demMenIssqn.getValorIssqnRecolher() != 0.0) {
            txtSolucao = "CNPJ: " + this.demMenIssqn.getCnpj() + "<BR>motivo de n\u00e3o exigibilidade: " + this.demMenIssqn.getMotivoNaoExigibilidade() + "<BR>ISSQN a recolher: " + this.demMenIssqn.getValorIssqnRecolher();
            this.regUtil.setErro(this.demMenIssqn.getLinhaIssqnMensal().intValue(), "EM043", coluna, (short)2, this.registro, txtSolucao);
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

