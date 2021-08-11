
package br.gov.pbh.desif.ws.xml.vo.tiposcomplexos;


public class DependenciaVO
{

    private String cnpjProprio;
    private Integer indInscMunl;
    private String codDepe;
    private Integer ctblPropria;

    public DependenciaVO()
    {
    }

    public String getCnpjProprio()
    {
        return cnpjProprio;
    }

    public void setCnpjProprio(String cnpjProprio)
    {
        this.cnpjProprio = cnpjProprio;
    }

    public Integer getIndInscMunl()
    {
        return indInscMunl;
    }

    public void setIndInscMunl(Integer indInscMunl)
    {
        this.indInscMunl = indInscMunl;
    }

    public String getCodDepe()
    {
        return codDepe;
    }

    public void setCodDepe(String codDepe)
    {
        this.codDepe = codDepe;
    }

    public Integer getCtblPropria()
    {
        return ctblPropria;
    }

    public void setCtblPropria(Integer ctblPropria)
    {
        this.ctblPropria = ctblPropria;
    }
}
