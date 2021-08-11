/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.service.registrobanco.impl;

import br.gov.pbh.desif.dao.IdentDeclaracaoDao;
import br.gov.pbh.desif.dao.PgccsPaiFilhoDao;
import br.gov.pbh.desif.dao.PlanoGeralContaComentadoDao;
import br.gov.pbh.desif.dao.TituloDao;
import br.gov.pbh.desif.dao.impl.TarifaServicoDaoImpl;
import br.gov.pbh.desif.model.pojo.IdentificacaoDeclaracao;
import br.gov.pbh.desif.model.pojo.PlanoGeralContaComentado;
import br.gov.pbh.desif.model.pojo.TarifaServico;
import br.gov.pbh.desif.model.pojo.Titulo;
import br.gov.pbh.desif.model.registros.RegUtil;
import br.gov.pbh.desif.service.contexto.Contexto;
import br.gov.pbh.desif.service.registrobanco.ValidaBancoReg0200;
import br.gov.pbh.desif.view.telas.PanGerarDeclaracao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ValidaBancoReg0200Impl
implements ValidaBancoReg0200 {
    private TituloDao tituloDao;
    private PlanoGeralContaComentadoDao pgccDao;
    private IdentDeclaracaoDao declaracaoDao;
    private TarifaServicoDaoImpl tarServDao;
    private PgccsPaiFilhoDao pgccsPFIdDao;
    private TarifaServico tarServ;
    private PanGerarDeclaracao panGerDec;
    private RegUtil regUtil;
    private HashMap mapeamentoCodIdentTarifa;
    private List descVazias;
    private final String registro = "0200";

    @Override
    public void executar() throws Exception {
        this.descVazias = new ArrayList();
        this.mapeamentoCodIdentTarifa = new HashMap();
        this.regUtil = new RegUtil();
        this.panGerDec = (PanGerarDeclaracao)Contexto.getObject("panGD");
        IdentificacaoDeclaracao declaracao = (IdentificacaoDeclaracao)this.declaracaoDao.load(new Integer(Integer.parseInt("1")));
        List respTitulo = this.tituloDao.identificarTipoInstituicao("id", declaracao.getTitulo());
        List respTarServ = this.tarServDao.findAll();
        if (respTitulo.size() > 0 && ((Titulo)respTitulo.get(0)).getObrigatoria() && respTarServ.size() == 0) {
            String txtSolucao = "Tipo Institui\u00e7\u00e3o : " + declaracao.getTitulo();
            this.regUtil.setErro(0L, "EI024", 2, (short)2, "0200", txtSolucao);
        }
        Iterator i = respTarServ.iterator();
        double incremento = this.regUtil.incremetoPorcentagem(30.0, respTarServ.size());
        double sentinela = 50.0;
        int atualizar = 0;
        while (i.hasNext()) {
            if (sentinela < 76.0) {
                atualizar = (int)sentinela;
                this.panGerDec.atualizarProgressoValidacao(atualizar, 100);
                sentinela += incremento;
            }
            this.tarServ = (TarifaServico)i.next();
            this.verificaCodIdentTarifa();
            this.verificaDescTarifa();
            this.verificaSubtitulo();
        }
        this.verificaDescricaoVazia();
    }

    public void verificaCodIdentTarifa() throws Exception {
        int campo = 3;
        if (this.tarServDao.verificaUnicidadeCodIdentTarifaeCosSubtitulo(this.tarServ.getCodIdentTarifa(), this.tarServ.getCodSubtitulo())) {
            String txtSolucao = "C\u00f3digo da tarifa: " + this.tarServ.getCodIdentTarifa() + ", subt\u00edtulo: " + this.tarServ.getCodSubtitulo();
            this.regUtil.setErro(this.tarServ.getNumLinhTariServ(), "EI015", campo, (short)2, "0200", txtSolucao);
        }
    }

    public void verificaDescTarifa() throws Exception {
        int campo = 4;
        if (this.mapeamentoCodIdentTarifa.isEmpty()) {
            if (!this.tarServ.getDescTarifa().equals("")) {
                this.mapeamentoCodIdentTarifa.put(this.tarServ.getCodIdentTarifa(), this.tarServ.getDescTarifa());
            } else {
                this.descVazias.add(this.tarServ);
            }
        } else if (!this.tarServ.getDescTarifa().equals("")) {
            if (this.mapeamentoCodIdentTarifa.containsKey(this.tarServ.getCodIdentTarifa())) {
                if (!this.mapeamentoCodIdentTarifa.containsValue(this.tarServ.getDescTarifa())) {
                    String txtSolucao = "C\u00f3digo da tarifa: " + this.tarServ.getCodIdentTarifa();
                    this.regUtil.setErro(this.tarServ.getNumLinhTariServ(), "EI020", campo, (short)2, "0200", txtSolucao);
                }
            } else {
                this.mapeamentoCodIdentTarifa.put(this.tarServ.getCodIdentTarifa(), this.tarServ.getDescTarifa());
            }
        } else {
            this.descVazias.add(this.tarServ);
        }
    }

    public void verificaSubtitulo() throws Exception {
        int campo = 5;
        List respPgcc = this.pgccDao.findField("conta", this.tarServ.getCodSubtitulo());
        if (respPgcc.size() == 0) {
            String txtSolucao = "C\u00f3digo da tarifa: " + this.tarServ.getCodIdentTarifa() + ", subt\u00edtulo: " + this.tarServ.getCodSubtitulo();
            this.regUtil.setErro(this.tarServ.getNumLinhTariServ(), "EI013", campo, (short)2, "0200", txtSolucao);
        } else {
            for (int i = 0; i < respPgcc.size(); ++i) {
                PlanoGeralContaComentado pgcc = (PlanoGeralContaComentado)respPgcc.get(i);
                if (!this.pgccsPFIdDao.identificarPossuiFilhos(pgcc)) continue;
                String txtSolucao = "C\u00f3digo da tarifa: " + this.tarServ.getCodIdentTarifa() + ", subt\u00edtulo: " + this.tarServ.getCodSubtitulo();
                this.regUtil.setErro(this.tarServ.getNumLinhTariServ(), "EI002", campo, (short)2, "0200", txtSolucao);
            }
        }
    }

    public void verificaDescricaoVazia() throws Exception {
        for (int i = 0; i < this.descVazias.size(); ++i) {
            if (this.mapeamentoCodIdentTarifa.containsKey(((TarifaServico)this.descVazias.get(i)).getCodIdentTarifa())) continue;
            String txtSolucao = "C\u00f3digo da tarifa: " + this.tarServ.getCodIdentTarifa();
            this.regUtil.setErro(((TarifaServico)this.descVazias.get(i)).getNumLinhTariServ(), "EI012", 4, (short)2, "0200", txtSolucao);
        }
    }

    public IdentDeclaracaoDao getDeclaracaoDao() {
        return this.declaracaoDao;
    }

    public void setDeclaracaoDao(IdentDeclaracaoDao declaracaoDao) {
        this.declaracaoDao = declaracaoDao;
    }

    public PlanoGeralContaComentadoDao getPgccDao() {
        return this.pgccDao;
    }

    public void setPgccDao(PlanoGeralContaComentadoDao pgccDao) {
        this.pgccDao = pgccDao;
    }

    public TituloDao getTituloDao() {
        return this.tituloDao;
    }

    public void setTituloDao(TituloDao tituloDao) {
        this.tituloDao = tituloDao;
    }

    public TarifaServicoDaoImpl getTarServDao() {
        return this.tarServDao;
    }

    public void setTarServDao(TarifaServicoDaoImpl tarServDao) {
        this.tarServDao = tarServDao;
    }

    public PgccsPaiFilhoDao getPgccsPFIdDao() {
        return this.pgccsPFIdDao;
    }

    public void setPgccsPFIdDao(PgccsPaiFilhoDao pgccsPFIdDao) {
        this.pgccsPFIdDao = pgccsPFIdDao;
    }
}

