package com.antoniojnavarro.naventory.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antoniojnavarro.naventory.model.entities.Empresa;
import com.antoniojnavarro.naventory.model.entities.Proveedor;
import com.antoniojnavarro.naventory.model.filters.ProveedorSearchFilter;
import com.antoniojnavarro.naventory.repository.commons.dto.paginationresult.PaginationResult;
import com.antoniojnavarro.naventory.repository.commons.enums.SortOrderEnum;
import com.antoniojnavarro.naventory.repository.repositories.ProveedorRepository;
import com.antoniojnavarro.naventory.services.api.ServicioEmpresa;
import com.antoniojnavarro.naventory.services.api.ServicioProveedor;
import com.antoniojnavarro.naventory.services.commons.ServicioException;
import com.antoniojnavarro.naventory.services.commons.ServicioMensajesI18n;
import com.antoniojnavarro.naventory.services.commons.ServicioValidacion;

@Service
public class ServicioProveedorImpl implements ServicioProveedor {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ServicioValidacion srvValidacion;
	@Autowired
	private ServicioEmpresa srvEmpresa;
	@Autowired
	private ServicioMensajesI18n srvMensajes;

	@Autowired
	private ProveedorRepository proveedorRepository;

	@Override
	public Proveedor findById(Integer id) throws ServicioException {
		return this.proveedorRepository.findOne(id);
	}

	@Override
	public List<Proveedor> findBySearchFilter(ProveedorSearchFilter searchFilter) throws ServicioException {
		return proveedorRepository.findBySearchFilter(searchFilter);
	}

	@Override
	public List<Proveedor> findBySearchFilter(ProveedorSearchFilter searchFilter, String sortField,
			SortOrderEnum sortOrder) throws ServicioException {
		return this.proveedorRepository.findBySearchFilter(searchFilter, sortField, sortOrder);
	}

	@Override
	public PaginationResult<Proveedor> findBySearchFilterPagination(ProveedorSearchFilter searchFilter, int pageNumber,
			int pageSize, String sortField, SortOrderEnum sortOrder) throws ServicioException {
		return this.proveedorRepository.findBySearchFilterPagination(searchFilter, pageNumber, pageSize, sortField, sortOrder);
	}

	@Override
	public PaginationResult<Proveedor> findBySearchFilterPagination(ProveedorSearchFilter searchFilter, int pageNumber,
			int pageSize) throws ServicioException {
		return this.proveedorRepository.findBySearchFilterPagination(searchFilter, pageNumber, pageSize);
	}

	@Override
	public List<Proveedor> findAll() throws ServicioException {
		return (List<Proveedor>) this.proveedorRepository.findAll();
	}

	@Override
	public boolean exists(Proveedor entity) throws ServicioException {
		if (entity == null) {
			throw new IllegalArgumentException("Entity no puede ser un nulo");
		}
		if (entity.getIdProv() == null)
			return false;
		return existsById(entity.getIdProv());
	}

	@Override
	public boolean existsById(Integer id) throws ServicioException {
		return this.existsById(id);
	}

	@Override
	public Proveedor save(Proveedor entity) throws ServicioException {
		return this.proveedorRepository.save(entity);
	}

	@Override
	public void validate(Proveedor entity) throws ServicioException {
		this.srvValidacion.isNull("Proveedor", entity);
		this.srvValidacion.isNull("Nombre", entity.getNombre());
		this.srvValidacion.isNull("Nombre Comercial", entity.getNombreCom());
		this.srvValidacion.isNull("Dirección", entity.getDireccion());
		this.srvValidacion.isNull("Ciudad", entity.getCiudad());
		this.srvValidacion.isNull("Provincia", entity.getProvincia());
		this.srvValidacion.isNull("País", entity.getPais());
		this.srvValidacion.isNull("Código Postal", entity.getCp());
		this.srvValidacion.isNull("Télefono 1", entity.getTel1());
		this.srvValidacion.isNull("Email", entity.getCorreo());
		if (!this.srvEmpresa.existsEmpresaByCif(entity.getEmpresa().getCif())) {
			throw new ServicioException(srvMensajes.getMensajeI18n("cif.noexist"));

		}

	}

	@Override
	public Proveedor saveOrUpdate(Proveedor entity, boolean validate) throws ServicioException {
		if (validate) {
			validate(entity);
		}
		return this.save(entity);
	}

	@Override
	public void delete(Proveedor entity) throws ServicioException {

		this.proveedorRepository.delete(entity);
	}

	@Override
	public void deleteRange(List<Proveedor> entity) throws ServicioException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteById(Integer id) throws ServicioException {
		this.proveedorRepository.delete(id);

	}

	@Override
	public List<Proveedor> findProveedoresByEmpresa(Empresa empresa) throws ServicioException {
		return this.proveedorRepository.findProveedoresByEmpresa(empresa);
	}
}
