

package br.gov.pbh.desif.service.registrobanco.impl;

import br.gov.pbh.desif.dao.IdentDeclaracaoDao;
import br.gov.pbh.desif.dao.IdentDependenciaDao;
import br.gov.pbh.desif.model.pojo.BalanceteAnaliticoMensal;
import br.gov.pbh.desif.service.registrobanco.*;

public class ValidaBancoModuloContabilImpl
    implements ValidaBancoModuloContabil
{

    private ValidaBancoReg0000 VBReg0000;
    private ValidaBancoReg0400 VBReg0400;
    private ValidaBancoReg0410 VBReg0410;
    private ValidaBancoReg0420 VBReg0420;
    private IdentDeclaracaoDao declaracaoDao;
    private BalanceteAnaliticoMensal balanceteMensal;
    private IdentDependenciaDao dependenciaDao;

    public ValidaBancoModuloContabilImpl()
    {
    }

    public void executar()
        throws Exception
    {
        VBReg0000.executar();
        VBReg0400.executar();
        VBReg0410.executar();
        VBReg0420.executar();
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

    public ValidaBancoReg0410 getVBReg0410()
    {
        return VBReg0410;
    }

    public void setVBReg0410(ValidaBancoReg0410 VBReg0410)
    {
        this.VBReg0410 = VBReg0410;
    }

    public BalanceteAnaliticoMensal getBalanceteMensal()
    {
        return balanceteMensal;
    }

    public void setBalanceteMensal(BalanceteAnaliticoMensal balanceteMensal)
    {
        this.balanceteMensal = balanceteMensal;
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

    public ValidaBancoReg0420 getVBReg0420()
    {
        return VBReg0420;
    }

    public void setVBReg0420(ValidaBancoReg0420 VBReg0420)
    {
        this.VBReg0420 = VBReg0420;
    }
}
