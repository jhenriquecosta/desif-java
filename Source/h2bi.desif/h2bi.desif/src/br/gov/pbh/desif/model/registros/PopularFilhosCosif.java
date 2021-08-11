

package br.gov.pbh.desif.model.registros;

import br.gov.pbh.desif.dao.CosifDao;
import br.gov.pbh.desif.dao.CosifPaiFilhoDao;
import br.gov.pbh.desif.model.pojo.*;
import br.gov.pbh.desif.service.contexto.Contexto;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;

public class PopularFilhosCosif
{

    private CosifDao cosifDao;
    private CosifPaiFilhoDao cosifPaiFilhoDao;

    public PopularFilhosCosif()
    {
        cosifDao = (CosifDao)Contexto.getObject("cosifDao");
        cosifPaiFilhoDao = (CosifPaiFilhoDao)Contexto.getObject("cosifPFIdDao");
    }

    public void popularPaiFilho()
    {
        List raizCosif = cosifDao.buscarRaizCosif();
        for(Iterator i = raizCosif.iterator(); i.hasNext(); System.out.println("-----------------------------------"))
        {
            Cosif cos = (Cosif)i.next();
            System.out.println((new StringBuilder()).append("Conta => ").append(cos.getNomeContaCosif()).toString());
            System.out.println((new StringBuilder()).append("Nivel => ").append(cos.getNumNivel()).toString());
        }

        montarArvore(raizCosif);
    }

    public void montarArvore(List raiz)
    {
        buscaGalhos(raiz);
    }

    public void buscaGalhos(List elemento)
    {
        Iterator i = elemento.iterator();
        Cosif cosif = null;
        List galhosResultantes = null;
        do
        {
            if(!i.hasNext())
                break;
            Object o = i.next();
            galhosResultantes = cosifDao.buscarGalhos(((Cosif)o).getNumeroContaCosif());
            cosif = (Cosif)o;
            if(galhosResultantes.size() > 0)
            {
                inserirFilhos(galhosResultantes, cosif);
                buscaGalhos(galhosResultantes);
            }
        } while(true);
    }

    public void inserirFilhos(List galhos, Cosif cosif)
    {
        Cosif cosifAux = null;
        CosifPaiFilho paiFilho;
        for(Iterator i = galhos.iterator(); i.hasNext(); cosifPaiFilhoDao.save(paiFilho))
        {
            cosifAux = (Cosif)i.next();
            CosifPaiFilhoConta ppfid = new CosifPaiFilhoConta(cosif.getNumeroContaCosif(), cosifAux.getNumeroContaCosif());
            paiFilho = new CosifPaiFilho(ppfid, cosif, cosifAux);
        }

    }

    public static void main(String args[])
    {
        PopularFilhosCosif popFilhosCosif = new PopularFilhosCosif();
        popFilhosCosif.popularPaiFilho();
    }
}