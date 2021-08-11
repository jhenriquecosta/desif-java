
package br.gov.pbh.desif.model.pojo;

import br.gov.pbh.desif.model.pojo.base.AbstractFeriadosId;
import java.io.Serializable;
import java.util.Date;

public class FeriadosId extends AbstractFeriadosId
    implements Serializable
{

    public FeriadosId()
    {
    }

    public FeriadosId(Date datFeri, String nomFeri)
    {
        super(datFeri, nomFeri);
    }
}