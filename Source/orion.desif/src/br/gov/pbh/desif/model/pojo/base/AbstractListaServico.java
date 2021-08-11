

package br.gov.pbh.desif.model.pojo.base;

import java.io.Serializable;
import java.util.*;

public abstract class AbstractListaServico
    implements Serializable
{

    private String id;
    private String nomeListaServico;
    private Date dataInicio;
    private Date dataFim;
    private Set codTributacaos;

    public AbstractListaServico()
    {
        codTributacaos = new HashSet(0);
    }

    public AbstractListaServico(String id, String nomeListaServico, Date dataInicio)
    {
        codTributacaos = new HashSet(0);
        this.id = id;
        this.nomeListaServico = nomeListaServico;
        this.dataInicio = dataInicio;
    }

    public AbstractListaServico(String id, String nomeListaServico, Date dataInicio, Date dataFim, Set codTributacaos)
    {
        this.codTributacaos = new HashSet(0);
        this.id = id;
        this.nomeListaServico = nomeListaServico;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.codTributacaos = codTributacaos;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getNomeListaServico()
    {
        return nomeListaServico;
    }

    public void setNomeListaServico(String nomeListaServico)
    {
        this.nomeListaServico = nomeListaServico;
    }

    public Date getDataInicio()
    {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio)
    {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim()
    {
        return dataFim;
    }

    public void setDataFim(Date dataFim)
    {
        this.dataFim = dataFim;
    }

    public Set getCodTributacaos()
    {
        return codTributacaos;
    }

    public void setCodTributacaos(Set codTributacaos)
    {
        this.codTributacaos = codTributacaos;
    }
}