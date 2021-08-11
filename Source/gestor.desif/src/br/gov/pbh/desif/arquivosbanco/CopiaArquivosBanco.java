/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.arquivosbanco;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class CopiaArquivosBanco {
    private String caminho;

    public CopiaArquivosBanco(String caminho) 
    {
        this.caminho = caminho;
    }

    public boolean copiar()
    {
        try
        {
            InputStream inScript = this.getClass().getResourceAsStream("/br/gov/pbh/desif/arquivosbanco/des_if.script");
            InputStream inProperties = this.getClass().getResourceAsStream("/br/gov/pbh/desif/arquivosbanco/des_if.properties");
            byte[] scriptAr = new byte[inScript.available()];
            byte[] propertiesAr = new byte[inProperties.available()];
            for (int i = 0; i < scriptAr.length; ++i) {
                inScript.read(scriptAr, i, 1);
            }
            inProperties.read(propertiesAr);
            FileOutputStream outScript = new FileOutputStream(this.caminho + File.separator + "des_if.script");
            FileOutputStream outProperties = new FileOutputStream(this.caminho + File.separator + "des_if.properties");
            outScript.write(scriptAr);
            outProperties.write(propertiesAr);
            inScript.close();
            inProperties.close();
            outScript.close();
            outProperties.close();
            return true;
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}

