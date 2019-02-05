package com.antoniojnavarro.naventory.app.security.social.providers;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

@Service
public class GoogleProvider extends BaseProvider {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8977724975811578208L;
	private String accessToken;
	@Autowired
	private Environment env;

	public String login() {
		NetHttpTransport transport;
		try {
			transport = GoogleNetHttpTransport.newTrustedTransport();
			final JacksonFactory jsonFactory = JacksonFactory.getDefaultInstance();
			GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
					.setAudience(Collections.singletonList(env.getProperty("spring.social.google.appId"))).build();

			final GoogleIdToken googleIdToken = verifier.verify(this.accessToken);
			if (googleIdToken == null) {
				return "error.login.google";
			}

			final Payload payload = googleIdToken.getPayload();
			final Boolean emailVerified = payload.getEmailVerified();

			if (!emailVerified) {
				return "error.login.google";
			}
			String nombre = (String) payload.get("name");
			String email = payload.getEmail();
			String apellidos = (String) payload.get("family_name");
			String fotoPerfil = (String) payload.get("picture");

			return iniciarSesion(email, nombre, apellidos, fotoPerfil);

		} catch (GeneralSecurityException | IOException e1) {
			e1.printStackTrace();
			return "error.login.google";

		}

	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

}