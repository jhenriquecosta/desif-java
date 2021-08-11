

package br.gov.pbh.desif.model.pojo.base;

import java.io.Serializable;
import java.util.Date;

public abstract class AbstractRelatorioEstaticoApuracaoIssqnVO
    implements Serializable
{

    private String nomeMunicipio;
    private String nomeInstituicao;
    private String iniCNPJ;
    private String fimCNPJ;
    private Date dataIniCompetencia;
    private Short tipoConsolidacao;

    public AbstractRelatorioEstaticoApuracaoIssqnVO()
    {
    }

    public AbstractRelatorioEstaticoApuracaoIssqnVO(String nomeMunicipio, String nomeInstituicao, String iniCNPJ, String fimCNPJ, Date dataIniCompetencia, Short tipoConsolidacao)
    {
        this.nomeMunicipio = nomeMunicipio;
        this.nomeInstituicao = nomeInstituicao;
        this.iniCNPJ = iniCNPJ;
        this.fimCNPJ = fimCNPJ;
        this.dataIniCompetencia = dataIniCompetencia;
        this.tipoConsolidacao = tipoConsolidacao;
    }

    public String getFimCNPJ()
    {
        return fimCNPJ;
    }

    public void setFimCNPJ(String fimCNPJ)
    {
        this.fimCNPJ = fimCNPJ;
    }

    public String getIniCNPJ()
    {
        return iniCNPJ;
    }

    public void setIniCNPJ(String iniCNPJ)
    {
        this.iniCNPJ = iniCNPJ;
    }

    public String getNomeInstituicao()
    {
        return nomeInstituicao;
    }

    public void setNomeInstituicao(String nomeInstituicao)
    {
        this.nomeInstituicao = nomeInstituicao;
    }

    public String getNomeMunicipio()
    {
        return nomeMunicipio;
    }

    public void setNomeMunicipio(String nomeMunicipio)
    {
        this.nomeMunicipio = nomeMunicipio;
    }

    public Date getDataIniCompetencia()
    {
        return dataIniCompetencia;
    }

    public void setDataIniCompetencia(Date dataIniCompetencia)
    {
        this.dataIniCompetencia = dataIniCompetencia;
    }

    public Short getTipoConsolidacao()
    {
        return tipoConsolidacao;
    }

    public void setTipoConsolidacao(Short tipoConsolidacao)
    {
        this.tipoConsolidacao = tipoConsolidacao;
    }
}