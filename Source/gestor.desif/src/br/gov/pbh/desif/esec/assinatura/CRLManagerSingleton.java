/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  br.com.esec.sdk.crl.CRLManager
 *  br.com.esec.sdk.crl.FileCRLRepository
 *  br.com.esec.sdk.crl.ICRLRepository
 */
package br.gov.pbh.desif.esec.assinatura;

import br.com.esec.sdk.crl.CRLManager;
import br.com.esec.sdk.crl.FileCRLRepository;
import br.com.esec.sdk.crl.ICRLRepository;
import java.io.File;

public class CRLManagerSingleton {
    private String DESIF_CRLS_DIR = System.getProperty("user.home") + File.separator + ".desif" + File.separator + "lcrs";
    private static CRLManagerSingleton instance;
    private CRLManager crlManager;

    private CRLManagerSingleton() {
        try {
            File file = new File(this.DESIF_CRLS_DIR);
            if (!file.exists()) {
                file.mkdirs();
            }
            this.crlManager = new CRLManager((ICRLRepository)new FileCRLRepository(this.DESIF_CRLS_DIR));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static CRLManagerSingleton getInstance() {
        if (instance == null) {
            instance = new CRLManagerSingleton();
        }
        return instance;
    }

    public CRLManager getCRLManager() {
        return this.crlManager;
    }
}

