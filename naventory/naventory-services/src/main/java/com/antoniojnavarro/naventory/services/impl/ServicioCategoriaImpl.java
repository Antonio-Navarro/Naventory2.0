package com.antoniojnavarro.naventory.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antoniojnavarro.naventory.model.entities.Categoria;
import com.antoniojnavarro.naventory.model.entities.Empresa;
import com.antoniojnavarro.naventory.model.filters.CategoriaSearchFilter;
import com.antoniojnavarro.naventory.repository.commons.dto.paginationresult.PaginationResult;
import com.antoniojnavarro.naventory.repository.commons.enums.SortOrderEnum;
import com.antoniojnavarro.naventory.repository.repositories.CategoriaRepository;
import com.antoniojnavarro.naventory.services.api.ServicioCategoria;
import com.antoniojnavarro.naventory.services.api.ServicioEmpresa;
import com.antoniojnavarro.naventory.services.commons.ServicioException;
import com.antoniojnavarro.naventory.services.commons.ServicioMensajesI18n;
import com.antoniojnavarro.naventory.services.commons.ServicioValidacion;

@Service
public class ServicioCategoriaImpl implements ServicioCategoria {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ServicioValidacion srvValidacion;
	@Autowired
	private ServicioEmpresa srvEmpresa;

	@Autowired
	private ServicioMensajesI18n srvMensajes;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Override
	public Categoria findById(Integer id) throws ServicioException {
		return this.categoriaRepository.findOne(id);
	}

	@Override
	public List<Categoria> findBySearchFilter(CategoriaSearchFilter searchFilter) throws ServicioException {
		return categoriaRepository.findBySearchFilter(searchFilter);
	}

	@Override
	public List<Categoria> findBySearchFilter(CategoriaSearchFilter searchFilter, String sortField,
			SortOrderEnum sortOrder) throws ServicioException {
		return this.categoriaRepository.findBySearchFilter(searchFilter, sortField, sortOrder);
	}

	@Override
	public PaginationResult<Categoria> findBySearchFilterPagination(CategoriaSearchFilter searchFilter, int pageNumber,
			int pageSize, String sortField, SortOrderEnum sortOrder) throws ServicioException {
		return this.categoriaRepository.findBySearchFilterPagination(searchFilter, pageNumber, pageSize, sortField, sortOrder);
	}

	@Override
	public PaginationResult<Categoria> findBySearchFilterPagination(CategoriaSearchFilter searchFilter, int pageNumber,
			int pageSize) throws ServicioException {
		return this.categoriaRepository.findBySearchFilterPagination(searchFilter, pageNumber, pageSize);
	}

	@Override
	public List<Categoria> findAll() throws ServicioException {
		return (List<Categoria>) this.categoriaRepository.findAll();
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
		return this.existsById(id);
	}

	@Override
	public Categoria save(Categoria entity) throws ServicioException {
		return this.categoriaRepository.save(entity);
	}

	@Override
	public void validate(Categoria entity) throws ServicioException {
		this.srvValidacion.isNull("Categoria", entity);
		this.srvValidacion.isNull("Nombre", entity.getNomCat());
		if (!this.srvEmpresa.existsEmpresaByCif(entity.getEmpresa().getCif())) {
			throw new ServicioException(srvMensajes.getMensajeI18n("cif.noexist"));

		}

	}

	@Override
	public Categoria saveOrUpdate(Categoria entity, boolean validate) throws ServicioException {
		if (validate) {
			validate(entity);
		}
		return this.save(entity);
	}

	@Override
	public void delete(Categoria entity) throws ServicioException {

		this.categoriaRepository.delete(entity);
	}

	@Override
	public void deleteRange(List<Categoria> entity) throws ServicioException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteById(Integer id) throws ServicioException {
		this.categoriaRepository.delete(id);

	}

	@Override
	public List<Categoria> findCategoriasByEmpresa(Empresa empresa) throws ServicioException {
		return this.categoriaRepository.findCategoriasByEmpresa(empresa);
	}
}
