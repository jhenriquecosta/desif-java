

package br.gov.pbh.desif.model.pojo;

import br.gov.pbh.desif.model.pojo.base.AbstractIndicesMonetarios;
import java.io.Serializable;

// Referenced classes of package br.gov.pbh.desif.model.pojo:
//            IndicesMonetariosId

public class IndicesMonetarios extends AbstractIndicesMonetarios
    implements Serializable
{

    public IndicesMonetarios()
    {
    }

    public IndicesMonetarios(IndicesMonetariosId id, Double valIndiMone)
    {
        super(id, valIndiMone);
    }
}