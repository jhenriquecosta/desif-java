

package br.gov.pbh.desif.service.registrobanco.impl;

import br.gov.pbh.desif.dao.*;
import br.gov.pbh.desif.dao.impl.TarifaServicoDaoImpl;
import br.gov.pbh.desif.model.pojo.*;
import br.gov.pbh.desif.model.registros.RegUtil;
import br.gov.pbh.desif.service.contexto.Contexto;
import br.gov.pbh.desif.service.registrobanco.ValidaBancoReg0200;
import br.gov.pbh.desif.view.telas.PanGerarDeclaracao;
import java.util.*;

public class ValidaBancoReg0200Impl
    implements ValidaBancoReg0200
{

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

    public ValidaBancoReg0200Impl()
    {
    }

    public void executar()
        throws Exception
    {
        descVazias = new ArrayList();
        mapeamentoCodIdentTarifa = new HashMap();
        regUtil = new RegUtil();
        panGerDec = (PanGerarDeclaracao)Contexto.getObject("panGD");
        IdentificacaoDeclaracao declaracao = (IdentificacaoDeclaracao)declaracaoDao.load(new Integer(Integer.parseInt("1")));
        List respTitulo = tituloDao.identificarTipoInstituicao("id", declaracao.getTitulo());
        List respTarServ = tarServDao.findAll();
        if(respTitulo.size() > 0 && ((Titulo)respTitulo.get(0)).getObrigatoria() && respTarServ.size() == 0)
        {
            String txtSolucao = (new StringBuilder()).append("Tipo Institui\347\343o : ").append(declaracao.getTitulo()).toString();
            regUtil.setErro(0L, "EI024", 2, (short)2, "0200", txtSolucao);
        }
        Iterator i = respTarServ.iterator();
        double incremento = regUtil.incremetoPorcentagem(30D, respTarServ.size());
        double sentinela = 50D;
        int atualizar = 0;
        for(; i.hasNext(); verificaSubtitulo())
        {
            if(sentinela < 76D)
            {
                atualizar = (int)sentinela;
                panGerDec.atualizarProgressoValidacao(atualizar, 100);
                sentinela += incremento;
            }
            tarServ = (TarifaServico)i.next();
            verificaCodIdentTarifa();
            verificaDescTarifa();
        }

        verificaDescricaoVazia();
    }

    public void verificaCodIdentTarifa()
        throws Exception
    {
        int campo = 3;
        if(tarServDao.verificaUnicidadeCodIdentTarifaeCosSubtitulo(tarServ.getCodIdentTarifa(), tarServ.getCodSubtitulo()))
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo da tarifa: ").append(tarServ.getCodIdentTarifa()).append(", subt\355tulo: ").append(tarServ.getCodSubtitulo()).toString();
            regUtil.setErro(tarServ.getNumLinhTariServ().longValue(), "EI015", campo, (short)2, "0200", txtSolucao);
        }
    }

    public void verificaDescTarifa()
        throws Exception
    {
        int campo = 4;
        if(mapeamentoCodIdentTarifa.isEmpty())
        {
            if(!tarServ.getDescTarifa().equals(""))
                mapeamentoCodIdentTarifa.put(tarServ.getCodIdentTarifa(), tarServ.getDescTarifa());
            else
                descVazias.add(tarServ);
        } else
        if(!tarServ.getDescTarifa().equals(""))
        {
            if(mapeamentoCodIdentTarifa.containsKey(tarServ.getCodIdentTarifa()))
            {
                if(!mapeamentoCodIdentTarifa.containsValue(tarServ.getDescTarifa()))
                {
                    String txtSolucao = (new StringBuilder()).append("C\363digo da tarifa: ").append(tarServ.getCodIdentTarifa()).toString();
                    regUtil.setErro(tarServ.getNumLinhTariServ().longValue(), "EI020", campo, (short)2, "0200", txtSolucao);
                }
            } else
            {
                mapeamentoCodIdentTarifa.put(tarServ.getCodIdentTarifa(), tarServ.getDescTarifa());
            }
        } else
        {
            descVazias.add(tarServ);
        }
    }

    public void verificaSubtitulo()
        throws Exception
    {
        int campo = 5;
        List respPgcc = pgccDao.findField("conta", tarServ.getCodSubtitulo());
        if(respPgcc.size() == 0)
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo da tarifa: ").append(tarServ.getCodIdentTarifa()).append(", subt\355tulo: ").append(tarServ.getCodSubtitulo()).toString();
            regUtil.setErro(tarServ.getNumLinhTariServ().longValue(), "EI013", campo, (short)2, "0200", txtSolucao);
        } else
        {
            for(int i = 0; i < respPgcc.size(); i++)
            {
                PlanoGeralContaComentado pgcc = (PlanoGeralContaComentado)respPgcc.get(i);
                if(pgccsPFIdDao.identificarPossuiFilhos(pgcc))
                {
                    String txtSolucao = (new StringBuilder()).append("C\363digo da tarifa: ").append(tarServ.getCodIdentTarifa()).append(", subt\355tulo: ").append(tarServ.getCodSubtitulo()).toString();
                    regUtil.setErro(tarServ.getNumLinhTariServ().longValue(), "EI002", campo, (short)2, "0200", txtSolucao);
                }
            }

        }
    }

    public void verificaDescricaoVazia()
        throws Exception
    {
        for(int i = 0; i < descVazias.size(); i++)
            if(!mapeamentoCodIdentTarifa.containsKey(((TarifaServico)descVazias.get(i)).getCodIdentTarifa()))
            {
                String txtSolucao = (new StringBuilder()).append("C\363digo da tarifa: ").append(tarServ.getCodIdentTarifa()).toString();
                regUtil.setErro(((TarifaServico)descVazias.get(i)).getNumLinhTariServ().longValue(), "EI012", 4, (short)2, "0200", txtSolucao);
            }

    }

    public IdentDeclaracaoDao getDeclaracaoDao()
    {
        return declaracaoDao;
    }

    public void setDeclaracaoDao(IdentDeclaracaoDao declaracaoDao)
    {
        this.declaracaoDao = declaracaoDao;
    }

    public PlanoGeralContaComentadoDao getPgccDao()
    {
        return pgccDao;
    }

    public void setPgccDao(PlanoGeralContaComentadoDao pgccDao)
    {
        this.pgccDao = pgccDao;
    }

    public TituloDao getTituloDao()
    {
        return tituloDao;
    }

    public void setTituloDao(TituloDao tituloDao)
    {
        this.tituloDao = tituloDao;
    }

    public TarifaServicoDaoImpl getTarServDao()
    {
        return tarServDao;
    }

    public void setTarServDao(TarifaServicoDaoImpl tarServDao)
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
}
