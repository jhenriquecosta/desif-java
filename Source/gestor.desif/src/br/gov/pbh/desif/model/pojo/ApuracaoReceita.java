/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.pojo;

import br.gov.pbh.desif.model.pojo.base.AbstractApuracaoReceita;
import java.io.Serializable;

public class ApuracaoReceita
extends AbstractApuracaoReceita
implements Serializable {
    private static final long serialVersionUID = -1598026028797439331L;

    public ApuracaoReceita() {
    }

    public ApuracaoReceita(Long id, String codigoTributacaoDesIf, Long linhaApuracaoReceita, String codigoDependencia, String codigoSubTitulo, Double valorCreditoMensal, Double valorDebitoMensal, Double valorReceitaDeclarada, Double valorBaseCalculo, Double valorAliquotaIssqn, Double valorDeducaoReceitaDeclarada, String DescDeducao, Double valorIncentivoFiscal, String descIncentivoFiscal, Short motivoNaoExigibilidade, String processoMotivoNaoExigibilidade, String cnpj) {
        super(id, codigoTributacaoDesIf, linhaApuracaoReceita, codigoDependencia, codigoSubTitulo, valorCreditoMensal, valorDebitoMensal, valorReceitaDeclarada, valorBaseCalculo, valorAliquotaIssqn, valorDeducaoReceitaDeclarada, DescDeducao, valorIncentivoFiscal, descIncentivoFiscal, motivoNaoExigibilidade, processoMotivoNaoExigibilidade, cnpj);
    }

    public ApuracaoReceita(String codigoTributacaoDesIf, String codigoSubTitulo, Double valorCreditoMensal, Double valorDebitoMensal, Double valorReceitaDeclarada, Double valorBaseCalculo, Double valorAliquotaIssqn, Double valorDeducaoReceitaDeclarada, Double valorIncentivoFiscal, String descIncentivoFiscal, String processoMotivoNaoExigibilidade) {
        super(codigoTributacaoDesIf, codigoSubTitulo, valorCreditoMensal, valorDebitoMensal, valorReceitaDeclarada, valorBaseCalculo, valorAliquotaIssqn, valorDeducaoReceitaDeclarada, valorIncentivoFiscal, descIncentivoFiscal, processoMotivoNaoExigibilidade);
    }
}

