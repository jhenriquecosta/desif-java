
package br.gov.pbh.desif.model.pojo.base;

import java.io.Serializable;
import java.util.Date;

public class AbstractRelatorioEstaticoApuracaoSubtituloVO
    implements Serializable
{

    private String nomeMunicipio;
    private String nomeInstituicao;
    private Date dataIniCompetencia;
    private String iniCNPJ;
    private String cnpjProprio;
    private String enderecoDependencia;
    private String cnpjUnificado;
    private String codigoDependencia;

    public AbstractRelatorioEstaticoApuracaoSubtituloVO()
    {
    }

    public AbstractRelatorioEstaticoApuracaoSubtituloVO(String nomeMunicipio, String nomeInstituicao, Date dataIniCompetencia, String iniCNPJ, String cnpjProprio, String enderecoDependencia, String cnpjUnificado, 
            String codigoDependencia)
    {
        this.nomeMunicipio = nomeMunicipio;
        this.nomeInstituicao = nomeInstituicao;
        this.dataIniCompetencia = dataIniCompetencia;
        this.iniCNPJ = iniCNPJ;
        this.cnpjProprio = cnpjProprio;
        this.enderecoDependencia = enderecoDependencia;
        this.cnpjUnificado = cnpjUnificado;
        this.codigoDependencia = codigoDependencia;
    }

    public String getCnpjProprio()
    {
        return cnpjProprio;
    }

    public void setCnpjProprio(String cnpjProprio)
    {
        this.cnpjProprio = cnpjProprio;
    }

    public String getCnpjUnificado()
    {
        return cnpjUnificado;
    }

    public void setCnpjUnificado(String cnpjUnificado)
    {
        this.cnpjUnificado = cnpjUnificado;
    }

    public Date getDataIniCompetencia()
    {
        return dataIniCompetencia;
    }

    public void setDataIniCompetencia(Date dataIniCompetencia)
    {
        this.dataIniCompetencia = dataIniCompetencia;
    }

    public String getEnderecoDependencia()
    {
        return enderecoDependencia;
    }

    public void setEnderecoDependencia(String enderecoDependencia)
    {
        this.enderecoDependencia = enderecoDependencia;
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

    public String getCodigoDependencia()
    {
        return codigoDependencia;
    }

    public void setCodigoDependencia(String codigoDependencia)
    {
        this.codigoDependencia = codigoDependencia;
    }
}