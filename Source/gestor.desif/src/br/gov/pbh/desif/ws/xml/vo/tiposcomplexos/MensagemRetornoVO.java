/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.ws.xml.vo.tiposcomplexos;

public class MensagemRetornoVO {
    private String detalhe;
    private String codigo;
    private String mensagem;

    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getMensagem() {
        return this.mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getDetalhe() {
        return this.detalhe;
    }

    public void setDetalhe(String detalhe) {
        this.detalhe = detalhe;
    }
}

