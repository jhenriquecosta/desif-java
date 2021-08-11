
package br.gov.pbh.desif.model.pojo;

import br.gov.pbh.desif.model.pojo.base.AbstractRelatorioApuracaoIssqnVO;

public class RelatorioApuracaoIssqnVO extends AbstractRelatorioApuracaoIssqnVO
{

    public RelatorioApuracaoIssqnVO()
    {
    }

    public RelatorioApuracaoIssqnVO(String codigoTributacaoDesIf, Double valorReceitaDeclaradaConsolidada, Double valorDeducaoReceitaDeclaradaSubtitulo, Double valorDeducaoReceitaDeclaradaConsolidada, Double valorBaseCalculo, Double valorAliquotaIssqn, Double valorIssqnDevido, 
            Double valorIssqnRetido, Double valorIncentivoFiscalSubtitulo, Double valorIncentivoFiscal, Double valorCredito, Double valorIssqnRecolhido, String processoMotivoNaoExigibilidade, Double valorIssqnRecolher)
    {
        super(codigoTributacaoDesIf, valorReceitaDeclaradaConsolidada, valorDeducaoReceitaDeclaradaSubtitulo, valorDeducaoReceitaDeclaradaConsolidada, valorBaseCalculo, valorAliquotaIssqn, valorIssqnDevido, valorIssqnRetido, valorIncentivoFiscalSubtitulo, valorIncentivoFiscal, valorCredito, valorIssqnRecolhido, processoMotivoNaoExigibilidade, valorIssqnRecolher);
    }
}