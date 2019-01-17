package com.antoniojnavarro.naventory.app.jsf.beans;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.antoniojnavarro.naventory.app.commons.PFScope;
import com.antoniojnavarro.naventory.app.security.services.api.ServicioAutenticacion;
import com.antoniojnavarro.naventory.app.security.services.dto.UsuarioAutenticado;
import com.antoniojnavarro.naventory.model.entities.Usuario;
import com.antoniojnavarro.naventory.services.api.ServicioUsuario;

@Named("perfilBean")
@Scope(value = PFScope.VIEW_SCOPED)
public class PerfilBean extends MasterBean {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(PerfilBean.class);

	// CAMPOS
	private UploadedFile file;
	// ENTITIES
	private UsuarioAutenticado usuarioAutenticado;
	// LISTAS
	// Graficas
	// SERVICIOS
	@Autowired
	private ServicioUsuario srvUsuario;

	@Autowired
	private ServicioAutenticacion srvAutenticacion;

	@PostConstruct
	public void init() {

		logger.info("Perfil.init()");
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public void upload() {
		if (file != null) {
			FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void gestorSubidaFicheros(FileUploadEvent event) {
		FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void inicilizarAtributos() {
	}

	public void borrarUsuario(Usuario user) {

		srvUsuario.delete(user);
		addInfo("users.succesDelete");
	}

}