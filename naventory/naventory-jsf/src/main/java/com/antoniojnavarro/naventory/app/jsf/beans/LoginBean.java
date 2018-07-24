package com.antoniojnavarro.naventory.app.jsf.beans;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;

import com.antoniojnavarro.naventory.app.commons.PFScope;
import com.antoniojnavarro.naventory.app.util.CifrarClave;
import com.antoniojnavarro.naventory.app.util.Constantes;
import com.antoniojnavarro.naventory.model.entities.Usuario;
import com.antoniojnavarro.naventory.services.api.ServicioUsuario;

@Named("loginBean")
@Scope(value = PFScope.VIEW_SCOPED)
public class LoginBean extends MasterBean {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(LoginBean.class);

	// CAMPOS
	private String email;
	private String password;
	// ENTITIES
	private Usuario usuario;
	@Autowired
	private UsuarioAutenticado usuarioAutenticado;

	// LISTAS

	// SERVICIOS
	@Autowired
	private ServicioUsuario srvUsuario;

	@PostConstruct
	public void init() {

		logger.info("LoginBean.init()");
	}

	public String login() {

		usuario = srvUsuario.findUsuarioByEmail(email);
		if (usuario != null && CifrarClave.correctEncoder(password, usuario.getPassword())) {
			usuarioAutenticado.setUsuario(usuario);
			return Constantes.GO_TO_HOME;
		} else {
			addError("login.userOrPasswordIncorrect");
			this.password = null;
			return new String();
		}

	}

	public String irARegistro() {
		return Constantes.GO_TO_REGISTER;
	}

	public String logout() {
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

}
