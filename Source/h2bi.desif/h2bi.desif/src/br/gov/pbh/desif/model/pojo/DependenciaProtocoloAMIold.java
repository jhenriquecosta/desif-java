
package br.gov.pbh.desif.model.pojo;


public class DependenciaProtocoloAMIold
{

    private String CNPJ_Proprio;
    private Short Indr_Insc_Munl;
    private String Cod_Depe;
    private Short Ctbl_Propria;

    public DependenciaProtocoloAMIold(String CNPJ_Proprio, Short Indr_Insc_Munl, String Cod_Depe, Short Ctbl_Propria)
    {
        this.CNPJ_Proprio = CNPJ_Proprio;
        this.Indr_Insc_Munl = Indr_Insc_Munl;
        this.Cod_Depe = Cod_Depe;
        this.Ctbl_Propria = Ctbl_Propria;
    }

    public DependenciaProtocoloAMIold()
    {
    }

    public String getCNPJ_Proprio()
    {
        return CNPJ_Proprio;
    }

    public void setCNPJ_Proprio(String CNPJ_Proprio)
    {
        this.CNPJ_Proprio = CNPJ_Proprio;
    }

    public String getCod_Depe()
    {
        return Cod_Depe;
    }

    public void setCod_Depe(String Cod_Depe)
    {
        this.Cod_Depe = Cod_Depe;
    }

    public Short getCtbl_Propria()
    {
        return Ctbl_Propria;
    }

    public void setCtbl_Propria(Short Ctbl_Propria)
    {
        this.Ctbl_Propria = Ctbl_Propria;
    }

    public Short getIndr_Insc_Munl()
    {
        return Indr_Insc_Munl;
    }

    public void setIndr_Insc_Munl(Short Indr_Insc_Munl)
    {
        this.Indr_Insc_Munl = Indr_Insc_Munl;
    }
}