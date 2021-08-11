
package br.gov.pbh.desif.esec.assinatura;

import java.util.List;

public class ListaAssinaturas
{

    List assinaturas;
    private boolean assinaturasValidas;
    private boolean certificadosValidos;

    public ListaAssinaturas()
    {
    }

    public List getAssinaturas()
    {
        return assinaturas;
    }

    public void setAssinaturas(List assinaturas)
    {
        this.assinaturas = assinaturas;
    }

    public boolean isAssinaturasValidas()
    {
        return assinaturasValidas;
    }

    public void setAssinaturasValidas(boolean assinaturasValidas)
    {
        this.assinaturasValidas = assinaturasValidas;
    }

    public boolean isCertificadosValidos()
    {
        return certificadosValidos;
    }

    public void setCertificadosValidos(boolean certificadosValidos)
    {
        this.certificadosValidos = certificadosValidos;
    }
}