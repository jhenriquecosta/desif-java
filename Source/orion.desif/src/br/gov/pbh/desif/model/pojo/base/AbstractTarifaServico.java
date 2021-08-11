
package br.gov.pbh.desif.model.pojo.base;

import java.io.Serializable;

public abstract class AbstractTarifaServico
    implements Serializable
{

    private Long id;
    private Long numLinhTariServ;
    private String codIdentTarifa;
    private String codSubtitulo;
    private String descTarifa;

    public AbstractTarifaServico()
    {
    }

    public AbstractTarifaServico(Long id, Long numLinhTariServ, String codIdentTarifa, String codSubtitulo, String descTarifa)
    {
        this.id = id;
        this.numLinhTariServ = numLinhTariServ;
        this.codIdentTarifa = codIdentTarifa;
        this.codSubtitulo = codSubtitulo;
        this.descTarifa = descTarifa;
    }

    public AbstractTarifaServico(String codIdentTarifa, String codSubtitulo, String descTarifa)
    {
        this.codIdentTarifa = codIdentTarifa;
        this.codSubtitulo = codSubtitulo;
        this.descTarifa = descTarifa;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getNumLinhTariServ()
    {
        return numLinhTariServ;
    }

    public void setNumLinhTariServ(Long numLinhTariServ)
    {
        this.numLinhTariServ = numLinhTariServ;
    }

    public String getCodIdentTarifa()
    {
        return codIdentTarifa;
    }

    public void setCodIdentTarifa(String codIdentTarifa)
    {
        this.codIdentTarifa = codIdentTarifa;
    }

    public String getCodSubtitulo()
    {
        return codSubtitulo;
    }

    public void setCodSubtitulo(String codSubtitulo)
    {
        this.codSubtitulo = codSubtitulo;
    }

    public String getDescTarifa()
    {
        return descTarifa;
    }

    public void setDescTarifa(String descTarifa)
    {
        this.descTarifa = descTarifa;
    }
}