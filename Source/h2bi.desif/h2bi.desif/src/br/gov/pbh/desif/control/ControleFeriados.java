
package br.gov.pbh.desif.control;

import br.gov.pbh.desif.dao.FeriadoDao;
import br.gov.pbh.desif.service.contexto.Contexto;
import java.util.Date;
import java.util.List;

public class ControleFeriados
{

    private FeriadoDao feriadoDao;

    public ControleFeriados()
    {
    }

    public Date[] getFeriados()
    {
        feriadoDao = (FeriadoDao)Contexto.getObject("feriadoDao");
        Object datas[] = feriadoDao.findAllDates().toArray();
        Date retorno[] = new Date[datas.length];
        for(int i = 0; i < datas.length; i++)
            retorno[i] = (Date)datas[i];

        return retorno;
    }
}