

package br.gov.pbh.desif.model.pojo.base;

import java.io.Serializable;
import java.util.Date;

public abstract class AbstractProtocoloInfoComunsMunicipios
    implements Serializable
{

    private Long protocolo;
    private String cnpjInstituicao;
    private String nomeInstituicao;
    private Date periodoInicCompetDecl;
    private Date periodoFimCompetDecl;
    private Date dataEntrega;
    private Double versaoValidador;
    private String versaoTermoRef;
    private Short tipoDeclaracao;
    private int totalContasInformadas;
    private int totalContasMaisAnalitico;
    private int totalContasMaisAnaliticoTributaveis;
    private int qtdeSubtituloRegistro0200;
    private int qtdeSubtituloRegistro0300;

    public AbstractProtocoloInfoComunsMunicipios()
    {
    }

    public AbstractProtocoloInfoComunsMunicipios(Long protocolo, String cnpjInstituicao, String nomeInstituicao, Date periodoInicCompetDecl, Date periodoFimCompetDecl, Date dataEntrega, Double versaoValidador, 
            String versaoTermoRef, Short tipoDeclaracao, int totalContasInformadas, int totalContasMaisAnalitico, int totalContasMaisAnaliticoTributaveis, int qtdeSubtituloRegistro0200, int qtdeSubtituloRegistro0300)
    {
        this.protocolo = protocolo;
        this.cnpjInstituicao = cnpjInstituicao;
        this.nomeInstituicao = nomeInstituicao;
        this.periodoInicCompetDecl = periodoInicCompetDecl;
        this.periodoFimCompetDecl = periodoFimCompetDecl;
        this.dataEntrega = dataEntrega;
        this.versaoValidador = versaoValidador;
        this.versaoTermoRef = versaoTermoRef;
        this.tipoDeclaracao = tipoDeclaracao;
        this.totalContasInformadas = totalContasInformadas;
        this.totalContasMaisAnalitico = totalContasMaisAnalitico;
        this.totalContasMaisAnaliticoTributaveis = totalContasMaisAnaliticoTributaveis;
        this.qtdeSubtituloRegistro0200 = qtdeSubtituloRegistro0200;
        this.qtdeSubtituloRegistro0300 = qtdeSubtituloRegistro0300;
    }

    public String getCnpjInstituicao()
    {
        return cnpjInstituicao;
    }

    public void setCnpjInstituicao(String cnpjInstituicao)
    {
        this.cnpjInstituicao = cnpjInstituicao;
    }

    public Date getDataEntrega()
    {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega)
    {
        this.dataEntrega = dataEntrega;
    }

    public String getNomeInstituicao()
    {
        return nomeInstituicao;
    }

    public void setNomeInstituicao(String nomeInstituicao)
    {
        this.nomeInstituicao = nomeInstituicao;
    }

    public Date getPeriodoFimCompetDecl()
    {
        return periodoFimCompetDecl;
    }

    public void setPeriodoFimCompetDecl(Date periodoFimCompetDecl)
    {
        this.periodoFimCompetDecl = periodoFimCompetDecl;
    }

    public Date getPeriodoInicCompetDecl()
    {
        return periodoInicCompetDecl;
    }

    public void setPeriodoInicCompetDecl(Date periodoInicCompetDecl)
    {
        this.periodoInicCompetDecl = periodoInicCompetDecl;
    }

    public Long getProtocolo()
    {
        return protocolo;
    }

    public void setProtocolo(Long protocolo)
    {
        this.protocolo = protocolo;
    }

    public int getQtdeSubtituloRegistro0200()
    {
        return qtdeSubtituloRegistro0200;
    }

    public void setQtdeSubtituloRegistro0200(int qtdeSubtituloRegistro0200)
    {
        this.qtdeSubtituloRegistro0200 = qtdeSubtituloRegistro0200;
    }

    public int getQtdeSubtituloRegistro0300()
    {
        return qtdeSubtituloRegistro0300;
    }

    public void setQtdeSubtituloRegistro0300(int qtdeSubtituloRegistro0300)
    {
        this.qtdeSubtituloRegistro0300 = qtdeSubtituloRegistro0300;
    }

    public Short getTipoDeclaracao()
    {
        return tipoDeclaracao;
    }

    public void setTipoDeclaracao(Short tipoDeclaracao)
    {
        this.tipoDeclaracao = tipoDeclaracao;
    }

    public int getTotalContasInformadas()
    {
        return totalContasInformadas;
    }

    public void setTotalContasInformadas(int totalContasInformadas)
    {
        this.totalContasInformadas = totalContasInformadas;
    }

    public int getTotalContasMaisAnalitico()
    {
        return totalContasMaisAnalitico;
    }

    public void setTotalContasMaisAnalitico(int totalContasMaisAnalitico)
    {
        this.totalContasMaisAnalitico = totalContasMaisAnalitico;
    }

    public int getTotalContasMaisAnaliticoTributaveis()
    {
        return totalContasMaisAnaliticoTributaveis;
    }

    public void setTotalContasMaisAnaliticoTributaveis(int totalContasMaisAnaliticoTributaveis)
    {
        this.totalContasMaisAnaliticoTributaveis = totalContasMaisAnaliticoTributaveis;
    }

    public String getVersaoTermoRef()
    {
        return versaoTermoRef;
    }

    public void setVersaoTermoRef(String versaoTermoRef)
    {
        this.versaoTermoRef = versaoTermoRef;
    }

    public Double getVersaoValidador()
    {
        return versaoValidador;
    }

    public void setVersaoValidador(Double versaoValidador)
    {
        this.versaoValidador = versaoValidador;
    }
}