

package br.gov.pbh.desif.model.pojo;

import java.sql.Timestamp;

public class ProtocoloICM
{

    private Long id;
    private String raizCnpj;
    private String nome;
    private String inicCompetDecl;
    private String fimCompetDecl;
    private Timestamp dataEntrega;
    private Double verValidador;
    private String verTermoRef;
    private Short tipoDeclaracao;
    private Short modulo;
    private String xmlAssinado;
    private Long totalContasInfo;
    private Long totalContasMaisAnalit;
    private Long totalContasMaisAnalitTrib;
    private Long qtdeSubtituloReg0200;
    private Long qtdeSubtituloReg0300;

    public ProtocoloICM(Long id, String raizCnpj, String nome, String inicCompetDecl, String fimCompetDecl, Timestamp dataEntrega, Double verValidador, 
            String verTermoRef, Short tipoDeclaracao, Short modulo, String xmlAssinado, Long totalContasInfo, Long totalContasMaisAnalit, Long totalContasMaisAnalitTrib, 
            Long qtdeSubtituloReg0200, Long qtdeSubtituloReg0300)
    {
        this.id = id;
        this.raizCnpj = raizCnpj;
        this.nome = nome;
        this.inicCompetDecl = inicCompetDecl;
        this.fimCompetDecl = fimCompetDecl;
        this.dataEntrega = dataEntrega;
        this.verValidador = verValidador;
        this.verTermoRef = verTermoRef;
        this.tipoDeclaracao = tipoDeclaracao;
        this.modulo = modulo;
        this.xmlAssinado = xmlAssinado;
        this.totalContasInfo = totalContasInfo;
        this.totalContasMaisAnalit = totalContasMaisAnalit;
        this.totalContasMaisAnalitTrib = totalContasMaisAnalitTrib;
        this.qtdeSubtituloReg0200 = qtdeSubtituloReg0200;
        this.qtdeSubtituloReg0300 = qtdeSubtituloReg0300;
    }

    public ProtocoloICM()
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

    public Long getQtdeSubtituloReg0200()
    {
        return qtdeSubtituloReg0200;
    }

    public void setQtdeSubtituloReg0200(Long qtdeSubtituloReg0200)
    {
        this.qtdeSubtituloReg0200 = qtdeSubtituloReg0200;
    }

    public Long getQtdeSubtituloReg0300()
    {
        return qtdeSubtituloReg0300;
    }

    public void setQtdeSubtituloReg0300(Long qtdeSubtituloReg0300)
    {
        this.qtdeSubtituloReg0300 = qtdeSubtituloReg0300;
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

    public Long getTotalContasInfo()
    {
        return totalContasInfo;
    }

    public void setTotalContasInfo(Long totalContasInfo)
    {
        this.totalContasInfo = totalContasInfo;
    }

    public Long getTotalContasMaisAnalit()
    {
        return totalContasMaisAnalit;
    }

    public void setTotalContasMaisAnalit(Long totalContasMaisAnalit)
    {
        this.totalContasMaisAnalit = totalContasMaisAnalit;
    }

    public Long getTotalContasMaisAnalitTrib()
    {
        return totalContasMaisAnalitTrib;
    }

    public void setTotalContasMaisAnalitTrib(Long totalContasMaisAnalitTrib)
    {
        this.totalContasMaisAnalitTrib = totalContasMaisAnalitTrib;
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