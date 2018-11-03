package com.antoniojnavarro.naventory.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antoniojnavarro.naventory.dao.commons.dto.paginationresult.PaginationResult;
import com.antoniojnavarro.naventory.dao.commons.enums.SortOrderEnum;
import com.antoniojnavarro.naventory.dao.repositories.EventoDao;
import com.antoniojnavarro.naventory.model.entities.Evento;
import com.antoniojnavarro.naventory.model.entities.Usuario;
import com.antoniojnavarro.naventory.model.filters.EventoSearchFilter;
import com.antoniojnavarro.naventory.services.api.ServicioEvento;
import com.antoniojnavarro.naventory.services.api.ServicioUsuario;
import com.antoniojnavarro.naventory.services.commons.ServicioException;
import com.antoniojnavarro.naventory.services.commons.ServicioMensajesI18n;
import com.antoniojnavarro.naventory.services.commons.ServicioValidacion;

@Service
public class ServicioEventoImpl implements ServicioEvento {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ServicioValidacion srvValidacion;

	@Autowired
	private ServicioMensajesI18n srvMensajes;

	@Autowired
	private EventoDao eventoDao;

	@Override
	public Evento findById(String id) throws ServicioException {
		return this.eventoDao.findOne(id);
	}

	@Override
	public List<Evento> findBySearchFilter(EventoSearchFilter searchFilter) throws ServicioException {
		// TODO Auto-generated method stub
		return eventoDao.findBySearchFilter(searchFilter);
	}

	@Override
	public List<Evento> findBySearchFilter(EventoSearchFilter searchFilter, String sortField, SortOrderEnum sortOrder)
			throws ServicioException {
		// TODO Auto-generated method stub
		return this.eventoDao.findBySearchFilter(searchFilter, sortField, sortOrder);
	}

	@Override
	public PaginationResult<Evento> findBySearchFilterPagination(EventoSearchFilter searchFilter, int pageNumber,
			int pageSize, String sortField, SortOrderEnum sortOrder) throws ServicioException {
		return this.eventoDao.findBySearchFilterPagination(searchFilter, pageNumber, pageSize, sortField, sortOrder);
	}

	@Override
	public PaginationResult<Evento> findBySearchFilterPagination(EventoSearchFilter searchFilter, int pageNumber,
			int pageSize) throws ServicioException {
		// TODO Auto-generated method stub
		return this.eventoDao.findBySearchFilterPagination(searchFilter, pageNumber, pageSize);
	}

	@Override
	public List<Evento> findAll() throws ServicioException {
		// TODO Auto-generated method stub
		return (List<Evento>) this.eventoDao.findAll();
	}

	@Override
	public boolean exists(Evento entity) throws ServicioException {
		if (entity == null) {
			throw new IllegalArgumentException("Entity no puede ser un nulo");
		}
		if (entity.getIdEvento() == null)
			return false;
		return existsById(entity.getIdEvento());
	}

	@Override
	public boolean existsById(String id) throws ServicioException {
		// TODO Auto-generated method stub
		return this.existsById(id);
	}

	@Override
	public Evento save(Evento entity) throws ServicioException {
		// TODO Auto-generated method stub
		return this.eventoDao.save(entity);
	}

	@Autowired
	private ServicioUsuario srvUsuario;

	@Override
	public void validate(Evento entity) throws ServicioException {
		this.srvValidacion.isNull("Evento", entity);
		this.srvValidacion.isNull("Título", entity.getTitulo());

		if (!this.srvUsuario.existsUsuarioByEmail(entity.getUsuario().getEmail())) {
			throw new ServicioException(srvMensajes.getMensajeI18n("categorias.email.exist"));

		}

	}

	@Override
	public Evento saveOrUpdate(Evento entity, boolean validate) throws ServicioException {
		if (validate) {
			validate(entity);
		}
		return this.save(entity);
	}

	@Override
	public void delete(Evento entity) throws ServicioException {

		this.eventoDao.delete(entity);
	}

	@Override
	public void deleteRange(List<Evento> entity) throws ServicioException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteById(String id) throws ServicioException {
		this.eventoDao.delete(id);

	}

	@Override
	public List<Evento> findEventosByUsuario(Usuario user) throws ServicioException {
		// TODO Auto-generated method stub
		return this.eventoDao.findEventosByUsuario(user);
	}

}
