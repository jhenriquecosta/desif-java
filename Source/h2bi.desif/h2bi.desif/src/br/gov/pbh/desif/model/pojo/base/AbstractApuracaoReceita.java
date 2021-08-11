

package br.gov.pbh.desif.model.pojo.base;

import java.io.Serializable;

public abstract class AbstractApuracaoReceita
    implements Serializable
{

    private Long id;
    private String codigoTributacaoDesIf;
    private Long linhaApuracaoReceita;
    private String codigoDependencia;
    private String codigoSubTitulo;
    private Double valorCreditoMensal;
    private Double valorDebitoMensal;
    private Double valorReceitaDeclarada;
    private Double valorBaseCalculo;
    private Double valorAliquotaIssqn;
    private Double valorDeducaoReceitaDeclarada;
    private String DescDeducao;
    private Double valorIncentivoFiscal;
    private String descIncentivoFiscal;
    private Short motivoNaoExigibilidade;
    private String processoMotivoNaoExigibilidade;
    private String cnpj;

    public AbstractApuracaoReceita()
    {
    }

    public AbstractApuracaoReceita(Long id, String codigoTributacaoDesIf, Long linhaApuracaoReceita, String codigoDependencia, String codigoSubTitulo, Double valorCreditoMensal, Double valorDebitoMensal, 
            Double valorReceitaDeclarada, Double valorBaseCalculo, Double valorAliquotaIssqn, Double valorDeducaoReceitaDeclarada, String DescDeducao, Double valorIncentivoFiscal, String descIncentivoFiscal, 
            Short motivoNaoExigibilidade, String processoMotivoNaoExigibilidade, String cnpj)
    {
        this.id = id;
        this.codigoTributacaoDesIf = codigoTributacaoDesIf;
        this.linhaApuracaoReceita = linhaApuracaoReceita;
        this.codigoDependencia = codigoDependencia;
        this.codigoSubTitulo = codigoSubTitulo;
        this.valorCreditoMensal = valorCreditoMensal;
        this.valorDebitoMensal = valorDebitoMensal;
        this.valorReceitaDeclarada = valorReceitaDeclarada;
        this.valorBaseCalculo = valorBaseCalculo;
        this.valorAliquotaIssqn = valorAliquotaIssqn;
        this.valorDeducaoReceitaDeclarada = valorDeducaoReceitaDeclarada;
        this.DescDeducao = DescDeducao;
        this.valorIncentivoFiscal = valorIncentivoFiscal;
        this.descIncentivoFiscal = descIncentivoFiscal;
        this.motivoNaoExigibilidade = motivoNaoExigibilidade;
        this.processoMotivoNaoExigibilidade = processoMotivoNaoExigibilidade;
        this.cnpj = cnpj;
    }

    public AbstractApuracaoReceita(String codigoTributacaoDesIf, String codigoSubTitulo, Double valorCreditoMensal, Double valorDebitoMensal, Double valorReceitaDeclarada, Double valorBaseCalculo, Double valorAliquotaIssqn, 
            Double valorDeducaoReceitaDeclarada, Double valorIncentivoFiscal, String descIncentivoFiscal, String processoMotivoNaoExigibilidade)
    {
        this.codigoTributacaoDesIf = codigoTributacaoDesIf;
        this.codigoSubTitulo = codigoSubTitulo;
        this.valorCreditoMensal = valorCreditoMensal;
        this.valorDebitoMensal = valorDebitoMensal;
        this.valorReceitaDeclarada = valorReceitaDeclarada;
        this.valorBaseCalculo = valorBaseCalculo;
        this.valorAliquotaIssqn = valorAliquotaIssqn;
        this.valorDeducaoReceitaDeclarada = valorDeducaoReceitaDeclarada;
        this.valorIncentivoFiscal = valorIncentivoFiscal;
        this.descIncentivoFiscal = descIncentivoFiscal;
        this.processoMotivoNaoExigibilidade = processoMotivoNaoExigibilidade;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getCodigoTributacaoDesIf()
    {
        return codigoTributacaoDesIf;
    }

    public void setCodigoTributacaoDesIf(String codigoTributacaoDesIf)
    {
        this.codigoTributacaoDesIf = codigoTributacaoDesIf;
    }

    public Long getLinhaApuracaoReceita()
    {
        return linhaApuracaoReceita;
    }

    public void setLinhaApuracaoReceita(Long linhaApuracaoReceita)
    {
        this.linhaApuracaoReceita = linhaApuracaoReceita;
    }

    public String getCodigoDependencia()
    {
        return codigoDependencia;
    }

    public void setCodigoDependencia(String codigoDependencia)
    {
        this.codigoDependencia = codigoDependencia;
    }

    public String getCodigoSubTitulo()
    {
        return codigoSubTitulo;
    }

    public void setCodigoSubTitulo(String codigoSubTitulo)
    {
        this.codigoSubTitulo = codigoSubTitulo;
    }

    public Double getValorCreditoMensal()
    {
        return valorCreditoMensal;
    }

    public void setValorCreditoMensal(Double valorCreditoMensal)
    {
        this.valorCreditoMensal = valorCreditoMensal;
    }

    public Double getValorDebitoMensal()
    {
        return valorDebitoMensal;
    }

    public void setValorDebitoMensal(Double valorDebitoMensal)
    {
        this.valorDebitoMensal = valorDebitoMensal;
    }

    public Double getValorReceitaDeclarada()
    {
        return valorReceitaDeclarada;
    }

    public void setValorReceitaDeclarada(Double valorReceitaDeclarada)
    {
        this.valorReceitaDeclarada = valorReceitaDeclarada;
    }

    public Double getValorBaseCalculo()
    {
        return valorBaseCalculo;
    }

    public void setValorBaseCalculo(Double valorBaseCalculo)
    {
        this.valorBaseCalculo = valorBaseCalculo;
    }

    public Double getValorAliquotaIssqn()
    {
        return valorAliquotaIssqn;
    }

    public void setValorAliquotaIssqn(Double valorAliquotaIssqn)
    {
        this.valorAliquotaIssqn = valorAliquotaIssqn;
    }

    public Double getValorDeducaoReceitaDeclarada()
    {
        return valorDeducaoReceitaDeclarada;
    }

    public void setValorDeducaoReceitaDeclarada(Double valorDeducaoReceitaDeclarada)
    {
        this.valorDeducaoReceitaDeclarada = valorDeducaoReceitaDeclarada;
    }

    public String getDescDeducao()
    {
        return DescDeducao;
    }

    public void setDescDeducao(String DescDeducao)
    {
        this.DescDeducao = DescDeducao;
    }

    public Double getValorIncentivoFiscal()
    {
        return valorIncentivoFiscal;
    }

    public void setValorIncentivoFiscal(Double valorIncentivoFiscal)
    {
        this.valorIncentivoFiscal = valorIncentivoFiscal;
    }

    public String getDescIncentivoFiscal()
    {
        return descIncentivoFiscal;
    }

    public void setDescIncentivoFiscal(String descIncentivoFiscal)
    {
        this.descIncentivoFiscal = descIncentivoFiscal;
    }

    public Short getMotivoNaoExigibilidade()
    {
        return motivoNaoExigibilidade;
    }

    public void setMotivoNaoExigibilidade(Short motivoNaoExigibilidade)
    {
        this.motivoNaoExigibilidade = motivoNaoExigibilidade;
    }

    public String getProcessoMotivoNaoExigibilidade()
    {
        return processoMotivoNaoExigibilidade;
    }

    public void setProcessoMotivoNaoExigibilidade(String processoMotivoNaoExigibilidade)
    {
        this.processoMotivoNaoExigibilidade = processoMotivoNaoExigibilidade;
    }

    public String getCnpj()
    {
        return cnpj;
    }

    public void setCnpj(String cnpj)
    {
        this.cnpj = cnpj;
    }
}