

package br.gov.pbh.desif.service.registrobanco.impl;

import br.gov.pbh.desif.dao.*;
import br.gov.pbh.desif.model.pojo.IdentServicosRemunVariavel;
import br.gov.pbh.desif.model.pojo.ServRemunVar;
import br.gov.pbh.desif.model.registros.RegUtil;
import br.gov.pbh.desif.service.contexto.Contexto;
import br.gov.pbh.desif.service.registrobanco.ValidaBancoReg0300;
import br.gov.pbh.desif.view.telas.PanGerarDeclaracao;
import java.util.*;

public class ValidaBancoReg0300Impl
    implements ValidaBancoReg0300
{

    private IdentServicosRemunVariavelDao identServRemVarDao;
    private final String registro = "0300";
    private RegUtil regUtil;
    private PanGerarDeclaracao panGerDec;
    private IdentServicosRemunVariavel identSerRemVar;
    private ServRemunVarDao servRemVarDao;
    private PlanoGeralContaComentadoDao pgccDao;
    private HashMap mapeamentoCodIdentTarifa;
    private List descVazias;

    public ValidaBancoReg0300Impl()
    {
    }

    public void executar()
        throws Exception
    {
        regUtil = new RegUtil();
        panGerDec = (PanGerarDeclaracao)Contexto.getObject("panGD");
        mapeamentoCodIdentTarifa = new HashMap();
        descVazias = new ArrayList();
        List respServRemVar = identServRemVarDao.findAll();
        Iterator i = respServRemVar.iterator();
        double incremento = regUtil.incremetoPorcentagem(30D, respServRemVar.size());
        double sentinela = 75D;
        int atualizar = 0;
        for(; i.hasNext(); verificaDescTarifa())
        {
            if(sentinela < 101D)
            {
                atualizar = (int)sentinela;
                panGerDec.atualizarProgressoValidacao(atualizar, 100);
                sentinela += incremento;
            }
            identSerRemVar = (IdentServicosRemunVariavel)i.next();
            verificaIdentServRemnVariavel();
            verificaSubtitulo();
        }

        verificaDescricaoVazia();
    }

    public void verificaIdentServRemnVariavel()
        throws Exception
    {
        int campo = 3;
        List resp = servRemVarDao.findField("cod", identSerRemVar.getCodIdentServRemnVariavel());
        if(resp.size() == 0)
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo do servi\347o : ").append(identSerRemVar.getCodIdentServRemnVariavel()).toString();
            regUtil.setErro(identSerRemVar.getNumLinhIdenServPrecVarl().longValue(), "EI017", campo, (short)2, "0300", txtSolucao);
        }
        if(identServRemVarDao.verificaUnicidadeCodIdentServRemVarSubtitulo(identSerRemVar.getCodIdentServRemnVariavel(), identSerRemVar.getCodSubtitulo()))
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo do servi\347o : ").append(identSerRemVar.getCodIdentServRemnVariavel()).append(" subt\355tulo : ").append(identSerRemVar.getCodSubtitulo()).toString();
            regUtil.setErro(identSerRemVar.getNumLinhIdenServPrecVarl().longValue(), "EI019", campo, (short)2, "0300", txtSolucao);
        }
    }

    public void verificaDescTarifa()
        throws Exception
    {
        int campo = 4;
        if(!identSerRemVar.getDescServRemnVariavel().equals(""))
            mapeamentoCodIdentTarifa.put(identSerRemVar.getCodIdentServRemnVariavel(), identSerRemVar.getDescServRemnVariavel());
        else
            descVazias.add(identSerRemVar);
    }

    public void verificaSubtitulo()
        throws Exception
    {
        int campo = 5;
        List respContSupe = pgccDao.findField("contaSupe", identSerRemVar.getCodSubtitulo());
        if(respContSupe.size() != 0)
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo do servi\347o : ").append(identSerRemVar.getCodIdentServRemnVariavel()).append(" C\363digo subtitulo : ").append(identSerRemVar.getCodSubtitulo()).toString();
            regUtil.setErro(identSerRemVar.getNumLinhIdenServPrecVarl().longValue(), "EI025", campo, (short)2, "0300", txtSolucao);
        }
        List respExisteConta = pgccDao.findField("conta", identSerRemVar.getCodSubtitulo());
        if(respExisteConta.size() == 0)
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo do servi\347o : ").append(identSerRemVar.getCodIdentServRemnVariavel()).append(" C\363digo subtitulo : ").append(identSerRemVar.getCodSubtitulo()).toString();
            regUtil.setErro(identSerRemVar.getNumLinhIdenServPrecVarl().longValue(), "EI026", campo, (short)2, "0300", txtSolucao);
        }
    }

    public void verificaDescricaoVazia()
        throws Exception
    {
        for(int i = 0; i < descVazias.size(); i++)
        {
            if(mapeamentoCodIdentTarifa.containsKey(((IdentServicosRemunVariavel)descVazias.get(i)).getCodIdentServRemnVariavel()))
                continue;
            List resp = servRemVarDao.findField("cod", ((IdentServicosRemunVariavel)descVazias.get(i)).getCodIdentServRemnVariavel());
            if(resp.isEmpty())
                continue;
            ServRemunVar srv = (ServRemunVar)resp.get(0);
            if(srv.getOpcObrig().intValue() == 1)
            {
                String txtSolucao = (new StringBuilder()).append("C\363digo do servi\347o : ").append(((IdentServicosRemunVariavel)descVazias.get(i)).getCodIdentServRemnVariavel()).toString();
                regUtil.setErro(((IdentServicosRemunVariavel)descVazias.get(i)).getNumLinhIdenServPrecVarl().longValue(), "EI022", 4, (short)2, "0300", txtSolucao);
            }
        }

    }

    public IdentServicosRemunVariavelDao getIdentServRemVarDao()
    {
        return identServRemVarDao;
    }

    public void setIdentServRemVarDao(IdentServicosRemunVariavelDao identServRemVarDao)
    {
        this.identServRemVarDao = identServRemVarDao;
    }

    public ServRemunVarDao getServRemVarDao()
    {
        return servRemVarDao;
    }

    public void setServRemVarDao(ServRemunVarDao servRemVarDao)
    {
        this.servRemVarDao = servRemVarDao;
    }

    public PlanoGeralContaComentadoDao getPgccDao()
    {
        return pgccDao;
    }

    public void setPgccDao(PlanoGeralContaComentadoDao pgccDao)
    {
        this.pgccDao = pgccDao;
    }
}
