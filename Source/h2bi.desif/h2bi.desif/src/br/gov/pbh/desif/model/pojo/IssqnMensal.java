
package br.gov.pbh.desif.model.pojo;

import br.gov.pbh.desif.model.pojo.base.AbstractIssqnMensal;
import java.io.Serializable;
import java.util.Set;

public class IssqnMensal extends AbstractIssqnMensal
    implements Serializable
{

    public IssqnMensal()
    {
    }

    public IssqnMensal(Long id, Long linhaIssqnMensal, String cnpj, Double valorReceitaDeclaradaConsolidada, Double valorBaseCalculo, Double valorAliquotaIssqn, Double valorIssqnDevido, 
            String codigoTributacaoDesIf, Double valorDeducaoReceitaDeclaradaSubtitulo, Double valorDeducaoReceitaDeclaradaConsolidada, String descDeducao, Double valorIssqnRetido, Double valorIncentivoFiscalSubtitulo, Double valorIncentivoFiscal, 
            Double valorIssqnRecolhido, Double valorIssqnRecolher, String descIncentivoFiscal, Double valorCredito, Short motivoNaoExigibilidade, String processoMotivoNaoExigibilidade, Set origemCredCompensars)
    {
        super(id, linhaIssqnMensal, cnpj, valorReceitaDeclaradaConsolidada, valorBaseCalculo, valorAliquotaIssqn, valorIssqnDevido, codigoTributacaoDesIf, valorDeducaoReceitaDeclaradaSubtitulo, valorDeducaoReceitaDeclaradaConsolidada, descDeducao, valorIssqnRetido, valorIncentivoFiscalSubtitulo, valorIncentivoFiscal, valorIssqnRecolhido, valorIssqnRecolher, descIncentivoFiscal, valorCredito, motivoNaoExigibilidade, processoMotivoNaoExigibilidade, origemCredCompensars);
    }

    public IssqnMensal(String cnpj, Double valorReceitaDeclaradaConsolidada, Double valorBaseCalculo, Double valorIssqnDevido, Double valorDeducaoReceitaDeclaradaSubtitulo, Double valorDeducaoReceitaDeclaradaConsolidada, Double valorIssqnRetido, 
            Double valorIncentivoFiscalSubtitulo, Double valorIncentivoFiscal, Double valorIssqnRecolhido, Double valorIssqnRecolher, Double valorCredito)
    {
        super(cnpj, valorReceitaDeclaradaConsolidada, valorBaseCalculo, valorIssqnDevido, valorDeducaoReceitaDeclaradaSubtitulo, valorDeducaoReceitaDeclaradaConsolidada, valorIssqnRetido, valorIncentivoFiscalSubtitulo, valorIncentivoFiscal, valorIssqnRecolhido, valorIssqnRecolher, valorCredito);
    }
}