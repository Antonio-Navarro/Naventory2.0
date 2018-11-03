package com.antoniojnavarro.naventory.services.commons;

import java.io.Serializable;

public interface ServicioMail extends Serializable {
	
	public void sendEmail(String toEmail, String subject, String body);

}
