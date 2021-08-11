/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  br.com.esec.j128.RC4SecretKey
 *  br.com.esec.jca.CryptoFactory
 */
package br.gov.pbh.desif.service.certificacao;

import br.com.esec.j128.RC4SecretKey;
import br.com.esec.jca.CryptoFactory;
import br.gov.pbh.desif.esec.config.AddLicense;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Cifragem {
    public static Cifragem instance;
    private String senha = "ihkladsfl768$00-=lDWAV14234hhh,,.<>h21";
    private RC4SecretKey key;

    public static Cifragem getInstance() throws InvalidKeyException, NoSuchAlgorithmException {
        if (instance == null) {
            try {
                instance = new Cifragem();
            }
            catch (GeneralSecurityException ex) {
                ex.printStackTrace();
            }
        }
        return instance;
    }

    private Cifragem() throws GeneralSecurityException {
        AddLicense.checkLicense();
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hSenha = md.digest(this.senha.getBytes());
        this.key = new RC4SecretKey(hSenha);
    }

    public byte[] encrypt(byte[] data) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher rsa = CryptoFactory.getFactory().createCipher("RC4", "J128");
        rsa.init(1, (Key)this.key);
        return rsa.doFinal(data);
    }

    public byte[] decrypt(byte[] cipherText) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher rsa = CryptoFactory.getFactory().createCipher("RC4", "J128");
        rsa.init(2, (Key)this.key);
        return rsa.doFinal(cipherText);
    }
}

