package com.antoniojnavarro.naventory.app.jsf.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;

import com.antoniojnavarro.naventory.app.commons.PFScope;
import com.antoniojnavarro.naventory.app.security.services.api.ServicioAutenticacion;
import com.antoniojnavarro.naventory.app.security.services.dto.UsuarioAutenticado;
import com.antoniojnavarro.naventory.app.util.Constantes;
import com.antoniojnavarro.naventory.model.entities.AlertaStock;
import com.antoniojnavarro.naventory.services.api.ServicioAlertaStock;

@Named("templateAppBean")
@Scope(value = PFScope.VIEW_SCOPED)
public class TemplateAppBean extends MasterBean {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(TemplateAppBean.class);

	// CAMPOS
	// ENTITIES
	private UsuarioAutenticado usuarioAutenticado;
	// LISTAS
	private List<AlertaStock> alertas;

	// SERVICIOS
	@Autowired
	private ServicioAlertaStock srvAlertaStock;

	@Autowired
	private ServicioAutenticacion srvAutenticacion;

	@PostConstruct
	public void init() {
		logger.info("TemplateApp.init()");

		this.usuarioAutenticado = srvAutenticacion.getUserDetailsCurrentUserLogged();

		cargarAlertas();
	}

	public void cargarAlertas() {
		alertas = this.srvAlertaStock.findAlertasByUsuario(this.usuarioAutenticado.getUsuario());
	}

	public List<AlertaStock> getAlertas() {
		return alertas;
	}

	public void setAlertas(List<AlertaStock> alertas) {
		this.alertas = alertas;
	}

	public boolean isExitenAlertas() {
		return (this.alertas.size() > 0);

	}

	public String logout() {
		logger.info("Cerrando sesion");
		SecurityContextHolder.clearContext();
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return Constantes.GO_TO_LOGIN;
	}

	public UsuarioAutenticado getUsuarioAutenticado() {
		return usuarioAutenticado;
	}

	public void setUsuarioAutenticado(UsuarioAutenticado usuarioAutenticado) {
		this.usuarioAutenticado = usuarioAutenticado;
	}

}