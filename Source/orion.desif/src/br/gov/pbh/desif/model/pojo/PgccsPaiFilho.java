

package br.gov.pbh.desif.model.pojo;

import br.gov.pbh.desif.model.pojo.base.AbstractPgccsPaiFilho;
import java.io.Serializable;

// Referenced classes of package br.gov.pbh.desif.model.pojo:
//            PgccsPaiFilhoId, PlanoGeralContaComentado

public class PgccsPaiFilho extends AbstractPgccsPaiFilho
    implements Serializable
{

    public PgccsPaiFilho()
    {
    }

    public PgccsPaiFilho(PgccsPaiFilhoId id, PlanoGeralContaComentado planoGeralContaComentadoByIdnPgccPai, PlanoGeralContaComentado planoGeralContaComentadoByIdnPgccFilho)
    {
        super(id, planoGeralContaComentadoByIdnPgccPai, planoGeralContaComentadoByIdnPgccFilho);
    }
}