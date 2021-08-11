
package br.gov.pbh.desif.ws.cliente;

import java.util.*;

public class ProtocoloAMI
{

    protected String anoMesInicCmpe;
    protected Date dataEntrega;
    protected Long id;
    protected List listaDependencia;
    protected List listaTotalizacao;
    protected Short modulo;
    protected String nome;
    protected String raizCnpj;
    protected Short tipoCnso;
    protected Short tipoDecl;
    protected String verTermoRef;
    protected Double verValidador;
    protected String xmlAssinado;

    public ProtocoloAMI()
    {
    }

    public String getAnoMesInicCmpe()
    {
        return anoMesInicCmpe;
    }

    public void setAnoMesInicCmpe(String value)
    {
        anoMesInicCmpe = value;
    }

    public Date getDataEntrega()
    {
        return dataEntrega;
    }

    public void setDataEntrega(Date value)
    {
        dataEntrega = value;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long value)
    {
        id = value;
    }

    public List getListaDependencia()
    {
        if(listaDependencia == null)
            listaDependencia = new ArrayList();
        return listaDependencia;
    }

    public List getListaTotalizacao()
    {
        if(listaTotalizacao == null)
            listaTotalizacao = new ArrayList();
        return listaTotalizacao;
    }

    public Short getModulo()
    {
        return modulo;
    }

    public void setModulo(Short value)
    {
        modulo = value;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String value)
    {
        nome = value;
    }

    public String getRaizCnpj()
    {
        return raizCnpj;
    }

    public void setRaizCnpj(String value)
    {
        raizCnpj = value;
    }

    public Short getTipoCnso()
    {
        return tipoCnso;
    }

    public void setTipoCnso(Short value)
    {
        tipoCnso = value;
    }

    public Short getTipoDecl()
    {
        return tipoDecl;
    }

    public void setTipoDecl(Short value)
    {
        tipoDecl = value;
    }

    public String getVerTermoRef()
    {
        return verTermoRef;
    }

    public void setVerTermoRef(String value)
    {
        verTermoRef = value;
    }

    public Double getVerValidador()
    {
        return verValidador;
    }

    public void setVerValidador(Double value)
    {
        verValidador = value;
    }

    public String getXmlAssinado()
    {
        return xmlAssinado;
    }

    public void setXmlAssinado(String value)
    {
        xmlAssinado = value;
    }
}
