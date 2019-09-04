package com.xc.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Properties;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AES {

	private static SecretKeySpec secretKey;
	private static byte[] key;

	public static void setKey(String myKey) {
		MessageDigest sha = null;
		try {
			key = myKey.getBytes("UTF-8");
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16);
			secretKey = new SecretKeySpec(key, "AES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public static String encrypt(String strToEncrypt, String secret) {
		try {
			setKey(secret);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
		} catch (Exception e) {
			System.out.println("Error while encrypting: " + e.toString());
		}
		return null;
	}

	public static String decrypt(String strToDecrypt, String secret) {
		try {
			setKey(secret);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
		} catch (Exception e) {
			System.out.println("Error while decrypting: " + e.toString());
		}
		return null;
	}

	public static void main(String[] args) {
		final String secretKey = "abcdefghsk";

		/*
		 * String originalString = "r@d1731@sf"; String encryptedString =
		 * AES.encrypt(originalString, secretKey);
		 * 
		 * String decryptedString = AES.decrypt(encryptedString, secretKey);
		 * 
		 * System.out.println(originalString); System.out.println(encryptedString);
		 * System.out.println(decryptedString);
		 * 
		 * // String originalString2 = "radhika+y@cloudwaveinc.com";
		 * 
		 * // String originalString2 = "amxcuserfree@org.com";
		 * 
		 * // String originalString2 = "amxcuser1gb@org.com"; // String originalString2
		 * = "amxcuser3gb@org.com"; String originalString2 = "amxcuser5gb@org.com";
		 * 
		 * String encryptedString2 = AES.encrypt(originalString2, secretKey);
		 * 
		 * String decryptedString2 = AES.decrypt(encryptedString2, secretKey);
		 * 
		 * System.out.println(originalString2); System.out.println(encryptedString2);
		 * System.out.println(decryptedString2);
		 * 
		 */

		try {
			InputStream input = new FileInputStream("src/main/resources/login.properties");
			Properties prop = new Properties();

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			String encryptedusername = prop.getProperty("username");
			System.out.println(encryptedusername);

			String decryptedusername = AES.decrypt(encryptedusername, secretKey);

			System.out.println(decryptedusername);

			String encryptedpwd = prop.getProperty("password");
			System.out.println(encryptedpwd);
			String decryptedpwd = AES.decrypt(encryptedpwd, secretKey);
			System.out.println(decryptedpwd);

		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}
}