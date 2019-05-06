package com.antoniojnavarro.naventory.app.security.services.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.antoniojnavarro.naventory.model.entities.Usuario;

public class UsuarioAutenticado extends User {

	private static final long serialVersionUID = 1L;

	private String email;
	private String nombre;
	private String apellido;
	private Usuario usuario;

	public UsuarioAutenticado(String username, String password, Collection<? extends GrantedAuthority> authorities,
			Usuario entity) {
		super(username, password, authorities);
		if (entity == null) {
			throw new IllegalArgumentException("El parámetro entity no puede ser nulo");
		}
		this.email = entity.getEmail();
		this.nombre = entity.getNombre();
		this.apellido = entity.getApellido();
		this.usuario = entity;

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
