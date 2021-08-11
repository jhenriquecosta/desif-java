/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.service.certificacao;

import br.gov.pbh.desif.service.certificacao.CertificadoA1;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CertificadoA1JdbcDefault
implements CertificadoA1 {
    @Override
    public byte[] recuperarCertificadoA1() throws IOException {
        String certificadoClienteA1 = "E:/ssl/homologa.pfx";
        FileInputStream certificadoA1 = new FileInputStream(certificadoClienteA1);
        byte[] certificadoA1Byte = this.converterInputStreamByte(certificadoA1);
        return certificadoA1Byte;
    }

    public byte[] converterInputStreamByte(InputStream inputStream) throws IOException {
        byte[] data = null;
        data = new byte[inputStream.available()];
        int bytesLidos = 0;
        int soma = 0;
        int inicio = 0;
        int tamanho = data.length;
        while (tamanho > 0) {
            bytesLidos = inputStream.read(data, inicio, tamanho);
            inicio += bytesLidos;
            tamanho -= bytesLidos;
            soma += bytesLidos;
        }
        inputStream.close();
        return data;
    }
}

