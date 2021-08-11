

package br.gov.pbh.desif.model.pojo.base;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractCidade
    implements Serializable
{

    private long id;
    private String nomeCidade;
    private String uf;
    private Set codTribuMunicipals;

    public AbstractCidade()
    {
        codTribuMunicipals = new HashSet(0);
    }

    public AbstractCidade(Long id)
    {
        codTribuMunicipals = new HashSet(0);
        this.id = id.longValue();
    }

    public AbstractCidade(long id, String nomeCidade, String uf)
    {
        codTribuMunicipals = new HashSet(0);
        this.id = id;
        this.nomeCidade = nomeCidade;
        this.uf = uf;
    }

    public AbstractCidade(long id, String nomeCidade, String uf, Set codTribuMunicipals)
    {
        this.codTribuMunicipals = new HashSet(0);
        this.id = id;
        this.nomeCidade = nomeCidade;
        this.uf = uf;
        this.codTribuMunicipals = codTribuMunicipals;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getNomeCidade()
    {
        return nomeCidade;
    }

    public void setNomeCidade(String nomeCidade)
    {
        this.nomeCidade = nomeCidade;
    }

    public String getUf()
    {
        return uf;
    }

    public void setUf(String uf)
    {
        this.uf = uf;
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