

package br.gov.pbh.desif.service.erro;

import br.gov.pbh.desif.dao.SistemaErroDao;
import br.gov.pbh.desif.model.pojo.Erro;
import br.gov.pbh.desif.model.pojo.SistemaErro;
import br.gov.pbh.desif.service.contexto.Contexto;

public class ErroService
{

    private SistemaErroDao sisErroDao;

    public ErroService()
    {
    }

    public void addErro(long linha, Erro codErro, int coluna, short tipoErro, String registro, String nomeCampo)
    {
        try
        {
            SistemaErro sisErro = new SistemaErro(new Long(linha), codErro, new Long(linha), new Integer(coluna), new Short(tipoErro), registro, nomeCampo, null);
            sisErroDao = (SistemaErroDao)Contexto.getObject("sistemaErroDao");
            sisErroDao.save(sisErro);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void addErro(long linha, Erro codErro, int coluna, short tipoErro, String registro, String nomeCampo, 
            String valorCampoErro)
    {
        try
        {
            SistemaErro sisErro = new SistemaErro(new Long(linha), codErro, new Long(linha), new Integer(coluna), new Short(tipoErro), registro, nomeCampo, valorCampoErro);
            sisErroDao = (SistemaErroDao)Contexto.getObject("sistemaErroDao");
            sisErroDao.save(sisErro);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
