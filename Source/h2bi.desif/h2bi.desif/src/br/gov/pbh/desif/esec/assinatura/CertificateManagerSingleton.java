package br.gov.pbh.desif.esec.assinatura;

import br.com.esec.sdk.certificate.ICPBrasilCACertificates;
import br.com.esec.sdk.manager.CertificateManager;
import br.com.esec.sdk.manager.DirectoryCertificateManager;
import java.io.File;

public class CertificateManagerSingleton
{

    private static CertificateManagerSingleton instance;
    private CertificateManager certManager;
    private String DESIF_CERTS_DIR;

    private CertificateManagerSingleton()
    {
        DESIF_CERTS_DIR = (new StringBuilder()).append(System.getProperty("user.home")).append(File.separator).append(".desif").append(File.separator).append("certificados-ac").toString();
        try
        {
            CertificateManager certManager = new DirectoryCertificateManager(DESIF_CERTS_DIR);
            certManager.load("".toCharArray());
            ICPBrasilCACertificates icpBrCaCerts = ICPBrasilCACertificates.getInstance();
            icpBrCaCerts.setOnlineUpdate(false);
            icpBrCaCerts.addCertificatePath(certManager.getCertificatePath());
            this.certManager = certManager;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static CertificateManagerSingleton getInstance()
    {
        if(instance == null)
            instance = new CertificateManagerSingleton();
        return instance;
    }

    public CertificateManager getCertificateManager()
    {
        return certManager;
    }
}