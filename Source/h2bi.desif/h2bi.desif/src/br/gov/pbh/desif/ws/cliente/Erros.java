

package br.gov.pbh.desif.ws.cliente;


public class Erros
{

    protected String detalhes;
    protected String id;
    protected String motivo;

    public Erros()
    {
    }

    public String getDetalhes()
    {
        return detalhes;
    }

    public void setDetalhes(String value)
    {
        detalhes = value;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String value)
    {
        id = value;
    }

    public String getMotivo()
    {
        return motivo;
    }

    public void setMotivo(String value)
    {
        motivo = value;
    }
}
