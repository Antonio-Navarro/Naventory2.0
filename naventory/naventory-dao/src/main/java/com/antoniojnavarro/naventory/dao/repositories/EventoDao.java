package com.antoniojnavarro.naventory.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.antoniojnavarro.naventory.dao.commons.api.Dao;
import com.antoniojnavarro.naventory.model.entities.Evento;
import com.antoniojnavarro.naventory.model.entities.Usuario;
import com.antoniojnavarro.naventory.model.filters.EventoSearchFilter;

public interface EventoDao extends Dao<Evento, EventoSearchFilter, String> {

	@Query("SELECT c FROM Evento c WHERE c.usuario = ?1")
	List<Evento> findEventosByUsuario(Usuario user);

}
