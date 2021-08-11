
package br.gov.pbh.desif.model.pojo;


public class TotalizacaoProtocoloAMI
{

    private Double receDeclCnso;
    private Double deduReceDeclSubTitu;
    private Double deduReceDeclCnso;
    private Double aliqISSQN;

    public TotalizacaoProtocoloAMI(Double receDeclCnso, Double deduReceDeclSubTitu, Double deduReceDeclCnso, Double aliqISSQN)
    {
        this.receDeclCnso = receDeclCnso;
        this.deduReceDeclSubTitu = deduReceDeclSubTitu;
        this.deduReceDeclCnso = deduReceDeclCnso;
        this.aliqISSQN = aliqISSQN;
    }

    public TotalizacaoProtocoloAMI()
    {
    }

    public Double getAliqISSQN()
    {
        return aliqISSQN;
    }

    public void setAliqISSQN(Double aliqISSQN)
    {
        this.aliqISSQN = aliqISSQN;
    }

    public Double getDeduReceDeclCnso()
    {
        return deduReceDeclCnso;
    }

    public void setDeduReceDeclCnso(Double deduReceDeclCnso)
    {
        this.deduReceDeclCnso = deduReceDeclCnso;
    }

    public Double getDeduReceDeclSubTitu()
    {
        return deduReceDeclSubTitu;
    }

    public void setDeduReceDeclSubTitu(Double deduReceDeclSubTitu)
    {
        this.deduReceDeclSubTitu = deduReceDeclSubTitu;
    }

    public Double getReceDeclCnso()
    {
        return receDeclCnso;
    }

    public void setReceDeclCnso(Double receDeclCnso)
    {
        this.receDeclCnso = receDeclCnso;
    }
}