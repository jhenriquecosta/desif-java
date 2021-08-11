

package br.gov.pbh.desif.model.pojo.base;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractSolucao
    implements Serializable
{

    private String id;
    private String descSolucao;
    private Set erroSolucaos;

    public AbstractSolucao()
    {
        erroSolucaos = new HashSet(0);
    }

    public AbstractSolucao(String id, String descSolucao)
    {
        erroSolucaos = new HashSet(0);
        this.id = id;
        this.descSolucao = descSolucao;
    }

    public AbstractSolucao(String id, String descSolucao, Set erroSolucaos)
    {
        this.erroSolucaos = new HashSet(0);
        this.id = id;
        this.descSolucao = descSolucao;
        this.erroSolucaos = erroSolucaos;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getDescSolucao()
    {
        return descSolucao;
    }

    public void setDescSolucao(String descSolucao)
    {
        this.descSolucao = descSolucao;
    }

    public Set getErroSolucaos()
    {
        return erroSolucaos;
    }

    public void setErroSolucaos(Set erroSolucaos)
    {
        this.erroSolucaos = erroSolucaos;
    }
}