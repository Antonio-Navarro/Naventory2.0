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
import com.antoniojnavarro.naventory.services.commons.ServicioException;
import com.antoniojnavarro.naventory.services.commons.ServicioMensajesI18n;

@Named("perfilBean")
@Scope(value = PFScope.VIEW_SCOPED)
public class PerfilBean extends MasterBean {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(PerfilBean.class);
	private String confPassword;
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

	@Autowired
	private ServicioMensajesI18n srvMensajes;

	@PostConstruct
	public void init() {

		logger.info("Perfil.init()");
		this.usuarioAutenticado = srvAutenticacion.getUserDetailsCurrentUserLogged();
		usuarioSelected = this.usuarioAutenticado.getUsuario();

	}

	public void validarIgualPassword() throws ServicioException {
		if (!usuarioSelected.getPassword().equals(confPassword)) {
			throw new ServicioException(srvMensajes.getMensajeI18n("register.password.distinct"));
		}
	}

	public void guardar() {
		if (!this.srvUsuario.existsById(usuarioSelected.getEmail())) {
			addError("email.error");
		}
		if (confPassword != null && !confPassword.isEmpty()) {
			validarIgualPassword();
		}
		this.srvUsuario.validateAndEmailOpcional(usuarioSelected, false);
		this.srvUsuario.saveOrUpdate(usuarioSelected, false);
		addInfo("user.save.ok");
	}

	public UploadedFile getFile() {
		return file;
	}

	public String getFotoPerfil() {
		fotoPerfil = new String(Base64.encodeBase64(usuarioAutenticado.getUsuario().getFotoPerf()));
		if (fotoPerfil != null && fotoPerfil.length() > 0) {
			return fotoPerfil;
		} else {
			return null;
		}
	}

	private String fotoPerfil;

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
		updateComponent("formApp:lighbox1");

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

	public String getConfPassword() {
		return confPassword;
	}

	public void setConfPassword(String confPassword) {
		this.confPassword = confPassword;
	}

}