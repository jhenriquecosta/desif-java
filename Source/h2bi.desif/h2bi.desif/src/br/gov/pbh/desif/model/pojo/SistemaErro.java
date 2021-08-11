
package br.gov.pbh.desif.model.pojo;

import br.gov.pbh.desif.model.pojo.base.AbstractSistemaErro;
import java.io.Serializable;

// Referenced classes of package br.gov.pbh.desif.model.pojo:
//            Erro

public class SistemaErro extends AbstractSistemaErro
    implements Serializable
{

    public SistemaErro()
    {
    }

    public SistemaErro(Long id, Erro erro, Long linha, Integer coluna, Short tipoErro, String registro, String nomeCampo)
    {
        super(id, erro, linha, coluna, tipoErro, registro, nomeCampo);
    }

    public SistemaErro(Long id, Erro erro, Long linha, Integer coluna, Short tipoErro, String registro, String nomeCampo, 
            String valorCampoErro)
    {
        super(id, erro, linha, coluna, tipoErro, registro, nomeCampo, valorCampoErro);
    }
}