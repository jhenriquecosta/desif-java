
package br.gov.pbh.desif.model.pojo;

import br.gov.pbh.desif.model.pojo.base.AbstractListaServico;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class ListaServico extends AbstractListaServico
    implements Serializable
{

    public ListaServico()
    {
    }

    public ListaServico(String id, String nomeListaServico, Date dataInicio)
    {
        super(id, nomeListaServico, dataInicio);
    }

    public ListaServico(String id, String nomeListaServico, Date dataInicio, Date dataFim, Set codTributacaos)
    {
        super(id, nomeListaServico, dataInicio, dataFim, codTributacaos);
    }
}