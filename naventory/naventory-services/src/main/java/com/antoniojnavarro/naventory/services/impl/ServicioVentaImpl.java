package com.antoniojnavarro.naventory.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antoniojnavarro.naventory.dao.commons.dto.paginationresult.PaginationResult;
import com.antoniojnavarro.naventory.dao.commons.enums.SortOrderEnum;
import com.antoniojnavarro.naventory.dao.repositories.ProductoDao;
import com.antoniojnavarro.naventory.dao.repositories.VentaDao;
import com.antoniojnavarro.naventory.model.dtos.GraficaGenericDto;
import com.antoniojnavarro.naventory.model.entities.Usuario;
import com.antoniojnavarro.naventory.model.entities.Venta;
import com.antoniojnavarro.naventory.model.filters.VentaSearchFilter;
import com.antoniojnavarro.naventory.services.api.ServicioUsuario;
import com.antoniojnavarro.naventory.services.api.ServicioVenta;
import com.antoniojnavarro.naventory.services.commons.ServicioException;
import com.antoniojnavarro.naventory.services.commons.ServicioMensajesI18n;
import com.antoniojnavarro.naventory.services.commons.ServicioValidacion;

@Service
public class ServicioVentaImpl implements ServicioVenta {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ServicioValidacion srvValidacion;

	@Autowired
	private ServicioMensajesI18n srvMensajes;

	@Autowired
	private VentaDao ventaDao;
	@Autowired
	private ProductoDao productoDao;

	@Override
	public Venta findById(Integer id) throws ServicioException {
		return this.ventaDao.findOne(id);
	}

	@Override
	public List<Venta> findBySearchFilter(VentaSearchFilter searchFilter) throws ServicioException {
		return ventaDao.findBySearchFilter(searchFilter);
	}

	@Override
	public List<Venta> findBySearchFilter(VentaSearchFilter searchFilter, String sortField, SortOrderEnum sortOrder)
			throws ServicioException {
		return this.ventaDao.findBySearchFilter(searchFilter, sortField, sortOrder);
	}

	@Override
	public PaginationResult<Venta> findBySearchFilterPagination(VentaSearchFilter searchFilter, int pageNumber,
			int pageSize, String sortField, SortOrderEnum sortOrder) throws ServicioException {
		return this.ventaDao.findBySearchFilterPagination(searchFilter, pageNumber, pageSize, sortField, sortOrder);
	}

	@Override
	public PaginationResult<Venta> findBySearchFilterPagination(VentaSearchFilter searchFilter, int pageNumber,
			int pageSize) throws ServicioException {
		return this.ventaDao.findBySearchFilterPagination(searchFilter, pageNumber, pageSize);
	}

	@Override
	public List<Venta> findAll() throws ServicioException {
		return ventaDao.findAll();
	}

	@Override
	public boolean exists(Venta entity) throws ServicioException {
		if (entity == null) {
			throw new IllegalArgumentException("Entity no puede ser un nulo");
		}
		if (entity.getIdVenta() == null)
			return false;
		return existsById(entity.getIdVenta());
	}

	@Override
	public boolean existsById(Integer id) throws ServicioException {
		return ventaDao.exists(id);
	}

	@Override
	public Venta save(Venta entity) throws ServicioException {
		return this.ventaDao.save(entity);
	}

	@Autowired
	private ServicioUsuario srvUsuario;

	@Override
	public void validate(Venta entity) throws ServicioException {
		this.srvValidacion.isNull("Venta", entity);
		this.srvValidacion.isNull("Cliente", entity.getCliente().getNombre());
		this.srvValidacion.isNull("Producto", entity.getProducto());
		this.srvValidacion.isNull("Cantidad", entity.getCantidad());
		if (!this.srvUsuario.existsUsuarioByEmail(entity.getUsuario().getEmail())) {
			throw new ServicioException(srvMensajes.getMensajeI18n("categorias.email.exist"));
		}

		validarStock(entity);
	}

	@Override
	public void validarStock(Venta entity) throws ServicioException {
		if (entity.getCantidad() > entity.getProducto().getStock()) {
			throw new ServicioException(srvMensajes.getMensajeI18n("venta.noStock", entity.getProducto().getUnidad(),
					entity.getProducto().getNombre()));
		}
	}

	@Override
	public Venta saveOrUpdate(Venta entity, boolean validate) throws ServicioException {
		if (validate) {
			validate(entity);
		}
		entity.getProducto().setStock(entity.getProducto().getStock() - entity.getCantidad());
		productoDao.save(entity.getProducto());
		return this.save(entity);
	}

	@Override
	public void delete(Venta entity) throws ServicioException {

		this.ventaDao.delete(entity);
	}

	@Override
	public void deleteRange(List<Venta> entity) throws ServicioException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteById(Integer id) throws ServicioException {
		this.ventaDao.delete(id);

	}

	@Override
	public List<Venta> findVentasByUsuario(Usuario user) throws ServicioException {
		// TODO Auto-generated method stub
		return this.ventaDao.findVentasByUsuarioOrderByFechaDesc(user);
	}

	@Override
	public Venta calcularVenta(Venta entity) {
		entity.setNombreProd(entity.getProducto().getNombre());
		entity.setUnidad(entity.getProducto().getUnidad());
		entity.setPrecio(entity.getProducto().getPrecio());
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
	public List<GraficaGenericDto> findFormasPagoGrafica(String email) {
		return this.ventaDao.findFormasPagoGrafica(email);
	}

	@Override
	public List<GraficaGenericDto> getVentasMensualesGrafica(String email) {
		return this.ventaDao.getVentasMensualesGrafica(email);
	}

	@Override
	public List<GraficaGenericDto> getIngresosMensualesGrafica(String email) {
		return this.ventaDao.getIngresosMensualesGrafica(email);

	}

	@Override
	public Long countByUsuario(Usuario usuario) {
		return this.ventaDao.countByUsuario(usuario);
	}
}