package com.antoniojnavarro.naventory.app.security.services.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.antoniojnavarro.naventory.app.security.services.dto.UsuarioAutenticado;
import com.antoniojnavarro.naventory.model.entities.Role;
import com.antoniojnavarro.naventory.model.entities.Usuario;
import com.antoniojnavarro.naventory.services.api.ServicioUsuario;
import com.antoniojnavarro.naventory.services.commons.ServicioMensajesI18n;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private ServicioUsuario srvUsuario;

	@Autowired
	private ServicioMensajesI18n srvMensajes;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Usuario usuario = this.srvUsuario.findUsuarioByEmail(login);
		if (usuario == null) {
			throw new UsernameNotFoundException(this.srvMensajes.getMensajeI18n("login.notFound"));
		} else if (!"Y".equals(usuario.getActivo())) {
			UsernameNotFoundException e = new UsernameNotFoundException(this.srvMensajes.getMensajeI18n("login.lock"));
			e.addSuppressed(new Throwable("login.lock"));
			throw e;
		}
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		if (usuario.getRoles() != null) {
			for (Role current : usuario.getRoles()) {
				authorities.add(new SimpleGrantedAuthority(current.getRole().name()));
			}
		}
		return new UsuarioAutenticado(usuario.getEmail(), usuario.getPassword(), authorities, usuario);
	}
}
