

package br.gov.pbh.desif.service.alerta;

import br.gov.pbh.desif.dao.SistemaAlertaDao;
import br.gov.pbh.desif.model.pojo.Alerta;
import br.gov.pbh.desif.model.pojo.SistemaAlerta;
import br.gov.pbh.desif.service.contexto.Contexto;

public class AlertaService
{

    private SistemaAlertaDao sisAlertaDao;

    public AlertaService()
    {
    }

    public void addAlerta(long linha, Alerta codAlerta, int coluna, short tipoErro, String registro, String nomeCampo)
    {
        try
        {
            SistemaAlerta sisAlerta = new SistemaAlerta(new Long(linha), codAlerta, new Long(linha), new Integer(coluna), new Short(tipoErro), registro, nomeCampo, null);
            sisAlertaDao = (SistemaAlertaDao)Contexto.getObject("sistemaAlertaDao");
            sisAlertaDao.save(sisAlerta);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void addAlerta(long linha, Alerta codAlerta, int coluna, short tipoErro, String registro, String nomeCampo, 
            String valorCampoErro)
    {
        try
        {
            SistemaAlerta sisAlerta = new SistemaAlerta(new Long(linha), codAlerta, new Long(linha), new Integer(coluna), new Short(tipoErro), registro, nomeCampo, valorCampoErro);
            sisAlertaDao = (SistemaAlertaDao)Contexto.getObject("sistemaAlertaDao");
            sisAlertaDao.save(sisAlerta);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
