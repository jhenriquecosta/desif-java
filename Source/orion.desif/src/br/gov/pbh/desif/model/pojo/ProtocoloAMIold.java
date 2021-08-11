
package br.gov.pbh.desif.model.pojo;

import java.sql.Timestamp;
import java.util.List;

public class ProtocoloAMIold
{

    private Long id;
    private String raizCnpj;
    private String Nome;
    private String Ano_Mes_Inic_Cmpe;
    private Short Tipo_Decl;
    private Short Tipo_Cnso;
    private Timestamp DataEntrega;
    private String xmlAssinado;
    private List ListaDependencia;
    private List ListaTotalizacao;

    public ProtocoloAMIold(Long id, String raizCnpj, String Nome, String Ano_Mes_Inic_Cmpe, Short Tipo_Decl, Short Tipo_Cnso, Timestamp DataEntrega, 
            String xmlAssinado, List ListaDependencia, List ListaTotalizacao)
    {
        this.id = id;
        this.raizCnpj = raizCnpj;
        this.Nome = Nome;
        this.Ano_Mes_Inic_Cmpe = Ano_Mes_Inic_Cmpe;
        this.Tipo_Decl = Tipo_Decl;
        this.Tipo_Cnso = Tipo_Cnso;
        this.DataEntrega = DataEntrega;
        this.xmlAssinado = xmlAssinado;
        this.ListaDependencia = ListaDependencia;
        this.ListaTotalizacao = ListaTotalizacao;
    }

    public ProtocoloAMIold()
    {
    }

    public String getAno_Mes_Inic_Cmpe()
    {
        return Ano_Mes_Inic_Cmpe;
    }

    public void setAno_Mes_Inic_Cmpe(String Ano_Mes_Inic_Cmpe)
    {
        this.Ano_Mes_Inic_Cmpe = Ano_Mes_Inic_Cmpe;
    }

    public Timestamp getDataEntrega()
    {
        return DataEntrega;
    }

    public void setDataEntrega(Timestamp DataEntrega)
    {
        this.DataEntrega = DataEntrega;
    }

    public List getListaDependencia()
    {
        return ListaDependencia;
    }

    public void setListaDependencia(List ListaDependencia)
    {
        this.ListaDependencia = ListaDependencia;
    }

    public List getListaTotalizacao()
    {
        return ListaTotalizacao;
    }

    public void setListaTotalizacao(List ListaTotalizacao)
    {
        this.ListaTotalizacao = ListaTotalizacao;
    }

    public String getNome()
    {
        return Nome;
    }

    public void setNome(String Nome)
    {
        this.Nome = Nome;
    }

    public Short getTipo_Cnso()
    {
        return Tipo_Cnso;
    }

    public void setTipo_Cnso(Short Tipo_Cnso)
    {
        this.Tipo_Cnso = Tipo_Cnso;
    }

    public Short getTipo_Decl()
    {
        return Tipo_Decl;
    }

    public void setTipo_Decl(Short Tipo_Decl)
    {
        this.Tipo_Decl = Tipo_Decl;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getRaizCnpj()
    {
        return raizCnpj;
    }

    public void setRaizCnpj(String raizCnpj)
    {
        this.raizCnpj = raizCnpj;
    }

    public String getXmlAssinado()
    {
        return xmlAssinado;
    }

    public void setXmlAssinado(String xmlAssinado)
    {
        this.xmlAssinado = xmlAssinado;
    }
}