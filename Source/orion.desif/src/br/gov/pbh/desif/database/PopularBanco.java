
package br.gov.pbh.desif.database;

import br.gov.pbh.desif.dao.ErroDao;
import br.gov.pbh.desif.model.pojo.Erro;
import br.gov.pbh.desif.service.arquivo.ManipuladorArquivo;
import br.gov.pbh.desif.service.contexto.Contexto;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PopularBanco
{

    private ManipuladorArquivo manipulaArq;
    private Erro er;
    private ErroDao dao;

    public PopularBanco()
    {
        lerArq();
    }

    public void lerArq()
    {
        try
        {
            dao = (ErroDao)Contexto.getObject("erroDao");
            manipulaArq = new ManipuladorArquivo("C:\\Documents and Settings\\guilherme.diniz\\Meus documentos\\Desif\\Popular Banco\\Erros.csv");
            String arq = manipulaArq.lerArquivo();
            System.out.println((new StringBuilder()).append("O que tem no arquivo = > ").append(arq).toString());
            String line[] = arq.split("\\n");
            System.out.println((new StringBuilder()).append("tamanho de linhas => ").append(line.length).toString());
            System.out.println((new StringBuilder()).append("linha 0 => ").append(line[0]).toString());
            for(int i = 0; i < line.length - 1; i++)
            {
                String token[] = line[i].split(";");
                if(token.length != 2)
                    if(token.length != 3);
                System.out.println("==========================");
                System.out.println((new StringBuilder()).append("Indice => ").append(i).toString());
                System.out.println((new StringBuilder()).append("codigo => ").append(er.getId()).toString());
                System.out.println((new StringBuilder()).append("mensagem => ").append(er.getMensagem()).toString());
                System.out.println((new StringBuilder()).append("motivo => ").append(er.getMotivo()).toString());
                System.out.println("==========================");
                dao.save(er);
            }

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public Date parseData(String data)
    {
        try
        {
            Locale ptBr = new Locale("pt", "BR");
            SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yy", ptBr);
            Date d = sdf1.parse(data);
            return d;
        }
        catch(ParseException ex)
        {
            return null;
        }
    }

    public static void main(String args[])
    {
        PopularBanco p = new PopularBanco();
    }
}