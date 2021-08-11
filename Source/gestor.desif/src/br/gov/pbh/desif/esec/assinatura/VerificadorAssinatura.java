/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  br.com.esec.pkix.x509.X509CertificateImpl
 *  br.com.esec.sdk.xmldsig.SignatureEsec
 *  br.com.esec.sdk.xmldsig.VerificationResult
 *  br.com.esec.sdk.xmldsig.XMLSignatureVerifier
 *  br.com.esec.sdk.xmldsig.XMLUtil
 *  br.com.esec.version.SDKVersion
 */
package br.gov.pbh.desif.esec.assinatura;

import br.com.esec.pkix.x509.X509CertificateImpl;
import br.com.esec.sdk.xmldsig.SignatureEsec;
import br.com.esec.sdk.xmldsig.VerificationResult;
import br.com.esec.sdk.xmldsig.XMLSignatureVerifier;
import br.com.esec.sdk.xmldsig.XMLUtil;
import br.com.esec.version.SDKVersion;
import br.gov.pbh.desif.esec.config.AddLicense;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.cert.Certificate;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.w3c.dom.Document;

public class VerificadorAssinatura {
    public static ListaAssinaturas verificarXml(String xml) {
        ListaAssinaturas listaAssinaturas = new ListaAssinaturas();
        try {
            AddLicense.checkLicense();
            listaAssinaturas.setAssinaturasValidas(true);
            listaAssinaturas.setCertificadosValidos(true);
            LinkedList<Assinatura> assinaturas = new LinkedList<Assinatura>();
            SignatureEsec.setProvider((String)"J128");
            String version = SDKVersion.getVersion();
            System.out.println("------------------------------" + version + "------------------------------");
            Document xmlDocument = XMLUtil.loadXMLFile((InputStream)new ByteArrayInputStream(xml.getBytes()));
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
          /* List verificationResults = verifier.verify();
            for (  Object result : verificationResults) 
            {
                Assinatura assinatura = new Assinatura();
                if (result.isSignatureOk())
                {
                    assinatura.setCertificado(new Certificado(new X509CertificateImpl((Certificate)result.getSignerCert()), Calendar.getInstance().getTime()));
                    if (!assinatura.getCertificado().isValido()) {
                        listaAssinaturas.setCertificadosValidos(false);
                    }
                    assinatura.setIntegra(true);
                } else {
                    listaAssinaturas.setAssinaturasValidas(false);
                    assinatura.setIntegra(false);
                    assinatura.setCertificado(null);
                }
                assinaturas.add(assinatura);
            }*/
            
            listaAssinaturas.setAssinaturas(assinaturas);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return listaAssinaturas;
    }
}

