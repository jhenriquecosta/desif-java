/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.service.registrobanco.impl;

import br.gov.pbh.desif.dao.ApuracaoReceitaDao;
import br.gov.pbh.desif.dao.IdentDeclaracaoDao;
import br.gov.pbh.desif.dao.IdentDependenciaDao;
import br.gov.pbh.desif.model.pojo.IdentificacaoDeclaracao;
import br.gov.pbh.desif.model.pojo.IdentificacaoDependencia;
import br.gov.pbh.desif.service.contexto.Contexto;
import br.gov.pbh.desif.service.registrobanco.ValidaBancoApuracaoMensalIssqn;
import br.gov.pbh.desif.service.registrobanco.ValidaBancoReg0000;
import br.gov.pbh.desif.service.registrobanco.ValidaBancoReg0400;
import br.gov.pbh.desif.service.registrobanco.ValidaBancoReg0430;
import br.gov.pbh.desif.service.registrobanco.ValidaBancoReg0440;
import java.io.Serializable;
import java.util.List;

public class ValidaBancoApuracaoMensalIssqnImpl
implements ValidaBancoApuracaoMensalIssqn {
    private ValidaBancoReg0000 VBReg0000;
    private ValidaBancoReg0400 VBReg0400;
    private ValidaBancoReg0430 VBReg0430;
    private ValidaBancoReg0440 VBReg0440;
    private IdentDeclaracaoDao declaracaoDao;
    private ApuracaoReceitaDao apuracaoMensalIssqnDao;
    private IdentDependenciaDao dependenciaDao;

    @Override
    public void executar() throws Exception {
        this.updateCnpj();
        this.VBReg0000.executar();
        this.VBReg0400.executar();
        this.VBReg0430.executar();
        this.VBReg0440.executar();
    }

    public void updateCnpj() throws Exception {
        IdentificacaoDeclaracao declaracao = (IdentificacaoDeclaracao)this.declaracaoDao.load(new Integer(Integer.parseInt("1")));
        if (declaracao.getTipoConsolidacao().intValue() == 1 || declaracao.getTipoConsolidacao().intValue() == 2) {
            this.apuracaoMensalIssqnDao.updateAll(declaracao.getCnpjResponsavelRecolhimento());
        } else if (declaracao.getTipoConsolidacao().intValue() == 3 || declaracao.getTipoConsolidacao().intValue() == 4) {
            this.dependenciaDao = (IdentDependenciaDao)Contexto.getObject("dependenciaDao");
            List<IdentificacaoDependencia> dependencias = this.dependenciaDao.findAll();
            for (IdentificacaoDependencia dependencia : dependencias)
            {
                this.apuracaoMensalIssqnDao.updateCodDependencia(dependencia.getCnpjUnificado(), dependencia.getCodigoDependencia());
            }
        }
    }

    public ValidaBancoReg0000 getVBReg0000() {
        return this.VBReg0000;
    }

    public void setVBReg0000(ValidaBancoReg0000 VBReg0000) {
        this.VBReg0000 = VBReg0000;
    }

    public ValidaBancoReg0400 getVBReg0400() {
        return this.VBReg0400;
    }

    public void setVBReg0400(ValidaBancoReg0400 VBReg0400) {
        this.VBReg0400 = VBReg0400;
    }

    public ValidaBancoReg0430 getVBReg0430() {
        return this.VBReg0430;
    }

    public void setVBReg0430(ValidaBancoReg0430 VBReg0430) {
        this.VBReg0430 = VBReg0430;
    }

    public ValidaBancoReg0440 getVBReg0440() {
        return this.VBReg0440;
    }

    public void setVBReg0440(ValidaBancoReg0440 VBReg0440) {
        this.VBReg0440 = VBReg0440;
    }

    public ApuracaoReceitaDao getApuracaoMensalIssqnDao() {
        return this.apuracaoMensalIssqnDao;
    }

    public void setApuracaoMensalIssqnDao(ApuracaoReceitaDao apuracaoMensalIssqnDao) {
        this.apuracaoMensalIssqnDao = apuracaoMensalIssqnDao;
    }

    public IdentDeclaracaoDao getDeclaracaoDao() {
        return this.declaracaoDao;
    }

    public void setDeclaracaoDao(IdentDeclaracaoDao declaracaoDao) {
        this.declaracaoDao = declaracaoDao;
    }

    public IdentDependenciaDao getDependenciaDao() {
        return this.dependenciaDao;
    }

    public void setDependenciaDao(IdentDependenciaDao dependenciaDao) {
        this.dependenciaDao = dependenciaDao;
    }
}

