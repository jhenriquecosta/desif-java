
package br.gov.pbh.desif.ws.cliente;

import java.util.ArrayList;
import java.util.List;

// Referenced classes of package br.gov.pbh.desif.ws.cliente:
//            ProtocoloAMI

public class ReceberResponseAMI
{

    protected List listaErros;
    protected ProtocoloAMI protocoloAMIVO;

    public ReceberResponseAMI()
    {
    }

    public List getListaErros()
    {
        if(listaErros == null)
            listaErros = new ArrayList();
        return listaErros;
    }

    public ProtocoloAMI getProtocoloAMIVO()
    {
        return protocoloAMIVO;
    }

    public void setProtocoloAMIVO(ProtocoloAMI value)
    {
        protocoloAMIVO = value;
    }
}
