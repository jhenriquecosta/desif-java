
package br.gov.pbh.desif.model.pojo;

import br.gov.pbh.desif.model.pojo.base.AbstractSistemaAlerta;
import java.io.Serializable;

// Referenced classes of package br.gov.pbh.desif.model.pojo:
//            Alerta

public class SistemaAlerta extends AbstractSistemaAlerta
    implements Serializable
{

    public SistemaAlerta()
    {
    }

    public SistemaAlerta(Long id, Alerta alerta, Long linha, Integer coluna, Short tipoErro, String registro, String nomeCampo)
    {
        super(id, alerta, linha, coluna, tipoErro, registro, nomeCampo);
    }

    public SistemaAlerta(Long id, Alerta alerta, Long linha, Integer coluna, Short tipoErro, String registro, String nomeCampo, 
            String valorCampoErro)
    {
        super(id, alerta, linha, coluna, tipoErro, registro, nomeCampo, valorCampoErro);
    }
}