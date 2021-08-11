/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.esec.assinatura;

import br.gov.pbh.desif.esec.assinatura.ContentType;
import br.gov.pbh.desif.esec.assinatura.SignatureInfo;
import br.gov.pbh.desif.esec.assinatura.SignatureStatus;
import java.util.List;

public class EnvelopeInfo {
    private String signatureFileName;
    private String contentFileName;
    private SignatureStatus signatureStatus;
    private ContentType contentType;
    private String digestType;
    private String digestValue;
    private List<SignatureInfo> signatureList;

    public EnvelopeInfo(String contentFileName, String signatureFileName, SignatureStatus signatureStatus, ContentType contentType, String digestType, String digestValue, List<SignatureInfo> signatureList) {
        this.contentFileName = contentFileName;
        this.signatureFileName = signatureFileName;
        this.signatureStatus = signatureStatus;
        this.contentType = contentType;
        this.digestType = digestType;
        this.digestValue = digestValue;
        this.signatureList = signatureList;
    }

    public String getContentFileName() {
        return this.contentFileName;
    }

    public String getSignatureFileName() {
        return this.signatureFileName;
    }

    public SignatureStatus getSignatureStatus() {
        return this.signatureStatus;
    }

    public ContentType getContentType() {
        return this.contentType;
    }

    public String getDigestType() {
        return this.digestType;
    }

    public String getDigestValue() {
        return this.digestValue;
    }

    public List<SignatureInfo> getSignatureList() {
        return this.signatureList;
    }
}

