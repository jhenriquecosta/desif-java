
package br.gov.pbh.desif.model.pojo.base;

import java.io.Serializable;
import java.util.Date;

public abstract class AbstractDemonstrativoRateioMensal
    implements Serializable
{

    private Long id;
    private Long numLinha;
    private String codigoDependencia;
    private Date AnoMesCompetencia;
    private String descricaoDetalhadaReceita;
    private Double valorReceitaRateada;
    private Short tipoPartida;
    private Integer codigoEvento;

    public AbstractDemonstrativoRateioMensal()
    {
    }

    public AbstractDemonstrativoRateioMensal(Long id, Long numLinha, String codigoDependencia, Date AnoMesCompetencia, String descricaoDetalhadaReceita, Double valorReceitaRateada, Short tipoPartida, 
            Integer codigoEvento)
    {
        this.id = id;
        this.numLinha = numLinha;
        this.codigoDependencia = codigoDependencia;
        this.AnoMesCompetencia = AnoMesCompetencia;
        this.descricaoDetalhadaReceita = descricaoDetalhadaReceita;
        this.valorReceitaRateada = valorReceitaRateada;
        this.tipoPartida = tipoPartida;
        this.codigoEvento = codigoEvento;
    }

    public AbstractDemonstrativoRateioMensal(String codigoDependencia, Date AnoMesCompetencia, String descricaoDetalhadaReceita, Double valorReceitaRateada, Short tipoPartida)
    {
        this.codigoDependencia = codigoDependencia;
        this.AnoMesCompetencia = AnoMesCompetencia;
        this.descricaoDetalhadaReceita = descricaoDetalhadaReceita;
        this.valorReceitaRateada = valorReceitaRateada;
        this.tipoPartida = tipoPartida;
    }

    public Long getNumLinha()
    {
        return numLinha;
    }

    public void setNumLinha(Long numLinha)
    {
        this.numLinha = numLinha;
    }

    public Date getAnoMesCompetencia()
    {
        return AnoMesCompetencia;
    }

    public void setAnoMesCompetencia(Date AnoMesCompetencia)
    {
        this.AnoMesCompetencia = AnoMesCompetencia;
    }

    public String getCodigoDependencia()
    {
        return codigoDependencia;
    }

    public void setCodigoDependencia(String codigoDependencia)
    {
        this.codigoDependencia = codigoDependencia;
    }

    public Integer getCodigoEvento()
    {
        return codigoEvento;
    }

    public void setCodigoEvento(Integer codigoEvento)
    {
        this.codigoEvento = codigoEvento;
    }

    public String getDescricaoDetalhadaReceita()
    {
        return descricaoDetalhadaReceita;
    }

    public void setDescricaoDetalhadaReceita(String descricaoDetalhadaReceita)
    {
        this.descricaoDetalhadaReceita = descricaoDetalhadaReceita;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Short getTipoPartida()
    {
        return tipoPartida;
    }

    public void setTipoPartida(Short tipoPartida)
    {
        this.tipoPartida = tipoPartida;
    }

    public Double getValorReceitaRateada()
    {
        return valorReceitaRateada;
    }

    public void setValorReceitaRateada(Double valorReceitaRateada)
    {
        this.valorReceitaRateada = valorReceitaRateada;
    }
}