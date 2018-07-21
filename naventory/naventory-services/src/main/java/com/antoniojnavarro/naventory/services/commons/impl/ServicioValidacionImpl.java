package com.antoniojnavarro.naventory.services.commons.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antoniojnavarro.naventory.services.api.ServicioUsuario;
import com.antoniojnavarro.naventory.services.commons.ServicioException;
import com.antoniojnavarro.naventory.services.commons.ServicioMensajesI18n;
import com.antoniojnavarro.naventory.services.commons.ServicioValidacion;

@Service
public class ServicioValidacionImpl implements ServicioValidacion {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ServicioMensajesI18n srvMensajes;
	
	@Autowired
	private ServicioUsuario srvUsuario;

	@Override
	public void isNull(String fieldLabel, Object valueField) throws ServicioException {
		if (valueField == null || (valueField.getClass().equals(String.class) && ((String) valueField).isEmpty())) {
			throw new ServicioException(srvMensajes.getMensajeI18n("nulo", fieldLabel));
		}
	}
	@Override
	public void existUser(String id) {
		if (srvUsuario.existsById(id)) {
			throw new ServicioException("El usuario ya existe");
		}
	}
}
