package br.gov.pbh.desif.arquivosbanco;

import java.io.*;

public class CopiaArquivosBanco
{

    final String caminho;

    public CopiaArquivosBanco(String caminho)
    {
        this.caminho = caminho;
    }

    public boolean copiar()
    {
        try
        {
            InputStream inScript = getClass().getResourceAsStream("/br/gov/pbh/desif/arquivosbanco/des_if.script");
            InputStream inProperties = getClass().getResourceAsStream("/br/gov/pbh/desif/arquivosbanco/des_if.properties");
            byte scriptAr[] = new byte[inScript.available()];
            byte propertiesAr[] = new byte[inProperties.available()];
            for(int i = 0; i < scriptAr.length; i++)
                inScript.read(scriptAr, i, 1);

            inProperties.read(propertiesAr);
            OutputStream outScript = new FileOutputStream((new StringBuilder()).append(caminho).append(File.separator).append("des_if.script").toString());
            OutputStream outProperties = new FileOutputStream((new StringBuilder()).append(caminho).append(File.separator).append("des_if.properties").toString());
            outScript.write(scriptAr);
            outProperties.write(propertiesAr);
            inScript.close();
            inProperties.close();
            outScript.close();
            outProperties.close();
            return true;
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
        return false;
    }
}