package br.gov.pbh.desif.esec.assinatura;

// Referenced classes of package br.gov.pbh.desif.esec.assinatura:
//            Certificado

public class Assinatura
{

    private Certificado certificado;
    private boolean integra;

    public Assinatura()
    {
    }

    public Certificado getCertificado()
    {
        return certificado;
    }

    public void setCertificado(Certificado certificado)
    {
        this.certificado = certificado;
    }

    public boolean isIntegra()
    {
        return integra;
    }

    public void setIntegra(boolean integra)
    {
        this.integra = integra;
    }
}