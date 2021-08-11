/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.service.registrobanco.impl;

import br.gov.pbh.desif.dao.PgccsPaiFilhoDao;
import br.gov.pbh.desif.dao.PlanoGeralContaComentadoDao;
import br.gov.pbh.desif.model.pojo.PgccsPaiFilho;
import br.gov.pbh.desif.model.pojo.PgccsPaiFilhoId;
import br.gov.pbh.desif.model.pojo.PlanoGeralContaComentado;
import br.gov.pbh.desif.model.registros.RegUtil;
import br.gov.pbh.desif.service.registrobanco.ValidaBancoInformacoesComunsMunicipios;
import br.gov.pbh.desif.service.registrobanco.ValidaBancoReg0000;
import br.gov.pbh.desif.service.registrobanco.ValidaBancoReg0100;
import br.gov.pbh.desif.service.registrobanco.ValidaBancoReg0200;
import br.gov.pbh.desif.service.registrobanco.ValidaBancoReg0300;
import java.util.Iterator;
import java.util.List;

public class ValidaBancoInformacoesComunsMunicipiosImpl
implements ValidaBancoInformacoesComunsMunicipios {
    private ValidaBancoReg0000 VBReg0000;
    private ValidaBancoReg0100 VBReg0100;
    private ValidaBancoReg0200 VBReg0200;
    private ValidaBancoReg0300 VBReg0300;
    private PgccsPaiFilhoDao pgccsPFIdDao;
    private PlanoGeralContaComentadoDao pgccDao;
    RegUtil regUtil = new RegUtil();
    private int nivel = 0;
    private int iteracao = 0;

    @Override
    public void executar() throws Exception {
        List<PlanoGeralContaComentado> listaPGCC = this.pgccDao.findAll();
        if (listaPGCC.size() == 0) {
            this.regUtil.setErro(0L, "EI023", 2, (short)2, "0100");
        } else {
            List contaNivel1Cosif;
            List contaNivel1Pgcc;
            for (PlanoGeralContaComentado pgcc : listaPGCC) 
            {
                List respNumContas = this.pgccDao.findField("conta", pgcc.getConta());
                if (respNumContas.size() <= 1) continue;
                String txtSolucao = "Conta: " + pgcc.getConta();
                this.regUtil.setErro(pgcc.getNumLinhaPgcc(), "EI001", 3, (short)2, "0100", txtSolucao);
            }
            List raiz = this.buscaRaizArvore();
            if (!((PlanoGeralContaComentado)raiz.get(0)).getContaCosif().equals("70000009")) {
                System.out.println("Verrifica raizes");
                this.regUtil.setErro(((PlanoGeralContaComentado)raiz.get(0)).getNumLinhaPgcc(), "EI027", 7, (short)2, "0100");
            }
            if ((contaNivel1Cosif = this.pgccDao.findField("contaCosif", ((PlanoGeralContaComentado)raiz.get(0)).getContaCosif())).size() != 1) {
                this.regUtil.setErro(((PlanoGeralContaComentado)raiz.get(0)).getNumLinhaPgcc(), "EI031", 7, (short)2, "0100");
            }
            if ((contaNivel1Pgcc = this.pgccDao.findField("conta", ((PlanoGeralContaComentado)raiz.get(0)).getConta())).size() != 1) {
                this.regUtil.setErro(((PlanoGeralContaComentado)raiz.get(0)).getNumLinhaPgcc(), "EI032", 3, (short)2, "0100");
            }
            if (!RegUtil.exErro) {
                this.montarArvore(raiz);
                this.nivel = 0;
                this.VBReg0000.executar();
                this.VBReg0100.executar();
                this.VBReg0200.executar();
                this.VBReg0300.executar();
            }
        }
    }

    public void montarArvore(List raiz) {
        this.buscaGalhos(raiz);
    }

    public List buscaRaizArvore() {
        List raiz = this.pgccDao.buscarRaizArvore();
        if (raiz.size() == 0) {
            this.regUtil.setErro(0L, "EI007", 3, (short)1, "0100", "N\u00e3o existe nenhum campo raiz do PGCC");
            raiz = null;
        } else {
            if (raiz.size() == 1) {
                return raiz;
            }
            if (raiz.size() > 1) {
                this.regUtil.setErro(0L, "EI021", 3, (short)1, "0100", "Existe mais de um campo raiz do PGCC");
                raiz = null;
            }
        }
        return raiz;
    }

    public void buscaGalhos(List elemento) {
        int nivelNoLoop = 0;
        ++this.nivel;
        this.iteracao = 0;
        Iterator i = elemento.iterator();
        PlanoGeralContaComentado pgcc = null;
        List galhosResultantes = null;
        if (elemento.size() > 1) {
            nivelNoLoop = this.nivel;
        }
        PlanoGeralContaComentado elem = (PlanoGeralContaComentado)elemento.get(0);
        
        System.out.println("Elemento: "+ elem.getConta() );
        while (i.hasNext()) {
            Object o = i.next();
            galhosResultantes = this.pgccDao.buscarGalhos(((PlanoGeralContaComentado)o).getConta());
            pgcc = (PlanoGeralContaComentado)o;
            if (this.iteracao != 0) {
                this.nivel = nivelNoLoop;
            }
            ++this.iteracao;
            pgcc.setNivel(this.nivel);
            this.pgccDao.update(pgcc);
            if (galhosResultantes.size() <= 0) continue;
            this.inserirFilhos(galhosResultantes, pgcc);
            this.buscaGalhos(galhosResultantes);
        }
    }

    public void inserirFilhos(List galhos, PlanoGeralContaComentado pgcc) {
        PlanoGeralContaComentado pgccAux2 = null;
        for (Object pgccAux : galhos)
        {
            pgccAux2 = (PlanoGeralContaComentado)pgccAux;
            PgccsPaiFilhoId ppfid = new PgccsPaiFilhoId(pgcc.getId(), pgccAux2.getId());
            PgccsPaiFilho paiFilho = new PgccsPaiFilho(ppfid, pgcc, pgccAux2);
            this.pgccsPFIdDao.save(paiFilho);
        }
    }

    public void verificaFilhos() {
        List l = this.pgccDao.findAll();
        for (int i = 0; i < l.size(); ++i) {
            PlanoGeralContaComentado pgcc = (PlanoGeralContaComentado)l.get(i);
        }
    }

    public void imprimirArvore(List arvore) {
        if (arvore.size() > 0) {
            for (Object o : arvore) {
                if (o instanceof List) {
                    this.imprimirArvore((List)o);
                    continue;
                }
                if (o == null) {
                    return;
                }
                System.out.println(((PlanoGeralContaComentado)o).getConta());
            }
        } else {
            System.out.println("A \u00e1rvore est\u00e1 vazia n\u00e3o h\u00e1 elementos para serem impressos.");
        }
    }

    public ValidaBancoReg0000 getVBReg0000() {
        return this.VBReg0000;
    }

    public void setVBReg0000(ValidaBancoReg0000 VBReg0000) {
        this.VBReg0000 = VBReg0000;
    }

    public ValidaBancoReg0100 getVBReg0100() {
        return this.VBReg0100;
    }

    public void setVBReg0100(ValidaBancoReg0100 VBReg0100) {
        this.VBReg0100 = VBReg0100;
    }

    public ValidaBancoReg0200 getVBReg0200() {
        return this.VBReg0200;
    }

    public void setVBReg0200(ValidaBancoReg0200 VBReg0200) {
        this.VBReg0200 = VBReg0200;
    }

    public ValidaBancoReg0300 getVBReg0300() {
        return this.VBReg0300;
    }

    public void setVBReg0300(ValidaBancoReg0300 VBReg0300) {
        this.VBReg0300 = VBReg0300;
    }

    public PlanoGeralContaComentadoDao getPgccDao() {
        return this.pgccDao;
    }

    public void setPgccDao(PlanoGeralContaComentadoDao pgccDao) {
        this.pgccDao = pgccDao;
    }

    public PgccsPaiFilhoDao getPgccsPFIdDao() {
        return this.pgccsPFIdDao;
    }

    public void setPgccsPFIdDao(PgccsPaiFilhoDao pgccsPFIdDao) {
        this.pgccsPFIdDao = pgccsPFIdDao;
    }

    public int getNivel() {
        return this.nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
}

