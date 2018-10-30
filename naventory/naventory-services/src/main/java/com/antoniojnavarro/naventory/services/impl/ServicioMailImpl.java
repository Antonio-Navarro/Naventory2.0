
package com.antoniojnavarro.naventory.services.impl;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import com.antoniojnavarro.naventory.services.commons.ServicioMail;

@Service
public class ServicioMailImpl implements ServicioMail {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Utility method to send simple HTML email
	 * 
	 * @param session
	 * @param toEmail
	 * @param subject
	 * @param body
	 */
	@Override
	public void sendEmail(String toEmail, String subject, String body) {
		try {
			Session session = createSession();
			MimeMessage msg = new MimeMessage(session);
			// set message headers
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");

			msg.setFrom(new InternetAddress("naventory@naventory.cerrajerianavarro.es",
					"Naventory - Gestión de Inventarios"));

			msg.setReplyTo(InternetAddress.parse("no_reply@naventory.cerrajerianavarro.es", false));

			msg.setSubject(subject, "UTF-8");

			msg.setContent(body, "text/html");

			msg.setSentDate(new Date());

			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
			System.out.println("Message is ready");
			Transport.send(msg);

			System.out.println("EMail Sent Successfully!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Session createSession() {
		final String fromEmail = "naventory@naventory.cerrajerianavarro.es";
		final String password = "bender40";

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.1and1.es");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");

		Authenticator auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		Session session = Session.getInstance(props, auth);
		return session;
	}
}
