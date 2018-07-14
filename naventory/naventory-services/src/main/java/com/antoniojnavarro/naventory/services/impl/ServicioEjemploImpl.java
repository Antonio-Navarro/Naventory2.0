package com.antoniojnavarro.naventory.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antoniojnavarro.naventory.dao.commons.dto.paginationresult.PaginationResult;
import com.antoniojnavarro.naventory.dao.commons.enums.SortOrderEnum;
import com.antoniojnavarro.naventory.dao.repositories.EjemploDao;
import com.antoniojnavarro.naventory.model.entities.Ejemplo;
import com.antoniojnavarro.naventory.model.filters.EjemploSearchFilter;
import com.antoniojnavarro.naventory.services.api.ServicioEjemplo;
import com.antoniojnavarro.naventory.services.commons.ServicioException;
import com.antoniojnavarro.naventory.services.commons.ServicioValidacion;

@Service
public class ServicioEjemploImpl implements ServicioEjemplo {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ServicioValidacion srvValidacion;

	@Autowired
	private EjemploDao ejemploDao;

	@Override
	public Ejemplo findById(Long id) throws ServicioException {
		return this.ejemploDao.findOne(id);
	}

	@Override
	public List<Ejemplo> findBySearchFilter(EjemploSearchFilter filtro) throws ServicioException {
		return this.ejemploDao.findBySearchFilter(filtro);
	}

	@Override
	public List<Ejemplo> findBySearchFilter(EjemploSearchFilter searchFilter, String sortField, SortOrderEnum sortOrder)
			throws ServicioException {
		return this.ejemploDao.findBySearchFilter(searchFilter, sortField, sortOrder);
	}

	@Override
	public PaginationResult<Ejemplo> findBySearchFilterPagination(EjemploSearchFilter searchFilter, int pageNumber,
			int pageSize, String sortField, SortOrderEnum sortOrder) throws ServicioException {
		return this.ejemploDao.findBySearchFilterPagination(searchFilter, pageNumber, pageSize, sortField, sortOrder);
	}

	@Override
	public PaginationResult<Ejemplo> findBySearchFilterPagination(EjemploSearchFilter searchFilter, int pageNumber,
			int pageSize) throws ServicioException {
		return this.ejemploDao.findBySearchFilterPagination(searchFilter, pageNumber, pageSize);
	}

	@Override
	public List<Ejemplo> findAll() throws ServicioException {
		return (List<Ejemplo>) this.ejemploDao.findAll();
	}

	@Override
	public boolean exists(Ejemplo entity) throws ServicioException {
		if (entity == null || entity.getId() == null || entity.getId() <= 0) {
			return false;
		}
		return existsById(entity.getId());
	}

	@Override
	public boolean existsById(Long id) throws ServicioException {
		return this.ejemploDao.exists(id);
	}

	@Override
	public Ejemplo save(Ejemplo entity) throws ServicioException {
		return this.ejemploDao.save(entity);
	}

	@Override
	public void validate(Ejemplo entity) throws ServicioException {
		this.srvValidacion.isNull("Nombre", entity.getNombre());
		this.srvValidacion.isNull("F. Alta", entity.getFechaAlta());
	}

	@Override
	public void delete(Ejemplo entity) throws ServicioException {
		this.ejemploDao.delete(entity);
	}

	@Override
	public void deleteById(Long id) throws ServicioException {
		this.ejemploDao.delete(id);
	}

	@Override
	public void deleteRange(List<Ejemplo> entity) throws ServicioException {
		this.ejemploDao.delete(entity);

	}
}
