

package br.gov.pbh.desif.model.pojo.base;

import java.io.Serializable;
import java.util.Date;

public abstract class AbstractIdentificacaoDeclaracao
    implements Serializable
{

    private Integer id;
    private String titulo;
    private Long cidade;
    private Integer linhaIdentificacaoDeclaracao;
    private String cnpjInstituicao;
    private String nomeInstituicao;
    private Date dataInicioCompetencia;
    private Date dataFimCompetencia;
    private Short moduloDeclaracao;
    private Short tipoDeclaracao;
    private Short tipoConsolidacao;
    private String cnpjResponsavelRecolhimento;
    private String protocoloDeclaracao;

    public AbstractIdentificacaoDeclaracao()
    {
    }

    public AbstractIdentificacaoDeclaracao(Integer id, String titulo, Long cidade, Integer linhaIdentificacaoDeclaracao, String cnpjInstituicao, String nomeInstituicao, Date dataInicioCompetencia, 
            Date dataFimCompetencia, Short moduloDeclaracao, Short tipoDeclaracao, Short tipoConsolidacao, String cnpjResponsavelRecolhimento, String protocoloDeclaracao)
    {
        this.id = id;
        this.titulo = titulo;
        this.cidade = cidade;
        this.linhaIdentificacaoDeclaracao = linhaIdentificacaoDeclaracao;
        this.cnpjInstituicao = cnpjInstituicao;
        this.nomeInstituicao = nomeInstituicao;
        this.dataInicioCompetencia = dataInicioCompetencia;
        this.dataFimCompetencia = dataFimCompetencia;
        this.moduloDeclaracao = moduloDeclaracao;
        this.tipoDeclaracao = tipoDeclaracao;
        this.tipoConsolidacao = tipoConsolidacao;
        this.cnpjResponsavelRecolhimento = cnpjResponsavelRecolhimento;
        this.protocoloDeclaracao = protocoloDeclaracao;
    }

    public AbstractIdentificacaoDeclaracao(Integer id, String titulo, Long cidade, Integer linhaIdentificacaoDeclaracao, String cnpjInstituicao, String nomeInstituicao, Date dataInicioCompetencia, 
            Date dataFimCompetencia, Short moduloDeclaracao, Short tipoDeclaracao, String cnpjResponsavelRecolhimento, String protocoloDeclaracao)
    {
        this.id = id;
        this.titulo = titulo;
        this.cidade = cidade;
        this.linhaIdentificacaoDeclaracao = linhaIdentificacaoDeclaracao;
        this.cnpjInstituicao = cnpjInstituicao;
        this.nomeInstituicao = nomeInstituicao;
        this.dataInicioCompetencia = dataInicioCompetencia;
        this.dataFimCompetencia = dataFimCompetencia;
        this.moduloDeclaracao = moduloDeclaracao;
        this.tipoDeclaracao = tipoDeclaracao;
        this.cnpjResponsavelRecolhimento = cnpjResponsavelRecolhimento;
        this.protocoloDeclaracao = protocoloDeclaracao;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getTitulo()
    {
        return titulo;
    }

    public void setTitulo(String titulo)
    {
        this.titulo = titulo;
    }

    public Long getCidade()
    {
        return cidade;
    }

    public void setCidade(Long cidade)
    {
        this.cidade = cidade;
    }

    public Integer getLinhaIdentificacaoDeclaracao()
    {
        return linhaIdentificacaoDeclaracao;
    }

    public void setLinhaIdentificacaoDeclaracao(Integer linhaIdentificacaoDeclaracao)
    {
        this.linhaIdentificacaoDeclaracao = linhaIdentificacaoDeclaracao;
    }

    public String getCnpjInstituicao()
    {
        return cnpjInstituicao;
    }

    public void setCnpjInstituicao(String cnpjInstituicao)
    {
        this.cnpjInstituicao = cnpjInstituicao;
    }

    public String getNomeInstituicao()
    {
        return nomeInstituicao;
    }

    public void setNomeInstituicao(String nomeInstituicao)
    {
        this.nomeInstituicao = nomeInstituicao;
    }

    public Date getDataInicioCompetencia()
    {
        return dataInicioCompetencia;
    }

    public void setDataInicioCompetencia(Date dataInicioCompetencia)
    {
        this.dataInicioCompetencia = dataInicioCompetencia;
    }

    public Date getDataFimCompetencia()
    {
        return dataFimCompetencia;
    }

    public void setDataFimCompetencia(Date dataFimCompetencia)
    {
        this.dataFimCompetencia = dataFimCompetencia;
    }

    public Short getModuloDeclaracao()
    {
        return moduloDeclaracao;
    }

    public void setModuloDeclaracao(Short moduloDeclaracao)
    {
        this.moduloDeclaracao = moduloDeclaracao;
    }

    public Short getTipoDeclaracao()
    {
        return tipoDeclaracao;
    }

    public void setTipoDeclaracao(Short tipoDeclaracao)
    {
        this.tipoDeclaracao = tipoDeclaracao;
    }

    public Short getTipoConsolidacao()
    {
        return tipoConsolidacao;
    }

    public void setTipoConsolidacao(Short tipoConsolidacao)
    {
        this.tipoConsolidacao = tipoConsolidacao;
    }

    public String getCnpjResponsavelRecolhimento()
    {
        return cnpjResponsavelRecolhimento;
    }

    public void setCnpjResponsavelRecolhimento(String cnpjResponsavelRecolhimento)
    {
        this.cnpjResponsavelRecolhimento = cnpjResponsavelRecolhimento;
    }

    public String getProtocoloDeclaracao()
    {
        return protocoloDeclaracao;
    }

    public void setProtocoloDeclaracao(String protocoloDeclaracao)
    {
        this.protocoloDeclaracao = protocoloDeclaracao;
    }

    public String toString()
    {
        String resp = (new StringBuilder()).append("Id= ").append(id).append("\ntitulo= ").append(titulo).append("\ncidade= ").append(cidade).append("\nlinha= ").append(linhaIdentificacaoDeclaracao).append("/ncnpjInstitui\347\343o= ").append(cnpjInstituicao).append("\nnome istitui\347\343o= ").append(nomeInstituicao).append("\n data inicio ").append(dataInicioCompetencia.toString()).append("\n data fim= ").append(dataFimCompetencia).append("\nmodulo declara\347\343o= ").append(moduloDeclaracao).append("\nTipo declara\347\343o= ").append(tipoDeclaracao).append("\nTipo Consolida\347\343o = ").append(tipoConsolidacao).append("\ncnpj resp recolhimento= ").append(cnpjResponsavelRecolhimento).append("\nprotocolo Declara\347\343o= ").append(protocoloDeclaracao).toString();
        return resp;
    }
}