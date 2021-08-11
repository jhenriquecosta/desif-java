
package br.gov.pbh.desif.ws.cliente;

import java.util.Date;

public class ProtocoloICM
{

    protected Date dataEntrega;
    protected String fimCompetDecl;
    protected Long id;
    protected String inicCompetDecl;
    protected Short modulo;
    protected String nome;
    protected Long qtdeSubtituloReg0200;
    protected Long qtdeSubtituloReg0300;
    protected String raizCnpj;
    protected Short tipoDeclaracao;
    protected Long totalContasInfo;
    protected Long totalContasMaisAnalit;
    protected Long totalContasMaisAnalitTrib;
    protected String verTermoRef;
    protected Double verValidador;
    protected String xmlAssinado;

    public ProtocoloICM()
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

    public Long getQtdeSubtituloReg0200()
    {
        return qtdeSubtituloReg0200;
    }

    public void setQtdeSubtituloReg0200(Long value)
    {
        qtdeSubtituloReg0200 = value;
    }

    public Long getQtdeSubtituloReg0300()
    {
        return qtdeSubtituloReg0300;
    }

    public void setQtdeSubtituloReg0300(Long value)
    {
        qtdeSubtituloReg0300 = value;
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

    public Long getTotalContasInfo()
    {
        return totalContasInfo;
    }

    public void setTotalContasInfo(Long value)
    {
        totalContasInfo = value;
    }

    public Long getTotalContasMaisAnalit()
    {
        return totalContasMaisAnalit;
    }

    public void setTotalContasMaisAnalit(Long value)
    {
        totalContasMaisAnalit = value;
    }

    public Long getTotalContasMaisAnalitTrib()
    {
        return totalContasMaisAnalitTrib;
    }

    public void setTotalContasMaisAnalitTrib(Long value)
    {
        totalContasMaisAnalitTrib = value;
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
