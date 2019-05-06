package com.antoniojnavarro.naventory.app.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CifrarClave {

	public static String encriptarClave(String password) {

		if (password == null) {
			return null;
		}
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder(12);
		return bcrypt.encode(password);
	}

	public static boolean correctEncoder(String userPassword, String encodedPassword) {
		if (userPassword == null || encodedPassword == null) {
			return false;
		}
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder(12);
		return bcrypt.matches(userPassword, encodedPassword);
	}

	public static String generarPassword() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789#$%.?";
		String pwd = RandomStringUtils.random(8, characters);
		return pwd;
	}

	public static String generarToken() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		String pwd = RandomStringUtils.random(15, characters);
		return pwd;
	}

}
