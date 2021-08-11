
package br.gov.pbh.desif.control;

import br.gov.pbh.desif.dao.IndiceMonetarioDao;
import br.gov.pbh.desif.model.pojo.IndicesMonetarios;
import br.gov.pbh.desif.service.contexto.Contexto;
import java.util.Date;
import java.util.List;

public class ControleIndiceMonet
{

    public ControleIndiceMonet()
    {
    }

    public double[] getIndicesCorrecaoByDatas(Date referencia, Date pagamento)
    {
        IndiceMonetarioDao monetDAO = (IndiceMonetarioDao)Contexto.getObject("indiceMonetarioDao");
        List list = monetDAO.findByDatas(referencia, pagamento);
        double indicesBanco[] = new double[list.size()];
        for(int i = 0; i < list.size(); i++)
        {
            IndicesMonetarios indiceMonet = (IndicesMonetarios)list.get(i);
            indicesBanco[i] = indiceMonet.getValIndiMone().doubleValue();
        }

        return indicesBanco;
    }
}