package com.antoniojnavarro.naventory.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antoniojnavarro.naventory.dao.commons.dto.paginationresult.PaginationResult;
import com.antoniojnavarro.naventory.dao.commons.enums.SortOrderEnum;
import com.antoniojnavarro.naventory.dao.repositories.ClienteDao;
import com.antoniojnavarro.naventory.model.dtos.GraficaGenericDto;
import com.antoniojnavarro.naventory.model.entities.Cliente;
import com.antoniojnavarro.naventory.model.entities.Usuario;
import com.antoniojnavarro.naventory.model.filters.ClienteSearchFilter;
import com.antoniojnavarro.naventory.services.api.ServicioCliente;
import com.antoniojnavarro.naventory.services.api.ServicioUsuario;
import com.antoniojnavarro.naventory.services.commons.ServicioException;
import com.antoniojnavarro.naventory.services.commons.ServicioMensajesI18n;
import com.antoniojnavarro.naventory.services.commons.ServicioValidacion;

@Service
public class ServicioClienteImpl implements ServicioCliente {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ServicioValidacion srvValidacion;

	@Autowired
	private ServicioMensajesI18n srvMensajes;

	@Autowired
	private ClienteDao clienteDao;

	@Override
	public Cliente findById(Integer id) throws ServicioException {
		return this.clienteDao.findOne(id);
	}

	@Override
	public List<Cliente> findBySearchFilter(ClienteSearchFilter searchFilter) throws ServicioException {
		return clienteDao.findBySearchFilter(searchFilter);
	}

	@Override
	public List<Cliente> findBySearchFilter(ClienteSearchFilter searchFilter, String sortField, SortOrderEnum sortOrder)
			throws ServicioException {
		return this.clienteDao.findBySearchFilter(searchFilter, sortField, sortOrder);
	}

	@Override
	public PaginationResult<Cliente> findBySearchFilterPagination(ClienteSearchFilter searchFilter, int pageNumber,
			int pageSize, String sortField, SortOrderEnum sortOrder) throws ServicioException {
		return this.clienteDao.findBySearchFilterPagination(searchFilter, pageNumber, pageSize, sortField, sortOrder);
	}

	@Override
	public PaginationResult<Cliente> findBySearchFilterPagination(ClienteSearchFilter searchFilter, int pageNumber,
			int pageSize) throws ServicioException {
		return this.clienteDao.findBySearchFilterPagination(searchFilter, pageNumber, pageSize);
	}

	@Override
	public List<Cliente> findAll() throws ServicioException {
		// TODO Auto-generated method stub
		return (List<Cliente>) this.clienteDao.findAll();
	}

	@Override
	public boolean exists(Cliente entity) throws ServicioException {
		if (entity == null) {
			throw new IllegalArgumentException("Entity no puede ser un nulo");
		}
		if (entity.getIdCliente() == null)
			return false;
		return existsById(entity.getIdCliente());
	}

	@Override
	public boolean existsById(Integer id) throws ServicioException {
		return this.existsById(id);
	}

	@Override
	public Cliente save(Cliente entity) throws ServicioException {
		return this.clienteDao.save(entity);
	}

	@Autowired
	private ServicioUsuario srvUsuario;

	@Override
	public void validate(Cliente entity) throws ServicioException {
		this.srvValidacion.isNull("Cliente", entity);
		this.srvValidacion.isNull("Nombre", entity.getNombre());
		this.srvValidacion.isNull("Nombre Comercial", entity.getNombreCom());
		this.srvValidacion.isNull("Dirección", entity.getDireccion());
		this.srvValidacion.isNull("Ciudad", entity.getCiudad());
		this.srvValidacion.isNull("Provincia", entity.getProvincia());
		this.srvValidacion.isNull("País", entity.getPais());
		this.srvValidacion.isNull("Código Postal", entity.getCp());
		this.srvValidacion.isNull("Télefono 1", entity.getTel1());
		this.srvValidacion.isNull("Email", entity.getCorreo());

		if (!this.srvUsuario.existsUsuarioByEmail(entity.getUsuario().getEmail())) {
			throw new ServicioException(srvMensajes.getMensajeI18n("categorias.email.exist"));

		}

	}

	@Override
	public Cliente saveOrUpdate(Cliente entity, boolean validate) throws ServicioException {
		if (validate) {
			validate(entity);
		}
		return this.save(entity);
	}

	@Override
	public void delete(Cliente entity) throws ServicioException {

		this.clienteDao.delete(entity);
	}

	@Override
	public void deleteRange(List<Cliente> entity) throws ServicioException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteById(Integer id) throws ServicioException {
		this.clienteDao.delete(id);

	}

	@Override
	public List<Cliente> findClientesByUsuario(Usuario user) throws ServicioException {
		return this.clienteDao.findClientesByUsuario(user);
	}

	@Override
	public List<GraficaGenericDto> findClientesGrafica(String email) {
		return this.clienteDao.findClientesGrafica(email);
	}

	@Override
	public Long countByUsuario(Usuario usuario) {
		return this.clienteDao.countByUsuario(usuario);
	}

}
