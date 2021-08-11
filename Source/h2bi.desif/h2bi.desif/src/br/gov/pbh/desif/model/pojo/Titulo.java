
package br.gov.pbh.desif.model.pojo;

import br.gov.pbh.desif.model.pojo.base.AbstractTitulo;
import java.io.Serializable;

public class Titulo extends AbstractTitulo
    implements Serializable
{

    public Titulo()
    {
    }

    public Titulo(String id)
    {
        super(id);
    }

    public Titulo(String id, String descTitulo, Boolean obrigatoria)
    {
        super(id, descTitulo, obrigatoria.booleanValue());
    }
}