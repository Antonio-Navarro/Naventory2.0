package com.antoniojnavarro.naventory.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antoniojnavarro.naventory.model.entities.Empresa;
import com.antoniojnavarro.naventory.model.entities.Usuario;
import com.antoniojnavarro.naventory.model.filters.UsuarioSearchFilter;
import com.antoniojnavarro.naventory.repository.commons.dto.paginationresult.PaginationResult;
import com.antoniojnavarro.naventory.repository.commons.enums.SortOrderEnum;
import com.antoniojnavarro.naventory.repository.repositories.UsuarioRepository;
import com.antoniojnavarro.naventory.services.api.ServicioUsuario;
import com.antoniojnavarro.naventory.services.commons.ServicioException;
import com.antoniojnavarro.naventory.services.commons.ServicioMensajesI18n;
import com.antoniojnavarro.naventory.services.commons.ServicioValidacion;

@Service
public class ServicioUsuarioImpl implements ServicioUsuario {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ServicioValidacion srvValidacion;

	@Autowired
	private ServicioMensajesI18n srvMensajes;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public Usuario findById(String id) throws ServicioException {
		return this.usuarioRepository.findOne(id);
	}

	@Override
	public List<Usuario> findBySearchFilter(UsuarioSearchFilter searchFilter) throws ServicioException {
		return this.usuarioRepository.findBySearchFilter(searchFilter);
	}

	@Override
	public List<Usuario> findBySearchFilter(UsuarioSearchFilter searchFilter, String sortField, SortOrderEnum sortOrder)
			throws ServicioException {
		return this.usuarioRepository.findBySearchFilter(searchFilter, sortField, sortOrder);

	}

	@Override
	public PaginationResult<Usuario> findBySearchFilterPagination(UsuarioSearchFilter searchFilter, int pageNumber,
			int pageSize, String sortField, SortOrderEnum sortOrder) throws ServicioException {
		return this.usuarioRepository.findBySearchFilterPagination(searchFilter, pageNumber, pageSize, sortField, sortOrder);
	}

	@Override
	public PaginationResult<Usuario> findBySearchFilterPagination(UsuarioSearchFilter searchFilter, int pageNumber,
			int pageSize) throws ServicioException {
		return this.usuarioRepository.findBySearchFilterPagination(searchFilter, pageNumber, pageSize);
	}

	@Override
	public List<Usuario> findAll() throws ServicioException {
		return (List<Usuario>) this.usuarioRepository.findAll();
	}

	@Override
	public boolean exists(Usuario entity) throws ServicioException {
		if (entity == null) {
			throw new IllegalArgumentException("Entity no puede ser un nulo");
		}
		if (entity.getEmail() == null)
			return false;
		return existsById(entity.getEmail());
	}

	@Override
	public boolean existsById(String id) throws ServicioException {
		return this.usuarioRepository.exists(id);

	}

	@Override
	public Usuario save(Usuario entity) throws ServicioException {
		return this.usuarioRepository.save(entity);
	}

	@Override
	public void delete(Usuario entity) throws ServicioException {
		this.usuarioRepository.delete(entity);

	}

	@Override
	public void deleteRange(List<Usuario> entity) throws ServicioException {
		throw new UnsupportedOperationException();

	}

	@Override
	public void deleteById(String id) throws ServicioException {
		this.usuarioRepository.delete(id);

	}

	@Override
	public Usuario findUsuarioByEmail(String email) throws ServicioException {
		return this.usuarioRepository.findUsuarioByEmail(email);
	}

	@Override
	public boolean existsUsuarioByEmail(String email) throws ServicioException {
		return this.usuarioRepository.existsUsuarioByEmail(email);
	}

	@Override
	public void validate(Usuario entity) throws ServicioException {
		validateAndEmailOpcional(entity, true);
	}

	@Override
	public void validateAndEmailOpcional(Usuario entity, boolean validarEmail) throws ServicioException {
		this.srvValidacion.isNull("Usuario", entity);
		this.srvValidacion.isNull("Nombre", entity.getNombre());
		this.srvValidacion.isNull("Apellidos", entity.getApellido());
		this.srvValidacion.isNull("Email", entity.getEmail());
		this.srvValidacion.isNull("Contraseña", entity.getPassword());
		this.srvValidacion.isNull("Empresa", entity.getEmpresa());
		if (validarEmail) {
			validateEmail(entity.getEmail());
		}
	}

	@Override
	public Usuario saveOrUpdate(Usuario entity, boolean validate) throws ServicioException {
		if (validate) {
			validate(entity);
		}
		return this.save(entity);
	}

	@Override
	public Usuario findUsuarioByEmailAndPassword(String email, String pass) throws ServicioException {

		return this.usuarioRepository.findUsuarioByEmailAndPassword(email, pass);
	}

	@Override
	public Usuario findUsuarioByEmailAndActivo(String email, String activo) throws ServicioException {

		return this.usuarioRepository.findUsuarioByEmailAndActivo(email, activo);
	}

	@Override
	public List<Usuario> findUsuarioByEmpresa(Empresa empresa) throws ServicioException {
		return (List<Usuario>) this.usuarioRepository.findUsuarioByEmpresa(empresa);
	}

	public Object[] findUsersGrafica() {
		return this.usuarioRepository.findUsersGrafica();
	}

	@Override
	public void validateEmail(String email) throws ServicioException {
		if (existsUsuarioByEmail(email)) {
			throw new ServicioException(srvMensajes.getMensajeI18n("register.email.exist"));
		}
	}
}
