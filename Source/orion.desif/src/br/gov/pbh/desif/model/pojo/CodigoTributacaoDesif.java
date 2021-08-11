

package br.gov.pbh.desif.model.pojo;

import br.gov.pbh.desif.model.pojo.base.AbstractCodigoTributacaoDesif;
import java.io.Serializable;
import java.util.Set;

// Referenced classes of package br.gov.pbh.desif.model.pojo:
//            ListaServico

public class CodigoTributacaoDesif extends AbstractCodigoTributacaoDesif
    implements Serializable
{

    public CodigoTributacaoDesif()
    {
    }

    public CodigoTributacaoDesif(String id)
    {
        super(id);
    }

    public CodigoTributacaoDesif(String id, ListaServico listaServico, String descCodigoTributacao)
    {
        super(id, listaServico, descCodigoTributacao);
    }

    public CodigoTributacaoDesif(String id, ListaServico listaServico, String descCodigoTributacao, Set codTribuMunicipals)
    {
        super(id, listaServico, descCodigoTributacao, codTribuMunicipals);
    }
}