
package br.gov.pbh.desif.service.registrobanco.impl;

import br.gov.pbh.desif.dao.*;
import br.gov.pbh.desif.model.pojo.*;
import br.gov.pbh.desif.model.registros.RegUtil;
import br.gov.pbh.desif.service.contexto.Contexto;
import br.gov.pbh.desif.service.registrobanco.ValidaBancoReg0100;
import br.gov.pbh.desif.view.telas.PanGerarDeclaracao;
import java.io.PrintStream;
import java.util.*;

public class ValidaBancoReg0100Impl
    implements ValidaBancoReg0100
{

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

    public ValidaBancoReg0100Impl()
    {
    }

    public void executar()
        throws Exception
    {
        regUtil = new RegUtil();
        panGerDec = (PanGerarDeclaracao)Contexto.getObject("panGD");
        List listaPGCC = pgccDao.findAll();
        Iterator i = listaPGCC.iterator();
        double incremento = regUtil.incremetoPorcentagem(55D, listaPGCC.size());
        double sentinela = 0.0D;
        int atualizar = 0;
        do
        {
            if(!i.hasNext())
                break;
            if(sentinela < 51D)
            {
                atualizar = (int)sentinela;
                panGerDec.atualizarProgressoValidacao(atualizar, 100);
                sentinela += incremento;
            }
            pgcc = (PlanoGeralContaComentado)i.next();
            verificaDesConta();
            verificaContaSuperior();
            verificaContaCosif();
            verificarCodTributacaoDesif();
            if(!RegUtil.exErro)
            {
                verificaExistemNiveisZerados();
                verificaContasAnaliticasPGCCCosif();
                verificaNiveisContasCosifPaieFilha();
                verificacaoDetalhadaNiveisPgccCosif();
                verificaNiveisContasPgccPaieFilha();
            }
        } while(true);
    }

    public void verificaExistemNiveisZerados()
        throws Exception
    {
        if(pgcc.getNivel().intValue() == 0)
        {
            System.out.println("VerificaExisteNiveisZerados");
            regUtil.setErro(pgcc.getNumLinhaPgcc().longValue(), "EI030", 7, (short)2, "0100");
        }
    }

    public void verificaConta()
        throws Exception
    {
        int campo = 3;
        List respNumContas = pgccDao.findField("conta", pgcc.getConta());
        if(respNumContas.size() > 1)
        {
            String txtSolucao = (new StringBuilder()).append("Conta: ").append(pgcc.getConta()).toString();
            regUtil.setErro(pgcc.getNumLinhaPgcc().longValue(), "EI001", campo, (short)2, "0100", txtSolucao);
        }
    }

    public void verificaDesConta()
        throws Exception
    {
        int campo = 5;
        if(!pgccsPFIdDao.identificarPossuiFilhos(pgcc) && pgcc.getDescConta().equals(""))
        {
            String txtSolucao = (new StringBuilder()).append("Conta: ").append(pgcc.getConta()).toString();
            regUtil.setErro(pgcc.getNumLinhaPgcc().longValue(), "EI004", campo, (short)2, "0100", txtSolucao);
        }
    }

    public void verificaContaSuperior()
        throws Exception
    {
        int campo = 6;
        List respExisteContaSupComoConta = null;
        if(!pgcc.getContaSupe().equals(""))
        {
            respExisteContaSupComoConta = pgccDao.findField("conta", pgcc.getContaSupe());
            if(respExisteContaSupComoConta != null && respExisteContaSupComoConta.size() == 0)
            {
                String txtSolucao = (new StringBuilder()).append("Conta: ").append(pgcc.getConta()).append(" Conta Superior:  ").append(pgcc.getContaSupe()).toString();
                regUtil.setErro(pgcc.getNumLinhaPgcc().longValue(), "EI005", campo, (short)2, "0100", txtSolucao);
            }
        }
    }

    public void verificaContaCosif()
        throws Exception
    {
        int campo = 7;
        List respContaCosif = cosifDao.findField("numeroContaCosif", pgcc.getContaCosif());
        if(respContaCosif.size() == 0)
        {
            String txtSolucao = (new StringBuilder()).append("Conta: ").append(pgcc.getConta()).append(" conta Cosif: ").append(pgcc.getContaCosif()).toString();
            regUtil.setErro(pgcc.getNumLinhaPgcc().longValue(), "EI008", campo, (short)2, "0100", txtSolucao);
        } else
        {
            IdentificacaoDeclaracao declaracao = (IdentificacaoDeclaracao)declaracaoDao.load(new Integer(Integer.parseInt("1")));
            Cosif cosif = (Cosif)respContaCosif.get(0);
            if(cosif.getDataCriacao().after(declaracao.getDataInicioCompetencia()))
            {
                String txtAlerta = (new StringBuilder()).append("Conta PGCC: ").append(pgcc.getConta()).append(", Conta COSIF: ").append(pgcc.getContaCosif()).append(", m\352s inicio da compet\352ncia informado: ").append(declaracao.getDataInicioCompetencia()).append(", data cria\347\343o da conta COSIF: ").append(cosif.getDataCriacao()).toString();
                regUtil.setAlerta(pgcc.getNumLinhaPgcc().longValue(), "A008", campo, (short)2, "0100", txtAlerta);
            }
            if(cosif.getDataExtinsao() != null && cosif.getDataExtinsao().before(declaracao.getDataFimCompetencia()))
            {
                String txtAlerta = (new StringBuilder()).append("Conta PGCC: ").append(pgcc.getConta()).append(", Conta COSIF: ").append(pgcc.getContaCosif()).append(", m\352s fim da compet\352ncia informado: ").append(declaracao.getDataFimCompetencia()).append(", data extin\347\343o da conta COSIF: ").append(cosif.getDataExtinsao()).toString();
                regUtil.setAlerta(pgcc.getNumLinhaPgcc().longValue(), "A009", campo, (short)2, "0100", txtAlerta);
            }
        }
        String grupoContaCosif = pgcc.getContaCosif().substring(0, 1);
        if(!grupoContaCosif.equals("7"))
        {
            String txtSolucao = (new StringBuilder()).append("Conta: ").append(pgcc.getConta()).append(" conta Cosif: ").append(pgcc.getContaCosif()).toString();
            regUtil.setErro(pgcc.getNumLinhaPgcc().longValue(), "EI009", campo, (short)2, "0100", txtSolucao);
        }
    }

    public void verificaContasAnaliticasPGCCCosif()
        throws Exception
    {
        if(!pgccsPFIdDao.identificarPossuiFilhos(pgcc))
        {
            Cosif cosif = cosifDao.BuscaCosif(pgcc.getContaCosif());
            if(cosifDao.buscarGalhos(cosif.getNumeroContaCosif()).size() > 0)
                regUtil.setErro(pgcc.getNumLinhaPgcc().longValue(), "EI033", 3, (short)2, "0100");
        }
    }

    public void verificaNiveisContasCosifPaieFilha()
        throws Exception
    {
        Cosif cosif = cosifDao.BuscaCosif(pgcc.getContaCosif());
        if(cosif.getNumNivel().intValue() != 1)
        {
            PlanoGeralContaComentado pgccPai = pgccDao.buscaPgcc(pgcc.getContaSupe());
            Cosif cosifPaiPgcc = cosifDao.BuscaCosif(pgccPai.getContaCosif());
            if(cosifPaiPgcc.getNumNivel().intValue() > cosif.getNumNivel().intValue())
            {
                System.out.println("Problema no Cosif");
                regUtil.setErro(pgcc.getNumLinhaPgcc().longValue(), "EI030", 6, (short)2, "0100");
            }
        }
    }

    public void verificaNiveisContasPgccPaieFilha()
        throws Exception
    {
        if(pgcc.getNivel().intValue() != 1)
        {
            PlanoGeralContaComentado pgccPai = pgccDao.buscaPgcc(pgcc.getContaSupe());
            if(pgccPai.getNivel().intValue() > pgcc.getNivel().intValue())
            {
                System.out.println("Problema no Pgcc");
                regUtil.setErro(pgcc.getNumLinhaPgcc().longValue(), "EI030", 6, (short)2, "0100");
            }
        }
    }

    public void verificacaoDetalhadaNiveisPgccCosif()
        throws Exception
    {
        Cosif cosifFilho = cosifDao.BuscaCosif(pgcc.getContaCosif());
        if(cosifFilho.getNumNivel().intValue() != 1)
        {
            PlanoGeralContaComentado pgccPai = pgccDao.buscaPgcc(pgcc.getContaSupe());
            Cosif cosifPgccPai = cosifDao.BuscaCosif(pgccPai.getContaCosif());
            Cosif cosifPai = cosifDao.BuscaCosif(cosifFilho.getNumeroContaSuperior());
            if(cosifPgccPai.getNumNivel().intValue() == cosifFilho.getNumNivel().intValue())
            {
                if(!pgcc.getContaCosif().equals(pgccPai.getContaCosif()))
                {
                    System.out.println("verificacaoDetalhadaNiveisPgccCosif()");
                    String txtSolucao = (new StringBuilder()).append("Conta: ").append(pgcc.getConta()).append(", Conta Cosif: ").append(pgcc.getContaCosif()).append("Conta Superior: ").append(pgccPai.getConta()).append("Conta Cosif Superior : ").append(pgccPai.getContaCosif()).toString();
                    regUtil.setErro(pgcc.getNumLinhaPgcc().longValue(), "EI027", 7, (short)2, "0100", txtSolucao);
                }
            } else
            {
                String cosifSuperior = cosifPai.getNumeroContaCosif();
                int numeroNivelCosif = cosifFilho.getNumNivel().intValue();
                int numeroNivelCosifPai = cosifPgccPai.getNumNivel().intValue();
                int i = 0;
                do
                {
                    if(numeroNivelCosif - i <= 0)
                        break;
                    i++;
                    if((numeroNivelCosifPai == numeroNivelCosif - i) & cosifPgccPai.getNumeroContaCosif().equals(cosifSuperior))
                        break;
                    if(cosifSuperior.equals("70000009"))
                    {
                        i = numeroNivelCosif;
                    } else
                    {
                        Cosif cosifAvo = cosifDao.BuscaCosif(cosifSuperior);
                        cosifSuperior = cosifAvo.getNumeroContaSuperior();
                    }
                } while(true);
                if(numeroNivelCosif - i == 0)
                {
                    System.out.println("Algoritimo");
                    String txtSolucao = (new StringBuilder()).append("Conta: ").append(pgcc.getConta()).append(", Conta Cosif: ").append(pgcc.getContaCosif()).append("Conta Superior: ").append(pgccPai.getConta()).append("Conta Cosif Superior : ").append(pgccPai.getContaCosif()).toString();
                    regUtil.setErro(pgcc.getNumLinhaPgcc().longValue(), "EI027", 7, (short)2, "0100", txtSolucao);
                }
            }
        }
    }

    public void verificarCodTributacaoDesif()
        throws Exception
    {
        int campo = 8;
        if(pgccsPFIdDao.identificarPossuiFilhos(pgcc) && !pgcc.getCodTribDesif().trim().equals(""))
        {
            String txtSolucao = (new StringBuilder()).append("Conta: ").append(pgcc.getConta()).append(" C\363digos de Tributa\347\343o da DES-IF: ").append(pgcc.getCodTribDesif()).toString();
            regUtil.setErro(pgcc.getNumLinhaPgcc().longValue(), "EI010", campo, (short)2, "0100", txtSolucao);
        }
        if(!pgcc.getCodTribDesif().equals(""))
        {
            List respTrib = codTribDao.buscaCodTributacaoDesif("id", pgcc.getCodTribDesif());
            if(respTrib.size() == 0)
            {
                String txtSolucao = (new StringBuilder()).append("Conta: ").append(pgcc.getConta()).append(" C\363digos de Tributa\347\343o da DES-IF: ").append(pgcc.getCodTribDesif()).toString();
                regUtil.setErro(pgcc.getNumLinhaPgcc().longValue(), "EG011", campo, (short)2, "0100", txtSolucao);
            }
        }
        List respReg200 = tarServDao.findField("codSubtitulo", pgcc.getConta());
        if(respReg200.size() > 0 && pgcc.getCodTribDesif().equals(""))
        {
            String txtSolucao = (new StringBuilder()).append("Conta: ").append(pgcc.getConta()).toString();
            regUtil.setErro(pgcc.getNumLinhaPgcc().longValue(), "EI014", campo, (short)2, "0100", txtSolucao);
        }
    }

    public CosifDao getCosifDao()
    {
        return cosifDao;
    }

    public void setCosifDao(CosifDao cosifDao)
    {
        this.cosifDao = cosifDao;
    }

    public PlanoGeralContaComentadoDao getPgccDao()
    {
        return pgccDao;
    }

    public void setPgccDao(PlanoGeralContaComentadoDao pgccDao)
    {
        this.pgccDao = pgccDao;
    }

    public CodTributacaoDesifDao getCodTribDao()
    {
        return codTribDao;
    }

    public void setCodTribDao(CodTributacaoDesifDao codTribDao)
    {
        this.codTribDao = codTribDao;
    }

    public CodTribuMunicipalDao getCodTribMunicDao()
    {
        return codTribMunicDao;
    }

    public void setCodTribMunicDao(CodTribuMunicipalDao codTribMunicDao)
    {
        this.codTribMunicDao = codTribMunicDao;
    }

    public IdentServicosRemunVariavelDao getIdentServRemVarDao()
    {
        return identServRemVarDao;
    }

    public void setIdentServRemVarDao(IdentServicosRemunVariavelDao identServRemVarDao)
    {
        this.identServRemVarDao = identServRemVarDao;
    }

    public TarifaServicoDao getTarServDao()
    {
        return tarServDao;
    }

    public void setTarServDao(TarifaServicoDao tarServDao)
    {
        this.tarServDao = tarServDao;
    }

    public PgccsPaiFilhoDao getPgccsPFIdDao()
    {
        return pgccsPFIdDao;
    }

    public void setPgccsPFIdDao(PgccsPaiFilhoDao pgccsPFIdDao)
    {
        this.pgccsPFIdDao = pgccsPFIdDao;
    }

    public IdentDeclaracaoDao getDeclaracaoDao()
    {
        return declaracaoDao;
    }

    public void setDeclaracaoDao(IdentDeclaracaoDao declaracaoDao)
    {
        this.declaracaoDao = declaracaoDao;
    }

    public CosifPaiFilhoDao getCosifPFIdDao()
    {
        return CosifPFIdDao;
    }

    public void setCosifPFIdDao(CosifPaiFilhoDao CosifPFIdDao)
    {
        this.CosifPFIdDao = CosifPFIdDao;
    }
}
