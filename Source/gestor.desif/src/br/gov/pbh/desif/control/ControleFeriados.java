/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.control;

import br.gov.pbh.desif.dao.FeriadoDao;
import br.gov.pbh.desif.service.contexto.Contexto;
import java.util.Date;
import java.util.List;

public class ControleFeriados {
    private FeriadoDao feriadoDao;

    public Date[] getFeriados() {
        this.feriadoDao = (FeriadoDao)Contexto.getObject("feriadoDao");
        Object[] datas = this.feriadoDao.findAllDates().toArray();
        Date[] retorno = new Date[datas.length];
        for (int i = 0; i < datas.length; ++i) {
            retorno[i] = (Date)datas[i];
        }
        return retorno;
    }
}

