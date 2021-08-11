
package br.gov.pbh.desif.ws.cliente;


public class DependenciaProtocoloAMI
{

    protected String cnpjProprio;
    protected String codDepe;
    protected Short ctblPropria;
    protected Short indrInscMunl;

    public DependenciaProtocoloAMI()
    {
    }

    public String getCnpjProprio()
    {
        return cnpjProprio;
    }

    public void setCnpjProprio(String value)
    {
        cnpjProprio = value;
    }

    public String getCodDepe()
    {
        return codDepe;
    }

    public void setCodDepe(String value)
    {
        codDepe = value;
    }

    public Short getCtblPropria()
    {
        return ctblPropria;
    }

    public void setCtblPropria(Short value)
    {
        ctblPropria = value;
    }

    public Short getIndrInscMunl()
    {
        return indrInscMunl;
    }

    public void setIndrInscMunl(Short value)
    {
        indrInscMunl = value;
    }
}
