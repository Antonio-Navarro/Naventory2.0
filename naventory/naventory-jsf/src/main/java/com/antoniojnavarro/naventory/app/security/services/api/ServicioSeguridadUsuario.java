package com.antoniojnavarro.naventory.app.security.services.api;

import com.antoniojnavarro.naventory.model.entities.Usuario;
import com.antoniojnavarro.naventory.services.commons.ServicioException;

public interface ServicioSeguridadUsuario {

	Usuario saveOrUpdate(Usuario entity) throws ServicioException;

}
