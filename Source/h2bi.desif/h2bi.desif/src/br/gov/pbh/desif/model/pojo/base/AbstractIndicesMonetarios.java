
package br.gov.pbh.desif.model.pojo.base;

import br.gov.pbh.desif.model.pojo.IndicesMonetariosId;
import java.io.Serializable;

public abstract class AbstractIndicesMonetarios
    implements Serializable
{

    private IndicesMonetariosId id;
    private Double valIndiMone;

    public AbstractIndicesMonetarios()
    {
    }

    public AbstractIndicesMonetarios(IndicesMonetariosId id, Double valIndiMone)
    {
        this.id = id;
        this.valIndiMone = valIndiMone;
    }

    public IndicesMonetariosId getId()
    {
        return id;
    }

    public void setId(IndicesMonetariosId id)
    {
        this.id = id;
    }

    public Double getValIndiMone()
    {
        return valIndiMone;
    }

    public void setValIndiMone(Double valIndiMone)
    {
        this.valIndiMone = valIndiMone;
    }
}