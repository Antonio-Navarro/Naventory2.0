package com.antoniojnavarro.naventory.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antoniojnavarro.naventory.dao.commons.dto.paginationresult.PaginationResult;
import com.antoniojnavarro.naventory.dao.commons.enums.SortOrderEnum;
import com.antoniojnavarro.naventory.dao.repositories.ProveedorDao;
import com.antoniojnavarro.naventory.model.entities.Proveedor;
import com.antoniojnavarro.naventory.model.entities.Usuario;
import com.antoniojnavarro.naventory.model.filters.ProveedorSearchFilter;
import com.antoniojnavarro.naventory.services.api.ServicioProveedor;
import com.antoniojnavarro.naventory.services.api.ServicioUsuario;
import com.antoniojnavarro.naventory.services.commons.ServicioException;
import com.antoniojnavarro.naventory.services.commons.ServicioMensajesI18n;
import com.antoniojnavarro.naventory.services.commons.ServicioValidacion;

@Service
public class ServicioProveedorImpl implements ServicioProveedor {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ServicioValidacion srvValidacion;

	@Autowired
	private ServicioMensajesI18n srvMensajes;


	@Autowired
	private ProveedorDao proveedorDao;


	@Override
	public Proveedor findById(Integer id) throws ServicioException {
		return this.proveedorDao.findOne(id);
	}


	@Override
	public List<Proveedor> findBySearchFilter(ProveedorSearchFilter searchFilter) throws ServicioException {
		// TODO Auto-generated method stub
		return proveedorDao.findBySearchFilter(searchFilter);
	}


	@Override
	public List<Proveedor> findBySearchFilter(ProveedorSearchFilter searchFilter, String sortField,
			SortOrderEnum sortOrder) throws ServicioException {
		// TODO Auto-generated method stub
		return this.proveedorDao.findBySearchFilter(searchFilter, sortField, sortOrder);
	}


	@Override
	public PaginationResult<Proveedor> findBySearchFilterPagination(ProveedorSearchFilter searchFilter, int pageNumber,
			int pageSize, String sortField, SortOrderEnum sortOrder) throws ServicioException {
		return this.proveedorDao.findBySearchFilterPagination(searchFilter, pageNumber, pageSize, sortField, sortOrder);
	}


	@Override
	public PaginationResult<Proveedor> findBySearchFilterPagination(ProveedorSearchFilter searchFilter, int pageNumber,
			int pageSize) throws ServicioException {
		// TODO Auto-generated method stub
		return this.proveedorDao.findBySearchFilterPagination(searchFilter, pageNumber, pageSize);
	}


	@Override
	public List<Proveedor> findAll() throws ServicioException {
		// TODO Auto-generated method stub
		return (List<Proveedor>) this.proveedorDao.findAll();
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
		// TODO Auto-generated method stub
		return this.existsById(id);
	}


	@Override
	public Proveedor save(Proveedor entity) throws ServicioException {
		// TODO Auto-generated method stub
		return this.proveedorDao.save(entity);
	}

	@Autowired
	private ServicioUsuario srvUsuario;
	
	@Override
	public void validate(Proveedor entity) throws ServicioException {
		this.srvValidacion.isNull("Proveedor", entity);
		this.srvValidacion.isNull("Nombre", entity.getNombre());
		this.srvValidacion.isNull("Nombre Comercial", entity.getNombreCom());
		this.srvValidacion.isNull("Dirección", entity.getDireccion());
		this.srvValidacion.isNull("Ciudad", entity.getCiudad());
		this.srvValidacion.isNull("Provincia", entity.getProvincia());
		this.srvValidacion.isNull("País", entity.getPais());
		this.srvValidacion.isNull("Código Postal",entity.getCp());
		this.srvValidacion.isNull("Télefono 1", entity.getTel1());
		this.srvValidacion.isNull("Email", entity.getCorreo());
		if(!this.srvUsuario.existsUsuarioByEmail(entity.getUsuario().getEmail())) {
			throw new ServicioException(srvMensajes.getMensajeI18n("categorias.email.exist"));

		}
		
	}
	@Override
	public Proveedor saveOrUpdate(Proveedor entity, boolean validate) throws ServicioException {
		if(validate) {
			validate(entity);
		}
		return this.save(entity);
	}

	@Override
	public void delete(Proveedor entity) throws ServicioException {

		this.proveedorDao.delete(entity);
	}


	@Override
	public void deleteRange(List<Proveedor> entity) throws ServicioException {
		throw new UnsupportedOperationException();		
	}


	@Override
	public void deleteById(Integer id) throws ServicioException {
		this.proveedorDao.delete(id);
		
	}


	@Override
	public List<Proveedor> findProveedoresByUsuario(Usuario user) throws ServicioException {
		// TODO Auto-generated method stub
		return this.proveedorDao.findProveedoresByUsuario(user); 
	}
}
