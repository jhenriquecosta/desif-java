/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.service.erro;

import br.gov.pbh.desif.dao.SistemaErroDao;
import br.gov.pbh.desif.model.pojo.Erro;
import br.gov.pbh.desif.model.pojo.SistemaErro;
import br.gov.pbh.desif.service.contexto.Contexto;
import java.io.Serializable;

public class ErroService {
    private SistemaErroDao sisErroDao;

    public void addErro(long linha, Erro codErro, int coluna, short tipoErro, String registro, String nomeCampo) {
        try {
            SistemaErro sisErro = new SistemaErro(new Long(linha), codErro, new Long(linha), new Integer(coluna), new Short(tipoErro), registro, nomeCampo, null);
            this.sisErroDao = (SistemaErroDao)Contexto.getObject("sistemaErroDao");
            this.sisErroDao.save(sisErro);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addErro(long linha, Erro codErro, int coluna, short tipoErro, String registro, String nomeCampo, String valorCampoErro) {
        try {
            SistemaErro sisErro = new SistemaErro(new Long(linha), codErro, new Long(linha), new Integer(coluna), new Short(tipoErro), registro, nomeCampo, valorCampoErro);
            this.sisErroDao = (SistemaErroDao)Contexto.getObject("sistemaErroDao");
            this.sisErroDao.save(sisErro);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

