/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.service.arquivo;

import br.gov.pbh.desif.service.arquivo.ManipuladorArquivo;
import java.io.IOException;

public class CarregarArquivo {
    private ManipuladorArquivo manipulaArq;

    public String lerArquivo(String caminho) {
        try {
            this.manipulaArq = new ManipuladorArquivo(caminho);
            String arq = this.manipulaArq.lerArquivo();
            this.manipulaArq.liberarRecursos();
            return arq;
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}

