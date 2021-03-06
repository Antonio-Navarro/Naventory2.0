package com.antoniojnavarro.naventory.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.antoniojnavarro.naventory.model.dtos.GraficaGenericDto;
import com.antoniojnavarro.naventory.model.entities.Empresa;
import com.antoniojnavarro.naventory.model.entities.Venta;
import com.antoniojnavarro.naventory.model.filters.VentaSearchFilter;
import com.antoniojnavarro.naventory.repository.commons.dto.paginationresult.PaginationResult;
import com.antoniojnavarro.naventory.repository.commons.enums.SortOrderEnum;
import com.antoniojnavarro.naventory.repository.repositories.ProductoRepository;
import com.antoniojnavarro.naventory.repository.repositories.VentaRepository;
import com.antoniojnavarro.naventory.services.api.ServicioEmpresa;
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
	private ServicioEmpresa srvEmpresa;

	@Autowired
	private VentaRepository ventaRepository;

	@Autowired
	private ProductoRepository productoRepository;

	@Override
	public Venta findById(Integer id) throws ServicioException {
		return this.ventaRepository.findOne(id);
	}

	@Override
	public List<Venta> findBySearchFilter(VentaSearchFilter searchFilter) throws ServicioException {
		return ventaRepository.findBySearchFilter(searchFilter);
	}

	@Override
	public List<Venta> findBySearchFilter(VentaSearchFilter searchFilter, String sortField, SortOrderEnum sortOrder)
			throws ServicioException {
		return this.ventaRepository.findBySearchFilter(searchFilter, sortField, sortOrder);
	}

	@Override
	public PaginationResult<Venta> findBySearchFilterPagination(VentaSearchFilter searchFilter, int pageNumber,
			int pageSize, String sortField, SortOrderEnum sortOrder) throws ServicioException {
		return this.ventaRepository.findBySearchFilterPagination(searchFilter, pageNumber, pageSize, sortField, sortOrder);
	}

	@Override
	public PaginationResult<Venta> findBySearchFilterPagination(VentaSearchFilter searchFilter, int pageNumber,
			int pageSize) throws ServicioException {
		return this.ventaRepository.findBySearchFilterPagination(searchFilter, pageNumber, pageSize);
	}

	@Override
	public List<Venta> findAll() throws ServicioException {
		return ventaRepository.findAll();
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
		return ventaRepository.exists(id);
	}

	@Override
	public Venta save(Venta entity) throws ServicioException {
		return this.ventaRepository.save(entity);
	}

	@Override
	public void validate(Venta entity) throws ServicioException {
		this.srvValidacion.isNull("Venta", entity);
		this.srvValidacion.isNull("Cliente", entity.getCliente().getNombre());
		this.srvValidacion.isNull("Producto", entity.getProducto());
		this.srvValidacion.isNull("Cantidad", entity.getCantidad());
		if (!this.srvEmpresa.existsEmpresaByCif(entity.getEmpresa().getCif())) {
			throw new ServicioException(srvMensajes.getMensajeI18n("cif.noexist"));

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
		productoRepository.save(entity.getProducto());
		return this.save(entity);
	}

	@Override
	public void delete(Venta entity) throws ServicioException {

		this.ventaRepository.delete(entity);
	}

	@Override
	public void deleteRange(List<Venta> entity) throws ServicioException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteById(Integer id) throws ServicioException {
		this.ventaRepository.delete(id);

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
	public List<GraficaGenericDto> findFormasPagoGrafica(Empresa empresa) {
		return this.ventaRepository.findFormasPagoGrafica(empresa);
	}

	@Override
	public List<GraficaGenericDto> getVentasMensualesGrafica(Empresa empresa) {
		return this.ventaRepository.getVentasMensualesGrafica(empresa);
	}

	@Override
	public List<GraficaGenericDto> getIngresosMensualesGrafica(Empresa empresa, Integer numMeses) {
		return this.ventaRepository.getIngresosMensualesGrafica(empresa, new PageRequest(0, numMeses));

	}

	@Override
	public Long countByEmpresa(Empresa empresa) {
		return this.ventaRepository.countByEmpresa(empresa);
	}
}