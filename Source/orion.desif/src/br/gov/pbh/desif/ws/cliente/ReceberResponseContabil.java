
package br.gov.pbh.desif.ws.cliente;

import java.util.ArrayList;
import java.util.List;

// Referenced classes of package br.gov.pbh.desif.ws.cliente:
//            ProtocoloContabil

public class ReceberResponseContabil
{

    protected List listaErros;
    protected ProtocoloContabil protocoloContabil;

    public ReceberResponseContabil()
    {
    }

    public List getListaErros()
    {
        if(listaErros == null)
            listaErros = new ArrayList();
        return listaErros;
    }

    public ProtocoloContabil getProtocoloContabil()
    {
        return protocoloContabil;
    }

    public void setProtocoloContabil(ProtocoloContabil value)
    {
        protocoloContabil = value;
    }
}
