/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.database;

import br.gov.pbh.desif.dao.ErroDao;
import br.gov.pbh.desif.model.pojo.Erro;
import br.gov.pbh.desif.service.arquivo.ManipuladorArquivo;
import br.gov.pbh.desif.service.contexto.Contexto;
import java.io.PrintStream;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PopularBanco {
    private ManipuladorArquivo manipulaArq;
    private Erro er;
    private ErroDao dao;

    public PopularBanco() 
    {
        this.lerArq();
    }

    public void lerArq() {
        try {
            this.dao = (ErroDao)Contexto.getObject("erroDao");
            this.manipulaArq = new ManipuladorArquivo("C:\\Documents and Settings\\guilherme.diniz\\Meus documentos\\Desif\\Popular Banco\\Erros.csv");
            String arq = this.manipulaArq.lerArquivo();
            System.out.println("O que tem no arquivo = > " + arq);
            String[] line = arq.split("\\n");
            System.out.println("tamanho de linhas => " + line.length);
            System.out.println("linha 0 => " + line[0]);
            for (int i = 0; i < line.length - 1; ++i) {
                String[] token = line[i].split(";");
                if (token.length == 2 || token.length == 3) {
                    // empty if block
                }
                System.out.println("==========================");
                System.out.println("Indice => " + i);
                System.out.println("codigo => " + this.er.getId());
                System.out.println("mensagem => " + this.er.getMensagem());
                System.out.println("motivo => " + this.er.getMotivo());
                System.out.println("==========================");
                this.dao.save(this.er);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Date parseData(String data) {
        try {
            Locale ptBr = new Locale("pt", "BR");
            SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yy", ptBr);
            Date d = sdf1.parse(data);
            return d;
        }
        catch (ParseException ex) {
            return null;
        }
    }

    public static void main(String[] args) {
        PopularBanco p = new PopularBanco();
    }
}

