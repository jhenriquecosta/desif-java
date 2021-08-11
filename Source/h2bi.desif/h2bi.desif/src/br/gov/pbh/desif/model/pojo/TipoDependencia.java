
package br.gov.pbh.desif.model.pojo;

import br.gov.pbh.desif.model.pojo.base.AbstractTipoDependencia;
import java.io.Serializable;

public class TipoDependencia extends AbstractTipoDependencia
    implements Serializable
{

    public TipoDependencia()
    {
    }

    public TipoDependencia(Integer id)
    {
        super(id);
    }

    public TipoDependencia(Integer id, String descTipoDependencia)
    {
        super(id, descTipoDependencia);
    }
}