/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.ws;

import java.io.File;

public class CertificadoDigital {
    public static void autenticarAcessoSSLA3(String trustStorePath, String trustStoreType, String trustStorePassword) throws Exception {
    }

    public static void autenticarAcessoSSL(String trustStorePath, String trustStoreType, String trustStorePassword) throws Exception {
    }

    public static void autenticarAcessoSSLA1(String keyStorePath, String keyStoreType, String keyStorePassword, String trustStorePath, String trustStoreType, String trustStorePassword) throws Exception {
    }

    public static void getCertificadoA1() throws Exception {
        String certPathCertificadoA1 = System.getProperty("user.dir");
        String keyStorePath = certPathCertificadoA1 + File.separator + "homologa.pfx";
        String keyStoreType = "PKCS12";
        String keyStorePassword = "1234";
        String trustStorePath = certPathCertificadoA1 + File.separator + "clientLocalhost.ts";
        String trustStoreType = "JCEKS";
        String trustStorePassword = "123456";
        CertificadoDigital.autenticarAcessoSSLA1(keyStorePath, keyStoreType, keyStorePassword, trustStorePath, trustStoreType, trustStorePassword);
    }

    public static void getCertificadoA3() throws Exception {
        String certPath = System.getProperty("user.dir");
        String trustStorePath = certPath + File.separator + "clientLocalhost.ts";
        String trustStoreType = "JCEKS";
        String trustStorePassword = "123456";
        CertificadoDigital.autenticarAcessoSSL(trustStorePath, trustStoreType, trustStorePassword);
    }
}

