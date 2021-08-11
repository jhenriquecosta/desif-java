

package br.gov.pbh.desif.service.registrobanco.impl;

import br.gov.pbh.desif.dao.*;
import br.gov.pbh.desif.model.pojo.IdentificacaoDeclaracao;
import br.gov.pbh.desif.model.pojo.IdentificacaoDependencia;
import br.gov.pbh.desif.service.contexto.Contexto;
import br.gov.pbh.desif.service.registrobanco.*;
import java.util.Iterator;
import java.util.List;

public class ValidaBancoApuracaoMensalIssqnImpl
    implements ValidaBancoApuracaoMensalIssqn
{

    private ValidaBancoReg0000 VBReg0000;
    private ValidaBancoReg0400 VBReg0400;
    private ValidaBancoReg0430 VBReg0430;
    private ValidaBancoReg0440 VBReg0440;
    private IdentDeclaracaoDao declaracaoDao;
    private ApuracaoReceitaDao apuracaoMensalIssqnDao;
    private IdentDependenciaDao dependenciaDao;

    public ValidaBancoApuracaoMensalIssqnImpl()
    {
    }

    public void executar()
        throws Exception
    {
        updateCnpj();
        VBReg0000.executar();
        VBReg0400.executar();
        VBReg0430.executar();
        VBReg0440.executar();
    }

    public void updateCnpj()
        throws Exception
    {
        IdentificacaoDeclaracao declaracao = (IdentificacaoDeclaracao)declaracaoDao.load(new Integer(Integer.parseInt("1")));
        if(declaracao.getTipoConsolidacao().intValue() == 1 || declaracao.getTipoConsolidacao().intValue() == 2)
            apuracaoMensalIssqnDao.updateAll(declaracao.getCnpjResponsavelRecolhimento());
        else
        if(declaracao.getTipoConsolidacao().intValue() == 3 || declaracao.getTipoConsolidacao().intValue() == 4)
        {
            dependenciaDao = (IdentDependenciaDao)Contexto.getObject("dependenciaDao");
            List dependencias = dependenciaDao.findAll();
            IdentificacaoDependencia dependencia;
            for(Iterator i = dependencias.iterator(); i.hasNext(); apuracaoMensalIssqnDao.updateCodDependencia(dependencia.getCnpjUnificado(), dependencia.getCodigoDependencia()))
                dependencia = (IdentificacaoDependencia)i.next();

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

    public ValidaBancoReg0400 getVBReg0400()
    {
        return VBReg0400;
    }

    public void setVBReg0400(ValidaBancoReg0400 VBReg0400)
    {
        this.VBReg0400 = VBReg0400;
    }

    public ValidaBancoReg0430 getVBReg0430()
    {
        return VBReg0430;
    }

    public void setVBReg0430(ValidaBancoReg0430 VBReg0430)
    {
        this.VBReg0430 = VBReg0430;
    }

    public ValidaBancoReg0440 getVBReg0440()
    {
        return VBReg0440;
    }

    public void setVBReg0440(ValidaBancoReg0440 VBReg0440)
    {
        this.VBReg0440 = VBReg0440;
    }

    public ApuracaoReceitaDao getApuracaoMensalIssqnDao()
    {
        return apuracaoMensalIssqnDao;
    }

    public void setApuracaoMensalIssqnDao(ApuracaoReceitaDao apuracaoMensalIssqnDao)
    {
        this.apuracaoMensalIssqnDao = apuracaoMensalIssqnDao;
    }

    public IdentDeclaracaoDao getDeclaracaoDao()
    {
        return declaracaoDao;
    }

    public void setDeclaracaoDao(IdentDeclaracaoDao declaracaoDao)
    {
        this.declaracaoDao = declaracaoDao;
    }

    public IdentDependenciaDao getDependenciaDao()
    {
        return dependenciaDao;
    }

    public void setDependenciaDao(IdentDependenciaDao dependenciaDao)
    {
        this.dependenciaDao = dependenciaDao;
    }
}
