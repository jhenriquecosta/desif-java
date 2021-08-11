/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.service.alerta;

import br.gov.pbh.desif.dao.SistemaAlertaDao;
import br.gov.pbh.desif.model.pojo.Alerta;
import br.gov.pbh.desif.model.pojo.SistemaAlerta;
import br.gov.pbh.desif.service.contexto.Contexto;
import java.io.Serializable;

public class AlertaService {
    private SistemaAlertaDao sisAlertaDao;

    public void addAlerta(long linha, Alerta codAlerta, int coluna, short tipoErro, String registro, String nomeCampo) {
        try {
            SistemaAlerta sisAlerta = new SistemaAlerta(new Long(linha), codAlerta, new Long(linha), new Integer(coluna), new Short(tipoErro), registro, nomeCampo, null);
            this.sisAlertaDao = (SistemaAlertaDao)Contexto.getObject("sistemaAlertaDao");
            this.sisAlertaDao.save(sisAlerta);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addAlerta(long linha, Alerta codAlerta, int coluna, short tipoErro, String registro, String nomeCampo, String valorCampoErro) {
        try {
            SistemaAlerta sisAlerta = new SistemaAlerta(new Long(linha), codAlerta, new Long(linha), new Integer(coluna), new Short(tipoErro), registro, nomeCampo, valorCampoErro);
            this.sisAlertaDao = (SistemaAlertaDao)Contexto.getObject("sistemaAlertaDao");
            this.sisAlertaDao.save(sisAlerta);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

