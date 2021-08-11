
package br.gov.pbh.desif.esec.assinatura;

import br.com.esec.pkix.x509.X509CertificateImpl;
import br.com.esec.sdk.xmldsig.*;
import br.com.esec.version.SDKVersion;
import br.gov.pbh.desif.esec.config.AddLicense;
import java.io.ByteArrayInputStream;
import java.io.PrintStream;
import java.util.*;
import org.w3c.dom.Document;

// Referenced classes of package br.gov.pbh.desif.esec.assinatura:
//            ListaAssinaturas, Assinatura, Certificado

public class VerificadorAssinatura
{

    public VerificadorAssinatura()
    {
    }

    public static ListaAssinaturas verificarXml(String xml)
    {
        ListaAssinaturas listaAssinaturas = new ListaAssinaturas();
        try
        {
            AddLicense.checkLicense();
            listaAssinaturas.setAssinaturasValidas(true);
            listaAssinaturas.setCertificadosValidos(true);
            List assinaturas = new LinkedList();
            SignatureEsec.setProvider("J128");
            String version = SDKVersion.getVersion();
            System.out.println((new StringBuilder()).append("------------------------------").append(version).toString());
            Document xmlDocument = XMLUtil.loadXMLFile(new ByteArrayInputStream(xml.getBytes()));
            XMLSignatureVerifier verifier = new XMLSignatureVerifier(xmlDocument);
            List verificationResults = verifier.verify();
            Assinatura assinatura;
            for(Iterator i$ = verificationResults.iterator(); i$.hasNext(); assinaturas.add(assinatura))
            {
                VerificationResult result = (VerificationResult)i$.next();
                assinatura = new Assinatura();
                if(result.isSignatureOk())
                {
                    assinatura.setCertificado(new Certificado(new X509CertificateImpl(result.getSignerCert()), Calendar.getInstance().getTime()));
                    if(!assinatura.getCertificado().isValido())
                        listaAssinaturas.setCertificadosValidos(false);
                    assinatura.setIntegra(true);
                } else
                {
                    listaAssinaturas.setAssinaturasValidas(false);
                    assinatura.setIntegra(false);
                    assinatura.setCertificado(null);
                }
            }

            listaAssinaturas.setAssinaturas(assinaturas);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return listaAssinaturas;
    }
}