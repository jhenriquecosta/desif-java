
package br.gov.pbh.desif.model.pojo;


public class TotalizacaoProtocoloAMIold
{

    private Double Rece_Decl_Cnso;
    private Double Dedu_Rece_Decl_Sub_Titu;
    private Double Dedu_Rece_Decl_Cnso;
    private Double Aliq_ISSQN;

    public TotalizacaoProtocoloAMIold(Double Rece_Decl_Cnso, Double Dedu_Rece_Decl_Sub_Titu, Double Dedu_Rece_Decl_Cnso, Double Aliq_ISSQN)
    {
        this.Rece_Decl_Cnso = Rece_Decl_Cnso;
        this.Dedu_Rece_Decl_Sub_Titu = Dedu_Rece_Decl_Sub_Titu;
        this.Dedu_Rece_Decl_Cnso = Dedu_Rece_Decl_Cnso;
        this.Aliq_ISSQN = Aliq_ISSQN;
    }

    public TotalizacaoProtocoloAMIold()
    {
    }

    public Double getAliq_ISSQN()
    {
        return Aliq_ISSQN;
    }

    public void setAliq_ISSQN(Double Aliq_ISSQN)
    {
        this.Aliq_ISSQN = Aliq_ISSQN;
    }

    public Double getDedu_Rece_Decl_Cnso()
    {
        return Dedu_Rece_Decl_Cnso;
    }

    public void setDedu_Rece_Decl_Cnso(Double Dedu_Rece_Decl_Cnso)
    {
        this.Dedu_Rece_Decl_Cnso = Dedu_Rece_Decl_Cnso;
    }

    public Double getDedu_Rece_Decl_Sub_Titu()
    {
        return Dedu_Rece_Decl_Sub_Titu;
    }

    public void setDedu_Rece_Decl_Sub_Titu(Double Dedu_Rece_Decl_Sub_Titu)
    {
        this.Dedu_Rece_Decl_Sub_Titu = Dedu_Rece_Decl_Sub_Titu;
    }

    public Double getRece_Decl_Cnso()
    {
        return Rece_Decl_Cnso;
    }

    public void setRece_Decl_Cnso(Double Rece_Decl_Cnso)
    {
        this.Rece_Decl_Cnso = Rece_Decl_Cnso;
    }
}