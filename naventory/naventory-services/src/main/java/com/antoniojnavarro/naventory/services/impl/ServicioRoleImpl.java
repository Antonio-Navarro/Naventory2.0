package com.antoniojnavarro.naventory.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antoniojnavarro.naventory.dao.commons.dto.paginationresult.PaginationResult;
import com.antoniojnavarro.naventory.dao.commons.enums.SortOrderEnum;
import com.antoniojnavarro.naventory.dao.repositories.RoleDao;
import com.antoniojnavarro.naventory.model.entities.Role;
import com.antoniojnavarro.naventory.model.filters.RoleSearchFilter;
import com.antoniojnavarro.naventory.services.api.ServicioRole;
import com.antoniojnavarro.naventory.services.commons.ServicioException;
import com.antoniojnavarro.naventory.services.commons.ServicioValidacion;

@Service
public class ServicioRoleImpl implements ServicioRole {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ServicioValidacion srvValidacion;

	@Autowired
	private RoleDao roleDao;

	@Override
	public Role findById(Integer id) throws ServicioException {
		return this.roleDao.findOne(id);
	}

	@Override
	public List<Role> findBySearchFilter(RoleSearchFilter searchFilter) throws ServicioException {
		// TODO Auto-generated method stub
		return roleDao.findBySearchFilter(searchFilter);
	}

	@Override
	public List<Role> findBySearchFilter(RoleSearchFilter searchFilter, String sortField,
			SortOrderEnum sortOrder) throws ServicioException {
		// TODO Auto-generated method stub
		return this.roleDao.findBySearchFilter(searchFilter, sortField, sortOrder);
	}

	@Override
	public PaginationResult<Role> findBySearchFilterPagination(RoleSearchFilter searchFilter,
			int pageNumber, int pageSize, String sortField, SortOrderEnum sortOrder) throws ServicioException {
		return this.roleDao.findBySearchFilterPagination(searchFilter, pageNumber, pageSize, sortField, sortOrder);
	}

	@Override
	public PaginationResult<Role> findBySearchFilterPagination(RoleSearchFilter searchFilter,
			int pageNumber, int pageSize) throws ServicioException {
		// TODO Auto-generated method stub
		return this.roleDao.findBySearchFilterPagination(searchFilter, pageNumber, pageSize);
	}

	@Override
	public List<Role> findAll() throws ServicioException {
		// TODO Auto-generated method stub
		return (List<Role>) this.roleDao.findAll();
	}

	@Override
	public boolean exists(Role entity) throws ServicioException {
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
	public Role save(Role entity) throws ServicioException {
		// TODO Auto-generated method stub
		return this.roleDao.save(entity);
	}
	@Override
	public void validate(Role entity) throws ServicioException {
		this.srvValidacion.isNull("Role", entity);

	}

	@Override
	public Role saveOrUpdate(Role entity, boolean validate) throws ServicioException {
		if (validate) {
			validate(entity);
		}
		return this.save(entity);
	}

	@Override
	public void delete(Role entity) throws ServicioException {

		this.roleDao.delete(entity);
	}

	@Override
	public void deleteRange(List<Role> entity) throws ServicioException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteById(Integer id) throws ServicioException {
		this.roleDao.delete(id);

	}


	
}
