/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.service.arquivo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;

public class ManipuladorArquivo {
    private File file;
    private BufferedReader reader;
    private BufferedWriter writer;

    public ManipuladorArquivo(String diretorio, String arquivo) throws IOException {
        File dir = new File(diretorio);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        this.file = new File(dir, arquivo);
    }

    public ManipuladorArquivo(String arquivo) throws IOException {
        this.file = new File(arquivo);
        if (!this.file.exists()) {
            this.file.createNewFile();
        }
    }

    public boolean apagarArquivo() {
        return this.file.delete();
    }

    public void escreverArquivo(String conteudo) throws IOException {
        if (this.writer == null) {
            this.writer = new BufferedWriter(new FileWriter(this.file, true));
        }
        this.writer.write(conteudo);
        this.writer.newLine();
        this.writer.flush();
    }

    public String lerArquivo() throws IOException {
        if (this.reader == null) {
            this.reader = new BufferedReader(new InputStreamReader((InputStream)new FileInputStream(this.file), "UTF-8"));
        }
        String aux = null;
        StringBuilder string = new StringBuilder();
        while ((aux = this.reader.readLine()) != null) {
            string.append(new String(aux.getBytes("UTF-8"), "UTF-8") + "\n");
        }
        return new String(string.toString().getBytes("UTF-8"), "UTF-8");
    }

    public void liberarRecursos() throws IOException {
        if (this.reader != null) {
            this.reader.close();
            this.reader = null;
        }
        if (this.writer != null) {
            this.writer.close();
            this.writer = null;
        }
    }
}

