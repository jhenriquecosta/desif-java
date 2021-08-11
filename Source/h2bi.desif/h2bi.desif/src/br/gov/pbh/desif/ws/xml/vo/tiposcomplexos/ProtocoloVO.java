
package br.gov.pbh.desif.ws.xml.vo.tiposcomplexos;

import java.util.Date;
import java.util.List;

public class ProtocoloVO
{

    private String raizCnpj;
    private Long codigo;
    private String nome;
    private String anoMesInicCmpe;
    private Integer tipoDecl;
    private Integer tipoCnso;
    private Date datEntrega;
    public List listaDependencia;
    public List listaTotalizacao;

    public ProtocoloVO()
    {
    }

    public String getRaizCnpj()
    {
        return raizCnpj;
    }

    public void setRaizCnpj(String raizCnpj)
    {
        this.raizCnpj = raizCnpj;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getAnoMesInicCmpe()
    {
        return anoMesInicCmpe;
    }

    public void setAnoMesInicCmpe(String anoMesInicCmpe)
    {
        this.anoMesInicCmpe = anoMesInicCmpe;
    }

    public Integer getTipoDecl()
    {
        return tipoDecl;
    }

    public void setTipoDecl(Integer tipoDecl)
    {
        this.tipoDecl = tipoDecl;
    }

    public Integer getTipoCnso()
    {
        return tipoCnso;
    }

    public void setTipoCnso(Integer tipoCnso)
    {
        this.tipoCnso = tipoCnso;
    }

    public Date getDatEntrega()
    {
        return datEntrega;
    }

    public void setDatEntrega(Date datEntrega)
    {
        this.datEntrega = datEntrega;
    }

    public List getListaDependencia()
    {
        return listaDependencia;
    }

    public void setListaDependencia(List listaDependencia)
    {
        this.listaDependencia = listaDependencia;
    }

    public List getListaTotalizacao()
    {
        return listaTotalizacao;
    }

    public void setListaTotalizacao(List listaTotalizacao)
    {
        this.listaTotalizacao = listaTotalizacao;
    }

    public Long getCodigo()
    {
        return codigo;
    }

    public void setCodigo(Long codigo)
    {
        this.codigo = codigo;
    }
}
