
package br.gov.pbh.desif.model.pojo;

import br.gov.pbh.desif.model.pojo.base.AbstractErroSolucao;
import java.io.Serializable;

// Referenced classes of package br.gov.pbh.desif.model.pojo:
//            Erro, Solucao

public class ErroSolucao extends AbstractErroSolucao
    implements Serializable
{

    public ErroSolucao()
    {
    }

    public ErroSolucao(Long id, Erro erro, Solucao solucao)
    {
        super(id.longValue(), erro, solucao);
    }
}