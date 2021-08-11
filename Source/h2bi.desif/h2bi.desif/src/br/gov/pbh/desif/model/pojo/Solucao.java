
package br.gov.pbh.desif.model.pojo;

import br.gov.pbh.desif.model.pojo.base.AbstractSolucao;
import java.io.Serializable;
import java.util.Set;

public class Solucao extends AbstractSolucao
    implements Serializable
{

    public Solucao()
    {
    }

    public Solucao(String id, String descSolucao)
    {
        super(id, descSolucao);
    }

    public Solucao(String id, String descSolucao, Set erroSolucaos)
    {
        super(id, descSolucao, erroSolucaos);
    }
}