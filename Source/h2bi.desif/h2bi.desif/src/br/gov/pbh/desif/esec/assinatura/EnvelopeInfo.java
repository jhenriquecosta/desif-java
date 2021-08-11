
package br.gov.pbh.desif.esec.assinatura;

import java.util.List;

// Referenced classes of package br.gov.pbh.desif.esec.assinatura:
//            SignatureStatus, ContentType

public class EnvelopeInfo
{

    private String signatureFileName;
    private String contentFileName;
    private SignatureStatus signatureStatus;
    private ContentType contentType;
    private String digestType;
    private String digestValue;
    private List signatureList;

    public EnvelopeInfo(String contentFileName, String signatureFileName, SignatureStatus signatureStatus, ContentType contentType, String digestType, String digestValue, List signatureList)
    {
        this.contentFileName = contentFileName;
        this.signatureFileName = signatureFileName;
        this.signatureStatus = signatureStatus;
        this.contentType = contentType;
        this.digestType = digestType;
        this.digestValue = digestValue;
        this.signatureList = signatureList;
    }

    public String getContentFileName()
    {
        return contentFileName;
    }

    public String getSignatureFileName()
    {
        return signatureFileName;
    }

    public SignatureStatus getSignatureStatus()
    {
        return signatureStatus;
    }

    public ContentType getContentType()
    {
        return contentType;
    }

    public String getDigestType()
    {
        return digestType;
    }

    public String getDigestValue()
    {
        return digestValue;
    }

    public List getSignatureList()
    {
        return signatureList;
    }
}