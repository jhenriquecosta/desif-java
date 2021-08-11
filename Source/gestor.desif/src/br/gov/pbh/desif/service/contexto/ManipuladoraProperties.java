/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.service.contexto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class ManipuladoraProperties {
    private String caminhoArquivo;
    private Properties prop = new Properties();

    public ManipuladoraProperties() {
    }

    public ManipuladoraProperties(String arquivo) {
        this.caminhoArquivo = arquivo;
    }

    public boolean carregarPropriedades() throws IOException {
        boolean carregado = false;
        File arquivo = new File(this.caminhoArquivo);
        if (arquivo.exists()) {
            this.prop.load(new FileInputStream(arquivo));
            carregado = true;
        }
        return carregado;
    }

    public boolean alterarPropriedade(String chave, String novoValor) throws IOException {
        boolean alterado = false;
        File arquivo = new File(this.caminhoArquivo);
        if (!arquivo.exists()) {
            File dir = new File(arquivo.getParent());
            dir.mkdirs();
            arquivo.createNewFile();
        }
        if (arquivo.canWrite()) {
            this.prop.setProperty(chave, novoValor);
            this.prop.store(new FileOutputStream(arquivo), null);
            alterado = true;
        }
        return alterado;
    }

    public String obterPropriedade(String chave) {
        return this.prop.getProperty(chave);
    }

    public void setCaminhoArquivo(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }
}

