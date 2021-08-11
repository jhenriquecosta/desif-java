/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  br.com.esec.sdk.certificate.CertificatePath
 *  br.com.esec.sdk.certificate.ICPBrasilCACertificates
 *  br.com.esec.sdk.manager.CertificateManager
 *  br.com.esec.sdk.manager.DirectoryCertificateManager
 */
package br.gov.pbh.desif.esec.assinatura;

import br.com.esec.sdk.certificate.CertificatePath;
import br.com.esec.sdk.certificate.ICPBrasilCACertificates;
import br.com.esec.sdk.manager.CertificateManager;
import br.com.esec.sdk.manager.DirectoryCertificateManager;
import java.io.File;

public class CertificateManagerSingleton {
    private static CertificateManagerSingleton instance;
    private CertificateManager certManager;
    private String DESIF_CERTS_DIR = System.getProperty("user.home") + File.separator + ".desif" + File.separator + "certificados-ac";

    private CertificateManagerSingleton() {
        try {
            DirectoryCertificateManager certManager = new DirectoryCertificateManager(this.DESIF_CERTS_DIR);
            certManager.load("".toCharArray());
            ICPBrasilCACertificates icpBrCaCerts = ICPBrasilCACertificates.getInstance();
            icpBrCaCerts.setOnlineUpdate(false);
            icpBrCaCerts.addCertificatePath(certManager.getCertificatePath());
            this.certManager = certManager;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static CertificateManagerSingleton getInstance() {
        if (instance == null) {
            instance = new CertificateManagerSingleton();
        }
        return instance;
    }

    public CertificateManager getCertificateManager() {
        return this.certManager;
    }
}

