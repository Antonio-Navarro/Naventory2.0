package com.antoniojnavarro.naventory.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antoniojnavarro.naventory.model.entities.Empresa;
import com.antoniojnavarro.naventory.model.entities.Producto;
import com.antoniojnavarro.naventory.model.filters.ProductoSearchFilter;
import com.antoniojnavarro.naventory.repository.commons.dto.paginationresult.PaginationResult;
import com.antoniojnavarro.naventory.repository.commons.enums.SortOrderEnum;
import com.antoniojnavarro.naventory.repository.repositories.ProductoRepository;
import com.antoniojnavarro.naventory.services.api.ServicioEmpresa;
import com.antoniojnavarro.naventory.services.api.ServicioProducto;
import com.antoniojnavarro.naventory.services.commons.ServicioException;
import com.antoniojnavarro.naventory.services.commons.ServicioMensajesI18n;
import com.antoniojnavarro.naventory.services.commons.ServicioValidacion;

@Service
public class ServicioProductoImpl implements ServicioProducto {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ServicioValidacion srvValidacion;
	@Autowired
	private ServicioEmpresa srvEmpresa;

	@Autowired
	private ServicioMensajesI18n srvMensajes;

	@Autowired
	private ProductoRepository productoRepository;

	@Override
	public Producto findById(String id) throws ServicioException {
		return this.productoRepository.findOne(id);
	}

	@Override
	public List<Producto> findBySearchFilter(ProductoSearchFilter searchFilter) throws ServicioException {
		return productoRepository.findBySearchFilter(searchFilter);
	}

	@Override
	public List<Producto> findBySearchFilter(ProductoSearchFilter searchFilter, String sortField,
			SortOrderEnum sortOrder) throws ServicioException {
		return this.productoRepository.findBySearchFilter(searchFilter, sortField, sortOrder);
	}

	@Override
	public PaginationResult<Producto> findBySearchFilterPagination(ProductoSearchFilter searchFilter, int pageNumber,
			int pageSize, String sortField, SortOrderEnum sortOrder) throws ServicioException {
		return this.productoRepository.findBySearchFilterPagination(searchFilter, pageNumber, pageSize, sortField, sortOrder);
	}

	@Override
	public PaginationResult<Producto> findBySearchFilterPagination(ProductoSearchFilter searchFilter, int pageNumber,
			int pageSize) throws ServicioException {
		return this.productoRepository.findBySearchFilterPagination(searchFilter, pageNumber, pageSize);
	}

	@Override
	public List<Producto> findAll() throws ServicioException {
		return (List<Producto>) this.productoRepository.findAll();
	}

	@Override
	public boolean exists(Producto entity) throws ServicioException {
		if (entity == null) {
			throw new IllegalArgumentException("Entity no puede ser un nulo");
		}
		if (entity.getSku() == null)
			return false;
		return existsById(entity.getSku());
	}

	@Override
	public boolean existsById(String id) throws ServicioException {
		// TODO Auto-generated method stub
		return productoRepository.exists(id);
	}

	@Override
	public Producto save(Producto entity) throws ServicioException {
		// TODO Auto-generated method stub
		return this.productoRepository.save(entity);
	}

	@Override
	public void validate(Producto entity) throws ServicioException {
		this.srvValidacion.isNull("Producto", entity);
		this.srvValidacion.isNull("Categoría", entity.getCategoria());
		this.srvValidacion.isNull("Proveedor", entity.getProveedor());
		this.srvValidacion.isNull("SKU", entity.getSku());
		this.srvValidacion.isNull("Categoría", entity.getCategoria().getIdCat());
		this.srvValidacion.isNull("Proveedor", entity.getProveedor().getIdProv());
		this.srvValidacion.isNull("Nombre del producto", entity.getNombre());
		this.srvValidacion.isNull("Descripción", entity.getDescripcion());
		this.srvValidacion.isNull("Coste de compra", entity.getCoste());
		this.srvValidacion.isNull("Precio de venta", entity.getPrecio());
		this.srvValidacion.isNull("Stock", entity.getStock());
		this.srvValidacion.isNull("Stock mínimo", entity.getStockMin());
		if (!this.srvEmpresa.existsEmpresaByCif(entity.getEmpresa().getCif())) {
			throw new ServicioException(srvMensajes.getMensajeI18n("cif.noexist"));

		}

	}

	@Override
	public Producto saveOrUpdate(Producto entity, boolean validate) throws ServicioException {
		if (validate) {
			validate(entity);
		}
		return this.save(entity);
	}

	@Override
	public void delete(Producto entity) throws ServicioException {

		this.productoRepository.delete(entity);
	}

	@Override
	public void deleteRange(List<Producto> entity) throws ServicioException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteById(String id) throws ServicioException {
		this.productoRepository.delete(id);

	}

	@Override
	public void validateSKU(String sku) throws ServicioException {
		if (existsById(sku)) {
			throw new ServicioException(srvMensajes.getMensajeI18n("productos.exist"));
		}
	}

	@Override
	public List<Producto> findProductosByEmpresa(Empresa empresa) throws ServicioException {
		return this.productoRepository.findProductosByEmpresa(empresa);
	}

	@Override
	public Float getTotalInventario(Empresa empresa) throws ServicioException {
		return this.productoRepository.getTotalInventario(empresa);
	}

	@Override
	public Long countByEmpresa(Empresa empresa) {
		return this.productoRepository.countByEmpresa(empresa);
	}
}
