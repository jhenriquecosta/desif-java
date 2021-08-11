
package br.gov.pbh.desif.ws;

import java.io.File;

public class CertificadoDigital
{

    public CertificadoDigital()
    {
    }

    public static void autenticarAcessoSSLA3(String s, String s1, String s2)
        throws Exception
    {
    }

    public static void autenticarAcessoSSL(String s, String s1, String s2)
        throws Exception
    {
    }

    public static void autenticarAcessoSSLA1(String s, String s1, String s2, String s3, String s4, String s5)
        throws Exception
    {
    }

    public static void getCertificadoA1()
        throws Exception
    {
        String certPathCertificadoA1 = System.getProperty("user.dir");
        String keyStorePath = (new StringBuilder()).append(certPathCertificadoA1).append(File.separator).append("homologa.pfx").toString();
        String keyStoreType = "PKCS12";
        String keyStorePassword = "1234";
        String trustStorePath = (new StringBuilder()).append(certPathCertificadoA1).append(File.separator).append("clientLocalhost.ts").toString();
        String trustStoreType = "JCEKS";
        String trustStorePassword = "123456";
        autenticarAcessoSSLA1(keyStorePath, keyStoreType, keyStorePassword, trustStorePath, trustStoreType, trustStorePassword);
    }

    public static void getCertificadoA3()
        throws Exception
    {
        String certPath = System.getProperty("user.dir");
        String trustStorePath = (new StringBuilder()).append(certPath).append(File.separator).append("clientLocalhost.ts").toString();
        String trustStoreType = "JCEKS";
        String trustStorePassword = "123456";
        autenticarAcessoSSL(trustStorePath, trustStoreType, trustStorePassword);
    }
}
