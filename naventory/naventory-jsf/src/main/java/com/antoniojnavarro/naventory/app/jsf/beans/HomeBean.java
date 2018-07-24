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

import com.antoniojnavarro.naventory.app.commons.PFScope;

@Named("homeBean")
@Scope(value = PFScope.VIEW_SCOPED)
public class HomeBean extends MasterBean {
	private static final Logger logger = LoggerFactory.getLogger(HomeBean.class);
	private static final long serialVersionUID = 1L;

	@Autowired
	private UsuarioAutenticado usuarioAutenticado;
	private String nombre = "Antonio Javier";

	@PostConstruct
	private void init() {
		if (usuarioAutenticado.getUsuario() == null) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			ExternalContext externalContext = facesContext.getExternalContext();
			try {
				externalContext.redirect("../login.xhtml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public UsuarioAutenticado getUsuarioAutenticado() {
		return usuarioAutenticado;
	}

	public void setUsuarioAutenticado(UsuarioAutenticado usuarioAutenticado) {
		this.usuarioAutenticado = usuarioAutenticado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
