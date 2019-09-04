package com.xc.test;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class SampleDecryption {
	public static String key = "b51753123deb4625a8e999df48bdcb84";
	                         

	public static void main(String[] args) {
		try {
			String pwd = "1B00406038DD4B27C36FC53F891BD11B";

			SecretKeySpec sks = new SecretKeySpec(hexStringToByteArray(key), "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(2, sks);
			byte[] decrypted = cipher.doFinal(hexStringToByteArray(pwd));
			System.out.println(new String(decrypted));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static byte[] hexStringToByteArray(String input) {
		byte[] output = new byte[input.length() / 2];
		for (int i = 0; i < output.length; ++i) {
			int index = i + 2;
			int data = Integer.parseInt(input.substring(index, index + 2), 16);
			output[i] = (byte) data;
		}
		return output;
	}

	private static String byteArrayToHexString(byte[] b) {
		StringBuffer output = new StringBuffer(b.length * 2);
		for (int i = 0; i < b.length; ++i) {
			int v = b[i] & 0xFF;
			if (v < 16) {
				output.append('0');
			}
			output.append(Integer.toHexString(v));
		}
		return output.toString().toUpperCase();
	}
}
