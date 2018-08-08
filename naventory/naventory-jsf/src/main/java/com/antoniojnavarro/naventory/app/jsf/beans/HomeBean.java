package com.antoniojnavarro.naventory.app.jsf.beans;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.antoniojnavarro.naventory.app.commons.PFScope;

@Named("homeBean")
@Scope(value = PFScope.VIEW_SCOPED)
public class HomeBean extends MasterBean {
	private static final Logger logger = LoggerFactory.getLogger(HomeBean.class);
	private static final long serialVersionUID = 1L;

	private String nombre = "Antonio Javier";
	@Autowired
	private UsuarioAutenticado usuarioAuteticado;

	
	@PostConstruct
	public void init() {
		logger.debug("Pasando por el init de home");
		this.usuarioAuteticado.isLoged();
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public UsuarioAutenticado getUsuarioAuteticado() {
		return usuarioAuteticado;
	}


	public void setUsuarioAuteticado(UsuarioAutenticado usuarioAuteticado) {
		this.usuarioAuteticado = usuarioAuteticado;
	}
	
	
}
