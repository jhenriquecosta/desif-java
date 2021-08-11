

package br.gov.pbh.desif.model.pojo.base;

import br.gov.pbh.desif.model.pojo.PgccsPaiFilhoId;
import br.gov.pbh.desif.model.pojo.PlanoGeralContaComentado;
import java.io.Serializable;

public abstract class AbstractPgccsPaiFilho
    implements Serializable
{

    private PgccsPaiFilhoId id;
    private PlanoGeralContaComentado planoGeralContaComentadoByIdnPgccPai;
    private PlanoGeralContaComentado planoGeralContaComentadoByIdnPgccFilho;

    public AbstractPgccsPaiFilho()
    {
    }

    public AbstractPgccsPaiFilho(PgccsPaiFilhoId id, PlanoGeralContaComentado planoGeralContaComentadoByIdnPgccPai, PlanoGeralContaComentado planoGeralContaComentadoByIdnPgccFilho)
    {
        this.id = id;
        this.planoGeralContaComentadoByIdnPgccPai = planoGeralContaComentadoByIdnPgccPai;
        this.planoGeralContaComentadoByIdnPgccFilho = planoGeralContaComentadoByIdnPgccFilho;
    }

    public PgccsPaiFilhoId getId()
    {
        return id;
    }

    public void setId(PgccsPaiFilhoId id)
    {
        this.id = id;
    }

    public PlanoGeralContaComentado getPlanoGeralContaComentadoByIdnPgccPai()
    {
        return planoGeralContaComentadoByIdnPgccPai;
    }

    public void setPlanoGeralContaComentadoByIdnPgccPai(PlanoGeralContaComentado planoGeralContaComentadoByIdnPgccPai)
    {
        this.planoGeralContaComentadoByIdnPgccPai = planoGeralContaComentadoByIdnPgccPai;
    }

    public PlanoGeralContaComentado getPlanoGeralContaComentadoByIdnPgccFilho()
    {
        return planoGeralContaComentadoByIdnPgccFilho;
    }

    public void setPlanoGeralContaComentadoByIdnPgccFilho(PlanoGeralContaComentado planoGeralContaComentadoByIdnPgccFilho)
    {
        this.planoGeralContaComentadoByIdnPgccFilho = planoGeralContaComentadoByIdnPgccFilho;
    }

    public boolean equals(Object obj)
    {
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        AbstractPgccsPaiFilho other = (AbstractPgccsPaiFilho)obj;
        return id == other.id || id != null && id.equals(other.id);
    }

    public int hashCode()
    {
        int hash = 7;
        hash = 67 * hash + (id == null ? 0 : id.hashCode());
        return hash;
    }
}