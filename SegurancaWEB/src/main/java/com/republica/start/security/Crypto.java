package com.republica.start.security;


import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.republica.start.entity.User;

public class Crypto {
	
	public Crypto() {
		Security.addProvider(new BouncyCastleProvider());
	}

	public User Encriptar(User user) throws Exception {
		KeyPair keyPair = generateKeyPair();
		String publicKey = encodePublicKey(keyPair.getPublic());
		String encryptedPassword = encrypt(user.getPassword(), keyPair.getPublic());
		User newUser = user;
		newUser.setPassword(encryptedPassword);
		newUser.setpublic_Key(publicKey);

		return newUser;
	}

	public User Desncriptar(User user) throws Exception {
		KeyPair userKeyPair = new KeyPair(decodePublicKey(user.getpublic_Key()), null);
		String decryptedPassword = decrypt(user.getPassword(), userKeyPair.getPrivate());
		User newUser = user;
		newUser.setPassword(decryptedPassword);
		newUser.setpublic_Key(decryptedPassword);
		
		return newUser;
	}

//Função para gerar um par de chaves RSA
	public static KeyPair generateKeyPair() throws NoSuchAlgorithmException, Exception {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
		keyPairGenerator.initialize(new ECGenParameterSpec("secp256r1")); // Use uma curva ECC adequada
		return keyPairGenerator.generateKeyPair();
	}

// Função para codificar a chave pública em formato Base64
	public static String encodePublicKey(PublicKey publicKey) {
		byte[] encodedPublicKey = publicKey.getEncoded();
		return Base64.getEncoder().encodeToString(encodedPublicKey);
	}

// Função para decodificar a chave pública a partir do formato Base64
	public static PublicKey decodePublicKey(String encodedPublicKey) throws Exception {
		byte[] publicKeyBytes = Base64.getDecoder().decode(encodedPublicKey);
		KeyFactory keyFactory = KeyFactory.getInstance("EC");
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
		return keyFactory.generatePublic(keySpec);
	}

// Função para criptografar uma senha com a chave pública
	public static String encrypt(String plaintext, PublicKey publicKey) throws Exception {
		Cipher cipher = Cipher.getInstance("ECIES", "BC"); // ECC com Bouncy Castle
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] ciphertext = cipher.doFinal(plaintext.getBytes());
		return Base64.getEncoder().encodeToString(ciphertext);
	}

// Função para descriptografar uma senha com a chave privada
	public static String decrypt(String ciphertext, PrivateKey privateKey) throws Exception {
		Cipher cipher = Cipher.getInstance("ECIES", "BC"); // ECC com Bouncy Castle
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] ciphertextBytes = Base64.getDecoder().decode(ciphertext);
		byte[] plaintextBytes = cipher.doFinal(ciphertextBytes);
		return new String(plaintextBytes);
	}
}