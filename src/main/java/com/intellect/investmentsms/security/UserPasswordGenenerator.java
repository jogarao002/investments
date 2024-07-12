package com.intellect.investmentsms.security;

import java.util.Random;

public class UserPasswordGenenerator {
	

	/*
	 * This our Password generating method We have use static here, so that we not
	 * to make any object for it
	 */
	public static String getPassword(int len) {

		// Length of your password decided from usersserviceIMPL
		// System.out.println(geek_Password(length));

		System.out.print("Your new password is : ");

		/*
		 * A strong password has Cap_chars, Lower_chars, numeric value and symbols. So
		 * we are using all of them to generate our password
		 */ 
		String capitalChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String smallChars = "abcdefghijklmnopqrstuvwxyz";
		String numbers = "0123456789";

		String values = capitalChars + smallChars + numbers;

		// Using random method
		Random rndmMethod = new Random();

		char[] password = new char[len];

		for (int i = 0; i < len; i++) {
			/*
			 * Use of charAt() method : to get character value
			 * Use of nextInt() as it is scanning the value as int
			 */
			password[i] = values.charAt(rndmMethod.nextInt(values.length()));

		}
		return new String(password);
	}

	public static String getOTP(int len) {

		// Length of your otp decided from VendorEmployeeServiceImpl

		String numbers = "0123456789";

		String values = numbers;

		// Using random method
		Random rndmMethod = new Random();

		char[] otp = new char[len];

		for (int i = 0; i < len; i++) {
			/*
			 * Use of charAt() method : to get character value
			 * Use of nextInt() as it is scanning the value as int
			 */
			otp[i] = values.charAt(rndmMethod.nextInt(values.length()));

		}
		return new String(otp);
	}

}
