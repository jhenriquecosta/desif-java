

package br.gov.pbh.desif.model.pojo.base;

import java.io.Serializable;

public abstract class AbstractEventosContabeis
    implements Serializable
{

    private int id;
    private String descricaoEvento;

    public AbstractEventosContabeis()
    {
    }

    public AbstractEventosContabeis(int id, String descricaoEvento)
    {
        this.id = id;
        this.descricaoEvento = descricaoEvento;
    }

    public String getDescricaoEvento()
    {
        return descricaoEvento;
    }

    public void setDescricaoEvento(String descricaoEvento)
    {
        this.descricaoEvento = descricaoEvento;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
}