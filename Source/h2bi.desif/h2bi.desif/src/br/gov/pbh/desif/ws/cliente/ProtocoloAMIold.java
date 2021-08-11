
package br.gov.pbh.desif.ws.cliente;

import java.util.*;

public class ProtocoloAMIold
{

    protected String Ano_Mes_Inic_Cmpe;
    protected Date DataEntrega;
    protected Long id;
    protected List ListaDependencia;
    protected List ListaTotalizacao;
    protected String Nome;
    protected String RaizCnpj;
    protected Short Tipo_Cnso;
    protected Short Tipo_Decl;
    protected String xmlAssinado;

    public ProtocoloAMIold()
    {
    }

    public String getAno_Mes_Inic_Cmpe()
    {
        return Ano_Mes_Inic_Cmpe;
    }

    public void setAno_Mes_Inic_Cmpe(String value)
    {
        Ano_Mes_Inic_Cmpe = value;
    }

    public Date getDataEntrega()
    {
        return DataEntrega;
    }

    public void setDataEntrega(Date value)
    {
        DataEntrega = value;
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
        if(ListaDependencia == null)
            ListaDependencia = new ArrayList();
        return ListaDependencia;
    }

    public List getListaTotalizacao()
    {
        if(ListaTotalizacao == null)
            ListaTotalizacao = new ArrayList();
        return ListaTotalizacao;
    }

    public String getNome()
    {
        return Nome;
    }

    public void setNome(String value)
    {
        Nome = value;
    }

    public String getRaizCnpj()
    {
        return RaizCnpj;
    }

    public void setRaizCnpj(String value)
    {
        RaizCnpj = value;
    }

    public Short getTipo_Cnso()
    {
        return Tipo_Cnso;
    }

    public void setTipo_Cnso(Short value)
    {
        Tipo_Cnso = value;
    }

    public Short getTipo_Decl()
    {
        return Tipo_Decl;
    }

    public void setTipo_Decl(Short value)
    {
        Tipo_Decl = value;
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
