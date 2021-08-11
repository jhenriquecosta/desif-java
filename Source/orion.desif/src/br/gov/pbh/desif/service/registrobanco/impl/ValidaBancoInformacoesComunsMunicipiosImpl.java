package br.gov.pbh.desif.service.registrobanco.impl;

import br.gov.pbh.desif.dao.PgccsPaiFilhoDao;
import br.gov.pbh.desif.dao.PlanoGeralContaComentadoDao;
import br.gov.pbh.desif.model.pojo.*;
import br.gov.pbh.desif.model.registros.RegUtil;
import br.gov.pbh.desif.service.registrobanco.*;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;

public class ValidaBancoInformacoesComunsMunicipiosImpl
    implements ValidaBancoInformacoesComunsMunicipios
{

    private ValidaBancoReg0000 VBReg0000;
    private ValidaBancoReg0100 VBReg0100;
    private ValidaBancoReg0200 VBReg0200;
    private ValidaBancoReg0300 VBReg0300;
    private PgccsPaiFilhoDao pgccsPFIdDao;
    private PlanoGeralContaComentadoDao pgccDao;
    RegUtil regUtil;
    private int nivel;
    private int iteracao;

    public ValidaBancoInformacoesComunsMunicipiosImpl()
    {
        nivel = 0;
        iteracao = 0;
        regUtil = new RegUtil();
    }

    public void executar()
        throws Exception
    {
        List listaPGCC = pgccDao.findAll();
        if(listaPGCC.size() == 0)
        {
            regUtil.setErro(0L, "EI023", 2, (short)2, "0100");
        } else
        {
            Iterator i = listaPGCC.iterator();
            do
            {
                if(!i.hasNext())
                    break;
                PlanoGeralContaComentado pgcc = (PlanoGeralContaComentado)i.next();
                List respNumContas = pgccDao.findField("conta", pgcc.getConta());
                if(respNumContas.size() > 1)
                {
                    String txtSolucao = (new StringBuilder()).append("Conta: ").append(pgcc.getConta()).toString();
                    regUtil.setErro(pgcc.getNumLinhaPgcc().longValue(), "EI001", 3, (short)2, "0100", txtSolucao);
                }
            } while(true);
            List raiz = buscaRaizArvore();
            if(!((PlanoGeralContaComentado)raiz.get(0)).getContaCosif().equals("70000009"))
            {
                System.out.println("Verrifica raizes");
                regUtil.setErro(((PlanoGeralContaComentado)raiz.get(0)).getNumLinhaPgcc().longValue(), "EI027", 7, (short)2, "0100");
            }
            List contaNivel1Cosif = pgccDao.findField("contaCosif", ((PlanoGeralContaComentado)raiz.get(0)).getContaCosif());
            if(contaNivel1Cosif.size() != 1)
                regUtil.setErro(((PlanoGeralContaComentado)raiz.get(0)).getNumLinhaPgcc().longValue(), "EI031", 7, (short)2, "0100");
            List contaNivel1Pgcc = pgccDao.findField("conta", ((PlanoGeralContaComentado)raiz.get(0)).getConta());
            if(contaNivel1Pgcc.size() != 1)
                regUtil.setErro(((PlanoGeralContaComentado)raiz.get(0)).getNumLinhaPgcc().longValue(), "EI032", 3, (short)2, "0100");
            if(!RegUtil.exErro)
            {
                montarArvore(raiz);
                nivel = 0;
                VBReg0000.executar();
                VBReg0100.executar();
                VBReg0200.executar();
                VBReg0300.executar();
            }
        }
    }

    public void montarArvore(List raiz)
    {
        buscaGalhos(raiz);
    }

    public List buscaRaizArvore()
    {
        List raiz = pgccDao.buscarRaizArvore();
        if(raiz.size() == 0)
        {
            regUtil.setErro(0L, "EI007", 3, (short)1, "0100", "N\343o existe nenhum campo raiz do PGCC");
            raiz = null;
        } else
        {
            if(raiz.size() == 1)
                return raiz;
            if(raiz.size() > 1)
            {
                regUtil.setErro(0L, "EI021", 3, (short)1, "0100", "Existe mais de um campo raiz do PGCC");
                raiz = null;
            }
        }
        return raiz;
    }

    public void buscaGalhos(List elemento)
    {
        int nivelNoLoop = 0;
        nivel++;
        iteracao = 0;
        Iterator i = elemento.iterator();
        PlanoGeralContaComentado pgcc = null;
        List galhosResultantes = null;
        if(elemento.size() > 1)
            nivelNoLoop = nivel;
        do
        {
            if(!i.hasNext())
                break;
            Object o = i.next();
            galhosResultantes = pgccDao.buscarGalhos(((PlanoGeralContaComentado)o).getConta());
            pgcc = (PlanoGeralContaComentado)o;
            if(iteracao != 0)
                nivel = nivelNoLoop;
            iteracao++;
            pgcc.setNivel(Integer.valueOf(nivel));
            pgccDao.update(pgcc);
            if(galhosResultantes.size() > 0)
            {
                inserirFilhos(galhosResultantes, pgcc);
                buscaGalhos(galhosResultantes);
            }
        } while(true);
    }

    public void inserirFilhos(List galhos, PlanoGeralContaComentado pgcc)
    {
        PlanoGeralContaComentado pgccAux = null;
        PgccsPaiFilho paiFilho;
        for(Iterator i = galhos.iterator(); i.hasNext(); pgccsPFIdDao.save(paiFilho))
        {
            pgccAux = (PlanoGeralContaComentado)i.next();
            PgccsPaiFilhoId ppfid = new PgccsPaiFilhoId(pgcc.getId(), pgccAux.getId());
            paiFilho = new PgccsPaiFilho(ppfid, pgcc, pgccAux);
        }

    }

    public void verificaFilhos()
    {
        List l = pgccDao.findAll();
        for(int i = 0; i < l.size(); i++)
        {
            PlanoGeralContaComentado pgcc = (PlanoGeralContaComentado)l.get(i);
        }

    }

    public void imprimirArvore(List arvore)
    {
        if(arvore.size() > 0)
        {
            for(Iterator i = arvore.iterator(); i.hasNext();)
            {
                Object o = i.next();
                if(o instanceof List)
                {
                    imprimirArvore((List)o);
                } else
                {
                    if(o == null)
                        return;
                    System.out.println(((PlanoGeralContaComentado)o).getConta());
                }
            }

        } else
        {
            System.out.println("A \341rvore est\341 vazia n\343o h\341 elementos para serem impressos.");
        }
    }

    public ValidaBancoReg0000 getVBReg0000()
    {
        return VBReg0000;
    }

    public void setVBReg0000(ValidaBancoReg0000 VBReg0000)
    {
        this.VBReg0000 = VBReg0000;
    }

    public ValidaBancoReg0100 getVBReg0100()
    {
        return VBReg0100;
    }

    public void setVBReg0100(ValidaBancoReg0100 VBReg0100)
    {
        this.VBReg0100 = VBReg0100;
    }

    public ValidaBancoReg0200 getVBReg0200()
    {
        return VBReg0200;
    }

    public void setVBReg0200(ValidaBancoReg0200 VBReg0200)
    {
        this.VBReg0200 = VBReg0200;
    }

    public ValidaBancoReg0300 getVBReg0300()
    {
        return VBReg0300;
    }

    public void setVBReg0300(ValidaBancoReg0300 VBReg0300)
    {
        this.VBReg0300 = VBReg0300;
    }

    public PlanoGeralContaComentadoDao getPgccDao()
    {
        return pgccDao;
    }

    public void setPgccDao(PlanoGeralContaComentadoDao pgccDao)
    {
        this.pgccDao = pgccDao;
    }

    public PgccsPaiFilhoDao getPgccsPFIdDao()
    {
        return pgccsPFIdDao;
    }

    public void setPgccsPFIdDao(PgccsPaiFilhoDao pgccsPFIdDao)
    {
        this.pgccsPFIdDao = pgccsPFIdDao;
    }

    public int getNivel()
    {
        return nivel;
    }

    public void setNivel(int nivel)
    {
        this.nivel = nivel;
    }
}
