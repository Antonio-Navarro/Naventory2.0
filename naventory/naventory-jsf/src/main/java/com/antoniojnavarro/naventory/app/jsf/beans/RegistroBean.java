package com.antoniojnavarro.naventory.app.jsf.beans;

import java.util.ArrayList;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.FlowEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.antoniojnavarro.naventory.app.commons.PFScope;
import com.antoniojnavarro.naventory.app.util.CifrarClave;
import com.antoniojnavarro.naventory.app.util.Constantes;
import com.antoniojnavarro.naventory.model.entities.Empresa;
import com.antoniojnavarro.naventory.model.entities.EmpresaInvitacion;
import com.antoniojnavarro.naventory.model.entities.Role;
import com.antoniojnavarro.naventory.model.entities.Usuario;
import com.antoniojnavarro.naventory.services.api.ServicioEmpresa;
import com.antoniojnavarro.naventory.services.api.ServicioEmpresaInvitacion;
import com.antoniojnavarro.naventory.services.api.ServicioRole;
import com.antoniojnavarro.naventory.services.api.ServicioUsuario;
import com.antoniojnavarro.naventory.services.commons.ServicioException;
import com.antoniojnavarro.naventory.services.commons.ServicioMensajesI18n;

@Named("registroBean")
@Scope(value = PFScope.VIEW_SCOPED)
public class RegistroBean extends MasterBean {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(RegistroBean.class);

	// CAMPOS
	private String confPassword;
	private boolean skip;
	// ENTITIES
	private Usuario usuario;

	// LISTAS

	// SERVICIOS
	@Autowired
	private ServicioUsuario srvUsuario;
	@Autowired
	private ServicioEmpresa srvEmpresa;
	@Autowired
	ServicioEmpresaInvitacion srvEmpresaInvitacion;
	@Autowired
	private ServicioRole srvRole;
	@Autowired
	private ServicioMensajesI18n srvMensajes;

	private boolean registroInvitacion;
	private EmpresaInvitacion invitacion;

	@PostConstruct
	public void init() {
		inicializarUsuario();
		logger.info("LoginBean.init()");
		Empresa empresa = comprobarSiInvitacion();
		if (empresa != null) {
			usuario.setEmpresa(empresa);
			registroInvitacion = true;
		} else if (invitacion == null || empresa == null) {
			addError("registro.invitacion.error");
		}
	}

	public void inicializarUsuario() {
		usuario = new Usuario();
		usuario.setEmpresa(new Empresa());
		confPassword = "";
		registroInvitacion = false;
	}

	public Empresa comprobarSiInvitacion() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		@SuppressWarnings("rawtypes")
		Map params = externalContext.getRequestParameterMap();
		String cif = (String) params.get("empresa");
		String email = (String) params.get("email");
		String token = (String) params.get("token");

		if (cif == null || cif.isEmpty() || email == null || email.isEmpty() || token == null || token.isEmpty()) {
			return null;
		}

		invitacion = srvEmpresaInvitacion.findEmpresaInvitacionByCifAndEmailAndToken(cif, email, token);

		if (invitacion != null && "S".equals(invitacion.getValido())) {
			usuario.setEmail(email);
			return srvEmpresa.findById(cif);
		} else {
			return null;
		}

	}

	public void validarIgualPassword() throws ServicioException {
		if (!usuario.getPassword().equals(confPassword)) {
			throw new ServicioException(srvMensajes.getMensajeI18n("register.password.distinct"));
		}
	}

	public String register() {
		validarIgualPassword();
		usuario.setPassword(CifrarClave.encriptarClave(usuario.getPassword()));
		ArrayList<Role> roles = new ArrayList<>();

		if (!registroInvitacion) {
			roles.add(srvRole.findById(2));
			roles.add(srvRole.findById(3));
			srvEmpresa.saveOrUpdate(usuario.getEmpresa());
		} else {
			invitacion.setValido("N");
			srvEmpresaInvitacion.saveOrUpdate(invitacion);
			roles.add(srvRole.findById(2));

		}

		usuario.setRoles(roles);
		this.srvUsuario.saveOrUpdate(usuario, true);
		addInfo("register.ok");
		inicializarUsuario();
		return Constantes.GO_TO_LOGIN;

	}

	public String onFlowProcess(FlowEvent event) {
		if (skip) {
			skip = false;
			return "usuarioTab";
		} else {
			return event.getNewStep();
		}
	}

	public String volverInicioSesion() {
		return Constantes.GO_TO_LOGIN;
	}

	public String getConfPassword() {
		return confPassword;
	}

	public void setConfPassword(String confPassword) {
		this.confPassword = confPassword;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isRegistroInvitacion() {
		return registroInvitacion;
	}

	public void setRegistroInvitacion(boolean registroInvitacion) {
		this.registroInvitacion = registroInvitacion;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

}
