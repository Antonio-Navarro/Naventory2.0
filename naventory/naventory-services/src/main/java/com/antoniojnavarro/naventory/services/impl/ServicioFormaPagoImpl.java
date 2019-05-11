package com.antoniojnavarro.naventory.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antoniojnavarro.naventory.model.entities.FormaPago;
import com.antoniojnavarro.naventory.model.filters.FormaPagoSearchFilter;
import com.antoniojnavarro.naventory.repository.commons.dto.paginationresult.PaginationResult;
import com.antoniojnavarro.naventory.repository.commons.enums.SortOrderEnum;
import com.antoniojnavarro.naventory.repository.repositories.FormaPagoRepository;
import com.antoniojnavarro.naventory.services.api.ServicioFormaPago;
import com.antoniojnavarro.naventory.services.commons.ServicioException;
import com.antoniojnavarro.naventory.services.commons.ServicioValidacion;

@Service
public class ServicioFormaPagoImpl implements ServicioFormaPago {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ServicioValidacion srvValidacion;

	@Autowired
	private FormaPagoRepository formaPagoRepository;


	@Override
	public FormaPago findById(Integer id) throws ServicioException {
		return this.formaPagoRepository.findOne(id);
	}


	@Override
	public List<FormaPago> findBySearchFilter(FormaPagoSearchFilter searchFilter) throws ServicioException {
		// TODO Auto-generated method stub
		return formaPagoRepository.findBySearchFilter(searchFilter);
	}


	@Override
	public List<FormaPago> findBySearchFilter(FormaPagoSearchFilter searchFilter, String sortField,
			SortOrderEnum sortOrder) throws ServicioException {
		// TODO Auto-generated method stub
		return this.formaPagoRepository.findBySearchFilter(searchFilter, sortField, sortOrder);
	}


	@Override
	public PaginationResult<FormaPago> findBySearchFilterPagination(FormaPagoSearchFilter searchFilter, int pageNumber,
			int pageSize, String sortField, SortOrderEnum sortOrder) throws ServicioException {
		return this.formaPagoRepository.findBySearchFilterPagination(searchFilter, pageNumber, pageSize, sortField, sortOrder);
	}


	@Override
	public PaginationResult<FormaPago> findBySearchFilterPagination(FormaPagoSearchFilter searchFilter, int pageNumber,
			int pageSize) throws ServicioException {
		// TODO Auto-generated method stub
		return this.formaPagoRepository.findBySearchFilterPagination(searchFilter, pageNumber, pageSize);
	}


	@Override
	public List<FormaPago> findAll() throws ServicioException {
		// TODO Auto-generated method stub
		return (List<FormaPago>) this.formaPagoRepository.findAll();
	}


	@Override
	public boolean exists(FormaPago entity) throws ServicioException {
		if (entity == null) {
			throw new IllegalArgumentException("Entity no puede ser un nulo");
		}
		if (entity.getIdPago() == null)
			return false;
		return existsById(entity.getIdPago());
	}


	@Override
	public boolean existsById(Integer id) throws ServicioException {
		// TODO Auto-generated method stub
		return this.existsById(id);
	}


	@Override
	public FormaPago save(FormaPago entity) throws ServicioException {
		// TODO Auto-generated method stub
		return this.formaPagoRepository.save(entity);
	}

	
	@Override
	public void validate(FormaPago entity) throws ServicioException {
		this.srvValidacion.isNull("FormaPago", entity);
		this.srvValidacion.isNull("Id", entity.getIdPago());
	}
	@Override
	public FormaPago saveOrUpdate(FormaPago entity, boolean validate) throws ServicioException {
		if(validate) {
			validate(entity);
		}
		return this.save(entity);
	}

	@Override
	public void delete(FormaPago entity) throws ServicioException {

		this.formaPagoRepository.delete(entity);
	}


	@Override
	public void deleteRange(List<FormaPago> entity) throws ServicioException {
		throw new UnsupportedOperationException();		
	}


	@Override
	public void deleteById(Integer id) throws ServicioException {
		this.formaPagoRepository.delete(id);
		
	}


	@Override
	public List<FormaPago> findFormasPagoByUsuario() throws ServicioException {
		// TODO Auto-generated method stub
		return this.formaPagoRepository.findFormasPagoByUsuario(); 
	}
}
