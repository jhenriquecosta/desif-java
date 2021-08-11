

package br.gov.pbh.desif.model.pojo.base;

import java.io.Serializable;
import java.util.Date;

public abstract class AbstractIdentificacaoDependencia
    implements Serializable
{

    private Long id;
    private Integer tipoDependencia;
    private Long cidade;
    private Long linhaIdentificacaoDependencia;
    private String codigoDependencia;
    private Short opcaoInscricaoMunicipal;
    private Short contabilidadePropria;
    private String cnpjUnificado;
    private String cnpjProprio;
    private String enderecoDependencia;
    private Date dataInicioParalizacao;
    private Date dataFimParalizacao;

    public AbstractIdentificacaoDependencia()
    {
    }

    public AbstractIdentificacaoDependencia(Long id, Integer tipoDependencia, Long cidade, Long linhaIdentificacaoDependencia, String codigoDependencia, Short opcaoInscricaoMunicipal, Short contabilidadePropria, 
            String cnpjUnificado, String cnpjProprio, String enderecoDependencia, Date dataInicioParalizacao, Date dataFimParalizacao)
    {
        this.id = id;
        this.tipoDependencia = tipoDependencia;
        this.cidade = cidade;
        this.linhaIdentificacaoDependencia = linhaIdentificacaoDependencia;
        this.codigoDependencia = codigoDependencia;
        this.opcaoInscricaoMunicipal = opcaoInscricaoMunicipal;
        this.cnpjProprio = cnpjProprio;
        this.contabilidadePropria = contabilidadePropria;
        this.enderecoDependencia = enderecoDependencia;
        this.cnpjUnificado = cnpjUnificado;
        this.dataInicioParalizacao = dataInicioParalizacao;
        this.dataFimParalizacao = dataFimParalizacao;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Integer getTipoDependencia()
    {
        return tipoDependencia;
    }

    public void setTipoDependencia(Integer tipoDependencia)
    {
        this.tipoDependencia = tipoDependencia;
    }

    public Long getCidade()
    {
        return cidade;
    }

    public void setCidade(Long cidade)
    {
        this.cidade = cidade;
    }

    public Long getLinhaIdentificacaoDependencia()
    {
        return linhaIdentificacaoDependencia;
    }

    public void setLinhaIdentificacaoDependencia(Long linhaIdentificacaoDependencia)
    {
        this.linhaIdentificacaoDependencia = linhaIdentificacaoDependencia;
    }

    public String getCodigoDependencia()
    {
        return codigoDependencia;
    }

    public void setCodigoDependencia(String codigoDependencia)
    {
        this.codigoDependencia = codigoDependencia;
    }

    public Short getOpcaoInscricaoMunicipal()
    {
        return opcaoInscricaoMunicipal;
    }

    public void setOpcaoInscricaoMunicipal(Short opcaoInscricaoMunicipal)
    {
        this.opcaoInscricaoMunicipal = opcaoInscricaoMunicipal;
    }

    public String getCnpjProprio()
    {
        return cnpjProprio;
    }

    public void setCnpjProprio(String cnpjProprio)
    {
        this.cnpjProprio = cnpjProprio;
    }

    public Short getContabilidadePropria()
    {
        return contabilidadePropria;
    }

    public void setContabilidadePropria(Short contabilidadePropria)
    {
        this.contabilidadePropria = contabilidadePropria;
    }

    public String getEnderecoDependencia()
    {
        return enderecoDependencia;
    }

    public void setEnderecoDependencia(String enderecoDependencia)
    {
        this.enderecoDependencia = enderecoDependencia;
    }

    public String getCnpjUnificado()
    {
        return cnpjUnificado;
    }

    public void setCnpjUnificado(String cnpjUnificado)
    {
        this.cnpjUnificado = cnpjUnificado;
    }

    public Date getDataInicioParalizacao()
    {
        return dataInicioParalizacao;
    }

    public void setDataInicioParalizacao(Date dataInicioParalizacao)
    {
        this.dataInicioParalizacao = dataInicioParalizacao;
    }

    public Date getDataFimParalizacao()
    {
        return dataFimParalizacao;
    }

    public void setDataFimParalizacao(Date dataFimParalizacao)
    {
        this.dataFimParalizacao = dataFimParalizacao;
    }
}