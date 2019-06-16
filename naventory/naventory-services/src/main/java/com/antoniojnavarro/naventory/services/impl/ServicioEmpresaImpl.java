package com.antoniojnavarro.naventory.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antoniojnavarro.naventory.model.entities.Empresa;
import com.antoniojnavarro.naventory.model.filters.EmpresaSearchFilter;
import com.antoniojnavarro.naventory.repository.commons.dto.paginationresult.PaginationResult;
import com.antoniojnavarro.naventory.repository.commons.enums.SortOrderEnum;
import com.antoniojnavarro.naventory.repository.repositories.EmpresaRepository;
import com.antoniojnavarro.naventory.services.api.ServicioEmpresa;
import com.antoniojnavarro.naventory.services.commons.ServicioException;
import com.antoniojnavarro.naventory.services.commons.ServicioMensajesI18n;
import com.antoniojnavarro.naventory.services.commons.ServicioValidacion;

@Service
public class ServicioEmpresaImpl implements ServicioEmpresa {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ServicioValidacion srvValidacion;

	@Autowired
	private ServicioMensajesI18n srvMensajes;
	@Autowired
	private EmpresaRepository empresaRepository;

	@Override
	public Empresa findById(String id) throws ServicioException {
		return this.empresaRepository.findOne(id);
	}

	@Override
	public List<Empresa> findBySearchFilter(EmpresaSearchFilter searchFilter) throws ServicioException {
		return this.empresaRepository.findBySearchFilter(searchFilter);
	}

	@Override
	public List<Empresa> findBySearchFilter(EmpresaSearchFilter searchFilter, String sortField, SortOrderEnum sortOrder)
			throws ServicioException {
		return this.empresaRepository.findBySearchFilter(searchFilter, sortField, sortOrder);

	}

	@Override
	public PaginationResult<Empresa> findBySearchFilterPagination(EmpresaSearchFilter searchFilter, int pageNumber,
			int pageSize, String sortField, SortOrderEnum sortOrder) throws ServicioException {
		return this.empresaRepository.findBySearchFilterPagination(searchFilter, pageNumber, pageSize, sortField, sortOrder);
	}

	@Override
	public PaginationResult<Empresa> findBySearchFilterPagination(EmpresaSearchFilter searchFilter, int pageNumber,
			int pageSize) throws ServicioException {
		return this.empresaRepository.findBySearchFilterPagination(searchFilter, pageNumber, pageSize);
	}

	@Override
	public List<Empresa> findAll() throws ServicioException {
		return (List<Empresa>) this.empresaRepository.findAll();
	}

	@Override
	public boolean exists(Empresa entity) throws ServicioException {
		if (entity == null) {
			throw new IllegalArgumentException("Entity no puede ser un nulo");
		}
		if (entity.getCif() == null)
			return false;
		return existsById(entity.getCif());
	}

	@Override
	public boolean existsById(String id) throws ServicioException {
		return this.empresaRepository.exists(id);

	}

	@Override
	public Empresa save(Empresa entity) throws ServicioException {
		return this.empresaRepository.save(entity);
	}

	@Override
	public void delete(Empresa entity) throws ServicioException {
		this.empresaRepository.delete(entity);

	}

	@Override
	public void deleteRange(List<Empresa> entity) throws ServicioException {
		throw new UnsupportedOperationException();

	}

	@Override
	public void deleteById(String id) throws ServicioException {
		this.empresaRepository.delete(id);

	}

	@Override
	public boolean existsEmpresaByCif(String cif) throws ServicioException {
		return this.empresaRepository.existsEmpresaByCif(cif);
	}

	@Override
	public void validate(Empresa entity) throws ServicioException {
		validateAndCifOpcional(entity, true);
	}

	@Override
	public void validateAndCifOpcional(Empresa entity, boolean validarCif) throws ServicioException {
		this.srvValidacion.isNull("Empresa", entity);
		this.srvValidacion.isNull("Nombre", entity.getNombre());
		this.srvValidacion.isNull("CIF", entity.getCif());
		this.srvValidacion.isNull("Domicilio social", entity.getDomicilioSocial());
		if (validarCif) {
			validateCif(entity.getCif());
		}
	}

	@Override
	public Empresa saveOrUpdate(Empresa entity, boolean validate) throws ServicioException {
		if (validate) {
			validate(entity);
		}
		return this.save(entity);
	}

	@Override
	public void validateCif(String cif) throws ServicioException {
		if (existsEmpresaByCif(cif)) {
			throw new ServicioException(srvMensajes.getMensajeI18n("register.cif.exist"));
		}
	}

}
