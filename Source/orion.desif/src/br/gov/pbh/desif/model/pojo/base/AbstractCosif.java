

package br.gov.pbh.desif.model.pojo.base;

import java.io.Serializable;
import java.util.*;

public abstract class AbstractCosif
    implements Serializable
{

    private String numeroContaCosif;
    private Date dataCriacao;
    private String nomeContaCosif;
    private String desFuncConta;
    private String numeroContaSuperior;
    private Date dataExtinsao;
    private Integer numNivel;
    private Set CosifPaiFilhosForContaCosifPai;
    private Set CosifPaiFilhosForContaCosifFilho;

    public AbstractCosif()
    {
        CosifPaiFilhosForContaCosifPai = new HashSet(0);
        CosifPaiFilhosForContaCosifFilho = new HashSet(0);
    }

    public AbstractCosif(String numeroContaCosif, Date dataCriacao, String nomeContaCosif, String desFuncConta, Date dataExtinsao, Integer numNivel)
    {
        CosifPaiFilhosForContaCosifPai = new HashSet(0);
        CosifPaiFilhosForContaCosifFilho = new HashSet(0);
        this.numeroContaCosif = numeroContaCosif;
        this.dataCriacao = dataCriacao;
        this.nomeContaCosif = nomeContaCosif;
        this.desFuncConta = desFuncConta;
        this.dataExtinsao = dataExtinsao;
        this.numNivel = numNivel;
    }

    public Date getDataCriacao()
    {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao)
    {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataExtinsao()
    {
        return dataExtinsao;
    }

    public void setDataExtinsao(Date dataExtinsao)
    {
        this.dataExtinsao = dataExtinsao;
    }

    public String getDesFuncConta()
    {
        return desFuncConta;
    }

    public void setDesFuncConta(String desFuncConta)
    {
        this.desFuncConta = desFuncConta;
    }

    public String getNomeContaCosif()
    {
        return nomeContaCosif;
    }

    public void setNomeContaCosif(String nomeContaCosif)
    {
        this.nomeContaCosif = nomeContaCosif;
    }

    public String getNumeroContaCosif()
    {
        return numeroContaCosif;
    }

    public void setNumeroContaCosif(String numeroContaCosif)
    {
        this.numeroContaCosif = numeroContaCosif;
    }

    public String getNumeroContaSuperior()
    {
        return numeroContaSuperior;
    }

    public void setNumeroContaSuperior(String numeroContaSuperior)
    {
        this.numeroContaSuperior = numeroContaSuperior;
    }

    public Integer getNumNivel()
    {
        return numNivel;
    }

    public void setNumNivel(Integer numNivel)
    {
        this.numNivel = numNivel;
    }

    public Set getCosifPaiFilhosForContaCosifFilho()
    {
        return CosifPaiFilhosForContaCosifFilho;
    }

    public void setCosifPaiFilhosForContaCosifFilho(Set CosifPaiFilhosForContaCosifFilho)
    {
        this.CosifPaiFilhosForContaCosifFilho = CosifPaiFilhosForContaCosifFilho;
    }

    public Set getCosifPaiFilhosForContaCosifPai()
    {
        return CosifPaiFilhosForContaCosifPai;
    }

    public void setCosifPaiFilhosForContaCosifPai(Set CosifPaiFilhosForContaCosifPai)
    {
        this.CosifPaiFilhosForContaCosifPai = CosifPaiFilhosForContaCosifPai;
    }

    public boolean equals(Object obj)
    {
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        AbstractCosif other = (AbstractCosif)obj;
        return numeroContaCosif == other.numeroContaCosif || numeroContaCosif != null && numeroContaCosif.equals(other.numeroContaCosif);
    }

    public int hashCode()
    {
        int hash = 3;
        hash = 29 * hash + (numeroContaCosif == null ? 0 : numeroContaCosif.hashCode());
        return hash;
    }
}