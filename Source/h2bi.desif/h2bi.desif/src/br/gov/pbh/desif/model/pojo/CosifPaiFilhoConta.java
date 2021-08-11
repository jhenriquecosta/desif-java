
package br.gov.pbh.desif.model.pojo;

import br.gov.pbh.desif.model.pojo.base.AbstractCosifPaiFilhoConta;
import java.io.Serializable;

public class CosifPaiFilhoConta extends AbstractCosifPaiFilhoConta
    implements Serializable
{

    public CosifPaiFilhoConta(String contaCosifPai, String contaCosifFilho)
    {
        super(contaCosifPai, contaCosifFilho);
    }

    public CosifPaiFilhoConta()
    {
    }
}