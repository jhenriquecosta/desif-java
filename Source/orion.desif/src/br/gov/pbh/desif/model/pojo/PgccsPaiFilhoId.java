

package br.gov.pbh.desif.model.pojo;

import br.gov.pbh.desif.model.pojo.base.AbstractPgccsPaiFilhoId;
import java.io.Serializable;

public class PgccsPaiFilhoId extends AbstractPgccsPaiFilhoId
    implements Serializable
{

    public PgccsPaiFilhoId()
    {
    }

    public PgccsPaiFilhoId(Long idPgccPai, Long idPgccFilho)
    {
        super(idPgccPai, idPgccFilho);
    }
}