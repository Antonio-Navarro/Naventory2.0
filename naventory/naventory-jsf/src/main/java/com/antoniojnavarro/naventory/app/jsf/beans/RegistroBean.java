package com.antoniojnavarro.naventory.app.jsf.beans;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.apache.tools.mail.MailMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.antoniojnavarro.naventory.app.commons.PFScope;
import com.antoniojnavarro.naventory.app.util.CifrarClave;
import com.antoniojnavarro.naventory.app.util.Constantes;
import com.antoniojnavarro.naventory.model.entities.Usuario;
import com.antoniojnavarro.naventory.services.api.ServicioUsuario;
import com.antoniojnavarro.naventory.services.commons.ServicioException;
import com.antoniojnavarro.naventory.services.commons.ServicioMensajesI18n;

import net.bytebuddy.description.ModifierReviewable.OfEnumeration;

@Named("registroBean")
@Scope(value = PFScope.VIEW_SCOPED)
public class RegistroBean extends MasterBean {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(RegistroBean.class);



	// CAMPOS	
	private String confPassword;
	// ENTITIES
	private Usuario usuario;

	// LISTAS

	// SERVICIOS
	@Autowired
	private ServicioUsuario srvUsuario;
	
	@PostConstruct
	public void init() {
		inicializarUsuario();
		logger.info("LoginBean.init()");
	}

	public void inicializarUsuario() {
		usuario = new Usuario();
		confPassword= "";
	}
	
	public void validarIgualPassword() {
		if(!usuario.getPassword().equals(confPassword)){			
			throw new ServicioException("login.passwords.distinct");			
		}
	}
	
	public void register(){
		validarIgualPassword();
		usuario.setPassword(CifrarClave.encriptarClave(usuario.getPassword()));		
		this.srvUsuario.save(usuario);
		inicializarUsuario();
	}
	
	public String volverInicioSesion(){
		return Constantes.GO_TO_LOGIN;
	}
	
	
	public String login() {

		return Constantes.GO_TO_HOME;

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
	


}
