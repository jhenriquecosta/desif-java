/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.registros;

import br.gov.pbh.desif.dao.CosifDao;
import br.gov.pbh.desif.dao.CosifPaiFilhoDao;
import br.gov.pbh.desif.model.pojo.Cosif;
import br.gov.pbh.desif.model.pojo.CosifPaiFilho;
import br.gov.pbh.desif.model.pojo.CosifPaiFilhoConta;
import br.gov.pbh.desif.service.contexto.Contexto;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

public class PopularFilhosCosif 
{
    private CosifDao cosifDao = (CosifDao)Contexto.getObject("cosifDao");
    private CosifPaiFilhoDao cosifPaiFilhoDao = (CosifPaiFilhoDao)Contexto.getObject("cosifPFIdDao");

    public void popularPaiFilho() 
    {
        List<Cosif> raizCosif = this.cosifDao.buscarRaizCosif();
        for (Cosif cos : raizCosif) 
        {
            System.out.println("Conta => " + cos.getNomeContaCosif());
            System.out.println("Nivel => " + cos.getNumNivel());
            System.out.println("-----------------------------------");
        }
        this.montarArvore(raizCosif);
    }

    public void montarArvore(List raiz) {
        this.buscaGalhos(raiz);
    }

    public void buscaGalhos(List elemento) {
        Iterator i = elemento.iterator();
        Cosif cosif = null;
        List galhosResultantes = null;
        while (i.hasNext()) {
            Object o = i.next();
            galhosResultantes = this.cosifDao.buscarGalhos(((Cosif)o).getNumeroContaCosif());
            cosif = (Cosif)o;
            if (galhosResultantes.size() <= 0) continue;
            this.inserirFilhos(galhosResultantes, cosif);
            this.buscaGalhos(galhosResultantes);
        }
    }

    public void inserirFilhos(List galhos, Cosif cosif) 
    {
        Cosif cosifAux2 = null;
        for (Object cosifAux : galhos) 
        {
            cosifAux2 = (Cosif)cosifAux;
            CosifPaiFilhoConta ppfid = new CosifPaiFilhoConta(cosif.getNumeroContaCosif(), cosifAux2.getNumeroContaCosif());
            CosifPaiFilho paiFilho = new CosifPaiFilho(ppfid, cosif, cosifAux2);
            this.cosifPaiFilhoDao.save(paiFilho);
        }
    }

    public static void main(String[] args) {
        PopularFilhosCosif popFilhosCosif = new PopularFilhosCosif();
        popFilhosCosif.popularPaiFilho();
    }
}

