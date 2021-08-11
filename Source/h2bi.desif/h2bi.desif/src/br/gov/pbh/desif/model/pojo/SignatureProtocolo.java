
package br.gov.pbh.desif.model.pojo;


public class SignatureProtocolo
{

    private String id;
    private String SignedInfo;
    private String SignatureValue;
    private String KeyInfo;

    public SignatureProtocolo()
    {
    }

    public String getKeyInfo()
    {
        return KeyInfo;
    }

    public void setKeyInfo(String KeyInfo)
    {
        this.KeyInfo = KeyInfo;
    }

    public String getSignatureValue()
    {
        return SignatureValue;
    }

    public void setSignatureValue(String SignatureValue)
    {
        this.SignatureValue = SignatureValue;
    }

    public String getSignedInfo()
    {
        return SignedInfo;
    }

    public void setSignedInfo(String SignedInfo)
    {
        this.SignedInfo = SignedInfo;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }
}