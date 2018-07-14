package com.antoniojnavarro.naventory.services.commons;

import java.io.Serializable;
import java.util.List;

import com.antoniojnavarro.naventory.dao.commons.dto.paginationresult.PaginationResult;
import com.antoniojnavarro.naventory.dao.commons.enums.SortOrderEnum;
import com.antoniojnavarro.naventory.model.commons.GenericEntity;
import com.antoniojnavarro.naventory.model.commons.filters.SearchFilter;

public interface ServicioCrud<T extends GenericEntity, F extends SearchFilter, ID extends Serializable>
		extends Serializable {

	T findById(ID id) throws ServicioException;

	List<T> findBySearchFilter(F searchFilter) throws ServicioException;

	List<T> findBySearchFilter(F searchFilter, String sortField, SortOrderEnum sortOrder) throws ServicioException;

	// Metodo con paginacion y ordenacion personalizada, fuera de las indicadas
	// en la anotacion @OrderByColumn
	PaginationResult<T> findBySearchFilterPagination(F searchFilter, int pageNumber, int pageSize, String sortField,
			SortOrderEnum sortOrder) throws ServicioException;

	PaginationResult<T> findBySearchFilterPagination(F searchFilter, int pageNumber, int pageSize)
			throws ServicioException;

	List<T> findAll() throws ServicioException;

	boolean exists(T entity) throws ServicioException;

	boolean existsById(ID id) throws ServicioException;

	default T saveOrUpdate(T entity, boolean validate) throws ServicioException {
		if (validate) {
			validate(entity);
		}
		return save(entity);
	}

	default T saveOrUpdate(T entity) throws ServicioException {
		return this.saveOrUpdate(entity, true);
	}

	T save(T entity) throws ServicioException;

	void validate(T entity) throws ServicioException;

	void delete(T entity) throws ServicioException;

	void deleteRange(List<T> entity) throws ServicioException;

	void deleteById(ID id) throws ServicioException;

}
