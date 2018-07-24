package com.antoniojnavarro.naventory.app.jsf.beans;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;

import com.antoniojnavarro.naventory.app.commons.PFScope;
import com.antoniojnavarro.naventory.model.entities.Usuario;

@Named("usuarioAutenticado")
@Scope(value = PFScope.SESSION_SCOPED)
public class UsuarioAutenticado extends MasterBean {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(UsuarioAutenticado.class);



	// CAMPOS
	// ENTITIES
	private Usuario usuario;

	// LISTAS

	// SERVICIOS
	
	@PostConstruct
	public void init() {

		logger.info("UsuarioAutenticado.init()");
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


}
