
package br.gov.pbh.desif.model.pojo;

import br.gov.pbh.desif.model.pojo.base.AbstractCosifPaiFilho;
import java.io.Serializable;

// Referenced classes of package br.gov.pbh.desif.model.pojo:
//            CosifPaiFilhoConta, Cosif

public class CosifPaiFilho extends AbstractCosifPaiFilho
    implements Serializable
{

    public CosifPaiFilho()
    {
    }

    public CosifPaiFilho(CosifPaiFilhoConta conta, Cosif CosifByNumContaCosifPai, Cosif CosifByNumContaCosifFilho)
    {
        super(conta, CosifByNumContaCosifPai, CosifByNumContaCosifFilho);
    }
}