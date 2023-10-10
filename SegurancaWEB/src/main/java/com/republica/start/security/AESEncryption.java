package com.republica.start.security;


import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.CFBBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.encoders.Base64;

import java.security.SecureRandom;

public class AESEncryption {
    public static String encrypt(String plaintext, byte[] encryptionKey) throws Exception {
        byte[] input = plaintext.getBytes("UTF-8");

        // Verificar o comprimento da chave
        if (encryptionKey.length != 16) {
            throw new IllegalArgumentException("A chave AES deve ter 16 bytes (128 bits).");
        }

        CipherParameters keyParam = new KeyParameter(encryptionKey);
        BufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new CFBBlockCipher(new AESEngine(), 128), new org.bouncycastle.crypto.paddings.ZeroBytePadding());
        cipher.init(true, keyParam);

        byte[] output = new byte[cipher.getOutputSize(input.length)];
        int bytesWritten = cipher.processBytes(input, 0, input.length, output, 0);
        cipher.doFinal(output, bytesWritten);

        return new String(Base64.encode(output), "UTF-8");
    }

    public static String decrypt(String ciphertext, byte[] encryptionKey) throws Exception {
        byte[] input = Base64.decode(ciphertext);

        // Verificar o comprimento da chave
        if (encryptionKey.length != 16) {
            throw new IllegalArgumentException("A chave AES deve ter 16 bytes (128 bits).");
        }

        CipherParameters keyParam = new KeyParameter(encryptionKey);
        BufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new CFBBlockCipher(new AESEngine(), 128), new org.bouncycastle.crypto.paddings.ZeroBytePadding());
        cipher.init(false, keyParam);

        byte[] output = new byte[cipher.getOutputSize(input.length)];
        int bytesWritten = cipher.processBytes(input, 0, input.length, output, 0);
        cipher.doFinal(output, bytesWritten);

        return new String(output, "UTF-8");
    }

//    public static void main(String[] args) throws Exception {
//        // Chave AES de 128 bits (16 bytes)
//        byte[] encryptionKey = new byte[16];
//        new SecureRandom().nextBytes(encryptionKey);
//
//        String plaintext = "SenhaSecreta123";
//
//        // Criptografar a senha
//        String encryptedPassword = encrypt(plaintext, encryptionKey);
//        System.out.println("Senha criptografada: " + encryptedPassword);
//
//        // Descriptografar a senha
//        String decryptedPassword = decrypt(encryptedPassword, encryptionKey);
//        System.out.println("Senha descriptografada: " + decryptedPassword);
//    }
}
