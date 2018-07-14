package com.antoniojnavarro.naventory.app.jsf.beans;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import com.antoniojnavarro.naventory.app.commons.PFScope;
import com.antoniojnavarro.naventory.app.util.Constantes;

@Named("loginBean")
@Scope(value = PFScope.VIEW_SCOPED)
public class LoginBean extends MasterBean {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(LoginBean.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	private String username;
	private String password;

	@PostConstruct
	public void init() {
		logger.info("LoginBean.init()");
	}

	public String login() {
		try {
			Authentication auth = new UsernamePasswordAuthenticationToken(this.username, password);
			Authentication result = this.authenticationManager.authenticate(auth);
			SecurityContextHolder.getContext().setAuthentication(result);
			return Constantes.GO_TO_HOME;
		} catch (AuthenticationException e) {
			logger.info(e.getMessage(), e);
			super.addWarning("login.userOrPasswordIncorrect");
			// Nunca se debe retornar "null". Si se retorna "null" la vista no
			// se actualiza
			return new String();
		}

	}

	public String logout() {
		SecurityContextHolder.clearContext();
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return Constantes.GO_TO_LOGIN; 
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
