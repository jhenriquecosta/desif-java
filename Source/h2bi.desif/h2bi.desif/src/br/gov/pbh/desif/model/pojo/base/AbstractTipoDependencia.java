

package br.gov.pbh.desif.model.pojo.base;

import java.io.Serializable;

public abstract class AbstractTipoDependencia
    implements Serializable
{

    private Integer id;
    private String descTipoDependencia;

    public AbstractTipoDependencia()
    {
    }

    public AbstractTipoDependencia(Integer id)
    {
        this.id = id;
    }

    public AbstractTipoDependencia(Integer id, String descTipoDependencia)
    {
        this.id = id;
        this.descTipoDependencia = descTipoDependencia;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getDescTipoDependencia()
    {
        return descTipoDependencia;
    }

    public void setDescTipoDependencia(String descTipoDependencia)
    {
        this.descTipoDependencia = descTipoDependencia;
    }
}