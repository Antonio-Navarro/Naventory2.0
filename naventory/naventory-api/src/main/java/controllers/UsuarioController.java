package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.antoniojnavarro.naventory.model.entities.Usuario;
import com.antoniojnavarro.naventory.services.api.ServicioUsuario;

@RestController
public class UsuarioController {

	@Autowired
	private ServicioUsuario srvUsuario;

	@GetMapping("/user/{email}")
	public ResponseEntity<?> getUsersByEmail(@PathVariable("email") String email) {
		Usuario user = this.srvUsuario.findUsuarioByEmail(email);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(user);
	}

}
