package com.antoniojnavarro.naventory.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antoniojnavarro.naventory.dao.commons.dto.paginationresult.PaginationResult;
import com.antoniojnavarro.naventory.dao.commons.enums.SortOrderEnum;
import com.antoniojnavarro.naventory.dao.repositories.CategoriaDao;
import com.antoniojnavarro.naventory.model.entities.Categoria;
import com.antoniojnavarro.naventory.model.entities.Usuario;
import com.antoniojnavarro.naventory.model.filters.CategoriaSearchFilter;
import com.antoniojnavarro.naventory.services.api.ServicioCategoria;
import com.antoniojnavarro.naventory.services.api.ServicioUsuario;
import com.antoniojnavarro.naventory.services.commons.ServicioException;
import com.antoniojnavarro.naventory.services.commons.ServicioMensajesI18n;
import com.antoniojnavarro.naventory.services.commons.ServicioValidacion;

@Service
public class ServicioCategoriaImpl implements ServicioCategoria {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ServicioValidacion srvValidacion;

	@Autowired
	private ServicioMensajesI18n srvMensajes;


	@Autowired
	private CategoriaDao categoriaDao;


	@Override
	public Categoria findById(Integer id) throws ServicioException {
		return this.categoriaDao.findOne(id);
	}


	@Override
	public List<Categoria> findBySearchFilter(CategoriaSearchFilter searchFilter) throws ServicioException {
		// TODO Auto-generated method stub
		return categoriaDao.findBySearchFilter(searchFilter);
	}


	@Override
	public List<Categoria> findBySearchFilter(CategoriaSearchFilter searchFilter, String sortField,
			SortOrderEnum sortOrder) throws ServicioException {
		// TODO Auto-generated method stub
		return this.categoriaDao.findBySearchFilter(searchFilter, sortField, sortOrder);
	}


	@Override
	public PaginationResult<Categoria> findBySearchFilterPagination(CategoriaSearchFilter searchFilter, int pageNumber,
			int pageSize, String sortField, SortOrderEnum sortOrder) throws ServicioException {
		return this.categoriaDao.findBySearchFilterPagination(searchFilter, pageNumber, pageSize, sortField, sortOrder);
	}


	@Override
	public PaginationResult<Categoria> findBySearchFilterPagination(CategoriaSearchFilter searchFilter, int pageNumber,
			int pageSize) throws ServicioException {
		// TODO Auto-generated method stub
		return this.categoriaDao.findBySearchFilterPagination(searchFilter, pageNumber, pageSize);
	}


	@Override
	public List<Categoria> findAll() throws ServicioException {
		// TODO Auto-generated method stub
		return (List<Categoria>) this.categoriaDao.findAll();
	}


	@Override
	public boolean exists(Categoria entity) throws ServicioException {
		if (entity == null) {
			throw new IllegalArgumentException("Entity no puede ser un nulo");
		}
		if (entity.getIdCat() == null)
			return false;
		return existsById(entity.getIdCat());
	}


	@Override
	public boolean existsById(Integer id) throws ServicioException {
		// TODO Auto-generated method stub
		return this.existsById(id);
	}


	@Override
	public Categoria save(Categoria entity) throws ServicioException {
		// TODO Auto-generated method stub
		return this.categoriaDao.save(entity);
	}

	@Autowired
	private ServicioUsuario srvUsuario;
	
	@Override
	public void validate(Categoria entity) throws ServicioException {
		this.srvValidacion.isNull("Categoria", entity);
		this.srvUsuario.validateEmail(entity.getUsuario().getEmail());
		
	}
	@Override
	public Categoria saveOrUpdate(Categoria entity, boolean validate) throws ServicioException {
		if(validate) {
			validate(entity);
		}
		return this.save(entity);
	}

	@Override
	public void delete(Categoria entity) throws ServicioException {

		this.categoriaDao.delete(entity);
	}


	@Override
	public void deleteRange(List<Categoria> entity) throws ServicioException {
		throw new UnsupportedOperationException();		
	}


	@Override
	public void deleteById(Integer id) throws ServicioException {
		this.categoriaDao.delete(id);
		
	}


	@Override
	public List<Categoria> findCategoriasByUsuario(Usuario user) throws ServicioException {
		// TODO Auto-generated method stub
		return this.categoriaDao.findCategoriasByUsuario(user);
	}
}
