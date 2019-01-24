package com.antoniojnavarro.naventory.app.jsf.beans;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.apache.commons.codec.binary.Base64;
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
	private Usuario usuarioSelected;
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
		this.usuarioAutenticado = srvAutenticacion.getUserDetailsCurrentUserLogged();
		usuarioSelected = this.usuarioAutenticado.getUsuario();

	}

	public UploadedFile getFile() {
		return file;
	}

	public String getFotoPerfil() {
		String imageString = new String(Base64.encodeBase64(usuarioSelected.getFotoPerf()));
		return imageString;
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
		this.file = event.getFile();

		InputStream is;
		byte[] buffer = null;
		try {
			is = event.getFile().getInputstream();
			buffer = new byte[(int) file.getSize()]; // creamos el buffer
			int readers = is.read(buffer); // leemos el archivo al buffer
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // lo abrimos. Lo importante es que sea un InputStream

		usuarioSelected.setFotoPerf(buffer);

		srvUsuario.saveOrUpdate(usuarioSelected, false);

	}

	public void inicilizarAtributos() {
	}

	public void borrarUsuario(Usuario user) {

		srvUsuario.delete(user);
		addInfo("users.succesDelete");
	}

	public Usuario getUsuarioSelected() {
		return usuarioSelected;
	}

	public void setUsuarioSelected(Usuario usuarioSelected) {
		this.usuarioSelected = usuarioSelected;
	}

}