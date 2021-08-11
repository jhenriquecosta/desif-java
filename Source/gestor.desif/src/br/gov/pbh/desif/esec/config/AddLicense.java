/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  br.com.esec.j128.J128
 *  br.com.esec.j128.mscapi.J128MSCAPI
 *  br.com.esec.license.License
 *  br.com.esec.pkcs.pkcs11.sc.OpencardProperties
 *  br.com.esec.pkcs.pkcs9.CounterSignatureAttribute
 *  br.com.esec.pkcs.pkcs9.SigningTimeAttribute
 *  br.com.esec.pkcs.pkcs9.UnstructuredNameAttribute
 *  br.com.esec.sdk.certificate.ICPBrasilInformations
 */
package br.gov.pbh.desif.esec.config;

import br.com.esec.j128.J128;
import br.com.esec.j128.mscapi.J128MSCAPI;
import br.com.esec.license.License;
import br.com.esec.pkcs.pkcs11.sc.OpencardProperties;
import br.com.esec.pkcs.pkcs9.CounterSignatureAttribute;
import br.com.esec.pkcs.pkcs9.SigningTimeAttribute;
import br.com.esec.pkcs.pkcs9.UnstructuredNameAttribute;
import br.com.esec.sdk.certificate.ICPBrasilInformations;
import java.security.GeneralSecurityException;
import java.security.Provider;
import java.security.Security;

public class AddLicense {
    private static boolean loaded = false;

    public static void checkLicense() throws GeneralSecurityException {
        if (!loaded) {
            AddLicense.loadLicense();
            ICPBrasilInformations.registerOtherNames();
            if (Security.getProvider("J128") == null) {
                Security.addProvider((Provider)new J128());
            }
            if (Security.getProvider("J128MSCAPI") == null) {
                Security.addProvider((Provider)new J128MSCAPI());
            }
            OpencardProperties.getInstance();
            SigningTimeAttribute.register();
            UnstructuredNameAttribute.register();
            CounterSignatureAttribute.register();
            loaded = true;
        }
    }

    private static void loadLicense() throws GeneralSecurityException {
        String orgName = "Prodabel";
        String orgLicense = "AAAApSNlLVNlYyBQcm9kdWN0IExpY2Vuc2UNCiNXZWQgQXByIDI0IDIwOjMzOjAzIEdNVCAyMDEzDQp2ZXJzaW9uPTIuMS4wDQp1c2VyPVByb2RhYmVsDQpkYXRlPXVubGltaXRlZA0KbmFtZT1TREstSmF2YSBHZXJhZG9yIGRlIExpY2VuY2FzDQpsaWIuMT1TREtKYXZhDQpsaWIuMD1TREtXZWINCgAAAQBaqfF1f1OSZUyzSdue7GXjPM9GCiE84OTROGuw8wzG5Cb2Ik9+zr0TGPbAW2DVjzXpjBOrfbDAuDmnRIpwpiXoAHFWoirjWU+cEaochZlMbCtf+UV73CtwiCdx0Tq8mOrS94zsf/0DL5SDm5Kp4W8qPdEYvWy/+6swdPaKvqyuWgtGJ0KVriTQ882P3Kd+Sbi+8+4bpidk1+QLyV1tzEhU9t2BzEy0L+7hu/S8yuEu2alj0GAe8whoRD5CKSm2IOaotzmT8x86kQ9r3NsPyID8txI51pc4FPjT3SX94QQCfFCmGentFTUEmpB22PXCySsAIP2b5Ky0SKy2IUBZtkm7";
        try {
            License.addLicense((String)orgName, (String)orgLicense);
        }
        catch (Exception e) {
            throw new GeneralSecurityException("Erro na validacao da licenca do componente de certificacao digital: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        AddLicense.checkLicense();
    }
}

