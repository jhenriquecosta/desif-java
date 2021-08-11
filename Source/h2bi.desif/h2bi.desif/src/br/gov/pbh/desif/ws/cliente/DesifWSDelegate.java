
package br.gov.pbh.desif.ws.cliente;


// Referenced classes of package br.gov.pbh.desif.ws.cliente:
//            ReceberRequest, ReceberResponseICM, ReceberResponseContabil, ReceberResponseAMI

public interface DesifWSDelegate
{

    public abstract ReceberResponseICM receberICM(ReceberRequest receberrequest);

    public abstract ReceberResponseContabil receberContabil(ReceberRequest receberrequest);

    public abstract ReceberResponseAMI receberAMI(ReceberRequest receberrequest);
}
