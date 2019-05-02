package com.antoniojnavarro.naventory.app.jsf.beans;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

import org.apache.commons.codec.binary.Base64;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;

import com.antoniojnavarro.naventory.app.commons.PFScope;
import com.antoniojnavarro.naventory.app.security.services.api.ServicioAutenticacion;
import com.antoniojnavarro.naventory.app.security.services.dto.UsuarioAutenticado;
import com.antoniojnavarro.naventory.app.util.CifrarClave;
import com.antoniojnavarro.naventory.app.util.Constantes;
import com.antoniojnavarro.naventory.model.entities.Evento;
import com.antoniojnavarro.naventory.model.entities.Usuario;
import com.antoniojnavarro.naventory.services.api.ServicioEvento;
import com.antoniojnavarro.naventory.services.api.ServicioUsuario;
import com.antoniojnavarro.naventory.services.commons.ServicioException;
import com.antoniojnavarro.naventory.services.commons.ServicioMensajesI18n;

@Named("perfilBean")
@Scope(value = PFScope.VIEW_SCOPED)
public class PerfilBean extends MasterBean {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(PerfilBean.class);
	private String confPassword;
	private String oldPassword;
	private String newPassword;

	// CAMPOS
	private UploadedFile file;
	// ENTITIES
	private UsuarioAutenticado usuarioAutenticado;
	private Usuario usuarioSelected;
	private ScheduleModel eventModel;
	private ScheduleEvent event = new DefaultScheduleEvent();

	// LISTAS
	private List<Evento> eventos;

	// Graficas
	// SERVICIOS
	@Autowired
	private ServicioUsuario srvUsuario;

	@Autowired
	private ServicioAutenticacion srvAutenticacion;

	@Autowired
	private ServicioMensajesI18n srvMensajes;
	@Autowired
	private ServicioEvento srvEvento;

	@PostConstruct
	public void init() {

		logger.info("Perfil.init()");
		this.usuarioAutenticado = srvAutenticacion.getUserDetailsCurrentUserLogged();
		usuarioSelected = this.usuarioAutenticado.getUsuario();
		crearCalendario();

	}

	public void validarIgualPassword() throws ServicioException {
		if (!newPassword.equals(confPassword)) {
			throw new ServicioException(srvMensajes.getMensajeI18n("register.password.distinct"));
		}
	}

	public void validarAntiguaPassword() throws ServicioException {
		if (!CifrarClave.correctEncoder(oldPassword, this.usuarioSelected.getPassword())) {
			throw new ServicioException(srvMensajes.getMensajeI18n("perfil.error.password"));
		}
	}

	public String darBaja() {
		this.usuarioSelected.setActivo("N");
		this.srvUsuario.validateAndEmailOpcional(usuarioSelected, false);
		this.srvUsuario.saveOrUpdate(usuarioSelected, false);
		addInfo("perfil.baja.ok");
		SecurityContextHolder.clearContext();
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return Constantes.GO_TO_LOGIN;
	}

	public void guardar() {
		if (!this.srvUsuario.existsById(usuarioSelected.getEmail())) {
			addError("email.error");
		} else {
			if (confPassword != null && !confPassword.isEmpty()) {
				validarIgualPassword();
				validarAntiguaPassword();
				usuarioSelected.setPassword(CifrarClave.encriptarClave(newPassword));
			}
		}
		this.srvUsuario.validateAndEmailOpcional(usuarioSelected, false);
		this.srvUsuario.saveOrUpdate(usuarioSelected, false);
		addInfo("user.save.ok");
	}

	public void inicializarCredenciales() {
		confPassword = null;
		oldPassword = null;
		newPassword = null;
	}

	public UploadedFile getFile() {
		return file;
	}

	public String getFotoPerfil() {
		if (usuarioAutenticado.getUsuario().getFotoPerf() == null) {
			return null;
		}
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
		usuarioAutenticado.getUsuario().setFotoPerf(buffer);

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

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	// CALENDARIO

	private void crearCalendario() {
		eventModel = new DefaultScheduleModel();

		this.eventos = this.srvEvento.findEventosByUsuario(this.usuarioAutenticado.getUsuario());

		for (Evento e : eventos) {
			eventModel.addEvent(new DefaultScheduleEvent(e.getTitulo(), e.getFechaInicio(), e.getFechaFin()));
		}

	}

	public ScheduleEvent getEvent() {
		return event;
	}

	public void setEvent(ScheduleEvent event) {
		this.event = event;
	}

	public void addEvent(ActionEvent actionEvent) {
		if (event.getId() == null) {
			eventModel.addEvent(event);
		} else {
			eventModel.updateEvent(event);
		}
		parsearEvento(event);
		event = new DefaultScheduleEvent();
	}

	public void parsearEvento(ScheduleEvent event) {
		Evento evento = new Evento();

		evento.setIdEvento(event.getId());
		evento.setDiaEntero(event.isAllDay());
		evento.setFechaInicio(event.getStartDate());
		evento.setFechaFin(event.getEndDate());
		evento.setTitulo(event.getTitle());
		evento.setUsuario(this.usuarioAutenticado.getUsuario());
		this.srvEvento.saveOrUpdate(evento);

	}

	public void onEventSelect(SelectEvent selectEvent) {
		event = (ScheduleEvent) selectEvent.getObject();
	}

	public void onDateSelect(SelectEvent selectEvent) {
		event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
	}

	public void onEventMove(ScheduleEntryMoveEvent event) {
		this.event = event.getScheduleEvent();
		parsearEvento(this.event);
		this.event = new DefaultScheduleEvent();

	}

	public void onEventResize(ScheduleEntryResizeEvent event) {
		this.event = event.getScheduleEvent();
		parsearEvento(this.event);
		this.event = new DefaultScheduleEvent();

	}

	public ScheduleModel getEventModel() {
		return eventModel;
	}

	public void setEventModel(ScheduleModel eventModel) {
		this.eventModel = eventModel;
	}

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

}