package com.antoniojnavarro.naventory.app.security.services.impl;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.antoniojnavarro.naventory.app.security.services.api.ServicioAutenticacion;
import com.antoniojnavarro.naventory.app.security.services.dto.UsuarioAutenticado;
import com.antoniojnavarro.naventory.services.commons.ServicioException;

@Service
public class ServicioAutenticacionImpl implements ServicioAutenticacion {

	private static final long serialVersionUID = 1L;

	@Override
	public String getUsernameCurrentUserLogged() throws ServicioException {
		return this.getUserDetailsCurrentUserLogged().getUsername();
	}

	@Override
	public UsuarioAutenticado getUserDetailsCurrentUserLogged() throws ServicioException {
		return ((UsuarioAutenticado) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
	}

	@Override
	public Collection<? extends GrantedAuthority> getRolesCurrentUserLogged() throws ServicioException {
		return SecurityContextHolder.getContext().getAuthentication().getAuthorities();
	}

}
