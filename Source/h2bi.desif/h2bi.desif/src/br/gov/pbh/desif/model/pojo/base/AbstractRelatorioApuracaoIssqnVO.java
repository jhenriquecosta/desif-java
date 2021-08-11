

package br.gov.pbh.desif.model.pojo.base;

import java.io.Serializable;

public abstract class AbstractRelatorioApuracaoIssqnVO
    implements Serializable
{

    private String codigoTributacaoDesIf;
    private Double valorReceitaDeclaradaConsolidada;
    private Double valorDeducaoReceitaDeclaradaSubtitulo;
    private Double valorDeducaoReceitaDeclaradaConsolidada;
    private Double valorBaseCalculo;
    private Double valorAliquotaIssqn;
    private Double valorIssqnDevido;
    private Double valorIssqnRetido;
    private Double valorIncentivoFiscalSubtitulo;
    private Double valorIncentivoFiscal;
    private Double valorCredito;
    private Double valorIssqnRecolhido;
    private String processoMotivoNaoExigibilidade;
    private Double valorIssqnRecolher;

    public AbstractRelatorioApuracaoIssqnVO()
    {
    }

    public AbstractRelatorioApuracaoIssqnVO(String codigoTributacaoDesIf, Double valorReceitaDeclaradaConsolidada, Double valorDeducaoReceitaDeclaradaSubtitulo, Double valorDeducaoReceitaDeclaradaConsolidada, Double valorBaseCalculo, Double valorAliquotaIssqn, Double valorIssqnDevido, 
            Double valorIssqnRetido, Double valorIncentivoFiscalSubtitulo, Double valorIncentivoFiscal, Double valorCredito, Double valorIssqnRecolhido, String processoMotivoNaoExigibilidade, Double valorIssqnRecolher)
    {
        this.codigoTributacaoDesIf = codigoTributacaoDesIf;
        this.valorReceitaDeclaradaConsolidada = valorReceitaDeclaradaConsolidada;
        this.valorDeducaoReceitaDeclaradaSubtitulo = valorDeducaoReceitaDeclaradaSubtitulo;
        this.valorDeducaoReceitaDeclaradaConsolidada = valorDeducaoReceitaDeclaradaConsolidada;
        this.valorBaseCalculo = valorBaseCalculo;
        this.valorAliquotaIssqn = valorAliquotaIssqn;
        this.valorIssqnDevido = valorIssqnDevido;
        this.valorIssqnRetido = valorIssqnRetido;
        this.valorIncentivoFiscalSubtitulo = valorIncentivoFiscalSubtitulo;
        this.valorIncentivoFiscal = valorIncentivoFiscal;
        this.valorCredito = valorCredito;
        this.valorIssqnRecolhido = valorIssqnRecolhido;
        this.processoMotivoNaoExigibilidade = processoMotivoNaoExigibilidade;
        this.valorIssqnRecolher = valorIssqnRecolher;
    }

    public String getCodigoTributacaoDesIf()
    {
        return codigoTributacaoDesIf;
    }

    public void setCodigoTributacaoDesIf(String codigoTributacaoDesIf)
    {
        this.codigoTributacaoDesIf = codigoTributacaoDesIf;
    }

    public String getProcessoMotivoNaoExigibilidade()
    {
        return processoMotivoNaoExigibilidade;
    }

    public void setProcessoMotivoNaoExigibilidade(String processoMotivoNaoExigibilidade)
    {
        this.processoMotivoNaoExigibilidade = processoMotivoNaoExigibilidade;
    }

    public Double getValorAliquotaIssqn()
    {
        return valorAliquotaIssqn;
    }

    public void setValorAliquotaIssqn(Double valorAliquotaIssqn)
    {
        this.valorAliquotaIssqn = valorAliquotaIssqn;
    }

    public Double getValorBaseCalculo()
    {
        return valorBaseCalculo;
    }

    public void setValorBaseCalculo(Double valorBaseCalculo)
    {
        this.valorBaseCalculo = valorBaseCalculo;
    }

    public Double getValorCredito()
    {
        return valorCredito;
    }

    public void setValorCredito(Double valorCredito)
    {
        this.valorCredito = valorCredito;
    }

    public Double getValorDeducaoReceitaDeclaradaConsolidada()
    {
        return valorDeducaoReceitaDeclaradaConsolidada;
    }

    public void setValorDeducaoReceitaDeclaradaConsolidada(Double valorDeducaoReceitaDeclaradaConsolidada)
    {
        this.valorDeducaoReceitaDeclaradaConsolidada = valorDeducaoReceitaDeclaradaConsolidada;
    }

    public Double getValorDeducaoReceitaDeclaradaSubtitulo()
    {
        return valorDeducaoReceitaDeclaradaSubtitulo;
    }

    public void setValorDeducaoReceitaDeclaradaSubtitulo(Double valorDeducaoReceitaDeclaradaSubtitulo)
    {
        this.valorDeducaoReceitaDeclaradaSubtitulo = valorDeducaoReceitaDeclaradaSubtitulo;
    }

    public Double getValorIncentivoFiscal()
    {
        return valorIncentivoFiscal;
    }

    public void setValorIncentivoFiscal(Double valorIncentivoFiscal)
    {
        this.valorIncentivoFiscal = valorIncentivoFiscal;
    }

    public Double getValorIncentivoFiscalSubtitulo()
    {
        return valorIncentivoFiscalSubtitulo;
    }

    public void setValorIncentivoFiscalSubtitulo(Double valorIncentivoFiscalSubtitulo)
    {
        this.valorIncentivoFiscalSubtitulo = valorIncentivoFiscalSubtitulo;
    }

    public Double getValorIssqnDevido()
    {
        return valorIssqnDevido;
    }

    public void setValorIssqnDevido(Double valorIssqnDevido)
    {
        this.valorIssqnDevido = valorIssqnDevido;
    }

    public Double getValorIssqnRecolher()
    {
        return valorIssqnRecolher;
    }

    public void setValorIssqnRecolher(Double valorIssqnRecolher)
    {
        this.valorIssqnRecolher = valorIssqnRecolher;
    }

    public Double getValorIssqnRecolhido()
    {
        return valorIssqnRecolhido;
    }

    public void setValorIssqnRecolhido(Double valorIssqnRecolhido)
    {
        this.valorIssqnRecolhido = valorIssqnRecolhido;
    }

    public Double getValorIssqnRetido()
    {
        return valorIssqnRetido;
    }

    public void setValorIssqnRetido(Double valorIssqnRetido)
    {
        this.valorIssqnRetido = valorIssqnRetido;
    }

    public Double getValorReceitaDeclaradaConsolidada()
    {
        return valorReceitaDeclaradaConsolidada;
    }

    public void setValorReceitaDeclaradaConsolidada(Double valorReceitaDeclaradaConsolidada)
    {
        this.valorReceitaDeclaradaConsolidada = valorReceitaDeclaradaConsolidada;
    }
}