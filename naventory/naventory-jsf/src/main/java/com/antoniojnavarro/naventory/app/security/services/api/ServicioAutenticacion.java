package com.antoniojnavarro.naventory.app.security.services.api;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import com.antoniojnavarro.naventory.app.security.services.dto.UsuarioAutenticado;
import com.antoniojnavarro.naventory.services.commons.ServicioException;

public interface ServicioAutenticacion extends Serializable {

	String getUsernameCurrentUserLogged() throws ServicioException;

	UsuarioAutenticado getUserDetailsCurrentUserLogged() throws ServicioException;

	Collection<? extends GrantedAuthority> getRolesCurrentUserLogged() throws ServicioException;

}
