

package br.gov.pbh.desif.service.arquivo;

import java.io.IOException;

// Referenced classes of package br.gov.pbh.desif.service.arquivo:
//            ManipuladorArquivo

public class CarregarArquivo
{

    private ManipuladorArquivo manipulaArq;

    public CarregarArquivo()
    {
    }

    public String lerArquivo(String caminho)
    {
        try
        {
            manipulaArq = new ManipuladorArquivo(caminho);
            String arq = manipulaArq.lerArquivo();
            manipulaArq.liberarRecursos();
            return arq;
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
}
