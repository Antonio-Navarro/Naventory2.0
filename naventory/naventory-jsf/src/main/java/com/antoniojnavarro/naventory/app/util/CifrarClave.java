package com.antoniojnavarro.naventory.app.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CifrarClave {

	public static String encriptarClave(String password) {
		
		if(password==null) {
			return null;
		}		
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder(11);		
		return bcrypt.encode(password);
	}
	
	public static boolean correctEncoder(String userPassword,String encodedPassword) {
		if(userPassword==null || encodedPassword==null ) {
			return false;
		}		
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder(11);		
		return bcrypt.matches(userPassword, encodedPassword);
	}
	
}
