

package br.gov.pbh.desif.model.pojo.base;

import br.gov.pbh.desif.model.pojo.Erro;
import br.gov.pbh.desif.model.pojo.Solucao;
import java.io.Serializable;

public abstract class AbstractErroSolucao
    implements Serializable
{

    private long id;
    private Erro erro;
    private Solucao solucao;

    public AbstractErroSolucao()
    {
    }

    public AbstractErroSolucao(long id, Erro erro, Solucao solucao)
    {
        this.id = id;
        this.erro = erro;
        this.solucao = solucao;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public Erro getErro()
    {
        return erro;
    }

    public void setErro(Erro erro)
    {
        this.erro = erro;
    }

    public Solucao getSolucao()
    {
        return solucao;
    }

    public void setSolucao(Solucao solucao)
    {
        this.solucao = solucao;
    }
}