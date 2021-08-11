

package br.gov.pbh.desif.model.pojo;

import br.gov.pbh.desif.model.pojo.base.AbstractIndicesMonetariosId;
import java.io.Serializable;
import java.util.Date;

public class IndicesMonetariosId extends AbstractIndicesMonetariosId
    implements Serializable
{

    public IndicesMonetariosId()
    {
    }

    public IndicesMonetariosId(Date datRef, Short tipIndi)
    {
        super(datRef, tipIndi);
    }
}