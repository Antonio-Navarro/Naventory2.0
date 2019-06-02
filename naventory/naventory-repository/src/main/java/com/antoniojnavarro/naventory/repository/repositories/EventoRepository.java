package com.antoniojnavarro.naventory.repository.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.antoniojnavarro.naventory.model.entities.Evento;
import com.antoniojnavarro.naventory.model.entities.Usuario;
import com.antoniojnavarro.naventory.model.filters.EventoSearchFilter;
import com.antoniojnavarro.naventory.repository.commons.api.BaseRepository;

public interface EventoRepository extends BaseRepository<Evento, EventoSearchFilter, String> {

	@Query("SELECT c FROM Evento c WHERE c.usuario = ?1")
	List<Evento> findEventosByUsuario(Usuario user);

}
