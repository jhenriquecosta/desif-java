package br.gov.pbh.desif.esec.assinatura;

import br.com.esec.sdk.crl.CRLManager;
import br.com.esec.sdk.crl.FileCRLRepository;
import java.io.File;

public class CRLManagerSingleton
{

    private String DESIF_CRLS_DIR;
    private static CRLManagerSingleton instance;
    private CRLManager crlManager;

    private CRLManagerSingleton()
    {
        DESIF_CRLS_DIR = (new StringBuilder()).append(System.getProperty("user.home")).append(File.separator).append(".desif").append(File.separator).append("lcrs").toString();
        try
        {
            File file = new File(DESIF_CRLS_DIR);
            if(!file.exists())
                file.mkdirs();
            crlManager = new CRLManager(new FileCRLRepository(DESIF_CRLS_DIR));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static CRLManagerSingleton getInstance()
    {
        if(instance == null)
            instance = new CRLManagerSingleton();
        return instance;
    }

    public CRLManager getCRLManager()
    {
        return crlManager;
    }
}