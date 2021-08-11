/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.service.certificacao;

public interface AssinarDsig {
    public String assinarDsig(String var1, String var2, String var3) throws Exception;

    public String assinarDsig(String var1, String var2, String var3, String var4) throws Exception;

    public String assinarDsigA3(String var1, String var2, String var3) throws Exception;

    public String assinarDsig(String var1) throws Exception;

    public byte[] assinarDsigP7s(byte[] var1, String var2) throws Exception;

    public boolean verificarDsig(String var1);
}

