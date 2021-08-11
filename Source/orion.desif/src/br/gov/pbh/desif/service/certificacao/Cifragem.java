
package br.gov.pbh.desif.service.certificacao;

import br.com.esec.j128.RC4SecretKey;
import br.com.esec.jca.CryptoFactory;
import br.com.esec.jca.ICryptoFactory;
import br.gov.pbh.desif.esec.config.AddLicense;
import java.security.*;
import javax.crypto.*;

public class Cifragem
{

    public static Cifragem instance;
    private String senha;
    private RC4SecretKey key;

    public static Cifragem getInstance()
        throws InvalidKeyException, NoSuchAlgorithmException
    {
        if(instance == null)
            try
            {
                instance = new Cifragem();
            }
            catch(GeneralSecurityException ex)
            {
                ex.printStackTrace();
            }
        return instance;
    }

    private Cifragem()
        throws GeneralSecurityException
    {
        senha = "ihkladsfl768$00-=lDWAV14234hhh,,.<>h21";
        AddLicense.checkLicense();
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte hSenha[] = md.digest(senha.getBytes());
        key = new RC4SecretKey(hSenha);
    }

    public byte[] encrypt(byte data[])
        throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
    {
        Cipher rsa = CryptoFactory.getFactory().createCipher("RC4", "J128");
        rsa.init(1, key);
        return rsa.doFinal(data);
    }

    public byte[] decrypt(byte cipherText[])
        throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
    {
        Cipher rsa = CryptoFactory.getFactory().createCipher("RC4", "J128");
        rsa.init(2, key);
        return rsa.doFinal(cipherText);
    }
}
