

package br.gov.pbh.desif.model.pojo.base;

import br.gov.pbh.desif.model.pojo.ListaServico;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractCodigoTributacaoDesif
    implements Serializable
{

    private String id;
    private ListaServico listaServico;
    private String descCodigoTributacao;
    private Set codTribuMunicipals;

    public AbstractCodigoTributacaoDesif()
    {
        codTribuMunicipals = new HashSet(0);
    }

    public AbstractCodigoTributacaoDesif(String id)
    {
        codTribuMunicipals = new HashSet(0);
        this.id = id;
    }

    public AbstractCodigoTributacaoDesif(String id, ListaServico listaServico, String descCodigoTributacao)
    {
        codTribuMunicipals = new HashSet(0);
        this.id = id;
        this.listaServico = listaServico;
        this.descCodigoTributacao = descCodigoTributacao;
    }

    public AbstractCodigoTributacaoDesif(String id, ListaServico listaServico, String descCodigoTributacao, Set codTribuMunicipals)
    {
        this.codTribuMunicipals = new HashSet(0);
        this.id = id;
        this.listaServico = listaServico;
        this.descCodigoTributacao = descCodigoTributacao;
        this.codTribuMunicipals = codTribuMunicipals;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public ListaServico getListaServico()
    {
        return listaServico;
    }

    public void setListaServico(ListaServico listaServico)
    {
        this.listaServico = listaServico;
    }

    public String getDescCodigoTributacao()
    {
        return descCodigoTributacao;
    }

    public void setDescCodigoTributacao(String descCodigoTributacao)
    {
        this.descCodigoTributacao = descCodigoTributacao;
    }

    public Set getCodTribuMunicipals()
    {
        return codTribuMunicipals;
    }

    public void setCodTribuMunicipals(Set codTribuMunicipals)
    {
        this.codTribuMunicipals = codTribuMunicipals;
    }
}