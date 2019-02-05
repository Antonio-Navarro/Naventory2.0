package com.antoniojnavarro.naventory.app.security.social.providers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import com.antoniojnavarro.naventory.app.util.CifrarClave;
import com.antoniojnavarro.naventory.app.util.Constantes;
import com.antoniojnavarro.naventory.model.entities.Role;
import com.antoniojnavarro.naventory.model.entities.Usuario;
import com.antoniojnavarro.naventory.services.api.ServicioRole;
import com.antoniojnavarro.naventory.services.api.ServicioUsuario;
import com.antoniojnavarro.naventory.services.commons.ServicioMail;

@Service
public class BaseProvider implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	private String passwordGenerada;

	private Usuario usuario;
	@Autowired
	private ServicioUsuario srvUsuario;
	@Autowired
	private ServicioRole srvRole;
	@Autowired
	private ServicioMail srvMail;
	@Autowired
	private AuthenticationManager authenticationManager;

	protected String iniciarSesion(String email, String nombre, String apellidos, String fotoPerfil) {
		usuario = srvUsuario.findById(email);
		this.passwordGenerada = CifrarClave.generarPassword();

		if (usuario == null) {
			usuario = new Usuario();
			usuario.setNombre(nombre);
			usuario.setApellido(apellidos);
			usuario.setEmail(email);
			usuario.setEmpresa("Particular");
			usuario.setFotoPerf(parsearImagenBlob(fotoPerfil));

			usuario.setPassword(CifrarClave.encriptarClave(passwordGenerada));
			ArrayList<Role> roles = new ArrayList<>();
			roles.add(srvRole.findById(2));
			usuario.setRoles(roles);

			ExecutorService executor = Executors.newSingleThreadExecutor();
			executor.submit(() -> {
				String body = "";
				body = "<center><img src='http://naventory.cerrajerianavarro.es/assets/img/logofinal.jpg'></center><hr/><br> Hola "
						+ this.usuario.getNombre() + ", te damos la bienvenida a Naventory!!<br>";
				body += "<h3 style='color:red'>Se ha registrado correctamente en Naventory V2</h3> <br><br>";
				body += "Su contraseña para acceder a través del formulario de inicio de sesión es: <b>"
						+ passwordGenerada + ".Válida hasta el próximo inicio de sesión con una red social. </b>";
				this.srvMail.sendEmail(usuario.getEmail(), "Registro de usuario", body);

			});
		}
		// UNICA PARA CADA SESIÓN DE USUARIO, QUE SE REGISTRE A TRAVÉS DE UNA RED SOCIAL

		usuario.setPassword(CifrarClave.encriptarClave(passwordGenerada));
		this.srvUsuario.saveOrUpdate(usuario, false);
		return autologin();

	}

	protected String autologin() {
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		if (usuario.getRoles() != null) {
			for (Role current : usuario.getRoles()) {
				authorities.add(new SimpleGrantedAuthority(current.getRole().name()));
			}
		}
		Authentication auth = new UsernamePasswordAuthenticationToken(usuario.getEmail(), passwordGenerada,
				authorities);
		new SimpleUrlAuthenticationSuccessHandler();
		Authentication result = this.authenticationManager.authenticate(auth);
		SecurityContextHolder.getContext().setAuthentication(result);

		return Constantes.GO_TO_HOME;
	}

	protected byte[] parsearImagenBlob(String profileImageUrl) {
		URL url;
		try {
			url = new URL(profileImageUrl);
			BufferedImage imagen = ImageIO.read(url);
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(imagen, "jpg", os);
			byte[] buffer = os.toByteArray();
			return buffer;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
