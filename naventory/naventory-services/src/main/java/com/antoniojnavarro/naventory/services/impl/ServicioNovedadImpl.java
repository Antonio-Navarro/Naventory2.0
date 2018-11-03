package com.antoniojnavarro.naventory.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antoniojnavarro.naventory.dao.commons.dto.paginationresult.PaginationResult;
import com.antoniojnavarro.naventory.dao.commons.enums.SortOrderEnum;
import com.antoniojnavarro.naventory.dao.repositories.NovedadDao;
import com.antoniojnavarro.naventory.model.entities.Novedad;
import com.antoniojnavarro.naventory.model.entities.Usuario;
import com.antoniojnavarro.naventory.model.filters.NovedadSearchFilter;
import com.antoniojnavarro.naventory.services.api.ServicioNovedad;
import com.antoniojnavarro.naventory.services.api.ServicioUsuario;
import com.antoniojnavarro.naventory.services.commons.ServicioException;
import com.antoniojnavarro.naventory.services.commons.ServicioMail;
import com.antoniojnavarro.naventory.services.commons.ServicioMensajesI18n;
import com.antoniojnavarro.naventory.services.commons.ServicioValidacion;

@Service
public class ServicioNovedadImpl implements ServicioNovedad {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ServicioValidacion srvValidacion;

	@Autowired
	private ServicioMensajesI18n srvMensajes;
	@Autowired
	ServicioMail srvMail;
	@Autowired
	private NovedadDao novedadDao;

	@Override
	public Novedad findById(Integer id) throws ServicioException {
		return this.novedadDao.findOne(id);
	}

	@Override
	public List<Novedad> findBySearchFilter(NovedadSearchFilter searchFilter) throws ServicioException {
		// TODO Auto-generated method stub
		return novedadDao.findBySearchFilter(searchFilter);
	}

	@Override
	public List<Novedad> findBySearchFilter(NovedadSearchFilter searchFilter, String sortField, SortOrderEnum sortOrder)
			throws ServicioException {
		// TODO Auto-generated method stub
		return this.novedadDao.findBySearchFilter(searchFilter, sortField, sortOrder);
	}

	@Override
	public PaginationResult<Novedad> findBySearchFilterPagination(NovedadSearchFilter searchFilter, int pageNumber,
			int pageSize, String sortField, SortOrderEnum sortOrder) throws ServicioException {
		return this.novedadDao.findBySearchFilterPagination(searchFilter, pageNumber, pageSize, sortField, sortOrder);
	}

	@Override
	public PaginationResult<Novedad> findBySearchFilterPagination(NovedadSearchFilter searchFilter, int pageNumber,
			int pageSize) throws ServicioException {
		// TODO Auto-generated method stub
		return this.novedadDao.findBySearchFilterPagination(searchFilter, pageNumber, pageSize);
	}

	@Override
	public List<Novedad> findAll() throws ServicioException {
		// TODO Auto-generated method stub
		return (List<Novedad>) this.novedadDao.findAll();
	}

	@Override
	public boolean exists(Novedad entity) throws ServicioException {
		if (entity == null) {
			throw new IllegalArgumentException("Entity no puede ser un nulo");
		}
		if (entity.getId() == null)
			return false;
		return existsById(entity.getId());
	}

	@Override
	public boolean existsById(Integer id) throws ServicioException {
		// TODO Auto-generated method stub
		return this.existsById(id);
	}

	@Override
	public Novedad save(Novedad entity) throws ServicioException {
		// TODO Auto-generated method stub
		return this.novedadDao.save(entity);
	}

	@Autowired
	private ServicioUsuario srvUsuario;

	@Override
	public void validate(Novedad entity) throws ServicioException {
		this.srvValidacion.isNull("Novedad", entity);
		if (!this.srvUsuario.existsUsuarioByEmail(entity.getUsuario().getEmail())) {
			throw new ServicioException(srvMensajes.getMensajeI18n("categorias.email.exist"));

		}

	}

	@Override
	public Novedad saveOrUpdate(Novedad entity, boolean validate) throws ServicioException {
		if (validate) {
			validate(entity);
		}
		return this.save(entity);
	}

	@Override
	public void delete(Novedad entity) throws ServicioException {

		this.novedadDao.delete(entity);
	}

	@Override
	public void deleteRange(List<Novedad> entity) throws ServicioException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteById(Integer id) throws ServicioException {
		this.novedadDao.delete(id);

	}

	@Override
	public List<Novedad> findNovedadesByUsuario(Usuario user, Integer limit) throws ServicioException {
		// TODO Auto-generated method stub
		List<Novedad> lista = this.novedadDao.findNovedadesByUsuario(user);

		return lista.subList(0, (lista.size() <= limit) ? lista.size() : limit);
	}

}
