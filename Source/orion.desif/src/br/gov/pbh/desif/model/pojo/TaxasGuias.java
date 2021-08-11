
package br.gov.pbh.desif.model.pojo;

import br.gov.pbh.desif.model.pojo.base.AbstractTaxasGuias;
import java.io.Serializable;

public class TaxasGuias extends AbstractTaxasGuias
    implements Serializable
{

    public TaxasGuias()
    {
    }

    public TaxasGuias(Short idnTaxaGuia, Double valTaxaBanc)
    {
        super(idnTaxaGuia, valTaxaBanc);
    }

    public TaxasGuias(Short idnTaxaGuia, Double valTaxaBanc, String desFormMult, String desFormJuro)
    {
        super(idnTaxaGuia, valTaxaBanc, desFormMult, desFormJuro);
    }
}