
package br.gov.pbh.desif.service.contexto;

import java.io.*;
import java.util.Properties;

public class ManipuladoraProperties
{

    private String caminhoArquivo;
    private Properties prop;

    public ManipuladoraProperties()
    {
        prop = new Properties();
    }

    public ManipuladoraProperties(String arquivo)
    {
        prop = new Properties();
        caminhoArquivo = arquivo;
    }

    public boolean carregarPropriedades()
        throws IOException
    {
        boolean carregado = false;
        File arquivo = new File(caminhoArquivo);
        if(arquivo.exists())
        {
            prop.load(new FileInputStream(arquivo));
            carregado = true;
        }
        return carregado;
    }

    public boolean alterarPropriedade(String chave, String novoValor)
        throws IOException
    {
        boolean alterado = false;
        File arquivo = new File(caminhoArquivo);
        if(!arquivo.exists())
        {
            File dir = new File(arquivo.getParent());
            dir.mkdirs();
            arquivo.createNewFile();
        }
        if(arquivo.canWrite())
        {
            prop.setProperty(chave, novoValor);
            prop.store(new FileOutputStream(arquivo), null);
            alterado = true;
        }
        return alterado;
    }

    public String obterPropriedade(String chave)
    {
        return prop.getProperty(chave);
    }

    public void setCaminhoArquivo(String caminhoArquivo)
    {
        this.caminhoArquivo = caminhoArquivo;
    }
}
