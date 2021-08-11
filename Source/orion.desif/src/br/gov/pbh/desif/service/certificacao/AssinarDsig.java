
package br.gov.pbh.desif.service.certificacao;


public interface AssinarDsig
{

    public abstract String assinarDsig(String s, String s1, String s2)
        throws Exception;

    public abstract String assinarDsig(String s, String s1, String s2, String s3)
        throws Exception;

    public abstract String assinarDsigA3(String s, String s1, String s2)
        throws Exception;

    public abstract String assinarDsig(String s)
        throws Exception;

    public abstract byte[] assinarDsigP7s(byte abyte0[], String s)
        throws Exception;

    public abstract boolean verificarDsig(String s);
}
