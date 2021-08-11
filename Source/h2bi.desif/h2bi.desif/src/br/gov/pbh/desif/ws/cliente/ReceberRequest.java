
package br.gov.pbh.desif.ws.cliente;

import javax.activation.DataHandler;

public class ReceberRequest
{

    protected String versao;
    protected DataHandler zip;

    public ReceberRequest()
    {
    }

    public String getVersao()
    {
        return versao;
    }

    public void setVersao(String value)
    {
        versao = value;
    }

    public DataHandler getZip()
    {
        return zip;
    }

    public void setZip(DataHandler value)
    {
        zip = value;
    }
}
