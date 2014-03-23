package edu.flight.dao;

import java.util.Random;

public abstract class PasswordChecker {
	public static String encrypt(String text) {
		String keyword = "Misery Loves Company";
		byte[] arr = text.getBytes();
		byte[] keyarr = keyword.getBytes();
		byte[] result = new byte[arr.length];
		for (int i = 0; i < arr.length; i++) {
			result[i] = (byte) (arr[i] ^ keyarr[i % keyarr.length]);
		}
		String resS = "";
		for (byte b : result)
			resS += (char) b;
		return resS;
	}

	public static String decrypt(String p_text, String keyWord) {
		byte[] text = p_text.getBytes();
		byte[] result = new byte[text.length];
		byte[] keyarr = keyWord.getBytes();
		for (int i = 0; i < text.length; i++) {
			result[i] = (byte) (text[i] ^ keyarr[i % keyarr.length]);
		}
		return new String(result);
	}

	private static String randomString(int len) {
		String AB = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random rnd = new Random();

		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		return sb.toString();
	}

	public static String formPass() {
		return encrypt(randomString(10));
	};
}
