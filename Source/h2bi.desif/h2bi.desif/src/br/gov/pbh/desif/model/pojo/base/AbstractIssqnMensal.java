
package br.gov.pbh.desif.model.pojo.base;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractIssqnMensal
    implements Serializable
{

    private String baseCnpj;
    private Long id;
    private Long linhaIssqnMensal;
    private String cnpj;
    private Double valorReceitaDeclaradaConsolidada;
    private Double valorBaseCalculo;
    private Double valorAliquotaIssqn;
    private Double valorIssqnDevido;
    private String codigoTributacaoDesIf;
    private Double valorDeducaoReceitaDeclaradaSubtitulo;
    private Double valorDeducaoReceitaDeclaradaConsolidada;
    private String descDeducao;
    private Double valorIssqnRetido;
    private Double valorIncentivoFiscalSubtitulo;
    private Double valorIncentivoFiscal;
    private Double valorIssqnRecolhido;
    private Double valorIssqnRecolher;
    private String descIncentivoFiscal;
    private Double valorCredito;
    private Short motivoNaoExigibilidade;
    private String processoMotivoNaoExigibilidade;
    private Set origemCredCompensars;

    public AbstractIssqnMensal()
    {
        origemCredCompensars = new HashSet(0);
    }

    public AbstractIssqnMensal(Long id, Long linhaIssqnMensal, String cnpj, Double valorReceitaDeclaradaConsolidada, Double valorBaseCalculo, Double valorAliquotaIssqn, Double valorIssqnDevido, 
            String codigoTributacaoDesIf, Double valorDeducaoReceitaDeclaradaSubtitulo, Double valorDeducaoReceitaDeclaradaConsolidada, String descDeducao, Double valorIssqnRetido, Double valorIncentivoFiscalSubtitulo, Double valorIncentivoFiscal, 
            Double valorIssqnRecolhido, Double valorIssqnRecolher, String descIncentivoFiscal, Double valorCredito, Short motivoNaoExigibilidade, String processoMotivoNaoExigibilidade, Set origemCredCompensars)
    {
        this.origemCredCompensars = new HashSet(0);
        this.id = id;
        this.codigoTributacaoDesIf = codigoTributacaoDesIf;
        this.linhaIssqnMensal = linhaIssqnMensal;
        this.cnpj = cnpj;
        this.valorReceitaDeclaradaConsolidada = valorReceitaDeclaradaConsolidada;
        this.valorBaseCalculo = valorBaseCalculo;
        this.valorAliquotaIssqn = valorAliquotaIssqn;
        this.valorIssqnDevido = valorIssqnDevido;
        this.valorDeducaoReceitaDeclaradaSubtitulo = valorDeducaoReceitaDeclaradaSubtitulo;
        this.valorDeducaoReceitaDeclaradaConsolidada = valorDeducaoReceitaDeclaradaConsolidada;
        this.descDeducao = descDeducao;
        this.valorIssqnRetido = valorIssqnRetido;
        this.valorIncentivoFiscalSubtitulo = valorIncentivoFiscalSubtitulo;
        this.valorIncentivoFiscal = valorIncentivoFiscal;
        this.valorIssqnRecolhido = valorIssqnRecolhido;
        this.valorIssqnRecolher = valorIssqnRecolher;
        this.descIncentivoFiscal = descIncentivoFiscal;
        this.valorCredito = valorCredito;
        this.motivoNaoExigibilidade = motivoNaoExigibilidade;
        this.processoMotivoNaoExigibilidade = processoMotivoNaoExigibilidade;
        this.origemCredCompensars = origemCredCompensars;
    }

    public AbstractIssqnMensal(String cnpj, Double valorReceitaDeclaradaConsolidada, Double valorBaseCalculo, Double valorIssqnDevido, Double valorDeducaoReceitaDeclaradaSubtitulo, Double valorDeducaoReceitaDeclaradaConsolidada, Double valorIssqnRetido, 
            Double valorIncentivoFiscalSubtitulo, Double valorIncentivoFiscal, Double valorIssqnRecolhido, Double valorIssqnRecolher, Double valorCredito)
    {
        origemCredCompensars = new HashSet(0);
        this.cnpj = cnpj;
        this.valorReceitaDeclaradaConsolidada = valorReceitaDeclaradaConsolidada;
        this.valorBaseCalculo = valorBaseCalculo;
        this.valorIssqnDevido = valorIssqnDevido;
        this.valorDeducaoReceitaDeclaradaSubtitulo = valorDeducaoReceitaDeclaradaSubtitulo;
        this.valorDeducaoReceitaDeclaradaConsolidada = valorDeducaoReceitaDeclaradaConsolidada;
        this.valorIssqnRetido = valorIssqnRetido;
        this.valorIncentivoFiscalSubtitulo = valorIncentivoFiscalSubtitulo;
        this.valorIncentivoFiscal = valorIncentivoFiscal;
        this.valorIssqnRecolhido = valorIssqnRecolhido;
        this.valorIssqnRecolher = valorIssqnRecolher;
        this.valorCredito = valorCredito;
    }

    public String getBaseCnpj()
    {
        return baseCnpj;
    }

    public void setBaseCnpj(String baseCnpj)
    {
        this.baseCnpj = baseCnpj;
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

    public Long getLinhaIssqnMensal()
    {
        return linhaIssqnMensal;
    }

    public void setLinhaIssqnMensal(Long linhaIssqnMensal)
    {
        this.linhaIssqnMensal = linhaIssqnMensal;
    }

    public String getCnpj()
    {
        return cnpj;
    }

    public void setCnpj(String cnpj)
    {
        this.cnpj = cnpj;
    }

    public Double getValorDeducaoReceitaDeclaradaConsolidada()
    {
        return valorDeducaoReceitaDeclaradaConsolidada;
    }

    public void setValorDeducaoReceitaDeclaradaConsolidada(Double valorDeducaoReceitaDeclaradaConsolidada)
    {
        this.valorDeducaoReceitaDeclaradaConsolidada = valorDeducaoReceitaDeclaradaConsolidada;
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

    public Double getValorIssqnDevido()
    {
        return valorIssqnDevido;
    }

    public void setValorIssqnDevido(Double valorIssqnDevido)
    {
        this.valorIssqnDevido = valorIssqnDevido;
    }

    public Double getValorDeducaoReceitaDeclaradaSubtitulo()
    {
        return valorDeducaoReceitaDeclaradaSubtitulo;
    }

    public void setValorDeducaoReceitaDeclaradaSubtitulo(Double valorDeducaoReceitaDeclaradaSubtitulo)
    {
        this.valorDeducaoReceitaDeclaradaSubtitulo = valorDeducaoReceitaDeclaradaSubtitulo;
    }

    public Double getValorReceitaDeclaradaConsolidada()
    {
        return valorReceitaDeclaradaConsolidada;
    }

    public void setValorReceitaDeclaradaConsolidada(Double valorReceitaDeclaradaConsolidada)
    {
        this.valorReceitaDeclaradaConsolidada = valorReceitaDeclaradaConsolidada;
    }

    public String getDescDeducao()
    {
        return descDeducao;
    }

    public void setDescDeducao(String descDeducao)
    {
        this.descDeducao = descDeducao;
    }

    public Double getValorIssqnRetido()
    {
        return valorIssqnRetido;
    }

    public void setValorIssqnRetido(Double valorIssqnRetido)
    {
        this.valorIssqnRetido = valorIssqnRetido;
    }

    public Double getValorIncentivoFiscalSubtitulo()
    {
        return valorIncentivoFiscalSubtitulo;
    }

    public void setValorIncentivoFiscalSubtitulo(Double valorIncentivoFiscalSubtitulo)
    {
        this.valorIncentivoFiscalSubtitulo = valorIncentivoFiscalSubtitulo;
    }

    public Double getValorIncentivoFiscal()
    {
        return valorIncentivoFiscal;
    }

    public void setValorIncentivoFiscal(Double valorIncentivoFiscal)
    {
        this.valorIncentivoFiscal = valorIncentivoFiscal;
    }

    public Double getValorIssqnRecolhido()
    {
        return valorIssqnRecolhido;
    }

    public void setValorIssqnRecolhido(Double valorIssqnRecolhido)
    {
        this.valorIssqnRecolhido = valorIssqnRecolhido;
    }

    public Double getValorIssqnRecolher()
    {
        return valorIssqnRecolher;
    }

    public void setValorIssqnRecolher(Double valorIssqnRecolher)
    {
        this.valorIssqnRecolher = valorIssqnRecolher;
    }

    public String getDescIncentivoFiscal()
    {
        return descIncentivoFiscal;
    }

    public void setDescIncentivoFiscal(String descIncentivoFiscal)
    {
        this.descIncentivoFiscal = descIncentivoFiscal;
    }

    public Double getValorCredito()
    {
        return valorCredito;
    }

    public void setValorCredito(Double valorCredito)
    {
        this.valorCredito = valorCredito;
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

    public Set getOrigemCredCompensars()
    {
        return origemCredCompensars;
    }

    public void setOrigemCredCompensars(Set origemCredCompensars)
    {
        this.origemCredCompensars = origemCredCompensars;
    }
}