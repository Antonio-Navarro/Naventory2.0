package com.antoniojnavarro.naventory.app.security.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.antoniojnavarro.naventory.app.security.services.api.ServicioSeguridadUsuario;
import com.antoniojnavarro.naventory.model.entities.Usuario;
import com.antoniojnavarro.naventory.services.api.ServicioUsuario;
import com.antoniojnavarro.naventory.services.commons.ServicioException;
import com.antoniojnavarro.naventory.services.commons.ServicioMensajesI18n;
import com.antoniojnavarro.naventory.services.commons.ServicioValidacion;

@Service
public class ServicioSeguridadUsuarioImpl implements ServicioSeguridadUsuario {

	@Autowired
	private ServicioUsuario srvUsuario;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private ServicioMensajesI18n srvMensajes;

	@Autowired
	private ServicioValidacion srvValidacion;

	@Override
	public Usuario saveOrUpdate(Usuario entity) throws ServicioException {

		this.srvValidacion.isNull("Usuario", entity);
		this.srvValidacion.isNull("Nombre", entity.getNombre());
		this.srvValidacion.isNull("Apellidos", entity.getApellido());
		this.srvValidacion.isNull("Empresa", entity.getEmpresa());
		this.srvValidacion.isNull("Rol", entity.getRoles());

		if (entity.getEmail() == null || entity.getEmail().isEmpty()) {
			// Nuevo usuario
			this.srvValidacion.isNull("Clave", entity.getPassword());
			entity.setPassword(this.bCryptPasswordEncoder.encode(entity.getPassword()));
		} else {
			Usuario persistInDatabase = this.srvUsuario.findById(entity.getEmail());
			if (persistInDatabase == null) {
				throw new ServicioException(this.srvMensajes.getMensajeI18n("usuario.noexiste"));
			}
			// Parece que el usuario ya existe
//			if (entity.getPassword() == null || entity.getPassword().isEmpty()) {
//				entity.setPassword(persistInDatabase.getPassword());
//			} else if (!entity.getPassword().equals(persistInDatabase.getPassword())) {
//				entity.setPassword(this.bCryptPasswordEncoder.encode(entity.getPassword()));
//			}
		}
		return this.srvUsuario.saveOrUpdate(entity, false);
	}

}
