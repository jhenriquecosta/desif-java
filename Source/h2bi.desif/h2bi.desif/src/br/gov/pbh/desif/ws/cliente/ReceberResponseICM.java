
package br.gov.pbh.desif.ws.cliente;

import java.util.ArrayList;
import java.util.List;

// Referenced classes of package br.gov.pbh.desif.ws.cliente:
//            ProtocoloICM

public class ReceberResponseICM
{

    protected List listaErros;
    protected ProtocoloICM protocoloICM;

    public ReceberResponseICM()
    {
    }

    public List getListaErros()
    {
        if(listaErros == null)
            listaErros = new ArrayList();
        return listaErros;
    }

    public ProtocoloICM getProtocoloICM()
    {
        return protocoloICM;
    }

    public void setProtocoloICM(ProtocoloICM value)
    {
        protocoloICM = value;
    }
}
