package com.antoniojnavarro.naventory.repository.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.antoniojnavarro.naventory.model.entities.Empresa;
import com.antoniojnavarro.naventory.model.entities.Producto;
import com.antoniojnavarro.naventory.model.filters.ProductoSearchFilter;
import com.antoniojnavarro.naventory.repository.commons.api.BaseRepository;

public interface ProductoRepository extends BaseRepository<Producto, ProductoSearchFilter, String> {

	List<Producto> findProductosByEmpresa(Empresa empresa);

	@Query("SELECT sum(p.precio * p.stock) FROM Producto p where p.empresa = ?1")
	Float getTotalInventario(Empresa empresa);

	Long countByEmpresa(Empresa empresa);

}
