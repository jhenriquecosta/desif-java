/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.service.registrobanco.impl;

import br.gov.pbh.desif.dao.IdentServicosRemunVariavelDao;
import br.gov.pbh.desif.dao.PlanoGeralContaComentadoDao;
import br.gov.pbh.desif.dao.ServRemunVarDao;
import br.gov.pbh.desif.model.pojo.IdentServicosRemunVariavel;
import br.gov.pbh.desif.model.pojo.ServRemunVar;
import br.gov.pbh.desif.model.registros.RegUtil;
import br.gov.pbh.desif.service.contexto.Contexto;
import br.gov.pbh.desif.service.registrobanco.ValidaBancoReg0300;
import br.gov.pbh.desif.view.telas.PanGerarDeclaracao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ValidaBancoReg0300Impl
implements ValidaBancoReg0300 {
    private IdentServicosRemunVariavelDao identServRemVarDao;
    private final String registro = "0300";
    private RegUtil regUtil;
    private PanGerarDeclaracao panGerDec;
    private IdentServicosRemunVariavel identSerRemVar;
    private ServRemunVarDao servRemVarDao;
    private PlanoGeralContaComentadoDao pgccDao;
    private HashMap mapeamentoCodIdentTarifa;
    private List descVazias;

    @Override
    public void executar() throws Exception {
        this.regUtil = new RegUtil();
        this.panGerDec = (PanGerarDeclaracao)Contexto.getObject("panGD");
        this.mapeamentoCodIdentTarifa = new HashMap();
        this.descVazias = new ArrayList();
        List respServRemVar = this.identServRemVarDao.findAll();
        Iterator i = respServRemVar.iterator();
        double incremento = this.regUtil.incremetoPorcentagem(30.0, respServRemVar.size());
        double sentinela = 75.0;
        int atualizar = 0;
        while (i.hasNext()) {
            if (sentinela < 101.0) {
                atualizar = (int)sentinela;
                this.panGerDec.atualizarProgressoValidacao(atualizar, 100);
                sentinela += incremento;
            }
            this.identSerRemVar = (IdentServicosRemunVariavel)i.next();
            this.verificaIdentServRemnVariavel();
            this.verificaSubtitulo();
            this.verificaDescTarifa();
        }
        this.verificaDescricaoVazia();
    }

    public void verificaIdentServRemnVariavel() throws Exception {
        String txtSolucao;
        int campo = 3;
        List resp = this.servRemVarDao.findField("cod", this.identSerRemVar.getCodIdentServRemnVariavel());
        if (resp.size() == 0) {
            txtSolucao = "C\u00f3digo do servi\u00e7o : " + this.identSerRemVar.getCodIdentServRemnVariavel();
            this.regUtil.setErro(this.identSerRemVar.getNumLinhIdenServPrecVarl(), "EI017", campo, (short)2, "0300", txtSolucao);
        }
        if (this.identServRemVarDao.verificaUnicidadeCodIdentServRemVarSubtitulo(this.identSerRemVar.getCodIdentServRemnVariavel(), this.identSerRemVar.getCodSubtitulo())) {
            txtSolucao = "C\u00f3digo do servi\u00e7o : " + this.identSerRemVar.getCodIdentServRemnVariavel() + " subt\u00edtulo : " + this.identSerRemVar.getCodSubtitulo();
            this.regUtil.setErro(this.identSerRemVar.getNumLinhIdenServPrecVarl(), "EI019", campo, (short)2, "0300", txtSolucao);
        }
    }

    public void verificaDescTarifa() throws Exception {
        int campo = 4;
        if (!this.identSerRemVar.getDescServRemnVariavel().equals("")) {
            this.mapeamentoCodIdentTarifa.put(this.identSerRemVar.getCodIdentServRemnVariavel(), this.identSerRemVar.getDescServRemnVariavel());
        } else {
            this.descVazias.add(this.identSerRemVar);
        }
    }

    public void verificaSubtitulo() throws Exception {
        List respExisteConta;
        int campo = 5;
        List respContSupe = this.pgccDao.findField("contaSupe", this.identSerRemVar.getCodSubtitulo());
        if (respContSupe.size() != 0) {
            String txtSolucao = "C\u00f3digo do servi\u00e7o : " + this.identSerRemVar.getCodIdentServRemnVariavel() + " C\u00f3digo subtitulo : " + this.identSerRemVar.getCodSubtitulo();
            this.regUtil.setErro(this.identSerRemVar.getNumLinhIdenServPrecVarl(), "EI025", campo, (short)2, "0300", txtSolucao);
        }
        if ((respExisteConta = this.pgccDao.findField("conta", this.identSerRemVar.getCodSubtitulo())).size() == 0) {
            String txtSolucao = "C\u00f3digo do servi\u00e7o : " + this.identSerRemVar.getCodIdentServRemnVariavel() + " C\u00f3digo subtitulo : " + this.identSerRemVar.getCodSubtitulo();
            this.regUtil.setErro(this.identSerRemVar.getNumLinhIdenServPrecVarl(), "EI026", campo, (short)2, "0300", txtSolucao);
        }
    }

    public void verificaDescricaoVazia() throws Exception {
        for (int i = 0; i < this.descVazias.size(); ++i) {
            List resp;
            ServRemunVar srv;
            if (this.mapeamentoCodIdentTarifa.containsKey(((IdentServicosRemunVariavel)this.descVazias.get(i)).getCodIdentServRemnVariavel()) || (resp = this.servRemVarDao.findField("cod", ((IdentServicosRemunVariavel)this.descVazias.get(i)).getCodIdentServRemnVariavel())).isEmpty() || (srv = (ServRemunVar)resp.get(0)).getOpcObrig() != 1) continue;
            String txtSolucao = "C\u00f3digo do servi\u00e7o : " + ((IdentServicosRemunVariavel)this.descVazias.get(i)).getCodIdentServRemnVariavel();
            this.regUtil.setErro(((IdentServicosRemunVariavel)this.descVazias.get(i)).getNumLinhIdenServPrecVarl(), "EI022", 4, (short)2, "0300", txtSolucao);
        }
    }

    public IdentServicosRemunVariavelDao getIdentServRemVarDao() {
        return this.identServRemVarDao;
    }

    public void setIdentServRemVarDao(IdentServicosRemunVariavelDao identServRemVarDao) {
        this.identServRemVarDao = identServRemVarDao;
    }

    public ServRemunVarDao getServRemVarDao() {
        return this.servRemVarDao;
    }

    public void setServRemVarDao(ServRemunVarDao servRemVarDao) {
        this.servRemVarDao = servRemVarDao;
    }

    public PlanoGeralContaComentadoDao getPgccDao() {
        return this.pgccDao;
    }

    public void setPgccDao(PlanoGeralContaComentadoDao pgccDao) {
        this.pgccDao = pgccDao;
    }
}

