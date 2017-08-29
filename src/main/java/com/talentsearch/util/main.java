package com.talentsearch.util;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String password = "admin123";
		String encodedpassword = MD5Encription.encrypt(password);
		System.out.println(encodedpassword);
	}

}
