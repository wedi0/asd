package com.republica.start.security;

import java.security.MessageDigest;
import org.apache.commons.codec.binary.Base64;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Enconder {
	  private String secretKey = "SomosProgramadores";

	    public String encode(String cadena) {
	        try {
	            MessageDigest md5 = MessageDigest.getInstance("MD5");
	            byte[] llavePassword = md5.digest(secretKey.getBytes("utf-8"));
	            byte[] BytesKey = Arrays.copyOf(llavePassword, 24);
	            SecretKey key = new SecretKeySpec(BytesKey, "DESede");
	            Cipher cifrado = Cipher.getInstance("DESede");
	            cifrado.init(Cipher.ENCRYPT_MODE, key);
	            byte[] plainTextBytes = cadena.getBytes("utf-8");
	            byte[] buf = cifrado.doFinal(plainTextBytes);
	            byte[] base64Bytes = Base64.encodeBase64(buf);
	            return new String(base64Bytes);
	        } catch (Exception ex) {
	            // Registre a exceção para depuração
	            ex.printStackTrace();
	            // Retorne uma mensagem de erro
	            return "Erro ao encriptar: " + ex.getMessage();
	        }
	    }

	    public String decode(String cadenaEncriptada) {
	        try {
	            byte[] message = Base64.decodeBase64(cadenaEncriptada.getBytes("utf-8"));
	            MessageDigest md5 = MessageDigest.getInstance("MD5");
	            byte[] digestOfPassword = md5.digest(secretKey.getBytes("utf-8"));
	            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
	            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
	            Cipher decipher = Cipher.getInstance("DESede");
	            decipher.init(Cipher.DECRYPT_MODE, key);
	            byte[] plainText = decipher.doFinal(message);
	            return new String(plainText, "UTF-8");
	        } catch (Exception ex) {
	            // Registre a exceção para depuração
	            ex.printStackTrace();
	            // Retorne uma mensagem de erro
	            return "Erro ao desencriptar: " + ex.getMessage();
	        }
	    }
	}

