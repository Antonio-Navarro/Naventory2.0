package com.antoniojnavarro.naventory.app.jsf.beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.antoniojnavarro.naventory.app.commons.PFScope;
import com.antoniojnavarro.naventory.app.util.CifrarClave;
import com.antoniojnavarro.naventory.app.util.Constantes;
import com.antoniojnavarro.naventory.model.entities.Role;
import com.antoniojnavarro.naventory.model.entities.Usuario;
import com.antoniojnavarro.naventory.services.api.ServicioRole;
import com.antoniojnavarro.naventory.services.api.ServicioUsuario;
import com.antoniojnavarro.naventory.services.commons.ServicioMail;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.json.JsonObject;
import com.restfb.json.JsonValue;
import com.restfb.types.User;

@Named("loginBean")
@Scope(value = PFScope.VIEW_SCOPED)
public class LoginBean extends MasterBean {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(LoginBean.class);

	// CAMPOS
	private String email;
	private String password;
	// ENTITIES
	private Usuario usuario;
	@Autowired
	private AuthenticationManager authenticationManager;

	// LISTAS

	// SERVICIOS
	@Autowired
	private ServicioUsuario srvUsuario;
	@Autowired
	private ServicioRole srvRole;
	@Autowired
	private ServicioMail srvMail;

	@PostConstruct
	public void init() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (!(auth instanceof AnonymousAuthenticationToken)) {

			FacesContext facesContext = FacesContext.getCurrentInstance();
			ExternalContext externalContext = facesContext.getExternalContext();
			try {
				externalContext.redirect(externalContext.getRequestContextPath() + "/private/home.xhtml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		logger.info("LoginBean.init()");

	}

	public String login() {

		try {
			Authentication auth = new UsernamePasswordAuthenticationToken(this.email, password);
			Authentication result = this.authenticationManager.authenticate(auth);
			SecurityContextHolder.getContext().setAuthentication(result);
			return Constantes.GO_TO_HOME;
		} catch (AuthenticationException e) {
			logger.info(e.getMessage(), e);
			addError("login.userOrPasswordIncorrect");
			// Nunca se debe retornar "null". Si se retorna "null" la vista no
			// se actualiza
			return new String();
		}
	}

	public String irARegistro() {
		return Constantes.GO_TO_REGISTER;
	}

	public String logout() {
		logger.info("Cerrando sesion");
		SecurityContextHolder.clearContext();
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return Constantes.GO_TO_LOGIN;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String loginFacebook() {
		String accesstoken = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("respuestaFacebook");
		logger.debug(accesstoken);

		if (accesstoken == null) {
			addError("error.login.facebook");
			return new String();
		}

		FacebookClient facebookClient = new DefaultFacebookClient(accesstoken, Version.VERSION_3_1);

		User user = facebookClient.fetchObject("me", User.class,
				Parameter.with("fields", "email,first_name,last_name"));

		JsonObject jsonObject = facebookClient.fetchObject("/" + user.getId() + "/picture", JsonObject.class,
				Parameter.with("height", "720"), Parameter.with("redirect", "false"));
		JsonValue jsonValue = jsonObject.get("data");
		JsonObject object = jsonValue.asObject();
		String profileImageUrl = object.get("url").asString();
		user.getPicture().setUrl(profileImageUrl);

		if (user == null) {
			addError("error.login.facebook");
			return new String();
		}
		logger.debug(user.getPicture().getUrl());

		usuario = srvUsuario.findById(user.getEmail());
		if (usuario == null) {
			usuario = new Usuario();
			usuario.setNombre(user.getFirstName());
			usuario.setApellido(user.getLastName());
			usuario.setEmail(user.getEmail());
			usuario.setEmpresa("Particular");
			String passwordGenerada = CifrarClave.generarPassword();
			usuario.setPassword(CifrarClave.encriptarClave(passwordGenerada));
			ArrayList<Role> roles = new ArrayList<>();
			roles.add(srvRole.findById(3));
			usuario.setRoles(roles);

			ExecutorService executor = Executors.newSingleThreadExecutor();
			executor.submit(() -> {
				String body = "";
				body = "<center><img src='http://naventory.cerrajerianavarro.es/assets/img/logofinal.jpg'></center><hr/><br> Hola "
						+ this.usuario.getNombre() + ", te damos la bienvenida a Naventory!!<br>";
				body += "<h3 style='color:red'>Se ha registrado correctamente en Naventory V2</h3> <br><br>";
				body += "Su contraseña para acceder a través del formulario de inicio de sesión es: <b>"
						+ passwordGenerada
						+ ".Válida hasta el próximo inicio de sesión con facebook. Cada inicio de sesión con Facebook se generará una nueva por temas de seguridad y será avisado del cambio de contraseña. </b>";
				this.srvMail.sendEmail(usuario.getEmail(), "Registro de usuario", body);

			});
		}
		// UNICA PARA CADA SESIÓN DE USUARIO, QUE SE REGISTRE A TRAVÉS DE FACEBOOK

		String passwordGenerada = CifrarClave.generarPassword();
		usuario.setPassword(CifrarClave.encriptarClave(passwordGenerada));

		this.srvUsuario.saveOrUpdate(usuario, false);
		Authentication auth = new UsernamePasswordAuthenticationToken(user.getEmail(), passwordGenerada,
				Arrays.asList(new SimpleGrantedAuthority("ROLE_FACEBOOK")));
		Authentication result = this.authenticationManager.authenticate(auth);
		SecurityContextHolder.getContext().setAuthentication(result);
		usuario = null;
		return Constantes.GO_TO_HOME;

	}
}
