/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.pojo;

public class SignatureProtocolo {
    private String id;
    private String SignedInfo;
    private String SignatureValue;
    private String KeyInfo;

    public String getKeyInfo() {
        return this.KeyInfo;
    }

    public void setKeyInfo(String KeyInfo) {
        this.KeyInfo = KeyInfo;
    }

    public String getSignatureValue() {
        return this.SignatureValue;
    }

    public void setSignatureValue(String SignatureValue) {
        this.SignatureValue = SignatureValue;
    }

    public String getSignedInfo() {
        return this.SignedInfo;
    }

    public void setSignedInfo(String SignedInfo) {
        this.SignedInfo = SignedInfo;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

