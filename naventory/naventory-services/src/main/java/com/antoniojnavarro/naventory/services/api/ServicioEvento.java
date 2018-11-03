package com.antoniojnavarro.naventory.services.api;

import java.util.List;

import com.antoniojnavarro.naventory.model.entities.Evento;
import com.antoniojnavarro.naventory.model.entities.Usuario;
import com.antoniojnavarro.naventory.model.filters.EventoSearchFilter;
import com.antoniojnavarro.naventory.services.commons.ServicioCrud;
import com.antoniojnavarro.naventory.services.commons.ServicioException;

public interface ServicioEvento extends ServicioCrud<Evento, EventoSearchFilter, String> {

	List<Evento> findEventosByUsuario(Usuario user) throws ServicioException;


}
