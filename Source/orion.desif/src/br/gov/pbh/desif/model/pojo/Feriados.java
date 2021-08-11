
package br.gov.pbh.desif.model.pojo;

import br.gov.pbh.desif.model.pojo.base.AbstractFeriados;
import java.io.Serializable;

// Referenced classes of package br.gov.pbh.desif.model.pojo:
//            FeriadosId

public class Feriados extends AbstractFeriados
    implements Serializable
{

    public Feriados()
    {
    }

    public Feriados(FeriadosId id, Short tipFeri)
    {
        super(id, tipFeri);
    }
}