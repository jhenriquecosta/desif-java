
package br.gov.pbh.desif.ws.xml.vo.tiposcomplexos;


public class MensagemRetornoVO
{

    private String detalhe;
    private String codigo;
    private String mensagem;

    public MensagemRetornoVO()
    {
    }

    public String getCodigo()
    {
        return codigo;
    }

    public void setCodigo(String codigo)
    {
        this.codigo = codigo;
    }

    public String getMensagem()
    {
        return mensagem;
    }

    public void setMensagem(String mensagem)
    {
        this.mensagem = mensagem;
    }

    public String getDetalhe()
    {
        return detalhe;
    }

    public void setDetalhe(String detalhe)
    {
        this.detalhe = detalhe;
    }
}
