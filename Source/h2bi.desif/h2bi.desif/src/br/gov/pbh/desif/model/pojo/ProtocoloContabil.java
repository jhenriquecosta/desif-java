
package br.gov.pbh.desif.model.pojo;

import java.sql.Timestamp;

public class ProtocoloContabil
{

    private Long id;
    private String raizCnpj;
    private String nome;
    private String inicCompetDecl;
    private String fimCompetDecl;
    private Long numDepeInfo;
    private Long numDepeInfoBalanc;
    private Long numBalancInformados;
    private Long numDepeInfoRateio;
    private Long numRateioInformados;
    private Timestamp dataEntrega;
    private Double verValidador;
    private String verTermoRef;
    private Short tipoDeclaracao;
    private Short modulo;
    private String xmlAssinado;

    public ProtocoloContabil(Long id, String raizCnpj, String nome, String inicCompetDecl, String fimCompetDecl, Long numDepeInfo, Long numDepeInfoBalanc, 
            Long numBalancInformados, Long numDepeInfoRateio, Long numRateioInformados, Timestamp dataEntrega, Double verValidador, String verTermoRef, Short tipoDeclaracao, 
            Short modulo, String xmlAssinado)
    {
        this.id = id;
        this.raizCnpj = raizCnpj;
        this.nome = nome;
        this.inicCompetDecl = inicCompetDecl;
        this.fimCompetDecl = fimCompetDecl;
        this.numDepeInfo = numDepeInfo;
        this.numDepeInfoBalanc = numDepeInfoBalanc;
        this.numBalancInformados = numBalancInformados;
        this.numDepeInfoRateio = numDepeInfoRateio;
        this.numRateioInformados = numRateioInformados;
        this.dataEntrega = dataEntrega;
        this.verValidador = verValidador;
        this.verTermoRef = verTermoRef;
        this.tipoDeclaracao = tipoDeclaracao;
        this.modulo = modulo;
        this.xmlAssinado = xmlAssinado;
    }

    public ProtocoloContabil()
    {
    }

    public Timestamp getDataEntrega()
    {
        return dataEntrega;
    }

    public void setDataEntrega(Timestamp dataEntrega)
    {
        this.dataEntrega = dataEntrega;
    }

    public String getFimCompetDecl()
    {
        return fimCompetDecl;
    }

    public void setFimCompetDecl(String fimCompetDecl)
    {
        this.fimCompetDecl = fimCompetDecl;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getInicCompetDecl()
    {
        return inicCompetDecl;
    }

    public void setInicCompetDecl(String inicCompetDecl)
    {
        this.inicCompetDecl = inicCompetDecl;
    }

    public Short getModulo()
    {
        return modulo;
    }

    public void setModulo(Short modulo)
    {
        this.modulo = modulo;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public Long getNumBalancInformados()
    {
        return numBalancInformados;
    }

    public void setNumBalancInformados(Long numBalancInformados)
    {
        this.numBalancInformados = numBalancInformados;
    }

    public Long getNumDepeInfo()
    {
        return numDepeInfo;
    }

    public void setNumDepeInfo(Long numDepeInfo)
    {
        this.numDepeInfo = numDepeInfo;
    }

    public Long getNumDepeInfoBalanc()
    {
        return numDepeInfoBalanc;
    }

    public void setNumDepeInfoBalanc(Long numDepeInfoBalanc)
    {
        this.numDepeInfoBalanc = numDepeInfoBalanc;
    }

    public Long getNumDepeInfoRateio()
    {
        return numDepeInfoRateio;
    }

    public void setNumDepeInfoRateio(Long numDepeInfoRateio)
    {
        this.numDepeInfoRateio = numDepeInfoRateio;
    }

    public Long getNumRateioInformados()
    {
        return numRateioInformados;
    }

    public void setNumRateioInformados(Long numRateioInformados)
    {
        this.numRateioInformados = numRateioInformados;
    }

    public String getRaizCnpj()
    {
        return raizCnpj;
    }

    public void setRaizCnpj(String raizCnpj)
    {
        this.raizCnpj = raizCnpj;
    }

    public Short getTipoDeclaracao()
    {
        return tipoDeclaracao;
    }

    public void setTipoDeclaracao(Short tipoDeclaracao)
    {
        this.tipoDeclaracao = tipoDeclaracao;
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
}