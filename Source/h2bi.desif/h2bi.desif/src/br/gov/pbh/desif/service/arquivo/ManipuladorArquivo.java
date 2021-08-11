
package br.gov.pbh.desif.service.arquivo;

import java.io.*;

public class ManipuladorArquivo
{

    private File file;
    private BufferedReader reader;
    private BufferedWriter writer;

    public ManipuladorArquivo(String diretorio, String arquivo)
        throws IOException
    {
        File dir = new File(diretorio);
        if(!dir.exists())
            dir.mkdirs();
        file = new File(dir, arquivo);
    }

    public ManipuladorArquivo(String arquivo)
        throws IOException
    {
        file = new File(arquivo);
        if(!file.exists())
            file.createNewFile();
    }

    public boolean apagarArquivo()
    {
        return file.delete();
    }

    public void escreverArquivo(String conteudo)
        throws IOException
    {
        if(writer == null)
            writer = new BufferedWriter(new FileWriter(file, true));
        writer.write(conteudo);
        writer.newLine();
        writer.flush();
    }

    public String lerArquivo()
        throws IOException
    {
        if(reader == null)
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
        String aux = null;
        StringBuilder string = new StringBuilder();
        while((aux = reader.readLine()) != null) 
            string.append((new StringBuilder()).append(aux).append("\n").toString());
        return string.toString();
    }

    public void liberarRecursos()
        throws IOException
    {
        if(reader != null)
        {
            reader.close();
            reader = null;
        }
        if(writer != null)
        {
            writer.close();
            writer = null;
        }
    }
}
