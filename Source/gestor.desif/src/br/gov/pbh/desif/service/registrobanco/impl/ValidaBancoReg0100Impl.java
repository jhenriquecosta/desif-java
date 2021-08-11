/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.service.registrobanco.impl;

import br.gov.pbh.desif.dao.CodTribuMunicipalDao;
import br.gov.pbh.desif.dao.CodTributacaoDesifDao;
import br.gov.pbh.desif.dao.CosifDao;
import br.gov.pbh.desif.dao.CosifPaiFilhoDao;
import br.gov.pbh.desif.dao.IdentDeclaracaoDao;
import br.gov.pbh.desif.dao.IdentServicosRemunVariavelDao;
import br.gov.pbh.desif.dao.PgccsPaiFilhoDao;
import br.gov.pbh.desif.dao.PlanoGeralContaComentadoDao;
import br.gov.pbh.desif.dao.TarifaServicoDao;
import br.gov.pbh.desif.model.pojo.Cosif;
import br.gov.pbh.desif.model.pojo.IdentificacaoDeclaracao;
import br.gov.pbh.desif.model.pojo.PlanoGeralContaComentado;
import br.gov.pbh.desif.model.registros.RegUtil;
import br.gov.pbh.desif.service.contexto.Contexto;
import br.gov.pbh.desif.service.registrobanco.ValidaBancoReg0100;
import br.gov.pbh.desif.view.telas.PanGerarDeclaracao;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ValidaBancoReg0100Impl
implements ValidaBancoReg0100 {
    private IdentDeclaracaoDao declaracaoDao;
    private PlanoGeralContaComentadoDao pgccDao;
    private TarifaServicoDao tarServDao;
    private IdentServicosRemunVariavelDao identServRemVarDao;
    private CosifDao cosifDao;
    private CodTributacaoDesifDao codTribDao;
    private CodTribuMunicipalDao codTribMunicDao;
    private PlanoGeralContaComentado pgcc;
    private PanGerarDeclaracao panGerDec;
    private PgccsPaiFilhoDao pgccsPFIdDao;
    private CosifPaiFilhoDao CosifPFIdDao;
    private RegUtil regUtil;
    private final String registro = "0100";

    @Override
    public void executar() throws Exception {
        this.regUtil = new RegUtil();
        this.panGerDec = (PanGerarDeclaracao)Contexto.getObject("panGD");
        List listaPGCC = this.pgccDao.findAll();
        Iterator i = listaPGCC.iterator();
        double incremento = this.regUtil.incremetoPorcentagem(55.0, listaPGCC.size());
        double sentinela = 0.0;
        int atualizar = 0;
        while (i.hasNext()) {
            if (sentinela < 51.0) {
                atualizar = (int)sentinela;
                this.panGerDec.atualizarProgressoValidacao(atualizar, 100);
                sentinela += incremento;
            }
            this.pgcc = (PlanoGeralContaComentado)i.next();
            this.verificaDesConta();
            this.verificaContaSuperior();
            this.verificaContaCosif();
            this.verificarCodTributacaoDesif();
            if (RegUtil.exErro) continue;
            this.verificaExistemNiveisZerados();
            this.verificaContasAnaliticasPGCCCosif();
            this.verificaNiveisContasCosifPaieFilha();
            this.verificacaoDetalhadaNiveisPgccCosif();
            this.verificaNiveisContasPgccPaieFilha();
        }
    }

    public void verificaExistemNiveisZerados() throws Exception {
        if (this.pgcc.getNivel() == 0) {
            System.out.println("VerificaExisteNiveisZerados");
            this.regUtil.setErro(this.pgcc.getNumLinhaPgcc(), "EI030", 7, (short)2, "0100");
        }
    }

    public void verificaConta() throws Exception {
        int campo = 3;
        List respNumContas = this.pgccDao.findField("conta", this.pgcc.getConta());
        if (respNumContas.size() > 1) {
            String txtSolucao = "Conta: " + this.pgcc.getConta();
            this.regUtil.setErro(this.pgcc.getNumLinhaPgcc(), "EI001", campo, (short)2, "0100", txtSolucao);
        }
    }

    public void verificaDesConta() throws Exception {
        int campo = 5;
        if (!this.pgccsPFIdDao.identificarPossuiFilhos(this.pgcc) && this.pgcc.getDescConta().equals("")) {
            String txtSolucao = "Conta: " + this.pgcc.getConta();
            this.regUtil.setErro(this.pgcc.getNumLinhaPgcc(), "EI004", campo, (short)2, "0100", txtSolucao);
        }
    }

    public void verificaContaSuperior() throws Exception {
        int campo = 6;
        List respExisteContaSupComoConta = null;
        if (!this.pgcc.getContaSupe().equals("") && (respExisteContaSupComoConta = this.pgccDao.findField("conta", this.pgcc.getContaSupe())) != null && respExisteContaSupComoConta.size() == 0) {
            String txtSolucao = "Conta: " + this.pgcc.getConta() + " Conta Superior:  " + this.pgcc.getContaSupe();
            this.regUtil.setErro(this.pgcc.getNumLinhaPgcc(), "EI005", campo, (short)2, "0100", txtSolucao);
        }
    }

    public void verificaContaCosif() throws Exception {
        int campo = 7;
        List respContaCosif = this.cosifDao.findField("numeroContaCosif", this.pgcc.getContaCosif());
        if (respContaCosif.size() == 0) {
            String txtSolucao = "Conta: " + this.pgcc.getConta() + " conta Cosif: " + this.pgcc.getContaCosif();
            this.regUtil.setErro(this.pgcc.getNumLinhaPgcc(), "EI008", campo, (short)2, "0100", txtSolucao);
        } else {
            String txtAlerta;
            IdentificacaoDeclaracao declaracao = (IdentificacaoDeclaracao)this.declaracaoDao.load(new Integer(Integer.parseInt("1")));
            Cosif cosif = (Cosif)respContaCosif.get(0);
            if (cosif.getDataCriacao().after(declaracao.getDataInicioCompetencia())) {
                txtAlerta = "Conta PGCC: " + this.pgcc.getConta() + ", Conta COSIF: " + this.pgcc.getContaCosif() + ", m\u00eas inicio da compet\u00eancia informado: " + declaracao.getDataInicioCompetencia() + ", data cria\u00e7\u00e3o da conta COSIF: " + cosif.getDataCriacao();
                this.regUtil.setAlerta(this.pgcc.getNumLinhaPgcc(), "A008", campo, (short)2, "0100", txtAlerta);
            }
            if (cosif.getDataExtinsao() != null && cosif.getDataExtinsao().before(declaracao.getDataFimCompetencia())) {
                txtAlerta = "Conta PGCC: " + this.pgcc.getConta() + ", Conta COSIF: " + this.pgcc.getContaCosif() + ", m\u00eas fim da compet\u00eancia informado: " + declaracao.getDataFimCompetencia() + ", data extin\u00e7\u00e3o da conta COSIF: " + cosif.getDataExtinsao();
                this.regUtil.setAlerta(this.pgcc.getNumLinhaPgcc(), "A009", campo, (short)2, "0100", txtAlerta);
            }
        }
        String grupoContaCosif = this.pgcc.getContaCosif().substring(0, 1);
        if (!grupoContaCosif.equals("7")) {
            String txtSolucao = "Conta: " + this.pgcc.getConta() + " conta Cosif: " + this.pgcc.getContaCosif();
            this.regUtil.setErro(this.pgcc.getNumLinhaPgcc(), "EI009", campo, (short)2, "0100", txtSolucao);
        }
    }

    public void verificaContasAnaliticasPGCCCosif() throws Exception {
        Cosif cosif;
        if (!this.pgccsPFIdDao.identificarPossuiFilhos(this.pgcc) && this.cosifDao.buscarGalhos((cosif = this.cosifDao.BuscaCosif(this.pgcc.getContaCosif())).getNumeroContaCosif()).size() > 0) {
            this.regUtil.setErro(this.pgcc.getNumLinhaPgcc(), "EI033", 3, (short)2, "0100");
        }
    }

    public void verificaNiveisContasCosifPaieFilha() throws Exception {
        Cosif cosifPaiPgcc;
        PlanoGeralContaComentado pgccPai;
        Cosif cosif = this.cosifDao.BuscaCosif(this.pgcc.getContaCosif());
        if (cosif.getNumNivel() != 1 && (cosifPaiPgcc = this.cosifDao.BuscaCosif((pgccPai = this.pgccDao.buscaPgcc(this.pgcc.getContaSupe())).getContaCosif())).getNumNivel() > cosif.getNumNivel()) {
            System.out.println("Problema no Cosif");
            this.regUtil.setErro(this.pgcc.getNumLinhaPgcc(), "EI030", 6, (short)2, "0100");
        }
    }

    public void verificaNiveisContasPgccPaieFilha() throws Exception {
        PlanoGeralContaComentado pgccPai;
        if (this.pgcc.getNivel() != 1 && (pgccPai = this.pgccDao.buscaPgcc(this.pgcc.getContaSupe())).getNivel() > this.pgcc.getNivel()) {
            System.out.println("Problema no Pgcc");
            this.regUtil.setErro(this.pgcc.getNumLinhaPgcc(), "EI030", 6, (short)2, "0100");
        }
    }

    public void verificacaoDetalhadaNiveisPgccCosif() throws Exception {
        Cosif cosifFilho = this.cosifDao.BuscaCosif(this.pgcc.getContaCosif());
        if (cosifFilho.getNumNivel() != 1) {
            PlanoGeralContaComentado pgccPai = this.pgccDao.buscaPgcc(this.pgcc.getContaSupe());
            Cosif cosifPgccPai = this.cosifDao.BuscaCosif(pgccPai.getContaCosif());
            Cosif cosifPai = this.cosifDao.BuscaCosif(cosifFilho.getNumeroContaSuperior());
            if (cosifPgccPai.getNumNivel().intValue() == cosifFilho.getNumNivel().intValue()) {
                if (!this.pgcc.getContaCosif().equals(pgccPai.getContaCosif())) {
                    System.out.println("verificacaoDetalhadaNiveisPgccCosif()");
                    String txtSolucao = "Conta: " + this.pgcc.getConta() + ", Conta Cosif: " + this.pgcc.getContaCosif() + "Conta Superior: " + pgccPai.getConta() + "Conta Cosif Superior : " + pgccPai.getContaCosif();
                    this.regUtil.setErro(this.pgcc.getNumLinhaPgcc(), "EI027", 7, (short)2, "0100", txtSolucao);
                }
            } else {
                String cosifSuperior = cosifPai.getNumeroContaCosif();
                int numeroNivelCosif = cosifFilho.getNumNivel();
                int numeroNivelCosifPai = cosifPgccPai.getNumNivel();
                int i = 0;
                while (numeroNivelCosif - i > 0 && !(numeroNivelCosifPai == numeroNivelCosif - ++i & cosifPgccPai.getNumeroContaCosif().equals(cosifSuperior))) {
                    if (cosifSuperior.equals("70000009")) {
                        i = numeroNivelCosif;
                        continue;
                    }
                    Cosif cosifAvo = this.cosifDao.BuscaCosif(cosifSuperior);
                    cosifSuperior = cosifAvo.getNumeroContaSuperior();
                }
                if (numeroNivelCosif - i == 0) {
                    System.out.println("Algoritimo");
                    String txtSolucao = "Conta: " + this.pgcc.getConta() + ", Conta Cosif: " + this.pgcc.getContaCosif() + "Conta Superior: " + pgccPai.getConta() + "Conta Cosif Superior : " + pgccPai.getContaCosif();
                    this.regUtil.setErro(this.pgcc.getNumLinhaPgcc(), "EI027", 7, (short)2, "0100", txtSolucao);
                }
            }
        }
    }

    public void verificarCodTributacaoDesif() throws Exception {
        List respReg200;
        String txtSolucao;
        List respTrib;
        int campo = 8;
        if (this.pgccsPFIdDao.identificarPossuiFilhos(this.pgcc) && !this.pgcc.getCodTribDesif().trim().equals("")) {
            String txtSolucao2 = "Conta: " + this.pgcc.getConta() + " C\u00f3digos de Tributa\u00e7\u00e3o da DES-IF: " + this.pgcc.getCodTribDesif();
            this.regUtil.setErro(this.pgcc.getNumLinhaPgcc(), "EI010", campo, (short)2, "0100", txtSolucao2);
        }
        if (!this.pgcc.getCodTribDesif().equals("") && (respTrib = this.codTribDao.buscaCodTributacaoDesif("id", this.pgcc.getCodTribDesif())).size() == 0) {
            txtSolucao = "Conta: " + this.pgcc.getConta() + " C\u00f3digos de Tributa\u00e7\u00e3o da DES-IF: " + this.pgcc.getCodTribDesif();
            this.regUtil.setErro(this.pgcc.getNumLinhaPgcc(), "EG011", campo, (short)2, "0100", txtSolucao);
        }
        if ((respReg200 = this.tarServDao.findField("codSubtitulo", this.pgcc.getConta())).size() > 0 && this.pgcc.getCodTribDesif().equals("")) {
            txtSolucao = "Conta: " + this.pgcc.getConta();
            this.regUtil.setErro(this.pgcc.getNumLinhaPgcc(), "EI014", campo, (short)2, "0100", txtSolucao);
        }
    }

    public CosifDao getCosifDao() {
        return this.cosifDao;
    }

    public void setCosifDao(CosifDao cosifDao) {
        this.cosifDao = cosifDao;
    }

    public PlanoGeralContaComentadoDao getPgccDao() {
        return this.pgccDao;
    }

    public void setPgccDao(PlanoGeralContaComentadoDao pgccDao) {
        this.pgccDao = pgccDao;
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

    public IdentServicosRemunVariavelDao getIdentServRemVarDao() {
        return this.identServRemVarDao;
    }

    public void setIdentServRemVarDao(IdentServicosRemunVariavelDao identServRemVarDao) {
        this.identServRemVarDao = identServRemVarDao;
    }

    public TarifaServicoDao getTarServDao() {
        return this.tarServDao;
    }

    public void setTarServDao(TarifaServicoDao tarServDao) {
        this.tarServDao = tarServDao;
    }

    public PgccsPaiFilhoDao getPgccsPFIdDao() {
        return this.pgccsPFIdDao;
    }

    public void setPgccsPFIdDao(PgccsPaiFilhoDao pgccsPFIdDao) {
        this.pgccsPFIdDao = pgccsPFIdDao;
    }

    public IdentDeclaracaoDao getDeclaracaoDao() {
        return this.declaracaoDao;
    }

    public void setDeclaracaoDao(IdentDeclaracaoDao declaracaoDao) {
        this.declaracaoDao = declaracaoDao;
    }

    public CosifPaiFilhoDao getCosifPFIdDao() {
        return this.CosifPFIdDao;
    }

    public void setCosifPFIdDao(CosifPaiFilhoDao CosifPFIdDao) {
        this.CosifPFIdDao = CosifPFIdDao;
    }
}

