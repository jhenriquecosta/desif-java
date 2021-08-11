/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.database;

import br.gov.pbh.desif.service.arquivo.ManipuladorArquivo;
import java.io.IOException;
import java.io.PrintStream;

public class TesteDAO {
    private ManipuladorArquivo manipulaArq;

    public TesteDAO() {
        this.lerArq();
    }

    public void lerArq() {
        try {
            this.manipulaArq = new ManipuladorArquivo("C:\\Documents and Settings\\guilherme.diniz\\Meus documentos\\Guilherme\\Popular Banco\\EventoContabel.csv");
            String arq = this.manipulaArq.lerArquivo();
            String[] line = arq.split("\\\n", -1);
            for (int i = 0; i < line.length - 1; ++i) {
                String[] token = line[i].split("\\;", -1);
                System.out.println("==========================");
                System.out.println("Indice => " + i);
                System.out.println("Primeiro => " + token[0]);
                System.out.println("Primeiro => " + token[1]);
                System.out.println("==========================");
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TesteDAO t = new TesteDAO();
        t.lerArq();
    }
}

