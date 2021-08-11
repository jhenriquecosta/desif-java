
package br.gov.pbh.desif.ws.jaxws;

import javax.activation.DataHandler;

public class ReceberRequest
{

    protected DataHandler zip;
    protected String versao;

    public ReceberRequest()
    {
    }

    public DataHandler getZip()
    {
        return zip;
    }

    public void setZip(DataHandler value)
    {
        zip = value;
    }

    public String getVersao()
    {
        return versao;
    }

    public void setVersao(String value)
    {
        versao = value;
    }
}
