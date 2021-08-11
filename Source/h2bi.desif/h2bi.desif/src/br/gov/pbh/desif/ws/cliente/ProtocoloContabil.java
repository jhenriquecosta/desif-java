
package br.gov.pbh.desif.ws.cliente;

import java.util.Date;

public class ProtocoloContabil
{

    protected Date dataEntrega;
    protected String fimCompetDecl;
    protected Long id;
    protected String inicCompetDecl;
    protected Short modulo;
    protected String nome;
    protected Long numBalancInformados;
    protected Long numDepeInfo;
    protected Long numDepeInfoBalanc;
    protected Long numDepeInfoRateio;
    protected Long numRateioInformados;
    protected String raizCnpj;
    protected Short tipoDeclaracao;
    protected String verTermoRef;
    protected Double verValidador;
    protected String xmlAssinado;

    public ProtocoloContabil()
    {
    }

    public Date getDataEntrega()
    {
        return dataEntrega;
    }

    public void setDataEntrega(Date value)
    {
        dataEntrega = value;
    }

    public String getFimCompetDecl()
    {
        return fimCompetDecl;
    }

    public void setFimCompetDecl(String value)
    {
        fimCompetDecl = value;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long value)
    {
        id = value;
    }

    public String getInicCompetDecl()
    {
        return inicCompetDecl;
    }

    public void setInicCompetDecl(String value)
    {
        inicCompetDecl = value;
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

    public Long getNumBalancInformados()
    {
        return numBalancInformados;
    }

    public void setNumBalancInformados(Long value)
    {
        numBalancInformados = value;
    }

    public Long getNumDepeInfo()
    {
        return numDepeInfo;
    }

    public void setNumDepeInfo(Long value)
    {
        numDepeInfo = value;
    }

    public Long getNumDepeInfoBalanc()
    {
        return numDepeInfoBalanc;
    }

    public void setNumDepeInfoBalanc(Long value)
    {
        numDepeInfoBalanc = value;
    }

    public Long getNumDepeInfoRateio()
    {
        return numDepeInfoRateio;
    }

    public void setNumDepeInfoRateio(Long value)
    {
        numDepeInfoRateio = value;
    }

    public Long getNumRateioInformados()
    {
        return numRateioInformados;
    }

    public void setNumRateioInformados(Long value)
    {
        numRateioInformados = value;
    }

    public String getRaizCnpj()
    {
        return raizCnpj;
    }

    public void setRaizCnpj(String value)
    {
        raizCnpj = value;
    }

    public Short getTipoDeclaracao()
    {
        return tipoDeclaracao;
    }

    public void setTipoDeclaracao(Short value)
    {
        tipoDeclaracao = value;
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
