
package br.gov.pbh.desif.model.pojo;

import br.gov.pbh.desif.model.pojo.base.AbstractOrigemCreditoCompensar;
import java.io.Serializable;
import java.util.Date;

// Referenced classes of package br.gov.pbh.desif.model.pojo:
//            IssqnMensal

public class OrigemCreditoCompensar extends AbstractOrigemCreditoCompensar
    implements Serializable
{

    public OrigemCreditoCompensar()
    {
    }

    public OrigemCreditoCompensar(Long id, IssqnMensal issqnMensal, Date dataCompetenciaOrigemCredito, Double valorOrigemCredito)
    {
        super(id.longValue(), issqnMensal, dataCompetenciaOrigemCredito, valorOrigemCredito.doubleValue());
    }
}