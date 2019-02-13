package com.antoniojnavarro.naventory.app.jsf.beans;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import com.antoniojnavarro.naventory.app.commons.PFScope;
import com.antoniojnavarro.naventory.app.security.social.FacebookProvider;
import com.antoniojnavarro.naventory.app.security.social.GoogleProvider;
import com.antoniojnavarro.naventory.app.util.Constantes;

@Named("loginBean")
@Scope(value = PFScope.VIEW_SCOPED)
public class LoginBean extends MasterBean {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(LoginBean.class);

	// CAMPOS
	private String email;
	private String password;
	// ENTITIES
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private FacebookProvider facebookProvider;
	@Autowired
	private GoogleProvider googleProvider;
	// LISTAS

	// SERVICIOS

	@PostConstruct
	public void init() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (!(auth instanceof AnonymousAuthenticationToken)) {

			FacesContext facesContext = FacesContext.getCurrentInstance();
			ExternalContext externalContext = facesContext.getExternalContext();
			try {
				externalContext.redirect(externalContext.getRequestContextPath() + "/private/home.xhtml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		logger.info("LoginBean.init()");

	}

	public String login() {

		try {
			Authentication auth = new UsernamePasswordAuthenticationToken(this.email, password);
			Authentication result = this.authenticationManager.authenticate(auth);
			SecurityContextHolder.getContext().setAuthentication(result);
			return Constantes.GO_TO_HOME;
		} catch (AuthenticationException e) {
			logger.info(e.getMessage(), e);
			addError("login.userOrPasswordIncorrect");
			// Nunca se debe retornar "null". Si se retorna "null" la vista no
			// se actualiza
			return new String();
		}
	}

	public String irARegistro() {
		return Constantes.GO_TO_REGISTER;
	}

	public String logout() {
		logger.info("Cerrando sesion");
		SecurityContextHolder.clearContext();
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return Constantes.GO_TO_LOGIN;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String loginFacebook() {
		String accesstoken = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("respuestaFacebook");

		if (accesstoken == null || accesstoken.length() <= 0) {
			addError("error.login.facebook");
			return new String();
		}

		this.facebookProvider.setAccessToken(accesstoken);
		String result = facebookProvider.login();

		if (!result.equals(Constantes.GO_TO_HOME)) {
			addError(result);
			return new String();
		}

		return Constantes.GO_TO_HOME;

	}

	public String loginGoogle() {

		String accesstoken = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("respuestaGoogle");

		if (accesstoken == null || accesstoken.length() <= 0) {
			addError("error.login.google");
			return new String();
		}
		this.googleProvider.setAccessToken(accesstoken);
		String result = googleProvider.login();

		if (!result.equals(Constantes.GO_TO_HOME)) {
			addError(result);
			return new String();
		}

		return Constantes.GO_TO_HOME;
	}

}
