
package br.gov.pbh.desif.model.pojo;

import java.sql.Timestamp;
import java.util.List;

public class ProtocoloAMI
{

    private Long id;
    private String raizCnpj;
    private String nome;
    private String anoMesInicCmpe;
    private Short tipoDecl;
    private Short tipoCnso;
    private Timestamp dataEntrega;
    private String verTermoRef;
    private Double verValidador;
    private String xmlAssinado;
    private Short modulo;
    private List listaDependencia;
    private List listaTotalizacao;

    public ProtocoloAMI(Long id, String raizCnpj, String nome, String anoMesInicCmpe, Short tipoDecl, Short tipoCnso, Timestamp dataEntrega, 
            String verTermoRef, Double verValidador, String xmlAssinado, Short modulo, List listaDependencia, List listaTotalizacao)
    {
        this.id = id;
        this.raizCnpj = raizCnpj;
        this.nome = nome;
        this.anoMesInicCmpe = anoMesInicCmpe;
        this.tipoDecl = tipoDecl;
        this.tipoCnso = tipoCnso;
        this.dataEntrega = dataEntrega;
        this.verTermoRef = verTermoRef;
        this.verValidador = verValidador;
        this.xmlAssinado = xmlAssinado;
        this.modulo = modulo;
        this.listaDependencia = listaDependencia;
        this.listaTotalizacao = listaTotalizacao;
    }

    public ProtocoloAMI()
    {
    }

    public String getAnoMesInicCmpe()
    {
        return anoMesInicCmpe;
    }

    public void setAnoMesInicCmpe(String anoMesInicCmpe)
    {
        this.anoMesInicCmpe = anoMesInicCmpe;
    }

    public Timestamp getDataEntrega()
    {
        return dataEntrega;
    }

    public void setDataEntrega(Timestamp dataEntrega)
    {
        this.dataEntrega = dataEntrega;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
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

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getRaizCnpj()
    {
        return raizCnpj;
    }

    public void setRaizCnpj(String raizCnpj)
    {
        this.raizCnpj = raizCnpj;
    }

    public Short getTipoCnso()
    {
        return tipoCnso;
    }

    public void setTipoCnso(Short tipoCnso)
    {
        this.tipoCnso = tipoCnso;
    }

    public Short getTipoDecl()
    {
        return tipoDecl;
    }

    public void setTipoDecl(Short tipoDecl)
    {
        this.tipoDecl = tipoDecl;
    }

    public String getVerTermoRef()
    {
        return verTermoRef;
    }

    public void setVerTermoRef(String verTermoRef)
    {
        this.verTermoRef = verTermoRef;
    }

    public Double getVerValidador()
    {
        return verValidador;
    }

    public void setVerValidador(Double verValidador)
    {
        this.verValidador = verValidador;
    }

    public String getXmlAssinado()
    {
        return xmlAssinado;
    }

    public void setXmlAssinado(String xmlAssinado)
    {
        this.xmlAssinado = xmlAssinado;
    }

    public Short getModulo()
    {
        return modulo;
    }

    public void setModulo(Short modulo)
    {
        this.modulo = modulo;
    }
}