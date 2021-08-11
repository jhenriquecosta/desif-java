
package br.gov.pbh.desif.service.relatorios;

import br.gov.pbh.des.componentes.guias.AbstractDetalhamentoISS;
import br.gov.pbh.desif.control.ControleFeriados;
import br.gov.pbh.desif.control.ControleIndiceMonet;
import java.util.*;

public class DetalhamentoIssImpl
    implements AbstractDetalhamentoISS
{

    private String razaoSocial;
    private String inscricaoMunicipal;
    private String cnpj;
    private double issqnDevido;
    private double issqnRetido;
    private double iss;
    private double valorCompensacao;
    private double valorIncentivo;
    private String textoGuia;
    private String strTipoGuia;
    private int tipoGuia;
    private Date referencia;
    private double taxaExpediente;
    private String numeroGuia;
    private int anoUltimoIndiceAtualizado;

    public DetalhamentoIssImpl()
    {
    }

    public void setAnoUltimoIndiceAtualizado(int anoUltimoIndiceAtualizado)
    {
        this.anoUltimoIndiceAtualizado = anoUltimoIndiceAtualizado;
    }

    public List getListaCompensacoes()
    {
        return null;
    }

    public String getRazaoSocial()
    {
        return razaoSocial;
    }

    public String getInscricaoMunicipal()
    {
        return inscricaoMunicipal;
    }

    public String getCnpj()
    {
        return cnpj;
    }

    public double[] getIndicesCorrecaoByDatas(Date referencia, Date pagamento)
    {
        return (new ControleIndiceMonet()).getIndicesCorrecaoByDatas(referencia, pagamento);
    }

    public double getIssqnDevido()
    {
        return issqnDevido;
    }

    public double getIssqnRetido()
    {
        return issqnRetido;
    }

    public double getIss()
    {
        return iss;
    }

    public double getValorCompensassao()
    {
        return valorCompensacao;
    }

    public double getValorIncentivo()
    {
        return valorIncentivo;
    }

    public List getIdNFs()
    {
        return null;
    }

    public String getTextoGuia()
    {
        return textoGuia;
    }

    public String getStrTipoGuia()
    {
        return strTipoGuia;
    }

    public int getTipoGuia()
    {
        return tipoGuia;
    }

    public Date getReferencia()
    {
        return referencia;
    }

    public double evtAddCompensacao()
    {
        return 0.0D;
    }

    public double getValorCompensacao()
    {
        return valorCompensacao;
    }

    public Date[] getFeriados()
    {
        return (new ControleFeriados()).getFeriados();
    }

    public double getTaxaExpediente()
    {
        return taxaExpediente;
    }

    public String getNumeroGuia()
    {
        return numeroGuia;
    }

    public void setCnpj(String cnpj)
    {
        this.cnpj = cnpj;
    }

    public void setInscricaoMunicipal(String inscricaoMunicipal)
    {
        this.inscricaoMunicipal = inscricaoMunicipal;
    }

    public void setRazaoSocial(String razaoSocial)
    {
        this.razaoSocial = razaoSocial;
    }

    public void setIssqnDevido(double issqnDevido)
    {
        this.issqnDevido = issqnDevido;
    }

    public void setIssqnRetido(double issqnRetido)
    {
        this.issqnRetido = issqnRetido;
    }

    public void setIss(double iss)
    {
        this.iss = iss;
    }

    public void setReferencia(Date referencia)
    {
        this.referencia = referencia;
    }

    public void setStrTipoGuia(String strTipoGuia)
    {
        this.strTipoGuia = strTipoGuia;
    }

    public void setTaxaExpediente(double taxaExpediente)
    {
        this.taxaExpediente = taxaExpediente;
    }

    public void setTextoGuia(String textoGuia)
    {
        this.textoGuia = textoGuia;
    }

    public void setTipoGuia(int tipoGuia)
    {
        this.tipoGuia = tipoGuia;
    }

    public void setValorCompensacao(double valorCompensacao)
    {
        this.valorCompensacao = valorCompensacao;
    }

    public void setValorIncentivo(double valorIncentivo)
    {
        this.valorIncentivo = valorIncentivo;
    }

    public void setNumeroGuia(String numGuia)
    {
        numeroGuia = numGuia;
    }

    public double evtAddIncentivo()
    {
        return 0.0D;
    }

    public List getListaIncentivos()
    {
        return new ArrayList();
    }

    public List getTributosGuia()
    {
        return new ArrayList();
    }

    public double evtAddDeducao()
    {
        return 0.0D;
    }

    public double getValorDeducao()
    {
        return 0.0D;
    }

    public List getListaDeducoes()
    {
        return new ArrayList();
    }

    public int getAnoUltimoIndiceAtualizado()
    {
        return anoUltimoIndiceAtualizado;
    }
}
