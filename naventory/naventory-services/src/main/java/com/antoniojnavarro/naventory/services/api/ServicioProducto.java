package com.antoniojnavarro.naventory.services.api;

import java.util.List;

import com.antoniojnavarro.naventory.model.entities.Empresa;
import com.antoniojnavarro.naventory.model.entities.Producto;
import com.antoniojnavarro.naventory.model.filters.ProductoSearchFilter;
import com.antoniojnavarro.naventory.services.commons.ServicioCrud;
import com.antoniojnavarro.naventory.services.commons.ServicioException;

public interface ServicioProducto extends ServicioCrud<Producto, ProductoSearchFilter, String> {

	List<Producto> findProductosByEmpresa(Empresa empresa) throws ServicioException;

	void validateSKU(String sku) throws ServicioException;

	Float getTotalInventario(Empresa empresa) throws ServicioException;

	Long countByEmpresa(Empresa empresa);

	List<Producto> findProductosStockBajo(String empresa);
}
