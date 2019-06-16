package com.antoniojnavarro.naventory.app.jsf.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.antoniojnavarro.naventory.app.commons.PFScope;
import com.antoniojnavarro.naventory.app.security.services.api.ServicioAutenticacion;
import com.antoniojnavarro.naventory.app.security.services.dto.UsuarioAutenticado;
import com.antoniojnavarro.naventory.app.util.CifrarClave;
import com.antoniojnavarro.naventory.model.entities.EmpresaInvitacion;
import com.antoniojnavarro.naventory.model.entities.Usuario;
import com.antoniojnavarro.naventory.services.api.ServicioEmpresaInvitacion;
import com.antoniojnavarro.naventory.services.api.ServicioUsuario;
import com.antoniojnavarro.naventory.services.commons.ServicioMail;

@Named("empresaBean")
@Scope(value = PFScope.VIEW_SCOPED)
public class EmpresaBean extends MasterBean {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(EmpresaBean.class);

	// CAMPOS
	private String emailInvitacion;
	// ENTITIES
	private UsuarioAutenticado usuarioAutenticado;
	// LISTAS
	private List<Usuario> usuarios;
	// SERVICIOS
	@Autowired
	private ServicioUsuario srvUsuario;

	@Autowired
	private ServicioEmpresaInvitacion srvEmpresaInvitacion;

	@Autowired
	private ServicioAutenticacion srvAutenticacion;
	@Autowired
	private ServicioMail srvMail;

	@PostConstruct
	public void init() {
		this.usuarioAutenticado = srvAutenticacion.getUserDetailsCurrentUserLogged();
		logger.info("Empresa.init()");
		cargarUsuarios();

	}

	public void inicilizarAtributos() {
		emailInvitacion = null;
	}

	public void newCorreo() {
		emailInvitacion = null;
	}

	public void enviarCorreo() {
		String tokenUser = CifrarClave.generarToken();

		EmpresaInvitacion ei = new EmpresaInvitacion();
		ei.setCif(usuarioAutenticado.getUsuario().getEmpresa().getCif());
		ei.setEmail(emailInvitacion);
		ei.setToken(tokenUser);

		srvEmpresaInvitacion.deleteEmpresaInvitacionByCifAndEmail(usuarioAutenticado.getUsuario().getEmpresa().getCif(),
				emailInvitacion);
		srvEmpresaInvitacion.save(ei);

//		ExecutorService executor = Executors.newSingleThreadExecutor();
//		executor.submit(() -> {
		String body = "";
		body = "<center><img src='http://naventory.cerrajerianavarro.es/assets/img/logofinal.jpg'></center><hr/>";
		body += "<h2 style='color:green'>Bienvenido a Naventory V2</h2> <br><br>";
		body += "Has recibido una invitación para unirte como gestor de la empresa "
				+ usuarioAutenticado.getUsuario().getEmpresa().getNombre()
				+ ".<br/><strong>Rellena tus datos a través de este enlace: <a href='http://localhost:8080/naventory/registro.xhtml?email="
				+ emailInvitacion + "&empresa=" + usuarioAutenticado.getUsuario().getEmpresa().getCif() + "&token="
				+ tokenUser + "'>Rellenar mis datos</a>" + "<br/>";
		ArrayList<String> usuarios = new ArrayList<String>();
		usuarios.add(emailInvitacion);

		this.srvMail.sendEmail(usuarios,
				"Invitacion a empresa " + usuarioAutenticado.getUsuario().getEmpresa().getNombre(), body);
//
//		}).isDone();
		emailInvitacion = null;

		addInfo("empresa.invitacion.success");

	}

	public void cargarUsuarios() {
		this.usuarios = srvUsuario.findUsuarioByEmpresa(usuarioAutenticado.getUsuario().getEmpresa());
	}

	public void cambiarEstadoActivo(Usuario user) {
		user.setActivo(("Y".equals(user.getActivo())) ? "N" : "Y");
		srvUsuario.saveOrUpdate(user, false);
		addInfo("users.succesUpdate");
	}

	public void borrarUsuario(Usuario user) {

		srvUsuario.delete(user);
		this.usuarios.remove(user);
		addInfo("users.succesDelete");
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public String getEmailInvitacion() {
		return emailInvitacion;
	}

	public void setEmailInvitacion(String emailInvitacion) {
		this.emailInvitacion = emailInvitacion;
	}

}