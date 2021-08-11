
package br.gov.pbh.desif.service.certificacao;

import java.io.*;

// Referenced classes of package br.gov.pbh.desif.service.certificacao:
//            CertificadoA1

public class CertificadoA1JdbcDefault
    implements CertificadoA1
{

    public CertificadoA1JdbcDefault()
    {
    }

    public byte[] recuperarCertificadoA1()
        throws IOException
    {
        String certificadoClienteA1 = "E:/ssl/homologa.pfx";
        InputStream certificadoA1 = new FileInputStream(certificadoClienteA1);
        byte certificadoA1Byte[] = converterInputStreamByte(certificadoA1);
        return certificadoA1Byte;
    }

    public byte[] converterInputStreamByte(InputStream inputStream)
        throws IOException
    {
        byte data[] = null;
        data = new byte[inputStream.available()];
        int bytesLidos = 0;
        int soma = 0;
        int inicio = 0;
        for(int tamanho = data.length; tamanho > 0;)
        {
            bytesLidos = inputStream.read(data, inicio, tamanho);
            inicio += bytesLidos;
            tamanho -= bytesLidos;
            soma += bytesLidos;
        }

        inputStream.close();
        return data;
    }
}
