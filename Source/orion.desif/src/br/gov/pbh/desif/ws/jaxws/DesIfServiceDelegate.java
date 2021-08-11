package br.gov.pbh.desif.ws.jaxws;

import javax.jws.soap.SOAPBinding;

// Referenced classes of package br.gov.pbh.desif.ws.jaxws:
//            ReceberRequest, ReceberResponse

public interface DesIfServiceDelegate
{

    public abstract ReceberResponse receber(ReceberRequest receberrequest);
}
