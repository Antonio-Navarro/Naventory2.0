package com.antoniojnavarro.naventory.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antoniojnavarro.naventory.dao.commons.dto.paginationresult.PaginationResult;
import com.antoniojnavarro.naventory.dao.commons.enums.SortOrderEnum;
import com.antoniojnavarro.naventory.dao.repositories.CompraDao;
import com.antoniojnavarro.naventory.dao.repositories.ProductoDao;
import com.antoniojnavarro.naventory.model.entities.Compra;
import com.antoniojnavarro.naventory.model.entities.Usuario;
import com.antoniojnavarro.naventory.model.filters.CompraSearchFilter;
import com.antoniojnavarro.naventory.services.api.ServicioCompra;
import com.antoniojnavarro.naventory.services.api.ServicioProducto;
import com.antoniojnavarro.naventory.services.api.ServicioUsuario;
import com.antoniojnavarro.naventory.services.commons.ServicioException;
import com.antoniojnavarro.naventory.services.commons.ServicioMensajesI18n;
import com.antoniojnavarro.naventory.services.commons.ServicioValidacion;

@Service
public class ServicioCompraImpl implements ServicioCompra {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ServicioValidacion srvValidacion;

	@Autowired
	private ServicioMensajesI18n srvMensajes;
	@Autowired
	private ServicioProducto srvProducto;
	@Autowired
	private CompraDao compraDao;

	@Autowired
	private ProductoDao productoDao;

	@Override
	public Compra findById(Integer id) throws ServicioException {
		return this.compraDao.findOne(id);
	}

	@Override
	public List<Compra> findBySearchFilter(CompraSearchFilter searchFilter) throws ServicioException {
		// TODO Auto-generated method stub
		return compraDao.findBySearchFilter(searchFilter);
	}

	@Override
	public List<Compra> findBySearchFilter(CompraSearchFilter searchFilter, String sortField, SortOrderEnum sortOrder)
			throws ServicioException {
		// TODO Auto-generated method stub
		return this.compraDao.findBySearchFilter(searchFilter, sortField, sortOrder);
	}

	@Override
	public PaginationResult<Compra> findBySearchFilterPagination(CompraSearchFilter searchFilter, int pageNumber,
			int pageSize, String sortField, SortOrderEnum sortOrder) throws ServicioException {
		return this.compraDao.findBySearchFilterPagination(searchFilter, pageNumber, pageSize, sortField, sortOrder);
	}

	@Override
	public PaginationResult<Compra> findBySearchFilterPagination(CompraSearchFilter searchFilter, int pageNumber,
			int pageSize) throws ServicioException {
		// TODO Auto-generated method stub
		return this.compraDao.findBySearchFilterPagination(searchFilter, pageNumber, pageSize);
	}

	@Override
	public List<Compra> findAll() throws ServicioException {
		// TODO Auto-generated method stub
		return (List<Compra>) this.compraDao.findAll();
	}

	@Override
	public boolean exists(Compra entity) throws ServicioException {
		if (entity == null) {
			throw new IllegalArgumentException("Entity no puede ser un nulo");
		}
		if (entity.getIdCompra() == null)
			return false;
		return existsById(entity.getIdCompra());
	}

	@Override
	public boolean existsById(Integer id) throws ServicioException {
		// TODO Auto-generated method stub
		return compraDao.exists(id);
	}

	@Override
	public Compra save(Compra entity) throws ServicioException {
		// TODO Auto-generated method stub
		return this.compraDao.save(entity);
	}

	@Autowired
	private ServicioUsuario srvUsuario;

	@Override
	public void validate(Compra entity) throws ServicioException {
		this.srvValidacion.isNull("Compra", entity);
		this.srvValidacion.isNull("Proveedor", entity.getProveedor());
		this.srvValidacion.isNull("Producto", entity.getProducto());
		this.srvValidacion.isNull("Factura", entity.getFactura());
		this.srvValidacion.isNull("Cantidad", entity.getCantidad());
		CompraSearchFilter searchFilter = new CompraSearchFilter();
		searchFilter = searchFilter.factura(entity.getFactura());
		searchFilter = searchFilter.usuario(entity.getUsuario().getEmail());
		List<Compra> compras = this.compraDao.findBySearchFilter(searchFilter);
		if (compras.size() > 0) {
			throw new ServicioException(srvMensajes.getMensajeI18n("compra.factura.existe"));
		}
		this.srvProducto.validate(entity.getProducto());
		if (!this.srvUsuario.existsUsuarioByEmail(entity.getUsuario().getEmail())) {
			throw new ServicioException(srvMensajes.getMensajeI18n("categorias.email.exist"));
		}
	}

	@Override
	public Compra saveOrUpdate(Compra entity, boolean validate) throws ServicioException {
		if (validate) {
			validate(entity);
		}
		return this.save(entity);
	}

	@Override
	public Compra saveNewOrUpdate(Compra entity, boolean validate, boolean nuevoProducto) throws ServicioException {
		if (validate) {
			validate(entity);
		}
		if (!nuevoProducto) {
			entity.getProducto().setStock(entity.getProducto().getStock() + entity.getCantidad());
		}
		productoDao.save(entity.getProducto());
		return this.save(entity);
	}

	@Override
	public void delete(Compra entity) throws ServicioException {

		this.compraDao.delete(entity);
	}

	@Override
	public void deleteRange(List<Compra> entity) throws ServicioException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteById(Integer id) throws ServicioException {
		this.compraDao.delete(id);

	}

	@Override
	public List<Compra> findComprasByUsuario(Usuario user) throws ServicioException {
		// TODO Auto-generated method stub
		return this.compraDao.findComprasByUsuarioOrderByFechaDesc(user);
	}

	@Override
	public Compra calcularCompra(Compra entity) {
		entity.setNombreProd(entity.getProducto().getNombre());
		entity.setUnidad(entity.getProducto().getUnidad());
		entity.setPrecio(entity.getProducto().getCoste());
		entity.setTotal(entity.getPrecio() * entity.getCantidad());
		if (entity.getDescuento() != null && entity.getDescuento() > 0) {
			entity.setTotal(entity.getTotal() - (entity.getTotal() * (entity.getDescuento() / 100)));
		} else {
			entity.setDescuento(0f);
		}
		if (entity.getIva() != null && entity.getIva() > 0) {
			entity.setTotal(entity.getTotal() + (entity.getTotal() * (entity.getIva() / 100)));
		} else {
			entity.setTotal(entity.getTotal() + (entity.getTotal() * (0.21f)));
			entity.setIva(21f);
		}

		return entity;
	}

	@Override
	public List<Object> getGastosMensualesGrafica(String email) {
		return this.compraDao.getGastosMensualesGrafica(email);

	}

	@Override
	public Long countByUsuario(Usuario usuario) {
		return this.compraDao.countByUsuario(usuario);

	}

}