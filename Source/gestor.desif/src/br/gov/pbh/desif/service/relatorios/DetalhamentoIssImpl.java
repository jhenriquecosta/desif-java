
package br.gov.pbh.desif.service.relatorios;

import br.gov.pbh.bhiss.componentes.guiaiss.TributoGuia;
import br.gov.pbh.des.componentes.guias.AbstractDetalhamentoISS;
import br.gov.pbh.desif.control.ControleFeriados;
import br.gov.pbh.desif.control.ControleIndiceMonet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DetalhamentoIssImpl
implements AbstractDetalhamentoISS {
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

    public void setAnoUltimoIndiceAtualizado(int anoUltimoIndiceAtualizado) {
        this.anoUltimoIndiceAtualizado = anoUltimoIndiceAtualizado;
    }

    public List getListaCompensacoes() {
        return null;
    }

    public String getRazaoSocial() {
        return this.razaoSocial;
    }

    public String getInscricaoMunicipal() {
        return this.inscricaoMunicipal;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public double[] getIndicesCorrecaoByDatas(Date referencia, Date pagamento) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Referencia " + df.format(referencia));
        System.out.println("Pagamento " + df.format(pagamento));
        return new ControleIndiceMonet().getIndicesCorrecaoByDatas(referencia, pagamento);
    }

    public double getIssqnDevido() {
        return this.issqnDevido;
    }

    public double getIssqnRetido() {
        return this.issqnRetido;
    }

    public double getIss() {
        return this.iss;
    }

    public double getValorCompensassao() {
        return this.valorCompensacao;
    }

    public double getValorIncentivo() {
        return this.valorIncentivo;
    }

    public List getIdNFs() {
        return null;
    }

    public String getTextoGuia() {
        return this.textoGuia;
    }

    public String getStrTipoGuia() {
        return this.strTipoGuia;
    }

    public int getTipoGuia() {
        return this.tipoGuia;
    }

    public Date getReferencia() {
        return this.referencia;
    }

    public double evtAddCompensacao() {
        return 0.0;
    }

    public double getValorCompensacao() {
        return this.valorCompensacao;
    }

    public Date[] getFeriados() {
        return new ControleFeriados().getFeriados();
    }

    public double getTaxaExpediente() {
        return this.taxaExpediente;
    }

    public String getNumeroGuia() {
        return this.numeroGuia;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void setInscricaoMunicipal(String inscricaoMunicipal) {
        this.inscricaoMunicipal = inscricaoMunicipal;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public void setIssqnDevido(double issqnDevido) {
        this.issqnDevido = issqnDevido;
    }

    public void setIssqnRetido(double issqnRetido) {
        this.issqnRetido = issqnRetido;
    }

    public void setIss(double iss) {
        this.iss = iss;
    }

    public void setReferencia(Date referencia) {
        this.referencia = referencia;
    }

    public void setStrTipoGuia(String strTipoGuia) {
        this.strTipoGuia = strTipoGuia;
    }

    public void setTaxaExpediente(double taxaExpediente) {
        this.taxaExpediente = taxaExpediente;
    }

    public void setTextoGuia(String textoGuia) {
        this.textoGuia = textoGuia;
    }

    public void setTipoGuia(int tipoGuia) {
        this.tipoGuia = tipoGuia;
    }

    public void setValorCompensacao(double valorCompensacao) {
        this.valorCompensacao = valorCompensacao;
    }

    public void setValorIncentivo(double valorIncentivo) {
        this.valorIncentivo = valorIncentivo;
    }

    public void setNumeroGuia(String numGuia) {
        this.numeroGuia = numGuia;
    }

    public double evtAddIncentivo() {
        return 0.0;
    }

    public List getListaIncentivos() {
        return new ArrayList();
    }

    public List<TributoGuia> getTributosGuia() {
        return new ArrayList<TributoGuia>();
    }

    public double evtAddDeducao() {
        return 0.0;
    }

    public double getValorDeducao() {
        return 0.0;
    }

    public List getListaDeducoes() {
        return new ArrayList();
    }

    public int getAnoUltimoIndiceAtualizado() {
        return this.anoUltimoIndiceAtualizado;
    }
}

