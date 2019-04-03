package com.antoniojnavarro.naventory.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antoniojnavarro.naventory.dao.commons.dto.paginationresult.PaginationResult;
import com.antoniojnavarro.naventory.dao.commons.enums.SortOrderEnum;
import com.antoniojnavarro.naventory.dao.repositories.EmpresaInvitacionDao;
import com.antoniojnavarro.naventory.model.entities.EmpresaInvitacion;
import com.antoniojnavarro.naventory.model.filters.EmpresaInvitacionSearchFilter;
import com.antoniojnavarro.naventory.services.api.ServicioEmpresaInvitacion;
import com.antoniojnavarro.naventory.services.commons.ServicioException;
import com.antoniojnavarro.naventory.services.commons.ServicioValidacion;

@Service
public class ServicioEmpresaInvitacionImpl implements ServicioEmpresaInvitacion {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ServicioValidacion srvValidacion;

	@Autowired
	private EmpresaInvitacionDao empresaInvitacionDao;

	@Override
	public EmpresaInvitacion findById(Integer id) throws ServicioException {
		return this.empresaInvitacionDao.findOne(id);
	}

	@Override
	public List<EmpresaInvitacion> findBySearchFilter(EmpresaInvitacionSearchFilter searchFilter)
			throws ServicioException {
		return this.empresaInvitacionDao.findBySearchFilter(searchFilter);
	}

	@Override
	public List<EmpresaInvitacion> findBySearchFilter(EmpresaInvitacionSearchFilter searchFilter, String sortField,
			SortOrderEnum sortOrder) throws ServicioException {
		return this.empresaInvitacionDao.findBySearchFilter(searchFilter, sortField, sortOrder);

	}

	@Override
	public PaginationResult<EmpresaInvitacion> findBySearchFilterPagination(EmpresaInvitacionSearchFilter searchFilter,
			int pageNumber, int pageSize, String sortField, SortOrderEnum sortOrder) throws ServicioException {
		return this.empresaInvitacionDao.findBySearchFilterPagination(searchFilter, pageNumber, pageSize, sortField,
				sortOrder);
	}

	@Override
	public PaginationResult<EmpresaInvitacion> findBySearchFilterPagination(EmpresaInvitacionSearchFilter searchFilter,
			int pageNumber, int pageSize) throws ServicioException {
		return this.empresaInvitacionDao.findBySearchFilterPagination(searchFilter, pageNumber, pageSize);
	}

	@Override
	public List<EmpresaInvitacion> findAll() throws ServicioException {
		return (List<EmpresaInvitacion>) this.empresaInvitacionDao.findAll();
	}

	@Override
	public boolean exists(EmpresaInvitacion entity) throws ServicioException {
		if (entity == null) {
			throw new IllegalArgumentException("Entity no puede ser un nulo");
		}
		if (entity.getIdInvitacion() == null)
			return false;
		return existsById(entity.getIdInvitacion());
	}

	@Override
	public boolean existsById(Integer id) throws ServicioException {
		return this.empresaInvitacionDao.exists(id);

	}

	@Override
	public EmpresaInvitacion save(EmpresaInvitacion entity) throws ServicioException {
		return this.empresaInvitacionDao.save(entity);
	}

	@Override
	public void delete(EmpresaInvitacion entity) throws ServicioException {
		this.empresaInvitacionDao.delete(entity);

	}

	@Override
	public void deleteRange(List<EmpresaInvitacion> entity) throws ServicioException {
		throw new UnsupportedOperationException();

	}

	@Override
	public void deleteById(Integer id) throws ServicioException {
		this.empresaInvitacionDao.delete(id);

	}

	@Override
	public void validate(EmpresaInvitacion entity) throws ServicioException {
		this.srvValidacion.isNull("EmpresaInvitacion", entity);
		this.srvValidacion.isNull("Cif", entity.getCif());
		this.srvValidacion.isNull("EMAIL", entity.getEmail());
		this.srvValidacion.isNull("Token", entity.getToken());

	}

	@Override
	public EmpresaInvitacion saveOrUpdate(EmpresaInvitacion entity, boolean validate) throws ServicioException {
		if (validate) {
			validate(entity);
		}
		return this.save(entity);
	}

	@Override
	public EmpresaInvitacion findEmpresaInvitacionByCifAndEmailAndToken(String cif, String email, String token) {
		return empresaInvitacionDao.findEmpresaInvitacionByCifAndEmailAndToken(cif, email, token);

	}

	@Override
	@Transactional
	public void deleteEmpresaInvitacionByCifAndEmail(String cif, String email) {
		empresaInvitacionDao.deleteEmpresaInvitacionByCifAndEmail(cif, email);

	}

}
