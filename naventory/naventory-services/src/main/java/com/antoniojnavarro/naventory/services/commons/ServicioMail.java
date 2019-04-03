package com.antoniojnavarro.naventory.services.commons;

import java.io.Serializable;
import java.util.List;

public interface ServicioMail extends Serializable {

	public void sendEmail(List<String> toEmail, String subject, String body) throws ServicioException;

}
