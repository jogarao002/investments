package com.intellect.investmentsms.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

@Component
public class PasswordEncryption {
	
	private static SecretKeySpec secretKey;
	private static byte[] key;
	private static String passPhrase = "aoadpq=-o098009-09345gksdfmsd";

	public static void setKey(String myKey) {

		try {
			key = myKey.getBytes("UTF-8");

			// Get the instance of MD5.
			MessageDigest md = MessageDigest.getInstance("MD5");

			// Get the hashed bytes of password.
			key = md.digest(key);

			// padding the copy of same length
			key = Arrays.copyOf(key, 16);

			// key data and algorithm specific
			secretKey = new SecretKeySpec(key, "AES");
		} catch (NoSuchAlgorithmException nsal) {
			nsal.printStackTrace();
		} catch (UnsupportedEncodingException usee) {
			usee.printStackTrace();
		}
	}

	public static String encrypt(String strToEncrypt) {
		
		String newPassword=null;
		try {
			setKey(passPhrase);
			Cipher cipher = Cipher.getInstance("AES");

			// intialize the cipher
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);

			// encode the password using base
			newPassword=Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
			return newPassword;
		} catch (Exception e) {
			System.out.println("Error while encrypting: " + e.toString());
		}
		return newPassword;
	}


}
