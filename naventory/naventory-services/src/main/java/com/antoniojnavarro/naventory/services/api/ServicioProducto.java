package com.antoniojnavarro.naventory.services.api;

import java.util.List;

import com.antoniojnavarro.naventory.model.entities.Producto;
import com.antoniojnavarro.naventory.model.entities.Usuario;
import com.antoniojnavarro.naventory.model.filters.ProductoSearchFilter;
import com.antoniojnavarro.naventory.services.commons.ServicioCrud;
import com.antoniojnavarro.naventory.services.commons.ServicioException;

public interface ServicioProducto extends ServicioCrud<Producto, ProductoSearchFilter, String> {

	List<Producto> findProductosByUsuario(Usuario user) throws ServicioException;

	void validateSKU(String sku) throws ServicioException;

	Float getTotalInventario(Usuario user) throws ServicioException;
}
