package com.antoniojnavarro.naventory.app.jsf.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.antoniojnavarro.naventory.app.commons.PFScope;
import com.antoniojnavarro.naventory.model.entities.Usuario;
import com.antoniojnavarro.naventory.services.api.ServicioUsuario;

@Named("administracionBean")
@Scope(value = PFScope.VIEW_SCOPED)
public class AdministracionBean extends MasterBean {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(AdministracionBean.class);

	// CAMPOS
	// ENTITIES
	// LISTAS
	private List<Usuario> usuarios;
	// SERVICIOS
	@Autowired
	private ServicioUsuario srvUsuario;

	@PostConstruct
	public void init() {

		logger.info("LoginBean.init()");
		cargarUsuarios();
	}

	public void inicilizarAtributos() {
		this.usuarios = null;	
	}
	
	public void cargarUsuarios() {
		this.usuarios = srvUsuario.findAll();
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	

}
