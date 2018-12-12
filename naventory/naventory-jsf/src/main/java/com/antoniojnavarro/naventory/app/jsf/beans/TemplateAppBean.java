package com.antoniojnavarro.naventory.app.jsf.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.antoniojnavarro.naventory.app.commons.PFScope;
import com.antoniojnavarro.naventory.model.entities.AlertaStock;
import com.antoniojnavarro.naventory.services.api.ServicioAlertaStock;

@Named("templateAppBean")
@Scope(value = PFScope.VIEW_SCOPED)
public class TemplateAppBean extends MasterBean {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(TemplateAppBean.class);

	// CAMPOS
	// ENTITIES
	@Autowired
	private UsuarioAutenticado usuarioAutenticado;
	// LISTAS
	private List<AlertaStock> alertas;

	// SERVICIOS
	@Autowired
	private ServicioAlertaStock srvAlertaStock;

	@PostConstruct
	public void init() {
		logger.info("TemplateApp.init()");
		this.usuarioAutenticado.isLoged();
		cargarAlertas();
	}

	public void cargarAlertas() {
		alertas = this.srvAlertaStock.findAlertasByUsuario(this.usuarioAutenticado.getUsuario());
	}

	public UsuarioAutenticado getUsuarioAutenticado() {
		return usuarioAutenticado;
	}

	public void setUsuarioAutenticado(UsuarioAutenticado usuarioAutenticado) {
		this.usuarioAutenticado = usuarioAutenticado;
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
}
